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
					      <td >管理调查问题</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span><a href="#">批量审核</a><a href="#">批量删除</a><a href="#">批量操作</a></span></td>
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
								    <td width="80">问题类型</td>
								    <td width="80">问题排序</td>
								    <td width="80">问题调查形式</td>
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
										    <td>
											     <ticket:systemCommon type="typeObj" value="${c.type}"/>
										    	 ${typeObj }
										    </td>
										    <td>${c.iseq}</td>
										    <td>
										    <ticket:systemCommon type="questionTypeObj" value="${c.questionType}"/>
										    	 ${questionTypeObj }
										    
										    </td>
										    <td><span>
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	</span></td>
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
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>