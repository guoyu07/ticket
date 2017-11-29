package com.ticket.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.PrivilegeInfo;
import com.ticket.pojo.RolePrivilege;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.SystemUser;
import com.ticket.pojo.UserRole;
import com.ticket.service.IPrivilegeInfoService;
import com.ticket.service.IRoleInfoService;
import com.ticket.service.IRolePrivilegeService;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.IUserRoleService;

/**
 * 后台模块的辅助工具类
* @ClassName: ModuleUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author HiSay 聚名科技
* @date 2014-10-15 下午05:59:42
*
 */
public class PrivilegeUtil {
	private static final ISystemModuleService systemModuleService = (ISystemModuleService)SpringUtil.getObjectFromSpring("systemModuleService");
	//private IUserActionsService userActionsService = (IUserActionsService)SpringUtil.getObjectFromSpring("userActionsService");
	private static final IPrivilegeInfoService privilegeInfoService = (IPrivilegeInfoService)SpringUtil.getObjectFromSpring("privilegeInfoService");
	private static final IRolePrivilegeService rolePrivilegeService = (IRolePrivilegeService) SpringUtil.getObjectFromSpring("rolePrivilegeService");
	private static final IRoleInfoService roleInfoService = (IRoleInfoService)SpringUtil.getObjectFromSpring("roleInfoService");
	private static final IUserRoleService userRoleService = (IUserRoleService)SpringUtil.getObjectFromSpring("userRoleService");
	/**
	 * 模块页面生成的字符串
	 * 
	 * @param moduleList
	 *            所有一级模块列表
	 * @return 模块生成的表格字符串
	 */
	public static String getModule(Collection<SystemModule> moduleList) throws ServiceException {
		if (moduleList == null || moduleList.size() < 0) {
			return null;
		} else {
			return getModuleHTML(moduleList, 0);
		}
	}

