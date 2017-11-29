<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
    
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">航班详情</s:param>
					<s:param name="url">javascript:window.location.assign($.cookie('listUrl'));</s:param>
				</s:include>
				<div class="mobile-main">
				<ticket:common type="memberFocusFlightObj" value="${flightNumber }" var="${flightDate }" cTypeId="${flightState }"/>
				<s:if test="#request.memberFocusFlightObj != null">
					<input type="hidden" id="forFocus" name="forFocus" value=""/>
				</s:if>
				<s:else>
					<input type="hidden" id="forFocus" name="forFocus" value="${forFocus }"/>
				</s:else>
				<input type="hidden" value="${flightNumber }" name="flightNumber" id="flightNumber"/>
	        	<input type="hidden" value="${flightDate }" name="flightDate" id="flightDate"/>
	        	<input type="hidden" value="${flightState }" name="flightState" id="flightState"/>
				<input type="hidden" id="mid" name="mid" value="${mid }"/>
				<input type="hidden" id="sessionMember" name="sessionMember" value="${session.sessionMember }">
					<div class="new_fly_ls">
		                <ul class="">
		                    <li style='margin-bottom:0px;'>
		                        <div class="fly_line clearfix no-background">
		                            <span class='text-center'><em class='c_blue fz40'>
		                            <ticket:common type="trangeFlightNoByCompanyCode" value="${airportPlan.flt }"/>
		                            	${trangeFlightNoByCompanyCode }
		                            </em></span>
		                        </div>
		                        <div class="fly_time clearfix text-center fz30">
		                            <ticket:common type="queryCompanyByFlightNo" value="${airportPlan.flt }"/>
									<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNo.id }" annexType="1" size="1"/>
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position:relative;top:5px;'>
		                            &nbsp;&nbsp;
									${queryCompanyByFlightNo.name }
		                        </div>
		                    </li>
		                </ul>
		            </div>
					<s:if test="#request.airportPlan != null">
						<div class='from_or_to_fly'>
							<div class="c_content">
				                <dl>
			                        <dt>
			                            <i class='from_fly'></i>
			                            	<ticket:common type="airportInfoByFourCode" value="${airportPlan.dept }"/>
			                            	${airportInfoByFourCode.name }
			                            	<span>${flightDate} ${airportPlan.std }</span>
			                        </dt>
			                        <dd class='line'>
			                            <div class="xl4">计划起飞时间<p class='c_black' style="font-size: 48px;">${airportPlan.std }</p></div>
			                            <div class="xl4">值机柜台<p>--</p></div>
			                            <div class="xl4">登机口<p>--</p></div>
			                        </dd>
			                    </dl>
			                </div>
			                <div class="c_content">
			                    <dl>
			                        <dt>
			                            <i class='from_to'></i>
			                            	<ticket:common type="airportInfoByFourCode" value="${airportPlan.arrive }"/>
			                            	${airportInfoByFourCode.name }
			                             <span>${flightDate} ${airportPlan.eta }</span>
			                        </dt>
			                        <dd class='line'>
			                            <div class="xl4">计划达到时间<p class='c_black' style="font-size: 48px;">${airportPlan.eta }</p></div>
			                            <div class="xl4">行李转盘<p>--</p></div>
			                            <div class="xl4">到达口<p>--</p></div>
			                        </dd>
			                    </dl>
			                </div>
						</div>
						<div class="tit_tab">
							<s:if test="airportPlan.dept=='ZPPP'">
								<a href="#" class="journeyHelper" flightNumber="${flightNumber }" flightDate="${flightDate }" flightState="depart"  flag="detail">旅程助手</a>
								<s:if test='focusFlightFlag == "hadFocus"'>
									<a href="/airport/bianjiedengji.ticket">便捷登机</a>
								</s:if>
								<s:else>
									<a href="#" flightNumber="${flightNumber }" flightDate="${flightDate }" flightState="depart" class='memberFocusFlight' plan="plan">关注航班</a>
								</s:else>
								<a href="/airport_restaurant.action?flag=canyin">餐饮服务</a>
								<s:set var="checkinInfos" value="checkinInfoService.query(flightNumber, flightDate)"></s:set>
								<s:if test="#checkinInfos.size() > 0">
									<a href="/airportm_checkinInfo.action?flightNumber=${airportPlan.flt }&flightDate=${airportPlan.flightDate }">网上值机</a>
								</s:if>
								<s:else>
									<a href="/airport/wangshangzhiji.ticket">网上值机</a>
								</s:else>
							</s:if>
							<s:else>
								<a href="#" class="journeyHelper" flightNumber="${flightNumber }" flightDate="${flightDate }" flightState="arrival">旅程助手</a>
								<s:if test='focusFlightFlag == "hadFocus"'>
									<a href="/airport/airport_restaurant.action?flag=lingsho">购物中心</a>
								</s:if>
								<s:else>
									<a href="#" flightNumber="${flightNumber }" flightDate="${flightDate }" flightState="arrival" class='memberFocusFlight' plan="plan" >关注航班</a>
								</s:else>
								<a href="/airport_restaurant.action?flag=canyin">餐饮服务</a>
								<a href="/airport/1455874140786.ticket">自助旅游</a>
							</s:else>
						</div>
					</s:if>
					<s:else>
						<div class="c_content">
							${noDataMessage }
						</div>
						<div class="tit_tab">
							<a href="javascript:return_prepage();">返回</a>
						</div>
					</s:else>
					<ticket:common type="airportInfoByFourCode" value="${airportPlan.dept }"/>
					<ticket:common type="advertFlightListByCity" value="${airportInfoByFourCode.name }"/>
					<s:if test="#request.advertFlightListByCity != null">
						<s:iterator value="#request.advertFlightListByCity" var="advertFlight">
							<ticket:common type="advertObjByName" value="${advertFlight.advert.name }"/>
							<s:if test="#request.advertObjByName != null">
								<ticket:commonAnnex type="queryAnnexList" entityUuid="${advertObjByName.id }" annexType="1" size="10"/>
				            	<s:if test="#request.queryAnnexList != null">
				            		<a href="${advertFlight.advert.url }"><img src="${queryAnnexList[0].annexPath }" alt=" " width="100%" /></a>
				            	</s:if>
							</s:if>
						</s:iterator>
					</s:if>
                    <!-- <a href="#"><img src="/upload/site/image/201612/20161227150127.png" alt=" " width="100%" height="80"></a> -->
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	<script type="text/javascript">
	    $(function(){
		    $('.focusOnFlight').unbind().bind('click',function(){
			    $("#flightNumber").val($(this).attr("flightNumber"));
			    $("#flightDate").val($(this).attr("flightDate"));
			    $("#flightState").val($(this).attr("flightState"));
		    	$.do_dialog.open({'msg':$('.fly_dialog')});
			 });
	    });
	</script>
	<script type="text/javascript"> 
		window.onload=function(){ 
		 var forFocus = $("#forFocus").val();
		 var flightNumber = $("#flightNumber").val();
		 var flightDate = $("#flightDate").val();
		 var flightState = $("#flightState").val();
		 if(!JM.isNull(forFocus)&&forFocus!=""){
			 $("#forFocus").val("");
			 $.ajax({
				type:'POST',
				datatype:'json',
				url:'airportm_memberFocusFlight.action',
				data:'flightNumber='+flightNumber+'&flightDate='+flightDate+'&flightState='+flightState,
				success:function(data){
					var val = $.parseJSON(data);
					if(val.status == JM.YES){
						window.location.reload();
						JM.alert('关注成功!');
						return;
					}else{
						window.location.reload();
						JM.alert(val.info);
						return;
					}
				}
			});
		 }
		};
	</script> 
</html>