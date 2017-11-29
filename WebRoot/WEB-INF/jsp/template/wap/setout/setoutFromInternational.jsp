<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadQuickMenu.js"></script>
	<ticket:cache group="资讯中心">
		<body class="mobile">
			<input type="hidden" id="quickMenuPosition" name="quickMenuPosition" value="b"/>
			<div class="mobile-view">
				<div class="mobile-page">
					<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
						<div class="header">
							<a href="javascript:;" class='FLAPP'><i class="icon-navicon"></i></a>
							出发
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
						<div class="tit b_blue " style="height:60px;line-height:60px;">
							<a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
						</div>
						<div class="tit_tab">
							<a href="/airport/chufa.ticket" class="setoutFrom" flag='guonei'><i class="icon-caret-down"></i>国内</a>
							<a href="#" class="setoutFrom selected" flag='guoji'><i class="icon-caret-down"></i>国际</a>
						</div>
							<div id="positionNavigatesetout" class="positionNavigatesetout">
								<div class="c_img text-center mr40">
							         <div class="pic1" style='display:none'>
							             <img src="/template/wap/images/area/pic2_1.png"  usemap="#m_pic" alt="" />
							             <map name="m_pic" id="m_pic">
							                <area shape="rect" coords="272,389,388,489" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="272,260,388,360" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="271,130,387,233" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="2,410,122,510" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="2,290,131,398" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="29,177,70,213" href="#" alt="" />
							                <area shape="rect" coords="0,0,130,108" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>" alt="" />
							             </map>
							         </div>
							         <div class="pic2">
							             <img  src="/template/wap/images/area/pic2.png"  usemap="#m_pic2" alt="" />
							             <map name="m_pic2" id="m_pic2">
							                <area shape="rect" coords="329,129,460,232" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="329,260,460,368" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="328,389,459,490" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="329,522,460,620" href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="63,522,194,620" href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="62,412,193,513" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="67,306,196,401" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="224,184,260,220" href="#" alt="" />
							                <area shape="rect" coords="86,205,175,287" href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="86,114,175,192" href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>" alt="" />
							                <area shape="rect" coords="61,1,186,103" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>" alt="" />
							             </map>
							         </div>
							     </div>
							</div>
						<div class="custom_menu mr40 SIQuickMenu">
							
						</div>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</div>
			<div class="dialog" style="display: none;">
				<%@ include file="../common/left.jsp" %>
			</div>
			<script type="text/javascript">
			    $(function(){
			        $('body').on('tap','.pic1 area:eq(5)',function(){
			            $('.pic2').show();
			            $('.pic1').hide();
			        });
			        $('body').on('tap','.pic2 area:eq(7)',function(){
			            $('.pic1').show();
			            $('.pic2').hide();
			        })
			    })
			</script>
		</body>
	</ticket:cache>
</html>