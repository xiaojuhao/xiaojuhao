package com.xjh.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsDictDO;
import com.xjh.dao.dataobject.WmsUploadFilesDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.service.DictService;
import com.xjh.service.TkMappers;

@Controller
@RequestMapping("/file")
public class FileController {
	@Resource
	HttpServletRequest request;
	@Resource
	DictService dict;

	@RequestMapping(value = "/show", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object show(HttpServletResponse response) {
		String image = request.getParameter("image");
		if (StringUtils.isBlank(image)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsDictDO imagepath = dict.query("DEFAULT", "image_path");
		if (imagepath == null) {
			return ResultBaseBuilder.fails("配置错误").rb(request);
		}
		File file = new File(imagepath.getDictVal() + image);
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
				WmsDictDO imagepath = dict.query("DEFAULT", "image_path");
				if (imagepath == null) {
					return ResultBaseBuilder.fails("上传文件失败").rb(request);
				}
				MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);
				Iterator<String> iter = multiRequest.getFileNames();
				if (user == null) {
					user = AccountUtils.getLoginUser(multiRequest);
				}
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
						String path = imagepath.getDictVal() + localFileName + ".jpg";
						file.transferTo(new File(path));
						WmsUploadFilesDO dd = new WmsUploadFilesDO();
						dd.setBusiNo(busiNo);
						dd.setContentType("image/jpeg");
						dd.setCreator(user == null ? "system" : user.getUserCode());
						dd.setFileName(localFileName);
						dd.setFileOriName(file.getOriginalFilename());
						dd.setFileLocation("local");
						dd.setFilePath(imagepath.getDictVal());
						dd.setGmtCreated(new Date());
						files.add(dd);
					}
				}
			}
			// 保存数据库
			for (WmsUploadFilesDO file : files) {
				TkMappers.inst().getUploadFilesMapper().insert(file);
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
		System.out.println(Long.toHexString(24 * 60 * 60 * 1000));
	}
}
