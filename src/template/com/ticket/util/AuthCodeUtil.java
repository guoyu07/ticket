package com.ticket.util;

import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;

/**
 * @Description：生成的验证码
 * @author：涂有
 * @date 2015年10月29日 下午4:30:58
 */
public class AuthCodeUtil {

	/**
	 * @Description：随机生成一个验证码
	 * @return
	 */
	public static String generate(){
		
		int random = new Random().nextInt(1000000);
		return String.format("%06d", random);
	}
	
	/**
	 * @Description：生成一个验证码并保存在session中
	 * @param key
	 * @return
	 */
	public static String generateAndSave(String key){
		
		String authCode = generate();
		ActionContext.getContext().getSession().put(key + ContextConstants.SMS_VERIFICATE_CODE, authCode);
		return authCode;
	}
	
	/**
	 * @Description：验证是否正确
	 * @param key 
	 * @param authCode
	 * @return
	 */
	public static boolean check(String key, String authCode){
		
		String saved = (String)ActionContext.getContext().getSession().get(key + ContextConstants.SMS_VERIFICATE_CODE);
		if(saved != null && saved.equals(authCode)){
			
			//验证成功后，删除对应key的验证码
			ActionContext.getContext().getSession().remove(key + ContextConstants.SMS_VERIFICATE_CODE);
			return true;
		}
		return false;
	}
	
	public static void main(String args[]){
		
		for(int i = 0; i < 1000; i++)
		System.out.println(AuthCodeUtil.generate());
	}
}
