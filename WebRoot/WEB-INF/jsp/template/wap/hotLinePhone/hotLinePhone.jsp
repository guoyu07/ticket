<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">服务热线</s:param>
				</s:include>
				<div class="mobile-main">
                    <div class="tit_tab">
                        <a href="#" class="selected">机场热线</a>
                        <a href="/airport_flightCompanyPhone.action?direct=true" class="" >航空公司热线</a>
                    </div>
					<ticket:common type="hotLineList"/>
					<s:if test="#request.hotLineList != null">
						<s:iterator id="hotLine" value="#request.hotLineList">
							<div class="c_content">
								<div class="media-body line">
									<div class="x9 fz24">
										${hotLine.name }
										<p class='fz16'>
											${hotLine.phone }
										</p>
									</div>
									<div class="x3 text-right" style='padding-top: 5px;'>
										<a href="tel:${hotLine.oneKeyCall}" class='fz18 c_blue'><i class='phone_icon'></i>一键拨号</a>
									</div>
								</div>
							</div>
						</s:iterator>
					</s:if>
					<!-- <a href="/airport_flightCompany.action">
					<div class="tit">
						航空公司
					</div>
					</a> -->
					<s:else>
						<div class="c_content">
							<div class="media-body line">
								<div class="x6 fz24">
									${noDataMessage }
								</div>
							</div>
						</div>
					</s:else>
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