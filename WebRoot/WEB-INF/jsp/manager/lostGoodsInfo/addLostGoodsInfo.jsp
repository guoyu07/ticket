<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/lostGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增遗失物品信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									物品类型：
								</td>
								<td>
									<select id="type_id" name="type_id" class="my_select" datatype="*">
										<option value="">请选择类别</option>
										<ticket:systemCommon type="goodsTypeList"/>
										<s:if test="#request.goodsTypeList != null">
											<s:iterator id="goodsType" value="#request.goodsTypeList">
												<option value="${goodsType.id }">${goodsType.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写物品类型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;">请填写物品名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品编号：
								</td>
								<td>
									<input id="goodsNumber" name="goodsNumber" ignore="ignore" class="my_input" errormsg="请输入收录单编号" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;">请输入收录单编号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品颜色：
								</td>
								<td>
									<select id="color" name="color" class="my_select" datatype="*">
										<option value="">请选择颜色</option>
										<ticket:systemCommon type="goodsColorList"/>
										<s:if test="#request.goodsColorList != null">
											<s:iterator id="goodsColor" value="#request.goodsColorList">
												<option value="${goodsColor.id }">${goodsColor.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写物品颜色</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取者姓名：
								</td>
								<td>
									<input id="pickerName" name="pickerName" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写拾取者姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取者电话：
								</td>
								<td>
									<input id="pickerPhone" name="pickerPhone" class="my_input" datatype="m" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写拾取者电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取时间：
								</td>
								<td>
									<input id="pickUpTime" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" name="pickUpTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写拾取时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									拾取位置：
								</td>
								<td>
									<select id="pickPosition_id" name="pickPosition_id" class="my_select" datatype="*">
										<option value="">请选择位置</option>
										<ticket:systemCommon type="lostPositionList"/>
										<s:if test="#request.lostPositionList != null">
											<s:iterator id="lostPosition" value="#request.lostPositionList">
												<option value="${lostPosition.id }">${lostPosition.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择拾取位置</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									上交时间：
								</td>
								<td>
									<input id="pickUpTime" name="commitTime" onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写上交时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									库存位置：
								</td>
								<td>
									<input id="pickUpTime" name="stockPosition" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写库存位置</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									物品描述：
								</td>
								<td>
									<textarea id="goodsDescript" name="goodsDescript" class="my_input" datatype="*"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写物品描述(关键词)</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea id="remark" name="remark" class="my_input" ignore="ignore"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									接收人：
								</td>
								<td>
									<input id="operator_id" name="operator_id" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写接收人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									其他信息一：
								</td>
								<td>
									<input id="otherInfoOne" name="otherInfoOne" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写其他信息一</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									其他信息二：
								</td>
								<td>
									<input id="otherInfoTwo" name="otherInfoTwo" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写其他信息二</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_showLostGoodsInfo.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
	<script type="text/javascript">
		var pageTracker = _gat._getTracker("UA-2103267-1");
		pageTracker._initData();
		pageTracker._trackPageview();
	</script>
	<script type="text/javascript">
		var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
		document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
	</script>
</html>