<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/letterStation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理站内信</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="120">标题</td>
							    <td width="30">是否发送</td>
							    <td width="80" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd hh:mm:ss"/></td>
									    <td>${c.title}</td>
									    <td><s:if test="#c.isSend == 0">未发送</s:if><s:else>已发送</s:else></td>
									    <td><span>
									    	<s:if test="#c.isSend == 0">
										    	<a href="javascript:;" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<a href="/${actionName}_send.action?id=${c.id }&versionFlag=${versionFlag}">发送</a>
									    	</s:if>
									    	<s:else>
										    	<a href="/${actionName}_statistic.action?id=${c.id }&versionFlag=${versionFlag}">统计</a>
									    	</s:else>
									    	<a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">站内信详情</a>
									    	</span></td>
									 </tr>
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