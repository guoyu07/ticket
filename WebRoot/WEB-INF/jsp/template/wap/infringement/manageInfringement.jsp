<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".back").click(function(){
			JM.goUrl("/airport_loginSuccessIndex.action");
		});
		$("#time").change(function(){
			$("#unitName option:eq(0)").attr("selected","selected");
			$("#illegalName option:eq(0)").attr("selected","selected");
		});
		
		$("#unitName").change(function(){
			$("#time option:eq(0)").attr("selected","selected");
			$("#illegalName option:eq(0)").attr("selected","selected");
		});
		
		$("#illegalName").change(function(){
			$("#time option:eq(0)").attr("selected","selected");
			$("#unitName option:eq(0)").attr("selected","selected");
		});
		
		$("#time").change(findByTimeAndUnitNameAndIllegalName);
		$("#unitName").change(findByTimeAndUnitNameAndIllegalName);
		$("#illegalName").change(findByTimeAndUnitNameAndIllegalName);
	});
	function look(dom){
		var textarea = $(dom);
		var id = textarea.next().val();
		var url = $(".idLink").attr("href");
		JM.goUrl(url + id);
	}
	function findByTimeAndUnitNameAndIllegalName(){
		var unitName = $("#unitName").val();
		var time = $("#time").val();
		var illegalName = $("#illegalName").val();
		$.ajax({
			url:"infringement_findByTime.action",
			type:"post",
			dataType:"json",
			data:{"time":time,"unitName":unitName,"illegalName":illegalName},
			success:function(data){
				if(data.status == "y"){
					$("#allInfringement").empty();
					var list = data.info;
					var length = list.length;
					var html = "";
					for(var i=0;i<list.length;i++){
						html += '<li name="" class="d_input block margin-large-top" onclick="look(this);">时间：'+ list[i].time +'；检查人：'+ list[i].inspectName +'；单位名称：'+ list[i].unitName +'；违规人员姓名：'+ list[i].illegalName +'；违规人员证件号码：'+ list[i].illegalCard +'；</li><input type="hidden" value="'+ list[i].id +'"/>';
					}
					$("#allInfringement").html(html);
				}
			},
			error:function(xmlhttprequest, textstatus, errorthrown){
				JM.alert(textstatus);
			}
		});
	}
</script>
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
						<div class="line-big">
							<div class="x4">
								<select id="time"name="time" class="d_input block">
									<option value="">时间</option>
									<s:if test="#times != null">
									<s:iterator value="#times" var="c">
										<option>${c }</option>
									</s:iterator>
									</s:if>
								</select>
							</div>
							<div class="x4">
								<select id="unitName"name="unitName" class="d_input block">
									<option value="">单位名称</option>
									<s:if test="#unitNames != null">
									<s:iterator value="#unitNames" var="c">
										<option>${c }</option>
									</s:iterator>
									</s:if>
								</select>
							</div>
							<div class="x4">
								<select id="illegalName"name="illegalName" class="d_input block">
									<option value="">违规人员姓名</option>
									<s:if test="#illegalNames != null">
									<s:iterator value="#illegalNames" var="c">
										<option>${c }</option>
									</s:iterator>
									</s:if>
								</select>
							</div>
						</div>
						<s:if test="pageModule != null && pageModule.totalCount > 0">
							<ul id="allInfringement">
							<s:iterator value="pageModule.pageList" var="c" status="st">
								<li name="" class="d_input block margin-large-top" disabled="disabled" onclick="look(this);" ><a style="border:1px solid black;">时间：${c.time }；检查人：${c.inspectName }；单位名称：${c.unitName }；违规人员姓名：${c.illegalName }；违规人员证件号码：${c.illegalCard }；</a></li>
								<input type="hidden" value="${c.id }"/>
							</s:iterator>
							</ul>
						</s:if>
						<s:else>
							${noDataMessage }
						</s:else>
						<a href="/infringement_look.action?id=" class="idLink" style="display: none;"></a>
						<div class="line clearfix mr40">
						<button type="" class="button d_button bg-yello block back" style="margin: 20px auto;">返回首页</button>
						</div>
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