package com.xjh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialSpecService {
	@Resource
	TkWmsMaterialSpecMapper specMapper;
	@Resource
	TkWmsMaterialSpecDetailMapper specDetailMapper;
	@Resource
	SequenceService sequence;
	@Resource
	MaterialService materialService;

	public WmsMaterialSpecDO queryByCode(String specCode) {
		if (StringUtils.isBlank(specCode)) {
			return null;
		}
		WmsMaterialSpecDO cond = new WmsMaterialSpecDO();
		cond.setSpecCode(specCode);
		WmsMaterialSpecDO spec = specMapper.selectOne(cond);
		return spec;
	}

	public List<WmsMaterialSpecDetailDO> queryMaterialSepcsByMaterialCode(String materialCode) {
		if (StringUtils.isBlank(materialCode)) {
			return new ArrayList<>();
		}
		WmsMaterialSpecDetailDO cond = new WmsMaterialSpecDetailDO();
		cond.setMaterialCode(materialCode);
		cond.setIsDeleted("N");
		List<WmsMaterialSpecDetailDO> list = this.specDetailMapper.select(cond);
		return list;
	}

	public WmsMaterialSpecDetailDO queryFirstSpecDetail(String materialCode) {
		if (CommonUtils.isAnyBlank(materialCode)) {
			return null;
		}
		WmsMaterialSpecDetailDO cond = new WmsMaterialSpecDetailDO();
		cond.setMaterialCode(materialCode);
		PageHelper.startPage(1, 1);
		PageHelper.orderBy("id");
		WmsMaterialSpecDetailDO spec = specDetailMapper.selectOne(cond);
		return spec;
	}

	private static Cache<String, WmsMaterialSpecDetailDO> cache = CacheBuilder.newBuilder() //
			.maximumSize(600) // 最多缓存的条数
			.expireAfterWrite(5, TimeUnit.MINUTES) // 缓存时间
			.build();

	public WmsMaterialSpecDetailDO querySpecDetailByCode(String materialCode, String specCode) {
		if (CommonUtils.isAnyBlank(materialCode, specCode)) {
			return null;
		}
		String key = "querySpecDetailByCode_" + materialCode + "_" + specCode;
		WmsMaterialSpecDetailDO t = cache.getIfPresent(key);
		if (t != null) {
			return t;
		}
		if ("MS000000".equals(specCode)) {
			WmsMaterialDO material = materialService.queryMaterialByCode(materialCode);
			if (material == null) {
				return null;
			}
			WmsMaterialSpecDetailDO spec = new WmsMaterialSpecDetailDO();
			spec.setSpecCode(specCode);
			spec.setSpecName(material.getStockUnit());
			spec.setSpecUnit(material.getStockUnit());
			spec.setStockUnit(material.getStockUnit());
			spec.setTransRate(BigDecimal.ONE);
			spec.setUtilizationRatio(100);
			cache.put(key, spec);
			return spec;
		}
		WmsMaterialSpecDetailDO cond = new WmsMaterialSpecDetailDO();
		cond.setSpecCode(specCode);
		cond.setMaterialCode(materialCode);
		WmsMaterialSpecDetailDO spec = specDetailMapper.selectOne(cond);
		if (spec != null)
			cache.put(key, spec);
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
