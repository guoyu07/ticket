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
					<div class="my_table_list_nav">
						<span>新增旅游景点</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											景点图片：
										</td>
										<td>
											<div id="fileQueue"></div>
											<div style="clear: both;">
											<input type="file" id="picture" name="picture"
											style="width: 200px;" tabindex="1" />
											</div>
											<span id="pictureTip" style="height: 22px;"></span>
											<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											景点介绍：
										</td>
										<td>
											<textarea id="introduce" name="introduce" class="my_input" datatype="*">
											</textarea>
											<!-- <input id="introduce" name="introduce" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请填写景点介绍</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											景点详情：
										</td>
										<td>
											<textarea id="detail" name="detail" class="my_input" datatype="*"></textarea>
											<!-- <input id="detail" name="detail" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请填写景点详情</p>
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