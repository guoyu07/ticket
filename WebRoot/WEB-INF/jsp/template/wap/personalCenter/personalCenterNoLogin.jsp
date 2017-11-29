<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<%-- <ticket:cache group="资讯中心"> --%>
   
 
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
    			<jsp:include page="../common/title.jsp">
					<jsp:param value="个人中心" name="title"/>
				</jsp:include>
				<div class="mobile-main">
                <div style=" border:1px solid #e6e6e6;pointer-events:none; padding:15px 0px 0px 10px; border-radius:10px; margin:40px;">
                <div style="background-color: #fff;
font-size: 12px;
position: absolute;
top: 86px;
width: 64px;
z-index: 100;" >四日天气</div>
                <iframe width="100%" name="airport" id="airport" scrolling="no" height="70" frameborder="0"  src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&py=kunming&num=4"></iframe></div>
					<div class="c_content clearfix" style="display:none;">
						<div> 
							<!-- <img src="/template/wap/images/personal_colud.jpg" height="96" width="96">
							<p class='text-center'>
							</p>  -->
							<div style="float: left;width: 50%;margin-top: 28px;pointer-events:none;" id="curWeather">
								<!-- <iframe id="cw" src="http://i.tianqi.com/index.php?c=code&id=8"  width="225" height="80" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe> -->
								
                                <iframe  allowtransparency="true" frameborder="0" width="195" height="96" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=0&d=1&bd=0&k=&f=&q=1&e=1&a=1&c=54511&w=195&h=96&align=center"></iframe>
							</div>
							<div style="width: 50%; margin-top: 0px;float: left; display:none;" class="x12" >
								<a href="javascript:JM.alert('该功能未开放');"
									class="button b_yello block padding-big text-center text-big">本日进港人数:0</a>
								<br/>
								<a href="javascript:JM.alert('该功能未开放');"
									class="button bg-sub block padding-big text-center text-big">本日离港人数:0</a>
							</div>
						</div>
					</div>
					<div class="tit" id="memberLogin">
						登录个人中心
					</div>
					<ul class="font_pic_ls clearfix padding-large-top">
						<li>
							<ticket:common type="advertListByTypeName" value="未登录个人中心广告图"/>
							<s:if test="#request.advertListByTypeName != null">
								<a href="${advertListByTypeName.url }">
									<ticket:commonAnnex type="annex" entityUuid="${advertListByTypeName.id }" annexType="1" size="1"/>
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
								</a>
							</s:if>
						</li>
					</ul>
					<ul class="font_cat_ls clearfix" style="padding: 20px 0px;">
						<li>
							<a href="http://m.ctrip.com/webapp/hotel/?&allianceid=303758&sid=776936">酒店</a>
						</li>
						<li>
							<a href="/airport_restaurant.action?flag=canyin">美食</a>
						</li>
						<li>
							<a href="http://m.ctrip.com/webapp/ticket/index?&allianceid=303758&sid=779220">景点</a>
						</li>
						<li>
							<a href="/airport_restaurant.action?flag=xiuxian">娱乐</a>
						</li>
					</ul>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	<script type="text/javascript">
        $(window).load(function() {
           $("#cw").attr("disabled",true);
        });
    </script>
	<%-- </ticket:cache> --%>
</html>
