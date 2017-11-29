<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <link rel="stylesheet" href="/template/wap/css/jd.css">
        <style type="text/css">
    

.arc_list ul li h1{text-overflow: ellipsis; width: 614px;}
    
    </style>
	<body class="mobile">
		<div class="mobile-view">
      			<div class="mobile-top" >
          			<div class="header" <c:if test="${! empty fromApp }">style="margin-top : -90px;"</c:if>>
	          	 		<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a> 
	          	 		${scenicSpots.name }
	          	 		<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn"> <i class="icon-bell"></i><ticket:common type="newMessages"/><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if> </a>
      				</div>
        		</div>
   
    			<!--------go 景点---------->
    			<div class="mobile-jd-arc">
          			<div class="jd-arc-top">
          				<ticket:commonAnnex type="queryAnnexList" entityUuid="${scenicSpots.id}" annexType="1" size="1"/>
							<s:if test="#request.queryAnnexList != null">
								<s:iterator id="annex" value="#request.queryAnnexList" status="st">
									<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
								</s:iterator>
							</s:if>
          				<div class="jd-arc-top-title">${scenicSpots.name }</div>
         
          			</div> 
           			<img width="100%" alt=" " src="/template/wap/images/top_shw.jpg" style="margin-top: -4px; position:absolute; z-index:888;">
          			<div class="jd-arc-c">
          				${scenicSpots.introduce }
          			</div>
          			<a href="#"><img src="/template/wap/images/jd_05.jpg" alt=" " class="img_arc_t"></a>
          			<div class="arc_list">
        				<ul>
        					<ticket:common type="scenicSpotsNewsList" value="${scenicSpots.id }"/>
        					<s:if test="#request.scenicSpotsNewsList != null">
        						<s:iterator value="#request.scenicSpotsNewsList" var="news">
		              				<li> <a href="/airport/${news.status.visitUrl }.ticket">
		              					<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="img_arc1">
											</s:iterator>
										</s:if>
		                				<h1>${news.introduce }</h1>
		                				<h2> <!-- <img src="images/icon_1.jpg" alt=" ">76372 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --><img src="/template/wap/images/icon_2.jpg" alt=" ">&nbsp;<s:date name="#news.status.createTime" format="yyyy-MM-dd"/></h2>
		                				</a> 
		                			</li>
	                			</s:iterator>
                			</s:if>
            			</ul>
      				</div>	
          			<div  class="notice_ft" style="display:none;"><i class="icon-arrow-circle-up"></i>点击加载更多 </div>
				</div>
        	<%@ include file="../common/quickNav.jsp"%>
    	</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>