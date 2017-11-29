<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<%-- <ticket:cache group="资讯中心"> --%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="头像设置" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar  third_label_bar">
						<a href="/airportm_updatePersonalInfo.action" class="border10">头像设置</a>
						<a href="/airportm_baseInfo.action" class="border10">基本信息</a>
						<a href="/airportm_detailInfo.action" class='border10'>详细信息</a>
						<a href="/airportm_companyInfo.action" class='border10'>公司信息</a>
						<a href="#" class='border10 selected'>教育信息</a>
						<a href="/airportm_otherInfo.action" class='border10'>其他信息</a>
					</div>
					 <div class="mr40">
		                <dl class='fz20'>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom edu'>
		                        <label class='fz18'><em>学历</em><span class='float-right c_l_grey'><font name="education">${memberDetailInfo.education }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>学校</em><span class='float-right c_l_grey'><font name="school">${memberDetailInfo.school }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>专业</em><span class='float-right c_l_grey'><font name="major">${memberDetailInfo.major }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                </dl>
		                <button class="button d_button b_blue c_white block" id="updateEducationInfo">保存</button>
		            </div>
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
		    <div class="edit_edu_dialog box_dialog">
		        <div  class="text-center b_white border10 mr40 padding-big">
		            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
		                <div class="line two_select padding-big-top">
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>院士</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>博士</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>硕士</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>本科</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>专科</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>高中</a>
		                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>初中</a>
		                </div>
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
		    $('dl dt.edu').on('click',function(){
		        var _this= $(this);
		        $.do_dialog.open({'msg':$('.edit_edu_dialog'),'initBefore':function(){
		            $('.edit_edu_dialog .line a').removeClass('selected');
		        },initAfter:function(rs){
		            $('.edit_edu_dialog .line a').on('click',function(){
		                $(this).addClass('selected').siblings().removeClass('selected');
		            })
		            $('.edit_edu_dialog button').on('click',function(){
		                if($('.edit_edu_dialog .line a').hasClass('selected')){
		                    _this.find('font').html($('.edit_edu_dialog .line a.selected').html());
		                }
		                $.do_dialog.close(rs.index);
		            })
		        }});
		    })
		</script>
	</body>
	<%-- </ticket:cache> --%>
</html>
