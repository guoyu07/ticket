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
						<span>调查问卷详情</span>
					</div>
					<form action="/surveyQuestionnaire_getQuestionnairAnswer.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<s:if test="questionList != null">
								<s:iterator id="question" value="questionList" status="st">
									<tr>
										<td class="text_align_left" style="color: #333;font-weight: bold" width="150">
											第${st.count }题 ${question.title }？
										</td>
									</tr>
									<tr>
										<td>
											<!-- 单选 -->
											<s:if test="#question.type ==0">
												<ticket:systemCommon type="questionOptionByQuestionId" value="${question.id }"/>
												<s:if test="#request.questionOptionByQuestionId != null">
													<s:iterator id="questionOption" value="#request.questionOptionByQuestionId">
														<input type="radio" value="${questionOption.optionNo}" name="name${question.questionNo }&${question.type}"/>${questionOption.title }
													</s:iterator>
												</s:if>
											</s:if>       
											<!-- 多选 -->
											<s:elseif test="#question.type ==1">
												<ticket:systemCommon type="questionOptionByQuestionId" value="${question.id }"/>
												<s:if test="#request.questionOptionByQuestionId != null">
													<s:iterator id="questionOption" value="#request.questionOptionByQuestionId">
														<input type="checkbox" value="${questionOption.optionNo}" name="name${question.questionNo }&${question.type}"/>${questionOption.title }
													</s:iterator>
												</s:if>
											</s:elseif>       
											<!-- 自定义文本-->
											<s:elseif test="#question.type ==2">
												<textarea name="name${question.questionNo }&${question.type}" style="width: 550px;height: 100px"></textarea>
											</s:elseif>       
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr><td>${noDataMessage }</td></tr>
							</s:else>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input onclick="javascript:history.go(-1);" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/surveyQuestionnaire_thanksActive.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>