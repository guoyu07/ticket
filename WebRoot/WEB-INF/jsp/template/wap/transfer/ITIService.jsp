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
						</a> 中转
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<i class="icon-bell"></i>
							<ticket:common type="newMessages"/>
							<s:if test="#request.newMessages > 0">
								<span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
							</s:if>
						</a>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 中转
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
						</a>
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab mrlr40">
						<div class="line">
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
										<span class="downward"></span>
									</button>
									<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国际
										</li>
									</ul>
								</div>
							</div>
							<div class="x2">
								<em class='icon-exchange'></em>
							</div>
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
										<span class="downward"></span>
									</button>
									<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国际
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>定制服务</a>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic11.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							 <area shape="rect" coords="2,1,130,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gj"/>" alt="" />
               <area shape="rect" coords="2,128,130,239" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" alt="" />
               <area shape="rect" coords="2,243,130,354" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gj"/>" alt="" />
               <area shape="rect" coords="3,371,131,482" href="<ticket:common type="positionUrl" value="xinglitiqu_zhongzhuan_gj_gj"/>" alt="" />
               <area shape="rect" coords="2,494,130,605" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" alt="" />
               <area shape="rect" coords="174,1,306,111" href="<ticket:common type="positionUrl" value=" bianfangjiancha_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="173,122,306,236" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="170,246,304,357" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="172,368,306,479" href="<ticket:common type="positionUrl" value="banlizhiji_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="169,489,302,603" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="350,1,477,110" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gj"/>" />
               <area shape="rect" coords="350,122,476,239" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gj"/>" />
						</map>
					</div>
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="javascript:;"  class='block c_white'>定制服务</a>
					</div>
					<div class="custom_menu mr40 TTIServiceQuickMenu">
						
					</div>
				</div>
				<div class="tit b_blue " style="height:60px;line-height:60px;">
					<a href="javascript:;"  class='block c_white'>定制服务</a>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
				<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>