<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemUser.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理系统管理员</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      		<a href="#" class="batchRealDeleteBtn" value="${actionName }CheckBox" entityName="${entityName }">批量删除</a>
					      		<a href="#" class="batchRestoreBtn" value="${actionName }CheckBox" entityName="${entityName }">批量还原</a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							    <td width="20" class="text_align_center"><input id="selectCheckBoxAllChk" objectChkName="${actionName }CheckBox" type="checkbox"></td>
							    <td width="40" class="text_align_center">编号</td>
							    <td width="80" class="text_align_center">创建时间</td>
							    <td width="80" class="text_align_center">登陆名</td>
							    <td width="80" class="text_align_center">登陆密码</td>
							    <td width="80" class="text_align_center">姓名</td>
							    <td width="80" class="text_align_center">性别</td>
							    <td width="80" class="text_align_center">电话</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td class="text_align_center"><input name="${actionName }CheckBox" type="checkbox" value="${c.id }"></td>
									    <td class="text_align_center">${st.count }</td>
									    <td class="text_align_center"><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td class="text_align_center">${c.loginId }</td>
									    <td class="text_align_center">${c.password }</td>
									    <td class="text_align_center">${c.name }</td>
									    <td class="text_align_center">
									    	<s:if test="#c.sex == 1">男性</s:if>
									    	<s:else>女性</s:else>
									    </td>
									    <td class="text_align_center">
									    	${c.phone }
									    </td>
									    <td class="text_align_center"><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="#" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
									    	</span></td>
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
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>