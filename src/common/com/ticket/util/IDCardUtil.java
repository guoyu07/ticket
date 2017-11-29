package com.ticket.util;

import java.util.Calendar;

public class IDCardUtil {

	/**
	 * 判断一个身份证号，是否未男性身份证
	 */
	public static boolean isMan(String idCard){
		
		String sex = idCard.substring(16, 17);
		int sexInt = Integer.parseInt(sex);
		if(sexInt % 2 == 1){
			
			return true;
		}
		return false;
	}
	
	/**
	 * 返回性别的汉字描述，
	 * @param idCard
	 * @return “男” or “女”
	 */
	public static String sex(String idCard){
		
		if(isMan(idCard)){
			
			return "男";
		}
		return "女";
	}
	
	/**
	 * 得到一个身份证号的生日信息
	 */
	public static Calendar getBirth(String idCard){
		
		String birthday = idCard.substring(6,14);
		String year = birthday.substring(0,4);
		String month = birthday.substring(4,6);
		String day = birthday.substring(6,8);
		
		Calendar birth = Calendar.getInstance();
		birth.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		return birth;
	}
}
