package com.ticket.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.AirportInfo;
import com.ticket.pojo.Pagination;


/**
 * 机场信息业务接口
 * @ClassName: IAirportInfoService   
 * @Description: 提供机场信息操作的增删改查   
 * @author HiSay  
 * @date  2016-04-01 16:37:25
 *
 */
public interface IAirportInfoService extends IBaseService<AirportInfo, String> {
	/**
	 * 保存机场信息实体
	 * @Title: IAirportInfoService
	 * @Description:
	 * @param @param name  机场名称
	 * @param @param englishName  英文名
	 * @param @param threeCode  三字码
	 * @param @param fourCode  四字码
	 * @param @param countryCode  国家代码
	 * @param @param districtFlag  地区标识
	 * @param @param searchFlag  字母搜索标识
	 * @param @return  保存成功则返回true, 保存失败则返回false.
	 * @param @throws ServiceException   
	 * @return  
	 * @throws
	 */
	boolean persist(String name,String englishName,String threeCode,String fourCode,String countryCode,String districtFlag,String searchFlag, String versionFlag) throws ServiceException;
	
	/**
	 * 修改机场信息实体
	 * @Title: IAirportInfoService
	 * @Description:
	 * @param @param name  机场名称
	 * @param @param englishName  英文名
	 * @param @param threeCode  三字码
	 * @param @param fourCode  四字码
	 * @param @param countryCode  国家代码
	 * @param @param districtFlag  地区标识
	 * @param @param searchFlag  字母搜索标识
	 * @param @return  修改成功则返回true, 修改失败则返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean merge(String id, String name,String englishName,String threeCode,String fourCode,String countryCode,String districtFlag,String searchFlag, String versionFlag) throws ServiceException;
	
	/**
	 * 移除机场信息实体
	 * @Title: IAirportInfoService
	 * @Description: 
	 * @param @param id 机场信息ID
	 * @param @return 移除成功返回true, 移除失败返回false.
	 * @param @throws ServiceException    
	 * @return   
	 * @throws
	 */
	boolean remove(String id) throws ServiceException;
	
	/**
	 * 根据机场拼音首字母初始化城市
	 * @param application
	 * @throws ServiceException
	 */
	void initialAllCityByCityPoi(ServletContext application)
			throws ServiceException;
	
	/**
	 * 更改机场信息时初始话化前端机场信息
	 * @param application
	 * @throws ServiceException
	 */
	void initAllCityByUpdate(Map application) throws ServiceException;

	/**
	 * 从文件导入机场信息
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	int importFromFile(String versionFlag) throws ServiceException;

	/**
	 * 根据三字码查询四字码
	 * @param threeCode 机场三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	String getFourCodeByThreeCode(String threeCode, String versionFlag) throws ServiceException;

	/**
	 * 根据四字码查询航空公司信息
	 * @param fourCode 机场四字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	AirportInfo queryByFourCode(String fourCode, String versionFlag) throws ServiceException;

	/**
	 * 根据三字码查询航空公司信息
	 * @param threeCode 三字码
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	AirportInfo queryByThreeCode(String threeCode, String versionFlag) throws ServiceException;

	/**
	 * 查询国内热门城市
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryDomHotAirport(String versionFlag) throws ServiceException;

	/**
	 * 查询国际热门城市
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryIntHotAirport(String versionFlag) throws ServiceException;

	/**
	 * 根据拼音首字母查询国内机场
	 * @param alpha  拼音首字母
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryDomAirportByAlpha(String alpha, String versionFlag)
			throws ServiceException;

	/**
	 * 根据拼音首字母查询国际机场
	 * @param alpha 拼音首字母
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryIntAirportByAlpha(String alpha, String versionFlag)
			throws ServiceException;

	/**
	 * 根据关键词查询国内机场（三字码，四字码，拼音，名称）
	 * @param keyword 关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryDomListByKeyword(String keyword, String versionFlag) throws ServiceException;

	/**
	 * 根据关键词查询国际机场（三字码，四字码，拼音，名称）
	 * @param keyword 关键词
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryIntListByKeyword(String keyword, String versionFlag) throws ServiceException;

	/**
	 * 管理国内机场信息
	 * @param versionFlag
	 * @param adminCommonPageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryDomAirport(String versionFlag, int adminCommonPageSize) throws ServiceException;

	/**
	 * 管理国际机场信息
	 * @param versionFlag
	 * @param adminCommonPageSize
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryIntAirport(String versionFlag, int adminCommonPageSize) throws ServiceException;

	/**
	 * 根据关键词查询机场信息
	 * @param keyword 机场关键词（包括名称，三字码，四字码，拼音）
	 * @param versionFlag
	 * @param pageSize 每页显示的数据数量
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryEntityByKeyword(String keyword,String versionFlag, int pageSize) throws ServiceException;

	/**
	 * 根据关键词查询国际机场信息
	 * @param keyword 机场关键词（包括名称，三字码，四字码，拼音）
	 * @param versionFlag
	 * @param pageSize 每页显示的数据数量
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryIntAirportByKeyword(String keyword, String versionFlag,
			int pageSize) throws ServiceException;

	/**
	 * 根据关键词查询国内机场信息（含港澳台）
	 * @param keyword 机场关键词（包括名称，三字码，四字码，拼音）
	 * @param versionFlag
	 * @param pageSize 每页显示的数据数量
	 * @return
	 * @throws ServiceException
	 */
	Pagination queryDomAirportByKeyword(String keyword, String versionFlag,
			int pageSize) throws ServiceException;

	/**
	 * 根据城市查询机场信息
	 * @param city 城市名
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryByCity(String city, String versionFlag) throws ServiceException;

	/**
	 * 从文件导入机场信息
	 * @param tempFilePath  文件路径
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	boolean importFromFile(String tempFilePath, String versionFlag) throws ServiceException;

	/**
	 * 查询所有机场信息
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryByList(String versionFlag) throws ServiceException;
	
	/**
	 * 根据关键词查询所有机场信息
	 * @param versionFlag
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryListByKeyword(String keyword,String versionFlag) throws ServiceException;

	/**
	 * 根据机场名称查询机场
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	List<AirportInfo> queryByName(String name) throws ServiceException;
}