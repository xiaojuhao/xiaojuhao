package com.xjh.support.datasource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DataBaseProperties extends java.util.Properties {
	private static final long serialVersionUID = 1L;

	public DataBaseProperties() throws IOException {
		String wmsProp = System.getProperty("user.home") + "/database.properties";
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
	}
}
