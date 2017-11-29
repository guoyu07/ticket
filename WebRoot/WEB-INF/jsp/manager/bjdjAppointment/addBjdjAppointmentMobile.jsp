<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjOrder.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>电话预约</span>
					</div>
					<form action="/${actionName}_addForMobile.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											身份证：
										</td>
										<td>
											<input id="IDCard" name="IDCard" class="my_input" datatype="/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											旅客姓名：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写旅客姓名</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											电话号码：
										</td>
										<td>
											<input id="mobile" name="mobile" class="my_input" datatype="/1[0-9]{10}/"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写电话号码</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											套餐类型：
										</td>
										<td>
											<select name="packageType" id="package">
												<s:iterator var="p" value="servicePackageService.queryAll(@com.ticket.pojo.BjdjServicePackage@class)">
												<option price="${p.price }" value="${p.id }" description="${p.description }">${p.name }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请填写电话号码</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班号：
										</td>
										<td>
											<input id="flightNo" name="flightNo" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班起飞时间：
										</td>
										<td>
											<input id="useTime" name="useTime" class="my_input" datatype="*" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写登机时间</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											行李：
										</td>
										<td>
											<textarea id="luggage" name="luggage" class="my_input" datatype="*" ignore="ignore"></textarea>
											<p class="Validform_checktip" style="display:inline;"> 请填写行李描述</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_addForMobile.action"></a>
						    <a id="manageLink" href="/${actionName}_manage.action"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>