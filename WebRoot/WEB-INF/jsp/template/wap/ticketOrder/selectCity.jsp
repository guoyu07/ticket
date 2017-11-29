<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ticket.pojo.SystemDictionary"%>
<%@page import="com.ticket.service.ISystemDictionaryService"%>
<%@page import="com.ticket.util.SpringUtil"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<input type="hidden" id="ticketType" value="${ticketType }"/>
       	<input type="hidden" id="buyType" value="${buyType }"/>
       	<input type="hidden" id="cityType" value="${cityType }"/>
		<div class="mobile-view">
		  <div class="mobile-page">
			<div class="mobile-top">
	            <div class="header">
	            	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
	            		<a href="/airport/hangbanchaxun.ticket" class="FLAPP" id="comeBack"><i class="icon-angle-left"></i></a>
	            	</s:if>
	            	<s:else>
	            		<a href="/ticketOrder.action" class="FLAPP"><i class="icon-angle-left"></i></a>
	            	</s:else>
	                <span class="tit_tab button-group head_tit_tab">
	                    <a href="javascript:;" cityPoi="domesticCityThreeCode" class="button fz26 selected changeCityPoiOrder"><i class="icon-sort-desc"></i>国内</a>
						<a href="javascript:;" cityPoi="InternationalCityThreeCode" class="button fz26 changeCityPoiOrder"><i class="icon-sort-desc"></i>国际</a>
	                </span>
	            </div>
	        </div>
	        <%-- </s:if>
	        <s:else>
	        <div class="mobile-top" style="display: none;">
	            <div class="header">
	            	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
	            		<a href="/airport/hangbanchaxun.ticket" class="FLAPP" id="comeBack"><i class="icon-angle-left"></i></a>
	            	</s:if>
	            	<s:else>
	            		<a href="/ticketOrder.action" class="FLAPP"><i class="icon-angle-left"></i></a>
	            	</s:else>
	                <span class="tit_tab button-group head_tit_tab">
	                    <a href="javascript:;" cityPoi="domesticCityThreeCode" class="button fz26 selected changeCityPoiOrder"><i class="icon-sort-desc"></i>国内</a>
						<a href="javascript:;" cityPoi="InternationalCityThreeCode" class="button fz26 changeCityPoiOrder"><i class="icon-sort-desc"></i>国际</a>
	                </span>
	            </div>
	        </div>
	        </s:else> --%>
			<div class="mobile-main">
            <dl class="">
            	<div id="allmap"></div>
                <div class="line-big padding-big-top border-bottom padding-bottom">
                    <div class="x10" style="position:relative">
                        <input name='' id='queryCityKeyword' type="text" class="d_input block fz20" style="border-radius:0px;border:0px;" placeholder='可输入城市名、英文名、三字码' />
                        <div class="tagMatches" style='margin-left:10px;display: block;' id="selectCityDiv"></div>
                    </div>
                    <div class="x2">
                    	<input type="hidden" id="searchType" name="searchType" value="${searchType }"/>
						<input type="hidden" id="cityPoi" name="cityPoi" value="domesticCityThreeCode"/>
                        <button type="button" class='button fz20' style="border-radius:0px;border:0px;"><i class="icon-bell" style='font-size:40px;'></i></button>
                    </div>
                </div>
                <s:if test="ticketType == 'domestic'">
                	<div id="cityList">
	                	<input type="hidden" id="domOrInt" value="guonei"/>
		                <dt class='fz22 b_l_grey padding-big margin-large-top'>附近机场</dt>
		                <div id="currentCity" name="currentCity"></div>
		                <dt class='fz22 b_l_grey padding-big margin-large-top' id='city'>历史选择</dt>
		                <s:if test="#session.sessionMember == null">
		                	<ticket:common type="cityCookie"/>
		                	<s:if test="#request.cityCookie != null">
		                		<s:iterator id="cookieCity" value="#request.cityCookie">
		                			<ticket:common type="airportInfoById" value="${cookieCity.city_id }"/>
			                		<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airportInfoById.threeCode }" cityCode="${airportInfoById.threeCode }" cityName="${airportInfoById.name }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
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
					</div>
                </s:if>
                <s:else>
                	<div id="cityList">
	                	<input type="hidden" id="domOrInt" value="guonei"/>
		                <dt class='fz22 b_l_grey padding-big margin-large-top'>附近机场</dt>
						<div id="currentCity" name="currentCity"></div>
						<dt class='fz22 b_l_grey padding-big margin-large-top' id='city'>历史选择</dt>
						<s:if test="#session.sessionMember == null">
						 	<ticket:common type="cityCookie"/>
					       	<s:if test="#request.cityCookie != null">
					       		<s:iterator id="cookieCity" value="#request.cityCookie">
					        		<ticket:common type="airportInfoById" value="${cookieCity.city_id }"/>
			                		<dd class='fz22 margin-big border-bottom padding-big-bottom ticketOrderSelectCityClass' value="${airportInfoById.threeCode }" cityCode="${airportInfoById.threeCode }" cityName="${airportInfoById.name }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
					       		</s:iterator>
					       	</s:if>
						</s:if>
						<s:else>
						   <ticket:common type="historyCityList"/>
					       	<s:if test="#request.historyCityList != null">
					       		<s:iterator id="historyCity" value="#request.historyCityList">
					       			<ticket:common type="airportInfoById" value="${historyCity.city_id }"/>
									<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airportInfoById.threeCode }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
					       		</s:iterator>
					       	</s:if>
						</s:else>
						<dt class='fz22 b_l_grey padding-big margin-large-top' id='hot'>热门机场</dt>
						<ticket:common type="intHotAirportList"/>
						<s:if test="#request.intHotAirportList != null">
							<s:iterator id="intHotAirport" value="#request.intHotAirportList">
								<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${intHotAirport.threeCode }" cityCode="${hotAirport.threeCode }" cityName="${hotAirport.name }">${intHotAirport.name }（${intHotAirport.threeCode  }）<p class='fz14'>${intHotAirport.englishName }</p></dd>
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
					</div>
                </s:else>
            </dl>
        </div>
        <div class="mobile-foot">
           <%@ include file="../common/quickNav.jsp" %>
            <div class="en_tag">
                <ul class='text-center c_blue'>
                    <li><a href="#city">历史选择</a></li>
                    <li><a href="#hot">热门机场</a></li>
                    <li><a href="#A">A</a></li>
                    <li><a href="#B">B</a></li>
                    <li><a href="#C">C</a></li>
                    <li><a href="#D">D</a></li>
                    <li><a href="#E">E</a></li>
                    <li><a href="#F">F</a></li>
                    <li><a href="#G">G</a></li>
                    <li><a href="#H">H</a></li>
                    <li><a href="#I">I</a></li>
                    <li><a href="#J">J</a></li>
                    <li><a href="#K">K</a></li>
                    <li><a href="#L">L</a></li>
                    <li><a href="#M">M</a></li>
                    <li><a href="#N">N</a></li>
                    <li><a href="#O">O</a></li>
                    <li><a href="#P">P</a></li>
                    <li><a href="#Q">Q</a></li>
                    <li><a href="#R">R</a></li>
                    <li><a href="#S">S</a></li>
                    <li><a href="#T">T</a></li>
                    <li><a href="#U">U</a></li>
                    <li><a href="#V">V</a></li>
                    <li><a href="#W">W</a></li>
                    <li><a href="#X">X</a></li>
                    <li><a href="#Y">Y</a></li>
                    <li><a href="#Z">Z</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="dialog" style="display:none;"></div>
<script type="text/javascript" src="/template/wap/js/ticketOrder/ticketOrder.js"></script>
<script type="text/javascript">
    $('.tit_tab a').on('tap',function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });
    initTicketOrderClass();
</script>
</body>
</html>