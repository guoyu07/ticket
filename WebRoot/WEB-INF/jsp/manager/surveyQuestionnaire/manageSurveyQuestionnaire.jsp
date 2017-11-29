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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理调查问卷</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      
					      
					      <span>
					      	
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">问卷标题</td>
							    <td width="80">问卷描述</td>
							    <td width="80">问卷所属模块</td>
							    <td width="100">问卷是否已发放</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.title }</td>
									    <td>${c.descript }</td>
									    <td>
									     <ticket:systemCommon type="survryModularObj" value="${c.survryModularId }"/>
								    	 ${survryModularObj.name }
									    </td>
									    <td>
									    <s:if test="#c.currentOpenUp == 1">
									      	是
									     </s:if>
									    <s:else>
									 	        否
									    </s:else>
									   </td>
									    <td><span>
									    	<a href="/${actionName}_showQuestionnaireDetail.action?id=${c.id }&versionFlag=${versionFlag}">查看问卷</a>
									    	<s:if test="#c.currentOpenUp == 0">
										    	<a href="/surveyQuestion_manage.action?surveyQuestionnaireId=${c.id }&versionFlag=${versionFlag}">管理问卷问题</a>
										    	<a href="/surveyQuestion_add.action?surveyQuestionnaireId=${c.id }&versionFlag=${versionFlag}">新增问卷问题</a>
										    	<a href="/surveyQuestionnaire_sendToObject.action?id=${c.id }&versionFlag=${versionFlag}">发放问卷</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<a href="#" class="logicDeleteBtn" entityName="SurveyQuestionnaire" value="${c.id }">删除</a>
									    	</s:if>
									    	<s:else>
										    	<a href="/surveyQuestionnaire_statistic.action?id=${c.id }&versionFlag=${versionFlag}">答卷统计</a>
										    </s:else>
									    	</span>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>