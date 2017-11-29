<%@ page language="java" pageEncoding="UTF-8"%>
<div class="mobile-foot">
    <div class="c_ft clearfix">
        <div class="ft_more_mask"></div>
        <div class="ft_more_con">
        	<a href='/airport.action' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0s;"><i style="top:16px"></i>首页</a>
        	<ticket:common type="newMessages"/>
        	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
	            <a href='javascript:;' memberId="<s:if test="#session.sessionMember == null">1</s:if><s:else>0</s:else>" 
	            	class='bounceInRight personalCenterBtn' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0s;"><i></i>个人中心
	            	<s:if test="#request.newMessages > 0"><span style="margin-top:5px;position: absolute;" class="badge bg-red">&nbsp;</span></s:if>
	            	</a>
        	</s:if>
        	<s:else>
        		 <a href='/airportm_login.action' memberId="1" class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0s;"><i></i>个人中心</a>
        	</s:else>
            <a href='/bjdj.action' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0s;"><i></i>便捷登机</a>
            <a href='evaluation_listPage.action' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.1s;"><i></i>评价/反馈</a>
            <!-- <a href='http://map.baidu.com/mobile/webapp/index/streetview/pid=1902570001150906150419546UZ&ss_panoType=inter&ss_heading=36&ss_pitch=-23&from=share&psp=&cpinfo=%5B%0A%20%20%7B%0A%20%20%20%20%22y%22%20%3A%201040.355753%2C%0A%20%20%20%20%22z%22%20%3A%20-181.101872%2C%0A%20%20%20%20%22x%22%20%3A%20997.635601%2C%0A%20%20%20%20%22name%22%20%3A%20%22%E7%9C%8B%E8%BF%99%E9%87%8C%22%0A%20%20%7D%0A%5D/vt=streetview%EF%BC%89' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.2s;"><i></i>全景</a> -->
            <a href='javascript:JM.alert("开发中，尽请期待");' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.2s;"><i></i>全景</a>
            <s:if test="news != null && news.mobile != null && news.mobile != ''">
            	<a href='tel:${news.mobile }' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.3s;"><i style="top:15px;"></i>电话</a>
            </s:if>
            <s:else>
            	<ticket:common type="servicePhone"/>
            	<a href='tel:0871-96566' class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.3s;"><i style="top:15px;"></i>电话</a>
            </s:else>
            <ticket:common type="myAnnounecementCount"/>
       		<a href="/airport/newss_announcement1.action" class='bounceInRight' style="-webkit-animation-duration:0.8s; -webkit-animation-delay:0.4s;"><i></i>公告板
       			<s:if test="#request.myAnnounecementCount == 1">
       				<span style="margin-top:5px;position: absolute;" class="badge bg-red">&nbsp;</span>
       			</s:if>
       		</a>
        </div>
        <a href="javascript:;" class="ft_more">
        <c:choose>
			<c:when test="${!empty sessionMember }">
				<ticket:common type="newMessages"/>
				<c:if test="${newMessages > 0 }">
					<span style="margin-top:5px;position: absolute;" class="badge bg-red">&nbsp;</span>
				</c:if>
			</c:when>
			<c:otherwise>
				<ticket:common type="myAnnounecementCount"/>
				<c:if test="${myAnnounecementCount == 1 }">
					<span style="margin-top:5px;position: absolute;" class="badge bg-red">&nbsp;</span>
				</c:if>
			</c:otherwise>
		</c:choose>
		</a>
        <!-- <ticket:common type="newMessages"/>
		<s:if test="#request.newMessages > 0">
        	<a href="javascript:;" class="ft_more"><span style="margin-top:5px;position: absolute;" class="badge bg-red">&nbsp;</span></a>
    	</s:if>
    	<s:else>
    		<a href="javascript:;" class="ft_more"></a>
    	</s:else> -->
    </div>
</div>