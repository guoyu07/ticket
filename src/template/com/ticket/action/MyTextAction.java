package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MyText;
import com.ticket.service.IMyTextService;
import com.ticket.util.GeneralUtil;

/**
 * 我的记事本控制器
 * @ClassName: MyTextAction   
 * @Description:  提供我的记事本的相关操作方法. 
 * @author HiSay  
 * @date 2016-02-15 11:26:54
 *
 */
public class MyTextAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//我的记事本的业务层
	@Resource private IMyTextService myTextService = null;
	//我的记事本实体
	private MyText myText = null;
	//主键
	private String id = null;
    //关联客户信id
	private String employeeInfo_id = null;
    //标题
	private String title = null;
    //内容
	private String content = null;
    //是否阅读
	private Integer isRead = null;
	
	/**
	 * 添加我的记事本
	 * @Title: MyTextAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMyText";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(employeeInfo_id)) {
				data = AjaxData.responseError(getText("employeeInfo_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isRead)) {
				data = AjaxData.responseError(getText("isRead.required"));
				return JSON;
			}
			//保存我的记事本实体
			boolean isSuc = false;//myTextService.persist(employeeInfo_id, title, content, isRead, versionFlag);
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
	 * 修改我的记事本
	 * @Title: MyTextAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMyText(myTextService.queryById(MyText.class.getSimpleName(), id));
			return "editMyText";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(employeeInfo_id)) {
				data = AjaxData.responseError(getText("employeeInfo_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(content)) {
				data = AjaxData.responseError(getText("content.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(isRead)) {
				data = AjaxData.responseError(getText("isRead.required"));
				return JSON;
			}
			//修改我的记事本实体
			boolean isSuc = false;//myTextService.merge(id, employeeInfo_id, title, content, isRead,  versionFlag);
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
	 * 管理我的记事本实体
	 * @Title: MyTextAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(myTextService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMyText";
	}
	
	/**
	 * 查看回收站
	 * @Title: MyTextAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(myTextService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMyText";
	}
	
	/**
	 * 逻辑删除我的记事本对象
	 * @Title: MyTextAction
	 * @Description: 把我的记事本对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = myTextService.logicDeleteEntity(MyText.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除我的记事本对象
	 * @Title: MyTextAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = myTextService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个我的记事本对象
	 * @Title: MyTextAction
	 * @Description: 从回收站还原我的记事本对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = myTextService.restoreEntity(MyText.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核我的记事本对象
	 * @Title: MyTextAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = myTextService.auditEntity(MyText.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MyTextAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = myTextService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MyText getMyText() {
		return myText;
	}
	public void setMyText(MyText myText) {
		this.myText = myText;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeInfo_id() {
		return employeeInfo_id;
	}
	public void setEmployeeInfo_id(String employeeInfo_id) {
		this.employeeInfo_id = employeeInfo_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}
