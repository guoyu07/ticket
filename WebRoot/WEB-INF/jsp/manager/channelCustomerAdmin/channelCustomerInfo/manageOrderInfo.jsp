<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理购买记录</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					   
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">订单编号</td>
								    <td width="80">订单类型</td>
								    <td width="80">购买时间</td>
								    <td width="80">单价</td>
								    <td width="80">数量</td>
								    <td width="80">合计</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td>${c.orderId }</td>
										    <td>
										    <s:if test="#c.orderType == 0">购买订单</s:if>
										    <s:else>赠送订单</s:else>
										    </td>
										    <td><s:date name="#c.payTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.goodsPrice}</td>
										    <td>${c.buyCount}</td>
										    <td>${c.paidAmount}</td>
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