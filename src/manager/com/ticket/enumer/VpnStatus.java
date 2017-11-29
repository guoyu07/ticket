package com.ticket.enumer;

public enum VpnStatus {

	untreated("未处理"),
	
	treated("处理成功"),

	cantProcess("处理不了");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private VpnStatus(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
