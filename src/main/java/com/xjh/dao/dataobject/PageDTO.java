package com.xjh.dao.dataobject;

import lombok.Data;

@Data
public class PageDTO {
	int pageSize = 20;
	int pageNo = 1;
}
