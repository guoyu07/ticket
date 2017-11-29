<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<%-- <ticket:cache group="资讯中心"> --%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="基本信息" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar  third_label_bar">
						<a href="/airportm_updatePersonalInfo.action" class="border10">头像设置</a>
						<a href="#" class='selected border10'>基本信息</a>
						<a href="/airportm_detailInfo.action" class='border10'>详细信息</a>
						<a href="/airportm_companyInfo.action" class='border10'>公司信息</a>
						<a href="/airportm_educationInfo.action" class='border10'>教育信息</a>
						<a href="/airportm_otherInfo.action" class='border10'>其他信息</a>
					</div>

					<div class="mr40">
						<dl class='fz20'>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>姓名</em><span class='float-right c_l_grey'><font name="realName">${sessionMember.realName }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>身份证</em><span class='float-right c_l_grey'><font name="IDCard">${sessionMember.IDCard }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>昵称</em><span class='float-right c_l_grey'><font name="nickName">${sessionMember.nickName }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dt
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom sex'>
								<label class='fz18'>
									<em>性别</em><span class='float-right c_l_grey'>
									<font name="sex">
										<s:if test="memberDetailInfo.sex == 1">男</s:if>
										<s:elseif test="memberDetailInfo.sex == 0">女</s:elseif>
										<s:else>未设置</s:else>
									</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dt>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>护照信息</em><span class='float-right c_l_grey'><font name="passport">${memberDetailInfo.passport }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dt
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom country'>
								<label class='fz18'>
									<em>国籍</em><span class='float-right c_l_grey'><font name="nationality">
									<s:if test="#memberDetailInfo.nationality != null">
										${memberDetailInfo.nationality }
									</s:if>
									<s:else>
										中国-中华人民共和国
									</s:else>
									</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dt>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>电话</em><span class='float-right c_l_grey'><font name="phone">${sessionMember.phone }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dt
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom place'>
								<label class='fz18'>
									<em>籍贯</em><span class='float-right c_l_grey'><font name="nativePlace">${memberDetailInfo.nativePlace }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dt>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>家庭地址</em><span class='float-right c_l_grey'><font name="homeAddress">${memberDetailInfo.homeAddress }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dd
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
								<label class='fz18'>
									<em>常用地址</em><span class='float-right c_l_grey'><font name="address">${sessionMember.address }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dd>
							<dt
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom age'>
								<label class='fz18'>
									<em>年龄</em><span class='float-right c_l_grey'><font name="age">${memberDetailInfo.age }</font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dt>
							<dt
								class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom birthday'>
								<label class='fz18'>
									<em>生日</em><span class='float-right c_l_grey'><font name="birthday"><s:date name="memberDetailInfo.birthday" format="yyyy-MM-dd"/></font>
										<i class='icon-angle-right float-right fz30 margin-big-left'></i>
									</span>
								</label>
							</dt>
						</dl>
						<button class="button d_button b_blue c_white block" id="updateBaseInfo">
							保存
						</button>
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
		    <div class="edit_sex_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div class="line two_select padding-big-top padding-large-bottom">
		                <a href="javascript:;" class='x5 padding fz20'>男</a>
		                <a href="javascript:;" class='x5 x2-move padding fz20'>女</a>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_ls_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                	<ticket:common type="systemDictionaryListByParentValueOrder" value="nationality"/>
		                	<s:if test="#request.systemDictionaryListByParentValueOrder != null">
		                		<s:iterator id="systemDictionary" value="#request.systemDictionaryListByParentValueOrder">
				                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>${systemDictionary.value }</a>
		                		</s:iterator>
		                	</s:if>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		    <div class="edit_age_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                    <c:forEach var="i" begin="8" end="100" step="1">
								<a href="javascript:;" class='x12 padding fz20 margin-bottom'>${i}</a>
							</c:forEach>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		    <div class="edit_place_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                    <ticket:common type="systemDictionaryListByParentValue" value="nativeplace"/>
		                	<s:if test="#request.systemDictionaryListByParentValue != null">
		                		<s:iterator id="systemDictionary" value="#request.systemDictionaryListByParentValue">
				                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>${systemDictionary.value }</a>
		                		</s:iterator>
		                	</s:if>
		                </div>
		            </div>
		            <button class="button d_button b_blue c_white block">保存</button>
		        </div>
		    </div>
		
		    <div class="edit_birthday_dialog box_dialog">
		        <div class="text-center b_white border10 mr40 padding-big">
		            <input type="date" name="" value="" class='input d_input' placeholder=''>
		            <br>
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
		    $('dl dt.sex').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_sex_dialog'),'initBefore':function(){
		            $('.edit_sex_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_sex_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_sex_dialog button').on('click',function(){
		                if($('.edit_sex_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_sex_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.country').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_ls_dialog'),'initBefore':function(){
		            $('.edit_ls_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_ls_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_ls_dialog button').on('click',function(){
		                if($('.edit_ls_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_ls_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.age').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_age_dialog'),'initBefore':function(){
		            $('.edit_age_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_age_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_age_dialog button').on('click',function(){
		                if($('.edit_age_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_age_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.place').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_place_dialog'),'initBefore':function(){
		            $('.edit_place_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_place_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_place_dialog button').on('click',function(){
		                if($('.edit_place_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_place_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		    $('dl dt.birthday').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_birthday_dialog'),'initBefore':function(){
		            $('.edit_birthday_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_birthday_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_birthday_dialog button').on('click',function(){
		                //if($('.edit_birthday_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_birthday_dialog input').val());
		                //}
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		</script>
	</body>
	<%-- </ticket:cache> --%>
</html>
