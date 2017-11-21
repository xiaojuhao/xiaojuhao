package com.xjh.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsNoticeDO;
import com.xjh.dao.tkmapper.TkWmsNoticeMapper;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Resource
	HttpServletRequest request;
	@Resource
	TkWmsNoticeMapper noticeMapper;

	@RequestMapping(value = "/lastest", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object lastest() {
		WmsNoticeDO cond = new WmsNoticeDO();
		cond.setStatus("1");
		PageResult<WmsNoticeDO> page = new PageResult<>();
		Example example = new Example(WmsNoticeDO.class, false, false);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo(cond);
		c.andLessThan("gmtExpired", new Date());
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		PageHelper.orderBy("gmt_created desc");
		List<WmsNoticeDO> list = noticeMapper.selectByExample(example);
		page.setValues(list);
		return ResultBaseBuilder.succ().data(page).rb(request);
	}
}
