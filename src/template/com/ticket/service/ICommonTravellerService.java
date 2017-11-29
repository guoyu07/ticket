package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonTraveller;
import com.ticket.pojo.Member;


/**
 * 常用旅客业务接口
 * @ClassName: ICommonTravellerService   
 * @Description: 提供常用旅客操作的增删改查   
 * @author HiSay  
 * @date  2016-01-05 16:30:37
 *
 */
public interface ICommonTravellerService extends IBaseService<CommonTraveller, String> {
	/**
	 * 保存常用旅客实体
	 * @Title: ICommonTravellerService
	 * @Description:
	 * @param @param chaName  中文名
	 * @param @param engName  英文名
	 * @param @param phone  手机号
	 * @param @param idCard  身份证号
	 * @param @param studentCard  学生证号
	 * @param @param gender  性别
	 * @param @param birth  生日
	 * @param @param memberId  会员ID
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	 CommonTraveller persist(String chaName,String engName,String phone,String gender,String birth,String memberId, String versionFlag) throws ServiceException;
	
	/**
	 * 修改常用旅客实体
	 * @Title: ICommonTravellerService
	 * @Description:
	 * @param @param chaName  中文名
	 * @param @param engName  英文名
	 * @param @param phone  手机号
	 * @param @param idCard  身份证号
	 * @param @param studentCard  学生证号
	 * @param @param gender  性别
	 * @param @param birth  生日
	 * @param @param memberId  会员ID
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String chaName,String engName,String phone,String gender,String birth,String memberId, String versionFlag) throws ServiceException;
	
	/**
	 * 移除常用旅客实体
	 * @Title: ICommonTravellerService
	 * @Description: 
	 * @param @param id 常用旅客ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @param memberId
	 * 			登录的会员的id
	 * @return
	 * @throws ServiceException
	 */
	List<CommonTraveller> getByMemberId(String memberId) throws ServiceException;
	
	/**
	 * 根据常用旅客id和会员id查找常用旅客
	 * @param id
	 * @param memberId
	 * @return
	 */
	CommonTraveller findById(String id,String memberId);
	
	/**
	 * 根据会员ID和常用旅客姓名查找常用旅客
	 * @param memberId
	 * @param name
	 * @return
	 */
	CommonTraveller findByMemberIdAndName(String memberId,String name);
	
	/**
	 * 传入一组身份证，自动判断是否保存（如果存在就不保存）
	 * @param member
	 * @param name 姓名
	 * @param idCard 证件号
	 * @param cardType 证件类型（军官证、身份证...）
	 */
	void persistOrNot(Member member, String name, String idCard, String cardType);
}