<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/keyWordLocation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td>管理关键词定位</td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="20">编号</td>
								    <td width="80">关键词</td>
								    <td width="80" colspan="4" align="center">页面</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<s:set var="pages" value="keyWordLocationPageService.query(#c.id)" />
								  		<s:set var="size" value="#pages.size()" />
								  		<s:if test="#size > 0">
								  		<s:iterator id="kp" value="#pages" status="st2">
								  		<tr>
								  			<s:if test="#st2.first">
										    <td rowspan="${size == 0 ? 1 : size }">${st.count }</td>
										    <td rowspan="${size == 0 ? 1 : size }"><ticket:format value="${c.keyword }" size="40"/></td>
										    </s:if>
								  			<td width="80">${kp.page.name }</td>
								  			<td width="80">${kp.page.url }</td>
								  			<td width="80">${kp.page.pcUrl }</td>
										    <td width="40">
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}Page" value="${kp.id }">删除页面</a>
										    </td>
									  		<s:if test="#st2.first">
										    <td rowspan="${size == 0 ? 1 : size }">
										    	<span>
											    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
											    	<a href="/${actionName}Page_add.action?id=${c.id }&versionFlag=${versionFlag}">增加页面</a>
											    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	</span>
										    </td>
										    </s:if>
										</tr>
								  		</s:iterator>
								  		</s:if>
								  		<s:else>
								  		<tr>
										    <td>${st.count }</td>
										    <td><ticket:format value="${c.keyword }" size="40"/></td>
								  			<td width="80"></td>
								  			<td width="80"></td>
								  			<td width="80"></td>
										    <td width="40"></td>
										    <td>
										    	<span>
											    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
											    	<a href="/${actionName}Page_add.action?id=${c.id }&versionFlag=${versionFlag}">增加页面</a>
											    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	</span>
										    </td>
										</tr>
								  		</s:else>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="7">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					        </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>