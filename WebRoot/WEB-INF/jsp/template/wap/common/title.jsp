<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
	<div class="header">
		<c:choose>
		<c:when test="${!empty param.direct }">
			<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
		</c:when>
		<c:otherwise>
			<a href="${empty param.url ? 'javascript:;' : param.url }" class='FL'><i class="icon-angle-left"></i></a>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${! empty param.title }">
			${param.title }
		</c:when>
		<c:when test="${! empty news }">
			${news.title }
		</c:when>
		<c:when test="${! empty newsClass }">
			${newsClass.name }
		</c:when>
		</c:choose>
		<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
			<c:choose>
				<c:when test="${!empty sessionMember }">
					<ticket:common type="newMessages"/>
					<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
				</c:when>
				<c:otherwise>
					<ticket:common type="myAnnounecementCount"/>
					<i class="icon-bell"></i><c:if test="${myAnnounecementCount == 1 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
				</c:otherwise>
			</c:choose>
			<%-- <ticket:common type="myAnnounecementCount"/>
			<i class="icon-bell"></i><c:if test="${myAnnounecementCount == 1 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if> --%>
		</a>
	</div>
	<div class="header_search">
		<div class="searchForm">
			<label class='button'>
				<i class='icon-search c_l_grey'></i>
				<!-- 用于相等时的判断跳转逻辑 -->
				<input type="hidden" name="keyword2" id="keyword2" value="<ticket:encoder encode="false" value="${commonKeyword }"/>">
				<input type="text" name="keyword" id="keyword" value="<ticket:encoder encode="false" value="${commonKeyword }"/>" placeholder='你要搜索的内容'>
			</label>
			<button class="button bg-sub" id="commonSearchBtn">
				搜索
			</button>
		</div>
	</div>
</div>