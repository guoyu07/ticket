<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		  <div class="mobile-page">
			<div class="mobile-top">
	            <div class="header">
	            	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
	            		<a href="javascript:history.go(-1)" class="FLAPP"><i class="icon-angle-left"></i></a>
	            	</s:if>
	            	<s:else>
	            		<a href="javascript:history.go(-1)" class="FLAPP"><i class="icon-angle-left"></i></a>
	            	</s:else>
	                <span class="tit_tab button-group head_tit_tab">
	                    <a href="javascript:;" cityPoi="domesticAirportThreeCode" class="button fz26 selected changeCityPoi"><i class="icon-sort-desc"></i>国内</a>
						<a href="javascript:;" cityPoi="InternationalAirportThreeCode" class="button fz26 changeCityPoi"><i class="icon-sort-desc"></i>国际</a>
	                </span>
	            </div>
	        </div>
			<div class="mobile-main">
            <dl class="">
            	<div id="allmap"></div>
                <div class="line-big padding-big-top border-bottom padding-bottom">
                    <div class="x10" style="position:relative">
                        <input name='' id='cityKeyword' type="text" class="d_input block fz20" style="border-radius:0px;border:0px;" placeholder='可输入城市名、英文名、三字码' />
                        <div class="tagMatches" style='margin-left:10px;display: block;' id="selectCityDiv">
                        </div>
                    </div>
                    <div class="x2">
                    	<input type="hidden" id="searchType" name="searchType" value="${searchType }"/>
						<input type="hidden" id="cityPoi" name="cityPoi" value="domesticCityThreeCode"/>
                        <button type="button" class='button fz20' style="border-radius:0px;border:0px;"><i class="icon-search" style='font-size:40px;'></i></button>
                    </div>
                </div>
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
		                		<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airportInfoById.threeCode }">${airportInfoById.name }（${airportInfoById.threeCode }）<p class='fz14'>${airportInfoById.englishName }</p></dd>
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
					<ticket:common type="domHotAirportList"/>
					<s:if test="#request.domHotAirportList != null">
						<s:iterator id="hotAirport" value="#request.domHotAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${hotAirport.threeCode }">${hotAirport.name }（${hotAirport.threeCode }）<p class='fz14'>${hotAirport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='A'>A</dt>
					<s:if test="#application.a_domesticAirportList != null">
						<s:iterator id="airport" value="#application.a_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='B'>B</dt>
					<s:if test="#application.b_domesticAirportList != null">
						<s:iterator id="airport" value="#application.b_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='C'>C</dt>
					<s:if test="#application.c_domesticAirportList != null">
						<s:iterator id="airport" value="#application.c_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='D'>D</dt>
					<s:if test="#application.d_domesticAirportList != null">
						<s:iterator id="airport" value="#application.d_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='E'>E</dt>
					<s:if test="#application.e_domesticAirportList != null">
						<s:iterator id="airport" value="#application.e_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='F'>F</dt>
					<s:if test="#application.f_domesticAirportList != null">
						<s:iterator id="airport" value="#application.f_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='G'>G</dt>
					<s:if test="#application.g_domesticAirportList != null">
						<s:iterator id="airport" value="#application.g_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='H'>H</dt>
					<s:if test="#application.h_domesticAirportList != null">
						<s:iterator id="airport" value="#application.h_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='I'>I</dt>
					<s:if test="#application.i_domesticAirportList != null">
						<s:iterator id="airport" value="#application.i_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='J'>J</dt>
					<s:if test="#application.j_domesticAirportList != null">
						<s:iterator id="airport" value="#application.j_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='K'>K</dt>
					<s:if test="#application.k_domesticAirportList != null">
						<s:iterator id="airport" value="#application.k_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='L'>L</dt>
					<s:if test="#application.l_domesticAirportList != null">
						<s:iterator id="airport" value="#application.l_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='M'>M</dt>
					<s:if test="#application.m_domesticAirportList != null">
						<s:iterator id="airport" value="#application.m_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='N'>N</dt>
					<s:if test="#application.n_domesticAirportList != null">
						<s:iterator id="airport" value="#application.n_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='O'>O</dt>
					<s:if test="#application.o_domesticAirportList != null">
						<s:iterator id="airport" value="#application.o_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='P'>P</dt>
					<s:if test="#application.p_domesticAirportList != null">
						<s:iterator id="airport" value="#application.p_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='Q'>Q</dt>
					<s:if test="#application.q_domesticAirportList != null">
						<s:iterator id="airport" value="#application.q_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='R'>R</dt>
					<s:if test="#application.r_domesticAirportList != null">
						<s:iterator id="airport" value="#application.r_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='S'>S</dt>
					<s:if test="#application.s_domesticAirportList != null">
						<s:iterator id="airport" value="#application.s_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='T'>T</dt>
					<s:if test="#application.t_domesticAirportList != null">
						<s:iterator id="airport" value="#application.t_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='U'>U</dt>
					<s:if test="#application.u_domesticAirportList != null">
						<s:iterator id="airport" value="#application.u_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='V'>V</dt>
					<s:if test="#application.v_domesticAirportList != null">
						<s:iterator id="airport" value="#application.v_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='W'>W</dt>
					<s:if test="#application.w_domesticAirportList != null">
						<s:iterator id="airport" value="#application.w_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='X'>X</dt>
					<s:if test="#application.x_domesticAirportList != null">
						<s:iterator id="airport" value="#application.x_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='Y'>Y</dt>
					<s:if test="#application.y_domesticAirportList != null">
						<s:iterator id="airport" value="#application.y_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
					<dt class='fz22 b_l_grey padding-big margin-large-top' id='Z'>Z</dt>
					<s:if test="#application.z_domesticAirportList != null">
						<s:iterator id="airport" value="#application.z_domesticAirportList">
							<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
						</s:iterator>
					</s:if>
				</div>
            </dl>
        </div>
        <div class="mobile-foot">
           <%@ include file="../common/quickNav.jsp" %>
            <div class="en_tag">
                <ul class='text-center c_blue'>
                    <li><a href="javascript:window.location.replace('#city');">历史选择</a></li>
                    <li><a href="javascript:window.location.replace('#hot');">热门机场</a></li>
                    <li><a href="javascript:window.location.replace('#A');">A</a></li>
                    <li><a href="javascript:window.location.replace('#B');">B</a></li>
                    <li><a href="javascript:window.location.replace('#C');">C</a></li>
                    <li><a href="javascript:window.location.replace('#D');">D</a></li>
                    <li><a href="javascript:window.location.replace('#E');">E</a></li>
                    <li><a href="javascript:window.location.replace('#F');">F</a></li>
                    <li><a href="javascript:window.location.replace('#G');">G</a></li>
                    <li><a href="javascript:window.location.replace('#H');">H</a></li>
                    <li><a href="javascript:window.location.replace('#I');">I</a></li>
                    <li><a href="javascript:window.location.replace('#J');">J</a></li>
                    <li><a href="javascript:window.location.replace('#K');">K</a></li>
                    <li><a href="javascript:window.location.replace('#L');">L</a></li>
                    <li><a href="javascript:window.location.replace('#M');">M</a></li>
                    <li><a href="javascript:window.location.replace('#N');">N</a></li>
                    <li><a href="javascript:window.location.replace('#O');">O</a></li>
                    <li><a href="javascript:window.location.replace('#P');">P</a></li>
                    <li><a href="javascript:window.location.replace('#Q');">Q</a></li>
                    <li><a href="javascript:window.location.replace('#R');">R</a></li>
                    <li><a href="javascript:window.location.replace('#S');">S</a></li>
                    <li><a href="javascript:window.location.replace('#T');">T</a></li>
                    <li><a href="javascript:window.location.replace('#U');">U</a></li>
                    <li><a href="javascript:window.location.replace('#V');">V</a></li>
                    <li><a href="javascript:window.location.replace('#W');">W</a></li>
                    <li><a href="javascript:window.location.replace('#X');">X</a></li>
                    <li><a href="javascript:window.location.replace('#Y');">Y</a></li>
                    <li><a href="javascript:window.location.replace('#Z');">Z</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="dialog" style="display:none;">
</div>
<script type="text/javascript">
    $('.tit_tab a').on('tap',function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    })
</script>
<script type="text/javascript">
	//百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	function myFun(result){
		var cityName = result.name;
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/airport_queryCityByIp.action',
			data:'keyword='+cityName,
			success:function(data){
				var str = "<dd class='fz22 margin-big border-bottom padding-big-bottom' value="+data.name+">"+data.description+"（"+data.name+"）<p class='fz14'>"+data.value+"</p></dd>";
				$("#currentCity").html(str);
				selectCityByList();
			}
		});
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
</script>
</body>
</html>