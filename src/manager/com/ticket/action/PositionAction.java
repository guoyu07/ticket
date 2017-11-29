package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.Position;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IPositionService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.DataAuthorityUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 员工岗位职责控制器
 * @ClassName: PositionAction   
 * @Description:  提供员工岗位职责的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-09 09:51:13
 *
 */
public class PositionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工岗位职责的业务层
	@Resource private IPositionService positionService = null;
	
	@Resource private ISystemOperationLogService logService = null;
	
	@Resource private ISystemModuleService systemModuleService = null;
	
	@Resource private ITreeService<Position, String> treeService = null;
	
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	//员工岗位职责实体
	private Position position = null;
	//主键
	private String id = null;
    //岗位民称
	private String name = null;
    //岗位职责
	private String duty = null;
    //岗位描述
	private String remark = null;
	//模块
	private String moduleHtml = null;
	/**
	 * 加载单层数
	 * @return
	 * @throws ServiceException 
	 */
	public String loadTree() throws ServiceException{
		String parentId = (String)ServletActionContext.getRequest().getParameter("id");
		List<SystemModule> dictionarys = null;
		
		//加载根节点
		if(GeneralUtil.isNull(parentId)){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(SystemModule.class);
			json.put("total", count);
			
			dictionarys = treeService.queryRootNode(SystemModule.class);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemModule dict = (SystemModule)o;
				Long subCount = treeService.countSubByParent(SystemModule.class, dict.getId());
				List<DataAuthoritys> modules = dataAuthoritysService.queryBuSystemModulId(dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				if(GeneralUtil.isNotNull(modules)){
					for(DataAuthoritys info:modules){
						jsonSingle.put("privilege", info.getName());
					}
				}
				jsonSingle.put("_parentId", "");
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
			
		}else{ //加载非根节点
			
			dictionarys = treeService.querySubByParent(SystemModule.class, parentId);
			JSONArray jsonArray = new JSONArray();
			for(Object o : dictionarys){
				
				SystemModule dict = (SystemModule)o;
				List<DataAuthoritys> modules = dataAuthoritysService.queryBuSystemModulId(dict.getId());
				Long subCount = treeService.countSubByParent(SystemModule.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				if(GeneralUtil.isNotNull(modules)){
					for(DataAuthoritys info:modules){
						jsonSingle.put("privilege", info.getName());
					}
				}
				jsonSingle.put("_parentId", dict.getParent_id());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	
	/**
	 * 给角色赋权
	 * @return
	 * @throws ServiceException
	 */
	public String grantPrivilege() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setModuleHtml(DataAuthorityUtil.recursion(systemModuleService.queryFirstModuleList(versionFlag),id));
		return "grantPrivilege";
	}
	/**
	 * 添加员工岗位职责
	 * @Title: PositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addPosition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("职位名称不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(duty)) {
				data = AjaxData.responseError("岗位职责不能为空");
				return JSON;
			}
			//保存员工岗位职责实体
			boolean isSuc = positionService.persist(name, duty, remark, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增员工岗位";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("添加成功");
			} else {
				data = AjaxData.responseError("添加失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 修改员工岗位职责
	 * @Title: PositionAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setPosition(positionService.queryById(Position.class.getSimpleName(), id));
			return "editPosition";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError("id不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("岗位名称不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(duty)) {
				data = AjaxData.responseError("岗位职责不能为空");
				return JSON;
			}
			//修改员工岗位职责实体
			boolean isSuc = positionService.merge(id, name, duty, remark,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改员工岗位";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("编辑成功");
			} else {
				data = AjaxData.responseError("编辑失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 管理员工岗位职责实体
	 * @Title: PositionAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(positionService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "managePosition";
	}
	
	/**
	 * 查看回收站
	 * @Title: PositionAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(positionService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recyclePosition";
	}
	
	/**
	 * 逻辑删除员工岗位职责对象
	 * @Title: PositionAction
	 * @Description: 把员工岗位职责对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = positionService.logicDeleteEntity(Position.class.getSimpleName(), id);
		if(isSuc) { 
			String logContent = "逻辑删除员工岗位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("删除成功");
		} else {
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 物理删除员工岗位职责对象
	 * @Title: PositionAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = positionService.remove(id);
		if(isSuc) {
			String logContent = "物理删除员工岗位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("删除成功");
		} else {
			data = AjaxData.responseError("删除失败");
		}
		return JSON;
	}
	
	/**
	 * 还原一个员工岗位职责对象
	 * @Title: PositionAction
	 * @Description: 从回收站还原员工岗位职责对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = positionService.restoreEntity(Position.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原员工岗位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("还原成功");
		} else {
			data = AjaxData.responseError("还原失败");
		}
		return JSON;
	}
	
	/**
	 * 审核员工岗位职责对象
	 * @Title: PositionAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = positionService.auditEntity(Position.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核员工岗位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("审核成功");
		} else {
			data = AjaxData.responseError("审核失败");
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: PositionAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = positionService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作员工岗位";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("操作成功");
		} else {
			data = AjaxData.responseError("操作失败");
		}
		return JSON;
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getModuleHtml() {
		return moduleHtml;
	}
	public void setModuleHtml(String moduleHtml) {
		this.moduleHtml = moduleHtml;
	}
	
}
