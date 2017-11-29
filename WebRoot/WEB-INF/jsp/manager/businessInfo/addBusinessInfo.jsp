<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/businessInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增商家信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									商业类别：
								</td>
								<td>
									<select name="businessType_id" id="businessType_id" class="my_select" datatype="*" nullmsg="请选择类别">
										<option value="">请选择商家类别</option>
											${classTree }
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择商家类别</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									商家名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写商家名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									楼层：
								</td>
								<td>
									<ticket:systemCommon type="systemDictionayList" value="businessInfo_lc"/>
									<select name="lc" class="my_input">
										<option value="">请选择楼层</option>
										<s:iterator id="d" value="#request.systemDictionayList">
										<option value="${d.id}">${d.name}</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择商家所在楼层</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									位置：
								</td>
								<td>
									<ticket:systemCommon type="systemDictionayList" value="businessInfo_wz"/>
									<select name="wz" class="my_input">
										<option value="">请选择位置</option>
										<s:iterator id="d" value="#request.systemDictionayList">
										<option value="${d.id}">${d.name}</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择商家所在楼层位置</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									商家图片：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									商家简介：
								</td>
								<td>
									<textarea id="introduce" name="introduce" class="my_input" datatype="*" ignore="ignore">
									</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									主营商品：
								</td>
								<td>
									<input id="mainSale" name="mainSale" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写主营商品</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									营业时间：
								</td>
								<td>
									<input id="businessHours" name="businessHours" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写营业时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									活动预告：
								</td>
								<td>
									<textarea id="activityForecast" name="activityForecast" class="my_input" datatype="*" ignore="ignore">
									</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系地址：
								</td>
								<td>
									<input id="address" name="address" class="my_input" datatype="*" style="width: 80%"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系地址</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n" value="0"/>
									<p class="Validform_checktip" style="display:inline;">值越大，越靠前！</p>
								</td>
							</tr>
							<%--<tr>
								<td>地图选点</td>
								<td><div id="businessMap"  style="height: 240px;width: 50%"></div></td>
							</tr>
							--%>
							<tr>
								<td class="text_align_right" width="150">
									商家经纬度:
								</td>
								<td>经度：<input id="longitude" name="longitude" class="my_input"/>
									纬度：<input id="latitude"  name="latitude" class="my_input"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									评分：
								</td>
								<td>
									<input id="score" name="score" class="my_input" datatype="n1-2" ignore="ignore" errormsg="评分在1-99" value="0"/>
									<p class="Validform_checktip" style="display:inline;">商家评分</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									人均价格：
								</td>
								<td>
									<input id="averagePrice" name="averagePrice" class="my_input" datatype="double" ignore="ignore" errormsg="请填写数字(支持六位小数)" value="0"/>
									<p class="Validform_checktip" style="display:inline;">人均价格</p>
								</td>
							</tr>
							<!-- <tr>
								<td class="text_align_right" width="150">
									楼层号：
								</td>
								<td>
									<input id="floorNumber" name="floorNumber" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;">请填写楼层号</p>
								</td>
							</tr> -->
							<tr>
								<td class="text_align_right" width="150">
									附近登机口：
								</td>
								<td>
									<ticket:systemCommon type="systemDictionayList" value="businessInfo_djk"/>
									<select id="djk" name="djk" class="my_input" datatype="*" ignore="ignore">
										<option value="">请选择登机口</option>
										<s:if test="#request.systemDictionayList != null">
											<s:iterator value="#request.systemDictionayList" var="sys">
												<option value="${sys.id }">${sys.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;">请填写登机口</p>
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