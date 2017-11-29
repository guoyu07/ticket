<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/donationServiceCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>分销服务码</span>
					</div>
					<form action="/channelCustomerAdmin_donationBjdjServiceCode.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" value="1" />
						<input type="hidden" name="id" value="${id }" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									姓名：
								</td>
								<td>
									<input name="username" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									手机号：
								</td>
								<td>
									<input name="phone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写手机号</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input type="submit" value="确认购买" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="manageLink" href="/channelCustomerAdmin_myBjdjServiceCode.action"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>