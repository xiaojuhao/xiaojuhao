package com.xjh.support.excel.wirte;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkBookHandlerImpl implements WorkBookHandler {

    @Override
    public Workbook createWorkBook(WorkBookType type) {
        if (type == WorkBookType.XLS) {
            return new HSSFWorkbook();
        } else if (type == WorkBookType.XLSX) {
            return new XSSFWorkbook();
        } else {
            return null;
        }
    }

}
