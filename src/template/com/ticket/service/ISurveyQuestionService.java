package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SurveyQuestion;



/**
 * 调查问题业务接口
 * @ClassName: ISurveyQuestionService   
 * @Description: 提供调查问题操作的增删改查   
 * @author HiSay  
 * @date  2015-11-12 14:53:43
 *
 */
public interface ISurveyQuestionService extends IBaseService<SurveyQuestion, String> {
	/**
	 * 保存调查问题实体
	 * @Title: ISurveyQuestionService
	 * @Description:
	 * @param @param questionNo  问题编号
	 * @param @param title  问题标题
	 * @param @param type  问题类型
	 * @param @param iseq  问题排序
	 * @param @param questionType  问题调查形式
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(Integer questionNo,String title,String surveyQuestionnaireId,Integer type,Integer iseq,Integer questionType, String versionFlag) throws ServiceException;
	
	/**
	 * 修改调查问题实体
	 * @Title: ISurveyQuestionService
	 * @Description:
	 * @param @param questionNo  问题编号
	 * @param @param title  问题标题
	 * @param @param type  问题类型
	 * @param @param iseq  问题排序
	 * @param @param questionType  问题调查形式
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, Integer questionNo,String title,String surveyQuestionnaireId,Integer type,Integer iseq,Integer questionType, String versionFlag) throws ServiceException;
	
	/**
	 * 移除调查问题实体
	 * @Title: ISurveyQuestionService
	 * @Description: 
	 * @param @param id 调查问题ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	
	/**
	 * 查询全部问卷问题列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SurveyQuestion> queryList(String versionFlag) throws ServiceException;
	
	

	/**
	 * 查询问卷问题列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryList(String versionFlag,String surveyQuestionnaireId,int pageSize) throws ServiceException;

	/**
	 * 根据问卷id获取问卷问题列表
	 * @param id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SurveyQuestion> queryByQuertionnaireId(String id, String versionFlag) throws ServiceException;

	/**
	 * 验证问卷的问题编号是否已存在
	 * @param surveyQuestionnaireId 问卷id
	 * @param questionNo 问题编号
	 * @return
	 * @throws ServiceException
	 */
	SurveyQuestion validateNoIsExist(String surveyQuestionnaireId, Integer questionNo) throws ServiceException;

	/**
	 * 验证问题是否已存在
	 * @param surveyQuestionnaireId 问卷id
	 * @param title 问题名称
	 * @return
	 * @throws ServiceException
	 */
	SurveyQuestion validateTitleIsExist(String surveyQuestionnaireId, String title) throws ServiceException;

}