package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ServiceBook;
import com.ticket.service.IServiceBookService;
import com.ticket.util.GeneralUtil;

/**
 * 服务订单控制器
 * @ClassName: ServiceBookAction   
 * @Description:  提供服务订单的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-15 12:56:11
 *
 */
public class ServiceBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//服务订单的业务层
	@Resource private IServiceBookService serviceBookService = null;
	//服务订单实体
	private ServiceBook serviceBook = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //身份证号
	private String idCard = null;
    //联系电话
	private String phone = null;
    //航班号
	private String flightNumber = null;
    //预订数量
	private Integer bookAmount = null;
    //使用时间
	private Date useTime = null;
    //服务码
	private String serviceKey = null;
    //付款状态
	private Integer payStatus = null;
    //付款时间
	private Date payTime = null;
    //付款方式
	private Integer payWay = null;
    //微信交易号
	private String weChatTransld = null;
    //支付宝名称
	private String alipayName = null;
    //支付宝交易号
	private String alipayTransld = null;
    //网银名称
	private String bankName = null;
	//关键词
	private String keyword =null;

	/**
	 * 添加服务订单
	 * @Title: ServiceBookAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addServiceBook";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(idCard)) {
				data = getText("idCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(phone)) {
				data = getText("phone.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNumber)) {
				data = getText("flightNumber.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(bookAmount)) {
				data = getText("bookAmount.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(useTime)) {
				data = getText("useTime.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(serviceKey)) {
				data = getText("serviceKey.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(payStatus)) {
				data = getText("payStatus.required");
				return TEXT;
			}
			/*if(GeneralUtil.isNull(payTime)) {
				AjaxUtil.writeString(response, request, getText("payTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				AjaxUtil.writeString(response, request, getText("payWay.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(weChatTransld)) {
				AjaxUtil.writeString(response, request, getText("weChatTransld.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(alipayName)) {
				AjaxUtil.writeString(response, request, getText("alipayName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(alipayTransld)) {
				AjaxUtil.writeString(response, request, getText("alipayTransld.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(bankName)) {
				AjaxUtil.writeString(response, request, getText("bankName.required"));
				return JSON;
			}*/
			//保存服务订单实体
			boolean isSuc = serviceBookService.persist(member_id, idCard, phone, flightNumber, bookAmount, useTime, serviceKey, payStatus, payTime, payWay, weChatTransld, alipayName, alipayTransld, bankName, versionFlag);
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
	 * 修改服务订单
	 * @Title: ServiceBookAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setServiceBook(serviceBookService.queryById(ServiceBook.class.getSimpleName(), id));
			return "editServiceBook";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = getText("id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = getText("member_id.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(idCard)) {
				data = getText("idCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(phone)) {
				data = getText("phone.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNumber)) {
				data = getText("flightNumber.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(bookAmount)) {
				data = getText("bookAmount.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(useTime)) {
				data = getText("useTime.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(serviceKey)) {
				data = getText("serviceKey.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(payStatus)) {
				data = getText("payStatus.required");
				return TEXT;
			}
			/*if(GeneralUtil.isNull(payTime)) {
				AjaxUtil.writeString(response, request, getText("payTime.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(payWay)) {
				AjaxUtil.writeString(response, request, getText("payWay.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(weChatTransld)) {
				AjaxUtil.writeString(response, request, getText("weChatTransld.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(alipayName)) {
				AjaxUtil.writeString(response, request, getText("alipayName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(alipayTransld)) {
				AjaxUtil.writeString(response, request, getText("alipayTransld.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(bankName)) {
				AjaxUtil.writeString(response, request, getText("bankName.required"));
				return JSON;
			}*/
			//修改服务订单实体
			boolean isSuc = serviceBookService.merge(id, member_id, idCard, phone, flightNumber, bookAmount, useTime, serviceKey, payStatus, payTime, payWay, weChatTransld, alipayName, alipayTransld, bankName,  versionFlag);
			//根据修改结果返回页面
			if(isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}
	
	/**
	 * 管理服务订单实体
	 * @Title: ServiceBookAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(serviceBookService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageServiceBook";
	}
	
	/**
	 * 查看回收站
	 * @Title: ServiceBookAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(serviceBookService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleServiceBook";
	}
	
	/**
	 * 逻辑删除服务订单对象
	 * @Title: ServiceBookAction
	 * @Description: 把服务订单对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = serviceBookService.logicDeleteEntity(ServiceBook.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除服务订单对象
	 * @Title: ServiceBookAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = serviceBookService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个服务订单对象
	 * @Title: ServiceBookAction
	 * @Description: 从回收站还原服务订单对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = serviceBookService.restoreEntity(ServiceBook.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核服务订单对象
	 * @Title: ServiceBookAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = serviceBookService.auditEntity(ServiceBook.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: ServiceBookAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = serviceBookService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量彻底删除服务订单信息
	 * @return
	 * @throws ServiceException
	 */
	public String batchRealDelete() throws ServiceException{
		boolean isSuc = serviceBookService.batchRealDelete(idsValue, versionFlag);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 根据关键词搜索订单信息
	 * @return
	 * @throws ServiceException
	 */
	public String search() throws ServiceException{
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(serviceBookService.queryPageModuleByKeyword(keyword, ContextConstants.ADMIN_COMMON_PAGE_SIZE, versionFlag));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageServiceBook";
	}

	public ServiceBook getServiceBook() {
		return serviceBook;
	}
	public void setServiceBook(ServiceBook serviceBook) {
		this.serviceBook = serviceBook;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Integer getBookAmount() {
		return bookAmount;
	}
	public void setBookAmount(Integer bookAmount) {
		this.bookAmount = bookAmount;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public String getServiceKey() {
		return serviceKey;
	}
	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public String getWeChatTransld() {
		return weChatTransld;
	}
	public void setWeChatTransld(String weChatTransld) {
		this.weChatTransld = weChatTransld;
	}
	public String getAlipayName() {
		return alipayName;
	}
	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}
	public String getAlipayTransld() {
		return alipayTransld;
	}
	public void setAlipayTransld(String alipayTransld) {
		this.alipayTransld = alipayTransld;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
