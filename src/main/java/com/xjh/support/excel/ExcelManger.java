package com.xjh.support.excel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xjh.support.excel.read.ExcelRead;
import com.xjh.support.excel.wirte.CellData;
import com.xjh.support.excel.wirte.CellService;
import com.xjh.support.excel.wirte.CellServiceImpl;
import com.xjh.support.excel.wirte.SheetService;
import com.xjh.support.excel.wirte.SheetServiceImpl;
import com.xjh.support.excel.wirte.WorkBookHandler;
import com.xjh.support.excel.wirte.WorkBookHandlerImpl;
import com.xjh.support.excel.wirte.WorkBookType;

/**
 * 使用类
 * 
 * @author ghliu
 */
public class ExcelManger {
    private static final String EXTENSION_XLS  = "xls";
    private static final String EXTENSION_XLSX = "xlsx";
    private static final Logger       LOG            = LoggerFactory.getLogger(ExcelManger.class);
    private ExcelManger() {

    }

    private static class SingletonHolder {
        private static final ExcelManger INSTANCE = new ExcelManger();
    }

    public static final ExcelManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Map<String, Object>> getExcel(InputStream in, String[] colnumName, int startSheet, int startRow)
            throws Exception {
        return getExcel(in, colnumName, null, startSheet, startRow);
    }

    /**
     * 默认2003
     * 
     * @param fileName
     * @param cellDatas
     * @param workType
     */
    public void createExcel(String fileName, CellData cellDatas, String fileType) {
        if (fileName == null || fileName.equals("")) {
            return;
        }
        WorkBookType workType = null;
        if (StringUtils.isBlank(fileType)) {
            workType = WorkBookType.XLS;
        }
        if (StringUtils.equals(fileType, EXTENSION_XLS)) {
            workType = WorkBookType.XLS;
        }

        if (StringUtils.equals(fileType, EXTENSION_XLSX)) {
            workType = WorkBookType.XLSX;
        }

        WorkBookHandler workBook = new WorkBookHandlerImpl();
        Workbook workbook = workBook.createWorkBook(workType);

        SheetService sheetService = new SheetServiceImpl();

        List<Sheet> sheets = sheetService.createSheet(workbook);

        CellService cellService = new CellServiceImpl();

        for (Sheet sheet : sheets) {
            cellService.createCells(workbook, cellDatas, sheet);
        }
        FileOutputStream fileOut = null;
        BufferedOutputStream bo = null;
        try {
            fileOut = new FileOutputStream(new File(fileName));
            bo = new BufferedOutputStream(fileOut);
            workbook.write(bo);
        } catch (IOException ex) {
            LOG.error("createExcel异常",ex);
        } finally {
            try {
                if (bo != null) {
                    bo.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                LOG.error("createExcel异常",e);
            }
        }
    }

    /**
     * @param out
     * @param cellDatas
     * @param fileType
     */
    public void createExcel(OutputStream out, CellData cellDatas, String fileType) {
        WorkBookType workType = null;
        if (StringUtils.isBlank(fileType)) {
            workType = WorkBookType.XLS;
        }
        if (StringUtils.equals(fileType, EXTENSION_XLS)) {
            workType = WorkBookType.XLS;
        }

        if (StringUtils.equals(fileType, EXTENSION_XLSX)) {
            workType = WorkBookType.XLSX;
        }

        WorkBookHandler workBook = new WorkBookHandlerImpl();
        Workbook workbook = workBook.createWorkBook(workType);

        SheetService sheetService = new SheetServiceImpl();

        List<Sheet> sheets = sheetService.createSheet(workbook);

        CellService cellService = new CellServiceImpl();

        for (Sheet sheet : sheets) {
            cellService.createCells(workbook, cellDatas, sheet);
        }
        BufferedOutputStream bo = null;
        try {
            bo = new BufferedOutputStream(out);
            workbook.write(bo);
        } catch (IOException ex) {
            LOG.error("createExcel异常",ex);
        } finally {
            try {
                if (bo != null) {
                    bo.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOG.error("createExcel异常",e);
            }
        }
    }

    public List<Map<String, Object>> getExcel(InputStream is, String[] colnumName, Integer[] colnumType,
                                              int startSheet, int startRow) throws Exception {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ExcelRead excel = new ExcelRead();
        // 存入文件列名,必填项
        excel.setColnumName(colnumName);
        // 文件列名对应类型,可选,不写可能会出现异常
        if (null != colnumType && colnumType.length > 0)
            excel.setColnumType(colnumType);

        List<List<HashMap<String, Object>>> exceldata = excel.getExcel(is, startSheet, startRow);
        if (exceldata != null) {
            for (List<HashMap<String, Object>> lista : exceldata) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < lista.size(); i++) {
                    HashMap<String, Object> hm = lista.get(i);
                    map.put(colnumName[i], hm.get(colnumName[i]));
                }
                list.add(map);
            }
        }
        return list;
    }

   /* public static void main(String[] args) throws Exception {
        File file1 = new File("E:\\xx.xlsx");
        InputStream in1 = new FileInputStream(file1);
        String[] colnumName1 = { "Name", "Age", "Sex", "Birthday", "Address", "Email", "Phone" };
        System.out.println(ExcelManger.getInstance().getExcel(in1, colnumName1, 0, 1));
        //2007,2003
        ExcelManger.getInstance().createExcel("E:\\excel_test.xlsx", getCellData(), EXTENSION_XLSX);
    }

    private static CellData getCellData() {
        CellData cellDatas = new CellData();
        List<String> titles = new ArrayList<String>();
        titles.add("标题测试1");
        titles.add("标题测试2");
        titles.add("标题测试3");
        titles.add("标题测试4");

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (int i = 0; i < 100000; i++) {
            List<String> vals = new ArrayList<String>(titles.size());
            vals.add("123142354365423");
            vals.add("值测试2");
            vals.add("值测试3");
            vals.add("值测试4");
            map.put(String.valueOf(i), vals);
        }
        cellDatas.setTitlesList(titles);
        cellDatas.setMap(map);

        return cellDatas;
    }*/
}
