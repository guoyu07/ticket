<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/convientBording/purchase.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="支付成功" name="title"/>
                </jsp:include>
				<div class="mobile-main fz24">
					<div class="c_content text-center fz30">
						<s:if test="#request.order != null">
						<p class='padding-top'>服务名称：${order.name }</p>
						<hr>
						<s:iterator value="codes" var="code">
						<p class='padding-top'>服务码：${code}</p>
						</s:iterator>
						</s:if>
						<s:else>
						<p class='padding-top'>支付成功</p>
						</s:else>
					</div>
					<s:if test="#request.order != null">
						<a class="tit" style="display: block; margin-bottom::50px;" href="bjdjServiceCodeActivate_page.action?orderId=${order.id }">立即激活</a>
					</s:if>
					<s:else>
						<a class="tit" style="display: block;margin-bottom:50px;" href="bjdjServiceCodeActivate_page.action">立即激活</a>
					</s:else>
					<s:if test="#request.order != null">
					<p class='mr40'>您的服务码已发送到${order.mobile}手机上，请查收谢谢！</p>
					</s:if>
					<div class="mrlr40">
						<div class="msg">
							<h1 class='c_red padding-big-bottom'>
								<i class='icon-heart'></i>&nbsp;&nbsp;温馨提示：
							</h1>
							${pay_success_tip }
						</div>
					</div>
					<div class="tit_tab">
						<s:if test="#buttonHelps != null">
							<s:iterator value="#buttonHelps" var="button">
								<s:if test="#button.buttonName == '个人中心'">
									<s:if test="#session.fromApp == null && #parameters.fromApp == null">
										<a href="#" class='b_yello c_grey personalCenterBtn'>${button.buttonName }</a> 
						        	</s:if>
						        	<s:else>
										<%-- <a href="/airport_appPersonnalCenter.action" class='b_yello c_grey'>${button.buttonName }</a>  --%>
						        		<a href="${button.buttonUrl }" class='b_yello c_grey'>${button.buttonName }</a>
						        	</s:else>
					        	</s:if>
					        	<s:elseif test="#button.buttonName == '专车服务'">
					        		<s:if test="#session.fromApp != null || #parameters.fromApp != null">
								    	<a href="app_going.action" class='b_yello c_grey'>${button.buttonName }</a>
								    </s:if>
								    <s:else>
								    	<a href="${button.buttonUrl }" class='b_yello c_grey'>${button.buttonName }</a>
								    </s:else>
								</s:elseif>
								<s:else>
									<a href="${button.buttonUrl }" class='b_yello c_grey'>${button.buttonName }</a>
								</s:else>
				        	</s:iterator>
						</s:if>
						<s:else>
							<s:if test="#session.fromApp == null && #parameters.fromApp == null">
								<a href="#" class='b_yello c_grey personalCenterBtn'>个人中心</a> 
						    </s:if>
						    <s:else>
						        <a href="airport_appPersonnalCenter.action" class='b_yello c_grey'>个人中心</a>
						    </s:else>
						    <s:if test="#session.fromApp != null || #parameters.fromApp != null">
						    	<a href="app_going.action" class='b_l_grey c_grey'>专车服务</a>
						    </s:if>
						    <s:else>
						    	<a href="#" class='b_l_grey c_grey'>专车服务</a>
						    </s:else>
						</s:else>
					</div>
				</div>
				<div class="mobile-foot">
					<p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN
						AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="saturation" style="display:none;">
			<div class="box_dialog">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class='txt txt-small radius-circle bg-red'><i
							class='icon-exclamation c_white'></i></span>&nbsp;&nbsp;服务码激活休息厅此预约时段已饱和
					</h2>
					<hr>
					<div class="media media-x">
						<div class="media-body fz18">
							您好！您预约的此时段，机场休息厅已饱和，是否同意放弃进厅服务，只享受此套餐内其他服务项目</div>
					</div>
				</div>
				<div class="tit_tab">
					<a id="queueWait" href="javascript:queueWait('${order.id}')" class=''>排队等候</a>
					<a id="agree" href="javascript:closeSaturation()" class='b_l_grey c_grey'>同意</a>
				</div>
			</div>
		</div>
		<div class="wait" style="display:none;">
			<div class="box_dialog">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class='txt txt-small radius-circle bg-red'>
						<i class='icon-exclamation c_white'></i></span>&nbsp;&nbsp;服务码激活-选择排除等候
					</h2>
					<hr>
					<div class="media media-x">
						<div class="media-body fz18">
							您好！感谢您对我们的支持，目前还有
							<font id="waitNumber"></font>
							人在您前面排队系统已自动帮您排队中，预约成功之后会以短信的形式通知您！
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
		<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>