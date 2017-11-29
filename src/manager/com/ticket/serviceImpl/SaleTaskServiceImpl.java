package com.ticket.serviceImpl;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.DepartmentInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.EmployeeOutVisit;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.PhoneVisit;
import com.ticket.pojo.RechargeRecord;
import com.ticket.pojo.SaleTask;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IDepartmentInfoService;
import com.ticket.service.ISaleTaskService;
import com.ticket.util.GeneralUtil;

/**
 * 销售任务业务接口实现类
 * @ClassName: ISaleTaskService   
 * @Description: 提供销售任务操作的增删改查   
 * @author HiSay  
 * @date 2016-05-05 11:18:16
 *
 */
public class SaleTaskServiceImpl extends BaseServiceImpl<SaleTask, String> implements ISaleTaskService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(SaleTaskServiceImpl.class);
	@Resource
	private IDepartmentInfoService departmentInfoService = null;
	public static final String percentFilePath;
	public static final Properties percentProperties;
	
	static{
		
		percentFilePath = SaleTaskServiceImpl.class.getResource("saleTaskPercent.properties").getPath();
		percentProperties = new Properties();
	}
	
	@Override
	public boolean merge(String id, String name, Date startTime,Date endTime,int signCount,double recharge,int phoneCount,int visitCount, String versionFlag) throws ServiceException {
		SaleTask saleTask = dbDAO.queryById(this.getTableNameFromEntity(SaleTask.class), id);
		saleTask.setStartTime(startTime);
		saleTask.setEndTime(endTime);
		
		SystemUser user = getSystemUser();
		if(!(user instanceof EmployeeInfo)){
			
			throw new ServiceException("执行此操作的必须是销售员工");
		}
		
		EmployeeInfo employeeInfo = (EmployeeInfo)user;
		saleTask.setEmployee(employeeInfo);
		DepartmentInfo department = employeeInfo.getDepartment();
		saleTask.setDepartment(department);
		saleTask.setSignCount(signCount);
		saleTask.setRecharge(recharge);
		saleTask.setPhoneCount(phoneCount);
		saleTask.setVisitCount(visitCount);
		saleTask.setRemark(name);
		dbDAO.merge(saleTask);
		return true;
	}

	@Override
	public boolean persist(String name, Date startTime,Date endTime,int signCount,double recharge,int phoneCount,int visitCount, String versionFlag) throws ServiceException {
		SaleTask saleTask = new SaleTask();
		saleTask.setStartTime(startTime);
		saleTask.setEndTime(endTime);
		SystemUser user = getSystemUser();
		if(!(user instanceof EmployeeInfo)){
			
			throw new ServiceException("执行此操作的必须是销售员工");
		}
		
		EmployeeInfo employeeInfo = (EmployeeInfo)user;
		saleTask.setEmployee(employeeInfo);
		DepartmentInfo department = employeeInfo.getDepartment();
		saleTask.setDepartment(department);
		saleTask.setSignCount(signCount);
		saleTask.setRecharge(recharge);
		saleTask.setPhoneCount(phoneCount);
		saleTask.setVisitCount(visitCount);
		saleTask.setRemark(name);
		dbDAO.persist(saleTask);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		SaleTask saleTask = dbDAO.queryById(this.getTableNameFromEntity(SaleTask.class), id);
		dbDAO.remove(saleTask);
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void publish(String taskId, String[] emps, int[] signCounts, 
			double[] recharges, int[] phoneCounts, int[] visitCounts) throws ServiceException {
		
		SaleTask task = get(SaleTask.class, taskId);
		task.setPublished(true);
		merge(task);
		for(int i = 0; i < emps.length; i++){
			
			SaleTask subTask = new SaleTask();
			subTask.setParent(task);
			subTask.setRemark(task.getRemark());
			
			//设置谁的任务
			EmployeeInfo emp = get(EmployeeInfo.class, emps[i]); 
			subTask.setEmployee(emp);
			
			//所属部门
			if(task.getDepartment().getChilds() != null && !task.getDepartment().getChilds().isEmpty()){
				
				DepartmentInfo department = emp.getDepartment();
				subTask.setDepartment(department);
			}
			subTask.setStartTime(task.getStartTime());
			subTask.setEndTime(task.getEndTime());
			subTask.setRecharge(recharges[i]);
			subTask.setSignCount(signCounts[i]);
			subTask.setPhoneCount(phoneCounts[i]);
			subTask.setVisitCount(visitCounts[i]);
			persist(subTask);
		}
	}

	@Override
	public int completionDegreeForSignCount(Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + Agreement.class.getName() + " t where t.secondAuditState=1 and t.status.createTime between ? and ?", 
				Long.class, startDate, endDate);
		return count.intValue();
	}

	@Override
	public double completionDegreeForRecharge(Date startDate, Date endDate) {
		
		Double count = dbDAO.executeJPQLForQuerySingle(
				"select (case when sum(t.amountOfMoney)=null then 0.0 else sum(t.amountOfMoney) end)"
				+ " from " + RechargeRecord.class.getName() + " t where t.status.createTime between ? and ?", 
				Double.class, startDate, endDate);
		return count;
	}

	@Override
	public int completionDegreeForPhoneCount(Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + PhoneVisit.class.getName() + " t where t.status.createTime between ? and ?", 
				Long.class, startDate, endDate);
		return count.intValue();
	}

	@Override
	public int completionDegreeForVisitCount(Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + EmployeeOutVisit.class.getName() + " t where t.status.createTime between ? and ?", 
				Long.class, startDate, endDate);
		return count.intValue();
	}

	@Override
	public int departmentCompletionDegreeForSignCount(DepartmentInfo department, Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + Agreement.class.getName() + " t,"
						+ " " + ChannelCustomerInfo.class.getName() + " cc,"
						+ " " + EmployeeInfo.class.getName() + " e,"
						+ " " + DepartmentInfo.class.getName() + " d "
 						+ " where t.channelCustomerInfo.id=cc.id and cc.employeeInfo.id=e.id and e.department.id=d.id"
 						+ " and t.secondAuditState=1 and d.path like ? and t.status.createTime between ? and ?", 
 						Long.class, '%' + department.getId() + '%', startDate, endDate);
		return count.intValue();
	}

	@Override
	public double departmentCompletionDegreeForRecharge(DepartmentInfo department, Date startDate, Date endDate) {
		
		Double count = dbDAO.executeJPQLForQuerySingle(
				"select (case when sum(t.amountOfMoney)=null then 0.0 else sum(t.amountOfMoney) end)"
						+ " from " + RechargeRecord.class.getName() + " t,"
						+ " " + ChannelCustomerInfo.class.getName() + " cc,"
						+ " " + EmployeeInfo.class.getName() + " e,"
						+ " " + DepartmentInfo.class.getName() + " d "
 						+ " where t.channelCustomerInfoId=cc.id and cc.employeeInfo.id=e.id and e.department.id=d.id and d.path like ? and t.status.createTime between ? and ?", 
				Double.class, '%' + department.getId() + '%', startDate, endDate);
		return count;
	}

	@Override
	public int departmentCompletionDegreeForPhoneCount(DepartmentInfo department, Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + PhoneVisit.class.getName() + " t,"
						+ " " + ChannelCustomerInfo.class.getName() + " cc,"
						+ " " + EmployeeInfo.class.getName() + " e,"
						+ " " + DepartmentInfo.class.getName() + " d "
 						+ " where t.customer.id=cc.id and cc.employeeInfo.id=e.id and e.department.id=d.id and d.path like ? and t.status.createTime between ? and ?", 
 						Long.class, '%' + department.getId() + '%', startDate, endDate);
		return count.intValue();
	}

	@Override
	public int departmentCompletionDegreeForVisitCount(DepartmentInfo department, Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle(
				"select count(t) from " + EmployeeOutVisit.class.getName() + " t,"
						+ " " + ChannelCustomerInfo.class.getName() + " cc,"
						+ " " + EmployeeInfo.class.getName() + " e,"
						+ " " + DepartmentInfo.class.getName() + " d "
 						+ " where t.customer.id=cc.id and cc.employeeInfo.id=e.id and e.department.id=d.id and d.path like ? and t.status.createTime between ? and ?", 
 						Long.class, '%' + department.getId() + '%', startDate, endDate);
		return count.intValue();
	}

	@Override
	public int onesCompletionDegreeForSignCount(EmployeeInfo employee, Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle("select count(t) from " + Agreement.class.getName() + " t"
				+ " where t.secondAuditState=1 and t.employeeInfo.id=? and t.status.createTime between ? and ?", Long.class, employee.getId(), startDate, endDate);
		return count.intValue();
	}

	@Override
	public double onesCompletionDegreeForRecharge(EmployeeInfo employee, Date startDate, Date endDate) {
		
		Double count = dbDAO.executeJPQLForQuerySingle("select (case when sum(t.amountOfMoney)=null then 0.0 else sum(t.amountOfMoney) end)"
				+ " from " + RechargeRecord.class.getName() + " t,"
				+ " " + ChannelCustomerInfo.class.getName() + " cc,"
				+ " " + EmployeeInfo.class.getName() + " e"
				+ " where t.channelCustomerInfoId=cc.id and cc.employeeInfo.id=e.id and e.id=? and t.status.createTime between ? and ?", 
				Double.class, employee.getId(), startDate, endDate);
		return count;
	}

	@Override
	public int onesCompletionDegreeForPhoneCount(EmployeeInfo employee, Date startDate, Date endDate) {
		
		Long count = dbDAO.executeJPQLForQuerySingle("select count(t) from " + PhoneVisit.class.getName() + " t,"
				+ " " + ChannelCustomerInfo.class.getName() + " cc,"
				+ " " + EmployeeInfo.class.getName() + " e"
				+ " where t.customer.id=cc.id and cc.employeeInfo.id=e.id and e.id=? and t.status.createTime between ? and ?", 
				Long.class, employee.getId(), startDate, endDate);
		return count.intValue();
	}

	@Override
	public int onesCompletionDegreeForVisitCount(EmployeeInfo employee, Date startDate, Date endDate) {

		Long count = dbDAO.executeJPQLForQuerySingle("select count(t) from " + EmployeeOutVisit.class.getName() + " t,"
				+ " " + ChannelCustomerInfo.class.getName() + " cc,"
				+ " " + EmployeeInfo.class.getName() + " e"
				+ " where t.customer.id=cc.id and cc.employeeInfo.id=e.id and e.id=? and t.status.createTime between ? and ?", 
				Long.class, employee.getId(), startDate, endDate);
		return count.intValue();
	}

	@Override
	public List<SaleTask> subSaleTask(SaleTask parent) {
		
		List<SaleTask> list = dbDAO.executeJPQLForQuery("select t from " + SaleTask.class.getName() + " t where t.parent=?", SaleTask.class, parent);
		return list;
	}

	@Override
	public double queryAllRechargeCount() throws ServiceException {
		List<SaleTask> list = this.queryAll(SaleTask.class);
		Date now = new Date();
		double count = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(GeneralUtil.isNotNull(list)){
			for(SaleTask saleTask:list){
				if(sdf.format(now).equals(sdf.format(saleTask.getEndTime()))){
					count += saleTask.getRecharge();
				}
			}
		}
		return count;
	}

	@Override
	public Pagination queryWhereInDepartment(EmployeeInfo employeeInfo)
			throws ServiceException {
		Pagination pagination = new Pagination();
		pagination = this.paginationQuery("select s from SaleTask s,EmployeeInfo e where s.employee.id = e.id and e.department.id in (select s.id from DepartmentInfo p , DepartmentInfo s where s.path like concat('%', p.id, '%') and p = ?)", employeeInfo.getDepartment());
		return pagination;
	}

	@Override
	public int getSignCountPercent() {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			if(GeneralUtil.isNotNull(percentProperties.getProperty("signCount"))){
				
				return Integer.parseInt(percentProperties.getProperty("signCount"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setSignCountPercent(int percent) {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			percentProperties.setProperty("signCount", percent + "");
			percentProperties.store(new FileOutputStream(percentFilePath), "销售任务占比");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRechargePercent() {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			if(GeneralUtil.isNotNull(percentProperties.getProperty("recharge"))){
				
				return Integer.parseInt(percentProperties.getProperty("recharge"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setRechargePercent(int percent) {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			percentProperties.setProperty("recharge", percent + "");
			percentProperties.store(new FileOutputStream(percentFilePath), "销售任务占比");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getPhoneCountPercent() {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			if(GeneralUtil.isNotNull(percentProperties.getProperty("phoneCount"))){
				
				return Integer.parseInt(percentProperties.getProperty("phoneCount"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setPhoneCountPercent(int percent) {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			percentProperties.setProperty("phoneCount", percent + "");
			percentProperties.store(new FileOutputStream(percentFilePath), "销售任务占比");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getVisitCountPercent() {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			if(GeneralUtil.isNotNull(percentProperties.getProperty("visitCount"))){
				
				return Integer.parseInt(percentProperties.getProperty("visitCount"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setVisitCountPercent(int percent) {
		
		try {
			percentProperties.load(new FileInputStream(percentFilePath));
			percentProperties.setProperty("visitCount", percent + "");
			percentProperties.store(new FileOutputStream(percentFilePath), "销售任务占比");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(percentFilePath);
		System.out.println(percentProperties);
		percentProperties.setProperty("age", "25");
		try {
			percentProperties.store(new FileOutputStream(percentFilePath), "销售任务占比");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(percentProperties);
	}
}