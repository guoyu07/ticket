<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="申请延期" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		        	<s:fielderror>
		        		<s:param>order</s:param>
		        	</s:fielderror>
		        	<form action="bjdjOrderTemplate_serviceCodeDelay.action" method="post" id="memberForm">
			        	<input type="hidden" name="codeId" value="${codeId }" />
			            <div class="mr40 panel">
			                <h1 class='b_l_grey padding margin-large-bottom fz26'>1、选择延期时长</h1>
			                <div style='margin-left:0px;margin-right:0px;font-size: 20px;'>
					        	<s:iterator value="delayRules" var="dr" status="st">
			                    <label style="display: block; padding: auto;">
			                    	<input name="delayRule" value="${dr.id }" type="radio" <s:if test="#st.count == 1">checked="checked"</s:if>/>${dr.delayTime }天${dr.record }积分
			                    </label>
			                    <br/>
			                    </s:iterator>
			                </div>
			            </div>
			            <div class="mr40 panel" style="text-align: center">
			            	<input type="submit" value="申请延期" class="button d_button block b_blue c_white" />
			            	<a href="bjdjOrderTemplate_allOrder.action" id="manageLink" style="display: none;"></a>
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