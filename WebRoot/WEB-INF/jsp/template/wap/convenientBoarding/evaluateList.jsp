<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<% String bjdjPage_id = (String) request.getParameter("bjdjPage_id"); %>
	<ticket:cache group="便捷登机">
	<script type="text/javascript">
		var methodName = 'list';
	</script>
    <style type="text/css">
    .ft_more{ position:fixed !important;}
    </style>
	<script type="text/javascript" src="/template/wap/js/convientBording/evaluateList.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="评价列表" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<input type="hidden" value="<%=bjdjPage_id %>" id="bjdjPage_id"/>
					<%-- <div class="label_bar">
		                <div class="fz26">评价统计（${count }）<br><br></div>
		                <s:iterator value="keywords" var="keyword">
		                <a href="#" class="b_grey c_grey">${keyword }</a>
		                </s:iterator>
		            </div> --%>
					<div class="tit"><a id="nextPage" href="javascript:nextPage()">加载更多评论</a></div>
				</div>
				<div class="mobile-foot">
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>