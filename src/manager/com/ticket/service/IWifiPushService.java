package com.ticket.service;

import com.ticket.pojo.Pagination;
import com.ticket.pojo.WifiPush;

/**
 * wifi推送数据
 * @author 涂有
 */
public interface IWifiPushService extends IBaseService<WifiPush, String> {
	
	/**
	 * 分页查询
	 * @return
	 */
	Pagination pagingQuery(String mac, String phone);
}