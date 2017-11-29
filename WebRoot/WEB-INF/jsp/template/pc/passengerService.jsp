<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
	function g(o){return document.getElementById(o);} 
	function HoverLi10(n){ 
		var isize = $("#isize").val();
		for(var i=1;i<=isize;i++){
			g('tb10_'+i).className='normaltab_10';
			g('tbc10_0'+i).className='undis';
		}
		g('tbc10_0'+n).className='dis';
		g('tb10_'+n).className='hovertab_10';
	}
</script>
<title>旅程须知 - 云南省昆明市长水机场</title>
</head>
<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<%@ include file="common/search.jsp"%>
	<div class="banner_27"></div banner>
	<div class="place w980 mt30">
		当前位置: <a href="/airportPc.action">首页</a> > 
		<a href="#">旅客须知</a>
	</div place w980 mt30>

	<div class="w980 mt30">
		<div class="LK_server other_L bhh">
			<ul id="tb11_">
				<li id="tb11_1" class="hovertab_11" onclick="x:HoverLi11(1);">
					行李打包</li>
				<li id="tb11_2" class="normaltab_11" onclick="x:HoverLi11(2);">
					行李寄存</li>
				<li id="tb11_3" class="normaltab_11" onclick="x:HoverLi11(3);">
					行李托运</li>
				<li id="tb11_4" class="normaltab_11" onclick="x:HoverLi11(4);"
					style="width: 187px; border-right: none;">行李查询</li>
			</ul tb11_>

			<h2 class="dis" id="tbc11_01">
				<ticket:pcCommon type="queryNewsByTitle" value="行李打包" />
				<s:if test="#queryNewsByTitle.pcContent != null">				
				${queryNewsByTitle.pcContent }
				</s:if>
				<s:else>
				${queryNewsByTitle.content }
				</s:else>
			</h2>
			<h2 class="undis" id="tbc11_02">
				<ticket:pcCommon type="queryNewsByTitle" value="行李寄存" />
				<s:if test="#queryNewsByTitle.pcContent != null">				
				${queryNewsByTitle.pcContent }
				</s:if>
				<s:else>
				${queryNewsByTitle.content }
				</s:else>
			</h2>
			<h2 class="undis" id="tbc11_03">
				<ticket:common type="newsList" value="${newsClass2.id }" size="100" />
				<s:if test="#request.newsList != null">
						<s:iterator id="news" value="#request.newsList" status="st">
								<div class="dis" id="tbc20_0${st.count }">
								<s:if test="#news.pcContent != null">
									${news.pcContent}
								</s:if>
								<s:else>
									${news.content}
								</s:else>
								</div>
								</s:iterator>
								</s:if>
			</h2>
			<h2 class="undis" id="tbc11_04">
				<br />
				<ticket:common type="newsList" value="${newsClass.id }" size="100" />
					<s:if test="#request.newsList != null">
							<s:set var="size" value="#request.newsList.size()"></s:set>
							<input value="${size }" id="isize" type="hidden"/>
						<s:iterator id="news" value="#request.newsList" status="st">
							<s:if test="#st.first">
								<li id="tb10_${st.count }" class="hovertab_10" onclick="x:HoverLi10(${st.count});"> ${news.title } </li>
							</s:if>
							<s:else>
								<li id="tb10_${st.count }" class="normaltab_10" onclick="x:HoverLi10(${st.count});"> ${news.title }</li>
							</s:else>
						</s:iterator>
					</s:if>
					<br />
					<s:if test="#request.newsList != null">
						<s:iterator id="news" value="#request.newsList" status="st">
							<s:if test="#st.first">
								<div class="dis" id="tbc10_0${st.count }">${news.content}
								<br />
								<ticket:common type="locationListById" value="${newsCommonObj.infoPosition_id }"/>
								<s:if test="#request.locationListById != null">
									<s:iterator id="location" value="#request.locationListById">
										<s:if
											test="#location.mobile != null && #location.mobile !='' ">
											<center
												>
												${location.name } <a href="tel:${location.mobile }"
													class='c_blue float-right'> <em class='quick_mobile'></em>一键拨号</a>
											</center>
										</s:if>
										<s:else>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<center class='padding-bottom fz22'>
													${location.name } <a href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=下载App能享受室内导航服务&output=html'"
														class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
												</center>
											</s:if>
											<s:else>
												<center class='padding-bottom fz22'>
													${location.name } <a
														href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber}"
														class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
												</center>
											</s:else>
										</s:else>
									</s:iterator>
								</s:if>
								</div>
							</s:if>
							<s:else>
								<div class="undis" id="tbc10_0${st.count }">${news.content}
								
								<ticket:common type="locationListById" value="${news.infoPosition_id }"/>
								<s:if test="#request.locationListById != null">
									<s:iterator id="location" value="#request.locationListById">
										<s:if
											test="#location.mobile != null && #location.mobile !='' ">
											<center
												class='padding-bottom fz22 border-small border-bottom'>
												${location.name } <a href="tel:${location.mobile }"
													class='c_blue float-right'> <em class='quick_mobile'></em>一键拨号</a>
											</center>
										</s:if>
										<s:else>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<center class='padding-bottom fz22'>
													${location.name } <a href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=下载App能享受室内导航服务&output=html'"
														class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
												</center>
											</s:if>
											<s:else>
												<center class='padding-bottom fz22'>
													${location.name } <a
														href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber}"
														class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
												</center>
											</s:else>
										</s:else>
									</s:iterator>
								</s:if>
								</div>
							</s:else>
						</s:iterator>
					</s:if>
			</h2>
			<%@ include file="common/FAQButton.jsp"%>
		</div CJXZ_door other_L bhh>

		<div id="Menu" class="MyMenu hh">
			<h2>
				旅客须知
			</h2>
			<!-- <div>
				<p>
					<a href="javascript:void(0);">旅客须知</a>
				</p>
			</div> -->
		</div>
	</div w980 mt30>

	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
