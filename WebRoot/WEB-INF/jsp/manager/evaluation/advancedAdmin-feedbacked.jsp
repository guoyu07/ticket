<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/evaluationAdmin.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >部门需跟进列表</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_evaluationAdminList.action?operationFlag=${operationFlag }" method="get">
								<input type="hidden" value="${operationFlag }" name="operationFlag"/>
							  	起始时间:<input name="startTime" type="text" 
								  			value="${param.startTime }"
								  			onclick="new WdatePicker();"/>
							  				&nbsp;&nbsp;&nbsp;&nbsp;
							  	结束时间:<input name="endTime" type="text" 
								  			value="${param.endTime }"
								  			onclick="new WdatePicker();"/>
							  				<br/>
							  			<input type="submit" value="查询"/>
							  			<input type="reset" value="重置"/>
						  	</form>
						  </td></tr>
					    <tr  class="text_align_left">
					      <td><span>
				      		<a href="#" class="batchFeedbackDepartmentBtn" entityName="${entityName}" value="${c.id }">批量追发部门</a>
				    		<%-- <a href="#" class="unsendDepartmentBtn" entityName="${entityName}" value="${c.id }">取消发送</a> --%>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20" class="text_align_center"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20" class="text_align_center">编号</td>
								    <td width="80" class="text_align_center">用户</td>
								    <td width="80" class="text_align_center">昵称</td>
								    <td width="80" class="text_align_center">时间</td>
								    <td width="80" class="text_align_center">大类</td>
								    <td width="80" class="text_align_center">项目</td>
								    <td width="80" class="text_align_center">详情</td>
								    <td width="80" class="text_align_center">描述</td>
								    <td width="80" class="text_align_center">补充图片</td>
								    <td width="80" class="text_align_center">已发送部门</td>
								    <td width="80" class="text_align_center">处理结果</td>
								    <td width="80" class="text_align_center">备注</td>
								    <td width="80" class="text_align_center">是否关闭</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
									<s:iterator var="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td>
								  				<input  name="${actionName}CheckBox" type="checkbox" value="${c.id }">
								  			</td>
										    <td>${st.count }</td>
										    <td>${c.evaluation.member.phone }</td>
										    <td>${c.evaluation.member.nickName }</td>
										    <td><s:date name="#c.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.evaluation.classes.name }</td>
										    <td>${c.evaluation.project.name }</td>
										    <td>
										    	<s:iterator var="i" value="#c.evaluation.items">
										    	${i.setting.name }--${i.star }分<br/>
										    	</s:iterator>
										    </td>
									    	<td>${c.evaluation.content }</td>
									    	<td>
									    		<c:forEach  var="item" items="${fn:split(c.evaluation.images, ', ') }">
									    			<c:if test="${item != null && item != '' ? true : false }">
									    			<img src="${item }" height="100" width="100" />
									    			</c:if>
									    		</c:forEach>
									    	</td>
									    	<td>
										    	<s:iterator var="item" value="evaluationService.getDepartments(#c.evaluation)">
										    	${item.department.name }
										    	</s:iterator>
									    	</td>
									    	<td>${c.msg }</td>
									    	<td>${c.remark }</td>
									    	<td>${c.evaluation.state.name!='pushed_state' ? '否' : '是'}</td>
										    <td>
										    	<%-- <s:if test="evaluationService.canSendToDepartment(#c.id)">
										    	<a href="#" class="sendDepartmentBtn" entityName="${entityName}" value="${c.id }">发送部门</a>
										    	</s:if> --%>
										    	<%-- <s:if test="evaluationService.canSendBack(#c.id)">
										    	<a href="#" class="unsendDepartmentBtn" entityName="${entityName}" value="${c.id }">取消发送</a>
										    	</s:if> --%>
										    	<s:if test="!#c.close">
										    	<a href="#" class="feedbackDepartmentBtn" entityName="${entityName}" value="${c.id }">追发部门</a>
										    	</s:if>
										    </td>
										</tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="20">${noDataMessage }</td>
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