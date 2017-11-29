<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >客户数据公共池</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	 <input type="text" value="请输入客户名称/联系电话关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  value="${keyword}" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<!-- 
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      	 -->
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">客户名称</td>
								    <td width="80">渠道分类</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">联系邮箱</td>
								    <td width="80">客户状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.name }</td>
										    <td>
										    <ticket:systemCommon type="channelTypeObj" value="${c.channelTypeId}"/>
									    	${channelTypeObj.name }
										    </td>
										    <td>
										    <s:if test="#session.systemEmployeeInfo != null">
										    	<s:if test="#session.systemEmployeeInfo.id == #c.employeeInfo_id">
										    			${c.contact }
										    	</s:if>
										    	<s:else>
										    		<s:if test="#c.employeeInfo_id != null && #c.employeeInfo_id != ''">
										    			保密
										    		</s:if>
										    		<s:else>
										    		${c.contact }
										    		</s:else>
										    	</s:else>
										    </s:if>
										    <s:else>
										    ${c.contact }
										    </s:else>
										    
										    </td>
										    <td>
										    <s:if test="#session.systemEmployeeInfo != null">
										    	<s:if test="#session.systemEmployeeInfo.id == #c.employeeInfo_id">
										    			${c.contactPhone }
										    	</s:if>
										    	<s:else>
										    		<s:if test="#c.employeeInfo_id != null && #c.employeeInfo_id != ''">
										    			保密
										    		</s:if>
										    		<s:else>
										    		${c.contactPhone }
										    		</s:else>
										    	</s:else>
										    </s:if>
										    <s:else>
										    ${c.contactPhone }
										    </s:else>
										    
										    </td>
										    <td>
										    <s:if test="#session.systemEmployeeInfo != null">
										    	<s:if test="#session.systemEmployeeInfo.id == #c.employeeInfo_id">
										    			 ${c.email }
										    	</s:if>
										    	<s:else>
										    		<s:if test="#c.employeeInfo_id != null && #c.employeeInfo_id != ''">
										    			保密
										    		</s:if>
										    		<s:else>
										    		${c.email }
										    		</s:else>
										    	</s:else>
										    </s:if>
										    <s:else>
										     ${c.email }
										    </s:else>
										    </td>
										    <td><ticket:systemCommon type="getChannelCustomerInfoState" value="${c.id}"/></td>
										    <td>
										    <a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">查看</a>
										    <s:if test="#c.employeeInfo_id == null">
										    <span>
										    	<a class="applyPickUp" value="${c.id }" entityName="${entityName}" employee_id="${c.employeeInfo_id}" href="javascript:;">申请捡单</a>
										    </span>
										    </s:if>
										    <s:else>
										    	<ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id}"/>
										    	${employeeInfoObj.name}
										    </s:else>
										    </td>
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
					  <%@ include file="customerPage.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>