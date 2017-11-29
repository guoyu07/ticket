package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Position;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IPositionService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ISystemUserService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

import net.sf.json.JSONArray;

/**
 * 员工信息控制器
 * @ClassName: EmployeeInfoAction   
 * @Description:  提供员工信息的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-03 15:33:02
 *
 */
public class EmployeeInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//员工信息的业务层
	@Resource 
	private IEmployeeInfoService employeeInfoService = null;
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	
	//系统管理员的业务层
	@Resource 
	private ISystemUserService systemUserService = null;
	@Resource 
	private IDepartmentInfoService departmentInfoService = null;
	@Resource 
	private IPositionService positionService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource 
	private ISystemOperationLogService logService = null;
	
	//员工信息实体
	private EmployeeInfo employeeInfo = null;
	private String id,position_id,department_id,name,entryDate,IDCard,phone,emergencyContact,
			emergencyPhone,keyword,loginId,password,departmentInfoHtml,channelCustomerInfo_id;
    private Integer state= null;
    private List<Position> positions = null;
    private List<ChannelCustomerInfo> channelCustomerInfos = null;
    
    /**
	 * @author wangjiafeng
	 * 获取渠道客户列表
	 * @method getChannelCustomerInfoList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-3 下午05:22:28
	 */
	public String getChannelCustomerInfoList() throws Exception{
		if(getSystemEmployeeInfo() == null){
			data = "";
			return TEXT;
		}
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword,getSystemEmployeeInfo(), null);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}
    
    /**
     * @author wangjiafeng
     * 绑定账号
     * @method bindChannelCustomerInfo
     * @return
     * @throws Exception
     * @return String
     * @date 2016-1-19 下午01:41:33
     */
    public String bindChannelCustomerInfo() throws Exception{
    	if(GeneralUtil.isNull(operationFlag)){
    		employeeInfo = employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), id);
    		channelCustomerInfos = channelCustomerInfoService.queryAllList(null, 2000);
    		return  "bindChannelCustomerInfo";
    	}
    	Boolean suc = employeeInfoService.bindChannelCustomerInfo(id, channelCustomerInfo_id);
    	if(suc) {
    		String logContent = "绑定客户";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess("绑定客户操作成功");
		} else {
			data = AjaxData.responseError("操作失败");
		}
    	return JSON;
    }
	
	/**
	 * 添加员工信息
	 * @Title: EmployeeInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(null));
			positions = positionService.queryAll(1000);
			return "addEmployeeInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(department_id)) {
				data = AjaxData.responseError(getText("department_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(entryDate)) {
				data = AjaxData.responseError(getText("entryDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(emergencyContact)) {
				data = AjaxData.responseError(getText("emergencyContact.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(emergencyPhone)) {
				data = AjaxData.responseError(getText("emergencyPhone.required"));
				return JSON;
			}
			//登陆名为空
			if(GeneralUtil.isNull(loginId)) {
				data = AjaxData.responseError(getText("loginId.required"));
				return JSON;
			}
			//登陆密码为空
			if(GeneralUtil.isNull(password)) {
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			if(systemUserService.validateLoginIsExists(loginId, null)) {
				data = AjaxData.responseError(getText("loginId.exists"));
				return JSON;
			}
			
			//保存员工信息实体
			try {
				employeeInfoService.add(null, department_id, name, entryDate, IDCard,
						phone, emergencyContact, emergencyPhone,loginId,password, state,position_id);
			} catch (Exception e) {
				data = AjaxData.responseError(getText("addFailed"));
				return JSON;
			}
			String logContent = "新增员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改员工信息
	 * @Title: EmployeeInfoAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			positions = positionService.queryAll(1000);
			this.setEmployeeInfo(employeeInfoService.queryById(EmployeeInfo.class.getSimpleName(), id));
			if(GeneralUtil.isNotNull(employeeInfo.getDepartment())){
				this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(employeeInfo.getDepartment().getId()));
			}else{
				this.setDepartmentInfoHtml(departmentInfoService.createDepartmentInfoOptionHtml(null));
			}
			return "editEmployeeInfo";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(department_id)) {
				data = AjaxData.responseError(getText("department_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(entryDate)) {
				data = AjaxData.responseError(getText("entryDate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(IDCard)) {
				data = AjaxData.responseError(getText("IDCard.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(phone)) {
				data = AjaxData.responseError(getText("phone.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(emergencyContact)) {
				data = AjaxData.responseError(getText("emergencyContact.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(emergencyPhone)) {
				data = AjaxData.responseError(getText("emergencyPhone.required"));
				return JSON;
			}
			//登陆名为空
			if(GeneralUtil.isNull(loginId)) {
				data = AjaxData.responseError(getText("loginId.required"));
				return JSON;
			}
			//登陆密码为空
			if(GeneralUtil.isNull(password)) {
				data = AjaxData.responseError(getText("password.required"));
				return JSON;
			}
			if(systemUserService.validateLoginIsExists(loginId, null)) {
				EmployeeInfo employeeInfo = employeeInfoService
						.queryById(EmployeeInfo.class.getSimpleName(), id);
				if(!employeeInfo.getLoginId().equals(loginId)){
					data = AjaxData.responseError(getText("loginId.exists"));
					return JSON;
				}
			}
			//修改员工信息实体
			try {
				employeeInfoService.merge(id, null, department_id, name, entryDate, IDCard, 
						phone, emergencyContact, emergencyPhone,state, position_id,loginId,password);
			} catch (Exception e) {
				data = AjaxData.responseError(getText("editFailed"));
				return JSON;
			}
			String logContent = "修改员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("editSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 管理员工信息实体
	 * @Title: EmployeeInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){	
						if(authority.getContent().equals("one")){
							this.setPageModule(employeeInfoService.paginationQuery("select c from Employee c where c.id = ?", info.getId()));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(employeeInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(employeeInfoService.queryWhereInDepartment(info));
						}
					}
				}
			}
		}else{
			this.setPageModule(employeeInfoService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEmployeeInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: EmployeeInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(employeeInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleEmployeeInfo";
	}
	
	/**
	 * 逻辑删除员工信息对象
	 * @Title: EmployeeInfoAction
	 * @Description: 把员工信息对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = employeeInfoService.logicDeleteEntity(EmployeeInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除员工信息对象
	 * @Title: EmployeeInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = employeeInfoService.remove(id);
		if(isSuc) {
			String logContent = "物理删除员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个员工信息对象
	 * @Title: EmployeeInfoAction
	 * @Description: 从回收站还原员工信息对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = employeeInfoService.restoreEntity(EmployeeInfo.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核员工信息对象
	 * @Title: EmployeeInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = employeeInfoService.auditEntity(EmployeeInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: EmployeeInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = employeeInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	/**
	 * 批量彻底删除员工信息实体
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException {
		boolean isSuc = employeeInfoService.batchRealDelete(idsValue,versionFlag);
		if(isSuc) {
			String logContent = "批量删除员工信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	

	/**
	 * 根据员工姓名/电话关键词检索员工信息
	 * @return
	 * @throws ServiceException
	 */
	public String search() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(employeeInfoService.queryPageModuleByKeyword(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageEmployeeInfo";
	}
	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String IDCard) {
		this.IDCard = IDCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getDepartmentInfoHtml() {
		return departmentInfoHtml;
	}

	public void setDepartmentInfoHtml(String departmentInfoHtml) {
		this.departmentInfoHtml = departmentInfoHtml;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String positionId) {
		position_id = positionId;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public String getChannelCustomerInfo_id() {
		return channelCustomerInfo_id;
	}

	public void setChannelCustomerInfo_id(String channelCustomerInfoId) {
		channelCustomerInfo_id = channelCustomerInfoId;
	}

	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}

	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}
	
}
