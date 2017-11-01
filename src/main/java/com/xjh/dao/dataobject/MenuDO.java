/**
 * 菜单类，记录每个菜品的物料
 */
package com.xjh.dao.dataobject;

import lombok.Data;

@Data
public class MenuDO {
	Long id;//ID号
	String menuName;//菜品名称
	String menuCode;//菜品编号
	String StoreNumber;//门店编号
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getStoreNumber() {
		return StoreNumber;
	}
	public void setStoreNumber(String storeNumber) {
		StoreNumber = storeNumber;
	}
	
}
