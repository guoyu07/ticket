<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<s:if test="#flag == 'guonei'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic13.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="172,316,324,462" href="javascript:;"
				alt="" />
			<area shape="rect" coords="172,120,324,268" href="javascript:;"
				alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic15.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="297,511,403,624" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,511,223,624" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,387,223,497" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,262,223,376" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,135,223,252" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,19,206,125" href="javascript:;"
				alt="" />
		</map>
	</div>
</s:else>
