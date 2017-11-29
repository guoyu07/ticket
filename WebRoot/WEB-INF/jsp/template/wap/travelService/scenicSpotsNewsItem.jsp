<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<s:if test="newsList != null && newsList.size() > 0">
	<ul>
	<s:iterator value="newsList" var="news">
		<li> 
			<a href="/airport/${news.status.visitUrl }.ticket">
				<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
				<s:if test="#request.queryAnnexList != null">
					<s:iterator id="annex" value="#request.queryAnnexList" status="st">
						<img id="2" src="<%-- <%=request.getScheme() %>://${image_server_url} --%>${annex.annexPath }" class="img_arc1">
					</s:iterator>
				</s:if>
				<!-- <img src="/template/wap/images/jd_06.jpg" alt=" " class="img_arc1"> -->
				<h1>${news.introduce }</h1>
				<h2> 
					<!-- <img src="/template/wap/images/icon_1.jpg" alt=" ">76372 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
					<img src="/template/wap/images/icon_2.jpg" alt=" ">&nbsp;<s:date name="#news.status.createTime" format="yyyy-MM-dd"/>
				</h2>
			</a> 
		</li>
	</s:iterator>
	</ul>
</s:if>