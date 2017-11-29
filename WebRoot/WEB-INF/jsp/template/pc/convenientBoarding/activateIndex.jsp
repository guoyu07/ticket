<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>激活 - 云南省昆明市长水机场</title>
		<script type="text/javascript">
			var orderId = "${orderId}";
			var codeId = "${codeId}";
		</script>
		<SCRIPT type="text/javascript" src="/template/pc/js/convenientBoarding/activate.js"></SCRIPT>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>
		<%@ include file="../common/nav.jsp" %>
		
		<div class="place w980 mt30">当前位置: <a href="/airportPc.action">首页</a> > <a href="javascript:;">激活</a></div>
		
		<div class="JiHuo w980 mt30" id="scrollTop">
			<form action="bjdjServiceCodeActivate_activate.action" method="post" id="pcForm">
				<input type="hidden" name="checkQueueWait" value="true">
				<a id="manageLink" href="pcServiceCode_activateSuccessPage.action" style="display: none;"></a>
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">第1步 输入有效证件</td>
					</tr>
					<tr>
						<td colspan="2" height="66">
							<form method="post" id="cardForm">
								<select id="select">
									<s:iterator value="comboList" var="dict">
										<option value="${dict.name }">${dict.value }</option>
									</s:iterator>
								</select>
								<input id="idCard" type="text" datatype="*" placeholder="请输入“添加出行人员”的证件号" style="padding-right: 45px; " />
								<div onclick="openCommonTraveller()" style="margin-left: -45px; height: 45px; width: 45px; background-color: #ddd; line-height: 45px; text-align: center; display: inline-block;">+</div>
								<img onclick="queryFlightByCertificate()" src="/template/pc/images/TiJiao.gif" align="absmiddle" style="margin-left: 45px;"/>
							</form>
						</td>
					</tr>
					<tr id="items">
						<td colspan="2">第2步 填写航班信息</td>
					</tr>
					<!-- ajax加载 -->
	                ${html }
					<tr id="last">
						<td></td>
						<td><a href="javascript:void();" onclick="JM.scrollTo('body','#scrollTop');$('#idCard').val('');"><img src="/template/pc/images/TianJiapeople.gif" /></a></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="image" src="/template/pc/images/JiHuo.gif" style="padding: 0; height: auto; width: auto;"/><br />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="saturation" style="display:none;">
			<table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
				<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
				<tr><td height="50" align="center">&nbsp;&nbsp;服务码激活休息厅此预约时段已饱和</td></tr>
				<tr><td height="40"><p>您好！您预约的此时段，机场休息厅已饱和，是否同意放弃进厅服务，只享受此套餐内其他服务项目</p></td></tr>
				<tr><td height="40">
					<a href="javascript:void();" onclick="smsTip()" class="">排队等候</a>
					<a href="javascript:void();" onclick="waitActivate()" class="b_l_grey c_grey">同意</a>
				</td></tr>
			</table>
		</div>
		<div id="wait" style="display:none;">
			<table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
				<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
				<tr><td height="50" align="center">&nbsp;&nbsp;服务码激活-选择排队等候</td></tr>
				<tr><td height="40"><p>您好！感谢您对我们的支持，等大厅有空位之后会以短信的形式通知您！</p></td></tr>
			</table>
		</div>
		<div id="dialog" style="display:none;">
		    <div id="warn_msg2">
		        <table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
					<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
					<tr><td height="40"><p>你好，您输入的证件号码未发现航班信息，请进行相关操作或手动输入信息</p></td></tr>
					<tr><td height="40">
						<a href="airportPc_checkOnLine.action" class="border b_white c_grey">网上值机</a>
		                <a href="javascript:void();" onclick="addBlankActivateItem()">选择航班</a>
					</td></tr>
				</table>
		    </div>
		    <div id="zj_ok">
		        <table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
					<tr><td height="40"><p>已值机航班</p></td></tr>
					<tr><td height="40" class="data"></td></tr>
					<tr><td height="40">
						<a href="javascript:void();" onclick="addActivateItem()">确认</a>
					</td></tr>
				</table>
		    </div>
		    <div id="warn_msg">
		        <table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
					<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
					<tr><td height="40"><p>请确定激活的航班信息，如姓名、航班号、航班时间。如遇信息不符请点击信息对应的框进行手动修改。</p></td></tr>
					<tr><td height="40">
						<a href="javascript:void();" onclick="closeUpdateCheckIn()">确认</a>
					</td></tr>
				</table>
		    </div>
		    <div id="fucus_ok">
		        <table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
					<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
					<tr><td height="40"><p>已关注航班</p></td></tr>
					<tr><td height="40" class="data"></td></tr>
					<tr><td height="40">
						<a href="javascript:void();" onclick="addFocusFlight()" class="border b_white c_grey">新增关注航班</a>
						<a href="javascript:void();" onclick="closeFocusFlight()">确认</a>
					</td></tr>
				</table>
		    </div>
		    <div id="commonTraveller">
		        <table cellspacing="0" cellpadding="0" width="600" height="290" style="background: white; border: 5px solid orange; padding: 15px;">
					<tr><td height="40"><p>常用旅客卡</p></td></tr>
					<tr><td height="40" class="data"></td></tr>
					<tr><td height="40">
						<a href="javascript:void();" onclick="closeCommonTraveller()">确认</a>
					</td></tr>
				</table>
		    </div>
		</div>
		<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
