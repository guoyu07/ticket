package com.ticket.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.service.IAirportInfoService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.DeleteFileUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONObject;

/**
 * 机场信息控制器
 * @ClassName: AirportInfoAction   
 * @Description:  提供机场信息的相关操作方法. 
 * @author HiSay  
 * @date 2016-04-01 16:37:25
 *
 */
public class AirportInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//机场信息的业务层
	@Resource private IAirportInfoService airportInfoService = null;
	//操作日志的业务层
	@Resource private ISystemOperationLogService logService = null;
	//机场信息实体
	private AirportInfo airportInfo = null;
	//主键
	private String id = null;
    //机场名称
	private String name = null;
    //英文名
	private String englishName = null;
    //三字码
	private String threeCode = null;
    //四字码
	private String fourCode = null;
    //国家代码
	private String countryCode = null;
    //地区标识
	private String districtFlag = null;
    //字母搜索标识
	private String searchFlag = null;
	//机场信息列表
	private List<AirportInfo> airportInfolist = null;
	//国内或者国际
	private String domOrInt = null;
	//关键词
	private String keyword = null;
	//文件路径
	private String filePath = null;
	
	/**
	 * 添加机场信息
	 * @Title: AirportInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addAirportInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(englishName)) {
				data = AjaxData.responseError(getText("englishName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(threeCode)) {
				data = AjaxData.responseError(getText("threeCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(fourCode)) {
				data = AjaxData.responseError(getText("fourCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(countryCode)) {
				data = AjaxData.responseError(getText("countryCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(districtFlag)) {
				data = AjaxData.responseError(getText("districtFlag.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(searchFlag)) {
				data = AjaxData.responseError(getText("searchFlag.required"));
				return JSON;
			}
			//保存机场信息实体
			boolean isSuc = airportInfoService.persist(name, englishName, threeCode, fourCode, countryCode, districtFlag, searchFlag, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改机场信息
	 * @Title: AirportInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setAirportInfo(airportInfoService.queryById(AirportInfo.class.getSimpleName(), id));
			return "editAirportInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(englishName)) {
				data = AjaxData.responseError(getText("englishName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(threeCode)) {
				data = AjaxData.responseError(getText("threeCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(fourCode)) {
				data = AjaxData.responseError(getText("fourCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(countryCode)) {
				data = AjaxData.responseError(getText("countryCode.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(districtFlag)) {
				data = AjaxData.responseError(getText("districtFlag.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(searchFlag)) {
				data = AjaxData.responseError(getText("searchFlag.required"));
				return JSON;
			}
			//修改机场信息实体
			boolean isSuc = airportInfoService.merge(id, name, englishName, threeCode, fourCode, countryCode, districtFlag, searchFlag,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				airportInfoService.initAllCityByUpdate(getApplication());
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理机场信息实体
	 * @Title: AirportInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		keyword = DecoderUtil.UtfDecoder(keyword);
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(domOrInt)){
			if("international".equals(domOrInt)){//如果是国内城市
				if(GeneralUtil.isNotNull(keyword)){
					this.setPageModule(airportInfoService.queryIntAirportByKeyword(keyword,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
				}else{
					this.setPageModule(airportInfoService.queryIntAirport(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
				}
			}else{//国内城市（含港澳台）
				if(GeneralUtil.isNotNull(keyword)){
					this.setPageModule(airportInfoService.queryDomAirportByKeyword(keyword,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
				}else{
					this.setPageModule(airportInfoService.queryDomAirport(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
				}
			}
		}else{
			if(GeneralUtil.isNotNull(keyword)){
				this.setPageModule(airportInfoService.queryEntityByKeyword(keyword,versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			}else{
				this.setPageModule(airportInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
			}
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageAirportInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: AirportInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(airportInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleAirportInfo";
	}
	
	/**
	 * 逻辑删除机场信息对象
	 * @Title: AirportInfoAction
	 * @Description: 把机场信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = airportInfoService.logicDeleteEntity(AirportInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除机场信息对象
	 * @Title: AirportInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = airportInfoService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个机场信息对象
	 * @Title: AirportInfoAction
	 * @Description: 从回收站还原机场信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = airportInfoService.restoreEntity(AirportInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核机场信息对象
	 * @Title: AirportInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = airportInfoService.auditEntity(AirportInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: AirportInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = airportInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 从Excel导入机场信息
	 * @return
	 * @throws ServiceException
	 */
	public void importAirportInfoFromExcel() throws ServiceException{
		System.out.println(airportInfoService.importFromFile(versionFlag));
		String logContent = "导入机场信息";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
	}
	
	
	/**
	 * 设置热门机场
	 * @return
	 * @throws ServiceException
	 */
	public String setHotAirport() throws ServiceException{
		AirportInfo ap = airportInfoService.queryById(AirportInfo.class.getSimpleName(), id);
		ap.getStatus().setHot(statusValue);
		airportInfoService.merge(ap);
		String logContent = "设置热门城市";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("success");
		return JSON;
	}
	
	public String downloadTemplate() throws ServiceException{
		HSSFWorkbook wb = new HSSFWorkbook();//1 声明一个工作薄
		HSSFSheet sheet = wb.createSheet();//2生成一张工作表
		sheet.setDefaultColumnWidth(15);//3设置表格默认列宽为15
		String[] headers = {"机场名称","英文名或拼音","机场三字码","机场四字码","国家标识","地区标识","英文名或拼音首字母"};
		HSSFRow row = sheet.createRow(0);//4 生成表格标题行
		for(int i = 0;i<headers.length;i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		try {
			//fileOut = new FileOutputStream("d:\\airport.xls");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
			response.addHeader("Content-Disposition", "attachment;filename=airportInfoTemplate.xls");
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}
	
	/**
	 * 导入excel文档数据
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor=Exception.class)
	public String importFromExcel() throws ServiceException{
		boolean flag = false;
		//获取httpServletResponse 对象
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		PrintWriter writer = null;
		try {
			writer = httpServletResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		JSONObject json = new JSONObject();
		try {
			if(GeneralUtil.isNotNull(filePath)){
				String[] fileNeedPath = filePath.split("\\.");
				String tempFilePath = null;
				if(filePath.indexOf("xlsx") != -1){
					tempFilePath = fileNeedPath[0] + ".xlsx";
				} else {
					tempFilePath = fileNeedPath[0] + ".xls";
				}
				tempFilePath = request.getSession().getServletContext().getRealPath("") + tempFilePath;
				
				if(DeleteFileUtil.fileIsExists(tempFilePath)){	//当前文件存在的情况下才执行
					
					flag = airportInfoService.importFromFile(tempFilePath,versionFlag);
					//删除导入上传导入文件
					DeleteFileUtil.deleteFile(tempFilePath);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			flag = false;
		}
		if(flag){		//	表示的是成功
			json.put("success", "数据导入成功");
		}else {			//	表示的是失败
			json.put("fail", "数据导入失败");
		}
		
		writer.print(json);  
		writer.flush();
		writer.close();
		
		return "success";
	}
	
	/**
	 * 将数据导出到excel
	 * @return
	 * @throws ServiceException
	 */
	public String exportToExcel() throws ServiceException{
		HSSFWorkbook wb = new HSSFWorkbook();//1 声明一个工作薄
		HSSFSheet sheet = wb.createSheet();//2生成一张工作表
		sheet.setDefaultColumnWidth(15);//3设置表格默认列宽为15
		String[] headers = {"机场名称","英文名或拼音","机场三字码","机场四字码","国家标识","地区标识","英文名或拼音首字母"};
		HSSFRow row = sheet.createRow(0);//4 生成表格标题行
		for(int i = 0;i<headers.length;i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
		}
		List<AirportInfo> list = airportInfoService.queryByList(versionFlag);
		if(list != null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				HSSFRow hRow = sheet.createRow(i+1);
				AirportInfo ap = list.get(i);
				String[] tempString = {ap.getName(),ap.getEnglishName(),ap.getThreeCode(),ap.getFourCode(),ap.getCountryCode(),ap.getDistrictFlag(),ap.getSearchFlag()};
				for(int j = 0;j<headers.length;j++){
					HSSFCell hCell = hRow.createCell(j);
					hCell.setCellValue(tempString[j]);
				}
				
			}
			//FileOutputStream fileOut = null;
			try {
				//fileOut = new FileOutputStream("d:\\airport.xls");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
				response.addHeader("Content-Disposition", "attachment;filename=airportInfo.xls");
				wb.write(response.getOutputStream());
				//data = AjaxData.responseSuccess("download suc");
			} catch (Exception e) {
				data = AjaxData.responseError("download fail");
			}  
		}else{
			try {
				//fileOut = new FileOutputStream("d:\\airport.xls");
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
				response.addHeader("Content-Disposition", "attachment;filename=airportInfo.xls");
				wb.write(response.getOutputStream());
				//data = AjaxData.responseSuccess("download suc");
			} catch (Exception e) {
				data = AjaxData.responseError("download fail");
			}  
		}
		
		return null;
	}
	public AirportInfo getAirportInfo() {
		return airportInfo;
	}
	public void setAirportInfo(AirportInfo airportInfo) {
		this.airportInfo = airportInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getThreeCode() {
		return threeCode;
	}
	public void setThreeCode(String threeCode) {
		this.threeCode = threeCode;
	}
	public String getFourCode() {
		return fourCode;
	}
	public void setFourCode(String fourCode) {
		this.fourCode = fourCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrictFlag() {
		return districtFlag;
	}
	public void setDistrictFlag(String districtFlag) {
		this.districtFlag = districtFlag;
	}
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public List<AirportInfo> getAirportInfolist() {
		return airportInfolist;
	}

	public void setAirportInfolist(List<AirportInfo> airportInfolist) {
		this.airportInfolist = airportInfolist;
	}

	public String getDomOrInt() {
		return domOrInt;
	}

	public void setDomOrInt(String domOrInt) {
		this.domOrInt = domOrInt;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
