package com.ticket.service;

import com.ticket.exception.ServiceException;

/**
 * 短信接口Service
 * 
 * @ClassName: ISmsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-11-4 上午09:17:26
 * 
 */
public interface ISmsService extends IBaseService<Object, String> {
	/**
	 * 发送短信的共用方法
	 * @Title: sendSmsCommon 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean sendSmsCommon(String mobile, String sendType) throws ServiceException;
}
