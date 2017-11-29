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
						<span>搜索词统计详情</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${countSearch.id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									搜索时间：
								</td>
								<td>
									<label>${countSearch.status.createTime }</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									搜索词：
								</td>
								<td>
									<label>${countSearch.searchWord }</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									触发的关键词：
								</td>
								<td>
									<label>${countSearch.keyword }</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									落地页链接：
								</td>
								<td>
									<label><a href="${countSearch.goUrlHref }">${countSearch.goUrlHref }</a></label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									会员：
								</td>
								<td>
									<label>${empty countSearch.member ? null : member.phone}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									IP地址：
								</td>
								<td>
									<label>${countSearch.ip }</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									搜索类型：
								</td>
								<td>
									<label>${countSearch.type.text }</label>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>