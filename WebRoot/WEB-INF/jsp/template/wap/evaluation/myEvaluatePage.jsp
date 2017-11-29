<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript">
		var methodName = 'myList';
	</script>
	<script type="text/javascript" src="/template/wap/js/evaluation/evaluateList.js"></script>
    <style type="text/css">
    .gginfo{ text-align:center;height:65px; line-height:65px; background-color:#fff2c9; color:#ffa200; border:1px solid #ffa200; border-radius:40px; margin:20px 40px;}
    
    </style>
    
    
    <link rel="stylesheet" type="text/css" href="/template/wap/css/demo.css">
    <!-- simple slide -->
    <script type="text/javascript" src="/template/wap/js/simple.slide.min.js"></script>
    <link rel="stylesheet" href="/template/wap/css/animate.css">
    <link rel="stylesheet" href="/template/wap/css/simple.slide.css">

    
    
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
		        	<jsp:param value="我的评价" name="title"/>
		        </jsp:include>
                 <div class="tab-notice">
					
       <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <td >
          	<ticket:common type="myAnnounecementCount"/>
          	<a href="javascript:location.replace('/airport/newss_announcement.action')">公告板<s:if test="#request.myAnnounecementCount == 1"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td>
          	<ticket:common type="myMessageCount"/>
          	<a href="javascript:location.replace('/airportm_myMessage.action')">私信<s:if test="#request.myMessageCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td class="tab-sel">
          	<ticket:common type="myEvaluationCount"/>
          	<a href="javascript:location.replace('/evaluation_myEvaluatePage.action')">我的评论<s:if test="#request.myEvaluationCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myFeedBackCount"/>
          	<a href="javascript:location.replace('/feedBack_myFeedBack.action')">我的反馈<s:if test="#request.myFeedBackCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
        </tr>
      </table>
    </div>  <hr color="#d8d8d8">
				<div class="mobile-main">
               
					<!-- ajax加载 -->
					<!-- <div class="tit"><a id="nextPage" href="javascript:query()">加载更多评论</a></div> -->
					<div class="tit"><a id="nextPage">加载更多评论</a></div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>