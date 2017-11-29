<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="head">
	<ul class="w980">
		<a href="javascript:void(0);"><h1>
				云南省昆明市长水机场
			</h1> </a>

		<dl class="hh">
			<!---- 登录前 ---->
			<s:if test="#session.sessionMember == null">
			<dd class="bhh"><a href="/pcBjdjMember_toLoginPage.action"><img src="/template/pc/images/button_login.gif" id="login"/></a></dd>
        	<dd class="bhh"><a href="/pcMembers_register.action"><img src="/template/pc/images/button_reg.gif" /></a></dd>
        	</s:if>
			<!---- /登录前 -->

			<!-- 登录后  -->
			<s:else>
			<li class="mainmenu hh">
				<s:if test="sessionMember.images != null">
									<img src="${sessionMember.images }" align="absmiddle">
								</s:if> 
								<s:else>
									<img src="/template/wap/images/no_avatar.png" align="absmiddle">
								</s:else>
				欢迎您，
				<a href="/airportPc_personalCenter.action">
					<s:if test="#session.sessionMember.nickName != null">
						${sessionMember.nickName }
					</s:if>
					<s:else>
						${sessionMember.phone }
					</s:else>
				</a>
				<dt>
					<a href="/airportPc_personalCenter.action"><p class="p1">
							我的资料
						</p> </a>
					<a href="/airportPc_myFavorite.action"><p class="p2">
							我的收藏
						</p> </a>
					<a href="/airportPc_myMessage.action"><p class="p3">
							消息盒子
						</p> </a>
					<a href="/airportPc_helpCenter.action"><p class="p4">
							帮助中心
						</p> </a>
					<a href="javascript:logout();"><p class="p5">
							注销
						</p> </a>
				</dt>
			</li> 
			</s:else>
			<!---- /登录后 ---->
		</dl>
	</ul w980>
</div head>
