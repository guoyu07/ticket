<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/manager/js/bjdjServiceCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理服务码表</td>
					    </tr>
						  <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_assignmentManage.action?versionFlag=site" method="get">
								服务码:<input type="text" name="start"/>
									&nbsp;&nbsp;~&nbsp;&nbsp;
									<input type="text" name="end"/><br/>
							  	创建时间:<input name="startTime" type="text" onclick="new WdatePicker();"/>
							  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endTime" type="text" onclick="new WdatePicker();"/>
							  			<br/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
						  	</form>
						  	<script type="text/javascript">
						  		$(function(){
						  			
						  			$('input[name="start"]').val("${param.start }");
						  			$('input[name="end"]').val("${param.end }");
						  			$('input[name="startTime"]').val("${param.startTime }");
						  			$('input[name="endTime"]').val("${param.endTime }");
						  		});
						  	</script>
						  </td></tr>
					    <tr>
					      <td>
							<table id="table" class="my_table100">
							  <tr class="my_table_top1">
							    <td width="150">服务码</td>
							    <td width="100">类型</td>
							    <td width="100">状态</td>
							    <td width="100">创建时间</td>
							    <td width="100">
							    	分类
							    	<a href='/${actionName}_edit.action<s:url value="" includeParams="get"></s:url>'>全部分类</a>
							    </td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
									    <td>${c.code }</td>
									    <td>${c.type.name }</td>
									    <td>${c.state.value }</td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									    <td>
									    	<s:if test="#c.type == null">
									    		<a href="/${actionName}_edit.action?id=${c.id }">分类</a>
									    	</s:if>
									    </td>
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