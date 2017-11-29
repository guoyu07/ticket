package com.ticket.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 数据格式化处理标签
 * @ClassName: DataFormatTag
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2015-1-26 下午04:48:50
 *
 */
public class DataFormatTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Integer size = null;
	private String value = null;
	private Boolean trim = false;
	private String fillValue = "...";
	private String style = null;
	private Boolean removeHTMLTag = false;

	private boolean filterSpace = true;
	
	public Boolean getRemoveHTMLTag() {
		return removeHTMLTag;
	}

	public void setRemoveHTMLTag(Boolean removeHTMLTag) {
		this.removeHTMLTag = removeHTMLTag;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public Boolean getTrim() {
		return trim;
	}

	public void setTrim(Boolean trim) {
		this.trim = trim;
	}

	public String getFillValue() {
		return fillValue;
	}

	public void setFillValue(String fillValue) {
		this.fillValue = fillValue;
	}

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if (value != null && value.length() > 0) {
				String temp = null;
				if(filterSpace) {
					temp = value.replaceAll("\\s*|\t|\r|\n|nbsp;", "")
					.replaceAll("<.*?>", "").replaceAll("&.*?;", "")
					.replaceAll("^[\u00ff\uffff]", "");
				} else {
					temp = value.replaceAll("^[\u00ff\uffff]", "").replaceAll("<.*?>", "").replaceAll("&.*?;", "");
				}
				if (trim) {
					temp = temp.trim();
				}

				if (this.size != null) {
					
					int count = 0;
					int index = 0;
					while (index < temp.length()) {
						
						if (temp.charAt(index) >= 0 && temp.charAt(index) <= 127) {
							count++;
						} else {
							count += 2;
						}
						if (count >= this.size.intValue()) {
							break;
						}
						index++;
					}

					if (count >= this.size.intValue()) {
						temp = temp.substring(0, index) + this.fillValue;
					} else {
						temp = temp.substring(0, index);
					}
				}
				out.print(temp);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public boolean isFilterSpace() {
		return filterSpace;
	}

	public void setFilterSpace(boolean filterSpace) {
		this.filterSpace = filterSpace;
	}

}