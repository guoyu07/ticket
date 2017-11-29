<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/elemobilePaidan.js"> </script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						电瓶车派单操作
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						电瓶车派单操作
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table">
							<tr>
								<td class="fz24 t_c" width="120" colspan="4">
									基本信息
								</td>
							</tr>
							<tr>
								<td class="fz20">
									验证日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<td class="fz20">
									是否验证通过
								</td>
								<td class="fz20">
									操作
								</td>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator id="c" value="pageModule.pageList" status="st">
									<tr>
										<td class="fz20">
											<s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="fz20">
											${c.serviceCode.code }
										</td>
										<td class="fz20">
											${c.passed==true?"通过":"不通过" }
										</td>
										<td class="fz20">
											<s:if test="!dispatchService.isDispatch(#c.id)">
												<a href="javascript:;" class="c_blue"
													entityName="${entityName}" value="">派单</a>
											</s:if>
											<input type="hidden" value="${c.id }" />
										</td>
									</tr>
								</s:iterator>
							</s:if>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
						<a href="/wapBjdjDispatch_elemobileJinDu.action"
							class="b_white c_grey border">工单进度</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<div class="pd_dialog box_dialog">
				<div class="c_content b_white">
					<table class="table table-bordered c_l_grey work_table">
						<tr>
							<td class="fz24 t_c" width="120" colspan="6">
								服务人员安排
							</td>
						</tr>
						<s:iterator value="items" var="item">
							<tr>
								<td class="fz20" style="text-align: center">
									${item.value }
								</td>

							</tr>
							<tr>
								<td class="fz20" style="text-align: center">
									<input type="hidden" name="operationFlag" id="operationFlag"
										value="1" />
									<input type="hidden" name="versionFlag" id="versionFlag"
										value="${versionFlag}" />
									<input type="hidden" name="id" value="" id="ids"/>
									<select name="electromobileDispatchItem" class="electromobileDispatchItem"
										datatype="*">
										<option value="">
											--未选择--
										</option>
										<s:iterator value="employee" var="e">
											<option value="${e.id }">
												${e.name }
											</option>
										</s:iterator>
									</select>
									<p class="receiveTip" style="display: inline;">
									</p>
								</td>
							</tr>
						</s:iterator>
					</table>
					<div class="tit b_blue">
						<a href="javascript:add();" class='c_white'>确认</a>
					</div>
				</div>
			</div>
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>
