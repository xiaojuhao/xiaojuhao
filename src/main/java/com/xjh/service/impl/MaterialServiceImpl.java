package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.mapper.WmsMaterialMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.vo.WmsMaterialVo;

@Service
public class MaterialServiceImpl implements MaterialService{
	@Resource
	WmsMaterialMapper wmsMaterialMapper;

	@Override
	public List<WmsMaterialVo> queryAllMaterials() {
		WmsMaterialDO example = new WmsMaterialDO();
		List<WmsMaterialDO> list = wmsMaterialMapper.selectByExample(example);
		List<WmsMaterialVo> ret = new ArrayList<>();
		for(WmsMaterialDO dd : list){
			WmsMaterialVo vo = new WmsMaterialVo();
			ret.add(vo);
			BeanUtils.copyProperties(dd, vo);
		}
		return ret;
	}
	
	
}
