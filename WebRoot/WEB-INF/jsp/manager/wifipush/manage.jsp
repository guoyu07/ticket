<%@page import="java.util.Date"%>
<%@page import="com.ticket.util.GeneralUtil"%>
<%@page import="com.ticket.pojo.WifiPush"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/wifi.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理wifi推送数据</td>
					    </tr>
					    <tr style="text-align: center">
					    	<td colspan="10">
								<form action="${actionName }_manage.action?versionFlag=site" method="get">
									MAC地址:<input type="text" name="mac" value="${param.mac }"/>
									电话号码:<input type="text" name="phone" value="${param.phone }"/>
						  			<input type="submit" value="查询"/>
							  	</form>
							</td>
						</tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="20">编号</td>
								    <td width="80">商家ID</td>
								    <td width="80">设备ID</td>
								    <td width="80">手机mac地址</td>
								    <td width="80">时间戳</td>
								    <td width="80">信号强度</td>
								    <td width="80">手机号码</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.mid }</td>
										    <td>${c.rid }</td>
										    <td>${c.mac }</td>
										    <td>
										    	<%
										    		WifiPush c = (WifiPush)request.getAttribute("c");
										    		if(GeneralUtil.isNotNull(c.getTs())){
										    			
											    		String ts = c.getTs() + "000";
										    			Long tss = Long.parseLong(ts);
										    			Date tsDate = new Date(tss);
										    			request.setAttribute("ts", tsDate);
										    		}
										    	%>
										    	<s:date name="#request.ts" format="yyyy-MM-dd HH:mm:ss"/>
										    </td>
										    <td>${c.sig }</td>
										    <td>${c.tel }</td>
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
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>