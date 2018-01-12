package com.xjh.support.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xjh.support.excel.model.CfCell;
import com.xjh.support.excel.model.CfRow;
import com.xjh.support.excel.model.CfSheet;
import com.xjh.support.excel.model.cell.CfBooleanCell;
import com.xjh.support.excel.model.cell.CfByteCell;
import com.xjh.support.excel.model.cell.CfDateCell;
import com.xjh.support.excel.model.cell.CfDoubleCell;
import com.xjh.support.excel.model.cell.CfIntegerCell;
import com.xjh.support.excel.model.cell.CfLongCell;
import com.xjh.support.excel.model.cell.CfNullCell;
import com.xjh.support.excel.model.cell.CfNumbericCell;
import com.xjh.support.excel.model.cell.CfStringCell;

/**
 * 
 * 类CfWorkbook.java的实现描述：TODO 类实现描述 
 * @author yinguoliang 2016年9月21日 下午1:05:55
 */
public class CfWorkbook {
	private static Logger LOG = LoggerFactory.getLogger(CfWorkbook.class);
	public List<CfSheet> sheets = new ArrayList<CfSheet>();

	/**
	 * 创建一个sheet
	 * @return
	 */
	public CfSheet newSheet() {
		CfSheet s = new CfSheet();
		sheets.add(s);
		return s;
	}

	/**
	 * 创建一个sheet
	 * @param sheetName
	 * @return
	 */
	public CfSheet newSheet(String sheetName) {
		CfSheet s = new CfSheet();
		s.name = sheetName;
		sheets.add(s);
		return s;
	}

	/**
	 * 返回给定位置的sheet对象
	 * @param i
	 * @return
	 */
	public CfSheet sheet(int i) {
		return sheets.get(i);
	}

