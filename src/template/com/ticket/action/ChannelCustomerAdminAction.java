package com.ticket.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.constants.JQueryUploadConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Member;
import com.ticket.pojo.OrderInfoDetail;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IEmployeeInfoZengLogService;
import com.ticket.service.IMemberService;
import com.ticket.service.IOrderInfoDetailService;
import com.ticket.service.IOrderInfoService;
import com.ticket.service.IRechargeRecordService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;

/**
 * 渠道客户后台控制器
 * @package  com.ticket.action
 * @file     ChannelCustomerAdminAction.java
 * @author   wangjiafeng
 * @date     2015-12-28 下午03:55:06
 * @version  V 1.0
 */
public class ChannelCustomerAdminAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IBjdjServiceCodeService bjdjServiceCodeService;
	@Resource
	private IOrderInfoDetailService orderInfoDetailService;
	@Resource
	private IRechargeRecordService rechargeRecordService;
	@Resource
	private IMemberService memberService;
	@Resource
	private IEmployeeInfoZengLogService zengLogService;
	
	private String id,username,password,oldPassword,repassword,recordNo,channelCustomerInfoId,payWay,receiptNo,
	loginName,loginPwd,nickName,realName,IDCard,phone,qq,email,address,keyword,useTime,bjdjServiceCodeId,memberId,
	flightNo;
    //充值金额
	private Double amountOfMoney;
	private Integer buyCount;
    //支付时间
	private Date payTime;
	private ChannelCustomerInfo channelCustomerInfo;
	private Member member;
	private List<Member> members;
	private BjdjServiceCode bjdjServiceCode;
	
	/**
	 * @author wangjiafeng
	 * 提醒开票
	 * @method txkp
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-3-3 下午02:40:33
	 */
	public String txkp() throws Exception{
		RechargeRecord rechargeRecord = rechargeRecordService
				.queryById(RechargeRecord.class.getSimpleName(), id);
		if(GeneralUtil.isNull(rechargeRecord.getState()) || rechargeRecord.getState() == 0){
			rechargeRecord.setState(2);
			rechargeRecordService.merge(rechargeRecord);
		}
		data = "success";
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 批量导入会员
	 * @method batchLeadingInMember
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2016-1-6 下午03:41:37
	 */
	public String batchLeadingInMember() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			return "batchLeadingInMember";
		}
		String file = getSinglePictureUrlFromJQueryUpLoader(fileNames, directory, JQueryUploadConstants.PICTURE_TYPE_DEFAULT);
		if(file != null){
			file = file.substring(0,file.indexOf(ContextConstants.UPLOAD_SEPARATOR_VALUE));
			List<Member> list = memberService.batchLeadingInMember(getSessionAdminUser().getId(), request, file);
			if(list != null){
				Pagination pagination = new Pagination(list.size());
				pagination.setPageList(list);
				this.setPageModule(pagination);
				this.setPageSize(list.size());
			}
			return "batchLeadingInMemberSuccess";
		}
		return null;
	}
	
	/**
	 * @author wangjiafeng
	 * 激活服务码
	 * @method activateBjdjServiceCoede
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-30 下午03:09:56
	 */
	public String activateBjdjServiceCode() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			members = memberService.queryByChannelCustomerInfoList(getSessionAdminUser().getId(), null, 15);
			bjdjServiceCode = bjdjServiceCodeService
					.queryById(BjdjServiceCode.class.getSimpleName(), bjdjServiceCodeId);
			return "activateBjdjServiceCode";
		}
		String result = bjdjServiceCodeService.activateBjdjServiceCoede(bjdjServiceCodeId, memberId,
					useTime,flightNo);
		if(Boolean.valueOf(result)){
			data = AjaxData.responseSuccess("激活服务码成功");
		}else{
			data = AjaxData.responseError(result);
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 通过关键词获取会员列表
	 * @method getMemberListByKeyword
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-30 下午03:25:24
	 */
	public String getMemberListByKeyword() throws Exception{
		members = memberService.queryByChannelCustomerInfoList(getSessionAdminUser().getId(),
				DecoderUtil.UtfDecoder(keyword), null);
		data = JSONArray.fromObject(members).toString();
		return TEXT;
	}
	
	/**
	 * @author wangjiafeng
	 * 管理渠道客户的会员
	 * @method memberManage
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-30 上午10:19:59
	 */
	public String memberManage() throws Exception{
		if(GeneralUtil.isNotNull(keyword)){
			keyword = DecoderUtil.UtfDecoder(keyword);
		}
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberService.queryByChannelCustomerInfo(getSessionAdminUser().getId(),
				keyword, 30));
		this.setPageSize(30);
		return "memberManage";
	}
	
	/**
	 * @author wangjiafeng
	 * 渠道客户会员添加
	 * @method memberAdd
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-30 上午09:38:58
	 */
	public String memberAdd() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			return "memberAdd";
		}
		if(memberService.validateLoginNameExist(loginName)){
			data = AjaxData.responseError("该用户名已被使用,请更换用户名");
			return JSON;
		}
		if(memberService.validePhoneExist(phone,versionFlag)){
			data = AjaxData.responseError("该电话号码已被使用,请更换号码");
			return JSON;
		}
		if(memberService.validateEmailExist(email)){
			data = AjaxData.responseError("该电子邮箱已被使用,请更换电子邮箱");
			return JSON;
		}
		
		Boolean suc = memberService.add(null, loginName,
				loginPwd, nickName, realName, IDCard, phone, 
				qq, email, address, getSessionAdminUser().getId());
		if(suc){
			data = AjaxData.responseSuccess("添加会员成功");
		}else{
			data = AjaxData.responseError("添加会员失败");
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 *  编辑渠道客户会员
	 * @method memberUpdate
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-30 上午10:01:06
	 */
	public String memberUpdate() throws Exception{
		if(GeneralUtil.isNull(operationFlag)){
			member = memberService.queryById(Member.class.getSimpleName(), id);
			return "memberUpdate";
		}
		member = memberService.queryById(Member.class.getSimpleName(), id);
		if(memberService.validateLoginNameExist(loginName)){
			if(!loginName.equals(member.getLoginName())){
				data = AjaxData.responseError("该用户名已被使用,请更换用户名");
				return JSON;
			}
		}
		if(memberService.validePhoneExist(phone,versionFlag)){
			if(!phone.equals(member.getPhone())){
				data = AjaxData.responseError("该电话号码已被使用,请更换号码");
				return JSON;
			}
		}
		if(memberService.validateEmailExist(email)){
			if(!email.equals(member.getEmail())){
				data = AjaxData.responseError("该电子邮箱已被使用,请更换电子邮箱");
				return JSON;
			}
		}
		Boolean suc = memberService.update(id,null, loginName,
				loginPwd, nickName, realName, IDCard, phone, 
				qq, email, address);
		if(suc){
			data = AjaxData.responseSuccess("编辑会员成功");
		}else{
			data = AjaxData.responseError("编辑会员失败");
		}
		return JSON;
	}
	
	/**
	 * @author wangjiafeng
	 * 管理我的服务码
	 * @method myBjdjServiceCode
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-29 下午04:34:39
	 */
	public String myBjdjServiceCode() throws Exception{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		Pagination pagination = new Pagination();
		List<OrderInfoDetail> details = orderInfoDetailService.getServiceCodeIdsByUnused(getSessionAdminUser().getId(), true, 30);
		pagination.setPageList(details);
		pagination.setTotalCount(details.size());
		this.setPageModule(pagination);
		this.setPageSize(30);
		return "myBjdjServiceCode";
	}
	
	/**
	 * @author wangjiafeng
	 * 查看购买记录
	 * @method manageOrderInfo
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-29 下午02:16:28
	 */
	public String manageOrderInfo() throws Exception{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(orderInfoService.queryAll(getSessionAdminUser().getId(), 30));
		this.setPageSize(30);
		return "manageOrderInfo";
	}
	
	/**
	 * @author wangjiafeng
	 * 购买服务码
	 * @method buyBjdjServiceCode
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2015-12-29 下午01:43:10
	 */
	public String buyBjdjServiceCode() throws Exception{
		if(GeneralUtil.isNull(this.getOperationFlag())){
			return "buyBjdjServiceCode";
		}
		if(GeneralUtil.isNull(buyCount)) {
			data = AjaxData.responseError("请输入购买数量");
			return JSON;
		}
		String result = orderInfoService.add(buyCount,payWay, getSessionAdminUser().getId());
		if(Boolean.valueOf(result)){
			data = AjaxData.responseSuccess("购买便捷登机服务码成功");
		}else{
			data = AjaxData.responseError(result);
		}
		return JSON;
	}
	
	/**
	 * 分销服务码
	 * @return
	 * @throws Exception
	 */
	public String donationBjdjServiceCode() throws Exception{
		
		if(GeneralUtil.isNull(this.getOperationFlag())){
			return "donationBjdjServiceCode";
		}
		if(GeneralUtil.isNull(username)) {
			data = AjaxData.responseError("姓名不能为空");
			return JSON;
		}
		if(GeneralUtil.isNull(phone)) {
			data = AjaxData.responseError("手机号码不能为空");
			return JSON;
		}
		try {
			zengLogService.addForDistribution(getSessionAdminUser().getId(), username, phone, id.split(","));
		} catch (Exception e) {
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess("购买便捷登机服务码成功");
		return JSON;
	}
	
	
	/**
	 * 
	 * @Title: RechargeRecordAction
	 * @Description: 查询当前登录客户充值记录实体 
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manageCustomer() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		if(user instanceof EmployeeInfo){
			this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, null,user.getId(), versionFlag,null));
		}else if(user instanceof ChannelCustomerInfo){
			this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, user.getId(),null, versionFlag,null));
		}else{
			this.setPageModule(rechargeRecordService.queryPageModuleByCustomerId(ContextConstants.ADMIN_COMMON_PAGE_SIZE, null,null, versionFlag,null));
		}
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageCustomerRechargeRecord";
	}
	
	/**
	 * 添加充值记录
	 * @Title: RechargeRecordAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addRechargeRecord";
		} else {
			//非空验证.
			
			if(GeneralUtil.isNull(amountOfMoney)) {
				data = AjaxData.responseError(getText("amountOfMoney.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				data = AjaxData.responseError(getText("payWay.required"));
				return JSON;
			}
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer str = new StringBuffer(format.format(date).toString());
			recordNo ="000"+ str.toString();
			receiptNo ="000";
			channelCustomerInfoId = super.getSessionAdminUser().getId();
			//保存充值记录实体
			boolean isSuc = rechargeRecordService.persist(recordNo, amountOfMoney, channelCustomerInfoId, payTime, payWay, receiptNo,null);
			
			//根据保存结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("addSuccess"));
			} else {
				data = AjaxData.responseError(getText("addFailed"));
			}
			return JSON;
		}
	}
 
	
	/**
	 * @author wangjiafeng
	 * 查看个人账户信息
	 * @method detail
	 * @return
	 * @throws Exception
	 * @return String
	 * @date 2015-12-28 下午04:57:57
	 */
	public String detail() throws Exception {
		this.setChannelCustomerInfo(channelCustomerInfoService
				.queryById(ChannelCustomerInfo.class.getSimpleName(), getSessionAdminUser().getId()));
		return "detailChannelCustomerInfo";
	}
	
	/**
	 * 进入渠道用户后台首页
	 */
	@Override
	public String execute() throws Exception {
		//如果未登陆, 则跳转到登陆页面.
		if (getSession().get(ContextConstants.SCOPE_CUSTOMER_USER) == null) {
			return "login";
		} else {
			return SUCCESS;
		}
	}
	
	/**
	 * @author wangjiafeng
	 * 用户登录
	 * @method login
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2015-12-28 下午04:11:54
	 */
	public String login() throws ServiceException {
		ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService.login(username, password);
		if(channelCustomerInfo!=null) {
			getSession().put(ContextConstants.SCOPE_CUSTOMER_USER, channelCustomerInfo) ;
			data = AjaxData.responseSuccess(getText("loginSuccess"));
		} else {
			data = AjaxData.responseError(getText("loginFailed"));
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
	 * @date 2015-12-28 下午05:19:21
	 */
	public String logout() throws Exception {
		getSession().remove(ContextConstants.SCOPE_CUSTOMER_USER) ;
		return "login";
	}
	
	/**
	 * @author wangjiafeng
	 * 修改密码
	 * @method updatePassword
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2015-12-28 下午04:31:20
	 */
	public String updatePassword() throws ServiceException {
		//原始密码是否为空
		if(GeneralUtil.isNull(oldPassword)) {
			data = getText("oldPassword.required");
			return TEXT;
		}
		//验证原始密码是否正确
		if(!oldPassword.equals(super.getSessionAdminUser().getPassword())) {
			data = getText("oldPassword.validFailed");
			return TEXT;
		}
		//新密码是否为空
		if(GeneralUtil.isNull(password)) {
			data = getText("password.required");
			return TEXT;
		}
		//确认密码是否为空
		if(GeneralUtil.isNull(repassword)) {
			data = getText("repassword.required");
			return TEXT;
		}
		if(!password.equals(repassword)) {
			data = getText("password.notEqual.repassword");
			return TEXT;
		}
		//保存渠道用户实体
		boolean isSuc = channelCustomerInfoService.updatePassword(super.getSessionAdminUser().getId(), password) ;
		//根据保存结果返回页面
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("updatePasswordSuccess"));
		} else {
			data = AjaxData.responseError(getText("updatePasswordSuccessFailed"));
		}
		return JSON;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public ChannelCustomerInfo getChannelCustomerInfo() {
		return channelCustomerInfo;
	}

	public void setChannelCustomerInfo(ChannelCustomerInfo channelCustomerInfo) {
		this.channelCustomerInfo = channelCustomerInfo;
	}

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getChannelCustomerInfoId() {
		return channelCustomerInfoId;
	}

	public void setChannelCustomerInfoId(String channelCustomerInfoId) {
		this.channelCustomerInfoId = channelCustomerInfoId;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Double getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(Double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getBjdjServiceCodeId() {
		return bjdjServiceCodeId;
	}

	public void setBjdjServiceCodeId(String bjdjServiceCodeId) {
		this.bjdjServiceCodeId = bjdjServiceCodeId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public BjdjServiceCode getBjdjServiceCode() {
		return bjdjServiceCode;
	}

	public void setBjdjServiceCode(BjdjServiceCode bjdjServiceCode) {
		this.bjdjServiceCode = bjdjServiceCode;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	
}
