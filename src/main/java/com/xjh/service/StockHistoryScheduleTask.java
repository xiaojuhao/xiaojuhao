package com.xjh.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.xjh.commons.Holder;
import com.xjh.dao.dataobject.WmsMaterialStockDO;
import com.xjh.dao.dataobject.WmsMaterialStockHistoryDO;
import com.xjh.dao.mapper.WmsMaterialStockHistoryMapper;
import com.xjh.dao.mapper.WmsMaterialStockMapper;
import com.xjh.support.datasource.WmsDataSource;
import com.xjh.valueobject.MaterialStockChangeVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockHistoryScheduleTask implements InitializingBean {
	public static AtomicBoolean isRunning = new AtomicBoolean(false);
	static Holder<StockHistoryScheduleTask> self = new Holder<>();
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
	@Resource
	DiandanSystemService diandanService;
	@Resource
	OrderMaterialService orderMaterialService;
	@Resource
	WmsDataSource wmsDataSource;

	public void changeStock(WmsMaterialStockHistoryDO record) {
		// 更新状态为处理中.....
		WmsMaterialStockHistoryDO status = new WmsMaterialStockHistoryDO();
		//		status.setId(record.getId());
		//		status.setStatus("9");
		//		TkMappers.inst().getMaterialStockHistoryMapper().updateByPrimaryKeySelective(status);
		// 初始化库存

		MaterialStockChangeVo changeVo = new MaterialStockChangeVo();
		changeVo.setMaterialCode(record.getMaterialCode());
		changeVo.setCabinCode(record.getCabinCode());
		changeVo.setStockChgAmt(record.getAmt());
		changeVo.setOperator(record.getOperator());
		int i = wmsMaterialStockMapper.changeByDelta(changeVo);
		if (i == 0) {
			materialService.initMaterialStock(record.getMaterialCode(), record.getCabinCode());
			changeVo = new MaterialStockChangeVo();
			changeVo.setMaterialCode(record.getMaterialCode());
			changeVo.setCabinCode(record.getCabinCode());
			changeVo.setStockChgAmt(record.getAmt());
			changeVo.setOperator(record.getOperator());
			wmsMaterialStockMapper.changeByDelta(changeVo);
		}
		//更新每日库存表里面的消费信息
		//		if (StockOpType.fromCode(record.getOpType()) == StockOpType.SALE) {
		//			stockDailyService.addConsume(record.getMaterialCode(), //
		//					record.getCabinCode(), //
		//					CommonUtils.formatDate(record.getGmtCreated(), "yyyyMMdd"), //
		//					Math.abs(record.getAmt()), null);
		//		} else if (record.getAmt() < 0) {
		//			stockDailyService.addConsume(record.getMaterialCode(), //
		//					record.getCabinCode(), //
		//					CommonUtils.formatDate(record.getGmtCreated(), "yyyyMMdd"), //
		//					null, Math.abs(record.getAmt()));
		//		}
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
		try (Connection connection = wmsDataSource.getConnection()) {
			long start = System.currentTimeMillis();
			log.info("调用库存轨迹存储过程---start---");
			String sql = "{call sp_handle_stock_his(?)}";
			CallableStatement stmt = connection.prepareCall(sql);
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.execute();
			int ret = stmt.getInt(1);
			stmt.close();
			log.info("调用库存轨迹存储过程---end,hanle:{},cost:{}", ret, System.currentTimeMillis() - start);
		} catch (Exception ex) {
			log.error("", ex);
		} finally {
			isRunning.set(false);
		}
		//		try {
		//			long start = System.currentTimeMillis();
		//			log.info("处理库存历史记录 ---- 开始 ------");
		//			while (true) {
		//				long startLoop = System.currentTimeMillis();
		//				log.info("查询数据开始------");
		//				List<WmsMaterialStockHistoryDO> list = wmsMaterialStockHistoryMapper.selectListToDeal();
		//				if (list == null || list.size() == 0) {
		//					break;
		//				}
		//				log.info("查询数据结束，获取{}条记录，耗时{}", list.size(), System.currentTimeMillis() - startLoop);
		//				log.info("处理数据开始------");
		//				startLoop = System.currentTimeMillis();
		//				for (WmsMaterialStockHistoryDO dd : list) {
		//					if (dd.getOpType().equals("correct")) {
		//						this.handleCorrect(dd);
		//					} else {
		//						this.changeStock(dd);
		//					}
		//				}
		//				log.info("处理数据结束，耗时{}", System.currentTimeMillis() - startLoop);
		//			}
		//			log.info("处理库存历史记录 ---- 结束,耗时{}---", System.currentTimeMillis() - start);
		//		} catch (Exception ex) {
		//			ex.printStackTrace();
		//		} finally {
		//			isRunning.set(false);
		//		}
	}

	public static void startTask() {
		if (self.get() != null) {
			service.submit(() -> self.get().start());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		self.set(this);
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					start();
				} catch (Exception ex) {
					log.error("定时任务", ex);
				}
			}
		}, 60, 60, TimeUnit.SECONDS);
	}
}
