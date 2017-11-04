package com.xjh.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface SearchService {
	public List<JSONObject> search(String search);
}
