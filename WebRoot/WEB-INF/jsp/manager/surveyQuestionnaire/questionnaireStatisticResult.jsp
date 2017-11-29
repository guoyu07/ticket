<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<script type="text/javascript" src="/manager/js/surveyQuestionnaire.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>调查问卷统计</span>
					</div>
					<form>
						<table class="my_table100 margin_top10">
							<tr class="text_align_left my_table_top">
								<td>问卷统计详情</td>
							</tr>
							<tr  class="text_align_left">
						      <td><span>
						     	 问卷标题:${surveyQuestionnaire.title }
						     	 发送数量：${questionnaireSendLog.sendCount }
						     	 反馈数量：${questionnaireSendLog.writeCount }
						     	 反馈率：<fmt:formatNumber value="${questionnaireSendLog.writeCount/questionnaireSendLog.sendCount}" pattern="#%"/>
						      </span></td>
						    </tr>
						    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							    <td width="10">问题编号</td>
							    <td width="50">问题名称</td>
							    <td width="10">问题回答总数</td>
							    <td width="10">选项类型</td>
							    <td width="200">统计详细</td>
							  </tr>
							  <s:if test="questionList != null">
							  	<s:iterator id="questionObj" value="questionList" status="st">
							  		<tr>
									    <td>${questionObj.questionNo }</td>
									    <td>${questionObj.title }</td>
									    <td>
									    	<s:set var="questionAnswerCount" value="questionnaireAnswerService.countByQuestionNo(surveyQuestionnaire.id,#questionObj.questionNo)"/>
									   		${questionAnswerCount }
									    </td>
									    <td>
									   		<s:if test="#questionObj.type ==0">
												单选
					     					</s:if>
									   		<s:if test="#questionObj.type ==1">
												多选
					     					</s:if>
									   		<s:if test="#questionObj.type ==2">
												自定义
					     					</s:if>
					     				</td>
									    <td>
									    	<s:if test="#questionObj.type ==2">
												<a type="button" questionnaireId="${surveyQuestionnaire.id }" questionNo="${questionObj.questionNo }" title="${questionObj.title }" class="showAnswerDetail" >查看详情</a>
					     					</s:if>
					     					<s:else>
									     		<table style="width: 100%">
									     			<tr><td style="width: 30%">选项名称</td><td>选择次数</td><td>选择比率</td></tr>
											    	<s:set var="optionListByQuestion" value="surveyOptionService.queryByQuestionId(#questionObj.id,'site')"/>
											     	<s:if test="#request.optionListByQuestion != null">
												     	<s:iterator id="questionOption" value="#request.optionListByQuestion">
										     				<tr>
										     					<%--<td>${questionOption.optionNo }</td>--%>
										     					<td>${questionOption.title }</td>
										     					<s:if test="#questionObj.type ==0">
																	<td>
																		<s:set var="getSelectedRedioCount" value="questionnaireAnswerService.countRadioAnswerByQustion(surveyQuestionnaire.id,#questionObj.questionNo,#questionOption.optionNo)"/>
																		${getSelectedRedioCount }
																	</td>
																	<td><fmt:formatNumber value="${getSelectedRedioCount/questionAnswerCount}" pattern="#%"/></td>
										     					</s:if>
										     					<s:if test="#questionObj.type ==1">
																	<td>
																		<s:set var="getSelectedCheckboxCount" value="questionnaireAnswerService.countCheckboxAnswerByQustion(surveyQuestionnaire.id,#questionObj.questionNo,#questionOption.optionNo)"/>
																		${getSelectedCheckboxCount }
																	</td>
																	<td><fmt:formatNumber value="${getSelectedCheckboxCount/questionAnswerCount}" pattern="#%"/></td>
										     					</s:if>
										     				</tr>
											     		</s:iterator>
											     	</s:if>
										     	</table>
									     	</s:else>
									    </td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="7">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
					       </td>
					    </tr>
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