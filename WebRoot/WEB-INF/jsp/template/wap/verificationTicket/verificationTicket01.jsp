<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="机票验真"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="c_content no-border">
						<form>
							<input type="text" class="input d_input"
								placeholder="输入您的电子客票/行程单号" />
							<br>
							<input type="text" class="input d_input" placeholder="您的姓名" />
							<br>
							<p>
								<input type="text" class="input d_input input-auto float-left"
									size="1" placeholder="输入验证码" style='width: 250px;' />
								<img src="/template/wap/images/pic/71.jpg" height="53"
									width="129" class="float-left margin-left"
									style='position: relative; top: 2px;'>
								<a href="#" class="float-right c_blue"
									style="line-height: 50px;">看不清楚换一张</a>
							</p>
							<br>
							<button type="" class="bg-sub button d_button bg-yello c_grey">
								验真
							</button>
						</form>
					</div>
				</div>
                <%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>