<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp' %>
<s:if test="transferLeft == '国内' && transferRight == '国内'">
	<div class="c_img text-center mr40">
          <img src="/template/wap/images/area/pic3.png"  usemap="#m_pic" alt=""/>
          <map name="m_pic" id="m_pic">
              <area shape="rect" coords="-8,484,136,606" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-6,363,138,476" href="<ticket:common type="positionUrl" value="anjian_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-13,243,131,353" href="<ticket:common type="positionUrl" value="banlizhongzhuanyou_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="0,122,144,239" href="<ticket:common type="positionUrl" value="quxingli_zhongzhuan_gn_gn"/>" alt="" />
              <area shape="rect" coords="-4,0,140,119" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>" alt="" />
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
          <img src="/template/wap/images/area/pic9.png"  usemap="#m_pic" alt="" />
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