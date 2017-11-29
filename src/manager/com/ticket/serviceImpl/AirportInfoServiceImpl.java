package com.ticket.serviceImpl;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.FlightSite;
import com.ticket.pojo.Pagination;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.IFlightSiteService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;

/**
 * 机场信息业务接口实现类
 * @ClassName: IAirportInfoService   
 * @Description: 提供机场信息操作的增删改查   
 * @author HiSay  
 * @date 2016-04-01 16:37:25
 *
 */
public class AirportInfoServiceImpl extends BaseServiceImpl<AirportInfo, String> implements IAirportInfoService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(AirportInfoServiceImpl.class);
	@Resource
	private IFlightSiteService flightSiteService = null;
	@Resource
	private IAirportInfoService airportInfoService = null;
	@Override
	public boolean merge(String id, String name,String englishName,String threeCode,String fourCode,String countryCode,String districtFlag,String searchFlag, String versionFlag) throws ServiceException {
		AirportInfo airportInfo = dbDAO.queryById(this.getTableNameFromEntity(AirportInfo.class), id);
		airportInfo.setName(DecoderUtil.UtfDecoder(name));
		airportInfo.setEnglishName(DecoderUtil.UtfDecoder(englishName));
		airportInfo.setThreeCode(DecoderUtil.UtfDecoder(threeCode));
		airportInfo.setFourCode(DecoderUtil.UtfDecoder(fourCode));
		airportInfo.setCountryCode(DecoderUtil.UtfDecoder(countryCode));
		airportInfo.setDistrictFlag(DecoderUtil.UtfDecoder(districtFlag));
		airportInfo.setSearchFlag(DecoderUtil.UtfDecoder(searchFlag));
		CommonEntity status = airportInfo.getStatus();
		status.setVersionFlag(versionFlag);
		airportInfo.setStatus(status);
		dbDAO.merge(airportInfo);
		return true;
	}

	@Override
	public boolean persist(String name,String englishName,String threeCode,String fourCode,String countryCode,String districtFlag,String searchFlag, String versionFlag) throws ServiceException {
		AirportInfo airportInfo = new AirportInfo();
		airportInfo.setName(DecoderUtil.UtfDecoder(name));
		airportInfo.setEnglishName(DecoderUtil.UtfDecoder(englishName));
		airportInfo.setThreeCode(DecoderUtil.UtfDecoder(threeCode));
		airportInfo.setFourCode(DecoderUtil.UtfDecoder(fourCode));
		airportInfo.setCountryCode(DecoderUtil.UtfDecoder(countryCode));
		airportInfo.setDistrictFlag(DecoderUtil.UtfDecoder(districtFlag));
		airportInfo.setSearchFlag(DecoderUtil.UtfDecoder(searchFlag));
		CommonEntity status = airportInfo.getStatus();
		status.setVersionFlag(versionFlag);
		airportInfo.setStatus(status);
		dbDAO.persist(airportInfo);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		AirportInfo airportInfo = dbDAO.queryById(this.getTableNameFromEntity(AirportInfo.class), id);
		dbDAO.remove(airportInfo);
		return true;
	}
	
	@Override
	public int importFromFile(String versionFlag) throws ServiceException {
		
		/*try {
			ReadExcelAirportUtils airportUtils = new ReadExcelAirportUtils("D:\\airportinfo.xls");
			List<AirportInfo> list = airportUtils.readExcelContent();
			for(AirportInfo ap : list){
				this.persist(ap);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		try {
			// 1 创建excel导入路径
			String path = "D:\\airportinfo.xls";
			// 2 创建文件输入流
			FileInputStream fileIn = new FileInputStream(path);
			// 3 根据文件输入流产生wookbook对象 
			HSSFWorkbook wb = new HSSFWorkbook(fileIn);
			// 4 获取Excel中的第一个表单
			Sheet sheetOne = wb.getSheetAt(0);
			// 5 对表单中的每一行进行迭代
			for(Row r:sheetOne){
				//6 如果当前的行号从0开始未达到2（第三行） 则重新循环
				if(r.getRowNum()<1){
					continue;
				}
				//7 创建实体类
				String threeCode = getCellFormula(r.getCell(0)).toString();
				AirportInfo apInfo = airportInfoService.queryByThreeCode(threeCode, versionFlag);
				if(apInfo != null){
					continue;
				}
				AirportInfo ap = new AirportInfo();
				//8 取出当前单元格数据，并封装在实体属性上
				
				ap.setThreeCode(threeCode);
				
				FlightSite fs = flightSiteService.queryEntityByThreeCode(threeCode, versionFlag);
				if(fs != null){
					ap.setFourCode(fs.getOrg());
				}
				String  englishName = getCellFormula(r.getCell(1)).toString();
				ap.setEnglishName(englishName);
				ap.setCountryCode(getCellFormula(r.getCell(2)).toString());
				ap.setDistrictFlag(getCellFormula(r.getCell(3)).toString());
				ap.setName(getCellFormula(r.getCell(4)).toString());
				if(GeneralUtil.isNotNull(englishName)){
					ap.setSearchFlag(englishName.substring(0,1));
				}
				this.persist(ap);
			}
			fileIn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<AirportInfo> queryDomAirportByAlpha(String alpha,String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.districtFlag =?3 or s.districtFlag =?4) and s.searchFlag=?5", new Object[]{"D","R",alpha}, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryIntAirportByAlpha(String alpha,String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.districtFlag =?3 and s.searchFlag=?4", new Object[]{"I",alpha}, orderBy, null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialAllCityByCityPoi(ServletContext applicattion) throws ServiceException {
		//domesticCityThreeCode
		try {
			List<String> alphaList = new ArrayList<String>();
			alphaList.add("a");
			alphaList.add("b");
			alphaList.add("c");
			alphaList.add("d");
			alphaList.add("e");
			alphaList.add("f");
			alphaList.add("g");
			alphaList.add("h");
			alphaList.add("i");
			alphaList.add("j");
			alphaList.add("k");
			alphaList.add("l");
			alphaList.add("m");
			alphaList.add("n");
			alphaList.add("o");
			alphaList.add("p");
			alphaList.add("q");
			alphaList.add("r");
			alphaList.add("s");
			alphaList.add("t");
			alphaList.add("u");
			alphaList.add("v");
			alphaList.add("w");
			alphaList.add("x");
			alphaList.add("y");
			alphaList.add("z");
			for(String alpha : alphaList) {
				List<AirportInfo> tempList = this.queryDomAirportByAlpha(alpha,versionFlag);
				List<AirportInfo> tempList2 = this.queryIntAirportByAlpha(alpha,versionFlag);
				if(tempList != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_domesticAirportList", tempList);
					} else {
						applicattion.setAttribute(alpha + "_domesticAirportList", tempList);
					}
				}
				if(tempList2 != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_internationalAirportList", tempList2);
					} else {
						applicattion.setAttribute(alpha + "_internationalAirportList", tempList2);
					}
				}
			}
		} catch(Exception e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initAllCityByUpdate(Map applicattion) throws ServiceException {
		//domesticCityThreeCode
		try {
			List<String> alphaList = new ArrayList<String>();
			alphaList.add("a");
			alphaList.add("b");
			alphaList.add("c");
			alphaList.add("d");
			alphaList.add("e");
			alphaList.add("f");
			alphaList.add("g");
			alphaList.add("h");
			alphaList.add("i");
			alphaList.add("j");
			alphaList.add("k");
			alphaList.add("l");
			alphaList.add("m");
			alphaList.add("n");
			alphaList.add("o");
			alphaList.add("p");
			alphaList.add("q");
			alphaList.add("r");
			alphaList.add("s");
			alphaList.add("t");
			alphaList.add("u");
			alphaList.add("v");
			alphaList.add("w");
			alphaList.add("x");
			alphaList.add("y");
			alphaList.add("z");
			for(String alpha : alphaList) {
				List<AirportInfo> tempList = this.queryDomAirportByAlpha(alpha,versionFlag);
				List<AirportInfo> tempList2 = this.queryIntAirportByAlpha(alpha,versionFlag);
				if(tempList != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_domesticAirportList", tempList);
					} else {
						applicattion.put(alpha + "_domesticAirportList", tempList);
					}
				}
				if(tempList2 != null) {
					if(getApplication() != null) {
						getApplication().put(alpha + "_internationalAirportList", tempList2);
					} else {
						applicattion.put(alpha + "_internationalAirportList", tempList2);
					}
				}
			}
		} catch(Exception e) {
			
		}
	}

	@Override
	public String getFourCodeByThreeCode(String threeCode, String versionFlag)
			throws ServiceException {
		AirportInfo ap = dbDAO.queryByCustom(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.threeCode =?3", new Object[]{threeCode});
		if(ap != null){
			return ap.getFourCode();
		}
		return null;
	}

	@Override
	public AirportInfo queryByFourCode(String fourCode, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByCustom(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.fourCode =?3", new Object[]{fourCode});
	}

	@Override
	public AirportInfo queryByThreeCode(String threeCode, String versionFlag)
			throws ServiceException {
		
		AirportInfo info = dbDAO.queryByCustom(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.threeCode =?3", new Object[]{threeCode});
		return info;
	}
	
	private Object getCellFormula(Cell cell){
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
				FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
				//evaluator.evaluateFormulaCell(cell);
				cellvalue = evaluator.evaluate(cell);
				//读取公式     
				//cellvalue = cell.getCellFormula();
				break;
			default:  
				cellvalue = "";  
			}
		}
		
		return cellvalue;
	}

	@Override
	public List<AirportInfo> queryDomHotAirport(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.districtFlag ='D' or s.districtFlag ='R') and s.status.hot =1", null, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryIntHotAirport(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.districtFlag ='I'  and s.status.hot =1", null, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryDomListByKeyword(String keyword,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.districtFlag ='D' or s.districtFlag ='R') and (s.name like ?3 or s.englishName like ?4 or s.threeCode =?5 or s.fourCode =?6)", new Object[]{keyword+"%",keyword+"%",keyword,keyword}, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryIntListByKeyword(String keyword,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.districtFlag ='I' and (s.name like ?3 or s.englishName like ?4 or s.threeCode =?5 or s.fourCode =?6)", new Object[]{"%"+keyword+"%",keyword+"%",keyword,keyword}, orderBy, null);
	}

	@Override
	public Pagination queryDomAirport(String versionFlag,
			int pageSize) throws ServiceException {
		// TODO Auto-generated method stub
		return dbDAO.queryByPageModule(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.districtFlag ='D' or s.districtFlag ='R')", null, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryIntAirport(String versionFlag,
			int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.districtFlag ='I'", null, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryDomAirportByKeyword(String keyword,
			String versionFlag, int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.districtFlag='D' or s.districtFlag='R') and (s.name like ?3 or s.threeCode =?4 or s.fourCode=?5 or s.englishName like ?6)", new Object[]{keyword+"%",keyword,keyword,keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryEntityByKeyword(String keyword, String versionFlag,
			int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and (s.name like ?3 or s.threeCode =?4 or s.fourCode=?5 or s.englishName like ?6)", new Object[]{keyword+"%",keyword,keyword,keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public Pagination queryIntAirportByKeyword(String keyword,
			String versionFlag, int pageSize) throws ServiceException {
		return dbDAO.queryByPageModule(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.districtFlag ='I' and (s.name like ?3 or s.threeCode =?4 or s.fourCode=?5 or s.englishName like ?6)", new Object[]{keyword+"%",keyword,keyword,keyword+"%"}, orderBy, PaginationContext.getOffset(), pageSize);
	}

	@Override
	public List<AirportInfo> queryByCity(String city, String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.name like ?3", new Object[]{city+"%"}, orderBy, null);
	}

	@Override
	public boolean importFromFile(String tempFilePath, String versionFlag)
			throws ServiceException {
		try {
			// 1 创建excel导入路径
			//String path = "D:\\airportinfo.xls";
			// 2 创建文件输入流
			FileInputStream fileIn = new FileInputStream(tempFilePath);
			// 3 根据文件输入流产生wookbook对象 
			HSSFWorkbook wb = new HSSFWorkbook(fileIn);
			// 4 获取Excel中的第一个表单
			Sheet sheetOne = wb.getSheetAt(0);
			// 5 对表单中的每一行进行迭代
			for(Row r:sheetOne){
				//6 如果当前的行号从0开始未达到2（第三行） 则重新循环
				if(r.getRowNum()<1){
					continue;
				}
				//7 创建实体类
				AirportInfo ap = new AirportInfo();
				//8 取出当前单元格数据，并封装在实体属性上
				ap.setName(getCellFormula(r.getCell(0)).toString());
				ap.setEnglishName(getCellFormula(r.getCell(1)).toString());
				ap.setThreeCode(getCellFormula(r.getCell(2)).toString());
				ap.setFourCode(getCellFormula(r.getCell(3)).toString());
				ap.setCountryCode(getCellFormula(r.getCell(4)).toString());
				ap.setDistrictFlag(getCellFormula(r.getCell(5)).toString());
				ap.setSearchFlag(getCellFormula(r.getCell(6)).toString());
				this.persist(ap);
			}
			fileIn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<AirportInfo> queryByList(String versionFlag)
			throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryListByKeyword(String keyword,
			String versionFlag) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, " and s.name like ?3 or s.englishName like ?4 or s.threeCode =?5 or s.fourCode =?6", new Object[]{"%"+keyword+"%","%"+keyword+"%",keyword,keyword}, orderBy, null);
	}

	@Override
	public List<AirportInfo> queryByName(String name) throws ServiceException {
		return dbDAO.queryByList(AirportInfo.class.getSimpleName(), deleteFlag, versionFlag, "and s.name like ?3", new Object[]{"%"+name+"%"}, orderBy, null);
	}
}