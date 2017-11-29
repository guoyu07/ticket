<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理电瓶车分单表</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<input type="text" value="请输入服务码" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword" value="${keyword}" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1" align="center">
								  	<td colspan="2" width="80">基本信息</td>
								  	<td colspan="2" width="80">服务人员安排</td>
								  </tr>
								  <tr class="my_table_top1" align="center">
								    <td>日期</td>
								    <td>服务码</td>
								    <s:iterator value="#items" var="item">
								    <td>${item.value }</td>
								    <td>状态</td>
								    </s:iterator>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td ><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td >${c.validation.serviceCode.code }</td>
										    <s:iterator value="#c.dispatchList" var="dl">
										    <td>${dl.employee.name }</td>
										    </s:iterator>
										    <td >
											    <s:if test="#c.ended == true">
											    结束
										    	</s:if>
										    	<s:else>
											    进行中
										    	</s:else>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="11">${noDataMessage }</td>
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