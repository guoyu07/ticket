package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.LetterStation;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemUser;


/**
 * 站内信业务接口
 * @ClassName: ILetterStationService   
 * @Description: 提供站内信操作的增删改查   
 * @author HiSay  
 * @date  2016-01-03 13:52:54
 *
 */
public interface ILetterStationService extends IBaseService<LetterStation, String> {
	/**
	 * @author wangjiafeng
	 * 保存站内信
	 * @method persist
	 * @param operator_id 操作员id
	 * @param title 站内信标题
	 * @param content 内容
	 * @param url 链接地址
	 * @return
	 * @throws ServiceException
	 * @return boolean
	 * @date 2016-1-3 下午01:57:18
	 */
	boolean persist(String operator_id,String title,String content,String url) throws ServiceException;
	
	/**
	 * 移除站内信实体
	 * @Title: ILetterStationService
	 * @Description: 
	 * @param @param id 站内信ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的站内信列表
	 * @method queryAll
	 * @param systemUser
	 * @param read
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-1-3 下午01:59:05
	 */
	Pagination queryAll(SystemUser systemUser,Integer read,Integer pageSize) throws ServiceException;
	/**
	 * @author wangjaifneg
	 * 统计数量
	 * @method queryCount
	 * @param systemUser_id
	 * @param read
	 * @return
	 * @throws ServiceException
	 * @return Long
	 * @date 2016-1-3 下午02:12:02
	 */
	Long queryCount(String systemUser_id,Integer read) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取所有的站内信列表
	 * @method queryAllLsit
	 * @param systemUser_id
	 * @param read
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return List<LetterStation>
	 * @date 2016-1-3 下午02:13:33
	 */
	List<LetterStation> queryAllList(String systemUser_id,Integer read,Integer pageSize) throws ServiceException;
	
	/**
	 * 管理员工的站内信
	 * @param id
	 * @param versionFlag
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryByEmployeeOrCustomer(String id, String versionFlag,
			int pageSize) throws ServiceException;
	
	/**
	 * 编辑站内信信息
	 * @param id id
	 * @param systemEmployeeInfo 系统登录的用户
	 * @param title 标题
	 * @param content  内容
	 * @param url 链接
	 * @return
	 * @throws ServiceException
	 */
	boolean merge(String id,String title, String content, String url) throws ServiceException;
}