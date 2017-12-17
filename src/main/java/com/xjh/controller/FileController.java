package com.xjh.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsUploadFilesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DictService;
import com.xjh.service.OssService;
import com.xjh.service.TkMappers;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {
	@Resource
	HttpServletRequest request;
	@Resource
	DictService dict;
	@Resource
	OssService ossService;

	ExecutorService executor = Executors.newSingleThreadExecutor();

	@RequestMapping(value = "/show", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object show(HttpServletResponse response) {
		String image = request.getParameter("image");
		if (StringUtils.isBlank(image)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsUploadFilesDO fileDO = new WmsUploadFilesDO();
		fileDO.setFileName(image);
		fileDO = TkMappers.inst().getUploadFilesMapper().selectOne(fileDO);
		if (fileDO == null) {
			return ResultBaseBuilder.fails("文件不存在").rb(request);
		}
		//
		if ("local".equals(fileDO.getFileLocation())) {
			File file = new File(fileDO.getFilePath() + "/" + fileDO.getFileName());
			if (!file.exists()) {
				return ResultBaseBuilder.fails("文件不存在").rb(request);
			}
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-store,no-cache");
			response.addHeader("Cache-Control", "post-check=0,pre-check=0");
			response.setHeader("Pragma", "no-cache");
			response.setContentType("image/jpeg");
			OutputStream out = null;
			try {
				BufferedImage bi = ImageIO.read(file);
				out = response.getOutputStream();
				ImageIO.write(bi, "png", out);
				out.flush();
			} catch (Exception e) {

			} finally {
				IOUtils.closeQuietly(out);
			}
		}
		if ("oss".equals(fileDO.getFileLocation())) {
			if (StringUtils.isBlank(fileDO.getFilePath())) {
				return null;
			}
			try {
				String url = fileDO.getFilePath();
				if (!url.endsWith("/")) {
					url = url + "/";
				}
				response.sendRedirect(url + fileDO.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object upload() {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			JSONObject ret = new JSONObject();

			List<WmsUploadFilesDO> files = new ArrayList<>();
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);
				if (user == null) {
					user = AccountUtils.getLoginUser(multiRequest);
				}
				if (user == null) {
					return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
				}
				File dir = new File(System.getProperty("user.home") + "/wmsuploadfiles/");
				if (dir.exists() == false) {
					dir.mkdirs();
				}
				Iterator<String> iter = multiRequest.getFileNames();

				String busiNo = CommonUtils.get(multiRequest, "busiNo");
				if (StringUtils.isBlank(busiNo)) {
					busiNo = CommonUtils.uuid();
				}
				ret.put("busiNo", busiNo);
				while (iter.hasNext()) {
					String localFileName = CommonUtils.uuid();
					ret.put("filename", localFileName);
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						String suffix = CommonUtils.fileSuffix(file.getOriginalFilename());
						String path = dir.getAbsolutePath() + "/" + localFileName + "." + suffix;
						File dst = new File(path);
						file.transferTo(dst);
						WmsUploadFilesDO dd = new WmsUploadFilesDO();
						dd.setBusiNo(busiNo);
						dd.setContentType("application/octet-stream");
						dd.setCreator(user == null ? "system" : user.getUserCode());
						dd.setFileName(dst.getName());
						dd.setFileOriName(file.getOriginalFilename());
						dd.setFileLocation("local");
						dd.setFilePath(dir.getAbsolutePath() + "/");
						dd.setGmtCreated(new Date());
						files.add(dd);
						//如果是图片，则压缩
						if (CommonUtils.isImage(suffix)) {
							dd.setContentType("image/jpg");
							if (dst.length() > 2097152) { //大于2M
								String minPath = dir.getAbsolutePath() + "/" + localFileName + "-min." + suffix;
								File minDst = new File(minPath);
								Thumbnails.of(dst).width(1600).height(1200).outputQuality(1.0).toFile(minDst);
								dd.setFileName(minDst.getName());
								dst.delete();//删除原始文件
							}
						}
					}
				}
			}
			// 保存数据库
			for (WmsUploadFilesDO file : files) {
				TkMappers.inst().getUploadFilesMapper().insert(file);
				executor.submit(() -> {
					try {
						ossService.upload(file);
					} catch (Exception ex) {
						log.error("", ex);
					}
				});
			}
			return ResultBaseBuilder.succ().data(ret).rb(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultBaseBuilder.fails("上传文件失败").rb(request);
	}

	@RequestMapping(value = "/uploadRaw", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object uploadRaw() {
		try {
			System.out.println("=================params==================");
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String p = e.nextElement();
				System.out.println(p + ":" + request.getParameter(p));
			}
			System.out.println("=================headers==================");
			Enumeration<String> h = request.getHeaderNames();
			while (h.hasMoreElements()) {
				String n = h.nextElement();
				System.out.println(n + ":" + request.getHeader(n));
			}
			//
			// InputStream in = request.getInputStream();
			// int len = request.getContentLength();
			// int size = 0;
			// int total = 0;
			// byte[] data = new byte[len];
			// while (total < len && size >= 0) {
			// size = in.read(data, total, len);
			// total += size;
			// }
			// System.out.println("read data size = " + total);
			// System.out.println(new String(data));
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println("------------------------------------------------");
				System.out.println(line);
			}
			br.close();
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails(ex.getMessage()).rb(request);
		}
	}

	public static void main(String[] args) {
		System.out.println(new File("/Users/yinguoliang/Pictures/1FFB015EBB9E4469931C2C39414C9916.png").length());
	}
}
