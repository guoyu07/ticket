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
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
						<tr class="my_table_top1">
							<td width="80">日期</td>
							<td width="80">服务人</td>
							<td width="80">服务码</td>
							<td width="80">办票服务</td>
							<td width="80">厅内服务</td>
						    <td width="80">安检服务</td>
						    <td width="80">送机服务</td>
						</tr>
						<s:iterator var="item" value="#list" status="st">
						<tr ${item[0] == id ? 'style="background-color: orange;"' : null}>
							<td>
								${item[1] }
							</td>
							<td>
								${item[2] }
							</td>
							<td>
								${item[3] }
							</td>
							<td>
								${item[4] }
							</td>
							<td>
								${item[5] }
							</td>
							<td>
								${item[6] }
							</td>
							<td>
								${item[7] }
							</td>
						</tr>
						</s:iterator>
					</table>
					<input id="submitBtn" type="submit" value="返回" class="btn_submit" style="margin: 20px;" onclick="JM.prevUrl();">
					<%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>