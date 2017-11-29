<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/wapelemobileorder.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						电瓶车工单进度
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						电瓶车工单进度
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div>
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" name="" value="" placeholder='服务码' id="fwm">
							</label>
							<button class="button bg-yello" id="ss">
								搜索
							</button>
						</div>
					</div>
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table" id="all">
							<tr>
								<td class="fz24 t_c" colspan="2">
									基本信息
								</td>
								<td class="fz24 t_c" colspan="2">
									服务人员安排
								</td>
							</tr>
							<tr>
								<td class="fz20">
									派单日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<s:iterator value="#items" var="item">
									<td class="fz20">
										${item.value }
									</td>
									<td class="fz20">
										状态
									</td>
								</s:iterator>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator id="c" value="pageModule.pageList" status="st">
									<tr>
										<td class="fz20">
											<s:date name="#c.status.createTime"
												format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="fz20">
											${c.validation.serviceCode.code }
										</td>
										<s:iterator value="#c.dispatchList" var="dl">
											<td class="fz20">
												${dl.employee.name }
											</td>
										</s:iterator>
										<td class="fz20">
											<s:if test="#c.ended == true">
											    结束
										    	</s:if>
											<s:else>
											    进行中
										    	</s:else>
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>


						<table class="table table-bordered c_l_grey work_table" id="find"
							style="display: none;">
							<tr>
								<td class="fz24 t_c" colspan="2">
									基本信息
								</td>
								<td class="fz24 t_c" colspan="2">
									服务人员安排
								</td>
							</tr>
							<tr>
								<td class="fz20">
									日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<s:iterator value="#items" var="item">
									<td class="fz20">
										${item.value }
									</td>
									<td class="fz20">
										状态
									</td>
								</s:iterator>
							</tr>
							<tr>
								<td class="fz20" id="time">
								</td>
								<td class="fz20" id="serviceCode">
								</td>
								<td class="fz20" id="employeeName">
								</td>
								<td class="fz20" id="ended">
								</td>
							</tr>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/wapBjdjDispatch_manageForElectromobile.action"
							class="b_yello c_grey">返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>