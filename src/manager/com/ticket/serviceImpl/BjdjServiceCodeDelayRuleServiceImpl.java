package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjServiceCodeDelayRule;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjServiceCodeDelayRuleService;

/**
 * 服务码延期规则业务接口实现类
 * @ClassName: IBjdjServiceCodeDelayRuleService   
 * @Description: 提供服务码延期规则操作的增删改查   
 * @author HiSay  
 * @date 2015-12-11 14:46:34
 *
 */
public class BjdjServiceCodeDelayRuleServiceImpl extends BaseServiceImpl<BjdjServiceCodeDelayRule, String> implements IBjdjServiceCodeDelayRuleService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjServiceCodeDelayRuleServiceImpl.class);

	@Override
	public boolean persist(Integer deadline,Integer record, Integer orderValue) throws ServiceException {
		BjdjServiceCodeDelayRule bjdjServiceCodeDelayRule = new BjdjServiceCodeDelayRule();
		bjdjServiceCodeDelayRule.setDelayTime(deadline);
		bjdjServiceCodeDelayRule.setRecord(record);
		CommonEntity status = bjdjServiceCodeDelayRule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjServiceCodeDelayRule.setStatus(status);
		dbDAO.persist(bjdjServiceCodeDelayRule);
		return true;
	}
	
	@Override
	public boolean merge(String id, Integer deadline,Integer record, Integer orderValue) throws ServiceException {
		BjdjServiceCodeDelayRule bjdjServiceCodeDelayRule = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCodeDelayRule.class), id);
		bjdjServiceCodeDelayRule.setDelayTime(deadline);
		bjdjServiceCodeDelayRule.setRecord(record);
		CommonEntity status = bjdjServiceCodeDelayRule.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjServiceCodeDelayRule.setStatus(status);
		dbDAO.merge(bjdjServiceCodeDelayRule);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjServiceCodeDelayRule bjdjServiceCodeDelayRule = dbDAO.queryById(this.getTableNameFromEntity(BjdjServiceCodeDelayRule.class), id);
		dbDAO.remove(bjdjServiceCodeDelayRule);
		return true;
	}

	@Override
	public List<BjdjServiceCodeDelayRule> queryAll() {
		
		List<BjdjServiceCodeDelayRule> list = dbDAO.executeJPQLForQueryEntity(
				"select s from "+BjdjServiceCodeDelayRule.class.getName()+" s order by s.status.orderValue");
		return list;
	}

}