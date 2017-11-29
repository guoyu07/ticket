package com.ticket.tags;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ticket.pojo.WordsPackage;
import com.ticket.service.IWordsPackageService;
import com.ticket.serviceImpl.WordsPackageServiceImpl;
import com.ticket.util.GeneralUtil;

import priv.tuyou.util.WebContext;

/**
 * 把屏蔽词替换为“*”号
 * @author：涂有
 * @date 2017年1月19日 下午4:10:14
 */
public class ShieldTag extends SimpleTagSupport {
	
	IWordsPackageService wordsPackageService = WebContext.getBean(WordsPackageServiceImpl.class);
	
	/**
	 * 要编码的字符串
	 */
	private String value;

	@Override
	public void doTag() throws JspException, IOException {
		
		if(GeneralUtil.isNotNull(value)){ //不为空才处理
			 
			WordsPackage words = wordsPackageService.get();
			if(GeneralUtil.isNotNull(words.getKeywords())){
				
				String[] wordsArray = words.getKeywords().split(",");
				for(String word : wordsArray){
					
					char[] shieldFilter = new char[word.length()];
					Arrays.fill(shieldFilter, '*');
					value = value.replace(word, new String(shieldFilter));
				}
				
			}
			getJspContext().getOut().print(value);
		}
		super.doTag();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
