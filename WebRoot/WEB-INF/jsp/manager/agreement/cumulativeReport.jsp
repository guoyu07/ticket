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
					    <tr  class="text_align_left my_table_top">
					      <td >已签约累计报表</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<%-- <td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td> --%>
								    <td width="80">客户总数</td>
								    <td width="80">新增客户总数</td>
								    <td width="80">总充值金额</td>
								    <td width="80">服务码购买数</td>
								    <td width="80">购买金额</td>
								    <td width="80">服务码激活数</td>
								    <td width="80">服务码验证数</td>
								    <td width="80">外出拜访数</td>
								    <td width="80">电话拜访数</td>
								    <td width="80">本月业绩任务</td>
								    <td width="80">本月累计完成数</td>
								    <td width="80">完成进度</td>
								  </tr>
								  		<tr>
								  			<%-- <td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td> --%>
										   
										    <td>${kehuCount }</td>
										    <td>${kehuAddCount }</td>
										    <td>${czMoney }</td>
										    <td>${buyCount }</td>
										    <td>${paidAmount }</td>
										    <td>${activeCount }</td>
										    <td>${validationCount }</td>
										    <td>${visitCount }</td>
										    <td>${phoneCount }</td>
										    <td>${taskMoney }</td>
										    <td>${completeMoney }</td>
										    <td>
										    	${fn:substring(progress,0,4) }%
										    </td>
										 </tr>
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