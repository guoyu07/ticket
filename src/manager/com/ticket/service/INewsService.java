package com.ticket.service;

import java.util.Date;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.News;
import com.ticket.pojo.Pagination;


/**
 * 新闻信息业务接口
 * @ClassName: INewsService   
 * @Description: 提供新闻信息操作的增删改查   
 * @author HiSay  
 * @date  2015-10-13 17:14:59
 *
 */
public interface INewsService extends IBaseService<News, String> {
	
	/**
	 * 初始化
	 */
	void init();
	
	/**
	 * 更新seo内存
	 * @param news
	 */
	void updateSeo(News news);
	
	/**
	 * 保存新闻信息实体
	 * @Title: INewsService
	 * @Description:
	 * @param @param newsClass_id  新闻类别id
	 * @param @param author  信息编辑
	 * @param @param title  信息标题
	 * @param @param subTitle  信息副标题
	 * @param @param content  新闻内容
	 * @param @param systemUser_id  管理员id
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	News persist(String newsClass_id,String author,String title,String subTitle,String introduce,String content,String pcContent,
			String systemUser_id,String thumbnail,List<String> image,String[] locationName,Double[] longitude,
			Double[] latitude,String versionFlag, String viewPageRedirectTemplate_id, 
			String useNewsClassName, String mobile,Integer orderValue, Date lastUpdateTime, 
			String[] mobiles,String[] url,String[] floorNumber,  String video, String seoKeyword, 
			String seoDescript, String seoUrl,String pcthumbnail,String infoPositionId,String scenicSpots_id) throws ServiceException;
	
	/**
	 * 修改新闻信息实体
	 * @Title: INewsService
	 * @Description:
	 * @param @param newsClass_id  新闻类别id
	 * @param @param author  信息编辑
	 * @param @param title  信息标题
	 * @param @param subTitle  信息副标题
	 * @param @param content  新闻内容
	 * @param @param systemUser_id  管理员id
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String newsClass_id,String author,String title,String subTitle,String introduce,String content,String pcContent,
			String systemUser_id,String thumbnail,List<String> image,String[] locationId,String[] locationName,Double[] longitude,
			Double[] latitude, String versionFlag, String viewPageRedirectTemplate_id, 
			String useNewsClassName, String mobile, Integer orderValue, Date lastUpdateTime, 
			String[] mobiles,String[] url,String[] floorNumber, String video, String seoKeyword,
			String seoDescript, String seoUrl,String pcthumbnail,String infoPositionId,String scenicSpots_id) throws ServiceException;
	
	/**
	 * 移除新闻信息实体
	 * @Title: INewsService
	 * @Description: 
	 * @param @param id 新闻信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 *批量彻底删除新闻信息
	 * @param versionFlag
	 * @param idsValue
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue,String versionFlag) throws ServiceException;

	/**
	 * 判断新闻标题是否已存在
	 * @param title 新闻标题  
	 * @param versionFlag
	 * @return
	 */
	boolean validateTitleExists(String title, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词搜索新闻信息
	 * @param keyword 关键词
	 * @param adminCommonPageSize
	 * @param versionFlag
	 * @return
	 */
	Pagination queryPageModuleByKeyword(String keyword, int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 根据新闻类别搜索新闻信息
	 * @param newsClass_id
	 * @param adminCommonPageSize
	 * @param versionFlag
	 * @return
	 */
	Pagination queryPageModuleByNewsClassId(String newsClass_id, int adminCommonPageSize, String versionFlag) throws ServiceException;
	
	/**
	 * 根据URL获取信息
	 * @param visitUrl
	 * @return
	 * @throws ServiceException
	 */
	News queryByUrl(String visitUrl, boolean addHits) throws ServiceException;
	/**
	 * 前端获取新闻列表
	 * @param newsClass_id  新闻栏目ID
	 * @param startSize     起始位置
	 * @param pageCount     获取数量
	 * @return
	 * @throws ServiceException
	 */
	List<News> queryListByFront(String newsClass_id, Integer startSize, Integer pageCount) throws ServiceException;
	
	/**
	 * 根据新闻类别查询新闻信息（一篇）
	 * @param newsClass_id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	News querySingleByNewsClassId(String newsClass_id,String versionFlag) throws ServiceException;
	
	/**
	 * 根据当前文章获取上一篇
	 * @param news
	 * @return
	 * @throws ServiceException
	 */
	News queryPrevNews(News news) throws ServiceException;
	
	/**
	 * 根据当前文章获取下一篇
	 * @param news
	 * @return
	 * @throws ServiceException
	 */
	News queryNextNews(News news) throws ServiceException;

	/**
	 * 根据新闻类别查询新闻信息
	 * @param newsClass_id 新闻类别id
	 * @param size         页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<News> queryListByType(String newsClass_id, Integer size, String versionFlag) throws ServiceException;

	/**
	 * 根据新闻标题查询实体
	 * @param value
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	News queryByTitle(String title, String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取指定类别下面的指定数量的新闻
	 * @method queryByNewsClassId
	 * @param newsClass_id
	 * @param audit
	 * @param cmd
	 * @param size
	 * @return
	 * @throws ServiceException
	 * @return List<News>
	 * @date 2016-2-17 下午05:13:51
	 */
	List<News> queryByNewsClassId(String newsClass_id,Integer audit,Integer cmd,String order,Integer size) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据类别id,关键词获取分页模型
	 * @method queryByClassAndKeyword
	 * @param newsClass_id
	 * @param keyword
	 * @param audit
	 * @param order
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 * @return Pagination
	 * @date 2016-2-19 下午05:06:35
	 */
	Pagination queryByClassAndKeyword(String newsClass_id,String keyword,Integer audit,String order,Integer pageSize) throws ServiceException;

	/**
	 * 根据新闻类别权限id查询新闻列表
	 * @param permissionId  权限id
	 * @param pageSize		页面
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByNewsClassPermissionId(String permissionId,
			int adminCommonPageSize, String versionFlag) throws ServiceException;

	/**
	 * 根据新闻类别权限id查询新闻回收站列表
	 * @param permissionId  权限id
	 * @param pageSize		页面
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryRecycleByNewsClassPermissionId(String permissionId,
			int adminCommonPageSize, String versionFlag) throws ServiceException;
	
	/**
	 * 根据景点查询文章
	 * @param scenicSpots_id
	 * @return
	 * @throws ServiceException
	 */
	List<News> queryByscenicSpots(String scenicSpots_id) throws ServiceException;
}