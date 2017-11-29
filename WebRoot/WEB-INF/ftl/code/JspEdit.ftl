<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/${entityNameFirstLowerCase}.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑${entityTitle}</span>
					</div>
					<form action="/${actionName }_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag }" />
						<input type="hidden" name="id" id="id" value="${id }" />
						<table class="my_table100 margin_top10">
							<#if attrList ??>
								<#list attrList as attr>
									<tr>
										<td class="text_align_right" width="150">
											${attr.attrComment}：
										</td>
										<td>
											<input id="${attr.attrName}" name="${attr.attrName}" class="my_input" datatype="*"
											       value="${dollar}${leftBrackets}${entityNameFirstLowerCase}.${attr.attrName}${rightBrackets}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写${attr.attrComment}</p>       
										</td>
									</tr>
								</#list>
							</#if>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="editLink" href="/${actionName }_edit.action?versionFlag=${versionFlag }"></a>
						    <a id="manageLink" href="/${actionName }_manage.action?versionFlag=${versionFlag }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>