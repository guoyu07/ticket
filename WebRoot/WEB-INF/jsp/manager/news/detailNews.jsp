<%@page import="com.ticket.util.UrlUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/news.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新闻详细信息</span>
					</div>
					<form action="/newsComment_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<input type="hidden" name="news_id" id="news_id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属类别
								</td>
								<td>
									<ticket:systemCommon type="newsClassObj" value="${news.newsClass_id}"/>
										<label>${newsClassObj.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息编辑：
								</td>
								<td>
									<label>${news.author}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息标题：
								</td>
								<td>
									<label>${news.title}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息副标题：
								</td>
								<td>
									<label>${news.subTitle}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									H5链接：
								</td>
								<td>
									<%=UrlUtil.getDomainName() %>airport/${news.status.visitUrl}.ticket
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC链接：
								</td>
								<td>
									<%=UrlUtil.getDomainName() %>airport/newsView/${news.id}.html
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									缩略图：
								</td>
								<td>
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
									<s:if test="#request.queryAnnexList != null">
										<s:iterator id="annex" value="#request.queryAnnexList" status="st">
											<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
										</s:iterator>
									</s:if>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻图片：
								</td>
								<td>
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="2" size="10"/>
									<s:if test="#request.queryAnnexList != null">
										<s:iterator id="annex" value="#request.queryAnnexList" status="st">
											<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
										</s:iterator>
									</s:if>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻内容：
								</td>
								<td>
									<label>${news.content}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									评论内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写评论内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									评论几星：
								</td>
								<td>
									<select id="star" name="star" class="my_select">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									关联景点：
								</td>
								<td>
									${news.scenicSpots.name }
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="评论" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/newsComment_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/newsComment_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>