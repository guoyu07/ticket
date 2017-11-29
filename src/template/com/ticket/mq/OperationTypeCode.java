package com.ticket.mq;

/**
 * @Description：航段操作类型代码，其枚举值
 * @author：涂有
 * @date 2015年11月26日 上午10:23:34
 */
public enum OperationTypeCode {

	ADF("新增航班"),
	ADV("提前航班"),
	ALT("备降航班"),
	CAN("取消航班"),
	CHG("改降航班"),
	CSF("共享航班"),
	DEL("延误航班"),
	DFI("延误时间不确定的航班"),
	TXT("测试用航班"),
	MVM("场间移动航班（维护）"),
	MVT("场间移动航班（牵引）"),
	OTH("其他类型的航班"),
	POS("调机"),
	REG("定期航班"),
	RET("返航航班"),
	SLI("滑回航班"),
	MER("合并航班"),
	NOP("非运营航班");
	
	/**
	 * 枚举字段的文字描述
	 */
	private String text;
	
	private OperationTypeCode(String text){
		
		this.text = text;
	}

	public String getValue() {
		return this.toString();
	}
	
	public String getText(){
		return text;
	}
}
