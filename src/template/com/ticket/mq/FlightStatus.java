package com.ticket.mq;

/**
 * @Description：航班状态标识，其枚举值
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum FlightStatus {

	NO("非运营航班"),
	AB("已起飞"),
	BD("登机中"),
	CX("取消航班"),
	DV("改降"),
	ES("估计时间"),
	EX("飞机已从前场起飞"),
	FB("行李递送开始"),
	FS("最终进场"),
	FX("航班完成"),
	GA("登机门准备开发"),
	SH("计划"),
	GC("登机门关闭"),
	GO("登机门开放"),
	LB("最后一件行李"),
	LC("最后呼叫"),
	LD("着陆"),
	NI("延误"),
	OB("上/撤轮档"),
	OT("按时"),
	OV("着陆失败"),
	RS("返回停机位"),
	ZN("进入机场一定区域内（约12分钟内到达机场）");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private FlightStatus(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
