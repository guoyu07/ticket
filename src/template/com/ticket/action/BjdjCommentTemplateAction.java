package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjComment;
import com.ticket.pojo.BjdjCommentKeywordStatistics;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IBjdjCommentKeywordStatisticsService;
import com.ticket.service.IBjdjCommentService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.UrlUtil;

/**
 * @Description：便捷登机评价表
 * @author：涂有
 * @date 2015年10月31日 下午4:45:14
 */
public class BjdjCommentTemplateAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Resource
	private IBjdjCommentService commentService;
	@Resource
	private IBjdjOrderService orderService;
	@Resource
	private IMemberService memberService;
	@Resource
	private ISystemDictionaryService dictionaryService;
	@Resource
	private IBjdjServiceCodeService serviceCodeService;
	@Resource
	private IBjdjCommentKeywordStatisticsService keywordStatisticsService;
	
	
	//便捷登机评论表实体
	private BjdjComment comment = null;
	//主键
	private String id = null;
    //评论内容
	private String content = null;
    //用户ID
	private String member_id = null;
    //评论时所在的IP地址
	private String ip = null;
    //评论日期
	private Date time = null;
	//服务码id
	private String serviceCode_id;
	//评价星数
	private Integer star;
	//图片
	private String images;
	//是否显示
	private boolean showName;
	//自定义评论项
	private String ruleId;
	//自定义打分
	private String rule;
	//原因
	private String reason;
	//回复
	private String feedback;
	
	//分页开始值
	private Integer pn;
	
	private String bjdjPage_id;
	
	/**
	 * @Description：默认跳到评论列表页面
	 * @return
	 */
	public String page(){
		
		//自定义评论规则
		List<SystemDictionary> evaluate_rule = dictionaryService.querySubByParentName("evaluate_rule");
		ActionContext.getContext().put("evaluate_rule", evaluate_rule);
		
		return "page";
	}
	
	/**
	 * 添加便捷登机评论表
	 * @Title: BjdjCommentAction
	 * @Description:   
	 * @return
	 * @throws ServiceException   
	 */
	public String add() throws ServiceException {

		try {
			ip = UrlUtil.getIpAddr(request);
			commentService.addComment(content, serviceCode_id, ip, star, images, showName, ruleId.split(","), rule.split(","), reason.split(","));
		} catch (Exception e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		
		data = AjaxData.responseSuccess(getText("comment.success"));
		return JSON;
	}
	
	/**
	 * @Description：进入评论列表页面
	 * @return
	 */
	public String listPage()throws Exception{
		
		long count = commentService.count();
		ActionContext.getContext().put("pageSize", ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		ActionContext.getContext().put("count", count);
		
		//关键词显示
		int showCount = Integer.parseInt(dictionaryService.getValueByName("keywords_show_count"));
		List<BjdjCommentKeywordStatistics> keywords = keywordStatisticsService.getMax(showCount);
		ActionContext.getContext().put("keywords", keywords);
		
		return "list";
	}
	
	/**
	 * 管理便捷登机评论表实体
	 * @Title: BjdjCommentAction
	 * @Description:    
	 * @return
	 * @throws ServiceException    
	 */
	public String list() throws ServiceException {
		
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
//		Pagination page = commentService.queryEntityByAdmin(versionFlag, pn, PaginationContext.getPagesize());
		Pagination page = null;
		if(GeneralUtil.isNull(bjdjPage_id)){
			page = commentService.paginationQuery("select b from BjdjComment b,BjdjServiceCode c,BjdjOrder d,BjdjServicePackage e,BjdjPage f where b.serviceCode.id = c.id and c.order.id = d.id and d.packageType.id = e.id and e.bjdjPage.id = f.id and f.url = ? and b.status.deleteFlag = 0 order by b.status.createTime ", "#");
		}else{
			page = commentService.paginationQuery("select b from BjdjComment b,BjdjServiceCode c,BjdjOrder d,BjdjServicePackage e,BjdjPage f where b.serviceCode.id = c.id and c.order.id = d.id and d.packageType.id = e.id and e.bjdjPage.id = f.id and f.id = ? and b.status.deleteFlag = 0 order by b.status.createTime ", bjdjPage_id);
		}
		
		if(page != null && page.getPageList() != null){
			
			ActionContext.getContext().put("comments", page.getPageList());
		}
		return "listItem";
	}
	
	public IBjdjCommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(IBjdjCommentService commentService) {
		this.commentService = commentService;
	}

	public BjdjComment getBjdjComment() {
		return comment;
	}

	public void setBjdjComment(BjdjComment bjdjComment) {
		this.comment = bjdjComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrder_id() {
		return member_id;
	}

	public void setOrder_id(String member_id) {
		this.member_id = member_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOrderId() {
		return serviceCode_id;
	}

	public void setOrderId(String orderId) {
		this.serviceCode_id = orderId;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	
	public Integer getPn() {
		return pn;
	}

	public void setPn(Integer pn) {
		this.pn = pn;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getServiceCode_id() {
		return serviceCode_id;
	}

	public void setServiceCode_id(String serviceCode_id) {
		this.serviceCode_id = serviceCode_id;
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

	public ISystemDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(ISystemDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public IBjdjServiceCodeService getServiceCodeService() {
		return serviceCodeService;
	}

	public void setServiceCodeService(IBjdjServiceCodeService serviceCodeService) {
		this.serviceCodeService = serviceCodeService;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public BjdjComment getComment() {
		return comment;
	}

	public void setComment(BjdjComment comment) {
		this.comment = comment;
	}

	public IBjdjCommentKeywordStatisticsService getKeywordStatisticsService() {
		return keywordStatisticsService;
	}

	public void setKeywordStatisticsService(
			IBjdjCommentKeywordStatisticsService keywordStatisticsService) {
		this.keywordStatisticsService = keywordStatisticsService;
	}

	public String getBjdjPage_id() {
		return bjdjPage_id;
	}

	public void setBjdjPage_id(String bjdjPage_id) {
		this.bjdjPage_id = bjdjPage_id;
	}
}
