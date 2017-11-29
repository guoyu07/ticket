package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.enumer.BjdjCheckWay;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjValidation;


/**
 * 便捷登机分单表业务接口
 * @ClassName: IBjdjDispatchService   
 * @Description: 提供便捷登机分单表操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 22:53:55
 *
 */
public interface IBjdjDispatchService extends IBaseService<BjdjDispatch, String> {
	
	/**
	 * 保存便捷登机分单表实体
	 * @param validation
	 * @param items
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatch persist(BjdjValidation validation, String[] items_id, String[] employee_id) throws ServiceException;

	/**
	 * 保存便捷登机分单表实体
	 * @param validation_id
	 * @param items
	 * @param employee_id
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatch persist(String validation_id, String[] items_id, String[] employee_id) throws ServiceException;
	
	/**
	 * 保存便捷登机分单表实体
	 * @Title: IBjdjDispatchService
	 * @Description:
	 * @param time  派单时间
	 * @param serviceCode_id  服务码ID
	 * @param member_id  用户ID
	 * @param flightNo  航班号
	 * @param boardingGate  登机口
	 * @param ended  是否结束
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
//	BjdjDispatch persistForElectromoible(BjdjValidation validation, String...employee_id) throws ServiceException;
	
	/**
	 * 保存便捷登机分单表实体
	 * @Title: IBjdjDispatchService
	 * @Description:
	 * @param time  派单时间
	 * @param serviceCode_id  服务码ID
	 * @param member_id  用户ID
	 * @param flightNo  航班号
	 * @param boardingGate  登机口
	 * @param ended  是否结束
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
//	BjdjDispatch persistForElectromoible(String validation_id, String...employee_id) throws ServiceException;
	
	/**
	 * 修改便捷登机分单表实体
	 * @Title: IBjdjDispatchService
	 * @Description:
	 * @param time  派单时间
	 * @param serviceCode_id  服务码ID
	 * @param member_id  用户ID
	 * @param flightNo  航班号
	 * @param boardingGate  登机口
	 * @param ended  是否结束
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	BjdjDispatch merge(String id, String validation_id, Date time,String boardingGate,boolean ended) throws ServiceException;
	
	/**
	 * 修改便捷登机分单表实体
	 * @Title: IBjdjDispatchService
	 * @Description:
	 * @param time  派单时间
	 * @param serviceCode_id  服务码ID
	 * @param member_id  用户ID
	 * @param flightNo  航班号
	 * @param boardingGate  登机口
	 * @param ended  是否结束
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	BjdjDispatch merge(String id, BjdjValidation validation, Date time,String boardingGate,boolean ended) throws ServiceException;
	
	/**
	 * 移除便捷登机分单表实体
	 * @Title: IBjdjDispatchService
	 * @Description: 
	 * @param id 便捷登机分单表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：得到员工的总接单数量
	 * @param id
	 * @return
	 */
	int receiveTotalAmount(String id);
	
	/**
	 * @Description：得到员工今天的接单数量
	 * @param id
	 * @return
	 */
	int receiveAmountToday(String id);
	
	/**
	 * @Description：检查某个验证是否已经分单
	 * @param validation_id
	 */
	boolean isDispatch(String validation_id);
	
	/**
	 * @Description：如果分单子项全部核销，和把分单大项设置为“结束”状态
	 * @param dispatch
	 * @return
	 */
	boolean verificateIfAllEnded(BjdjDispatch dispatch);
	
	/**
	 * 根据验证id查找到分单
	 * @param validationId
	 * @return
	 */
	BjdjDispatch findByValidationId(String validationId);
	
	/**
	 * 根据时间段查找核销数
	 * @param start
	 * @param end
	 * @return
	 */
	List<BjdjDispatch> getByTimes(Date start ,Date end);
	
	/**
	 * 根据时间段查找核销数(进厅)
	 * @param start
	 * @param end
	 * @return
	 */
	List<BjdjDispatch> getEnterHallByTimes(Date start ,Date end);
	
	/**
	 * 获取一个分单所有未接单的分单子项
	 * @param dispatch
	 * @return
	 */
	List<BjdjDispatchList> waitList(BjdjDispatch dispatch);
	
	/**
	 * 获取一个分单所有进行中分单子项
	 * @param dispatch
	 * @return
	 */
	List<BjdjDispatchList> ingList(BjdjDispatch dispatch);
	
	/**
	 * @Description：接单
	 * @param dispatch_id
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjDispatchList> accept(String dispatch_id) throws ServiceException;
	
	/**
	 * @Description：接单
	 * @param dispatch
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjDispatchList> accept(BjdjDispatch dispatch) throws ServiceException;
	
	/**
	 * @Description：核销一个分单
	 * @param id
	 * @param feedback
	 * @param way
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjDispatchList> verification(String id,String feedback,BjdjCheckWay way) throws ServiceException;
	
	/**
	 * @Description：核销一个分单
	 * @param dispatch
	 * @param feedback
	 * @param way
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjDispatchList> verification(BjdjDispatch dispatch,String feedback,BjdjCheckWay way) throws ServiceException;
	
	/**
	 * 获得分单角色名称
	 * @return
	 */
	String getDispatchRoleName();
	
	/**
	 * 设置分单角色名称
	 */
	void setDispathcRoleName(String name);
}