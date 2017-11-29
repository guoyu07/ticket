package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberFocusFlight;
import com.ticket.service.IMemberFocusFlightService;
import com.ticket.util.GeneralUtil;

/**
 * 会员关注航班控制器
 * 
 * @ClassName: MemberFocusFlightAction
 * @Description: 提供会员关注航班的相关操作方法.
 * @author HiSay
 * @date 2015-12-04 16:03:42
 * 
 */
public class MemberFocusFlightAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 会员关注航班的业务层
	@Resource
	private IMemberFocusFlightService memberFocusFlightService = null;
	// 会员关注航班实体
	private MemberFocusFlight memberFocusFlight = null;
	// 主键
	private String id = null;
	// 会员id
	private String member_id = null;
	// 航班号
	private String flightNumber = null;
	// 航班日期
	private String flightDate = null;
	// 会员角色
	private String memberRole = null;
	// 航班状态
	private String flightState = null;
	// 是否携带行李
	private Integer ifTakeLuggage = null;
	// 携带特殊人员
	private String takePerson = null;
	// 乘机人数
	private Integer personCount = null;
	// 是否已设置过旅程
	private Integer ifSet = null;
	// 电话号码
	private String mobile = null;
	// 座位号
	private String seatNo = null;
	// 电子客票号
	private String ticketNo = null;
	// 值机标识
	private String couponId = null;

	/**
	 * 添加会员关注航班
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String add() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			return "addMemberFocusFlight";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightNumber)) {
				data = AjaxData.responseError(getText("flightNumber.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightDate)) {
				data = AjaxData.responseError(getText("flightDate.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(memberRole)) {
				data = AjaxData.responseError(getText("memberRole.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightState)) {
				data = AjaxData.responseError(getText("flightState.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(ifTakeLuggage)) {
				data = AjaxData.responseError(getText("ifTakeLuggage.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(takePerson)) {
				data = AjaxData.responseError(getText("takePerson.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(personCount)) {
				data = AjaxData.responseError(getText("personCount.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(ifSet)) {
				data = AjaxData.responseError(getText("ifSet.required"));
				return JSON;
			}
			// 保存会员关注航班实体
			try {
				memberFocusFlightService.persist(member_id,
						flightNumber, flightDate, memberRole, flightState,
						ifTakeLuggage, takePerson, personCount, ifSet, mobile,
						seatNo, ticketNo, couponId, versionFlag);
			} catch (Exception e) {
				e.printStackTrace();
				data = AjaxData.responseError(getText("addFailed"));
				return JSON;
			}
			data = AjaxData.responseSuccess(getText("addSuccess"));
			return JSON;
		}
	}

	/**
	 * 修改会员关注航班
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String edit() throws ServiceException {
		if (GeneralUtil.isNull(operationFlag)) {
			this.setMemberFocusFlight(memberFocusFlightService.queryById(
					MemberFocusFlight.class.getSimpleName(), id));
			return "editMemberFocusFlight";
		} else {
			// 非空验证.
			if (GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightNumber)) {
				data = AjaxData.responseError(getText("flightNumber.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightDate)) {
				data = AjaxData.responseError(getText("flightDate.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(memberRole)) {
				data = AjaxData.responseError(getText("memberRole.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(flightState)) {
				data = AjaxData.responseError(getText("flightState.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(ifTakeLuggage)) {
				data = AjaxData.responseError(getText("ifTakeLuggage.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(takePerson)) {
				data = AjaxData.responseError(getText("takePerson.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(personCount)) {
				data = AjaxData.responseError(getText("personCount.required"));
				return JSON;
			}
			if (GeneralUtil.isNull(ifSet)) {
				data = AjaxData.responseError(getText("ifSet.required"));
				return JSON;
			}
			// 修改会员关注航班实体
			boolean isSuc = memberFocusFlightService.merge(id, member_id,
					flightNumber, flightDate, memberRole, flightState,
					ifTakeLuggage, takePerson, personCount, ifSet, mobile,
					seatNo, ticketNo, couponId, versionFlag);
			// 根据修改结果返回页面
			if (isSuc) {
				data = AjaxData.responseSuccess(getText("editSuccess"));
			} else {
				data = AjaxData.responseError(getText("editFailed"));
			}
			return JSON;
		}
	}

	/**
	 * 管理会员关注航班实体
	 * 
	 * @Title: MemberFocusFlightAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0]
				.getMethodName());
		this.setPageModule(memberFocusFlightService.queryEntityByAdmin(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberFocusFlight";
	}

	/**
	 * 查看回收站
	 * 
	 * @Title: MemberFocusFlightAction
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberFocusFlightService.queryRecycleEntity(
				versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberFocusFlight";
	}

	/**
	 * 逻辑删除会员关注航班对象
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description: 把会员关注航班对象放入回收站.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberFocusFlightService.logicDeleteEntity(
				MemberFocusFlight.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 物理删除会员关注航班对象
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description: 把配置对象实际从数据库里面删除.
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberFocusFlightService.remove(id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}

	/**
	 * 还原一个会员关注航班对象
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description: 从回收站还原会员关注航班对象
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberFocusFlightService.restoreEntity(
				MemberFocusFlight.class.getSimpleName(), id);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}

	/**
	 * 审核会员关注航班对象
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException 设定文件
	 * @return 返回类型
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberFocusFlightService.auditEntity(
				MemberFocusFlight.class.getSimpleName(), id, statusValue);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}

	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * 
	 * @Title: MemberFocusFlightAction
	 * @Description:
	 * @param @return
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberFocusFlightService.batchOperationEntity(
				versionFlag, idsValue, batchOperationType, isChecked);
		if (isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}

	public MemberFocusFlight getMemberFocusFlight() {
		return memberFocusFlight;
	}

	public void setMemberFocusFlight(MemberFocusFlight memberFocusFlight) {
		this.memberFocusFlight = memberFocusFlight;
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

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public String getFlightState() {
		return flightState;
	}

	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}

	public Integer getIfTakeLuggage() {
		return ifTakeLuggage;
	}

	public void setIfTakeLuggage(Integer ifTakeLuggage) {
		this.ifTakeLuggage = ifTakeLuggage;
	}

	public String getTakePerson() {
		return takePerson;
	}

	public void setTakePerson(String takePerson) {
		this.takePerson = takePerson;
	}

	public Integer getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Integer personCount) {
		this.personCount = personCount;
	}

	public Integer getIfSet() {
		return ifSet;
	}

	public void setIfSet(Integer ifSet) {
		this.ifSet = ifSet;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

}
