package com.xjh.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsUploadFilesDO;
import com.xjh.support.oss.OssUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OssService {
	public void upload(WmsUploadFilesDO file) throws Exception {
		if (file == null || file.getId() == null) {
			log.error("上传OSS文件失败:{}", CommonUtils.reflectString(file));
			return;
		}
		WmsUploadFilesDO cond = new WmsUploadFilesDO();
		cond.setId(file.getId());
		cond.setBusiNo(file.getBusiNo());
		cond.setFileLocation("local");
		file = TkMappers.inst().getUploadFilesMapper().selectOne(cond);
		if (file == null) {
			log.error("上传OSS文件失败:{}", CommonUtils.reflectString(file));
			return;
		}
		File localFile = new File(file.getFilePath() + "/" + file.getFileName());
		if (!localFile.exists()) {
			log.error("上传OSS文件失败,文件不存在,{}", CommonUtils.reflectString(file));
			return;
		}
		try (InputStream stream = new FileInputStream(localFile)) {
			OssUtils.uploadStream(file.getContentType(), file.getFileName(), stream);
			file.setFileLocation("oss");
			file.setFilePath(OssUtils.getOssPath());
			TkMappers.inst().getUploadFilesMapper().updateByPrimaryKeySelective(file);
		}
		localFile.delete();
	}
}
