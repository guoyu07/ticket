package com.ticket.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationSetting;
import com.ticket.service.IEvaluationService;

/**
 * pc的评价部门功能的请求控制器
 * @author tuyou
 */
public class PCEvaluationAction extends EvaluationAdminAction {

	private static final long serialVersionUID = 1L;

	@Resource
	protected IEvaluationService evaluationService;
	
	/**
	 * 打分多少
	 */
	protected Integer[] score;
	
	/**
	 * 默认跳转到评价页面
	 */
	public String evaluationPage(){
		
		List<EvaluationSetting> classess = treeService.queryRootNode(EvaluationSetting.class); 
		ActionContext.getContext().put("classess", classess);
		
		return "evaluationPage";
	}
	
	/**
	 * 评价
	 * @return
	 */
	public String evaluation(){

		this.star = score;
		return super.evaluate();
	}
	
	/**
	 * 管理服务厅表实体
	 * @Title: BjdjHallAction
	 * @Description:    
	 * @return
	 * @throws ServiceException    
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		// s.state.name='published_state' order by s.status.createTime desc
		this.setPageModule(evaluationService.queryEntity(" and s.state.name='published_state'", "s.status.createTime desc"));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manage";
	}

	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

	public Integer[] getScore() {
		return score;
	}

	public void setScore(Integer[] score) {
		this.score = score;
	}
}
