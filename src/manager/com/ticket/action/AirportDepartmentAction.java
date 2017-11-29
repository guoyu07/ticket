package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportDepartment;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IAirportDepartmentService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.QueryUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description：系统组织机构请求控制器
 * @author：涂有
 * @date 2015年12月31日 上午11:35:08
 */
public class AirportDepartmentAction extends BaseAction {

	/**   
	 * @Fields serialVersionUID :    
	 */ 
	private static final long serialVersionUID = 1L;
	
	//系统组织机构的业务层
	@Resource (name="airportDepartmentService")
	private IAirportDepartmentService<AirportDepartment, String> departmentService = null;
	@Resource private ISystemOperationLogService logService = null;
	//系统组织机构实体
	private AirportDepartment department = null;
	
	/**
	 * @Description：遍历所有字典
	 * @return
	 */
	public String traverse() {
		SystemUser user = QueryUtil.getEntity();
		if(user != null && user instanceof AirportEmployee){
			AirportEmployee employee = (AirportEmployee)user;
//			AirportDepartment department = employee.getDepartment_id();
			String departmentId = employee.getDepartment_id();
			id = departmentId;
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonSingle = new JSONObject();
			jsonSingle.put("id", id);
			jsonSingle.put("text", department.getName());
			JSONArray json = null;
			try {
				json = departmentService.traverseUnderParent(AirportDepartment.class, "name", department.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonSingle.put("chiden",json);
			jsonArray.add(jsonSingle);
			data = jsonArray.toString();
			return TEXT;
		}else{
			try {
				JSONArray json = departmentService.traverse(AirportDepartment.class, "name");
				System.out.println(json.toString());
				data = json.toString();
				return TEXT;
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
		}
	}
	
	/**
	 * @Description：加载树的单层数据,根据父ID
	 * @return
	 */
	public String loadTree(){
		
		List<AirportDepartment> dictionarys = null;
		SystemUser user = QueryUtil.getEntity();
		if(user != null && user instanceof AirportEmployee){
			AirportEmployee employee = (AirportEmployee)user;
//			AirportDepartment department = employee.getDepartment();
			String departmnetID = employee.getDepartment_id();
			id = departmnetID;
		}else if(user != null && user instanceof SystemUser){
		}
		//加载根节点
		if(GeneralUtil.isNull(id)){
			
			JSONObject json = new JSONObject();
			Long count = departmentService.count(AirportDepartment.class);
			json.put("total", count);
			//System.out.println(count);//8
			dictionarys = departmentService.queryRootNode(AirportDepartment.class);
//			System.out.println(dictionarys.size());//3
			JSONArray jsonArray = new JSONArray();
			for(AirportDepartment dict : dictionarys){
				
				Long subCount = departmentService.countSubByParent(AirportDepartment.class, dict.getId());
				JSONObject jsonSingle = new JSONObject();
				if(subCount > 0){
					jsonSingle.put("state", "closed");
				}
				jsonSingle.put("_parentId", null);
				jsonSingle.put("id", dict.getId());
				jsonSingle.put("name", dict.getName());
				jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
				jsonSingle.put("description", dict.getDescription());
				jsonSingle.put("departmentId", dict.getDepartmentId());
				jsonArray.add(jsonSingle);
			}
			json.put("rows", jsonArray);
			System.out.println(json.toString());
			data = json.toString();
			
		}else{ //加载非根节点
			//得到所有部门
			dictionarys = departmentService.query(AirportDepartment.class);
			JSONArray jsonArray = new JSONArray();
			for(AirportDepartment dict : dictionarys){
				JSONObject jsonSingle = new JSONObject();
				//如果部门id和登录者所在部门id一致
				if(dict.getId().equals(id)){
					List<AirportDepartment> dictionary = null;
					dictionary = departmentService.querySubByParent(AirportDepartment.class, dict.getId());
					if(dictionary.size() < 1){
						jsonSingle.put("id", dict.getId());
						jsonSingle.put("name", dict.getName());
						jsonSingle.put("orderValue", dict.getStatus().getOrderValue());
						jsonSingle.put("description", dict.getDescription());
						jsonSingle.put("departmentId", dict.getDepartmentId());
						jsonArray.add(jsonSingle);
					}else{						
						for(AirportDepartment dic : dictionary){
							
							Long subCount = departmentService.countSubByParent(AirportDepartment.class, dic.getId());
							//如果包含有子节点
							if(subCount > 0){
								
								jsonSingle.put("state", "closed");
							}
							jsonSingle.put("_parentId", dic.getParent().getId());
							jsonSingle.put("id", dic.getId());
							jsonSingle.put("name", dic.getName());
							jsonSingle.put("orderValue", dic.getStatus().getOrderValue());
							jsonSingle.put("description", dic.getDescription());
							jsonSingle.put("departmentId", dic.getDepartmentId());
							jsonArray.add(jsonSingle);
						}
					}
					
				}
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
		
		AirportDepartment parent = departmentService.getParentBySub(AirportDepartment.class, id);
		List<AirportDepartment> dicts = departmentService.querySubByParent(AirportDepartment.class, parent.getId());
		data = JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	/**
	 * @Description：根据父字典的name查询所有的子节点
	 * @return
	 */
	public String querySubByParentName(){
		
		List<AirportDepartment> dicts = departmentService.querySubByParent(AirportDepartment.class, "name", department.getName());
		data = JSONArray.fromObject(dicts).toString();
		return TEXT;
	}
	
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		return "manageAirportDepartment";
	}
	
	/**
	 * 添加系统组织机构
	 * @Title: AirportDepartmentAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() throws ServiceException {
		
		if(GeneralUtil.isNull(operationFlag)) {
			
			return "addAirportDepartment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(department.getName())) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(department.getDepartmentId())) {
				data = getText("id.required");
				return TEXT;
			}
			if(department.getParent() != null){
				
				if("".equals(department.getParent().getId().trim())){
					
					department.setParent(null);
				}
			}
			//保存系统组织机构实体
			try {
				departmentService.persist(department);
			} catch (ServiceException e) {
				data = AjaxData.responseError(e.getMessage());
				return JSON;
			}
			String logContent = "新增机场部门";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			//根据保存结果返回页面
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改系统组织机构
	 * @Title: AirportDepartmentAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			department = departmentService.get(AirportDepartment.class, id);
			return "editAirportDepartment";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(department.getId())) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(department.getName())) {
				data = getText("name.required");
				return TEXT;
			}
			if(department.getParent() != null){
				
				if(department.getId().equals(department.getParent().getId())){
					data = getText("dontSelectYourself");
					return TEXT;
				}
				if("".equals(department.getParent().getId().trim())){
					
					department.setParent(null);
				}
			}
			//修改系统组织机构实体
			try {
				departmentService.merge(department);
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(getText("editFailed"));
				return JSON;
			}
			String logContent = "修改机场部门";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			
			//根据修改结果返回页面
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * @Description：根据id得到一个字典对象
	 * @return
	 */
	public String loadAirportDepartment()throws Exception{
		
		data = net.sf.json.JSONObject.fromObject(departmentService.queryById("AirportDepartment", id)).toString();
		return TEXT;
	}
	
	/**
	 * 物理删除系统组织机构对象
	 * @Title: AirportDepartmentAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		
		//先检查是否有子节点
		Long count = departmentService.countSubByParent(AirportDepartment.class, id);
		if(count > 0){
			data = AjaxData.responseError(getText("hasChild"));
			return JSON;
		}
		
		try {
			departmentService.batchRealDelete(AirportDepartment.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			data = AjaxData.responseError(getText("deleteFailed"));
			return JSON;
		}
		String logContent = "删除机场部门";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		data = AjaxData.responseSuccess(getText("deleteSuccess"));
		return JSON;
	}

	public IAirportDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IAirportDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public AirportDepartment getDepartment() {
		return department;
	}

	public void setDepartment(AirportDepartment department) {
		this.department = department;
	}
}
