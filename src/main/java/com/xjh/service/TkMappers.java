package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesFormulaMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesMapper;
import com.xjh.dao.tkmapper.TkWmsSequenceMapper;
import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;

import lombok.Data;

@Service
@Data
public class TkMappers implements InitializingBean{
	@Resource TkWmsSessionMapper sessionMapper;
	@Resource TkWmsUserMapper userMapper;
	@Resource TkWmsMaterialStockMapper materialStockMapper;
	@Resource TkWmsMaterialStockHistoryMapper materialStockHistoryMapper;
	@Resource TkWmsRecipesMapper recipesMapper;
	@Resource TkWmsStoreMapper storeMapper;
	@Resource TkWmsSequenceMapper sequenceMapper;
	@Resource TkWmsMaterialMapper materialMapper;
	@Resource TkWmsWarehouseMapper warehouseMapper;
	@Resource TkWmsRecipesFormulaMapper recipesFormulaMapper;
	@Resource TkWmsSupplierMapper supplierMapper;
	@Resource TkWmsMaterialSupplierMapper materialSupplierMapper;
	
	@Override
    public void afterPropertiesSet() throws Exception {
        holder.setValue(this);
    }
    public static TkMappers inst(){
        return holder.getValue();
    }
    private static class Holder{
    	TkMappers value = null;
        public void setValue(TkMappers value){
            this.value = value;
        }
        public TkMappers getValue(){
            return this.value;
        }
    }
    private static Holder holder = new Holder();
}
