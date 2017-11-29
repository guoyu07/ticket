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
						<span>编辑广告信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
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
											<s:if test="#advertType.id == advert.advertType_id">
												<option selected="selected" value="${advertType.id }">${advertType.name }</option>
											</s:if>
											<s:else>
												<option value="${advertType.id }">${advertType.name }</option>
											</s:else>
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
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${advert.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*"
									       value="${advert.url}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告链接</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n"
									       value="${advert.status.orderValue}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告排序值</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									缩略图：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${advert.id}" annexType="1" size="1"/>
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
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}1${UPLOAD_SEPARATOR_VALUE}"/>
												</span>
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
												</span>
											</s:iterator>
										</s:if>
						       		</div>
						    		<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									广告内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*">${advert.content}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写广告内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									对应航班详情到达城市：
								</td>
								<td>
									<ticket:common type="airportInfoList"/>
									<ticket:common type="advertFlightListByAdvert" value="${advert.id }"/>
									<input type="checkbox" class="selectCheckBoxAllChks" objectChkName="arriveCitys"/>全选<br/>
									<s:if test="#request.airportInfoList != null">
										<s:iterator value="#request.airportInfoList" var="city">
											<s:set var="exsit" value="false"></s:set>
											<s:if test="#request.advertFlightListByAdvert != null">
												<s:iterator value="#request.advertFlightListByAdvert" var="advertFlight">
													<s:if test="#city.name == #advertFlight.arriveCity">
														<s:set var="exsit" value="true"></s:set>
													</s:if>
												</s:iterator>
											</s:if>
											<s:if test="#exsit == true">
												<input type="checkbox" name="arriveCitys" value="${city.name }" checked="checked"/>${city.name }
											</s:if>
											<s:else>
												<input type="checkbox" name="arriveCitys" value="${city.name }" />${city.name }
											</s:else> 
										</s:iterator>
									</s:if>
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