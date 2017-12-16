package com.xjh.commons;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WmsProp extends java.util.Properties {
	private static final long serialVersionUID = 1L;

	public WmsProp() {
		try {
			String wmsProp = System.getProperty("user.home") + "/wms.properties";
			File propFile = new File(wmsProp);
			if (propFile.exists()) {
				Properties p = new Properties();
				p.load(new FileReader(propFile));
				for (Object key : p.keySet()) {
					String val = p.getProperty(key.toString());
					if (StringUtils.isNotBlank(val)) {
						this.put(key, val.trim());
					}
				}
			}
		} catch (Exception ex) {
			log.error("", ex);
			throw new RuntimeException("初始化wms配置文件失败");
		}
	}
}
