<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<ticket:common type="businessTypeObj" value="${businessInfo.businessType_id }" />
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="${businessTypeObj.name }"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="c_content">
						<div class="media media-x">
							<a class="float-left" href="#"> 
							<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
							 <img src="http://${image_server_url}${annex.annexPath}" width="120" height="120"> </a>
							<div class="media-body line">
								<div class="x12 fz24 c_black">
									${businessInfo.name }
								</div>
								<div class="x12 fz20 c_l_grey padding-top">
									主营：${businessInfo.mainSale}
								</div>
								<div class="x12 fz16 c_l_grey padding-big-top height-big">
									<font class='inline_block c_blue fz20'><i
										class='tel_icon float-left'></i><a href="tel:${businessInfo.phone }">电话:${businessInfo.phone }</a></font>
								</div>
							</div>
							<br>
							<hr>
							<div class="media-foot line">
								<s:if test="businessInfo.introduce != null && businessInfo.introduce !=''">
									<h3 class='fz24 c_black'>
										商家简介:
									</h3>
									<p class='fz24'>
										${businessInfo.introduce }
									</p><br>
								</s:if>
								<s:if test="businessInfo.activityForecast != null && businessInfo.activityForecast !=''">
									<h3 class='fz24 c_black'>
										活动预告:
									</h3>
									<p class='fz24' style="color: #FF0000">
										${businessInfo.activityForecast }
									</p><br>
								</s:if>
								<h3 class='fz24 c_black'>
									商家地址:
								</h3>
								<p class='fz24'>
									${businessInfo.address }
								</p>
								<br>
								<h3 class='fz24 c_black'>
									营业时间:
								</h3>
								<p class='fz24'>
									${businessInfo.businessHours }
								</p>
								<br>
								<h3 class='fz24 c_black'>
									友情链接
								</h3>
								<p class='fz24 height-large padding-bottom' style="color: #3399FF">
									<ticket:common type="businessLinkList" value="${businessInfo.id}"/>
									<s:if test="#request.businessLinkList != null">
										<s:iterator id="friendlyLink" value="#request.businessLinkList">
											<a href="${friendlyLink.url }" style="color: #3399FF">${friendlyLink.name }</a>&nbsp;&nbsp;
										</s:iterator>
									</s:if>
								</p>

							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="../common/quickNav.jsp"%>
		</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>