<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/youZanGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增有赞出售中的商品</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											商品ID：
										</td>
										<td>
											<input id="num_iid" name="num_iid" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品ID</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品别名：
										</td>
										<td>
											<input id="alias" name="alias" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品别名</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品详情url：
										</td>
										<td>
											<input id="detail_url" name="detail_url" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品详情url</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品价格：
										</td>
										<td>
											<input id="price" name="price" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品价格</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品销量：
										</td>
										<td>
											<input id="sold_num" name="sold_num" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品销量</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											上架状态：
										</td>
										<td>
											<input id="listing" name="listing" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写上架状态</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>