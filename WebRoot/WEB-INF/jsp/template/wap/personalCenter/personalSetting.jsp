<%@page import="com.ticket.util.GeneralUtil"%>
<%@page import="com.ticket.pojo.Member"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="个人资料" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="c_content fz20">
						验证加V
						<i class='v_icon'></i>
						<i class='icon-angle-right float-right fz30 r_t_10'></i>
					</div>
					<div class="c_content">
						<p class='clearfix fz20'>
							头像
							<span class='float-right'>
								<s:if test="sessionMember.images != null">
									<img src="${sessionMember.images }" height="100" width="100" class='radius-circle'>
								</s:if> 
								<s:else>
									<img src="/template/wap/images/no_avatar.png" height="100" width="100" class='radius-circle'>
								</s:else>
							</span>
						</p>
						<hr />
						<p class='clearfix fz20'>
							昵称
							<span class='float-right r_t_1'>${sessionMember.nickName}&nbsp;&nbsp;</span>
						</p>
						<hr />
						<p class='clearfix fz20'>
							会员等级
							<span class='float-right r_t_1'>普通会员</span>
						</p>
					</div>
					<div class="line">
						<div class="x6">
							<div class='c_content clearfix'>
								<label class='fz18'>
									姓名
									<span class='float-right'>${sessionMember.realName }</span>
								</label>
							</div>
						</div>
						<div class="x6">
							<div class='c_content clearfix'>
								<label class='fz18'>
									性别
									<span class='float-right fz18'> 
										<s:if test="#detailInfo.sex == 0">
			                        		女
			                        	</s:if> 
			                        	<s:elseif test="#detailInfo.sex == 1">
			                        		男
			                        	</s:elseif> 
			                        	<s:else>
											未设置
			                        	</s:else> 
                        			</span>
								</label>
							</div>
						</div>
						<div class="x6">
							<div class='c_content clearfix'>
								<label class='fz18'>
									出生日期
									<span class='float-right'> 
										<s:date name="#detailInfo.birthday" format="yyyy-MM-dd" /> 
									</span>
								</label>
							</div>
						</div>
						<div class="x6">
							<div class='c_content clearfix'>
								<label class='fz18'>
									手机
									<span class='float-right'>
										<%
											
											Member member = (Member)session.getAttribute("sessionMember");
											if(GeneralUtil.isNotNull(member.getPhone())){
												String phone = member.getPhone().substring(0,3)+"****"+member.getPhone().substring(7);
												out.print(phone);
											}
										%>
									</span>
								</label>
							</div>
						</div>
						<div class="x6">
							<div class='c_content clearfix'>
								<label class='fz18'>
									邮箱
									<span class='float-right'>${sessionMember.email}</span>
								</label>
							</div>
						</div>
						<div class="x12">
							<div class='c_content clearfix height-big'>
								<label class='fz18'>
									身份证
									<span class='float-right'>${sessionMember.IDCard }
									<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</div>
						</div>
						<div class="x12">
							<a href="/airportm_toResetPassword.action">
								<div class='c_content clearfix height-big'>
									<label class='fz18'>
										修改密码
										<span class='float-right'> <i
											class='icon-angle-right float-right fz30 margin-big-left'></i>
										</span>
									</label>
								</div> 
							</a>
						</div>
					</div>
					<ul class="font_cat_ls clearfix" style="padding: 20px 0px;">
						<li>
							<!-- <a href="/airport_restaurant.action?flag=jiudian">酒店</a> -->
							<a href="http://m.ctrip.com/webapp/hotel/?&allianceid=303758&sid=776936">酒店</a>
						</li>
						<li>
							<a href="/airport_restaurant.action?flag=canyin">美食</a>
						</li>
						<li>
							<%-- <a href="/airport_travelInformation.action">景点</a> --%>
							<a href="http://m.ctrip.com/webapp/ticket/index?&allianceid=303758&sid=779220">景点</a>
						</li>
						<li>
							<a href="/airport_restaurant.action?flag=xiuxian">娱乐</a>
						</li>
					</ul>
					<div class="tit_tab">
						<a href="/airportm_updatePersonalInfo.action">修改资料</a>
						<a href="/airportm_logout.action" class="b_l_grey c_grey">注销</a>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>
