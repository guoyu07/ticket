<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
	<script type="text/javascript" src="/common/FileUpload.js"></script>
	<!-- jquery image cut -->
	<link rel="stylesheet" href="/common/jquery-image-cut/cropper.min.css" />
	<script type="text/javascript" src="/common/jquery-image-cut/cropper.min.js"></script>
	<script type="text/javascript" src="/template/wap/js/info/updatePersonInfo.js"></script>
	<%-- <ticket:cache group="资讯中心"> --%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="头像设置" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar third_label_bar">
						<a href="#" class="selected border10">头像设置</a>
						<a href="/airportm_baseInfo.action" class='border10'>基本信息</a>
						<a href="/airportm_detailInfo.action" class='border10'>详细信息</a>
						<a href="/airportm_companyInfo.action" class='border10'>公司信息</a>
						<a href="/airportm_educationInfo.action" class='border10'>教育信息</a>
						<a href="/airportm_otherInfo.action" class='border10'>其他信息</a>
					</div>
					<form action="airportm_updateImage.action" method="post" id="memberForm">
						<a href="/airportm_personalSetting.action?direct=true" id="manageLink" style="display: none;"></a>
						<div class="mr40">
							<hr>
							<h1 class='padding-large-bottom'>
								方法一：选择手机照片，上传编辑自己的头像
							</h1>
							<div class="clearfix fz18">
								<s:if test="sessionMember.images != null">
									<img src="${sessionMember.images }" class="icon-plus b_l_grey txt-large inline_block text-center" />
								</s:if> 
								<s:else>
									<img src="/template/wap/images/no_avatar.png" class="icon-plus b_l_grey txt-large inline_block text-center" />
								</s:else>
								<input type="hidden" name="images"/>
								<a id="uploadImage" href="#" class="float"><img src="/template/wap/images/choose_pic.png" height="61" width="196">
								</a>
								<span class='padding-big-top padding-large-left inline_block'>支持jpg、jpeg、gif、png、bmp格式</span>
							</div>
						</div>
						<div class="mr40">
							<h1 class='padding-large-bottom'>
								方法二：选择推荐头像，快速上传优质头像
							</h1>
							<div class="upload_ls clearfix padding-big-bottom">
								<s:if test="#logos != null">
									<s:iterator value="#logos" var="c">
										<%-- <input type="radio" value="${c.logoUrl }" name="logoUrls" onclick="selected(this)"/> --%>
										<img src="${c.logoUrl }"  height="100" width="100" onclick="selected(this)"/>
									</s:iterator>
								</s:if>
								<s:else>
									<a href="#" class='float-left padding-small'><img src="/template/wap/images/pic/87.jpg" height="100" width="100"></a>
								</s:else>
							</div>
						</div>
						<div class="mr40">
							<button class="button d_button b_blue c_white block">
								保存
							</button>
						</div>
					</form>
				</div>

				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
	</body>
	<%-- </ticket:cache> --%>
</html>