package com.ticket.serviceImpl;


import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ISystemModuleService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ModuleUtil;

/**
 * 系统模块业务接口实现类
 * @ClassName: ISystemModuleService   
 * @Description: 提供系统模块操作的增删改查   
 * @author HiSay  
 * @date 2014-10-15 13:49:51
 *
 */
public class SystemModuleServiceImpl extends BaseServiceImpl<SystemModule, String> implements ISystemModuleService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SystemModuleServiceImpl.class);
	
	@Override
	public boolean merge(String id, String name,String url,String parent_id,String icon, String versionFlag, Integer orderValue) throws ServiceException {
		SystemModule systemModule = dbDAO.queryById(this.getTableNameFromEntity(SystemModule.class), id);
		systemModule.setName(DecoderUtil.UtfDecoder(name));
		systemModule.setUrl(DecoderUtil.UtfDecoder(url));
		if("0".equals(parent_id)) {
			parent_id = null;
		}
		systemModule.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		systemModule.setStatus(status);
		dbDAO.merge(systemModule);
		return true;
	}

	@Override
	public boolean persist(String name,String url,String parent_id,String icon, String versionFlag, Integer orderValue) throws ServiceException {
		SystemModule systemModule = new SystemModule();
		systemModule.setName(DecoderUtil.UtfDecoder(name));
		systemModule.setUrl(DecoderUtil.UtfDecoder(url));
		if("0".equals(parent_id)) {
			parent_id = null;
		}
		systemModule.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		systemModule.setStatus(status);
		dbDAO.persist(systemModule);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		batchRealDelete(SystemModule.class, id);
		return true;
	}

	@Override
	public String getModuleHtml(String versionFlag) throws ServiceException {
		Collection<SystemModule> moduleList = this.queryFirstModuleList(versionFlag);
		String moduleHtml = null;
		if (moduleList != null) {
			moduleHtml = ModuleUtil.getModule(moduleList);
		}
		if (moduleHtml != null) {
			return moduleHtml;
		}
		return null;
	}
	
	@Override
	public String getModuleHtmlByAction(String versionFlag, String systemUser_id) throws ServiceException {
		Collection<SystemModule> moduleList = this.queryFirstModuleList(versionFlag);
		String moduleHtml = null;
		if (moduleList != null) {
			moduleHtml = ModuleUtil.getModuleByAction(moduleList, systemUser_id);
		}
		if (moduleHtml != null) {
			return moduleHtml;
		}
		return null;
	}
	
	@Override
	public boolean setModuleIsDefaultShow(String entityName, String id) throws ServiceException {
		//先取消之前所有的默认
		dbDAO.executeSQL("update " + SystemModule.class.getSimpleName() + " s set s.isDefaultShow=?1", new Object[]{ContextConstants.STATUS_OF_ZERO});
		
		SystemModule module = this.queryById(entityName, id);
		if(module.getIsDefaultShow().intValue() == ContextConstants.STATUS_OF_ZERO) {
			module.setIsDefaultShow(ContextConstants.STATUS_OF_ONE);
		} else {
			module.setIsDefaultShow(ContextConstants.STATUS_OF_ZERO);
		}
		this.merge(module);
		return true;
	}

	@Override
	public List<SystemModule> queryFirstModuleList(String versionFlag)
			throws ServiceException {
		List<SystemModule> firstModuleList = dbDAO.queryByList(this.getTableNameFromEntity(SystemModule.class), deleteFlag, versionFlag, "and s.parent_id is null", null, "s.status.orderValue desc, s.id asc", null);
		if(firstModuleList != null && !firstModuleList.isEmpty()) {
			return firstModuleList;
		} else {
			return null;
		}
	}
	
	@Override
	public List<SystemModule> queryFirstModuleList(SystemUser systemUser, String versionFlag)
			throws ServiceException {
		//获取系统里面的一级模块列表
		List<SystemModule> modules = null;
		if(ContextConstants.ADMIN.equals(systemUser.getLoginId())){
			
			modules = dbDAO.queryByList(this.getTableNameFromEntity(SystemModule.class), deleteFlag, versionFlag, "and s.parent_id is null", null, "s.status.orderValue", null);
		}else{
			
			modules = dbDAO.executeJPQLForQueryEntity("select distinct m"
					+ " from SystemUser u, UserRole ur, RoleInfo r, RolePrivilege rp, PrivilegeInfo p, SystemModule m"
					+ " where u.id = ur.userId and ur.roleId = r.id"
					+ " and r.id = rp.roleId"
					+ " and rp.privilegeId = p.id"
					+ " and rp.systemModuleId = m.id"
					+ " and m.parent_id is null"
					+ " and u.id = ? order by m.status.orderValue", systemUser.getId());
		}
		if(modules != null && !modules.isEmpty()) {
			return modules;
		}
		return null;
	}

	@Override
	public String queryModuleSelectOptionHtml(String parent_id, String versionFlag)
			throws ServiceException {
		List<SystemModule> firstModuleList = this.queryFirstModuleList(versionFlag);
		String moduleHtml = null;
		if (firstModuleList != null && !firstModuleList.isEmpty()) {
			moduleHtml = ModuleUtil.getSelectOptionHTML(firstModuleList, 0, parent_id);
		}
		if (GeneralUtil.isNotNull(moduleHtml)) {
			return moduleHtml;
		}
		return null;
	}

	@Override
	public List<SystemModule> queryChildModulesByParent(String parent_id)
			throws ServiceException {
		return dbDAO.queryByList(this.getTableNameFromEntity(SystemModule.class), deleteFlag, versionFlag, "and s.parent_id=?3", new Object[]{parent_id}, "s.status.orderValue", null);
	}
	
	@Override
	public List<SystemModule> queryChildModulesByParent(String userId, String parent_id)
			throws ServiceException {
		
		List<SystemModule> list = dbDAO.executeJPQLForQueryEntity("select distinct sm"
				+ " from SystemUser u, UserRole ur, RoleInfo r, RolePrivilege rp, PrivilegeInfo p, SystemModule sm"
				+ " where u.id = ur.userId and ur.roleId = r.id"
				+ " and r.id = rp.roleId"
				+ " and rp.privilegeId = p.id"
				+ " and rp.systemModuleId = sm.id"
				+ " and u.id = ?"
				+ " and sm.parent_id = ? order by sm.status.orderValue", userId, parent_id);
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void queryModuleLeftTreeJson(List<SystemModule> leftModuleList,
			String versionFlag) throws ServiceException {
		if (leftModuleList != null && leftModuleList.size() > 0) {
			JSONObject parentJson = new JSONObject();
			JSONArray parentJsonArray = new JSONArray();
			int i = 0;
			for(SystemModule module : leftModuleList){
				i++;
				JSONObject json = new JSONObject();	
				json.put("menuid",i); 
				json.put("icon", "icon-sys"); 
				if(module.getIcon()!=null && !module.getIcon().equals("") ){
					json.put("icon", module.getIcon());
				}else{
					json.put("icon", "icon-nav"); 
				}
				json.put("menuname", module.getName());
				JSONArray jsonArray = new JSONArray();
				List<SystemModule> list = this.getValidateChildModules(getSystemUser(), versionFlag.toString(), module.getId());
				if(list!=null && list.size()>0){
					for(SystemModule childModule : list){
						JSONObject childJson = new JSONObject();	
						childJson.put("menuid",childModule.getId()); 
						childJson.put("menuname", childModule.getName());
						if(childModule.getIcon()!=null && !childModule.getIcon().equals("") ){
							childJson.put("icon", childModule.getIcon());
						} else {
							childJson.put("icon", "icon-nav"); 
						}
						if(this.validateHaveChildModules(childModule.getId())) { 
							JSONArray json2Array = new JSONArray();
							List<SystemModule> childModuleList = this.queryChildModulesByParent(childModule.getId());
							for(SystemModule child2Module : childModuleList) {
								JSONObject child2Json = new JSONObject();
								childJson.put("menuid",child2Module.getId());
								child2Json.put("menuname", child2Module.getName());
								if(child2Module.getIcon()!=null && !child2Module.getIcon().equals("") ){
									child2Json.put("icon", child2Module.getIcon());
								}else{
									child2Json.put("icon", "icon-nav"); 
								}
								if(child2Module.getUrl().indexOf("?") == -1) {
									child2Json.put("url", child2Module.getUrl()+"?moduleId="+child2Module.getId());
								} else {
									child2Json.put("url", child2Module.getUrl()+"&moduleId="+child2Module.getId());
								}
								json2Array.add(child2Json);
							}
							childJson.put("child", json2Array);
						} else {
							if(childModule.getUrl().indexOf("?") == -1) {
								childJson.put("url", childModule.getUrl()+"?moduleId="+childModule.getId());
							} else {
								childJson.put("url", childModule.getUrl()+"&moduleId="+childModule.getId());
							}
						}
						jsonArray.add(childJson);
					}
				}
				json.put("menus", jsonArray);
				parentJsonArray.add(json);
			}
			parentJson.put("menus", parentJsonArray);
			getSession().put(ContextConstants.ADMIN_LEFT_MODULE_HTML, parentJson);
		}
	}

	@Override
	public boolean validateHaveChildModules(String parent_id)
			throws ServiceException {
		List<SystemModule> childList = this.queryChildModulesByParent(parent_id);
		if(childList != null && !childList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public List<SystemModule> getValidateChildModules(SystemUser systemUser, String versionFlag, String moduleId) throws ServiceException {
		//先获取一级模块下面的子模块
		List<SystemModule> childModuleList = null;
		if(ContextConstants.ADMIN.equals(systemUser.getLoginId())){

			childModuleList = this.queryChildModulesByParent(moduleId);
		}else{
			
			childModuleList = this.queryChildModulesByParent(systemUser.getId(), moduleId);
		}
//		if(childModuleList != null && childModuleList.size() > 0) {
//			//List<UserActions> actionList = userActionsService.queryActionListByUser(user.getId());
//			List<SystemModule> resultList = new ArrayList<SystemModule>();
//			//执行过滤操作
//			/*for(SystemModule m : childModuleList) {
//				for(UserActions ua : actionList) {
//					if(ua.getModule().getId().intValue() == m.getId().intValue()) {
//						if(userActionsService.validateUserHaveModuleAction(ua.getId())) {
//							resultList.add(m);
//						}
//					}
//				}
//			}*/
//			if(!resultList.isEmpty()) {
//				return resultList;
//			}
//		}
		return childModuleList;
	}

	@Override
	public List<SystemModule> queryLeftModules(String parentId, String versionFlag) throws ServiceException {
		SystemModule m = this.queryById(SystemModule.class.getSimpleName(), parentId) ;
		if (m == null) {
			SystemModule parentModule = dbDAO.queryByCustom(SystemModule.class.getSimpleName(), deleteFlag, versionFlag, "and s.isDefaultShow=?3 and s.parent_id is null", new Object[]{ContextConstants.STATUS_OF_ONE}) ;
			if (parentModule != null) {
				return this.getValidateChildModules(getSystemUser(), versionFlag, parentModule.getId());
			} else {
				parentModule = dbDAO.queryByCustom(SystemModule.class.getSimpleName(), deleteFlag, versionFlag, null, null) ;
				return this.getValidateChildModules(getSystemUser(), versionFlag, parentModule.getId());
			}
		} else {
			if (m.getParent_id() == null) {
				return this.getValidateChildModules(getSystemUser(), versionFlag, m.getId());
			}
		}
		return null;
	}
	@Override
	public List<SystemModule> queryByList(String versionFlag) throws ServiceException {
		return dbDAO.queryByList(SystemModule.class.getSimpleName(), deleteFlag, versionFlag, null, null, orderBy, null);
	}

	@Override
	public boolean persistAndGetChild(String name, String url, String parent_id, String icon, String versionFlag, Integer orderValue,String newsClass_id) {
		SystemModule systemModule = new SystemModule();
		systemModule.setName(DecoderUtil.UtfDecoder(name));
		systemModule.setUrl(DecoderUtil.UtfDecoder(url));
		if("0".equals(parent_id)) {
			parent_id = null;
		}
		systemModule.setParent_id(DecoderUtil.UtfDecoder(parent_id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		systemModule.setStatus(status);
		//添加三个子模块
		try {
			addChildModule(name, systemModule.getId(),newsClass_id, icon);
			manageChildModule(name, systemModule.getId(),newsClass_id, icon);
			recycleChildModule(name, systemModule.getId(),newsClass_id, icon);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		dbDAO.persist(systemModule);
		return true;
	}
	
	/**
	 * 新增模块
	 * @param name 名称
	 * @param id  父id
	 * @param icon 默认图标
	 * @throws ServiceException
	 */
	private void addChildModule(String name,String id,String newsClass_id,String icon) throws ServiceException{
		SystemModule systemModule = new SystemModule();
		systemModule.setName(DecoderUtil.UtfDecoder("新增"+name));
		systemModule.setUrl(DecoderUtil.UtfDecoder("/news_add.action?versionFlag=site&newsClass_id="+newsClass_id));
		systemModule.setParent_id(DecoderUtil.UtfDecoder(id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(3);
		systemModule.setStatus(status);
		dbDAO.persist(systemModule);
	}
	
	/**
	 * 管理模块
	 * @param name 名称
	 * @param id  父id
	 * @param icon 默认图标
	 * @throws ServiceException
	 */
	private void manageChildModule(String name,String id,String newsClass_id,String icon) throws ServiceException{
		SystemModule systemModule = new SystemModule();
		systemModule.setName(DecoderUtil.UtfDecoder("管理"+name));
		systemModule.setUrl(DecoderUtil.UtfDecoder("/news_manage.action?versionFlag=site&newsClass_id="+newsClass_id));
		systemModule.setParent_id(DecoderUtil.UtfDecoder(id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(2);
		systemModule.setStatus(status);
		dbDAO.persist(systemModule);
	}
	
	/**
	 * 删除模块
	 * @param name 名称
	 * @param id  父id
	 * @param icon 默认图标
	 * @throws ServiceException
	 */
	private void recycleChildModule(String name,String id,String newsClass_id,String icon) throws ServiceException{
		SystemModule systemModule = new SystemModule();
		systemModule.setName(DecoderUtil.UtfDecoder(name+"回收站"));
		systemModule.setUrl(DecoderUtil.UtfDecoder("/news_recycle.action?versionFlag=site&newsClass_id="+newsClass_id));
		systemModule.setParent_id(DecoderUtil.UtfDecoder(id));
		systemModule.setIcon(DecoderUtil.UtfDecoder(icon));
		CommonEntity status = systemModule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(1);
		systemModule.setStatus(status);
		dbDAO.persist(systemModule);
	}
}