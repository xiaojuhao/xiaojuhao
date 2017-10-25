package com.xjh.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.ResultBase;
import com.xjh.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public ResultBase<JSONObject> queryUser(Long userId) {
		return null;
	}

}
