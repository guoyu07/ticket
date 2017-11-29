package com.ticket.enumer;

public enum SaleTaskType {

	signCount("签约数"),
	
	recharge("充值金额"),

	phoneCount("电话拜访量"),

	visitCount("外出拜访量");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private SaleTaskType(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
