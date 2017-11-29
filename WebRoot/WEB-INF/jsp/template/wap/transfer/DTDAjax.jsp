<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic3.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="-8,484,136,606" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-6,363,138,476" href="<ticket:common type="positionUrl" value="anjian_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-13,243,131,353" href="<ticket:common type="positionUrl" value="banlizhongzhuanyou_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="0,122,144,239" href="<ticket:common type="positionUrl" value="quxingli_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-4,0,140,119" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>" alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic17.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="-6,432,150,565" href="<ticket:common type="positionUrl" value="dengji_zhongzhuanw_gn_gn"/>"
				alt="" />
			<area shape="rect" coords="-13,289,143,424" href="<ticket:common type="positionUrl" value="chayandengjipai_zhongzhuan_gn_gn"/>"
				alt="" />
			<area shape="rect" coords="-1,144,155,278" href="<ticket:common type="positionUrl" value="banlizhongzhuanwu_zhongzhuan_gn_gn"/>"
				alt="" />
			<area shape="rect" coords="2,2,158,134" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>"
				alt="" />
		</map>
	</div>
</s:else>