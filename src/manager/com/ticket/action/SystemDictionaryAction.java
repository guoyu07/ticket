package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统字典表控制器
 * @ClassName: SystemDictionaryAction   
 * @Description:  提供系统字典表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-24 15:27:39
 *
 */
public class SystemDictionaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统字典表的业务层
	@Resource(name="systemDictionaryService") 
	private ISystemDictionaryService systemDictionaryService = null;
	@Resource private ISystemOperationLogService logService = null;
	//树结构服务
	@Resource(name="treeService") 
	private ITreeService<SystemDictionary, String> treeService;
	//系统字典表实体
	private SystemDictionary systemDictionary = null;
	//主键
	private String id = null;
    //字典名称
	private String name = null;
	//排序值
	private Integer orderValue = null;
	//说明
	private String description = null;
    //字典值
	private String value = null;
    //父字典ID
	private String parent_id = null;
	//拼音
	private String pingYin = null;
	//状态值
	private Integer statusValue = null;
	//关键词
	private String keyword = null;
	/**
	 * 初始化城市的拼音
	 * @return
	 * @throws Exception
	 */
	public String initialPinyin() throws Exception {
		data = systemDictionaryService.initialCityCodeHasPinYin()+"";
		return TEXT;
	}
	
	/**
	 * @Description：遍历所有字典
	 * @return
	 */
	public String traverse() {
		try {
			if(GeneralUtil.isNull(name)){
				
				JSONArray json = treeService.traverse(SystemDictionary.class, "name", "value");
				data = json.toString();
				return TEXT;
			}else{
				
				SystemDictionary parent = systemDictionaryService.getByName(name);
				JSONArray json = treeService.traverseUnderParent(SystemDictionary.class, parent.getId(), "name", "value");
				data = json.toString();
				return TEXT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * @Description：加载树的单层数据,根据父ID
	 * @return
	 */
	public String loadTree(){
		String parentId = (String)ServletActionContext.getRequest().getParameter("id");
		List<SystemDictionary> dictionarys = null;
		
		//加载根节点
		if(GeneralUtil.isNull(parentId)){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(SystemDictionary.class);
			json.put("total", count);
			
			dictionarys = treeService.queryRootNode(SystemDictionary.class);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemDictionary dict = (SystemDictionary)o;
				Long subCount = treeService.countSubByParent(SystemDictionary.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", "");
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("value", dict.getValue());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
			
		}else{ //加载非根节点
			
			dictionarys = treeService.querySubByParent(SystemDictionary.class, parentId);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemDictionary dict = (SystemDictionary)o;
				Long subCount = treeService.countSubByParent(SystemDictionary.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", dict.getParent().getId());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("value", dict.getValue());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	
	/**
	 * @Description：查询兄弟节点
	 * @return
	 */
	public String querySibling(){
		
		SystemDictionary parent = treeService.getParentBySub(SystemDictionary.class, id);
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, parent.getId());
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentName(){
		
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, "name", name);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentValue(){
		
		List<SystemDictionary> dicts = treeService.querySubByParent(SystemDictionary.class, "value", value);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：通过字典名称，获得字典值
	 * @return 字典值
	 */
	public String getValueByName(){
		
		String value = systemDictionaryService.getValueByName(name);
		data = value;
		return TEXT;
	}
	
	/**
	 * @throws ServiceException 
	 * 添加系统字典表
	 * @Title: SystemDictionaryAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		
		if(GeneralUtil.isNull(operationFlag)) {
			
			return "addSystemDictionary";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(value)) {
				data = getText("value.required");
				return TEXT;
			}
			if("".equals(parent_id.trim())){
				
				parent_id = null;
			}
			//保存系统字典表实体
			try {
				systemDictionaryService.persist(name, value, orderValue, description, parent_id);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "新增系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			//根据保存结果返回页面
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改系统字典表
	 * @Title: SystemDictionaryAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			systemDictionary = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), id);
			return "editSystemDictionary";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(value)) {
				data = getText("value.required");
				return TEXT;
			}
			if(id.equals(parent_id)){
				data = getText("dontSelectYourself");
				return TEXT;
			}
			if(GeneralUtil.isNull(parent_id)){
				parent_id = null;
			}
			//修改系统字典表实体
			boolean isSuc = systemDictionaryService.merge(id, name, value, orderValue, description, parent_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改系统字典";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * @Description：根据id得到一个字典对象
	 * @return
	 */
	public String loadSystemDictionary()throws Exception{
		
		data = net.sf.json.JSONObject.fromObject(systemDictionaryService.queryById("SystemDictionary", id)).toString();
		return TEXT;
	}
	
	/**
	 * 管理系统字典表实体
	 * @Title: SystemDictionaryAction
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		try {
//			List<SystemDictionary> list = treeService.queryRootNode(SystemDictionary.class);
//			String html = PageCodeUtil.recursion(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		systemDictionaryService.initialAllCityByCityPoi(null);
		return "manageSystemDictionary";
	}
	
	/**
	 * 搜索国内城市
	 * @return
	 * @throws ServiceException
	 */
	public String searchDom() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemDictionaryService.queryPageModuleByKeywordAndDom(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageDomCity";
	}
	
	/**
	 * 搜索国际城市
	 * @return
	 * @throws ServiceException
	 */
	public String searchInt() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemDictionaryService.queryPageModuleByKeywordAndInt(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageIntCity";
	}
	
	/**
	 * 管理国内城市
	 * @return
	 * @throws ServiceException
	 */
	public String manageDomCity() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemDictionaryService.queryPageModuleByParentDom(ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageDomCity";
	}
	
	/**
	 * 管理国际城市
	 * @return
	 * @throws ServiceException
	 */
	public String manageIntCity() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(systemDictionaryService.queryPageModuleByParentInt(ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageIntCity";
	}
	
	/**
	 * 设置国内热门城市
	 * @return
	 * @throws ServiceException
	 */
	public String setDomHot() throws ServiceException{
		SystemDictionary systemDictionary = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), id);
		systemDictionary.getStatus().setHot(statusValue);
		systemDictionaryService.merge(systemDictionary);
		String logContent = "设置国内热门城市";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("success");
		return JSON;
	}
	
	/**
	 * 设置国际热门城市
	 * @return
	 * @throws ServiceException
	 */
	public String setIntHot() throws ServiceException{
		SystemDictionary systemDictionary = systemDictionaryService.queryById(SystemDictionary.class.getSimpleName(), id);
		systemDictionary.getStatus().setHot(statusValue);
		systemDictionaryService.merge(systemDictionary);
		String logContent = "设置国际热门城市";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess("success");
		return JSON;
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemDictionaryAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemDictionaryService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemDictionary";
	}
	
	/**
	 * 逻辑删除系统字典表对象
	 * @Title: SystemDictionaryAction
	 * @Description: 把系统字典表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemDictionaryService.logicDeleteEntity(SystemDictionary.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除系统字典表对象
	 * @Title: SystemDictionaryAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		
		//先检查是否有子节点
		Long count = treeService.countSubByParent(SystemDictionary.class, id);
		if(count > 0){
			data = AjaxData.responseError(getText("hasChild"));
			return JSON;
		}
		
		boolean isSuc = systemDictionaryService.remove(id);
		if(isSuc) {
			String logContent = "物理删除系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个系统字典表对象
	 * @Title: SystemDictionaryAction
	 * @Description: 从回收站还原系统字典表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemDictionaryService.restoreEntity(SystemDictionary.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核系统字典表对象
	 * @Title: SystemDictionaryAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemDictionaryService.auditEntity(SystemDictionary.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SystemDictionaryAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemDictionaryService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作系统字典";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 导入城市三字码
	 * @throws ServiceException
	 */
	public void importFromExcel() throws ServiceException{
		System.out.println(systemDictionaryService.importFromFile(versionFlag));
		String logContent = "导入城市三字码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
	}
	
	/**
	 * 导入国内城市三字码
	 * @throws ServiceException
	 */
	public void importDomesticCityCode() throws ServiceException{
		
		String logContent = "导入国内城市三字码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		systemDictionaryService.importDomesticCityCode(versionFlag);
	}
	/**
	 * 导入国际城市三字码
	 * @throws ServiceException
	 */
	public void importInternationalCityCode() throws ServiceException{
		
		String logContent = "导入国际城市三字码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		systemDictionaryService.importInternationalCityCode(versionFlag);
	}
	/**
	 * 导入航空公司二字码
	 * @throws ServiceException
	 */
	public void importFlightCompanyTwoCode() throws ServiceException{
		
		String logContent = "导入航空公司二字码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		System.out.println(systemDictionaryService.importFlightCompanyTwoCode(versionFlag));
	}
	
	/**
	 * 机场三字码、四字码导入
	 * @throws ServiceException
	 */
	public void importAirportCode() throws ServiceException{
		String logContent = "导入机场三字码,四字码";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		System.out.println(systemDictionaryService.importAirportCode(versionFlag));
	}

	/**
	 * 导入国际列表
	 * @throws ServiceException
	 */
	public void importNationality() throws ServiceException{
		String logContent = "导入国际列表";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		System.out.println(systemDictionaryService.importNationality(versionFlag));
	}
	
	public SystemDictionary getSystemDictionary() {
		return systemDictionary;
	}
	public void setSystemDictionary(SystemDictionary systemDictionary) {
		this.systemDictionary = systemDictionary;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public ITreeService<SystemDictionary, String> getTreeService() {
		return treeService;
	}
	public void setTreeService(ITreeService<SystemDictionary, String> treeService) {
		this.treeService = treeService;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public ISystemDictionaryService getSystemDictionaryService() {
		return systemDictionaryService;
	}

	public void setSystemDictionaryService(
			ISystemDictionaryService systemDictionaryService) {
		this.systemDictionaryService = systemDictionaryService;
	}

	public String getPingYin() {
		return pingYin;
	}

	public void setPingYin(String pingYin) {
		this.pingYin = pingYin;
	}

	public Integer getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(Integer statusValue) {
		this.statusValue = statusValue;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
