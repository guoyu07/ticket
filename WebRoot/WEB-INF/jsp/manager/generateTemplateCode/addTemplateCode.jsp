<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/generateTemplateCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>生成程序代码</span>
					</div>
					<form action="#" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag }" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right">
									保存路径：
								</td>
								<td>
									<select name="savePath" id="savePath" class="my_select" datatype="*">
										<option value="">
											请选择
										</option>
										<option value="manager_src_path">
											src_manager
										</option>
										<option value="common_src_path">
											src_common
										</option>
										<option value="template_src_path">
											src_template
										</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择保存路径</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									实体对象名称：
								</td>
								<td>
									<input id="entityName" name="entityName" class="my_input"  datatype="s4-30"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入实体对象名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									实体名称：
								</td>
								<td>
									<input value="测试用户" id="entityTitle" name="entityTitle"  datatype="s4-30"
										class="my_input" />
									<p class="Validform_checktip" style="display:inline;"> 请输入实体描述名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									实体ID类型：
								</td>
								<td>
									<input value="String" id="pojoIdType" name="pojoIdType" datatype="s3-20"
										class="my_input" />
									<p class="Validform_checktip" style="display:inline;"> 请输入实体对象主键的Java类型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									实体描述：
								</td>
								<td>
									<input class="my_input" id="entityDescript"  datatype="s3-20"
										name="entityDescript" />
									<p class="Validform_checktip" style="display:inline;"> 请输入实体描述内容</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right">
									实体属性定义：
								</td>
								<td>
									填写范例:String##name##@Column(length=50)##用户名称$$
									<br>
									<textarea class="my_textarea" id="entityAttribute"  datatype="*"
										name="entityAttribute">String##name##@Column(length=50)##用户名称$$String##loginId##@Column(length=20)##用户登陆ID</textarea>
									<p class="Validform_checktip"> 请输入实体的属性数据</p>	
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input name="" type="submit" value="提交" class="btn_submit">
						    <input name="" type="submit" value="取消" class="btn_remove margin_left20">
						    <a id="manageLink" href="/generateTemplateCode_add.action?versionFlag=${versionFlag }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>