<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjValidation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理电瓶车验证表</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<input type="text" value="请输入服务码" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword" value="${keyword}" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<a href="#" class="batchPaidanBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="batchPaidan">批量派单</a>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="40">编号</td>
								    <td width="80">验证时间</td>
								    <td width="80">服务码</td>
								    <td width="80">服务人</td>
								    <td width="80">是否验证通过</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.serviceCode.code }</td>
										    <td>${c.employee.name }</td>
										    <td>${c.passed==true?"通过":"不通过" }</td>
										    <td>
										    	<s:if test="!dispatchService.isDispatch(#c.id)">
										    	<a href="bjdjDispatch_addForElectromobile.action?id=${c.id }" class="" entityName="${entityName}" value="">分单</a>
										    	</s:if>
										    	<!-- <a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a> -->
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="10">${noDataMessage }</td>
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