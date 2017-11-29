<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemModule.js"></script>
	<script type="text/javascript">
	$(function(){
		d = new dTree('d');
		${moduleHtml}
		$("#showTree").html(d + "");
		$(".dTreeNode").hover(function(){
			$(this).css("background","#f5f5f5");
		},function(){
			$(this).css("background","fff");
		})
	});
	</script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100_tree margin_top10">
						<tr class="text_align_left my_table_top">
							<td>
								<span>
									 <a href="javascript: d.openAll();">展开所有</a> 
									 <a href="javascript: d.closeAll();">收起所有</a>
								</span>
							</td>
						</tr>
						<tr>
							<td>
								<div id="showTree" style="padding-top:10px; padding-left: 0px; padding-right: 0px;"></div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>