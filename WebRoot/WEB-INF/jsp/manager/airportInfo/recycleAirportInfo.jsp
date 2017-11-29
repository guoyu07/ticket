<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/airportInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理机场信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      		<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      		<a href="#" class="batchRestoreBtn" value="${actionName}CheckBox" entityName="${entityName}">批量还原</a>
					      </span></td>
					    </tr>
					      <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
								    <tr>
								      <td>
										<table class="my_table100">
										  <tr class="my_table_top1">
										    <td width="20"><input name="" type="checkbox" value=""></td>
										    <td width="80">创建时间</td>
										    <td width="80">机场名称</td>
										    <td width="80">三字码</td>
										    <td width="80">四字码</td>
										    <td width="150" class="text_align_center">操作</td>
										  </tr>
										  <tr>
										    <td><input name="" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name }</td>
										    <td>${c.threeCode }</td>
										    <td>${c.fourCode }</td>
										    <td><span>
										    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
												<a href="#" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
										    </span></td>
										  </tr>
										</table>
								       </td>
								    </tr>
							    </s:iterator>
						  </s:if>
						  <s:else>
						  	 <tr>
							    <td colspan="7">${noDataMessage }</td>
							  </tr>
						  </s:else>
					  </table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>