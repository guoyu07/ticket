<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="机票预订" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <div class="searchForm mr40">
		                <label class='button'><i class='icon-search'></i><input type="text" name="" value="" placeholder='(目的地)'></label>
		                <button class="button bg-sub">搜索</button>
		            </div>
		            <div class="area_ls mrlr40">
		            <dl>
		                <dt>A</dt>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		            </dl>
		            <dl>
		                <dt>A</dt>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		                <dd>阿坝</dd>
		            </dl>
		            </div>
		        </div>
	            <%@ include file="../common/quickNav.jsp"%>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>