package com.xjh.support.datasource;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.xjh.commons.WmsProp;

@Component
public class DataBaseProperties extends java.util.Properties {
	private static final long serialVersionUID = 1L;

	public DataBaseProperties() throws IOException {
		Properties p = new WmsProp();
		for (Object key : p.keySet()) {
			String val = p.getProperty(key.toString());
			if (StringUtils.isNotBlank(val)) {
				this.put(key, val.trim());
			}
		}
	}
}
