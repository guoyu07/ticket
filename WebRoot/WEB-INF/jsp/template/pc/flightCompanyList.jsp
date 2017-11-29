<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common/headAjax.jsp"%>
<s:if test="flightCompanyList != null">
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
		<s:iterator id="flightCompany" value="flightCompanyList">
			<tr>
				<td>
					<ticket:commonAnnex type="annex" entityUuid="${flightCompany.id }" annexType="1" size="1"/>
					<img width="80" height="80" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }">
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