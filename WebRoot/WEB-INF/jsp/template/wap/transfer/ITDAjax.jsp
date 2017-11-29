<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic9.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="-1,121,127,232" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="0,246,128,357" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="-1,367,127,478" href="<ticket:common type="positionUrl" value="xinglitiqu_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="-1,493,127,604" href="<ticket:common type="positionUrl" value="jianyanjianyixingli_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="201,492,329,603" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="201,250,329,361" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="203,373,331,484" href="<ticket:common type="positionUrl" value="zhongzhuanbanli_zhongzhuan_gj_gn"/>" alt="" />
              <area shape="rect" coords="198,120,330,240" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gn"/>" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic10.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="205,494,333,605" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="1,494,129,605" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="1,370,129,481" href="<ticket:common type="positionUrl" value="denghouxinglichayan_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="0,249,128,360" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="-1,122,127,233" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
		</map>
	</div>
</s:else>