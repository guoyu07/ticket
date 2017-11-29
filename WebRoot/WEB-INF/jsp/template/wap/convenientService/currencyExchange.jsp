<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>

					<div class="c_content">
						
						<ticket:common type="newsCommonObj" value="${news.id }" />
						<s:if test="#request.newsCommonObj != null">
							<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1" />
								<s:if test="#request.queryAnnexList != null">
									<s:iterator id="annex" value="#request.queryAnnexList" status="st">
										<img id="annex${st.count }" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="500" height="320">
									</s:iterator>
								</s:if>
							<dl class='llist'>
								<dd class='padding-bottom fz22'>
									${newsCommonObj.content }
								</dd>
								
							<div class="mr40 exchange_box line" style="display: none;">
								<div class="x10 clearfix" style="height:auto;">
									<div class="line">
										<div class="x4">
											<input type="text" id="money" class="input d_input fz26"  style="line-height:normal;height:70px;" value="1" placeholder='金额'>
										</div>
										<div class="x8">
											<select id="fromType" class='input d_input fz26'  style="line-height:normal;height:70px;">
												<option selected value="CNY">
													人民币
												</option>
												<option value="USD">
													美元
												</option>
												<option value="EUR">
													欧元
												</option>
												<option value="TWD">
													台币
												</option>
												<option value="HKD">
													港币
												</option>
												<option value="KRW">
													韩元
												</option>
												<option value="GBP">
													英镑
												</option>
												<option value="JPY">
													日元
												</option>
												<option value="THB">
													泰铢
												</option>
												<option value="RUB">
													卢布
												</option>
											</select>
										</div>
									</div>
									<div class="line">
										<div class="x4">
											<input type="text" id="exchange_money" class="input d_input fz26"  style="line-height:normal;height:70px;" value="1" placeholder='金额' readonly="readonly">
										</div>
										<div class="x8">
											<select id="toType" class='input d_input fz26' style="line-height:normal;height:70px;">
												<option  value="CNY">
													人民币
												</option>
												<option selected value="USD">
													美元
												</option>
												<option value="EUR">
													欧元
												</option>
												<option value="TWD">
													台币
												</option>
												<option value="HKD">
													港币
												</option>
												<option value="KRW">
													韩元
												</option>
												<option value="GBP">
													英镑
												</option>
												<option value="JPY">
													日元
												</option>
												<option value="THB">
													泰铢
												</option>
												<option value="RUB">
													卢布
												</option>
											</select>
										</div>
									</div>
								</div>
								<div class="x2"><button id="exchangeTransferBtn" type="button" class='button d_button bg-sub fz18'></button></div>
							</div>
		                    <div class="fy_ls mr40" style="display: none;">
		                        <h2 class="padding fz22">温馨提醒</h2>
		                        <h4>
		                            <div id="resultInfoDiv_bak">仅供参考，兑换汇率以实际情况为准。</div>
		                        </h4>
		                    </div>
							<ticket:common type="locationListById" value="${news.infoPosition_id }"/>
							<s:if test="#request.locationListById != null">
								<s:iterator id="location" value="#request.locationListById">
										<s:if test="#location.mobile != null && #location.mobile !='' ">
											<dd class='padding-bottom fz22 border-small border-bottom'>${location.name } 
											<a href="tel:${location.mobile }" class='c_blue float-right'>
											<em class='quick_mobile'></em>一键拨号</a></dd>
										</s:if>
										<s:else>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<dd class='padding-bottom fz22'>
													${location.name }
													<a href="http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=下载App能享受室内导航服务&output=html" class='c_blue float-right'><em
														class='quick_map'></em>导航到这</a>
												</dd>
											</s:if>
											<s:else>
												<dd class='padding-bottom fz22'>
													${location.name }
													<a href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber}" class='c_blue float-right'><em
														class='quick_map'></em>导航到这</a>
												</dd>
											</s:else>
										</s:else>
									</s:iterator>
								</dl>
							</s:if>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript">$(function(){exchangeRate();});</script>
	</body>
	</ticket:cache>
</html>