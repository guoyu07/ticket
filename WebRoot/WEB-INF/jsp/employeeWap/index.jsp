<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						销售后台页面
					</div>
				</div>
				<div class="mobile-main">
					<%@ include file="top.jsp"%>
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table">
							<tr>
								<td class="fz18">
									商品名称
								</td>
								<td class="fz18">
									计划任务
								</td>
								<td class="fz18">
									计划完成单数
								</td>
								<td class="fz18">
									实际完成
								</td>
								<td class="fz18">
									实际完成单数
								</td>
								<td class="fz18">
									退单数
								</td>
								<td class="fz18">
									退单金额
								</td>
							</tr>
							<tr>
								<td class="fz18">
									便捷等级
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
							</tr>
							<tr>
								<td class="fz18">
									便捷等级
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
								<td class="fz18">
									0
								</td>
							</tr>
						</table>
					</div>
					<div class="mr40">
						<a href="javascript:;" id="addnotebook"><img
							src="/template/wap/images/my_add_icon.png">
						</a>
					</div>
					<div class="mr40">
						<hr>
						<div class="panel no-border">

							<div class="mr20 text-left" id='notebook_list'>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<div class="notebook_dialog box_dialog">
				<form id="myTextForm" name="myTextForm">
				<div class="text-center b_white border10 mr40 padding-big">
					<input type="text" name="title" id="title" class='input d_input'
						placeholder='记事标题'>
					<br>
					<textarea name="content" id="content" class="input d_input" style='height:300px;'
						placeholder='记事内容'></textarea>
					<input type="button" id="myText_add_btn" value="添加" class='input d_input/'>
				</div>
				</form>
			</div>
			<div class="notebook_info_dialog box_dialog">
				
			</div>
		</div>
	<script type="text/javascript" src="/template/employeeInfoWap/js/index.js"></script>
	</body>
</html>