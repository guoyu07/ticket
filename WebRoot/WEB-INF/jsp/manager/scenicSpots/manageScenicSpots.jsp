<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/scenicSpots.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理旅游景点</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">名称</td>
								    <td width="100">景点图片</td>
								    <td width="80">景点介绍</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.name }</td>
										    <td>
										    	<ticket:commonAnnex type="annex" entityUuid="${c.id}" annexType="1" size="1"/>
										    	<s:if test="#request.annex != null">
										    		<s:iterator id="annex" value="#request.annex" status="st">
										    			<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
										    		</s:iterator>
										    	</s:if>
										    </td>
										    <td>${c.introduce }</td>
										    <td><span>
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<s:if test="#c.status.hot == 1">
											    	<a href="javascript:;" class="hotEntity"  entityName="${entityName}" statusValue="0" value="${c.id }" style="color:#1D9010;" tip="是否取消热门商品？" sucTip="取消热门成功！" failTip="取消热门失败！">取消热门景点</a>
												</s:if>
												<s:else>
												   	<a href="javascript:;" class="hotEntity"  entityName="${entityName}" statusValue="1" value="${c.id }" style="color:#FF0000;" tip="是否设为热门景点？" sucTip="景点热门成功！" failTip="景点热门失败！">设为热门景点</a>
												</s:else>
										    	</span></td>
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