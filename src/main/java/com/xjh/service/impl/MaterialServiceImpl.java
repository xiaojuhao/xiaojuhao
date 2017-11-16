package com.xjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import com.xjh.service.vo.WmsMaterialStockVo;
import com.xjh.service.vo.WmsMaterialVo;

import tk.mybatis.mapper.entity.Example;

@Service
public class MaterialServiceImpl implements MaterialService {
	@Resource
	TkWmsMaterialMapper tkWmsMaterialMapper;
	@Resource
	TkWmsMaterialStockMapper tkWmsMaterialStockMapper;

	@Override
	public ResultBase<Boolean> initMaterialStock(String materialCode, String cabinCode) {
		WmsMaterialDO example = new WmsMaterialDO();
		example.setMaterialCode(materialCode);
		List<WmsMaterialDO> materials = tkWmsMaterialMapper.select(example);
		for (WmsMaterialDO m : materials) {
			WmsMaterialStockDO stock = new WmsMaterialStockDO();
			stock.setMaterialCode(m.getMaterialCode());
			stock.setStockType("1");
			List<WmsMaterialStockDO> stocks = tkWmsMaterialStockMapper.select(stock);
			if (stocks == null || stocks.size() == 0) {
				WmsMaterialStockDO insert = new WmsMaterialStockDO();
				insert.setMaterialCode(m.getMaterialCode());
				insert.setMaterialName(m.getMaterialName());
				insert.setStockUnit(m.getStockUnit());
				insert.setCabinCode("ZK0000");
				insert.setCabinName("总库");
				insert.setCabinType("0");
				insert.setStockType("1");
				insert.setStockUnit(m.getStockUnit());
				insert.setCurrStock(0D);
				insert.setUsedStock(0D);
				this.tkWmsMaterialStockMapper.insert(insert);
			}
			if (StringUtils.isNotBlank(cabinCode)) {
				String cabinName = null;
				String cabinType = cabinCode.startsWith("WH") ? "1" : "2";
				WmsMaterialStockDO fenku = new WmsMaterialStockDO();
				fenku.setMaterialCode(materialCode);
				fenku.setCabinCode(cabinCode);
				fenku.setStockType("2");
				fenku = TkMappers.inst().getMaterialStockMapper().selectOne(fenku);
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
				if (fenku == null) {
					fenku = new WmsMaterialStockDO();
					fenku.setMaterialCode(materialCode);
					fenku.setMaterialName(m.getMaterialName());
					fenku.setCabinCode(cabinCode);
					fenku.setCabinType(cabinType);
					fenku.setCabinName(cabinName);
					fenku.setCurrStock(0D);
					fenku.setUsedStock(0D);
					fenku.setStockType("2");
					fenku.setStockUnit(m.getStockUnit());
					this.tkWmsMaterialStockMapper.insert(fenku);
				}
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
		int totalRows = this.tkWmsMaterialMapper.selectCount(example);
		PageHelper.startPage(example.getPageNo(), example.getPageSize());
		List<WmsMaterialDO> list = tkWmsMaterialMapper.select(example);
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
	public PageResult<WmsMaterialStockVo> queryMaterialsStock(WmsMaterialStockDO example, WmsUserDO user) {
		PageResult<WmsMaterialStockVo> page = new PageResult<>();
		page.setTotalRows(0);
		if (example == null) {
			example = new WmsMaterialStockDO();
		}

		Example exam = new Example(WmsMaterialStockDO.class, false, false);
		Example.Criteria cri = exam.createCriteria();
		cri.andEqualTo(example);
		if (user != null && !"1".equals(user.getUserRole())) {
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

	@Override
	public WmsMaterialDO getMaterialByCode(String code) {
		WmsMaterialDO m = new WmsMaterialDO();
		m.setMaterialCode(code);
		m = tkWmsMaterialMapper.selectOne(m);
		return m;
	}

}
