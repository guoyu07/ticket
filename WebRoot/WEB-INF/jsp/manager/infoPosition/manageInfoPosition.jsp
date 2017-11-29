<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/infoPosition.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理机场设施位置</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
					    	<form action="${actionName }_manage.action?versionFlag=site" method="get">
					    		别名或名称：<input type="text" name="keyword" value="${keyword }"/>
					    		<br/>
					  			<input type="submit" value="查询"/>
					    	</form>
					    </td>
					    <tr class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a target="_blank" href="${actionName }_downReport.action?keyword=${keyword }">下载</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">设施别名</td>
							    <td width="80">设施名称</td>
							    <td width="80">经度</td>
							    <td width="80">纬度</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.positionAlias }</td>
									    <td>${c.name }</td>
									    <s:if test="#c.longitude.length() > 15">
									    	<td><s:property value="#c.longitude.substring(0,15)+'...'" /> </td>
									    </s:if>
									    <s:else>
									    	<td>${c.longitude }</td>
									    </s:else>
									    <s:if test="#c.latitude.length() > 15">
									    	<td><s:property value="#c.latitude.substring(0,15)+'...'" /> </td>
									    </s:if>
									    <s:else>
									    	<td>${c.latitude }</td>
									    </s:else>
									    <td><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
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