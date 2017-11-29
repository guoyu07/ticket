<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/newsClass.js"></script>
	<script type="text/javascript">
	$(function(){
		d = new dTree('d');
		${newsClassHtml}
		$("#showTree").html(d + "");
		$(".dTreeNode").hover(function(){
			$(this).css("background","#f5f5f5");
		},function(){
			$(this).css("background","fff");
		})
	});
	</script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100_tree margin_top10">
						<s:if test="permissionId != null">
							<tr  class="text_align_left my_table_top">
						      <td>管理图片系列</td>
						    </tr>
						    <tr  class="text_align_left">
						      <td>
						      <span>
						      </span></td>
						    </tr>
						    <tr>
						      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">创建时间</td>
								    <td width="80">系列名称</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name }</td>
										    <td><span>
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}&newsClass_id=${newsClass_id}">编辑</a>
										    	</span></td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="8">${noDataMessage }</td>
									  </tr>
								  </s:else>
							</s:if>
							<s:else>
							<tr class="text_align_left my_table_top">
								<td>
									<span>
										 <a href="javascript: d.openAll();">展开所有</a> 
										 <a href="javascript: d.closeAll();">收起所有</a>
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<div id="showTree" style="padding-top:10px; padding-left: 0px; padding-right: 0px;"></div>
								</td>
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