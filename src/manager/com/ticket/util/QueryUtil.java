package com.ticket.util;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.SystemUser;

/**
 * 根据登录者获得其权限，根据权限获得相应的数据权限
 * @author Administrator
 *
 */
public class QueryUtil {
	
	
	
	/**
	 * 获取后台的登录实体类型
	 * 可能是系统管理员systemUser,或者是机场员工airportEmployee
	 * @return
	 */
	public static SystemUser getEntity(){
		//获取后台登录session中的实体
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		return user;
	}
}
