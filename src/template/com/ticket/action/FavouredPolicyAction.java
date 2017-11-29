package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.FavouredPolicy;
import com.ticket.service.IFavouredPolicyService;
import com.ticket.util.GeneralUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 优惠政策控制器
 * @ClassName: FavouredPolicyAction   
 * @Description:  提供优惠政策的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-14 14:34:06
 *
 */
public class FavouredPolicyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//优惠政策的业务层
	@Resource private IFavouredPolicyService favouredPolicyService = null;
	//优惠政策实体
	private FavouredPolicy favouredPolicy = null;
	//主键
	private String id = null;
    //优惠编号
	private String favouredPolicyNo = null;
    //名称
	private String name = null;
    //所需充值金额
	private Double rechargeAmount = null;
    //折扣率
	private Double discountRate = null;
    //优惠方式
	private String discountWay = null;
    //描述
	private String descript = null;
	//优惠等级
	private Integer grade = null;
	//赠送金额
	private Double giftAmount =null;
	
	public String findByCustomerId() throws ServiceException{
		List<FavouredPolicy> favouredPolicys = favouredPolicyService.queryByCustomerId(id);
		JSONObject object = new JSONObject();
		JSONArray object1 = new JSONArray();
		object1.add(favouredPolicys);
		object.put("favouredPolicys", favouredPolicys);
		data = AjaxData.responseSuccess(JSONObject.fromObject(object).toString());
		return JSON;
	}
	/**
	 * 添加优惠政策
	 * @Title: FavouredPolicyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addFavouredPolicy";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(favouredPolicyNo)) {
				data = AjaxData.responseError(getText("favouredPolicyNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(rechargeAmount)) {
				data = AjaxData.responseError(getText("rechargeAmount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(discountRate)) {
				data = AjaxData.responseError(getText("discountRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(discountWay)) {
				data = AjaxData.responseError(getText("DiscountWay.required"));
				return JSON;
			}
			
			/*if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}*/
			//保存优惠政策实体
			boolean isSuc = favouredPolicyService.persist(favouredPolicyNo, name, rechargeAmount, discountRate, discountWay, descript,grade,giftAmount, versionFlag);
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
	 * 修改优惠政策
	 * @Title: FavouredPolicyAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setFavouredPolicy(favouredPolicyService.queryById(FavouredPolicy.class.getSimpleName(), id));
			return "editFavouredPolicy";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(favouredPolicyNo)) {
				data = AjaxData.responseError(getText("favouredPolicyNo.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(name)) {
				data = AjaxData.responseError(getText("name.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(rechargeAmount)) {
				data = AjaxData.responseError(getText("rechargeAmount.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(discountRate)) {
				data = AjaxData.responseError(getText("discountRate.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(discountWay)) {
				data = AjaxData.responseError(getText("DiscountWay.required"));
				return JSON;
			}
			
			/*if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}*/
			//修改优惠政策实体
			boolean isSuc = favouredPolicyService.merge(id, favouredPolicyNo, name, rechargeAmount, discountRate, discountWay, descript, grade,giftAmount, versionFlag);
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
	 * 管理优惠政策实体
	 * @Title: FavouredPolicyAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(favouredPolicyService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageFavouredPolicy";
	}
	
	/**
	 * 查看回收站
	 * @Title: FavouredPolicyAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(favouredPolicyService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleFavouredPolicy";
	}
	
	/**
	 * 逻辑删除优惠政策对象
	 * @Title: FavouredPolicyAction
	 * @Description: 把优惠政策对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = favouredPolicyService.logicDeleteEntity(FavouredPolicy.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除优惠政策对象
	 * @Title: FavouredPolicyAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = favouredPolicyService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个优惠政策对象
	 * @Title: FavouredPolicyAction
	 * @Description: 从回收站还原优惠政策对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = favouredPolicyService.restoreEntity(FavouredPolicy.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核优惠政策对象
	 * @Title: FavouredPolicyAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = favouredPolicyService.auditEntity(FavouredPolicy.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: FavouredPolicyAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = favouredPolicyService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public FavouredPolicy getFavouredPolicy() {
		return favouredPolicy;
	}
	public void setFavouredPolicy(FavouredPolicy favouredPolicy) {
		this.favouredPolicy = favouredPolicy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFavouredPolicyNo() {
		return favouredPolicyNo;
	}
	public void setFavouredPolicyNo(String favouredPolicyNo) {
		this.favouredPolicyNo = favouredPolicyNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public Double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}
	public String getDiscountWay() {
		return discountWay;
	}
	public void setDiscountWay(String discountWay) {
		this.discountWay = discountWay;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getGrade() {
		return grade;
	}
}
