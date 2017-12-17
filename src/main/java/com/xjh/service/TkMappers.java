package com.xjh.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.dao.tkmapper.TkWmsDictMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyDetailMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialDepleteReportMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSplitMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockDailyMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockHistoryMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialStockMapper;
import com.xjh.dao.tkmapper.TkWmsMaterialSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsMenuMapper;
import com.xjh.dao.tkmapper.TkWmsNoticeMapper;
import com.xjh.dao.tkmapper.TkWmsOrderMapper;
import com.xjh.dao.tkmapper.TkWmsOrdersMaterialMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesFormulaMapper;
import com.xjh.dao.tkmapper.TkWmsRecipesMapper;
import com.xjh.dao.tkmapper.TkWmsRolesMapper;
import com.xjh.dao.tkmapper.TkWmsRolesMenusMapper;
import com.xjh.dao.tkmapper.TkWmsSequenceMapper;
import com.xjh.dao.tkmapper.TkWmsSessionMapper;
import com.xjh.dao.tkmapper.TkWmsStoreMapper;
import com.xjh.dao.tkmapper.TkWmsSupplierMapper;
import com.xjh.dao.tkmapper.TkWmsTaskMapper;
import com.xjh.dao.tkmapper.TkWmsTimerJobMapper;
import com.xjh.dao.tkmapper.TkWmsUploadFilesMapper;
import com.xjh.dao.tkmapper.TkWmsUserMapper;
import com.xjh.dao.tkmapper.TkWmsUserRolesMapper;
import com.xjh.dao.tkmapper.TkWmsWarehouseMapper;

import lombok.Data;

@Service
@Data
public class TkMappers implements InitializingBean {
	@Resource
	TkWmsSessionMapper sessionMapper;
	@Resource
	TkWmsUserMapper userMapper;
	@Resource
	TkWmsMaterialStockMapper materialStockMapper;
	@Resource
	TkWmsMaterialStockHistoryMapper materialStockHistoryMapper;
	@Resource
	TkWmsRecipesMapper recipesMapper;
	@Resource
	TkWmsStoreMapper storeMapper;
	@Resource
	TkWmsSequenceMapper sequenceMapper;
	@Resource
	TkWmsMaterialMapper materialMapper;
	@Resource
	TkWmsWarehouseMapper warehouseMapper;
	@Resource
	TkWmsRecipesFormulaMapper recipesFormulaMapper;
	@Resource
	TkWmsSupplierMapper supplierMapper;
	@Resource
	TkWmsMaterialSupplierMapper materialSupplierMapper;
	@Resource
	TkWmsDictMapper dictMapper;
	@Resource
	TkWmsMenuMapper menuMapper;
	@Resource
	TkWmsMaterialSplitMapper materialSplitMapper;
	@Resource
	TkWmsInventoryApplyMapper purchaseOrderMapper;
	@Resource
	TkWmsInventoryApplyDetailMapper purchaseOrderDetailMapper;
	@Resource
	TkWmsUploadFilesMapper uploadFilesMapper;
	@Resource
	TkWmsRolesMapper rolesMapper;
	@Resource
	TkWmsRolesMenusMapper rolesMenusMapper;
	@Resource
	TkWmsUserRolesMapper userRolesMapper;
	@Resource
	TkWmsOrderMapper ordersMapper;
	@Resource
	TkWmsOrdersMaterialMapper ordersMaterialMapper;
	@Resource
	TkWmsTaskMapper taskMapper;
	@Resource
	TkWmsMaterialStockDailyMapper stockDailyMapper;
	@Resource
	TkWmsTimerJobMapper timerJobMapper;
	@Resource
	TkWmsNoticeMapper noticeMapper;
	@Resource
	TkWmsMaterialDepleteReportMapper materialDepleteReportMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		holder.setValue(this);
	}

	public static TkMappers inst() {
		return holder.getValue();
	}

	private static class Holder {
		TkMappers value = null;

		public void setValue(TkMappers value) {
			this.value = value;
		}

		public TkMappers getValue() {
			return this.value;
		}
	}

	private static Holder holder = new Holder();
}
