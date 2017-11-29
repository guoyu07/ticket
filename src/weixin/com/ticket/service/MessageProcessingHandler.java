package com.ticket.service;

import com.ticket.pojo.InMessage;
import com.ticket.pojo.OutMessage;
/**
 * 消息处理器
 * @author GodSon
 *
 */
public interface MessageProcessingHandler {
	public final static String MSG_TYPE_TEXT = "text";
	public final static String MSG_TYPE_LOCATION = "location";
	public final static String MSG_TYPE_IMAGE = "image";
	public final static String MSG_TYPE_LINK = "link";
	public final static String MSG_TYPE_EVENT = "event";
	
	public final static String MSG_TYPE_NEWS = "news";
	public final static String MSG_TYPE_MUSIC = "music";
	public final static String MSG_TYPE_VIDEO = "video";
	
	/** 
     * 事件类型：subscribe(订阅) 
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
      
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
      
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";  
	
	/**
	 * 文字内容的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage textTypeMsg(InMessage msg);
	
	/**
	 * 地理位置类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage locationTypeMsg(InMessage msg);
	
	/**
	 * 图片类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage imageTypeMsg(InMessage msg);
	
	/**
	 * 链接类型的消息处理
	 * @param msg
	 * @return
	 */
	public OutMessage linkTypeMsg(InMessage msg);
	/**
	 * 事件类型的消息处理。<br/>
	 * 在用户首次关注公众账号时，系统将会推送一条subscribe的事件
	 * @param msg
	 * @return
	 */
	public OutMessage eventTypeMsg(InMessage msg);
}
