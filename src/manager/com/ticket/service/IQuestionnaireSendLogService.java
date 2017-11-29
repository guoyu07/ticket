package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireSendLog;


/**
 * 问卷发放日志业务接口
 * @ClassName: IQuestionnaireSendLogService   
 * @Description: 提供问卷发放日志操作的增删改查   
 * @author HiSay  
 * @date  2016-05-05 16:21:51
 *
 */
public interface IQuestionnaireSendLogService extends IBaseService<QuestionnaireSendLog, String> {
	/**
	 * 保存问卷发放日志实体
	 * @Title: IQuestionnaireSendLogService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param employee_id  发放人
	 * @param @param sendCount  问卷发送数量
	 * @param @param writeCount  问卷完成数量
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String questionnaireId,String employee_id,Integer sendCount,Integer writeCount, String versionFlag) throws ServiceException;
	
	/**
	 * 修改问卷发放日志实体
	 * @Title: IQuestionnaireSendLogService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param employee_id  发放人
	 * @param @param sendCount  问卷发送数量
	 * @param @param writeCount  问卷完成数量
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String questionnaireId,String employee_id,Integer sendCount,Integer writeCount, String versionFlag) throws ServiceException;
	
	/**
	 * 移除问卷发放日志实体
	 * @Title: IQuestionnaireSendLogService
	 * @Description: 
	 * @param @param id 问卷发放日志ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据问卷id查询问卷发放日志
	 * @param id 问卷id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	QuestionnaireSendLog queryByQuestionnaireId(String id, String versionFlag) throws ServiceException;
}