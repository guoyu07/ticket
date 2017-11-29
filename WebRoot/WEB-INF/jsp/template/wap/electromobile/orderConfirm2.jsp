<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="/template/wap/js/electromobile/orderConfirm2.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
		        <div class="mobile-top">
		            <div class="header">
		                <s:if test="#session.fromApp != null">
							<a onclick="comeBack();" class="FLAPP" ><i class="icon-navicon"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:return_prepage();" class="FL" id="comeback"><i class="icon-angle-left"></i></a>
						</s:else>
		                订单确认
		            </div>
		        </div>
		        </s:if>
		        <s:else>
		         <div class="mobile-top" style="display: none;">
		            <div class="header">
		                <s:if test="#session.fromApp != null">
							<a onclick="comeBack();" class="FLAPP" ><i class="icon-navicon"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:return_prepage();" class="FL" id="comeback"><i class="icon-angle-left"></i></a>
						</s:else>
		                订单确认
		            </div>
		        </div>
		        </s:else>
		        <div class="mobile-main">
		            <div class="order_form">
		                <form id="submitForm" action="electromobile_generateOrder.action" method="post">
		                	<input type="hidden" name="count" value="${count==null ? 1 : count}" />
		                    <div class='label'>
	                            <i class='fly_icon'></i>电瓶车  
		                    	<span><font class="c_red" id="electromobile_price">${electromobile_price}</font>RMB</span>
		                    </div>
		                    <div class='label'>
		                    	数量  
		                    	<span class='text-center line-large' style='width:100px'>
									<i class='icon-minus-square-o x4'></i>
	                                <div class="x4">
										<font id="number" class="c_red">${count==null ? 1 : count}</font>
	                                </div>
									<i class='icon-plus-square-o x4'></i>
								</span>
		                    </div>
		                    <div class='label'>
	                            <i class='count_icon'></i>总价  
	    	                    <span><font id="total_price" class="c_red">${total_price==null ? electromobile_price : total_price}</font>RMB</span>
	                        </div>
		                    <div class="line clearfix mrlr40">
		                        <p style='margin-left:0px;'>
		                        	<input id="agree" type="checkbox" name="agree" value="agree">
		                        	<label for="agree" style="padding: 0;border: 0;">已阅读并同意</label>
		                        	<a href="/airport/1447916241347.ticket" class='c_red'>《免责条款》</a>
		                        	<s:fielderror>
		                        		<s:param>serviceCode</s:param>
		                        	</s:fielderror>
		                        	<s:fielderror>
		                        		<s:param>notLogin</s:param>
		                        	</s:fielderror>
		                        </p>
		                        <input type="submit" id="submitBtn" class="button d_button bg-yello block" value="提交订单">
		                    </div>
		                </form>
		            </div>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
	</body>
	</ticket:cache>
</html>