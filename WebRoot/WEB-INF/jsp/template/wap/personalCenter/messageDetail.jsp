<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="消息提醒" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="notice_ls">
						<h4 class='line'>
							<div class="x3">
							<ticket:common type="messageDetail" value="${id }" var="message"/>
							<ticket:commonAnnex type="annex" entityUuid="${message.massInfo_id}" annexType="1" size="1"/>
								<s:if test="#request.annex != null">
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="120" height="120">
								</s:if>
							</div>
							<div class="x9 padding-left">
								${message.title }
								<p>
									提醒时间：<s:date name="#request.message.status.createTime" format="yyyy-MM-dd HH:mm"/>
								</p>
							</div>
						</h4>
						<div class="text_content">
							${message.message }
							<br/>
							<s:if test="message.url != null">
								<s:if test="message.url.startsWith('http')">
									<a href="${message.url }">${message.url }</a>
								</s:if>
								<s:else>
									<a href="http://${message.url }">${message.url }</a>
								</s:else>
							</s:if>
							<s:if test="flightDate != null">
								<div class="text-right">
									<a href="airport_queryByFlightNoAndDate.action?flightNumber=${message.flightNumber }&flightDate=${flightDate }" class='c_blue'>
										<i class='icon-chevron-circle-right margin-right'></i>查看航班
									</a>
								</div>
							</s:if>
						</div>
						<h3 class="notice_ft_ls">
							<ul>
								<li>
									<ticket:common type="prevMessage" msg="${message}"/>
									<s:if test="#request.prevMessage != null">
										<a href="javascript:location.replace('/airportm_messageDetail.action?id=${prevMessage.id }')"><span>上一则：</span>
										<ticket:format value="${prevMessage.title}" size="20"/></a>
									</s:if>
									<s:else>
										<a><span>上一则：</span><font>没有了</font></a>
									</s:else>
								</li>
								<li>
									<ticket:common type="nextMessage" msg="${message}"/>
									<s:if test="#request.nextMessage != null">
										<a href="javascript:location.replace('/airportm_messageDetail.action?id=${nextMessage.id }')"><span>下一则：</span>
										<ticket:format value="${nextMessage.title}" size="20"/></a>
									</s:if>
									<s:else>
										<a><span>下一则：</span><font>没有了</font></a>
									</s:else>
								</li>
							</ul>
						</h3>
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
