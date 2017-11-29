<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/countSearch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
						<tr class="text_align_left my_table_top">
							<td>管理统计搜索词表</td>
						</tr>
						<tr style="text-align: center">
					    	<td colspan="10">
								<form action="${actionName }_manage.action?versionFlag=site" method="get">
									搜索时间：<input type="text" name="startTime" onclick="new WdatePicker();" value='${param.startTime }'/>&nbsp;&nbsp;~&nbsp;&nbsp;
					    			<input type="text" name="endTime" onclick="new WdatePicker();" value='${param.endTime }'/>
									排除关键词：<input type="text" name="excludeWord" value="${param.excludeWord }"/>
									搜索关键词：<input type="text" name="searchWord" value="${param.searchWord }"/>
									搜索类型：<select name="type">
										<option value="" ${empty param.type ? 'selected="selected"' : null }>全部</option>
										<s:iterator var="t" value="@com.ticket.enumer.SearchType@values()">
										<option value="${t.value }" ${param.type == t.value ? 'selected="selected"' : null }>${t.text }</option>
										</s:iterator>
									</select>
						  			<input type="submit" value="查询"/>
						  			<a href="${actionName }_batchDownLoad.action?searchWord=${param.keyword }&startTime=${param.startTime }&endTime=${param.endTime }&type=${param.type }&excludeWord=${param.excludeWord}" target="_blank">下载</a>
							  	</form>
							</td>
						</tr>
						<tr class="text_align_left">
							<td>
								<span>
									<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
									<a><input id="isChecked" type="checkbox" /></a>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<table class="my_table100">
									<tr class="my_table_top1">
										<td width="20"><input id="selectCheckBoxAllChk"
											objectChkName="${actionName}CheckBox" type="checkbox">
										</td>
										<td width="20">编号</td>
										<td width="80">日期</td>
										<td width="80">搜索词</td>
										<td width="80">触发的关键词</td>
										<td width="80">触发的否定词</td>
										<td>落地页链接</td>
										<td width="80">落地页名称</td>
										<td width="100" class="text_align_center">操作</td>
									</tr>
									<s:if test="pageModule != null && pageModule.totalCount > 0">
										<s:iterator id="c" value="pageModule.pageList" status="st">
											<tr>
												<td><input name="${actionName}CheckBox" type="checkbox"
													value="${c.id }">
												</td>
												<td>${st.count }</td>
												<td><s:date name="#c.status.createTime" format="yyyy-MM-dd" /></td>
												<td>${c.searchWord }</td>
											    <td><ticket:format value="${c.keyword }" size="15"/></td>
												<td><ticket:format value="${c.negative }" size="15"/></td>
												<td>${c.goUrlHref }</td>
												<td>${c.goUrl }</td>
												<td><span> 
													<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
													<a href="countSearch_detail.action?id=${c.id }">查看详情</a>
												</span>
												</td>
											</tr>
										</s:iterator>
									</s:if>
									<s:else>
										<tr>
											<td colspan="80">${noDataMessage }</td>
										</tr>
									</s:else>
								</table></td>
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