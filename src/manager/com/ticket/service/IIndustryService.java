package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Industry;


/**
 * 企业行业业务接口
 * @ClassName: IIndustryService   
 * @Description: 提供企业行业操作的增删改查   
 * @author HiSay  
 * @date  2016-01-11 09:44:02
 *
 */
public interface IIndustryService extends IBaseService<Industry, String> {
	/**
	 * 保存企业行业实体
	 * @Title: IIndustryService
	 * @Description:
	 * @param @param name  行业名称
	 * @param @param introduce  行业描述
	 * @param @param path  访问地址
	 * @param @param deep  深度
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String introduce,String parnetId) throws ServiceException;
	
	/**
	 * 修改企业行业实体
	 * @Title: IIndustryService
	 * @Description:
	 * @param @param name  行业名称
	 * @param @param introduce  行业描述
	 * @param @param path  访问地址
	 * @param @param deep  深度
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String introduce,String parnetId) throws ServiceException;
	
	/**
	 * 移除企业行业实体
	 * @Title: IIndustryService
	 * @Description: 
	 * @param @param id 企业行业ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  改变一个类别的path
	 * @method changePath
	 * @param Industry
	 * @throws ServiceException
	 * @return void
	 * @date 2014-5-14 上午10:34:32
	 */
	void changePath(Industry industry) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 改变一个类别的深度
	 * @method changeDept
	 * @param Industry
	 * @throws ServiceException
	 * @return void
	 * @date 2014-5-14 上午10:35:07
	 */
	void changeDeep(Industry industry) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 解析类别下面的所有子类别id
	 * @method parseClassIds
	 * @param classId
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午10:00:27
	 */
	String parseClassIds(String classId) throws ServiceException;
	/**
	 * @author wangjiafeng
	 *  获取所有的顶级级类别
	 * @param size 
	 * @method queryClassByParentIsNull
	 * @return
	 * @return List<Industry>
	 * @date 2014-5-14 上午09:38:22
	 */
	List<Industry> queryClassByParentIsNull(Integer size) throws  ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取部门顶级目录所树
	 * @method createIndustryTree
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:44:31
	 */
	String createIndustryTree() throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据LisT写出目录树
	 * @method createIndustryTree
	 * @param list
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:43:52
	 */
	String createIndustryTree(List<Industry> list)
			throws ServiceException;

	/**
	 * @author wangjaifeng
	 * 根据栏目id获取目录树
	 * @method createIndustryOptionHtml
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:43:14
	 */
	String createIndustryOptionHtml(String id)
			throws ServiceException;

	/**
	 * @author wangjiafeng
	 * 根据list和指定的id获取指定的目录树
	 * @method createIndustryOptionHtml
	 * @param topList
	 * @param level
	 * @param selectId
	 * @return
	 * @throws ServiceException
	 * @return String
	 * @date 2014-5-14 上午09:42:31
	 */
	String createIndustryOptionHtml(List<Industry> topList,
			int level, String selectId) throws ServiceException;
}