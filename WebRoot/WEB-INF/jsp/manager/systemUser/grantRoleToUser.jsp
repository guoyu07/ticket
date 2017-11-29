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
					<input type="hidden" id="userId" name="userId" value="${id }"/>
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >给用户授予角色</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  	<tr class="my_table_top1">
							    	<td width="80" class="text_align_center">角色名</td>
							    	<td width="80" class="text_align_center">选择角色</td>
							  	</tr>
							  	<ticket:systemCommon type = "roleList"/>
							  		<s:if test="#request.roleList != null">
							  			<s:iterator id="roleInfo" value="#request.roleList" status="st">
							  				<tr>
											    <td class="text_align_center">
													${roleInfo.name }
												</td>
											    <td><span>
											    	<ticket:systemCommon type="userRoleObj" userId="${systemUser.id}" roleId="${roleInfo.id}"/>
											    		<s:if test="#request.userRoleObj != null">
											    			<input type="checkbox" id="checkbox${st.count }" class="selectedRole" roleId="${roleInfo.id }"/>
											    		</s:if>
											    		<s:else>
											    			<input type="checkbox" id="checkbox${st.count }" class="selectRole" roleId="${roleInfo.id }"/>
											    		</s:else>
											    	</span>
											    </td>
										  </tr>
							  			</s:iterator>
							  		</s:if>
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