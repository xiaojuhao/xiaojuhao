package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.MaterielDO;

public interface MeterielMapper {
	//根据供货商查找物品信息
	public List<MaterielDO> findMeterielBySupplierName(String SupplierName) throws Exception;
	//根据物品名称查找物品信息
	public MaterielDO findMeterielByMaterielName(String MaterielName) throws Exception;
	//根据有效期查找物品信息
	public List<MaterielDO> findMaterielByMaterielTermOfValidity(String MaterielTermOfValidity) throws Exception;
	//根据入库时间查找物品信息
	public List<MaterielDO> findMaterielByObjectStorageTime(String MaterielStorageTime) throws Exception;
	//根据物品数量查找物品名称
	public List<MaterielDO> findMaterielByObjectAmount(String ObjectAmount) throws Exception;
	//增加物品信息
	public void insertMateriel(MaterielDO materiel) throws Exception;
	//删除物品信息
    public void deleteMateriel(String MaterielName) throws Exception;
	//更新物品信息
    public void updateMateriel(String MaterielName) throws Exception;

}
