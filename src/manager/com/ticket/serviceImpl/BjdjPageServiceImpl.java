package com.ticket.serviceImpl;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AdvertType;
import com.ticket.pojo.BjdjPage;
import com.ticket.pojo.BjdjPageTemplate;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.News;
import com.ticket.pojo.SystemDictionary;
import com.ticket.service.IAdvertTypeService;
import com.ticket.service.IBjdjPageService;
import com.ticket.service.IBjdjPageTemplateService;
import com.ticket.service.INewsService;
import com.ticket.service.ISystemDictionaryService;
import com.ticket.util.DecoderUtil;

/**
 * 便捷登机跳转页面业务接口实现类
 * @ClassName: IBjdjPageService   
 * @Description: 提供便捷登机跳转页面操作的增删改查   
 * @author HiSay  
 * @date 2016-08-08 16:09:07
 *
 */
public class BjdjPageServiceImpl extends BaseServiceImpl<BjdjPage, String> implements IBjdjPageService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjPageServiceImpl.class);
	@Resource
	private INewsService newsService;
	@Resource
	private IAdvertTypeService advertTypeService;
	@Resource
	private ISystemDictionaryService systemDictionaryService;
	@Resource
	private IBjdjPageTemplateService bjdjPageTemplateService;
	
	@Override
	public boolean persist(String name,String url, String servicePhone,String infoPositionAlias,String serviceFlow_id,String serviceProfile_id,String useSerms_id,String paymentSuccess_id,String advertType_id,String smsTemplate_id,String activeSuccess_id, String versionFlag) throws ServiceException {
		BjdjPage bjdjPage = new BjdjPage();
		bjdjPage.setName(DecoderUtil.UtfDecoder(name));
		bjdjPage.setUrl(DecoderUtil.UtfDecoder(url));
		bjdjPage.setServicePhone(servicePhone);
		bjdjPage.setInfoPositionAlias(infoPositionAlias);
		News serviceFlow = newsService.queryById(News.class.getName(), serviceFlow_id);
		News serviceProfile = newsService.queryById(News.class.getName(), serviceProfile_id);
		News useSerms = newsService.queryById(News.class.getName(), useSerms_id);
		BjdjPageTemplate paymentSuccess = bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), paymentSuccess_id);
		BjdjPageTemplate activeSuccess = bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), activeSuccess_id);
		bjdjPage.setServiceFlow(serviceFlow);
		bjdjPage.setServiceProfile(serviceProfile);
		bjdjPage.setUseSerms(useSerms);
		bjdjPage.setPaySuccess(paymentSuccess);
		bjdjPage.setActiveSuc(activeSuccess);
		AdvertType advertType = advertTypeService.queryById(AdvertType.class.getName(), advertType_id);
		bjdjPage.setAdvertType(advertType);
		SystemDictionary dictionary = systemDictionaryService.queryById(SystemDictionary.class.getName(), smsTemplate_id);
		bjdjPage.setSmsTemplate(dictionary);
		CommonEntity status = bjdjPage.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjPage.setStatus(status);
		dbDAO.persist(bjdjPage);
		return true;
	}
	
	@Override
	public boolean merge(String id, String name,String url,String servicePhone,String infoPositionAlias,String serviceFlow_id,String serviceProfile_id,String useSerms_id,String paymentSuccess_id,String advertType_id,String smsTemplate_id,String activeSuccess_id, String versionFlag) throws ServiceException {
		BjdjPage bjdjPage = dbDAO.queryById(this.getTableNameFromEntity(BjdjPage.class), id);
		bjdjPage.setName(DecoderUtil.UtfDecoder(name));
		bjdjPage.setUrl(DecoderUtil.UtfDecoder(url));
		bjdjPage.setServicePhone(servicePhone);
		bjdjPage.setInfoPositionAlias(infoPositionAlias);
		News serviceFlow = newsService.queryById(News.class.getName(), serviceFlow_id);
		News serviceProfile = newsService.queryById(News.class.getName(), serviceProfile_id);
		News useSerms = newsService.queryById(News.class.getName(), useSerms_id);
		BjdjPageTemplate paymentSuccess = bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), paymentSuccess_id);
		BjdjPageTemplate activeSuccess = bjdjPageTemplateService.queryById(BjdjPageTemplate.class.getName(), activeSuccess_id);
		bjdjPage.setServiceFlow(serviceFlow);
		bjdjPage.setServiceProfile(serviceProfile);
		bjdjPage.setUseSerms(useSerms);
		bjdjPage.setPaySuccess(paymentSuccess);
		bjdjPage.setActiveSuc(activeSuccess);
		AdvertType advertType = advertTypeService.queryById(AdvertType.class.getName(), advertType_id);
		bjdjPage.setAdvertType(advertType);
		SystemDictionary dictionary = systemDictionaryService.queryById(SystemDictionary.class.getName(), smsTemplate_id);
		bjdjPage.setSmsTemplate(dictionary);
		CommonEntity status = bjdjPage.getStatus();
		status.setVersionFlag(versionFlag);
		bjdjPage.setStatus(status);
		dbDAO.merge(bjdjPage);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjPage bjdjPage = dbDAO.queryById(this.getTableNameFromEntity(BjdjPage.class), id);
		dbDAO.remove(bjdjPage);
		return true;
	}

	@Override
	public BjdjPage queryByUrl(String url) throws ServiceException {
		BjdjPage bjdjPage = dbDAO.executeJPQLForQuerySingle("select c from " + BjdjPage.class.getName() + " c where c.url = ?", BjdjPage.class ,url);
		return bjdjPage;
	}

}