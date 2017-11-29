<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="值机信息" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<s:if test="#request.checkinList.size() != 0">
						<s:iterator id="checkinInfo" value="#request.checkinList">
							<ticket:common type="memberObj" value="${member.id }"/>
							<div class="notice_ls" style="color: #666">
								<h4>
									<p class="fz22" style="color: #333">
										航班号：${flightNumber }
										<span class='float-right fz22'>
											<ticket:common type="systemDicObjByName" value="${checkinInfo.deptAirportCode }"/>${systemDicObjByName.description }
											-
											<ticket:common type="systemDicObjByName" value="${checkinInfo.destAirportCode }"/>${systemDicObjByName.description }
										</span>
									</p>
								</h4>
								<div class="text_content">
									<!-- <h2 class="fz26 padding-top padding-bottom" style="color: #333">
										值机人姓名
									</h2> -->
									<h5 class="fz22 padding-top padding-bottom">
										电子客票号
										<span class='margin-large-left'>${checkinInfo.ticketNo }</span>
									</h5>
									<!-- <h5 class="fz22 padding-top padding-bottom">
										身份证
										<span class='margin-large-left'></span>
									</h5> -->
									<h5 class="fz22 padding-top padding-bottom">
										座位号
										<span class='margin-large-left'>${checkinInfo.seatNo }</span>
									</h5>
									
									<s:property value="#url"/>
									<a href="airportm_removeCheckinInfo.action?flightNo=${flightNumber }&tktNo=${checkinInfo.ticketNo }&coupon=1&flightDate=${checkinInfo.flightDate }&deptCode=${checkinInfo.deptAirportCode }&destCode=${checkinInfo.destAirportCode }&id=${checkinInfo.id }" class="margin-big-top button d_button c_white b_blue">
										取消值机
									</a>
									<a href="airport_dengjipai.action?tktNo=${checkinInfo.ticketNo }&coupon=1" class="margin-big-top button d_button c_white b_blue">
										登机牌
									</a>
									<s:if test="#checkinInfo.byShare!=null">
										<a id="revokeShare" ticketNo="${checkinInfo.ticketNo }" value="${checkinInfo.id }" class="margin-big-top button d_button c_white b_blue">取消分享</a>
									</s:if>
								</div>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<div class="notice_ls" style="color: #666">
							<h1>${noDataMessage }</h1>
						</div>
					</s:else>
				</div>
				<div class="mobile-foot" style="height:140px;">
                    <a href="airportm_addCheckinInfo.action"><div class="tit" >去值机</div></a>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>
