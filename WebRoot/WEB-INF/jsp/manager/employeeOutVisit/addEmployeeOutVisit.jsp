<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/employeeOutVisit.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增员工外出拜访记录</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input type="hidden" value="${channelCustomerInfo.id }" id="customer_id" name="customer_id"/>
									<label>${channelCustomerInfo.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拜访日期：
								</td>
								<td>
									<input id="visitDate" onclick="WdatePicker();" name="visitDate" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写拜访日期</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input value="${channelCustomerInfo.name}" id="companyName" name="companyName" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系人：
								</td>
								<td>
									<input id="contact" name="contact" class="my_input" value="${channelCustomerInfo.contact}" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="contactPhone" name="contactPhone" class="my_input" value="${channelCustomerInfo.contactPhone}" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拜访目的：
								</td>
								<td>
									<textarea id="visitPurpose" name="visitPurpose" class="my_input" datatype="*"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写拜访目的</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									陪同人：
								</td>
								<td>
									<input id="accompanyPerPerson" name="accompanyPerPerson" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写陪同人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									出发时间：
								</td>
								<td>
									<input id="setoutTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="setoutTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写出发时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									返回时间：
								</td>
								<td>
									<input id="backTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="backTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写返回时间</p>
								</td>
							</tr> 
							<tr>
								<td class="text_align_right" width="150">
									拜访结果：
								</td>
								<td>
									<textarea id="visitResult" name="visitResult" class="my_input" style="width: 100%;height: 500px;" datatype="*"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写拜访结果</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?customer_id=${channelCustomerInfo.id }&versionFlag=${versionFlag}&state=${state}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>