package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjHall;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjServicePackage;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IBjdjHallService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjServicePackageService;
import com.ticket.util.GeneralUtil;

/**
 * 便捷登机服务套餐控制器
 * @ClassName: BjdjServicePackageAction   
 * @Description:  提供便捷登机服务套餐的相关操作方法. 
 * @author HiSay  
 * @date 2016-06-30 17:48:33
 *
 */
public class BjdjServicePackageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机服务套餐的业务层
	@Resource private IBjdjServicePackageService bjdjServicePackageService;
	@Resource private IBjdjHallService bjdjHallService;
	@Resource private IBjdjPageService bjdjPageService;
	//便捷登机服务套餐实体
	private BjdjServicePackage bjdjServicePackage;
	//主键
	private String id;
    //服务套餐名称
	private String name;
    //服务套餐价格
	private Double price;
    //服务项描述
	private String description;
	//对应的服务厅id
	private String bjdjHall_id;
	//前端显示页面id
	private String page_id;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.name", Condition.like_left, name);
		group.and("s.bjdjHall.number", Condition.eq, bjdjHall_id);
		group.and("s.price", Condition.eq, price);
		List<BjdjServicePackage> list = bjdjServicePackageService.getDbDAO().executeJPQLForQuery("select s from " + BjdjServicePackage.class.getName() + " s " + group.toString(true), group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/bjdjServicePackage.jasper", list, "服务套餐");
		return null;
	}
	
	/**
	 * 添加便捷登机服务套餐
	 * @Title: BjdjServicePackageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<BjdjHall> bjdjHalls = bjdjHallService.queryAll(BjdjHall.class);
			List<BjdjPage> bjdjPages = bjdjPageService.queryAll(BjdjPage.class);
			ActionContext.getContext().put("bjdjPages", bjdjPages);
			ActionContext.getContext().put("bjdjHalls", bjdjHalls);
			return "addBjdjServicePackage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(price)) {
				data = AjaxData.responseError(getText("price.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(bjdjHall_id)) {
				data = AjaxData.responseError("服务厅不能为空！");
				return JSON;
			}
			//保存便捷登机服务套餐实体
			boolean isSuc = bjdjServicePackageService.persist(name, price, description,bjdjHall_id,page_id, versionFlag);
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
	 * 修改便捷登机服务套餐
	 * @Title: BjdjServicePackageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			List<BjdjHall> bjdjHalls = bjdjHallService.queryAll(BjdjHall.class);
			List<BjdjPage> bjdjPages = bjdjPageService.queryAll(BjdjPage.class);
			ActionContext.getContext().put("bjdjPages", bjdjPages);
			ActionContext.getContext().put("bjdjHalls", bjdjHalls);
			this.setBjdjServicePackage(bjdjServicePackageService.queryById(BjdjServicePackage.class.getSimpleName(), id));
			return "editBjdjServicePackage";
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
			if(GeneralUtil.isNull(price)) {
				data = AjaxData.responseError(getText("price.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(description)) {
				data = AjaxData.responseError(getText("description.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(bjdjHall_id)) {
				data = AjaxData.responseError("服务厅不能为空！");
				return JSON;
			}
			if(GeneralUtil.isNull(page_id)) {
				data = AjaxData.responseError("前端显示页面不能为空！");
				return JSON;
			}
			//修改便捷登机服务套餐实体
			boolean isSuc = bjdjServicePackageService.merge(id, name, price, description,bjdjHall_id, page_id, versionFlag);
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
	 * 管理便捷登机服务套餐实体
	 * @Title: BjdjServicePackageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		SqlParamGroup group = new SqlParamGroup("s.name", Condition.like_left, name);
		group.and("s.bjdjHall.number", Condition.eq, bjdjHall_id);
		group.and("s.price", Condition.eq, price);
		group.and("s.status.deleteFlag", Condition.eq, 0);
		this.setPageModule(bjdjServicePackageService.paginationQuery("select s from " + BjdjServicePackage.class.getName() + " s " + group.toString(true), group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjServicePackage";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjServicePackageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(bjdjServicePackageService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjServicePackage";
	}
	
	/**
	 * 逻辑删除便捷登机服务套餐对象
	 * @Title: BjdjServicePackageAction
	 * @Description: 把便捷登机服务套餐对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = bjdjServicePackageService.logicDeleteEntity(BjdjServicePackage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除便捷登机服务套餐对象
	 * @Title: BjdjServicePackageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = bjdjServicePackageService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个便捷登机服务套餐对象
	 * @Title: BjdjServicePackageAction
	 * @Description: 从回收站还原便捷登机服务套餐对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = bjdjServicePackageService.restoreEntity(BjdjServicePackage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核便捷登机服务套餐对象
	 * @Title: BjdjServicePackageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = bjdjServicePackageService.auditEntity(BjdjServicePackage.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: BjdjServicePackageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = bjdjServicePackageService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public BjdjServicePackage getBjdjServicePackage() {
		return bjdjServicePackage;
	}
	public void setBjdjServicePackage(BjdjServicePackage bjdjServicePackage) {
		this.bjdjServicePackage = bjdjServicePackage;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBjdjHall_id() {
		return bjdjHall_id;
	}

	public void setBjdjHall_id(String bjdjHall_id) {
		this.bjdjHall_id = bjdjHall_id;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
}
