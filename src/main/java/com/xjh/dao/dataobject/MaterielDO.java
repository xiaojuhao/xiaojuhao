/**
 * 存储可以拆分的物品，例如三文鱼，可以拆分成鱼头、鱼腩等
 * 供Object表使用
 */
package com.xjh.dao.dataobject;
import java.sql.Date;

import lombok.Data;

@Data
public class MaterielDO {
	Long ID;//物品编号
	String SupplierName;//供货商名称
	String MaterielName;//物品名
	String MaterielNumber;//物品名
	String MaterielDescribe;//物品描述
	String MaterielUnit;//物品计量单位
	float MaterielAmount;//物品数量
	Date MaterielTermOfValidity;//物品有效期
	Date MaterielStorageTime;//入库时间

}
