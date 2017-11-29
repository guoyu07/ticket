<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjRefund.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理退款记录</td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">申请时间</td>
								    <td width="80">退款订单</td>
								    <td width="80">支付方式</td>
								    <td width="80">外部订单号</td>
								    <td width="80">退款原因</td>
								    <td width="80">允许退款</td>
								    <td width="80">批注</td>
								    <td width="80">退款时间</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.order.number }</td>
										    <td>${c.order.payMethod.text }</td>
										    <td>${c.order.numberOut }</td>
										    <td>${c.reason }</td>
										    <td>
											    <s:if test="#c.allow != null">
											    	<s:if test="#c.allow">是</s:if>
											    	<s:else>否</s:else>
											    </s:if>
										    </td>
										    <td>${c.remark }</td>
										    <td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td><span>
												<s:if test="#c.success != null">
											    	<s:if test="#c.success">成功</s:if>
											    	<s:else>失败</s:else>
											    </s:if>
										    	<s:elseif test="#c.allow">
										    		<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">重新审批</a>
										    	</s:elseif>
										    	<s:elseif test="#c.allow == null">
										    		<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">审批</a>
										    	</s:elseif>
										    </span></td>
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