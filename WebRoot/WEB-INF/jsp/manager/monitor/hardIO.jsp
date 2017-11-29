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
						    <td width="80">分区</td>
						    <td width="80">每秒处理的IO次数</td>
						    <td width="80">每秒从设备读取的数据量</td>
						    <td width="80">每秒从设备写的数据量</td>
						    <td width="80">读取的数据总量</td>
						    <td width="80">写入的数据总量</td>
						</tr>
						<s:iterator var="io" value="ios">
				  		<tr>
						    <td>${io.deivceName }</td>
						    <td>${io.tps }</td>
						    <td>${io.readPs }${io.unit }/s</td>
						    <td>${io.writePs }${io.unit }/s</td>
						    <td>${io.read }${io.unit }</td>
						    <td>${io.write }${io.unit }</td>
						 </tr>
						 </s:iterator>
					</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>