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
						    <td width="80">用户时间</td>
						    <td width="80">系统时间</td>
						    <td width="80">系统调整</td>
						    <td width="80">空闲时间</td>
						    <td width="80">等待时间</td>
						    <td width="80">硬件中断处理时间</td>
						    <td width="80">软件中断处理时间</td>
						    <td width="80">丢失时间</td>
						</tr>
				  		<tr>
						    <td>${usage.userTime }%</td>
						    <td>${usage.systemTime }%</td>
						    <td>${usage.niceTime }%</td>
						    <td>${usage.idleTime }%</td>
						    <td>${usage.waitTime }%</td>
						    <td>${usage.hardIrqTime }%</td>
						    <td>${usage.softIrqTime }%</td>
						    <td>${usage.stealTime }%</td>
						 </tr>
					</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>