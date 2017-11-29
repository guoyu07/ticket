package com.ticket.mq;

/**
 * @Description：航段操作类型
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum Otc {

	NOP("非运营"),
	CHG("改降"),
	ADF("新增"),
	ADV("提前"),
	ALT("备降"),
	CAN("提前"),
	CSF("共享"),
	DEL("延误"),
	DFI("延误待定"),
	MVM("维护移动"),
	MVT("牵引移动"),
	OTH("其他"),
	POS("调机"),
	REG("定期"),
	RET("返航"),
	SLI("滑回"),
	MER("合并");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private Otc(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
