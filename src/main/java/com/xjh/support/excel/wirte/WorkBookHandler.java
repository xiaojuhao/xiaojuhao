package com.xjh.support.excel.wirte;

import org.apache.poi.ss.usermodel.Workbook;

public interface WorkBookHandler {

    public Workbook createWorkBook(WorkBookType type);
}
