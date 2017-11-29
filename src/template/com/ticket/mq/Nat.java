package com.ticket.mq;

/**
 * @Description：航班性质（大类）
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum Nat {

	PAX("客机"),
	CGO("货机"),
	GEN("通用"),
	SPE("特殊");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private Nat(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
