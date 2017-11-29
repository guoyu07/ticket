<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>购买服务码</span>
					</div>
					<form action="/buyServiceCode_buy.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									
									<tr>
										<td class="text_align_right" width="150">
											购买数量：
										</td>
										<td>
											<input id="number" name="number" class="my_input" datatype="n"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写购买数量</p>
										</td>
									</tr>
									<%--<tr>
										<td class="text_align_right" width="150">
											支付方式：
										</td>
										<td>
											<input id="payWay" name="payWay" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付方式</p>
										</td>
									</tr>
									
						--%></table>
						<div class="my_table_list_l">
						    <input id="submitBtn" type="submit" value="确认充值" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/bjdjService_add.buy?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/bjdjServiceCodeLog_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>