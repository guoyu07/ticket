<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="头像设置" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar  third_label_bar">
						<a href="/airportm_updatePersonalInfo.action" class="border10">头像设置</a>
						<a href="/airportm_baseInfo.action" class="border10">基本信息</a>
						<a href="/airportm_detailInfo.action" class='border10'>详细信息</a>
						<a href="/airportm_companyInfo.action" class='border10'>公司信息</a>
						<a href="/airportm_educationInfo.action" class='border10'>教育信息</a>
						<a href="#" class='border10 selected'>其他信息</a>
					</div>

					<div class="mr40">
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									微信账号&nbsp;&nbsp;${memberDetailInfo.weChatId }
									<s:if test="memberDetailInfo.weChatId != null">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
									
								</label>
							</dd>
						</dl>
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									微博账号&nbsp;&nbsp;${memberDetailInfo.weiboNumber }
									<s:if test="memberDetailInfo.weiboNumber != null && memberDetailInfo.weiboNumber != ''">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
								</label>
							</dd>
						</dl>
						<%-- <dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									人人网账号
									<s:if test="memberDetailInfo.renrenAccount != null">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
								</label>
							</dd>
						</dl> --%>
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									QQ账号&nbsp;&nbsp;${sessionMember.qq  }
									<s:if test="#session.sessionMember.qq != null && #session.sessionMember.qq != ''">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
								</label>
							</dd>
						</dl>
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									百度账号&nbsp;&nbsp;${memberDetailInfo.baiduAccount }
									<s:if test="memberDetailInfo.baiduAccount != null">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
								</label>
							</dd>
						</dl>
						<%-- <dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									搜狗账号
									<s:if test="memberDetailInfo.sogouAccount != null">
										<span class='float-right c_l_grey'>已绑定</span>
									</s:if>
									<s:else>
										<span class='float-right c_blue'>绑定</span>
									</s:else>
								</label>
							</dd>
						</dl> --%>
						<button class="button d_button b_blue c_white block" >
							保存
						</button>
					</div>
				</div>

				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>
