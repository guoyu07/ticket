<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理充值记录</td>
					    </tr>
					    <tr  style="text-align: center">
					      <td><span>
					   		<form >
					      		公司名称或用户名:<input name="keyword" type="text" />
							  			<br/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
					      	</form>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">充值编号</td>
								    <td width="80">充值时间</td>
								    <td width="80">公司名称</td>
								    <td width="80">充值金额</td>
								    <td width="80">赠送金额</td>
								    <td width="80">客户用户名</td>
								    <td width="80">所属员工</td>
								    <td width="80">渠道类别</td>
								    <td width="80">实际到账金额</td>
								    <td width="80">操作人员</td>
								     <td width="80">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td>${c.recordNo }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>
										    <ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo.id}"/>
										    ${channelCustomerInfoObj.name}
										    </td>
										    <td>${c.amountOfMoney }</td>
										    <td>${c.songMoney}</td>
										    <td>${channelCustomerInfoObj.loginId}</td>
										    <ticket:systemCommon type="employeeByCustomerId" value="${c.channelCustomerInfo.id}"/>
										    <td>${employeeByCustomerId.name }</td>
										    <ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfoObj.channelType.id}"/>
										    <td>${channelTypeObj.name }</td>
										    <td>${c.amountOfMoney +c.songMoney}</td>
										    <td>
										    <ticket:systemCommon type="getSystemUserObj" value="${c.systemUser.id}"/>
										    ${getSystemUserObj.name}
										    </td>
										    <td>
										   		<s:if test="#c.state == 2">
										   			<a href="javascript:;" attrValue="${c.id}" class="tykp">申请开发票,点击同意开票</a>
										   		</s:if>
										   		<s:if test="#c.state == 1">
										   			已开发票
										   		</s:if>
										   		<s:if test="#c.state == 0 || #c.state == null || #c.state == ''">
										   			未开发票
										   			<a href="receipt_add.action?channelCustomerInfoId=${c.channelCustomerInfo.id}&amountOfMoney=${c.amountOfMoney }">开发票</a>
										   		</s:if>
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
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>