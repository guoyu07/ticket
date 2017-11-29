<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<script type="text/javascript" src="/template/pc/js/pptBox.js"></script>
		<title>商家展示 - 云南省昆明市长水机场</title>
        <style type="text/css">
        #tbc10_01 dd{ padding:0px !important;}
		.shop_down dd img{ margin:7px;}
		select {
		    
		  
		    appearance:none;
		    -moz-appearance:none;
		    -webkit-appearance:none;
		
		  
		
		    background: url(http://www.kmcsia.com/template/pc/images/down3.gif) no-repeat scroll right center transparent;
		
		}

		select::-ms-expand { display: none; }
        </style>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_18"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">商家展示</a>
		</div place w980 mt30>

		<div class="w980 mt30" style="height: 418px;">
			<div class="shop_L bhh">
				<ul id="tb10_">
					<ticket:common type="businessTopTypeList" />
					<s:iterator id="b" value="#request.businessTopTypeList" status="st">
					<li attrValue="${b.id}" class="businessTopType <s:if test="#b.id == businessInfoType_id">hovertab_10</s:if><s:else>normaltab_10</s:else>"  
					<s:if test="#st.last">style="border-right: none;"</s:if>>
						${b.name}
					</li>
					</s:iterator>
				</ul tb10_>

				<h2 class="dis" id="tbc10_01">
					<form id="businessInfoForm" action="/airportPc_businessDisplay.action" method="post">
					<input type="hidden" id="businessInfoType_id" name="businessInfoType_id" value="${businessInfoType_id}"/>
					<p>
						<input name="keyword" type="text" placeholder="输入商户名称" value="${keyword}" />
						<a href="javascript:;" onclick="$('#businessInfoForm').submit();"><img src="/template/pc/images/shop_search_button.gif"
								align="absmiddle" />
						</a>
					</p>
					<dl>
						<dd>
							
							<ticket:systemCommon type="systemDictionayList" value="businessInfo_lc"/>
							<select name="lc" >
								<option value="">请选择楼层</option>
								<s:iterator id="d" value="#request.systemDictionayList">
								<option <s:if test="#d.id == lc">selected</s:if> value="${d.id}">${d.name}</option>
								</s:iterator>
							</select>
						</dd>
						<dd>
							
							<ticket:systemCommon type="systemDictionayList" value="businessInfo_wz"/>
							<select name="wz">
								<option value="">请选择位置</option>
								<s:iterator id="d" value="#request.systemDictionayList">
								<option <s:if test="#d.id == wz">selected</s:if> value="${d.id}">${d.name}</option>
								</s:iterator>
							</select>
						</dd>
						<dd>
							
							<select name="businessInfoType_id2" id="businessInfoType_id2">
								<option value="">请选择分类</option>
								<s:iterator id="b" value="businessTypes">
									<option <s:if test="#b.id == businessInfoType_id2">selected</s:if> value="${b.id}">${b.name}</option>
								</s:iterator>
							</select>
						</dd>
						<dd style="border: none;">
							<small><a href="/airportPc_businessDisplay.action">查看所有商户</a>
							</small><a href="javascript:;" onclick="$('#businessInfoForm').submit();"><img src="/template/pc/images/search_button.gif" />
							</a>
						</dd>
					</dl>
					</form>
				</h2>
			</div shop_L bhh>

			<div class="shop_R hh">
				<ticket:common type="advertListNew" value="PC机场商业图片" size="4"/>
				<script>
					
						var box =new PPTBox();
						box.width = 415; //宽度
						box.height = 418;//高度
						box.autoplayer = 3;//自动播放间隔时间
						<s:if test="#request.advertListNew != null && #request.advertListNew.size > 0">
							<s:iterator id="advert" value="#request.advertListNew">
								<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
								box.add({"url":"<%=request.getScheme() %>://${image_server_url}${annex.annexPath}","href":"${advert.url}","title":"${advert.name}"})
							</s:iterator>
						</s:if>
						<s:else>
							box.add({"url":"/template/pc/images/img2.jpg","href":"javascript:;","title":"悬浮提示标题1"})
						</s:else>
						box.show();

				</script>
			</div shop_R hh>
		</div w980 mt30>

		<div class="shop_down w980 mt30" style="width:1030px;">
			<dl>
				<s:iterator id="b" value="businessInfos">
				<dd class="bhh">
					<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${b.id}" annexType="1" size="1" />
					<a href="airport/businessInfoView/${b.id}.html"><img alt="${b.name}" src="${queryAnnexList[0].annexPath}"/>
					</a>
				</dd>
				</s:iterator>
			</dl>
		</div shop_down w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
		<script>
			$(function (){
				
				$(".businessTopType").on("click",function (){
					if($(this).hasClass("hovertab_10")){
						$(this).addClass("normaltab_10");
						$(this).removeClass("hovertab_10");
						$("#businessInfoType_id").val("");
					}else{
						$(".businessTopType").each(function (){
							if($(this).hasClass("hovertab_10")){
								$(this).addClass("normaltab_10");
								$(this).removeClass("hovertab_10");
							}
						});
						$(this).addClass("hovertab_10");
						$(this).removeClass("normaltab_10");
						var id = $(this).attr("attrValue");
						$("#businessInfoType_id").val(id);
					}
					window.location.href='/airportPc_businessDisplay.action?businessInfoType_id='+$("#businessInfoType_id").val();
				});
			})
		</script>
		
	</body>
</html>
