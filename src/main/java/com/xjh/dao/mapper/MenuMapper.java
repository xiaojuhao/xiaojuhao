package com.xjh.dao.mapper;


import com.xjh.dao.dataobject.MenuDO;

public interface MenuMapper {
	
    //根据菜品编号查询菜品信息
    public MenuDO findMenuByMenuCode(String menuCode) throws Exception;
    //添加才菜品信息
    public void insertMenu(MenuDO menu) throws Exception;
    //删除菜品信息
    public void deleteMenu(String menuCode) throws Exception;

}
