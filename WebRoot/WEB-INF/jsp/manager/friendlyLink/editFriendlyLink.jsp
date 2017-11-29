<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/friendlyLink.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑友情链接</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="businessInfo_id" id="businessInfo_id" value="${friendlyLink.businessInfo_id}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									商家名称：
								</td>
								<td>
									<label >
										<ticket:systemCommon type="businessInfoObj" value="${friendlyLink.businessInfo_id}"/>
										${businessInfoObj.name }
									</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									链接名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${friendlyLink.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写链接名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									链接地址：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*"
									       value="${friendlyLink.url}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写链接地址</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}&businessInfo_id=${friendlyLink.businessInfo_id}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>