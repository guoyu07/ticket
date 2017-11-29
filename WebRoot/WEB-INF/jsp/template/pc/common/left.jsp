<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.rightmenuicon1{  background: rgba(0, 0, 0, 0) url("http://www.kmcsia.com/template/pc/image/left_menu_icon.png") no-repeat scroll right top !important;}
.leftmenuicon1{ background: rgba(0, 0, 0, 0) url("http://www.kmcsia.com/template/pc/image/right_menu_icon.png") no-repeat scroll right top !important;}
</style>

<script type="text/javascript">
var right1=0;
function showRight(){
		right1++;
		if(right1%2==0){
			document.getElementById("right_menu_icon1").style.backgroundImage="url( http://www.kmcsia.com/template/pc/image/left_menu_icon.png)";
			}else{
				document.getElementById("right_menu_icon1").style.backgroundImage="url( http://www.kmcsia.com/template/pc/image/right_menu_icon.png)";
				}
	}

</script>
<div class="user_info_right_side" style="left: -120px;">
	<a href="javascript:showRight();" class="right_menu_icon" id="right_menu_icon1"></a>
	<div class="user_info_right">
		<div class="user_avatar">
			<s:if test="#session.sessionMember != null" >
			<a href="javascript:logout();">注销</a>
			</s:if>
			<s:else><a href="/pcBjdjMember_toLoginPage.action">登录</a></s:else>
			<s:if test="#session.sessionMember != null" >
				<img
					src="${sessionMember.images }"
					width="80" height="80">
			</s:if>
			<s:else>
				<img src="/template/pc/images/morentouxiang.jpg" width="80" height="80">
			</s:else>
			<p>
				<s:if test="#session.sessionMember.nickName != null">
					${sessionMember.nickName }
				</s:if>
				<s:else>
					${sessionMember.phone }
				</s:else>
			</p>
		</div>
		<ul>
			<li><hr>
				<a href="/pcOrder.action" class="selected"><i class='setting_icon_1'></i>
				<p>便捷登机</p>
			</a>
			</li>
			<li><hr>
				<a href="/airportPc_payAttentionToUs.action"><i class='setting_icon_2'></i>
				<p>关注我们</p>
			</a>
			</li>
			<li><hr>
				<a href="/airportPc_serviceHotLine.action"><i class='setting_icon_3'></i>
				<p>机场热线</p>
			</a>
			</li>
			<li><hr>
				<a href="/pCEvaluation_evaluationPage.action"><i class='setting_icon_4'></i>
				<p>我要评价</p>
			</a>
			</li>
		</ul>
	</div>
</div>