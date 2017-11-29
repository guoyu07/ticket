<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 网上值机
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
				</div>
				<div class="mobile-main">
					<div class="c_content clearfix">
						<span class='c_black float-left fz22'><br />
							<br />
							<br />您的值机信息为：</span>
						<span class='float-right text-right'> <img
								src="../images/pic/79.jpg">
							<p class='fz20'>
								如二维码无法识别，请
								<span class='c_blue'>补打登机牌</span>
							</p> </span>
					</div>
					<div class="new_fly_ls" style='padding-bottom: 0px;'>
						<ul class="">
							<li style='margin-bottom: 0px;'>
								<h2 class='text-center'>
									航班号：MU3486
								</h2>
								<hr>
								<div class="fly_line clearfix">
									<div class="clearfix">
										<span class='float-left'>昆明<font>出发城市</font>
										</span>
										<span class='float-right'>重庆<font>到达城市</font>
										</span>
									</div>
									<div class="line clearfix padding-big-top">
										<div class='x6 text-left'>
											<font class="fz16">起飞时间</font>
										</div>
										<div class='x6 text-right'>
											<font class="fz16">到达时间</font>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="c_content line">
						<div class="x6 fz20 text-center">
							登机口：
							<font class='fz28 c_red'>58</font>
						</div>
						<div class="x6 fz20 text-center">
							座位号：
							<font class='fz28 c_red'>20A</font>
						</div>
					</div>
					<div class="c_content">
						登机开始时间：2015-07-02 12:00 登机截止时间：2015-07-02 12:20 预计到达时间：2015-07-02
						15:35 机型：波音737

					</div>
					<div class="msg mr40">
						<h4>
							单架次延误公告：
						</h4>
						<P>
							尊敬的旅客你好，由于
							<span class='text-dot'>天气原因</span>目前从昆明飞往北京的MU5714航班预计延误半小时，请耐心等待机场最新通知，谢谢！
						</P>
					</div>
					<div class="tit_tab">
						<a href="#" class="button">旅程指南</a>
					</div>
					<%@include file="../common/quickNav.jsp" %>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp" %>
		</div>
	</body>
</html>
