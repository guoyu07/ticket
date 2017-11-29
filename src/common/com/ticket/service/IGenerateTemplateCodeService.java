package com.ticket.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.TemplateCode;

/**
 * 模板代码生成工具业务接口层
 * @ClassName: IGenerateTemplateCodeService   
 * @Description: 可以根据模板文件生成对应的java代码,js代码,jsp代码等.   
 * @author HiSay  
 * @date Jul 31, 2013 3:37:30 PM   
 *
 */
public interface IGenerateTemplateCodeService extends IBaseService<TemplateCode, Long> {
	/**
	 * 生成java代码,js代码等.
	 * @Title: IGenerateTemplateCodeService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param entityName   实体对象名称
	 * @param @param entityTitle  实体名称
	 * @param @param request      HttpServletRequest
	 * @param @param response     HttpServletResponse
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean generateJavaCode(String entityName, String entityTitle, String entityDescript, String entityAttribute, 
			HttpServletRequest request, HttpServletResponse response, String savePath, String pojoIdType) throws ServiceException;
	
	/**
	 * 根据实体名称删除对应
	 * @Title: IGenerateTemplateCodeService
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param @param entityName              实体名称
	 * @param @param isDeleteAction          是否删除实体对应的Action文件
	 * @param @param isDeleteService         是否删除实体对应的Service接口层文件
	 * @param @param isDeleteServiceImpl     是否删除实体对应的ServiceImpl接口实现层文件
	 * @param @param isDeleteJs              是否删除实体对应的Js文件
	 * @param @param isDeleteJsp             是否删除实体对应的Jsp文件
	 * @param @param isDeleteMessage         是否删除实体对应的properties文件
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean delete(String entityName, Integer isDeleteAction, Integer isDeleteService, Integer isDeleteServiceImpl, Integer isDeleteJs, 
			Integer isDeleteJsp, Integer isDeleteMessage, Integer isDeletePojo, String deletePath) throws ServiceException;
}
