<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<script type="text/javascript" src="/template/pc/js/flightNotice.js"></script>
		<title>乘机须知页面 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_27"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">乘机须知页面</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="CJXZ_door other_L bhh">
				<ul id="tb8_">
					<li id="tb8_1" class="hovertab_8" onclick="x:HoverLi8(1);">
						安全检查须知
					</li>
					<li id="tb8_2" class="normaltab_8" onclick="x:HoverLi8(2);">
						乘机时间须知
					</li>
					<li id="tb8_3" class="normaltab_8" onclick="x:HoverLi8(3);">
						边防检查须知
					</li>
					<li id="tb8_4" class="normaltab_8" onclick="x:HoverLi8(4);">
						旅客通关须知
					</li>
					<li id="tb8_5" class="normaltab_8" onclick="x:HoverLi8(5);">
						检验检疫须知
					</li>
					<li id="tb8_6" class="normaltab_8" onclick="x:HoverLi8(6);"
						style="width: 128px; border-right: none;">
						72小时过境迁
					</li>
				</ul tb8_>

				<h2 class="dis" id="tbc8_01">
					<ticket:common type="newsCommonObj" value="0f36d340-bb9b-4943-8d1b-5b6d712f590d"/>
					${newsCommonObj.content}
				</h2>
				<h2 class="undis" id="tbc8_02">
					2
				</h2>
				<h2 class="undis" id="tbc8_03">
					<ticket:common type="newsCommonObj" value="18b5e85a-8168-4ea8-89ff-b8e7ede60a4f"/>
					${newsCommonObj.content}
				</h2>
				<h2 class="undis" id="tbc8_04">
					<ticket:common type="newsCommonObj" value="4924b63a-9cbc-416a-aced-c8bf05a4f566"/>
					${newsCommonObj.content}
				</h2>
				<h2 class="undis" id="tbc8_05">
					<ticket:common type="newsCommonObj" value="cd254366-0e12-40d4-9197-c109537ebbb5"/>
					${newsCommonObj.content}
				</h2>
				<h2 class="undis" id="tbc8_06">
					<ticket:common type="newsCommonObj" value="b0d3c59c-a671-4f7e-9291-ed7eceef77ea"/>
					${newsCommonObj.content}
				</h2>
				<%@ include file="common/FAQButton.jsp" %>
			</div CJXZ_door other_L bhh>

			<%@ include file="common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
