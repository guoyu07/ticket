package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SystemDictionary;

/**
 * APP端业务接口
 * @ClassName: INewsService   
 * @Description: 提供新闻信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-13 17:14:59
 *
 */
public interface IAppService extends IBaseService<Object, String> {
	
	/**
	 * 获取左侧菜单
	 * @return
	 * @throws Exception
	 */
	String queryLeftMenu() throws Exception;
	/**
	 * 根据新闻类别获取新闻列表
	 * @param newsClassId
	 * @return
	 * @throws Exception
	 */
	String queryNewsByNewsClassId(String newsClass_id) throws Exception;
	/**
	 * 根据id查询新闻实体
	 * @param news_id
	 * @return
	 * @throws ServiceException
	 */
	String QueryNewsById(String news_id) throws ServiceException;
	/**
	 * 根据别名查询新闻类别
	 * @param classAlias
	 * @return
	 */
	String queryClassByAlias(String classAlias) throws ServiceException;
	/**
	 * 获取温馨提示的内容
	 * @return
	 * @throws ServiceException
	 */
	String queryKindRemindContent() throws ServiceException;
	
	/**
	 * 获取右侧菜单链接
	 * @param member_id 会员id
	 * @return
	 * @throws ServiceException
	 */
	String getRightMenuUrl(String member_id) throws ServiceException;
	
	/**
	 * 根据id删除会员设置的快捷菜单
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteQuickMenu(String id) throws ServiceException;
	
	/**
	 * 网上值机相关须知
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> queryCheckOnLineNotice() throws ServiceException;
	/**
	 * 会员关注航班接口
	 * @param member_id    会员id
	 * @param flightNumber 航班号
	 * @param flightDate   航班日期
	 * @param flightState  航班状态
	 * @param memberRole   会员角色
	 * @return
	 * @throws ServiceException
	 */
	String focusFlight(String member_id, String flightNumber, String flightDate,
			String flightState, String memberRole,String setoutCity,String reachCity) throws ServiceException;
	
	/**
	 * 保存位置按钮
	 * @param name  位置名称
	 * @param positionAlias  位置别名
	 * @param longitude   经度
	 * @param latitude	纬度
	 * @param versionFlag  版本标识
	 * @return
	 * @throws ServiceException
	 */
	String savePosition(String name, String positionAlias, String longitude,
			String latitude, String versionFlag) throws ServiceException;
	
	/**
	 * 根据id删除设施位置
	 * @param id 位置id
	 * @return
	 * @throws ServiceException
	 */
	String deletePositionById(String id) throws ServiceException;
	
	/**
	 * 根据值机信息保存关注航班实体
	 * @param memberId  会员id
	 * @param flightNumber 航班编号
	 * @param flightDate  航班日期
	 * @param mobile	电话
	 * @param seatNo	座位号
	 * @param ticketNo	电子客票号
	 * @param couponId	值机标识
	 * @return
	 * @throws ServiceException
	 */
	String focusFlightByCheckin(String memberId, String flightNumber,
			String flightDate, String mobile, String seatNo, String ticketNo,
			String couponId) throws ServiceException;
	
	/**
	 * 根据电话号码和内容发送短信
	 * @param mobile 电话号码
	 * @param smsContent 短信内容
	 * @return
	 * @throws ServiceException
	 */
	String sendSms(String mobile, String smsContent) throws ServiceException;
	
	/**
	 * 查询所有的快捷菜单
	 * @return
	 * @throws ServiceException
	 */
	String queryAllQuickMenu() throws ServiceException;
	
	/**
	 * 保存用户反馈的信息
	 * @param name 用户名或名字
	 * @param mobile 电话
	 * @param url 来源
	 * @param content 反馈内容
	 * @return
	 * @throws ServiceException
	 */
	String saveSysFreebackInfo(String name, String mobile, String url,
			String content) throws ServiceException;
	
}