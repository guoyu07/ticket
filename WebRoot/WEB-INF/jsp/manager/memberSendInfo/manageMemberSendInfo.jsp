<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/memberSendInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td >管理会员信息发送记录</td>
					    </tr>
					    <tr class="text_align_left">
					      <td><span>
							<form action="${actionName }_manage.action?versionFlag=site" method="get" style="float: left;" enctype="application/x-www-form-urlencoded;charset=UTF-8">
							  	推送时间:<input name="startTime" type="text" onclick="new WdatePicker();" value='<s:date name="startTime" format="yyyy-MM-dd" />'/>
						  				~
							  			<input name="endTime" type="text" onclick="new WdatePicker();" value='<s:date name="endTime" format="yyyy-MM-dd" />'/>
							  	推送标题:<input name="title" value="${title }"/>
							  	电话号码:<input name="phone" value="${phone }"/>
							  	MAC地址:<input name="mac" value="${mac }"/>
				  					<input type="submit" value="查询"/>
							</form>
					      	<a target="_blank" href="${actionName }_downReport.action?startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />&title=${title}&phone=${phone}&mac=${mac}">下载</a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="20">编号</td>
								    <td width="80">推送时间</td>
								    <td width="80">推送方式</td>
								    <td width="80">推送标题</td>
								    <td width="80">推送内容</td>
								    <td width="80">推送链接</td>
								    <td width="80">电话号码</td>
								    <td width="80">MAC地址</td>
								    <td width="80">是否已读</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td>${st.count }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.method.text }</td>
										    <td>${c.title }</td>
										    <td>${c.message }</td>
										    <td>${c.url }</td>
									    	<s:if test="#c.phone == null">
										    	<s:set var="phone" value="memberMacService.get(@com.ticket.pojo.Member@class, #c.member_id).phone"></s:set>
									    	</s:if>
									    	<s:else>
										    	<s:set var="phone" value="#c.phone"></s:set>
									    	</s:else>
										    <td>
										    	<s:property value="phone"/>
										    </td>
										    <td>
										    	<s:iterator var="membermac" value="memberMacService.queryByPhone(#phone)" status="st">
										    		${membermac.mac } ${st.last ? null : '<br/>' }
										    	</s:iterator>
										    </td>
										    <td>${c.h5 ? '<font color="blue">已读</font>' : '<font color="red">未读</font>' }</td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="70">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					        </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
					  <%-- <%
						StringBuffer url = new StringBuffer();
						Enumeration enums = request.getParameterNames();
						while(enums.hasMoreElements()){
							
							String name = (String)enums.nextElement();
							Strinb value = 
							if("title".equals(name)){
								
								
							}
							if(!"pn".equals(name)){
								
								url.append("&").append(URLEncoder.encode(name, "UTF-8")).append("=").append();
							}
						}
						request.setAttribute("url", url);
					%>
					<s:if test="pageModule != null && pageModule.totalCount > 0">
						<div class="paging">
							<div class="black">
								<a>总数：${pageModule.totalCount }条</a>
								<pg:pager items="${pageModule.totalCount}"
									url="/${actionName}_${currentMethod}.action" maxIndexPages="10"
									maxPageItems="${pageSize}"
									export="currentPageNumber=pageNumber">
									<pg:param name="method" />
									<pg:first>
										<a href="${pageUrl}${url }">首页</a>
									</pg:first>
									<pg:prev>
										<a href="${pageUrl}${url }">上一页</i>
										</a>
									</pg:prev>
									<pg:pages>
										<c:choose>
											<c:when test="${currentPageNumber eq pageNumber }">
												<span class="current">${pageNumber}</span>
											</c:when>
											<c:otherwise>
												<a href="${pageUrl}${url }">${pageNumber}</a>
											</c:otherwise>
										</c:choose>
									</pg:pages>
									<pg:next>
										<a href="${pageUrl}${url }">下一页</a>
									</pg:next>
									<pg:last>
										<a href="${pageUrl}${url }">末页</a>
									</pg:last>
								</pg:pager>
							</div>
						</div>
					</s:if> --%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>