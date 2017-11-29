<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/evaluationReportTask.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理评论报表任务</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20">编号</td>
								    <td width="50">报表文件名</td>
								    <td width="50">报表性质</td>
								    <td width="50">报表类型</td>
								    <td width="120">时间</td>
								    <!-- <td width="30">邮箱</td>
								    <td width="30">状态</td> -->
								    <td width="40" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.name }</td>
										    <td>${c.property.text }</td>
										    <td>${c.type.text }</td>
										    <td>
										    	<s:iterator var="time" value="#c.times">
										    		<s:date name="#time.startTime" format="yyyy-MM-dd"/>
										    		<s:if test="#time.endTime != null">
											    		&nbsp;&nbsp;--&nbsp;&nbsp;
											    		<s:date name="#time.endTime" format="yyyy-MM-dd"/>
										    		</s:if>
										    		<br/>
										    	</s:iterator>	
										    </td>
										    <%-- <td>${c.email }</td>
										    <td>${c.launch ? '启动' : '关闭' }</td> --%>
										    <td><span>
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="${actionName }_generate.action?idsValue=${c.id }" target="_blank" entityName="${entityName}" value="${c.id }">生成</a>
										    	<%-- <a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span> --%>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="70">${noDataMessage }</td>
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