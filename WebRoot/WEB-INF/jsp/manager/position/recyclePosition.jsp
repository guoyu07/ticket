<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/position.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理员工岗位职责</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">岗位名称</td>
								    <td width="80">岗位职责</td>
								    <td width="80">岗位描述</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <tr>
								    <td>${c.name}</td>
								    <td>${c.duty}</td>
								    <td>${c.remark}</td>
								    <td><span>
								    <a href="javascript:;" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									<a href="javascript:;" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
								    </span></td>
								  </tr>
								</table>
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