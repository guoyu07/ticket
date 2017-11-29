<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/common/searchResult.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="title" name="搜索结果"/>
				</jsp:include>
				<div class="mobile-main">
					<input type="hidden" id="newsClass_id" value="${newsClass.id }"/>
					<input type="hidden" id="countId" value="${id }"/>
					
					<div class="notice_ls">
						<ul id="search_list">
                       
								
                                
                                <s:if test="locationList.size() != 0">
                                <s:property value="#locationList.size()"/>
								<s:iterator id="searchObj" value="locationList" status="st">
									<div id="loadNews">
										<li>
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#searchObj.url, 'memberId', #parameters.memberId)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'member_id', #parameters.member_id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'mid', #parameters.mid)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'id', #parameters.id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'fromApp', #session.fromApp)" />
											<a href="javascript:;"  class='fz26' onclick="havaUrl(this);" attrValue="${newUrl }"><span></span>
												<font style="color: #00a9ff;"><ticket:format value="${searchObj.title }" size="20"/></font>
											</a>
										</li>
									</div>
								</s:iterator>
                                </s:if>
							<s:else>
								<div id="loadNews">
									<li style="overflow:auto; height:auto;text-indent:3.4em;">
										
                                       
                                        
                                        
											<span style="color:red; font-size:32px; ">亲，本站没有搜到相关信息，请您前往<a href="http://m.kunmingia.com/airport/wenxuntai.ticket?direct=true" style="color:blue;font-size:32px;">问讯台</a>或拨打机场服务热线<a href="tel:087196566" style="color:blue;font-size:32px;">0871-96566</a>进行咨询。</span>
										</a>
									</li>
								</div>
							</s:else>
						</ul>
					</div>
					
					<div class="notice_ls" style="display:none;">
						<ul>
							<s:if test="searchList.size() != 0">
								<s:iterator id="searchObj" value="searchList" status="st">
									<div id="loadNews">
										<li>
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#searchObj.url, 'memberId', #parameters.memberId)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'member_id', #parameters.member_id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'mid', #parameters.mid)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'id', #parameters.id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'fromApp', #session.fromApp)" />
											<a href="javascript:;"  class='fz26' onclick="havaUrl(this);" attrValue="${newUrl }"><span></span>
												<font style="color: #00a9ff;"><ticket:format value="${searchObj.title }" size="20"/></font>
											</a>
										</li>
									</div>
								</s:iterator>
							</s:if>
							<s:else>
								<div id="loadNews">
									<li>
										<a href="javascript:;">
											<span>亲，本站没有搜到相关信息！</span>
										</a>
									</li>
								</div>
							</s:else>
						</ul>
						<!-- <s:if test="searchList != null">
							<h3 class="notice_ft" startSize="4" getCount="4">
								<i class="fa fa-download" ></i>加载更多
							</h3>
						</s:if> -->
					</div>
					<s:if test="businessList.size() != 0">
					<div class="notice_ls" style=" display:none;">
						<ul>
								<s:iterator id="searchObj" value="businessList" status="st">
									<div id="loadNews">
										<li>
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#searchObj.url, 'memberId', #parameters.memberId)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'member_id', #parameters.member_id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'mid', #parameters.mid)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'id', #parameters.id)" />
											<s:set var="newUrl" value="@com.ticket.util.GeneralUtil@addParam(#newUrl, 'fromApp', #session.fromApp)" />
											<a href="javascript:;"  class='fz26' onclick="havaUrl(this);" attrValue="${newUrl }"><span></span>
												<font style="color: #00a9ff;"><ticket:format value="${searchObj.title }" size="20"/></font>
											</a>
										</li>
									</div>
								</s:iterator>
						</ul>
					</div>
					</s:if>
					<div class="mr40">
		                <div class='tit_tab' style="margin-left: 35%">
		                    <%-- <a href="javascript:;" onclick="goDitu(this);" attrValue="http://map.baidu.com/?newmap=1&ie=utf-8&s=s%26wd%3D<%=URLEncoder.encode((String)request.getAttribute("commonKeyword"), "utf-8") %>" class="x5 button bg-yello d_button">地图搜索</a>
		                    <div class="x1"></div> --%>
		                    <a href="javascript:;" onclick="goBaidu(this);" attrValue="http://m.baidu.com/s?word=${commonKeyword }" class="x5 button bg-yello d_button">百度搜索</a>
		                </div>
		            </div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>
