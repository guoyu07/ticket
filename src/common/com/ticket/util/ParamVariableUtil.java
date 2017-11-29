package com.ticket.util;

/**
 * 含有参数变量的字符串操作工具类
 * @author 涂有
 */
public class ParamVariableUtil {

	/**
	 * 变量名的前缀
	 */
	public static final String prefix = "{";
	
	/**
	 * 变量名的后缀
	 */
	public static final String subfix = "}";
	
	public static boolean containsVariable(String str){
		
		if(GeneralUtil.isNull(str)){
			
			return false;
		}
		
		return str.matches("^.*\\{.+\\}.*$");
	}
	
	/**
	 * 检测一个字符串中是否包含指定参数
	 * @param str（可为空null）
	 * @param varName 变量名
	 * @return
	 */
	public static boolean contains(String str, String varName){
		
		if(GeneralUtil.isNull(str)){
			
			return false;
		}
		
		return str.indexOf(prefix + varName + subfix) == -1 ? false : true;
	}
	
	/**
	 * 把字符串中指定变量名替换为真是内容
	 * @param str
	 * @param varName
	 * @param content
	 * @return
	 */
	public static String replace(String str, String varName, String content){
		
		if(GeneralUtil.isNull(str)){
			
			return null;
		}
		
		if(GeneralUtil.isNull(varName) || GeneralUtil.isNull(content)){
			
			return str;
		}
		
		return str.replace(prefix + varName + subfix, content);
	}
	
	public static void main(String[] args) {
		
		System.out.println(contains("asdfasdf{flightNumber}asdf", "flightNumber"));
		System.out.println(replace("as{flightNumber}dfasdf{flightNumber}asdf", "flightNumber", "航班号"));
		System.out.println(containsVariable("asasdfd{}sdf"));
	}
}
