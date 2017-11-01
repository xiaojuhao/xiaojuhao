package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.ObjectDO;

public interface ObjectMapper {
	
	//根据供货商查找物品信息
	public List<ObjectDO> findObjectBySupplierName(String SupplierName) throws Exception;
	//根据物品名称查找物品信息
	public ObjectDO findObjectByObjectName(String ObjectName) throws Exception;
	//根据有效期查找物品信息
	public List<ObjectDO> findObjectByObjectTermOfValidity(String ObjectTermOfValidity) throws Exception;
	//根据入库时间查找物品信息
	public List<ObjectDO> findObjectByObjectStorageTime(String ObjectStorageTime) throws Exception;
	//根据菜品名称查找物品信息
	public List<ObjectDO> findObjectByMenuName_Link(String menuName_Link) throws Exception;
	//根据物品数量查找物品名称
	public List<ObjectDO> findObjectByObjectAmount(String ObjectAmount) throws Exception;
	//增加物品信息
	public void insertObject(ObjectDO object) throws Exception;
	//删除物品信息
    public void deleteObject(String ObjectName) throws Exception;
	//更新物品信息
    public void updateObject(String ObjectName) throws Exception;

}
