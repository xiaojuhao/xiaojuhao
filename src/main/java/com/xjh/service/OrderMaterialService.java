package com.xjh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.Fraction;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsTaskDO;
import com.xjh.dao.tkmapper.TkWmsOrderMapper;

@Service
public class OrderMaterialService {
	@Resource
	RecipesService recipesService;
	@Resource
	TkWmsOrderMapper orderMapper;

	public void handleOrders() {
		ResultBase<WmsTaskDO> task = TaskService.initTask("handle_orders", "handle_orders", "处理订单原料");
		if (task.getIsSuccess() == false) {
			return;
		}
		task = TaskService.reStartTask(task.getValue());
		if (task.getIsSuccess() == false) {
			return;
		}
		try {
			//删除的记录: status=2 & isDeleted = Y
			for (;;) {
				WmsOrdersDO example = new WmsOrdersDO();
				example.setStatus("2");
				example.setIsDeleted("Y");
				PageHelper.startPage(1, 20);//每次取20记录
				List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(example);
				if (CollectionUtils.isEmpty(orders)) {
					break;//没有数据就退出
				}
				//遍历每一个order
				for (WmsOrdersDO order : orders) {
					WmsOrdersDO update = new WmsOrdersDO();
					update.setId(order.getId());
					ResultBase<Boolean> rb = this.handleRevertOrders(order);
					if (rb.getIsSuccess()) {
						update.setStatus("4");//撤销状态
						update.setRemark(rb.getMessage());
					} else {
						update.setStatus("3");
						update.setRemark(rb.getMessage());
					}
					//update order status
					orderMapper.updateByPrimaryKeySelective(update);
				}
			}
			//待处理的记录: status = 1
			for (;;) {
				WmsOrdersDO example = new WmsOrdersDO();
				example.setStatus("1");
				PageHelper.startPage(1, 20);//每次取20记录
				List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(example);
				if (CollectionUtils.isEmpty(orders)) {
					break;//没有数据就退出
				}
				//遍历每一个order
				for (WmsOrdersDO order : orders) {
					WmsOrdersDO update = new WmsOrdersDO();
					update.setId(order.getId());
					ResultBase<Boolean> rb = this.handleOrders(order);
					if (rb.getIsSuccess()) {
						update.setStatus("2");
						update.setRemark(rb.getMessage());
					} else {
						update.setStatus("3");
						update.setRemark(rb.getMessage());
					}
					//update order status
					orderMapper.updateByPrimaryKeySelective(update);
				}
			}
		} finally {
			TaskService.finishTask(task.getValue());
		}
	}

