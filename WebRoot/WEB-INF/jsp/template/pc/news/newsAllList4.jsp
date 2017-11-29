<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>问询台 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>

		<%@ include file="../common/nav.jsp" %>

		<%@ include file="../common/search.jsp" %>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">问询台</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="FAQ_search other_L bhh">
				<s:iterator id="n" value="pageModule.pageList" status="st">
				<dl>
					<dt>
						<s:if test="#n.pcContent != null && #n.pcContent != ''">
						${n.pcContent}
						</s:if>
						<s:else>
						${n.content}
						</s:else>
					</dt>
				</dl>
				</s:iterator>
				<%@ include file="../common/FAQButton.jsp" %>
			</div FAQ_search other_L bhh>
			<%@ include file="../common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
		<script>
			function goToPage(url,keyword){
			window.location.href=url+"&keyword="+JM.encodeByValue(keyword);
			}
		</script>
	</body>
</html>
