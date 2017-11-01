package com.xjh.dao.mapper;

import java.util.List;

import com.xjh.dao.dataobject.UserDO;

public interface UserMapper {
	
    //根据账户名称查询用户信息
    public UserDO findUserByAccount(String Account) throws Exception;
    //根据名字查询用户
    public List<UserDO> findUserByName(String name) throws Exception;
    //添加用户信息
    public void insertUser(UserDO user) throws Exception;
    //删除用户信息
    public void deleteUser(String Account) throws Exception;
    //更新用户信息
    public void updateUser(String Account) throws Exception;

}
