package com.ticket.serviceImpl;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjTime;
import com.ticket.pojo.CommonEntity;
import com.ticket.service.IBjdjTimeService;

/**
 * 便捷登机时间段分配业务接口实现类
 * @ClassName: IBjdjTimeService   
 * @Description: 提供便捷登机时间段分配操作的增删改查   
 * @author HiSay  
 * @date 2016-03-23 10:11:25
 *
 */
public class BjdjTimeServiceImpl extends BaseServiceImpl<BjdjTime, String> implements IBjdjTimeService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BjdjTimeServiceImpl.class);
	
	@Override
	public boolean merge(String id, String startTime,String endTime,Integer orderValue, String versionFlag) throws ServiceException {
		BjdjTime bjdjTime = dbDAO.queryById(this.getTableNameFromEntity(BjdjTime.class), id);
		bjdjTime.setStartTime(startTime);
		bjdjTime.setEndTime(endTime);
		CommonEntity status = bjdjTime.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjTime.setStatus(status);
		dbDAO.merge(bjdjTime);
		return true;
	}

	@Override
	public boolean persist(String startTime,String endTime,Integer orderValue, String versionFlag) throws ServiceException {
		BjdjTime bjdjTime = new BjdjTime();
		bjdjTime.setStartTime(startTime);
		bjdjTime.setEndTime(endTime);
		CommonEntity status = bjdjTime.getStatus();
		status.setVersionFlag(versionFlag);
		status.setOrderValue(orderValue);
		bjdjTime.setStatus(status);
		dbDAO.persist(bjdjTime);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		BjdjTime bjdjTime = dbDAO.queryById(this.getTableNameFromEntity(BjdjTime.class), id);
		dbDAO.remove(bjdjTime);
		return true;
	}

	@Override
	public List<BjdjTime> getAll() throws ServiceException {
		List<BjdjTime> list = dbDAO.executeJPQLForQuery("select s from " + BjdjTime.class.getName() + " s where s.status.deleteFlag=0 order by s.status.orderValue ",BjdjTime.class);
		return list;
	}

}