package com.xjh.valueobject;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MenuVo implements Serializable {
	private static final long serialVersionUID = 1L;
	Long id;
	String menuName;
	String menuCode;
	String menuIcon;
	String menuIndex;
	List<MenuVo> subs;
}
