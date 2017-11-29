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
					<div class="my_table_list_nav">
						<span>转接客户信息</span>
					</div>
					<form action="#" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10" style="border: 0;margin-left: 50px">
						  <tr>
						    <td class="text_align_right" width="150">
						    	<ticket:systemCommon type="employeeList"/>
						    	<select id="beforeEmployee_id" name="beforeEmployee_id" class="my_input" datatype="*">
						    		<option value="">请选择转出客户的员工</option>
						    		<s:if test="#request.employeeList != null">
						    			<s:iterator id="employee" value="#request.employeeList">
						    				<option value="${employee.id }">${employee.name }</option>
						    			</s:iterator>
						    		</s:if>
						    	</select>
						    	<p class="Validform_checktip" style="display:inline;">请选择员工</p>
						    </td>
						    <td align="center">操作</td>
						    <td>
						    	<select id="afterEmployee_id" name="afterEmployee_id" class="my_input" datatype="*">
						    		<option value="">请选择接收客户的员工</option>
						    		<s:if test="#request.employeeList != null">
						    			<s:iterator id="employee" value="#request.employeeList">
						    				<option value="${employee.id }">${employee.name }</option>
						    			</s:iterator>
						    		</s:if>
						    	</select><br>
						    	<p class="Validform_checktip" style="display:inline;">请选择员工</p>
						    </td>
						  </tr>
							<tr id="intention">
								<td valign="top" width="150">
									<p>
										请选择意向客户：
									</p>
									<div id="selectIntentionCustomerDiv">
										<select id="selectIntentionCustomer"
											name="selectIntentionCustomer" multiple="true"
											style="width: 200px; height: 180px">

										</select>
									</div>
								</td>
								<td width="80" align="center">
									<p>
										<input type="button" id="singleSelectIntention" value=">"
											style="width: 50px; height: 30px; text-align: center;" />
									</p>
									<p>
										<input type="button" id="multiSelectIntention" value=">>"
											style="width: 50px; height: 30px; text-align: center;" />
									</p>
									<p>
										<input type="button" id="singleDisSelectIntention" value="<" 
											style="width: 50px; height: 30px; text-align: center;" />
									</p>
									<p>
										<input type="button" id="multiDisSelectIntention" value="<<" 
											style="width: 50px; height: 30px; text-align: center;" />
									</p>
								</td>
								<td valign="top">
									<p>
										您选择的意向客户如下:
									</p>
									<select id="getIntentionCustomer" name="getIntentionCustomer"
										multiple="true" style="width: 200px; height: 180px">
									</select>
								</td>
							</tr>
							<tr id="channel">
								<td valign="top" width="150" id="channelChild">
									<p>
										请选择渠道客户：
									</p>
									<select id="selectChannelCustomer" name="selectChannelCustomer"
										multiple="true" style="width: 200px; height: 180px">
											
									</select>
								</td>
								<td width="80" align="center">
									<p>
										<input type="button" id="singleSelectChannel" value=">"
											style="text-align: center; width: 50px; height: 30px" />
									</p>
									<p>
										<input type="button" id="multiSelectChannel" value=">>"
											style="text-align: center; width: 50px; height: 30px" />
									</p>
									<p>
										<input type="button" id="singleDisSelectChannel" value="<" 
											style="text-align: center; width: 50px; height: 30px" />
									</p>
									<p>
										<input type="button" id="multiDisSelectChannel" value="<<" 
											style="text-align: center; width: 50px; height: 30px" />
									</p>
								</td>
								<td valign="top">
									<p>
										您选择的渠道客户如下:
									</p>
									<select id="getChannelCustomer" name="getChannelCustomer"
										multiple="true" style="width: 200px; height: 180px">

									</select>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="transferCustomer" type="button" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>