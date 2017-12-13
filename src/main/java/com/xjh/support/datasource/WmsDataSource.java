package com.xjh.support.datasource;

import java.io.IOException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class WmsDataSource extends DriverManagerDataSource {
	public WmsDataSource() throws IOException {
		DataBaseProperties prop = new DataBaseProperties();
		super.setDriverClassName("com.mysql.jdbc.Driver");
		super.setUrl(prop.getProperty("wms.mysql.url"));
		super.setUsername(prop.getProperty("wms.mysql.username"));
		super.setPassword(prop.getProperty("wms.mysql.password"));
	}
}
