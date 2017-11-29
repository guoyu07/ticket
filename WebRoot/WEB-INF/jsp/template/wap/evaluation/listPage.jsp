<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript">
		var methodName = 'list';
	</script>
    <script type="text/javascript" src="/template/wap/js/evaluation/evaluateList.js"></script>
    <link rel="stylesheet" type="text/css" href="/template/wap/css/list.css"/>
   
    <style type="text/css">
    .gginfo{ text-align:center;height:65px; line-height:65px; background-color:#fff2c9; color:#ffa200; border:1px solid #ffa200; border-radius:40px; margin:20px 40px;}
    
    </style>
    
     
    <!-- simple slide -->
    <script type="text/javascript" src="/template/wap/js/simple.slide.min.js"></script>
    <link rel="stylesheet" href="/template/wap/css/animate.css">
    <link rel="stylesheet" href="/template/wap/css/simple.slide.css">

    <style type="text/css">
    .ft_more{ display:none !important;}
    
    </style>
	<body class="mobile quick" >
		<div class="mobile-view" >
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="评价列表" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="label_bar">
		                <div class="fz16 gginfo" >欢迎进行评价及反馈，我们在收到您的宝贵意见后，会第一时间进行回复。</div>
		            </div>
		            <!-- ajax加载 -->
                    
					<div class="notice_ft" style="margin:0px 40px; width:auto !important; border-radius:8px;"><a id="nextPage" style="color:#7f7f7f;" ><i class="icon-arrow-circle-up"></i>加载更多评论</a></div>
				</div>
				<div class="mobile-foot" style="height:140px;">
                    <div onClick="location.href='evaluation_evaluatePage.action'" class="tit" style="width:40%; cursor:pointer; display:inline-block; float:left; margin:40px 0px 0px 40px;">服务评论</div>
                    <div onClick="location.href='/feedBack_myFeedBack.action'" class="tit" style="width:40%; display:inline-block;cursor:pointer; float:right; margin:40px 40px 0px 0px;">软件反馈</div>
				</div>
			</div>
			<%@ include file="../common/quickNav.jsp"%>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>