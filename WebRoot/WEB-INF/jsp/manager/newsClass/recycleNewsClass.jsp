<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/newsClass.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理新闻类别</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span><a href="#">批量审核</a><a href="#">批量删除</a><a href="#">批量操作</a></span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">会员等级</td>
							    <td width="80">登录名</td>
							    <td width="80">密码</td>
							    <td width="80">真实姓名</td>
							    <td width="80">性别</td>
							    <td width="80">身份证号</td>
							    <td width="80">昵称</td>
							    <td width="80">联系邮箱</td>
							    <td width="80">qq</td>
							    <td width="80">微信号</td>
							    <td width="80">联系地址</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.memberLevel_id }</td>
									    <td>${c.loginName }</td>
									    <td>${c.loginPwd }</td>
									    <td>${c.realName }</td>
									    <td>${c.sex }</td>
									    <td>${c.IDCard }</td>
									    <td>${c.nickName }</td>
									    <td>${c.email }</td>
									    <td>${c.qq }</td>
									    <td>${c.weChatId }</td>
									    <td>${c.address }</td>
									    <td><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="#" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="14">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
					       </td>
					    </tr>
					  </table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>