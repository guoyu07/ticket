package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Infringement;


/**
 * 内部员工罚单业务接口
 * @ClassName: IInfringementService   
 * @Description: 提供内部员工罚单操作的增删改查   
 * @author HiSay  
 * @date  2016-03-04 19:58:20
 *
 */
public interface IInfringementService extends IBaseService<Infringement, String> {
	/**
	 * 保存内部员工罚单实体
	 * @Title: IInfringementService
	 * @Description:
	 * @param @param time  时间
	 * @param @param inspectName  检查人
	 * @param @param unitName  单位名称
	 * @param @param illegalMatter  违规事项
	 * @param @param rules  涉及规章制度
	 * @param @param rectificationOpinion  整改意见
	 * @param @param scenePhoto  现场照片
	 * @param @param numberId  罚单编号
	 * @param @param illegalName  违规人姓名
	 * @param @param illegalCard  违规人证件号
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String time,String inspectName,String unitName,String illegalMatter,String rules,String rectificationOpinion,String scenePhoto,String numberId,String illegalName,String illegalCard, String versionFlag) throws ServiceException;
	
	/**
	 * 修改内部员工罚单实体
	 * @Title: IInfringementService
	 * @Description:
	 * @param @param time  时间
	 * @param @param inspectName  检查人
	 * @param @param unitName  单位名称
	 * @param @param illegalMatter  违规事项
	 * @param @param rules  涉及规章制度
	 * @param @param rectificationOpinion  整改意见
	 * @param @param scenePhoto  现场照片
	 * @param @param numberId  罚单编号
	 * @param @param illegalName  违规人姓名
	 * @param @param illegalCard  违规人证件号
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String time,String inspectName,String unitName,String illegalMatter,String rules,String rectificationOpinion,String scenePhoto,String numberId,String illegalName,String illegalCard, String versionFlag) throws ServiceException;
	
	/**
	 * 移除内部员工罚单实体
	 * @Title: IInfringementService
	 * @Description: 
	 * @param @param id 内部员工罚单ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	* 返回罚单的数量
	*/
	Long infringementCount() throws ServiceException;
	/**
	 * 根据罚单id查找罚单
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Infringement findById(String id) throws ServiceException;
	/**
	 * 根据罚单时间，单位名称，被罚人员名称查找罚单
	 * @param time
	 * @param unitName
	 * @param illegalName
	 * @return
	 * @throws ServiceException
	 */
	List<Infringement> findByTimeAndUnitNameAndIllegalName(String time,String unitName,String illegalName) throws ServiceException;
}