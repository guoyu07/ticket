<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<s:if test="flag=='queryByFlightNo'">
	<div class="search_time c_content">
		<i class="icon-space-shuttle text-sub margin-large-right"></i>
		航班号&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="input input-auto d_input" name="flightNumber" id="flightNumber" size="20">
	</div>
	<div class="search_time c_content">
		<i class="icon-calendar text-sub margin-large-right"></i>
		选择日期&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" id="flightDate" name="flightDate" value="<ticket:common type="currentDate"/>" class='no-border c_l_grey fz22'>&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<button type="" class="button bg-sub radius-big queryFlightByNoAndDate">
		航班查询
	</button>
</s:if>
<s:elseif test="flag=='queryByCity'">
	<ul class="">
            <li style="padding:20px;">
                <div class="fly_line clearfix">
                    <div class="x5">
                        <span class="select_tit">
                            <div class="button-group">
                                <button type="button" class="button  bg dropdown-toggle text-left selectOrgCity">
                                	<s:if test="#session.orgCity != null">
                                		<font id="orgCity" cityThreeCode=${session.orgCityCode }>${session.orgCity }</font> <i class="icon-sort-desc"></i>
                                					<input type="hidden" id="org" name="org" value="${session.orgCityCode }"/>
                                	</s:if>
                                	<s:else>
                                    	<font id="orgCity" cityThreeCode="KMG">昆明</font> <i class="icon-sort-desc"></i>
                                					<input type="hidden" id="org" name="org" value="KMG"/>
                                    </s:else>
                                </button>
                            </div>
                            <em>出发城市</em>
                        </span>
                    </div>
                    <div class="x2 exchangeCity"></div>
                    <div class="x5">
                        <span class="select_tit">
                            <div class="button-group">
                                <button type="button" class="button  bg dropdown-toggle text-left selectDesCity">
                                    <s:if test="#session.desCity != null">
                                		<font id="desCity" cityThreeCode=${session.desCityCode }>${session.desCity }</font> <i class="icon-sort-desc"></i>
                                					<input type="hidden" id="des" name="des" value="${session.desCityCode }"/>
                                	</s:if>
                                	<s:else>
                                    	<font id="desCity" cityThreeCode="PEK">北京</font> <i class="icon-sort-desc"></i>
                                					<input type="hidden" id="des" name="des" value="PEK"/>
                                    </s:else>
                                </button>
                            </div>
                            <em>到达城市</em>
                        </span>
                    </div>
                </div>
            </li>
        </ul>
       <div class="search_time c_content">
           <i class="icon-calendar text-sub margin-large-right"></i> 
           	选择日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" id="flightDate" value="<ticket:common type="currentDate"/>" name="flightDate" class='no-border c_l_grey fz22'>&nbsp;&nbsp;&nbsp;&nbsp;
       </div>
       <button type="" class="button bg-sub radius-big queryFlightByCity">航班查询</button>
</s:elseif>