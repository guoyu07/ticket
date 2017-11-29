<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		var price = ${order.price };
	</script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="申请转赠" name="title"/>
                </jsp:include>
		        
		        <div class="mobile-main">
		        	<form action="bjdjOrderTemplate_orderDonation.action" method="post" id="memberForm" name="commonForm">
			        	<input type="hidden" name="orderId" value="${order.id }" />
			            <div class="mr40">
			                <h1 class='b_l_grey padding margin-large-bottom fz26'>1、选择转赠服务码（可多选）</h1>
			                <dl class='fz24'>
			                	<s:iterator value="codes" var="code" status="st">
			                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
			                        <label class='fz24'>
			                        	<em>${code }</em>
			                        	<span class='float-right'>
			                        		<s:if test="#st.first">
			                        		<input name="code" value="${id }" class='d_checkbox' type="checkbox" datatype="*" checked="checked">
			                        		<p class="Validform_checktip" style="display:inline;"></p>
			                        		</s:if>
			                        		<s:else>
			                        		<input name="code" value="${id }" class='d_checkbox' type="checkbox">
			                        		</s:else>
			                        	</span>
			                        </label>
			                    </dd>
			                    </s:iterator>
			                </dl>
				        	<s:fielderror>
				        		<s:param>order</s:param>
				        	</s:fielderror>
			            </div>
			            <div class="mr40">
			                <h1 class='b_l_grey padding margin-large-bottom fz26'>
			                    <span class="select_tit text-left" style='margin-left:0px;margin-right:0px;'>
			                        <span class="button-group no-background text-left">
			                            <button type="button" class="button bg dropdown-toggle fz26 no-background text-left" >
			                                <font>2、输入转赠电话 </font>
			                            </button>
			                        </span>
			                    </span>
			                </h1>
			                <input name="mobile" type='text' class='input d_input' datatype="m" placeholder='请输入您需要转赠的电话号码'>
			                <p class="Validform_checktip" style="display:inline;"></p>
			                <s:fielderror>
				        		<s:param>mobile</s:param>
				        	</s:fielderror>
				        	<h1 class='c_red padding margin-large-bottom fz20 text-center'>温馨提示：请确认转赠电话号码，一旦转赠则无法进行其它操作。</h1>
			            </div>
			            <div class="mr40" style="text-align: center">
			            	<input type="submit" value="确认转赠" class="button d_button block b_blue c_white" />
			            	<a id="manageLink" href="bjdjOrderTemplate_allOrder.action" style="display: none;"></a>
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