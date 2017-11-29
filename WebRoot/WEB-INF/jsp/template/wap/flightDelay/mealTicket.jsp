<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="机票改签"/>
			   </jsp:include>
				<div class="mobile-main">
					<div class="mr40 line-big">
						<div class="x9">
							<input type="text" name="" value="" class="input"
								style='margin-left: -10px; height: 40px;'>
						</div>
						<div class="x2">
							<button type=""
								class='button fz20 padding-top padding-bottom bg-sub'
								style='margin-left: -15px; width: 140px;'>
								确定
							</button>
						</div>
					</div>
					<ticket:common type="flightCompanyList"/>
					<s:if test="#request.flightCompanyList != null">
						<s:iterator id="flightCompany" value="#request.flightCompanyList">
							<div class="c_content">
								<div class="media-body line">
									<div class="x3">
										<ticket:commonAnnex type="annex" entityUuid="${flightCompany.id}" annexType="1" size="1"/>
											<s:if test="#request.annex != null">
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
											</s:if>
									</div>
									<div class="x6 fz24">
										${flightCompany.name }
										<p class='fz16'>
											电话：${flightCompany.phone }
										</p>
									</div>
									<div class="x3" style='padding-top: 5px;'>
										<a href="tel:${flightCompany.phone }" class='fz18 c_blue'><i class='phone_icon'></i>一键拨号</a>
									</div>
								</div>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<div class="c_content">
							<div class="media-body line">
								${noDataMessage }
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
</html>