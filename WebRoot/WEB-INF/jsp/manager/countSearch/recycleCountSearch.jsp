<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					    <tr  class="text_align_left my_table_top">
					      <td >管理统计搜索词表</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span><a href="#">批量审核</a><a href="#">批量删除</a><a href="#">批量操作</a></span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="290">编号</td>
								    <td width="20"><input name="" type="checkbox" value=""></td>
								    <td width="80">创建时间</td>
								    <td width="80">属性名称</td>
								    <td width="80">属性名称</td>
								    <td>属性名称</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <tr>
								    <td>${c.id }</td>
								    <td><input name="" type="checkbox" value="${c.id }"></td>
								    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
								    <td>属性值</td>
								    <td>属性值</td>
								    <td>属性值</td>
								    <td><span><a href="#">删除</a><a href="#">编辑</a></span></td>
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