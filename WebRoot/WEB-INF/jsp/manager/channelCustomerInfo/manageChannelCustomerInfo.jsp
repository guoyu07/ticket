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
					      <td >管理渠道客户信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	 <input type="text" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword" value="<s:if test="keyword != null && keyword != ''">${keyword}</s:if><s:else>请输入客户名称/联系电话关键词</s:else>" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	
					      	<a href="javascript:;" class="batchGiveUpCustomer">批量放弃客户</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">公司名称</td>
								    <td width="80">用户名</td>
								    <td width="80">渠道分类</td>
								    <td width="100">开户日期</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">优惠政策</td>
								    <td width="80">合同签约时间</td>
								    <td width="80">合同签约金额</td>
								    <td width="80">所属员工</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.name }</td>
										    <td>${c.loginId }</td>
										    <td>
										    <ticket:systemCommon type="channelTypeObj" value="${c.channelTypeId}"/>
									    	${channelTypeObj.name }
										    </td>
										    <td><s:date name="#c.openAccountDate" format="yyyy-MM-dd"/></td>
										    <td>${c.contact }</td>
										    <td>${c.contactPhone }</td>
										    <td>
										     <ticket:systemCommon type="favouredPolicyObj" value="${c.favouredPolicyId}"/>
									    	${favouredPolicyObj.name }
										    </td>
										    <ticket:systemCommon type="getAgreementObj" value="${c.agreement_id}"/>
										    <td><s:date name="#request.getAgreementObj.signingDate" format="yyyy-MM-dd"/></td>
										    <td>${getAgreementObj.firstRenewMoney}</td>
										    <td>
										    <s:if test="c.customerOwner != null || c.customerOwner != ''">
										    <ticket:systemCommon type="employeeInfoObj" value="${c.customerOwner}"/>
										    </s:if>
										    <s:else>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id2}"/>
										    </s:else>
										    ${employeeInfoObj.name}
										    </td>
										    <td><span>
										    	<a href="/${actionName}_view.action?id=${c.id }&versionFlag=${versionFlag}">查看</a>
										    	<!-- 
										    	<a href="javascript:;" attrValue="${c.id}" class="singleGiveUpCustomer">放弃客户</a>
										    	 -->
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<a href="/employeeOutVisit_add.action?id=${c.id}&state=1">添加外出拜访记录</a>
										    	<a href="/phoneVisit_add.action?id=${c.id}&state=1">添加电话拜访记录</a>
										    	<a href="/employeeOutVisit_manage.action?customer_id=${c.id}&state=1">管理外出拜访记录</a>
										    	<a href="/phoneVisit_manage.action?customer_id=${c.id}&state=1">管理电话拜访记录</a>
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