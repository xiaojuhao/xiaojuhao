/**
 * 用户类，存储用户信息
 */
package com.xjh.dao.dataobject;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDO {
	
	Long ID;//自增序号
	String Account;//账号(必须)
	String Password;//密码(必须)
	Integer Privilege;// 权限(必须)
	String StroeNumber;// 门店编号
	String Name;// 名字(必须)
	String IDNumber;// 身份证号(必须)
	Boolean SEX;//性别
	Integer Age;// 年龄
	String EmployeeNumber;// 员工编号(流水号)
	String Position;// 职位(等级)
	String State;// 当前状态(未入职、在职、离职、休假)
	String HoursOfLabour;// 工作时段
	Date EntryTime;// 入职时间
	Date LeaveDate;// 离职时间
	Date Birthday;// 生日
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Integer getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(Integer privilege) {
		Privilege = privilege;
	}
	public String getStroeNumber() {
		return StroeNumber;
	}
	public void setStroeNumber(String stroeNumber) {
		StroeNumber = stroeNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public Boolean getSEX() {
		return SEX;
	}
	public void setSEX(Boolean sEX) {
		SEX = sEX;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public String getEmployeeNumber() {
		return EmployeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		EmployeeNumber = employeeNumber;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getHoursOfLabour() {
		return HoursOfLabour;
	}
	public void setHoursOfLabour(String hoursOfLabour) {
		HoursOfLabour = hoursOfLabour;
	}
	public Date getEntryTime() {
		return EntryTime;
	}
	public void setEntryTime(Date entryTime) {
		EntryTime = entryTime;
	}
	public Date getLeaveDate() {
		return LeaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		LeaveDate = leaveDate;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

}
