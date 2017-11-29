package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.MyText;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.service.IAgreementService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.IEmployeeInfoZengLogService;
import com.ticket.service.ILetterStationService;
import com.ticket.service.IMyTextService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IOrderInfoService;
import com.ticket.util.GeneralUtil;

/**
 * 手机版公司销售前台控制器
 * @package  com.ticket.action
 * @file     EmployeeInfoWapAdmin.java
 * @author   wangjiafeng
 * @date     2016-1-18 下午03:20:13
 * @version  V 1.0`
 */
public class EmployeeInfoWapAdminAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource
	private IEmployeeInfoService employeeInfoService = null;
	@Resource
	private IMyTextService myTextService = null;
	@Resource
	private IEmployeeInfoZengLogService employeeInfoZengLogService = null;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService = null;
	@Resource
	private IOrderInfoDetailService orderInfoDetailService = null;
	@Resource
	private ILetterStationService letterStationService = null;
	@Resource
	private IAgreementService agreementService = null;
	@Resource
	private IOrderInfoService orderInfoService = null;
	private String password,channelCustomerInfoLoginId,remark,idCard,flightNo,useTime,customerName,loginId,
		contact,contactPhone,mobile,name,title,content;
	private Integer count = null;
	private List<OrderInfoDetail> orderInfoDetails = null;
	private List<Agreement> agreements = null;
	private MyText myText;
	private List<ChannelCustomerInfo> channelCustomerInfos = null;
	
	/**
	 * @author wangjiafeng
	 * 添加我的记事
	 * @method myTextAdd
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-15 下午02:18:08
	 */
	public String myTextAdd() throws Exception{
		myText = myTextService.persist(getWapEmployeeInfo(), title, content);
		if(myText != null){
			data = "success";
		}else{
			data = "failed";
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 获取我的记事本列表
	 * @method myTextList
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-15 下午02:22:32
	 */
	public String myTextList() throws Exception{
		this.setPageModule(myTextService.queryAll(getWapEmployeeInfo(), null, null, 10));
		this.setPageSize(10);
		return "myTextList";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取我的记事本详细
	 * @method myTextView
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-2-15 下午02:24:17
	 */
	public String myTextView() throws Exception{
		myText = myTextService.queryById(MyText.class.getSimpleName(), id);
		myTextService.changeRead(id, 1);
		return "myTextView";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取站内信列表
	 * @method letterStation
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-20 下午05:29:56
	 */
	public String letterStationList() throws Exception{
		this.setMarkIndex(7);
		this.setPageModule(letterStationService.queryAll(null, null, 30));
		this.setPageSize(30);
		return "letterStationList";
	}
	
	/**
	 * @author wangjiafeng
	 *  查询合同
	 * @method searchAgreement
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-20 下午04:40:36
	 */
	public String getAgreementList() throws Exception{
		agreements = agreementService
				.queryListByChannelCustomerInfoName(getWapEmployeeInfo(), name, 1);
		return "getAgreementList";
	}
	
	/**
	 * @author wangjiafeng
	 * 获取合同
	 * @method getAgreement
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-20 下午03:39:24
	 */
	public String getAgreement()  throws Exception{
		this.setMarkIndex(6);
		if(GeneralUtil.isNull(operationFlag)){
			return "getAgreement";
		}
		String result = agreementService.addByWap(getWapEmployeeInfo(), name, remark);
		if(Boolean.valueOf(result)){
			data = "success";
		}else{
			data = result;
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 *  绑定便捷登机服务码
	 * @method memberBindService
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-20 下午02:01:45
	 */
	public String memberBindServiceCode() throws Exception{
		if(GeneralUtil.isNull(mobile)){
			data = AjaxData.responseError("手机号码不能为空");
			return JSON;
		}
		String result = orderInfoDetailService.memberBindServiceCode(mobile,name,getWapEmployeeInfo());
		if(Boolean.valueOf(result)){
			data = "success";
		}else{
			data = result;
		}
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 *  批量领码
	 * @method batchGetServiceCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-20 上午10:34:28
	 */
	public String batchGetServiceCode() throws Exception{
		this.setMarkIndex(5);
		if(GeneralUtil.isNull(operationFlag)){
			if(GeneralUtil.isNotNull(getWapEmployeeInfo().getChannelCustomerInfo())){
				orderInfoDetails = orderInfoDetailService
					.getServiceCodeIdsByUnused(getWapEmployeeInfo().getChannelCustomerInfo().getId(),true,5);
			}
			return "batchGetServiceCode";
		}
		if(GeneralUtil.isNull(getWapEmployeeInfo().getChannelCustomerInfo())){
			data = AjaxData.responseError("您的账号没有绑定渠道客户,不能领取服务码");
			return JSON;
		}
		if(GeneralUtil.isNull(count)){
			data = AjaxData.responseError("领取数量不能为空");
			return JSON;
		}
		String result = orderInfoService.add(count,"账号扣款", getWapEmployeeInfo().getChannelCustomerInfo().getId());
		if(Boolean.valueOf(result)){
			data = AjaxData.responseSuccess("领取便捷登机服务码成功");
		}else{
			data = AjaxData.responseError(result);
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 录入客户信息
	 * @method channelCustomerInfoAdd
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-19 下午05:39:21
	 */
	public String channelCustomerInfoAdd() throws Exception{
		this.setMarkIndex(4);
		if(GeneralUtil.isNull(operationFlag)){
			return "channelCustomerInfoAdd";
		}
		if(GeneralUtil.isNull(customerName)){
			data = AjaxData.responseError("公司名称不能为空");
			return JSON;
		}
		if(GeneralUtil.isNull(loginId)){
			data = AjaxData.responseError("登录账号不能为空");
			return JSON;
		}
		if(GeneralUtil.isNull(contact)){
			data = AjaxData.responseError("联系人不能为空");
			return JSON;
		}
		if(GeneralUtil.isNull(contactPhone)){
			data = AjaxData.responseError("联系电话不能为空");
			return JSON;
		}
		if(channelCustomerInfoService.IsExit(customerName) != null){
			data = AjaxData.responseError("该公司已经在客户池里边,请核对");
			return JSON;
		}
		if(channelCustomerInfoService.queryByLoginIdExist(loginId) != null){
			data = AjaxData.responseError("该用户名已被占用,请选择其他用户名");
			return JSON;
		}
		Boolean suc = channelCustomerInfoService.addWap(customerName, loginId, 
				password, contact, contactPhone,getWapEmployeeInfo());
		if(suc){
			data = AjaxData.responseSuccess("录入客户成功");
		}else{
			data = AjaxData.responseError("添加失败");
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 首页,
	 * @method index
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-18 下午04:29:05
	 */
	public String index() throws Exception{
		this.setMarkIndex(1);
		return "index";
	}
	
	
	
	/**
	 * @author wangjiafeng
	 * 获取指定客户未激活的服务码
	 * @method getUnusedCodeByChannelCutomerInfo
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-19 下午03:04:52
	 */
	public String getUnusedCodeByChannelCutomerInfo() throws Exception{
		orderInfoDetails = orderInfoDetailService
					.getUnusedCodeListByChannelCustomerInfoLoginId(channelCustomerInfoLoginId,false,5);
		return "getUnusedCodeByChannelCutomerInfo";
	}
	
	/**
	 * @author wangjiafeng
	 * 我要激活
	 * @method iWillActivate
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-19 下午02:24:31
	 */
	public String iWillActivate() throws Exception{
		this.setMarkIndex(3);
		if(GeneralUtil.isNull(operationFlag)){
			return "iWillActivate";
		}
		String result = bjdjServiceCodeService.iWillActivate(channelCustomerInfoLoginId, 
					idCard, flightNo, useTime);
		if(Boolean.valueOf(result)){
			data = "success";
		}else{
			data = result;
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 我要录单    添加服务码赠送日志
	 * @method employeeInfoZengLogAdd
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-19 上午10:19:14
	 */
	public String employeeInfoZengLogAdd() throws ServiceException{
		this.setMarkIndex(2);
		if(GeneralUtil.isNull(operationFlag)){
			channelCustomerInfos = channelCustomerInfoService.queryAllList(null, getWapEmployeeInfo(), null);
			return "employeeInfoZengLogAdd";
		}
		String result = employeeInfoZengLogService.add(getWapEmployeeInfo(), count, channelCustomerInfoLoginId, remark);
		if(Boolean.valueOf(result)){
			data = AjaxData.responseSuccess("赠送操作成功");
		}else{
			data = AjaxData.responseError(result);
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 企业员工登录手机销售后台
	 * @method login
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-18 下午03:37:30
	 */
	public String login() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			if(getWapEmployeeInfo() != null){
				return "reLogin";
			}
			return "login";
		}
		EmployeeInfo employeeInfo = employeeInfoService.queryByLogin(loginId, password);
		if(employeeInfo != null){
			getSession().put(ContextConstants.SCOPE_EMPLOYEEINFO_USER, employeeInfo);
			data = AjaxData.responseSuccess("登录成功");
		}else{
			data = AjaxData.responseError("登录失败");
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 退出登录
	 * @method logout
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-18 下午04:20:32
	 */
	public String logout() throws Exception{
		
		EmployeeInfo emp = getWapEmployeeInfo();
		if(emp != null){
			getSession().remove(ContextConstants.SCOPE_EMPLOYEEINFO_USER);
		}
		return "login";
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

	public String getChannelCustomerInfoLoginId() {
		return channelCustomerInfoLoginId;
	}

	public void setChannelCustomerInfoLoginId(String channelCustomerInfoLoginId) {
		this.channelCustomerInfoLoginId = channelCustomerInfoLoginId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public List<OrderInfoDetail> getOrderInfoDetails() {
		return orderInfoDetails;
	}

	public void setOrderInfoDetails(List<OrderInfoDetail> orderInfoDetails) {
		this.orderInfoDetails = orderInfoDetails;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Agreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(List<Agreement> agreements) {
		this.agreements = agreements;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MyText getMyText() {
		return myText;
	}

	public void setMyText(MyText myText) {
		this.myText = myText;
	}

	public List<ChannelCustomerInfo> getChannelCustomerInfos() {
		return channelCustomerInfos;
	}

	public void setChannelCustomerInfos(
			List<ChannelCustomerInfo> channelCustomerInfos) {
		this.channelCustomerInfos = channelCustomerInfos;
	}
}	
