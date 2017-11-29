<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<% String bjdjPage_id = (String) request.getParameter("bjdjPage_id"); %>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">${news.title }</s:param>
				</s:include>
				<input type="hidden" value="${session.fromApp }" id="sessionApp"/>
				<input type="hidden" value="${parameters.fromApp }" id="parametersApp"/>
				<div class="mobile-main">
					<s:if test="!news.title.equals('服务流程')">
					<ul class="mr20">
						${news.content }
					</ul>
					</s:if>
					<s:else>
						${news.content }
					</s:else>
				</div>
				<div class="mobile-foot">
					<s:if test="#session.sessionMember != null">
		            		<s:if test="#session.sessionMember.memberType != null">
		            			<s:if test="#session.sessionMember.member == null">
		            				<a href="javascript:relation();" class='fz36'><i></i>立即购买</a>
		            			</s:if>
		            			<s:else>
		            				<s:if test="#parameters.bjdjPage_id != null">
				            			<a href="bjdjOrderTemplate_confirmPageAjax.action?versionFlag=site&bjdjPage_id=<%=bjdjPage_id %>" class='fz36'><i></i>立即购买</a>
				            		</s:if>
				            		<s:else>
				            			<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='fz36'><i></i>立即购买</a>
				            		</s:else>
				            	</s:else>
		            		</s:if>
		            		<s:else>
			            		<s:if test="#parameters.bjdjPage_id != null">
				            		<a href="bjdjOrderTemplate_confirmPageAjax.action?versionFlag=site&bjdjPage_id=<%=bjdjPage_id %>" class='fz36'><i></i>立即购买</a>
				            	</s:if>
				            	<s:else>
				            		<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='fz36'><i></i>立即购买</a>
				            	</s:else>
			            	</s:else>
		            	</s:if>
		            	<s:else>
		            		<s:if test="#parameters.bjdjPage_id != null">
				            	<a href="bjdjOrderTemplate_confirmPageAjax.action?versionFlag=site&bjdjPage_id=<%=bjdjPage_id %>" class='fz36'><i></i>立即购买</a>
				            </s:if>
				            <s:else>
				            	<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='fz36'><i></i>立即购买</a>
				            </s:else>
		            	</s:else>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		<div class="dialog" style="display:none;">
    <div class="box_dialog check_phone">
        <div class="c_content b_white">
            <div class="media media-x">
                <div class="media-body fz18">
                    <div class="order_form">
                        <div class="line clearfix mr40">
                            <div class="x12 mrtb20">
                                <input name='' id="phone" class="input d_input b_l_grey block" placeholder='请输入手机号码'>
                            </div>
                            <div class="x5"><button id="getCaptchaLogins" onclick="getvalidation();" class="button d_button bg-yello block getvalidation">获取验证码</button></div>
                            <div class="x1"></div>
                            <div class="x6"><input name='' id="code" class="input d_input b_l_grey " placeholder='输入验证码'></div>
                            <div class="x12 mrtb20"><button class="button d_button bg-yello block" onclick="validation();">确认关联</button></div>
                            <div class="x12 mrtb20"><button class="button d_button bg-yello block" onclick="closes();">取消</button></div>
                        </div>
                    </div>
                    <!-- <label>手机： <input type="text" class="input input-auto" name=""></label>
                    <div class="tit_tab">
                        <a href="javascript:;">获取验证码</a>
                        <input type="text" class="input" name=""></label>
                    </div>
                    <div class="tit_tab">
                        <a href="javascript:;">确认关联</a>
                    </div> -->
                    <hr>
                    <div class="text-center padding fz22 loginfo">
                       
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	<script type="text/javascript">
		function closes(){
		$.do_dialog.close();
		var $getCaptcha = $("#getCaptchaLogins");
			$getCaptcha.text("获取验证码");
			$("#phone").val("");
			$("#code").val("");
			$(".loginfo").text("");
	}
	function relation(){
		var sfromApp = $("#parametersApp").val();
	 	var pfromApp = $("#sessionApp").val();
	 	var fromApp = "" ;
	 	if(sfromApp != null){
	 		fromApp = sfromApp;
	 	}
	 	if(pfromApp != null){
	 		fromApp = pfromApp;
	 	}
		$.ajax({
			url:"memberQq_relation.action",
			type:"post",
			dataType:"json",
			data:{"fromApp":fromApp},
			success:function(data){
				if(data.status == "y"){
					$.do_dialog.open({'msg':$('.check_phone')});
				}else{
					JM.goUrl("/memberQq_relationInteface.action");
				}
			}
		});
	}
	function getvalidation(){
		var $getCaptcha = $("#getCaptchaLogins");
		var text = $getCaptcha.text();
		var phone = $("#phone").val();
		if(!phone || $.trim(phone) == ''){
			JM.alert('请输入你的手机号！', 2000);
		}else if(phone.length != 11){
			JM.alert('请输入正确的手机号码！', 2000);
		}else{
	    	$.post(
				'memberQq_relationMemberToSendCode.action',
				{
					phone : phone
				},
				function(data){
					
					
					var surplus = 60;
					var handler = setInterval(function(){
						
						if(surplus > 0){
							
							$getCaptcha.text(--surplus + '秒后重新获取...');
						}else{
							
							$getCaptcha.text(text);
							clearInterval(handler);
						}
					}, 1000);
				}
			);
	    }
	}

	function validation(){
		var code = $("#code").val();
		var phone = $("#phone").val();
		if(phone == null || JM.trim(phone) == ""){
			JM.alert("请输入手机号码", 2000);
		}else if(phone.length != 11){
			JM.alert("请输入正确的手机号码", 2000);
		}else
		if(code == null || JM.trim(code) == ""){
			JM.alert("请输入验证码", 2000);
		}else{
			$.post("memberQq_relationMember.action",{authCode:code,phone:phone},function(data){
				if(data.status == "y"){
	    			$(".loginfo").text(data.info+"正在跳转。。");
	    			var t1 = window.setTimeout(JM.goPage("bjdjOrderTemplate_confirmPage.action?versionFlag=site"),2000); 
					window.clearTimeout(t1);			
				}else{
					$(".loginfo").text(data.info);
				}
			},"json");
		}
	}
	</script>
	</body>
</html>