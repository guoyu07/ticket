package com.ticket.service;
import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Agreement;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;


/**
 * 客户合同业务接口
 * @ClassName: IAgreementService   
 * @Description: 提供客户合同操作的增删改查   
 * @author HiSay  
 * @date  2015-11-04 14:49:37
 *
 */
public interface IAgreementService extends IBaseService<Agreement, String> {
	/**
	 * 保存客户合同实体
	 * @Title: IAgreementService
	 * @Description:
	 * @param @param name  合同名称
	 * @param @param applayClassId  申请类别
	 * @param @param employee_id  员工
	 * @param @param applyDate  申请时间
	 * @param @param canApplayCount  可申请数
	 * @param @param gotCount  当前已领取数
	 * @param @param chargeStatus  未批复原因
	 * @param @param receiveDate  领用日期
	 * @param @param signingDate  签订日期
	 * @param @param receivablesDate  收款日期
	 * @param @param returnDate  交回日期
	 * @param @param auditDate  审核日期
	 * @param @param openAccountDate  开户日期
	 * @param @param onlineDate  上线日期
	 * @param @param firstRenewMoney  签订金额
	 * @param @param firstRenewDate  首次续费
	 * @param @param lastCheckDate  上次清查日期
	 * @param @param customerId  渠道客户ID
	 * @param @param amountOfMoney  金额
	 * @param @param content     合同内容
	 * @param @param agreementState     合同状态
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	String persist(String name,String applayClassId,EmployeeInfo employeeInfo,
			String applyDate,Integer canApplayCount,Integer gotCount,String chargeStatus,
			Date receiveDate,Date signingDate,Date receivablesDate,Date returnDate,String auditDate,
			Date openAccountDate,Date onlineDate,Double firstRenewMoney,Date firstRenewDate,
			Date lastCheckDate,String customerId,Double amountOfMoney,String content,
			Integer agreementState) throws ServiceException;
	
	/**
	 * 修改客户合同实体
	 * @Title: IAgreementService
	 * @Description:
	 * @param @param name  合同名称
	 * @param @param applayClassId  申请类别
	 * @param @param employee_id  员工
	 * @param @param applyDate  申请时间
	 * @param @param canApplayCount  可申请数
	 * @param @param gotCount  当前已领取数
	 * @param @param chargeStatus  未批复原因
	 * @param @param receiveDate  领用日期
	 * @param @param signingDate  签订日期
	 * @param @param receivablesDate  收款日期
	 * @param @param returnDate  交回日期
	 * @param @param auditDate  审核日期
	 * @param @param openAccountDate  开户日期
	 * @param @param onlineDate  上线日期
	 * @param @param firstRenewMoney  签订金额
	 * @param @param firstRenewDate  首次续费
	 * @param @param lastCheckDate  上次清查日期
	 * @param @param customerId  渠道客户ID
	 * @param @param amountOfMoney  金额
	 * @param @param content     合同内容
	 * @param @param agreementState     合同状态
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,String name, String applayClassId,Date applyDate,
			Integer canApplayCount,Integer gotCount,String chargeStatus,Date receiveDate,
			Date signingDate,Date receivablesDate,Date returnDate,Date auditDate,
			Date openAccountDate,Date onlineDate,Double firstRenewMoney,Date firstRenewDate,
			Date lastCheckDate,String customerId,Double amountOfMoney, String content,
			Integer agreementState) throws ServiceException;
	
	//修改基础合同信息
	boolean merge(String id,String name, String applayClassId,String applyDate,String content) throws ServiceException;
	//修改审核阶段信息
	boolean merge(String id,String name,String auditDate, EmployeeInfo auditEmployee,String chargeStatus,Date lastCheckDate,
			Integer agreementState,String picture) throws ServiceException;
	//修改签回阶段信息
	boolean merge(String id,String contacts,String phone,String email,String payway, 
			Date signingDate, Double firstRenewMoney,Integer agreementState,String picture,
			String qhRemark) throws ServiceException;
	/**
	 * 移除客户合同实体
	 * @Title: IAgreementService
	 * @Description: 
	 * @param @param id 客户合同ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	
	/**
	 * 根据员工ID以及合同状态获取员工合同记录
	 * @param versionFlag
	 * @param pageSize    页面大小
	 * @param employeeId  员工ID
	 * @param agreementState  合同状态
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByEmployeeId(String versionFlag, int pageSize, String employeeId,Integer agreementState) throws ServiceException;
	
	/**
	 * 根据合同状态获取合同记录
	 * @param versionFlag
	 * @param pageSize    页面大小
	 * @param agreementState  合同状态
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByState(String versionFlag, int pageSize,Integer agreementState) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据渠道客户获取合同
	 * @method queryByChannelCustomerInfo
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Agreement
	 * @date 2016-1-4 下午01:48:42
	 */
	Agreement queryByChannelCustomerInfo(String channelCustomerInfo_id,Integer agreementState,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 验证合同是否重复
	 * @method validateIsExist
	 * @param channelCustomerInfo_id
	 * @param agreementState
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-4 下午01:57:36
	 */
	Boolean validateIsExist(String channelCustomerInfo_id,Integer agreementState,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的合同
	 * @method queryAll
	 * @param employeeInfo
	 * @param agreementStates
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-6 上午09:47:34
	 */
	Pagination queryAll(EmployeeInfo employeeInfo,String agreementStates,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 修改合同状态
	 * @method changeAgreementState
	 * @param id
	 * @param agreementState
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-6 上午09:49:31
	 */
	Boolean changeAgreementState(String id,Integer agreementState) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取还在生效的合同
	 * @method queryNewAgreement
	 * @param channelCustomerInfo_id
	 * @param effective
	 * @return
	 * @throws ServiceException
	 * @return Agreement
	 * @date 2016-1-12 上午10:25:20
	 */
	Agreement queryNewAgreement(String channelCustomerInfo_id,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 验证还用户是否在保护期
	 * @method validateIsAdd
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-12 上午11:16:15
	 */
	String validateIsAdd(String channelCustomerInfo_id,EmployeeInfo employeeInfo) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 合同作废
	 * @method changeEffective
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-15 上午10:05:04
	 */
	Boolean changeEffective(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取最新的客户添加的合同
	 * @method getNewAgreement
	 * @param channelCustomer_id
	 * @param employeeInfo_id
	 * @param state
	 * @param effective
	 * @return
	 * @throws ServiceException
	 * @return Agreement
	 * @date 2016-1-15 上午11:20:15
	 */
	Agreement getNewAgreement(String channelCustomer_id,String employeeInfo_id,String state,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 改变合同状态
	 * @method changeAgreementByHitSingleLog
	 * @param channelCustomerInfo_id
	 * @param employeeInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-15 上午11:46:38
	 */
	Boolean changeAgreementByHitSingleLog(String channelCustomerInfo_id,String employeeInfo_id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取指定客户的合同列表
	 * @method queryByCustomerInfo
	 * @param channelCustomerInfo_id
	 * @param effective
	 * @return
	 * @throws ServiceException
	 * @return List<Agreement>
	 * @date 2016-1-15 上午11:52:34
	 */
	List<Agreement> queryByCustomerInfo(String channelCustomerInfo_id,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据企业名称,员工查询该合同是否存在
	 * @method queryByChannelCustomerInfoName
	 * @param employeeInfo
	 * @param name
	 * @param effective
	 * @return
	 * @throws ServiceException
	 * @return Agreement
	 * @date 2016-1-20 下午04:05:08
	 */
	Agreement queryByChannelCustomerInfoName(EmployeeInfo employeeInfo,String name,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 搜索合同信息
	 * @method queryByChannelCustomerInfoName
	 * @return
	 * @throws ServiceException
	 * @return List<Agreement>
	 * @date 2016-1-20 下午04:18:29
	 */
	List<Agreement> queryListByChannelCustomerInfoName(EmployeeInfo employeeInfo,String name,Integer effective) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 手机添加
	 * @method addByWap
	 * @param employeeInfo
	 * @param name
	 * @param remark
	 * @return
	 * @throws ServiceException
	 * @return string
	 * @date 2016-1-20 下午04:24:32
	 */
	String addByWap(EmployeeInfo employeeInfo,String name,String remark) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 发放合同
	 * @method fafang
	 * @param id
	 * @param picture
	 * @param state
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-21 上午10:39:56
	 */
	Boolean fafang(String id,Integer secondAuditState,String secondAuditRemark,Integer state,String employeeInfo_id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有有效签约的合同列表
	 * @method queryByStateAndEffective
	 * @param state
	 * @param effective
	 * @return
	 * @throws ServiceException
	 * @return List<Agreement>
	 * @date 2016-2-23 下午02:46:39
	 */
	List<Agreement> queryByStateAndEffective(Integer state,Integer effective,Integer size) throws ServiceException;
	
	/**
	 * 合同总数
	 * @return
	 */
	long allCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 审批同数
	 * @return
	 */
	long approvalCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 发放合同数
	 * @return
	 */
	long issueCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 合同签回数
	 * @return
	 */
	long signBackCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 归档签回数
	 * @return
	 */
	long archiveCount(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 合同数据列表
	 * @return
	 */
	List<Agreement> allList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 审批数据列表
	 * @return
	 */
	List<Agreement> approvalList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 发放数据列表
	 * @return
	 */
	List<Agreement> issueList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 签回数数据列表
	 * @return
	 */
	List<Agreement> signBackList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 归档数据列表
	 * @return
	 */
	List<Agreement> archiveList(Date startDate, Date endDate,EmployeeInfo employeeInfo,int type);
	
	/**
	 * 查找所有属于该部门的所有合同
	 * @param employeeInfo
	 * @param agreementStates
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereIsDepartMent(EmployeeInfo employeeInfo,String agreementStates,Integer pageSize) throws ServiceException;
	
	/**
	 * 根据员工和客户查找合同
	 * @param employeeInfo
	 * @param customerId
	 * @param eff
	 * @return
	 * @throws ServiceException
	 */
	Agreement queryByEmployeeIdAndName(EmployeeInfo employeeInfo,String customerId,Integer eff) throws ServiceException;
	
	/**
	 * 根据员工和客户查找合同
	 * @param employeeInfo
	 * @param customerId
	 * @param eff
	 * @return
	 * @throws ServiceException
	 */
	List<Agreement> queryByEmployeeIdAndName2(EmployeeInfo employeeInfo,String customerId,Integer eff) throws ServiceException;
	
	/**
	 * 根据客户id查找合同
	 * @return
	 * @throws ServiceException
	 */
	Agreement querybyCustomerId(String customerId) throws ServiceException;
}