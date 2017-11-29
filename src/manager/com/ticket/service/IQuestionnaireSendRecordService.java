package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireSendRecord;


/**
 * 问卷发放记录表业务接口
 * @ClassName: IQuestionnaireSendRecordService   
 * @Description: 提供问卷发放记录表操作的增删改查   
 * @author HiSay  
 * @date  2016-05-04 16:18:18
 *
 */
public interface IQuestionnaireSendRecordService extends IBaseService<QuestionnaireSendRecord, String> {
	/**
	 * 保存问卷发放记录表实体
	 * @Title: IQuestionnaireSendRecordService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param employee_id  操作员id
	 * @param @param object_id  发送对象id
	 * @param @param isWrite  是否填写
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String questionnaireId,String employee_id,String object_id,Integer isWrite, String versionFlag) throws ServiceException;
	
	/**
	 * 修改问卷发放记录表实体
	 * @Title: IQuestionnaireSendRecordService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param employee_id  操作员id
	 * @param @param object_id  发送对象id
	 * @param @param isWrite  是否填写
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String questionnaireId,String employee_id,String object_id,Integer isWrite, String versionFlag) throws ServiceException;
	
	/**
	 * 移除问卷发放记录表实体
	 * @Title: IQuestionnaireSendRecordService
	 * @Description: 
	 * @param @param id 问卷发放记录表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 查看员工或者渠道客户是否填写过该调查问卷
	 * @param id  对象id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	QuestionnaireSendRecord queryByObjectId(String id, String versionFlag) throws ServiceException;
}