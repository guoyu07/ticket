<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/preResultDefinition.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >查看内存搜索</td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td>H5连接</td>
								    <td>PC连接</td>
								    <td>查看</td>
								  </tr>
								  <tr>
								  	<td colspan="3"><h1>精确定位</h1></td>
								  </tr>
								  	<s:iterator id="c" value="@com.ticket.serviceImpl.CommonSearchServiceImpl@location.values()" status="st">
								  		<tr>
										    <td><a href="${c.url }">${c.title }</a></td>
										    <td><a href="${c.pcUrl }">${c.title }</a></td>
										    <td><a href="preResultDefinition_showDeatils.action?versionFlag=site&orderValue=${st.index }">查看详情</a></td>
										 </tr>
								  	</s:iterator>
								  <tr>
								  	<td colspan="3"><h1>普通搜索</h1></td>
								  </tr>
								  	<s:iterator id="c" value="@com.ticket.serviceImpl.CommonSearchServiceImpl@seo.values()" status="st">
								  		<tr>
										    <td><a href="${c.url }">${c.title }</a></td>
										    <td><a href="${c.pcUrl }">${c.title }</a></td>
										    <td><a href="preResultDefinition_showDeatils.action?versionFlag=site&orderValue=${st.index }">查看详情</a></td>
										 </tr>
								  	</s:iterator>
								  <tr>
								  	<td colspan="3"><h1>商业搜索</h1></td>
								  </tr>
								  	<s:iterator id="c" value="@com.ticket.serviceImpl.CommonSearchServiceImpl@business.values()" status="st">
								  		<tr>
										    <td><a href="${c.url }">${c.title }</a></td>
										    <td><a href="${c.pcUrl }">${c.title }</a></td>
										    <td><a href="preResultDefinition_showDeatils.action?versionFlag=site&orderValue=${st.index }">查看详情</a></td>
										 </tr>
								  	</s:iterator>
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