<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<!-- <script type="text/javascript" src="/manager/js/realTimeData.js"></script> -->
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					  <tr class="my_table_top1">
					  	<td width="45">大厅</td>
					  	<td width="70">总容量</td>
					    <td width="50">占位数</td>
					    <td width="80">剩余座位</td>
					  </tr>
					  	<s:iterator value="#list" var="row" status="st">
					  		<s:if test="!#st.last">
						  		<tr>
								    <td>${row[0] }</td>
								    <td>${row[1] }</td>
								    <td>${row[2] }</td>
								    <td>${row[3] }</td>
								</tr>
							</s:if>
					  		<s:else>
						  		<tr class="my_table_top1">
								    <td>${row[0] }</td>
								    <td>${row[1] }</td>
								    <td>${row[2] }</td>
								    <td>${row[3] }</td>
								</tr>
							</s:else>
					  	</s:iterator>
					</table>
				  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>