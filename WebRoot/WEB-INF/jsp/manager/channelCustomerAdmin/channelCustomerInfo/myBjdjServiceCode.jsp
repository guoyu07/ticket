<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<script type="text/javascript" src="/manager/js/donationServiceCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理我的服务码</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchRequest" url="channelCustomerAdmin_donationBjdjServiceCode.action" redirect="true" value="${actionName}CheckBox">批量分销</a>
					   		<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="10"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="40">服务码</td>
								    <td width="40">状态</td>
								    <td width="60">创建时间</td>
								    <td width="80">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<ticket:systemCommon type="bjdjServiceCodeObj" value="${c.bjdjServiceCode_id}"/>
								  			<td width="20"><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${bjdjServiceCodeObj.code}</td>
										    <td>${bjdjServiceCodeObj.state.value}</td>
										    <td><s:date name="#request.bjdjServiceCodeObj.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>
											    <a href="#" class="singleRequest" url="channelCustomerAdmin_donationBjdjServiceCode.action" redirect="true" id="${c.id }">分销</a>
											    <s:if test="#c.state== 0">
												    <s:if test="#request.bjdjServiceCodeObj.state.name == 'unused'">
												    	<span><a href="/channelCustomerAdmin_activateBjdjServiceCode.action?bjdjServiceCodeId=${c.bjdjServiceCode_id}">激活服务码</a></span>
												    </s:if>
												    <s:else>
												    	使用人员:${bjdjServiceCodeObj.member.realName}
												    </s:else>
											    </s:if>
											    <s:else>
											    	<s:if test="#request.bjdjServiceCodeObj.state.name == 'unused'">
												    	<span><a href="/channelCustomerAdmin_activateBjdjServiceCode.action?bjdjServiceCodeId=${c.bjdjServiceCode_id}">激活服务码</a></span>
												    </s:if>
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
					  <%@ include file="/WEB-INF/jsp/manager/common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>