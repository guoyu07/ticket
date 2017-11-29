<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/businessEvent.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理商家活动</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<%-- <a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a>
					      	<a><input id="isChecked" type="checkbox"/></a> --%>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">创建时间</td>
								    <td width="80">活动名称</td>
								    <td width="80">活动开始时间</td>
								    <td width="80">活动结束时间</td>
								    <td width="80">活动内容</td>
								    <td>活动图片</td>
								    <td>有效期</td>
								    <td>状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name}</td>
										    <td><s:date name="#c.startTime" format="yyyy-MM-dd"/></td>
										    <td><s:date name="#c.endTime" format="yyyy-MM-dd"/></td>
										    <td>${c.content }</td>
										    <td>
										    	<ticket:commonAnnex type="annex" entityUuid="${c.id }" size="1" annexType="1"/> 
										    	<s:if test="#request.annex != null">
										    		<img src="${annex.annexPath }" width="150" height="150"/>
										    	</s:if>
										    </td>
										    <td>
										    	<ticket:common type="businessEventExpiry" value="${c.id }"/>
										    	<s:if test="#request.businessEventExpiry == 1">有效</s:if>
										    	<s:elseif test="#request.businessEventExpiry == 2">未开始</s:elseif>
										    	<s:else>已过期</s:else>
										    </td>
										    <td>${c.freezeStatus.text }</td>
										    <td><span>
										    	<%-- <a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a> --%>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
										    	<s:if test="#c.freezeStatus != @com.ticket.enumer.FreezeStatus@freeze">
										    		<a id="freeze" href="javascript:freeze();" attrId="${c.id }">暂停/冻结</a>
										    	</s:if>
										    	<s:else>
										    		<a id="actived" href="javascript:actived();" attrId="${c.id }">启用/解冻</a>
										    	</s:else>
										    	</span>
										    </td>
										    	
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