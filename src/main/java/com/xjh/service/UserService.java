package com.xjh.service;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.ResultBase;

public interface UserService {
	public ResultBase<JSONObject> queryUser(Long userId);
}
