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
					      <td >管理意向客户信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	 <input type="text" value="请输入客户名称/联系电话关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword" value="${keyword}" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<a href="javascript:;" class="batchGiveUpCustomer" >批量放弃客户</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">公司名称</td>
								    <td width="80">渠道分类</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">联系邮箱</td>
								 	<td width="80">客户状态</td>
								    <td width="220" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.name }</td>
										    <td>${c.channelType.name }</td>
										    <td>${c.contact }</td>
										    <td>${c.contactPhone }</td>
										    <td>${c.email }</td>
										    <td><ticket:systemCommon type="getChannelCustomerInfoState" value="${c.id}"/></td>
										    <td><span>
										    	<a href="javascript:;" class="singleGiveUpCustomer" value="${c.id }">放弃客户</a>
										    	<a href="/${actionName}_editBase.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<a href="/customerProtectLog_add2.action?channelCustomerInfo_id=${c.id}">申请保护</a>
										    	<a href="/agreement_add2.action?customerId=${c.id}">申请合同</a>
										    	<a href="/employeeOutVisit_add.action?id=${c.id}&state=0">添加外出拜访记录</a>
										    	<a href="/phoneVisit_add.action?id=${c.id}&state=0">添加电话拜访记录</a>
										    	<a href="/employeeOutVisit_manage.action?customer_id=${c.id}&state=0">管理外出拜访记录</a>
										    	<a href="/phoneVisit_manage.action?customer_id=${c.id}&state=0">管理电话拜访记录</a>
										    	<a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">查看详情</a>
										    	</span>
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
					  <%@ include file="customerPage.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>