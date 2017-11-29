<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript"
	src="/template/wap/js/showCommonTraveller.js"></script>
<script type="text/javascript" src="/template/wap/js/commonTraveller.js"></script>
<body class="mobile">
	<div class="mobile-view">
		<div class="mobile-page">
			<jsp:include page="../common/title.jsp">
				<jsp:param value="常用旅客" name="title" />
			</jsp:include>
			<form action="" method="post" id="memberForm3"
				onsubmit="javascript:return false;">
				<div class="mobile-main">
					<div class="guestinfo">
						<div class="lst clearfix">
							<span>中文名</span> <label> <input type="hidden"
								value="${traveller.id }" id="travellerId" /> <input type="text"
								name="cName" id="cName" value="${traveller.chaName }"
								placeholder='姓名'>
								<p id="namePage"
									style="display: none; color:red; font-size:10px;"></p>
							</label>
						</div>
						<div class="lst clearfix">
							<span>英文名</span> <label style="width:200px;"> <input
								id="eNameLast" type="input" name="eNameLast"
								value="${fn:split(traveller.engName, ' ')[1] }"
								placeholder='姓LastName'>
								<p id="eNamePage1"
									style="display: none; color:red; font-size:10px;"></p>
							</label> <label style="width:210px;border-left:1px solid #ccc;">
								<input id="eNameFirst" type="input" name="eNameFirst"
								value="${fn:split(traveller.engName, ' ')[0] }"
								placeholder='名FirstName'>
								<p id="eNamePage2"
									style="display: none; color:red; font-size:10px;"></p>
							</label>
						</div>
						<div class="lst clearfix">
							<span>手机号<font>+86</font>
							</span><label> <input type="input" name="phone"
								value="${traveller.phone }" id="phone" datatype="m"
								placeholder='手机号'>
								<p class="Validform_checktip" style="display:inline;"></p>
							</label>
						</div>
					</div>

					<div class="guestinfo">
						<div id="idInfo">
							<s:if test="#cardList != null">
								<s:iterator value="cardList" id="list1">
									<div class="lst clearfix">
										<input type="hidden" value="${list1.id }" id="cardId" /> <span
											class="title"> <i class="icon-minus-circle"></i> <span>${list1.cardType }</span>
											<em class="icon-angle-right"></em>
										</span> <label> <s:if test="#list1.cardType == '身份证'">
												<input type="input" id="cardValues" name="${list1.cardType }"
													value="${list1.cardValue }" datatype="sf"
													errormsg="请输入正确的15位或18位的证件号码" placeholder="请输入证件号码">
												<p class="Validform_checktip" style="display:inline;"></p>
											</s:if> <s:else>
												<input type="input" id="cardValues" name="${list1.cardType }"
													value="${list1.cardValue }" datatype="*"
													placeholder="请输入证件号码">
												<p class="Validform_checktip" style="display:inline;"></p>
											</s:else>
										</label>
									</div>
								</s:iterator>
							</s:if>
						</div>
						<div class="lst clearfix">
							<span style='width:100%'> <i
								class='icon-plus-circle c_blue' style="display:none"></i> <select
								id="idselect"
								style="background-image: url('/template/wap/images/add_icon.jpg');
												background-repeat: no-repeat;
												padding-left: 35px; padding-top:0px; background-color:#fff; margin-top:25px; height:35px; width:100%;">
									<option value="">添加证件</option>
									<s:iterator var="t" value="#type">
										<s:set var="exist" value="false" />
										<s:iterator value="cardList" id="list">
											<s:if test="#t.name == #list.cardType">
												<s:set var="exist" value="true" />
											</s:if>
										</s:iterator>
										<s:if test="#exist == false">
											<option value="${t.name }" name="${t.id }">${t.name}</option>
										</s:if>
										<s:else>
											<option value="${t.name }" name="${t.id }"
												style="display: none">${t.name}</option>
										</s:else>
									</s:iterator>
							</select>
								<p id="errorPage"
									style="display: none; color:red; font-size:10px;"></p>
							</span>
						</div>
					</div>
					<div class="guestinfo">
						<h3>填写非身份证号码时需要选择性别和出生日期</h3>
						<div>
							<div class="lst clearfix">
								<span>性别</span><label><input type="input" id="gender"
									value="${traveller.gender } " placeholder='性别'
									datatype="/^[\u4e00-\u9fa5]$/">
									<p class="Validform_checktip" style="display:inline;"></p> </label>
							</div>
							<div class="lst clearfix">
								<span>生日</span><label><input type="date" id="birth"
									value="${traveller.birth }" placeholder='生日'
									datatype="/^\d{4}-\d{1,2}-\d{1,2}$/">
									<p class="Validform_checktip" style="display:inline;"></p> </label>
							</div>
						</div>
					</div>
					<!-- <div class="line clearfix mr20">
						<input type="submit" id="editCommon" class="button d_button bg-yello block" value="修改">
					</div> -->
					<div id="editCommon" class="tit custom_menu_btn "
						style="padding-top:0px;margin-bottom:40px;">修改</div>
					<a href="commonTraveller_show.action" id="manageLink"
						style="display: none;"></a>
					<!-- <div class="line clearfix mr20">
						<input type="submit" id="delCommon" class="button d_button bg-yello block" value="删除"
						javascript="return false;">
					</div> -->
					<div id="delCommon" class="tit custom_menu_btn "
						style="padding-top:0px;margin-bottom:40px;">删除</div>
					<a href="commonTraveller_show.action" id="manageLink"
						style="display: none;"></a>
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
