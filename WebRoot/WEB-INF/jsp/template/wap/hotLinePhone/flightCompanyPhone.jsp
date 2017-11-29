<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
    
<style type="text/css">
.table td{ height:50px; line-height:50px; vertical-align:middle; font-size:26px !important;}
.quick_map{ margin-top:12px; padding-left:38px !important; }
.drop-menu{ z-index:9999;}
</style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">服务热线</s:param>
				</s:include>
				<div class="mobile-main">
                    <div class="tit_tab">
                        <a href="/airport_hotLinePhone.action?direct=true" class="">机场热线</a>
                        <a href="#" class="selected" >航空公司热线</a>
                    </div>
					<div class="select_tit">
						<ticket:common type="flightCompanyList" />
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
								<s:if test="#request.flightCompanyList != null">
									<s:iterator id="flightCompany" value="#request.flightCompanyList" status="st">
										<s:if test="#st.first">
											<font style="color:#00AAFF">${flightCompany.name }</font>
										</s:if>
									</s:iterator>
								</s:if>
								<span class="downward" style="color:#00AAFF"></span>
							</button>
							<ul class="drop-menu flightAjax">
								<s:if test="#request.flightCompanyList != null">
									<s:iterator id="flightCompany" value="#request.flightCompanyList">
										<li flightCompanyid="${flightCompany.id }" class='c_blue'>
											${flightCompany.name }
										</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="mr40 flightContent">
						<s:if test="#request.flightCompanyList != null">
							<s:iterator id="flightCompanyObj" value="#request.flightCompanyList" status="st">
								<s:if test="#st.first">
									<table class="table table-bordered c_l_grey">
										<tr>
											<td class="fz26 text-center" colspan="2">
												<ticket:commonAnnex type="annex" entityUuid="${flightCompanyObj.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="40" width="40" style='position:relative;top:5px;'>
												${flightCompanyObj.name }
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right" width="150">
												电话
											</td>
											<td class="fz26">
												${flightCompanyObj.phone }
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right">
												官网
											</td>
											<td class="fz26">
												<a href="${flightCompanyObj.theOfficialWebsite }" class='c_blue'>${flightCompanyObj.theOfficialWebsite }</a>
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right">
												散客柜台
											</td>
											<td class="fz26">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.customerCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.customerCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right">
												团队柜台
											</td>
											<td class="fz26">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.teamCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.teamCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right">
												头等舱柜台
											</td>
											<td class="fz26">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.firstClassCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.firstClassCounter }</a>
											</td>
										</tr>
										<tr>
											<td class="fz26 text-right">
												应急柜台
											</td>
											<td class="fz26">
												<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.emergencyCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.emergencyCounter }</a>
												</td>
											</tr>
									</table>
								</s:if>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		
	</body>
	</ticket:cache>
</html>
