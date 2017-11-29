<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/employeeOutVisit.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理员工外出拜访记录</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							    <td width="80">出访人</td>
							    <td width="80">拜访时间</td>
							    <td width="80">公司名称</td>
							    <td width="80">用户名</td>
							    <td width="80">联系人</td>
							    <td width="80">联系电话</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td>
									    	<ticket:systemCommon type="employeeInfoObj" value="${c.employee_id }"/>
									    		${employeeInfoObj.name}
									    </td>
									    <td><s:date name="#c.visitDate" format="yyyy-MM-dd"/></td>
									    	<ticket:systemCommon type="channelCustomerInfoObj" value="${c.customer_id }"/>
									    <td>
									    	${channelCustomerInfoObj.name }
									    </td>
									    <td>
									    	${channelCustomerInfoObj.loginId }
									    </td>
									    <td>${c.contact }</td>
									    <td>${c.contactPhone }</td>
									    <td><span>
									    	<a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">查看详情</a>
									    	<a href="/remarkVisitRecord_add.action?record_id=${c.id }&versionFlag=${versionFlag}&customer_id=${customer_id }&phoneOrOut=out">添加备注</a>
									    	<a href="/remarkVisitRecord_manage.action?record_id=${c.id }&versionFlag=${versionFlag}">查看所有备注</a>
									    	</span>
									    </td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="10">${noDataMessage }</td>
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