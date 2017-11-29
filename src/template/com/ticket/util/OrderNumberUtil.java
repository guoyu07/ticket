package com.ticket.util;

import java.util.Date;

/**
 * @Description：订单号生成器
 * @author：涂有
 * @date 2015年10月29日 下午5:55:56
 */
public class OrderNumberUtil {

	public static String generate(){
		
		return new Date().getTime() + AuthCodeUtil.generate();
	}
	
	public static void main(String args[]){
		
		System.out.println(OrderNumberUtil.generate());
	}
}
