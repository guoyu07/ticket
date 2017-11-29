<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript"
	src="/template/pc/js/jquery.artDialog-4.1.7.js"></script>
<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
	<script type="text/javascript" src="/common/FileUpload.js"></script>
<script type="text/javascript" src="/template/pc/js/myAccount.js"></script>
<script type="text/javascript">
	function show() {
		JM.goUrl("/pcCommonTraller_show.action");
	}
</script>
<script type="text/javascript">
		EvPNG.fix('img,ol');
		function MM_jumpMenu(targ,selObj,restore){eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
		if (restore) selObj.selectedIndex=0;}
	</script>
<title>账户信息 - 云南省昆明市长水机场</title>
</head>
<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh">
				<p>站内公告：</p></li>
			<li id="rollTextMenu1" style="display: block">
				长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导3</li>
		</ul>

		<dl class="hh">
			<dd class="bhh">
				<a href="javascript:rollText(-1);"><img
					src="/template/pc/images/pre1.gif"
					onmouseover="this.src='/template/pc/images/pre2.gif'"
					onmouseout="this.src='/template/pc/images/pre1.gif'"> </a>
			</dd>
			<dd class="bhh">
				<a href="javascript:rollText(1);"><img
					src="/template/pc/images/next1.gif"
					onmouseover="this.src='/template/pc/images/next2.gif'"
					onmouseout="this.src='/template/pc/images/next1.gif'"> </a>
			</dd>
		</dl>
	</div gg w980 mt30>
	<SCRIPT type=text/javascript>
	<!--
		var rollText_k = 3; //菜单总数
		var rollText_i = 1; //菜单默认值
		//setFocus1(0);
		rollText_tt = setInterval("rollText(1)", 8000);
		function rollText(a) {
			clearInterval(rollText_tt);
			rollText_tt = setInterval("rollText(1)", 8000);
			rollText_i += a;
			if (rollText_i > rollText_k) {
				rollText_i = 1;
			}
			if (rollText_i == 0) {
				rollText_i = rollText_k;
			}
			//alert(i)
			for ( var j = 1; j <= rollText_k; j++) {
				document.getElementById("rollTextMenu" + j).style.display = "none";
			}
			document.getElementById("rollTextMenu" + rollText_i).style.display = "block";
		}
	//-->
	</SCRIPT>
	<%@ include file="common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" /> </a></li>
			<li class="bhh"><a href="airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" /> </a></li>
			<li class="bhh"><a href="airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" /> </a></li>
			<li class="bhh"><a href="airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" /> </a></li>
			<li class="bhh"><a href="airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" /> </a></li>
			<li class="bhh"><a href="airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" /> </a></li>
		</ul>
	</div menu1>

	<div class="w980">
		<div class="L bhh">
			<ul>
				<li><a href="pcOrder_allOrder.action"><img
						src="/template/pc/images/button1_1.gif"
						onmouseover="this.src='/template/pc/images/button1_2.gif'"
						onmouseout="this.src='/template/pc/images/button1_1.gif'" /> </a></li>
				<li><a href="javascript:void(0);"><img
						src="/template/pc/images/button2_2.gif" /> </a></li>
				<li><a href="/pcMembers_myPayInfo.action"><img
						src="/template/pc/images/button3_1.gif"
						onmouseover="this.src='/template/pc/images/button3_2.gif'"
						onmouseout="this.src='/template/pc/images/button3_1.gif'" /> </a></li>
				<li><a href="airportPc_myFavorite.action"><img
						src="/template/pc/images/button4_1.gif"
						onmouseover="this.src='/template/pc/images/button4_2.gif'"
						onmouseout="this.src='/template/pc/images/button4_1.gif'" /> </a></li>
			</ul>
		</div L bhh>

		<div class="ZHXX hh">
			<dl>
				<dd class="bhh">
					<s:if test="sessionMember.images != null">
						<img src="${sessionMember.images }" height="100" width="100"
							class='radius-circle'>
					</s:if>
					<s:else>
						<img src="/template/wap/images/no_avatar.png" height="100"
							width="100" class='radius-circle'>
					</s:else>
				</dd>
				<dt class="bhh" style="width: 200px;">
					<s:if test="#session.sessionMember.nickName != null">
						${sessionMember.nickName }
					</s:if> <s:else>
						${sessionMember.phone }
					</s:else> 
					<br/> 
					<a href="javascript:change();">修改头像</a>&nbsp;
					<a href="/pcMembers_resetPwd.action">修改密码</a>&nbsp;
					<a href="javascript:change1();">修改资料</a>
				</dt>
				<dd class='user_info_order_btn'>
					<button type="button" onclick="show()">常用旅客</button>
				</dd>
			</dl>
			<ul id="update">
				<li>
					<p>不可修改</p> 会员等级：普通会员</li>
				<li><a href="javascript:;" class="input4_dialog_btn"><p>设置</P></a>
				<em>昵称</em>：<font name="nickName">${sessionMember.nickName }</font>
				</li>
				<li><a href="javascript:;" class="input_dialog_btn"><p>设置</p>
				</a> <em>姓名</em>：<font name="realName">${sessionMember.realName }</font>
				</li>
				<li><a href="javascript:;" class="select_dialog_btn"><p>设置</p>
				</a> <em>性别</em>：<font name="sex"><s:if
							test="#detailInfo.sex == 0">
							女
							</s:if> <s:if test="#detailInfo.sex == 1">
							男
							</s:if> </font>
				</li>
				<li><a href="javascript:;" class="date_dialog_btn"><p>设置</p>
				</a> <em>出生日期</em>：<font name="birthday"><s:date
							name="#detailInfo.birthday" format="yyyy-MM-dd" /> </font>
				</li>
				<li><a href="javascript:;" class="input1_dialog_btn"><p>设置</p>
				</a> <em>手机</em>：<font name="phone">${sessionMember.phone}</font>
				</li>
				<li><a href="javascript:;" class="input2_dialog_btn"><p>设置</p>
				</a> <em>邮箱</em>：<font name="email">${sessionMember.email }</font>
				</li>
				<li><a href="javascript:;" class="input3_dialog_btn"><p>设置</p>
				</a> <em>身份证</em>：<font name="IDCard">${sessionMember.IDCard }</font>
				</li>
				<li><a href="/pcMembers_resetPwd.action"><p>修改</p> </a>修改密码：xxxxxxxx
				</li>
				<li><button id="updateinfo">保存设置</button>
				</li>
			</ul>
			<ul id="updateimg" style="display: none;">
			<form action="airportm_updateImage.action" method="post" id="memberForm">
				<a href="pcMembers_personalSetting.action" id="manageLink" style="display: none;"></a>
				<li><s:if test="sessionMember.images != null">
						<img class="icon-plus" src="${sessionMember.images }" height="300" width="300"/>
					</s:if> <s:else>
						<img src="/template/wap/images/no_avatar.png" class="icon-plus"/>
					</s:else> <input type="hidden" name="images" /> <a id="uploadImage" href="javascript:;"
					class="float"><br/><img src="/template/wap/images/choose_pic.png"
						height="61" width="196"> </a> <span>支持jpg、jpeg、gif、png、bmp格式</span>
				</li>
				<li>
					<button>
								保存
							</button>
				</li>
				</form>
			</ul>
		</div ZHXX hh>
	</div w980>
	<div style='display:none'>
		<div class="input_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["input_dialog"].close()'></a> <label><input
				type="text" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="input1_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["input1_dialog"].close()'></a> <label><input
				type="text" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="input2_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["input2_dialog"].close()'></a> <label><input
				type="text" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="input3_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["input3_dialog"].close()'></a> <label><input
				type="text" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="input4_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["input4_dialog"].close()'></a> <label><input
				type="text" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="select_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["select_dialog"].close()'></a> <label>男<input
				type="radio" name="sex" class='radio' id="m" /> </label> <label>女<input
				type="radio" name="sex" class='radio' id="f" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
		<div class="date_dialog inp_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["date_dialog"].close()'></a> <label><input
				type="date" name="" class="input" value="" /> </label>
			<div class="add">
				<button type="submit">保存</button>
			</div>
		</div>
	</div>

	<%@ include file="common/left.jsp"%>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
