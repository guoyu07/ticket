package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.FrontMenu;
import com.ticket.service.IFrontMenuService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 前端菜单表控制器
 * @ClassName: FrontMenuAction   
 * @Description:  提供前端菜单表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-24 15:27:39
 */
public class FrontMenuAction extends BaseAction {

	protected static final long serialVersionUID = 1L;
	
	//前端菜单表的业务层
	@Resource(name="frontMenuService") 
	protected IFrontMenuService frontMenuService = null;
	//树结构服务
	@Resource(name="treeService") 
	protected ITreeService<FrontMenu, String> treeService;
	
	@Resource private ISystemOperationLogService logService = null;
	//前端菜单表实体
	protected FrontMenu frontMenu = null;
	//主键
	protected String id = null;
    //菜单名称
	protected String name = null;
	//排序值
	protected Integer orderValue = null;
	//说明
	protected String description = null;
    //菜单值
	protected String value = null;
    //父菜单ID
	protected String parent_id = null;
	//是否加载子菜单
	protected boolean loadSub;
	
	/**
	 * @Description：遍历所有菜单
	 * @return
	 */
	public String traverse() {
		try {
			if(GeneralUtil.isNull(name)){
				
				JSONArray json = treeService.traverse(FrontMenu.class, "name", "value");
				data = json.toString();
				return TEXT;
			}else{
				
				FrontMenu parent = frontMenuService.getByName(name);
				JSONArray json = treeService.traverseUnderParent(FrontMenu.class, parent.getId(), "name", "value");
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
		List<FrontMenu> dictionarys = null;
		
		//加载根节点
		if(GeneralUtil.isNull(parentId)){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(FrontMenu.class);
			json.put("total", count);
			
			dictionarys = treeService.queryRootNode(FrontMenu.class);
			JSONArray jsonArray = new JSONArray();
			for(FrontMenu dict : dictionarys){
				
				Long subCount = treeService.countSubByParent(FrontMenu.class, dict.getId());
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
			
			dictionarys = treeService.querySubByParent(FrontMenu.class, parentId);
			JSONArray jsonArray = new JSONArray();
			for(FrontMenu dict : dictionarys){
				
				Long subCount = treeService.countSubByParent(FrontMenu.class, dict.getId());
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
		
		FrontMenu parent = treeService.getParentBySub(FrontMenu.class, id);
		List<FrontMenu> dicts = treeService.querySubByParent(FrontMenu.class, parent.getId());
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父菜单的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentName(){
		
		List<FrontMenu> dicts = treeService.querySubByParent(FrontMenu.class, "name", name);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父菜单的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentValue(){
		
		List<FrontMenu> dicts = treeService.querySubByParent(FrontMenu.class, "value", value);
		data = net.sf.json.JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：通过菜单名称，获得菜单值
	 * @return 菜单值
	 */
	public String getValueByName(){
		
		String value = frontMenuService.getValueByName(name);
		data = value;
		return TEXT;
	}
	
	/**
	 * @throws ServiceException 
	 * 添加前端菜单表
	 * @Title: FrontMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		
		if(GeneralUtil.isNull(operationFlag)) {
			
			return "addFrontMenu";
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
			//保存前端菜单表实体
			try {
				frontMenuService.persist(name, value, loadSub, orderValue, description, parent_id);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "新增前端菜单表";
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
	 * 修改前端菜单表
	 * @Title: FrontMenuAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			frontMenu = frontMenuService.get(FrontMenu.class, id);
			return "editFrontMenu";
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
			//修改前端菜单表实体
			boolean isSuc = frontMenuService.merge(id, name, value, loadSub, orderValue, description, parent_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改前端菜单表";
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
	 * @Description：根据id得到一个菜单对象
	 * @return
	 */
	public String loadFrontMenu()throws Exception{
		
		data = net.sf.json.JSONObject.fromObject(frontMenuService.queryById("FrontMenu", id)).toString();
		return TEXT;
	}
	
	/**
	 * 管理前端菜单表实体
	 * @Title: FrontMenuAction
	 * @Description:    
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		try {
//			List<FrontMenu> list = treeService.queryRootNode(FrontMenu.class);
//			String html = PageCodeUtil.recursion(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "manageFrontMenu";
	}
	
	/**
	 * 查看回收站
	 * @Title: FrontMenuAction
	 * @Description:    
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(frontMenuService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFrontMenu";
	}
	
	/**
	 * 逻辑删除前端菜单表对象
	 * @Title: FrontMenuAction
	 * @Description: 把前端菜单表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = frontMenuService.logicDeleteEntity(FrontMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除前端菜单表";
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
	 * 物理删除前端菜单表对象
	 * @Title: FrontMenuAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		
		//先检查是否有子节点
		Long count = treeService.countSubByParent(FrontMenu.class, id);
		if(count > 0){
			data = AjaxData.responseError(getText("hasChild"));
			return JSON;
		}
		
		boolean isSuc = frontMenuService.remove(id);
		if(isSuc) {
			String logContent = "物理删除前端菜单表";
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
	 * 还原一个前端菜单表对象
	 * @Title: FrontMenuAction
	 * @Description: 从回收站还原前端菜单表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = frontMenuService.restoreEntity(FrontMenu.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原前端菜单表";
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
	 * 审核前端菜单表对象
	 * @Title: FrontMenuAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = frontMenuService.auditEntity(FrontMenu.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核前端菜单表";
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
	 * @Title: FrontMenuAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = frontMenuService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作前端菜单表";
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

	public IFrontMenuService getFrontMenuService() {
		return frontMenuService;
	}

	public void setFrontMenuService(IFrontMenuService frontMenuService) {
		this.frontMenuService = frontMenuService;
	}

	public ITreeService<FrontMenu, String> getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService<FrontMenu, String> treeService) {
		this.treeService = treeService;
	}

	public FrontMenu getFrontMenu() {
		return frontMenu;
	}

	public void setFrontMenu(FrontMenu frontMenu) {
		this.frontMenu = frontMenu;
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

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isLoadSub() {
		return loadSub;
	}

	public void setLoadSub(boolean loadSub) {
		this.loadSub = loadSub;
	}
}
