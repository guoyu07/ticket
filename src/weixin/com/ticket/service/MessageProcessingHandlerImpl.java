package com.ticket.service;

import java.util.ArrayList;
import java.util.List;

import com.ticket.pojo.Article;
import com.ticket.pojo.Articles;
import com.ticket.pojo.InMessage;
import com.ticket.pojo.NewsClass;
import com.ticket.pojo.OutMessage;
import com.ticket.util.ConvertUtil;
import com.ticket.util.GeneralUtil;
import com.ticket.util.SpringUtil;

/**
 * 自定义实现消息处理
 * @author GodSon
 *
 */
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {

	private static final IArticleService articleService = (IArticleService)SpringUtil.getObjectFromSpring("articleService");
	private static final INewsClassService newsClassService = (INewsClassService)SpringUtil.getObjectFromSpring("newsClassService");
	private String versionFlag = null;
	//private String domain = "weixin.jms100.com";
	private String domain = "www.teatianxia.com";
	private static final ConvertUtil<String> convertUtil = new ConvertUtil<String>();
	@Override
	@SuppressWarnings("unused")
	public OutMessage textTypeMsg(InMessage msg) {
		try {
			OutMessage oms = new OutMessage();
			//String name = methodComparedService.getClassName("weixinReply", versionFlag);
			NewsClass nc = null;//newsClassService.queryByName(versionFlag, name, true);
			List<Article> list = null; //articleService.queryArticleListFront(nc.getId(), versionFlag, 5, msg.getContent());
			
			if(list != null && list.size() > 0) {
				List<Articles> articles = new ArrayList<Articles>();
				oms.setMsgType(MessageProcessingHandler.MSG_TYPE_NEWS);
				for(Article art : list) {
					Articles  article = new Articles();
					article.setTitle(art.getTitle());
					article.setDescription(art.getIntroduce());
					if(GeneralUtil.isNotNull(art.getThumbnail())) {
						article.setPicUrl("http://" + domain + art.getThumbnail());
					} else {
						article.setPicUrl("http://" + domain+ "/template/store/images/wx_default.jpg");
					}
					if(GeneralUtil.isNotNull(art.getIntroduce())) {
						article.setUrl(art.getIntroduce());
					} else {
						article.setUrl("http://" + domain + this.getArticleUrl(art.getStatus().getVisitUrl()+""));
					}
					articles.add(article);
				}
				oms.setArticles(articles);
				oms.setArticleCount(articles.size());
				oms.setFuncFlag(1);
				return oms;
			} 
			return oms;
		} catch(Exception e) {
			return new OutMessage();
		}
	}

	@Override
	public OutMessage locationTypeMsg(InMessage msg) {
		OutMessage oms = new OutMessage();
		return oms;
	}

	@Override
	public OutMessage imageTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutMessage linkTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutMessage eventTypeMsg(InMessage msg) {
		// 事件类型
		String eventType = msg.getEvent();
		OutMessage oms = new OutMessage();
		// 订阅
		if (eventType.equals(MessageProcessingHandler.EVENT_TYPE_SUBSCRIBE)) {
			oms.setMsgType(MessageProcessingHandler.MSG_TYPE_NEWS);
			try {
				//String name = methodComparedService.getClassName("weixinArticle", versionFlag);
				NewsClass nc = null;//newsClassService.queryByName(versionFlag, name, true);
				List<Article> list = null;//articleService.queryArticleListFront(nc.getId(), null, versionFlag, 5, false);
				List<Articles> articles = new ArrayList<Articles>();
				if(list != null && list.size() > 0) {
					for(Article art : list) {
						Articles  article = new Articles();
						article.setTitle(art.getTitle());
						article.setDescription(art.getContent());
						if(GeneralUtil.isNotNull(art.getThumbnail())) {
							article.setPicUrl("http://" + domain + art.getThumbnail());
						} else {
							article.setPicUrl("http://" + domain + "/template/store/images/wx_default.jpg");
						}
						if(GeneralUtil.isNotNull(art.getIntroduce())) {
							article.setUrl(art.getIntroduce());
						} else {
							article.setUrl("http://" + domain + this.getArticleUrl(art.getStatus().getVisitUrl()+""));
						}
						articles.add(article);
					}
				}
				oms.setArticles(articles);
				oms.setArticleCount(articles.size());
				oms.setFuncFlag(1);
			} catch(Exception e) {
			}
		} else if (eventType.equals(MessageProcessingHandler.EVENT_TYPE_UNSUBSCRIBE)) {
			// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
		} else if (eventType.equals(MessageProcessingHandler.EVENT_TYPE_CLICK)) {
			// 事件KEY值，与创建自定义菜单时指定的KEY值对应
			String eventKey = msg.getEventKey();
			if (eventKey.equals("aoruola")) {}
		} else {
			oms.setContent("输入无效！欢迎光临聚名商微信公众平台");
		}
		return oms;
	}
	
	/**
	 * 获取文章的链接
	 * @param url
	 * @return
	 */
	public String getArticleUrl(String url){
		try {
			//url format:/${art.url }/${member.status.visitUrl}.html
			return "/articleInfo/"+url+".html";
		} catch(Exception e) {
			return "#";
		}
	}
}
