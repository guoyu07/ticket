<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						<a href="#" class="FL"><i class="icon-angle-left"></i>
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
					<form action="/airportm_checkOnlineSubmit.action" id="memberForm" name="memberForm" alertConfirm="false">
						<div class="c_content">
							<div class="zj_first">
								<h5>
									<i></i>值机须知
								</h5>
								<div class="text-big">
									1. 目前仅支持国内航班办理值机； 2. 携带儿童、婴儿，请直接至柜台办理值机； 3.
									如发生临时更换机型情况，航空公司将为你重新安排座位。 如与原座位安排不符，请以航空公司安排为准； 4.
									已办理值机后，如需办理退票，需先到值机柜台，取消已打 登机牌、已托运的行李，再退票。
								</div>
							</div>
						</div>
						<div class="c_content">
							<p>
								<i class="icon-caret-down bg-grey margin-right"></i>
								身份证：&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" class="input input-auto" id="IDCard" name="IDCard" size="40" errormsg="aa" datatype="*">
								<p class="Validform_checktip" style="display:inline;"></p>
							</p>
						</div>
						<div class="c_content">
							<p>
								手机：&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" class="input input-auto" size="40" id="mobile" name="mobile" datatype="m">
								<p class="Validform_checktip" style="display:inline;"></p>
							</p>
						</div>
						<div class="c_content">
							<p>
								输入验证码：&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" class="input input-auto" size="30" id="smsCode" name="smsCode" datatype="n4-4">
								<button class="button bg-sub">
									获取验证码
								</button>
							</p>
						</div>
						<div class="c_content text-center">
							<label>
								<i class='icon-check-square c_blue'></i>&nbsp;&nbsp;
								<input type="checkbox" style='display: none' name="" value="">
									已阅读并同意
								<span class='c_blue'>值机协议</span>
							</label>
						</div>
						<div class="tit_tab">
							<a href="#" class="button">提交</a>
						</div>
					</form>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>