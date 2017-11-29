package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.QuestionnaireAnswer;


/**
 * 问卷发放记录表业务接口
 * @ClassName: IQuestionnaireAnswerService   
 * @Description: 提供问卷发放记录表操作的增删改查   
 * @author HiSay  
 * @date  2016-05-04 16:31:27
 *
 */
public interface IQuestionnaireAnswerService extends IBaseService<QuestionnaireAnswer, String> {
	/**
	 * 保存问卷发放记录表实体
	 * @Title: IQuestionnaireAnswerService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param object_id  答题人id
	 * @param @param questionNo  问题编号
	 * @param @param qustionAnswer  问题答案
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String questionnaireId,String object_id,Integer questionNo,String qustionAnswer, String versionFlag) throws ServiceException;
	
	/**
	 * 修改问卷发放记录表实体
	 * @Title: IQuestionnaireAnswerService
	 * @Description:
	 * @param @param questionnaireId  问卷id
	 * @param @param object_id  答题人id
	 * @param @param questionNo  问题编号
	 * @param @param qustionAnswer  问题答案
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String questionnaireId,String object_id,Integer questionNo,String qustionAnswer, String versionFlag) throws ServiceException;
	
	/**
	 * 移除问卷发放记录表实体
	 * @Title: IQuestionnaireAnswerService
	 * @Description: 
	 * @param @param id 问卷发放记录表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据问卷id和问题编号统计答案数量
	 * @param questionnaireId 问卷id
	 * @param questionNo 问题编号
	 * @return
	 * @throws ServiceException
	 */
	Long countByQuestionNo(String questionnaireId,String questionNo) throws ServiceException;
	
	/**
	 * 根据问卷id 选项编号 选项编号统计单选题的选项选择数
	 * @param questionnaireId 问卷id
	 * @param questionNo 问题编号
	 * @param optionNo 选项编号
	 * @return
	 */
	Long countRadioAnswerByQustion(String questionnaireId,String questionNo,String optionNo);
	
	/**
	 * 根据问卷id 选项编号 选项编号统计多选题的选项选择数
	 * @param questionnaireId 问卷id
	 * @param questionNo 问题编号
	 * @param optionNo 选项编号
	 * @return
	 */
	Long countCheckboxAnswerByQustion(String questionnaireId,String questionNo,String optionNo);

	/**
	 * 根据问卷名称 和问题编号查找答案列表
	 * @param questionnaireId
	 * @param questionNo
	 * @return
	 * @throws ServiceException
	 */
	List<QuestionnaireAnswer> queryByQuestionNo(String questionnaireId, String questionNo) throws ServiceException;
	
}