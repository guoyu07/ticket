package com.ticket.service;

import java.util.List;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportBusinessType;


/**
 * 机场商业类别业务接口
 * @ClassName: IAirportBusinessTypeService   
 * @Description: 提供机场商业类别操作的增删改查   
 * @author HiSay  
 * @date  2015-11-16 13:35:58
 *
 */
public interface IAirportBusinessTypeService extends IBaseService<AirportBusinessType, String> {
	/**
	 * 保存机场商业类别实体
	 * @Title: IAirportBusinessTypeService
	 * @Description:
	 * @param @param name  类别名称
	 * @param @param parent_id  上级类别id
	 * @param @param introduce  类别介绍
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String parent_id,String introduce,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 修改机场商业类别实体
	 * @Title: IAirportBusinessTypeService
	 * @Description:
	 * @param @param name  类别名称
	 * @param @param parent_id  上级类别id
	 * @param @param introduce  类别介绍
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String parent_id,String introduce,Integer orderValue, String versionFlag) throws ServiceException;
	
	/**
	 * 移除机场商业类别实体
	 * @Title: IAirportBusinessTypeService
	 * @Description: 
	 * @param @param id 机场商业类别ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;

	/**
	 * 根据版本标识和父类别ID获取模块的HTML代码
	 * @Title: IAirportBusinessTypeService
	 * @Description:   
	 * @param @param parent_id    上级类别ID, 如果获取顶级类别, 则传入0
	 * @param @param versionFlag  版本表示
	 * @param @return 生成后的HTML代码
	 * @param @throws ServiceException    设定文件   
	 * @return     返回类型   
	 * @throws
	 */
	String queryAirportBusinessTypeSelectOptionHtml(String parent_id, String versionFlag) throws ServiceException;
	
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
	List<AirportBusinessType> queryFirstAirportBusinessTypeList(String versionFlag) throws ServiceException;
	
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
	boolean validateHaveChildAirportBusinessTypes(String parent_id) throws ServiceException;
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
	List<AirportBusinessType> queryChildAirportBusinessTypesByParent(String parent_id) throws ServiceException;
	
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
	boolean setAirportBusinessTypeIsDefaultShow(String entityName, String id) throws ServiceException;

	/**
	 * 查询顶级机场商业类别
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportBusinessType> queryTopList(String versionFlag)throws ServiceException;

	/**
	 * 查询二级商品类别
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportBusinessType> querySecondList(String parent_id,String versionFlag) throws ServiceException;

	/**
	 * 批量彻底删除会员信息
	 * @param idsValue
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean batchRealDelete(String idsValue, String versionFlag) throws ServiceException;
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
	 * 查询最底层商业类别
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportBusinessType> queryChildList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据名称查询类别
	 * @param name
	 * @return
	 */
	AirportBusinessType queryByName(String name);
}