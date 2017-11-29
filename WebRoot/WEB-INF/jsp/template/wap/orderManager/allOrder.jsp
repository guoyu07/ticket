<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		var mid = '${param.mid}';
	</script>
	<script type="text/javascript" src="/template/wap/js/convientBording/orderManager.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="我的订单" name="title"/>
                </jsp:include>
		        
		        <div class="mobile-main">
		            <div class="order_ls">
		                <div class="tit_tab line mr20">
							<div class="x3">
								<a href="javascript:location.replace('bjdjOrderTemplate_allOrder.action?orderStatus=all${empty mid ? null : '&mid=' }${empty mid ? null : mid }')" class="${empty orderStatus || orderStatus == 'all' ? 'selected' : null }"><i class="icon-caret-down"></i>全部订单</a>
							</div>
							<div class="x3">
								<a href="javascript:location.replace('bjdjOrderTemplate_allOrder.action?orderStatus=needPay${empty mid ? null : '&mid=' }${empty mid ? null : mid }')" class="${orderStatus == 'needPay' ? 'selected' : null }"><i class="icon-caret-down"></i>待付款</a>
							</div>
							<div class="x3">
								<a href="javascript:location.replace('bjdjOrderTemplate_allOrder.action?orderStatus=needComment${empty mid ? null : '&mid=' }${empty mid ? null : mid }')" class="${orderStatus == 'needComment' ? 'selected' : null }"><i class="icon-caret-down"></i>待评价</a>
							</div>
							<div class="x3">
								<a href="javascript:location.replace('bjdjOrderTemplate_allOrder.action?orderStatus=donation${empty mid ? null : '&mid=' }${empty mid ? null : mid }')" class="${orderStatus == 'donation' ? 'selected' : null }"><i class="icon-caret-down"></i>赠送</a>
							</div>
						</div>
						<div id="orderList">
							<!-- 全部订单 -->
							<ticket:common type="advertObjByName" value="便捷登机商品图片"/>
							<ticket:commonAnnex type="annex" entityUuid="${advertObjByName.id }" annexType="1" size="1"/>
							<s:if test="orderStatus == 'all' || orderStatus == null">
								<ul>
								<s:if test="#allOrder.size() > 0">
								<s:iterator value="allOrder" var="order">
								    <li class="mr40 line border-bottom padding-large-bottom">
								        <div class="x4">
								        	<s:if test="#order.type == @com.ticket.enumer.BjdjOrderType@bjdj">
								        		<ticket:common type="bjdjPageObj" value="${order.id }"/>
								        		<s:if test='#request.bjdjPageObj != null && #request.bjdjPageObj.url == "#" '>
									        		<a href="/airport/bjdj.action?direct=true">
									        			<img src="${annex.annexPath }" style="height: 200px;width: 200px;">
									        		</a>
								        		</s:if>
								        		<s:else>
								        			<a href="/bjdj_indexAjax.action?direct=true&bjdjPage_id=${bjdjPageObj.id }">
									        			<img src="${annex.annexPath }" style="height: 200px;width: 200px;">
									        		</a>
								        		</s:else>
								        	</s:if>
								        	<s:else>
								        		<a href="/airport/electromobile.action?direct=true">
								        			<img src="/template/wap/images/pic/80.jpg" >
								        		</a>
								        	</s:else>
								        </div>
								        <div class="x6">
								            <h4 class="fz26 padding-big-bottom">
								            	<s:if test="#order.type == @com.ticket.enumer.BjdjOrderType@bjdj">
								            		<ticket:common type="bjdjPageObj" value="${order.id }"/>
									        		<s:if test='#request.bjdjPageObj != null && #request.bjdjPageObj.url == "#" '>
										        		<a href="/airport/bjdj.action?direct=true">
										        			${order.name }
										        		</a>
									        		</s:if>
									        		<s:else>
									        			<a href="/bjdj_indexAjax.action?direct=true&bjdjPage_id=${bjdjPageObj.id }">
										        			${order.name }
										        		</a>
									        		</s:else>
								            	</s:if>
								            	<s:else>
									        		<a href="/airport/electromobile.action?direct=true">
									        			${order.name }
									        		</a>
								        		</s:else>
								            </h4>
								            <div class="fz20 padding-big-bottom">
								            	总价<span class="c_red">￥${order.price * order.count }</span> 
								            	数量：${order.count }
								            </div>
								            <div class="fz20 padding-big-bottom">
								            	<s:date name="#order.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
								            <div class="fz20 padding-big-bottom">
								            	状态：<span class='c_blue'>${order.state.value }</span>
								            </div>
								        </div>
								        <div class="x2">
											<a href="bjdjOrderTemplate_orderDetailsPage.action?orderId=${order.id }" class="button b_blue c_white fz20 margin-bottom block text-center">详情</a>
								            <s:if test="#order.state.name == 'noPay'">
												<a href="bjdjOrderTemplate_payPage.action?orderId=${order.id }" class="button b_blue c_white fz20 margin-bottom block text-center">去付款</a>
											</s:if>
											
											<!-- 判断是否显示“转赠”按钮 -->
											<s:if test="orderService.queryCanDonationServiceCode(#order).size() > 0">
												<a href="bjdjOrderTemplate_orderDonationPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">转赠</a>
											</s:if>
											
											<!-- 判断是否显示“激活”按钮 -->
											<s:if test="orderService.queryCanActivateServiceCode(#order).size() > 0">
												<a href="bjdjServiceCodeActivate_page.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">激活</a>
											</s:if>
											
											<!-- 判断是否显示“评论”按钮 -->
											<s:if test="orderService.queryCanCommentServiceCode(#order).size() > 0">
												<a href="bjdjOrderTemplate_orderDetailsPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">评论</a>
											</s:if>
											
											<!-- 判断是否显示“退款”按钮  -->
											<s:if test="orderService.canRefund(#order)">
								          	  <a href="bjdjOrderTemplate_orderRefundPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">退款</a>
								            </s:if>
											
											<!-- 判断是否显示“删除”按钮 -->
											<s:if test="orderService.canDelete(#order)">
												<a href="javascript:realDelete('${order.id }')" class="button fz20 margin-bottom block text-center">删除</a>
											</s:if>
								        </div>
								    </li>
								</s:iterator>
								</s:if>
								<s:else>
									<div class="c_content clearfix">${noDataMessage }</div>
								</s:else>
								</ul>
							</s:if>
							
							<!-- 待支付订单 -->
							<s:elseif test="orderStatus == 'needPay'">
								<ul>
								<s:if test="#needPay.size() > 0">
								<s:iterator value="needPay" var="order" status="status">
									<li class="mr40 line border-bottom padding-large-bottom">
									    <div class="x1">
									        <input type="checkbox" name="" value="" class="d_checkbox">
									    </div>
									    <div class="x4"><img src="${annex.annexPath }" style="height: 200px;width: 200px;"></div>
									    <div class="x5">
									        <h4 class="fz26 padding-big-bottom">${order.name }</h4>
									        <div class="fz20 padding-big-bottom">
									        	总价<span class="c_red">￥${order.price * order.count }</span> 
									        	数量：${order.count }
									        </div>
									        <div class="fz20 padding-big-bottom">
								            	<s:date name="#order.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	状态：<span class='c_blue'>${order.state.value }</span>
									        </div>
									    </div>
									    <div class="x2">
									        <a href="bjdjOrderTemplate_orderDetailsPage.action?orderId=${order.id }" class="button b_blue c_white fz20 margin-bottom block text-center">详情</a>
									    	<s:if test="#order.state.name == 'noPay'">
												<a href="bjdjOrderTemplate_payPage.action?orderId=${order.id }" class="button b_blue c_white fz20 margin-bottom block text-center">去付款</a>
											</s:if>
											<!-- 判断是否显示“删除”按钮 -->
											<s:if test="orderService.canDelete(#order)">
												<a href="javascript:realDelete('${order.id }')" class="button fz20 margin-bottom block text-center">删除</a>
											</s:if>
									    </div>
									</li>
									<s:if test="status.last">
										<div class="mr40"><button class="button d_button block b_blue c_white">立即付款</button></div>
									</s:if>
								</s:iterator>
								</s:if>
								<s:else>
									<div class="c_content clearfix">${noDataMessage }</div>
								</s:else>
								</ul>
							</s:elseif>
							
							<!-- 待评论订单 -->
							<s:elseif test="orderStatus == 'needComment'">
								<ul>
								<s:if test="#needComment.size() > 0">
								<s:iterator value="needComment" var="order">
									<li class="mr40 line border-bottom padding-large-bottom">
									    <div class="x4"><img src="${annex.annexPath }" style="height: 200px;width: 200px;"></div>
									    <div class="x6">
									        <h4 class="fz26 padding-big-bottom">${order.name }</h4>
									        <div class="fz20 padding-big-bottom">
									        	总价<span class="c_red">￥${order.price * order.count }</span> 
									        	数量：${order.count }
									        </div>
									        <div class="fz20 padding-big-bottom">
								            	<s:date name="#order.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	状态：<span class='c_blue'>${order.state.value }</span>
									        </div>
									    </div>
									    <div class="x2">
									    	<a href="bjdjOrderTemplate_orderDetailsPage.action?orderId=${order.id }" class="button b_blue c_white fz20 margin-bottom block text-center">详情</a>
									    	
									    	<!-- 判断是否显示“评论”按钮 -->
											<s:if test="orderService.queryCanCommentServiceCode(#order).size() > 0">
												<a href="bjdjOrderTemplate_orderDetailsPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">评论</a>
											</s:if>
									    	
									        <!-- 判断是否显示“删除”按钮 -->
											<s:if test="orderService.canDelete(#order)">
												<a href="javascript:realDelete('${order.id }')" class="button fz20 margin-bottom block text-center">删除</a>
											</s:if>
									    </div>
									</li>
								</s:iterator>
								</s:if>
								<s:else>
									<div class="c_content clearfix">${noDataMessage }</div>
								</s:else>
								</ul>
							</s:elseif>
							<s:elseif test="orderStatus == 'donation'">
								<ul>
								<s:if test="#donation.size() > 0">
								<s:iterator value="donation" var="c">
								    <li class="mr40 line border-bottom padding-large-bottom">
								        <div class="x4">
								        	<%-- <img src="${annex.annexPath }" style="height: 200px;width: 200px;"><br/> --%>
								        	<img src="${c.twoDimensionCodePath }" style="height: 200px;width: 200px;"><br/>
								        	<h4 class="fz26 padding-big-bottom">${c.order.name } </h4>
								        </div>
								        <div class="x6">
								        	<s:set var="donation" value="operationService.recentlyOperationToMember(#c, 'donation', #c.member)" />
									        <div class="fz20 padding-big-bottom">
									        	服务码：<span class="c_red">${c.code }</span> 
									        </div>
									        <div class="fz20 padding-big-bottom">
									        	赠送人：
									        	<span class='c_blue'>
									        	<s:if test="#donation.fromMember.realName == null || #donation.fromMember.realName.trim() == ''">
							               			${donation.fromMember.phone }
							               		</s:if>
							               		<s:else>
							               			${donation.fromMember.realName }
							               		</s:else>
									        	</span>
									        </div>
									        <div class="fz20 padding-big-bottom">
								            	赠送时间：<s:date name="#donation.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	有效期：<s:date name="#c.expireTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	状态：<span class='c_red'>${c.state.value }</span>
									        </div>
								        </div>
								        <div class="x2">
											<!-- 判断是否显示“激活”按钮 --> 
											<s:if test="serviceCodeService.canActivate(#c)">
												<a href="bjdjServiceCodeActivate_page.action" class="button fz20 margin-bottom block text-center">激活</a>
											</s:if>
											
											<!-- 判断是否显示“评论”按钮 -->
											<s:if test="serviceCodeService.canComment(#c)">
												<a href="bjdjCommentTemplate.action?serviceCode_id=${c.id }" class="button fz20 margin-bottom block text-center">评价</a>
											</s:if>
											
											<!-- 判断是否显示“赠送”按钮 -->
											<%-- <s:if test="serviceCodeService.canDonate(#c)">
												<a href="bjdjOrderTemplate_orderDonationPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">转赠</a>
											</s:if> --%>
											
											<%-- 
											<!-- 判断是否显示“删除”按钮 -->
											<s:if test="serviceCodeService.canDelete(#c)">
												<a href="javascript:realDelete('${order.id }')" class="button fz20 margin-bottom block text-center">删除</a>
											</s:if> 
											--%>
								        </div>
								    </li>
								</s:iterator>
								</s:if>
								<s:elseif test="#dispatches.size() > 0">
								<s:iterator value="dispatches" var="c">
								    <li class="mr40 line border-bottom padding-large-bottom">
								        <div class="x4">
								        	<%-- <img src="${annex.annexPath }" style="height: 200px;width: 200px;"><br/> --%>
								        	<img src="${c.twoDimensionCodePath }" style="height: 200px;width: 200px;"><br/>
								        	<h4 class="fz26 padding-big-bottom">${c.orderInfoDetail.orderInfo.orderId } </h4>
								        </div>
								        <div class="x6">
								        	<s:set var="donation" value="operationService.recentlyOperationToMember(#c, 'donation', #c.member)" />
									        <div class="fz20 padding-big-bottom">
									        	服务码：<span class="c_red">${c.orderInfoDetail.bjdjServiceCode.code }</span> 
									        </div>
									        <div class="fz20 padding-big-bottom">
									        	赠送人：
									        	<span class='c_blue'>
									        		${c.employeeInfoZengLog.channelCustomerInfo.name }
									        	</span>
									        </div>
									        <div class="fz20 padding-big-bottom">
								            	赠送时间：<s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	有效期：<s:date name="#c.orderInfoDetail.bjdjServiceCode.expireTime" format="yyyy-MM-dd HH:mm:ss"/>
								            </div>
									        <div class="fz20 padding-big-bottom">
									        	状态：<span class='c_red'>${c.orderInfoDetail.bjdjServiceCode.state.value }</span>
									        </div>
								        </div>
								        <div class="x2">
											<!-- 判断是否显示“激活”按钮 --> 
											<s:if test="detailService.canActivate(#c)">
												<a href="bjdjServiceCodeActivate_page.action" class="button fz20 margin-bottom block text-center">激活</a>
											</s:if>
											
											<!-- 判断是否显示“评论”按钮 -->
											<s:if test="detailService.canComment(#c)">
												<a href="bjdjCommentTemplate.action?serviceCode_id=${c.orderInfoDetail.bjdjServiceCode.id }" class="button fz20 margin-bottom block text-center">评价</a>
											</s:if>
											
											<!-- 判断是否显示“赠送”按钮 -->
											<%-- <s:if test="detailService.canDonate(#c.orderInfoDetail.bjdjServiceCode)">
												<a href="bjdjOrderTemplate_orderDonationPage.action?orderId=${order.id }" class="button fz20 margin-bottom block text-center">转赠</a>
											</s:if> --%>
											
											<%-- 
											<!-- 判断是否显示“删除”按钮 -->
											<s:if test="detailService.canDelete(#c.orderInfoDetail.bjdjServiceCode)">
												<a href="javascript:realDelete('${order.id }')" class="button fz20 margin-bottom block text-center">删除</a>
											</s:if> 
											--%>
								        </div>
								    </li>
								</s:iterator>
								</s:elseif>
								<s:else>
									<div class="c_content clearfix">${noDataMessage }</div>
								</s:else>
								</ul>
							</s:elseif>
						</div>
		            </div>
		        </div>
	            <%@ include file="../common/quickNav.jsp"%>
		    </div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>