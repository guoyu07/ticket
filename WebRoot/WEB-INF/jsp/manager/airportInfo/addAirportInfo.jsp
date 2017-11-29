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
					<div class="my_table_list_nav">
						<span>新增机场信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									机场名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写机场名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									英文名或拼音：
								</td>
								<td>
									<input id="englishName" name="englishName" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写机场英文名或拼音</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									三字码：
								</td>
								<td>
									<input id="threeCode" name="threeCode" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写三字码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									四字码：
								</td>
								<td>
									<input id="fourCode" name="fourCode" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写四字码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									国家代码：
								</td>
								<td>
									<input id="countryCode" name="countryCode" class="my_input" nullmsg="请填写国家代码，例中国填写：CN" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;">(例：中国应填写CN)</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									地区标识：
								</td>
								<td>
									<select id="districtFlag" name="districtFlag" class="my_input" datatype="*">
										<option value="D">中国大陆</option>
										<option value="R">港澳台</option>
										<option value="I">国外</option>
									</select>
									<p class="Validform_checktip" style="display:inline;">请选择地区</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									字母搜索标识：
								</td>
								<td>
									<input id="searchFlag" name="searchFlag" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;">英文名或拼音首字母</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="" onclick="javascript:history.go(-1);" type="button" value="取消" class="btn_remove margin_left20">
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