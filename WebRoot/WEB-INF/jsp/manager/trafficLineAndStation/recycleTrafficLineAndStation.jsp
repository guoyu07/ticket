<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/trafficLineAndStation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理路线与车站关联</td>
					    </tr>
					    <tr  class="text_align_left">
					      	<td><span>
					      		<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      		<a href="#" class="batchRestoreBtn" value="${actionName}CheckBox" entityName="${entityName}">批量还原</a>
					    	</span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">路线名称</td>
							    <td width="80">车站名称</td>
							    <td>车站类型</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>
									    	<ticket:systemCommon type="trafficLineObj" value="${c.trafficLine_id }"/>
									    	${trafficLineObj.name }
									    </td>
									    <td>
									    	<ticket:systemCommon type="trafficStationObj" value="${c.trafficStation_id }"/>
									    	${trafficStationObj.name }
									    </td>
									    <td>
									    	<s:if test="#c.stationType==1">
									    		往返车站
									    	</s:if>
									    	<s:elseif test="#c.stationType==2">
									    		单程去
									    	</s:elseif>
									    	<s:elseif test="#c.stationType==3">
									    	           单程回
									    	</s:elseif>
									    </td>
									    <td><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="#" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
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