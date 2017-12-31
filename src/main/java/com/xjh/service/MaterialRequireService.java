package com.xjh.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjh.commons.CommonUtils;
import com.xjh.commons.DateBuilder;
import com.xjh.dao.dataobject.WmsMaterialDO;
import com.xjh.dao.dataobject.WmsMaterialRequireDO;
import com.xjh.dao.tkmapper.TkWmsMaterialRequireMapper;
import com.xjh.valueobject.CabinVo;

@Service
public class MaterialRequireService {
	@Resource
	CabinService cabinService;
	@Resource
	MaterialService materialService;
	@Resource
	TkWmsMaterialRequireMapper requireMapper;

	public void addRequire(String cabinCode, String materialCode, Double requireAmt, String date) {
		if (requireAmt == null) {
			requireAmt = 0D;
		}
		if (CommonUtils.isAnyBlank(cabinCode, materialCode)) {
			return;
		}
		CabinVo cabin = cabinService.getCabinByCode(cabinCode);
		WmsMaterialDO material = materialService.getMaterialByCode(materialCode);
		if (cabin == null || material == null) {
			return;
		}
		Date requireDate = CommonUtils.parseDate(date, "yyyyMMdd");
		if (requireDate == null) {
			requireDate = DateBuilder.now().zeroAM().date();
		}
		WmsMaterialRequireDO cond = new WmsMaterialRequireDO();
		cond.setMaterialCode(materialCode);
		cond.setCabinCode(cabinCode);
		cond.setStatus("0");
		cond.setRequireGroup("DEFAULT");
		PageHelper.orderBy("require_date desc");
		List<WmsMaterialRequireDO> list = requireMapper.select(cond);
		if (list.size() == 0) {
			WmsMaterialRequireDO r = new WmsMaterialRequireDO();
			r.setCabinCode(cabinCode);
			r.setCabinName(cabin.getName());
			r.setMaterialCode(materialCode);
			r.setRequireGroup("DEFAULT");
			r.setMaterialName(material.getMaterialName());
			r.setMaterialCate(material.getCategory());
			r.setRequireDate(requireDate);
			r.setRequireAmt(requireAmt);
			r.setStatus("0");
			r.setGmtCreated(new Date());
			r.setCreator("system");
			r.setGmtModified(new Date());
			r.setModifier("system");
			requireMapper.insert(r);
		} else {
			WmsMaterialRequireDO r = list.get(0);
			if (r.getRequireAmt() == null) {
				r.setRequireAmt(0D);
			}
			r.setGmtModified(new Date());
			r.setModifier("system");
			r.setRequireDate(requireDate);
			r.setRequireAmt(r.getRequireAmt() + requireAmt);
			requireMapper.updateByPrimaryKeySelective(r);
		}
	}
}
