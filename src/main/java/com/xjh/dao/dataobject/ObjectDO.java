/**
 * 物料类，存储原始材料
 */
package com.xjh.dao.dataobject;
import java.sql.Date;

import lombok.Data;

@Data
public class ObjectDO {
	Long ID;//物品编号
	String SupplierName;//供货商名称
	String ObjectName;//物品名
	String ObjectNumber;//物品编号
	String ObjectDescribe;//物品描述
	String ObjectUnit;//物品计量单位
	float ObjectAmount;//物品数量
	Date ObjectTermOfValidity;//物品有效期
	Date ObjectStorageTime;//入库时间
	float ObjectUtilizationRate;//使用率 
	String menuName_Link;//菜品名称
	String menuCode_Link;//菜品编号
	String MaterielName_Link;//物品名
	String MaterielNumber_Link;//物品名
	float MaterielUtilizationRate;//原材料使用率 
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	public String getObjectName() {
		return ObjectName;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public String getObjectDescribe() {
		return ObjectDescribe;
	}
	public void setObjectDescribe(String objectDescribe) {
		ObjectDescribe = objectDescribe;
	}
	public String getObjectUnit() {
		return ObjectUnit;
	}
	public void setObjectUnit(String objectUnit) {
		ObjectUnit = objectUnit;
	}
	public float getObjectAmount() {
		return ObjectAmount;
	}
	public void setObjectAmount(float objectAmount) {
		ObjectAmount = objectAmount;
	}
	public Date getObjectTermOfValidity() {
		return ObjectTermOfValidity;
	}
	public void setObjectTermOfValidity(Date objectTermOfValidity) {
		ObjectTermOfValidity = objectTermOfValidity;
	}
	public Date getObjectStorageTime() {
		return ObjectStorageTime;
	}
	public void setObjectStorageTime(Date objectStorageTime) {
		ObjectStorageTime = objectStorageTime;
	}
	public float getObjectUtilizationRate() {
		return ObjectUtilizationRate;
	}
	public void setObjectUtilizationRate(float objectUtilizationRate) {
		ObjectUtilizationRate = objectUtilizationRate;
	}
	public String getObjectNumber() {
		return ObjectNumber;
	}
	public void setObjectNumber(String objectNumber) {
		ObjectNumber = objectNumber;
	}
	public String getMenuName_Link() {
		return menuName_Link;
	}
	public void setMenuName_Link(String menuName_Link) {
		this.menuName_Link = menuName_Link;
	}
	public String getMenuCode_Link() {
		return menuCode_Link;
	}
	public void setMenuCode_Link(String menuCode_Link) {
		this.menuCode_Link = menuCode_Link;
	}
	public String getMaterielName_Link() {
		return MaterielName_Link;
	}
	public void setMaterielName_Link(String materielName_Link) {
		MaterielName_Link = materielName_Link;
	}
	public String getMaterielNumber_Link() {
		return MaterielNumber_Link;
	}
	public void setMaterielNumber_Link(String materielNumber_Link) {
		MaterielNumber_Link = materielNumber_Link;
	}
	public float getMaterielUtilizationRate() {
		return MaterielUtilizationRate;
	}
	public void setMaterielUtilizationRate(float materielUtilizationRate) {
		MaterielUtilizationRate = materielUtilizationRate;
	}
	
}
