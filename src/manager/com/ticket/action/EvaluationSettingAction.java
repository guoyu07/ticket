package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationSetting;
import com.ticket.service.IEvaluationSettingService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;

/**
 * 评价设置控制器
 * @ClassName: EstimateSetManageAction   
 * @Description:  提供评价设置的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-26 10:56:19
 */
public class EvaluationSettingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//评价设置的业务层
	@Resource 
	private IEvaluationSettingService evaluationSettingService;
	@Resource(name="treeService") 
	private ITreeService<EvaluationSetting, String> treeService;
	@Resource private ISystemOperationLogService logService = null;
	//评价设置实体
	private EvaluationSetting estimateSetManage;
	//主键
	private String id;
    //评价类别
	private String classes;
    //评价项目
	private String project;
    //评价指标
	private String target;
	//排序值
	protected Integer orderValue;
    //是否启用(具体到指标)
	private Integer enabled;
    //父ID
	private String parent_id;
	//名称
	private String name;
	/**
	 * 加载所有评价设置
	 * @return
	 */
	public String queryRoot(){
		
		List<EvaluationSetting> list = treeService.queryRootNode(EvaluationSetting.class);
		data = net.sf.json.JSONArray.fromObject(list).toString();
		return TEXT;
	}
	/**
	 * 根据父节点查找子节点
	 * @return
	 */
	public String querySubByParentId(){
		
		List<EvaluationSetting> list = evaluationSettingService.findByParentId(id);
		data = AjaxData.responseSuccess(list);
		return JSON;
	}
	
	/**
	 * 判断该节点是否还能创建子节点
	 * @return
	 * @throws ServiceException
	 */
	public String isEnd() throws ServiceException{
		EvaluationSetting estimateSetManage = evaluationSettingService.queryById(EvaluationSetting.class.getName(), id);
		EvaluationSetting estimateSetManage2 = estimateSetManage.getParent();
		if(estimateSetManage2 != null){
			EvaluationSetting estimateSetManage3 = evaluationSettingService.queryById(EvaluationSetting.class.getName(), estimateSetManage2.getParent().getId());
			if(estimateSetManage3 != null){
				data = AjaxData.responseSuccess("");
			}else{
				data = AjaxData.responseError("");
			}
		}else{
			data = AjaxData.responseError("");
		}
		return JSON;
	}
	/**
	 * @Description：遍历所有字典
	 * @return
	 */
	public String traverse() {
		try {
			if(GeneralUtil.isNull(name)){
				
				JSONArray json = treeService.traverse(EvaluationSetting.class, "name");
				data = json.toString();
				return TEXT;
			}else{
				
				EvaluationSetting parent = evaluationSettingService.getByName(name);
				JSONArray json = treeService.traverseUnderParent(EvaluationSetting.class, parent.getId(), "name", "enabled");
				data = json.toString();
				return TEXT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 加载树
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadTree(){
		
		List<EvaluationSetting>	estimateSetManages = null;
		
		//加载根节点
		if(GeneralUtil.isNull(id)){
			
			JSONObject json = new JSONObject();
			Long count = treeService.count(EvaluationSetting.class);
			json.put("total", count);
			
			estimateSetManages = treeService.queryRootNode(EvaluationSetting.class);
			JSONArray jsonArray = new JSONArray();
			for(Object o : estimateSetManages){
				EvaluationSetting dict = (EvaluationSetting)o;
				Long subCount = treeService.countSubByParent(EvaluationSetting.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parent_id",  "");
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				String ifEnabled = "";
				if(dict.getEnabled() == 1){
					ifEnabled = "已禁用";
				}else{
					ifEnabled = "未禁用";
				}
				jsonSingle.put("enabled", ifEnabled);
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
			
		}else{ //加载非根节点
			
			estimateSetManages = treeService.querySubByParent(EvaluationSetting.class, id);
			JSONArray jsonArray = new JSONArray();
			for(Object o : estimateSetManages){
				EvaluationSetting dict = (EvaluationSetting)o;
				Long subCount = treeService.countSubByParent(EvaluationSetting.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parent_id",  dict.getParent().getId());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				String ifEnabled = "";
				if(dict.getEnabled() == 1){
					ifEnabled = "已禁用";
				}else{
					ifEnabled = "未禁用";
				}
				jsonSingle.put("enabled", ifEnabled);
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	/**
	 * 添加评价设置
	 * @Title: EstimateSetManageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addEstimateSetManage";
		} else {
			if(GeneralUtil.isNull(enabled)) {
				enabled = 0;
			}
			if(GeneralUtil.isNull(parent_id)) {
				parent_id = null;
			}
			if(GeneralUtil.isNull(name)){
				data = AjaxData.responseError("评价类别，项目或指标不能为空");
				return JSON;
			}
			//保存评价设置实体
			boolean isSuc = evaluationSettingService.persist(enabled,  name, orderValue, parent_id);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增评价类别/项目/指标";
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
	 * 修改评价设置
	 * @Title: EstimateSetManageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setEstimateSetManage(evaluationSettingService.queryById(EvaluationSetting.class.getSimpleName(), id));
			return "editEstimateSetManage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(enabled)) {
				enabled = 0;
			}
			if(GeneralUtil.isNull(parent_id)) {
				parent_id = null;
			}
			if(GeneralUtil.isNull(name)){
				data = AjaxData.responseError("评价类别，项目或指标不能为空");
				return JSON;
			}
			//修改评价设置实体
			boolean isSuc = evaluationSettingService.merge(id, enabled, name, orderValue, parent_id);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改评价类别/项目/指标";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess("修改成功");
			} else {
				data = AjaxData.responseError("修改失败");
			}
			return JSON;
		}
	}
	
	/**
	 * 管理评价设置实体
	 * @Title: EstimateSetManageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(evaluationSettingService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		
		return "manageEstimateSetManage";
	}
	
	/**
	 * 查看回收站
	 * @Title: EstimateSetManageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(evaluationSettingService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEstimateSetManage";
	}
	
	/**
	 * 逻辑删除评价设置对象
	 * @Title: EstimateSetManageAction
	 * @Description: 把评价设置对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = evaluationSettingService.logicDeleteEntity(EvaluationSetting.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除评价类别/项目/指标";
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
	 * 物理删除评价设置对象
	 * @Title: EstimateSetManageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = evaluationSettingService.remove(id);
		if(isSuc) {
			String logContent = "物理删除评价类别/项目/指标";
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
	 * 还原一个评价设置对象
	 * @Title: EstimateSetManageAction
	 * @Description: 从回收站还原评价设置对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = evaluationSettingService.restoreEntity(EvaluationSetting.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原评价类别/项目/指标";
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
	 * 审核评价设置对象
	 * @Title: EstimateSetManageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = evaluationSettingService.auditEntity(EvaluationSetting.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核评价类别/项目/指标";
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
	 * @Title: EstimateSetManageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = evaluationSettingService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作评价类别/项目/指标";
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
	
	public EvaluationSetting getEstimateSetManage() {
		return estimateSetManage;
	}
	public void setEstimateSetManage(EvaluationSetting estimateSetManage) {
		this.estimateSetManage = estimateSetManage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public IEvaluationSettingService getEvaluationSettingService() {
		return evaluationSettingService;
	}

	public void setEvaluationSettingService(
			IEvaluationSettingService estimateSetManageService) {
		this.evaluationSettingService = estimateSetManageService;
	}

	public ITreeService<EvaluationSetting, String> getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService<EvaluationSetting, String> treeService) {
		this.treeService = treeService;
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
}
