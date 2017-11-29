<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='guonei'">
	<div id="positionNavigate" class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic1.png" width="545" height="639"
			usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="193,492,375,616" href="javascript:;"
				alt="" />
			<area shape="rect" coords="193,324,375,474" href="javascript:;"
				alt="" />
			<area shape="rect" coords="309,195,432,288" href="javascript:;"
				alt="" />
			<area shape="rect" coords="144,195,273,288" href="javascript:;"
				alt="" />
			<area shape="rect" coords="193,0,375,158" href="javascript:;" alt="" />
		</map>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
         <div class="pic1">
             <img src="/template/wap/images/area/pic2_1.png"  usemap="#m_pic" alt="" />
             <map name="m_pic" id="m_pic">
                <area shape="rect" coords="272,389,388,489" href="javascript:;" alt="" />
                <area shape="rect" coords="272,260,388,360" href="javascript:;" alt="" />
                <area shape="rect" coords="271,130,387,233" href="javascript:;" alt="" />
                <area shape="rect" coords="2,410,122,510" href="javascript:;" alt="" />
                <area shape="rect" coords="2,290,131,398" href="javascript:;" alt="" />
                <area shape="rect" coords="29,177,70,213" href="javascript:;" alt="" />
                <area shape="rect" coords="0,0,130,108" href="javascript:;" alt="" />
             </map>
         </div>
         <div class="pic2" style='display:none'>
             <img  src="/template/wap/images/area/pic2.png"  usemap="#m_pic2" alt="" />
             <map name="m_pic2" id="m_pic2">
                <area shape="rect" coords="329,129,460,232" href="javascript:;" alt="" />
                <area shape="rect" coords="329,260,460,368" href="javascript:;" alt="" />
                <area shape="rect" coords="328,389,459,490" href="javascript:;" alt="" />
                <area shape="rect" coords="329,522,460,620" href="javascript:;" alt="" />
                <area shape="rect" coords="63,522,194,620" href="javascript:;" alt="" />
                <area shape="rect" coords="62,412,193,513" href="javascript:;" alt="" />
                <area shape="rect" coords="67,306,196,401" href="javascript:;" alt="" />
                <area shape="rect" coords="224,184,260,220" href="javascript:;" alt="" />
                <area shape="rect" coords="86,205,175,287" href="javascript:;" alt="" />
                <area shape="rect" coords="86,114,175,192" href="javascript:;" alt="" />
                <area shape="rect" coords="61,1,186,103" href="javascript:;" alt="" />
             </map>
         </div>
     </div>
</s:else>