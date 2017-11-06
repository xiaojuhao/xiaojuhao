package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesMapper;
import com.xjh.dao.tkmapper.TkWmsSequenceMapper;
import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;

import lombok.Data;

@Service
@Data
public class Mappers implements InitializingBean{
	@Resource TkWmsSessionMapper sessionMapper;
	@Resource TkWmsUserMapper userMapper;
	@Resource TkWmsMaterialStockMapper materialStockMapper;
	@Resource TkWmsMaterialStockHistoryMapper materialStockHistoryMapper;
	@Resource TkWmsRecipesMapper recipesMapper;
	@Resource TkWmsStoreMapper storeMapper;
	@Resource TkWmsSequenceMapper sequenceMapper;
	@Resource TkWmsMaterialMapper materialMapper;
	@Resource TkWmsWarehouseMapper warehouseMapper;
	@Override
    public void afterPropertiesSet() throws Exception {
        holder.setValue(this);
    }
    public static Mappers inst(){
        return holder.getValue();
    }
    private static class Holder{
    	Mappers value = null;
        public void setValue(Mappers value){
            this.value = value;
        }
        public Mappers getValue(){
            return this.value;
        }
    }
    private static Holder holder = new Holder();
}
