<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>航班信息 - 云南省昆明市长水机场 </title>
	</head>

	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>

		<div class="banner_1"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="#">航班信息</a>
		</div place w980 mt30>

		<div class="HBXX_article w980 mt30">
            <label class="searchFlightType" onclick="window.location.href='/airportPc_flightQuery.action'">航班查询</label>
            <label class="searchFlightType selected">航空公司查询</label>
			<input type="text" name="keyword" id="keyword" value="" orderFlag="" placeholder='请输入航空公司信息关键词' class="bhh">
			<button type="button" class="searchFlightCompanyByKeyword">查询</button>
			<label class="searchFlightCompanyByKeyword" orderFlag="pinyin">
				按拼音首字母排序
			</label>
			<label class="selected searchFlightCompanyByKeyword" orderFlag="twoCode">
				按二字码排序
			</label>
			<div id="flightCompanySearchResult">
				<ticket:pcCommon type="flightCompanyList" size="1000"/>
				<s:if test="#request.flightCompanyList != null">
					<table cellpadding="0" cellspacing="0" frame="border" rules="all">
						<tr>
							<th width="100">
								LOGO
							</th>
							<th width="150">
								航空公司
							</th>
							<th width="100">
								值机柜台
							</th>
							<th width="100">
								应急柜台
							</th>
							<th width="200">
								服务热线
							</th>
							<th width="330">
								官网
							</th>
						</tr>
						<s:iterator id="flightCompany" value="#request.flightCompanyList">
							<tr>
								<td>
									<ticket:commonAnnex type="annex" entityUuid="${flightCompany.id }" annexType="1" size="1"/>
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
								</td>
								<td>
									${flightCompany.name }
								</td>
								<td>
									${flightCompany.customerCounter}
								</td>
								<td>
									${flightCompany.emergencyCounter }
								</td>
								<td>
									${flightCompany.phone }
								</td>
								<td>
									<b><a href="${flightCompany.theOfficialWebsite }">${flightCompany.theOfficialWebsite }</a> </b>
								</td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					${noDataMessage }
				</s:else>
			</div>
		</div>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
