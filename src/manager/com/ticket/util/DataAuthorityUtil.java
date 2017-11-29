package com.ticket.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.PositionDataAuthority;
import com.ticket.pojo.SystemModule;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IPositionDataAuthorityService;
import com.ticket.service.IPositionService;
import com.ticket.service.ISystemModuleService;

/**
 * 销售CRM后台数据权限辅助工具类
 * @author Administrator
 *
 */
public class DataAuthorityUtil {
	private static final ISystemModuleService systemModuleService = (ISystemModuleService)SpringUtil.getObjectFromSpring("systemModuleService");
	private static final IPositionService positionService = (IPositionService)SpringUtil.getObjectFromSpring("positionService");
	private static final IPositionDataAuthorityService positionDataAuthorityService = (IPositionDataAuthorityService)SpringUtil.getObjectFromSpring("positionDataAuthorityService");
	private static final IDataAuthoritysService dataAuthoritysService = (IDataAuthoritysService)SpringUtil.getObjectFromSpring("dataAuthoritysService");
	/**
	 * 递归生成树
	 * @param modules
	 * @return
	 */
	public static String recursion(Collection<SystemModule> modules,String positionId) throws ServiceException {
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
			List<DataAuthoritys> privilegeList = dataAuthoritysService.queryBuSystemModulId(module.getId());
			for (DataAuthoritys privilegeInfo : privilegeList) {
				PositionDataAuthority rp = positionDataAuthorityService.queryByRIDAndMIDAndPID(positionId, currentId, privilegeInfo.getId(), versionFlag);
				if(rp != null){
					sbControl.append("<input type=\"radio\" class=\"grantPrivilege\" positionId=\""+positionId+"\" systemModuleId =\""+currentId+"\" privilegeId=\""+privilegeInfo.getId()+"\" name=\""+module.getId()+"\"/>"+privilegeInfo.getName());
				}else{
					sbControl.append("<input type=\"radio\" positionId=\""+positionId+"\" systemModuleId =\""+currentId+"\" privilegeId=\""+privilegeInfo.getId()+"\" name=\""+module.getId()+"\"/>"+privilegeInfo.getName());
				}
				//sbControl.append("<input type=\"checkbox\" class=\"test\" privilegeId="" systemModuleId="" roleId=""/>"+privilegeInfo.getName());
			}
			sbControl.append("</div>");
			
			sb.append("d.add('"+ currentId +"', '"+ parentId +"', '"+ module.getName() +"', '#', '', '', '', '', '', '"+ sbControl.toString() +"');");
			 
			Collection<SystemModule> childs = systemModuleService.queryChildModulesByParent(module.getId());
			 if(childs != null && childs.size() > 0) {
				 sb.append(recursion(childs,positionId));
			 } 
		}
		 return sb.toString();
	}
}
