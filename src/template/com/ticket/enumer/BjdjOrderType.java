package com.ticket.enumer;

/**
 * @Description：订单类型
 * @author：涂有
 * @date 2015年10月30日 下午1:31:15
 */
public enum BjdjOrderType {
	
	bjdj("便捷登机"), //便捷登机
	electromobile("电瓶车"); //电瓶车
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private BjdjOrderType(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
