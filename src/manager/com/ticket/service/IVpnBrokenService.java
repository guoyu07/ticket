package com.ticket.service;

import com.ticket.enumer.VpnStatus;
import com.ticket.pojo.VpnBroken;

/**
 * vpn断线业务处理
 * @author tuyou
 */
public abstract interface IVpnBrokenService extends IBaseService<VpnBroken, String>{
	
	/**
	 * 得到断线未处理的一条
	 * @return
	 */
	VpnBroken get();
	
	/**
	 * 保存一次修改记录
	 * @return
	 */
	VpnBroken tryAdd();
	
	/**
	 * 处理断线问题
	 * @param id
	 * @param state
	 * @param remark
	 * @return
	 */
	VpnBroken process(VpnStatus state, String remark);
	
	/**
	 * 设置网页提醒是否开启
	 * @param pageNotify
	 * @return
	 */
	VpnBroken setPageNotify(boolean pageNotify);
	
	/**
	 * 发送通知
	 * @param vpnBroken
	 * @return
	 */
	void sendNotify(VpnBroken vpnBroken);
}