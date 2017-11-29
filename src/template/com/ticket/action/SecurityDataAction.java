package com.ticket.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ticket.pojo.SecurityData;
import com.ticket.service.ISecurityDataService;

import net.sf.json.JSONObject;


/**
 * 保存安检数据的action 
 * @author zzf
 */
public class SecurityDataAction extends BaseAction  implements
ModelDriven<SecurityData>, Preparable{

	private static final long serialVersionUID = 1L;
    @Resource
	private ISecurityDataService securityDataService;
	private SecurityData securityData;
	private  String ticketNo;
	/**
	 * 保存安检数据
	 * @return
	 */
	public String saveSecurityData(){
		
		JSONObject jsonObject = new JSONObject();
		boolean flag = false;
		
		try {
			
			flag = securityDataService.saveSecurityByEntity(securityData);
		} catch (Exception e) {
			
			flag = false;
			e.printStackTrace();
		}
		
		if(flag){
			
			jsonObject.put("success", "true");
		}else{
			
			jsonObject.put("fail", "true");
		}
		
		data = jsonObject.toString();
		return TEXT;
	}
	
	/**
	 * 使用prepare 在根据方法名称去创建model
	 * modelDriver 是将model添加到栈顶
	 * prepare 过滤器为栈顶赋值
	 */
	public void prepareSaveSecurityData(){
		
		securityData = new SecurityData();
	}

	@Override
	public void prepare() throws Exception {}

	@Override
	public SecurityData getModel() {
		
		return securityData;
	}
	
	/**
	 * 查询安检数据的接口, 根据电子客票号进行查找
	 * @return
	 */
	public String search(){
		
		JSONObject jsonObject = new JSONObject();
		String flagStr = "";
		
		try {
			SecurityData value = securityDataService.getSecurityByTicketNo(ticketNo);
			jsonObject.put("value", value);
			flagStr = "success";
		} catch (Exception e) {
			
			flagStr = "fail";
			e.printStackTrace();
		}
		
		jsonObject.put(flagStr, "true");
		data = jsonObject.toString();
		return TEXT;
	}
	
	/**
	 * 输入数据
	 */
	public String input(){
		
		return "input";
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
}
