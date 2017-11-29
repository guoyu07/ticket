package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelType;
import com.ticket.service.IChannelTypeService;
import com.ticket.util.GeneralUtil;

/**
 * 渠道分类控制器
 * @ClassName: ChannelTypeAction   
 * @Description:  提供渠道分类的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-03 17:39:54
 */
public class ChannelTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//渠道分类的业务层
	@Resource private IChannelTypeService channelTypeService = null;
	//渠道分类实体
	private ChannelType channelType = null;
	//主键
	private String id = null;
    //分类名称
	private String name = null;
    //分类描述
	private String descript = null;
	private String systemDictionary_id = null;
	//员工客户分类
	private Integer empCustomerFlag = null;
	
	/**
	 * 添加渠道分类
	 * @Title: ChannelTypeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addChannelType";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
			//保存渠道分类实体
			boolean isSuc = channelTypeService.persist(name, descript, systemDictionary_id,empCustomerFlag);
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
	 * 修改渠道分类
	 * @Title: ChannelTypeAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setChannelType(channelTypeService.queryById(ChannelType.class.getSimpleName(), id));
			return "editChannelType";
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
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
			//修改渠道分类实体
			boolean isSuc = channelTypeService.merge(id, name, descript,  systemDictionary_id,empCustomerFlag);
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
	 * 管理渠道分类实体
	 * @Title: ChannelTypeAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(channelTypeService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageChannelType";
	}
	
	/**
	 * 查看回收站
	 * @Title: ChannelTypeAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(channelTypeService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleChannelType";
	}
	
	/**
	 * 逻辑删除渠道分类对象
	 * @Title: ChannelTypeAction
	 * @Description: 把渠道分类对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = channelTypeService.logicDeleteEntity(ChannelType.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除渠道分类对象
	 * @Title: ChannelTypeAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = channelTypeService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个渠道分类对象
	 * @Title: ChannelTypeAction
	 * @Description: 从回收站还原渠道分类对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = channelTypeService.restoreEntity(ChannelType.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核渠道分类对象
	 * @Title: ChannelTypeAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = channelTypeService.auditEntity(ChannelType.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ChannelTypeAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = channelTypeService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public ChannelType getChannelType() {
		return channelType;
	}
	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
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
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getSystemDictionary_id() {
		return systemDictionary_id;
	}

	public void setSystemDictionary_id(String systemDictionaryId) {
		systemDictionary_id = systemDictionaryId;
	}

	public Integer getEmpCustomerFlag() {
		return empCustomerFlag;
	}

	public void setEmpCustomerFlag(Integer empCustomerFlag) {
		this.empCustomerFlag = empCustomerFlag;
	}
	
}
