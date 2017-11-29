package com.ticket.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportDepartment;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.Evaluation;
import com.ticket.pojo.EvaluationDepartmentRalation;
import com.ticket.pojo.EvaluationForbiddenMember;
import com.ticket.pojo.EvaluationItem;
import com.ticket.pojo.EvaluationProcess;
import com.ticket.pojo.EvaluationSetting;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IEvaluationService;
import com.ticket.service.IEvaluationSettingService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.GeneralUtil;
import com.ticket.util.ResourceUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.ValidateUtil;

public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements IEvaluationService{

	@Resource
	protected ISystemDictionaryService dictionaryService;
	@Resource
	protected IEvaluationSettingService evaluationSettingService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Evaluation add(String class_id, String project_id, 
			String[] items_id, Integer[] stars, String content, String images) throws ServiceException{
		
		//是否已经屏蔽了此用户
		if(isSheild(getMember())){
			
			throw new ServiceException(ResourceUtil.getText("comment.member.shield"));
		}
		
		//是否达到评论时间间隔内只能评论5条
		String timeInterval = dictionaryService.getValueByName("time_interval");
		String timeIntarvalCount = dictionaryService.getValueByName("time_intarval_count");
		if(timeInterval != null && ValidateUtil.isInteger(timeInterval)
				&& timeIntarvalCount != null && ValidateUtil.isInteger(timeIntarvalCount)){
			
			Integer interval = Integer.parseInt(timeInterval);
			Integer count = Integer.parseInt(timeIntarvalCount);
			Date now = new Date();
			List<Evaluation> list = query(getMember(), new Date(now.getTime() - interval * 60 * 1000), now);
			if(list.size() >= count){
				
				throw new ServiceException(ResourceUtil.getText("comment.frequently", timeInterval));
			}
		}
		
		
		//非空验证.
		if(GeneralUtil.isNull(class_id)){
			
			throw new ServiceException(ResourceUtil.getText("comment.classes.required"));
		}
		if(GeneralUtil.isNull(project_id)){
			
			throw new ServiceException(ResourceUtil.getText("comment.project.required"));
		}
		if(GeneralUtil.isNull(content)) {
			
			throw new ServiceException(ResourceUtil.getText("comment.content.required"));
		}
		
		//获取IP地址
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = UrlUtil.getIpAddr(request);
		
		Evaluation evaluation = new Evaluation();
		evaluation.setMember(getMember());
		evaluation.setClasses(new EvaluationSetting(class_id));
		evaluation.setProject(new EvaluationSetting(project_id));
		evaluation.setContent(content);
		evaluation.setImages(images);
		evaluation.setShowName(true);
		evaluation.setIp(ip);
		
		//设置状态
		SystemDictionary newEval = dictionaryService.getByName("new_state");
		evaluation.setState(newEval);
		
		//保存自定义评论项
		for(int i = 0; items_id != null && i < items_id.length; i++){
			
			EvaluationItem item = new EvaluationItem();
			item.setComment(evaluation);
			item.setStar(stars[i]);
			
			EvaluationSetting setting = evaluationSettingService.get(EvaluationSetting.class, items_id[i].trim());
			item.setSetting(setting);
			
			item.setComment(evaluation);
			persist(item);
		}
		dbDAO.persist(evaluation);
		return evaluation;
	}

	@Override
	public List<Evaluation> queryAllForEvaluationAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryAllForPublishAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryAllForDepartmentAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryFeedback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> querySended() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryHasImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryHasContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryDeleted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryNeedVerify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryNeedFollowUp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryFeedback(String department_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluation> queryPublished() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean canSend(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?) and t.state.name != 'new_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int send(String ids) throws ServiceException {
		
		String entityName = Evaluation.class.getName();
		if(!canSend(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.send.unable"));
		}
		
		SystemDictionary sendedState = dictionaryService.getByName("sended_state");
		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t set t.state=? where t.id in (?)", sendedState, ids);
		
		return effect;
	}
	
	@Override
	public boolean canFeedback(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?)"
						+ " and t.state.name != 'new_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int feedback(String ids, String content) throws ServiceException {

		//存在不能评论的
		if(!canFeedback(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.feedback.unable"));
		}
		
		//评论内容不能为空
		if(GeneralUtil.isNull(content)){
			
			throw new ServiceException(ResourceUtil.getText("comment.content.required"));
		}
		
		String entityName = Evaluation.class.getName();
		SystemDictionary pushedState = dictionaryService.getByName("pushed_state");
		if(pushedState == null){
			
			throw new ServiceException(ResourceUtil.getText("dictionary.exception"));
		}
		
		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t"
				+ " set t.feedback=?, t.feedbackTime=?, t.feedback_user=?, t.state=?"
				+ " where t.id in (?)"
					, content, new Date(), getSystemUser(), pushedState, ids);
		
		return effect;
	}
	
	@Override
	public boolean canShield(String ids) throws ServiceException {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		String forbiddenTable = EvaluationForbiddenMember.class.getName();
		//查看在
		List<Evaluation> evaluations = dbDAO.executeJPQLForQuery("select t from " + entityName + " t"
				+ " where t.id in (?) and t.member.id not in(select f.member.id from " + forbiddenTable + " f)",
				Evaluation.class, ids);
		if(evaluations.size() > 0){
			
			return true;
		}
		return false;
	}

	@Override
	public int shield(String ids) throws ServiceException {
		
		String entityName = Evaluation.class.getName();
		String forbiddenTable = EvaluationForbiddenMember.class.getName();
		
		//查看在
		List<Evaluation> evaluations = dbDAO.executeJPQLForQuery("select t from " + entityName + " t"
				+ " where t.id in (?) and t.member.id not in(select f.member.id from " + forbiddenTable + " f)",
				Evaluation.class, ids);
		
		Set<String> evaSet = new HashSet<String>();
		for(Evaluation e : evaluations){
			
			evaSet.add(e.getMember().getId());
		}
		int count = 0;
		for(String memberId : evaSet){
			
			if(GeneralUtil.isNotNull(memberId)){
				
				count++;
				persist(new EvaluationForbiddenMember(new Member(memberId)));
			}
		}
		return count;
	}

	@Override
	public int sendToDepartment(String ids, String department_id, String involveSystem, String shipmentQualityOpinion, String msg, String remark)
			 throws ServiceException{
		
		if(getSystemUser() instanceof AirportEmployee == false){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		if(GeneralUtil.isNull(department_id)){
			
			throw new ServiceException(ResourceUtil.getText("department.required"));
		}
		
		int count = 0;
		for(String id : ids.split(",")){
			
			Evaluation evaluation = get(Evaluation.class, id);
			List<EvaluationDepartmentRalation> departments = new ArrayList<EvaluationDepartmentRalation>();
			for(String did : department_id.split(",")){
				
				EvaluationDepartmentRalation r = new EvaluationDepartmentRalation();
				r.setDepartment(get(AirportDepartment.class, did));
				r.setEvaluation(evaluation);
				departments.add(r);
				persist(r);
			}
			evaluation.setDepartment(departments);
			evaluation.setAdvancedAdmin((AirportEmployee)getSystemUser());
			if(GeneralUtil.isNotNull(involveSystem)){
				
				evaluation.setInvolveSystem(involveSystem.trim());
			}
			if(GeneralUtil.isNotNull(msg)){
				
				evaluation.setMsg(msg.trim());
			}
			if(GeneralUtil.isNotNull(shipmentQualityOpinion)){
				
				evaluation.setShipmentQualityOpinion(shipmentQualityOpinion.trim());
			}
			if(GeneralUtil.isNotNull(remark)){
				
				evaluation.setRemark(remark.trim());
			}
			//设置对应评论实体的状态：已发送给部门
			SystemDictionary sended_to_department = dictionaryService.getByName("sended_to_department");
			evaluation.setState(sended_to_department);
			merge(evaluation);
			
			EvaluationProcess process = new EvaluationProcess();
			process.setEvaluation(evaluation);
			//设置状态为：未处理
			SystemDictionary untreated_state = dictionaryService.getByName("untreated_state");
			process.setState(untreated_state);
			dbDAO.persist(process);
			
			count++;
		}
		return count;
	}
	
	@Override
	public boolean canSendBack(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?) and t.state.name != 'sended_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int sendBack(String ids) throws ServiceException {
		
		if(getSystemUser() instanceof AirportEmployee == false){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		if(!canSendBack(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.sendback.unable"));
		}
		
		String entityName = Evaluation.class.getName();
		SystemDictionary pushedState = dictionaryService.getByName("new_state");
		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t set t.state=? where t.id in (?)"
					, pushedState, ids);
		return effect;
	}
	
	@Override
	public boolean canPublish(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?) and t.state.name != 'pushed_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int publish(String ids) throws ServiceException {
		
		if(!canPublish(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.publish.unable"));
		}
		
		String entityName = Evaluation.class.getName();
		SystemDictionary publishedState = dictionaryService.getByName("published_state");
		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t set t.state=? where t.id in (?)"
					, publishedState, ids);
		
		return effect;
	}
	
	@Override
	public boolean canUnpublish(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?) and t.state.name != 'published_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int unpublish(String ids) throws ServiceException {
		
		if(!canUnpublish(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.unpublish.unable"));
		}
		
		String entityName = Evaluation.class.getName();
		SystemDictionary pushedState = dictionaryService.getByName("pushed_state");
		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t set t.state=? where t.id in (?)"
					, pushedState, ids);
		
		return effect;
	}

	@Override
	public List<EvaluationProcess> getAllProcess(String id) {
		
		String tableName = EvaluationProcess.class.getName();
		List<EvaluationProcess> list = dbDAO.executeJPQLForQuery("select t from " + tableName + " t where t.evaluation.id=?"
				, EvaluationProcess.class, id);
		return list;
	}

	@Override
	public EvaluationProcess getNewestProcess(String id) {
		
		String tableName = EvaluationProcess.class.getName();
		EvaluationProcess list = dbDAO.executeJPQLForQuerySingle(
				"select t from " + tableName + " t where t.evaluation.id=?"
						+ " and t.status.createTime=(select max(t.status.createTime) from " + tableName + ")"
				, EvaluationProcess.class, id);
		return list;
	}

	@Override
	public boolean canSendToDepartment(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t"
						+ " where t.id in (?) and t.state.name != 'sended_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public boolean isDelete(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String entityName = Evaluation.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + entityName + " t where t.id in (?) and t.status.deleteFlag = 1", ids);
		if(canntSend > 0){
			
			return true;
		}
		return false;
	}
	
	@Override
	public long count() {
		
		long count = (Long)dbDAO.executeJPQLForQuerySingle("select count(*) from " + Evaluation.class.getName()
				+ " s where s.status.deleteFlag=0 and s.state.name='published_state'");
		return count;
	}
	
	@Override
	public boolean canDepartmentProcess(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String processName = EvaluationProcess.class.getName();
		long canntSend = (Long)dbDAO.executeJPQLForQuerySingle(
				"select count(*) from " + processName + " p"
						+ " where p.id in (?) and p.state.name != 'untreated_state'", ids);
		if(canntSend > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int departmentProcess(String ids, boolean close, String shipmentQualityOpinion, String msg, String remark) throws ServiceException{
		
		if(getSystemUser() instanceof AirportEmployee == false){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		if(!canDepartmentProcess(ids)){
			
			throw new ServiceException(ResourceUtil.getText("comment.process.unable"));
		}
		
//		String entityName = EvaluationProcess.class.getName();
//		SystemDictionary state = dictionaryService.getByName("treated_state");
//		int effect = dbDAO.executeJPQLForUpdate(
//				"update " + entityName + " t set t.state=?, t.processMsg=?, t.departmentAdmin=?, t.processTime=?"
//						+ " where t.id in (?)"
//					, state, content, getSystemUser(), new Date(), ids);
		int effect = 0;
		for(String id : ids.split(",")){
			
			EvaluationProcess process = get(EvaluationProcess.class, id);
			process.setClose(close);
			process.setDepartmentAdmin((AirportEmployee)getSystemUser());
			if(GeneralUtil.isNotNull(msg)){
				
				process.setMsg(msg.trim());
			}
			if(GeneralUtil.isNotNull(remark)){
				
				process.setRemark(remark.trim());
			}
			process.setProcessTime(new Date());
			
			//所属对象修改状态
			process.setState(dictionaryService.getByName("treated_state"));
			merge(process);
			
			if(close){
				
				Evaluation evaluation = process.getEvaluation();
				evaluation.setState(dictionaryService.getByName("pushed_state"));
				merge(evaluation);
			}
		}
		return effect;
	}

	@Override
	public boolean canFeedbackDepartment(String ids) {
		
		if(GeneralUtil.isNull(ids)){
			
			return false;
		}
		
		String processName = EvaluationProcess.class.getName();
		long effect = dbDAO.executeJPQLForQuerySingle("select count(p) from " + processName + " p"
					+ " where p.id in (?) and p.state.name!='treated_state')"
					, Long.class, ids);
		if(effect > 0){
			
			return false;
		}
		return true;
	}

	@Override
	public int feedbackDepartment(String ids) throws ServiceException {
		
		int effect = 0;
		for(String id : ids.split(",")){
			
			EvaluationProcess process = get(EvaluationProcess.class, id);
			Evaluation evaluation = process.getEvaluation();
			
			EvaluationProcess newProcess = new EvaluationProcess();
			newProcess.setEvaluation(evaluation);
			//设置状态为：未处理
			newProcess.setState(dictionaryService.getByName("untreated_state"));
			dbDAO.persist(newProcess);
			
			effect++;
		}
		
		return effect;
//		//存在不能评论的
//		if(!canFeedbackDepartment(ids)){
//			
//			throw new ServiceException(ResourceUtil.getText("comment.feedback.unable"));
//		}
//		
//		if(getSystemUser() instanceof AirportEmployee == false){
//			
//			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
//		}
//		
//		//评论内容不能为空
//		if(GeneralUtil.isNull(content)){
//			
//			throw new ServiceException(ResourceUtil.getText("comment.content.required"));
//		}
//		
//		String entityName = EvaluationProcess.class.getName();
//		SystemDictionary state = null;
//		if(qualified){
//			
//			state = dictionaryService.getByName("qualified_state");
//		}else{
//			
//			state = dictionaryService.getByName("unqualified_state");
//		}
//		if(state == null){
//			
//			throw new ServiceException(ResourceUtil.getText("dictionary.exception"));
//		}
//		
//		int effect = dbDAO.executeJPQLForUpdate("update " + entityName + " t"
//				+ " set t.processFeedbackMsg=?, t.processFeedbackTime=?, t.state=?"
//				+ " where t.id in (?)"
//					, content, new Date(), state, ids);
//		
//		if(qualified){
//			
//			String evaluationTable = Evaluation.class.getName();
//			state = dictionaryService.getByName("pushed_state");
//			dbDAO.executeJPQLForUpdate("update " + evaluationTable + " t"
//					+ " set t.state=?"
//					+ " where t.id in (select s.evaluation.id from " + entityName + " s where s.id in (?))"
//						,state, ids);
//		}
//		
//		return effect;
	}

	@Override
	public int topOrNot(String ids) throws ServiceException{
		
		int effect = 0;
		for(String id : ids.split(",")){
			
			EvaluationProcess process = get(EvaluationProcess.class, id);
			process.setTop(!process.isTop());
			merge(process);
		}
		return effect;
	}

	@Override
	public boolean isSheild(Member member) {
		
		if(member == null){
			
			return true;
		}
		
		String tableName = EvaluationForbiddenMember.class.getName();
		EvaluationForbiddenMember forbidden = dbDAO.executeJPQLForQuerySingle(
				"select t from " + tableName + " t where t.member=?", EvaluationForbiddenMember.class, member);
		if(forbidden != null){
			
			return true;
		}
		return false;
	}

	@Override
	public Evaluation getNewestEvaluation(Member member) {
		
		String tableName = Evaluation.class.getName();
		List<Evaluation> evaluation = dbDAO.executeJPQLForQuery(
				"select t from " + tableName + " t where t.member=? order by t.status.createTime desc"
				, Evaluation.class, 0, 1, member);
		if(evaluation.size() == 0){
			
			return null;
		}
		return evaluation.get(0);
	}

	@Override
	public List<EvaluationProcess> getProcess(Evaluation evaluation) {
		
		String tableName = EvaluationProcess.class.getName();
		List<EvaluationProcess> list = dbDAO.executeJPQLForQuery(
				"select t from " + tableName + " t where t.evaluation=? order by t.createTime desc"
				, EvaluationProcess.class, 0, 1, evaluation);
		return list;
	}

	@Override
	public List<EvaluationDepartmentRalation> getDepartments(Evaluation evaluation) {
		
		String tableName = EvaluationDepartmentRalation.class.getName();
		List<EvaluationDepartmentRalation> list = dbDAO.executeJPQLForQuery(
				"select t from " + tableName + " t where t.evaluation=?"
				, EvaluationDepartmentRalation.class, 0, 1, evaluation);
		return list;
	}

	@Override
	public List<Evaluation> query(Date startTime, Date endTime) {
		
		StringBuffer sb = new StringBuffer();
		List<Date> time = new ArrayList<Date>();
		if(GeneralUtil.isNotNull(startTime)){
			
			sb.append(" and t.status.createTime > ?");
			time.add(startTime);
		}
		if(GeneralUtil.isNotNull(endTime)){
			
			sb.append(" and t.status.createTime < ?");
			time.add(endTime);
		}
		
		String tableName = Evaluation.class.getName();
		List<Evaluation> evaluation = dbDAO.executeJPQLForQuery(
				"select t from " + tableName + " t where 1=1" + sb.toString()
				, Evaluation.class, time.toArray());
		return evaluation;
	}
	
	@Override
	public List<Evaluation> query(Member member, Date startTime, Date endTime) {
		
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		params.add(member);
		if(GeneralUtil.isNotNull(startTime)){
			
			sb.append(" and t.status.createTime > ?");
			params.add(startTime);
		}
		if(GeneralUtil.isNotNull(endTime)){
			
			sb.append(" and t.status.createTime < ?");
			params.add(endTime);
		}
		
		String tableName = Evaluation.class.getName();
		List<Evaluation> evaluation = dbDAO.executeJPQLForQuery(
				"select t from " + tableName + " t where t.member=?" + sb.toString()
				, Evaluation.class, params.toArray());
		return evaluation;
	}

	@Override
	public long myEvaluationCount() {

		return myEvaluationCount(getMember());
	}

	@Override
	public long myEvaluationCount(Member member) {
		
		if(member == null || GeneralUtil.isNull(member.getId())){
			
			return 0;
		}
		
		String tableName = Evaluation.class.getName();
		long count = dbDAO.executeJPQLForQuerySingle("select count(s) from " + tableName + " s"
				+ " where s.status.deleteFlag=0 and s.member=?"
				, Long.class, member);
		return count;
	}

	@Override
	public Integer queryByMember() {
		List<Evaluation> evaluations = dbDAO.executeJPQLForQuery("select c from " + Evaluation.class.getName() + " c where c.member = ? and c.status.deleteFlag=0",Evaluation.class, getMember());
		int count = 0;
		if(GeneralUtil.isNotNull(evaluations)){
			for(Evaluation evaluation:evaluations){
				if(GeneralUtil.isNotNull(evaluation.getFeedback_user()) && !evaluation.isRend()){
					count++;
				}
			}
		}
		return count;
	}
}
