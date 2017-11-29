<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/trafficType.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增交通路线类型</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									路线类别名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写路线类别名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									路线类别介绍：
								</td>
								<td>
									<textarea id="introduce" name="introduce" class="my_input" ignore ="ignore" style="width: 100%;height: 240px">
									
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写路线类别介绍</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="0" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写排序值，值越大，越靠前！</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="200px">
									前端显示路线还是详情：
								</td>
								<td>
									<select id="hot" name="hot" class="my_select"/>
										<option value="1">路线</option>
										<option value="0">详情</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>地图选点</td>
								<td><div id="positionMap"  style="height: 240px;width: 50%"></div></td>
							</tr>
							<tr>
								<td>您选择的是</td>
								<td>经度：<input id="longitude" name="longitude"/>
									纬度：<input id="latitude"  name="latitude"/>
									楼层：<input id="floorNumber"  name="floorNumber"/>
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