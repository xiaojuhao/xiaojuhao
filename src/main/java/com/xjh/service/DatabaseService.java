package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;

public interface DatabaseService {
	public void diaoboCommit(//
			WmsInventoryApplyDO applyDO, //
			List<WmsInventoryApplyDetailDO> applyDetailList, //
			List<WmsMaterialStockHistoryDO> historyList//
	);

	public void diaoboConfirm(WmsInventoryApplyDO applyUpdate, //
			List<WmsInventoryApplyDetailDO> applyDetailUpdateList, //
			List<WmsMaterialStockHistoryDO> historyInsertList);

	public void commitPurchaseOrder(//
			WmsInventoryApplyDO applyDO, //
			List<WmsInventoryApplyDetailDO> list);

	public void claimLossInsert(//
			WmsInventoryApplyDO applyDO, //
			WmsInventoryApplyDetailDO applyDetailDO, //
			WmsMaterialStockHistoryDO historyDO)

	;

	public void correctStock(//
			WmsMaterialStockHistoryDO preHis, //
			WmsMaterialStockHistoryDO postHis);
}
