package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ChannelCustomerInfo;
import com.ticket.pojo.EmployeeInfo;
import com.ticket.pojo.Pagination;

/**
 * 渠道客户信息业务接口
 * 
 * @ClassName: IChannelCustomerInfoService
 * @Description: 提供渠道客户信息操作的增删改查
 * @author HiSay
 * @date 2015-11-04 10:39:40
 * 
 */
public interface IChannelCustomerInfoService extends
		IBaseService<ChannelCustomerInfo, String> {
	/**
	 * 保存渠道客户信息实体
	 * 
	 * @Title: IChannelCustomerInfoService
	 * @Description:
	 * @param @param customerName 客户名称
	 * @param @param loginId 用户名
	 * @param @param channelTypeId 渠道分类
	 * @param @param openAccountDate 开户日期
	 * @param @param contact 联系人
	 * @param @param contactPhone 联系电话
	 * @param @param openAccountMoney 开户金额
	 * @param @param payWay 支付方式
	 * @param @return 保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	boolean persist(String customerName, String loginId, String password,
			String channelTypeId, Date openAccountDate, String contact,
			String contactPhone, Double openAccountMoney, String payWay,
			Double balance, String favouredPolicyId,
			String email, EmployeeInfo employeeInfo, String industry_id,
			String channelPosition_id, Integer state) throws ServiceException;

	/**
	 * @author wangjiafeng 添加基本渠道客户
	 * @method persist
	 * @param customerName
	 * @param channelTypeId
	 * @param contact
	 * @param contactPhone
	 * @param industry
	 * @param email
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 * @return boolean
	 * @date 2015-12-31 上午11:08:49
	 */
	boolean persist(String customerName, String channelTypeId, String contact,
			String contactPhone, String industry, String email,
			EmployeeInfo employeeInfo, String industry_id,
			String channelPosition_id) throws ServiceException;

	/**
	 * 修改渠道客户信息实体
	 * 
	 * @Title: IChannelCustomerInfoService
	 * @Description:
	 * @param @param customerName 客户名称
	 * @param @param loginId 用户名
	 * @param @param channelTypeId 渠道分类
	 * @param @param openAccountDate 开户日期
	 * @param @param contact 联系人
	 * @param @param contactPhone 联系电话
	 * @param @param openAccountMoney 开户金额
	 * @param @param payWay 支付方式
	 * @param @return 修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	boolean merge(String id, String customerName, String loginId,
			String password, String channelTypeId, Date openAccountDate,
			String contact, String contactPhone, Double openAccountMoney,
			String payWay, Double balance, String favouredPolicyId,
			String industry, String email, EmployeeInfo employeeInfo,
			String industry_id, String channelPosition_id, String idCard,
			String yyzzNumber, List<String> annexs) throws ServiceException;

	/**
	 * @author wangjiafeng 编辑基本客户信息
	 * @method merge
	 * @param id
	 * @param customerName
	 * @param channelTypeId
	 * @param contact
	 * @param contactPhone
	 * @param industry
	 * @param email
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 * @return boolean
	 * @date 2015-12-31 上午11:15:03
	 */
	boolean merge(String id, String customerName, String channelTypeId,
			String contact, String contactPhone, String industry, String email,
			EmployeeInfo employeeInfo, String industry_id,
			String channelPosition_id) throws ServiceException;

	/**
	 * 移除渠道客户信息实体
	 * 
	 * @Title: IChannelCustomerInfoService
	 * @Description:
	 * @param @param id 渠道客户信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException
	 * @return
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * @author wangjiafeng 获取用户信息
	 * @method login
	 * @param loginId
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @return ChannelCustomerInfo
	 * @date 2015-12-31 上午11:15:48
	 */
	ChannelCustomerInfo login(String loginId, String password)
			throws ServiceException;

	/**
	 * 查询客户名称是否已注册
	 * 
	 * @param loginId
	 *            客户名称
	 * @return
	 * @throws ServiceException
	 */
	ChannelCustomerInfo IsExit(String customerName) throws ServiceException;

	boolean updatePassword(String id, String password) throws ServiceException;

	/**
	 * @author wangjiafeng 获取所有的渠道客户
	 * @method queryAll
	 * @param keyword
	 * @param employeeInfo
	 * @param isPublic
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-31 上午11:20:55
	 */
	Pagination queryAll(String keyword, EmployeeInfo employeeInfo,
			Boolean isPublic, Integer state, String order, Integer pageSize)
			throws ServiceException;
	
	/**
	 * 获取所有的意向客户
	 * @param keyword
	 * @param employeeInfo
	 * @param isPublic
	 * @param state
	 * @param order
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAll3(String keyword, EmployeeInfo employeeInfo,
			Boolean isPublic, Integer state, String order, Integer pageSize)
			throws ServiceException;

	/**
	 * @author wangjiafeng 获取所有的渠道客户
	 * @method queryAll
	 * @param keyword
	 * @param isEmployeeNull
	 * @param isPublic
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-31 上午11:20:55
	 */
	Pagination queryAll2(String keyword, EmployeeInfo employeeInfo,
			Boolean isEmployeeNull, Integer state, String order,
			Integer pageSize) throws ServiceException;

	/**
	 * @author wangjiafeng 查询用户是否存在
	 * @method queryByLoginIdExist
	 * @param loginId
	 * @return
	 * @throws ServiceException
	 * @return ChannelCustomerInfo
	 * @date 2015-12-31 下午02:13:55
	 */
	ChannelCustomerInfo queryByLoginIdExist(String loginId)
			throws ServiceException;

	/**
	 * @author wangjiafeng 批量放弃客户
	 * @method batchGiveUpCustomer
	 * @param idsValue
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-31 下午05:29:01
	 */
	Boolean batchGiveUpCustomer(String idsValue) throws ServiceException;

	/**
	 * @author wangjiafeng 放弃客户
	 * @method singleGiveUpCustomer
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-31 下午05:29:53
	 */
	Boolean singleGiveUpCustomer(String id) throws ServiceException;

	/**
	 * @author wangjiafeng 获取渠道客户列表
	 * @method queryAllList
	 * @param keyword
	 * @param isPublic
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<ChannelCustomerInfo>
	 * @date 2016-1-2 上午11:58:25
	 */
	List<ChannelCustomerInfo> queryAllList(String keyword, Integer size)
			throws ServiceException;

	/**
	 * @author wangjiafeng 获取所有的渠道客户列表
	 * @method queryAllList
	 * @param keyword
	 * @param employeeInfo
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<ChannelCustomerInfo>
	 * @date 2016-1-3 下午04:52:56
	 */
	List<ChannelCustomerInfo> queryAllList(String keyword,
			EmployeeInfo employeeInfo, Integer size) throws ServiceException;

	/**
	 * @author wangjiafeng 获取所有的渠道客户
	 * @method queryAllList
	 * @param keyword
	 * @param employeeInfo_id
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<ChannelCustomerInfo>
	 * @date 2016-1-3 下午04:54:09
	 */
	List<ChannelCustomerInfo> queryAllListId(String keyword,
			String employeeInfo_id, Integer size) throws ServiceException;

	/**
	 * @author wangjiafeng 获取渠道客户状态
	 * @method getChannelCustomerInfoState
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2016-1-11 下午02:45:29
	 */
	String getChannelCustomerInfoState(String id) throws ServiceException;

	/**
	 * @author wangjiafeng 手机录入客户信息
	 * @method addWap
	 * @param customerName
	 * @param loginId
	 * @param password
	 * @param contact
	 * @param contactPhone
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2016-1-19 下午05:42:56
	 */
	Boolean addWap(String customerName, String loginId, String password,
			String contact, String contactPhone, EmployeeInfo employeeInfo)
			throws ServiceException;

	/**
	 * @author wangjiafeng 初始化渠道客户
	 * @method initCustomer
	 * @throws ServiceException
	 * @return void
	 * @date 2016-2-23 下午02:34:10
	 */
	void initCustomer() throws ServiceException;

	/**
	 * 客户转接
	 * 
	 * @param afterEmployee_id
	 *            转接之后的员工ID
	 * @param beforeCustomer_id
	 *            客户ID
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean transferCustomer(String afterEmployee_id, String customer_id,
			String versionFlag) throws ServiceException;

	/**
	 * 根据员工查询意向客户
	 * 
	 * @param employee_id
	 *            员工id
	 * @return
	 */
	List<ChannelCustomerInfo> queryIntentionCustomerListByEmployee(
			String employee_id);

	/**
	 * 根据员工查询渠道客户
	 * 
	 * @param employee_id
	 *            员工id
	 * @return
	 */
	List<ChannelCustomerInfo> queryChannelCustomerListByEmployee(
			String employee_id);

	/**
	 * 根据客户名称关键词查询所有的客户信息
	 * 
	 * @param keyword
	 *            关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> getAllCustomer(String keyword, String versionFlag)
			throws ServiceException;

	/**
	 * 根据渠道客户类别查询渠道客户列表
	 * 
	 * @param channelTypeId
	 *            渠道客户类别id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> queryChannelCustomerList(String channelTypeId,
			String versionFlag) throws ServiceException;

	/**
	 * 统计意向客户总数
	 * 
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Long countTodayIntentionCustomer(String versionFlag)
			throws ServiceException;

	/**
	 * 根据部门统计意向客户
	 * 
	 * @param dept_id
	 *            部门id
	 * @return
	 * @throws ServiceException
	 */
	Long countIntentionByDept(String dept_id) throws ServiceException;

	/**
	 * 根据时间段统计意向客户
	 * 
	 * @param start
	 *            起始日期
	 * @param end
	 *            终止日期
	 * @param dept_id
	 *            部门id
	 * @return
	 */
	Long countIntentionCustomerByDate(Date startDate, Date endDate,
			String dept_id) throws ServiceException;

	/**
	 * 查询意向客户列表
	 * 
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> queryIntentionCustomerList(String versionFlag)
			throws ServiceException;

	/**
	 * 查找渠道客户总数
	 * 
	 * @return
	 * @throws ServiceException
	 */
	long queryAllCount(String employee_id) throws ServiceException;

	/**
	 * 查找这个月渠道客户的增加数
	 * 
	 * @return
	 * @throws ServiceException
	 */
	long queryThisMonthAddCount(String employee_id) throws ServiceException;

	/**
	 * 获取某员工下的新客户数量
	 * 
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	long countNewCustomer(String employee_id, Date startDate, Date endDate,
			String channelTypeId) throws ServiceException;

	/**
	 * 获取某员工下的老客户数量
	 * 
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	long countOldCustomer(String employee_id, Date startDate, Date endDate,
			String channelTypeId) throws ServiceException;

	/**
	 * 根据员工查找属于他的客户
	 * 
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> queryCustomerByEmployeeId(String employee_id)
			throws ServiceException;

	/**
	 * 根据员工信息查找指定渠道类型的渠道客户
	 * 
	 * @param employee_id
	 * @param channelTypeId
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> queryChannelCustomerListByEmployeeAndChannelType(
			String employee_id, String channelTypeId) throws ServiceException;

	/**
	 * 根据部门获取意向客户列表
	 * 
	 * @param deptPath
	 *            部门路径
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<ChannelCustomerInfo> queryByTopDepartment(String deptPath,
			String versionFlag) throws ServiceException;

	/**
	 * 得到渠道客户的最近一个维护时间（电话拜访和外出拜访的最近时间）
	 * 
	 * @param channelCustomber_id
	 * @return
	 */
	Date getLastDate(String channelCustomber_id);
	
	/**
	 * 查找员工所属的部门的全部客户
	 * @param employeeInfo
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryAllWhereInDepartment(EmployeeInfo employeeInfo,Integer state) throws ServiceException;
}