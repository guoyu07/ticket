<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<%@page import="com.ticket.pojo.SystemDictionary"%>
<%@page import="com.ticket.service.ISystemDictionaryService"%>
<%@page import="com.ticket.util.SpringUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:if test="flag=='domesticCityThreeCode'">
	<input type="hidden" id="domOrInt" value="guonei"/>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='city'>历史选择</dt>
	<s:if test="#session.sessionMember == null">
	 	<ticket:common type="cityCookie"/>
       	<s:if test="#request.cityCookie != null">
       		<s:iterator id="cookieCity" value="#request.cityCookie">
        		<ticket:common type="airportInfoById" value="${cookieCity.city_id }"/>
		        <dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airportInfoById.threeCode }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
       		</s:iterator>
       	</s:if>
	</s:if>
	<s:else>
	   <ticket:common type="historyCityList"/>
       	<s:if test="#request.historyCityList != null">
       		<s:iterator id="historyCity" value="#request.historyCityList">
       			<ticket:common type="airportInfoById" value="${historyCity.city_id }"/>
				<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airportInfoById.threeCode }" cityCode="${airportInfoById.name }" cityName="${airportInfoById.threeCode}">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
       		</s:iterator>
       	</s:if>
	</s:else>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='hot'>热门机场</dt>
	<ticket:common type="domHotAirportList"/>
	<s:if test="#request.domHotAirportList != null">
		<s:iterator id="hotAirport" value="#request.domHotAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${hotAirport.threeCode }" cityCode="${hotAirport.threeCode }" cityName="${hotAirport.name }">${hotAirport.name }（${hotAirport.threeCode }）<p class='fz14'>${hotAirport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='A'>A</dt>
	<s:if test="#application.a_domesticAirportList != null">
		<s:iterator id="airport" value="#application.a_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='B'>B</dt> 
	<s:if test="#application.b_domesticAirportList != null">
		<s:iterator id="airport" value="#application.b_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='C'>C</dt>
	<s:if test="#application.c_domesticAirportList != null">
		<s:iterator id="airport" value="#application.c_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='D'>D</dt>
	<s:if test="#application.d_domesticAirportList != null">
		<s:iterator id="airport" value="#application.d_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='E'>E</dt>
	<s:if test="#application.e_domesticAirportList != null">
		<s:iterator id="airport" value="#application.e_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='F'>F</dt>
	<s:if test="#application.f_domesticAirportList != null">
		<s:iterator id="airport" value="#application.f_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='G'>G</dt>
	<s:if test="#application.g_domesticAirportList != null">
		<s:iterator id="airport" value="#application.g_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='H'>H</dt>
	<s:if test="#application.h_domesticAirportList != null">
		<s:iterator id="airport" value="#application.h_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='I'>I</dt>
	<s:if test="#application.i_domesticAirportList != null">
		<s:iterator id="airport" value="#application.i_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='J'>J</dt>
	<s:if test="#application.j_domesticAirportList != null">
		<s:iterator id="airport" value="#application.j_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='K'>K</dt>
	<s:if test="#application.k_domesticAirportList != null">
		<s:iterator id="airport" value="#application.k_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='L'>L</dt>
	<s:if test="#application.l_domesticAirportList != null">
		<s:iterator id="airport" value="#application.l_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='M'>M</dt>
	<s:if test="#application.m_domesticAirportList != null">
		<s:iterator id="airport" value="#application.m_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='N'>N</dt>
	<s:if test="#application.n_domesticAirportList != null">
		<s:iterator id="airport" value="#application.n_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='O'>O</dt>
	<s:if test="#application.o_domesticAirportList != null">
		<s:iterator id="airport" value="#application.o_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='P'>P</dt>
	<s:if test="#application.p_domesticAirportList != null">
		<s:iterator id="airport" value="#application.p_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Q'>Q</dt>
	<s:if test="#application.q_domesticAirportList != null">
		<s:iterator id="airport" value="#application.q_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='R'>R</dt>
	<s:if test="#application.r_domesticAirportList != null">
		<s:iterator id="airport" value="#application.r_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='S'>S</dt>
	<s:if test="#application.s_domesticAirportList != null">
		<s:iterator id="airport" value="#application.s_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='T'>T</dt>
	<s:if test="#application.t_domesticAirportList != null">
		<s:iterator id="airport" value="#application.t_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='U'>U</dt>
	<s:if test="#application.u_domesticAirportList != null">
		<s:iterator id="airport" value="#application.u_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='V'>V</dt>
	<s:if test="#application.v_domesticAirportList != null">
		<s:iterator id="airport" value="#application.v_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='W'>W</dt>
	<s:if test="#application.w_domesticAirportList != null">
		<s:iterator id="airport" value="#application.w_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='X'>X</dt>
	<s:if test="#application.x_domesticAirportList != null">
		<s:iterator id="airport" value="#application.x_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Y'>Y</dt>
	<s:if test="#application.y_domesticAirportList != null">
		<s:iterator id="airport" value="#application.y_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Z'>Z</dt>
	<s:if test="#application.z_domesticAirportList != null">
		<s:iterator id="airport" value="#application.z_domesticAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
</s:if>
<s:elseif test="flag=='InternationalCityThreeCode'">
	<input type="hidden" id="domOrInt" value="guowai"/>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='city'>历史选择</dt>
	<s:if test="#session.sessionMember == null">
	 	<ticket:common type="cityCookie"/>
       	<s:if test="#request.cityCookie != null">
       		<s:iterator id="cookieCity" value="#request.cityCookie">
        		<ticket:common type="airportInfoById" value="${cookieCity.city_id }"/>
				<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airportInfoById.threeCode }" cityCode="${airportInfoById.name }" cityName="${airportInfoById.threeCode}">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
       		</s:iterator>
       	</s:if>
	</s:if>
	<s:else>
	   <ticket:common type="historyCityList"/>
       	<s:if test="#request.historyCityList != null">
       		<s:iterator id="historyCity" value="#request.historyCityList">
       			<ticket:common type="airportInfoById" value="${historyCity.city_id }"/>
				<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airportInfoById.threeCode }" cityCode="${airportInfoById.threeCode }" cityName="${airportInfoById.name }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
       		</s:iterator>
       	</s:if>
	</s:else>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='hot'>热门机场</dt>
	<ticket:common type="intHotAirportList"/>
	<s:if test="#request.intHotAirportList != null">
		<s:iterator id="intHotAirport" value="#request.intHotAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${intHotAirport.threeCode }" cityCode="${intHotAirport.threeCode }" cityName="${intHotAirport.name}">${intHotAirport.name }（${intHotAirport.threeCode }）<p class='fz14'>${intHotAirport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='A'>A</dt>
	<s:if test="#application.a_internationalAirportList != null">
		<s:iterator id="airport" value="#application.a_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='B'>B</dt>
	<s:if test="#application.b_internationalAirportList != null">
		<s:iterator id="airport" value="#application.b_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='C'>C</dt>
	<s:if test="#application.c_internationalAirportList != null">
		<s:iterator id="airport" value="#application.c_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='D'>D</dt>
	<s:if test="#application.d_internationalAirportList != null">
		<s:iterator id="airport" value="#application.d_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='E'>E</dt>
	<s:if test="#application.e_internationalAirportList != null">
		<s:iterator id="airport" value="#application.e_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='F'>F</dt>
	<s:if test="#application.f_internationalAirportList != null">
		<s:iterator id="airport" value="#application.f_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='G'>G</dt>
	<s:if test="#application.g_internationalAirportList != null">
		<s:iterator id="airport" value="#application.g_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='H'>H</dt>
	<s:if test="#application.h_internationalAirportList != null">
		<s:iterator id="airport" value="#application.h_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='I'>I</dt>
	<s:if test="#application.i_internationalAirportList != null">
		<s:iterator id="airport" value="#application.i_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='J'>J</dt>
	<s:if test="#application.j_internationalAirportList != null">
		<s:iterator id="airport" value="#application.j_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='K'>K</dt>
	<s:if test="#application.k_internationalAirportList != null">
		<s:iterator id="airport" value="#application.k_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='L'>L</dt>
	<s:if test="#application.l_internationalAirportList != null">
		<s:iterator id="airport" value="#application.l_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='M'>M</dt>
	<s:if test="#application.m_internationalAirportList != null">
		<s:iterator id="airport" value="#application.m_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='N'>N</dt>
	<s:if test="#application.n_internationalAirportList != null">
		<s:iterator id="airport" value="#application.n_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='O'>O</dt>
	<s:if test="#application.o_internationalAirportList != null">
		<s:iterator id="airport" value="#application.o_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='P'>P</dt>
	<s:if test="#application.p_internationalAirportList != null">
		<s:iterator id="airport" value="#application.p_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Q'>Q</dt>
	<s:if test="#application.q_internationalAirportList != null">
		<s:iterator id="airport" value="#application.q_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='R'>R</dt>
	<s:if test="#application.r_internationalAirportList != null">
		<s:iterator id="airport" value="#application.r_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='S'>S</dt>
	<s:if test="#application.s_internationalAirportList != null">
		<s:iterator id="airport" value="#application.s_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='T'>T</dt>
	<s:if test="#application.t_internationalAirportList != null">
		<s:iterator id="airport" value="#application.t_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='U'>U</dt>
	<s:if test="#application.u_internationalAirportList != null">
		<s:iterator id="airport" value="#application.u_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='V'>V</dt>
	<s:if test="#application.v_internationalAirportList != null">
		<s:iterator id="airport" value="#application.v_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='W'>W</dt>
	<s:if test="#application.w_internationalAirportList != null">
		<s:iterator id="airport" value="#application.w_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='X'>X</dt>
	<s:if test="#application.x_internationalAirportList != null">
		<s:iterator id="airport" value="#application.x_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Y'>Y</dt>
	<s:if test="#application.y_internationalAirportList != null">
		<s:iterator id="airport" value="#application.y_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
	<dt class='fz22 b_l_grey padding-big margin-large-top' id='Z'>Z</dt>
	<s:if test="#application.z_internationalAirportList != null">
		<s:iterator id="airport" value="#application.z_internationalAirportList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</s:if>
</s:elseif>