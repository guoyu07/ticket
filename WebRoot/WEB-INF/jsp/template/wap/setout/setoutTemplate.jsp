<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="quickMenuPosition" name="quickMenuPosition" value="a"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
					</div>
					<div class="tit_tab">
						<a href="#" class="selected setoutFrom" flag='guonei'><i class="icon-caret-down"></i>国内</a>
						<a href="/airport_setoutFromInternational.action" class="" flag='guoji'><i class="icon-caret-down"></i>国际</a>
					</div>
					<div class="c_img text-center mr40 padding-big">
						<img src="/template/wap/images/area/pic1.png" 
							usemap="#m_pic" alt="" />
							<map name="m_pic" id="m_pic">
								<area shape="rect" coords="193,492,375,616" href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
									alt="" />
								<area shape="rect" coords="193,324,375,474" href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
									alt="" />
								<area shape="rect" coords="309,195,432,288" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guonei"/>"
									alt="" />
								<area shape="rect" coords="144,195,273,288" href="<ticket:common type="positionUrl" value="zizhuzhiji_chufa_guonei"/>"
									alt="" />
								<area shape="rect" coords="193,0,375,158" href="<ticket:common type="positionUrl" value="chufadating_chufa_guonei"/>"
									alt="" />
							</map>
					</div>
					<div class="custom_menu mr40 SDQuickMenu">
						
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
</html>