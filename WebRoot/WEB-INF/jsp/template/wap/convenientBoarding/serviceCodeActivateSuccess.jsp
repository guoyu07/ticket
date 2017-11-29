<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <style type="text/css">
    .ft_more{ bottom:0px !important;}
    </style>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="激活成功" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <div class="c_content mr40">
		                <div class="success_icon"></div>
		                <br><br>
		                <p class='text-center fz24'>您的服务码已成功激活，便捷登机服务已预约成功！</p>
		            </div>
					<div class="mrlr40">
	                	<div class="msg">
	                		<h1 class='c_red padding-big-bottom'><i class='icon-heart'>
	                		</i>&nbsp;&nbsp;温馨提示：
	                		</h1>
	                		${activate_success_tip }
						</div>
					</div>
		            <div class="tit_tab">
		            	<s:if test="#buttonHelps != null">
		            		<s:iterator value="#buttonHelps" var="button">
		            			<s:if test="#button.buttonType == 1">
					                <%-- <a href="airport_checkinServiceNotice.action" class='b_yello c_grey'>网上值机</a>
					                <s:set var="position" value="infoPositionService.queryByName('商务中心', 'site')"></s:set>
					                <a href="airport_daohang.action?longitude=${position.longitude }&latitude=${position.latitude }&name=<%=URLEncoder.encode("商务中心", "UTF-8") %>&floorNumber=${position.floorNumber }" 
					                	class='b_yello c_grey'>导航到服务柜台</a> --%>
					                <a href="${button.buttonUrl }" ${button.buttonClass }>${button.buttonName }</a>
				                </s:if>
			                </s:iterator>
		                </s:if>
		            </div>
		            <div class="line mr40">
		            	<s:if test="#buttonHelps != null">
		            		<s:iterator value="#buttonHelps" var="button">
		            			<s:if test="#button.buttonType == 2">
		            				  <div class="x6">
		            				  	<button onclick="javascript:window.location='${button.buttonUrl}'" 
					                		${button.buttonClass }>${button.buttonName }</button>
		            				  </div>
		            			</s:if>
		            		</s:iterator>
		            	</s:if>
		                <!-- <div class="x6">
		                	<button onclick="javascript:window.location='bjdjServiceCodeActivate_page.action'" 
		                		class='b_orange c_white height-big button d_button block' 
		                		style='border:0px;border-radius:0px;width:99%'>继续激活</button>
		                </div>
		                <div class="x6">
		                	<button onclick="javascript:window.location='bjdj.action'" 
		                		class='b_blue c_white height-big button d_button block'  
		                		style='border:0px;border-radius:0px;'>便捷登机首页</button>
		                </div> -->
		            </div>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
	        <%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>