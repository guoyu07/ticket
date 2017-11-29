<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/agreement.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理审批客户合同</td>
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
								    <td width="100">创建时间</td>
								    <td width="150">合同编号</td>
								    <td width="120">申请类别</td>
								     <td width="120">申请员工</td>
								    <td width="80">公司名称</td>
								    <td width="100">审批结果</td>
								    <td width="100">审批时间</td>
								    <td width="100">未批复原因</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name}</td>
										    <td>
										     <ticket:systemCommon type="applayClassObj" value="${c.applayClassId}"/>
									    	 ${applayClassObj.name }
										    </td>
										     <td>
										     <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id2}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										   
										    <td>
										      <ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo_id}"/>
									    	 ${channelCustomerInfoObj.name }
										    </td>
										    <td>
										    <s:if test="#c.agreementState == 1">审批通过</s:if>
										    <s:if test="#c.agreementState == 2">审批未通过</s:if>
										    </td>
										    <td><s:date name="#c.auditDate" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.chargeStatus }</td>
										    <td>
										    	<span>
										    	<s:if test="#c.agreementState == 0">
										    		<a href="/${actionName}_sp.action?id=${c.id }&versionFlag=${versionFlag}">审批</a>
										    	</s:if>
										    	<s:if test="#c.agreementState == 1">
										    		<a href="/${actionName}_qh.action?id=${c.id }&versionFlag=${versionFlag}">合同签回</a>
										    	</s:if>
										    	<s:if test="#c.agreementState == 2 || (#c.agreementState >= 3 && #c.secondAuditState == 0)">
										    		<a href="javascript:;" onclick="reapply('${c.id }')">重新申请</a>
										    	</s:if>
										    	<s:if test="#c.effective == 1 && #c.agreementState != 1">
										    		<a href="javascript:;" class="changeEffective" attrValue="${c.id}">取消申请</a>
										    	</s:if>
										    	<s:else>
										    		<a href="javascript:;" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	</s:else>
										    	<ticket:commonAnnex type="queryAnnexList" entityUuid="${c.id}" annexType="1" size="1"/>
												<s:if test="#request.queryAnnexList != null && #request.queryAnnexList.size >0">
													<a href="/downloadAnnex.action?id=${queryAnnexList[0].id}"
													class="b_blue c_white fz18 button">下载合同</a>
												</s:if>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>