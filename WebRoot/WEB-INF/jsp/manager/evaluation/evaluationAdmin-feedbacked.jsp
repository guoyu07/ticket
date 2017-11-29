<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" >
		var actionName = '${actionName}';
	</script>
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
					      <td >评价系统</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<%@ include file="search.jsp" %>
						  </td></tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchSendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量送审</a>
					      	<a href="#" class="batchFeedbackBtn" value="${actionName}CheckBox" entityName="${entityName}">批量回复</a>
					      	<a href="#" class="batchShieldBtn" value="${actionName}CheckBox" entityName="${entityName}">批量屏蔽ID</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
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
								    <s:if test="operationFlag == 'feedback'">
								    <td width="80" class="text_align_center">回复</td>
								    </s:if>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
									<s:iterator var="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td>
								  				<input  name="${actionName}CheckBox" type="checkbox" value="${c.id }">
								  			</td>
										    <td>${st.count }</td>
										    <td>${c.member.phone }</td>
										    <td>${c.member.nickName }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.classes.name }</td>
										    <td>${c.project.name }</td>
										    <td>
										    	<s:iterator var="i" value="#c.items">
										    	${i.setting.name }--${i.star }分<br/>
										    	</s:iterator>
										    </td>
									    	<td>${c.content }</td>
									    	<td>
									    		<c:forEach  var="item" items="${fn:split(c.images, ', ') }">
									    			<c:if test="${item != null && item != '' ? true : false }">
									    			<img src="${item }" height="100" width="100" />
									    			</c:if>
									    		</c:forEach>
									    	</td>
									    	<s:if test="operationFlag == 'feedback'">
									    	<td>${c.feedback }</td>
									    	</s:if>
										    <td>
										    	<s:if test="evaluationService.canSend(#c.id)">
										    	<a href="#" class="sendBtn" entityName="${entityName}" value="${c.id }">送审</a>
										    	</s:if>
										    	<s:if test="evaluationService.canFeedback(#c.id)">
										    	<a href="#" class="feedbackBtn" entityName="${entityName}" value="${c.id }">回复</a>
										    	</s:if>
										    	<s:if test="evaluationService.canShield(#c.id)">
										    	<a href="#" class="shieldBtn" entityName="${entityName}" value="${c.id }">屏蔽此ID</a>
										    	</s:if>
										    	<s:if test="!evaluationService.isDelete(#c.id)">
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
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