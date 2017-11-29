<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/lostGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >下载登记以及领取信息</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
								<form action="${actionName }_batchDownLoad.action?versionFlag=site" method="post">
									时间:<input name="startTime" type="text" onclick="new WdatePicker();"/>
							  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endTime" type="text" onclick="new WdatePicker();"/>
							  			<br/>
						  			<input type="submit" value="下载"/>
						  			<input type="reset" value="重置"/>
								</form>
							</td>
						</tr>
					  </table>
					  <form id="commonForm" name="commonForm">
					  	<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									文件模板：
								</td>
								<td>
									<a style="float:left;" href="/manager/ExcelTrmplate/info.xls">文件模板下载</a>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>