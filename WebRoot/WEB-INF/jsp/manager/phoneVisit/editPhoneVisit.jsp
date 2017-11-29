<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/phoneVisit.js"></script>
	<body>
		<div class="site_warp">
			o<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑电话回访记录</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									出访人：
								</td>
								<td>
									<ticket:systemCommon type="employeeInfoObj" value="${phoneVisit.employee_id}"/>
									${employeeInfoObj.name}
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									回访日期：
								</td>
								<td>
									<input id="visitDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="visitDate" class="my_input" datatype="*"
									       value="<s:date name="phoneVisit.visitDate" format="yyyy-MM-dd HH:mm:ss"/>"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写回访日期</p>       
								</td>
							</tr>
						
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input id="companyName" name="companyName" class="my_input" datatype="*"
									       value="${phoneVisit.companyName}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系人：
								</td>
								<td>
									<input id="contact" name="contact" class="my_input" datatype="*"
									       value="${phoneVisit.contact}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系人</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="contactPhone" name="contactPhone" class="my_input" datatype="m"
									       value="${phoneVisit.contactPhone}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									沟通内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*" style="width: 100%;height: 500px;">${phoneVisit.content}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写沟通内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									下次沟通日期：
								</td>
								<td>
									<input id="nextVisitDate" onclick="WdatePicker();" name="nextVisitDate" class="my_input" datatype="*"
									       value="<s:date name="phoneVisit.nextVisitDate" format="yyyy-MM-dd"/>"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写下次沟通日期</p>       
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