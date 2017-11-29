<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
	<script type="text/javascript" src="/common/FileUpload.js"></script>
	<script type="text/javascript" src="/template/wap/js/evaluation/evaluate.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
		        	<jsp:param value="我要评价" name="title"/>
		        </jsp:include>
		        <div class="mobile-main">
		        	<form action="evaluation_evaluate.action" method="post" id="memberForm">
		        		<a href="evaluation_listPage.action" id="manageLink" style="display: none;"></a>
			            <div class="mr40">
			                <select id="classes" datatype="*" name="classes" class="input d_input b_grey fz22" style="height:70px;line-height:40px;">
			                	<s:iterator value="classess" var="item">
				                    <option value="${item.id }">${item.name }</option>
			                	</s:iterator>
			                </select>
			                <div class="Validform_checktip fz22" style="margin:10px 0px;">&nbsp;</div>
			                <select id="project" datatype="*" name="project" class="input d_input b_grey fz22" style="height:70px;line-height:40px;">
			                	<!-- ajax加载 -->
			                </select>
			                <div class="Validform_checktip fz22" style="margin:10px 0px;">&nbsp;</div>
			            </div>
			            <div class="mr40" id="target">
							<!-- ajax加载 -->
			            </div>
			            <div class="mr40">
			                <textarea datatype="*" placeholder="在此可留下您的宝贵意见" name="content" class="input b_l_grey input-auto fz22" style="width:100%" rows="5"></textarea>
			                <div class="Validform_checktip"></div>
			                <br><br>
			                <h1 class="fz26">上传图片：</h1>
			                <br><br>
			                <p class='line text-center'>
			                    <a href="#"><img class="icon-plus b_l_grey txt-large inline_block text-center float-left" src="/template/wap/images/upload.jpg" style="border:1px solid #ccc;"/></a>
			                    <a href="#"><img class="icon-plus b_l_grey txt-large inline_block text-center" src="/template/wap/images/upload.jpg" style="border:1px solid #ccc;" /></a>
			                    <a href="#"><img class="icon-plus b_l_grey txt-large inline_block text-center float-right" src="/template/wap/images/upload.jpg" style="border:1px solid #ccc;"  /></a>
			                </p>
			            </div>
			            <a href="javascript:;" onclick="javascript:{var form = $(this).parent(); form.submit();}" class="tit block b_blue c_white" style="width:auto;">提交</a>
			            <a href="tel:0871-96566" class="tit block b_yello fz20" style="width:auto;">点击拨打机场客服电话</a>
		            </form>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>