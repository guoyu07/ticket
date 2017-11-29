<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjServiceCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td>管理服务码表</td>
					    </tr>
						  <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manage.action?versionFlag=site" method="get">
								服务码:<input type="text" name="start" value="${start }"/>
									&nbsp;&nbsp;~&nbsp;&nbsp;
									<input type="text" name="end" value="${end }"/><br/>
							  	类型:<select name="type_id">
										<option value="">全部</option>
										<s:iterator var="dict" value="dictionaryService.querySubByParentValue('service_code_type')">
										<option value="${dict.id }" ${type_id==dict.id ? 'selected="selected"' : null}>${dict.name }</option>
										</s:iterator>
							       </select>
							  	状态:<select name="state_id">
							  			<option value="">全部</option>
										<s:iterator var="dict" value="dictionaryService.querySubByParentName('service_code_status')">
										<option value="${dict.id }" ${state_id==dict.id ? 'selected="selected"' : null}>${dict.value }</option>
										</s:iterator>
							       </select>
							       <br/>
							  	创建时间:<input name="startTime" type="text" onclick="new WdatePicker();" value='<s:date name="startTime" format="yyyy-MM-dd" />'/>
							  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endTime" type="text" onclick="new WdatePicker();" value='<s:date name="endTime" format="yyyy-MM-dd" />'/>
							  			<br/>
								账号名:<input type="text" name="loginName" value="${loginName }"/>
								真实姓名:<input type="text" name="realName" value="${realName }"/>
								电话号码:<input type="text" name="phone" value="${phone }"/>
								身份证号:<input type="text" name="IDCard" value="${IDCard }"/><br/>
					  			<input type="submit" value="查询"/>
						  	</form>
						  </td></tr>
						<tr class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchUnFrozeBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="unFroze">批量解冻结</a>
					      	<a href="#" class="batchFrozeBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="froze">批量冻结</a>
					      	<a href="#" class="batchActivateBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="activate">批量激活</a>
					      	<a href="#" class="batchDonationBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="donation">批量转赠</a>
					      	<a href="bjdjServiceCode_generate.action" target="_blank">数据报表生成</a>
					      	<a target="_blank" href="${actionName }_downReport.action?start=${start}&end=${end}&type_id=${type_id}&state_id=${state_id}&startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />&loginName=${loginName}&realName=${realName}&phone=${phone}&IDCard=${IDCard}">下载</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table id="table" class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="100">服务码</td>
							    <td width="100">类型</td>
							    <td width="50">状态</td>
							    <td width="100">创建时间</td>
							    <td width="100">账号名</td>
							    <td width="100">真实姓名</td>
							    <td width="100">电话</td>
							    <td width="100">身份证</td>
							    <td width="100">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }" attrValue="${c.code }"></td>
									    <td>${c.code }</td>
									    <td>${c.type.name }</td>
									    <td>${c.state.value }</td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									    <s:if test="#c.order != null">
										    <td>${c.order.member.loginName }</td>
										    <td>${c.order.member.realName }</td>
										    <td>${c.order.member.phone }</td>
										    <td>${c.order.member.IDCard }</td>
									    </s:if>
									    <s:else>
									    	<td></td>
									    	<td></td>
									    	<td></td>
									    	<td></td>
									    </s:else>
									    <td>
									    	<%-- <a href="/${actionName}_distribution.action?id=${c.id }">分配</a> --%>
									    	<s:if test="#c.state.value == '已冻结'">
									    		<a attrValue="${c.id }" onclick="unFroze(this);">解冻结</a>
									    	</s:if>
									    	<s:if test="#c.state.value != '已冻结'">
									    		<a attrValue="${c.id }" onclick="froze(this);">冻结</a>
									    	</s:if>
									    	<s:if test="#c.state.value == '未使用'">
									    		<a href="/${actionName}_activate.action?idsValue=${c.id }&codes=${c.code}">激活</a>
									    		<a href="/${actionName}_donation.action?idsValue=${c.id }&codes=${c.code}">转赠</a>
									    	</s:if>
									    	<s:if test="#c.state.name == 'activated'">
										    	<a href="javascript:;" onclick="unactived('${c.id }')">取消激活</a>
									    	</s:if>
									    </td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="711">${noDataMessage }</td>
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