package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.enumer.FreezeStatus;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessEvent;
import com.ticket.service.IBusinessEventService;
import com.ticket.util.GeneralUtil;

/**
 * 商家活动控制器
 * @ClassName: BusinessEventAction   
 * @Description:  提供商家活动的相关操作方法. 
 * @author HiSay  
 * @date 2016-12-14 10:20:43
 *
 */
public class BusinessEventAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//商家活动的业务层
	@Resource private IBusinessEventService businessEventService;
	//商家活动实体
	private BusinessEvent businessEvent;
	//主键
	private String id;
    //商家活动名称
	private String name;
    //活动内容
	private String content;
	//活动开始时间
	private Date startTime;
	//活动结束时间
	private Date endTime;
	//冻结状态
	private FreezeStatus freezeStatus;
	
	/**
	 * 添加商家活动
	 * @Title: BusinessEventAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBusinessEvent";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//保存商家活动实体
			boolean isSuc = businessEventService.persist(name, content, getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),startTime, endTime,versionFlag);
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
	 * 修改商家活动
	 * @Title: BusinessEventAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBusinessEvent(businessEventService.queryById(BusinessEvent.class.getSimpleName(), id));
			return "editBusinessEvent";
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
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			//修改商家活动实体
			boolean isSuc = businessEventService.merge(id, name, content, getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT),startTime, endTime ,versionFlag);
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
	 * 管理商家活动实体
	 * @Title: BusinessEventAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(businessEventService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBusinessEvent";
	}
	
	/**
	 * 冻结
	 * @return
	 */
	public String freeze(){
		boolean isSuc = businessEventService.freeze(id);
		if(isSuc){
			data = AjaxData.responseSuccess("冻结商家活动成功！");
		}else{
			data = AjaxData.responseError("冻结商家活动失败!");
		}
		return JSON;
	}
	
	/**
	 * 解冻
	 * @return
	 */
	public String actived(){
		boolean isSuc = businessEventService.actived(id);
		if(isSuc){
			data = AjaxData.responseSuccess("解冻商家活动成功！");
		}else{
			data = AjaxData.responseError("解冻商家活动失败!");
		}
		return JSON;
	}
	
	/**
	 * 查看回收站
	 * @Title: BusinessEventAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(businessEventService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBusinessEvent";
	}
	
	/**
	 * 逻辑删除商家活动对象
	 * @Title: BusinessEventAction
	 * @Description: 把商家活动对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = businessEventService.logicDeleteEntity(BusinessEvent.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除商家活动对象
	 * @Title: BusinessEventAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = businessEventService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个商家活动对象
	 * @Title: BusinessEventAction
	 * @Description: 从回收站还原商家活动对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = businessEventService.restoreEntity(BusinessEvent.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核商家活动对象
	 * @Title: BusinessEventAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = businessEventService.auditEntity(BusinessEvent.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BusinessEventAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = businessEventService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public BusinessEvent getBusinessEvent() {
		return businessEvent;
	}
	public void setBusinessEvent(BusinessEvent businessEvent) {
		this.businessEvent = businessEvent;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public FreezeStatus getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(FreezeStatus freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
}
