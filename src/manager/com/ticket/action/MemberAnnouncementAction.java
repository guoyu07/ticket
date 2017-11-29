package com.ticket.action;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.MemberAnnouncement;
import com.ticket.pojo.News;
import com.ticket.service.IMemberAnnouncementService;
import com.ticket.util.GeneralUtil;

/**
 * 通知中心信息阅读控制器
 * @ClassName: MemberAnnouncementAction   
 * @Description:  提供通知中心信息阅读的相关操作方法. 
 * @author HiSay  
 * @date 2016-09-22 16:00:51
 *
 */
public class MemberAnnouncementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//通知中心信息阅读的业务层
	@Resource private IMemberAnnouncementService memberAnnouncementService;
	//通知中心信息阅读实体
	private MemberAnnouncement memberAnnouncement;
	//主键
	private String id;
    //用户游客id
	private String member_id;
    //已阅读新闻信息
	private News news;
	
	/**
	 * 添加通知中心信息阅读
	 * @Title: MemberAnnouncementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addMemberAnnouncement";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(news)) {
				data = AjaxData.responseError(getText("news.required"));
				return JSON;
			}
			//保存通知中心信息阅读实体
			boolean isSuc = memberAnnouncementService.persist(member_id, news, versionFlag);
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
	 * 修改通知中心信息阅读
	 * @Title: MemberAnnouncementAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setMemberAnnouncement(memberAnnouncementService.queryById(MemberAnnouncement.class.getSimpleName(), id));
			return "editMemberAnnouncement";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(member_id)) {
				data = AjaxData.responseError(getText("member_id.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(news)) {
				data = AjaxData.responseError(getText("news.required"));
				return JSON;
			}
			//修改通知中心信息阅读实体
			boolean isSuc = memberAnnouncementService.merge(id, member_id, news,  versionFlag);
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
	 * 管理通知中心信息阅读实体
	 * @Title: MemberAnnouncementAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(memberAnnouncementService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageMemberAnnouncement";
	}
	
	/**
	 * 查看回收站
	 * @Title: MemberAnnouncementAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(memberAnnouncementService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleMemberAnnouncement";
	}
	
	/**
	 * 逻辑删除通知中心信息阅读对象
	 * @Title: MemberAnnouncementAction
	 * @Description: 把通知中心信息阅读对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = memberAnnouncementService.logicDeleteEntity(MemberAnnouncement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除通知中心信息阅读对象
	 * @Title: MemberAnnouncementAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = memberAnnouncementService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个通知中心信息阅读对象
	 * @Title: MemberAnnouncementAction
	 * @Description: 从回收站还原通知中心信息阅读对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = memberAnnouncementService.restoreEntity(MemberAnnouncement.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核通知中心信息阅读对象
	 * @Title: MemberAnnouncementAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = memberAnnouncementService.auditEntity(MemberAnnouncement.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: MemberAnnouncementAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = memberAnnouncementService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	public MemberAnnouncement getMemberAnnouncement() {
		return memberAnnouncement;
	}
	public void setMemberAnnouncement(MemberAnnouncement memberAnnouncement) {
		this.memberAnnouncement = memberAnnouncement;
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
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
}
