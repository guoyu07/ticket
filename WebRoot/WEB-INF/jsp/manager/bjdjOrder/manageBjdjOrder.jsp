<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjOrder.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理便捷登机订单表</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
						    <form action="${actionName }_manage.action?versionFlag=site" method="get">
					    		下单时间：<input type="text" name="startTime" onclick="new WdatePicker();" value='<s:date name="startTime" format="yyyy-MM-dd" />'/>&nbsp;&nbsp;~&nbsp;&nbsp;
					    		<input type="text" name="endTime" onclick="new WdatePicker();" value='<s:date name="endTime" format="yyyy-MM-dd" />'/>
					    		支付时间：<input type="text" name="startTime2" onclick="new WdatePicker();" value='<s:date name="startTime2" format="yyyy-MM-dd" />'/>&nbsp;&nbsp;~&nbsp;&nbsp;
					    		<input type="text" name="endTime2" onclick="new WdatePicker();" value='<s:date name="endTime2" format="yyyy-MM-dd" />'/>
					    		状态：
					    		<select name="state">
				    				<option value="">所有</option>
					    			<s:iterator var="dict" value="dictionaryService.querySubByParentName('order_state')">
					    				<option value="${dict.id }" ${state==dict.id ? 'selected="selected"' : null }>${dict.value }</option>
					    			</s:iterator>
					    		</select>
								<br/>
					    		商品名称：<input type="text" name="name" value="${name}" />
					    		订单号：<input type="text" name="number" value="${number }"/>
					    		服务码：<input type="text" name="serviceCode" value="${serviceCode }"/><br/>
					  			<input type="submit" value="查询"/>
					    	</form>
				    	</td></tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a target="_blank" href="${actionName }_downReport.action?name=${name }&phone=${phone }&loginId=${loginId }&IDCard=${IDCard }&startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />">下载</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">订单号</td>
								    <td width="80">下单用户</td>
								    <td width="120">下单时间</td>
								    <td width="80">商品名称</td>
								    <td width="80">状态</td>
								    <td width="80">数量</td>
								    <td width="80">单价</td>
								    <td width="80">支付时间</td>
								    <td width="80">实付金额</td>
								    <td width="80">优惠金额</td>
								    <td width="80">消耗积分</td>
								    <td width="80">服务码</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.number }</td>
										    <td>${c.member.phone }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.name }</td>
										    <td>${c.state.value}</td>
										    <td>${c.count }</td>
										    <td>${c.price }</td>
										    <td>${c.payTime }</td>
										    <td>${c.realPay }</td>
										    <td>${c.discountAmount }</td>
										    <td>${c.consumptionIntegral }</td>
										    <td>
										    	<s:iterator value="#c.serviceCodes" var="serviceCode" status="st">
										    		${code }
										    		<s:if test="!st.last">
										    			<br/>
										    		</s:if>
										    	</s:iterator>
										    </td>
										    <td>
										    	<span>
											    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	</span>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="20">${noDataMessage }</td>
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