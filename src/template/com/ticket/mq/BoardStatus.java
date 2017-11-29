package com.ticket.mq;

/**
 * @Description：登机口状态标识，其枚举值
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum BoardStatus {

	A("登机门管理员准备开放登机门"),
	B("登机中"),
	C("关闭"),
	L("最后呼叫"),
	O("开放");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private BoardStatus(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
