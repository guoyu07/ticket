<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/advert.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增广告信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									广告类型：
								</td>
								<td>
									<select id="advertType_id" name="advertType_id" class="my_select" datatype="*">
									<option value="">请选择广告类型</option>
									<ticket:systemCommon type="advertTypeList"/>
									<s:if test="#request.advertTypeList != null">
										<s:iterator id = "advertType" value="#request.advertTypeList">
											<option value="${advertType.id }">${advertType.name }</option>
										</s:iterator>
									</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告链接</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告排序值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告图片：
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
									广告内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告内容</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									对应航班详情到达城市：
								</td>
								<td>
									<input type="checkbox" class="selectCheckBoxAllChks" objectChkName="arriveCitys"/>全选<br/>
									<ticket:common type="airportInfoList"/>
									<s:if test="#request.airportInfoList != null">
										<s:iterator value="#request.airportInfoList" var="city">
											<input type="checkbox" name="arriveCitys" value="${city.name }"/>${city.name }
										</s:iterator>
									</s:if>
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