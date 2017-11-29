<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/template/wap/js/wap.js?random=55555"></script>
<%@include file="../common/headAjax.jsp"%>
<s:if test="id != null && id !=''">
	<ticket:common type="trafficLineListByTypeId" value="${id }"/>
	<s:if test="#request.trafficLineListByTypeId != null">
		<div class="mr40 tab_ls">
			<s:iterator id="trafficLine" value="#request.trafficLineListByTypeId">
				<dl>
					<dt class='fz20 line'>
						<div class='x11'>
							<font class='fz26' style="color:#151515">${trafficLine.name }</font>
	                           <p class='padding-top' style="color:#696969">${trafficLine.startStation }-${trafficLine.endStation }</p> 
						</div>
						<div class="x1 text-right">
							<span class='icon-plus'></span>
						</div>
					</dt>
					<dd class='fz22'>	
						<ul>
							<li style="	border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #CCCCCC; padding-bottom:20px; padding-top:20px;">
							<ticket:common type="trafficStationListByLineAndGo" value="${trafficLine.id }"/>
							<s:if test="#request.trafficStationListByLineAndGo != null">
								去程：
								<s:iterator id="trafficLineAndStation" value="#request.trafficStationListByLineAndGo" status="st">
									<ticket:common type="trafficStationObj" value="${trafficLineAndStation.trafficStation_id }"/>
									<s:if test="#st.last">
										<b>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:if>
											<s:else>
												<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:else>
										</b>
										<s:if test="#trafficStationObj.descript != null">
											(${trafficStationObj.descript })
										</s:if>
									</s:if>
									<s:else>
										<b>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:if>
											<s:else>
												<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:else>
										</b>
										<s:if test="#trafficStationObj.descript != null">
											(${trafficStationObj.descript })
										</s:if>——
									</s:else>
								</s:iterator>
							</s:if>
							<li style="	border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #CCCCCC; padding-bottom:20px;padding-top:20px;">
							<ticket:common type="trafficStationListByLineAndBack" value="${trafficLine.id }"/>
							<s:if test="#request.trafficStationListByLineAndBack != null">
								回程：
								<s:iterator id="trafficLineAndStation" value="#request.trafficStationListByLineAndBack" status="st">
									<ticket:common type="trafficStationObj" value="${trafficLineAndStation.trafficStation_id }"/>
									<s:if test="#st.last">
										<b>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:if>
											<s:else>
												<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:else>
										</b>
										<s:if test="#trafficStationObj.descript != null">
											(${trafficStationObj.descript })
										</s:if>
									</s:if>
									<s:else>
										<b>
											<s:if test="#session.fromApp == null && #parameters.fromApp == null">
												<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:if>
											<s:else>
												<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
											</s:else>
										</b>
										<s:if test="#trafficStationObj.descript != null">
											(${trafficStationObj.descript })
										</s:if>——
									</s:else>
								</s:iterator>
							</s:if>
							<br />
							票价：${trafficLine.price }元<br>
							<br />发车频率：${trafficLine.departureRate }<br />
							首班车时间：<s:date name="#trafficLine.startTime" format="HH:mm" />，末班车时间：<s:date name="#trafficLine.endTime" format="HH:mm" /><br/>
							<s:if test="#trafficLine.remark != null">
								注：${trafficLine.remark }
							</s:if>
							</li>
						</ul>
					</dd>
				</dl>
			</s:iterator>
			<a 
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				href="http://api.map.baidu.com/marker?location=${trafficTypeObj.latitude },${trafficTypeObj.longitude }&title=${trafficTypeObj.name }&content=${trafficTypeObj.name }&output=html&floorNumber=${trafficTypeObj.floorNumber}"
				</s:if>
				<s:else>
				href="/airport_daohang.action?longitude=${trafficTypeObj.longitude }&latitude=${trafficTypeObj.latitude }&name=${trafficTypeObj.name }&floorNumber=${trafficTypeObj.floorNumber}"
				</s:else>
				>
				<div class="tit b_blue c_white">
					<s:if test='id == "d1f0e4cc-57fe-4c4d-ac5a-99436ea1e242"'>
						机场公交乘车点
					</s:if>
					<s:if test='id == "71978e39-c5e0-4672-98a3-c8398b99a5bd"'>
						地铁乘坐点
					</s:if>
					<s:if test='id == "ae6e0811-baf2-48d7-ad37-cafa0a8834e1"'>
						空港快线乘车点
					</s:if>
				</div>
			</a>
		</div>
	</s:if>
	<s:else>
		<div class="mr40 tab_ls">
			<dl>
				<dt class='fz20 line'>
					<div class='x11'>
						${noDataMessage }
					</div>
				</dt>
			</dl>
		</div>
	</s:else>
</s:if>