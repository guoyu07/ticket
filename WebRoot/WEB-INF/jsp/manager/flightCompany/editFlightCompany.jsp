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
						<span>编辑航空公司信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									公司名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${flightCompany.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司二字码：
								</td>
								<td>
									<input id="twoCode" name="twoCode" class="my_input" datatype="*"
									       value="${flightCompany.twoCode}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司二字码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司三字码：
								</td>
								<td>
									<input id="threeCode" name="threeCode" class="my_input" datatype="*"
									       value="${flightCompany.threeCode}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司三字码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司电话：
								</td>
								<td>
									<textarea id="phone" name="phone" class="my_input" datatype="*">
										${flightCompany.phone}
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写公司电话</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									官网：
								</td>
								<td>
									<input id="theOfficialWebsite" name="theOfficialWebsite" class="my_input" datatype="*"
									       value="${flightCompany.theOfficialWebsite}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写官网</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									散客柜台：
								</td>
								<td>
									<input id="customerCounter" name="customerCounter" class="my_input" ignore="ignore"
									       value="${flightCompany.customerCounter}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写散客柜台</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									团队柜台：
								</td>
								<td>
									<input id="teamCounter" name="teamCounter" class="my_input" ignore="ignore"
									       value="${flightCompany.teamCounter}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写团队柜台</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									头等舱柜台：
								</td>
								<td>
									<input id="firstClassCounter" name="firstClassCounter" class="my_input" ignore="ignore"
									       value="${flightCompany.firstClassCounter}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写头等舱柜台</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									应急柜台：
								</td>
								<td>
									<input id="emergencyCounter" name="emergencyCounter" class="my_input" ignore="ignore"
									       value="${flightCompany.emergencyCounter}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写应急柜台</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									航空公司排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="${flightCompany.status.orderValue }" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 提示：排序值越大，越靠前！</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									公司图标：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${flightCompany.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<s:set name="fId" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1, #annex.annexPath.indexOf('.'))" />
												<s:set name="fName" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1)" />
												<s:set name="fDirectory" value="#annex.annexPath.substring(#annex.annexPath.indexOf('/')+1, #annex.annexPath.lastIndexOf('/'))" />
												<span id="span${fId }">
												<span style="position: absolute; z-index: 10; float: right;">
													<a href="#" onClick="delImgUpdate('${fId}','${fName }');return false;">
														<img src="/common/jQueryUpload/cancel.png" border="0" />
													</a>
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}2${UPLOAD_SEPARATOR_VALUE}"/>
												</span>
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
												</span>
											</s:iterator>
										</s:if>
						       		</div>
						    		<input type="file" id="logo" name="logo" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									经度：
								</td>
								<td>
									<input id="longitude" name="longitude" class="my_input" ignore="ignore"
									       value="${flightCompany.longitude}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写经度</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									纬度：
								</td>
								<td>
									<input id="latitude" name="latitude" class="my_input" ignore="ignore"
									       value="${flightCompany.latitude}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写纬度</p>       
								</td>
							</tr>
							<tr>
								<td>自助值机点：&nbsp;&nbsp;<a href="javascript:addPosition();">+</a></td>
								<td id='jwdu'>
									<s:if test="#poiList != null">
										<s:iterator value="#poiList" var="poi">
											<p>
											经度：<input class="selfLongitude" name="selfLongitude" value="${poi.longitude }"/>
											纬度：<input class="selfLatitude"  name="selfLatitude" value="${poi.latitude }" />
											楼层号：<input class="floorNumber"  name="floorNumber" value="${poi.floorNumber }" />
											单/多点位名称:<input class="className"  name="positionName" value="${poi.name }" />
											<a href="javascript:;" class="removePoi">移除</a></p>
										</s:iterator>
									</s:if>
									<s:else>
									
									</s:else>
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