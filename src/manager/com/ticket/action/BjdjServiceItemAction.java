package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.service.IBjdjServiceItemService;
import com.ticket.util.GeneralUtil;

/**
 * 便捷登机服务项控制器
 * @ClassName: BjdjServiceItemAction   
 * @Description:  提供便捷登机服务项的相关操作方法. 
 * @author HiSay  
 * @date 2016-06-30 17:48:04
 *
 */
public class BjdjServiceItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机服务项的业务层
	@Resource private IBjdjServiceItemService bjdjServiceItemService = null;
	//便捷登机服务项实体
	private BjdjServiceItem bjdjServiceItem = null;
	//主键
	private String id = null;
    //服务项名称
	private String name = null;
    //服务项描述
	private String description = null;
	
	/**
	 * 添加便捷登机服务项
	 * @Title: BjdjServiceItemAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjServiceItem";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			//保存便捷登机服务项实体
			boolean isSuc = bjdjServiceItemService.persist(name, description, versionFlag);
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
	 * 修改便捷登机服务项
	 * @Title: BjdjServiceItemAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjServiceItem(bjdjServiceItemService.queryById(BjdjServiceItem.class.getSimpleName(), id));
			return "editBjdjServiceItem";
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
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			//修改便捷登机服务项实体
			boolean isSuc = bjdjServiceItemService.merge(id, name, description,  versionFlag);
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
	 * 管理便捷登机服务项实体
	 * @Title: BjdjServiceItemAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjServiceItemService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServiceItem";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjServiceItemAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjServiceItemService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServiceItem";
	}
	
	/**
	 * 逻辑删除便捷登机服务项对象
	 * @Title: BjdjServiceItemAction
	 * @Description: 把便捷登机服务项对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjServiceItemService.logicDeleteEntity(BjdjServiceItem.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除便捷登机服务项对象
	 * @Title: BjdjServiceItemAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjServiceItemService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个便捷登机服务项对象
	 * @Title: BjdjServiceItemAction
	 * @Description: 从回收站还原便捷登机服务项对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjServiceItemService.restoreEntity(BjdjServiceItem.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机服务项对象
	 * @Title: BjdjServiceItemAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjServiceItemService.auditEntity(BjdjServiceItem.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjServiceItemAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjServiceItemService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public BjdjServiceItem getBjdjServiceItem() {
		return bjdjServiceItem;
	}
	public void setBjdjServiceItem(BjdjServiceItem bjdjServiceItem) {
		this.bjdjServiceItem = bjdjServiceItem;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
