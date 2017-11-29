<%@page import="com.ticket.util.UrlUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>bjdj支付激活页面模板详细信息</span>
					</div>
					<form action="" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<input type="hidden" name="news_id" id="news_id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									模板名称
								</td>
								<td>
									<label>${bjdjPageTemplate.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									页面内容：
								</td>
								<td>
									<label>${bjdjPageTemplate.content}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									按钮名称：
								</td>
								<td>
									<label>${bjdjPageTemplate.buttonName}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									按钮链接：
								</td>
								<td>
									<label>${bjdjPageTemplate.buttonUrl}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									按钮类型：
								</td>
								<td>
									<label>${bjdjPageTemplate.buttonType}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									按钮样式：
								</td>
								<td>
									<label>${bjdjPageTemplate.buttonClass}</label>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <!-- <input id="submitBtn" type="submit" value="评论" class="btn_submit"> -->
						    <a href="/bjdjPageTemplate_manage.action?versionFlag=${versionFlag}">返回</a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>