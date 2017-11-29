<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" >
		var actionName = '${actionName}';
	</script>
	<script type="text/javascript" src="/manager/js/bjdjComment.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理便捷登机评论表</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="bjdjComment_generate.action">数据报表生成</a>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td rowspan="2" width="20" class="text_align_center"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td rowspan="2" width="80" class="text_align_center">用户</td>
								    <td rowspan="2" width="80" class="text_align_center">服务码</td>
								    <td rowspan="2" width="80" class="text_align_center">IP</td>
								    <td rowspan="2" width="100" class="text_align_center">时间</td>
								    <td rowspan="2" width="200" class="text_align_center">内容</td>
								    <td rowspan="2" width="40" class="text_align_center">总星级</td>
								    <td rowspan="2" width="40" class="text_align_center">图片</td>
								    <td colspan="3" width="220" class="text_align_center">自定义打分项</td>
								    <td rowspan="2" width="220" class="text_align_center">回复人</td>
								    <td rowspan="2" width="220" class="text_align_center">回复时间</td>
								    <td rowspan="2" width="220" class="text_align_center">回复内容</td>
								    <td rowspan="2" width="80" class="text_align_center">操作</td>
								  </tr>
								  <tr class="my_table_top1">
								    <td width="40" class="text_align_center">项目</td>
								    <td width="40" class="text_align_center">星级</td>
								    <td width="200" class="text_align_center">原因</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator var="c" value="pageModule.pageList" status="st">
									    <s:iterator var="item" value="#c.items" status="st">
								  		<tr>
									    	<s:if test="#st.first">
									  			<td rowspan='<s:property value="#c.items.size()"/>'>
									  				<input  name="${actionName}CheckBox" type="checkbox" value="${c.id }">
									  			</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>${c.serviceCode.member==null ? c.serviceCode.order.member.phone : c.serviceCode.member.phone }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>${c.serviceCode.code }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>${c.ip }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>${c.content }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>${c.star }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>
											    	<c:forEach  var="item" items="${fn:split(c.images, ', ') }">
										    			<c:if test="${item != null && item != '' ? true : false }">
										    				<img src="${item }" height="100" width="100" />
										    			</c:if>
									    			</c:forEach>
											    </td>
										    </s:if>
									    	<td>${item.content }</td>
									    	<td>${item.star }</td>
									    	<td>${item.reason }</td>
									    	<s:if test="#st.first">
										    	<td rowspan='<s:property value="#c.items.size()"/>'>
										    		<s:if test="#c.feedback_user != null">
										    			<s:if test="c.feedback_user.realName == null">
										    				${c.feedback_user.realName }
										    			</s:if>
										    			<s:else>
										    				${c.feedback_user.phone }
										    			</s:else>
										    		</s:if>
										    	</td>
										    	<td rowspan='<s:property value="#c.items.size()"/>'><s:date name="#c.feedbackTime" format="yyyy-HH-dd HH:mm:ss"/></td>
										    	<td rowspan='<s:property value="#c.items.size()"/>'>${c.feedback }</td>
											    <td rowspan='<s:property value="#c.items.size()"/>'>
											    	<s:if test="#c.feedbackTime == null">
											    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">回复</a>
											    	</s:if>
											    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
											    </td>
										    </s:if>
										 </tr>
									    </s:iterator>
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