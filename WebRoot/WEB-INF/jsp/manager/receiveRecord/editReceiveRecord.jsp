<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/receiveRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑领取记录</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											物品id：
										</td>
										<td>
											<input id="lostGoods_id" name="lostGoods_id" class="my_input" datatype="*"
											       value="${receiveRecord.lostGoods_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写物品id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											领取人姓名：
										</td>
										<td>
											<input id="personName" name="personName" class="my_input" datatype="*"
											       value="${receiveRecord.personName}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写领取人姓名</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											领取时间：
										</td>
										<td>
											<input id="receiveTime" name="receiveTime" class="my_input" datatype="*"
											       value="${receiveRecord.receiveTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写领取时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											证件类型：
										</td>
										<td>
											<input id="certificateType" name="certificateType" class="my_input" datatype="*"
											       value="${receiveRecord.certificateType}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写证件类型</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											证件号码：
										</td>
										<td>
											<input id="receiveCertificateNumber" name="receiveCertificateNumber" class="my_input" datatype="*"
											       value="${receiveRecord.receiveCertificateNumber}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写证件号码</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											领取人电话：
										</td>
										<td>
											<input id="phone" name="phone" class="my_input" datatype="*"
											       value="${receiveRecord.phone}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写领取人电话</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											领取方式：
										</td>
										<td>
											<input id="receiveWay" name="receiveWay" class="my_input" datatype="*"
											       value="${receiveRecord.receiveWay}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写领取方式</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											 出库人：
										</td>
										<td>
											<input id="writeOffPerson" name="writeOffPerson" class="my_input" datatype="*"
											       value="${receiveRecord.writeOffPerson}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写出库人</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											 出库时间：
										</td>
										<td>
											<input id="" name="" class="my_input" datatype="*"
											       value="${receiveRecord.writeOffTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写出库人</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											发放人：
										</td>
										<td>
											<input id="putOutPerson" name="putOutPerson" class="my_input" datatype="*"
											       value="${receiveRecord.putOutPerson}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写发放人</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											发放时间：
										</td>
										<td>
											<input id="" name="" class="my_input" datatype="*"
											       value="${receiveRecord.putOutTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写发放时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											备注：
										</td>
										<td>
											<input id="remark" name="remark" class="my_input" datatype="*"
											       value="${receiveRecord.remark}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>       
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