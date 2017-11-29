package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.EvaluationTemplate;
import com.ticket.service.IEvaluationTemplateService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.ResourceUtil;

/**
 * 评论模板业务接口实现类
 * @ClassName: IEvaluationTemplateService   
 * @Description: 提供评论模板操作的增删改查   
 * @author HiSay  
 * @date 2016-02-03 18:17:36
 *
 */
public class EvaluationTemplateServiceImpl extends BaseServiceImpl<EvaluationTemplate, String> implements IEvaluationTemplateService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EvaluationTemplateServiceImpl.class);
	
	@Override
	public boolean merge(String id, String title,String content) throws ServiceException {
		
		if(getSystemUser() instanceof AirportEmployee == false){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		EvaluationTemplate evaluationTemplate = dbDAO.queryById(this.getTableNameFromEntity(EvaluationTemplate.class), id);
		evaluationTemplate.setTitle(DecoderUtil.UtfDecoder(title));
		evaluationTemplate.setContent(DecoderUtil.UtfDecoder(content));
		evaluationTemplate.setUser((AirportEmployee)getSystemUser());
		dbDAO.merge(evaluationTemplate);
		return true;
	}

	@Override
	public boolean persist(String title,String content) throws ServiceException {
		
		if(getSystemUser() instanceof AirportEmployee == false){
			
			throw new ServiceException(ResourceUtil.getText("airportEmployee.must"));
		}
		
		EvaluationTemplate evaluationTemplate = new EvaluationTemplate();
		evaluationTemplate.setTitle(DecoderUtil.UtfDecoder(title));
		evaluationTemplate.setContent(DecoderUtil.UtfDecoder(content));
		evaluationTemplate.setUser((AirportEmployee)getSystemUser());
		dbDAO.persist(evaluationTemplate);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		EvaluationTemplate evaluationTemplate = dbDAO.queryById(this.getTableNameFromEntity(EvaluationTemplate.class), id);
		dbDAO.remove(evaluationTemplate);
		return true;
	}

}