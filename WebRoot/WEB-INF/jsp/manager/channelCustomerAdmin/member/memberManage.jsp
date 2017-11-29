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
					      <td >管理我的会员</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      <span>
					      	<form action="/channelCustomerAdmin_memberManage.action" method="post">
					   		<input type="text" 
								class="my_input" placeholder='请输入搜索关键词' id="keyword" name="keyword" value="${keyword}" style="width:320px;height:23px">
					      	<input type="submit" value="检索"  class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	</form>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">用户名</td>
								    <td width="80">昵称</td>
								    <td width="80">手机</td>
								    <td width="80">邮箱</td>
								    <td width="80">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td>${c.loginName}</td>
										    <td>${c.nickName}</td>
										    <td>${c.phone}</td>
										    <td>${c.email}</td>
										    <td><span><a href="/channelCustomerAdmin_memberUpdate.action?id=${c.id}">编辑</a></span></td>
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
					  <%@ include file="memberPage.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>