	/**
	 * 模块在页面生成的字符串辅助方法
	 */
	private static String getModuleHTML(Collection<SystemModule> nodes, int level) throws ServiceException {
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级模块集合
		Iterator<SystemModule> it = nodes.iterator();
		// 循环输出模块
		while (it.hasNext()) {
			// 获得模块对象
			SystemModule module = it.next();
			if (module.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			// 控制字符缩进
			int index = level;
			result.append("<tr height=\"40\" bgcolor=\"#FFFFFF\" onMouseOver=\"this.style.backgroundColor='#BFDFFF'\"  onmouseout=\"this.style.backgroundColor=''\">");
			result.append("<td align=\"left\">");
			// 如果缩进变量大于1则打印一个空格
			while (index > 0) {
				index--;
			}
			// 控制图标显示
			if (module.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (module.getParent_id() != null) {
				if (it.hasNext()) {
					if (!systemModuleService.validateHaveChildModules(module.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!systemModuleService.validateHaveChildModules(module.getId())) {
						result.append("<img src='/manager/images/tree_line2.gif' width='17' height='16' />");
					}
				}
				if (it.hasNext() == false) {
					result.append("<img src='/manager/images/tree_line2.gif' width='17' height='16' /><img src='/manager/images/tree_folder3.gif' width='15' height='15' />");
				} else {
					// 原来
					result.append("<img src='/manager/images/tree_line1.gif' width='17' height='16' /><img src='/manager/images/tree_folder3.gif' width='15' height='15' />");
				}
			}

			result.append(module.getName());
			if (module.getParent_id() == null) {
				if (module.getIsDefaultShow() == 0) {
					result.append("&nbsp;&nbsp;<a href=\"/systemModule_setDefault.action?id="+module.getId()+"\" style=\"color:green;\">设为默认显示</a>");
				} else {
					result.append("&nbsp;&nbsp;<a href=\"/systemModule_setDefault.action?id="+module.getId()+"\" style=\"color:red;\">取消默认显示</a>");
				}
			}
			result.append("</td><td align=\"center\">&nbsp;" + module.getUrl());
			result.append("</td><td align=\"center\" width=\"450\">");
			result.append("<a href=\"/systemModule_addChild.action?parentId="+module.getId()+"\">添加子模块</a>"
							+ "&nbsp;|&nbsp;<a href=\"/systemModule_update.action?id="+module.getId()+"\">编辑模块</a>"
							+ "&nbsp;|&nbsp;<a onclick=\"if(confirm('是否确认删除该模块?')){return true;}else{return false;}\" href=\"/systemModule_delete.action?id="
							+ module.getId() + "\">删除模块</a>");
			result.append("&nbsp;|&nbsp;<a href=\"/systemModule_moveUp.action?id="+module.getId()+"\"></a>");
			result.append("&nbsp;|&nbsp;<a href=\"/systemModule_moveDown.action?id="+module.getId()+"\"></a>");
			result.append("</td></tr>");
			Collection<SystemModule> childs = systemModuleService.queryChildModulesByParent(module.getId());
			// 如果子模块大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getModuleHTML(childs, level + 1));
			}
		}
		  return result.toString();
	}

	/**
	 * 模块下拉菜单的实现
	 * 
	 * @param nodes
	 *            一级模块集合
	 * @param level
	 *            0
	 * @param flag
	 *            版本标识
	 * @return 模块下拉菜单的字符串
	 */
	public static String getSelectOptionHTML(Collection<SystemModule> nodes, int level,
			String parentIDChild) throws ServiceException {
		StringBuffer result = new StringBuffer();
		if(nodes == null) {
			return result.toString();
		}
		Iterator<SystemModule> it = nodes.iterator();
		while (it.hasNext()) {
			SystemModule module = it.next();
			int index = level;
			String step = "";
			while (index > 0) {
				step += "&nbsp;&nbsp;";
				index--;
			}
			if (module.getParent_id() != null) {
				if (it.hasNext() == false) {
					step += "└&nbsp;";
				} else {
					step += "├&nbsp;";
				}
			}
			if (module.getId().equals(parentIDChild)) {
				result.append("<option value=\"" + module.getId()
						+ "\" selected>" + step + module.getName()
						+ "</option>");
			} else {
				result.append("<option value=\"" + module.getId() + "\">"
						+ step + module.getName() + "</option>");
			}
			if (systemModuleService.validateHaveChildModules(module.getId())) {
				result.append(getSelectOptionHTML(systemModuleService.queryChildModulesByParent(module.getId()), level + 1,parentIDChild));
			}
		}
		return result.toString();
	}

	
	/**
	 * 模块页面生成的字符串
	 * 
	 * @param moduleList
	 *            所有一级模块列表
	 * @return 模块生成的表格字符串
	 */
	public static String getModuleByAction(Collection<SystemModule> moduleList, String systemUser_id) throws ServiceException {
		if (moduleList == null || moduleList.size() < 0) {
			return null;
		} else {
			return getModuleHTMLByAction(moduleList, 0, systemUser_id);
		}
	}

	/**
	 * 模块在页面生成的字符串辅助方法
	 */
	private static String getModuleHTMLByAction(Collection<SystemModule> nodes, int level, String systemUser_id) throws ServiceException{
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级模块集合
		Iterator<SystemModule> it = nodes.iterator();
		// 循环输出模块
		while (it.hasNext()) {
			// 获得模块对象
			SystemModule module = it.next();
			if (module.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			// 控制字符缩进
			int index = level;
			result
					.append("<tr height=\"40\" bgcolor=\"#FFFFFF\" onMouseOver=\"this.style.backgroundColor='#BFDFFF'\"  onmouseout=\"this.style.backgroundColor=''\">");
			result.append("<td align=\"left\">");
			// 如果缩进变量大于1则打印一个空格
			while (index > 0) {
				result.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				index--;
			}
			// 控制图标显示
			if (module.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (module.getParent_id() != null) {
				if (it.hasNext()) {
					if (!systemModuleService.validateHaveChildModules(module.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!systemModuleService.validateHaveChildModules(module.getId())) {
						result.append("<img src='/manager/images/tree_line2.gif' width='17' height='16' />");
					}
				}
				if (it.hasNext() == false) {
					result.append("<img src='/manager/images/tree_line2.gif' width='17' height='16' /><img src='/manager/images/tree_folder3.gif' width='15' height='15' />");
				} else {
					// 原来
					result.append("<img src='/manager/images/tree_line1.gif' width='17' height='16' /><img src='/manager/images/tree_folder3.gif' width='15' height='15' />");
				}
			}

			result.append(module.getName());
			result.append("</td><td align=\"center\">");
			result.append("</td><td align=\"center\"><input type='checkbox' tabindex=\""+module.getId()+"\" onclick=\"batchLine(this);\"/></td></tr>");

			Collection<SystemModule> childs = systemModuleService.queryChildModulesByParent(module.getId());
			// 如果子模块大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getModuleHTMLByAction(childs, level + 1, systemUser_id));
			}
		}
		return result.toString();
	}
	
	/**
	 * 递归生成树
	 * @param modules
	 * @return
	 */
	public static String recursion(Collection<SystemModule> modules,String roleId) throws ServiceException {
		String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE;
		if(modules == null || modules.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<SystemModule> it = modules.iterator();
		
		while (it.hasNext()) {
			SystemModule module = it.next();
			if (module.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			//id, pid, name, url, title, target, icon, iconOpen, open, myself
			String currentId = module.getId();
			SystemModule _parentModule = null;
			if(GeneralUtil.isNotNull(module.getParent_id())) {
				_parentModule = systemModuleService.queryById(SystemModule.class.getSimpleName(), module.getParent_id());
			}
			String parentId = "-1";
			if(_parentModule != null) {
				parentId = _parentModule.getId();
			}
			
			StringBuilder sbControl = new StringBuilder();
			sbControl.append("<div class=\"dTreeRight\">");
			List<PrivilegeInfo> privilegeList = privilegeInfoService.queryBuSystemModulId(module.getId());
			for (PrivilegeInfo privilegeInfo : privilegeList) {
				RolePrivilege rp = rolePrivilegeService.queryByRIDAndMIDAndPID(roleId, currentId, privilegeInfo.getId(), versionFlag);
				if(rp != null){
					sbControl.append("<input type=\"checkbox\" class=\"grantPrivilege\" roleId=\""+roleId+"\" systemModuleId =\""+currentId+"\" privilegeId=\""+privilegeInfo.getId()+"\" name=\""+privilegeInfo.getName()+module.getId()+"\"/>"+privilegeInfo.getName());
				}else{
					sbControl.append("<input type=\"checkbox\" roleId=\""+roleId+"\" systemModuleId =\""+currentId+"\" privilegeId=\""+privilegeInfo.getId()+"\" name=\""+privilegeInfo.getName()+module.getId()+"\"/>"+privilegeInfo.getName());
				}
				//sbControl.append("<input type=\"checkbox\" class=\"test\" privilegeId="" systemModuleId="" roleId=""/>"+privilegeInfo.getName());
			}
			sbControl.append("</div>");
			
			sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ module.getName() +"', '#', '', '', '', '', '', '"+ sbControl.toString() +"');");
			 
			Collection<SystemModule> childs = systemModuleService.queryChildModulesByParent(module.getId());
			 if(childs != null && childs.size() > 0) {
				 sb.append(recursion(childs,roleId));
			 } 
		}
		 return sb.toString();
	}
	/**
	 * 判断登录用户是否有该操作权限
	 * @return
	 * @throws ServiceException 
	 */
	public static boolean checkPrivilege(String includeMethods) throws ServiceException{
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		List<UserRole> userRoles = userRoleService.findByUserId(user.getId());
		boolean yeah = false;
		if(ContextConstants.ADMIN.equals(user.getLoginId())){//如果登录的是管理员
			yeah = true;
		}else{
			if(userRoles != null){
				for(UserRole userRole:userRoles){
					List<RolePrivilege> rolePrivileges = rolePrivilegeService.queryByRoleId(userRole.getRoleId());
					if(rolePrivileges != null && yeah == false){
						for(RolePrivilege rolePrivilege:rolePrivileges){
							PrivilegeInfo privilegeInfo = privilegeInfoService.queryById(PrivilegeInfo.class.getName(), rolePrivilege.getPrivilegeId());
							if(includeMethods.equals(privilegeInfo.getIncludeMethods())){
								yeah = true;
								break;
							}
						}
					}
				}
			}
		}
		return yeah;
	}
}
