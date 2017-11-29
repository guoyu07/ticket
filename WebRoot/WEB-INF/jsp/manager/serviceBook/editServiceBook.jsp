<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/serviceBook.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑服务订单</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									会员id：
								</td>
								<td>
									<input id="member_id" name="member_id" class="my_input" datatype="*"
									       value="${serviceBook.member_id}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写会员id</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身份证号：
								</td>
								<td>
									<input id="idCard" name="idCard" class="my_input" datatype="/^[1-9](\d{16}|\d{13})[0-9xX]$/"
									       value="${serviceBook.idCard}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"
									       value="${serviceBook.phone}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航班号：
								</td>
								<td>
									<input id="flightNumber" name="flightNumber" class="my_input" datatype="*"
									       value="${serviceBook.flightNumber}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写航班号</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									预订数量：
								</td>
								<td>
									<input id="bookAmount" name="bookAmount" class="my_input" datatype="n1-2"
									       value="${serviceBook.bookAmount}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写预订数量</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									使用时间：
								</td>
								<td>
									<input id="useTime" name="useTime" onclick="new WdatePicker();" class="my_input" datatype="*"
									       value="${serviceBook.useTime}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写使用时间</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									服务码：
								</td>
								<td>
									<input id="serviceKey" name="serviceKey" class="my_input" datatype="*"
									       value="${serviceBook.serviceKey}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写服务码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									付款状态：
								</td>
								<td>
									<select id="payStatus" name="payStatus" class="my_input" datatype="*"
									       currentValue="${serviceBook.payStatus}">
										<option value="1">已支付</option>
										<option value="0">未支付</option>       	
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写付款状态</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>