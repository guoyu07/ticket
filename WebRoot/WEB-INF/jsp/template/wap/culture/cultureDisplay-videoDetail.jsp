<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="kv_pics">
						<ticket:commonAnnex type="annex" entityUuid="${news.id}" annexType="1" size="1" />
                        <video width="100%" height="375" controls="controls" preload="none" poster="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
                        	<ticket:commonAnnex type="annex" entityUuid="${news.id}" annexType="3" size="1"/>
                            <source src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" type="video/mp4">
                        </video>
					</div>
					<div class="c_content">
						<h2 class='padding-big-bottom fz24'>
							<ticket:format value="${news.title }" size="8"/>
						</h2>
						<div class="fz22">
							<ticket:format value="${news.introduce}" size="75"/>
						</div>
					</div>
					<div class="c_content">
						<div class="c_l_grey fz22">
							${news.content }
						</div>
					</div>
					<div class="tit_tab" style="display:none;">
						<a href="" class="b_yello c_grey">导航到乘车点</a>
						<a href="" class="b_yello c_grey">一键预约</a>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
        <link rel="stylesheet" href="/template/wap/js/jquery.mediaelement/mediaelementplayer.min.css">
		<script type="text/javascript" src="/template/wap/js/jquery.mediaelement/mediaelement-and-player.min.js"></script>
        <script type="text/javascript">
        $(function(){
            $('video').mediaelementplayer(/* Options */);
        });
        </script>
	</body>
	</ticket:cache>
</html>