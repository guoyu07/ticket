<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="收藏页面" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<ticket:common type="memberFavoriteList"/>
					<s:if test="#request.memberFavoriteList != null">
						<s:iterator id="memberFavorite" value="#request.memberFavoriteList">
							<s:if test="#memberFavorite.objectType == 'NewsClass'">
								<ticket:common type="newsClassObj" value="${memberFavorite.objectId }"/>
								<div class="c_content clearfix fz20">
									<span class="toFavoritePage" newsClassAlias="${newsClassObj.alias }">${newsClassObj.name }</span>
									<a href="javascript:unFavorite('${memberFavorite.id }')" class='fz20 float-right'><i
									 class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
								</div>
							</s:if>
							<s:elseif test="#memberFavorite.objectType == 'News'">
								<ticket:common type="newsCommonObj" value="${memberFavorite.objectId }"/>
								<div class="c_content clearfix fz20">
									<span class="toFavoritePage" visitUrl="${newsCommonObj.status.visitUrl }">${newsCommonObj.title }</span>
									<a href="javascript:unFavorite('${memberFavorite.id }')" class='fz20 float-right'><i
									 class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
								</div>
							</s:elseif>
							<s:else>
								<div class="c_content clearfix fz20 ">
									<span class="toFavoritePage" url="${memberFavorite.url }">${memberFavorite.title }</span>
									<a href="javascript:unFavorite('${memberFavorite.id }')" class='fz20 float-right'><i
										class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
								</div>
							</s:else>
						</s:iterator>
					</s:if>
					<s:else>
						<div class="c_content clearfix fz20 toFavoritePage">
					 		${noDataMessage }
					 	</div>
					</s:else>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>