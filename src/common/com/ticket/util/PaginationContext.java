package com.ticket.util;

/**
 * 用ThreadLoca来设置Pagination的offset和pagesize
 * @ClassName: PaginationContext   
 * @Description:    
 * @author HiSay  
 * @date Jul 31, 2013 9:22:44 AM   
 *
 */
public class PaginationContext {
	
	/**
	 * 当前页码数
	 */
	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	
	/**
	 * 一页显示多少页码
	 */
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
	
	
	static{
		//初始，分页值
		offset.set(0);
		pagesize.set(30);
	}
	
	/**
	 * 获得当前页码数
	 */
	public static int getOffset(){
		Integer os = (Integer)offset.get();
		if(os == null){
			return 0;
		}
		return os;
	}
	
	/**
	 * 设置当前页码数
	 */
	public static void setOffset(int offsetvalue){
		offset.set(offsetvalue);
	}
	
	/**
	 * 移除页码数
	 */
	public static void removeOffset(){
		offset.remove();
	}
	
	/**
	 * 获得一页显示多少页码的数
	 */
	public static int getPagesize(){
		Integer ps = (Integer)pagesize.get();
		if(ps == null){
			return 30;
		}
		return ps;
	}
	
	/**
	 * 设置页码数
	 */
	public static void setPagesize(int pagesizevalue){
		pagesize.set(pagesizevalue);
	}
	
	/**
	 * 移除页码数
	 */
	public static void removePagesize(){
		pagesize.remove();
	}
	
}
