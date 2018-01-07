package com.xjh.service;

import java.util.List;

import com.xjh.dao.dataobject.WmsInventoryApplyDO;
import com.xjh.dao.dataobject.WmsInventoryApplyDetailDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;

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
			List<WmsInventoryApplyDO> applyList, //
			List<WmsInventoryApplyDetailDO> list);

	public void claimLossInsert(//
			WmsInventoryApplyDO applyDO, //
			WmsInventoryApplyDetailDO applyDetailDO, //
			WmsMaterialStockHistoryDO historyDO)

	;

	public void correctStock(//
			WmsMaterialStockHistoryDO preHis, //
			WmsMaterialStockHistoryDO postHis);
	
	//销售数据同步，集中更新数据库
	public void salesCommit(//
			List<WmsOrdersDO> ordersList, //
			List<WmsOrdersMaterialDO> ordersMaterialList, //
			List<WmsMaterialStockHistoryDO> historyList//
	);
}
