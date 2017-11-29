<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="i"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:window.location.assign($.cookie('whereUrl'));" class='FL'><i class="icon-angle-left"></i></a>
						国际出发[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>无托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu SIChangeLuggageState">
		                            <li flag="yes">有托运行李</li>
		                            <li flag="no">无托运行李</li>
		                        </ul>
		                    </div>
		                    </span>]
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
						</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国际</a>
					</div>
					<div id="SIContent">
						<div class="c_img text-center mr40">
							<div class="pic1">
								<img src="/template/wap/images/area/pic16_1.png"  usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="107,188,143,222" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="353,126,469,234" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="353,260,469,368" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="353,390,469,498" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="76,410,192,518" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="76,300,192,408" href="javascript:;"
										alt="" />
									<area shape="rect" coords="76,0,192,108" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
										alt="" />
								</map>
							</div>
							<div class="pic2" style='display: none'>
								<img src="/template/wap/images/area/pic16.png"  usemap="#m_pic2" alt="" />
								<map name="m_pic2" id="m_pic2">
									<area shape="rect" coords="244,180,288,223" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,517,483,627" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,387,483,495" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,258,483,360" href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,128,483,230" href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,525,216,627" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,413,216,517" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,304,216,402" href="javascript:;" alt="" />
									<area shape="rect" coords="103,198,197,289" href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="103,108,197,199" href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,0,208,108" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
										alt="" />
								</map>
							</div>
						</div>
					</div>
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<%-- <s:set var="memberFocusFlightObj" value="memberFocusFlightService.queryById('MemberFocusFlight',id)"></s:set>
						<s:if test="#memberFocusFlightObj.ifSet==1"> --%>
							<a href="/airportm_editFocus.action?id=${id }"  class='block c_white'>定制服务<!-- 修改乘机人信息 --></a>
						<%-- </s:if>
						<s:else>
							<a href="javascript:;"  class='block c_white customizedService' id="${id }">定制服务</a>
						</s:else> --%>
					</div>
					<div class="custom_menu mr40 SIServiceQuickMenu">
						
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript">
	$(function() {
		$('.pic1 area').eq(0).on('tap', function() {
			$('.pic2').show();
			$('.pic1').hide();
		});
		$('.pic2 area').eq(0).on('tap', function() {
			$('.pic1').show();
			$('.pic2').hide();
		})
	})
</script>
	</body>
</html>