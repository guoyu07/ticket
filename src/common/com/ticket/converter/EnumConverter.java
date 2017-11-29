package com.ticket.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.ticket.util.GeneralUtil;

/**
 * 枚举类型装换器
 * @author tuyou
 */
public class EnumConverter extends StrutsTypeConverter{

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		
		if(GeneralUtil.isNull(arg1)){
			
			return null;
		}
		
		//长度为1是，直接返回
		if(arg1.length == 1){
			
			if(GeneralUtil.isNull(arg1[0])){
				
				return null;
			}else{
				
				return Enum.valueOf(arg2, arg1[0]);
			}
		}
		
		//长度为1以上时，返回数组
		Object[] objs = new Object[arg1.length];
		for(int i = 0; i < arg1.length; i++){
			
			String arg = arg1[i];
			if(GeneralUtil.isNull(arg)){
				
				objs[i] = null;
			}else{
				
				objs[i] = Enum.valueOf(arg2, arg);
			}
		}
		return objs;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		
		return null;
	}
}
