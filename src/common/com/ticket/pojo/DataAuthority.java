package com.ticket.pojo;

/**
 * 数据权限公共接口，实现此接口的实体类表示此表的数据要实现有权限控制的访问
 * @author tuyou
 */
public interface DataAuthority {

	/**
	 * 得到拥有此权限的角色
	 * @return
	 */
	String getRole();
	
	/**
	 * 设置拥有此数据权限的角色
	 * @param role
	 */
	void setRole(String role);
	
	/**
	 * 返回是否访问权限
	 */
	void hasAuthority();
}
