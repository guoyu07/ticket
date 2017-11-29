package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemOrg;
import com.ticket.service.ISystemOrgService;
import com.ticket.service.ITreeService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description：系统组织机构请求控制器
 * @author：涂有
 * @date 2015年12月31日 上午11:35:08
 */
public class SystemOrgAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID :    
	 */ 
	private static final long serialVersionUID = 1L;
	
	//系统组织机构的业务层
	@Resource (name="systemOrgService")
	private ISystemOrgService<SystemOrg, String> orgService = null;
	
	//系统组织机构实体
	private SystemOrg org = null;
	
	/**
	 * @Description：遍历所有字典
	 * @return
	 */
	public String traverse() {
		
		try {
			JSONArray json = orgService.traverse(SystemOrg.class, "name");
			data = json.toString();
			return TEXT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * @Description：加载树的单层数据,根据父ID
	 * @return
	 */
	public String loadTree(){
		
		List<SystemOrg> dictionarys = null;
		
		//加载根节点
		if(GeneralUtil.isNull(id)){
			
			JSONObject json = new JSONObject();
			Long count = orgService.count(SystemOrg.class);
			json.put("total", count);
			
			dictionarys = orgService.queryRootNode(SystemOrg.class);
			JSONArray jsonArray = new JSONArray();
			for(SystemOrg dict : dictionarys){
				
				Long subCount = orgService.countSubByParent(SystemOrg.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", null);
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonSingle.put("description", dict.getDescription());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			data = json.toString();
			
		}else{ //加载非根节点
			
			dictionarys = orgService.querySubByParent(SystemOrg.class, id);
			JSONArray jsonArray = new JSONArray();
			for(SystemOrg dict : dictionarys){
				
				Long subCount = orgService.countSubByParent(SystemOrg.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", dict.getParent().getId());
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonSingle.put("description", dict.getDescription());
				jsonArray.add(jsonSingle);
			}
			data = jsonArray.toString();
		}
		return TEXT;
	}
	
	/**
	 * @Description：查询兄弟节点
	 * @return
	 */
	public String querySibling(){
		
		SystemOrg parent = orgService.getParentBySub(SystemOrg.class, id);
		List<SystemOrg> dicts = orgService.querySubByParent(SystemOrg.class, parent.getId());
		data = JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentName(){
		
		List<SystemOrg> dicts = orgService.querySubByParent(SystemOrg.class, "name", org.getName());
		data = JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		try {
//			List<SystemOrg> list = treeService.queryRootNode(SystemOrg.class);
//			String html = PageCodeUtil.recursion(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "manageSystemOrg";
	}
	
	/**
	 * 添加系统组织机构
	 * @Title: SystemOrgAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() {
		
		if(GeneralUtil.isNull(operationFlag)) {
			
			return "addSystemOrg";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(org.getName())) {
				data = getText("name.required");
				return TEXT;
			}
			if(org.getParent() != null){
				
				if("".equals(org.getParent().getId().trim())){
					
					org.setParent(null);
				}
			}
			//保存系统组织机构实体
			try {
				orgService.persist(org);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			//根据保存结果返回页面
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改系统组织机构
	 * @Title: SystemOrgAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			org = orgService.get(SystemOrg.class, id);
			return "editSystemOrg";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(org.getId())) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(org.getName())) {
				data = getText("name.required");
				return TEXT;
			}
			if(org.getParent() != null){
				
				if(org.getId().equals(org.getParent().getId())){
					data = getText("dontSelectYourself");
					return TEXT;
				}
				if("".equals(org.getParent().getId().trim())){
					
					org.setParent(null);
				}
			}
			//修改系统组织机构实体
			try {
				orgService.merge(org);
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(getText("editFailed"));
				return JSON;
			}
			
			//根据修改结果返回页面
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * @Description：根据id得到一个字典对象
	 * @return
	 */
	public String loadSystemOrg()throws Exception{
		
		data = net.sf.json.JSONObject.fromObject(orgService.queryById("SystemOrg", id)).toString();
		return TEXT;
	}
	
	/**
	 * 物理删除系统组织机构对象
	 * @Title: SystemOrgAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		
		//先检查是否有子节点
		Long count = orgService.countSubByParent(SystemOrg.class, id);
		if(count > 0){
			data = AjaxData.responseError(getText("hasChild"));
			return JSON;
		}
		
		try {
			org = orgService.get(SystemOrg.class, id);
			orgService.remove(org);
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError(getText("deleteFailed"));
			return JSON;
		}
		data = AjaxData.responseSuccess(getText("deleteSuccess"));
		return JSON;
	}

	public SystemOrg getOrg() {
		return org;
	}

	public void setOrg(SystemOrg org) {
		this.org = org;
	}

	public ITreeService getTreeService() {
		return orgService;
	}

	public void setTreeService(ISystemOrgService orgService) {
		this.orgService = orgService;
	}
}
