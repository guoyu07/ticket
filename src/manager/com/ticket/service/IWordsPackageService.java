package com.ticket.service;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.WordsPackage;


/**
 * 关键词包业务接口
 * @ClassName: IWordsPackageService   
 * @Description: 提供关键词包操作的增删改查   
 * @author HiSay  
 * @date  2016-09-28 15:44:19
 *
 */
public interface IWordsPackageService extends IBaseService<WordsPackage, String> {
	/**
	 * 保存关键词包实体
	 * @Title: IWordsPackageService
	 * @Description:
	 * @param @param name  包名
	 * @param @param keywords  包名
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String keywords, String versionFlag) throws ServiceException;
	
	/**
	 * 修改关键词包实体
	 * @Title: IWordsPackageService
	 * @Description:
	 * @param @param name  包名
	 * @param @param keywords  包名
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String name,String keywords, String versionFlag) throws ServiceException;
	
	/**
	 * 移除关键词包实体
	 * @Title: IWordsPackageService
	 * @Description: 
	 * @param @param id 关键词包ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 获取屏蔽词
	 * @author：涂有
	 * @date 2017年1月19日 下午4:15:31
	 * @return
	 */
	WordsPackage get();
}