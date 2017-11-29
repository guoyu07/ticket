package com.ticket.util;

import java.util.Arrays;


/**
 * @Description：服务码生成工具类
 * @author：涂有
 * @date 2015年10月28日 上午9:54:34
 */
public class ServiceCodeUtil {
	
	public final static char[] chars = {'0','1','2','3','4','5','6','7','8','9',
			'a','b','c','d','e','f','g','h','i','j','k','l','m',
			'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static String next(String currentCode, char[] rule){
		
		StringBuffer next = new StringBuffer();
		boolean carry = false; //是否有进位
		
		int subIndex = 11; //应该加1的位数，默认是最后一位，但是当最后一位被规则固定后，就应该前进一位
		for(int i = currentCode.length() - 1; i >= 0; i--){
			
			if(rule[i] == '*'){
				
				subIndex = i;
				break;
			}
		}
		
		//倒着循环是因为，数组的下标
		for(int i = currentCode.length() - 1; i >= 0; i--){
			
			//如果在规则中已经固定了的，就直接加入
			if(rule[i] != '*'){
				
				next.append(rule[i]);
				continue;
			}
			
			char c = currentCode.charAt(i);
			
			//先检查上一位是否有进位
			if(carry){
				
				carry = false;
				if(c == 'z'){ //检查本位是否有进位
					
					c = '0';
					carry = true;
				}else{ //没有进位就直接加一
					
					int index = Arrays.binarySearch(chars, c);
					c = chars[index + 1];
				}
			}
			
			//进行普通的加1操作
			if(i == subIndex){
				
				if(c == 'z'){ //检查本位是否有进位
					
					c = '0';
					carry = true;
				}else{ //没有进位就直接加一
					
					int index = Arrays.binarySearch(chars, c);
					c = chars[index + 1];
				}
			}
			next.append(c);
		}
		return next.reverse().toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(next("000000000000", new char[]{'*','*','*','*','*','*','*','*','*','*','*','*'}));
	}
}
