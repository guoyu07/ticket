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
						<span>编辑机场信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									机场名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${airportInfo.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写机场名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									英文名：
								</td>
								<td>
									<input id="englishName" name="englishName" class="my_input" datatype="*"
									       value="${airportInfo.englishName}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写英文名</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									三字码：
								</td>
								<td>
									<input id="threeCode" name="threeCode" class="my_input" datatype="*"
									       value="${airportInfo.threeCode}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写三字码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									四字码：
								</td>
								<td>
									<input id="fourCode" name="fourCode" class="my_input" datatype="*"
									       value="${airportInfo.fourCode}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写四字码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									国家代码：
								</td>
								<td>
									<input id="countryCode" name="countryCode" class="my_input" datatype="*"
									       value="${airportInfo.countryCode}"/>
									<p class="Validform_checktip" style="display:inline;">请填写国家代码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									地区标识：
								</td>
								<td>
									<select id="districtFlag" name="districtFlag" currentValue="${airportInfo.districtFlag}" class="my_input" datatype="*">
										<option value="D">中国大陆</option>
										<option value="R">港澳台</option>
										<option value="I">国外</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写地区标识</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									字母搜索标识：
								</td>
								<td>
									<input id="searchFlag" name="searchFlag" class="my_input" datatype="*"
									       value="${airportInfo.searchFlag}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写字母搜索标识</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
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