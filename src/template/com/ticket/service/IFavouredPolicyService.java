package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.ApplayClass;
import com.ticket.pojo.FavouredPolicy;


/**
 * 优惠政策业务接口
 * @ClassName: IFavouredPolicyService   
 * @Description: 提供优惠政策操作的增删改查   
 * @author HiSay  
 * @date  2015-11-14 14:34:06
 *
 */
public interface IFavouredPolicyService extends IBaseService<FavouredPolicy, String> {
	/**
	 * 保存优惠政策实体
	 * @Title: IFavouredPolicyService
	 * @Description:
	 * @param @param favouredPolicyNo  优惠编号
	 * @param @param name  名称
	 * @param @param rechargeAmount  所需充值金额
	 * @param @param discountRate  折扣率
	 * @param @param discountWay  优惠方式
	 * @param @param descript  描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String favouredPolicyNo,String name,Double rechargeAmount,Double discountRate,String discountWay,String descript,Integer grade, Double giftAmount,String versionFlag) throws ServiceException;
	/**
	 * 修改优惠政策实体
	 * @Title: IFavouredPolicyService
	 * @Description:
	 * @param @param favouredPolicyNo  优惠编号
	 * @param @param name  名称
	 * @param @param rechargeAmount  所需充值金额
	 * @param @param discountRate  折扣率
	 * @param @param discountWay  优惠方式
	 * @param @param descript  描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String favouredPolicyNo,String name,Double rechargeAmount,Double discountRate,String discountWay,String descript, Integer grade,Double giftAmount,String versionFlag) throws ServiceException;
	
	/**
	 * 移除优惠政策实体
	 * @Title: IFavouredPolicyService
	 * @Description: 
	 * @param @param id 优惠政策ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * 查询优惠政策列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<FavouredPolicy> queryList(String versionFlag) throws ServiceException;
	/**
	 * 根据渠道类型查找优惠政策
	 * @param customerId
	 * @return
	 * @throws ServiceException
	 */
	List<FavouredPolicy> queryByCustomerId(String customerId) throws ServiceException;
}