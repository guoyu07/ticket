package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.DataAuthoritys;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IDataAuthoritysService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;

/**
 * 客保日志控制器
 * @ClassName: CustomerProtectLogAction   
 * @Description:  提供客保日志的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-02 10:21:06
 *
 */
public class CustomerProtectLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//客保日志的业务层
	@Resource private ICustomerProtectLogService customerProtectLogService = null;
	@Resource private IDataAuthoritysService dataAuthoritysService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	private CustomerProtectLog customerProtectLog = null;
	private String id,channelCustomerInfo_id,employeeInfo_id,keyword;
	private Date startTime,endTime;
	private List<ChannelCustomerInfo> channelCustomerInfos = null;
	private Integer dateCount = null;
	private ChannelCustomerInfo channelCustomerInfo = null;
	/**
	 * @author wangjiafeng
	 * 延长保护时间
	 * @method changeEndTime
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-2 下午04:49:08
	 */
	public String changeEndTime() throws Exception{
		if(GeneralUtil.isNull(operationFlag)) {
			customerProtectLog = customerProtectLogService.queryById(CustomerProtectLog.class.getSimpleName(), id);
			return "editCustomerProtectLog";
		} else {
			if(!customerProtectLogService.validateIsChangeEndTime(id)){
				data = AjaxData.responseError("3天内暂时不会到期,不能延期");
				return JSON;
			}
			String suc = customerProtectLogService.changeEndTime(id, dateCount);
			//根据保存结果返回页面
			if(Boolean.valueOf(suc)) {
				data = AjaxData.responseSuccess("延长保护时间操作成功");
			} else {
				data = AjaxData.responseError(suc);
			}
			return JSON;
		}
	}
	
	
	/**
	 * @author wangjiafeng
	 * 取消保护
	 * @method changeExpire
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-2 下午04:13:07
	 */
	public String changeExpire() throws ServiceException{
		Boolean suc = customerProtectLogService.changeExpire(id);
		if(suc){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 验证客户是否能保护
	 * @method validateIsProtected
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-2 上午11:47:42
	 */
	public String validateIsProtected() throws Exception{
		String result = "";
		if(getSystemEmployeeInfo() != null){
			result = customerProtectLogService
					.validateIsProtected(channelCustomerInfo_id, getSystemEmployeeInfo().getId());
		}else{
			result = "系统管理员不能录入客保信息!";
		}
		if(Boolean.valueOf(result)){
			data = "success";
		}else{
			data = result;
		}
		return TEXT;
	}
	/**
	 * @author wangjiafeng
	 * 获取渠道客户列表
	 * @method getChannelCustomerInfoList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-2 下午01:37:25
	 */
	public String getChannelCustomerInfoList() throws Exception{
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword, getSystemEmployeeInfo(), null);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}

	
	/**
	 * 添加客保日志
	 * @Title: CustomerProtectLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword, getSystemEmployeeInfo(), 20);
			return "addCustomerProtectLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(channelCustomerInfo_id)) {
				data = AjaxData.responseError(getText("channelCustomerInfo_id.required"));
				return JSON;
			}
			//保存客保日志实体
			String result = customerProtectLogService.persist(channelCustomerInfo_id,getSystemEmployeeInfo());
			//根据保存结果返回页面
			if(Boolean.valueOf(result)) {
				data = AjaxData.responseSuccess("录入客保信息成功!");
			} else {
				data = AjaxData.responseError(result);
			}
			return JSON;
		}
	}
	
	/**
	 * 添加客保日志
	 * @Title: CustomerProtectLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add2() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfo = channelCustomerInfoService
					.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfo_id);
			return "addCustomerProtectLog2";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(channelCustomerInfo_id)) {
				data = AjaxData.responseError(getText("channelCustomerInfo_id.required"));
				return JSON;
			}
			//保存客保日志实体
			String result = customerProtectLogService.persist(channelCustomerInfo_id,getSystemEmployeeInfo());
			//根据保存结果返回页面
			if(Boolean.valueOf(result)) {
				data = AjaxData.responseSuccess("录入客保信息成功!");
			} else {
				data = AjaxData.responseError(result);
			}
			return JSON;
		}
	}
	
	/**
	 * 管理客保日志实体
	 * @Title: CustomerProtectLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		String method = this.getClass().getSimpleName() + "_" + new Exception().getStackTrace()[0].getMethodName();
		
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			EmployeeInfo info = (EmployeeInfo) user;
			
			List<DataAuthoritys> authoritys = dataAuthoritysService.queryByEmployeeId(info.getId());
			if(authoritys != null){
				for(DataAuthoritys authority:authoritys){
					if(authority.getInMethod().equals(method)){	
						if(authority.getContent().equals("one")){
							this.setPageModule(customerProtectLogService.queryAll(info, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
						if(authority.getContent().equals("departMent")){
							this.setPageModule(customerProtectLogService.queryWhereInDepartment(info));
						}
						if(authority.getContent().equals("all")){
							this.setPageModule(customerProtectLogService.queryAll(null, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
						}
					}
				}
			}
		}else{
			this.setPageModule(customerProtectLogService.queryAll(null, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		}
		
		
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCustomerProtectLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: CustomerProtectLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(customerProtectLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleCustomerProtectLog";
	}
	
	/**
	 * 逻辑删除客保日志对象
	 * @Title: CustomerProtectLogAction
	 * @Description: 把客保日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = customerProtectLogService.logicDeleteEntity(CustomerProtectLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除客保日志对象
	 * @Title: CustomerProtectLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = customerProtectLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个客保日志对象
	 * @Title: CustomerProtectLogAction
	 * @Description: 从回收站还原客保日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = customerProtectLogService.restoreEntity(CustomerProtectLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核客保日志对象
	 * @Title: CustomerProtectLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = customerProtectLogService.auditEntity(CustomerProtectLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: CustomerProtectLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = customerProtectLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public CustomerProtectLog getCustomerProtectLog() {
		return customerProtectLog;
	}
	public void setCustomerProtectLog(CustomerProtectLog customerProtectLog) {
		this.customerProtectLog = customerProtectLog;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelCustomerInfo_id() {
		return channelCustomerInfo_id;
	}
	public void setChannelCustomerInfo_id(String channelCustomerInfo_id) {
		this.channelCustomerInfo_id = channelCustomerInfo_id;
	}
	public String getEmployeeInfo_id() {
		return employeeInfo_id;
	}
	public void setEmployeeInfo_id(String employeeInfo_id) {
		this.employeeInfo_id = employeeInfo_id;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}
	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}
	public Integer getDateCount() {
		return dateCount;
	}
	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	
}
