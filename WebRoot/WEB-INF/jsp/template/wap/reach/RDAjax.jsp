<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic12.png"  usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="176,432,324,576" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guonei" alt="" />
            <area shape="rect" coords="176,231,347,385" href="<ticket:common type="positionUrl" value="xinglizhuanpan_daoda_guonei"/>" alt="" />
            <area shape="rect" coords="176,45,320,193" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guonei"/>" alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic13.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="172,316,324,462" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guonei"
				alt="" />
			<area shape="rect" coords="172,120,324,268" href="javascript:;"
				alt="" />
		</map>
	</div>
</s:else>