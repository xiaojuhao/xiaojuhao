package com.xjh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.commons.Fraction;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsOrdersDO;
import com.xjh.dao.dataobject.WmsOrdersMaterialDO;
import com.xjh.dao.dataobject.WmsRecipesFormulaDO;
import com.xjh.dao.dataobject.WmsTaskDO;
import com.xjh.dao.dataobject.WmsTimerJobDO;
import com.xjh.dao.tkmapper.TkWmsOrderMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderMaterialService {
	@Resource
	RecipesService recipesService;
	@Resource
	TkWmsOrderMapper orderMapper;
	@Resource
	MaterialService materialService;

	public void handleOrders() {
		try {
			log.info("处理订单原料-----开始------");
			internalHandleOrders();
			log.info("处理订单原料-----结束------");
		} catch (Exception ex) {
			log.error("", ex);
			log.info("处理订单原料-----出现异常------");
		}

	}

	private void internalHandleOrders() {
		ResultBase<WmsTaskDO> task = TaskService.initTask("handle_orders", "handle_orders", "处理订单原料");
		if (task.getIsSuccess() == false) {
			log.error("初始化任务失败:{}", task.getMessage());
			return;
		}
		task = TaskService.reStartTask(task.getValue());
		if (task.getIsSuccess() == false) {
			log.error("启动任务失败:{}", task.getMessage());
			return;
		}
		Date startDate = DateBuilder.today().date();
		Date endDate = startDate;
		int index = 0;
		CountDownLatch latch = new CountDownLatch(0);
		try {
			//删除的记录: status=2 & isDeleted = Y
			log.info("处理订单原料-----处理删除的记录---开始---");
			for (;;) {
				WmsOrdersDO example = new WmsOrdersDO();
				example.setStatus("2");
				example.setIsDeleted("Y");
				PageHelper.startPage(1, 100);//每次取20记录
				List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(example);
				if (CollectionUtils.isEmpty(orders)) {
					break;//没有数据就退出
				}
				log.info("处理订单原料-----处理删除的记录---第{}批---", index++);
				//遍历每一个order
				for (WmsOrdersDO order : orders) {
					latch.await();
					if (CommonUtils.partiallyOrder(order.getSaleDate(), startDate)) {
						startDate = order.getSaleDate();
					}
					if (CommonUtils.partiallyOrder(endDate, order.getSaleDate())) {
						endDate = order.getSaleDate();
					}
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
			log.info("处理订单原料-----处理删除的记录---结束---");
			//待处理的记录: status = 1
			index = 0;
			log.info("处理订单原料-----处理正常的记录---开始---");
			for (;;) {
				WmsOrdersDO example = new WmsOrdersDO();
				example.setStatus("1");
				PageHelper.startPage(1, 100);//每次取20记录
				List<WmsOrdersDO> orders = TkMappers.inst().getOrdersMapper().select(example);
				if (CollectionUtils.isEmpty(orders)) {
					break;//没有数据就退出
				}
				log.info("处理订单原料-----处理正常的记录---第{}批---", index++);
				//遍历每一个order
				for (WmsOrdersDO order : orders) {
					if (CommonUtils.partiallyOrder(order.getSaleDate(), startDate)) {
						startDate = order.getSaleDate();
					}
					if (CommonUtils.partiallyOrder(endDate, order.getSaleDate())) {
						endDate = order.getSaleDate();
					}
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
			log.info("处理订单原料-----处理正常的记录---结束---");
		} catch (Exception ex) {
			log.error("", ex);
		} finally {
			TaskService.finishTask(task.getValue());
		}

		//处理完订单消耗的原料之后，触发一个统计任务
		WmsTimerJobDO job = new WmsTimerJobDO();
		job.setScheduledTime(new Date());
		JSONObject param = new JSONObject();
		param.put("startDate", CommonUtils.formatDate(startDate, "yyyyMMdd"));
		param.put("endDate", CommonUtils.formatDate(endDate, "yyyyMMdd"));
		job.setJobParam(param.toJSONString());
		job.setJobType("material_sale_stat");
		job.setJobName("统计销售原料");
		job.setStatus("0");
		job.setVersion(0);
		TkMappers.inst().getTimerJobMapper().insert(job);
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
			materialStockHistory.setAffectStock("Y");
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
			WmsMaterialDO material = materialService.queryMaterialByCode(recipesformula.getMaterialCode());
			if (material == null || "Y".equals(material.getIsDeleted())) {
				continue;//原料已经删除
			}
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
			materialStockHistory.setAffectStock("Y");
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
		return ResultBaseBuilder.succ().msg("扣库成功").rb();
	}
}
