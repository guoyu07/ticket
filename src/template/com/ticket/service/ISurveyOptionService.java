package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SurveyOption;


/**
 * 问题选项业务接口
 * @ClassName: ISurveyOptionService   
 * @Description: 提供问题选项操作的增删改查   
 * @author HiSay  
 * @date  2015-11-12 14:58:46
 *
 */
public interface ISurveyOptionService extends IBaseService<SurveyOption, String> {
	/**
	 * 保存问题选项实体
	 * @Title: ISurveyOptionService
	 * @Description:
	 * @param @param optionNo  选项编号
	 * @param @param surveyQuestionId  所属问题
	 * @param @param title  选项标题
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String optionNo,String surveyQuestionId,String title, String versionFlag) throws ServiceException;
	
	/**
	 * 修改问题选项实体
	 * @Title: ISurveyOptionService
	 * @Description:
	 * @param @param optionNo  选项编号
	 * @param @param surveyQuestionId  所属问题
	 * @param @param title  选项标题
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String optionNo,String surveyQuestionId,String title, String versionFlag) throws ServiceException;
	
	/**
	 * 移除问题选项实体
	 * @Title: ISurveyOptionService
	 * @Description: 
	 * @param @param id 问题选项ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询问题选项列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryList(String versionFlag,String surveyQuestionId,int pageSize) throws ServiceException;

	/**
	 * 根据问题id查询选项列表
	 * @param  问题id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SurveyOption> queryByQuestionId(String question_id, String versionFlag) throws ServiceException;

	/**
	 * 验证选项编号是否已存在
	 * @param surveyQuestionId 问题id
	 * @param optionNo 选项编号
	 * @return
	 * @throws ServiceException
	 */
	SurveyOption validateNoExist(String surveyQuestionId, String optionNo) throws ServiceException;
	
	/**
	 * 验证选项标题是否已存在
	 * @param surveyQuestionId 问题id
	 * @param title 选项标题
	 * @return
	 * @throws ServiceException
	 */
	SurveyOption validateTitleExist(String surveyQuestionId, String title) throws ServiceException;
	
}