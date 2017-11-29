package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.pojo.SystemModule;
import com.ticket.pojo.SystemUser;
import com.ticket.service.ISystemModuleService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.service.ISystemUserLoginLogService;
import com.ticket.service.ISystemUserService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.MD5Util;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 系统管理员控制器
 * @ClassName: SystemUserAction   
 * @Description:  提供系统管理员的相关操作方法. 
 * @author HiSay  
 * @date 2014-10-14 09:35:50
 */
public class SystemUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//系统管理员的业务层
	@Resource private ISystemUserService systemUserService = null;
	//系统模块业务层
	@Resource private ISystemModuleService systemModuleService = null;
	//系统管理员登陆日志业务层
	@Resource private ISystemUserLoginLogService systemUserLoginLogService = null;
	@Resource private ISystemOperationLogService logService = null;
	
	//系统管理员实体
	private SystemUser systemUser = null;
	//主键
	private String id = null;
    //管理员呢称
	private String name = null;
    //管理员性别
	private Integer sex = null;
    //登陆名称
	private String loginId = null;
    //登陆密码
	private String password = null;
	//一级模块列表
	private List<SystemModule> systemModuleList = null;
	//系统模块
	private SystemModule parentSystemModule = null;
	//原始密码
	private String oldPassword = null;
	//新密码
	private String repassword = null;
	//联系电话
	private String phone = null;
	//创建时间
	private Date startTime, endTime;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.name", Condition.like_left, name).and(new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime)));
		group = group.and("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
		group = group.and("s.status.versionFlag", Condition.eq, versionFlag);
		List<SystemUser> list = systemUserService.getDbDAO().executeJPQLForQuery("select s from " + SystemUser.class.getName() + " s " + group.toString(true), group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/systemUser.jasper", list, "系统用户");
		return null;
	}
	
	/**
	 * 初始化登陆后的数据
	 * @Title: initSessionDataByLogined 
	 * @param @throws ServiceException    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	private void initSessionDataByLogined() throws ServiceException {
		if (getSession().get(ContextConstants.ADMIN_TOP_MODULE_LIST) == null) {
			systemModuleList = systemModuleService.queryFirstModuleList(super.getSessionAdminUser(), versionFlag) ;
			getSession().put(ContextConstants.ADMIN_TOP_MODULE_LIST, systemModuleList);
		}
		
		if(systemModuleList != null && !systemModuleList.isEmpty()){
			
			List<SystemModule> leftModuleList = systemModuleService.queryLeftModules(systemModuleList.get(0).getId(), versionFlag);
			if(leftModuleList != null && !leftModuleList.isEmpty()) {
				this.setParentSystemModule(systemModuleService.queryById(SystemModule.class.getSimpleName(), leftModuleList.get(0).getParent_id()));
				//systemModuleService.queryModuleLeftTreeJson(leftModuleList, versionFlag) ;
				getSession().put(ContextConstants.ADMIN_LEFT_MODULE_LIST, leftModuleList);
			} else {
				getSession().remove(ContextConstants.ADMIN_LEFT_MODULE_LIST);
			}
		}
		//清空模块的leftIndex和markIndex
		getSession().remove("leftIndex");
		getSession().remove("markIndex");
	}
	
	/**
	 * 进入后台首页
	 */
	@Override
	public String execute() throws Exception {
		//如果未登陆, 则跳转到登陆页面.
		if (getSession().get(ContextConstants.SCOPE_SYSTEM_USER) == null) {
			return "login";
		} else {
			initSessionDataByLogined();
			return SUCCESS;
		}
	}
	
	/**
	 * 管理员登陆
	* @Title: login 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String login() throws Exception {
		//验证为空
		if(GeneralUtil.isNull(verificateCode)) {
			data = AjaxData.responseError(getText("verificateCode.required"));
			return JSON;
		}
		String sessionVerificateCode = (String)getSession().get(ContextConstants.SCOPE_VERIFICATE_CODE);
		//验证不准确
		if(!verificateCode.toLowerCase().equals(sessionVerificateCode)) {
			data = AjaxData.responseError(getText("verificateCode.inputError"));
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
		
		systemUser = systemUserService.queryByLoginIdAndPassword(loginId, password) ;
		//帐号或密码错误
		if(systemUser == null) {
			data = AjaxData.responseError(getText("usernameOrPassword.error"));
			return JSON;
		}
		//帐号未审核
		if(systemUser.getStatus().getAudit() ==  ContextConstants.STATUS_OF_ZERO) {
			data = AjaxData.responseError(getText("systemUser.not.audit"));
			return JSON;
		}
		if(systemUser != null) {
			getSession().put(ContextConstants.SCOPE_SYSTEM_USER, systemUser) ;
			initSessionDataByLogined();
			systemUserLoginLogService.persist(request.getRemoteHost(), systemUser.getId(), versionFlag) ;
			data = AjaxData.responseSuccess(getText("loginSuccess"));
		} else {
			data = AjaxData.responseError(getText("loginFailed"));
		}
		return JSON;
	}
	
	/**
	 * 管理员退出
	* @Title: logout 
	* @throws Exception    设定文件 
	* @return String    返回类型 
	 */
	public String logout() throws Exception {
		getSession().remove(ContextConstants.SCOPE_SYSTEM_USER) ;
		getSession().remove(ContextConstants.SCOPE_SYSTEM_EMPLOYEEINFO) ;
		getSession().remove(ContextConstants.SCOPE_CUSTOMER_USER) ;
		getSession().remove(ContextConstants.ADMIN_TOP_MODULE_LIST);
		getSession().remove(ContextConstants.ADMIN_LEFT_MODULE_LIST);
		return "login";
	}
	
	/**
	 * 添加系统管理员
	 * @Title: SystemUserAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSystemUser";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(sex)) {
				data = getText("sex.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(loginId)) {
				data = getText("loginId.required");
				return TEXT;
			}
			if(systemUserService.validateLoginIsExists(loginId, null)) {
				data = getText("loginId.exists");
				return TEXT;
			}
			if(GeneralUtil.isNull(password)) {
				data = getText("password.required");
				return TEXT;
			}
			//保存系统管理员实体
			boolean isSuc = systemUserService.persist(name, sex, loginId, password, phone, versionFlag);
			//根据保存结果返回页面
			if(isSuc) {
				String logContent = "新增系统管理员";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 修改系统管理员
	 * @Title: SystemUserAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSystemUser(systemUserService.queryById(SystemUser.class.getSimpleName(), id));
			return "editSystemUser";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(name)) {
				data = getText("name.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(sex)) {
				data = getText("sex.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(loginId)) {
				data = getText("loginId.required");
				return TEXT;
			}
			if(systemUserService.validateLoginIsExists(loginId, id)) {
				data = getText("loginId.exists");
				return TEXT;
			}
			if(GeneralUtil.isNull(password)) {
				data = getText("password.required");
				return TEXT;
			}
			//修改系统管理员实体
			boolean isSuc = systemUserService.merge(id, name, sex, loginId, password, phone, versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				String logContent = "修改系统管理员";
				String logName = SystemOparetionLogUtil.getLogName();
				String logTime = SystemOparetionLogUtil.getLogTime();
				String logIp = SystemOparetionLogUtil.getLogIp();
				
				logService.persist(logName, logContent, logTime, logIp, versionFlag);
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理系统管理员实体
	 * @Title: SystemUserAction
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SqlParamGroup group = new SqlParamGroup("s.name", Condition.like_left, name).and(new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime)));
		group = group.and("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO);
		group = group.and("s.status.versionFlag", Condition.eq, versionFlag);
		this.setPageModule(systemUserService.paginationQuery("select s from " + SystemUser.class.getName() + " s " + group.toString(true) + " order by s.status.createTime desc", group.getParamArray()));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSystemUser";
	}
	
	/**
	 * 查看回收站
	 * @Title: SystemUserAction
	 * @return
	 * @throws ServiceException   
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(systemUserService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSystemUser";
	}
	
	/**
	 * 逻辑删除系统管理员对象
	 * @Title: SystemUserAction
	 * @Description: 把系统管理员对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = systemUserService.logicDeleteEntity(SystemUser.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除系统管理员";
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
	 * 物理删除系统管理员对象
	 * @Title: SystemUserAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = systemUserService.remove(id);
		if(isSuc) {
			String logContent = "物理删除系统管理员";
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
	 * 还原一个系统管理员对象
	 * @Title: SystemUserAction
	 * @Description: 从回收站还原系统管理员对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = systemUserService.restoreEntity(SystemUser.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原系统管理员";
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
	 * 审核系统管理员对象
	 * @Title: SystemUserAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = systemUserService.auditEntity(SystemUser.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核系统管理员";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SystemUserAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = systemUserService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作系统管理员";
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
	 * 修改用户密码
	 * @Title: updatePassword 
	 * @return
	 * @throws ServiceException    设定文件 
	 * @return String    返回类型 
	 */
	public String updatePassword() throws ServiceException {
		//原始密码是否为空
		if(GeneralUtil.isNull(oldPassword)) {
			data = AjaxData.responseError(getText("oldPassword.required"));
			return JSON;
		}
		//验证原始密码是否正确
		if(!MD5Util.Azdg.strMD5(oldPassword).equals(super.getSessionAdminUser().getPassword())) {
			data = AjaxData.responseError(getText("oldPassword.validFailed"));
			return JSON;
		}
		//新密码是否为空
		if(GeneralUtil.isNull(password)) {
			data = AjaxData.responseError(getText("password.required"));
			return JSON;
		}
		//确认密码是否为空
		if(GeneralUtil.isNull(repassword)) {
			data = AjaxData.responseError(getText("repassword.required"));
			return JSON;
		}
		if(!password.equals(repassword)) {
			data = AjaxData.responseError(getText("password.notEqual.repassword"));
			return JSON;
		}
		//保存系统管理员实体
		boolean isSuc = systemUserService.updatePassword(super.getSessionAdminUser().getId(), password);
		//根据保存结果返回页面
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("updatePasswordSuccess"));
		} else {
			data = AjaxData.responseError(getText("updatePasswordSuccessFailed"));
		}
		return JSON;
	}
	
	/**
	 * 给用户授予角色
	 * @return
	 * @throws ServiceException
	 */
	public String grantRole() throws ServiceException{
		systemUser = systemUserService.queryById(SystemUser.class.getSimpleName(), id);
		String logContent = "给用户授予角色";
		String logName = SystemOparetionLogUtil.getLogName();
		String logTime = SystemOparetionLogUtil.getLogTime();
		String logIp = SystemOparetionLogUtil.getLogIp();
		
		logService.persist(logName, logContent, logTime, logIp, versionFlag);
		return "grantRole";
	}
	
	/**
	 * 批量彻底删除用户信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = systemUserService.batchRealDelete(idsValue,versionFlag);
		//根据保存结果返回页面
		if(isSuc) {
			String logContent = "批量删除用户信息";
			String logName = SystemOparetionLogUtil.getLogName();
			String logTime = SystemOparetionLogUtil.getLogTime();
			String logIp = SystemOparetionLogUtil.getLogIp();
			
			logService.persist(logName, logContent, logTime, logIp, versionFlag);
			data = AjaxData.responseSuccess(getText("updatePasswordSuccess"));
		} else {
			data = AjaxData.responseError(getText("updatePasswordSuccessFailed"));
		}
		return JSON;
	}
	
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<SystemModule> getSystemModuleList() {
		return systemModuleList;
	}

	public void setSystemModuleList(List<SystemModule> systemModuleList) {
		this.systemModuleList = systemModuleList;
	}

	public SystemModule getParentSystemModule() {
		return parentSystemModule;
	}

	public void setParentSystemModule(SystemModule parentSystemModule) {
		this.parentSystemModule = parentSystemModule;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
