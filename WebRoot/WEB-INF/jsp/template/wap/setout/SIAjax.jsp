<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<s:if test="flag=='yes'">
	<div class="c_img text-center mr40">
		<div class="pic1">
			<img src="/template/wap/images/area/pic16_1.png"  usemap="#m_pic" alt="" />
			<map name="m_pic" id="m_pic">
				<area shape="rect" coords="107,188,143,222" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
					alt="" />
				<area shape="rect" coords="353,126,469,234" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
					alt="" />
				<area shape="rect" coords="353,260,469,368" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
					alt="" />
				<area shape="rect" coords="353,390,469,498" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
					alt="" />
				<area shape="rect" coords="76,410,192,518" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
					alt="" />
				<area shape="rect" coords="76,300,192,408" href="javascript:;"
					alt="" />
				<area shape="rect" coords="76,0,192,108" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
					alt="" />
			</map>
		</div>
		<div class="pic2" style='display: none'>
			<img src="/template/wap/images/area/pic16.png"  usemap="#m_pic2" alt="" />
			<map name="m_pic2" id="m_pic2">
				<area shape="rect" coords="244,180,288,223" href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,517,483,627" href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,387,483,495" href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,258,483,360" href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="367,128,483,230" href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,525,216,627" href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,413,216,517" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,304,216,402" href="javascript:;"
										alt="" />
									<area shape="rect" coords="103,198,197,289" href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="103,108,197,199" href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>"
										alt="" />
									<area shape="rect" coords="86,0,208,108" href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
										alt="" />
			</map>
		</div>
	</div>
</s:if>
<s:else>
	<div class="c_img text-center mr40">
		<div class="pic1">
			<img src="/template/wap/images/area/pic4_1.png"  usemap="#m_pic" alt="" />
			<map name="m_pic" id="m_pic">
				<area shape="rect" coords="237,186,275,223" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,520,474,623" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,391,474,499" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,252,474,360" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,527,198,623" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,412,198,514" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,301,198,406" href="javascript:;"
					alt="" />
				<area shape="rect" coords="94,207,188,288" href="javascript:;"
					alt="" />
				<area shape="rect" coords="94,115,188,199" href="javascript:;"
					alt="" />
				<area shape="rect" coords="74,0,198,108" href="javascript:;"
					alt="" />
			</map>
		</div>
		<div class="pic2" style='display: none'>
			<img src="/template/wap/images/area/pic4.png"  usemap="#m_pic2" alt="" />
			<map name="m_pic2" id="m_pic2">
				<area shape="rect" coords="237,186,275,223" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,520,474,623" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,391,474,499" href="javascript:;"
					alt="" />
				<area shape="rect" coords="360,252,474,360" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,527,198,623" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,412,198,514" href="javascript:;"
					alt="" />
				<area shape="rect" coords="80,301,198,406" href="javascript:;"
					alt="" />
				<area shape="rect" coords="94,207,188,288" href="javascript:;"
					alt="" />
				<area shape="rect" coords="94,115,188,199" href="javascript:;"
					alt="" />
				<area shape="rect" coords="74,0,198,108" href="javascript:;"
					alt="" />
			</map>
		</div>
	</div>
</s:else>