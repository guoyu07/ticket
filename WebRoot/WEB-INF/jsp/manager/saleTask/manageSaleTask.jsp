<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/saleTask.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td >管理销售任务</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20">编号</td>
								    <td width="80">任务名称</td>
								    <td width="80">开始时间</td>
								    <td width="80">结束时间</td>
								    <td width="80">所属人</td>
								    <td width="80">所属部门</td>
								    <td width="80">签约数</td>
								    <td width="80">充值金额</td>
								    <td width="80">电话拜访量</td>
								    <td width="80">外出拜访量</td>
								    <td width="100" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.remark }</td>
										    <td><s:date name="#c.startTime" format="yyyy-MM-dd"/></td>
										    <td><s:date name="#c.endTime" format="yyyy-MM-dd"/></td>
										    <td>${c.employee.name }</td>
										    <td>${c.department.name }</td>
										    <td>${c.signCount }</td>
										    <td>${c.recharge }</td>
										    <td>${c.phoneCount }</td>
										    <td>${c.visitCount }</td>
										    <td><span>
										    	<s:if test="#c.published == false && #c.department != null">
											    	<a href="${actionName }_publish.action?id=${c.id }">下发</a>
											    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	</s:if>
										    	<s:else>
											    	<a href="${actionName }_degreeOfCompletion.action?id=${c.id }">查看完成度</a>
											    </s:else>
										    </span></td>
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