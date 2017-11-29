package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MessageTemplate;
import com.ticket.pojo.Wifi;
import com.ticket.pojo.WifiArea;
import com.ticket.pojo.WifiAreaMessage;
import com.ticket.service.IWifiAreaMessageService;
import com.ticket.util.GeneralUtil;

/**
 * ifi消息关联控制器
 * @ClassName: WifiMessageAction   
 * @Description:  提供ifi消息关联的相关操作方法. 
 * @author HiSay  
 * @date 2016-08-09 10:51:02
 *
 */
public class WifiAreaMessageAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//ifi消息关联的业务层
	@Resource private IWifiAreaMessageService wifiMessageService;
	//ifi消息关联实体
	private List<WifiArea> wifis;
	private List<MessageTemplate> messages;
	private WifiAreaMessage wifiMessage;
	//主键
	private String id;
    //wifi
	private Wifi wifi;
    //消息
	private MessageTemplate message;
	
	/**
	 * 添加ifi消息关联
	 * @Title: WifiMessageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			wifis = wifiMessageService.queryAll(WifiArea.class);
			messages = wifiMessageService.queryAll(MessageTemplate.class);
			return "addWifiMessage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(wifi) || GeneralUtil.isNull(wifi.getId())) {
				data = AjaxData.responseError(getText("wifi.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(message) || GeneralUtil.isNull(message.getId())) {
				data = AjaxData.responseError(getText("message.required"));
				return JSON;
			}
			//保存ifi消息关联实体
			boolean isSuc = wifiMessageService.persist(wifiMessageService.get(WifiArea.class, wifi.getId()), 
					wifiMessageService.get(MessageTemplate.class, message.getId()), versionFlag);
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
	 * 修改ifi消息关联
	 * @Title: WifiMessageAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			
			wifis = wifiMessageService.queryAll(WifiArea.class);
			messages = wifiMessageService.queryAll(MessageTemplate.class);
			this.setWifiMessage(wifiMessageService.queryById(WifiAreaMessage.class.getSimpleName(), id));
			return "editWifiMessage";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(wifi) || GeneralUtil.isNull(wifi.getId())) {
				data = AjaxData.responseError(getText("wifi.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(message) || GeneralUtil.isNull(message.getId())) {
				data = AjaxData.responseError(getText("message.required"));
				return JSON;
			}
			//修改ifi消息关联实体
			boolean isSuc = wifiMessageService.merge(id, wifiMessageService.get(WifiArea.class, wifi.getId())
					, wifiMessageService.get(MessageTemplate.class, message.getId()),  versionFlag);
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
	 * 管理ifi消息关联实体
	 * @Title: WifiMessageAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(wifiMessageService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageWifiMessage";
	}
	
	/**
	 * 查看回收站
	 * @Title: WifiMessageAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(wifiMessageService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleWifiMessage";
	}
	
	/**
	 * 逻辑删除ifi消息关联对象
	 * @Title: WifiMessageAction
	 * @Description: 把ifi消息关联对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = wifiMessageService.logicDeleteEntity(WifiAreaMessage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除ifi消息关联对象
	 * @Title: WifiMessageAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = wifiMessageService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个ifi消息关联对象
	 * @Title: WifiMessageAction
	 * @Description: 从回收站还原ifi消息关联对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = wifiMessageService.restoreEntity(WifiAreaMessage.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核ifi消息关联对象
	 * @Title: WifiMessageAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = wifiMessageService.auditEntity(WifiAreaMessage.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: WifiMessageAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = wifiMessageService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public WifiAreaMessage getWifiMessage() {
		return wifiMessage;
	}
	public void setWifiMessage(WifiAreaMessage wifiMessage) {
		this.wifiMessage = wifiMessage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Wifi getWifi() {
		return wifi;
	}
	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}
	public MessageTemplate getMessage() {
		return message;
	}
	public void setMessage(MessageTemplate message) {
		this.message = message;
	}
	public List<WifiArea> getWifis() {
		return wifis;
	}
	public void setWifis(List<WifiArea> wifis) {
		this.wifis = wifis;
	}
	public List<MessageTemplate> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageTemplate> messages) {
		this.messages = messages;
	}
}
