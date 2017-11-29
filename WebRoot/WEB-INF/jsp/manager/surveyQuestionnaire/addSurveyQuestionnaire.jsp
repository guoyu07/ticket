<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/surveyQuestionnaire.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增调查问卷</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									问卷标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问卷标题</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问卷描述：
								</td>
								<td>
									<input id="descript" name="descript" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问卷描述</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问卷调查模块：
								</td>
								<td>
									<select name="survryModularId" id="survryModularId" class="my_select" ignore="ignore" datatype="*" >
									
									<option value="">请选择问卷调查模块</option>
									<ticket:systemCommon type="survryModularList" value="0"/>
									<s:if test="#request.survryModularList != null">
										<s:iterator id="survryModular" value="#request.survryModularList">
											<option value="${survryModular.id }">${survryModular.name }</option>
										</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请选择问卷调查模块
									</p>
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问卷截止日期：
								</td>
								<td>
									<input id="deadLine" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" name="deadLine" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写截止日期</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
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