<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<s:if test="#session.fromApp != null">
							<a onclick="comeBack();" class="FLAPP" ><i class="icon-navicon"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:return_prepage();" class="FL" id="comeback"><i class="icon-angle-left"></i></a>
						</s:else>
						 服务介绍
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<i class="icon-bell"></i>
							<ticket:common type="newMessages"/>
							<s:if test="#request.newMessages > 0">
								<span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
							</s:if>
						</a>
					</div>
				</div>
				<div class="mobile-main">
					<div class="pic">
						<img src="/template/wap/images/pic/65.jpg">
					</div>
					<div class="new_fly_ls">
						<div class="msg">
							<h4>
								温馨提示：
							</h4>
							<P>
								部分登机口距离安检区较远，旅程中有行动不便旅客建议选乘电瓶车
							</P>
							<span class='clearfix padding-bottom'>
								<a href=""class="float-right text-dot">天气原因</a>
							</span>
						</div>
					</div>
					<div class="c_content" style='margin-top: 0px; font-size: 20px;'>
						服务区域：国内出发层（F3）各登机口 乘车地点：国内出发F3层前中心区导乘台 服务时间：06:00-23:00
						服务电话：0871-67091605 有偿服务：收费标准10元/人/次 爱心服务：为老、弱、病、残、孕提供免费电瓶车服务
					</div>
					<div class="c_content text-center no-border"
						style="padding: 0px 0px;">
						<label>
							<i class='icon-check-square c_blue'></i>&nbsp;&nbsp;
							<input type="checkbox" style='display: none' name="" value="">
							已阅读并同意
							<span class='c_blue'>值机协议</span>
						</label>
					</div>
					<div class="tit">
						导航到乘车点
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>