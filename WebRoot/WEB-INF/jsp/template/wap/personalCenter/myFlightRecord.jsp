<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="我的飞行记录" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="line-big mr40">
						<div class="x6">
							<div class="button block padding-big">
								<p class='fz18 padding-bottom'>
									总飞行时长
								</p>
								<h3 class='fz24'>
									0分钟
								</h3>
							</div>
						</div>
						<div class="x6">
							<div class="button block padding-big">
								<p class='fz18 padding-bottom'>
									总飞行里程
								</p>
								<h3 class='fz24'>
									0公里
								</h3>
							</div>
						</div>
					</div>
					<div class="line-big mr40">
						<div class="x6">
							<div class="button block padding-big">
								<p class='fz18 padding-bottom'>
									总飞行次数
								</p>
								<h3 class='fz24'>
									0次
								</h3>
							</div>
						</div>
						<div class="x6">
							<div class="button block padding-big">
								<p class='fz18 padding-bottom'>
									未飞行次数
								</p>
								<h3 class='fz24'>
									0次
								</h3>
							</div>
						</div>
					</div>
					<div class="tit">
						已结束的行程
					</div>
					<div class="c_content line">
						<%--<div class="x8">
							<h4 class='fz30 padding-bottom c_grey'>
								昆明出发 度假特价
							</h4>
							<p class='fz20'>
								起飞：首都机场 2015-07-07 08:30
							</p>
							<p class='fz20'>
								到达：首都机场 2015-07-07 08:30
							</p>
							<p class='fz20'>
								<p class="text-left fz20">
									<img src="/template/wap/images/pic/62.png" height="24" width="24"
										style='position: relative; top: 5px;'>
									&nbsp;&nbsp;昆明航空&nbsp;&nbsp;
									<font class='c_black'>CA4171</font>
								</p>
							</p>
						</div>
						<div class="x4 text-right">
							<div class="text-right">
								<br>
								<br>
								<p>
									<a href="" class='c_blue fz20 c_grey padding-bottom'>2015-07-07</a>
								</p>
								<br>
								<a href='' class="button bg-blue float-right fz20">已使用</a>
							</div>
						</div>--%>
						${noDataMessage }
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>