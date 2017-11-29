package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceItem;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.BjdjServicePackageItem;
import com.ticket.service.IBjdjServicePackageItemService;
import com.ticket.util.GeneralUtil;

/**
 * 便捷登机服务套餐关联项控制器
 * @ClassName: BjdjServicePackageItemAction   
 * @Description:  提供便捷登机服务套餐关联项的相关操作方法. 
 * @author HiSay  
 * @date 2016-06-30 17:49:00
 *
 */
public class BjdjServicePackageItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机服务套餐关联项的业务层
	@Resource private IBjdjServicePackageItemService bjdjServicePackageItemService;
	//便捷登机服务套餐关联项实体
	private BjdjServicePackageItem bjdjServicePackageItem;
	//主键
	private String id;
    //服务套餐名称
	private String packageName;
    //服务项
	private String item;
	//服务顺序
	private Integer orderValue;
	
	/**
	 * 添加便捷登机服务套餐关联项
	 * @Title: BjdjServicePackageItemAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			List<BjdjServiceItem> items = bjdjServicePackageItemService.queryAll(BjdjServiceItem.class);
			List<BjdjServicePackage> packages = bjdjServicePackageItemService.queryAll(BjdjServicePackage.class);
			ActionContext.getContext().put("items", items);
			ActionContext.getContext().put("packages", packages);
			return "addBjdjServicePackageItem";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(packageName)) {
				data = AjaxData.responseError(getText("packageName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(item)) {
				data = AjaxData.responseError(getText("item.required"));
				return JSON;
			}
			//保存便捷登机服务套餐关联项实体
			try {
				bjdjServicePackageItemService.persist(bjdjServicePackageItemService.get(BjdjServicePackage.class, packageName), 
						bjdjServicePackageItemService.get(BjdjServiceItem.class, item), orderValue, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
			}
			return JSON;
		}
	}
	
	/**
	 * 修改便捷登机服务套餐关联项
	 * @Title: BjdjServicePackageItemAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<BjdjServiceItem> items = bjdjServicePackageItemService.queryAll(BjdjServiceItem.class);
			List<BjdjServicePackage> packages = bjdjServicePackageItemService.queryAll(BjdjServicePackage.class);
			ActionContext.getContext().put("items", items);
			ActionContext.getContext().put("packages", packages);
			this.setBjdjServicePackageItem(bjdjServicePackageItemService.queryById(BjdjServicePackageItem.class.getSimpleName(), id));
			return "editBjdjServicePackageItem";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(packageName)) {
				data = AjaxData.responseError(getText("package.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(item)) {
				data = AjaxData.responseError(getText("item.required"));
				return JSON;
			}
			//修改便捷登机服务套餐关联项实体
			boolean isSuc = bjdjServicePackageItemService.merge(id, bjdjServicePackageItemService.get(BjdjServicePackage.class, packageName), 
					bjdjServicePackageItemService.get(BjdjServiceItem.class, item), orderValue, versionFlag);
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
	 * 管理便捷登机服务套餐关联项实体
	 * @Title: BjdjServicePackageItemAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjServicePackageItemService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServicePackageItem";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjServicePackageItemAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjServicePackageItemService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServicePackageItem";
	}
	
	/**
	 * 逻辑删除便捷登机服务套餐关联项对象
	 * @Title: BjdjServicePackageItemAction
	 * @Description: 把便捷登机服务套餐关联项对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjServicePackageItemService.logicDeleteEntity(BjdjServicePackageItem.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除便捷登机服务套餐关联项对象
	 * @Title: BjdjServicePackageItemAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjServicePackageItemService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个便捷登机服务套餐关联项对象
	 * @Title: BjdjServicePackageItemAction
	 * @Description: 从回收站还原便捷登机服务套餐关联项对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjServicePackageItemService.restoreEntity(BjdjServicePackageItem.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机服务套餐关联项对象
	 * @Title: BjdjServicePackageItemAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjServicePackageItemService.auditEntity(BjdjServicePackageItem.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjServicePackageItemAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjServicePackageItemService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public BjdjServicePackageItem getBjdjServicePackageItem() {
		return bjdjServicePackageItem;
	}
	public void setBjdjServicePackageItem(BjdjServicePackageItem bjdjServicePackageItem) {
		this.bjdjServicePackageItem = bjdjServicePackageItem;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public IBjdjServicePackageItemService getBjdjServicePackageItemService() {
		return bjdjServicePackageItemService;
	}

	public void setBjdjServicePackageItemService(
			IBjdjServicePackageItemService bjdjServicePackageItemService) {
		this.bjdjServicePackageItemService = bjdjServicePackageItemService;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
