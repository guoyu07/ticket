package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.pojo.Pagination;
import com.ticket.pojo.WifiPush;
import com.ticket.service.IWifiPushService;
import com.ticket.util.GeneralUtil;

public class WifiPushServiceImpl extends BaseServiceImpl<WifiPush, String> implements IWifiPushService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(WifiPushServiceImpl.class);

	@Override
	public Pagination pagingQuery(String mac, String phone) {
		
		StringBuffer where = new StringBuffer("select s from " + WifiPush.class.getName() + " s where 1=1");
		List<String> params = new ArrayList<String>();
		if(GeneralUtil.isNotNull(mac)){
			
			where.append(" and s.mac = ?");
			params.add(mac);
		}
		if(GeneralUtil.isNotNull(phone)){
			
			where.append(" and s.tel = ?");
			params.add(phone);
		}
		if(params.size() > 0){
			
			where.append(" order by s.status.createTime desc");
		}
		return paginationQuery(where.toString(), params.toArray());
	}

}