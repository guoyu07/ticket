<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/receipt.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
				<ticket:systemCommon type="employeeInfoObj" value="${systemUser.id}"/>
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理收据发票</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					        <input type="hidden" id="leftIndexInt" value="${leftIndex}"/>
							<select id="searchapplyClass_id" name="applyClass_id" class="my_input">
								<option value="">申请类别</option>
								<s:iterator id="a" value="applayClasses">
									<option <s:if test="#a.id == applyClass_id">selected</s:if> value="${a.id}">${a.name}</option>
								</s:iterator>
							</select>
					      	<a href="javascript:;" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="100">收据发票编号</td>
								    <td width="80">用户名</td>
								    <td width="80">收据名称(付款客户)</td>
								    <td width="80">申请时间</td>
								    <td width="80">申请金额</td>
								    <td width="80">申请人</td>
								    <td width="80">申请类型</td>
								    <td width="80">审核人员</td>
								    <td width="80">审核时间</td>
								    <td width="80">状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.receiptNo }</td>
										    <ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfoId }"/>
										    <td>${channelCustomerInfoObj.loginId }</td>
										    <td>${c.name }</td>
										    <td><s:date name="#c.issueTime" format="yyyy-MM-dd"/></td>
										    <td>${c.amountOfMoney }</td>
										    <td>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfoId}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										     <td>
										   	<ticket:systemCommon type="applayClassObj" value="${c.applyClass_id}"/>
									    	 ${applayClassObj.name }
										    </td>
										    <td>
										    	<ticket:systemCommon type="getSystemUserObj" value="${c.systemUser_id}"/>
										    	${getSystemUserObj.name}
										    </td>
										    <td>
										    	<s:date name="#c.auditTime" format="yyyy-MM-dd HH:mm:ss"/>
										    </td>
										   
										    <td>
										    <s:if test="#c.state == 0">
										    	未申请
										    </s:if>
										    <s:if test="#c.state == 1">
										    	已申请,未审核
										    </s:if>
										    <s:if test="#c.state == 2">
										    	审核未通过
										    </s:if>
										    <s:if test="#c.state == 3">
										    	审核通过,未开票
										    </s:if>
										    <s:if test="#c.state == 4">
										    	已开票
										    </s:if>
										    </td>
										    <td>
										    	<span>
										    	<s:if test="#c.state == 0">
										    		<a href="javascript:;" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    		<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    		<a href="javascript:;" class="submitApply" attrValue="${c.id}">提交申请</a>
										    	</s:if>
										    	<s:if test="#c.state == 1">
										    		<a href="javascript:;" class="auditPass" attrValue="${c.id}">通过审核</a>
										    		<a href="javascript:;" class="auditNoPass" attrValue="${c.id}">驳回申请</a>
										    	</s:if>
										    	<s:if test="#c.state == 2">
										    		<a href="javascript:;" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    		<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    		<a href="javascript:;" class="submitApply" attrValue="${c.id}">再次提交申请</a>
										    	</s:if>
										    	<s:if test="#c.state == 3">
										    		<a href="javascript:;" class="stateSuccess" attrValue="${c.id}">同意开票</a>
										    	</s:if>
										    	<s:if test="#c.state == 4">
										    		<a href="/${actionName}_detail.action?id=${c.id }&versionFlag=${versionFlag}">编辑发票号</a>
										    		已完成
										    	</s:if>
										    	<a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">查看</a>
										    	</span>
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
					  <%@ include file="page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>