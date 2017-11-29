<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/pageCacheKey.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理页面缓存key管理</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="refreshAllBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="refresh">刷新所有</a>
					      	<a href="#" class="batchRefreshBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="refresh">批量刷新</a>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20">编号</td>
								    <td width="80">所属组</td>
								    <td width="80">键名</td>
								    <td width="80">缓存范围</td>
								    <td width="80">缓存时间</td>
								    <td width="80">缓存表达式</td>
								    <td width="80">备注</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.group.groupName }</td>
										    <td>${c.keyName }</td>
										    <td>${c.scope }</td>
										    <td>${c.time }</td>
										    <td>${c.cron }</td>
										    <td>${c.remarks }</td>
										    <td><span>
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="javascript:refresh('${c.id }')">刷新</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="70">${noDataMessage }</td>
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