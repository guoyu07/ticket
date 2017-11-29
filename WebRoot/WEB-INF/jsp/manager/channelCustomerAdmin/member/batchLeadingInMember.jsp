<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/batchLeadingInMember.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>导入会员</span>
					</div>
					<form id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									文件模板：
								</td>
								<td>
									<a style="float:left;" href="/manager/ExcelTrmplate/memberTemplate.xls">文件模板下载</a>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									选择文件：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="picture" name="picture"
									style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input type="button" id="commonFormSumit" value="导入" class="btn_submit">
						</div>
					</form>
				</div>
				<div id="viewbox2" class="site_right_box">
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>