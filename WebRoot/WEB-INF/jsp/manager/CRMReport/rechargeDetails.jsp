<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td>销售CRM-充值详细信息</td>
					    <tr>
					      <td>
							<table id="table" class="my_table100">
							  <tr class="my_table_top1">
							    <td width="100">充款时间</td>
							    <td width="100">充款金额</td>
							    <td width="100">充款方式</td>
							    <td width="100">开票金额</td>
							    <td width="100">开票时间</td>
							    <td width="100">未开票金额</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.amountOfMoney }</td>
									    <td>${c.payWay }</td>
									    <td>0</td>
									    <td></td>
									    <td>${c.amountOfMoney }</td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="711">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
							<div class="my_table_list_r">
							    <input id="returnBtn" type="button" value="返回" class="btn_submit margin_left20">
							</div>
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