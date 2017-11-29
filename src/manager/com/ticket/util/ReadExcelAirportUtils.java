package com.ticket.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ticket.pojo.AirportInfo;

public class ReadExcelAirportUtils {

	private Workbook wb;		// 工作簿
	private Sheet sheet;		// excel对应的sheet
	private Row row;			// 行号
	
	/**
	 * 初始化数据
	 * @param filePath
	 */
	public ReadExcelAirportUtils(String filePath) {
		
		if(filePath == null){
			
			return;
		}
		
		String ext = filePath.substring(filePath.lastIndexOf("."));
		try {
			InputStream inputStream = new FileInputStream(filePath);
			if (".xls".equals(ext)) {
				
				wb = new HSSFWorkbook(inputStream);
			} else if (".xlsx".equals(ext)) {
				
				wb = new XSSFWorkbook(inputStream);
			} else {
				wb = null;
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取Excel 表格表头的内容
	 * @return
	 * @throws Exception 
	 */
	public String[] readExcelTitle() throws Exception{
		
		if(wb == null){
			
			throw new Exception("WorkBook 对象为空") ;
		}
		
		sheet = wb.getSheetAt(0);					 // 获取第一个sheet
		row = sheet.getRow(4);						 // 获取当前获取到的 sheet 的第一行
		int colNum = row.getPhysicalNumberOfCells(); // 标题总列数
		System.out.println("colNum:"+ colNum);
		
		String[] titles = new String[colNum];
		for(int i = 0; i < colNum; i++){
			
			titles[i] = row.getCell(i).getCellFormula();
		}
		
		return titles;
	}
	
	/**
	 * 读取Excel数据内容
	 * @param InputStream
     * @return Map 包含单元格数据内容的Map对象
	 * @throws Exception 
	 */
	public List<AirportInfo> readExcelContent() throws Exception{
		
		if (wb == null) {
			
			throw new Exception("Workbook对象为空！");
		}
		
		List<AirportInfo> list = new ArrayList<AirportInfo>();
		sheet = wb.getSheetAt(0);					// 获取第一个sheet
		int rowNum = sheet.getLastRowNum();			// 得到总行数
		row = sheet.getRow(0);						// 得到第0行(excel的第一行)
		int colNum = row.getPhysicalNumberOfCells();// 获取行上的总的列数
		
		// 正文内容应该从第二行开始,第一行为表头的标题
		for(int i = 4; i < rowNum; i++){    		// 循环行
			
			row = sheet.getRow(i);
			int j = 0;
			//创建一个航班计划的
			AirportInfo airportInfo = new AirportInfo();
			while(j < colNum){ 						 //  循环列
				
				Object obj = getCellFormatValue(row.getCell(j));
				switch (j) {
					case 1:airportInfo.setThreeCode(obj.toString());
					break;
					case 2:airportInfo.setEnglishName(obj.toString());
					break;
					case 3:airportInfo.setCountryCode(obj.toString());
					break;
					case 4:airportInfo.setDistrictFlag(obj.toString());
					break;
					case 5:airportInfo.setName(obj.toString());
					break;
					case 6:airportInfo.setFourCode(obj.toString());
					break;
					case 7:airportInfo.setSearchFlag(obj.toString());
				}
				
				j++;
			}
			
			list.add(airportInfo);
		}
		
		return list;
	}
	
	/**
	 * 根据Cell类型设置数据
	 * @param cell
	 * @return
	 */
	private Object getCellFormatValue(Cell cell){
		
		Object cellvalue = "";
		if(GeneralUtil.isNotNull(cell)){		//存在值
			
			switch (cell.getCellType()) { 		// 根据列的类型
			case Cell.CELL_TYPE_NUMERIC: 	    // 数字类型
				
				//先看是否是日期格式     
                if(HSSFDateUtil.isCellDateFormatted(cell)){     
                    //读取日期格式     
                	 Date date = cell.getDateCellValue();
                     cellvalue = date;  
                }else{     
                    //读取数字     
                	cellvalue = cell.getNumericCellValue();   
                }     
				break;
			case Cell.CELL_TYPE_STRING:         // 字符类型
				
				// 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
				break;
				
			case Cell.CELL_TYPE_BOOLEAN:		// 布尔类型
				
				cellvalue = cell.getBooleanCellValue();
				break;
				
			case Cell.CELL_TYPE_FORMULA:  		// 表达式
				
				 //读取公式     
				cellvalue = cell.getCellFormula();
				break;
			default:  
				cellvalue = "";  
			}
		}
		
		return cellvalue;
	}
}
