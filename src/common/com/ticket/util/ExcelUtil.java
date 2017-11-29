package com.ticket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel读写工具类
 * @author tuyou
 */
public class ExcelUtil {

	private InputStream input;
	
	private Workbook workbook;
	
	private CellStyle cellStyle; 
	
	/**
	 * 当前游标的定位
	 */
	private Sheet currentSheet;
	private Row currentRow;
	private Cell currentCell;
	
	/**
	 * 当前游标的定位
	 */
	int sheet, row, col;
	
	/**
	 * 根据文件名实例化类
	 * @param fileName 文件绝对路径
	 * @return
	 * @throws Exception
	 */
	public Workbook readExcel(final String fileName) throws Exception{
		
		input = new FileInputStream(fileName);
		if(fileName.endsWith(".xls")){
			
			workbook = new HSSFWorkbook(input);
		}else if(fileName.endsWith(".xlsx")){
			
			workbook = new XSSFWorkbook(input);
		}else{
			
			throw new IllegalAccessException("不支持除：xls/xlsx以外的文件格式!!!");
		}
		readSheet(sheet);
		readRow(row);
		readCell(col);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		
		return workbook;
	}
	
	/**
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Workbook readExcel(File file)throws Exception{
		
		return readExcel(file.getPath());
	}
	
	/**
	 * 创建一个2003格式的excel，文件后缀名为：xls
	 * @return
	 */
	public Workbook createExcel2003(){

		workbook = new HSSFWorkbook();
		readSheet(sheet);
		readRow(row);
		readCell(col);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		return workbook;
	}
	
	/**
	 * 创建一个2003格式的excel，文件后缀名为：xlsx
	 * @return
	 */
	public Workbook createExcel2007(){
		
		workbook = new XSSFWorkbook();
		readSheet(sheet);
		readRow(row);
		readCell(col);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		return workbook;
	}
	
	/**
	 * 获取当前sheet数目 
	 * @return
	 */
	public int getSheetCount(){
		
		return workbook.getNumberOfSheets();
	}
	
	/**
	 * 读取指定的sheet
	 * @param index
	 * @return
	 */
	public Sheet readSheet(int index){
		
		if(workbook != null){
			
			if(index >= 0 && index <= getSheetCount()){
				
				currentSheet = workbook.getSheetAt(index);
				return currentSheet;
			}else if(index >= getSheetCount()){
				
				currentSheet = workbook.createSheet("sheet" + (index+1));
				return currentSheet;
			}
		}
		sheet = index;
		currentSheet = null;
		return null;
	}
	
	/**
	 * 创建指定的sheet，并设置名称
	 * @param index
	 * @param name
	 * @return
	 */
	public Sheet createSheet(int index, String name){
		
		if(workbook != null){
			
			if(index >= 0 && index <= getSheetCount()){ //有则返回
				
				currentSheet = workbook.getSheetAt(index);
				sheet = index;
				return currentSheet;
			}else if(index >= getSheetCount()){ //无责创建
				
				currentSheet = workbook.createSheet(name);
				sheet = getSheetCount() - 1;
			}
		}
		currentSheet = null;
		return null;
	}
	
	/**
	 * 得到当前sheet的行数
	 * @return
	 */
	public int getRowCount(){
		
		if(currentSheet == null){
			
			return 0;
		}
		return currentSheet.getLastRowNum();
	}
	
	/**
	 * 读取指定的行
	 * @param index 行
	 * @return
	 */
	public Row readRow(int row){
		
		readSheet(sheet);
		if(row >= 0 && row <= getRowCount()){ //有则返回
			
			currentRow = currentSheet.getRow(row);
			this.row = row;
			return currentRow;
		}else if(row >= getRowCount()){ //无责创建
			
			currentRow = currentSheet.createRow(row);
			this.row = getRowCount() - 1;
			return currentRow;
		}
		currentRow = null;
		return null;
	}
	
	/**
	 * 读取指定的行
	 * @param index 行
	 * @return
	 */
	public Row readRow(int sheet, int row){
		
		if(readSheet(sheet) != null){
			
			return readRow(row);
		}
		return null;
	}
	
	/**
	 * 得到当前行的单元格数
	 * @return
	 */
	public int getCellCount(){
		
		if(currentRow == null){
			
			return 0;
		}
		return currentRow.getLastCellNum();
	}
	
	/**
	 * 定位cell的位置并返回
	 * @param col
	 * @return
	 */
	public Cell readCell(int col){
		
		if(currentRow == null){
			
			readRow(sheet, row);
		}
		
		if(col < getCellCount() && col > -1){ //有则返回
			
			currentCell = currentRow.getCell(col);
			currentCell.setCellStyle(cellStyle);
			this.col = col;
			return currentCell;
		}else if(col >= getCellCount()){ //无则创建
			
			currentCell = currentRow.createCell(col);
			currentCell.setCellStyle(cellStyle);
			this.col = getCellCount() - 1;
			return currentCell;
		}
		currentCell = null;
		return currentCell;
	}
	
	/**
	 * 定位cell的位置并返回
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell readCell(int row, int col){
		
		if(readRow(row) != null){
			
			return readCell(col);
		}
		return currentCell;
	}
	
	/**
	 * 定位cell的位置并返回
	 * @param sheet
	 * @param row
	 * @param col
	 * @return
	 */
	public Cell readCell(int sheet, int row, int col){
		
		if(readSheet(sheet) != null){
			
			return readCell(row, col);
		}
		return null;
	}
	
