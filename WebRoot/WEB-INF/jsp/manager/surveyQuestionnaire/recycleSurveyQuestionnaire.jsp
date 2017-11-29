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
					      <td><span><a href="#">批量删除</a><a href="#">批量操作</a></span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="50">问卷编号</td>
								    <td width="80">创建时间</td>
								    <td width="80">问卷标题</td>
								    <td width="80">问卷描述</td>
								    <td width="80">问卷所属模块</td>
								    <td width="100">是否当前启用问卷</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.survryNo }</td>
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
										    	<a href="#" class="realDeleteBtn" entityName="SurveyQuestionnaire" value="${c.id }">删除</a>
										    	<a href="#" class="restoreBtn" entityName="SurveyQuestionnaire" value="${c.id }">还原</a>
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