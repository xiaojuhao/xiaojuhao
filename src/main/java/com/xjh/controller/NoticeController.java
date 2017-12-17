package com.xjh.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.AccountUtils;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsNoticeDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsNoticeMapper;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsNoticeMapper noticeMapper;

	@RequestMapping(value = "/latest", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object latest() {
		WmsUserDO user = AccountUtils.getLoginUser(request);
		if (user == null) {
			return ResultBaseBuilder.fails(ResultCode.no_login).rb(request);
		}
		String msgType = CommonUtils.get(request, "msgType");
		WmsNoticeDO cond = new WmsNoticeDO();
		cond.setStatus("1");
		cond.setMsgType(msgType);
		cond.setPageSize(CommonUtils.getPageSize(request));
		cond.setPageNo(CommonUtils.getPageNo(request));
		PageResult<WmsNoticeDO> page = new PageResult<>();
		Example example = new Example(WmsNoticeDO.class, false, false);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo(cond);
		c.andGreaterThan("gmtExpired", new Date());
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc, id desc");
		List<WmsNoticeDO> list = noticeMapper.selectByExample(example);
		int totalRows = noticeMapper.selectCountByExample(example);
		page.setValues(list);
		page.setTotalRows(totalRows);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}
}
