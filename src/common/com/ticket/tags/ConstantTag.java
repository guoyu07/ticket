package com.ticket.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ticket.constants.ContextConstants;
import com.ticket.util.ConstantUtil;

/**
 * 系统常量标签
 * @ClassName: ConstantTag   
 * @Description: 可以在业务用自定义标签的形式获取系统里面的常量   
 * @author HiSay  
 * @date Aug 14, 2013 11:23:27 AM   
 *
 */
public class ConstantTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String versionFlag = ContextConstants.VERSION_FLAG_OF_CHINESE; //语言版本标识,默认为中文版.
	private String type = "getValue";        //自定义标签业务分类, 用type属性来区分自定义标签处理的不同的业务方法.默认为获取constants.properties里面的value值
	private Integer size = 1;                //当获取列表的时候. 这个size表示获取的数量.默认取1条.
	private String value = null;             //辅助传值进来. 
	
	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			/**
			 * 页面传入key值, 到constants.properties里面获取value值
			 */
			if("getValue".equals(type)) {
				out.print(ConstantUtil.getConstantValueAsString(value));
			}
		} catch (Exception e) {
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getVersionFlag() {
		return versionFlag;
	}

	public void setVersionFlag(String versionFlag) {
		this.versionFlag = versionFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
