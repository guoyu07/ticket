package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberMac;
import com.ticket.service.IMemberMacService;
import com.ticket.util.GeneralUtil;

/**
 * 用户的mac地址控制器
 * @ClassName: MemberMacAction   
 * @Description:  提供用户的mac地址的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-09 10:51:20
 *
 */
public class MemberMacAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//用户的mac地址的业务层
	@Resource private IMemberMacService memberMacService;
	//用户的mac地址实体
	private MemberMac memberMac;
	//主键
	private String id;
    //电话号码
	private String phone;
    //mac地址
	private String mac;
	
	/**
	 * 添加用户的mac地址
	 * @Title: MemberMacAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMemberMac";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("member.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(mac)) {
				data = AjaxData.responseError(getText("mac.required"));
				return JSON;
			}
			//保存用户的mac地址实体
			boolean isSuc = memberMacService.persist(phone, mac, versionFlag);
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
	 * 修改用户的mac地址
	 * @Title: MemberMacAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMemberMac(memberMacService.queryById(MemberMac.class.getSimpleName(), id));
			return "editMemberMac";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("member.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(mac)) {
				data = AjaxData.responseError(getText("mac.required"));
				return JSON;
			}
			//修改用户的mac地址实体
			boolean isSuc = memberMacService.merge(id, phone, mac,  versionFlag);
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
	 * 管理用户的mac地址实体
	 * @Title: MemberMacAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberMacService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberMac";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberMacAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberMacService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberMac";
	}
	
	/**
	 * 逻辑删除用户的mac地址对象
	 * @Title: MemberMacAction
	 * @Description: 把用户的mac地址对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberMacService.logicDeleteEntity(MemberMac.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除用户的mac地址对象
	 * @Title: MemberMacAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberMacService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个用户的mac地址对象
	 * @Title: MemberMacAction
	 * @Description: 从回收站还原用户的mac地址对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberMacService.restoreEntity(MemberMac.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核用户的mac地址对象
	 * @Title: MemberMacAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberMacService.auditEntity(MemberMac.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberMacAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberMacService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MemberMac getMemberMac() {
		return memberMac;
	}
	public void setMemberMac(MemberMac memberMac) {
		this.memberMac = memberMac;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
}