	/**
	 * 返回指定单元格的内容
	 * @param index
	 * @return
	 */
	public String getValue(int index){
		
		currentCell = readCell(index);
		if(currentCell == null){
			
			return null;
		}
		
		if(currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC){
			
			DecimalFormat format = new DecimalFormat();
			return format.format(currentCell.getNumericCellValue()).replaceAll(",", "");
		}else{
			
			return currentCell.getStringCellValue();
		}
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容 
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValue(int row, int col){
		
		readRow(row);
		return getValue(col);
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 获取指定单元格的内容
	 * @param sheet
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValue(int sheet, int row, int col){
		
		readSheet(sheet);
		return getValue(row, col);
	}

	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容，如果内容为空，则向上一行读取，直到内容不为空才返回
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValueUpward(int row, int col){
		
		return getValueUpward(row, col, 0);
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容，如果内容为空，则向上一行读取，直到内容不为空才返回
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValueUpward(int row, int col, int minRow){
		
		readRow(row);
		String value = getValue(row, col);
		while(GeneralUtil.isNull(value) && row >= minRow){
			
			value = getValueUpward(--row, col, minRow);
		}
		
		return value;
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容，如果内容为空，则向下一行读取，直到内容不为空才返回
	 * @param index
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValueDownward(int index){
		
		currentCell = readCell(index);
		while(GeneralUtil.isNull(currentCell.getStringCellValue())){
			
			currentCell = readCell(++index);
			if(currentCell == null){
				
				return null;
			}
		}
		return currentCell.getStringCellValue();
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容，如果内容为空，则向下一行读取，直到内容不为空才返回
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValueDownward(int row, int col){
		
		readRow(row);
		return getValueDownward(col);
	}
	
	/**
	 * @Title: getValue 
	 * @Description: 读取指定单元格的内容，如果内容为空，则向下一行读取，直到内容不为空才返回
	 * @param sheet
	 * @param row
	 * @param col
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public String getValueDownward(int sheet, int row, int col){
		
		readSheet(sheet);
		return getValueDownward(row, col);
	}
	
	/**
	 * @Title: close 
	 * @Description: 关闭输入流
	 * @param  设定文件 
	 * @return void 返回类型 
	 * @throws
	 */
	public void close(){
		
		if(input != null){
			
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 写excel到输出流
	 * @param out
	 */
	public void save(OutputStream out){
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(out != null){
				
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void write(String value){

		if(currentCell != null){
			
			currentCell.setCellValue(value);
			if(currentSheet != null){
				
				currentSheet.autoSizeColumn(col);
			}
		}
	}
	
	public void write(int number){
		
		if(currentCell != null){
			
			currentCell.setCellValue(number);
			if(currentSheet != null){
				
				currentSheet.autoSizeColumn(col);
			}
		}
	} 
	
	public void write(double number){
		
		if(currentCell != null){
			
			currentCell.setCellValue(number);
			if(currentSheet != null){
				
				currentSheet.autoSizeColumn(col);
			}
		}
	} 
	
	public void write(Date date){
		
		if(currentCell != null){
			
			currentCell.setCellValue(date);
			if(currentSheet != null){
				
				currentSheet.autoSizeColumn(col);
			}
		}
	} 
	
	public void write(Object obj){
		
		if(currentCell != null){
			
			if(obj instanceof Double){
				
				currentCell.setCellValue((Double)obj);
			}else if(obj instanceof Integer){
				
				currentCell.setCellValue((Integer)obj);
			}else if(obj instanceof String || obj == null){
				
				currentCell.setCellValue((String)obj);
			}else if(obj instanceof Date){
				
				currentCell.setCellValue(DateUtil.formatDateToSimpleString((Date)obj));
			}
			if(currentSheet != null){
				
				currentSheet.autoSizeColumn(col);
			}
		}
	}
	
	public void write(int col, String value){
		
		readCell(col);
		write(value);
	}
	
	public void write(int col, int number){
		
		readCell(col);
		write(number);
	} 
	
	public void write(int col, double number){
		
		readCell(col);
		write(number);
	} 
	
	public void write(int col, Date date){
		
		readCell(col);
		write(date);
	} 
	
	public void write(int col, Object obj){
		
		readCell(col);
		write(obj);
	} 
	
	public void write(int row, int col, String value){
		
		readCell(row, col);
		write(value);
		currentCell.setCellStyle(cellStyle);
	}
	
	public void write(int row, int col, int number){
		
		readCell(row, col);
		write(number);
		currentCell.setCellStyle(cellStyle);
	} 
	
	public void write(int row, int col, double number){
		
		readCell(row, col);
		write(number);
	} 
	
	public void write(int row, int col, Date date){
		
		readCell(row, col);
		write(date);
	} 
	
	public void write(int row, int col, Object obj){
		
		readCell(row, col);
		write(obj);
	} 
	
	public void write(int sheet, int row, int col, String value){
		
		readCell(sheet, row, col);
		write(value);
	}
	
	public void write(int sheet, int row, int col, int number){
		
		readCell(sheet, row, col);
		write(number);
	} 
	
	public void write(int sheet, int row, int col, double number){
		
		readCell(sheet, row, col);
		write(number);
	} 
	
	public void write(int sheet, int row, int col, Date date){
		
		readCell(sheet, row, col);
		write(date);
	} 
	
	public void write(int sheet, int row, int col, Object obj){
		
		readCell(sheet, row, col);
		write(obj);
	} 
}