	/**
	 * 根据Name返回sheet
	 * @param sheetName
	 * @return
	 */
	public CfSheet sheet(String sheetName) {
		for (CfSheet s : sheets) {
			if (s.name.equals(sheetName)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * 将CfWorkbook转为HSSFWorkbook<br>
	 * 主要的逻辑就是遍历CfWorkbook里面的sheets-rows-cells,<br>
	 * 逐个对应的HSSFWorkbook(三层循环)<br>
	 * 导出的时候会考虑excel的样式
	 * @return
	 */
	public HSSFWorkbook toHSSFWorkbook() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFDataFormat fmt = wb.createDataFormat();
		int sheetIdx = 0;
		//遍历sheet
		for (CfSheet sheet : sheets) {
			sheet.sheetInx = sheetIdx++;
			HSSFSheet sh = null;
			if (sheet.name == null || sheet.name.trim().isEmpty()) {
				sh = wb.createSheet();
			} else {
				sh = wb.createSheet(sheet.name);
			}
			int rowIdx = 0;
			//遍历rows
			for (CfRow row : sheet.rows) {
				row.rowIdx = rowIdx++;
				HSSFRow r = sh.createRow(row.rowIdx);
				int cellIdx = -1;
				//遍历cells
				for (CfCell cell : row.cells) {
					cellIdx++;
					HSSFCellStyle style = wb.createCellStyle();
					//给cell加上边框
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);
					if (row.isTitleRow) {//如果是title,则加上背景色，并居中
						HSSFFont font = wb.createFont();
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						style.setFont(font);
						style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
					} else if (cell.isReadOnly) {//只读的内容，加上背景色
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
					}

					HSSFCell c = r.createCell(cellIdx);
					c.setCellStyle(style);
					//String
					if (cell instanceof CfStringCell) {
						String val = ((CfStringCell) cell).value;
						c.setCellValue(val);
						if (cell.width > 0) {
							sheet.minWidth(cellIdx, cell.width);
						} else {
							sheet.minWidth(cellIdx, val.length() * 2);
						}
					}
					//Date
					else if (cell instanceof CfDateCell) {
						CfDateCell dateCell = (CfDateCell) cell;
						String format = dateCell.format;
						if (format == null)
							format = "yyyy-MM-dd";
						style.setDataFormat(fmt.getFormat(format));
						c.setCellValue(dateCell.value);
						sheet.minWidth(cellIdx, 15);
					}
					//Double
					else if (cell instanceof CfDoubleCell) {
						CfDoubleCell doubleCell = (CfDoubleCell) cell;
						c.setCellValue(doubleCell.value);
					}
					//Long
					else if (cell instanceof CfLongCell) {
						CfLongCell longCell = (CfLongCell) cell;
						style.setDataFormat(fmt.getFormat("0"));
						c.setCellValue(longCell.value);
					}
					//Integer
					else if (cell instanceof CfIntegerCell) {
						CfIntegerCell intCell = (CfIntegerCell) cell;
						style.setDataFormat(fmt.getFormat("0"));
						c.setCellValue(intCell.value);
					}
					//Byte
					else if (cell instanceof CfByteCell) {
						CfByteCell byteCell = (CfByteCell) cell;
						style.setDataFormat(fmt.getFormat("0"));
						c.setCellValue(byteCell.value);
					}
					//NULL
					else if (cell instanceof CfNullCell) {
						c.setCellType(HSSFCell.CELL_TYPE_BLANK);
					}
					//others
					else {
						throw new RuntimeException("unsupported type");
					}
				}
			}
			//
			if (sheet.isHidden) {
				wb.setSheetHidden(sheet.sheetInx, true);
			}
			//
			for (Map.Entry<Integer, Integer> entry : sheet.columnWidth.entrySet()) {
				int column = entry.getKey();
				int width = entry.getValue();
				if (width > 5 && width < 30) {
					sh.setColumnWidth(column, (width + 2) * 256);
				} else if (width >= 30) {
					sh.setColumnWidth(column, 40 * 256);
				}
			}
		}
		return wb;
	}

	/**
	 * 从文件中读取excel，并封装为CfWorkbook<br>
	 * 注意：这里只读取excel内容，包括样式等信息会丢失
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public void readFrom(File file) throws IOException {
		if (file == null || !file.exists() || file.isDirectory()) {
			return;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			readFrom(fis);
		} finally {
			//			fis.close();
			IOUtils.closeQuietly(fis);
		}
	}

	public void readFrom(InputStream fis) throws IOException {
		POIFSFileSystem poiFile = new POIFSFileSystem(fis);
		HSSFWorkbook hssfWb = new HSSFWorkbook(poiFile);
		int numberOfSheets = hssfWb.getNumberOfSheets();
		//read and build sheet
		for (int i = 0; i < numberOfSheets; i++) {
			HSSFSheet s = hssfWb.getSheetAt(i);
			CfSheet sheet = new CfSheet();
			//read one sheet
			readHSSFSheet(sheet, s);
			sheet.sheetInx = i;
			sheets.add(sheet);
		}
	}

	/**
	 * @param sheet
	 * @param hssfSheet
	 * @return
	 */
	private CfSheet readHSSFSheet(CfSheet sheet, HSSFSheet hssfSheet) {
		sheet.name = hssfSheet.getSheetName();
		for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null)
				continue;
			sheet.newRow();//row是一对一的
			//读取cell
			for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
				HSSFCell hssfCell = hssfRow.getCell(cellNum);
				//读取cell内容
				CfCell cell = this.readFromHSSFCell(hssfCell);
				cell.row = sheet.lastRow();
				cell.sheet = cell.row.sheet;
				sheet.lastRow().cells.add(cell);
			}
		}
		return sheet;
	}

	/**
	 * 读取HSSFCell里面的内容
	 * @param hssfCell
	 * @return
	 */
	private CfCell readFromHSSFCell(HSSFCell hssfCell) {
		if (hssfCell == null) {
			return new CfNullCell();
		}
		CfCell cell = null;
		switch (hssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			String strVal = hssfCell.getStringCellValue();
			cell = new CfStringCell(StringUtils.isBlank(strVal) ? null : strVal);
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			Double dblVal = hssfCell.getNumericCellValue();
			CfNumbericCell numCell = new CfNumbericCell(dblVal);
			//单元格可能是日期，但是excel这里无法区分
			//所以，防止万一，顺便读出来，使用的时候有业务自己判断是使用Double还是Date
			try {
				if (dblVal != null && dblVal >= 0)
					numCell.dateValue = hssfCell.getDateCellValue();
			} catch (Exception ex) {
				LOG.error("", ex);
			}
			cell = numCell;
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			Boolean boolVal = hssfCell.getBooleanCellValue();
			cell = new CfBooleanCell(boolVal);
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cell = new CfNullCell();
			break;
		default:
			cell = new CfNullCell();
			break;
		}
		return cell;
	}
}
