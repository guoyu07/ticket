package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.HitSingleLog;
import com.ticket.service.IAgreementService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IHitSingleLogService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;

/**
 * 撞单日志控制器
 * @ClassName: HitSingleLogAction   
 * @Description:  提供撞单日志的相关操作方法. 
 * @author HiSay  
 * @date 2016-01-03 09:38:37
 *
 */
public class HitSingleLogAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//撞单日志的业务层
	@Resource private IHitSingleLogService hitSingleLogService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private IAgreementService agreementService = null;
	@Resource
	private ICustomerProtectLogService customerProtectLogService = null;
	//撞单日志实体
	private HitSingleLog hitSingleLog = null;
	private ChannelCustomerInfo channelCustomerInfo = null;
	private Agreement agreement = null;
	//主键
	private String id,employeeInfo_id,channelCustomerInfo_id,keyword,agreement_id,remark;
	private List<ChannelCustomerInfo> channelCustomerInfos = null;
	private CustomerProtectLog customerProtectLog = null;
	private Integer state = null;
	
	/**
	 * @author wangjiafeng
	 * 审核撞单申请
	 * @method changeState
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-15 下午02:51:46
	 */
	public String changeState() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			hitSingleLog = hitSingleLogService.queryById(HitSingleLog.class.getSimpleName(), id);
			channelCustomerInfo = hitSingleLog.getChannelCustomerInfo();
			agreement = hitSingleLog.getAgreement();
			return "editHitSingleLog";
		}
		Boolean suc = hitSingleLogService.changeState(id, state, remark);
		if(suc) {
			data = AjaxData.responseSuccess("操作成功");
		} else {
			data = AjaxData.responseError("操作失败");
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 查看
	 * @method view
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-15 下午03:18:37
	 */
	public String view() throws Exception{
		hitSingleLog = hitSingleLogService.queryById(HitSingleLog.class.getSimpleName(), id);
		channelCustomerInfo = hitSingleLog.getChannelCustomerInfo();
		agreement = hitSingleLog.getAgreement();
		return "view";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取合同信息,及用户列表
	 * @method getAgreenmentAndCustomer
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-15 上午11:03:28
	 */
	public String getAgreenmentAndCustomer() throws ServiceException{
		customerProtectLog = customerProtectLogService.queryNewLogLess(id,new Date(),0);
		channelCustomerInfo  = channelCustomerInfoService.queryById(ChannelCustomerInfo.class.getSimpleName(), id);
		if(getSystemEmployeeInfo() != null){
			agreement = agreementService.getNewAgreement(channelCustomerInfo.getId(), getSystemEmployeeInfo().getId(),"0,1,2,3,4,5", 1);
		}
		return "getAgreenmentAndCustomer";
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
		channelCustomerInfos = channelCustomerInfoService.queryAllList(keyword, null);
		data = JSONArray.fromObject(channelCustomerInfos).toString();
		return TEXT;
	}
	
	/**
	 * 添加撞单日志
	 * @Title: HitSingleLogAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			channelCustomerInfos = channelCustomerInfoService.queryAllList(null, 999);
			return "addHitSingleLog";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(channelCustomerInfo_id)) {
				data = AjaxData.responseError("客户不能为空");
				return JSON;
			}
			
			if(GeneralUtil.isNull(agreement_id)) {
				data = AjaxData.responseError("您没有申请合同,即使提交撞单申请也是无用!");
				return JSON;
			}
			
			//保存撞单日志实体
			String result = hitSingleLogService.persist(channelCustomerInfo_id, getSystemEmployeeInfo(),agreement_id);
			//根据保存结果返回页面
			if(Boolean.valueOf(result)) {
				data = AjaxData.responseSuccess("撞单申请添加成功,请等待相关人员判定");
			} else {
				data = AjaxData.responseError(result);
			}
			return JSON;
		}
	}
	
	
	/**
	 * 管理撞单日志实体
	 * @Title: HitSingleLogAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(hitSingleLogService.queryAll(getSystemEmployeeInfo(), ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageHitSingleLog";
	}
	
	/**
	 * 查看回收站
	 * @Title: HitSingleLogAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(hitSingleLogService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleHitSingleLog";
	}
	
	/**
	 * 逻辑删除撞单日志对象
	 * @Title: HitSingleLogAction
	 * @Description: 把撞单日志对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = hitSingleLogService.logicDeleteEntity(HitSingleLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除撞单日志对象
	 * @Title: HitSingleLogAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = hitSingleLogService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个撞单日志对象
	 * @Title: HitSingleLogAction
	 * @Description: 从回收站还原撞单日志对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = hitSingleLogService.restoreEntity(HitSingleLog.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核撞单日志对象
	 * @Title: HitSingleLogAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = hitSingleLogService.auditEntity(HitSingleLog.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: HitSingleLogAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = hitSingleLogService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public HitSingleLog getHitSingleLog() {
		return hitSingleLog;
	}
	public void setHitSingleLog(HitSingleLog hitSingleLog) {
		this.hitSingleLog = hitSingleLog;
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
	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}
	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public String getAgreement_id() {
		return agreement_id;
	}
	public void setAgreement_id(String agreementId) {
		agreement_id = agreementId;
	}
	public CustomerProtectLog getCustomerProtectLog() {
		return customerProtectLog;
	}
	public void setCustomerProtectLog(CustomerProtectLog customerProtectLog) {
		this.customerProtectLog = customerProtectLog;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
