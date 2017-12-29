package com.xjh.eventbus.evthandles;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.xjh.commons.CommonUtils;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialSpecDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialSupplierDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.tkmapper.TkWmsMaterialSpecDetailMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesFormulaMapper;
import com.xjh.eventbus.BusCruise;

import tk.mybatis.mapper.entity.Example;

@Component
public class MaterialChangeHandler implements InitializingBean {
	@Resource
	TkWmsMaterialSpecDetailMapper tkWmsMaterialSpecDetailMapper;
	@Resource
	TkWmsMaterialStockMapper tkWmsMaterialStockMapper;
	@Resource
	TkWmsMaterialSupplierMapper tkWmsMaterialSupplierMapper;
	@Resource
	TkWmsRecipesFormulaMapper tkWmsRecipesFormulaMapper;

	@Subscribe
	public void handle(MaterialChange mc) {
		if (mc == null) {
			return;
		}
		WmsMaterialDO m = mc.material;
		if (m == null || CommonUtils.isAnyBlank(m.getMaterialCode(), m.getMaterialName())) {
			return;
		}
		updateMaterialSpecDetail(m);
		updateMaterialStock(m);
		updateMaterialSupplier(m);
		updateRecipesFormula(m);
	}

	private void updateMaterialSpecDetail(WmsMaterialDO m) {
		Example example = new Example(WmsMaterialSpecDetailDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("materialCode", m.getMaterialCode());
		WmsMaterialSpecDetailDO data = new WmsMaterialSpecDetailDO();
		data.setMaterialName(m.getMaterialName());
		data.setStockUnit(m.getStockUnit());
		tkWmsMaterialSpecDetailMapper.updateByExampleSelective(data, example);
	}

	private void updateMaterialStock(WmsMaterialDO m) {
		Example example = new Example(WmsMaterialStockDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("materialCode", m.getMaterialCode());
		WmsMaterialStockDO data = new WmsMaterialStockDO();
		data.setMaterialName(m.getMaterialName());
		data.setStockUnit(m.getStockUnit());
		tkWmsMaterialStockMapper.updateByExampleSelective(data, example);
	}

	private void updateMaterialSupplier(WmsMaterialDO m) {
		Example example = new Example(WmsMaterialSupplierDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("materialCode", m.getMaterialCode());
		WmsMaterialSupplierDO data = new WmsMaterialSupplierDO();
		data.setMaterialName(m.getMaterialName());
		tkWmsMaterialSupplierMapper.updateByExampleSelective(data, example);
	}

	private void updateRecipesFormula(WmsMaterialDO m) {
		Example example = new Example(WmsRecipesFormulaDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("materialCode", m.getMaterialCode());
		WmsRecipesFormulaDO data = new WmsRecipesFormulaDO();
		data.setMaterialName(m.getMaterialName());
		tkWmsRecipesFormulaMapper.updateByExampleSelective(data, example);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		BusCruise.registerHandler(this);
	}

}
