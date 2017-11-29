<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<s:if test="#request.newsList != null">
	<s:iterator id="news" value="#request.newsList">
		<dl class="clearfix">
			<dt>
				<a href="/airport/${news.status.visitUrl }.ticket">
					<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
					<s:if test="#request.queryAnnexList != null">
						<s:iterator id="annex" value="#request.queryAnnexList" status="st">
							<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="78" width="121">
						</s:iterator>
					</s:if>
				</a>
			</dt>
			<a href="/airport/${news.status.visitUrl }.ticket">
				<dd>
					<h6>
						${news.title }
					</h6>
					<p>
						${news.subTitle }
					</p>
				</dd>
			</a>
		</dl>
	</s:iterator>
</s:if>

