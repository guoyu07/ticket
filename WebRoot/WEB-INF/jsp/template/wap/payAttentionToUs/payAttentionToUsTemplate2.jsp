﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="关注下载" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<jsp:include page="../common/favoriteAndShare.jsp">
						<jsp:param value="${prevUrl }" name="url"/>
						<jsp:param value="关注下载" name="title"/>
					</jsp:include>
					<%-- <%@ include file="../common/favoriteAndShare.jsp" %> --%>
					<div class="c_content text-center" style=" background-color:#eacb42;">

<p style="font-weight:bold; font-size:0.8rem; color:#484634; padding-bottom:20px; border-bottom:5px solid #484634;">
									长水机场 1.1 测试版						</p>
<br>
<center>
<a href="https://itunes.apple.com/cn/app/kun-ming-zhang-shui-ji-chang/id1146258707?mt=8" target="_blank">
<img src="/template/wap/images/iphone1.jpg" alt="" width="60%" /></a><br><br>

<a href="http://app.mi.com/details?id=com.gk.ticket.activity&ref=search" target="_blank">
<img src="/template/wap/images/android1.jpg" alt="" width="60%" /></a>

<br>
<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center">
<a href="http://weibo.com/u/5882266685" target="_blank">
<img src="/template/wap/images/blog1.jpg" alt="" style="width:60%;" /></a>
<br>
<img src="/template/wap/images/Wechat1.jpg" alt=""  style="width:60%; margin-top:20px"  />
<br>
<img src="/template/wap/images/qr2.jpg" alt="" style="width:50%; margin-top:20px"  />
</td>
</tr>

  </tr>
 
</table>

</center>


						<!--<ticket:common type="advertListNew" value="关注我们" size="20"/>
						<s:if test="#request.advertListNew != null">
							<s:iterator id="advert" value="#request.advertListNew">
								<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1"/>
								<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }"/>
								<p>
									${advert.name }
								</p>
								<br>
								<p>
									<s:if test="#session.fromApp == null && #parameters.fromApp == null">
									<button class="button bg-sub text-big OneKeyFocus"
										style='width: 200px; padding: 10px 0px;' url="${advert.url }">
										<s:if test='%{#advert.name.indexOf("下载")>=0}'>
											一键下载
										</s:if>
										<s:elseif test='%{#advert.name.indexOf("微信")>=0}'>扫码关注</s:elseif>
										<s:else>
											一键关注
										</s:else>
									</button>
									</s:if>
									<s:else>
										<button class="button bg-sub text-big OneKeyFocus"
										style='width: 200px; padding: 10px 0px;' url="${advert.url }">
										<s:if test='%{#advert.name.indexOf("下载")>=0}'>
											扫码下载
										</s:if>
										<s:elseif test='%{#advert.name.indexOf("微博")>=0}'>一键关注</s:elseif>
										<s:else>
											扫码关注
										</s:else>
									</button>
									</s:else>
								</p>
								<br>
							</s:iterator>
						</s:if>-->
					</div>
					<s:if test="#session.fromApp == null && #parameters.fromApp == null">
						<div class="c_content text-content" style="display:none;">
							<p class="fz20">
								机场APP火热下载中，安装后可了解最新长水机场动态可获取第一手信息，现在下载安装，赠送5积分。
							</p>
							<a href="itms-services://?action=download-manifest&url=https://www.kcia.com.cn/app/ios/manifest.plist" class="tit block1">机场APP下载活动介绍</a>
						</div>
					</s:if>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>