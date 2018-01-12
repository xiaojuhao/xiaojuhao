package com.xjh.support.excel.wirte;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface SheetService {
    List<Sheet> createSheet(Workbook wb, String... sheetNames);
}
