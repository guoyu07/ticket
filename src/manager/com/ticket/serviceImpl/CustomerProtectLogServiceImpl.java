package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.CustomerProtectLog;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;
import com.ticket.service.IChannelCustomerInfoService;
import com.ticket.service.ICustomerProtectLogService;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.IEmployeeInfoService;
import com.ticket.service.ILetterStationService;
import com.ticket.util.DateUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.PaginationContext;
import com.ticket.util.SmsUtil;
/**
 * 客保日志业务接口实现类
 * @ClassName: ICustomerProtectLogService   
 * @Description: 提供客保日志操作的增删改查   
 * @author HiSay  
 * @date 2016-01-02 10:21:06
 *
 */
public class CustomerProtectLogServiceImpl extends BaseServiceImpl<CustomerProtectLog, String> implements ICustomerProtectLogService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(CustomerProtectLogServiceImpl.class);
	@Resource
	private IChannelCustomerInfoService channelCustomerInfoService = null;
	@Resource
	private ILetterStationService letterStationService = null;
	@Resource
	private IEmployeeInfoService employeeInfoService = null;
	/**
	 * 部门信息的业务层
	 */
	@Resource private IDepartmentInfoService departmentInfoService = null;

	@Override
	public String persist(String channelCustomerInfo_id,EmployeeInfo employeeInfo) throws ServiceException {
		try {
			String result = "录入客保信息错误";
			if(employeeInfo != null){
				//验证客户是否能保护
				result = validateIsProtected(channelCustomerInfo_id, employeeInfo.getId());
				if(Boolean.valueOf(result)){
					CustomerProtectLog customerProtectLog = new CustomerProtectLog();
					customerProtectLog.setChannelCustomerInfo(get(ChannelCustomerInfo.class, channelCustomerInfo_id));
					customerProtectLog.setDepartment(employeeInfo.getDepartment());
					customerProtectLog.setEmployeeInfo(employeeInfo);
					customerProtectLog.setStartTime(DateUtil.getDateToThisDateStart("0", new Date()));
					customerProtectLog.setThreeTime(DateUtil.getDateToThisDateStart("-3", new Date()));
					customerProtectLog.setEndTime(DateUtil.getDateToThisDateStart("-15", new Date()));
					this.persist(customerProtectLog);
					result = "true";
				}
			}else{
				result = "系统管理员不能录入客保信息!";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		CustomerProtectLog customerProtectLog = dbDAO.queryById(this.getTableNameFromEntity(CustomerProtectLog.class), id);
		dbDAO.remove(customerProtectLog);
		return true;
	}

	@Override
	public List<CustomerProtectLog> queryAllList(String channelCustomerInfoId,
			String employeeInfoId, Date startTime,Integer expire, Integer size)
			throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(channelCustomerInfoId)){
				sb.append("and s.channelCustomerInfo.id=?3 ");
				objects.add(new Object[]{3,channelCustomerInfoId});
			}
			if(GeneralUtil.isNotNull(employeeInfoId)){
				sb.append("and s.employeeInfo.id=?4 ");
				objects.add(new Object[]{4,employeeInfoId});
			}
			if(GeneralUtil.isNotNull(startTime)){
				sb.append("and s.endTime >=?5 ");
				objects.add(new Object[]{5,startTime});
			}
			if(GeneralUtil.isNotNull(expire)){
				sb.append("and s.isExpire=?6 ");
				objects.add(new Object[]{6,expire});
			}
			return dbDAO.queryByListNew(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, 
					sb.toString(), objects, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String validateIsProtected(String channelCustomerInfoId,
			String employeeInfoId) throws ServiceException {
		try {
			String result = "验证错误";
			ChannelCustomerInfo channelCustomerInfo = channelCustomerInfoService
							.queryById(ChannelCustomerInfo.class.getSimpleName(), channelCustomerInfoId);
			if(GeneralUtil.isNull(channelCustomerInfo.getEmployeeInfo())){
				result = "公共客户不能申请保护";
				return result;
			}
			if(!channelCustomerInfo.getEmployeeInfo().getId().equals(employeeInfoId)){
				result = "该客户不属于您,不能申请保护";
				return result;
			}
			List<CustomerProtectLog> list2 = queryAllList(channelCustomerInfoId, null, new Date(),0,4);
			if(list2 != null && list2.size() > 0){
				result = "该客户还在保护期,请选择其他的客户!";
				return result;
			}
			List<CustomerProtectLog> list = queryAllList(channelCustomerInfoId, employeeInfoId, null,0, 4);
			if(list != null && list.size() > 2){
				result = "该客户您已经连续保护超过2次,不能申请保护!";
			}else{
				result = "true";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "验证错误";
		}
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
			return dbDAO.queryByPageModuleNew(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, PaginationContext.getOffset(), pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void validateCustomerProtectIsToPublicPool() throws ServiceException {
		try {
			//将客保过期的用户设置到公共池
			List<CustomerProtectLog> list = queryExpireLog(new Date(), 0, null);
			if(list != null && list.size() > 0){
				for(CustomerProtectLog c:list){
					//将客户设置到公共池
					ChannelCustomerInfo channelCustomerInfo = c.getChannelCustomerInfo();
					String employeeInfo_id = channelCustomerInfo.getEmployeeInfo().getId();
					channelCustomerInfo.setEmployeeInfo(null);
					channelCustomerInfoService.merge(channelCustomerInfo);
					//将日志设置成已过期
					c.setIsExpire(1);
					this.merge(c);
					EmployeeInfo employeeInfo = employeeInfoService
								.queryById(EmployeeInfo.class.getSimpleName(), employeeInfo_id);
					StringBuffer sb = new StringBuffer();
					sb.append(employeeInfo.getName());
					if(employeeInfo.getSex().intValue() == 0){
						sb.append("女士,");
					}else{
						sb.append("先生,");
					}
					sb.append("您的客户");
					sb.append(channelCustomerInfo.getName());
					sb.append("保护时间已过期,已于");
					sb.append(DateUtil.parseDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
					sb.append("移入客户公共池!");
					//发送站内信
					letterStationService.persist(employeeInfo_id,"客保过期通知", sb.toString(),null);
					//发送短信
					SmsUtil.sendSms(employeeInfo.getPhone(), sb.toString());
				}
			}
			//将3天内即将到期的客户发送提醒短信 及站内信
			List<CustomerProtectLog> list2 = queryNewLogListLess(new Date(), 0);
			if(list2 != null && list2.size() > 0){
				for(CustomerProtectLog c:list){
					ChannelCustomerInfo channelCustomerInfo = c.getChannelCustomerInfo();
					EmployeeInfo employeeInfo = channelCustomerInfo.getEmployeeInfo();
					StringBuffer sb = new StringBuffer();
					sb.append(employeeInfo.getName());
					if(employeeInfo.getSex().intValue() == 0){
						sb.append("女士,");
					}else{
						sb.append("先生,");
					}
					sb.append("您的客户");
					sb.append(channelCustomerInfo.getName());
					sb.append("保护时间将于");
					sb.append(DateUtil.parseDateToString(c.getThreeTime(), "yyyy-MM-dd HH:mm:ss"));
					sb.append("过期,请抓紧时间沟通联系,过期后将");
					sb.append("移入客户公共池!");
					//发送站内信
					letterStationService.persist(channelCustomerInfo.getEmployeeInfo().getId(),"客保过期通知", sb.toString(),null);
					//发送短信
					SmsUtil.sendSms(employeeInfo.getPhone(), sb.toString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CustomerProtectLog> queryExpireLog(Date date, Integer expire,
			Integer size) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			List<Object[]> objects = new ArrayList<Object[]>();
			if(GeneralUtil.isNotNull(date)){
				sb.append("and s.endTime < ?3 ");
				objects.add(new Object[]{3,date});
			}
			if(GeneralUtil.isNotNull(expire)){
				sb.append("and s.isExpire=?4 ");
				objects.add(new Object[]{4,expire});
			}
			return dbDAO.queryByListNew(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, orderBy, size);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean changeExpire(String id) throws ServiceException {
		try {
			CustomerProtectLog customerProtectLog = queryById(CustomerProtectLog.class.getSimpleName(), id);
			customerProtectLog.setIsExpire(1);
			this.merge(customerProtectLog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String changeEndTime(String id, Integer endTime)
			throws ServiceException {
		try {
			String result = "操作错误";
			if(!validateIsChangeEndTime(id)){
				result = "3天内暂时不会到期,不能延期";
				return result;
			}
			CustomerProtectLog customerProtectLog = queryById(CustomerProtectLog.class.getSimpleName(), id);
			customerProtectLog.setEndTime(DateUtil
						.getDateToThisDateEnd(endTime.toString(), customerProtectLog.getEndTime()));
			this.merge(customerProtectLog);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作错误";
		}
	}

	@Override
	public CustomerProtectLog queryNewLogLess(String channelCustomerInfoId,
			Date threeDate, Integer isExpire) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select c from CustomerProtectLog c where ");
			sb.append("c.channelCustomerInfo.id=?1 ");
			sb.append("and c.threeTime >?2 ");
			sb.append("and c.isExpire=?3 ");
			sb.append("order by c.id desc");
			List<Object[]> objects = new ArrayList<Object[]>();
			objects.add(new Object[]{1,channelCustomerInfoId});
			objects.add(new Object[]{2,threeDate});
			objects.add(new Object[]{3,isExpire});
			return dbDAO.executeJPQLForQuerySingle(sb.toString(), CustomerProtectLog.class, objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CustomerProtectLog queryNewLogThan(String channelCustomerInfoId,
			Date threeDate, Integer isExpire) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select c from CustomerProtectLog c where ");
			sb.append("c.channelCustomerInfo.id=?1 ");
			sb.append("and c.threeTime <?2 ");
			sb.append("and c.isExpire=?3 ");
			sb.append("and c.endTime >?4 ");
			sb.append("order by c.id desc");
			List<Object[]> objects = new ArrayList<Object[]>();
			objects.add(new Object[]{1,channelCustomerInfoId});
			objects.add(new Object[]{2,threeDate});
			objects.add(new Object[]{3,isExpire});
			objects.add(new Object[]{4,threeDate});
			return dbDAO.executeJPQLForQuerySingle(sb.toString(), CustomerProtectLog.class, objects.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerProtectLog> queryNewLogListLess(Date threeDate,
			Integer isExpire) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("and c.threeTime >?2 ");
			sb.append("and c.isExpire=?3 ");
			List<Object[]> objects = new ArrayList<Object[]>();
			objects.add(new Object[]{2,threeDate});
			objects.add(new Object[]{3,isExpire});
			return dbDAO.queryByListNew(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, "order by c.id desc", null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CustomerProtectLog> queryNewLogListThan(Date threeDate,
			Integer isExpire) throws ServiceException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("and c.threeTime <?2 ");
			sb.append("and c.isExpire=?3 ");
			sb.append("and c.endTime >?4 ");
			List<Object[]> objects = new ArrayList<Object[]>();
			objects.add(new Object[]{2,threeDate});
			objects.add(new Object[]{3,isExpire});
			objects.add(new Object[]{4,threeDate});
			return dbDAO.queryByListNew(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag,
					sb.toString(), objects, "order by c.id desc", null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean validateIsChangeEndTime(String id)
			throws ServiceException {
		try {
			CustomerProtectLog customerProtectLog = queryById(CustomerProtectLog.class.getSimpleName(), id);
			if(customerProtectLog != null && customerProtectLog.getIsExpire() == 0){
				Date threeDate = DateUtil.getDateToThisDateStart("3", customerProtectLog.getEndTime());
				if(threeDate.getTime() < new Date().getTime()){
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CustomerProtectLog querybyCustomerId(String channelCustomerInfo_id)
			throws ServiceException {
		return dbDAO.queryByCustom(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.channelCustomerInfo.id =?3", new Object[]{channelCustomerInfo_id});
	}

	@Override
	public Long countIntentionByDept(String dept_id) throws ServiceException {
		return dbDAO.getTotalCount(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.department.id in ("+departmentInfoService.parseClassIds(dept_id)+") and s.status.createTime > ?3", new Object[]{DateUtil.getTodayStart()});
	}

	@Override
	public Long countToday(String versionFlag) throws ServiceException {
		return dbDAO.getTotalCount(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.createTime>?3", new Object[]{DateUtil.getTodayStart()});
	}

	@Override
	public Long countCustomerProtectByDate(Date startDate, Date endDate,
			String dept_id) throws ServiceException{
		return dbDAO.getTotalCount(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, "and s.status.createTime between ?3 and ?4 and s.department.id in ("+departmentInfoService.parseClassIds(dept_id)+")", new Object[]{startDate,endDate});
	}

	@Override
	public boolean isInProtected(String customer_id) throws ServiceException {
		CustomerProtectLog customerProtectLog = dbDAO.queryByCustom(CustomerProtectLog.class.getSimpleName(), deleteFlag, versionFlag, " and s.channelCustomerInfo.id =?3", new Object[]{customer_id});
		if(customerProtectLog == null){
			return false;
		}else{
			if(customerProtectLog.getIsExpire() == 1){
				return false;
			}
		}
		return true;
	}

	@Override
	public Pagination queryWhereInDepartment(EmployeeInfo employeeInfo)
			throws ServiceException {
		Pagination pagination = new Pagination();
		pagination = this.paginationQuery("select c from CustomerProtectLog c where c.department in (select s from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p.id = ? ))", employeeInfo.getDepartment());
		return pagination;
	}

}