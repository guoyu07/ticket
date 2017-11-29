package com.ticket.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.sf.json.JSONArray;

/**
 * @Description：专门处理树的结构的服务类
 * @author：涂有
 * @date 2015年10月24日 下午2:06:58
 */
public interface ITreeService<T, ID> extends IBaseService<Object, String>{

	/**
	 * @Description：查询树所有的节点
	 * @return
	 */
	public <T> List<T> query(Class<T> c);
	
	/**
	 * @Description：统计父节点包含的子节点个数
	 * @param c
	 * @return
	 */
	public <T> Long countSubByParent(Class<T> c, String parent_id);
	
	/**
	 * @Description：根据父id获得子对象
	 * @return
	 */
	public <T> List<T> querySubByParent(Class<T> c, String parent_id);
	
	/**
	 * @Description：根据父id判断有没有子节点
	 * @return
	 * @throws
	 */
	public <T> boolean hasSubByParent(Class<T> c, String parent_id);
	
	/**
	 * @Description：得到根节点(可能有多个)
	 */
	public <T> List<T> queryRootNode(Class<T> c);
	
	/**
	 * @Description：通过子节点查询得到父节点
	 * @param c
	 * @param sub_id
	 * @return
	 */
	public <T> T getParentBySub(Class<T> c, String sub_id);

	/**
	 * @Description：遍历树的所有节点
	 * @param c
	 * @param textColumnName 树节点文字的对应的数据库字段
	 * @return json字符串
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public <T> JSONArray traverse(Class<T> c, String...textColumnName) throws Exception ;
	
	/**
	 * @Description：遍历树指定节点下的所有节点
	 * @param c
	 * @param textColumnName 树节点文字的对应的数据库字段
	 * @return json字符串
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public <T> JSONArray traverseUnderParent(Class<T> c, String parent_id, String...textColumnName) throws Exception ;
	
	/**
	 * @Description：通过父子段的指定属性得到子节点数组
	 * @param columnName
	 * @param value
	 * @return
	 */
	public <T> List<T> querySubByParent(Class<T> c, String columnName, String value);
}
