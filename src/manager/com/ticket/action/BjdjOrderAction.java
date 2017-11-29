package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjServiceCode;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ISystemOperationLogService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SystemOparetionLogUtil;

/**
 * 便捷登机订单表控制器
 * @ClassName: BjdjOrderAction   
 * @Description:  提供便捷登机订单表的相关操作方法. 
 * @author HiSay  
 * @date 2015-10-23 15:22:31
 *
 */
public class BjdjOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//便捷登机订单表的业务层
	@Resource private IBjdjOrderService orderService = null;
	//用户业务层
	@Resource private IMemberService memberService = null;
	//服务码业务层
	@Resource private IBjdjServiceCodeService serviceCodeService = null;
	//字典业务层
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource 
	private ISystemOperationLogService logService = null;
	//便捷登机订单表实体
	private BjdjOrder order = null;
	//主键
	private String id = null;
    //用户ID
	private String member_id = null;
	//商品名称
	private String name = null;
	//订单号
	private String number = null;
    //订单日期
	private Date time = null;
    //是否完成
	private String state = null;
	//行李
	private String luggage;
	//航班号(多个用逗号隔开)
	private String flightNo;
	//身份证号
	private String IDCard;
	//电话号码
	private String mobile;
	//邮箱
	private String email;
	//服务码
	private String serviceCode;
	//数量
	private Integer count;
	//下单时间
	private Date startTime, endTime;
	//支付时间
	private Date startTime2, endTime2;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		group.and("s.payTime", Condition.ge, DateUtil.getDayStart(startTime2)).and("s.payTime", Condition.le, DateUtil.getDayEnd(endTime2));
		group.and("s.state.id", Condition.eq, state);
		group.and("s.name", Condition.like_left, name);
		group.and("s.number", Condition.eq, number);
		String condition = group.toString(true);
		if(GeneralUtil.isNotNull(serviceCode)){
			
			condition += " and exists (select sc from "+BjdjServiceCode.class.getName()+" sc where sc.order=s and sc.code='"+serviceCode+"')";
		}
		List<BjdjOrder> list = orderService.getDbDAO().executeJPQLForQuery("select s from " + BjdjOrder.class.getName() + " s " + condition, group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/bjdjOrder.jasper", list, "便捷登机订单", "/WEB-INF/excel/jasper/");
		return null;
	}
	
	/**
	 * 添加便捷登机订单表
	 * @Title: BjdjOrderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addForMobile() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjOrderMobile";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(IDCard)) {
				data = getText("IDCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNo)) {
				data = getText("flightNo.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(count) || count <= 0) {
				data = getText("amount.positive");
				return TEXT;
			}
			if(GeneralUtil.isNull(mobile)) {
				data = getText("mobile.required");
				return TEXT;
			}
			
			//根据保存结果返回页面
//			order = orderService.generateByMobile(null, count, BjdjOrderType.bjdj, mobile);
//			order.setLuggage(luggage);
//			order.setFlightNo(flightNo);
//			orderService.merge(order);
			
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 添加便捷登机订单表
	 * @Title: BjdjOrderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addForHall() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjOrderHall";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(IDCard)) {
				data = getText("IDCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNo)) {
				data = getText("flightNo.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(count) || count <= 0) {
				data = getText("amount.positive");
				return TEXT;
			}
			if(GeneralUtil.isNull(email)) {
				data = getText("mobile.required");
				return TEXT;
			}
			
			//根据保存结果返回页面
//			order = orderService.generateByMobile(null, count, BjdjOrderType.bjdj, mobile);
//			order.setLuggage(luggage);
//			order.setFlightNo(flightNo);
//			orderService.merge(order);
			
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 添加便捷登机订单表
	 * @Title: BjdjOrderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String addForEmail() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addBjdjOrderEmail";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(IDCard)) {
				data = getText("IDCard.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(flightNo)) {
				data = getText("flightNo.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(count) || count <= 0) {
				data = getText("amount.positive");
				return TEXT;
			}
			if(GeneralUtil.isNull(mobile)) {
				data = getText("mobile.required");
				return TEXT;
			}
			
			//根据保存结果返回页面
//			order = orderService.generateByEmail(null, count, BjdjOrderType.bjdj, email);
//			order.setLuggage(luggage);
//			order.setFlightNo(flightNo);
//			orderService.merge(order);
			
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}
	
	/**
	 * 修改便捷登机订单表
	 * @Title: BjdjOrderAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjOrder(orderService.queryById(BjdjOrder.class.getSimpleName(), id));
			return "editBjdjOrder";
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
			if(GeneralUtil.isNull(time)) {
				data = getText("time.required");
				return TEXT;
			}
			if(GeneralUtil.isNull(state)) {
				data = getText("state.required");
				return TEXT;
			}
			//修改便捷登机订单表实体
//			boolean isSuc = bjdjOrderService.merge(id, number, member_id, time, state,  versionFlag);
//			//根据修改结果返回页面
//			if(isSuc) {
//				data = AjaxData.responseSuccess(getText("editSuccess"));
//			} else {
//				data = AjaxData.responseError(getText("editFailed"));
//			}
			return JSON;
		}
	}
	
	/**
	 * 管理便捷登机订单表实体
	 * @Title: BjdjOrderAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime));
		group.and("s.payTime", Condition.ge, DateUtil.getDayStart(startTime2)).and("s.payTime", Condition.le, DateUtil.getDayEnd(endTime2));
		group.and("s.state.id", Condition.eq, state);
		group.and("s.name", Condition.like_left, name);
		group.and("s.number", Condition.eq, number);
		
		String condition = group.toString(true);
		if(GeneralUtil.isNotNull(serviceCode)){
			
			condition += " and exists (select sc from "+BjdjServiceCode.class.getName()+" sc where sc.order=s and sc.code='"+serviceCode+"')";
		}
		Pagination page = orderService.paginationQuery("select s from " + BjdjOrder.class.getName() + " s " + condition + 
				" order by s.status.createTime desc", group.getParamArray());
		this.setPageModule(page);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjOrder";
	}
	
	/**
	 * 查看回收站
	 * @Title: BjdjOrderAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(orderService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleBjdjOrder";
	}
	
	/**
	 * 逻辑删除便捷登机订单表对象
	 * @Title: BjdjOrderAction
	 * @Description: 把便捷登机订单表对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = orderService.logicDeleteEntity(BjdjOrder.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "逻辑删除订单";
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
	 * 物理删除便捷登机订单表对象
	 * @Title: BjdjOrderAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = orderService.remove(id);
		if(isSuc) {
			String logContent = "物理删除订单";
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
	 * 还原一个便捷登机订单表对象
	 * @Title: BjdjOrderAction
	 * @Description: 从回收站还原便捷登机订单表对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = orderService.restoreEntity(BjdjOrder.class.getSimpleName(), id);
		if(isSuc) {
			String logContent = "还原订单";
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
	 * 审核便捷登机订单表对象
	 * @Title: BjdjOrderAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = orderService.auditEntity(BjdjOrder.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			String logContent = "审核订单";
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
	 * @Title: BjdjOrderAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = orderService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			String logContent = "批量操作订单";
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
	 * 物理删除对象
	 * @Title: BjdjQueueAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String batchRealDelete() throws ServiceException {
		
		boolean isSuc = orderService.batchRealDelete(BjdjOrder.class, idsValue);
		if(isSuc) {
			String logContent = "批量删除订单";
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
	
	public BjdjOrder getBjdjOrder() {
		return order;
	}
	public void setBjdjOrder(BjdjOrder bjdjOrder) {
		this.order = bjdjOrder;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLuggage() {
		return luggage;
	}
	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public IBjdjOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IBjdjOrderService orderService) {
		this.orderService = orderService;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public ISystemOperationLogService getLogService() {
		return logService;
	}

	public void setLogService(ISystemOperationLogService logService) {
		this.logService = logService;
	}

	public BjdjOrder getOrder() {
		return order;
	}

	public void setOrder(BjdjOrder order) {
		this.order = order;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Date getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Date startTime2) {
		this.startTime2 = startTime2;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
