package com.ticket.action;

import java.util.Date;

import javax.annotation.Resource;

import com.ticket.constants.ContextConstants;
import com.ticket.enumer.PayMethod;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjOrder;
import com.ticket.pojo.BjdjRefund;
import com.ticket.service.IBjdjRefundService;
import com.ticket.util.GeneralUtil;

/**
 * 退款记录控制器
 * @ClassName: BjdjRefundAction   
 * @Description:  提供退款记录的相关操作方法. 
 * @author HiSay  
 * @date 2016-11-25 10:59:56
 *
 */
public class BjdjRefundAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//退款记录的业务层
	@Resource private IBjdjRefundService bjdjRefundService;
	//退款记录实体
	private BjdjRefund bjdjRefund;
	//主键
	private String id;
    //退款订单
	private BjdjOrder order;
    //是否允许退款
	private Boolean allow;
    //管理员备注
	private String remark;
    //退款原因说明
	private String reason;
    //操作时间
	private Date time;
	
	/**
	 * 修改退款记录
	 * @Title: BjdjRefundAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setBjdjRefund(bjdjRefundService.queryById(BjdjRefund.class.getSimpleName(), id));
			return "editBjdjRefund";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				addFieldError("id", getText("id.required"));
				return "editBjdjRefund";
			}
			if(GeneralUtil.isNull(allow)) {
				addFieldError("allow", getText("allow.required"));
				return "editBjdjRefund";
			}
			
			bjdjRefund = bjdjRefundService.queryById(BjdjRefund.class.getSimpleName(), id);
			try {
				bjdjRefundService.audit(id, allow, remark);
				if(bjdjRefund.getOrder().getPayMethod() == PayMethod.alipay){
					
					return null;
				}else{
					
					return "toManage";
				}
			} catch (ServiceException e) {
				addFieldError("other", e.getMessage());
				return "editBjdjRefund";
			}
		}
	}
	
	/**
	 * 管理退款记录实体
	 * @Title: BjdjRefundAction
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(bjdjRefundService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageBjdjRefund";
	}
	
	public BjdjRefund getBjdjRefund() {
		return bjdjRefund;
	}
	public void setBjdjRefund(BjdjRefund bjdjRefund) {
		this.bjdjRefund = bjdjRefund;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BjdjOrder getOrder() {
		return order;
	}
	public void setOrder(BjdjOrder order) {
		this.order = order;
	}
	public Boolean getAllow() {
		return allow;
	}
	public void setAllow(Boolean allow) {
		this.allow = allow;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
