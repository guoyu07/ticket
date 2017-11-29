<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/agreement.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<form action="${actionName }_togetherReport.action" method="get" style="text-align: center; margin-top: 110px;">
					  	开始日期:<input name="startDate" type="text" onclick="new WdatePicker();"/>
					  	结束日期:<input name="endDate" type="text" onclick="new WdatePicker();"/>
					  	<br/>
					  	<br/>
			  			<input type="submit" value="查询"/>
			  			<input type="reset" value="重置"/>
				  	</form>
				  	<script type="text/javascript">
				  		$(function(){
				  			
				  			//初始化分页查询的条件值
				  			$('input[name="startDate"]').val("${param.startDate }");
				  			$('input[name="endDate"]').val("${param.endDate }");
				  		});
				  	</script>
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >充款汇总表</td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="100">充款客户数</td>
								    <td width="100">充款金额</td>
								    <td width="100">开票数</td>
								    <td width="100">开票金额</td>
								  </tr>
								  	<tr>
								  		<td><a href="${actionName }_detailsReport.action?keyword=all&startDate=${param.startDate }&endDate=${param.endDate }">${count[0] }</a></td>
								  		<td><a href="${actionName }_detailsReport.action?keyword=all&startDate=${param.startDate }&endDate=${param.endDate }">${count[1] }</a></td>
								  		<td><a href="${actionName }_detailsReport.action?keyword=ticket&startDate=${param.startDate }&endDate=${param.endDate }">${count[2] }</a></td>
								  		<td><a href="${actionName }_detailsReport.action?keyword=ticket&startDate=${param.startDate }&endDate=${param.endDate }">${count[3] }</a></td>
									</tr>
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