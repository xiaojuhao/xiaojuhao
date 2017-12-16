package com.xjh.support.oss;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.xjh.commons.WmsProp;

public class OssUtils {
	static WmsProp config = new WmsProp();
	static String endpoint = "oss-cn-beijing.aliyuncs.com";
	static String accessKeyId = config.getProperty("wms.oss.accesskeyid");
	static String accessKeySecret = config.getProperty("wms.oss.accesssecret");
	static OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	static String bucket = "xjhpic";
	static String osspath = "http://xjhpic.oss-cn-beijing.aliyuncs.com/";

	public static String getOssPath() {
		return osspath;
	}

	public static void uploadPicStream(String fileName,InputStream stream) throws Exception {
		uploadStream("image/jpeg",fileName, stream);
	}

	public static void uploadStream(String contentType,String fileName, InputStream stream) throws Exception {
		if (stream == null || StringUtils.isBlank(contentType)) {
			return;
		}
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		client.putObject(bucket, fileName, stream, meta);
	}
}
