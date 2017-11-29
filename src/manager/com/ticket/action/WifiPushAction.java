package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberSendInfo.PushMethod;
import com.ticket.pojo.MessageTemplate;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.service.IMemberService;
import com.ticket.service.IWifiPushService;
import com.ticket.util.GeneralUtil;

/**
 * wifi推送控制器
 * @author 涂有  
 */
public class WifiPushAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//wifi设备的业务层
	@Resource private IWifiPushService wifiPushService;
	@Resource private IMemberSendInfoService sendInfoService;
	@Resource private IMemberService memberService;
	
	private List<MessageTemplate> messages;
	private String id;
	private String phone;
	private String mac;
	
	/**
	 * 手动推送数据
	 * @return
	 */
	public String add() throws ServiceException{
		
		if(GeneralUtil.isNull(operationFlag)) {
			
			messages = wifiPushService.queryAll(MessageTemplate.class);
			return "add";
		} else {
			
			if(GeneralUtil.isNull(phone)){
				
				data = AjaxData.responseError("电话号码不能为空");
				return JSON;
			}
			if(GeneralUtil.isNull(id)){
				
				data = AjaxData.responseError("选择的消息不能为空");
				return JSON;
			}
			
			Member member = memberService.queryByMobile(phone, versionFlag);
			if(member == null){
				
				data = AjaxData.responseError("你输入的号码还未注册");
				return JSON;
			}
			MessageTemplate message = memberService.get(MessageTemplate.class, id);
			
			sendInfoService.persist(member.getId(), PushMethod.manual, "", message.getTitle(), message.getContent(), message.getUrl(), null, null, versionFlag);
			data = AjaxData.responseSuccess("发送成功");
			return JSON;
		}
	}
	
	/**
	 * 管理页面
	 * @return
	 * @throws ServiceException
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(wifiPushService.pagingQuery(mac, phone));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manage";
	}

	public List<MessageTemplate> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageTemplate> messages) {
		this.messages = messages;
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
