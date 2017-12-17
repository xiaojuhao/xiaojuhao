package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.PageResult;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsStoreDO;
import com.xjh.dao.dataobject.WmsUserDO;
import com.xjh.dao.dataobject.WmsWarehouseDO;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.service.MaterialService;
import com.xjh.service.TkMappers;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialServiceImpl implements MaterialService {
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper tkWmsMaterialStockMapper;

	@Override
	public ResultBase<WmsMaterialStockDO> initMaterialStock(String materialCode, String cabinCode) {
		WmsMaterialDO example = new WmsMaterialDO();
		example.setMaterialCode(materialCode);
		WmsMaterialDO material = tkWmsMaterialMapper.selectOne(example);
		String cabinName = null;
		String cabinType = cabinCode.startsWith("WH") ? "1" : "2";
		WmsMaterialStockDO stockDO = new WmsMaterialStockDO();
		stockDO.setMaterialCode(materialCode);
		stockDO.setCabinCode(cabinCode);
		stockDO = TkMappers.inst().getMaterialStockMapper().selectOne(stockDO);
		if (cabinCode.startsWith("WH")) {
			WmsWarehouseDO warehouse = new WmsWarehouseDO();
			warehouse.setWarehouseCode(cabinCode);
			warehouse = TkMappers.inst().getWarehouseMapper().selectOne(warehouse);
			cabinName = warehouse.getWarehouseName();
		} else if (cabinCode.startsWith("MD")) {
			WmsStoreDO store = new WmsStoreDO();
			store.setStoreCode(cabinCode);
			store = TkMappers.inst().getStoreMapper().selectOne(store);
			cabinName = store.getStoreName();
		}
		if (stockDO == null) {
			stockDO = new WmsMaterialStockDO();
			stockDO.setMaterialCode(materialCode);
			stockDO.setMaterialName(material.getMaterialName());
			stockDO.setCabinCode(cabinCode);
			stockDO.setCabinType(cabinType);
			stockDO.setCabinName(cabinName);
			stockDO.setCurrStock(0D);
			stockDO.setStatus("1");
			stockDO.setStockUnit(material.getStockUnit());
			stockDO.setGmtModified(new Date());
			stockDO.setModifier("system");
			stockDO.setWarningStock(0D);
			this.tkWmsMaterialStockMapper.insert(stockDO);
		}
		return ResultBaseBuilder.succ().data(stockDO).rb();
	}

	@Override
	public PageResult<WmsMaterialDO> queryMaterials(WmsMaterialDO example) {
		PageResult<WmsMaterialDO> page = new PageResult<WmsMaterialDO>();
		if (example == null) {
			example = new WmsMaterialDO();
		}
		int totalRows = this.tkWmsMaterialMapper.selectCount(example);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsMaterialDO> list = tkWmsMaterialMapper.select(example);
		// List<WmsMaterialVo> ret = new ArrayList<>();
		// for (WmsMaterialDO dd : list) {
		// WmsMaterialVo vo = new WmsMaterialVo();
		// ret.add(vo);
		// BeanUtils.copyProperties(dd, vo);
		// }
		page.setPageNo(example.getPageNo());
		page.setPageSize(example.getPageSize());
		page.setTotalRows(totalRows);
		page.setValues(list);
		return page;
	}

	@Override
	public int insertMaterial(WmsMaterialDO example) {
		if (example == null) {
			return 0;
		}
		int i = tkWmsMaterialMapper.insert(example);
		return i;
	}

	@Override
	public int updateMaterial(WmsMaterialDO example) {
		if (example == null) {
			return 0;
		}
		int i = this.tkWmsMaterialMapper.updateByPrimaryKeySelective(example);
		return i;
	}

	@Override
	public PageResult<WmsMaterialStockDO> queryMaterialsStock(WmsMaterialStockDO example, WmsUserDO user) {
		PageResult<WmsMaterialStockDO> page = new PageResult<>();
		page.setTotalRows(0);
		if (example == null) {
			example = new WmsMaterialStockDO();
		}

		Example exam = new Example(WmsMaterialStockDO.class, false, false);
		Example.Criteria cri = exam.createCriteria();
		cri.andEqualTo(example);
		if (user != null && !"1".equals(user.getIsSu())) {
			List<String> cabinCodes = new ArrayList<>();
			if (StringUtils.isNotBlank(user.getAuthStores())) {
				String[] arr = user.getAuthStores().split(",");
				for (String s : arr)
					cabinCodes.add(s);
			}
			if (StringUtils.isNotBlank(user.getAuthWarehouse())) {
				String[] arr = user.getAuthWarehouse().split(",");
				for (String s : arr)
					cabinCodes.add(s);
			}
			cri.andIn("cabinCode", cabinCodes);
		}
		int totalRows = this.tkWmsMaterialStockMapper.selectCountByExample(exam);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		PageHelper.orderBy("id");
		List<WmsMaterialStockDO> list = tkWmsMaterialStockMapper.selectByExample(exam);
		page.setTotalRows(totalRows);
		page.setPageNo(example.getPageNo());
		page.setPageSize(example.getPageSize());
		page.setValues(list);
		return page;
	}

	@Override
	public WmsMaterialDO getMaterialByCode(String code) {
		WmsMaterialDO m = new WmsMaterialDO();
		m.setMaterialCode(code);
		m = tkWmsMaterialMapper.selectOne(m);
		return m;
	}

}
