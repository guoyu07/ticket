package com.ticket.enumer;

/**
 * 冻结状态
 * @author xiongwei
 *
 */
public enum FreezeStatus {
	init("初始状态"),
	
	freeze("暂停／冻结"), 
	
	actived("启用／解冻");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private FreezeStatus(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
