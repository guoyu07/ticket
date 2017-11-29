package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.enumer.BjdjCheckWay;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportEmployee;
import com.ticket.pojo.BjdjDispatch;
import com.ticket.pojo.BjdjDispatchList;
import com.ticket.pojo.BjdjServiceItem;


/**
 * 分单流程表业务接口
 * @ClassName: IBjdjDispatchListService   
 * @Description: 提供分单流程表操作的增删改查   
 * @author HiSay  
 * @date  2015-11-23 22:55:31
 *
 */
public interface IBjdjDispatchListService extends IBaseService<BjdjDispatchList, String> {
	
	/**
	 * @Description：生成一个分单流程表实体
	 * @param dispatch_id
	 * @param employee_id
	 * @param sequence
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatchList persist(String dispatch_id,String employee_id,BjdjServiceItem info,Integer sequence) throws ServiceException;
	
	/**
	 * @Description：生成一个分单流程表实体
	 * @param dispatch
	 * @param employee
	 * @param sequence
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatchList persist(BjdjDispatch dispatch,AirportEmployee employee,BjdjServiceItem info,Integer sequence) throws ServiceException;
	
	/**
	 * @Description：接单
	 * @param dispatchList_id
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatchList acceptDispatchList(String dispatchList_id) throws ServiceException;
	
	/**
	 * @Description：接单
	 * @param dispatchList
	 * @return
	 * @throws ServiceException
	 */
	BjdjDispatchList accept(BjdjDispatchList dispatchList) throws ServiceException;
	
	/**
	 * @Description：核销一个分单
	 * @param id
	 * @param feedback
	 * @param way
	 * @return
	 * @throws ServiceException
	 */
	boolean verification(String id,String feedback,BjdjCheckWay way) throws ServiceException;
	
	/**
	 * @Description：核销一个分单
	 * @param dispatch
	 * @param feedback
	 * @param way
	 * @return
	 * @throws ServiceException
	 */
	boolean verification(BjdjDispatchList dispatchList,String feedback,BjdjCheckWay way) throws ServiceException;
	
	/**
	 * @Description：系统自动核销指定航班的分单
	 * @param flightNumber 航班号
	 * @param flightDate 航班日期
	 */
	void systemVerification(String flightNumber, String flightDate) throws ServiceException;
	
	/**
	 * @Description：移除分单流程表实体
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：接受工单的数量
	 * @param employee
	 * @return
	 */
	Long acceptAmount(AirportEmployee employee);
	
	/**
	 * @Description：没有接受的工单数量
	 * @param employee
	 * @return
	 */
	Long noAcceptAmount(AirportEmployee employee);
	
	/**
	 * @Description：完成的工单
	 * @param employee
	 * @return
	 */
	Long endedAcceptAmount(AirportEmployee employee);
	
	/**
	 * @Description：接受的工单
	 * @param employee
	 * @return
	 */
	List<BjdjDispatchList> acceptDispatchList(AirportEmployee employee);
	
	/**
	 * @Description：没有接受的工单
	 * @param employee
	 * @return
	 */
	List<BjdjDispatchList> noAcceptDispatchList(AirportEmployee employee);
	
	/**
	 * @Description：已经完结的工单
	 * @param employee
	 * @return
	 */
	List<BjdjDispatchList> endedDispatchList(AirportEmployee employee);
	
	/**
	 * @Description：得到一个分单子项的前一子项
	 * @param dispatchList
	 * @return
	 */
	BjdjDispatchList previous(BjdjDispatchList dispatchList);

	/**
	 * @Description：是否可以核销
	 * @return
	 */
	boolean canVerificate(BjdjDispatchList dispatchList);
	
	/**
	 * 根据时间段查找核销的工单
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ServiceException
	 */
	List<BjdjDispatchList> getByTimes(Date startTime,Date endTime) throws ServiceException;
	
	/**
	 * 服务人员工作汇总统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Object[]> serviceGather(Date startDate, Date endDate);
	
	/**
	 * 服务人员工作明细
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Object[]> serviceDetail(Date startDate, Date endDate);
}