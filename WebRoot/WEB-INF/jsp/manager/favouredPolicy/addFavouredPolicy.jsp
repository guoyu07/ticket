<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/favouredPolicy.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增优惠政策</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											优惠编号：
										</td>
										<td>
											<input id="favouredPolicyNo" name="favouredPolicyNo" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写优惠编号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											所需充值金额：
										</td>
										<td>
											<input id="rechargeAmount" name="rechargeAmount" class="my_input" datatype="n"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写所需充值金额</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											充值比率：
										</td>
										<td>
											<input id="discountRate" name="discountRate" class="my_input"/>
											<p class="Validform_checktip" style="display:inline;">请输入充值比率</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											实际到账金额：
										</td>
										<td id="sumMoney">
											0
										</td>
									</tr>
									<tr style="display:none;">
										<td class="text_align_right" width="150">
											优惠等级：
										</td>
										<td>
											<select  id="grade" name="grade">
												<option value="">请选择优惠等级</option>
												<option value="1">一级</option>
												<option value="2">二级</option>
												<option value="3">三级</option>
											</select>
											
											<p class="Validform_checktip" style="display:inline;"> 请选择优惠等级</p>
										</td>
									</tr>
									
									<tr>
										<td class="text_align_right" width="150">
											描述：
										</td>
										<td>
											<input id="descript" name="descript" class="my_input" />
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											渠道类型：
										</td>
										<td>
											<select name="discountWay" id="discountWay" class="my_select" ignore="ignore" datatype="*" >
											<ticket:systemCommon type="channelTypeList" value="0"/>
											<s:if test="#request.channelTypeList != null">
												<s:iterator id="channelType" value="#request.channelTypeList">
													<option value="${channelType.id }">${channelType.name }</option>
												</s:iterator>
												</s:if>
											</select>
											<p class="Validform_checktip" style="display: inline;">
												请填写渠道类型
											</p>
											
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