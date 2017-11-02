package com.xjh.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xjh.dao.dataobject.WmsSequenceDO;
import com.xjh.dao.tkmapper.TkWmsSequenceMapper;
import com.xjh.service.SequenceService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SequenceServiceImpl implements SequenceService {
	@Resource
	TkWmsSequenceMapper sequenceMapper;

	@Override
	public long next(String sequenceCode) {
		if (StringUtils.isBlank(sequenceCode)) {
			return -1;
		}
		WmsSequenceDO dd = new WmsSequenceDO();
		dd.setSequenceCode(sequenceCode);
		WmsSequenceDO sequence = sequenceMapper.selectOne(dd);
		if (sequence == null) {
			WmsSequenceDO insert = new WmsSequenceDO();
			insert.setSequenceCode(sequenceCode);
			insert.setNextValue(2L);
			insert.setModifyVersion(0L);
			sequenceMapper.insert(insert);
			return 1;
		}
		
		while (true) {
			dd = new WmsSequenceDO();
			dd.setSequenceCode(sequenceCode);
			sequence = sequenceMapper.selectOne(dd);

			WmsSequenceDO update = new WmsSequenceDO();
			update.setNextValue(sequence.getNextValue() + 1);
			update.setModifyVersion(sequence.getModifyVersion()+1);
			Example exam = new Example(WmsSequenceDO.class);
			Example.Criteria c = exam.createCriteria();
			c.andEqualTo("id", sequence.getId());
			c.andEqualTo("modifyVersion", sequence.getModifyVersion());
			int i = sequenceMapper.updateByExampleSelective(update, exam);
			if (i > 0) {
				return sequence.getNextValue();
			}
		}
	}

}
