<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
         <img src="/template/wap/images/area/pic14.png"  usemap="#m_pic" alt="" />
	    <map name="m_pic" id="m_pic">
	          <area shape="rect" coords="202,245,330,356" href="<ticket:common type="positionUrl" value="haiguanjiancha_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="203,363,331,474" href="<ticket:common type="positionUrl" value="jianyanjianyixingli_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="201,486,329,597" href="<ticket:common type="positionUrl" value="xinglitiqu_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="2,484,130,595" href="<ticket:common type="positionUrl" value="bianfangjiancha_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="0,357,128,468" href="<ticket:common type="positionUrl" value="kouanqianzheng_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="198,126,326,237" href="/airport_trafficGuide.action" alt="" />
	          <area shape="rect" coords="-3,117,125,228" href="<ticket:common type="positionUrl" value="jianyanjianyiren_daoda_guoji"/>" alt="" />
	          <area shape="rect" coords="-4,0,124,111" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guoji"/>" alt="" />
	    </map>
     </div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic15.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="297,511,403,624" href="/airport_trafficGuide.action" alt="" />
			<area shape="rect" coords="91,511,223,624" href="<ticket:common type="positionUrl" value="haiguanjiancha_daoda_guoji"/>" />
			<area shape="rect" coords="91,387,223,497" href="<ticket:common type="positionUrl" value="bianfangjiancha_daoda_guoji"/>" />
			<area shape="rect" coords="91,262,223,376" href="<ticket:common type="positionUrl" value="kouanqianzheng_daoda_guoji"/>" />
			<area shape="rect" coords="91,135,223,252" href="<ticket:common type="positionUrl" value="jianyanjianyiren_daoda_guoji"/>" />
			<area shape="rect" coords="91,19,206,125" href="javascript:;" alt="" />
		</map>
	</div>
</s:else>