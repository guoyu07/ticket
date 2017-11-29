<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp' %>
<s:if test="transferLeft == '国内' && transferRight == '国内'">
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
		<br>
		<br>
		<div class="fz22 text-left" style="margin-left: 18px">
        	<span class="c_red">温馨提示：</span>
        	已在始发站办理过联程联运的旅客，无需<br>在中转地提取行李，可按无行李流程办理。
        </div>
        <br>
	 </div>
	 <div class="custom_menu mr40 transferQuickMenu">
		<div class="line text-center">
			<s:if test="#session.sessionMember == null">
				<ticket:common type="visiorMenuList" value="c"/>
				<s:if test="#request.visiorMenuList != null">
					<s:iterator id="visitorMenu" value="#request.visiorMenuList">
						<div class="x3">
							<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
							<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
						</div>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="c"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:if>
			<s:else>
				<!-- 会员自定义快捷菜单 -->
				<ticket:common type="memberMenuList" value="c"/>
				<s:if test="#request.memberMenuList != null">
					<s:iterator id="menuSet" value="#request.memberMenuList">
						<ticket:common type="quickMenuObj" value="${menuSet.quickMenu_id }"/>
							<s:if test="#request.quickMenuObj != null">
								<div class="x3">
									<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
										<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
								</div>
							</s:if>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="c"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:else>
		</div>
	</div>
</s:if>
<s:elseif test="transferLeft == '国内' && transferRight == '国际'">
	 <div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic8.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="291,386,419,497" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
			<area shape="rect" coords="92,619,220,743" href="<ticket:common type="positionUrl" value="haiguan_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="294,510,422,621" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="294,624,422,744" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,500,219,611" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,376,219,487" href="<ticket:common type="positionUrl" value="chayandengjipai_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,253,219,364" href="<ticket:common type="positionUrl" value="tongchengxiuxitingdenghou_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,130,219,241" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
            <area shape="rect" coords="91,0,220,125" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gj"/>" alt="" />
		</map>
		<br>
		<br>
		<div class="fz22 text-left" style="margin-left: 18px">
        	<span class="c_red">温馨提示：</span>
        	已在始发站办理过联程联运的旅客，无需<br>在中转地提取行李，可按无行李流程办理。
        </div>
        <br>
	</div>
	<div class="custom_menu mr40 transferQuickMenu">
		<div class="line text-center">
			<s:if test="#session.sessionMember == null">
				<ticket:common type="visiorMenuList" value="d"/>
				<s:if test="#request.visiorMenuList != null">
					<s:iterator id="visitorMenu" value="#request.visiorMenuList">
						<div class="x3">
							<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
							<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
						</div>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="d"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:if>
			<s:else>
			<!-- 会员自定义快捷菜单 -->
				<ticket:common type="memberMenuList" value="d"/>
				<s:if test="#request.memberMenuList != null">
					<s:iterator id="menuSet" value="#request.memberMenuList">
						<ticket:common type="quickMenuObj" value="${menuSet.quickMenu_id }"/>
							<s:if test="#request.quickMenuObj != null">
								<div class="x3">
									<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
										<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
								</div>
							</s:if>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="d"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:else>
		</div>
	</div>
</s:elseif>
<s:elseif test="transferLeft == '国际' && transferRight == '国内'">
	<div class="c_img text-center mr40">
		<img src="/template/wap/images/area/pic10.png" usemap="#m_pic" alt="" />
		<map name="m_pic" id="m_pic">
			<area shape="rect" coords="205,873,333,984" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="0,622,127,738" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gj_gn"/>">
            <area shape="rect" coords="-1,753,128,862" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gj_gn"/>">
            <area shape="rect" coords="-1,871,127,987" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>">
            <area shape="rect" coords="1,494,129,605" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="1,370,129,481" href="<ticket:common type="positionUrl" value="tongchengxiuxitingdenghou_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="0,249,128,360" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="-1,122,127,233" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
            <area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
		</map>
		<br>
		<br>
		<div class="fz22 text-left" style="margin-left: 18px">
        	<span class="c_red">温馨提示：</span>
        	已在始发站办理过联程联运的旅客，无需<br>在中转地提取行李，可按无行李流程办理。
        </div>
        <br>
	</div>
	<div class="custom_menu mr40 transferQuickMenu">
		<div class="line text-center">
			<s:if test="#session.sessionMember == null">
				<ticket:common type="visiorMenuList" value="c"/>
				<s:if test="#request.visiorMenuList != null">
					<s:iterator id="visitorMenu" value="#request.visiorMenuList">
						<div class="x3">
							<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
							<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
						</div>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="c"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:if>
			<s:else>
				<!-- 会员自定义快捷菜单 -->
				<ticket:common type="memberMenuList" value="c"/>
				<s:if test="#request.memberMenuList != null">
					<s:iterator id="menuSet" value="#request.memberMenuList">
						<ticket:common type="quickMenuObj" value="${menuSet.quickMenu_id }"/>
							<s:if test="#request.quickMenuObj != null">
								<div class="x3">
									<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
										<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
								</div>
							</s:if>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="c"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:else>
		</div>
	</div>
</s:elseif>
<s:elseif test="transferLeft == '国际' && transferRight == '国际'">
	<div class="c_img text-center mr40">
		<br>
        <br>
        <div class="fz22 text-left" style="margin-left: 18px"><span class="c_red">温馨提示：</span>昆明机场暂不受理此项业务，请按正常国际进出港流程办理，感谢您的理解与配合。
        </div>
        <br>
        <br>
	</div>
	<div class="custom_menu mr40 transferQuickMenu">
		<div class="line text-center">
			<s:if test="#session.sessionMember == null">
				<ticket:common type="visiorMenuList" value="d"/>
				<s:if test="#request.visiorMenuList != null">
					<s:iterator id="visitorMenu" value="#request.visiorMenuList">
						<div class="x3">
							<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
							<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
						</div>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="d"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:if>
			<s:else>
			<!-- 会员自定义快捷菜单 -->
				<ticket:common type="memberMenuList" value="d"/>
				<s:if test="#request.memberMenuList != null">
					<s:iterator id="menuSet" value="#request.memberMenuList">
						<ticket:common type="quickMenuObj" value="${menuSet.quickMenu_id }"/>
							<s:if test="#request.quickMenuObj != null">
								<div class="x3">
									<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
										<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
								</div>
							</s:if>
					</s:iterator>
				</s:if>
				<s:if test="#request.defaultList != null">
					<s:iterator id="default" value="#request.defaultList">
						<div class="x3">
							<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="d"></a>
						</div>
					</s:iterator>
				</s:if>
			</s:else>
		</div>
	</div>
</s:elseif>