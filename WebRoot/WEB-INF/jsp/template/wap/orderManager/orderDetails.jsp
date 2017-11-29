<%@page import="com.ticket.constants.ContextConstants"%>
<%@page import="com.ticket.pojo.Member"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	Member me = (Member)session.getAttribute(ContextConstants.SCOPE_MEMBER);
%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript"src="/template/wap/js/DimensionCode.js"></script>
	<script type="text/javascript"src="/template/wap/js/convientBording/activate.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="订单详情" name="title" />
				</jsp:include>
				<div class="mobile-main">
					<div class="mr40">
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>订单名称：</em><span class='c_l_grey'>${order.name }</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>订单号：</em><span class='c_l_grey'>${order.number }</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>状态：</em>
									<span class='c_blue'>${order.state.value }</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>总价：</em><span class='c_red'>￥${order.price *
										order.count }</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>数量：</em><span class='c_l_grey'>${order.count }</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>手机号：</em><span class='c_l_grey'>${order.mobile }</span>
								</label>
							</dd>
						</dl>
					</div>
					<div class="table-responsive border fz20">
						<table class="table height-large">
							<tbody>
								<tr>
									<th class='b_l_grey'>
										服务码
									</th>
									<th class='b_l_grey'>
										过期时间
									</th>
									<th class='b_l_grey'>
										状态
									</th>
									<th class='b_l_grey'>
										信息
									</th>
								</tr>
								<s:iterator value="codes" var="c">
									<tr>
										<td>
											${c.code }
											<input type="hidden" value="${c.twoDimensionCodePath }"/>
											<button class="button b_blue c_white float-right">
												二维码
											</button>
										</td>
										<td>
											<s:date name="#c.expireTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class='c_blue'>
											<s:if
												test="#c.expireTime.getTime() < @java.lang.System@currentTimeMillis()">
		                                		已过期
		                                	</s:if>
											<s:else>
		                                		${c.state.value }
		                                	</s:else>
										</td>
										<td class='c_blue'>
											<s:if test="!serviceCodeService.isMine(#c)">
		                                		已赠送给：
		                                		<s:if
													test="#c.member.realName == null || #c.member.realName.trim() == ''">
		                                			${c.member.phone }
		                                		</s:if>
												<s:else>
		                                			${c.member.realName }
		                                		</s:else>
											</s:if>
											<s:elseif test="serviceCodeService.canComment(#c)">
												<ticket:common type="bjdjPageObj" value="${orderId }"/>
												<s:if test='#request.bjdjPageObj != null && #request.bjdjPageObj.url == "#"'>
													<a href="bjdjCommentTemplate_page.action?serviceCode_id=${c.id }"
														class="button fz20 margin-bottom block text-center">评价</a>
												</s:if>
												<s:else>
													<a href="bjdjCommentTemplate_page.action?serviceCode_id=${c.id }&bjdjPage_id=${bjdjPageObj.id }"
														class="button fz20 margin-bottom block text-center">评价</a>
												</s:else>
											</s:elseif>
											<s:elseif test="#c.state.name == 'expired' || #c.expireTime.getTime() < @java.lang.System@currentTimeMillis()">
												<a href="bjdjOrderTemplate_serviceCodeDelayPage.action?codeId=${c.id }"
													class="button fz20 margin-bottom block text-center">延期</a>
											</s:elseif>
											<s:elseif test="#c.state.name == 'activated'">
												<a href="javascript:unactived('${c.id }');"
													class="button fz20 margin-bottom block text-center">取消激活</a>
											</s:elseif>
											<s:set var="info" value="infoPositionService.queryByAlias('bianjiedengjifuwutai')"></s:set>
											<a href="/airport_daohang.action?longitude=${info.longitude }&latitude=${info.latitude }&name=${info.name }&floorNumber=${info.floorNumber}"
												class="button fz20 margin-bottom block text-center">导航</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display:none;" id="dialog">
			<div class="check_dialog box_dialog">
				<div class="c_content b_white">
					<div class="tit_tab">
						<img src="" id="img1">
					</div>
				</div>
			</div>
	</div>
	<div class="dialog" style="display: none;">
		<%@ include file="../common/left.jsp"%>
	</div>
	</body>
</html>