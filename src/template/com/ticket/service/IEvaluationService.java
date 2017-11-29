package com.ticket.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Evaluation;
import com.ticket.pojo.EvaluationDepartmentRalation;
import com.ticket.pojo.EvaluationProcess;
import com.ticket.pojo.Member;

/**
 * 评价系统service
 * @author tuyou
 */
@Transactional(rollbackFor=Exception.class)
public interface IEvaluationService extends IBaseService<Evaluation, String>{

	/**
	 * 增加一条评价
	 * @param class_id 大类id
	 * @param project_id 项目id
	 * @param items_id 指标id数组
	 * @param stars 指标打分数组
	 * @param content 评论内容
	 * @param images 评论附加图片
	 * @return
	 * @throws ServiceException
	 */
	Evaluation add(
			String class_id, String project_id, 
			String[] items_id, Integer[] stars, String content, String images
			) throws ServiceException;
	
	/**
	 * 查询所有的(评价管理员)
	 * @return
	 */
	List<Evaluation> queryAllForEvaluationAdmin();
	
	/**
	 * 查询所有的(发布管理员)
	 * @return
	 */
	List<Evaluation> queryAllForPublishAdmin();
	
	/**
	 * 查询所有的(部门管理员)
	 * @return
	 */
	List<Evaluation> queryAllForDepartmentAdmin();
	
	/**
	 * 查询已回复的
	 * @return
	 */
	List<Evaluation> queryFeedback();
	
	/**
	 * 查询已送审的
	 * @return
	 */
	List<Evaluation> querySended();
	
	/**
	 * 查询有图片的
	 * @return
	 */
	List<Evaluation> queryHasImages();
	
	/**
	 * 查询有描述的
	 * @return
	 */
	List<Evaluation> queryHasContent();
	
	/**
	 * 查询已删除
	 * @return
	 */
	List<Evaluation> queryDeleted();
	
	/**
	 * 查询需审核
	 * @return
	 */
	List<Evaluation> queryNeedVerify();
	
	/**
	 * 查询需跟进
	 * @return
	 */
	List<Evaluation> queryNeedFollowUp();
	
	/**
	 * 查询一个部门回复了的
	 * @return
	 */
	List<Evaluation> queryFeedback(String department_id);
	
	/**
	 * 查询已发布的
	 * @return
	 */
	List<Evaluation> queryPublished();
	
	/**
	 * 一批评论是否可以执行送审操作
	 * @param ids 需要送审的评论数据id， 用逗号隔开
	 * @return 
	 */
	boolean canSend(String ids);
	
	/**
	 * 送审操作
	 * @param ids 需要送审的评论数据id， 用逗号隔开
	 * @return 影响数据条数
	 */
	int send(String ids) throws ServiceException;
	
	/**
	 * 是否可以执行回复操作
	 * @param ids
	 * @return
	 */
	boolean canFeedback(String ids);
	
	/**
	 * 回复操作
	 * @return 影响数据条数
	 */
	int feedback(String ids, String content) throws ServiceException;
	
	/**
	 * 是否可以执行屏蔽操作
	 * @return 影响数据条数
	 */
	boolean canShield(String ids) throws ServiceException;
	
	/**
	 * 屏蔽操作
	 * @return 影响数据条数
	 */
	int shield(String ids) throws ServiceException;
	
	/**
	 * 是否可以发送给部门的操作（高级管理员）
	 * @return 影响数据条数
	 */
	boolean canSendToDepartment(String ids);
	
	/**
	 * 发送给部门的操作（高级管理员）
	 * @param ids
	 * @param department_id 部门id
	 * @param involveSystem 涉及制度
	 * @param msg 处理意见
	 * @param remark 备注
	 * @return
	 * @throws ServiceException
	 */
	int sendToDepartment(String ids, String department_id, String involveSystem, String shipmentQualityOpinion, String msg, String remark) throws ServiceException;
	
	/**
	 * 是否可以执行发回操作(也就是部门处理合格)
	 * @return
	 */
	boolean canSendBack(String ids);
	
	/**
	 * 发回操作（高级管理员）
	 * @return 影响数据条数
	 */
	int sendBack(String ids) throws ServiceException;
	
	/**
	 * 是否可以发布
	 * @param ids
	 * @return
	 */
	boolean canPublish(String ids);
	
	/**
	 * 发布显示操作（发布管理员）
	 * @return 影响数据条数
	 */
	int publish(String ids) throws ServiceException;
	
	/**
	 * 是否可以取消发布
	 * @param ids
	 * @return
	 */
	boolean canUnpublish(String ids);
	
	/**
	 * 取消发布显示操作（发布管理员）
	 * @return 影响数据条数
	 */
	int unpublish(String ids) throws ServiceException;
	
	/**
	 * 得到一条评论的所有处理信息
	 * @param id 评论id
	 * @return
	 */
	List<EvaluationProcess> getAllProcess(String id);
	
	/**
	 * 得到一条评论的最新处理信息
	 * @param id 评论id
	 * @return
	 */
	EvaluationProcess getNewestProcess(String id);
	
	/**
	 * 是否已经删除
	 */
	boolean isDelete(String ids);
	
	/**
	 * 统计评论数量
	 * @return
	 */
	long count();
	
	/**
	 * 一批评论是否可以处理
	 * @param ids
	 * @return
	 */
	boolean canDepartmentProcess(String ids);
	
	/**
	 * 部门处理评论信息
	 * @param ids
	 * @param close 是否关闭
	 * @param shipmentQualityOpinion 运质意见
	 * @param msg 处理意见
	 * @param remark 备注
	 * @return
	 * @throws ServiceException
	 */
	int departmentProcess(String ids, boolean close, String shipmentQualityOpinion, String msg, String remark) throws ServiceException;
	
	/**
	 * 是否可以回复部门了
	 * @param ids 处理信息（EvaluationProcess）
	 * @return
	 */
	boolean canFeedbackDepartment(String ids);
	
	/**
	 * 高级管理员回复相关部门
	 * @param ids
	 * @param content 回复内容
	 * @param qualified 是否合格
	 * @return
	 */
	int feedbackDepartment(String ids) throws ServiceException;
	
	/**
	 * 把处理消息置顶
	 * @param ids
	 * @return
	 */
	int topOrNot(String ids) throws ServiceException;
	
	/**
	 * 是否屏蔽了用户
	 * @param member
	 * @return
	 */
	boolean isSheild(Member member);
	
	/**
	 * 得到指定用户的最新评论
	 * @param member
	 * @return
	 */
	Evaluation getNewestEvaluation(Member member);
	
	/**
	 * 得到指定用户的评论统计数量
	 * @param member
	 * @return
	 */
	long myEvaluationCount();
	
	/**
	 * 得到指定用户的评论统计数量
	 * @param member
	 * @return
	 */
	long myEvaluationCount(Member member);
	
	/**
	 * 得到一条评论的所有处理信息
	 * @param evaluation
	 * @return
	 */
	List<EvaluationProcess> getProcess(Evaluation evaluation);
	
	/**
	 * 得到评论的需要处理部门
	 * @param evaluation
	 * @return
	 */
	List<EvaluationDepartmentRalation> getDepartments(Evaluation evaluation);
	
	/**
	 * 查询某个时间段的评价数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Evaluation> query(Date startTime, Date endTime);
	
	/**
	 * 查询某个时间段的评价数据
	 * @param member
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Evaluation> query(Member member, Date startTime, Date endTime);
	
	/**
	 * 查询会员的评价回复数
	 * @return
	 */
	Integer queryByMember();
}
