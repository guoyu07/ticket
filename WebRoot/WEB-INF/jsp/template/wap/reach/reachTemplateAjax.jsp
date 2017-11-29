<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<s:if test="flag == 'guonei'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic12.png" 
			usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="176,432,324,576" href="javascript:;" alt="" />
            <area shape="rect" coords="176,231,347,385" href="javascript:;" alt="" />
            <area shape="rect" coords="176,45,320,193" href="javascript:;" alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
          <img src="/template/wap/images/area/pic14.png"  usemap="#m_pic" alt="" />
          <map name="m_pic" id="m_pic">
                <area shape="rect" coords="202,245,330,356" href="javascript:;" alt="" />
                <area shape="rect" coords="203,363,331,474" href="javascript:;" alt="" />
                <area shape="rect" coords="201,486,329,597" href="javascript:;" alt="" />
                <area shape="rect" coords="2,484,130,595" href="javascript:;" alt="" />
                <area shape="rect" coords="0,357,128,468" href="javascript:;" alt="" />
                <area shape="rect" coords="198,126,326,237" href="javascript:;" alt="" />
                <area shape="rect" coords="-3,117,125,228" href="javascript:;" alt="" />
                <area shape="rect" coords="-4,0,124,111" href="javascript:;" alt="" />
          </map>
      </div>
</s:else>
