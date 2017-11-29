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
						<span>新增领取记录</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									选择物品：
								</td>
								<td>
									<s:if test="lostGoods_id != null && lostGoods_id !=''">
										<input type="hidden" name="lostGoods_id" id="lostGoods_id" value="${lostGoods_id}" />
										<ticket:systemCommon type="lostGoodsInfoObj" value="${lostGoods_id }"/>
										${lostGoodsInfoObj.name }
									</s:if>
									<s:else>
										<select id="lostGoods_id" name="lostGoods_id" class="my_input" datatype="*">
											<option value="">请选择物品</option>
										</select>
										<p class="Validform_checktip" style="display:inline;"> 请填写选择物品</p>
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									领取人姓名：
								</td>
								<td>
									<input id="personName" name="personName" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写领取人姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									领取时间：
								</td>
								<td>
									<input id="receiveTime" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" name="receiveTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写领取时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									证件类型：
								</td>
								<td>
									<select id="certificateType" name="certificateType" class="my_select" datatype="*">
										<option value="">请选择证件类型</option>
										<ticket:systemCommon type="certificateTypeList"/>
										<s:if test="#request.certificateTypeList != null">
											<s:iterator id="certificateType" value="#request.certificateTypeList">
												<option value="${certificateType.id }">${certificateType.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写证件类型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									证件号码：
								</td>
								<td>
									<input id="receiveCertificateNumber" name="receiveCertificateNumber" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写证件号码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									领取人电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写领取人电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									领取方式：
								</td>
								<td>
									<select id="receiveWay" name="receiveWay" class="my_select" datatype="*">
										<option value="">请选择领取方式</option>
										<ticket:systemCommon type="receiveWayList"/>
										<s:if test="#request.receiveWayList != null">
											<s:iterator id="receiveWay" value="#request.receiveWayList">
												<option value="${receiveWay.id }">${receiveWay.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写领取方式</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									出库人：
								</td>
								<td>
									<input id="writeOffPerson" name="writeOffPerson" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写出库人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									出库时间：
								</td>
								<td>
									<input id="writeOffTime" name="writeOffTime" class="my_input" datatype="*" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写出库时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									发放人：
								</td>
								<td>
									<input id="putOutPerson" name="putOutPerson" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写发放人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									发放时间：
								</td>
								<td>
									<input id="putOutTime" name="putOutTime" class="my_input" datatype="*" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写发放时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea id="remark" name="remark" class="my_input" ignore="ignore" style="width: 80%;height: 280px">
										
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/lostGoodsInfo_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>