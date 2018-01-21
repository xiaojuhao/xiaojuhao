package com.xjh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyDetailMapper;
import com.xjh.dao.tkmapper.TkWmsInventoryApplyMapper;
import com.xjh.service.DatabaseService;
import com.xjh.service.TkMappers;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	@Resource
	TkWmsInventoryApplyMapper inventoryApplyMapper;
	@Resource
	TkWmsInventoryApplyDetailMapper inventoryApplyDetailMapper;
	@Override
	@Transactional
	public void diaoboCommit(WmsInventoryApplyDO applyDO, //
			List<WmsInventoryApplyDetailDO> applyDetailList, //
			List<WmsMaterialStockHistoryDO> historyList) {
		// 1.
		inventoryApplyMapper.insert(applyDO);
		// 2.
		for (WmsInventoryApplyDetailDO detail : applyDetailList) {
			inventoryApplyDetailMapper.insert(detail);
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
		if(applyUpdate != null)
		inventoryApplyMapper.updateByPrimaryKeySelective(applyUpdate);
		// 2.
		if(applyDetailUpdateList!=null)
		for (WmsInventoryApplyDetailDO update : applyDetailUpdateList) {
			inventoryApplyDetailMapper.updateByPrimaryKeySelective(update);
		}
		// 3.
		if(historyInsertList != null)
		for (WmsMaterialStockHistoryDO insert : historyInsertList) {
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
	}

	@Override
	@Transactional
	public void commitPurchaseOrder(List<WmsInventoryApplyDO> applyList, List<WmsInventoryApplyDetailDO> list) {
		// 1.
		for (WmsInventoryApplyDetailDO detail : list) {
			inventoryApplyDetailMapper.insert(detail);
		}
		// 2.
		for (WmsInventoryApplyDO applyDO : applyList) {
			inventoryApplyMapper.insert(applyDO);
		}
	}

	@Override
	@Transactional
	public void claimLossInsert(WmsInventoryApplyDO applyDO, WmsInventoryApplyDetailDO applyDetailDO,
			WmsMaterialStockHistoryDO historyDO) {
		inventoryApplyMapper.insert(applyDO);
		inventoryApplyDetailMapper.insert(applyDetailDO);
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
