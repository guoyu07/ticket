package com.ticket.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.TimeSearch;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ITimeSearchService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 搜索统计控制器
 * @ClassName: TimeSearchAction   
 * @Description:  提供搜索统计的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-12 11:26:22
 *
 */
public class TimeSearchAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//搜索统计的业务层
	@Resource private ITimeSearchService timeSearchService;
	@Resource private ISystemOperationLogService logService = null;
	//搜索统计实体
	private TimeSearch timeSearch;
	//主键
	private String id;
    //落地页链接
	private String goUrl;
    //展现量
	private Integer showRate;
    //点击量
	private Integer clickRate;
    //触发展现的搜索词
	private String showKeyword;
    //触发点击的搜索词
	private String clickKeyword;
    //对应的预定义搜索结果id
	private String preResultDefinitionId;
	
	private Date date;
		
	private InputStream inputStream;
	
	private String fileName;	
	
	
	/**
	 * 添加搜索统计
	 * @Title: TimeSearchAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addTimeSearch";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(goUrl)) {
				data = AjaxData.responseError(getText("goUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(showRate)) {
				data = AjaxData.responseError(getText("showRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(clickRate)) {
				data = AjaxData.responseError(getText("clickRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(showKeyword)) {
				data = AjaxData.responseError(getText("showKeyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(clickKeyword)) {
				data = AjaxData.responseError(getText("clickKeyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(preResultDefinitionId)) {
				data = AjaxData.responseError(getText("preResultDefinitionId.required"));
				return JSON;
			}
			//保存搜索统计实体
			boolean isSuc = timeSearchService.persist(goUrl, showRate, clickRate, showKeyword, clickKeyword, preResultDefinitionId, versionFlag);
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
	 * 修改搜索统计
	 * @Title: TimeSearchAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setTimeSearch(timeSearchService.queryById(TimeSearch.class.getSimpleName(), id));
			return "editTimeSearch";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(goUrl)) {
				data = AjaxData.responseError(getText("goUrl.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(showRate)) {
				data = AjaxData.responseError(getText("showRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(clickRate)) {
				data = AjaxData.responseError(getText("clickRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(showKeyword)) {
				data = AjaxData.responseError(getText("showKeyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(clickKeyword)) {
				data = AjaxData.responseError(getText("clickKeyword.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(preResultDefinitionId)) {
				data = AjaxData.responseError(getText("preResultDefinitionId.required"));
				return JSON;
			}
			//修改搜索统计实体
			boolean isSuc = timeSearchService.merge(id, goUrl, showRate, clickRate, showKeyword, clickKeyword, preResultDefinitionId,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理搜索统计实体
	 * @Title: TimeSearchAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(timeSearchService.queryEntityByAdmin(date,versionFlag,ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageTimeSearch";
	}
	
	public String manageNoDate() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(timeSearchService.queryAllNoDate(date,versionFlag,ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "noData";
	}
	
	/**
	 * 查看回收站
	 * @Title: TimeSearchAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(timeSearchService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleTimeSearch";
	}
	
	/**
	 * 逻辑删除搜索统计对象
	 * @Title: TimeSearchAction
	 * @Description: 把搜索统计对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = timeSearchService.logicDeleteEntity(TimeSearch.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除搜索统计对象
	 * @Title: TimeSearchAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = timeSearchService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个搜索统计对象
	 * @Title: TimeSearchAction
	 * @Description: 从回收站还原搜索统计对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = timeSearchService.restoreEntity(TimeSearch.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核搜索统计对象
	 * @Title: TimeSearchAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = timeSearchService.auditEntity(TimeSearch.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: TimeSearchAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = timeSearchService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public String batchDownLoad() throws ServiceException, FileNotFoundException{
		String logContent = "下载搜索统计";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		String destFilePath = timeSearchService.createJxls(date);
		File file = new File(destFilePath);
		fileName = file.getName();
		inputStream = new FileInputStream(file);
		return "download";
	}
	
	public TimeSearch getTimeSearch() {
		return timeSearch;
	}
	public void setTimeSearch(TimeSearch timeSearch) {
		this.timeSearch = timeSearch;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoUrl() {
		return goUrl;
	}
	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}
	public Integer getShowRate() {
		return showRate;
	}
	public void setShowRate(Integer showRate) {
		this.showRate = showRate;
	}
	public Integer getClickRate() {
		return clickRate;
	}
	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}
	public String getShowKeyword() {
		return showKeyword;
	}
	public void setShowKeyword(String showKeyword) {
		this.showKeyword = showKeyword;
	}
	public String getClickKeyword() {
		return clickKeyword;
	}
	public void setClickKeyword(String clickKeyword) {
		this.clickKeyword = clickKeyword;
	}
	public String getPreResultDefinitionId() {
		return preResultDefinitionId;
	}
	public void setPreResultDefinitionId(String preResultDefinitionId) {
		this.preResultDefinitionId = preResultDefinitionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
