<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
	<script type="text/javascript" src="/common/FileUpload.js"></script>
	<!-- <script type="text/javascript" src="/template/wap/js/plupload/plupload.full.min.js"></script>
	<script type="text/javascript" src="/template/wap/js/plupload/plupload.upload.js"></script> -->
	<script type="text/javascript" src="/template/wap/js/feedback.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
		        	<jsp:param value="我要追加反馈" name="title"/>
		        </jsp:include>
		        <div class="mobile-main">
		        	<form action="feedBackReply_add.action" method="post" id="memberForm">
		        		<a href="feedBack_myFeedBack.action" id="manageLink" style="display: none;"></a>
		        		<input type="hidden" value="site" name="operationFlag"/>
			            <input type="hidden" value="${feedback_id }" name="feedback_id"/>
			            <div class="mr40">
			                <textarea datatype="*" name="description" class="input b_l_grey input-auto fz22" style="width:100%" rows="5" placeholder="请输入反馈信息"></textarea>
			                <div class="Validform_checktip"></div>
			                <br><br>
			                <h1 class="fz26">上传图片：</h1>
			                <br><br>
			                <p class='line text-center'>
			                    <a href="#"><img class="icon-plus b_l_grey txt-large inline_block text-center float-left" src="/template/wap/images/upload.jpg" style="border:1px solid #ccc;" /></a>
			                </p>
			            </div>
			            <a href="javascript:;" onclick="javascript:{var form = $(this).parent(); form.submit();}" class="tit block b_blue c_white" style="width:auto;">提交</a>
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