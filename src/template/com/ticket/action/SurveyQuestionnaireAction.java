package com.ticket.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ticket.bo.AjaxData;
import com.ticket.constants.ContextConstants;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.QuestionnaireAnswer;
import com.ticket.pojo.QuestionnaireSendLog;
import com.ticket.pojo.QuestionnaireSendRecord;
import com.ticket.pojo.StationLetterReadLog;
import com.ticket.pojo.SurveyQuestion;
import com.ticket.pojo.SurveyQuestionnaire;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ILetterStationService;
import com.ticket.service.IQuestionnaireAnswerService;
import com.ticket.service.IQuestionnaireSendLogService;
import com.ticket.service.IQuestionnaireSendRecordService;
import com.ticket.service.IStationLetterReadLogService;
import com.ticket.service.ISurveyOptionService;
import com.ticket.service.ISurveyQuestionService;
import com.ticket.service.ISurveyQuestionnaireService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.UrlUtil;

/**
 * 调查问卷控制器
 * @ClassName: SurveyQuestionnaireAction   
 * @Description:  提供调查问卷的相关操作方法. 
 * @author HiSay  
 * @date 2015-11-11 17:10:59
 *
 */
public class SurveyQuestionnaireAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	//调查问卷的业务层
	@Resource private ISurveyQuestionnaireService surveyQuestionnaireService = null;
	@Resource private ISurveyQuestionService surveyQuestionService = null;
	@Resource private IDepartmentInfoService departmentInfoService = null;//部门信息的业务层
	@Resource private IEmployeeInfoService employeeInfoService = null;//员工信息的业务层
	@Resource private IQuestionnaireSendRecordService questionnaireSendRecordService = null;//问卷发放记录的业务层
	@Resource private IChannelCustomerInfoService channelCustomerInfoService = null;//渠道用户的业务层
	@Resource private IQuestionnaireSendLogService questionnaireSendLogService = null;//渠道用户的业务层
	@Resource private ILetterStationService letterStationService = null;//站内信的业务层
	@Resource private IQuestionnaireAnswerService questionnaireAnswerService = null;//问卷答案的业务层
	@Resource private ISurveyOptionService surveyOptionService = null;//问卷答案的业务层
	@Resource private IStationLetterReadLogService stationLetterReadLogService = null;//问卷答案的业务层
	//调查问卷实体
	private SurveyQuestionnaire surveyQuestionnaire = null;
	//主键
	private String id = null;
    //问卷编号
	private String survryNo = null;
    //问卷标题
	private String title = null;
    //问卷描述
	private String descript = null;
    //问卷模块类型
	private Integer type = null;
    //调查模块ID
	private String survryModularId = null;
    //实体表名
	private String entityName = null;
    //实体ID
	private String entityId = null;
    //当前开放问卷
	private Integer currentOpenUp = null;
	//问卷截止日期
	private Date deadLine = null;
	//部门树
	private String deptHtml = null;
	
	//问卷问题列表
	private List<SurveyQuestion> questionList = null;
	//选择的id列表
	private String idsValue = null;
	//发送问卷的对象
	private String sendType = null;
	//问卷发放日志
	private QuestionnaireSendLog questionnaireSendLog = null;
	//问卷问题编号
	private String questionNo = null;
	//问卷答案列表
	private List<QuestionnaireAnswer> questionnaireAnswerList = null;
	//问题实体
	private String questionTitle = null;
	
	/**
	 * 添加调查问卷
	 * @Title: SurveyQuestionnaireAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	public String add() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			return "addSurveyQuestionnaire";
		} else {
			//非空验证.
			/*if(GeneralUtil.isNull(survryNo)) {
				data = AjaxData.responseError(getText("survryNo.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(type)) {
//				data = AjaxData.responseError(getText("type.required"));
//				return JSON;
//			}
			if(GeneralUtil.isNull(survryModularId)) {
				data = AjaxData.responseError(getText("survryModularId.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(entityName)) {
				data = AjaxData.responseError(getText("entityName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(entityId)) {
				data = AjaxData.responseError(getText("entityId.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(currentOpenUp)) {
				//data = AjaxData.responseError(getText("currentOpenUp.required"));
				currentOpenUp=0;
			}
			/**
			 * 验证标题是否已存在
			 */
			if(surveyQuestionnaireService.validateTitleExist(title,versionFlag) != null){
				data = AjaxData.responseError(getText("title.exist"));
				return JSON;
			}
			//保存调查问卷实体
			boolean isSuc = surveyQuestionnaireService.persist(survryNo, title, descript, type, survryModularId, entityName, entityId, currentOpenUp,deadLine, versionFlag);
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
	 * 修改调查问卷
	 * @Title: SurveyQuestionnaireAction
	 * @Description:   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String edit() throws ServiceException {
		if(GeneralUtil.isNull(operationFlag)) {
			this.setSurveyQuestionnaire(surveyQuestionnaireService.queryById(SurveyQuestionnaire.class.getSimpleName(), id));
			return "editSurveyQuestionnaire";
		} else {
			//非空验证.
			if(GeneralUtil.isNull(id)) {
				data = AjaxData.responseError(getText("id.required"));
				return JSON;
			}
			/*if(GeneralUtil.isNull(survryNo)) {
				data = AjaxData.responseError(getText("survryNo.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(title)) {
				data = AjaxData.responseError(getText("title.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(descript)) {
				data = AjaxData.responseError(getText("descript.required"));
				return JSON;
			}
//			if(GeneralUtil.isNull(type)) {
//				data = AjaxData.responseError(getText("type.required"));
//				return JSON;
//			}
			if(GeneralUtil.isNull(survryModularId)) {
				data = AjaxData.responseError(getText("survryModularId.required"));
				return JSON;
			}
		    /*if(GeneralUtil.isNull(entityName)) {
				data = AjaxData.responseError(getText("entityName.required"));
				return JSON;
			}
			if(GeneralUtil.isNull(entityId)) {
				data = AjaxData.responseError(getText("entityId.required"));
				return JSON;
			}*/
			if(GeneralUtil.isNull(currentOpenUp)) {
				//data = AjaxData.responseError(getText("currentOpenUp.required"));
				currentOpenUp=0;
			}
			
			/**
			 * 验证标题是否已存在
			 */
			if(surveyQuestionnaireService.validateTitleExist(title,versionFlag) != null){
				SurveyQuestionnaire obj = surveyQuestionnaireService.validateTitleExist(title,versionFlag);
				if(!id.equals(obj.getId())){
					data = AjaxData.responseError(getText("title.exist"));
					return JSON;
				}
			}
			//修改调查问卷实体
			boolean isSuc = surveyQuestionnaireService.merge(id, survryNo, title, descript, type, survryModularId, entityName, entityId, currentOpenUp,deadLine, versionFlag);
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
	 * 管理调查问卷实体
	 * @Title: SurveyQuestionnaireAction
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String manage() throws ServiceException {
		this.setCurrentMethod(new Exception().getStackTrace()[0].getMethodName());
		this.setPageModule(surveyQuestionnaireService.queryEntityByAdmin(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "manageSurveyQuestionnaire";
	}
	
	/**
	 * 查看回收站
	 * @Title: SurveyQuestionnaireAction
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String recycle() throws ServiceException {
		this.setPageModule(surveyQuestionnaireService.queryRecycleEntity(versionFlag, ContextConstants.ADMIN_COMMON_PAGE_SIZE));
		this.setPageSize(ContextConstants.ADMIN_COMMON_PAGE_SIZE);
		return "recycleSurveyQuestionnaire";
	}
	
	/**
	 * 逻辑删除调查问卷对象
	 * @Title: SurveyQuestionnaireAction
	 * @Description: 把调查问卷对象放入回收站.   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String logicDelete() throws ServiceException {
		boolean isSuc = surveyQuestionnaireService.logicDeleteEntity(SurveyQuestionnaire.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 物理删除调查问卷对象
	 * @Title: SurveyQuestionnaireAction
	 * @Description: 把配置对象实际从数据库里面删除.   
	 * @param @return
	 * @param @throws ServiceException   
	 * @return   
	 * @throws
	 */
	public String realDelete() throws ServiceException {
		boolean isSuc = surveyQuestionnaireService.remove(id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("deleteSuccess"));
		} else {
			data = AjaxData.responseError(getText("deleteFailed"));
		}
		return JSON;
	}
	
	/**
	 * 还原一个调查问卷对象
	 * @Title: SurveyQuestionnaireAction
	 * @Description: 从回收站还原调查问卷对象   
	 * @param @return
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	public String restore() throws ServiceException {
		boolean isSuc = surveyQuestionnaireService.restoreEntity(SurveyQuestionnaire.class.getSimpleName(), id);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("restoreSuccess"));
		} else {
			data = AjaxData.responseError(getText("restoreFailed"));
		}
		return JSON;
	}
	
	/**
	 * 审核调查问卷对象
	 * @Title: SurveyQuestionnaireAction
	 * @Description:  
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	public String audit() throws ServiceException {
		boolean isSuc = surveyQuestionnaireService.auditEntity(SurveyQuestionnaire.class.getSimpleName(), id, statusValue);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("auditSuccess"));
		} else {
			data = AjaxData.responseError(getText("auditFailed"));
		}
		return JSON;
	}
	
	/**
	 * 批量操作实体状态值(审核\推荐\热门\还原\逻辑删除等)
	 * @Title: SurveyQuestionnaireAction
	 * @Description:    
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	public String batchOperation() throws ServiceException {
		boolean isSuc = surveyQuestionnaireService.batchOperationEntity(versionFlag, idsValue, batchOperationType, isChecked);
		if(isSuc) {
			data = AjaxData.responseSuccess(getText("batchSuccess"));
		} else {
			data = AjaxData.responseError(getText("batchFailed"));
		}
		return JSON;
	}
	
	/**
	 * 查看问卷详情
	 * @return
	 * @throws ServiceException
	 */
	public String showQuestionnaireDetail() throws ServiceException{
		
		//验证问卷是否过期
		if(surveyQuestionnaireService.validateDeadlineExpire(id,versionFlag)){
			return "questionnaireExpire";
		}
		QuestionnaireSendRecord qsr = null;
		if(getSystemEmployeeInfo() != null){
			qsr = questionnaireSendRecordService.queryByObjectId(getSystemEmployeeInfo().getId(),versionFlag);
			if(qsr != null){
				if(qsr.getIsWrite() == 1){//如果已经填写过问卷，则不能重复填写
					return "questionnaireHasWrite";
				}
			}
		}
		if(getSystemChannelCustomer() != null){
			qsr = questionnaireSendRecordService.queryByObjectId(getSystemChannelCustomer().getId(),versionFlag);
			if(qsr != null){
				if(qsr.getIsWrite() == 1){//如果已经填写过问卷，则不能重复填写
					return "questionnaireHasWrite";
				}
			}
		}
		this.setQuestionList(surveyQuestionService.queryByQuertionnaireId(id,versionFlag));
		return "showQuestionnaireDetail";
	}
	
	/**
	 * 问卷答案
	 * @return
	 * @throws ServiceException
	 */
	public String getQuestionnairAnswer() throws ServiceException{
		//系统管理员不能填写问卷
		if(getSystemEmployeeInfo() == null && getSystemChannelCustomer() == null){
			data = AjaxData.responseError(getText("systemUser.cannotoper"));
			return JSON;
		}
		//验证问卷是否过期 过期问卷不能提交答案
		if(surveyQuestionnaireService.validateDeadlineExpire(id,versionFlag)){
			data = AjaxData.responseError(getText("questionnaire.expire"));
			return JSON;
		}
		QuestionnaireSendRecord qsr = null;
		if(getSystemEmployeeInfo() != null){
			qsr = questionnaireSendRecordService.queryByObjectId(getSystemEmployeeInfo().getId(),versionFlag);
			if(qsr != null){
				if(qsr.getIsWrite() == 1){//如果已经填写过问卷，则不能重复填写
					data = AjaxData.responseError(getText("questionnaire.hasActive"));
					return JSON;
				}
			}
			List<SurveyQuestion> list = surveyQuestionService.queryByQuertionnaireId(id, versionFlag);
			if(list != null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					SurveyQuestion sq = list.get(i);
					if(sq.getType()==1){
						String[] aa = request.getParameterValues("name"+sq.getQuestionNo()+"&"+sq.getType().toString());
						if(GeneralUtil.isNotNull(aa));{
							String a = "";
							for(int j=0;j<aa.length;j++){
								if(j==aa.length-1){
									a += aa[j];
								}else{
									a += aa[j]+",";
								}
							}
							QuestionnaireAnswer qa = new QuestionnaireAnswer();
							qa.setObject_id(getSystemEmployeeInfo().getId());
							qa.setQuestionnaireId(id);
							qa.setQuestionNo(sq.getQuestionNo());
							qa.setQustionAnswer(a);
							questionnaireAnswerService.persist(qa);//保存自定义答案
						}
						//System.out.println(sq.getQuestionNo()+a);
					}else{
						String a = request.getParameter("name"+sq.getQuestionNo()+"&"+sq.getType().toString());
						if(GeneralUtil.isNotNull(a)){
							QuestionnaireAnswer qa = new QuestionnaireAnswer();
							qa.setObject_id(getSystemEmployeeInfo().getId());
							qa.setQuestionnaireId(id);
							qa.setQuestionNo(sq.getQuestionNo());
							qa.setQustionAnswer(a);
							questionnaireAnswerService.persist(qa);//保存选择题答案
						}
//						System.out.println(sq.getQuestionNo()+a);
					}
				}
			}
		}
		if(getSystemChannelCustomer() != null){
			qsr = questionnaireSendRecordService.queryByObjectId(getSystemChannelCustomer().getId(),versionFlag);
			if(qsr != null){
				if(qsr.getIsWrite() == 1){//如果已经填写过问卷，则不能重复填写
					data = AjaxData.responseError(getText("questionnaire.hasActive"));
					return JSON;
				}
			}
			List<SurveyQuestion> list = surveyQuestionService.queryByQuertionnaireId(id, versionFlag);
			if(list != null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					SurveyQuestion sq = list.get(i);
					if(sq.getType()==1){
						String[] aa = request.getParameterValues("name"+sq.getQuestionNo()+"&"+sq.getType().toString());
						if(GeneralUtil.isNotNull(aa)){
							
							String a = "";
							for(int j=0;j<aa.length;j++){
								if(j==aa.length-1){
									a += aa[j];
								}else{
									a += aa[j]+",";
								}
							}
							QuestionnaireAnswer qa = new QuestionnaireAnswer();
							qa.setObject_id(getSystemChannelCustomer().getId());
							qa.setQuestionnaireId(id);
							qa.setQuestionNo(sq.getQuestionNo());
							qa.setQustionAnswer(a);
							questionnaireAnswerService.persist(qa);//保存自定义答案
						}
						//System.out.println(sq.getQuestionNo()+a);
					}else{
						String a = request.getParameter("name"+sq.getQuestionNo()+"&"+sq.getType().toString());
						if(GeneralUtil.isNotNull(a)){
							
							QuestionnaireAnswer qa = new QuestionnaireAnswer();
							qa.setObject_id(getSystemChannelCustomer().getId());
							qa.setQuestionnaireId(id);
							qa.setQuestionNo(sq.getQuestionNo());
							qa.setQustionAnswer(a);
							questionnaireAnswerService.persist(qa);//保存选择题答案
						}
//						System.out.println(sq.getQuestionNo()+a);
					}
				}
			}
		}
		QuestionnaireSendLog qsl = questionnaireSendLogService.queryByQuestionnaireId(id,versionFlag);
		if(qsl != null){
			if(GeneralUtil.isNull(qsl.getWriteCount())){
				
				qsl.setWriteCount(+1);
			}else{
				
				qsl.setWriteCount(qsl.getWriteCount()+1);
			}
		}
		questionnaireSendLogService.merge(qsl);//让问卷记录填写次数加一
		qsr.setIsWrite(ContextConstants.STATUS_OF_ONE);
		questionnaireSendRecordService.merge(qsr);//设置问卷状态为已参与
		data = AjaxData.responseSuccess(getText("questionnaire.submit"));
		return JSON;
	}
	
	/**
	 * 感谢参与问卷调查
	 * @return
	 * @throws ServiceException
	 */
	public String thanksActive() throws ServiceException{
		return "thanksActive";
	}
	/**
	 * 发放问卷
	 * @return
	 * @throws ServiceException
	 */
	public String sendToObject() throws ServiceException{
		if(GeneralUtil.isNull(operationFlag)){
			this.setDeptHtml(departmentInfoService.getDeptTree(departmentInfoService.queryFirstDept(versionFlag)));
			return "selectDeptAndCustomer";
		}else{
			if(getSystemEmployeeInfo() == null){
				data = AjaxData.responseError(getText("systemUser.cannotoper"));
				return JSON;
			}
			SurveyQuestionnaire sq = surveyQuestionnaireService.queryById(SurveyQuestionnaire.class.getSimpleName(), id);
			if("dept".equals(sendType)){//根据部门发送问卷
				String[] ids = idsValue.split(",");
				Integer sendCount = 0;
				for(String deptId : ids){
					List<EmployeeInfo> list = employeeInfoService.queryByDepartmentId(deptId, versionFlag);
					if(list != null && !list.isEmpty()){
						for(EmployeeInfo emp : list){
							QuestionnaireSendRecord qsr = new QuestionnaireSendRecord();
							qsr.setQuestionnaireId(id);
							qsr.setObject_id(emp.getId());
							qsr.setEmployee_id(getSystemEmployeeInfo().getId());
							qsr.setIsWrite(ContextConstants.STATUS_OF_ZERO);
							questionnaireSendRecordService.persist(qsr);
							LetterStation ls = new LetterStation();
							ls.setOperator_id(getSystemEmployeeInfo().getId());
							ls.setTitle(sq.getTitle());
							ls.setUrl(UrlUtil.getDomainName()+"surveyQuestionnaire_showQuestionnaireDetail.action?id="+id);
							ls.setIsSend(1);
							/*ls.setOper_id(getSystemEmployeeInfo().getId());
							ls.setEmployee_id(emp.getId());
							ls.setIsRead(0);*/
							letterStationService.persist(ls);
							StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
							stationLetterReadLog.setIsRead(0);
							stationLetterReadLog.setLetter_id(ls.getId());
							stationLetterReadLog.setObject_id(emp.getId());
							stationLetterReadLogService.persist(stationLetterReadLog);
							sendCount = sendCount +1;
						}
					}
				}
				QuestionnaireSendLog qsl = new QuestionnaireSendLog();
				qsl.setEmployee_id(getSystemEmployeeInfo().getId());
				qsl.setSendCount(sendCount);
				qsl.setQuestionnaireId(id);
				questionnaireSendLogService.persist(qsl);
				sq.setCurrentOpenUp(1);
				surveyQuestionnaireService.merge(sq);
			}else if("channelType".equals(sendType)){//根据渠道类别发送问卷
				String[] ids = idsValue.split(",");
				Integer sendCount = 0;
				for(String channelTypeId : ids){
					List<ChannelCustomerInfo> list = channelCustomerInfoService.queryChannelCustomerList(channelTypeId,versionFlag);
					if(list != null && !list.isEmpty()){
						for(ChannelCustomerInfo customer : list){
							QuestionnaireSendRecord qsr = new QuestionnaireSendRecord();
							qsr.setQuestionnaireId(id);
							qsr.setObject_id(customer.getId());
							qsr.setEmployee_id(getSystemEmployeeInfo().getId());
							qsr.setIsWrite(ContextConstants.STATUS_OF_ZERO);
							questionnaireSendRecordService.persist(qsr);
							LetterStation ls = new LetterStation();
							ls.setContent(sq.getTitle());
							ls.setUrl(UrlUtil.getDomainName()+"surveyQuestionnaire_showQuestionnaireDetail.action?id="+id);
							ls.setIsSend(1);
							/*ls.setSystemUser_id(getSystemEmployeeInfo().getId());
							ls.setEmployee_id(customer.getId());
							ls.setIsRead(0);*/
							letterStationService.persist(ls);
							StationLetterReadLog stationLetterReadLog = new StationLetterReadLog();
							stationLetterReadLog.setIsRead(0);
							stationLetterReadLog.setLetter_id(ls.getId());
							stationLetterReadLog.setObject_id(customer.getId());
							
							stationLetterReadLogService.persist(stationLetterReadLog);
							sendCount = sendCount + 1; 
						}
					}
				}
				QuestionnaireSendLog qsl = new QuestionnaireSendLog();
				qsl.setEmployee_id(getSystemEmployeeInfo().getId());
				qsl.setSendCount(sendCount);
				qsl.setQuestionnaireId(id);
				questionnaireSendLogService.persist(qsl);
				sq.setCurrentOpenUp(1);
				surveyQuestionnaireService.merge(sq);
			}else{
				
				data = AjaxData.responseError(getText(""));
			}
			data = AjaxData.responseSuccess(getText(""));
			return JSON;
		}
	}
	
	/**
	 * 统计调查问卷
	 * @return
	 * @throws ServiceException
	 */
	public String statistic() throws ServiceException{
		SurveyQuestionnaire questionnaire = surveyQuestionnaireService.queryById(SurveyQuestionnaire.class.getSimpleName(), id);
		List<SurveyQuestion> questionList = surveyQuestionService.queryByQuertionnaireId(id, versionFlag);
		if(questionList != null && !questionList.isEmpty()){
			this.setQuestionList(questionList);
		}
		QuestionnaireSendLog qsr = questionnaireSendLogService.queryByQuestionnaireId(id, versionFlag);
		this.setQuestionnaireSendLog(qsr);
		this.setSurveyQuestionnaire(questionnaire);
		return "questionnaireStatisticResult";
	}
	
	/**
	 * 问卷自定义答案详情
	 * @return
	 * @throws ServiceException
	 */
	public String showAnswerDetail() throws ServiceException{
		List<QuestionnaireAnswer> list = questionnaireAnswerService.queryByQuestionNo(id,questionNo);
		this.setQuestionnaireAnswerList(list);
		this.setQuestionTitle(DecoderUtil.UtfDecoder(questionTitle));
		return "answerDetail";
	}
	public SurveyQuestionnaire getSurveyQuestionnaire() {
		return surveyQuestionnaire;
	}
	public void setSurveyQuestionnaire(SurveyQuestionnaire surveyQuestionnaire) {
		this.surveyQuestionnaire = surveyQuestionnaire;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSurvryNo() {
		return survryNo;
	}
	public void setSurvryNo(String survryNo) {
		this.survryNo = survryNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSurvryModularId() {
		return survryModularId;
	}
	public void setSurvryModularId(String survryModularId) {
		this.survryModularId = survryModularId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Integer getCurrentOpenUp() {
		return currentOpenUp;
	}
	public void setCurrentOpenUp(Integer currentOpenUp) {
		this.currentOpenUp = currentOpenUp;
	}

	public List<SurveyQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<SurveyQuestion> questionList) {
		this.questionList = questionList;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getDeptHtml() {
		return deptHtml;
	}

	public void setDeptHtml(String deptHtml) {
		this.deptHtml = deptHtml;
	}

	public String getIdsValue() {
		return idsValue;
	}

	public void setIdsValue(String idsValue) {
		this.idsValue = idsValue;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public QuestionnaireSendLog getQuestionnaireSendLog() {
		return questionnaireSendLog;
	}

	public void setQuestionnaireSendLog(QuestionnaireSendLog questionnaireSendLog) {
		this.questionnaireSendLog = questionnaireSendLog;
	}

	public IQuestionnaireAnswerService getQuestionnaireAnswerService() {
		return questionnaireAnswerService;
	}

	public void setQuestionnaireAnswerService(
			IQuestionnaireAnswerService questionnaireAnswerService) {
		this.questionnaireAnswerService = questionnaireAnswerService;
	}

	public ISurveyOptionService getSurveyOptionService() {
		return surveyOptionService;
	}

	public void setSurveyOptionService(ISurveyOptionService surveyOptionService) {
		this.surveyOptionService = surveyOptionService;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	public List<QuestionnaireAnswer> getQuestionnaireAnswerList() {
		return questionnaireAnswerList;
	}

	public void setQuestionnaireAnswerList(
			List<QuestionnaireAnswer> questionnaireAnswerList) {
		this.questionnaireAnswerList = questionnaireAnswerList;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
}
