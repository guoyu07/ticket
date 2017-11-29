<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<%-- <ticket:cache group="资讯中心"> --%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="详细信息" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar  third_label_bar">
						<a href="/airportm_updatePersonalInfo.action" class="border10">头像设置</a>
						<a href="/airportm_baseInfo.action" class="border10">基本信息</a>
						<a href="#" class='selected border10'>详细信息</a>
						<a href="/airportm_companyInfo.action" class='border10'>公司信息</a>
						<a href="/airportm_educationInfo.action" class='border10'>教育信息</a>
						<a href="/airportm_otherInfo.action" class='border10'>其他信息</a>
					</div>

					<div class="mr40">
		                <dl class='fz20'>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom bool'>
		                        <label class='fz18'><em>血型</em><span class='float-right c_l_grey'><font name="bloodType">${memberDetailInfo.bloodType }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom star'>
		                        <label class='fz18'><em>星座</em><span class='float-right c_l_grey'><font name="constellation">${memberDetailInfo.constellation }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom animal'>
		                        <label class='fz18'><em>属相</em><span class='float-right c_l_grey'><font name="zodiac">${memberDetailInfo.zodiac }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom marry'>
		                        <label class='fz18'><em>婚配</em><span class='float-right c_l_grey'><font name="marriage">
										<s:if test="memberDetailInfo.marriage == 1">已婚</s:if>
										<s:elseif test="memberDetailInfo.marriage == 0">未婚</s:elseif>
										<s:else>未设置</s:else>
									</font> <i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom height'>
		                        <label class='fz18'><em>身高</em><span class='float-right c_l_grey'><font name="height"><fmt:formatNumber value="${memberDetailInfo.height }" pattern="#0" /></font>cm <i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom weight'>
		                        <label class='fz18'><em>体重</em><span class='float-right c_l_grey'><font name="weight">${memberDetailInfo.weight }</font>kg<i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>邮箱</em><span class='float-right c_l_grey'><font name="email">${sessionMember.email }</font> <i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom like'>
		                        <label class='fz18'><em>兴趣点</em><span class='float-right c_l_grey'><font name="hobby">${memberDetailInfo.hobby }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>车牌号</em><span class='float-right c_l_grey'><font name="plateNumber">${memberDetailInfo.plateNumber }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>个性签名</em><span class='float-right c_l_grey'><font name="personalitySignature">${memberDetailInfo.personalitySignature }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                </dl>
		                <button class="button d_button b_blue c_white block" id="updateDetailInfo">保存</button>
		            </div>

				</div>

				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display:none;">
		    <div class="edit_dialog box_dialog">
		        <div class="text-center b_white border10 mr40 padding-big">
		            <input type="text" name="" value="" class='input d_input' placeholder=''>
		            <br>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		    <div class="edit_marry_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div class="line two_select padding-big-top padding-large-bottom">
		                <a href="javascript:;" class='x5 padding fz20'>已婚</a>
		                <a href="javascript:;" class='x5 x2-move padding fz20'>未婚</a>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		    <div class="edit_bool_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div class="line two_select padding-big-top padding-large-bottom">
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>A</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>B</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>O</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>AB</a>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		    <div class="edit_star_dialog box_dialog">
		         <div class="text-center b_white border10 mr40 padding-big">
		            <div style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>白羊座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>金牛座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>双子座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>巨蟹座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>狮子座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>处女座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>天秤座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>天蝎座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>射手座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>摩羯座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>水瓶座</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>双鱼座</a>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_animal_dialog box_dialog">
		        <div class="text-center b_white border10 mr40 padding-big">
		            <div style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>鼠</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>牛</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>虎</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>兔</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>龙</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>蛇</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>马</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>羊</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>猴</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>鸡</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>狗</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>猪</a>
		           		</div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_height_dialog box_dialog">
		        <div class="text-center b_white border10 mr40 padding-big">
		            <div style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                    <c:forEach var="i" begin="120" end="240" step="1">
								<a href="javascript:;" class='x12 padding fz20 margin-bottom'>${i}</a>
							</c:forEach>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_weight_dialog box_dialog">
		        <div class="text-center b_white border10 mr40 padding-big">
		            <div style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                    <c:forEach var="i" begin="20" end="100" step="1">
								<a href="javascript:;" class='x12 padding fz20 margin-bottom'>${i}</a>
							</c:forEach>
								<a href="javascript:;" class='x12 padding fz20 margin-bottom'>100以上</a>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_like_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div class="line two_select padding-big-top padding-large-bottom">
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>阅读</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>运动</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>饮食</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>旅游</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>音乐</a>
		                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>其他</a>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		</div>
		<script type="text/javascript">
		    $('dl dd').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_dialog'),'initBefore':function(){
		            $('.edit_dialog input[type="text"]').val('');
		            $('.edit_dialog input[type="text"]').attr('placeholder','请输入您的'+_this.find('em').html());
		        },initAfter:function(rs){
		            $('.edit_dialog button').on('click',function(){
		                var $value = $('.edit_dialog input[type="text"]').val();
		                if($value){
		                    _this.find('font').html($value);
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.marry').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_marry_dialog'),'initBefore':function(){
		            $('.edit_marry_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_marry_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_marry_dialog button').on('click',function(){
		                if($('.edit_marry_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_marry_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.bool').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_bool_dialog'),'initBefore':function(){
		            $('.edit_bool_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_bool_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_bool_dialog button').on('click',function(){
		                if($('.edit_bool_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_bool_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.star').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_star_dialog'),'initBefore':function(){
		            $('.edit_star_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_star_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_star_dialog button').on('click',function(){
		                if($('.edit_star_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_star_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.animal').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_animal_dialog'),'initBefore':function(){
		            $('.edit_animal_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_animal_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_animal_dialog button').on('click',function(){
		                if($('.edit_animal_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_animal_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.height').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_height_dialog'),'initBefore':function(){
		            $('.edit_height_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_height_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_height_dialog button').on('click',function(){
		                if($('.edit_height_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_height_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.weight').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_weight_dialog'),'initBefore':function(){
		            $('.edit_weight_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_weight_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_weight_dialog button').on('click',function(){
		                if($('.edit_weight_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_weight_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.like').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_like_dialog'),'initBefore':function(){
		            $('.edit_like_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_like_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_like_dialog button').on('click',function(){
		                if($('.edit_like_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_like_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		</script>
	</body>
	<%-- </ticket:cache> --%>
</html>
