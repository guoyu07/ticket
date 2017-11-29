package com.ticket.service;

import com.ticket.pojo.InMessage;
import com.ticket.pojo.OutMessage;

public abstract class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage allType(InMessage msg){
		OutMessage out = new OutMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}
	@Override
	public OutMessage textTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage locationTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage imageTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage linkTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage eventTypeMsg(InMessage msg) {
		return allType(msg);
	}

}
