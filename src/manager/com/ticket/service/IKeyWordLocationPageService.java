package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.KeyWordLocation;
import com.ticket.pojo.KeyWordLocationPage;
import com.ticket.pojo.Page;


/**
 * 关键词定位关联页面业务接口
 * @ClassName: IKeyWordLocationPageService   
 * @Description: 提供关键词定位关联页面操作的增删改查   
 * @author HiSay  
 * @date  2016-09-30 15:38:12
 *
 */
public interface IKeyWordLocationPageService extends IBaseService<KeyWordLocationPage, String> {
	/**
	 * 保存关键词定位关联页面实体
	 * @Title: IKeyWordLocationPageService
	 * @Description:
	 * @param @param keyword  关键词
	 * @param @param page  关联页面
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(KeyWordLocation keyword,Page page, int orderValue) throws ServiceException;
	
	/**
	 * 移除关键词定位关联页面实体
	 * @Title: IKeyWordLocationPageService
	 * @Description: 
	 * @param @param id 关键词定位关联页面ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 查询某批关键词下关联的页面
	 * @param keywordLocation_id
	 * @return
	 */
	List<KeyWordLocationPage> query(String keywordLocation_id);
}