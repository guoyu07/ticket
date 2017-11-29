<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>评价中心 - 云南省昆明市长水机场</title>
	</head>
	<body>
	<s:if test="#parameters.iframe == null">
		<%@ include file="common/top.jsp" %>
		<%@ include file="common/nav.jsp" %>
		
		<div class="place w980 mt30">
			当前位置: <a href="/airportPc.action">首页</a> > <a href="#">评价列表</a>
			<div style="background-color:#0099FF;  float:right; margin-right:10px; text-align:center; color:#FFFFFF;" >
			<a href="/pCEvaluation_evaluationPage.action" class='button bg-sub' style="float: right; color:#FFFFFF; width:100px; height:30px;text-align:center; padding-top:10px;">评论</a>
			</div>
		</div place w980 mt30>
		
		<div class="w980 mt30">
			<div class="FAQ_search bhh news_list" style="width:980px;">
		        <div class="text_ls">
		        	<s:if test="pageModule != null && pageModule.totalCount > 0">
						<s:iterator id="c" value="pageModule.pageList" status="st">
				    		<dl style="width:920px;">
				    			<dd>
				    				<small><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></small>
				    				<a href="#"><font color="#0066FF">用户名：${fn:substring(c.member.phone, 0, 3) }****${fn:substring(c.member.phone, 7, 11) }</font></a>
				    			</dd>
				    			<dt>
				    				${c.content }
				    				<c:forEach var="image" items="${fn:split(c.images, ',') }">
										<c:if test="${image != null && image != '' ? true : false }">
											<a href="${image }" target="_self" class="padding-top inline_block">
												<img height="100" width="100" src="${image }"/>
											</a>
										</c:if>
									</c:forEach>
									<br />
				    				<s:if test="#c.feedback != null">
				    					<font color="#FF0000">管理员回复：${c.feedback }</font>
				    				</s:if>
				    				<s:else>
					    				<s:set var="process" value="evaluationService.getProcess(#c)"></s:set>
							    		<s:if test="#process != null">
								    		<font color="#FF0000">管理员处理信息：${process[0].msg }</font>
							    		</s:if>
				    				</s:else>
				    			</dt>
				    		</dl>
		    			</s:iterator>
		    		</s:if>
		        </div>
				<%@ include file="common/page.jsp"%>
			</div FAQ_search other_L bhh>
		</div w980 mt30>
		<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>		
	</s:if>
	<s:else>
		<div class="mt30">
			<div class="FAQ_search bhh news_list">
		        <div class="text_ls">
		        	<s:if test="pageModule != null && pageModule.totalCount > 0">
						<s:iterator id="c" value="pageModule.pageList" status="st">
				    		<dl>
				    			<dd>
				    				<small><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></small>
				    				<a href="#"><font color="#0066FF">用户名：${fn:substring(c.member.phone, 0, 3) }****${fn:substring(c.member.phone, 7, 11) }</font></a>
				    			</dd>
				    			<dt>
				    				${c.content }
				    				<c:forEach var="image" items="${fn:split(c.images, ',') }">
										<c:if test="${image != null && image != '' ? true : false }">
											<a href="${image }" target="_self" class="padding-top inline_block">
												<img height="100" width="100" src="${image }"/>
											</a>
										</c:if>
									</c:forEach>
									<br />
				    				<s:if test="#c.feedback != null">
				    					<font color="#FF0000">管理员回复：${c.feedback }</font>
				    				</s:if>
				    				<s:else>
					    				<s:set var="process" value="evaluationService.getProcess(#c)"></s:set>
							    		<s:if test="#process != null">
								    		<font color="#FF0000">管理员处理信息：${process[0].msg }</font>
							    		</s:if>
				    				</s:else>
				    			</dt>
				    		</dl>
		    			</s:iterator>
		    		</s:if>
		        </div>
				<%@ include file="common/page.jsp"%>
			</div FAQ_search other_L bhh>
		</div mt30>
	</s:else>
	</body>
</html>

