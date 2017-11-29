<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/advert.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100">
						<tr class="my_table_top1">
						    <td width="80">总的</td>
						    <td width="80">已使用</td>
						    <td width="80">剩余</td>
						</tr>
				  		<tr>
						    <td>${usage.total }${usage.unit }</td>
						    <td>${usage.used }${usage.unit }</td>
						    <td>${usage.free }${usage.unit }</td>
						 </tr>
					</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>