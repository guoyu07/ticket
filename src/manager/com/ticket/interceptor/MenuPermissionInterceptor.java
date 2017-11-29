package com.ticket.interceptor;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.dao.IDbDAO;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.SystemUser;
import com.ticket.util.QueryUtil;

/**
 * @Description：进行菜单检查的拦截器
 * @author：涂有
 * @date 2015年10月20日 下午11:18:39
 */
public class MenuPermissionInterceptor<T, ID extends Serializable> extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 通用的DAO层
	 */
	@Resource public IDbDAO<T, ID> dbDAO = null;
	
	/**
	 * @Description：拦截请求，验证当前用户是否有访问指定action和method的权限
	 * @param：@param arg0
	 * @param：@return
	 * @param：@throws Exception
	 * @throws
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		//获取要访问的是哪个action和method
		String urlName = arg0.getInvocationContext().getName();
		
		//先确定访问的action是否为菜单url
		List<PrivilegeInfo> privilegeObjs = dbDAO.executeJPQLForQuery("select p from SystemModule p where p.url like ?", 
				PrivilegeInfo.class, "%" + urlName + ".action%");
		//其他没有配置就不作验证
		if(privilegeObjs.size() == 0){
			
			return arg0.invoke();
		}
		
		SystemUser user = QueryUtil.getEntity();
		
		//如果是管理员，则拥有默认拥有全部权限
		if(ContextConstants.ADMIN.equals(user.getLoginId())){
			
			return arg0.invoke();
		}
		
		//不是管理员用户，就根据权限检查当前用户是否可以访问指定的菜单url
		long count = (Long)dbDAO.executeJPQLForQuerySingle("select count(*)"
				+ " from SystemUser u,UserRole ur,RoleInfo r,RolePrivilege rp,PrivilegeInfo p,SystemModule m"
				+ " where u.id = ur.userId"
				+ " and ur.roleId = r.id"
				+ " and r.id = rp.roleId"
				+ " and rp.privilegeId = p.id"
				+ " and rp.systemModuleId = m.id"
				+ " and u.id = ?"
				+ " and m.url like ?", user.getId(), "%"+urlName+"%");
		if(count > 0){
			
			return arg0.invoke();
		}
		ActionContext.getContext().put("text", "菜单");
		return Action.ERROR;
	}
}
