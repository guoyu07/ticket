<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjPage.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理便捷登机跳转页面</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<%-- <a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a> --%>
					      	<%-- <a href="#" class="realDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a> --%>
					      	<%-- <a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a> --%>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="290'">id</td>
								    <td width="80">便捷登机页面名称</td>
								    <td width="80">页面编号</td>
								    <td width="80">服务电话号码</td>
								    <td width="80">导航点位别名</td>
								    <td width="80">服务流程</td>
								    <td width="80">服务简介</td>
								    <td width="80">使用条款</td>
								    <td width="80">支付成功</td>
								    <td width="80">首页图片</td>
								    <td width="80">短信模板</td>
								    <td width="80">激活成功</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.id }</td>
										    <td>${c.name }</td>
										    <td>${c.url }</td>
										    <td>${c.servicePhone }</td>
										    <td>${c.infoPositionAlias }</td>
										    <td>${c.serviceFlow.title }</td>
										    <td>${c.serviceProfile.title }</td>
										    <td>${c.useSerms.title }</td>
										    <td>${c.paySuccess.name }</td>
										    <td>${c.advertType.name }</td>
										    <td>${c.smsTemplate.name }</td>
										    <td>${c.activeSuc.name }</td>
										    <td><span>
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="100">${noDataMessage }</td>
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