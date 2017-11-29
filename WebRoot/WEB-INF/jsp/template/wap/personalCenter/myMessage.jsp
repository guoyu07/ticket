<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="消息提醒" name="title"/>
				</jsp:include>
               <div class="tab-notice">
					
       <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <td >
          	<ticket:common type="myAnnounecementCount"/>
          	<a href="javascript:location.replace('/airport/newss_announcement.action')">公告板<s:if test="#request.myAnnounecementCount == 1"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td class="tab-sel">
          	<ticket:common type="myMessageCount"/>
          	<a href="javascript:location.replace('/airportm_myMessage.action')">私信<s:if test="#request.myMessageCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myEvaluationCount"/>
          	<a href="javascript:location.replace('/evaluation_myEvaluatePage.action')">我的评论<s:if test="#request.myEvaluationCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myFeedBackCount"/>
          	<a href="javascript:location.replace('/feedBack_myFeedBack.action')">我的反馈<s:if test="#request.myFeedBackCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
        </tr>
      </table>
    </div>  <hr color="#d8d8d8">
				<div class="mobile-main">
               
  
					<div class="msg_ls">
						<ticket:common type="myMessageList" var="messages"/>
						<s:if test="#request.messages.size() != 0">
							<s:iterator var="m" value="#request.messages">
								<div class="c_text mr40">
									<div class='head clearfix'>
										<span class='float-left'><i class='clock_icon float-left'></i><s:date name="#m.status.createTime" format="yyyy-MM-dd HH:mm"/>
										<s:if test="!#m.h5">
											(未读)
										</s:if>
										</span>
										<a href="javascript:return false;" class='float-right c_blue deleteMessage' message_id="${m.id }">删除</a>
									</div>
									<div class='body clearfix'>
										<ticket:commonAnnex type="annex" entityUuid="${m.massInfo_id}" annexType="1" size="1"/>
										<s:if test="#request.annex != null">
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="120" height="120">
										</s:if>
										${m.title }
										<hr/>
										<ticket:format value="${m.message }" size="40"/>
										<div class="text-right">
											<a href="/airportm_messageDetail.action?id=${m.id }" class='c_blue'>
											<i class='icon-chevron-circle-right margin-right'></i>查看详情</a>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="c_text mr40">
								暂无消息提醒
							</div>
						</s:else>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>