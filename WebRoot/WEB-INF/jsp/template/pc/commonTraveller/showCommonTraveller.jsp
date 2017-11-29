<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/head.jsp"%>
<script src="/template/pc/js/jquery.artDialog-4.1.7.js"></script>
<script type="text/javascript" src="/template/pc/js/pcCommonTraveller.js"></script>
<title>常用旅客 - 云南省昆明市长水机场</title>
</head>
<body>
	<%@ include file="../common/top.jsp"%>

	<%@ include file="../common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh">
				<p>站内公告：</p>
			</li>
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
					onmouseout="this.src='/template/pc/images/pre1.gif'">
				</a>
			</dd>
			<dd class="bhh">
				<a href="javascript:rollText(1);"><img
					src="/template/pc/images/next1.gif"
					onmouseover="this.src='/template/pc/images/next2.gif'"
					onmouseout="this.src='/template/pc/images/next1.gif'">
				</a>
			</dd>
		</dl>
	</div gg w980 mt30>
	<SCRIPT type=text/javascript>
			<!--
			var rollText_k=3; //菜单总数
			var rollText_i=1; //菜单默认值
			//setFocus1(0);
			rollText_tt=setInterval("rollText(1)",8000);
			function rollText(a){
			clearInterval(rollText_tt);
			rollText_tt=setInterval("rollText(1)",8000);
			rollText_i+=a;
			if (rollText_i>rollText_k){rollText_i=1;}
			if (rollText_i==0){rollText_i=rollText_k;}
			//alert(i)
			 for (var j=1; j<=rollText_k; j++){
			 document.getElementById("rollTextMenu"+j).style.display="none";
			 }
			 document.getElementById("rollTextMenu"+rollText_i).style.display="block";
			} 
			//-->
		</SCRIPT>
	<%@ include file="../common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" /> </a>
			</li>
		</ul>
	</div menu1>

	<div class="w980">
		<div class="L bhh">
			<ul>
				<li><a href="pcOrder_allOrder.action"><img
						src="/template/pc/images/button1_1.gif"
						onmouseover="this.src='/template/pc/images/button1_2.gif'"
						onmouseout="this.src='/template/pc/images/button1_1.gif'" /> </a>
				</li>
				<li><a href="javascript:void(0);"><img
						src="/template/pc/images/button2_2.gif" /> </a>
				</li>
				<li><a href="/pcMembers_myPayInfo.action"><img
						src="/template/pc/images/button3_1.gif"
						onmouseover="this.src='/template/pc/images/button3_2.gif'"
						onmouseout="this.src='/template/pc/images/button3_1.gif'" /> </a>
				</li>
				<li><a href="airportPc_myFavorite.action"><img
						src="/template/pc/images/button4_1.gif"
						onmouseover="this.src='/template/pc/images/button4_2.gif'"
						onmouseout="this.src='/template/pc/images/button4_1.gif'" /> </a>
				</li>
			</ul>
		</div L bhh>

		<div class="ZHXX hh cfzz">
			<div class="order_info">
				<h3 align="center">常用旅客</h3>
				<table>
					<s:if test="#travellerList != null">
						<s:iterator value="travellerList" id="list">
							<tr>
								<td align="left"><s:if
										test="#list.chaName != null && #list.engName != null">
										<s:if test="#list.chaName != null">
											<dt>
												${list.chaName}<i class="icon-angle-right"></i> <input
													type="hidden" value="${list.id }" /> <a
													href="commonTraveller_editCommon.action" id="editCommon"
													style="display: none;"></a>
											</dt>
											<br />
										</s:if>
										<s:if test="#list.engName != null">
											<dt>${list.engName}</dt><br />
										</s:if>
									</s:if> <s:else>
										<s:if test="#list.chaName != null">
											<dt>
												${list.chaName}<i class="icon-angle-right"></i> <input
													type="hidden" value="${list.id }" /> <a
													href="commonTraveller_editCommon.action" id="editCommon"
													style="display: none;"></a>
											</dt>
											<br />
										</s:if>
										<s:if test="#list.engName != null">
											<dt>
												${list.engName}<i class="icon-angle-right"></i> <input
													type="hidden" value="${list.id }" /> <a
													href="commonTraveller_editCommon.action" id="editCommon"
													style="display: none;"></a>
											</dt><br />
										</s:if>
									</s:else> <s:if test="#list.phone != null && #list.phone != ''">
										<dd>
											<span>手机号</span>&nbsp;&nbsp;+86 ${list.phone}
										</dd>
									</s:if> <s:if test="#cardList != null">
										<s:iterator value="cardList" id="list1">
											<s:if
												test="#list1.cardType != null && #list1.parentId == #list.id">
												<dd>
													<span>${list1.cardType}</span>&nbsp;&nbsp;${list1.cardValue}
													<input type="hidden" value="${list1.cardValue}" name="card" />
												</dd>
											</s:if>
										</s:iterator>
									</s:if> <s:if test="#list.gender != null && #list.gender != ''">
										<dd>
											<span>性别</span>&nbsp;&nbsp;${list.gender}
										</dd>
									</s:if> <s:if test="#list.birth != null && #list.birth != ''">
										<dd>
											<span>生日</span>&nbsp;&nbsp;${list.birth}
										</dd>
									</s:if></td>
								<td align="right"><a href="javascript:;" class="modify_btn">修改</a>
									<input type="hidden" value="${list.id }" /></td>
							</tr>
						</s:iterator>
					</s:if>
				</table>
				<a href="javascript:add();"><img
					src="/template/pc/images/TianJiarenyuan.jpg" /> </a>
			</div>
		</div ZHXX hh>
	</div w980>

	<div style='display:none'>
		<div class="modify_dialog">
			<a href="javascript:;" class="close"
				onclick='$.dialog.list["modify_dialog"].close()'></a>
			<form action="" method="post" id="memberForm3" onsubmit="javascript:return false;">
				<label> <span>中文名</span> 
				<input type="hidden" value="" id="travellerId"/>
				<input type="text" name="" value=""
					id="cName" placeholder="请输入中文名字"/>
					<p id="namePage" style="display: none; color:red; font-size:10px;"></p>
				</label> <label> <span>英文名</span> <input id="eNameLast" type="text"
					name="" value="" style="width:209px;" placeholder="姓LastName"/>
					<p id="eNamePage1" style="display: none; color:red; font-size:10px;"></p>
					 <input id="eNameFirst" placeholder="名FirstName"
					type="text" name="" value="" style="width:209px;margin-left:10px;" />
					<p id="eNamePage2" style="display: none; color:red; font-size:10px;"></p>
				</label> <label> <span>手机号</span> <input type="text" name=""
					value="" id="phone" datatype="m" placeholder='手机号'/>
					<p class="Validform_checktip" style="display:inline;"></p>
				</label>
				<div class="idInfo">
					<!-- ajax生成 -->
				</div>
				<div class="add">
					<select id="idselect">
					<!-- ajax生成 --> 
					</select>
					<p id="errorPage" style="display: none; color:red; font-size:10px;"></p>
					<p>填写非身份证号码时需要选择性别和出生日期</p>
				</div>
				<label> <span>性别</span> <input type="text" name="" value="" id="gender"/>
				</label> <label> <span>生日</span> <input type="text" name="" value="" id="birth"/>
				</label>
				<div class="add btn">
					<button type="submit" id="editCommon">修改</button>
					<a href="pcCommonTraller_show.action" id="manageLink" style="display: none;"></a>
					<button type="submit" id="delCommon">删除</button>
					<a href="pcCommonTraller_show.action" id="manageLink" style="display: none;"></a>
					<button type="submit" id="addCommon">提交</button>
					<a href="pcCommonTraller_show.action" id="manageLink" style="display: none;"></a>
				</div>
			</form>
		</div>
	</div>
		<%@ include file="../common/left.jsp" %>
	<%@ include file="../common/bottom.jsp"%>

</body>
</html>
