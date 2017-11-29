<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic7.png"  usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="180,465,341,604" href="<ticket:common type="positionUrl" value="chufadating"/>"
				alt="" />
			<area shape="rect" coords="180,317,341,450" href="<ticket:common type="positionUrl" value="xinglituoyun_chufa_guonei"/>"
				alt="" />
			<area shape="rect" coords="180,165,341,302" href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
				alt="" />
			<area shape="rect" coords="180,9,341,152" href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
				alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic6.png"
			usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="171,410,348,569" href="<ticket:common type="positionUrl" value="chufadating"/>"
				alt="" />
			<area shape="rect" coords="171,216,348,378" href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
				alt="" />
			<area shape="rect" coords="171,36,348,186" href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
				alt="" />
		</map>
	</div>
</s:else>