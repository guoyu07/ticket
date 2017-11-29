<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="flightCompanyObj" value="${id }"/>
<s:if test="#request.flightCompanyObj != null">

</s:if>
<table class="table table-bordered c_l_grey">
	<tr>
		<td class="fz26 text-center" colspan="2" >
			<ticket:commonAnnex type="annex" entityUuid="${flightCompanyObj.id }" annexType="1" size="1"/>
			<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position:relative;top:5px;'>
			${flightCompanyObj.name }
		</td>
	</tr>
	<tr>
		<td class="fz18 text-right" width="150">
			电话
		</td>
		<td class="fz18">
			${flightCompanyObj.phone }
		</td>
	</tr>
	<tr>
		<td class="fz18 text-right">
			官网
		</td>
        
		<td class="fz18">
			<a href="${flightCompanyObj.theOfficialWebsite }" class='c_blue'>${flightCompanyObj.theOfficialWebsite }</a>
		</td>
	</tr>
    <tbody style="display:none">
	<tr>
		<td class="fz18 text-right">
			散客柜台
		</td>
		<td class="fz18">
			<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.customerCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.customerCounter }</a>
		</td>
	</tr>
	<tr>
		<td class="fz18 text-right">
			团队柜台
		</td>
		<td class="fz18">
			<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.teamCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.teamCounter }</a>
		</td>
	</tr>
	<tr>
		<td class="fz18 text-right">
			头等舱柜台
		</td>
		<td class="fz18">
			<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.firstClassCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.firstClassCounter }</a>
		</td>
	</tr>
	<tr>
		<td class="fz18 text-right">
			应急柜台
		</td>
		<td class="fz18">
			<a href="<ticket:common type="infoPositionListByCounter" value="${flightCompanyObj.emergencyCounter }"/>" class='c_blue '><em class='quick_map'></em>${flightCompanyObj.emergencyCounter }</a>
			</td>
		</tr>
        </tbody>
</table>