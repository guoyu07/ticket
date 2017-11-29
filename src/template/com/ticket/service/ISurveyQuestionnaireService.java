package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.SurveyQuestionnaire;


/**
 * 调查问卷业务接口
 * @ClassName: ISurveyQuestionnaireService   
 * @Description: 提供调查问卷操作的增删改查   
 * @author HiSay  
 * @date  2015-11-11 17:10:59
 *
 */
public interface ISurveyQuestionnaireService extends IBaseService<SurveyQuestionnaire, String> {
	/**
	 * 保存调查问卷实体
	 * @Title: ISurveyQuestionnaireService
	 * @Description:
	 * @param @param survryNo  问卷编号
	 * @param @param title  问卷标题
	 * @param @param descript  问卷描述
	 * @param @param type  问卷模块类型
	 * @param @param survryModularId  调查模块ID
	 * @param @param entityName  实体表名
	 * @param @param entityId  实体ID
	 * @param @param currentOpenUp  当前开放问卷
	 * @param @param deadLine  截止日期
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String survryNo,String title,String descript,Integer type,String survryModularId,String entityName,String entityId,Integer currentOpenUp,Date deadLine, String versionFlag) throws ServiceException;
	
	/**
	 * 修改调查问卷实体
	 * @Title: ISurveyQuestionnaireService
	 * @Description:
	 * @param @param survryNo  问卷编号
	 * @param @param title  问卷标题
	 * @param @param descript  问卷描述
	 * @param @param type  问卷模块类型
	 * @param @param survryModularId  调查模块ID
	 * @param @param entityName  实体表名
	 * @param @param entityId  实体ID
	 * @param @param currentOpenUp  当前开放问卷
	 * @param @param deadLine  截止日期
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id,String survryNo,String title,String descript,Integer type,String survryModularId,String entityName,String entityId,Integer currentOpenUp,Date deadLine, String versionFlag) throws ServiceException;
	
	/**
	 * 移除调查问卷实体
	 * @Title: ISurveyQuestionnaireService
	 * @Description: 
	 * @param @param id 调查问卷ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询问卷列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SurveyQuestionnaire> queryList(String versionFlag) throws ServiceException;

	/**
	 * 验证问卷标题是否已存在
	 * @param title 问卷标题
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	SurveyQuestionnaire validateTitleExist(String title, String versionFlag) throws ServiceException;

	/**
	 * 验证问卷是否过期 
	 * @param id  问卷id
	 * @param versionFlag 
	 * @return
	 * @throws ServiceException
	 */
	boolean validateDeadlineExpire(String id, String versionFlag) throws ServiceException;
}