<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/member.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理会员信息</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manage.action?versionFlag=site" method="get">
							  	创建时间:<input name="startTime" type="text" onclick="new WdatePicker();" value='<s:date name="startTime" format="yyyy-MM-dd" />'/>
						  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endTime" type="text" onclick="new WdatePicker();" value='<s:date name="endTime" format="yyyy-MM-dd" />'/>
							  			&nbsp;&nbsp;&nbsp;&nbsp;
					  					<input type="submit" value="查询"/>
							</form>
						</td></tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchDownLoadBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="batchDownLoad">全部下载</a>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a>
					      	<%-- <a href="${actionName }_batchDownLoad.action?startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />">下载</a> --%>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">会员姓名</td>
							    <td width="80">会员等级</td>
							    <td width="80">联系电话</td>
							    <td width="80">身份证号</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.nickName }</td>
									    <td>
											<ticket:systemCommon type="memberLevelObj" value="${c.memberLevel_id}"/>
											${memberLevelObj.name }	
										</td>
									    <td>${c.phone }</td>
									    <td>${c.IDCard}</td>
									    <td><span>
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>