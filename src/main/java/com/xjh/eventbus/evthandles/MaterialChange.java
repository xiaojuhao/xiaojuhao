package com.xjh.eventbus.evthandles;

import com.xjh.dao.dataobject.WmsMaterialDO;

public class MaterialChange {
	WmsMaterialDO material;

	public MaterialChange(WmsMaterialDO material) {
		this.material = material;
	}
}
