<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic18.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="197,492,326,607" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="197,366,326,484" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="197,247,326,355" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="1,486,126,604" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="0,368,125,480" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="3,244,128,362" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="-2,120,131,242" href="<ticket:common type="positionUrl" value="tiquxingli_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="-1,0,132,120" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gj"/>" alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic8.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="294,254,422,365" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="294,378,422,489" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="294,500,422,611" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,500,219,611" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,376,219,487" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,253,219,364" href="<ticket:common type="positionUrl" value="dengjipaijiancha_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,130,219,241" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,0,220,125" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
		</map>
	</div>
</s:else>