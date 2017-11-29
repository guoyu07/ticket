<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/specialPersonWithQuickMenu.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增服务对应菜单</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									服务人员类型：
								</td>
								<td>
									<select id="personType" name="personType" class="my_select" datatype="*">
										<option value="1">老人（>70）</option>
										<option value="2">儿童（2-12））</option>
										<option value="3">婴儿（< 2）</option>
										<option value="4">担架旅客</option>
										<option value="5">轮椅旅客</option>
										<option value="6">孕妇</option>
										<%--<option value="">请选择人员类型</option>
										<ticket:systemCommon type="systemDictionayList" value="serviePersonType"/>
										<s:if test="#request.systemDictionayList != null">
											<s:itrator id="systemDic" value="#request.systemDictionayList">
												<option value="${systemDic.value }">${systemDic.name }</option>
											</s:itrator>
										</s:if>
									--%></select>
									<p class="Validform_checktip" style="display:inline;"> 请选择服务人员类型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									快捷菜单id：
								</td>
								<td>
									<select id="quickMenu_id" name="quickMenu_id" class="my_select" datatype="*">
										<option value="">请选择快捷菜单</option>
										<ticket:systemCommon type="quickMenuList"/>
										<s:if test="#request.quickMenuList != null">
											<s:iterator id="quickMenu" value="#request.quickMenuList">
												<option value="${quickMenu.id }">${quickMenu.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择快捷菜单</p>
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