
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="申请退款" name="title"/>
                </jsp:include>
		        
		        <div class="mobile-main">
		        	<form action="bjdjOrderTemplate_orderRefund.action" method="post" id="memberForm">
		        		<a id="manageLink" href="bjdjOrderTemplate_allOrder.action" style="display: none;"></a>
			        	<input type="hidden" name="orderId" value="${order.id }" />
			            <!-- <div class="mr40">
			                <h1 class='b_l_grey padding margin-large-bottom fz26'>1、选择退款方式</h1>
			                <div class="select_tit" style='margin-left:0px;margin-right:0px;'>
			                    <div class="button-group no-background">
			                        <button type="button" class="button  bg dropdown-toggle fz22" style="border:1px solid #ddd">
			                            <font>原渠道退回</font> <span class="downward"></span>
			                        </button>
			                        <ul class="drop-menu">
			                            <li>原渠道退回</li>
			                            <li>退至资金池</li>
			                        </ul>
			                    </div>
			                </div>
			            </div> -->
			            <div class="mr40">
			                <h1 class='b_l_grey padding margin-large-bottom fz26'>选择退款原因</h1>
			                <dl class='fz24'>
			                	<s:iterator value="dicts" var="dict" status="st">
			                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
			                        <label class='fz24'>
			                        	<em>${dict.description }</em>
			                        	<span class='float-right'>
			                        		<s:if test="#st.first">
			                        		<input name="reason" value="${dict.description }" class='d_checkbox' type="radio" checked="checked">
			                        		</s:if>
			                        		<s:else>
			                        		<input name="reason" value="${dict.description }" class='d_checkbox' type="radio">
			                        		</s:else>
			                        	</span>
			                        </label>
			                    </dd>
			                    </s:iterator>
			                </dl>
			            </div>
			            <div class="mr40" style="text-align: center">
			            	<input type="submit" value="申请退款" class="button d_button block b_blue c_white" />
			            </div>
		            </form>
		        </div>
		        <%@ include file="../common/quickNav.jsp"%>
		    </div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>