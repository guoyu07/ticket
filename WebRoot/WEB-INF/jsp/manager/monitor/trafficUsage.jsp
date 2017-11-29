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
						    <td width="80">网卡名称</td>
						    <td width="80">每秒接收的数据包量</td>
						    <td width="80">每秒发出的数据包量</td>
						    <td width="80">每秒接收的字节数</td>
						    <td width="80">每秒发送的字节数</td>
						    <td width="80">每秒收到的压缩包的数量</td>
						    <td width="80">每秒发送的压缩包数量</td>
						    <td width="80">每秒收到广播包数量</td>
						</tr>
						<s:iterator var="usage" value="usages">
				  		<tr>
						    <td>${usage.cardName }</td>
						    <td>${usage.rxpcks }/s</td>
						    <td>${usage.txpcks }/s</td>
						    <td>${usage.rxKB }kb/s</td>
						    <td>${usage.txKB }kb/s</td>
						    <td>${usage.rxcmp }/s</td>
						    <td>${usage.txcmp }/s</td>
						    <td>${usage.rxmcst }/s</td>
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