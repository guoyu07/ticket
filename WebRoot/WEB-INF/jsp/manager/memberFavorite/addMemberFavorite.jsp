<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/memberFavorite.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增会员收藏</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											会员id：
										</td>
										<td>
											<input id="member_id" name="member_id" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写会员id</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											收藏对象id：
										</td>
										<td>
											<input id="objectId" name="objectId" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写收藏对象id</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											收藏类型：
										</td>
										<td>
											<input id="objectType" name="objectType" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写收藏类型</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											收藏标题：
										</td>
										<td>
											<input id="Title" name="Title" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写收藏标题</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											收藏链接：
										</td>
										<td>
											<input id="url" name="url" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写收藏链接</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>