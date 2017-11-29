<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/infringement.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑内部员工罚单</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											时间：
										</td>
										<td>
											<input id="time" name="time" class="my_input" datatype="*"
											       value="${infringement.time}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											检查人：
										</td>
										<td>
											<input id="inspectName" name="inspectName" class="my_input" datatype="*"
											       value="${infringement.inspectName}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写检查人</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											单位名称：
										</td>
										<td>
											<input id="unitName" name="unitName" class="my_input" datatype="*"
											       value="${infringement.unitName}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写单位名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											违规事项：
										</td>
										<td>
											<input id="illegalMatter" name="illegalMatter" class="my_input" datatype="*"
											       value="${infringement.illegalMatter}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写违规事项</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											涉及规章制度：
										</td>
										<td>
											<input id="rules" name="rules" class="my_input" datatype="*"
											       value="${infringement.rules}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写涉及规章制度</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											整改意见：
										</td>
										<td>
											<input id="rectificationOpinion" name="rectificationOpinion" class="my_input" datatype="*"
											       value="${infringement.rectificationOpinion}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写整改意见</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											现场照片：
										</td>
										<td>
											<input id="scenePhoto" name="scenePhoto" class="my_input" datatype="*"
											       value="${infringement.scenePhoto}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写现场照片</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											罚单编号：
										</td>
										<td>
											<input id="numberId" name="numberId" class="my_input" datatype="*"
											       value="${infringement.numberId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写罚单编号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											违规人姓名：
										</td>
										<td>
											<input id="illegalName" name="illegalName" class="my_input" datatype="*"
											       value="${infringement.illegalName}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写违规人姓名</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											违规人证件号：
										</td>
										<td>
											<input id="illegalCard" name="illegalCard" class="my_input" datatype="*"
											       value="${infringement.illegalCard}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写违规人证件号</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
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