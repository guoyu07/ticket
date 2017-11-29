<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/electromobile/index.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	            	<jsp:param value="电瓶车服务" name="title"/>
	            </jsp:include>
		        <div class="mobile-main">
		            <div class="pic">
		            	<ticket:common type="advertListNew" value="电瓶车首页" size="10"/>
						<s:if test="#request.advertListNew != null">
							<s:iterator id="advert" value="#request.advertListNew">
								<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
								<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}">
							</s:iterator>
						</s:if>
		            </div>
		            <%-- <div class="msg mr40 fz18">
		            	<h2>温馨提示：</h2>
		            	${electromobile_index_tip.description }
		            	<span class='clearfix padding-bottom'>
		            		<a href="/airport/1447915879112.ticket" class="float-right text-dot">查看详情</a>
		            	</span>
		            </div> --%>
		            <div class="c_content">
		            	<s:if test="#parameters.airport != null">
		            		<s:set name="systemDicObjByValue" value="dictionaryService.getByValue('dianpingchejieshao_airport')"></s:set>
		            	</s:if>
		            	<s:else>
		            		<s:set name="systemDicObjByValue" value="dictionaryService.getByValue('dianpingchejieshao_love')"></s:set>
		            	</s:else>
						${systemDicObjByValue.description }
		            </div>
		            <%-- <div class="c_content text-center no-border clearfix" style="padding:0px 0px;">
		                <span class='float-left c_red fz22'>￥：${electromobile_price }</span>
		                <label class='float-right'>
		                	<input id="agree" type="checkbox" name="agree" value="agree">
		                	已阅读并同意 
		                	<a href="/airport/1447916241347.ticket"><span class='c_blue'>免责条款</span></a>
		                </label>
		            </div> --%>
		            
		            
		            <!-- <form id="confirmForm" action="electromobile_confirmPage.action" method="post" style="display: none;">
		            	<input id="submitBtn" type="submit" value="" />
		            	<input type="hidden" name="type" value="electromobile" />
		            </form> -->
		            <!--<a href="javascript:submit()"><div class="tit b_blue c_white">立即购买</div></a>-->
		            <br>
		            <s:set var="info" value="infoPositionService.queryByName('电瓶车', 'site')"></s:set>
		            <dd class='padding-bottom fz22' style="width: 80%;margin-left: 10%">
		            	电瓶车乘车点
		            	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
			                <a href="http://api.map.baidu.com/marker?location=${info.latitude },${info.longitude }&title=${info.name }&content=电瓶车服务&output=html" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
		            	</s:if>
		            	<s:else>
		            		<a href="/airport_daohang.action?longitude=102.935932&latitude=25.103555&name=电瓶车&floorNumber=F3" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
		            	</s:else>
		            </dd>
		            <%-- <a href="/airport_daohang.action?longitude=${info.longitude }&latitude=${info.latitude }&name=${info.name }&floorNumber=${info.floorNumber}"><div class="tit b_blue c_white">导航</div></a> --%>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>