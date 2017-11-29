package com.ticket.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Member;
import com.ticket.pojo.Pagination;


/**
 * 会员信息业务接口
 * @ClassName: IMemberService   
 * @Description: 提供会员信息操作的增删改查   
 * @author HiSay  
 * @date  2015-11-13 18:01:16
 *
 */
public interface IMemberService extends IBaseService<Member, String> {
	/**
	 * 保存会员信息实体
	 * @Title: IMemberService
	 * @Description:
	 * @param @param memberLevel_id  会员等级
	 * @param @param loginName  登录名
	 * @param @param loginPwd  登录密码
	 * @param @param nickName  昵称
	 * @param @param realName  真实姓名
	 * @param @param IDCard  身份证号
	 * @param @param phone  联系电话
	 * @param @param qq  QQ号码
	 * @param @param email  联系邮箱
	 * @param @param address  联系地址
	 * @param @param record  积分
	 * @param @param experience  经验值
	 * @param @param visitorType  旅客类型
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,String IDCard,String phone,String qq,String email,String address,Integer record,String experience,String visitorType, String versionFlag) throws ServiceException;
	/**
	 * 修改会员信息实体
	 * @Title: IMemberService
	 * @Description:
	 * @param @param memberLevel_id  会员等级
	 * @param @param loginName  登录名
	 * @param @param loginPwd  登录密码
	 * @param @param nickName  昵称
	 * @param @param realName  真实姓名
	 * @param @param IDCard  身份证号
	 * @param @param phone  联系电话
	 * @param @param qq  QQ号码
	 * @param @param email  联系邮箱
	 * @param @param address  联系地址
	 * @param @param record  积分
	 * @param @param experience  经验值
	 * @param @param visitorType  旅客类型
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,String IDCard,String phone,String qq,String email,String address,Integer record,String experience,String visitorType, String versionFlag) throws ServiceException;
	
	/**
	 * 移除会员信息实体
	 * @Title: IMemberService
	 * @Description: 
	 * @param @param id 会员信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 批量彻底删除会员信息
	 * @param idsValue  选中的会员id
	 * @param versionFlag 版本标识
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词查询会员信息
	 * @param keyword
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByKeyword(String keyword, Integer pageSize, String versionFlag) throws ServiceException;

	/**
	 * @Description：通过字段名和字段值获取一批对象
	 * @param columnName
	 * @param value
	 * @return
	 */
	List<Member> getByColumn(String columnName, String value);

	/**
	 * 根据登录名密码查询会员实体
	 * @param loginName
	 * @param loginPwd
	 * @return
	 * @throws ServiceException
	 */
	Member queryByLoginnameAndLoginpwd(String loginName, String loginPwd) throws ServiceException;
	
	/**
	 * 根据邮箱密码查询会员实体
	 * @param loginName
	 * @param loginPwd
	 * @return
	 * @throws ServiceException
	 */
	Member queryByEmailAndLoginpwd(String email, String loginPwd) throws ServiceException;

	/**
	 * 验证电话号码是否已被注册
	 * @param phone
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean validePhoneExist(String phone, String versionFlag) throws ServiceException;

	/**
	 * 根据电话号码和登录密码查询会员实体
	 * @param phone
	 * @param password
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Member queryByMobileAndPwd(String phone, String password, String versionFlag) throws ServiceException;

	/**
	 * 根据电话号码查询会员实体
	 * @param mobile  电话号码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Member queryByMobile(String mobile, String versionFlag);
	
	/**
	 * 根据URL查询会员实体
	 * @param mobile  电话号码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Member queryByUrl(String url) throws ServiceException;
	
	/**
	 * @Description：通过手机号码生成用户，并发送短信（用于自动注册）
	 * @param mobile
	 * @return
	 */
	Member generateByMobileAndSendSms(String mobile) throws ServiceException;
	
	/**
	 * @Description：通过手机号码生成用户，并发送短信（用于自动注册）
	 * @param mobile
	 * @return
	 */
	Member generateByMobileAndSendSms(String mobile, boolean autoLogin) throws ServiceException;

	/**
	 * @Description：通过电子邮箱生成用户，并发送邮箱通知（用于自动注册）
	 * @param email
	 * @return
	 */
	Member generateByEmailAndSendSms(String email) throws ServiceException;
	
	/**
	 * @Description：通过身份证号得到用户
	 * @param idCard
	 * @return
	 */
	Member getByIdCard(String idCard);
	
	/**
	 * @Description：混合登录（用户名，邮箱，电话号码）
	 * @param EmployeeId
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	Member queryByMixAndPwd(String mix, String password) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 渠道客户添加会员
	 * @method add
	 * @param memberLevel_id
	 * @param loginName
	 * @param loginPwd
	 * @param nickName
	 * @param realName
	 * @param IDCard
	 * @param phone
	 * @param qq
	 * @param email
	 * @param adress
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-30 上午09:29:57
	 */
	Boolean add(String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,
			String IDCard,String phone,String qq,String email,String address,
			String channelCustomerInfo_id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 渠道客户编辑会员
	 * @method update
	 * @param id
	 * @param memberLevel_id
	 * @param loginName
	 * @param loginPwd
	 * @param nickName
	 * @param realName
	 * @param IDCard
	 * @param phone
	 * @param qq
	 * @param email
	 * @param adress
	 * @param channelCustomerInfo_id
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-30 上午09:30:20
	 */
	Boolean update(String id,String memberLevel_id,String loginName,String loginPwd,String nickName,String realName,
			String IDCard,String phone,String qq,String email,String address) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 验证用户名是否存在
	 * @method validateLoginNameExist
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-30 上午09:50:05
	 */
	Boolean validateLoginNameExist(String loginName) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 验证邮箱是否存在
	 * @method validateEmailExist
	 * @param email
	 * @return
	 * @throws ServiceException
	 * @return Boolean
	 * @date 2015-12-30 上午09:50:28
	 */
	Boolean validateEmailExist(String email) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 查询指定渠道客户的会员分页模型
	 * @method queryByChannelCustomerInfo
	 * @param channelCustomerInfo_id
	 * @param keyword
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2015-12-30 上午10:09:07
	 */
	Pagination queryByChannelCustomerInfo(String channelCustomerInfo_id,String keyword,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 查询会员
	 * @method queryByChannelCustomerInfoList
	 * @param channelCustomerInfo_id
	 * @param keyword
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<Member>
	 * @date 2015-12-30 下午03:16:47
	 */
	List<Member> queryByChannelCustomerInfoList(String channelCustomerInfo_id,String keyword,Integer size) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 导入会员
	 * @method batchLeadingInMember
	 * @param request
	 * @param file
	 * @return
	 * @throws ServiceException
	 * @return List<Member>
	 * @date 2016-1-6 下午04:11:36
	 */
	List<Member> batchLeadingInMember(String channelCustomerInfo_id,HttpServletRequest request,String file) throws ServiceException;
	/**
	 * 根据会员特性查询会员列表
	 * @param massObjCharacter  群发对象特性
	 * @param versionFlag  版本标识
	 * @return
	 */
	List<Member> queryMemberByCharacter(String massObjCharacter, String versionFlag);
	/**
	 * 根据电话号码查询用户信息
	 * @param mobile 电话号码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Member queryByPhone(String mobile, String versionFlag) throws ServiceException;
	
	/**
	 * 清理分享用户
	 * @throws ServiceException
	 */
	void clearShareMember() throws ServiceException;
}