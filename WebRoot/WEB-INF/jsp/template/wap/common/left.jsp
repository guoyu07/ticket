<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
.label_bar a{ width:100%; border-radius:30px; color:#2d9cd8; line-height:63px; font-size:28px; font-weight:bold;}
.margin-large-top{ margin-top:0px; margin-bottom:28px;}
</style>
<div class="side_menu">
    <div class="side_menu_con">
        <a href="/airport/hangbanchaxun.ticket?direct=true" class="close_btn"></a>
        <ul>
		
        	<s:iterator var="menu" value="frontMenuService.queryRoot()">
        		<s:set var="subMenus" value="frontMenuService.querySub(#menu)"></s:set>
        		<s:if test="#subMenus.size() == 0">
					<li <s:if test="#menu.name=='旅客须知' || #menu.name=='航延服务' || #menu.name=='保险服务'">class='border'</s:if>>
						<h6>
							<s:if test="#menu.name == '个人中心' && #session.sessionMember == null">
								<a <s:if test="#session.menuId == #menu.id">class="c_yello"</s:if>
									attrValue="gerenzhongxin"
									href="javascript:;"
									href_='<s:property value="#menu.value"/>'
									onclick="saveMenuId(this)"
									id="<s:property value="#menu.id"/>">
									${menu.name }
								</a>
							</s:if>
							<s:elseif test="#menu.name == '个人中心' && #session.sessionMember != null">
								<a <s:if test="#session.menuId == #menu.id">class="c_yello"</s:if>
									attrValue="gerenzhongxin"
									href="javascript:;"
									href_='javascript:geren();'
									onclick="saveMenuId(this)"
									id="<s:property value="#menu.id"/>">
									${menu.name }
								</a>
							</s:elseif>
							<s:elseif test="#menu.name != '个人中心'">
								<a <s:if test="#session.menuId == #menu.id">class="c_yello"</s:if>
									href="javascript:;"
									href_='<s:property value="#menu.value"/>'
									onclick="saveMenuId(this)"
									id="<s:property value="#menu.id"/>">
									${menu.name }
								</a>
							</s:elseif>
						</h6>
					</li>
        		</s:if>
        		<s:else>
        			<s:if test="#menu.name=='机场商业'">
        				<li class='more'>
							<h6>
								<a href="javascript:;">机场商业</a>
								<s:if test="#session.menuId == #menu.id">
									<i class='icon-sort-asc'></i>
								</s:if>
								<s:else>
									<i class='icon-sort-desc'></i>
								</s:else>
							</h6>
							<dl>
			        			<s:iterator var="subMenu" value="subMenus">
			        				<s:if test="#subMenu.name == '餐饮'">
										<dd>
											<a <s:if test="#session.menuId == #subMenu.id">class="c_yello"</s:if>
												href="javascript:;" 
												href_='airport_restaurant.action?flag=canyin&direct=true'
												onclick="saveMenuId(this)"
												id="<s:property value="#subMenu.id"/>">
												${subMenu.name }
											</a>
										</dd>
									</s:if>
			        				<s:elseif test="#subMenu.name == '零售'">
										<dd>
											<a <s:if test="#session.menuId == #subMenu.id">class="c_yello"</s:if> 
												href="javascript:;" 
												href_='airport_restaurant.action?flag=lingshou&direct=true'
												onclick="saveMenuId(this)"
												id="<s:property value="#subMenu.id"/>">
												${subMenu.name }
											</a>
										</dd>
									</s:elseif>
			        				<s:elseif test="#subMenu.name == '休闲'">
										<dd>
											<a <s:if test="#session.menuId == #subMenu.id">class="c_yello"</s:if> 
												href="javascript:;" 
												href_='airport_restaurant.action?flag=xiuxian&direct=true'
												onclick="saveMenuId(this)"
												id="<s:property value="#subMenu.id"/>">
												${subMenu.name }
											</a>
										</dd>
									</s:elseif>
			        				<s:elseif test="#subMenu.name == '酒店'">
										<dd>
											<a <s:if test="#session.menuId == #subMenu.id">class="c_yello"</s:if> 
												href="javascript:;" 
												href_='airport_restaurant.action?flag=jiudian&direct=true'
												onclick="saveMenuId(this)"
												id="<s:property value="#subMenu.id"/>">
												${subMenu.name }
											</a>
										</dd>
									</s:elseif>
								</s:iterator>
							</dl>
						</li>
        			</s:if>
        			<s:else>
			            <li class='<s:if test="#menu.name=='旅客须知' || #menu.name=='航延服务' || #menu.name=='旅游咨询'">border</s:if> more'>
							<h6>
								<a href="javascript:;">${menu.name }</a>
								<s:if test="#session.menuId == #menu.id">
									<i class='icon-sort-asc'></i>
								</s:if>
								<s:else>
									<i class='icon-sort-desc'></i>
								</s:else>
							</h6>
							<dl>
			        			<s:iterator var="subMenu" value="subMenus">
									<dd>
										<a <s:if test="#session.menuId == #subMenu.id">class="c_yello"</s:if> 
											href="javascript:;" 
											href_='<s:property value="#subMenu.value"/>'
											onclick="saveMenuId(this)"
											id="<s:property value="#subMenu.id"/>">
											${subMenu.name }
										</a>
									</dd>
								</s:iterator>
							</dl>
						</li>
					</s:else>
				</s:else>
			</s:iterator>
        </ul>
    </div>
</div>
<div class="side_right_menu">
    <div class="side_menu_con">
        <div class="avatar">
        	<a href="/airportm_personalSetting.action?direct=true">
	            <div class="avatar_pic">
	            	<s:if test="sessionMember.images != null">
						<img src="${sessionMember.images }" height="100" width="100" class='radius-circle'>
					</s:if> 
					<s:else>
						<img src="/template/wap/images/no_avatar.png" height="100" width="100" class='radius-circle'>
					</s:else>
	            </div>
            </a>
        </div>
        <div class="clearfix text-center">
        <span class="fz30 c_white">
        <s:if test="sessionMember.nickName != null">
        		${sessionMember.nickName }
        	</s:if>
        	<s:else>
        		未设置
        	</s:else>
        <hr  style="border-top:1px solid #fff;"/>
        
        <div style="display:none">
        	<i class='user_icon1'></i>&nbsp;&nbsp;
        	<s:if test="sessionMember.nickName != null">
        		${sessionMember.nickName }
        	</s:if>
        	<s:else>
        		未设置
        	</s:else></div>
        	 </span>
              <div style="display:none">
        <a href="airportm_myMessage.action" class="badge-corner"><i class='icon-envelope-o c_white'  style="font-size:40px;"></i>
        <span class="badge bg-red" style="top:-18px;">
        	<s:if test="#session.sessionMember != null">
	            <ticket:common type="newMessages"/>
	            ${newMessages > 0 ? '&nbsp;&nbsp;' : null }
            </s:if>
        </span></a></div></div>
        
        <div class="line text-center margin-large-top">
            <div class="x7 fz24 c_white" id="myFavorite" style="text-align:left; text-indent:3px;"><a href="airportm_myMessage.action"  class="c_white"><p class='fz40 margin-big-bottom' style="display:none;">
            <s:if test="#session.sessionMember != null">
	            <ticket:common type="myFavoriteCount"/>
	            ${myFavoriteCount >= 100 ? '99+' : myFavoriteCount }
            </s:if>
            <s:else>0</s:else>
            </p><i class='icon-envelope c_white' ></i>
            <ticket:common type="noReadMessageCount"/>
			<s:if test="#request.noReadMessageCount > 0">
            <span class="tab-badge bg-red" style="margin-top:-2px; margin-left:-5px;"></span>
            </s:if>
            
            &nbsp;&nbsp;我的消息</a></div>
            <div class="x5 fz24 c_white" style="text-align:left;text-indent:3px;">
	           
		            <a href="/airportm_myFavorite.action" class="c_white"> <i class='icon-star c_white' ></i>&nbsp;&nbsp;我的收藏</a>
	            
            </div>
        </div>
        <div class="label_bar" style="padding-left:0px;">
            <a href="/airportm_myFocusOn.action" class="b_white " style="letter-spacing:4px;">我的航班</a>
             <a href="/airportm_checkinInfo.action" class="b_white " style="letter-spacing:5px;">登&nbsp;机&nbsp;牌</a>
            <a href="/bjdjOrderTemplate_allOrder.action" class="b_white " style="letter-spacing:4px;">我的订单</a>
           
            <a href="/commonTraveller_show.action" class="b_white " style="letter-spacing:4px;">常用信息</a>
            
        </div>
        
        <div class="side_right_icon centerMenuList">
            <ul class="clearfix">
            	<s:if test="#session.sessionMember != null">
	            	<ticket:common type="memberSetQuickMenuList" size="5"/>
	            	<s:if test="#request.memberSetQuickMenuList != null">
	            		<s:iterator id="memberQuickMenu" value="#request.memberSetQuickMenuList">
	            			<li>
	            				<ticket:common type="quickMenuObj" value="${memberQuickMenu.quickMenu_id }"/>
	            				<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
									<a href="${quickMenuObj.url }"> <img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
									<!-- <a href="#" memberQuickMenuId="${memberQuickMenu.id }" class="del deleteSetMenu">删除</a></li> -->
                            </li>
	            		</s:iterator>
	            	</s:if>
	           		<s:if test="#request.defaultList != null">
						<s:iterator id="default" value="#request.defaultList">
							<li><a href="javascript:;" class="icons3 custom_menu_btn toSetQuickMenu" defaultPosition="g"></a></li>
						</s:iterator>
					</s:if>
            	</s:if>
            </ul>
            <!-- <div class="tit custom_menu_btn toSetQuickMenu" defaultPosition="g" style="padding-top:0px;margin-bottom:40px;">设置快捷菜单</div> -->
        </div>
    </div>
</div>