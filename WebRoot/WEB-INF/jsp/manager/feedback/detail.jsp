<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/feedback.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>反馈详细信息</span>
					</div>
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											反馈人员：
										</td>
										<td>
											<p>
											<s:if test="feedback.member.nickName != null">
												${feedback.member.nickName}
											</s:if>
											<s:else>
												${feedback.member.phone}
											</s:else>
											</p>
											<%-- <input id="member" name="member" class="my_input" datatype="*"
											       value="${feedback.member}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写反馈人员</p>        --%>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题描述：
										</td>
										<td>
											<p>${feedback.description}</p>
											<%-- <input id="description" name="description" class="my_input" datatype="*"
											       value="${feedback.description}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问题描述</p> --%>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											手机号码：
										</td>
										<td>
											<p>${feedback.phone}</p>
											<%-- <input id="phone" name="phone" class="my_input" datatype="*"
											       value="${feedback.phone}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写手机号码</p> --%>       
										</td>
									</tr>
									<%-- <s:if test="#feedback.images != null"> --%>
									<tr>
										<td class="text_align_right" width="150">
											图片：
										</td>
										<td>
											<c:forEach var="image" items="${fn:split(feedback.images, ',') }">
												<c:if test="${image != null && image != '' ? true : false }">
													<img src="${image }" height="200" width="200">
												</c:if>
											</c:forEach>
											<%-- <input id="phone" name="phone" class="my_input" datatype="*"
											       value="${feedback.phone}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写手机号码</p> --%>       
										</td>
									</tr>
									<%-- </s:if> --%>
									<ticket:common type="feedbackReplyObjList" value="${feedback.id }"/>
									<s:if test="#request.feedbackReplyObjList != null">
										<s:iterator value="#request.feedbackReplyObjList" var="feedbackReplyObj">
											<s:if test="#feedbackReplyObj.description != null">
												<tr>
													<td class="text_align_right" width="150">
														追加反馈：
													</td>
													<td>
														<c:forEach var="image" items="${fn:split(feedbackReplyObj.images, ',') }">
															<c:if test="${image != null && image != '' ? true : false }">
																<img src="${image }" height="200" width="200">
															</c:if>
														</c:forEach>
														<p>${feedbackReplyObj.description }</p>
													</td>
												</tr>
											</s:if>
											<s:if test="#feedbackReplyObj.systemUser != null">
												<tr>
													<td class="text_align_right" width="150">
														回复人员：
													</td>
													<td>
														<p>
															<s:if test="#feedbackReplyObj.systemUser.name != null">
																${feedbackReplyObj.systemUser.name }
															</s:if>
															<s:else>
																${feedbackReplyObj.systemUser.phone }
															</s:else>
														</p>
													</td>
												</tr>
											</s:if>
											<s:if test="#feedbackReplyObj.replyContent != null">
												<tr>
													<td class="text_align_right" width="150">
														回复内容：
													</td>
													<td>
														<p>${feedbackReplyObj.replyContent }</p>
													</td>
												</tr>
											</s:if>
										</s:iterator>
									</s:if>
									<tr>
										<td colspan="2">
										  <a href="/feedback_manage.action?versionFlag=site">返回</a>
										</td>
									</tr>
						</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>