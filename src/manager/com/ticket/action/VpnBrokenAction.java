package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.enumer.VpnStatus;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.VpnBroken;
import com.ticket.service.IVpnBrokenService;
import com.ticket.util.GeneralUtil;

/**
 * vpn断线记录控制器
 * @author 涂有  
 */
public class VpnBrokenAction extends BaseAction {

	private static final long serialVersionUID = 3413413241324L;
	
	@Resource
	private IVpnBrokenService vpnBrokenService;
	
	//电瓶车实体表实体
	private VpnBroken vpnBroken;
    //vpn断线状态
	private VpnStatus state;
	//断线处理说明
	private String remark;
	
	/**
	 * 处理断线情况
	 * @return
	 * @throws ServiceException
	 */
	public String edit() {
		if(GeneralUtil.isNull(operationFlag)) {
			return "edit";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(state)) {
				data = AjaxData.responseError("number.required");
				return JSON;
			}
			
			vpnBroken = vpnBrokenService.process(state, remark);
			data = AjaxData.responseSuccess("处理结果保存成功");
			return JSON;
		}
	}
	
	/**
	 * 设置是否打开页面提醒
	 * @return
	 */
	public String set(){
		
		vpnBroken = vpnBrokenService.get();
		if(vpnBroken.isPageNotify()){
			
			vpnBrokenService.setPageNotify(false);
		}else{
			
			vpnBrokenService.setPageNotify(true);
		}
		
		data = AjaxData.responseSuccess("设置成功");
		return JSON;
	}
	
	/**
	 * vpn断线情况的管理页面
	 * @return
	 * @throws ServiceException
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(vpnBrokenService.paginationQuery("select s from " + VpnBroken.class.getName() + " s order by s.time desc"));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manage";
	}
	
	public VpnBroken getVpnBroken() {
		return vpnBroken;
	}

	public void setVpnBroken(VpnBroken vpnBroken) {
		this.vpnBroken = vpnBroken;
	}

	public VpnStatus getState() {
		return state;
	}

	public void setState(VpnStatus state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
