package com.ticket.util;

import java.text.DecimalFormat;

/**
 * 格式化金额工具类
 * @ClassName: MoneyUtil   
 * @Description: 对金额进行指定格式的输出显示
 * @author HiSay  
 * @date Jul 8, 2013 11:50:06 AM   
 *
 */
public class MoneyUtil {
	private final static DecimalFormat nf = new DecimalFormat("###,##0.00");

	/**
	 * 格式化普通字符串为指定格式的金额字符串
	 * @Title: MoneyUtil
	 * @Description: 对传入的字符串进行金额格式化
	 * @param @param s
	 * @param @return 格式化后的字符串
	 * @return  String
	 * @throws
	 */
	public static String formatToMoney(String s) {
		if (s == null || s.equals("")) {
			return "0.00";
		}
		try {
			return formatToMoney(Double.parseDouble(s));
		} catch (Exception e) {
			return s;
		}
	}
	
	/**
	 * 格式化金额为指定格式的金额字符串
	 * @Title: MoneyUtil
	 * @Description: 对传入的金额值进行金额格式化
	 * @param @param s
	 * @param @return 格式化后的字符串
	 * @return  String
	 * @throws
	 */
	public static String formatToMoney(double d) {
		try {
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}
}