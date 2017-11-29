package com.ticket.action;

import java.util.Map;

import com.ticket.exception.ServiceException;

/**
 * PC端的服务码请求处理控制器
 * @author tuyou
 *
 */
public class PCServiceCodeAction extends BjdjServiceCodeActivateAction{

	private static final long serialVersionUID = 1L;

	/**
	 * 默认处理，跳转到激活首页
	 */
	public String page(){
		
		super.page();
		return "activatePage";
	}
	
	/**
	 * 跳转到激活成功页面
	 * @return
	 */
	public String activateSuccessPage(){
		
		return "activateSuccessPage";
	}
	
	/**
	 * 跳转到激活等待页面
	 * @return
	 */
	public String activateWaitPage(){
		
		return "activateWaitPage";
	}
	
	/**
	 * 通过身份证等证件添加
	 * @return
	 */
	public String addByCertificate(){
		
		super.addByCertificate();
		for(Map<String, Object> map : list){
			
			String date = (String)map.get("date");
			date = date.replaceAll("T", " ");
			map.put("date", date);
		}
		return "activateItem";
	}
	
	/**
	 * @Description：通过身份证等证件添加
	 * @return
	 * @throws ServiceException 
	 */
	public String addBlank(){
		
		return super.addBlank();
	}
}
