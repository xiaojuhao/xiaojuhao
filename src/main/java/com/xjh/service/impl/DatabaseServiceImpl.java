package com.xjh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.service.DatabaseService;
import com.xjh.service.TkMappers;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Override
	@Transactional
	public void diaoboCommit(WmsInventoryApplyDO applyDO, //
			List<WmsInventoryApplyDetailDO> applyDetailList, //
			List<WmsMaterialStockHistoryDO> historyList) {
		// 1.
		TkMappers.inst().getPurchaseOrderMapper().insert(applyDO);
		// 2.
		for (WmsInventoryApplyDetailDO detail : applyDetailList) {
			TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
		}
		// 3.
		for (WmsMaterialStockHistoryDO his : historyList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(his);
		}
	}

	@Override
	@Transactional
	public void diaoboConfirm(WmsInventoryApplyDO applyUpdate, List<WmsInventoryApplyDetailDO> applyDetailUpdateList,
			List<WmsMaterialStockHistoryDO> historyInsertList) {
		// 1.
		TkMappers.inst().getPurchaseOrderMapper().updateByPrimaryKeySelective(applyUpdate);
		// 2.
		for (WmsInventoryApplyDetailDO update : applyDetailUpdateList) {
			TkMappers.inst().getPurchaseOrderDetailMapper().updateByPrimaryKeySelective(update);
		}
		// 3.
		for (WmsMaterialStockHistoryDO insert : historyInsertList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
	}

	@Override
	@Transactional
	public void commitPurchaseOrder(List<WmsInventoryApplyDO> applyList, List<WmsInventoryApplyDetailDO> list) {
		// 1.
		for (WmsInventoryApplyDetailDO detail : list) {
			TkMappers.inst().getPurchaseOrderDetailMapper().insert(detail);
		}
		// 2.
		for (WmsInventoryApplyDO applyDO : applyList) {
			TkMappers.inst().getPurchaseOrderMapper().insert(applyDO);
		}
	}

	@Override
	@Transactional
	public void claimLossInsert(WmsInventoryApplyDO applyDO, WmsInventoryApplyDetailDO applyDetailDO,
			WmsMaterialStockHistoryDO historyDO) {
		TkMappers.inst().getPurchaseOrderMapper().insert(applyDO);
		TkMappers.inst().getPurchaseOrderDetailMapper().insert(applyDetailDO);
		TkMappers.inst().getMaterialStockHistoryMapper().insert(historyDO);
	}

	@Override
	@Transactional
	public void correctStock(WmsMaterialStockHistoryDO preHis, WmsMaterialStockHistoryDO postHis) {
		// prehis和posthis具有先后顺序
		if (preHis != null)
			TkMappers.inst().getMaterialStockHistoryMapper().insert(preHis);
		if (postHis != null)
			TkMappers.inst().getMaterialStockHistoryMapper().insert(postHis);
	}

	@Override
	@Transactional
	//销售数据同步，集中更新数据库
	public void salesCommit(List<WmsOrdersDO> ordersList, List<WmsOrdersMaterialDO> ordersMaterialList,
			List<WmsMaterialStockHistoryDO> historyList) {
		// 1.
		for (WmsOrdersDO orders : ordersList) {
			TkMappers.inst().getOrdersMapper().updateByPrimaryKey(orders);
		}
		// 2.
		for (WmsOrdersMaterialDO ordersMaterial : ordersMaterialList) {
			TkMappers.inst().getOrdersMaterialMapper().insert(ordersMaterial);
		}
		// 3.
		for (WmsMaterialStockHistoryDO insert : historyList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
	}

}
