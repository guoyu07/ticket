package com.ticket.tags;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ticket.util.GeneralUtil;

/**
 * 对编码的字符串进行解码
 * @author 涂有
 */
public class EncoderTag extends SimpleTagSupport {
	
	/**
	 * 变量名(变量名为空，则直接输出到页面)
	 */
	private String var;
	
	/**
	 * 要编码的字符串
	 */
	private String value;
	
	/**
	 * 是否为编码，反之为解码
	 */
	private boolean encode;

	@Override
	public void doTag() throws JspException, IOException {
		
		if(GeneralUtil.isNotNull(value)){ //不为空才处理
			 
			String newValue = null;
			if(encode){ //判断是编码还是解码
				
				newValue = URLEncoder.encode(value, "utf-8");
			}else{
				
				newValue = URLDecoder.decode(value, "utf-8");
			}
			
			if(GeneralUtil.isNull(var)){ //直接输出
				
				getJspContext().getOut().write(newValue);
			}else{ //设置到变量内
				
				getJspContext().setAttribute(var, newValue);
			}
		}
		super.doTag();
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEncode() {
		return encode;
	}

	public void setEncode(boolean encode) {
		this.encode = encode;
	}
}
