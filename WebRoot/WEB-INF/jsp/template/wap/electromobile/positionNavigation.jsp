<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>	
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zfTIKtNx9zBgxLIwpAOt28dE"></script>
	<script type="text/javascript" src="/template/wap/js/electromobile/positionNavigation.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
		            <div class="header">
		                <s:if test="#session.fromApp != null">
							<a onclick="comeBack();" class="FLAPP" ><i class="icon-navicon"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:return_prepage();" class="FL" id="comeback"><i class="icon-angle-left"></i></a>
						</s:else>
		                乘车点导航
		                <a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<i class="icon-bell"></i>
							<ticket:common type="newMessages"/>
							<s:if test="#request.newMessages > 0">
								<span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
							</s:if>
						</a>
		            </div>
		        </div>
		        <div class="mobile-main">
		            <div class="c_img mr40" style="text-align: center">
		                <div id="newsLocation" style="height: 240px;"></div>
		                <dl class='mrlr40 c_black fz20' style="padding-top:30px;">
		                    <dt class='border-bottom padding-bottom margin-bottom'>
		                    	<span class="c_l_grey">当前位置：</span><span>昆明长水机场G18值柜台</span>
		                    </dt>
		                    <dt class='border-bottom padding-bottom margin-bottom'>
		                    	<span>昆明长水机场G18值柜台</span><p class="fz16 c_l_grey">昆明长水机场1号航站楼二层</p>
		                    </dt>
		                    <dt class='padding-top clearfix height-large'>
		                        <a href="" class="x6 text-center">
		                        	<font class='inline_block'>
		                        		<i class='map_icon float-left'></i>&nbsp;&nbsp;到这去
		                        	</font> 
		                        	<span class='float-right c_l_grey'>|</span>
		                        </a>
		                        <a href="" class="x6 text-center">
		                        	<font class='inline_block'>
		                        		<i class='go_hear_icon float-left'></i>&nbsp;&nbsp;到这去
		                        	</font>
		                        </a>
		                    </dt>
		                </dl>
		            </div>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>