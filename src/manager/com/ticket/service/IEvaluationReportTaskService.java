package com.ticket.service;

import java.io.OutputStream;
import java.util.Date;

import com.ticket.enumer.EvaluationReportProperty;
import com.ticket.enumer.EvaluationReportType;
import com.ticket.enumer.EvaluationReportUpdateFrequency;
import com.ticket.exception.ServiceException;
import com.ticket.pojo.EvaluationReportTask;


/**
 * 评论报表任务业务接口
 * @ClassName: IEvaluationReportTaskService   
 * @Description: 提供评论报表任务操作的增删改查   
 * @author HiSay  
 * @date  2016-02-04 21:40:28
 *
 */
public interface IEvaluationReportTaskService extends IBaseService<EvaluationReportTask, String> {
	/**
	 * 保存评论报表任务实体
	 * @Title: IEvaluationReportTaskService
	 * @Description:
	 * @param @param name  报表文件名
	 * @param @param property  报表性质
	 * @param @param type  报表类型
	 * @param @param frequency  更新频率
	 * @param @param email  邮箱
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,EvaluationReportProperty property,EvaluationReportType type
			,EvaluationReportUpdateFrequency frequency,String email, Date[] startTime, Date[] endTime, boolean launch)
					throws ServiceException;
	
	/**
	 * 修改评论报表任务实体
	 * @Title: IEvaluationReportTaskService
	 * @Description:
	 * @param @param name  报表文件名
	 * @param @param property  报表性质
	 * @param @param type  报表类型
	 * @param @param frequency  更新频率
	 * @param @param email  邮箱
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,EvaluationReportProperty property,EvaluationReportType type
			,EvaluationReportUpdateFrequency frequency,String email, Date[] startTime, Date[] endTime, boolean launch)
					throws ServiceException;
	
	/**
	 * 移除评论报表任务实体
	 * @Title: IEvaluationReportTaskService
	 * @Description: 
	 * @param @param id 评论报表任务ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 生成一个报表
	 * @param id 报表任务id
	 * @return 生成的excel文件
	 */
	void generate(EvaluationReportTask task, OutputStream out) throws ServiceException;
}