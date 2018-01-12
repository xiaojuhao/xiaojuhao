package com.xjh.support.excel.wirte;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SheetServiceImpl implements SheetService {

    @Override
    public List<Sheet> createSheet(Workbook wb, String... sheetNames) {
        String defaultName = "sheet_1";
        List<Sheet> list = new ArrayList<Sheet>();
        if (wb == null) {
            return null;
        }
        if (sheetNames == null || sheetNames.length == 0) {
            Sheet sheet = wb.createSheet(defaultName);
            list.add(sheet);
        }
        if (sheetNames != null) {
            for (int i = 0; i < sheetNames.length; i++) {
                Sheet sheet = wb.createSheet(sheetNames[i]);
                list.add(sheet);
            }
        }
        return list;
    }

}
