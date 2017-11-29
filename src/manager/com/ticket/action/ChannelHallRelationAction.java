package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelHallRelation;
import com.ticket.service.IChannelHallRelationService;
import com.ticket.util.GeneralUtil;

/**
 * 渠道类型服务大厅关联关系控制器
 * @ClassName: ChannelHallRelationAction   
 * @Description:  提供渠道类型服务大厅关联关系的相关操作方法. 
 * @author tuyou  
 * @date 2016-03-18 16:22:12
 *
 */
public class ChannelHallRelationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//渠道类型服务大厅关联关系的业务层
	@Resource private IChannelHallRelationService ChannelHallRelationService = null;
	//渠道类型服务大厅关联关系实体
	private ChannelHallRelation channelHallRelation = null;
	//主键
	private String id = null;
    //大厅
	private String hall = null;
    //渠道类型
	private String channel = null;
	
	/**
	 * 添加渠道类型服务大厅关联关系
	 * @Title: ChannelHallRelationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addChannelHallRelation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(hall)) {
				data = AjaxData.responseError(getText("hall.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channel)) {
				data = AjaxData.responseError(getText("channel.required"));
				return JSON;
			}
			//保存渠道类型服务大厅关联关系实体
			try {
				ChannelHallRelationService.persist(hall, channel, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
				return JSON;
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
	}
	
	/**
	 * 修改渠道类型服务大厅关联关系
	 * @Title: ChannelHallRelationAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setChannelHallRelation(ChannelHallRelationService.queryById(ChannelHallRelation.class.getSimpleName(), id));
			return "editChannelHallRelation";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(hall)) {
				data = AjaxData.responseError(getText("hall.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(channel)) {
				data = AjaxData.responseError(getText("channel.required"));
				return JSON;
			}
			try {
				ChannelHallRelationService.merge(id, hall, channel, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
				return JSON;
			} catch (Exception e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
		}
	}
	
	/**
	 * 管理渠道类型服务大厅关联关系实体
	 * @Title: ChannelHallRelationAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(ChannelHallRelationService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageChannelHallRelation";
	}
	
	/**
	 * 查看回收站
	 * @Title: ChannelHallRelationAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(ChannelHallRelationService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleChannelHallRelation";
	}
	
	/**
	 * 逻辑删除渠道类型服务大厅关联关系对象
	 * @Title: ChannelHallRelationAction
	 * @Description: 把渠道类型服务大厅关联关系对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = ChannelHallRelationService.logicDeleteEntity(ChannelHallRelation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除渠道类型服务大厅关联关系对象
	 * @Title: ChannelHallRelationAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = ChannelHallRelationService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个渠道类型服务大厅关联关系对象
	 * @Title: ChannelHallRelationAction
	 * @Description: 从回收站还原渠道类型服务大厅关联关系对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = ChannelHallRelationService.restoreEntity(ChannelHallRelation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核渠道类型服务大厅关联关系对象
	 * @Title: ChannelHallRelationAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = ChannelHallRelationService.auditEntity(ChannelHallRelation.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ChannelHallRelationAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = ChannelHallRelationService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public ChannelHallRelation getChannelHallRelation() {
		return channelHallRelation;
	}
	public void setChannelHallRelation(ChannelHallRelation channelHallRelation) {
		this.channelHallRelation = channelHallRelation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public IChannelHallRelationService getChannelHallRelationService() {
		return ChannelHallRelationService;
	}

	public void setChannelHallRelationService(
			IChannelHallRelationService channelHallRelationService) {
		ChannelHallRelationService = channelHallRelationService;
	}

	public String getHall() {
		return hall;
	}

	public void setHall(String hall) {
		this.hall = hall;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
