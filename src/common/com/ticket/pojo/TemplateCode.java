package com.ticket.pojo;

import java.io.Serializable;

/**
 * 模板代码实体
 * @ClassName: TemplateCode   
 * @Description: 用于在动态生成POJO的时候, 保存相关属性用的.   
 * @author HiSay  
 * @date Jul 31, 2013 6:45:58 PM   
 *
 */
public class TemplateCode extends BasePojo implements Serializable {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 属性名称
	 * 例如:String name = null; Integer sex = null; Double money = null;
	 */
	private String attrName = null;
	
	/**
	 * 属性名称
	 * 首字母大写
	 */
	private String firstCapAttrName = null;
	
	/**
	 * 属性数据类型
	 */
	private String attrDataType = null;
	/**
	 * 属性注解类型
	 * 例如:@Column(length=100) @Temporal(TemporalType.TIMESTAMP)
	 */
	private String attrAnnotation = null;
	/**
	 * 属性注释
	 * 例如:名称; 性别; 金额
	 */
	private String attrComment = null;
	/**
	 * 属性注释
	 * 为Unicode的注释
	 */
	private String attrUnicodeComment = null;
	
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrDataType() {
		return attrDataType;
	}
	public void setAttrDataType(String attrDataType) {
		this.attrDataType = attrDataType;
	}
	public String getAttrAnnotation() {
		return attrAnnotation;
	}
	public void setAttrAnnotation(String attrAnnotation) {
		this.attrAnnotation = attrAnnotation;
	}
	public String getAttrComment() {
		return attrComment;
	}
	public void setAttrComment(String attrComment) {
		this.attrComment = attrComment;
	}
	public String getFirstCapAttrName() {
		return firstCapAttrName;
	}
	public void setFirstCapAttrName(String firstCapAttrName) {
		this.firstCapAttrName = firstCapAttrName;
	}
	public String getAttrUnicodeComment() {
		return attrUnicodeComment;
	}
	public void setAttrUnicodeComment(String attrUnicodeComment) {
		this.attrUnicodeComment = attrUnicodeComment;
	}

}
