package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.HitSingleLog;
import com.ticket.pojo.Pagination;
import com.ticket.service.IAgreementService;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IHitSingleLogService;
import com.ticket.util.PaginationContext;

/**
 * 撞单日志业务接口实现类
 * @ClassName: IHitSingleLogService   
 * @Description: 提供撞单日志操作的增删改查   
 * @author HiSay  
 * @date 2016-01-03 09:38:37
 *
 */
public class HitSingleLogServiceImpl extends BaseServiceImpl<HitSingleLog, String> implements IHitSingleLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(HitSingleLogServiceImpl.class);
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private ICustomerProtectLogService customerProtectLogService = null;
	@Resource
	private IAgreementService agreementService = null;
	

	@Override
	public String persist(String channelCustomerInfo_id,EmployeeInfo employeeInfo,String agreement_id) throws ServiceException {
		try {
			String result= "操作错误";
			if(employeeInfo != null){
				HitSingleLog hitSingleLog = new HitSingleLog();
				hitSingleLog.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfo_id));
				hitSingleLog.setEmployeeInfo(employeeInfo);
				hitSingleLog.setAgreement(get(Agreement.class, agreement_id));
				this.persist(hitSingleLog);
				result = "true";
			}else{
				result = "系统管理员不能添加撞单申请!";
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "添加撞单申请错误";
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		HitSingleLog hitSingleLog = dbDAO.queryById(this.getTableNameFromEntity(HitSingleLog.class), id);
		dbDAO.remove(hitSingleLog);
		return true;
	}


	@Override
	public Pagination queryAll(EmployeeInfo employeeInfo, Integer pageSize)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			if(employeeInfo != null){
				sb.append("and s.employeeInfo.id=?3 ");
				objects.add(new Object[]{3,employeeInfo.getId()});
			}
			return dbDAO.queryByPageModuleNew(HitSingleLog.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeState(String id, Integer state, String remark)
			throws ServiceException {
		try {
			HitSingleLog hitSingleLog = queryById(HitSingleLog.class.getSimpleName(), id);
			hitSingleLog.setState(state);
			hitSingleLog.setRemark(remark);
			this.merge(hitSingleLog);
			//审核通过
			if(state.intValue() == 1){
				CustomerProtectLog customerProtectLog = customerProtectLogService
				.queryNewLogLess(hitSingleLog.getChannelCustomerInfo().getId(),
						hitSingleLog.getStatus().getCreateTime(), 0);
				//如果前三天谁收回款项,提交撞单申请,客户就是谁的
				if(customerProtectLog != null){
					ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
								.queryById(ChannelCustomerInfo.class.getSimpleName(),
										hitSingleLog.getChannelCustomerInfo().getId());
					channelCustomerInfo.setEmployeeInfo2(hitSingleLog.getEmployeeInfo());
					channelCustomerInfoService.merge(channelCustomerInfo);
					customerProtectLog.setIsExpire(1);
					customerProtectLogService.merge(customerProtectLog);
					//判断客户属于谁,删除并废除相关合同
					agreementService.changeAgreementByHitSingleLog(channelCustomerInfo.getId(),
								channelCustomerInfo.getEmployeeInfo().getId());
				}else{
					ChannelCustomerInfo channelCustomerInfo = hitSingleLog.getChannelCustomerInfo();
					channelCustomerInfo.setEmployeeInfo2(channelCustomerInfo.getEmployeeInfo());
					channelCustomerInfoService.merge(channelCustomerInfo);
					// 查看是否是4到15天内收到款项的
					customerProtectLog = customerProtectLogService.queryNewLogThan(hitSingleLog.getChannelCustomerInfo().getId(), 
									hitSingleLog.getStatus().getCreateTime(), 0);
					if(customerProtectLog != null){
						customerProtectLog.setIsExpire(1);
						customerProtectLogService.merge(customerProtectLog);
					}
					//判断客户属于谁,删除并废除相关合同
					agreementService.changeAgreementByHitSingleLog(channelCustomerInfo.getId(),
								channelCustomerInfo.getEmployeeInfo().getId());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}