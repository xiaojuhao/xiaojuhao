package com.xjh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.Constants;
import com.xjh.commons.CookieUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsRolesDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsUserRolesDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.service.TkMappers;
import com.xjh.service.UserService;
import com.xjh.valueobject.UserVo;

import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Resource
	UserService userService;
	@Resource
	HttpServletRequest request;

	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login(HttpServletResponse response) {
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");// 密码原文
		//

		if (CommonUtils.isAnyBlank(userCode, password)) {
			return ResultBaseBuilder.fails("入参错误").rb(request);
		}
		WmsUserDO userDO = new WmsUserDO();
		userDO.setUserCode(userCode);
		userDO.setPassword(CommonUtils.md5(password));
		ResultBase<WmsUserDO> loginRs = userService.login(userDO, request, response);
		if (loginRs.getIsSuccess() == false) {
			return ResultBaseBuilder.wrap(loginRs).rb(request);
		}
		UserVo ret = new UserVo();
		ret.setUserName(loginRs.getValue().getUserName());
		ret.setLoginCookie(loginRs.getValue().getLoginCookie());
		ret.setIsSu(loginRs.getValue().getIsSu());
		return ResultBaseBuilder.succ().data(ret).rb(request);
	}

	@RequestMapping(value = "/loginInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object loginInfo(HttpServletResponse response) {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		user.setPassword(null);
		user.setLoginCookie(null);
		return ResultBaseBuilder.succ().data(user).rb(request);
	}

	@RequestMapping(value = "/modifyMyProfile", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object modifyMyProfile(HttpServletResponse response) {
		try {
			WmsUserDO user = AccountUtils.getLoginUser(request);
			if (user == null) {
				return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
			}
			log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
			String userName = CommonUtils.get(request, "userName");
			String password = CommonUtils.get(request, "password");
			String userMobile = CommonUtils.get(request, "userMobile");

			if (StringUtils.isNotBlank(userName)) {
				user.setUserName(userName);
			}
			if (StringUtils.isNotBlank(password)) {
				user.setPassword(CommonUtils.md5(password));
			}
			if(StringUtils.isNotBlank(userMobile)){
				user.setUserMobile(userMobile);
			}
			TkMappers.inst().getUserMapper().updateByPrimaryKey(user);
			return ResultBaseBuilder.succ().rb(request);
		} catch (Exception e) {
			log.error("", e);
			return ResultBaseBuilder.fails("系统异常:" + e.getMessage()).rb(request);
		}
	}

	@RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object logout(HttpServletResponse response) {
		CookieUtils.addCookie(request, response, Constants.WMS_LOGIN_KEY, null, null);
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/queryUsers", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUsers(WmsUserDO userDO) {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		log.info("操作人:{}-{}", user.getUserCode(), user.getUserName());
		PageResult<WmsUserDO> page = this.userService.queryUsers(userDO);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}

	@RequestMapping(value = "/queryUserByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object queryUserByCode() {
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String userCode = CommonUtils.get(request, "userCode");
		if (StringUtils.isBlank(userCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userCode);
		user = TkMappers.inst().getUserMapper().selectOne(user);
		if (user == null) {
			return ResultBaseBuilder.fails("用户信息不存在").rb(request);
		}
		return ResultBaseBuilder.succ().data(user).rb(request);
	}

	@RequestMapping(value = "/saveUser", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object saveUser(WmsUserDO input) {
		if (CommonUtils.isAnyBlank(input.getUserCode())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String userRoleStr = CommonUtils.get(request, "userRoleStr");
		// 密码保存为MD5
		if (StringUtils.isNotBlank(input.getPassword())) {
			input.setPassword(CommonUtils.md5(input.getPassword()));
		}
		// 查询用户是否存在
		ResultBase<WmsUserDO> rs = userService.queryUser(input.getUserCode());
		if (rs.getIsSuccess() == false) {
			// 用户不存在，新建用户
			if (StringUtils.isBlank(input.getPassword())) {
				input.setPassword(CommonUtils.md5("123456"));
			}
			if (CommonUtils.isBlank(input.getUserName())) {
				input.setUserName(input.getUserCode());// 默认username=usercode
			}
			input.setStatus("1");
			TkMappers.inst().getUserMapper().insert(input);
		} else {
			// 更新数据
			WmsUserDO user = rs.getValue();
			if (StringUtils.isNotBlank(input.getUserName()))
				user.setUserName(input.getUserName());
			if (StringUtils.isNotBlank(input.getPassword()))
				user.setPassword(input.getPassword());
			if (StringUtils.isNotBlank(input.getUserMobile()))
				user.setUserMobile(input.getUserMobile());
			if (StringUtils.isNotBlank(input.getIsSu()))
				user.setIsSu(input.getIsSu());
			if (StringUtils.isNotBlank(input.getStoreCode()))
				user.setStoreCode(input.getStoreCode());
			if (StringUtils.isNotBlank(input.getStatus()))
				user.setStatus(input.getStatus());
			user.setAuthStores(input.getAuthStores());
			user.setAuthWarehouse(input.getAuthWarehouse());
			TkMappers.inst().getUserMapper().updateByPrimaryKey(user);
		}

		assert StringUtils.isNotBlank(input.getUserCode());
		///user roles
		WmsUserRolesDO delete = new WmsUserRolesDO();
		delete.setUserCode(input.getUserCode());
		TkMappers.inst().getUserRolesMapper().delete(delete);
		if (userRoleStr != null) {
			Observable.fromArray(userRoleStr.split(",")) //
					.filter((roleCode) -> StringUtils.isNotBlank(roleCode))//
					.map((roleCode) -> {
						WmsRolesDO cond = new WmsRolesDO();
						cond.setRoleCode(roleCode);
						return TkMappers.inst().getRolesMapper().selectOne(cond);
					}) //
					.filter((roleDO) -> roleDO != null) //
					.map((roleDO) -> {
						WmsUserRolesDO role = new WmsUserRolesDO();
						role.setRoleCode(roleDO.getRoleCode());
						role.setRoleName(roleDO.getRoleName());
						role.setUserCode(input.getUserCode());
						return role;
					})//
					.forEach((userRoleDO) -> TkMappers.inst().getUserRolesMapper().insert(userRoleDO))//
			;
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/resetPassword", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object resetPassword() {
		String userCode = CommonUtils.get(request, "userCode");
		if (CommonUtils.isAnyBlank(userCode)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb(request);
		}
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		WmsUserDO user = new WmsUserDO();
		user.setUserCode(userCode);
		user = TkMappers.inst().getUserMapper().selectOne(user);
		if (user != null) {
			WmsUserDO update = new WmsUserDO();
			update.setId(user.getId());
			update.setPassword(CommonUtils.md5("123456"));
			TkMappers.inst().getUserMapper().updateByPrimaryKeySelective(update);
		}
		return ResultBaseBuilder.succ().rb(request);
	}

	@RequestMapping(value = "/mycabins", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object mycabins() {
		WmsUserDO loginUser = AccountUtils.getLoginUser(request);
		if (loginUser == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		List<JSONObject> mycabins = new ArrayList<>();
		List<WmsStoreDO> allStores = TkMappers.inst().getStoreMapper().selectAll();
		List<WmsWarehouseDO> allWarehouses = TkMappers.inst().getWarehouseMapper().selectAll();
		if ("1".equals(loginUser.getIsSu())) {
			allStores.forEach((it) -> {
				mycabins.add(CommonUtils.asJSONObject("cabinCode", it.getStoreCode(), //
						"cabinName", it.getStoreName()));
			});
			allWarehouses.forEach((it) -> {
				mycabins.add(CommonUtils.asJSONObject("cabinCode", it.getWarehouseCode(), //
						"cabinName", it.getWarehouseName()));
			});
		} else {
			List<String> auths = new ArrayList<>();
			auths.addAll(CommonUtils.splitAsList(loginUser.getAuthStores(), ","));
			auths.addAll(CommonUtils.splitAsList(loginUser.getAuthWarehouse(), ","));

			allStores.forEach((it) -> {
				if (auths.contains(it.getStoreCode())) {
					mycabins.add(CommonUtils.asJSONObject("cabinCode", it.getStoreCode(), //
							"cabinName", it.getStoreName()));
				}
			});
			allWarehouses.forEach((it) -> {
				if (auths.contains(it.getWarehouseCode())) {
					mycabins.add(CommonUtils.asJSONObject("cabinCode", it.getWarehouseCode(), //
							"cabinName", it.getWarehouseName()));
				}
			});
		}
		return ResultBaseBuilder.succ().data(mycabins).rb(request);
	}
}
