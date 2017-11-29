package com.ticket.service;


import java.util.List;

import javax.servlet.ServletContext;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.Pagination;
import com.ticket.pojo.SystemDictionary;

/**
 * 系统字典表业务接口
 * @ClassName: ISysDictionaryService   
 * @Description: 提供系统字典表操作的增删改查   
 * @author HiSay  
 * @date  2015-10-24 15:27:39
 *
 */
public interface ISystemDictionaryService extends IBaseService<SystemDictionary, String> {
	
	/**
	 * 保存系统字典表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  字典名称
	 * @param value  字典值
	 * @param parent_id  父字典ID
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	boolean persist(String name,String value, int sort, String description,String parent_id) throws ServiceException;
	
	/**
	 * 保存系统字典表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  字典名称
	 * @param value  字典值
	 * @param parent  父字典
	 * @return  保存成功则返回true, 保存失败则返回false.
	 * @throws ServiceException   
	 */
	boolean persist(String name,String value, int sort, String description,SystemDictionary parent) throws ServiceException;
	
	/**
	 * 修改系统字典表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  字典名称
	 * @param value  字典值
	 * @param parent_id  父字典ID
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean merge(String id, String name,String value, int sort, String description,String parent_id) throws ServiceException;
	
	/**
	 * 修改系统字典表实体
	 * @Title: ISysDictionaryService
	 * @Description:
	 * @param name  字典名称
	 * @param value  字典值
	 * @param parent  父字典
	 * @return  修改成功则返回true, 修改失败则返回false.
	 * @throws ServiceException    
	 */
	boolean merge(String id, String name,String value, int sort, String description,SystemDictionary parent) throws ServiceException;
	
	/**
	 * 移除系统字典表实体
	 * @Title: ISysDictionaryService
	 * @Description: 
	 * @param id 系统字典表ID
	 * @return 移除成功返回true, 移除失败返回false.
	 * @throws ServiceException    
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * @Description：通过字典名称获得字典值
	 * @param name 字典名称
	 * @return
	 */
	String getValueByName(String name);
	
	/**
	 * @Description：通过字典名称获得字典值
	 * @param name 字典名称
	 * @return
	 */
	String getDescriptByName(String name);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> querySubByParentNameAndValue(String name, String keyword);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> querySubByParentNameAndValueAndPinYing(String name, String keyword);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> querySubByParentName(String parentName);
	
	/**
	 * @Description：查询子节点节点通过父节点名称
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> querySubByParentValue(String parentValue);
	
	/**
	 * @Description：查询子节点节点通过父节点名称 根据首字母排序
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> querySubByParentValueOrderByLetter(String parentValue, String...columnNames);
	
	/**
	 * @Description：通过名称获取字典对象
	 * @param name
	 * @return
	 */
	SystemDictionary getByName(String name);
	
	/**
	 * @Description：通过字典值获取字典对象
	 * @param value
	 * @return
	 */
	SystemDictionary getByValue(String value);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParent(String parent_id, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParent(SystemDictionary parent, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	boolean existSameUnderParentByName(String parent_name, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	SystemDictionary getUnderParent(String parent_id, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	SystemDictionary getUnderParent(SystemDictionary parent, String name);
	
	/**
	 * @Description：在父节点下是否存在name相同的对象
	 * @param parent_id
	 * @param name
	 * @return
	 */
	SystemDictionary getUnderParentByName(String parent_name, String name);
	
	/**
	 * 导入城市三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer importFromFile(String versionFlag) throws ServiceException;

	/**
	 * 导入国内城市三字码
	 * @param versionFlag
	 * @throws ServiceException
	 */
	Integer importDomesticCityCode(String versionFlag) throws ServiceException;

	/**
	 * 导入国际城市三字码
	 * @param versionFlag
	 * @throws ServiceException
	 */
	Integer importInternationalCityCode(String versionFlag) throws ServiceException;

	/**
	 * 根据城市名称模糊检索城市
	 * @param keyword  关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	SystemDictionary queryByName(String keyword, String versionFlag) throws ServiceException;
	/**
	 * 初始化城市编码的拼音
	 * @return
	 * @throws ServiceException
	 */
	Integer initialCityCodeHasPinYin() throws ServiceException;

	/**
	 * 导入航空公司二字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer importFlightCompanyTwoCode(String versionFlag) throws ServiceException;

	/**
	 * 根据城市标识查询城市列表
	 * @param cityPoi
	 * @return
	 * @throws ServiceException
	 */
	void initialAllCityByCityPoi(ServletContext application) throws ServiceException;

	/**
	 * 导入国籍列表
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer importNationality(String versionFlag) throws ServiceException;

	/**
	 * 查询国内热门城市
	 * @param versionFlag
	 * @return
	 */
	List<SystemDictionary> queryDomCityByHot(String versionFlag);

	/**
	 * 查询国际热门城市
	 * @param versionFlag
	 * @return
	 */
	List<SystemDictionary> queryIntCityByHot(String versionFlag);

	/**
	 * 查询国内城市
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByParentDom(int pageSize, String versionFlag) throws ServiceException;
	
	/**
	 * 查询国际城市
	 * @param pageSize
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByParentInt(int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词查询国内城市
	 * @param keyword  关键词
	 * @param pageSize  页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByKeywordAndDom(String keyword,
			int pageSize, String versionFlag) throws ServiceException;
	
	/**
	 * 根据关键词查询国际城市
	 * @param keyword  关键词
	 * @param pageSize  页面大小
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryPageModuleByKeywordAndInt(String keyword,
			int pageSize, String versionFlag) throws ServiceException;

	/**
	 * 根据城市名查询机场
	 * @param keyword
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<SystemDictionary> queryByDescription(String keyword, String versionFlag) throws ServiceException;
	/**
	 * @author wangjiafeng
	 * 根据父名称跟描述查询数据字典id
	 * @method queryParentNameAndDes
	 * @param parentName
	 * @param des
	 * @return
	 * @throws ServiceException
	 * @return SystemDictionary
	 * @date 2016-3-8 上午09:24:05
	 */
	SystemDictionary queryParentNameAndDes(String parentName,String des) throws ServiceException;

	/**
	 * 导入城市三四字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	Integer importAirportCode(String versionFlag) throws ServiceException;
	
	/**
	 * 获取指定字典的所有子字典
	 * @param parent_id
	 * @return
	 */
	List<SystemDictionary> querySubByParent(String parent_id);
	
	/**
	 * 查询某字典下是否有指定名称的子节点
	 * @param parent
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	SystemDictionary queryByParentAndName(SystemDictionary parent,String name) throws ServiceException;
}