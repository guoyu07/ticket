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
						<span>回复处理部门</span>
					</div>
					<form action="/${actionName}_feedbackDepartment.action" id="commonForm" name="commonForm">
						<input type="hidden" name="idsValue" value="${idsValue}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									回复处理信息：
								</td>
								<td>
									<textarea name="content" class="my_input" datatype="*" style="height: 100px; width: 200px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写你对处理信息的回复</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否合格：
								</td>
								<td>
									<input name="qualified" type="radio" value="true"/>&nbsp;&nbsp;合格&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="qualified" type="radio" value="false"/>&nbsp;&nbsp;不合格&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<p class="Validform_checktip" style="display:inline;"> 请填写你对处理信息的回复</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
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