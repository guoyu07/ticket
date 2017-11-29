<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>长水百问 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="/pcBjdjMember_loginPensonCenter.action">个人中心</a> >
			<a href="javascript:;">${newsClass.name}</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="FAQ_search other_L bhh">
				<form id="newsSearchForm"  action="/airportPc_helpCenter.action" method="post">
				<input name="keyword" type="text" placeholder="请输入问题关键词" value="${keyword}" />
				<a href="javascript:$('#newsSearchForm').submit();"><img src="/template/pc/images/FAQ_search.gif" align="absmiddle" style="vertical-align:middle"  />
				</a>
				</form>
				<s:iterator id="n" value="pageModule.pageList" status="st">
				<dl>
					<dd>
						${st.count}.${n.title}
					</dd>
					<dt>
						答：${n.content}
					</dt>
				</dl>
				</s:iterator>

				<s:if test="pageModule != null && pageModule.totalCount > 0">
				<div class="page_list">
					<ul>
						<pg:pager items="${pageModule.totalCount}" url=""
						maxIndexPages="5" maxPageItems="${pageSize}"
						export="currentPageNumber=pageNumber">
						<pg:param name="method" />
						<pg:first>
							<a href="javascript:;" onclick="goToPage('${pageUrl}','${keyword}');" ><li class="bhh">首页</li></a>
						</pg:first>
						<pg:prev>
							<a href="javascript:;" onclick="goToPage('${pageUrl}','${keyword}');" ><li class="bhh">上一页</li></a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber eq pageNumber }">
									<a class="hover"><li class="bhh">${pageNumber}</li></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:;" onclick="goToPage('${pageUrl}','${keyword}');"><li class="bhh">${pageNumber}</li></a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a href="javascript:;" onclick="goToPage('${pageUrl}','${keyword}');"><li class="bhh">下一页</li></a>
						</pg:next>
						<pg:last>
							<a href="javascript:;" onclick="goToPage('${pageUrl}','${keyword}');"><li class="bhh">末页</li></a>
						</pg:last>
						</pg:pager>
					</ul>
					
				</div page_list>
				</s:if>
				<%@ include file="common/FAQButton.jsp" %>
			</div FAQ_search other_L bhh>

			<div id="Menu" class="MyMenu hh">
				<h2>
					${parentNewsClass.name}
				</h2>
				<s:if test="pageModule.pageList != null && pageModule.pageList.size > 0">
				<s:iterator id="n" value="pageModule.pageList">
				<div>
					<span><a href="/airport/newsView/${n.id}.html">${n.title}</a>
					</span>
				</div>
				</s:iterator>
				</s:if>
			</div MyMenu hh>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
		<script>
			function goToPage(url,keyword){
			window.location.href=url+"&keyword="+JM.encodeByValue(keyword);
			}
		</script>
	</body>
</html>
