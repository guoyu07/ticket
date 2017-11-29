<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/surveyQuestion.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增调查问题 </span>
						
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属问卷：
								</td>
								<s:if test="surveyQuestionnaireId != null">
									<td>
										<ticket:systemCommon type="surveyQuestionnaireObj" value="${surveyQuestionnaireId }"/>
										<label>${surveyQuestionnaireObj.title }</label>
										<input type="hidden" name="surveyQuestionnaireId" id="surveyQuestionnaireId" value="${surveyQuestionnaireObj.id }" />
									</td>
								</s:if>
								<s:else>
									<td>
									<select name="surveyQuestionnaireId" id="surveyQuestionnaireId" class="my_select" ignore="ignore" datatype="*" >
										<option value="">请选择所属问卷</option>
											<ticket:systemCommon type="surveyQuestionnaireList" value="0"/>
											<s:if test="#request.surveyQuestionnaireList != null">
												<s:iterator id="surveyQuestionnaire" value="#request.surveyQuestionnaireList">
													<option value="${surveyQuestionnaire.id }">${surveyQuestionnaire.title }</option>
												</s:iterator>
											</s:if>
										</select>
										<p class="Validform_checktip" style="display: inline;">
											请选择所属问卷
										</p>
									</td>
								</s:else>
								
							</tr>
				
							<tr>
								<td class="text_align_right" width="150">
									问题编号
								</td>
								<td>
									<select id="questionNo" name="questionNo" class="my_input">
									<c:forEach var="i" begin="1" end="50">
									  <option value="${i }">${i }</option>
									</c:forEach>
									
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写问题编号</p>
								</td>
							</tr>
							
							<tr>
								<td class="text_align_right" width="150">
									问题标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问题标题</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问题类型：
								</td>
								<td>
									<input id="type1" name="type" value="0"  checked="checked" type="radio" />单选
									<input id="type2" name="type" value="1" type="radio" />多选
									<input id="type3" name="type" value="2" type="radio" />自定义文本
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="" onclick="javascript:history.go(-1)" type="submit" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}&"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}&surveyQuestionnaireId=${surveyQuestionnaireId }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>