package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.Evaluation;
import com.ticket.pojo.EvaluationSetting;
import com.ticket.service.IBjdjCommentKeywordStatisticsService;
import com.ticket.service.IBjdjOrderService;
import com.ticket.service.IBjdjServiceCodeService;
import com.ticket.service.IEvaluationService;
import com.ticket.service.IEvaluationSettingService;
import com.ticket.service.IMemberService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.service.ITreeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 评价系统前端请求控制器
 * @author tuyou
 */
public class EvaluationAction extends BaseAction{

	public static final long serialVersionUID = 1L;
	@Resource
	public IEvaluationSettingService evaluationSettingService;
	@Resource
	public IEvaluationService evaluationService;
	@Resource
	public IBjdjOrderService orderService;
	@Resource
	public IMemberService memberService;
	@Resource
	public ISystemDictionaryService dictionaryService;
	@Resource
	public IBjdjServiceCodeService serviceCodeService;
	@Resource
	public IBjdjCommentKeywordStatisticsService keywordStatisticsService;
	@Resource
	public ITreeService<EvaluationSetting, String> treeService;
	
	//便捷登机评论表实体
	public Evaluation evaluation;
	public List<Evaluation> evaluations;
	//主键
	public String id;
	//评价大类
	public String classes;
	//评价项目
	public String project;
	//评价指标
	public String[] targetId;
	//评价指标打分
	public Integer[] star;
    //评论内容
	public String content;
    //用户ID
	public String member_id;
	//服务码id
	public String serviceCode_id;
	//图片
	public String images;
	
	/**
	 * 设置已读
	 * @return
	 * @throws ServiceException 
	 */
	public String isRend() throws ServiceException{
		Evaluation evaluation = evaluationService.queryById(Evaluation.class.getName(), id);
		evaluation.setRend(true);
		evaluationService.merge(evaluation);
		data = AjaxData.responseSuccess("已读");
		return JSON;
	}
	
	/**
	 * 评价页
	 * @return
	 */
	public String evaluatePage(){
		
		List<EvaluationSetting> classess = treeService.queryRootNode(EvaluationSetting.class); 
		ActionContext.getContext().put("classess", classess);
		
		return "evaluatePage";
	}
	
	/**
	 * 我的评价页
	 * @return
	 */
	public String myEvaluatePage(){
		
		return "myEvaluatePage";
	}
	
	/**
	 * 评价列表页
	 * @return
	 */
	public String listPage(){
		
		long count = evaluationService.count();
		ActionContext.getContext().put("pageSize", ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		ActionContext.getContext().put("count", count);
		
		//关键词显示
//		int showCount = Integer.parseInt(dictionaryService.getValueByName("keywords_show_count"));
//		List<BjdjCommentKeywordStatistics> keywords = keywordStatisticsService.getMax(showCount);
//		ActionContext.getContext().put("keywords", keywords);
		
		return "listPage";
	}

	/**
	 * 评价
	 * @return
	 * @throws ServiceException 
	 */
	public String evaluate(){
		
		//保存便捷登机评论表实体
		try {
			
			evaluation = evaluationService.add(classes, project, targetId, star, content, images);
		} catch (ServiceException e) {
			
			data = AjaxData.responseError(e.getMessage());
			return JSON;
		}
		data = AjaxData.responseSuccess("评价成功，请前往个人中心查看评论或反馈。");
		return JSON;
	}
	
	/**
	 * 评价页面异步加载的评论项
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public String list() throws ServiceException {
		
		pageSize = 30;
		String tableName = Evaluation.class.getName();
		evaluations = evaluationService.getDbDAO().executeJPQLForQuery(
				"select s from " + tableName + " s where s.status.deleteFlag=0 and s.state.name='published_state' order by s.status.createTime desc"
				, pn, pageSize);
		
		//直接返回页面
		if("html".equals(operationFlag)){
			
			return "listItem";
		}
		
		//只返回数据
		JSONArray array = new JSONArray();
		for(Evaluation e : evaluations){
			
			JSONObject object = new JSONObject();
			object.put("content", e.getContent());
			object.put("images", e.getImages());
			array.add(object);
		}
		data = array.toString();
		return TEXT;
	}
	
	/**
	 * 我的评论项分页查询
	 * @return
	 * @throws ServiceException
	 */
	public String myList() throws ServiceException {
		
		pageSize = 30;
		String tableName = Evaluation.class.getName();
		evaluations = evaluationService.getDbDAO().executeJPQLForQuery(
				"select s from " + tableName + " s where s.status.deleteFlag=0 and s.member=? order by s.status.createTime desc"
				, pn, pageSize, getSessionMember());
			
		//直接返回页面
		if("html".equals(operationFlag)){
			
			return "myListItem";
		}
		
		//只返回数据
		JSONArray array = new JSONArray();
		for(Evaluation e : evaluations){
			
			JSONObject object = new JSONObject();
			object.put("content", e.getContent());
			object.put("images", e.getImages());
			array.add(object);
		}
		data = array.toString();
		return TEXT;
	}
	
	/**
	 * 逻辑删除问题选项对象
	 * @Title: SurveyOptionAction
	 * @Description: 把问题选项对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = evaluationService.logicDeleteEntity(Evaluation.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	public IEvaluationSettingService getEvaluationSettingService() {
		return evaluationSettingService;
	}

	public void setEvaluationSettingService(
			IEvaluationSettingService evaluationSettingService) {
		this.evaluationSettingService = evaluationSettingService;
	}

	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
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

	public IBjdjCommentKeywordStatisticsService getKeywordStatisticsService() {
		return keywordStatisticsService;
	}

	public void setKeywordStatisticsService(
			IBjdjCommentKeywordStatisticsService keywordStatisticsService) {
		this.keywordStatisticsService = keywordStatisticsService;
	}

	public ITreeService<EvaluationSetting, String> getTreeService() {
		return treeService;
	}

	public void setTreeService(ITreeService<EvaluationSetting, String> treeService) {
		this.treeService = treeService;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String[] getTargetId() {
		return targetId;
	}

	public void setTargetId(String[] targetId) {
		this.targetId = targetId;
	}

	public Integer[] getStar() {
		return star;
	}

	public void setStar(Integer[] star) {
		this.star = star;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getPn() {
		return pn;
	}

	public void setPn(Integer pn) {
		this.pn = pn;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
}
