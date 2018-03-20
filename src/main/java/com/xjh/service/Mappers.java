package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.dao.mapper.WmsMaterialSupplierMapper;

import lombok.Data;

@Service
@Data
public class Mappers implements InitializingBean {
	@Resource
	WmsMaterialStockMapper stockMapper;
	@Resource
	WmsMaterialSupplierMapper wmsMaterialSupplierMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		holder.setValue(this);
	}

	public static Mappers inst() {
		return holder.getValue();
	}

	private static class Holder {
		Mappers value = null;

		public void setValue(Mappers value) {
			this.value = value;
		}

		public Mappers getValue() {
			return this.value;
		}
	}

	private static Holder holder = new Holder();
}
