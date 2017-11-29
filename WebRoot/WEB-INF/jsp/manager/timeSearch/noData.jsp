<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/timeSearch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理搜索统计</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manageNoDate.action?versionFlag=site" method="post">
							  	日期:<input name="date" type="text" onclick="new WdatePicker();"/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
						  	</form>
						  	<script type="text/javascript">
						  		$(function(){
						  			$('input[name="date"]').val("${param.date }");
						  		});
						  	</script>
						  </td></tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<%-- <a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a> --%>
					      	<a href="/timeSearch_manage.action?versionFlag=site">有结果搜索关键词</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="50">编号</td>
								    <td width="80">日期</td>
								    <td width="80">页面名称</td>
								    <td width="80">落地页链接</td>
								    <td width="80">展现量</td>
								    <td width="80">点击量</td>
								    <td width="80">触发展现的搜索词</td>
								    <td width="80">触发点击的搜索词</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.definition.pageName }</td>
										    <td>${c.goUrl }</td>
										    <td>${c.showRate }</td>
										    <td>${c.clickRate }</td>
										    <td>${c.showKeyword }</td>
										    <td>${c.clickKeyword }</td>
										    <td><span>
										    	<%-- <a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a> --%>
										    	</span>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="100">${noDataMessage }</td>
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