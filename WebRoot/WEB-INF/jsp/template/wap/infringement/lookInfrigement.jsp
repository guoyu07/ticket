<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>
<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
					<div class="mobile-top">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i> </a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="mobile-top" style="display: none;">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i> </a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40">
						<h1 class='text-center c_black fz24'>现场处理表</h1>
						<h5 class='text-right c_black fz18 padding-big-bottom'>NO.0${infringement.numberId }号</h5>
						<table class="table table-bordered c_black work_table">
							<tr>
								<td class="fz18" width="120">时间</td>
								<td class="fz18"><input type="text" value="${infringement.time }"
									style="width:100%;height:100%;border:0px;">
								</td>
								<td class="fz18">检查人</td>
								<td class="fz18"><input type="text" value="${infringement.inspectName }"
									style="width:100%;height:100%;border:0px;">
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">单位名称</td>
								<td colspan="3" class="fz18"><input type="text" value="${infringement.unitName }"
									style="width:100%;height:100%;border:0px;">
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">违规人员名称</td>
								<td class="fz18"><input type="text" value="${infringement.illegalName }"
									style="width:100%;height:100%;border:0px;">
								</td>
								<td class="fz18">违规人员证件号</td>
								<td class="fz18"><input type="text" value="${infringement.illegalCard }"
									style="width:100%;height:100%;border:0px;">
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">违规事项</td>
								<td colspan="3" class="fz18"><textarea name=""
										style="width:100%;height:100px;border:0px;"></textarea>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">涉及规章制度</td>
								<td colspan="3" class="fz18"><textarea name="" 
										style="width:100%;height:100px;border:0px;">${infringement.rules }</textarea>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">整改意见</td>
								<td colspan="3" class="fz18"><textarea name=""
										style="width:100%;height:100px;border:0px;">${infringement.rectificationOpinion }</textarea>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">现场照片</td>
								<td colspan="3" class="fz18">
									<img alt="" src="${infringement.scenePhoto }">
								</td>
							</tr>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/infringement_manage.action" class="b_yello c_l_grey">返回列表</a>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</ticket:cache>
</html>