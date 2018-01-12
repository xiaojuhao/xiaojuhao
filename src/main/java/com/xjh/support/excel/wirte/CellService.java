package com.xjh.support.excel.wirte;
 
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public interface CellService {
     void createCells(Workbook workbook,CellData datas,Sheet sheet);
}


