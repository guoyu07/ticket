<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/commonTraveller.js"></script>
  	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="常用旅客" name="title"/>
                </jsp:include>
				<form action="" method="post" id="memberForm2" onsubmit="javascript:return false;">
					<div class="mobile-main">
						<div class="guestinfo">
							<div class="lst clearfix">
								<span>中文名</span>
								<label>
									<input type="text" name="cName" id="cName" placeholder='姓名'>
									<p id="namePage" style="display: none; color:red; font-size:10px;"></p>
								</label>
							</div>
							<div class="lst clearfix">
								<span>英文名</span>
								<label style="width:200px;">
									<input id="eNameLast" type="input" name="eNameLast"
									placeholder='姓LastName'>
									<p id="eNamePage1" style="display: none; color:red; font-size:10px;"></p>
								</label>
								<label style="width:210px;border-left:1px solid #ccc;">
								<input id="eNameFirst" type="input" name="eNameFirst" 
									placeholder='名FirstName'>
									<p id="eNamePage2" style="display: none; color:red; font-size:10px;"></p>
								</label>
							</div>
							<div class="lst clearfix">
								<span>手机号<font>+86</font>
								</span><label>
								<input type="input" name="phone" id="phone" 
								datatype="m" placeholder='手机号'>
								<p class="Validform_checktip" style="display:inline;"></p>
								</label>
							</div>
						</div>
		
						<div class="guestinfo">
							<div id="idInfo">
								<!-- ajax自动生成 -->
							</div>
							<div class="lst clearfix">
								<span style='width:100%'> <i class='icon-plus-circle c_blue' style="display:none;"></i> 
								<select id="idselect" style="background-image: url('/template/wap/images/add_icon.jpg');
background-repeat: no-repeat;
padding-left: 35px; padding-top:0px; background-color:#fff; margin-top:25px; height:35px;width:100%;">
										<option value="">添加证件</option>
										<s:iterator var="t" value="#type">
										<option value="${t.name }" name="${t.id }">${t.name }</option>
										</s:iterator>
								</select> 
									<p id="errorPage" style="display: none; color:red; font-size:10px;"></p>
								</span>
							</div>
						</div>
						<div class="guestinfo">
							<h3>填写非身份证号码时需要选择性别和出生日期</h3>
							<div>
								<div class="lst clearfix">
									<span>性别</span><label><input type="input"  id="gender"
										placeholder='性别' datatype="/^[\u4e00-\u9fa5]$/">
										<p class="Validform_checktip" style="display:inline;"></p>
									</label>
								</div>
								<div class="lst clearfix">
									<span>生日</span><label><input type="date" id="birth"
										placeholder='生日' datatype="/^\d{4}-\d{1,2}-\d{1,2}$/">
										<p class="Validform_checktip" style="display:inline;"></p>
									</label>
								</div>
							</div>
						</div>
						<div class="line clearfix mr20">
							<input type="submit" id="btncomm" class="button d_button bg-yello block" value="提交">
						</div>
						<a href="commonTraveller_show.action" id="manageLink" style="display: none;"></a>
					</div>
				</form>
				<div class="c_ft clearfix">
					<a href="#" class="ft_more"></a>
				</div>
					<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>
