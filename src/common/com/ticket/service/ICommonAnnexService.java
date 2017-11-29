package com.ticket.service;

import java.io.File;
import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonAnnex;


/**
 * 公共附件表业务接口
 * @ClassName: ICommonAnnexService   
 * @Description: 提供公共附件表操作的增删改查   
 * @author HiSay  
 * @date  2013-09-16 16:05:53
 *
 */
public interface ICommonAnnexService extends IBaseService<CommonAnnex, String> {
	/**
	 * 移除公共附件表实体
	 * @Title: ICommonAnnexService
	 * @Description: 
	 * @param @param id 公共附件表ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据实体的uuid和类型获取附件列表
	 * @Title: ICommonAnnexService
	 * @Description:    
	 * @param @param entityId    实体ID
	 * @param @param annexType   附件的类型(例如:JPG,GIF,BMP等) 
	 * @param @param size        获取的数量, 如果获取所有则传入null即可.
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	List<CommonAnnex> queryListByEntityId(String entityId, Integer annexType, Integer size) throws ServiceException;
	
	/**
	 * 根据实体的uuid和类型获取单个附件对象
	 * @Title: ICommonAnnexService
	 * @Description:   
	 * @param @param entityId	   实体ID
	 * @param @param annexType   附件的类型(例如:JPG,GIF,BMP等) 
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	CommonAnnex queryCommonAnnexByEntityId(String entityId, Integer annexType) throws ServiceException;
	
	/**
	 * 根据实体的uuid来删除附件信息
	 * @Title: ICommonAnnexService
	 * @Description: 当彻底删除一个实体的时候, 要对应的把该实体的附件信息删除掉.
	 * @param @param entityId 实体的uuid
	 * @param @return
	 * @param @throws ServiceException 
	 * @return 
	 * @throws
	 */
	boolean removeByEntityId(String entityId) throws ServiceException;

	/**
	 * 
	 * @Title: ICommonAnnexService
	 * @Description: 保存附件  
	 * @param @param files
	 * @param @param entityName
	 * @param @param entityId
	 * @param @param versionFlag
	 * @param @return 
	 * @return 
	 * @throws
	 */
	boolean persist(String files, String annexTitle, String annexContent, String annexOrderValue,
			String entityName, String entityId, String versionFlag) throws ServiceException;

	/**
	 * 
	 * @Title: ICommonAnnexService
	 * @Description: 解析单个附件的路径，多用于提取单张图片
	 * @param @param files
	 * @param @return 
	 * @return 
	 * @throws
	 */
	String getFirstFiles(String files) throws ServiceException;

	/**
	 * 
	 * @Title: ICommonAnnexService
	 * @Description: 根据父目录查询目录列表
	 * @param @param parentDir 父目录
	 * @param @return 
	 * @return 
	 * @throws
	 */
	List<File> queryDirectoryList(String parentFile) throws ServiceException;

	/**
	 * @author 海水
	 * @Description: 根据文件名称，解析文件类别
	 * @method parseTypeByFileName
	 * @param fileName
	 * @return String
	 * @date 2013-11-4 下午02:50:34
	 */
	String parseTypeByFileName(String fileName) throws ServiceException;

	/**
	 * @author 海水
	 * @Description: 根据文件的名字，解析默认图片
	 * @method parseDefaultImageByFileName
	 * @param fileName
	 * @return
	 * @return String
	 * @date 2013-11-4 下午02:59:37
	 */
	String parseDefaultImageByFileName(String fileName) throws ServiceException;

	/**
	 * @author 海水
	 * @Description： 修改排序值
	 * @method changeOrderValue
	 * @param id
	 * @param orderValue
	 * @return
	 * @return Boolean
	 * @date 2013-11-4 下午09:14:46
	 */
	Boolean changeOrderValue(String id, Integer orderValue) throws ServiceException;

	/**
	 * @author 海水
	 * @Description 修改附件信息
	 * @method editCommonAnnex
	 * @param id
	 * @param title
	 * @param content
	 * @return
	 * @return Boolean
	 * @date 2013-11-4 下午09:40:24
	 */
	Boolean editCommonAnnex(String id, String title, String content) throws ServiceException;
	
	/**
	 * @Title: createTargetFile 
	 * @Description: 根据版本号和文件名称创建文件要上传的地方
	 * @param @param versionFlag
	 * @param @param fileName
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	String createTargetFile(String versionFlag, String fileName) throws ServiceException;
	/**
	 * 
	 * TODO 获取某个固定对象，固定ID下面所有的annex
	 * @method queryByObjectIdAnnex
	 * @param entityName
	 * @param entityId
	 * @return
	 */
	List<CommonAnnex> queryByObjectIdAnnex(String entityName, String entityId, String versionFlag) throws ServiceException;
	/**
	 * @Description: 根据图书对象ID和分页大小获取图书附件对象列表
	 * @Title: queryAdminAll方法名
	 * @param size
	 * @param entityId
	 * @return List<BookAnnex>返回类型
	 * @throws
	 */
	List<CommonAnnex> queryAdminAll(Integer size, String entityId) throws ServiceException;
	/**
	 * @Description: 根据图书对象ID和附件类型获取图书附件对象列表
	 * @Title: queryList方法名
	 * @param type
	 * @param entityId
	 * @return List<BookAnnex>返回类型
	 * @throws
	 */
	List<CommonAnnex> queryList(Integer type, String entityId, String versoinFlag,String entityName) throws ServiceException;
	/**
	 * @Description: 根据图书对象ID和附件版本标识获取图书附件对象列表
	 * @Title: queryListAll方法名
	 * @param versionFlag
	 * @param entityId
	 * @return
	 */
	List<CommonAnnex> queryListAll(String versionFlag, String entityId) throws ServiceException;
	/**
	 * 根据URL获取附件对象
	 * @method queryByUrl
	 * @param url
	 * @return
	 */
	CommonAnnex queryByUrl(String url, String entityName) throws ServiceException;
	/**
	 * 根据pic获取附件对象
	 * @method queryByUrl
	 * @param url
	 * @return
	 */
	CommonAnnex queryByAnnexPath(String annexPath) throws ServiceException;
	
}