<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/surveyOption.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增问题选项</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属问题：
								</td>
								<s:if test="surveyQuestionId != null">
									<td>
										<ticket:systemCommon type="surveyQuestionObj" value="${surveyQuestionId }"/>
										<label>${surveyQuestionObj.title }</label>
										<input name="surveyQuestionId" id="surveyQuestionId" type="hidden" value="${surveyQuestionObj.id }">
									</td>
								</s:if>
								<s:else>
									<td>
										<select name="surveyQuestionId" id="surveyQuestionId" class="my_select" ignore="ignore" datatype="*" >
										
										<option value="">请选择所属问题</option>
										<ticket:systemCommon type="surveyQuestionList" value="0"/>
										<s:if test="#request.surveyQuestionList != null">
											<s:iterator id="surveyQuestion" value="#request.surveyQuestionList">
											<option value="${surveyQuestion.id }">${surveyQuestion.title }</option>
											</s:iterator>
											</s:if>
										</select>
										<p class="Validform_checktip" style="display: inline;">
											请选择所属问题
										</p>
									</td>
								</s:else>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									选项编号：
								</td>
								<td>
									<select id="optionNo" name="optionNo" class="my_input">
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
										<option value="E">E</option>
										<option value="F">F</option>
										<option value="G">G</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写选项编号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									选项标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写选项标题</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input onclick="javascript:history.go(-1)" type="submit" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}&surveyQuestionId=${surveyQuestionId }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>