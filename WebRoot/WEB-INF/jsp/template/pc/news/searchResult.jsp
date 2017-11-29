<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<script type="text/javascript" src="/common/searchResult.js"></script>
		<title>全站搜索 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>

		<%@ include file="../common/nav.jsp" %>

		<%@ include file="../common/search.jsp" %>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">全站搜索</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="FAQ_search other_L bhh">
				<input type="hidden" id="newsClass_id" value="${newsClass.id }"/>
				<input type="hidden" id="countId" value="${id }"/>
				<form id="newsSearchForm"  action="/airportPc_commonSearch.action" method="post">
				<input name="keyword" id="keyword" type="text" placeholder="请输入问题关键词" value="${keyword}" />
				<a href="javascript:$('#newsSearchForm').submit();"><img src="/template/pc/images/FAQ_search.gif" align="absmiddle" />
				</a>
				</form>
				<s:if test="searchList != null">
				<s:iterator id="searchObj" value="searchList" status="st">
				<dl>
					<dd>
						<a href="javascript:;" attrValue="${searchObj.pcUrl }" onclick="havaUrl(this);"><ticket:format value="${searchObj.title }" size="40"/></a>
					</dd>
				</dl>
				</s:iterator>
				</s:if>
				<s:else>
					<dl>
						<dd>
							<a href="javascript:;" attrValue="http://m.baidu.com/s?word=${commonKeyword }" onclick="goBaidu(this);"><span></span>
								亲，本站没有搜到相关信息！点击<font style="font-weight: bold;color:#2319DC;">百度搜索</font>查看结果
							</a>
						</dd>
					</dl>
				</s:else>
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
