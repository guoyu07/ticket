<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/convientBording/index.js"></script>
	<body class="mobile quick quick-home">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <s:if test="#session.fromApp == null && #parameters.fromApp == null">
					<div class="mobile-top">
						<div class="header">
							<s:if test="#parameters.direct != null">
								<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
							</s:if>
							<s:else>
								<a href="javascript:;" class='FL'><i class="icon-angle-left"></i></a>
							</s:else>
							<span class="FR">
								<i class="memberFavorite" title="便捷登机" url="bjdj.action">收藏</i>
								<i class="icon-sign-out showdiv_reg">分享</i>
							</span>
						</div>
					</div>
				</s:if>
				
				<!--自定义登陆弹出框-->
					<div id="popupbgLayer" class="popupbg"></div>
					<iframe id='popupIframeLayer' class='popupIframe' frameborder='0' ></iframe>
					
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32 popupdiv" id="popupLayer_reg">
						<div class="popup_title"><span>分享到</span>
							<div id="closeDiv_reg" class="popupclose_btn"><a href="#">&times;</a></div>
						</div>	
						<div class="popup_show">
							<a class="jiathis_button_tsina">新浪微博</a>
							<a class="jiathis_button_weixin">微信</a>
							<a class="jiathis_button_qzone">QQ空间</a>
							<a class="jiathis_button_cqq">QQ好友</a>
							<a class="jiathis_button_email">邮件</a>
							<a class="jiathis_button_tieba">百度贴吧</a>
							<a class="jiathis_button_baidu">百度空间</a>
							<a class="jiathis_button_meilishuo">美丽说</a>
							<a class="jiathis_button_copy">复制网址</a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
							<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
						</div>
					</div>
		        <div class="mobile-main" style='padding:0px;'>
		            <div class="kv_pics quick_home_kv">
		                <div class="swiper-container">
		                    <ul class="swiper-wrapper">
		                        <ticket:common type="advertListNew" value="${advertType }" size="10"/>
								<s:if test="#request.advertListNew != null">
									<s:iterator id="advert" value="#request.advertListNew">
										<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
										<li class="swiper-slide">
				                            <a href=""><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}"></a>
				                        </li>
									</s:iterator>
								</s:if>
		                    </ul>
		                   <%--  <p class="fz24"><a href="/airport/1448619404241.ticket"><i></i>休息厅实况 :  已成功预约（${wait }）席位，剩余（${surplus }）席位</a></p> --%>
		                </div>
		                <a href="javascript:;" class='kv_prev icon-angle-left'></a>
		                <a href="javascript:;" class='kv_next icon-angle-right'></a>
		
		                <dl class="hot_news clearfix ">
		                    <dt class='fz26 line'>
		                        <div class="x5 text-left">
		                            ${package1.name } [ ${package1.price}元 ]
		                        </div>
		                        <div class="x7 text-center">
		                            <div class='fz18 c_white'>
		                            	<s:iterator value="bjdj_items" var="item" status="status">
											[${item.name }]
											<s:if test="#status.count % (#bjdj_items.size()/2+1) == 0">
												<br/><br/>
											</s:if>
										</s:iterator>
		                            </div> 
		                        </div>
		                    </dt>
		                </dl>
		            </div>
		            <div class="quick_icons clearfix">
		                <a href="/airport/${serviceProfile }.ticket" class="fz30"><i></i><p>便捷登机介绍</p></a>
		                <a href="/airport/${serviceFlow }.ticket" class="fz30"><i></i><p>服务流程</p></a>
		                <a href="/airport/${useSerms }.ticket" class="fz30"><i></i><p>使用条款</p></a>
		                <a href="tel:${service_phone}" class="fz30"><i></i><p>咨询电话</p></a>
		                <a href="bjdjCommentTemplate_listPage.action" class="fz30"><i></i><p>查看评价</p></a>
		                
		                <a href="<ticket:common type="positionUrl" value="${infoPositionAlias }" />" class="fz30"><i></i><p>导航</p></a>
                        <input type="hidden" value="<ticket:common type="positionUrl" value="${infoPositionAlias }" />"/>
		            </div>
		        </div>
		        <div class="mobile-foot">
		            <div class="home_btn">
		            	<s:if test="#session.sessionMember != null">
		            		<s:if test="#session.sessionMember.memberType != null">
		            			<s:if test="#session.sessionMember.member == null">
		            				<a href="javascript:relation();" class='h_buy'><i></i>立即购买</a>
		            			</s:if>
		            			<s:else>
				            		<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='h_buy'><i></i>立即购买</a>
				            	</s:else>
		            		</s:if>
		            		<s:else>
			            		<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='h_buy'><i></i>立即购买</a>
			            	</s:else>
		            	</s:if>
		            	<s:else>
		            		<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class='h_buy'><i></i>立即购买</a>
		            	</s:else>
		            </div>
		        </div>
		        
		    </div>
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
		$.do_dialog.open({'msg':$('.check_phone')});
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
			$.post("memberQq_relationMember.action",{authCode:JM.trim(code),phone:phone},function(data){
				if(data.status == "y"){
	    			$(".loginfo").text(data.info+"正在跳转。。");
	    			var t1 = window.setTimeout(JM.goPage("bjdjOrderTemplate_confirmPage.action?versionFlag=site"),2000); 
				}else{
					$(".loginfo").text(data.info);
				}
			},"json");
		}
	}
	</script>
	</body>
</html>
