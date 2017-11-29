<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>便捷登机首页 - 云南省昆明市长水机场</title>
		<script src="/template/pc/js/jquery.artDialog-4.1.7.js"></script>
		<script type="text/javascript" src="/template/pc/js/convenientBoarding/bjdj.js"></script>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>
		<%@ include file="../common/nav.jsp" %>
		
		<div class="place w980 mt30">当前位置: <a href="/airportPc.action">首页</a> > <a href="javascript:;">便捷登机</a></div>
		
		<div class="bjdj_index w980 mt30">
			<form action="pcOrder_generatePage.action" method="post">
				<ul class="bhh"><img src="/template/pc/images/img.jpg" /></ul>
				<dl class="hh">
					<dd>
						<h2>${package1.name }</h2>
						[<s:iterator value="bjdj_items" var="item" status="status">${item.name }&nbsp;&nbsp;<s:if test="#status.count % 4 == 0"></s:if></s:iterator>]
					</dd>
					<dt>
					<b>价格：</b>￥${package1.price }<br />
					<s:if test="#session.sessionMember != null">
		            	<s:if test="#session.sessionMember.memberType != null">
		            		<s:if test="#session.sessionMember.member == null">
		            			<a href="javascript:relation();" class='h_buy'>
		            				<img src="/template/pc/images/buy.gif"/>
		            			</a>
		            		</s:if>
		            		<s:else>
				            	<input type="image" src="/template/pc/images/buy.gif" style="width: 190px; height:67px;"/>
				            </s:else>
		            	</s:if>
		            	<s:else>
			            	<input type="image" src="/template/pc/images/buy.gif" style="width: 190px; height:67px;"/>
			            </s:else>
		            </s:if>
		            <s:else>
		            	<input type="image" src="/template/pc/images/buy.gif" style="width: 190px; height:67px;"/>
		            </s:else>
					</dt>
				</dl>
			</form>
		</div>
		
		<div class="sleep w980 mt30"><p>休息厅实况已选择<b>${wait }座</b>，剩余<b>${surplus }个</b>空座位。</p></div>
		
		<div class="bjdj_index_door w980 mt30">
			<ul id="tb5_">
		        <li class="hovertab_5" id="tb5_1" onclick="x:HoverLi5(1);"><a href="javascript:void(0);">服务介绍</a></li>
		        <li class="normaltab_5" id="tb5_2" onclick="x:HoverLi5(2);"><a href="javascript:void(0);">服务流程</a></li>
				<li class="normaltab_5" id="tb5_3" onclick="x:HoverLi5(3);"><a href="javascript:void(0);">使用条款</a></li>
				<li class="normaltab_5" id="tb5_4" onclick="x:HoverLi5(4);"><a href="javascript:void(0);">交通指南</a></li>
				<li class="normaltab_5" id="tb5_5" onclick="x:HoverLi5(5);"><a href="javascript:void(0);">用户评价</a></li>
		    </ul>
			
			<dl class="dis" id="tbc5_01">${serviceIntroduce.content }</dl>
			<dl class="undis" id="tbc5_02">${serviceProcess.content }</dl>
			<dl class="undis" id="tbc5_03">${use.content }</dl>
			<dl class="undis" id="tbc5_04">交通指南</dl>
			<dl class="undis" id="tbc5_05">
		        <iframe id="evaluation" src="pCEvaluation_manage.action?iframe=true" width="100%" frameborder="0"></iframe>
			</dl>
		</div>
		<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
		<script type="text/javascript">
			var height = document.getElementById("evaluation").contentWindow.screen.height;
			$(document.getElementById("evaluation")).attr('height', height);
		</script>
		<div style='display:none'>
			<div class="form-dialog">
			    <form action="" method="post" >
			        <div class="label"><input type="text" name="" placeholder="输入手机号码" id="phone"></div>
			        <div class="label"><input type="text" name="" placeholder="输入验证码" class="input-auto" id="code"><button id="getCaptchaLogins" onclick="getvalidation();" type="button" class="btn-code">获取验证码</button></div>
			        <div class="label">
			            <button type="button" class="btn-on" onclick="validation();">确认关联</button>
			            <button type="button" class="btn-off" onclick="closes();">取消</button>
			        </div>
			    </form>
				<div class="text-center loginfo">
	                       
	            </div>
			</div>
		</div>
	<script type="text/javascript">
		function relation(){
	    	$.slide({'id':'form-dialog','content':$('.form-dialog').get(0),lock:true});//成功
		}
	    $('input,textarea').placeholder();
	    function getvalidation(){
			var $getCaptcha = $("#getCaptchaLogins");
			var text = $getCaptcha.text();
			var phone = $("#phone").val();
			if(!phone || $.trim(phone) == ''){
				JM.alert('请输入你的手机号！', 2000);
			}else if(phone.length != 11){
				JM.alert('请输入正确的手机号码！', 2000);
			}else{
		    	$.post(
					'memberQq_relationMemberToSendCode.action',
					{
						phone : phone
					},
					function(data){
						
						
						var surplus = 60;
						var handler = setInterval(function(){
							
							if(surplus > 0){
								
								$getCaptcha.text(--surplus + '秒后重新获取...');
							}else{
								
								$getCaptcha.text(text);
								clearInterval(handler);
							}
						}, 1000);
					}
				);
		    }
		}
		function validation(){
			var code = $("#code").val();
			var phone = $("#phone").val();
			if(phone == null || JM.trim(phone) == ""){
				JM.alert("请输入手机号码", 2000);
			}else if(phone.length != 11){
				JM.alert("请输入正确的手机号码", 2000);
			}else
			if(code == null || JM.trim(code) == ""){
				JM.alert("请输入验证码", 2000);
			}else{
				$.post("memberQq_relationMember.action",{authCode:JM.trim(code),phone:phone},function(data){
					if(data.status == "y"){
		    			$(".loginfo").text(data.info+"正在跳转。。");
		    			var t1 = window.setTimeout(JM.goPage("pcOrder.action?versionFlag=site"),2000); 
					}else{
						$(".loginfo").text(data.info);
					}
				},"json");
			}
		}
		function closes(){
			$.dialog.list["form-dialog"].close();
			var $getCaptcha = $("#getCaptchaLogins");
			$getCaptcha.text("获取验证码");
			$("#phone").val("");
			$("#code").val("");
			$(".loginfo").text("");
		}
	</script>
	</body>
</html>

