package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.Pagination;


/**
 * 新闻类别业务接口
 * @ClassName: INewsClassService   
 * @Description: 提供新闻类别操作的增删改查   
 * @author HiSay  
 * @date  2015-10-13 17:40:45
 *
 */
public interface INewsClassService extends IBaseService<NewsClass, String> {
	/**
	 * 保存新闻类别实体
	 * @Title: INewsClassService
	 * @Description:
	 * @param @param name  类别名称
	 * @param permissionId 权限id
	 * @param @param parent_id  上级类别
	 * @param @param descript  类别描述
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String permissionId,String parent_id,String descript, String versionFlag, 
			String alias, String englishName, String listPageRedirectTemplate_id,
			Integer orderValue,String picture,String pcListTemplate_id,String pcViewTemplate_id) throws ServiceException;
	
	/**
	 * 修改新闻类别实体
	 * @Title: INewsClassService
	 * @Description:
	 * @param @param name  类别名称
	 * @param permissionId 权限id
	 * @param @param parent_id  上级类别
	 * @param @param descript  类别描述
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String permissionId,String parent_id,String descript, String versionFlag, 
			String alias, String englishName, String listPageRedirectTemplate_id, 
			Integer orderValue,String picture,String pcListTemplate_id,String pcViewTemplate_id) throws ServiceException;
	
	/**
	 * 移除新闻类别实体
	 * @Title: INewsClassService
	 * @Description: 
	 * @param @param id 新闻类别ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据版本标识和父类别ID获取模块的HTML代码
	 * @Title: INewsClassService
	 * @Description:   
	 * @param @param parent_id    上级类别ID, 如果获取顶级类别, 则传入0
	 * @param @param versionFlag  版本表示
	 * @param @return 生成后的HTML代码
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	String queryNewsClassSelectOptionHtml(String parent_id, String versionFlag) throws ServiceException;
	
	/**
	 * 获取第一级的类别列表(也就是顶级类别列表)
	 * @Title: IModuleService
	 * @Description: 根据版本标识获取顶级类别列表   
	 * @param @param versionFlag 类别的版本标识
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<NewsClass> queryFirstNewsClassList(String versionFlag) throws ServiceException;
	
	/**
	 * 验证父模块是否含有子模块
	 * @Title: validateHaveChildModules 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param parent_id
	 * @param @return
	 * @param @throws ServiceException    设定文件 
	 * @return boolean    返回类型 
	 * @throws
	 */
	boolean validateHaveChildNewsClasss(String parent_id) throws ServiceException;
	/**
	 * 根据父模块ID获取该父模块下面的子模块列表
	 * @Title: IModuleService
	 * @Description:    
	 * @param @param parent_id  父模块ID
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	List<NewsClass> queryChildNewsClasssByParent(String parent_id) throws ServiceException;
	
	/**
	 * 设置模块是否为默认显示
	 * @Title: IModuleService
	 * @Description: 根据模块的ID设置模块是否为默认显示.   
	 * @param @param id
	 * @param @return
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	boolean setNewsClassIsDefaultShow(String entityName, String id) throws ServiceException;
	/**
	 * 验证别名是否已存在
	 * @param versionFlag
	 * @param alias
	 * @return
	 * @throws ServiceException
	 */
	boolean validateAliasExists(String versionFlag, String alias, String id) throws ServiceException;
	/**
	 * 根据别名获取实体
	 * @param versionFlag
	 * @param alias
	 * @return
	 * @throws ServiceException
	 */
	NewsClass queryByAlias(String versionFlag, String alias) throws ServiceException;

	/**
	 * 前端获取新闻类别列表
	 * @param newsClass_id  新闻栏目ID
	 * @param startSize     起始位置
	 * @param pageCount     获取数量
	 * @return
	 * @throws ServiceException
	 */
	List<NewsClass> queryListByFront(String id, Integer startSize, Integer pageCount);

	/**
	 * 查询第一级新闻类别列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<NewsClass> queryParentList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据新闻类别别名查询子类别列表
	 * @param newsClass_id  新闻类别id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<NewsClass> queryListByParentId(String newsClass_id, String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 解析父栏目下面的子ids
	 * @method parseClassIds
	 * @param classId
	 * @return
	 * @return String
	 * @date 2016-2-17 下午04:21:36
	 */
	String parseClassIds(String classId) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 获取顶部栏目
	 * @method getTopNewsClass
	 * @param newsClass
	 * @return
	 * @throws ServiceException
	 * @return NewsClass
	 * @date 2016-2-18 上午10:36:25
	 */
	NewsClass getTopNewsClass(NewsClass newsClass) throws ServiceException;

	/**
	 * 根据权限id查询类别实体
	 * @param permissionId
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	NewsClass queryByPermissionId(String permissionId, String versionFlag) throws ServiceException;

	/**
	 * 验证权限是否存在
	 * @param permissionId 权限id
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean validatePermissionExists(String permissionId, String versionFlag) throws ServiceException;

	/**
	 * 根据父id查询页面模型
	 * @param parent_id 父id
	 * @param pageSize	页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByParentId(String parent_id,
			int pageSize, String versionFlag) throws ServiceException;
}