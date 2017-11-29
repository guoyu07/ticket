<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/feedback/feedbacklist.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="反馈问题详情" name="title"/>
                </jsp:include>
				<div class="mobile-main">
				<div class="panel mr40 border10">
					<div class="panel-head fz22 padding-big-top padding-big-bottom">
						反馈时间：<s:date name="feedback.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
					</div>
					<div class="panel-body fz22 padding-big-top padding-big-bottom">
						<div class="media media-x">
							<c:forEach var="image" items="${fn:split(feedback.images, ',') }">
								<c:if test="${image != null && image != '' ? true : false }">
									<a class="float-left" href="#">
										<img src="${image }" height="100" width="100">
									</a>
								</c:if>
							</c:forEach>
							<div class="media-body">
								${feedback.description }
							</div>
							<ticket:common type="feedbackReplyObjList" value="${feedback.id }"/> 
							<s:if test="#request.feedbackReplyObjList != null">
								<s:iterator value="#request.feedbackReplyObjList" var="feedbackReplyObj">
									<s:if test="#feedbackReplyObj.replyContent != null">
										<hr class="border-bottom"/>
										<span class="c_blue">管理员回复：</span>${feedbackReplyObj.replyContent }
										<!-- <button class="c_blue" onclick="zhuijiafankui(this);">追加反馈</button> -->
										<%-- <input type="hidden" value="${feedback.id }"/> --%>
										<%-- <div class="media-body">
											管理员回复：${feedbackReplyObj.replyContent }
										</div> --%>
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
										<%-- <div class="media-body">
											追加反馈：${feedbackReplyObj.description }
										</div> --%>
									</s:if>
								</s:iterator>
							</s:if>
						</div>
					</div>
				</div>
				</div>
				<div class="mobile-foot" style="height:130px;">
                    <div class="tit"><a href="/feedBackReply_addFeedback.action?feedback_id=${feedback.id }">追加反馈</a></div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/quickNav.jsp"%>
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>