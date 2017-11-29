package com.ticket.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ticket.constants.ContextConstants;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.ITreeService;

/**
 * 后台模块的辅助工具类
 * @ClassName: ModuleUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名科技
 * @date 2014-10-15 下午05:59:42
 */
public class PageCodeUtil {

	private static final ITreeService treeService = (ITreeService)SpringUtil.getObjectFromSpring("treeServiceImpl");

	/**
	 * 模块页面生成的字符串
	 * 
	 * @param moduleList
	 *            所有一级模块列表
	 * @return 模块生成的表格字符串
	 * @throws Exception 
	 */
	public static <T> String getTableCode(Collection<T> moduleList) throws Exception {
		if (moduleList == null || moduleList.size() < 0) {
			return null;
		} else {
			return getTreeLevelCode(moduleList, 0);
		}
	}

	/**
	 * 模块在页面生成的字符串辅助方法
	 */
	private static <T> String getTreeLevelCode(Collection<T> nodes, int level) throws Exception {
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级模块集合
		Iterator<T> it = nodes.iterator();
		// 循环输出模块
		while (it.hasNext()) {
			// 获得模块对象
			T module = it.next();
			Method getStatus = module.getClass().getDeclaredMethod("getStatus");
			CommonEntity status = (CommonEntity)getStatus.invoke(module);
			if (status.getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			Method getParent_id = module.getClass().getDeclaredMethod("getParent_id");
			String parent_id = (String)getParent_id.invoke(module);
			
			Method getId = module.getClass().getDeclaredMethod("getId");
			String id = (String)getId.invoke(module);
			if (parent_id == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			else if (parent_id != null) {
				if (it.hasNext()) {
					if (!treeService.hasSubByParent(module.getClass(), parent_id)) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!treeService.hasSubByParent(module.getClass(), parent_id)) {
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

			Method getName = module.getClass().getDeclaredMethod("getName");
			String name = (String)getName.invoke(module);
			result.append(name);
			if (parent_id== null) {
				
				Method getIsDefaultShow = module.getClass().getDeclaredMethod("getIsDefaultShow");
				int isDefaultShow = (Integer)getIsDefaultShow.invoke(module);
				if (isDefaultShow == 0) {
					result.append("&nbsp;&nbsp;<a href=\"/systemModule_setDefault.action?id="+id+"\" style=\"color:green;\">设为默认显示</a>");
				} else {
					result.append("&nbsp;&nbsp;<a href=\"/systemModule_setDefault.action?id="+id+"\" style=\"color:red;\">取消默认显示</a>");
				}
			}
			
			Method getUrl = module.getClass().getDeclaredMethod("getUrl");
			String url = (String)getUrl.invoke(module);
			result.append("</td><td align=\"center\">&nbsp;" + url);
			result.append("</td><td align=\"center\" width=\"450\">");
			result.append("<a href=\"/systemModule_addChild.action?parentId="+id+"\">添加子模块</a>"
							+ "&nbsp;|&nbsp;<a href=\"/systemModule_update.action?id="+id+"\">编辑模块</a>"
							+ "&nbsp;|&nbsp;<a onclick=\"if(confirm('是否确认删除该模块?')){return true;}else{return false;}\" href=\"/systemModule_delete.action?id="
							+ id + "\">删除模块</a>");
			result.append("&nbsp;|&nbsp;<a href=\"/systemModule_moveUp.action?id="+id+"\"></a>");
			result.append("&nbsp;|&nbsp;<a href=\"/systemModule_moveDown.action?id="+id+"\"></a>");
			result.append("</td></tr>");
			List<T> childs = treeService.querySubByParent((Class<T>)module.getClass(), parent_id);
			// 如果子模块大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getTreeLevelCode(childs, level + 1));
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
	public static <T> String getSelectOptionHTML(Collection<T> nodes, int level,
			String parentIDChild) throws Exception {
		StringBuffer result = new StringBuffer();
		if(nodes == null) {
			return result.toString();
		}
		Iterator<T> it = nodes.iterator();
		while (it.hasNext()) {
			T module = it.next();
			int index = level;
			String step = "";
			while (index > 0) {
				step += "&nbsp;&nbsp;";
				index--;
			}
			Method getParent_id = module.getClass().getDeclaredMethod("getParent_id");
			String parent_id = (String)getParent_id.invoke(module);
			if (parent_id != null) {
				if (it.hasNext() == false) {
					step += "└&nbsp;";
				} else {
					step += "├&nbsp;";
				}
			}
			Method getId = module.getClass().getDeclaredMethod("getId");
			Method getName = module.getClass().getDeclaredMethod("getName");
			String id = (String)getId.invoke(module);
			String name = (String)getName.invoke(module);
					
			if (id.equals(parentIDChild)) {
				result.append("<option value=\"" + id
						+ "\" selected>" + step + name
						+ "</option>");
			} else {
				result.append("<option value=\"" + id + "\">"
						+ step + name + "</option>");
			}
			if (treeService.hasSubByParent(module.getClass(), parent_id)) {
				result.append(getSelectOptionHTML(treeService.querySubByParent(module.getClass(), parent_id), level + 1,parentIDChild));
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
	public static <T> String getModuleByAction(Collection<T> moduleList, String systemUser_id) throws Exception {
		if (moduleList == null || moduleList.size() < 0) {
			return null;
		} else {
			return getModuleHTMLByAction(moduleList, 0, systemUser_id);
		}
	}

	/**
	 * 模块在页面生成的字符串辅助方法
	 */
	private static <T> String getModuleHTMLByAction(Collection<T> nodes, int level, String systemUser_id) throws Exception{
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级模块集合
		Iterator<T> it = nodes.iterator();
		// 循环输出模块
		while (it.hasNext()) {
			// 获得模块对象
			T module = it.next();
			
			Method getStatus = module.getClass().getDeclaredMethod("getStatus");
			CommonEntity status = (CommonEntity)getStatus.invoke(module);
			if (status.getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			Method getParent_id = module.getClass().getDeclaredMethod("getParent_id");
			String parent_id = (String)getParent_id.invoke(module);
			if (parent_id== null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			
			Method getId = module.getClass().getDeclaredMethod("getId");
			String id = (String)getId.invoke(module);
			// 控制图标显示
			if (parent_id != null) {
				
				if (it.hasNext()) {
					if (!treeService.hasSubByParent(module.getClass(), parent_id)) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!treeService.hasSubByParent(module.getClass(), parent_id)) {
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
			
			Method getName = module.getClass().getDeclaredMethod("getName");
			String name = (String)getName.invoke(module);
			result.append(name);
			result.append("</td><td align=\"center\">");
			result.append("</td><td align=\"center\"><input type='checkbox' tabindex=\""+id+"\" onclick=\"batchLine(this);\"/></td></tr>");

			List<T> childs = treeService.querySubByParent((Class<T>)module.getClass(), parent_id);
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
	public static <T> String recursion(Collection<T> modules) throws Exception {
		if(modules == null || modules.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = modules.iterator();
		
		while (it.hasNext()) {
			T module = it.next();
			
			Method getStatus = module.getClass().getDeclaredMethod("getStatus");
			CommonEntity status = (CommonEntity)getStatus.invoke(module);
			if (status.getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			//id, pid, name, url, title, target, icon, iconOpen, open, myself
			
			Method getParent_id = module.getClass().getDeclaredMethod("getParent_id");
			String parent_id = (String)getParent_id.invoke(module);
			
			Method getId = module.getClass().getDeclaredMethod("getId");
			String id = (String)getId.invoke(module);
			
			StringBuilder sbControl = new StringBuilder();
			sbControl.append("<div class=\"dTreeRight\">");
			sbControl.append("<span class=\"dTree_add\"><a href=\"/systemModule_add.action?parent_id="+ id +"\">添加子模块</a></span>");
			sbControl.append("<span class=\"dTree_editor\"><a href=\"/systemModule_edit.action?id="+ id +"\">编辑模块</a></span>");
			sbControl.append("<span class=\"dTree_delete\"><a href=\"/systemModule_realDelete.action?id="+ id +"\">删除模块</a></span>");
//			if(_parentModule == null) {
//				
//				Method getIsDefaultShow = module.getClass().getDeclaredMethod("getIsDefaultShow");
//				int isDefaultShow = (Integer)getIsDefaultShow.invoke(module);
//				if(isDefaultShow == ContextConstants.STATUS_OF_ONE) {
//					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#2DB626;\" href=\"/systemModule_defaults.action?id="+ id +"\">已设置为默认</a></span>");
//				} else {
//					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#FF0000;\" href=\"/systemModule_defaults.action?id="+ id +"\">设置为默认显示</a></span>");
//				}
//			}
			//sbControl.append("<span class=\"dTree_up\"><a href=\"/systemModule_moveUp.action?id="+ id +"\"></a></span>");
			//sbControl.append("<span class=\"dTree_down\"><a href=\"/systemModule_moveDown.action?id="+ id +"\"></a></span>");
			
//			Method getUrl = module.getClass().getDeclaredMethod("getUrl");
//			String url = (String)getUrl.invoke(module);
//			sbControl.append("<span class=\"dTree_moduleLink\">"+ url +"</span>");
			sbControl.append("</div>");
			
			Method getName = module.getClass().getDeclaredMethod("getName");
			String name = (String)getName.invoke(module);
			sb.append("d.add('"+ id +"', '"+ parent_id +"', '"+ name +"', '#', '', '', '', '', '', '"+ sbControl.toString() +"');");
			 
			List<T> childs = treeService.querySubByParent((Class<T>)module.getClass(), id);
			 if(childs != null && childs.size() > 0) {
				 sb.append(recursion(childs));
			 } 
		}
		 return sb.toString();
	}
}
