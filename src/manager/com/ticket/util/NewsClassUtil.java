package com.ticket.util;

import java.util.Collection;
import java.util.Iterator;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsClass;
import com.ticket.service.INewsClassService;

/**
 * 后台类别的辅助工具类
* @ClassName: NewsClassUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author HiSay 聚名科技
* @date 2014-10-15 下午05:59:42
*
 */
public class NewsClassUtil {

	private static final INewsClassService newsClassService = (INewsClassService)SpringUtil.getObjectFromSpring("newsClassService");
	//private IUserActionsService userActionsService = (IUserActionsService)SpringUtil.getObjectFromSpring("userActionsService");

	/**
	 * 类别页面生成的字符串
	 * 
	 * @param newsClassList
	 *            所有一级类别列表
	 * @return 类别生成的表格字符串
	 */
	public static String getNewsClass(Collection<NewsClass> newsClassList) throws ServiceException {
		if (newsClassList == null || newsClassList.size() < 0) {
			return null;
		} else {
			return getNewsClassHTML(newsClassList, 0);
		}
	}

	/**
	 * 类别在页面生成的字符串辅助方法
	 */
	private static String getNewsClassHTML(Collection<NewsClass> nodes, int level) throws ServiceException {
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级类别集合
		Iterator<NewsClass> it = nodes.iterator();
		// 循环输出类别
		while (it.hasNext()) {
			// 获得类别对象
			NewsClass newsClass= it.next();
			if (newsClass.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			if (newsClass.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (newsClass.getParent_id() != null) {
				if (it.hasNext()) {
					if (!newsClassService.validateHaveChildNewsClasss(newsClass.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!newsClassService.validateHaveChildNewsClasss(newsClass.getId())) {
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

			result.append(newsClass.getName());
			if (newsClass.getParent_id() == null) {
				if (newsClass.getIsDefaultShow() == 0) {
					result.append("&nbsp;&nbsp;<a href=\"/newsClass_setDefault.action?id="+newsClass.getId()+"\" style=\"color:green;\">设为默认显示</a>");
				} else {
					result.append("&nbsp;&nbsp;<a href=\"/newsClass_setDefault.action?id="+newsClass.getId()+"\" style=\"color:red;\">取消默认显示</a>");
				}
			}
			//result.append("</td><td align=\"center\">&nbsp;" + newsClass.getUrl());
			result.append("</td><td align=\"center\" width=\"450\">");
			result.append("<a href=\"/newsClass_addChild.action?parentId="+newsClass.getId()+"\">添加子类别</a>"
							+ "&nbsp;|&nbsp;<a href=\"/newsClass_update.action?id="+newsClass.getId()+"\">编辑类别</a>"
							+ "&nbsp;|&nbsp;<a onclick=\"if(confirm('是否确认删除该类别?')){return true;}else{return false;}\" href=\"/newsClass_delete.action?id="
							+ newsClass.getId() + "\">删除类别</a>");
			result.append("&nbsp;|&nbsp;<a href=\"/newsClass_moveUp.action?id="+newsClass.getId()+"\"></a>");
			result.append("&nbsp;|&nbsp;<a href=\"/newsClass_moveDown.action?id="+newsClass.getId()+"\"></a>");
			result.append("</td></tr>");
			Collection<NewsClass> childs = newsClassService.queryChildNewsClasssByParent(newsClass.getId());
			// 如果子类别大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getNewsClassHTML(childs, level + 1));
			}
		}
		  return result.toString();
	}

	/**
	 * 类别下拉菜单的实现
	 * 
	 * @param nodes
	 *            一级类别集合
	 * @param level
	 *            0
	 * @param flag
	 *            版本标识
	 * @return 类别下拉菜单的字符串
	 */
	public static String getSelectOptionHTML(Collection<NewsClass> nodes, int level,
			String parentIDChild) throws ServiceException {
		StringBuffer result = new StringBuffer();
		if(nodes == null) {
			return result.toString();
		}
		Iterator<NewsClass> it = nodes.iterator();
		while (it.hasNext()) {
			NewsClass newsClass= it.next();
			int index = level;
			String step = "";
			while (index > 0) {
				step += "&nbsp;&nbsp;";
				index--;
			}
			if (newsClass.getParent_id() != null) {
				if (it.hasNext() == false) {
					step += "└&nbsp;";
				} else {
					step += "├&nbsp;";
				}
			}
			if (newsClass.getId().equals(parentIDChild)) {
				result.append("<option value=\"" + newsClass.getId()
						+ "\" selected>" + step + newsClass.getName()
						+ "</option>");
			} else {
				result.append("<option value=\"" + newsClass.getId() + "\">"
						+ step + newsClass.getName() + "</option>");
			}
			if (newsClassService.validateHaveChildNewsClasss(newsClass.getId())) {
				result.append(getSelectOptionHTML(newsClassService.queryChildNewsClasssByParent(newsClass.getId()), level + 1,parentIDChild));
			}
		}
		return result.toString();
	}

	
	/**
	 * 类别页面生成的字符串
	 * 
	 * @param newsClassList
	 *            所有一级类别列表
	 * @return 类别生成的表格字符串
	 */
	public static String getNewsClassByAction(Collection<NewsClass> newsClassList, String systemUser_id) throws ServiceException {
		if (newsClassList == null || newsClassList.size() < 0) {
			return null;
		} else {
			return getNewsClassHTMLByAction(newsClassList, 0, systemUser_id);
		}
	}

	/**
	 * 类别在页面生成的字符串辅助方法
	 */
	private static String getNewsClassHTMLByAction(Collection<NewsClass> nodes, int level, String systemUser_id) throws ServiceException{
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级类别集合
		Iterator<NewsClass> it = nodes.iterator();
		// 循环输出类别
		while (it.hasNext()) {
			// 获得类别对象
			NewsClass newsClass= it.next();
			if (newsClass.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			if (newsClass.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (newsClass.getParent_id() != null) {
				if (it.hasNext()) {
					if (!newsClassService.validateHaveChildNewsClasss(newsClass.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!newsClassService.validateHaveChildNewsClasss(newsClass.getId())) {
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

			result.append(newsClass.getName());
			result.append("</td><td align=\"center\">");
			result.append("</td><td align=\"center\"><input type='checkbox' tabindex=\""+newsClass.getId()+"\" onclick=\"batchLine(this);\"/></td></tr>");

			Collection<NewsClass> childs = newsClassService.queryChildNewsClasssByParent(newsClass.getId());
			// 如果子类别大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getNewsClassHTMLByAction(childs, level + 1, systemUser_id));
			}
		}
		return result.toString();
	}
	
	/**
	 * 递归生成树
	 * @param newsClasss
	 * @return
	 */
	public static String recursion(Collection<NewsClass> newsClasss) throws ServiceException {
		if(newsClasss == null || newsClasss.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<NewsClass> it = newsClasss.iterator();
		
		while (it.hasNext()) {
			NewsClass newsClass= it.next();
			if (newsClass.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			//id, pid, name, url, title, target, icon, iconOpen, open, myself
			String currentId = newsClass.getId();
			NewsClass _parentNewsClass = null;
			if(GeneralUtil.isNotNull(newsClass.getParent_id())) {
				_parentNewsClass = newsClassService.queryById(NewsClass.class.getSimpleName(), newsClass.getParent_id());
			}
			String parentId = "-1";
			if(_parentNewsClass != null) {
				parentId = _parentNewsClass.getId();
			}
			
			StringBuilder sbControl = new StringBuilder();
			sbControl.append("<div class=\"dTreeRight\">");
			sbControl.append("<span class=\"dTree_add\"><a href=\"/newsClass_add.action?parent_id="+ currentId +"\">添加子类别</a></span>");
			sbControl.append("<span class=\"dTree_editor\"><a href=\"/newsClass_edit.action?id="+ currentId +"\">编辑类别</a></span>");
			sbControl.append("<span class=\"dTree_delete\"><a href=\"/newsClass_realDelete.action?id="+ currentId +"\">删除类别</a></span>");
			if(_parentNewsClass == null) {
				if(newsClass.getIsDefaultShow() == ContextConstants.STATUS_OF_ONE) {
					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#2DB626;\" href=\"/newsClass_defaults.action?id="+ currentId +"\">已设置为默认</a></span>");
				} else {
					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#FF0000;\" href=\"/newsClass_defaults.action?id="+ currentId +"\">设置为默认显示</a></span>");
				}
			}
			//sbControl.append("<span class=\"dTree_up\"><a href=\"/newsClass_moveUp.action?id="+ currentId +"\"></a></span>");
			//sbControl.append("<span class=\"dTree_down\"><a href=\"/newsClass_moveDown.action?id="+ currentId +"\"></a></span>");
			//sbControl.append("<span class=\"dTree_newsClassLink\">"+ newsClass.getUrl() +"</span>");
			sbControl.append("</div>");
			
			sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ newsClass.getName() +"', '#', '', '', '', '', '', '"+ sbControl.toString() +"');");
			 
			Collection<NewsClass> childs = newsClassService.queryChildNewsClasssByParent(newsClass.getId());
			 if(childs != null && childs.size() > 0) {
				 sb.append(recursion(childs));
			 } 
		}
		 return sb.toString();
	}
}
