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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理调查问题
					      </td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      <span>
					      	<a href="/surveyQuestion_add.action?surveyQuestionnaireId=${surveyQuestionnaireId }&versionFlag=${versionFlag}">新增问题</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="60">问题编号</td>
								    <td width="80">创建时间</td>
								    <td width="80">问题标题</td>
								    <td width="80">所属问卷</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.questionNo}</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.title}</td>
										    <td>
										     <ticket:systemCommon type="surveyQuestionnaireObj" value="${c.surveyQuestionnaireId }"/>
									    	 ${surveyQuestionnaireObj.title }
										    </td>
										    <td><span>
										    	<s:if test="#c.type == 2"></s:if>
										    	<s:else>
											    	<a href="/surveyOption_manage.action?surveyQuestionId=${c.id }&versionFlag=${versionFlag}">管理选项</a>
											    	<a href="/surveyOption_add.action?surveyQuestionId=${c.id }&versionFlag=${versionFlag}">新增选项</a>
										    	</s:else>
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
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