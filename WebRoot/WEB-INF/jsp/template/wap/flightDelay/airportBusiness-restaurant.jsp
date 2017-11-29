<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 航班延误
						<a href="/newss_announcement.action" class="FR">
						<ticket:common type="myAnnounecementCount"/>
		<s:if test="#request.myAnnounecementCount == 1">
			<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
		</s:if>
		<s:else>
			<i class="icon-bell"></i>
		</s:else>
						</a>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 航班延误
						<a href="/newss_announcement.action" class="FR">
						<ticket:common type="myAnnounecementCount"/>
		<s:if test="#request.myAnnounecementCount == 1">
			<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
		</s:if>
		<s:else>
			<i class="icon-bell"></i>
		</s:else>
						</a>
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr30 line-big tit_tabs">
						<div class="x4">
							<a href="#"
								class="button bg-yello d_button block text-center selected"><i
								class="icon-caret-down"></i>国内</a>
						</div>
						<div class="x4">
							<a href="#" class="button bg-sub d_button block text-center"><i
								class="icon-caret-down"></i>国内</a>
						</div>
						<div class="x4">
							<a href="#" class="button bg-sub d_button block text-center"><i
								class="icon-caret-down"></i>国内</a>
						</div>
					</div>
					<div class="c_content text-center clearfix">
						<a href="#" class='x3 fz20'>中餐<span class='float-right' style=" display:none;">|</span>
						</a>
						<a href="#" class='x3 fz20'>西餐<span class='float-right' style=" display:none;">|</span>
						</a>
						<a href="#" class='x3 fz20'>小吃<span class='float-right' style=" display:none;">|</span>
						</a>
						<a href="#" class='x3 fz20'>米线</a>
					</div>
					<div class="mr40">
						<select name="" class='input fz30 height-big text-center'
							style='height: 50px;'>
							<option value="">
								区域
							</option>
						</select>
					</div>
					<div class="c_content">
						<div class="media media-x">
							<a class="float-left" href="#"><img
									src="/template/wap/images/pic/75.jpg">
							</a>
							<div class="media-body line">
								<div class="x12 fz24 c_black">
									1. 肯德基（长水机场店）
								</div>
								<!-- <div class="x6 padding-top">
									<font class="c_orange fz22">50分</font>
								</div> -->
								<div class="x6 fz22 padding-top">
									人均
									<font class="c_orange fz22">￥36.0</font>
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									西式快餐
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									38m
								</div>
							</div>
							<br>
							<hr>
							<div class="media-foot line height-large">
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='go_hear_icon float-left'></i>到这去</font> <span
									class='float-right c_l_grey'>|</span> </a>
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='tel_icon float-left'></i>电话</font>
								</a>
							</div>
						</div>
					</div>
					<div class="c_content">
						<div class="media media-x">
							<a class="float-left" href="#"><img
									src="/template/wap/images/pic/75.jpg">
							</a>
							<div class="media-body line">
								<div class="x12 fz24 c_black">
									1. 肯德基（长水机场店）
								</div>
								<!-- <div class="x6 padding-top">
									<font class="c_orange fz22">50分</font>
								</div> -->
								<div class="x6 fz22 padding-top">
									人均
									<font class="c_orange fz22">￥36.0</font>
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									西式快餐
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									38m
								</div>
							</div>
							<br>
							<hr>
							<div class="media-foot line height-large">
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='go_hear_icon float-left'></i>到这去</font> <span
									class='float-right c_l_grey'>|</span> </a>
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='tel_icon float-left'></i>电话</font>
								</a>
							</div>
						</div>
					</div>
					<div class="c_content">
						<div class="media media-x">
							<a class="float-left" href="#"><img
									src="/template/wap/images/pic/75.jpg">
							</a>
							<div class="media-body line">
								<div class="x12 fz24 c_black">
									1. 肯德基（长水机场店）
								</div>
								<!-- <div class="x6 padding-top">
									<font class="c_orange fz22">50分</font>
								</div> -->
								<div class="x6 fz22 padding-top">
									人均
									<font class="c_orange fz22">￥36.0</font>
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									西式快餐
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									38m
								</div>
							</div>
							<br>
							<hr>
							<div class="media-foot line height-large">
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='go_hear_icon float-left'></i>到这去</font> <span
									class='float-right c_l_grey'>|</span> </a>
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='tel_icon float-left'></i>电话</font>
								</a>
							</div>
						</div>
					</div>
					<div class="c_content">
						<div class="media media-x">
							<a class="float-left" href="#"><img
									src="/template/wap/images/pic/75.jpg">
							</a>
							<div class="media-body line">
								<div class="x12 fz24 c_black">
									1. 肯德基（长水机场店）
								</div>
								<!-- <div class="x6 padding-top">
									<font class="c_orange fz22">50分</font>
								</div> -->
								<div class="x6 fz22 padding-top">
									人均
									<font class="c_orange fz22">￥36.0</font>
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									西式快餐
								</div>
								<div class="x6 fz16 c_l_grey padding-top">
									38m
								</div>
							</div>
							<br>
							<hr>
							<div class="media-foot line height-large">
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='go_hear_icon float-left'></i>到这去</font> <span
									class='float-right c_l_grey'>|</span> </a>
								<a href="" class="x6 text-center"><font class='inline_block'><i
										class='tel_icon float-left'></i>电话</font>
								</a>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>