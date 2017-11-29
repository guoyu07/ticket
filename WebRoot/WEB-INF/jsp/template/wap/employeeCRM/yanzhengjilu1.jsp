<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/wapBjdjValid.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						验证记录
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						验证记录
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="javascript:void(0);" >便捷登机</a>
						<a href="/wapValidation_manageElectromobile.action" class='b_white c_grey border'>电瓶车服务</a>
					</div>
					<div>
							<div class="searchForm">
								<label class='button'>
									<i class='icon-search c_l_grey'></i>
									<input type="text" name="" value="" placeholder='服务码' id="fuwuma">
								</label>
								<button class="button bg-yello" id="show">
									搜索
								</button>
							</div>
					</div>
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table" id="allll">
							<tr>
								<td class="fz20">
									序号
								</td>
								<td class="fz20">
									验证日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<td class="fz20">
									服务厅
								</td>
								<td class="fz20">
									航班信息
								</td>
								<td class="fz20">
									乘机人信息
								</td>
								<td class="fz20">
									服务人员
								</td>
								<td class="fz20">
									是否验证通过
								</td>
								<td width="80" class="text_align_center">
									操作
								</td>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator id="c" value="pageModule.pageList" status="st">
									<tr>
										    <td class="fz20">${st.count }</td>
										    <td class="fz20"><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td class="fz20">${c.appointment.serviceCode.code }</td>
										    <td class="fz20">${c.hall.number }</td>
										    <td class="fz20">${c.appointment.flightNo }</td>
										    <td class="fz20">${c.appointment.name }</td>
										    <td class="fz20">${c.employee.name }</td>
										    <td class="fz20">${c.passed==true?"通过":"不通过" }</td>
										    <td class="fz20">
										    	<s:if test="!dispatchService.isDispatch(#c.id)">
										    	<a href="wapBjdjDispatch_manage.action" class="" entityName="${entityName}" value="">分单</a>
										    	</s:if>
										    	 <%-- <a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a> --%> 
										    </td>
										 </tr>
								</s:iterator>
							</s:if>
						</table>
						<table class="table table-bordered c_l_grey work_table" style="display: none;" id="one">
							<tr>
								<td class="fz20">
									序号
								</td>
								<td class="fz20">
									验证日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<!--<td class="fz20">
									航班信息
								</td>
								-->
								<!--<td class="fz20">
									乘机人信息
								</td>
								-->
								<td class="fz20">
									服务人员
								</td>
								<td class="fz20">
									是否验证通过
								</td>
								<td width="80" class="text_align_center">
									操作
								</td>
							</tr>
							<tr>
								<td class="fz20">
									1
								</td>
								<td class="fz20" id="yztime">
								</td>
								<td class="fz20" id="servicecode">
								</td>
								<td class="fz20" id="employee">
								</td>
								<td class="fz20" id="passed">
								</td>
								<td class="fz20">
									<s:if test="!dispatchService.isDispatch(#c.id)">
										<a href="/wapBjdjDispatch_manage.action"
											class="" entityName="${entityName}" value="">分单</a>
									</s:if>
									<%-- <a href="#" class="realDeleteBtn" entityName="${entityName}"
										value="${c.id }">删除</a> --%>
								</td>
								<!--<td class="fz20"></td>
								<td class="fz20"></td>
							-->
							</tr>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>