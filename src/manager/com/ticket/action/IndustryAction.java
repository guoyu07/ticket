package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Industry;
import com.ticket.service.IIndustryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 企业行业控制器
 * @ClassName: IndustryAction   
 * @Description:  提供企业行业的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-11 09:44:02
 *
 */
public class IndustryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//企业行业的业务层
	@Resource private IIndustryService industryService = null;
	@Resource private ISystemOperationLogService logService = null;
	//企业行业实体
	private Industry industry = null;
	//主键
	private String id,name,introduce,parentId,industryHtml,industryTree = null;
	
	
	/**
	 * @author wangjiafeng
	 * 修改排序值
	 * @method changeOrderValue
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-5 上午11:06:50
	 */
	public String changeOrderValue() throws Exception{
		industry = industryService.queryById(Industry.class.getSimpleName(), id);
		Boolean suc = false;
		if(industry != null && GeneralUtil.isNotNull(orderValue)){
			industry.getStatus().setOrderValue(orderValue);
			industryService.merge(industry);
			suc = true;
		}
		if(suc){
			String logContent = "修改企业行业排序值";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * 添加企业行业
	 * @Title: IndustryAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setIndustryHtml(industryService.createIndustryOptionHtml(id));
			return "addIndustry";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("行业名称不能为空");
				return JSON;
			}
			
			//保存企业行业实体
			boolean isSuc = industryService.persist(name, introduce, parentId);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增企业行业";
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
	 * 修改企业行业
	 * @Title: IndustryAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setIndustry(industryService.queryById(Industry.class.getSimpleName(), id));
			if(industry.getParent() != null){
				this.setIndustryHtml(industryService.createIndustryOptionHtml(industry.getParent().getId()));
			}else{
				this.setIndustryHtml(industryService.createIndustryOptionHtml(null));
			}
			return "editIndustry";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError("不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError("行业名称不能为空");
				return JSON;
			}
			
			//修改企业行业实体
			boolean isSuc = industryService.merge(id, name, introduce,parentId);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改企业行业";
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
	 * 管理企业行业实体
	 * @Title: IndustryAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		industryTree = industryService.createIndustryTree();
		return "manageIndustry";
	}
	
	/**
	 * 查看回收站
	 * @Title: IndustryAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(industryService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleIndustry";
	}
	
	/**
	 * 逻辑删除企业行业对象
	 * @Title: IndustryAction
	 * @Description: 把企业行业对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = industryService.logicDeleteEntity(Industry.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除企业行业";
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
	 * 物理删除企业行业对象
	 * @Title: IndustryAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = industryService.remove(id);
		if(isSuc) {
			String logContent = "物理删除企业行业";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = "success";
		} else {
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * 还原一个企业行业对象
	 * @Title: IndustryAction
	 * @Description: 从回收站还原企业行业对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = industryService.restoreEntity(Industry.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "批量操作企业行业";
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
	 * 审核企业行业对象
	 * @Title: IndustryAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = industryService.auditEntity(Industry.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核企业行业";
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
	 * @Title: IndustryAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = industryService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作企业行业";
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
	
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
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
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIndustryHtml() {
		return industryHtml;
	}

	public void setIndustryHtml(String industryHtml) {
		this.industryHtml = industryHtml;
	}

	public String getIndustryTree() {
		return industryTree;
	}

	public void setIndustryTree(String industryTree) {
		this.industryTree = industryTree;
	}
	
}
