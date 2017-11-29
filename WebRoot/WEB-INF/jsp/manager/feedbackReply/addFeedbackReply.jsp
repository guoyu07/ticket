<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/feedbackReply.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>回复反馈</span>
					</div>
					<form action="/feedbackReply_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="feedback_id" id="feedback_id" value="${feedback.id }"/>
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											反馈时间：
										</td>
										<td>
											<p><s:date name="#feedback.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></p>
											<%-- <input class="my_input" 
												value="<s:date name="#feedback.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>"/> --%>
											<!-- <p class="Validform_checktip" style="display:inline;"> 请填写反馈时间</p> -->
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											反馈人员：
										</td>
										<td>
											<p>
												<s:if test="#feedback.member.nickName != null">
													${feedback.member.nickName }
												</s:if>
												<s:else>
													${feedback.member.phone }
												</s:else>
											</p>
											<%-- <input class="my_input"
												value="${feedback.member.phone }" /> --%>
											<!-- <p class="Validform_checktip" style="display:inline;"> 请填写反馈人员</p> -->
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题描述：
										</td>
										<td>
											<%-- <textarea id="description" name="description" class="my_input" disabled="disabled">${feedback.description }
											</textarea> --%>
											<p>${feedback.description }</p>
											<%-- input  class="my_input"
												value="${feedback.description }"/> --%>
											<!-- <p class="Validform_checktip" style="display:inline;"> 请填写问题描述</p> -->
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题图片：
										</td>
										<td>
											<s:if test="#feedback.images != null">
												<img alt="" src="${feedback.images }" width="200px" height="200px">
											</s:if>
											<!-- <input id="description" name="description" class="my_input" datatype="*"
												value="" disabled="disabled"/> -->
											<!-- <p class="Validform_checktip" style="display:inline;"> 请填写问题描述</p> -->
										</td>
									</tr>
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
									<!-- <tr>
										<td class="text_align_right" width="150">
											客服人员：
										</td>
										<td>
											<input id="systemUser" name="systemUser" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写客服人员</p>
										</td>
									</tr> -->
									<tr>
										<td class="text_align_right" width="150">
											回复内容：
										</td>
										<td>
											<input id="replyContent" name="replyContent" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写回复内容</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/feedbackReply_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/feedback_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>