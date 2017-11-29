<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/letterStation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>查看站内信</span>
					</div>
					<form>
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="80">
									标题：
								</td>
								<td>
									${letterStation.title}      
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="80">
									内容：
								</td>
								<td>
									${letterStation.content}      
								</td>
							</tr>
							<s:if test="letterStation.url != null && letterStation.url != ''">
								<tr>
									<td class="text_align_right" width="150">
										链接地址：
									</td>
									<td>
										<div style="float: left;">
											<a href="${letterStation.url}">点击访问</a>  
										</div>
									</td>
								</tr>
							</s:if>
						</table>
						<div class="my_table_list_r">
						    <input id="" onclick="javascript:window.location.href='/stationLetterReadLog_manage.action'";" type="button" value="返回" class="btn_remove margin_left20">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>