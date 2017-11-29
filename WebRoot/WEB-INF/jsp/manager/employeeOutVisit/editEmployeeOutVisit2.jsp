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
						<span>编辑员工外出拜访记录</span>
					</div>
					<form action="/${actionName}_edit2.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									返回时间：
								</td>
								<td>
									<input id="backTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="backTime" class="my_input" datatype="*"
									       value="<s:date name="employeeOutVisit.backTime" format="yyyy-MM-dd HH:mm:ss"/>"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写返回时间</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拜访结果：
								</td>
								<td>
									<textarea id="visitResult" name="visitResult" class="my_input" datatype="*">${employeeOutVisit.visitResult }</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写拜访结果</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拜访状态：
								</td>
								<td>
									<select name="state" id="state">
										<option <s:if test="employeeOutVisit.state == 0">selected</s:if> value ="0">进行中</option>
										<option <s:if test="employeeOutVisit.state == 1">selected</s:if> value ="1">拜访完成(结束)</option>
									</select> 
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
		<script>
			$(function (){
				jQuery(function(){
					visitResult = createEditor('visitResult');
				});
			});
		</script>
	</body>
</html>