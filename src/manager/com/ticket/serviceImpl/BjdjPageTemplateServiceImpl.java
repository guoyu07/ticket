package com.ticket.serviceImpl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjPageTemplateService;
import com.ticket.util.DecoderUtil;

/**
 * jdj支付激活页面模板业务接口实现类
 * @ClassName: IBjdjPageTemplateService   
 * @Description: 提供jdj支付激活页面模板操作的增删改查   
 * @author HiSay  
 * @date 2016-08-18 15:26:40
 *
 */
public class BjdjPageTemplateServiceImpl extends BaseServiceImpl<BjdjPageTemplate, String> implements IBjdjPageTemplateService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjPageTemplateServiceImpl.class);

	@Override
	public boolean persist(String name,String content,String buttonName,String buttonUrl,String buttonType,String buttonClass, String versionFlag) throws ServiceException {
		BjdjPageTemplate bjdjPageTemplate = new BjdjPageTemplate();
		bjdjPageTemplate.setName(DecoderUtil.UtfDecoder(name));
		bjdjPageTemplate.setContent(DecoderUtil.UtfDecoder(content));
		bjdjPageTemplate.setButtonName(DecoderUtil.UtfDecoder(buttonName));
		bjdjPageTemplate.setButtonUrl(DecoderUtil.UtfDecoder(buttonUrl));
		bjdjPageTemplate.setButtonType(DecoderUtil.UtfDecoder(buttonType));
		bjdjPageTemplate.setButtonClass(DecoderUtil.UtfDecoder(buttonClass));
		CommonEntity status = bjdjPageTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjPageTemplate.setStatus(status);
		dbDAO.persist(bjdjPageTemplate);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String content,String buttonName,String buttonUrl,String buttonType,String buttonClass, String versionFlag) throws ServiceException {
		BjdjPageTemplate bjdjPageTemplate = dbDAO.queryById(this.getTableNameFromEntity(BjdjPageTemplate.class), id);
		bjdjPageTemplate.setName(DecoderUtil.UtfDecoder(name));
		bjdjPageTemplate.setContent(DecoderUtil.UtfDecoder(content));
		bjdjPageTemplate.setButtonName(DecoderUtil.UtfDecoder(buttonName));
		bjdjPageTemplate.setButtonUrl(DecoderUtil.UtfDecoder(buttonUrl));
		bjdjPageTemplate.setButtonType(DecoderUtil.UtfDecoder(buttonType));
		bjdjPageTemplate.setButtonClass(buttonClass);
		CommonEntity status = bjdjPageTemplate.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjPageTemplate.setStatus(status);
		dbDAO.merge(bjdjPageTemplate);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjPageTemplate bjdjPageTemplate = dbDAO.queryById(this.getTableNameFromEntity(BjdjPageTemplate.class), id);
		dbDAO.remove(bjdjPageTemplate);
		return true;
	}

}