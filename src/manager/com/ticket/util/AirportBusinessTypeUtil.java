package com.ticket.util;

import java.util.Collection;
import java.util.Iterator;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportBusinessType;
import com.ticket.service.IAirportBusinessTypeService;

/**
 * 后台类别的辅助工具类
* @ClassName: AirportBusinessTypeUtil
* @Description: TODO(这里用一句话描述这个类的作用)
* @author HiSay 聚名科技
* @date 2014-10-15 下午05:59:42
*
 */
public class AirportBusinessTypeUtil {

	private static final IAirportBusinessTypeService airportBusinessTypeService = (IAirportBusinessTypeService)SpringUtil.getObjectFromSpring("airportBusinessTypeService");
	//private IUserActionsService userActionsService = (IUserActionsService)SpringUtil.getObjectFromSpring("userActionsService");

	/**
	 * 类别页面生成的字符串
	 * 
	 * @param airportBusinessTypeList
	 *            所有一级类别列表
	 * @return 类别生成的表格字符串
	 */
	public static String getAirportBusinessType(Collection<AirportBusinessType> airportBusinessTypeList) throws ServiceException {
		if (airportBusinessTypeList == null || airportBusinessTypeList.size() < 0) {
			return null;
		} else {
			return getAirportBusinessTypeHTML(airportBusinessTypeList, 0);
		}
	}

