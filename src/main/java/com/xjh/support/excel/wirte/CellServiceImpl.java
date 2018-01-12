package com.xjh.support.excel.wirte;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class CellServiceImpl implements CellService {

    /**
     * 没有样式的列
     * 
     * @param datas 数据源
     * @param sheet excel里面的Sheet面板对象
     */
    @Override
    public void createCells(Workbook wb, CellData dataSource, Sheet sheet) {
        if (sheet == null || dataSource == null) {
            return;
        }
        //设置标题
        Row row = sheet.createRow(0);
        List<String> titles = dataSource.getTitlesList();

        for (int m = 0; m < titles.size(); m++) {
            Cell cell = row.createCell(m);
            cell.setCellValue(titles.get(m));
        }
        Map<String, List<String>> map = dataSource.getMap();
        if (map == null || map.size() < 1) {
            return;
        }
        for (int i = 0; i < map.size(); i++) {
            List<String> list = map.get(String.valueOf(i));
            if (list == null) {
                continue;
            }
            //第二行开始设置值
            Row rows = sheet.createRow(i + 1);
            for (int j = 0; j < list.size(); j++) {
                Cell cell = rows.createCell(j);
                String value = Util.isBigNumber(list.get(j)) != true ? list.get(j) : list.get(j) + "";
                cell.setCellValue(value);
            }
        }

        //        Iterator<Entry<String, List<String>>> it = (Iterator<Entry<String, List<String>>>) map.entrySet().iterator();
        //        int i = 1;
        //        while (it.hasNext()) {
        //            Entry<String, List<String>> entry = it.next();
        //            //第二行开始设置值 Row rows = sheet.createRow(i); List<String> list =
        //            entry.getValue();
        //            for (int j = 0; j < list.size(); j++) {
        //                Cell cell = rows.createCell(j);
        //                String value = Util.isBigNumber(list.get(j)) != true ? list.get(j) : list.get(j) + "";
        //                cell.setCellValue(value);
        //            }
        //            i++;
        //        }

    }
}
