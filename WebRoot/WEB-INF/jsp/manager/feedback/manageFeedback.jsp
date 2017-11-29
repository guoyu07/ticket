<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/feedback.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box"> 
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理公测反馈</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manage.action?versionFlag=site" method="post">
							  	日期:<input id="startTime" name="startTime" type="text" onclick="new WdatePicker();"/>
							  	~~<input id="endTime" name="endTime" type="text" onclick="new WdatePicker();"/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
						  	</form>
						  	<script type="text/javascript">
						  		$(function(){
						  			$('input[name="startTime"]').val("${param.startTime }");
						  			$('input[name="endTime"]').val("${param.endTime }");
						  		});
						  	</script>
						  </td></tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchDownLoadBtn1" value="${actionName}CheckBox" entityName="${entityName}" methodName="batchDownLoad">按时间下载</a>
					      	<a href="#" class="batchDownLoadBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="batchDownLoad">全部下载</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20">编号</td>
								    <td width="80">反馈时间</td>
								    <td width="80">联系方式</td>
								    <td>问题描述</td>
								    <td>图片</td>
								    <!-- <td>是否回复</td> -->
								    <td>是否有追加反馈及回复</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.phone }</td>
										    <td>${c.description }</td>
										    <td>
										    	<s:if test="#c.images != null">
										    		<img src="${c.images }" width="200px" height="200px"/>
										    	</s:if>
										    </td>
										    <%-- <td>
										    	<ticket:common type="feedbackReplyObj" value="${c.id }"/>
												<s:if test="#request.feedbackReplyObj != null">
													已回复
												</s:if>
												<s:else>
													未回复
												</s:else>
										    </td> --%>
										    <td>
												<ticket:common type="feedBackStatus" value="${c.id }"/>	
												<s:if test="#request.feedBackStatus == 1">
													<span style="color: red;">有追加反馈没有回复</span>
												</s:if>
												<s:elseif test="#request.feedBackStatus == 2">
													<span style="color:blue;">已回复无追加反馈</span>
												</s:elseif>
												<s:elseif test="#request.feedBackStatus == 3">
													<span style="color: blue;">有追加反馈且已回复</span>
												</s:elseif>
												<s:elseif test="#request.feedBackStatus == 4">
													<span style="color: red;">无追加反馈且未回复</span>
												</s:elseif>					    
										    </td>
										    <td><span>
										    	<a href="/feedback_detail.action?id=${c.id }">详细信息</a>
										    	<a href="/feedbackReply_add.action?feedback_id=${c.id }">回复</a>
										    	<%-- <a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a> --%>
										    	</span></td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="100">${noDataMessage }</td>
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