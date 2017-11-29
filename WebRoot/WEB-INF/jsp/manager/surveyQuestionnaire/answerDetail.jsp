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
						<span>答案详情</span>
					</div>
					<form action="/surveyQuestionnaire_getQuestionnairAnswer.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td width="10%">问题名称</td>
								<td>${questionTitle }</td>
							</tr>
							<s:if test="questionnaireAnswerList != null">
								<s:iterator id="answer" value="questionnaireAnswerList" status="st">
								<tr>
									<td>
										第${st.count }个回答
									</td>
									<td>
										${answer.qustionAnswer }
									</td>
								</tr>
								</s:iterator>
							</s:if>
						</table>
						<div class="my_table_list_r">
						    <input onclick="javascript:history.go(-1);" type="button" value="返回" class="btn_remove margin_left20">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>