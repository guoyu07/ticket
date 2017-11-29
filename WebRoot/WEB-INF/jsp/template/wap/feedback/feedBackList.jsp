<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/feedback/feedbacklist.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="反馈列表" name="title"/>
                </jsp:include>
                <div class="tab-notice">
					
       <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <td >
          	<ticket:common type="myAnnounecementCount"/>
          	<a href="javascript:location.replace('/airport/newss_announcement.action')">公告板<s:if test="#request.myAnnounecementCount == 1"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myMessageCount"/>
          	<a href="javascript:location.replace('/airportm_myMessage.action')">私信<s:if test="#request.myMessageCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myEvaluationCount"/>
          	<a href="javascript:location.replace('/evaluation_myEvaluatePage.action')">我的评论<s:if test="#request.myEvaluationCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td class="tab-sel">
          	<ticket:common type="myFeedBackCount"/>
          	<a href="javascript:location.replace('/feedBack_myFeedBack.action')">我的反馈<s:if test="#request.myFeedBackCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
        </tr>
      </table>
    </div>  <hr color="#d8d8d8">
				<div class="mobile-main">
              
					<div class="label_bar">
		                <div class="fz26">反馈统计（${count }）</div>
		            </div>
		        <s:iterator var="item" value="feedbacks">
				<div class="panel mr40 border10">
					<div class="panel-head fz22 padding-big-top padding-big-bottom">
						反馈时间：<s:date name="#item.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
						<ticket:common type="feedbackReplyObj" value="${item.id }"/>
						<s:if test="#request.feedbackReplyObj != null">
							<span style="color: blue;">已回复</span>
						</s:if>
						<%-- <a href="javascript:JM.alert('${item.feedback == null ? '管理员未回复' :  item.feedback}');" class='button bg-sub float-right'>查看管理员回复</a> --%>
					</div>
					<div class="panel-body fz22 padding-big-top padding-big-bottom">
						<div class="media media-x" onclick="detail(this);">
							<input type="hidden" value="${item.id }"/>
							<c:forEach var="image" items="${fn:split(item.images, ',') }">
								<c:if test="${image != null && image != '' ? true : false }">
									<a class="float-left" href="#">
										<img src="${image }" height="100" width="100">
									</a>
								</c:if>
							</c:forEach>
							<div class="media-body">
								${item.description }
							</div>
							<%-- <ticket:common type="feedbackReplyObjList" value="${item.id }"/> 
							<s:if test="#request.feedbackReplyObjList != null">
								<s:iterator value="#request.feedbackReplyObjList" var="feedbackReplyObj">
									<s:if test="#feedbackReplyObj.replyContent != null">
										<hr class="border-bottom"/>
										<span class="c_blue">管理员回复：</span>${feedbackReplyObj.replyContent }
										<button class="c_blue" onclick="zhuijiafankui(this);">追加反馈</button>
										<input type="hidden" value="${item.id }"/>
										<div class="media-body">
											管理员回复：${feedbackReplyObj.replyContent }
										</div>
									</s:if>
									<s:if test="#feedbackReplyObj.description != null">
										<hr class="border-bottom"/>
										<span class="c_blue">追加反馈：</span><br/>
										<c:forEach var="image" items="${fn:split(feedbackReplyObj.images, ',') }">
											<c:if test="${image != null && image != '' ? true : false }">
												<a class="float-left" href="#">
													<img src="${image }" height="100" width="100">
												</a>
											</c:if>
										</c:forEach>
										${feedbackReplyObj.description }
										<div class="media-body">
											追加反馈：${feedbackReplyObj.description }
										</div>
									</s:if>
								</s:iterator>
							</s:if> --%>
						</div>
					</div>
				</div>
				</s:iterator>
		            <!-- ajax加载 -->
					<!-- <div class="tit b_blue"><a id="nextPage" class="c_white" href="javascript:query()">加载更多反馈</a></div> -->
				</div>
				<div class="mobile-foot" style="height:130px;">
                    <div class="tit" onclick="location.href='feedBack_goToFeedBack.action'" style="cursor: pointer;">功能反馈</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/quickNav.jsp"%>
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>