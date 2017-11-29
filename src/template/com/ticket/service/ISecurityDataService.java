package com.ticket.service;

import java.util.List;

import com.ticket.pojo.SecurityData;

/**
 * 安检数据服务接口
 * @author zzf
 */
public interface ISecurityDataService extends IBaseService<SecurityData, String>{

	/**
	 * 通过电子客票号获取安检实体数据
	 * @param ticketNo
	 * @return
	 */
	SecurityData getSecurityByTicketNo(String ticketNo);
	
	/**
	 * 存储安检数据通过，实体对象
	 * @param securityData
	 * @return
	 */
	boolean saveSecurityByEntity(SecurityData securityData); 
	
	/**
	 * 得到对应身份证所有的值机信息
	 * @param IDCard
	 * @return
	 */
	List<SecurityData> findByIDCard(String IDCard);
	
	/**
	 * 通过身份证号和航班号获取一个值机数据
	 * @param idCard
	 * @param flightNo
	 * @return
	 */
	SecurityData get(String idCard, String flightNo);
}
