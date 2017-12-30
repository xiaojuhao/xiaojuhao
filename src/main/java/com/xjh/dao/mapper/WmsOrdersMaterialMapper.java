package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.service.vo.MaterialSaleStatVo;

public interface WmsOrdersMaterialMapper {

	List<MaterialSaleStatVo> stat(MaterialSaleStatVo input);

}