	/**
	 * 类别在页面生成的字符串辅助方法
	 */
	private static String getAirportBusinessTypeHTML(Collection<AirportBusinessType> nodes, int level) throws ServiceException {
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级类别集合
		Iterator<AirportBusinessType> it = nodes.iterator();
		// 循环输出类别
		while (it.hasNext()) {
			// 获得类别对象
			AirportBusinessType airportBusinessType= it.next();
			if (airportBusinessType.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			if (airportBusinessType.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (airportBusinessType.getParent_id() != null) {
				if (it.hasNext()) {
					if (!airportBusinessTypeService.validateHaveChildAirportBusinessTypes(airportBusinessType.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!airportBusinessTypeService.validateHaveChildAirportBusinessTypes(airportBusinessType.getId())) {
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

			result.append(airportBusinessType.getName());
			if (airportBusinessType.getParent_id() == null) {
				if (airportBusinessType.getIsDefaultShow() == 0) {
					result.append("&nbsp;&nbsp;<a href=\"/airportBusinessType_setDefault.action?id="+airportBusinessType.getId()+"\" style=\"color:green;\">设为默认显示</a>");
				} else {
					result.append("&nbsp;&nbsp;<a href=\"/airportBusinessType_setDefault.action?id="+airportBusinessType.getId()+"\" style=\"color:red;\">取消默认显示</a>");
				}
			}
			//result.append("</td><td align=\"center\">&nbsp;" + airportBusinessType.getUrl());
			result.append("</td><td align=\"center\" width=\"450\">");
			result.append("<a href=\"/airportBusinessType_addChild.action?parentId="+airportBusinessType.getId()+"\">添加子类别</a>"
							+ "&nbsp;|&nbsp;<a href=\"/airportBusinessType_update.action?id="+airportBusinessType.getId()+"\">编辑类别</a>"
							+ "&nbsp;|&nbsp;<a onclick=\"if(confirm('是否确认删除该类别?')){return true;}else{return false;}\" href=\"/airportBusinessType_delete.action?id="
							+ airportBusinessType.getId() + "\">删除类别</a>");
			result.append("&nbsp;|&nbsp;<a href=\"/airportBusinessType_moveUp.action?id="+airportBusinessType.getId()+"\"></a>");
			result.append("&nbsp;|&nbsp;<a href=\"/airportBusinessType_moveDown.action?id="+airportBusinessType.getId()+"\"></a>");
			result.append("</td></tr>");
			Collection<AirportBusinessType> childs = airportBusinessTypeService.queryChildAirportBusinessTypesByParent(airportBusinessType.getId());
			// 如果子类别大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getAirportBusinessTypeHTML(childs, level + 1));
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
	public static String getSelectOptionHTML(Collection<AirportBusinessType> nodes, int level,
			String parentIDChild) throws ServiceException {
		StringBuffer result = new StringBuffer();
		if(nodes == null) {
			return result.toString();
		}
		Iterator<AirportBusinessType> it = nodes.iterator();
		while (it.hasNext()) {
			AirportBusinessType airportBusinessType= it.next();
			int index = level;
			String step = "";
			while (index > 0) {
				step += "&nbsp;&nbsp;";
				index--;
			}
			if (airportBusinessType.getParent_id() != null) {
				if (it.hasNext() == false) {
					step += "└&nbsp;";
				} else {
					step += "├&nbsp;";
				}
			}
			if (airportBusinessType.getId().equals(parentIDChild)) {
				result.append("<option value=\"" + airportBusinessType.getId()
						+ "\" selected>" + step + airportBusinessType.getName()
						+ "</option>");
			} else {
				result.append("<option value=\"" + airportBusinessType.getId() + "\">"
						+ step + airportBusinessType.getName() + "</option>");
			}
			if (airportBusinessTypeService.validateHaveChildAirportBusinessTypes(airportBusinessType.getId())) {
				result.append(getSelectOptionHTML(airportBusinessTypeService.queryChildAirportBusinessTypesByParent(airportBusinessType.getId()), level + 1,parentIDChild));
			}
		}
		return result.toString();
	}

	
	/**
	 * 类别页面生成的字符串
	 * 
	 * @param airportBusinessTypeList
	 *            所有一级类别列表
	 * @return 类别生成的表格字符串
	 */
	public static String getAirportBusinessTypeByAction(Collection<AirportBusinessType> airportBusinessTypeList, String systemUser_id) throws ServiceException {
		if (airportBusinessTypeList == null || airportBusinessTypeList.size() < 0) {
			return null;
		} else {
			return getAirportBusinessTypeHTMLByAction(airportBusinessTypeList, 0, systemUser_id);
		}
	}

	/**
	 * 类别在页面生成的字符串辅助方法
	 */
	private static String getAirportBusinessTypeHTMLByAction(Collection<AirportBusinessType> nodes, int level, String systemUser_id) throws ServiceException{
		// 返回的字符串
		StringBuffer result = new StringBuffer();
		// 一级类别集合
		Iterator<AirportBusinessType> it = nodes.iterator();
		// 循环输出类别
		while (it.hasNext()) {
			// 获得类别对象
			AirportBusinessType airportBusinessType= it.next();
			if (airportBusinessType.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
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
			if (airportBusinessType.getParent_id() == null) {
				result.append("<img src='/manager/images/tree_folder4.gif' width='15' height='15' />");
			}
			// 控制图标显示
			if (airportBusinessType.getParent_id() != null) {
				if (it.hasNext()) {
					if (!airportBusinessTypeService.validateHaveChildAirportBusinessTypes(airportBusinessType.getId())) {
						result.append("<img src='/manager/images/tree_line3.gif' width='17' height='16' />");
					}
				} else {
					if (!airportBusinessTypeService.validateHaveChildAirportBusinessTypes(airportBusinessType.getId())) {
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

			result.append(airportBusinessType.getName());
			result.append("</td><td align=\"center\">");
			result.append("</td><td align=\"center\"><input type='checkbox' tabindex=\""+airportBusinessType.getId()+"\" onclick=\"batchLine(this);\"/></td></tr>");

			Collection<AirportBusinessType> childs = airportBusinessTypeService.queryChildAirportBusinessTypesByParent(airportBusinessType.getId());
			// 如果子类别大于0,则继续递归循环
			if (childs != null && childs.size() > 0) {
				result.append(getAirportBusinessTypeHTMLByAction(childs, level + 1, systemUser_id));
			}
		}
		return result.toString();
	}
	
	/**
	 * 递归生成树
	 * @param airportBusinessTypes
	 * @return
	 */
	public static String recursion(Collection<AirportBusinessType> airportBusinessTypes) throws ServiceException {
		if(airportBusinessTypes == null || airportBusinessTypes.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<AirportBusinessType> it = airportBusinessTypes.iterator();
		
		while (it.hasNext()) {
			AirportBusinessType airportBusinessType= it.next();
			if (airportBusinessType.getStatus().getDeleteFlag().intValue() == ContextConstants.STATUS_OF_ONE) {
				continue;
			}
			//id, pid, name, url, title, target, icon, iconOpen, open, myself
			String currentId = airportBusinessType.getId();
			AirportBusinessType _parentAirportBusinessType = null;
			if(GeneralUtil.isNotNull(airportBusinessType.getParent_id())) {
				_parentAirportBusinessType = airportBusinessTypeService.queryById(AirportBusinessType.class.getSimpleName(), airportBusinessType.getParent_id());
			}
			String parentId = "-1";
			if(_parentAirportBusinessType != null) {
				parentId = _parentAirportBusinessType.getId();
			}
			
			StringBuilder sbControl = new StringBuilder();
			sbControl.append("<div class=\"dTreeRight\">");
			sbControl.append("<span class=\"dTree_add\"><a href=\"/airportBusinessType_add.action?parent_id="+ currentId +"\">添加子类别</a></span>");
			sbControl.append("<span class=\"dTree_editor\"><a href=\"/airportBusinessType_edit.action?id="+ currentId +"\">编辑类别</a></span>");
			sbControl.append("<span class=\"dTree_delete\"><a href=\"/airportBusinessType_realDelete.action?id="+ currentId +"\">删除类别</a></span>");
			if(_parentAirportBusinessType == null) {
				if(airportBusinessType.getIsDefaultShow() == ContextConstants.STATUS_OF_ONE) {
					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#2DB626;\" href=\"/airportBusinessType_defaults.action?id="+ currentId +"\">已设置为默认</a></span>");
				} else {
					sbControl.append("<span class=\"dTree_delete\"><a style=\"color:#FF0000;\" href=\"/airportBusinessType_defaults.action?id="+ currentId +"\">设置为默认显示</a></span>");
				}
			}
			//sbControl.append("<span class=\"dTree_up\"><a href=\"/airportBusinessType_moveUp.action?id="+ currentId +"\"></a></span>");
			//sbControl.append("<span class=\"dTree_down\"><a href=\"/airportBusinessType_moveDown.action?id="+ currentId +"\"></a></span>");
			//sbControl.append("<span class=\"dTree_airportBusinessTypeLink\">"+ airportBusinessType.getUrl() +"</span>");
			sbControl.append("</div>");
			
			sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ airportBusinessType.getName() +"', '#', '', '', '', '', '', '"+ sbControl.toString() +"');");
			 
			Collection<AirportBusinessType> childs = airportBusinessTypeService.queryChildAirportBusinessTypesByParent(airportBusinessType.getId());
			 if(childs != null && childs.size() > 0) {
				 sb.append(recursion(childs));
			 } 
		}
		 return sb.toString();
	}
}
