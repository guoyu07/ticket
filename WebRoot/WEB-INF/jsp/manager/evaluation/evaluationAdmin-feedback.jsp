<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/manager/js/evaluationAdmin.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>发送到处理部门</span>
					</div>
					<form action="/${actionName}_feedback.action" id="commonForm" name="commonForm">
						<input type="hidden" name="idsValue" value="${idsValue}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									回复内容：
								</td>
								<td>
									<textarea name="content" class="my_input" datatype="*" style="height: 100px; width: 400px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写回复内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模板：
								</td>
								<td>
									<ul class="easyui-datalist" style="width:400px;height:200px">
										<s:iterator var="t" value="templates">
									    <li value="${t.content }">${content }</li>
									    </s:iterator>
									</ul> 
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="发送" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="manageLink" href="<%=request.getHeader("Referer") %>"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>