package com.ticket.util;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Jpush消息推送的工具类
 * @author 涂有
 */
public class JPushClientUtil {
	
	/**
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
	 */
	private static long timeToLive = 60 * 60 * 24;

	private static final String appKey = "3fe375c339a2e2c83617f962";
	private static final String masterSecret = "c500f64d1c9b956a9211e543";

	private static final JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	
	/**
	 * 推送给所有人
	 * @param content 推送内容
	 */
	public static void sendAll(String content){
		
		PushPayload payload = PushPayload.alertAll(content);
		send(payload);
	}
	
	/**
	 * 推送给所有人
	 * @param content 推送内容
	 */
	public static void sendAll(String title, String content, String url){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", url);
		//android
		PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setNotification(Notification.android(content, title, map))
                .setAudience(Audience.all())
                .build();
		send(payload);
		
		//ios
		payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setNotification(Notification.ios(title, map))
                .setAudience(Audience.all())
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
		send(payload);
	}
	
	/**
	 * 发送给单个用户
	 * @param user_id 用户id
	 * @param title 消息标题
	 * @param content 消息内容
	 * @param url 跳转的url
	 */
	public static void sendToSingleUser(String user_id, String title, String content, String url){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", url);
		
		//android
		PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setNotification(Notification.android(content, title, map))
                .setAudience(Audience.alias(user_id.replaceAll("-", "")))
                .build();
		send(payload);
		
		//ios
		payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setNotification(Notification.ios(title, map))
                .setAudience(Audience.alias(user_id.replaceAll("-", "")))
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
		send(payload);
	}
	
	/**
	 * 发送数据
	 * @param payload
	 */
	private static void send(PushPayload payload){
		
		try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
        	
            System.out.println("Connection error, should retry later");

        } catch (APIRequestException e) {
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
	}

	public static void main(String[] args) {
		
		sendToSingleUser("d212c3d4-5902-472c-9adb-d6c4bd62a0c0", "消息标题", "消息内容", "https://www.baidu.com");
//		sendAll("消息标题", "消息内容", "https://www.baidu.com");
//		sendAll("消息内容");
	}
}