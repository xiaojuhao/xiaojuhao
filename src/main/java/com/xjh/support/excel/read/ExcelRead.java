package com.xjh.support.excel.read;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ServiceException;

/**
 * 获取excel内容
 * 
 * @author ghliu
 */
public class ExcelRead {

    private final static Logger     LOG = LoggerFactory.getLogger(ExcelRead.class);
    /** 列名 */
    private String[]                colnumName;
    /** 列类型 */
    private Integer[]               colnumType;
    /** 代码KV */
    private HashMap<String, Object> codeType;
    /** 写入到的Object类型,该方法必须继承ExcelObj才可以使用 */
    private ExcelObj                dto;

    /**
     * 获取excel到一个List<List<HashMap<String,Object>>> 从外到内依次为：sheet页,行,单元格
     * 
     * @param file 要读取的文件
     * @param snum 读取那个sheet页,从0算起
     * @param rnum 从那行开始读取,从0算起
     * @return 返回一个List<List<HashMap<String,Object>>>对象
     * @throws Exception
     */
    public List<List<HashMap<String, Object>>> getExcel(InputStream is, int snum, int rnum) throws Exception {
        List<List<HashMap<String, Object>>> list = new ArrayList<List<HashMap<String, Object>>>();
        Object localObject = null;
        // 传入路径
        Workbook workbook = WorkbookFactory.create(is);
        Sheet childSheet = workbook.getSheetAt(snum);
        for (int j = rnum; j <= childSheet.getLastRowNum(); j++) {
            //读取行元素
            List<HashMap<String, Object>> listrow = new ArrayList<HashMap<String, Object>>();
            Row row = childSheet.getRow(j);
            if (null != row) {
                HashMap<String, Object> cellv = null;
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    //读取单元格
                    Cell cell = row.getCell(k);
                    cellv = new HashMap<String, Object>();
                    if (cell == null) {
                        cellv.put(colnumName[k], null);
                        listrow.add(cellv);
                        continue;
                    } else {
                        // 判断获取类型
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                localObject = cell.getNumericCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                localObject = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                localObject = cell.getBooleanCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                localObject = "";
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                int a = (cell.getCellFormula().indexOf("+") + 1)
                                        + (cell.getCellFormula().indexOf('/') + 1)
                                        + (cell.getCellFormula().indexOf('*') + 1)
                                        + (cell.getCellFormula().indexOf('-') + 1);
                                if (a <= 0) {
                                    localObject = cell.getCellFormula();
                                } else if (a > 0) {
                                    localObject = cell.getNumericCellValue();
                                }
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                localObject = Byte.valueOf(cell.getErrorCellValue());
                                break;
                            default:
                                LOG.info("未知类型");
                                break;
                        }
                        try {
                            //限制类型的时候,做下面的类型强制转换
                            if (colnumType != null) {
                                localObject = getRightTypeValue(localObject, k);
                            }
                        } catch (Exception e) {
                            LOG.error("读取excel限制类型异常：", e);
                            cellv.put("ErrMsg", e.getMessage());
                            listrow.add(cellv);
                            continue;
                        }
                        cellv.put(colnumName[k], localObject);
                    }
                    listrow.add(cellv);
                }
                list.add(listrow);
            }
        }
        return list;
    }

    /**
     * 这里获取的值是输入正确,但是单元格属性设置错误导致类型错误,需要矫正的值
     * 
     * @param localObject
     * @param k
     * @return 经过类型矫正的值
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private Object getRightTypeValue(Object localObject, int k) throws Exception {
        switch (colnumType[k]) {
            case ColT.NO://不做任何转换
                break;
            case ColT.CCODE:
                //需要根据KV转换规则进行转换
                Object colType = codeType.get(colnumName[k]);
                if (colType != null) {
                    HashMap<String, Object> colHm = (HashMap<String, Object>) colType;
                    Object colObj = colHm.get(localObject.toString());
                    if (colObj == null) {
                        throw new ServiceException("找不到对应的代码值!");
                    } else {
                        localObject = colObj;
                    }
                } else {
                    Object obj = codeType.get(localObject.toString());
                    if (obj == null) {
                        throw new ServiceException("找不到对应的代码值!");
                    } else {
                        localObject = obj;
                    }
                }
                break;
            case ColT.CDATE:
                if (!(localObject instanceof Date)) {
                    if (localObject instanceof String) {
                        //按照格式yyyy-MM-dd转换
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        localObject = sdf.parse(localObject.toString());
                    } else if (localObject instanceof Double) {
                        int day = ((Double) localObject).intValue();
                        Calendar c = Calendar.getInstance();
                        c.set(1900, 0, 1);
                        c.add(Calendar.DAY_OF_YEAR, day);
                        localObject = c.getTime();
                    } else {
                        throw new ServiceException("日期格式错误");
                    }
                }
                break;
            case ColT.CDOUBLE:
                if (!(localObject instanceof Double)) {
                    if (localObject instanceof String) {
                        localObject = Double.parseDouble(localObject.toString());
                    }
                }
                break;
            case ColT.CFLOAT:
                if (localObject instanceof Double) {
                    localObject = ((Double) localObject).floatValue();
                }
                break;
            case ColT.CINT:
                if (localObject instanceof Double) {
                    localObject = ((Double) localObject).intValue();
                }
                break;
            case ColT.CLONG:
                if (localObject instanceof Double) {
                    localObject = ((Double) localObject).longValue();
                }
                break;
            case ColT.CSTRING:
                if (localObject instanceof Double) {
                    java.text.DecimalFormat formatter = new java.text.DecimalFormat("######## ");
                    localObject = formatter.format(localObject);
                } else {
                    localObject = localObject.toString();
                }
                break;
        }
        return localObject;
    }

    public ExcelObj getDTO() {
        return dto;
    }

    public void setDTO(ExcelObj dto) {
        this.dto = dto;
    }

    public String[] getColnumName() {
        return colnumName;
    }

    public void setColnumName(String[] newColnumName) {
        if (ArrayUtils.isNotEmpty(newColnumName)) {
            this.colnumName = Arrays.copyOf(newColnumName, newColnumName.length);
        } else {
            this.colnumName = null;
        }
    }

    public Integer[] getColnumType() {
        return colnumType;
    }

    public void setColnumType(Integer[] newColnumType) {
        if (ArrayUtils.isNotEmpty(newColnumType)) {
            this.colnumType = Arrays.copyOf(newColnumType, newColnumType.length);
        } else {
            this.colnumType = null;
        }
    }

    public HashMap<String, Object> getCodeType() {
        return codeType;
    }

    public void setCodeType(HashMap<String, Object> codeType) {
        this.codeType = codeType;
    }

}
