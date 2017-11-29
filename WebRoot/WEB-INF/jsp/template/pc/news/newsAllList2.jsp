<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp"%>
		<title>${newsClass.name} - 云南省昆明市长水机场</title>
		<script type="text/javascript" src="/template/pc/js/door.js"></script>
	</head>
	<body>
		<%@ include file="../common/top.jsp"%>
		<%@ include file="../common/nav.jsp"%>
		<%@ include file="../common/search.jsp"%>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">${newsClass.name}</a>
		</div place w980 mt30>
		<div class="w980 mt30">
			<div class="FAQ_search bhh news_list">
				<div class="pic_text_ls">
					<s:iterator id="n" value="pageModule.pageList">
					<dl class="clearfix">
						<dt class='pic'>
							<a href="/airport/newsView/${n.id}.html">
							<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${n.id}" annexType="4" size="1" />
							<s:if test="#request.queryAnnexList != null">
								<img src="${queryAnnexList[0].annexPath}" height="88" width="129"/>
							</s:if>
							<s:else>
								<img src="/template/pc/image/temp/4.jpg" height="88" width="129"/>
							</s:else>
							</a>
						</dt>
						<dd>
							<a href="/airport/newsView/${n.id}.html"><ticket:format value="${n.title}" size="40"/></a>
						</dd>
						<dt>
							<ticket:format value="${n.introduce}" size="120"/>
						</dt>
					</dl>
					</s:iterator>
				</div>
				<s:if test="pageModule != null && pageModule.totalCount > 0">
				<div class="page_list">
					<ul>
						<pg:pager items="${pageModule.totalCount}" url=""
						maxIndexPages="5" maxPageItems="${pageSize}"
						export="currentPageNumber=pageNumber">
						<pg:param name="method" />
						<pg:first>
							<a href="${pageUrl}" ><li class="bhh">首页</li></a>
						</pg:first>
						<pg:prev>
							<a href="${pageUrl}" ><li class="bhh">上一页</li></a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber }">
									<a class="hover"><li class="bhh">${pageNumber}</li></a>
								</c:when>
								<c:otherwise>
									<a href="${pageUrl}"><li class="bhh">${pageNumber}</li></a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a href="${pageUrl}"><li class="bhh">下一页</li></a>
						</pg:next>
						<pg:last>
							<a href="${pageUrl}"><li class="bhh">末页</li></a>
						</pg:last>
						</pg:pager>
					</ul>
					
				</div page_list>
				</s:if>

			</div FAQ_search other_L bhh>
			<div id="Menu" class="MyMenu hh">
				<h2>
					${parentNewsClass.name}
				</h2>
				<ticket:common type="newsClassChilds" value="${parentNewsClass.alias}"/>
				<s:if test="#request.newsClassChilds != null && #request.newsClassChilds.size > 0">
				<s:iterator id="n" value="#request.newsClassChilds">
				<div>
					<span><a href="/airport/newsList/${n.id}.html">${n.name}</a>
					</span>
				</div>
				</s:iterator>
				</s:if>
				<ticket:common type="newsList" value="${parentNewsClass.id }" size="10000"/>
				<s:if test="#request.newsList != null">
				<s:iterator id="newsObj" value="#request.newsList">
					<s:if test="#newsObj.newsClass_id == parentNewsClass.id">
					<div>
						<span>
						<a href="/airport/newsView/${newsObj.status.visitUrl }.html">${newsObj.title }</a>
						</span>
					</div>
					</s:if>
				</s:iterator>
		</s:if>
			</div MyMenu hh>
		</div w980 mt30>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