	public ResultBase<Boolean> handleRevertOrders(WmsOrdersDO order) {
		WmsOrdersMaterialDO cond = new WmsOrdersMaterialDO();
		cond.setOrderId(order.getId());
		List<WmsOrdersMaterialDO> ordersMaterialList = TkMappers.inst().getOrdersMaterialMapper().select(cond);
		List<WmsMaterialStockHistoryDO> materialStockHistoryList = new ArrayList<>();
		String saleDate = CommonUtils.formatDate(order.getSaleDate(), "yyyMMdd");
		for (WmsOrdersMaterialDO ordersMaterial : ordersMaterialList) {
			//将原料消耗额取反
			ordersMaterial.setMaterialTotalAmt(-1 * ordersMaterial.getMaterialTotalAmt());
			//恢复库存
			WmsMaterialStockHistoryDO materialStockHistory = new WmsMaterialStockHistoryDO();
			materialStockHistory.setAmt(-1 * ordersMaterial.getMaterialTotalAmt()); //库存恢复
			materialStockHistory.setCabinCode(order.getStoreCode());
			materialStockHistory.setCabinName(order.getStoreName());
			materialStockHistory.setCabinType("2");
			materialStockHistory.setGmtCreated(new Date());
			materialStockHistory.setMaterialCode(ordersMaterial.getMaterialCode());
			materialStockHistory.setMaterialName(ordersMaterial.getMaterialName());
			materialStockHistory.setOperator("system");
			materialStockHistory.setOpType("sale");
			materialStockHistory.setStatus("0");
			String remark = "对冲" + order.getRecipesName() + saleDate + "销售";
			materialStockHistory.setRemark(remark);
			materialStockHistory.setStockUnit(ordersMaterial.getMaterialUnit());
			materialStockHistory.setTotalPrice(order.getTotalPrice());
			materialStockHistory.setRelateCode(order.getId() + "");
			materialStockHistoryList.add(materialStockHistory);
		}

		for (WmsOrdersMaterialDO ordersMaterial : ordersMaterialList) {
			ordersMaterial.setId(null);
			TkMappers.inst().getOrdersMaterialMapper().insert(ordersMaterial);
		}
		for (WmsMaterialStockHistoryDO insert : materialStockHistoryList) {
			insert.setId(null);
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
		return ResultBaseBuilder.succ().rb();
	}

	public ResultBase<Boolean> handleOrders(WmsOrdersDO order) {
		List<WmsOrdersMaterialDO> ordersMaterialList = new ArrayList<>();
		List<WmsMaterialStockHistoryDO> materialStockHistoryList = new ArrayList<>();
		String recipesCode = order.getRecipesCode();
		String recipesName = order.getRecipesName();
		List<WmsRecipesFormulaDO> recipesformulas = recipesService.queryRecipesFormula(recipesCode);
		if (recipesformulas == null || recipesformulas.size() == 0) {
			return ResultBaseBuilder.fails("没有配置原料").data(false).rb();
		}
		String saleDate = CommonUtils.formatDate(order.getSaleDate(), "yyyMMdd");
		for (WmsRecipesFormulaDO recipesformula : recipesformulas) {
			WmsOrdersMaterialDO ordersMaterial = new WmsOrdersMaterialDO();
			ordersMaterial.setOrderId(order.getId());
			ordersMaterial.setMaterialCode(recipesformula.getMaterialCode());
			ordersMaterial.setMaterialName(recipesformula.getMaterialName());
			ordersMaterial.setMaterialUnit(recipesformula.getMaterialUnit());
			ordersMaterial.setRecipesCode(recipesCode);
			ordersMaterial.setRecipesName(recipesName);
			ordersMaterial.setSaleDate(order.getSaleDate());
			ordersMaterial.setSaleNum(order.getSaleNum());
			ordersMaterial.setStoreCode(order.getStoreCode());
			ordersMaterial.setStoreName(order.getStoreName());
			//计算总量
			double total = (Fraction.from(recipesformula.getMaterialAmt()).mul(Fraction.from(order.getSaleNum())))
					.toBigDecimal().doubleValue();
			ordersMaterial.setMaterialTotalAmt(total);
			ordersMaterialList.add(ordersMaterial);

			WmsMaterialStockHistoryDO materialStockHistory = new WmsMaterialStockHistoryDO();
			materialStockHistory.setAmt(-1 * total);
			materialStockHistory.setCabinCode(order.getStoreCode());
			materialStockHistory.setCabinName(order.getStoreName());
			materialStockHistory.setCabinType("2");
			materialStockHistory.setGmtCreated(new Date());
			materialStockHistory.setMaterialCode(recipesformula.getMaterialCode());
			materialStockHistory.setMaterialName(recipesformula.getMaterialName());
			materialStockHistory.setOperator("system");
			materialStockHistory.setOpType("sale");
			materialStockHistory.setStatus("0");
			String remark = order.getRecipesName() + saleDate + "销售";
			materialStockHistory.setRemark(remark);
			materialStockHistory.setStockUnit(recipesformula.getMaterialUnit());
			materialStockHistory.setTotalPrice(order.getTotalPrice());
			materialStockHistory.setRelateCode(order.getId() + "");
			materialStockHistoryList.add(materialStockHistory);
		}

		for (WmsOrdersMaterialDO ordersMaterial : ordersMaterialList) {
			ordersMaterial.setId(null);
			TkMappers.inst().getOrdersMaterialMapper().insert(ordersMaterial);
		}
		for (WmsMaterialStockHistoryDO insert : materialStockHistoryList) {
			insert.setId(null);
			TkMappers.inst().getMaterialStockHistoryMapper().insert(insert);
		}
		return ResultBaseBuilder.succ().rb();
	}
}
