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

import com.ticket.pojo.AirportPlan;

public class ReadExcelUtils {

	private Workbook wb;		// 工作簿
	private Sheet sheet;		// excel对应的sheet
	private Row row;			// 行号
	
	/**
	 * 初始化数据
	 * @param filePath
	 */
	public ReadExcelUtils(String filePath) {
		
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
	public List<AirportPlan> readExcelContent() throws Exception{
		
		if (wb == null) {
			
			throw new Exception("Workbook对象为空！");
		}
		
		List<AirportPlan> list = new ArrayList<AirportPlan>();
		sheet = wb.getSheetAt(0);					// 获取第一个sheet
		int rowNum = sheet.getLastRowNum();			// 得到总行数
		row = sheet.getRow(0);						// 得到第0行(excel的第一行)
		int colNum = row.getPhysicalNumberOfCells();// 获取行上的总的列数
		
		// 正文内容应该从第二行开始,第一行为表头的标题
		for(int i = 4; i < rowNum; i++){    		// 循环行
			
			row = sheet.getRow(i);
			int j = 0;
			//创建一个航班计划的
			AirportPlan airportPlan = new AirportPlan();
			while(j < colNum){ 						 //  循环列
				
				Object obj = getCellFormatValue(row.getCell(j));
				switch (j) {
				case 1:			//	表示的是批号
					
					airportPlan.setBatch_number(obj.toString());
					break;
				case 2:			//	航班号
					
					airportPlan.setFlt(obj.toString());
					break;
					
				case 3:			//	机型
					
					airportPlan.setAirportType(obj.toString());
					break;
					
				case 4:			//	任务
					
					airportPlan.setTask(obj.toString());
					break;
					
				case 5:			//	周期
					
					airportPlan.setCycle(obj.toString());
					break;
					
				case 6:			//	起飞 
					
					airportPlan.setDept(obj.toString());
					break;
					
				case 7:			//	预起
					
					StringBuffer stringBuffer = null;
					if(GeneralUtil.isNotNull(obj)){
						
						stringBuffer = new StringBuffer(obj.toString());
						stringBuffer.insert(2, ":");
					}
					
					airportPlan.setStd(stringBuffer.toString());
					break;
					
				case 8:			//	预落
					
					StringBuffer stringBuffer2 = null;
					if(GeneralUtil.isNotNull(obj)){
						
						stringBuffer2 = new StringBuffer(obj.toString());
						stringBuffer2.insert(2, ":");
					}
					
					airportPlan.setEta(stringBuffer2.toString());
					
					break;
					
				case 9:			//	降落
					
					airportPlan.setArrive(obj.toString());
					break;
					
				case 10:		//	起始日期
					
				
					Date date = null;
					if(GeneralUtil.isNotNull(obj)){
						
						date = 	DateUtil.parseShortStringToDate(obj.toString());
					}
					
					airportPlan.setStart_date(date);
					break;
					
				case 11:		//	终止日期
					
					Date date2 = null;
					if(GeneralUtil.isNotNull(obj)){
						
						date2 = 	DateUtil.parseShortStringToDate(obj.toString());
					}
					
					airportPlan.setEnd_date(date2);
					break;
					
				case 12:		//	相关部门
					
					airportPlan.setRelevant_departments(obj.toString());
					break;
					
				case 13:		//	入境点
					
					airportPlan.setIntoPoint(obj.toString());
					break;
					
				case 14:		//	入时
					
					airportPlan.setIntoTime(obj.toString());
					break;
					
				case 15:		//	进境高度
					
					airportPlan.setIntoHight(obj.toString());
					break;
					
				case 16:		// 	出境点
					
					airportPlan.setOutPoint(obj.toString());
					break;
					
				case 17:		//	出点时间
					
					airportPlan.setOutTime(obj.toString());
					break;
					
				case 18:		//	出境高度
					
					airportPlan.setOutHeight(obj.toString());
					break;
					
				case 19:		//	总调批文号
					
					airportPlan.setEndBatchNum(obj.toString());
					break;
					
				case 20:		//	备注
					
					airportPlan.setRemark(obj.toString());
					break;
					
				case 21:		//	航路
					
					
					airportPlan.setAir_route(obj.toString());
					break;
				}
				
				j++;
			}
			
			list.add(airportPlan);
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
