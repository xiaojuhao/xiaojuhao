package com.xjh.service;

import java.util.Date;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.ResultBase;
import com.xjh.commons.ResultBaseBuilder;
import com.xjh.commons.ResultCode;
import com.xjh.dao.dataobject.WmsTaskDO;

public class TaskService {
	public static ResultBase<WmsTaskDO> initTask(String taskType, String taskId, String taskName) {
		if (CommonUtils.isAnyBlank(taskType, taskId, taskName)) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		WmsTaskDO cond = new WmsTaskDO();
		cond.setTaskId(taskId);
		cond.setTaskType(taskType);
		WmsTaskDO task = TkMappers.inst().getTaskMapper().selectOne(cond);
		if (task != null) {
			return ResultBaseBuilder.succ().data(task).rb();
		}
		task = new WmsTaskDO();
		task.setTaskId(taskId);
		task.setTaskName(taskName);
		task.setTaskType(taskType);
		task.setGmtCreated(new Date());
		task.setGmtModified(new Date());
		task.setStatus("0");
		try {
			TkMappers.inst().taskMapper.insert(task);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("初始化异常").rb();
		}
		return ResultBaseBuilder.succ().data(task).rb();
	}

	public static ResultBase<WmsTaskDO> startTask(WmsTaskDO task) {
		if (task == null || CommonUtils.isAnyBlank(task.getTaskType(), task.getTaskId())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		WmsTaskDO cond = new WmsTaskDO();
		cond.setTaskId(task.getTaskId());
		cond.setTaskType(task.getTaskType());
		WmsTaskDO taskdd = TkMappers.inst().getTaskMapper().selectOne(cond);
		if (taskdd == null) {
			return ResultBaseBuilder.fails(ResultCode.task_not_exists).data(task).rb();
		}
		if (!"0".equals(taskdd.getStatus())) {
			return ResultBaseBuilder.fails(ResultCode.task_is_running).msg("无法启动").data(task).rb();
		}
		WmsTaskDO update = new WmsTaskDO();
		update.setId(taskdd.getId());
		update.setStatus("1");
		update.setGmtStart(new Date());
		try {
			TkMappers.inst().taskMapper.updateByPrimaryKeySelective(update);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("启动异常").rb();
		}
		return ResultBaseBuilder.succ().data(taskdd).rb();
	}

	public static ResultBase<WmsTaskDO> finishTask(WmsTaskDO task) {
		if (task == null || CommonUtils.isAnyBlank(task.getTaskType(), task.getTaskId())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		WmsTaskDO cond = new WmsTaskDO();
		cond.setTaskId(task.getTaskId());
		cond.setTaskType(task.getTaskType());
		WmsTaskDO taskdd = TkMappers.inst().getTaskMapper().selectOne(cond);
		if (taskdd == null) {
			return ResultBaseBuilder.fails(ResultCode.task_not_exists).rb();
		}
		if (!"1".equals(taskdd.getStatus())) {
			return ResultBaseBuilder.fails(ResultCode.task_is_running).msg("无法结束").data(task).rb();
		}
		WmsTaskDO update = new WmsTaskDO();
		update.setId(taskdd.getId());
		update.setStatus("2");
		update.setGmtEnd(new Date());
		update.setRemark(task.getRemark());
		try {
			TkMappers.inst().taskMapper.updateByPrimaryKeySelective(update);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("启动异常").rb();
		}
		return ResultBaseBuilder.succ().data(task).rb();
	}

	public static ResultBase<WmsTaskDO> onError(WmsTaskDO task) {
		if (task == null || CommonUtils.isAnyBlank(task.getTaskType(), task.getTaskId())) {
			return ResultBaseBuilder.fails(ResultCode.param_missing).rb();
		}
		WmsTaskDO cond = new WmsTaskDO();
		cond.setTaskId(task.getTaskId());
		cond.setTaskType(task.getTaskType());
		WmsTaskDO taskdd = TkMappers.inst().getTaskMapper().selectOne(cond);
		if (taskdd == null) {
			return ResultBaseBuilder.fails(ResultCode.task_not_exists).rb();
		}
		WmsTaskDO update = new WmsTaskDO();
		update.setId(taskdd.getId());
		update.setStatus("3");
		update.setRemark(task.getRemark());
		try {
			TkMappers.inst().taskMapper.updateByPrimaryKeySelective(update);
		} catch (Exception ex) {
			return ResultBaseBuilder.fails("操作异常").rb();
		}
		return ResultBaseBuilder.succ().data(task).rb();
	}
}
