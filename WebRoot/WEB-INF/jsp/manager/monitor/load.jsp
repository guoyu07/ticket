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
						    <td width="80">一分钟内</td>
						    <td width="80">五分钟内</td>
						    <td width="80">十分钟内</td>
						</tr>
				  		<tr>
						    <td>${load.one }</td>
						    <td>${load.five }</td>
						    <td>${load.ten }</td>
						 </tr>
					</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>