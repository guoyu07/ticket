<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript"
		src="/manager/js/pageRedirectTemplate.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
						<tr class="text_align_left my_table_top">
							<td>
								页面跳转模板回收站
							</td>
						</tr>
						<tr class="text_align_left">
							<td>
								<span> <a href="#" class="batchRealDeleteAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
								<a href="#" class="batchRestoreBtn" value="${actionName}CheckBox" entityName="${entityName}">批量还原</a> 
								</a> </span>
							</td>
						</tr>
						<tr>
							<td>
								<table class="my_table100">
									<tr class="my_table_top1">
										<td width="20">
											<input id="selectCheckBoxAllChk"
												objectChkName="${actionName}CheckBox" type="checkbox">
										</td>
										<td width="80">
											页面名称
										</td>
										<td width="80">
											跳转页面
										</td>
										<td width="80">
											页面类型
										</td>
										<td>
											单篇多篇
										</td>
										<td>所在目录</td>
										<td>到哪个页面Ajax</td>
										<td width="150" class="text_align_center">
											操作
										</td>
									</tr>
									<s:if test="pageModule != null && pageModule.totalCount > 0">
										<s:iterator id="c" value="pageModule.pageList" status="st">
											<tr>
												<td>
													<input name="${actionName}CheckBox" type="checkbox"
														value="${c.id }">
												</td>
												<td>
													${c.name }
												</td>
												<td>
													${c.toPage }
												</td>
												<td>
													<s:if test="#c.type == 1">
														列表页
													</s:if>
													<s:else>
														详细页
													</s:else>
												</td>
												<td>
													<s:if test="#c.isSinglePage == 1">
														单篇
													</s:if>
													<s:else>
														多篇
													</s:else>
												</td>
												<td>${c.directory }</td>
												<td>${c.toPageAjax }</td>
												<td>
													<span> <a href="#" class="logicDeleteBtn"
														entityName="${entityName}" value="${c.id }">删除</a> <a
														href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a>
													</span>
												</td>
											</tr>
										</s:iterator>
									</s:if>
									<s:else>
										<tr>
											<td colspan="7">
												${noDataMessage }
											</td>
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