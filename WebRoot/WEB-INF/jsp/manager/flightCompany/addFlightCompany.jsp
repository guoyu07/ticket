<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/flightCompany.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增航空公司信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司二字码：
								</td>
								<td>
									<input id="twoCode" name="twoCode" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司二字码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司三字码：
								</td>
								<td>
									<input id="threeCode" name="threeCode" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司三字码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司电话：
								</td>
								<td>
									<textarea id="phone" name="phone" class="my_input" datatype="*">
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									官网：
								</td>
								<td>
									<input id="theOfficialWebsite" name="theOfficialWebsite" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写官网</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									散客柜台：
								</td>
								<td>
									<input id="customerCounter" name="customerCounter" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写散客柜台</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									团队柜台：
								</td>
								<td>
									<input id="teamCounter" name="teamCounter" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写团队柜台</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									头等舱柜台：
								</td>
								<td>
									<input id="firstClassCounter" name="firstClassCounter" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写头等舱柜台</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									应急柜台：
								</td>
								<td>
									<input id="emergencyCounter" name="emergencyCounter" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写应急柜台</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;">提示：排序值越大，越靠前！</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司logo：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									 	<input type="file" id="logo" name="logo" style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									经度：
								</td>
								<td>
									<input id="longitude" name="longitude" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写经度</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									纬度：
								</td>
								<td>
									<input id="latitude" name="latitude" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写纬度</p>
								</td>
							</tr>
							<tr>
								<td>自助值机点：&nbsp;&nbsp;<a href="javascript:addPosition();">+</a></td>
								<td id='jwdu'><%--
									经度：<input class="longitude" name="longitude" class="my_input" datatype="*"/>
									纬度：<input class="latitude"  name="latitude" class="my_input" datatype="*"/>
									楼层号：<input class="floorNumber"  name="floorNumber" class="my_input" datatype="*"/>
									单/多点位名称:<input class="positionName"  name="positionName" class="my_input" datatype="*"/>
									<a href="javascript:;" onclick="removeP(this);">移除</a>
									</p>
								--%></td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="resetBtn" type="button" onclick='javascript:window.location.href="/${actionName}_manage.action?versionFlag=${versionFlag}"' value="取消" class="btn_remove margin_left20">
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