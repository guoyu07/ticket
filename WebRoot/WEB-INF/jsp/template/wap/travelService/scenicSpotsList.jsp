<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <link rel="stylesheet" href="/template/wap/css/jd.css">
    
    <style type="text/css">
    .jd-count{ padding-top:50px;}
	.mobile-jd-list ul li{ width:28% !important;}
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<ticket:common type="scenicSpotsList"/>
      		<div class="mobile-page">
    			<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
    				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
          			<div class="header"> 
          				<s:if test="#parameters.direct != null">
          					<a href="javascript:;" class='FL'>
	          					<i class="icon-navicon"></i>
	          				</a> 
          				</s:if>
          				<s:else>
          					<a href="${param.url == null ? 'javascript:;' : param.url }" class='FL'><i class="icon-angle-left"></i></a>
          				</s:else>
                        热门景点
          				<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
          			 		<i class="icon-bell"></i><ticket:common type="newMessages"/><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
          			  	</a><div class="jd-count">共<em><s:if test="#request.scenicSpotsList != null && #request.scenicSpotsList.size() > 0">${scenicSpotsList.size() }</s:if><s:else>0</s:else></em>个景点</div>
      				</div>
      				</s:if>
        		</div>
    
    			<!--------go 景点---------->
   				<div class="mobile-jd-list">
          			<ul>
          				<s:if test="#request.scenicSpotsList != null">
          					<s:iterator value="#request.scenicSpotsList" var="scenicSpots">
		        				<li>
		        					<a href="/airport_scenicSpotsDetail.action?id=${scenicSpots.id }">
			        					<ticket:commonAnnex type="queryAnnexList" entityUuid="${scenicSpots.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
											   	<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
											</s:iterator>
										</s:if>
			              				<h1>${scenicSpots.name }</h1>
			              				<p>${fn:substring(scenicSpots.introduce,0,10) }</p>
		              				</a>
		            			</li>
          					</s:iterator>
          				</s:if>
	      			</ul>
	        	</div>
        	</div>
        		<%@ include file="../common/quickNav.jsp"%>
    	</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>