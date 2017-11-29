<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/serviceBook.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理服务订单</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      	<input type="text" value="请输入订单联系电话关键词"					      		
					      	onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 					      		
					      	onBlur="if(!value){value=defaultValue;this.style.color='#999'}"					
					      	class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">	
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      <span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">会员名</td>
							    <td width="80">身份证号</td>
							    <td width="80">联系电话</td>
							    <td width="80">航班号</td>
							    <td width="80">数量</td>
							    <td width="80">使用时间</td>
							    <td width="80">服务码</td>
							    <td width="80">支付状态</td>
							    <td width="80">付款方式</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.member_id }</td>
									    <td>${c.idCard }</td>
									    <td>${c.phone }</td>
									    <td>${c.flightNumber }</td>
									    <td>${c.bookAmount }</td>
									    <td><s:date name="#c.useTime" format="yyyy-MM-dd"/></td>
									    <td>${c.serviceKey }</td>
									    <td>
									    	<s:if test="#c.payStatus == 1">已支付</s:if> 
									    	<s:else>未支付</s:else>
									    </td>
									    <td>${c.payWay }</td>
									    <td><span>
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="12">${noDataMessage }</td>
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