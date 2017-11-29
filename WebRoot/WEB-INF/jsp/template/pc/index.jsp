<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld"%>
<!DOCTYPE html>
<html lang="zh-cn" xmlns:wb="http://open.weibo.com/wb">
	<head>
		<meta property="qc:admins" content="247747144456516711637571120724036134176" />
		<meta property="wb:webmaster" content="6c008415a8c940a5" />
		<!-- <meta property="wb:webmaster" content="21ec8d53640bc3b6" /> -->
		<!-- <meta property="qc:admins" content="667761677353323637571120724036134176" /> -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta name="renderer" content="webkit">
		<title>首页</title>
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link rel="stylesheet" href="/template/pc/css/animate.min.css">
		<link rel="stylesheet" href="/template/pc/css/pintuer.css">
		<link rel="stylesheet" href="/template/pc/css/public.css">
		<link rel="stylesheet" href="/template/pc/css/index.css">
		<link rel="stylesheet" href="/template/pc/css/base.css">
		<script type="text/javascript" src="/template/pc/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/template/pc/js/jquery.carouFredSel-min.js"></script>
		<script type="text/javascript" src="/common/JM.js"></script>
		<script type="text/javascript" src="/template/pc/js/pc.js"></script>
		<script type="text/javascript" src="/template/pc/js/script.js"></script>
		<script type="text/javascript" src="/template/pc/js/png.js"></script>
		
		<link rel="stylesheet" href="/common/easydialog/easydialog.css" />
		<script src="/common/easydialog/easydialog.min.js"></script>
		<script type="text/javascript" src="/template/pc/js/flightNotice.js"></script>
		<script type="text/javascript">
			EvPNG.fix('img,ol,div,a,button');
			function MM_jumpMenu(targ, selObj, restore) {
				eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
						+ "'");
				if (restore)
					selObj.selectedIndex = 0;
			}
		</script>
		<script type="text/javascript" src="/template/pc/js/user.js"></script>
		<script type="text/javascript" src="/template/pc/js/right_menu.js"></script>
		<script type="text/javascript" src="/template/pc/js/door.js"></script>
		<script type="text/javascript" src="/template/pc/js/ppp.js"></script>
		<script src="/common/validForm/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="/template/pc/js/pcBjdjMember.js"></script>
		<script type="text/javascript">
			$(function() {
				$("li.mainmenu").hover(function() {
					$(this).find("dt").stop(true, true);
					$(this).find("dt").slideDown();
				}, function() {
					$(this).find("dt").stop(true, true);
					$(this).find("dt").slideUp();
				});
				
			})
			function logout(){
				$.getJSON("pcBjdjMember_logouts.action",function(data){
					if(data.status == 'y'){
						JM.alert(data.info, 1000, function(){
							 JM.goUrl("/airportPc.action");
						});
					}
				});
			}
			function MM_jumpMenu(targ, selObj, restore) { //v3.0
				eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
						+ "'");
				if (restore)
					selObj.selectedIndex = 0;
			}
		</script>
		<script type="text/javascript" src="/template/pc/js/jquery.carouFredSel-min.js"></script>
		<script type="text/javascript" src="/template/pc/js/script.js"></script>
		<script type="text/javascript" src="/template/pc/js/pc.js"></script>
		<style type="text/css">
		.ind_tool {
		  background: rgba(0, 0, 0, 0) url("http://www.kmcsia.com/template/pc/image/bg_black_0.3.png") repeat scroll 0 0;
		  height: 538px;
		  left: 25px;
		  position: fixed;
		  top: 120px;
		  width: 162px;
		  z-index: 5;
		}
		</style>
	</head>
	<body>
		<div class="ind_s1">
			<div class="s1_kv">
				<ticket:common type="advertListNew" value="PC首页图片" size="10"/>
				<ul>
					<s:if test="#request.advertListNew != null">
						<s:iterator id="advert" value="#request.advertListNew">
							<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
							<li style='background-image: url(<%=request.getScheme() %>://${image_server_url}${annex.annexPath})'>
								<a href="${advert.url }" class="effect"></a>
							</li>
						</s:iterator>
					</s:if>
					<s:else>
					<li style='background-image: url(/template/pc/image/ind/s1_bg.jpg)'>
						<a href="#" class="effect"></a>
					</li>
					</s:else>
				</ul>
				<div class="s1_btns">
					<span class='nums'>1/2</span>
					<span class='btns'><a href="javascript:;"></a><a
						href="javascript:;"></a> </span>
				</div>
			</div>
			<div class="wp">
				<div class="ind_header">
					<a class="logo" href="/airportPc.action"></a>
					<div class="header_right">
						 <s:if test="#session.sessionMember == null">
							<a href="/pcMembers_toLoginPage.action" class="login_btn"
								id="login">登录</a>
							<a href="/pcMembers_register.action" class="reg_btn"
								id="register">注册</a>
						</s:if>
						
						<s:if test="#session.sessionMember != null">
							<div class="head">
								<li class="mainmenu hh">
									<s:if test="sessionMember.images != null">
									<img src="${sessionMember.images }" align="absmiddle" width="50px" height="50px">
								</s:if> 
								<s:else>
									<img src="/template/wap/images/no_avatar.png" align="absmiddle" width="50px" height="50px">
								</s:else>
									欢迎您，
									<a href="/airportPc_personalCenter.action"> <s:if
											test="#session.sessionMember.nickName != null">
								${sessionMember.nickName }
								</s:if> <s:else>
								${sessionMember.phone }
								</s:else> </a>
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
										<a href="/airportPc_airportFAQ.action"><p class="p4">
												帮助中心
											</p> </a>
										<a href="javascript:logout();"><p class="p5">
												注销
											</p> </a>
									</dt>
								</li>
							</div>
						</s:if> 
						<div class="menu"></div>
					</div>
				</div>


				<div class="ind_menu">
					<a class="ind_news_close" href="javascript:;"><i class='effect'></i>
					</a>
					<ul>
						<li>
							<a href="/airportPc.action" class="effect"><span>长水首页
									<p>
										Home
									</p> </span><em></em> </a>
						</li>
						<li>
							<a href="/airportPc_flightQuery.action" class="effect"><span>航班信息
									<p>
										Flight
									</p> </span><em></em> </a>
						</li>
						<ticket:systemCommon type="getNewsClassObj" value="changshuidongtai" />
						<li>
							<a href="/airport/newsList/${getNewsClassObj.id}.html" class="effect"><span>长水动态
									<p>
										News
									</p> </span><em></em> </a>
						</li>
						<li>
							<a href="/airportPc_trafficGuide.action" class="effect"><span>交通指南
									<p>
										Traffic Guide
									</p> </span><em></em> </a>
						</li>
						<li>
							<a href="#" class="effect"><span>票务指南
									<p>
										Air tichets
									</p> </span><em></em> </a>
						</li>
						<li>
							<a href="/airportPc_trafficGuideMenu.action" class="effect"><span>旅客服务
									<p>
										Service
									</p> </span><em></em> </a>
						</li>
						<li>
							<a href="/airportPc_businessDisplay.action" class="effect"><span>机场商业
									<p>
										Buesiness
									</p> </span> </a>
						</li>
					</ul>
				</div>
				<div class="kv"></div>
			</div>
			<div class="s1_form">
				<div class="wp">
					<form action="/airportPc_flightQuery.action" method="post">
						<div class="tab">
							<a href="javascript:;" class="go_btn selected selectFlightFlag" flightFlag="depart"></a>
							<a href="javascript:;" class="change_btn selectFlightFlag" flightFlag="arrival"></a>
							<input type="hidden" id="flightFlag" value="depart" />
						</div>
						<div class="search">
							<input type="text" name="keyword" id="keyword" placeholder='请输入客机航班号或城市'>
							<button id="indexSearchFlight" onclick="return false;"></button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="ind_s2">
			<div class="wp posr">
                <div class="ind_tool">
                    <div class="weather">
                        <iframe allowtransparency="true" frameborder="0" width="140" height="128" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=1&d=1&bd=0&k=000000&f=ffffff&q=1&e=1&a=1&c=54511&w=140&h=128&align=center"></iframe>
                    </div>
                    <div class="ind_tool_menu">
                        <ul class="clearfix">
                            <li>
                                <a href="/airportPc_checkOnLine.action" class="s1"><em></em><span>网上值机</span>
                                </a>
                            </li>
                            <li>
                                <a href="/pcElectromobile.action" class="s2"><em></em><span>电瓶车</span>
                                </a>
                            </li>
                            <li>
                                <a href="/airportPc_flightDelayNotice.action" class="s3"><em></em><span>机场简介</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="s4"><em></em><span>机场全景</span> </a>
                            </li>
                            <li>
                                <a href="/airportPc_serviceHotLine.action" class="s5"><em></em><span>机场热线</span>
                                </a>
                            </li>
                            <li>
                                <a href="/pCEvaluation_manage.action" class="s6"><em></em><span>评价列表</span> </a>
                            </li>
                        </ul>
                    </div>
                </div>
				<div class='title'></div>
				<div class="guide">
					<a href="/airportPc_checkOnLine.action" class="g1 effect"> <img
							src="/template/pc/image/pics/ind_pic1.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									网上值机
								</h2>
								<h3>
									ONLINE CHECK-IN
								</h3>
								<h4>
									01.
								</h4>
							</div>
						</div> </a>
					<a href="/pcMembers_domesticArriveHaveNo.action" class="g2 effect"> <img
							src="/template/pc/image/pics/ind_pic2.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									到达
								</h2>
								<h3>
									ARRIVALS
								</h3>
								<h4>
									02.
								</h4>
							</div>
						</div> </a>
					<a href="/pcMembers_domesticGoNoFlightInfo.action" class="g3 effect"> <img
							src="/template/pc/image/pics/ind_pic3.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									出发
								</h2>
								<h3>
									SET OFF
								</h3>
								<h4>
									03.
								</h4>
							</div>
						</div> </a>
					<ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
					<a href="/airport/newsList/${getNewsClassObj.id}.html" class="g4 effect"> <img
							src="/template/pc/image/pics/ind_pic4.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									乘机须知
								</h2>
								<h3>
									FLY NOTES
								</h3>
								<h4>
									04.
								</h4>
							</div>
						</div> </a>
					<a href="pcOrder.action" class="g5 effect"> <img
							src="/template/pc/image/pics/ind_pic5.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									便捷登机
								</h2>
								<h3>
									ONLINE CHECK-IN
								</h3>
								<h4>
									05.
								</h4>
							</div>
						</div> </a>
					<a href="/pcMembers_domesticToDomesticHaveNo.action" class="g6 effect"> <img
							src="/template/pc/image/pics/ind_pic6.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									中转
								</h2>
								<h3>
									TRANSIT
								</h3>
								<h4>
									06.
								</h4>
							</div>
						</div> </a>
					<a href="#" class="g7 effect"> <img
							src="/template/pc/image/pics/ind_pic7.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									票务指南
								</h2>
								<h3>
									ONLINE CHECK-IN
								</h3>
								<h4>
									07.
								</h4>
							</div>
						</div> </a>
					<a href="/airportPc_businessDisplay.action" class="g8 effect">
						<img src="/template/pc/image/pics/ind_pic8.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									机场商业
								</h2>
								<h3>
									AIRPORT BUSINESS
								</h3>
								<h4>
									08.
								</h4>
							</div>
						</div> </a>
					<a href="/airportPc_trafficGuide.action" class="g9 effect"> <img
							src="/template/pc/image/pics/ind_pic9.jpg">
						<div class="mask">
							<div class="con_t">
								<h2>
									交通指南
								</h2>
								<h3>
									ONLINE CHECK-IN
								</h3>
								<h4>
									09.
								</h4>
							</div>
						</div> </a>
				</div>
				<div class="server_icons">
					<a href="/airportPc_payAttentionToUs.action" class="icon1"></a>
					<ticket:systemCommon type="getNewsClassObj" value="jichangwenhua" />
					<a href="/airport/newsList/${getNewsClassObj.id}.html" class="icon2"></a>
					<a href="/airportPc_payAttentionToUs.action" class="icon3"></a>
					<a href="/airportPc_flightDelayNotice.action" class="icon4"></a>
				</div>
			</div>
		</div>
		<!-- 新闻中心 长水动态  -->
		<ticket:systemCommon type="getNewsClassObj" value="changshuidongtai" />
		<div class="ind_news">
			<div class="wp posr">
				<div class="title">
					<a class="ind_news_more"
						href="/airport/newsList/${getNewsClassObj.id}.html"></a>
				</div>
				<ticket:systemCommon type="getNewsClassObj" value="jichangyaowen" />
				<ticket:systemCommon type="getNewsListByNewsClassId"
					value="${getNewsClassObj.id}" size="3" />
				<div class="pics">
					<ul id='news_kv' class='news_kv'>
						<s:iterator id="n" value="#request.getNewsListByNewsClassId">
							<ticket:commonAnnex type="queryAnnexList" entityUuid="${n.id}"
								annexType="4" size="1" />
							<s:if test="#request.queryAnnexList != null">
								<li>
									<img src="${queryAnnexList[0].annexPath}"  width='800' height="580"/>
								</li>
							</s:if>
							<s:else>
								<li>
									<img src="/template/pc/image/pics/ind_pic10.jpg" width='800' height="580" />
								</li>
							</s:else>
						</s:iterator>
					</ul>
					<div class="text">
						<ul id='news_kv_thumb'>
							<s:iterator id="n" value="#request.getNewsListByNewsClassId">
								<li>
									<a href="/airport/newsView/${n.id}.html"><ticket:format
											value="${n.title}" size="20" /> <br /> ${n.subTitle}
										<div class="posttime">
											<s:date name="#n.status.createTime" format="yyyy/MM/dd" />
										</div> </a>
								</li>
							</s:iterator>
						</ul>
						<div class="text_btns">
							<a class="prev" href="javascript:;"></a>
							<a class="next" href="javascript:;"></a>
						</div>
						<a href="javascript:;" class="text_next"></a>
					</div>
				</div>
				<ticket:systemCommon type="getNewsClassObj" value="tupianxinwen" />
				<ticket:systemCommon type="getNewsListByNewsClassId"
					value="${getNewsClassObj.id}" size="4" />
				<div class="ind_newslist">
					<ul>
						<s:iterator id="n" value="#request.getNewsListByNewsClassId">
							<li>
								<a href="/airport/newsView/${n.id}.html" class="effect"> <ticket:commonAnnex
										type="queryAnnexList" entityUuid="${n.id}" annexType="4"
										size="1" /> <s:if test="#request.queryAnnexList != null">
										<img src="${queryAnnexList[0].annexPath}" width="470" height='338'/>
									</s:if> <s:else>
										<img src="/template/pc/image/pics/ind_pic11.jpg" width="470" height='338'/>
									</s:else>
									<p>
										<ticket:format value="${n.title}" size="25" />
										<span></span>
									</p> </a>
							</li>
						</s:iterator>
					</ul>
					<ticket:systemCommon type="getNewsClassObj" value="shipinzhanshi" />
					<div class="ind_video">
						<a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'> <img
								src="/template/pc/image/pics/ind_pic15.jpg" width='250' height='676'>
							<div class="mask">
								<span></span>
							</div> </a>
					</div>
				</div>

			</div>
		</div>

		<div class="ind_help">
			<div class="bg_l"></div>
			<div class="bg_r"></div>
			<div class='wp'>
				<div class="ind_help_title"></div>
				<ul class="clearfix line">
					<li class="ind_help_item item1">
						<h2>
							乘机指南
						</h2>

						<h3>
							FLY GUIDE
						</h3>
						<ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
						<a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'>乘机须知  /  Flight information</a>
						<a href="/airport/newsView/1446185712168.html" class='effect'>临时乘机证明
							/ Opportunity to prove</a>
						<a href="/airportPc_serviceHotLine.action" class='effect'>服务热线（机场）
							/ Airport Service Hotline</a>
						<a href="/airportPc_flightCompanyInfo.action" class='effect'>航空公司
							/ Airline company</a>
						<a href="/airport/newsView/1446175559324.html" class='effect'>72小时过境签
							/ 72 hours transit sign</a>
						<a href="/airport/newsView/1446185024277.html" class='effect'>落地签自拍系统
							/ Landing sign self system</a>
						<a href="/airportPc_flightQuery.action" class='effect'>航班查询 / Flight inquiry</a>
						<a href="/airportPc_checkOnLine.action" class='effect'>网上值机 / Online check-in</a>
						<a href="" class='effect'>票务指南 / Ticket Guide</a>
						<a href="/airport/newsView/1446172391293.html" class='effect'>头等舱服务
							/ First class service</a>
						<%-- <ticket:systemCommon type="getNewsClassObj" value="xinglituoyun" />
						<a href="/airport/newsList/${getNewsClassObj.id}.html"
							class='effect'>行李托运 / luggage transportation</a> --%>
						<a href="/airportPc_disclaimer.action" class='effect'>免责申明 /
							disclaimer</a>
					</li>
					<li class="ind_help_item item2">
						<h2>
							信息查询
						</h2>
						<h3>
							INFORMATION INQUIRY
						</h3>
						<a href="/airport/newsView/1446185801121.html" class='effect'>遗失物品查询 / Lost item query</a>
						<a href="/pcElectromobile.action" class='effect'>电瓶车服务 /
							Battery car service</a>
						<a href="/pcOrder.action" class='effect'>便捷登机/ Flight Status</a>
						<ticket:systemCommon type="getNewsClassObj" value="wenxuntai" />
						<a href="/airport/newsList/${getNewsClassObj.id}.html"
							class='effect'>问讯中心 / The information desk</a>
						<a href="/airport/newsView/1446175448090.html" class='effect'>行李打包
							/ Packing</a>
						<a href="/airport/newsView/1446175628277.html" class='effect'>行李寄存
							/ Luggage Deposit</a>
						<ticket:systemCommon type="getNewsClassObj" value="xinglichaxun" />
						<a href="/airport/newsList/${getNewsClassObj.id}.html"
							class='effect'>行李查询 / Baggage inquiry</a>
						<ticket:systemCommon type="getNewsClassObj" value="shangwuzhongxin" />
						<a href="/airport/newsList/${getNewsClassObj.id}.html"
							class='effect'>商务中心 / Business Centre</a>
					</li>
					<li class="ind_help_item item3">
						<h2>
							机场服务
						</h2>
						<h3>
							AIRPORT SERVICE
						</h3>
						<a href="/airport/newsView/1446106183074.html" class='effect'>卫生间
							/ TOILET </a>
						<a href="/airport/newsView/1446175364027.html" class='effect'>吸烟室
							/ Smoking Room</a>
						<a href="/airport/newsView/1446175886277.html" class='effect'>更衣室
							/ Locker Room</a>
						<a href="/airport/newsView/1453778265833.html" class='effect'>儿童娱乐区
							/ entertainment for children</a>
						<a href="/airport/newsView/1446176429246.html" class='effect'>公用电话
							/ Public phone</a>
						<a href="/airport/newsView/1446176545699.html" class='effect'>自动售货机
							/ Vending machine</a>
						<a href="/airport/newsView/1446176637605.html" class='effect'>自助充电站
							/ Self charging station</a>
						<a href="/airport/newsView/1446176733699.html" class='effect'>饮水机
							/ Water dispenser</a>
						<a href="/airport/newsView/1446176842684.html" class='effect'>手推车
							/ trolley</a>
						<a href="/airport/newsView/1446176987074.html" class='effect'>WIFI取号机
							/ WIFI take number machine</a>
						<a href="/airport/newsView/1446211475980.html" class='effect'>自助存取款机
							/ ATM</a>
					</li>
					<li class="ind_help_item item4">
						<h2>
							爱心服务
						</h2>
						<h3>
							OTHER
						</h3>
						<a href="/airport/newsView/1453864857457.html" class='effect'>老年人及残疾人候机区
							/ Disabled</a>
						<a
							href="/airport/newsView/159ffc61-7b73-43b4-994e-85448da8dc41.html"
							class='effect'>母婴候机区 / Waiting area</a>
						<a
							href="/airport/newsView/402fb597-d437-41c0-8486-081c0169f9a1.html"
							class='effect'>母婴室 / room of mother and infant</a>
						<a href="/airport/newsView/1446436247822.html" class='effect'>残障人士专用卫生间/
							Private bathroom</a>
						<a href="/airport/newsView/1445676839067.html" class='effect'>轮椅服务
							/ Wheelchair service</a>
						<a href="/airport/newsView/1446173505027.html" class='effect'>儿童及婴儿手推车
							/ Child and baby trolley</a>
						<a href="/airport/newsView/1446173978965.html" class='effect'>无人陪护老人/儿童旅客 / Old man / child</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="ind_foot">
			opyright yunnan airport group co.,ltd. All Rights Reserved.
		</div>
		<script>
			$(function (){
				$(".chufaType").on("click",function (){
					var isChufa = $(this).attr("attrValue");
					$("#isChufa").val(isChufa);
					$(".chufaType").removeClass("selected");
					$(this).addClass("selected");
				});
			});
		
		</script>
	</body>
</html>
