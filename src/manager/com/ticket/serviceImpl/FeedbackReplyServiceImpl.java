package com.ticket.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ticket.exception.ServiceException;
import com.ticket.pojo.CommonEntity;
import com.ticket.pojo.Feedback;
import com.ticket.pojo.FeedbackReply;
import com.ticket.pojo.Member;
import com.ticket.pojo.SystemUser;
import com.ticket.service.IFeedbackReplyService;
import com.ticket.service.IFeedbackService;
import com.ticket.util.DecoderUtil;
import com.ticket.util.GeneralUtil;

/**
 * 反馈与回复业务接口实现类
 * @ClassName: IFeedbackReplyService   
 * @Description: 提供反馈与回复操作的增删改查   
 * @author HiSay  
 * @date 2016-08-15 15:11:31
 *
 */
public class FeedbackReplyServiceImpl extends BaseServiceImpl<FeedbackReply, String> implements IFeedbackReplyService {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(FeedbackReplyServiceImpl.class);
	
	@Resource private IFeedbackService feedbackService;

	@Override
	public boolean persist(String feedBack_id,Member member,String description,SystemUser systemUser,String replyContent,String images, String versionFlag) throws ServiceException {
		FeedbackReply feedbackReply = new FeedbackReply();
		Feedback feedBack = feedbackService.queryById(Feedback.class.getName() , feedBack_id);
		feedbackReply.setFeekBack(feedBack);
		if(GeneralUtil.isNotNull(member)){
			feedbackReply.setMember(member);
		}
		if(GeneralUtil.isNotNull(description)){
			feedbackReply.setDescription(DecoderUtil.UtfDecoder(description));
		}
		if(GeneralUtil.isNotNull(systemUser)){
			feedbackReply.setSystemUser(systemUser);
		}
		if(GeneralUtil.isNotNull(replyContent)){
			feedbackReply.setReplyContent(DecoderUtil.UtfDecoder(replyContent));
		}
		if(GeneralUtil.isNotNull(images)){
			feedbackReply.setImages(images);
		}
		CommonEntity status = feedbackReply.getStatus();
		status.setVersionFlag(versionFlag);
		feedbackReply.setStatus(status);
		dbDAO.persist(feedbackReply);
		return true;
	}
	
	@Override
	public boolean merge(String id, Feedback feekBack,Member member,String description,SystemUser systemUser,String replyContent, String versionFlag) throws ServiceException {
		FeedbackReply feedbackReply = dbDAO.queryById(this.getTableNameFromEntity(FeedbackReply.class), id);
		feedbackReply.setFeekBack(feekBack);
		feedbackReply.setMember(member);
		feedbackReply.setDescription(DecoderUtil.UtfDecoder(description));
		feedbackReply.setSystemUser(systemUser);
		feedbackReply.setReplyContent(DecoderUtil.UtfDecoder(replyContent));
		CommonEntity status = feedbackReply.getStatus();
		status.setVersionFlag(versionFlag);
		feedbackReply.setStatus(status);
		dbDAO.merge(feedbackReply);
		return true;
	}

	@Override
	public boolean remove(String id) throws ServiceException {
		FeedbackReply feedbackReply = dbDAO.queryById(this.getTableNameFromEntity(FeedbackReply.class), id);
		dbDAO.remove(feedbackReply);
		return true;
	}

	@Override
	public List<FeedbackReply> queryByFeedbackId(String feedbackId)
			throws ServiceException {
		List<FeedbackReply> feedbackReplies = dbDAO.executeJPQLForQuery("select c from " + FeedbackReply.class.getName() + " c where c.feekBack.id = ? order by c.status.createTime", FeedbackReply.class , feedbackId);
		return feedbackReplies;
	}

	@Override
	public FeedbackReply isBacks(String feedback_id) throws ServiceException {
		FeedbackReply reply = dbDAO.executeJPQLForQuerySingle("select c from " + FeedbackReply.class.getName() + " c where c.feekBack.id = ? and c.systemUser.id != null and c.replyContent != null",FeedbackReply.class, feedback_id);
		return reply;
	}

	@Override
	public Integer isFeedBackOrBack(String feedback_id) throws ServiceException {
		List<FeedbackReply> feedbackReplies = dbDAO.executeJPQLForQuery("select c from " + FeedbackReply.class.getName() + " c where c.feekBack.id = ? order by c.status.createTime", FeedbackReply.class , feedback_id);
		int back = 0;
		if(feedbackReplies != null && feedbackReplies.size() == 1){
			for(FeedbackReply feedbackReply:feedbackReplies){
				if(feedbackReply.getMember() != null){
					back = 1;//追加反馈了未回复
				}else if(feedbackReply.getSystemUser() != null){
					back = 2;//没有追加反馈但已回复
				}
			}
		}else if(feedbackReplies != null && feedbackReplies.size() > 1){
			List<FeedbackReply> list1 = new ArrayList<FeedbackReply>();//会员反馈
			List<FeedbackReply> list2 = new ArrayList<FeedbackReply>();//管理员回复
			for(FeedbackReply feedbackReply:feedbackReplies){
				if(feedbackReply.getMember() != null){
					list1.add(feedbackReply);
				}else if(feedbackReply.getSystemUser() != null){
					list2.add(feedbackReply);
				}
			}
			
			FeedbackReply huifu = null;
			FeedbackReply fankui = null;
			if(list1 != null && list1.size() > 1){
				for(int i=0;i<list1.size();i++){//管理员回复最晚时间
					for(int j=i+1;j<list1.size();j++){
						//i时间更大
						if(list1.get(i).getStatus().getCreateTime().compareTo(list1.get(j).getStatus().getCreateTime()) > 0){
							fankui = list1.get(i);
						}else{//j时间更大
							fankui = list1.get(j);
							continue;
						}
					}
				}
			}else if(list1 != null && list1.size() == 1){
				for(FeedbackReply feedbackReply:list1){
					fankui = feedbackReply;
				}
			}
			if(list2 != null && list2.size() > 1){
				for(int i=0;i<list2.size();i++){//会员追加反馈最晚时间
					for(int j=i+1;j<list2.size();j++){
						//i时间更大
						if(list2.get(i).getStatus().getCreateTime().compareTo(list2.get(j).getStatus().getCreateTime()) > 0){
							huifu = list2.get(i);
						}else{//j时间更大
							huifu = list2.get(j);
							continue;
						}
					}
				}
			}else if(list2 != null && list2.size() == 1){
				for(FeedbackReply feedbackReply:list2){
					huifu = feedbackReply;
				}
			}
			
			//追加反馈和管理员回复都存在，判断谁在后面
			if(huifu != null && fankui != null){
				//管理员回复在后
				if(huifu.getStatus().getCreateTime().compareTo(fankui.getStatus().getCreateTime()) > 0){
					back = 3;//有追加反馈并且已经回复
				}else if(huifu.getStatus().getCreateTime().compareTo(fankui.getStatus().getCreateTime()) < 0){
					back = 1;//有追加反馈没有回复
				}
			}else if(huifu != null && fankui == null){
				back = 2;//没有追加反馈但已回复
			}else if(huifu == null && fankui != null){
				back = 1;//有追加反馈没有回复
			}
 		}else if(feedbackReplies == null || feedbackReplies.size() == 0){
 			back = 4;//没有追加反馈也没有回复
 		}
		return back;
	}
}