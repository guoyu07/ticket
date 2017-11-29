package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BusinessEvent;
import com.ticket.pojo.BusinessEventBind;
import com.ticket.pojo.BusinessInfo;
import com.ticket.service.IBusinessEventBindService;
import com.ticket.service.IBusinessEventService;
import com.ticket.service.IBusinessInfoService;
import com.ticket.util.GeneralUtil;

/**
 * 商家活动绑定控制器
 * @ClassName: BusinessEventBindAction   
 * @Description:  提供商家活动绑定的相关操作方法. 
 * @author HiSay  
 * @date 2016-12-15 10:18:13
 *
 */
public class BusinessEventBindAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//商家活动绑定的业务层
	@Resource private IBusinessEventBindService businessEventBindService;
	//商家活动绑定实体
	private BusinessEventBind businessEventBind;
	@Resource
	private IBusinessInfoService businessInfoService;
	@Resource
	private IBusinessEventService businessEventService;
	//主键
	private String id;
    //商家
	private BusinessInfo businessInfo;
    //活动
	private BusinessEvent businessEvent;
	//商家id
	private String businessInfo_id;
	//活动id
	private String businessEvent_id;
	
	/**
	 * 添加商家活动绑定
	 * @Title: BusinessEventBindAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBusinessInfo(businessInfoService.get(BusinessInfo.class, businessInfo_id));
			return "addBusinessEventBind";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(businessInfo_id)){
				data = AjaxData.responseError("商家ID不能为空！");
				return JSON;
			}
			businessInfo = businessInfoService.get(BusinessInfo.class, businessInfo_id);
			if(GeneralUtil.isNull(businessInfo)) {
				data = AjaxData.responseError(getText("businessInfo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessEvent_id)){
				data = AjaxData.responseError("活动ID不能为空！");
				return JSON;
			}
			businessEvent = businessEventService.get(BusinessEvent.class, businessEvent_id);
			if(GeneralUtil.isNull(businessEvent)) {
				data = AjaxData.responseError(getText("businessEvent.required"));
				return JSON;
			}
			businessEventBind = businessEventBindService.queryByBusinessIdAndEventId(businessInfo_id, businessEvent_id);
			if(businessEventBind != null){
				data = AjaxData.responseError("该商品已经绑定过该活动了！");
			}else{
				//保存商家活动绑定实体
				boolean isSuc = businessEventBindService.persist(businessInfo, businessEvent, versionFlag);
				//根据保存结果返回页面
				if(isSuc) {
					data = AjaxData.responseSuccess(getText("addSuccess"));
				} else {
					data = AjaxData.responseError(getText("addFailed"));
				}
			}
			return JSON;
		}
	}
	
	/**
	 * 修改商家活动绑定
	 * @Title: BusinessEventBindAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBusinessEventBind(businessEventBindService.queryById(BusinessEventBind.class.getSimpleName(), id));
			return "editBusinessEventBind";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessInfo)) {
				data = AjaxData.responseError(getText("businessInfo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(businessEvent)) {
				data = AjaxData.responseError(getText("businessEvent.required"));
				return JSON;
			}
			//修改商家活动绑定实体
			boolean isSuc = businessEventBindService.merge(id, businessInfo, businessEvent,  versionFlag);
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
	 * 管理商家活动绑定实体
	 * @Title: BusinessEventBindAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		if(GeneralUtil.isNotNull(id)){
			this.setPageModule(businessEventBindService.paginationQuery("select c from " + BusinessEventBind.class.getName() + " c where c.businessInfo.id = ? ", BusinessEventBind.class,id));
		}else{
			this.setPageModule(businessEventBindService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBusinessEventBind";
	}
	
	/**
	 * 查看回收站
	 * @Title: BusinessEventBindAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(businessEventBindService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBusinessEventBind";
	}
	
	/**
	 * 逻辑删除商家活动绑定对象
	 * @Title: BusinessEventBindAction
	 * @Description: 把商家活动绑定对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = businessEventBindService.logicDeleteEntity(BusinessEventBind.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除商家活动绑定对象
	 * @Title: BusinessEventBindAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = businessEventBindService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个商家活动绑定对象
	 * @Title: BusinessEventBindAction
	 * @Description: 从回收站还原商家活动绑定对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = businessEventBindService.restoreEntity(BusinessEventBind.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核商家活动绑定对象
	 * @Title: BusinessEventBindAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = businessEventBindService.auditEntity(BusinessEventBind.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BusinessEventBindAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = businessEventBindService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public BusinessEventBind getBusinessEventBind() {
		return businessEventBind;
	}
	public void setBusinessEventBind(BusinessEventBind businessEventBind) {
		this.businessEventBind = businessEventBind;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}
	public BusinessEvent getBusinessEvent() {
		return businessEvent;
	}
	public void setBusinessEvent(BusinessEvent businessEvent) {
		this.businessEvent = businessEvent;
	}

	public String getBusinessInfo_id() {
		return businessInfo_id;
	}

	public void setBusinessInfo_id(String businessInfo_id) {
		this.businessInfo_id = businessInfo_id;
	}

	public String getBusinessEvent_id() {
		return businessEvent_id;
	}

	public void setBusinessEvent_id(String businessEvent_id) {
		this.businessEvent_id = businessEvent_id;
	}
}
