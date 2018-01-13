package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.service.vo.StockReportVo;

public interface StockReportMapper {
	List<StockReportVo> reportData(StockReportVo input);
}
