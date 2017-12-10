package com.xjh.service;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.dataobject.WmsTaskDO;
import com.xjh.dao.mapper.WmsMaterialStockHistoryMapper;
import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.valueobject.MaterialStockChangeVo;

@Service
public class StockHistoryScheduleTask implements InitializingBean {
	public static AtomicBoolean isRunning = new AtomicBoolean(false);
	static StockHistoryScheduleTask self = null;
	static ExecutorService service = Executors.newFixedThreadPool(10);
	@Resource
	WmsMaterialStockHistoryMapper wmsMaterialStockHistoryMapper;
	@Resource
	MaterialService materialService;
	@Resource
	WmsMaterialStockMapper wmsMaterialStockMapper;
	@Resource
	StockDailyService stockDailyService;
	@Resource
	CabinService cabinService;

	public void changeStock(WmsMaterialStockHistoryDO record) {
		// 更新状态为处理中.....
		WmsMaterialStockHistoryDO status = new WmsMaterialStockHistoryDO();
		status.setId(record.getId());
		status.setStatus("9");
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
		// 初始化库存
		materialService.initMaterialStock(record.getMaterialCode(), record.getCabinCode());
		MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
		changeVo.setMaterialCode(record.getMaterialCode());
		changeVo.setCabinCode(record.getCabinCode());
		changeVo.setStockChgAmt(record.getAmt());
		changeVo.setOperator(record.getOperator());
		wmsMaterialStockMapper.changeByDelta(changeVo);
		// 处理完成。。。
		status.setId(record.getId());
		status.setStatus("1");
		status.setOperator(record.getOperator());
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
	}

	public void handleCorrect(WmsMaterialStockHistoryDO record) {
		if (!"correct".equals(record.getOpType())) {
			return;
		}
		// 更新状态为处理中.....
		WmsMaterialStockHistoryDO status = new WmsMaterialStockHistoryDO();
		status.setId(record.getId());
		status.setStatus("9");
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
		// 初始化库存
		materialService.initMaterialStock(record.getMaterialCode(), record.getCabinCode());
		WmsMaterialStockDO stock = new WmsMaterialStockDO();
		stock.setMaterialCode(record.getMaterialCode());
		stock.setCabinCode(record.getCabinCode());
		stock.setCabinType(record.getCabinType());
		stock = TkMappers.inst().getMaterialStockMapper().selectOne(stock);
		double preStock = stock.getCurrStock();
		WmsMaterialStockDO update = new WmsMaterialStockDO();
		update.setId(stock.getId());
		update.setCurrStock(record.getAmt());
		TkMappers.inst().getMaterialStockMapper().updateByPrimaryKeySelective(update);
		// 处理完成。。。
		status.setId(record.getId());
		status.setStatus("1");
		status.setPreStock(preStock);
		status.setPostStock(record.getAmt());
		status.setOperator(record.getOperator());
		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
	}

	public void start() {
		if (!isRunning.compareAndSet(false, true)) {
			return;
		}
		try {
			while (true) {
				WmsMaterialStockHistoryDO dd = wmsMaterialStockHistoryMapper.selectOneToDeal();
				if (dd == null) {
					break;
				}
				if (dd.getOpType().equals("correct")) {
					this.handleCorrect(dd);
				} else {
					this.changeStock(dd);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			isRunning.set(false);
		}
	}

	public static void startTask() {
		if (self != null) {
			service.submit(() -> self.start());
		}
	}

	public static void initDailyStock() {
		if (self == null) {
			return;
		}
		Calendar c = Calendar.getInstance();
		int m = c.get(Calendar.MINUTE);
		if (m % 10 != 0) { //每10分钟启动一次
			return;
		}
		String today = CommonUtils.stringOfToday("yyyyMMdd");
		ResultBase<WmsTaskDO> task = TaskService.initTask("init-stock-daily", today, "初始化每日库存");
		if (!task.getIsSuccess()) {
			return;
		}
		task = TaskService.reStartTask(task.getValue());
		if (!task.getIsSuccess()) {
			return;
		}
		final WmsTaskDO taskDO = task.getValue();
		service.submit(() -> {
			try {
				TkMappers.inst().materialStockMapper.selectAll()//
						.forEach((d) -> {
							self.stockDailyService.getOrInitStockDaily( //
									d.getMaterialCode(), //
									d.getCabinCode(), //
									today);
						});
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				TaskService.finishTask(taskDO);
			}
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		self = this;
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					//self.start();
					initDailyStock();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}, 0, 60, TimeUnit.SECONDS);
	}
}
