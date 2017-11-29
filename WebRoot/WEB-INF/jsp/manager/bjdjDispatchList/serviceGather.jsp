<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatchList.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<form action="${actionName }_serviceGather.action" method="get" style="text-align: center; margin-top: 110px;">
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
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
						<tr class="my_table_top1">
							<td width="80">日期</td>
							<td width="80">服务人</td>
							<s:iterator var="item" value="serviceItemService.queryAll(@com.ticket.pojo.BjdjServiceItem@class)">
							<td width="80">${item.name }</td>
							</s:iterator>
						</tr>
						<s:if test="">
						<s:iterator var="item" value="#list" status="st">
						<tr>
							<td>
								${item[1] }
							</td>
							<td>
								${item[2] }
							</td>
							<c:forEach begin="3" items="${item }" var="serviceItem">
							<td>
								<a href="bjdjDispatchList_serviceDetail.action?id=${item[0] }&startDate=${param.startDate }&endDate=${param.endDate }">${serviceItem }</a>
							</td>
							</c:forEach>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td colspan="19">请选择合适的时间段，才能查询出数据</td>
							</tr>
						</s:else>
					</table>
					<%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>