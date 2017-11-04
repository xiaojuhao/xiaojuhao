package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

@Service
public class MaterialServiceImpl implements MaterialService {
	@Resource
	TkWmsMaterialMapper wmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper wmsMaterialStockMapper;

	@Override
	public ResultBase<Boolean> initMaterials() {
		List<WmsMaterialDO> materials = wmsMaterialMapper.selectAll();
		for (WmsMaterialDO m : materials) {
			WmsMaterialStockDO stock = new WmsMaterialStockDO();
			stock.setMaterialCode(m.getMaterialCode());
			stock.setStockType("1");
			List<WmsMaterialStockDO> stocks = wmsMaterialStockMapper.select(stock);
			if (stocks == null || stocks.size() == 0) {
				WmsMaterialStockDO insert = new WmsMaterialStockDO();
				CommonUtils.copyPropertiesQuietly(insert, m);
				insert.setStoreCode("M000");
				insert.setStoreName("总库");
				insert.setStockType("1");
				insert.setCurrStock(0D);
				insert.setUsedStock(0D);
				this.wmsMaterialStockMapper.insert(insert);
			}
		}
		return ResultBaseBuilder.succ().data(true).rb();
	}

	@Override
	public PageResult<WmsMaterialVo> queryMaterials(WmsMaterialDO example) {
		PageResult<WmsMaterialVo> page = new PageResult<WmsMaterialVo>();
		if (example == null) {
			example = new WmsMaterialDO();
		}
		int totalRows = this.wmsMaterialMapper.selectCount(example);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsMaterialDO> list = wmsMaterialMapper.select(example);
		List<WmsMaterialVo> ret = new ArrayList<>();
		for (WmsMaterialDO dd : list) {
			WmsMaterialVo vo = new WmsMaterialVo();
			ret.add(vo);
			BeanUtils.copyProperties(dd, vo);
		}
		page.setPageNo(example.getPageNo());
		page.setPageSize(example.getPageSize());
		page.setTotalRows(totalRows);
		page.setValues(ret);
		return page;
	}

	@Override
	public int addMaterial(WmsMaterialDO example) {
		if (example == null) {
			return 0;
		}
		int i = wmsMaterialMapper.insert(example);
		if (i > 0) {
			WmsMaterialStockDO stock = new WmsMaterialStockDO();
			stock.setMaterialCode(example.getMaterialCode());
			stock.setMaterialName(example.getMaterialName());
			stock.setStockUnit(example.getStockUnit());
			stock.setStockType("1");
			stock.setStoreCode("M000");
			stock.setStoreName("总库");
			stock.setCurrStock(0D);
			stock.setUsedStock(0D);
			wmsMaterialStockMapper.insert(stock);
		}
		return i;
	}

	@Override
	public PageResult<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example) {
		PageResult<WmsMaterialStockVo> page = new PageResult<>();
		page.setTotalRows(0);
		if (example == null) {
			example = new WmsMaterialStockDO();
		}
		int totalRows = this.wmsMaterialStockMapper.selectCount(example);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		PageHelper.orderBy("id");
		List<WmsMaterialStockDO> list = wmsMaterialStockMapper.select(example);
		List<WmsMaterialStockVo> ret = new ArrayList<>();
		for (WmsMaterialStockDO dd : list) {
			WmsMaterialStockVo vo = new WmsMaterialStockVo();
			ret.add(vo);
			BeanUtils.copyProperties(dd, vo);
		}
		page.setTotalRows(totalRows);
		page.setPageNo(example.getPageNo());
		page.setPageSize(example.getPageSize());
		page.setValues(ret);
		return page;
	}

}
