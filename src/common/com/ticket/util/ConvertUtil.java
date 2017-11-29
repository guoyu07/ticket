package com.ticket.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 集合-字符串的转换工具类
 * @ClassName: ConvertUtil   
 * @Description: 对字符串和集合之间进行相互的转换操作.   
 * @author HiSay  
 * @date Jul 9, 2013 6:55:08 AM   
 *   
 * @param <T>
 */
public class ConvertUtil<T> {
	
	/**
	 * setToList(Set集合转换为List集合)
	 * @param set 任意的Set集合
	 * @return list 返回转换后的List集合
	 */
	public List<T> setToList(Set<T> set) {
		if(set != null && set.size() > 0) {
			Iterator<T> iterator = set.iterator();
			List<T> list = new ArrayList<T>();
			while(iterator.hasNext()) {
				list.add(iterator.next());
			}
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * listToString(List集合转换为字符串)
	 * @param list {@link java.util.List}
	 * @param separate 接合符号
	 * @return String List集合拆分后的数组
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String listToString(List<T> list, String separate) {
		if(list != null && list.size() > 0) {
			if(separate != null && separate.trim().length() > 0) {
				StringBuilder sb = new StringBuilder();
				for(T str : list) {
					sb.append(str);
					sb.append(separate);
				}
				return sb.substring(0, sb.length() - separate.length());
			}
		}
		return null;
	}
	
	/**
	 * 
	 * strintToList(根据指定的字符串和分割符,转换为List集合并返回)
	 * @param value 指定的字符串
	 * @param separate 接合符号
	 * @return String List集合
	 */
	@SuppressWarnings("unchecked")
	public List<T> stringToList(String value, String separate) {
		if(value != null && value.trim().length() > 0) {
			if(separate != null && separate.trim().length() > 0) {
				List<T> list = new ArrayList<T>();
				StringTokenizer stk = new StringTokenizer(value, separate);
				while (stk.hasMoreTokens()) {
					list.add((T)stk.nextToken());
				}
				return list;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> stringToListNew(String value, String separate) {
		if(value != null && value.trim().length() > 0) {
			if(separate != null) {
				List<T> list = new ArrayList<T>();
				StringTokenizer stk = new StringTokenizer(value, separate);
				while (stk.hasMoreTokens()) {
					list.add((T)stk.nextToken());
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * strintToList(以分隔符为分隔，取得变量组)
	 * @param strSource 指定的字符串
	 * @param separate 分割符号
	 * @return 返回数组
	 */
	public static String[] stringToArray(String strSource, String separator) {
		int arrayCount = GeneralUtil.getSizeBySeparator(strSource, separator);
		String[] stringlist = new String[arrayCount];
		int intPos = 0;
		int i = 0;
		while ((intPos = strSource.indexOf(separator)) != -1) {
			stringlist[i] = strSource.substring(0, intPos);
			strSource = strSource.substring(intPos + 1, strSource.length());
			i++;
		}
		stringlist[i] = strSource;
		return stringlist;
	}
	
	/**
	 * 把Integer数组转换成List集合
	 * 
	 * @param array
	 *            要转换的Integer数组
	 * @return
	 */
	public List<Integer> integerArrayToList(Integer[] array) {
		if (array != null && array.length > 0) {
			List<Integer> integerList = new ArrayList<Integer>();
			for (int i = 0; i < array.length; i++) {
				integerList.add(array[i]);
			}
			return integerList;
		} else {
			return null;
		}
	}
}
