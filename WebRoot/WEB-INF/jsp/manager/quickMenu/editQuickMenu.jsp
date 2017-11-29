<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/manager/js/quickMenu.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑快捷菜单</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									菜单类型：
								</td>
								<td>
									<input id="quickMenuType_id" name="quickMenuType_id" class="my_input easyui-combobox" datatype="*"
									       value="${quickMenu.quickMenuType_id}"
									       data-options="url:'systemDictionary_querySubByParentValue.action?value=quick_menu_type',valueField:'id',textField:'name'"/>
									<p class="Validform_checktip" style="display:inline;"> 请选择菜单分类</p>    
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${quickMenu.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写菜单名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*" style="width: 70%"
									       value="${quickMenu.url}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写菜单链接</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单图标：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${quickMenu.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<s:set name="fId" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1, #annex.annexPath.indexOf('.'))" />
												<s:set name="fName" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1)" />
												<s:set name="fDirectory" value="#annex.annexPath.substring(#annex.annexPath.indexOf('/')+1, #annex.annexPath.lastIndexOf('/'))" />
												<span id="span${fId }">
												<span style="position: absolute; z-index: 10; float: right;">
													<a href="#" onClick="delImgUpdate('${fId}','${fName }');return false;">
														<img src="/common/jQueryUpload/cancel.png" border="0" />
													</a>
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}1${UPLOAD_SEPARATOR_VALUE}"/>
												</span>
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
												</span>
											</s:iterator>
										</s:if>
						       		</div>
						    		<input type="file" id="icon" name="icon" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否默认显示：
								</td>
								<td>
									<s:iterator value="{'a','b','c','d','e','f','g','h','i','j','k','l','m'}" id='number'>
									
										<input type="checkbox" 
											${fn:contains(quickMenu.defaultShowPosition,number) ? 'check="true"' : ''}
											name="defaultShowPosition" value="${number }">
										<s:if test="#number == 'a'">
											左侧国内出发
										</s:if>
										<s:elseif test="#number == 'b'">
											左侧国际出发
										</s:elseif>
										<s:elseif test="#number == 'c'">
											左侧国内中转
										</s:elseif>
										<s:elseif test="#number == 'd'">
											左侧国际中转
										</s:elseif>
										<s:elseif test="#number == 'e'">
											左侧国内到达
										</s:elseif>
										<s:elseif test="#number == 'f'">
											左侧国际到达
										</s:elseif>
										<s:elseif test="#number == 'g'">
											个人中心
										</s:elseif>
										<s:elseif test="#number == 'h'">
											服务菜单国内出发
										</s:elseif>
										<s:elseif test="#number == 'i'">
											服务菜单国际出发
										</s:elseif>
										<s:elseif test="#number == 'j'">
											服务菜单国内中转
										</s:elseif>
										<s:elseif test="#number == 'k'">
											服务菜单国际中转
										</s:elseif>
										<s:elseif test="#number == 'l'">
											服务菜单国内到达
										</s:elseif>
										<s:elseif test="#number == 'm'">
											服务菜单国际到达
										</s:elseif>
									</s:iterator>
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