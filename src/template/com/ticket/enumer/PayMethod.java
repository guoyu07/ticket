package com.ticket.enumer;

public enum PayMethod {

	baidu("百度支付"),
	alipay("支付宝支付"),
	swift("微信(威富通)支付"),
	weixin("微信支付");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private PayMethod(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
	
	public static void main(String args[]){
		
		for(PayMethod p : PayMethod.values()){
			
			System.out.println(p + "--" + p.getText());
		}
	}
}
