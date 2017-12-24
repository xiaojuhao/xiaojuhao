package com.xjh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.dao.dataobject.WmsMaterialSpecDO;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialSpecService {
	@Resource
	TkWmsMaterialSpecMapper specMapper;
	@Resource
	SequenceService sequence;

	public WmsMaterialSpecDO queryByCode(String specCode) {
		if (StringUtils.isBlank(specCode)) {
			return null;
		}
		WmsMaterialSpecDO cond = new WmsMaterialSpecDO();
		cond.setSpecCode(specCode);
		WmsMaterialSpecDO spec = specMapper.selectOne(cond);
		return spec;
	}

	public List<WmsMaterialSpecDO> queryByExample(WmsMaterialSpecDO cond) {
		if (cond == null) {
			return new ArrayList<>();
		}
		PageHelper.orderBy("id desc");
		PageHelper.startPage(cond.getPageNo(), cond.getPageSize());
		return specMapper.select(cond);
	}

	public int updateByCode(WmsMaterialSpecDO spec) {
		if (spec == null || StringUtils.isBlank(spec.getSpecCode())) {
			return 0;
		}
		Example example = new Example(WmsMaterialSpecDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("specCode", spec.getSpecCode());
		return specMapper.updateByExampleSelective(spec, example);
	}

	public int insert(WmsMaterialSpecDO spec) {
		return specMapper.insert(spec);
	}

	public String nextSpecCode() {
		long id = sequence.next("material_spec");
		return "MS" + StringUtils.leftPad(id + "", 6, "0");
	}
}
