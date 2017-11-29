package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.MemberMac;
import com.ticket.pojo.MemberSendInfo;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SqlParam.Condition;
import com.ticket.pojo.SqlParamGroup;
import com.ticket.service.IMemberMacService;
import com.ticket.service.IMemberSendInfoService;
import com.ticket.util.DateUtil;
import com.ticket.util.ValidateUtil;

/**
 * 会员信息发送记录控制器
 * @ClassName: MemberSendInfoAction   
 * @Description:  提供会员信息发送记录的相关操作方法. 
 * @author HiSay  
 * @date 2016-02-03 20:53:58
 */
public class MemberSendInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//会员信息发送记录的业务层
	@Resource private IMemberSendInfoService memberSendInfoService = null;
	//用户mac业务管理层
	@Resource private IMemberMacService memberMacService;
	//会员信息发送记录实体
	private MemberSendInfo memberSendInfo = null;
	//主键
	private String id = null;
    //会员id
	private String member_id = null;
    //电话
	private String phone = null;
    //信息id
	private String info_id = null;
	//标题
	private String title;
	//mac地址
	private String mac;
	//查询时间段
	private Date startTime, endTime;
	
	/**
	 * 下载报表
	 * @return
	 * @throws ServiceException
	 */
	public String downReport() {
		
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and(new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime)));
		group.and("s.title", Condition.like_left, title);
		
		String sql = group.toString(true);
		if(ValidateUtil.isNotNull(phone)){
			
			sql += " and exists (select m from "+Member.class.getName()+" m where m.phone = '"+phone+"' and m.id=s.member_id)";
		}
		if(ValidateUtil.isNotNull(mac)){
			
			sql += " and exists (select mm from "+MemberMac.class.getName()+" mm where mm.mac = '"+mac+"' and exists (select m from "+Member.class.getName()+" m where m.phone = mm.phone and m.id=s.member_id))";
		}
		List<MemberSendInfo> list = memberSendInfoService.getDbDAO().executeJPQLForQuery(
				"select new MemberSendInfo("
						+ "s.status, "
						+ "(select m.phone from "+Member.class.getName()+" m where m.id=s.member_id), "
						+ "s.method, s.title, s.message, s.url, "
//						+ "(select mm.mac from "+MemberMac.class.getName()+" mm where mm.phone = (select m.phone from "+Member.class.getName()+" m where m.id=s.member_id)),"
						+ "s.h5"
						+ ")"
				+ " from " + MemberSendInfo.class.getName() + " s "
				+ sql + " order by s.status.createTime desc", group.getParamArray());
		exportExcel("/WEB-INF/excel/jasper/memberSendInfo.jasper", list, "用户消息报表");
		return null;
	}
	
	/**
	 * 管理会员信息发送记录实体
	 * @Title: MemberSendInfoAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		SqlParamGroup group = new SqlParamGroup("s.status.deleteFlag", Condition.eq, ContextConstants.STATUS_OF_ZERO).and("s.status.versionFlag", Condition.eq, versionFlag);
		group.and(new SqlParamGroup("s.status.createTime", Condition.ge, DateUtil.getDayStart(startTime)).and("s.status.createTime", Condition.le, DateUtil.getDayEnd(endTime)));
		group.and("s.title", Condition.like_left, title);
		
		String sql = group.toString(true);
		if(ValidateUtil.isNotNull(phone)){
			
			sql += " and exists (select m from "+Member.class.getName()+" m where m.phone = '"+phone+"' and m.id=s.member_id)";
		}
		if(ValidateUtil.isNotNull(mac)){
			
			sql += " and exists (select mm from "+MemberMac.class.getName()+" mm where mm.mac = '"+mac+"' and exists (select m from "+Member.class.getName()+" m where m.phone = mm.phone and m.id=s.member_id))";
		}
		Pagination list = memberSendInfoService.paginationQuery("select s from " + MemberSendInfo.class.getName() + " s "
					+ sql + " order by s.status.createTime desc", group.getParamArray());
		this.setPageModule(list);
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberSendInfo";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberSendInfoAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberSendInfoService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberSendInfo";
	}
	
	/**
	 * 逻辑删除会员信息发送记录对象
	 * @Title: MemberSendInfoAction
	 * @Description: 把会员信息发送记录对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberSendInfoService.logicDeleteEntity(MemberSendInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除会员信息发送记录对象
	 * @Title: MemberSendInfoAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberSendInfoService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个会员信息发送记录对象
	 * @Title: MemberSendInfoAction
	 * @Description: 从回收站还原会员信息发送记录对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberSendInfoService.restoreEntity(MemberSendInfo.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核会员信息发送记录对象
	 * @Title: MemberSendInfoAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberSendInfoService.auditEntity(MemberSendInfo.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberSendInfoAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberSendInfoService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MemberSendInfo getMemberSendInfo() {
		return memberSendInfo;
	}
	public void setMemberSendInfo(MemberSendInfo memberSendInfo) {
		this.memberSendInfo = memberSendInfo;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public IMemberSendInfoService getMemberSendInfoService() {
		return memberSendInfoService;
	}

	public void setMemberSendInfoService(
			IMemberSendInfoService memberSendInfoService) {
		this.memberSendInfoService = memberSendInfoService;
	}

	public IMemberMacService getMemberMacService() {
		return memberMacService;
	}

	public void setMemberMacService(IMemberMacService memberMacService) {
		this.memberMacService = memberMacService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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
