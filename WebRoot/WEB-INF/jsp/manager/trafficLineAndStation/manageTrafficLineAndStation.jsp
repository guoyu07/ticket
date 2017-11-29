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
					      <td>
				      		根据路线检索：
					      	<select name="trafficLineID" id="trafficLineID" class="my_select">
								<option value="">请选择路线</option>
								<ticket:systemCommon type="trafficLineList"/>
								<s:if test="#request.trafficLineList != null">
									<s:iterator id="trafficLine" value ="#request.trafficLineList">
										<ticket:systemCommon type="trafficTypeObj" value="${trafficLine.trafficType_id }"/>
										<option value="${trafficLine.id }">${trafficLine.name }</option>
									</s:iterator>
								</s:if>
							</select>
					      <span>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="220">路线名称</td>
							    <td width="400">车站名称</td>
							    <td width="80">排序值</td>
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
									    <td>${c.status.orderValue }</td>
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
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}&trafficLine_id=${c.trafficLine_id }">编辑</a></span></td>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>