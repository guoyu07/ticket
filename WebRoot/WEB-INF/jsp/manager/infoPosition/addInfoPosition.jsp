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
					<div class="my_table_list_nav">
						<span>新增位置点</span>
					</div>
					<form action="" id="memberForm2" name="memberForm2" onsubmit="javascript:return false;">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									位置别名：
								</td>
								<td>
									<input id="positionAlias" name="positionAlias" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写位置别名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									位置名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写位置名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									电话：
								</td>
								<td>
									<input id="mobile" name="mobile" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写链接</p>
								</td>
							</tr>
							<!-- <tr>
								<td class="text_align_right" width="150">
									楼层号：
								</td>
								<td>
									<input id="floorNumber" name="floorNumber" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写楼层号</p>
								</td>
							</tr> -->
							<tr>
								<td>地图选点</td>
								<td><div id="positionMap"  style="height: 240px;width: 50%"></div></td>
							</tr>
							<tr>
								<td>您选择的是&nbsp;&nbsp;<a href="javascript:addInfoPosition();">+</a></td>
								<td id='jwdu'>
									<p>
									经度：<input class="longitude" name="longitude"/>
									纬度：<input class="latitude"  name="latitude"/>
									楼层号：<input class="floorNumber"  name="floorNumber"/>
									单/多点位名称:<input class="className"  name="className" datatype="*"/>
									<a href="javascript:;" onclick="removeP(this);">移除</a>
									</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="add" type="button" value="提交" class="btn_submit">
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