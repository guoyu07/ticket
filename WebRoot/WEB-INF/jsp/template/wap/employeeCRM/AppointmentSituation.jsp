<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript"
		src="/template/wap/js/employeeCRM/wapAppoimtment.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						预约情况
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						预约情况
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="javascript:void(0);" id="today">今天</a>
						<a href="javascript:;" id="threedays">最近三天</a>
					</div>
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
								<td class="fz18" width="120">
									预约方式
								</td>
								<td class="fz18">
									预约使用时间
								</td>
								<td class="fz18">
									旅客姓名
								</td>
								<td class="fz18">
									服务码
								</td>
								<td class="fz18">
									乘机人信息
								</td>
								<td class="fz18">
									航班信息
								</td>
								<td class="fz18">
									备注
								</td>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator var="c" value="pageModule.pageList" status="st">
									<tr>
										<td class="fz18 text-right" width="120">
											${c.way == 'online' ? '在线预约' : '线下预约'}
										</td>
										<td class="fz18">
											<s:date name="#c.useTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="fz18">
											${c.name }
										</td>
										<td class="fz18">
											${c.serviceCode.code }
										</td>
										<td class="fz18">
											${c.mobile }
										</td>
										<td class="fz18">
											${c.flightNo }
										</td>
										<td class="fz18">
											${c.description }
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="18">
										${noDataMessage }
									</td>
								</tr>
							</s:else>
						</table>
						<table class="table table-bordered c_l_grey work_table"
							id="oneday" style="display: none;">
							<tr>
								<td class="fz18" width="120">
									预约方式
								</td>
								<td class="fz18">
									预约使用时间
								</td>
								<td class="fz18">
									旅客姓名
								</td>
								<td class="fz18">
									服务码
								</td>
								<td class="fz18">
									乘机人信息
								</td>
								<td class="fz18">
									航班信息
								</td>
								<td class="fz18">
									备注
								</td>
							</tr>
							<s:if test="#list.size()> 0">
								<s:iterator var="c" value="list" status="st">
									<tr>
										<td class="fz18 text-right" width="120">
											${c.way == 'online' ? '在线预约' : '线下预约'}
										</td>
										<td class="fz18">
											<s:date name="#c.useTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="fz18">
											${c.name }
										</td>
										<td class="fz18">
											${c.serviceCode.code }
										</td>
										<td class="fz18">
											${c.mobile }
										</td>
										<td class="fz18">
											${c.flightNo }
										</td>
										<td class="fz18">
											${c.description }
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="18">
										${noDataMessage }
									</td>
								</tr>
							</s:else>
						</table>
						<table class="table table-bordered c_l_grey work_table"
							id="threeday" style="display: none;">
							<tr>
								<td class="fz18" width="120">
									预约方式
								</td>
								<td class="fz18">
									预约使用时间
								</td>
								<td class="fz18">
									旅客姓名
								</td>
								<td class="fz18">
									服务码
								</td>
								<td class="fz18">
									乘机人信息
								</td>
								<td class="fz18">
									航班信息
								</td>
								<td class="fz18">
									备注
								</td>
							</tr>
							<s:if test="#list2.size() > 0">
								<s:iterator var="c" value="list2" status="st">
									<tr>
										<td class="fz18 text-right" width="120">
											${c.way == 'online' ? '在线预约' : '线下预约'}
										</td>
										<td class="fz18">
											<s:date name="#c.useTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="fz18">
											${c.name }
										</td>
										<td class="fz18">
											${c.serviceCode.code }
										</td>
										<td class="fz18">
											${c.mobile }
										</td>
										<td class="fz18">
											${c.flightNo }
										</td>
										<td class="fz18">
											${c.description }
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="18">
										${noDataMessage }
									</td>
								</tr>
							</s:else>
						</table>
						<table class="table table-bordered c_l_grey work_table" id="byId"
							style="display: none;">
							<tr>
								<td class="fz18" width="120">
									预约方式
								</td>
								<td class="fz18">
									预约使用时间
								</td>
								<td class="fz18">
									旅客姓名
								</td>
								<td class="fz18">
									服务码
								</td>
								<td class="fz18">
									乘机人信息
								</td>
								<td class="fz18">
									航班信息
								</td>
								<td class="fz18">
									备注
								</td>
							</tr>
							<tr>
								<td class="fz18 text-right" width="120" id="ways">
									
								</td>
								<td class="fz18" id="usetimes">
									
								</td>
								<td class="fz18" id="names">
									
								</td>
								<td class="fz18" id="codes">
									
								</td>
								<td class="fz18" id="mobiles">
									
								</td>
								<td class="fz18" id="flightNos">
									
								</td>
								<td class="fz18" id="descriptions">
									
								</td>
							</tr>
							<s:else>
								<tr>
									<td colspan="18">
										没有数据
									</td>
								</tr>
							</s:else>
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