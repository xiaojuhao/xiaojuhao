package com.xjh.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsLockDO;
import com.xjh.dao.tkmapper.TkWmsLockMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class LockService {
	@Resource
	TkWmsLockMapper lockMapper;

	public boolean tryLock(String key, int expireSeconds) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		WmsLockDO lock = new WmsLockDO();
		lock.setLockKey(key);
		lock = lockMapper.selectOne(lock);
		if (lock == null) {
			lock = new WmsLockDO();
			lock.setLockKey(key);
			lock.setLockVal("Y");
			lock.setExpireTime(DateBuilder.now().futureSeconds(expireSeconds).date());
			lock.setCreatedTime(DateBuilder.now().date());
			lockMapper.insert(lock);
			return true;
		}
		if (CommonUtils.partiallyOrder(new Date(), lock.getExpireTime())) {
			return false;
		}
		lock.setExpireTime(DateBuilder.now().futureSeconds(expireSeconds).date());
		lockMapper.updateByPrimaryKey(lock);
		return true;
	}

	public void releaseLock(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		Example example = new Example(WmsLockDO.class, false, false);
		Example.Criteria cri = example.createCriteria();
		cri.andEqualTo("lockKey", key);
		lockMapper.deleteByExample(example);
	}
}
