<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="航空公司信息"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="share_bar">
						<a href="#"><i class="memberFavorite icon-star-o" title="航空公司信息" url="/airport_flightCompany.action">
						<span style="font-style: normal;color: #4f4b47;margin-left: 5px">收藏</span></i></a>
						<a href="#" class="showdiv_reg" style="display:;"><i class="icon-share-alt"></i>分享</a>
					</div>
					
					<!--自定义登陆弹出框-->
					<div id="popupbgLayer" class="popupbg"></div>
					<iframe id='popupIframeLayer' class='popupIframe' frameborder='0' ></iframe>
					
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32 popupdiv" id="popupLayer_reg">
						<div class="popup_title"><span>分享到</span>
							<div id="closeDiv_reg" class="popupclose_btn"><a href="#">&times;</a></div>
						</div>	
						<div class="popup_show">
							<a class="jiathis_button_tsina">新浪微博</a>
							<a class="jiathis_button_weixin">微信</a>
							<a class="jiathis_button_qzone">QQ空间</a>
							<a class="jiathis_button_cqq">QQ好友</a>
							<a class="jiathis_button_email">邮件</a>
							<a class="jiathis_button_tieba">百度贴吧</a>
							<a class="jiathis_button_baidu">百度空间</a>
							<a class="jiathis_button_meilishuo">美丽说</a>
							<a class="jiathis_button_copy">复制网址</a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
							<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
						</div>
					</div>
					<div class="select_tit">
						<ticket:common type="flightCompanyList" />
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
								<s:if test="#request.flightCompanyList != null">
									<s:iterator id="flightCompany" value="#request.flightCompanyList" status="st">
										<s:if test="#st.first">
											<font style="color: #00AAFF">${flightCompany.name }</font>
										</s:if>
									</s:iterator>
								</s:if>
								<span class="downward" style="color: #00AAFF"></span>
							</button>
							<ul class="drop-menu flightAjax">
								<s:if test="#request.flightCompanyList != null">
									<s:iterator id="flightCompany" value="#request.flightCompanyList">
										<li flightCompanyid="${flightCompany.id }" style="color: #00AAFF">
											${flightCompany.name }
										</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="mr40 flightContent">
						<s:if test="#request.flightCompanyList != null">
							<s:iterator id="flightCompanyObj" value="#request.flightCompanyList" status="st">
								<s:if test="#st.first">
									<table class="table table-bordered c_l_grey">
										<tr>
											<td class="fz26 text-center" colspan="2" style="color: black;">
												<ticket:commonAnnex type="annex" entityUuid="${flightCompanyObj.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="40" width="40" style='position:relative;top:5px;'>
												${flightCompanyObj.name }
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right" width="120">
												电话
											</td>
											<td class="fz18">
												${flightCompanyObj.phone }
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right">
												官网
											</td>
											<td class="fz18">
												<a href="${flightCompanyObj.theOfficialWebsite }" class='c_blue'>${flightCompanyObj.theOfficialWebsite }</a>
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right">
												散客柜台
											</td>
											<td class="fz18">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.customerCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.customerCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right">
												团队柜台
											</td>
											<td class="fz18">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.teamCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.teamCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right">
												头等舱柜台
											</td>
											<td class="fz18">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.firstClassCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.firstClassCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz18 text-right">
												应急柜台
											</td>
											<td class="fz18">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.emergencyCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.emergencyCounter }</a>
												</td>
											</tr>
									</table>
								</s:if>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>