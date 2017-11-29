<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="name w980 mt30">
	<ul>
		<li class="li1 bhh">
			<dl>
				<ol>
					<img src="/template/pc/images/use_head_bg.png" />
				</ol>
				<dd class="bhh">
					<s:if test="sessionMember.images != null">
									<img src="${sessionMember.images }" height="100" width="100" class='radius-circle'>
								</s:if> 
								<s:else>
									<img src="/template/wap/images/no_avatar.png" height="100" width="100" class='radius-circle'>
								</s:else>
				</dd>
				<dt class="bhh">
					<p>
						<s:if test="#session.sessionMember.nickName != null">
							${sessionMember.nickName }
						</s:if>
						<s:else>
							${sessionMember.phone }
						</s:else>
					</p>
					<p>
						<img src="/template/pc/images/vip.gif" align="left" />
						<b>VIP1</b>
					</p>
					<p>
						<a href="javascript:logout();"><img src="/template/pc/images/delete.gif" />
						</a>
					</p>
				</dt>
			</dl>
		</li>
		<li class="li2 bhh">
			<center>
				<b>我的积分：</b>0
			</center>
		</li>
		<li class="li3 bhh">
			<center>
				<b>我的评价：</b>0条
			</center>
		</li>
		<li class="li4 bhh">
			<a href="javascript:;"><img src="/template/pc/images/qd.gif" />
			</a>
		</li>
	</ul>
</div name w980 mt30>
