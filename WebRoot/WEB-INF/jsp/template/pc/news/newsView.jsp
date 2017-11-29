<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>${news.title} - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>

		<%@ include file="../common/nav.jsp" %>

		<%@ include file="../common/search.jsp" %>

		<div class="banner_25"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">新闻详细</a>
		</div place w980 mt30>

		<div class="other_article_article w980 mt30">
			<h2>
				${news.title}
			</h2>
			<div class="text_content">
				<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${news.id}" annexType="3" size="1" />
				<s:if test="#request.queryAnnexList != null && #request.queryAnnexList.size > 0">
					<ticket:commonAnnex type="annex" entityUuid="${news.id}" annexType="4" size="1" />
					<div id="newsViewVideoPlayer" pictureUrl="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}"
						videoUrl="${queryAnnexList[0].annexPath}" style="text-align: center">

					</div>
				</s:if>
				<s:if test="news.pcContent != null && news.pcContent != ''">
				${news.pcContent}
				</s:if>
				<s:else>
				${news.content}
				</s:else>
			</div>
		</div other_article_article w980 mt30>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
		<script type="text/javascript" src="/template/pc/js/swfobject.js"></script>
		<script type="text/javascript" src="/template/pc/js/newsView.js"></script>
	</body>
</html>
