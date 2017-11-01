package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

@Service
public class MaterialServiceImpl implements MaterialService{
	@Resource
	TkWmsMaterialMapper wmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper wmsMaterialStockMapper;
	
	@Override
	public List<WmsMaterialVo> queryMaterials(WmsMaterialDO example) {
		if(example == null){
			example = new WmsMaterialDO();
		}
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsMaterialDO> list = wmsMaterialMapper.select(example);
		List<WmsMaterialVo> ret = new ArrayList<>();
		for(WmsMaterialDO dd : list){
			WmsMaterialVo vo = new WmsMaterialVo();
			ret.add(vo);
			BeanUtils.copyProperties(dd, vo);
		}
		return ret;
	}
	
	@Override
	public List<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example) {
		if(example == null){
			example = new WmsMaterialStockDO();
		}
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsMaterialStockDO> list = wmsMaterialStockMapper.select(example);
		List<WmsMaterialStockVo> ret = new ArrayList<>();
		for(WmsMaterialStockDO dd : list){
			WmsMaterialStockVo vo = new WmsMaterialStockVo();
			ret.add(vo);
			BeanUtils.copyProperties(dd, vo);
		}
		return ret;
	}
	
	
}
