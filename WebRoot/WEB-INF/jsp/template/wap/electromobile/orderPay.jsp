<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	                	<jsp:param value="订单支付" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="panel mr40 border10">
		                <div class="panel-head fz22 padding-big-top padding-big-bottom text-center">电瓶车服务</div>
		                <div class="panel-body fz16 padding-big-top padding-big-bottom text-center">
		                	<p class="c_red fz22">${order.price * order.count}元</p>
		                </div>
		            </div>
		            <div class="pay_select mr40">
						<div class="line">
							<div class="x2">
								<img src="/template/wap/images/alipay_pay.png">
							</div>
							<div class="x8 padding-large-left">
								支付宝支付
								<p>推荐有支付宝账户的用户使用</p>
							</div>
							<div class="x2 text-right">
								<input type="radio" name="payMethod" value="alipay" checked="checked">
							</div>
						</div>
						<div class="line">
							<div class="x2">
								<img src="/template/wap/images/weixin_pay.png">
							</div>
							<div class="x8 padding-large-left">
								微信支付
								<p>推荐已安装微信的用户使用</p>
							</div>
							<div class="x2 text-right">
								<input type="radio" name="payMethod" value="wechat_pay">
							</div>
						</div>
						<div class="line">
							<div class="x2">
								<img src="/template/wap/images/quick_pay.png">
							</div>
							<div class="x8 padding-large-left">
								快捷支付
								<p>推荐注册过快捷支付的用户使用</p>
							</div>
							<div class="x2 text-right">
								<input type="radio" name="payMethod" value="quick_pay">
							</div>
						</div>
						<div class="line">
							<div class="x2">
								<img src="/template/wap/images/union_pay.png">
							</div>
							<div class="x8 padding-large-left">
								银行卡支付
								<p>推荐开通网上银卡的用户使用</p>
							</div>
							<div class="x2 text-right">
								<input type="radio" name="payMethod" value="bank_pay">
							</div>
						</div>
					</div>
		            <div class="mrlr40"></div>
		            <input type="submit" id="submitBtn" value="" style="display: none;"/>
		            <a href="${order.payUrl }" class="tit" style="display: block;">确认支付</a>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>