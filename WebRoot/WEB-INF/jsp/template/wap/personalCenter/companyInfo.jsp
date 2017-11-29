<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<%-- <ticket:cache group="资讯中心"> --%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="公司信息" name="title"/>
					<jsp:param value="airportm_personalSetting.action?direct=true" name="url"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="label_bar  third_label_bar">
						<a href="/airportm_updatePersonalInfo.action" class="border10">头像设置</a>
						<a href="/airportm_baseInfo.action" class="border10">基本信息</a>
						<a href="/airportm_detailInfo.action" class='border10'>详细信息</a>
						<a href="#" class='selected border10'>公司信息</a>
						<a href="/airportm_educationInfo.action" class='border10'>教育信息</a>
						<a href="/airportm_otherInfo.action" class='border10'>其他信息</a>
					</div>
					<div class="mr40">
		                <dl class='fz20'>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>公司名称</em><span class='float-right c_l_grey'><font name="companyName">${memberDetailInfo.companyName }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dd>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom company'>
		                        <label class='fz18'><em>企业性质</em><span class='float-right c_l_grey'><font name="enterpriseProperty">${memberDetailInfo.enterpriseProperty }</font> <i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom honor'>
		                        <label class='fz18'><em>头衔</em><span class='float-right c_l_grey'><font name="honor">${memberDetailInfo.honor }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom admin'>
		                        <label class='fz18'><em>职级</em><span class='float-right c_l_grey'><font name="rank">${memberDetailInfo.rank }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom worker'>
		                        <label class='fz18'><em>职业</em><span class='float-right c_l_grey'><font name="occupation">${memberDetailInfo.occupation }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                    <dt class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom comin'>
		                        <label class='fz18'><em>月收入</em><span class='float-right c_l_grey'><font name="income">${memberDetailInfo.income }</font><i class='icon-angle-right float-right fz30 margin-big-left'></i></span></label>
		                    </dt>
		                </dl>
		                <button class="button d_button b_blue c_white block" id="updateCompanyInfo">保存</button>
		            </div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
			<div class="dialog" style="display:none;">
			    <div class="edit_dialog box_dialog">
			        <div class="text-center b_white border10 mr40 padding-big">
			            <input type="text" name="" value="" class='input d_input' placeholder=''>
			            <br>
			            <button class="button d_button b_blue c_white block">保存</button>
			        </div>
			    </div>
			    <div class="edit_company_dialog box_dialog">
			        <div  class="text-center b_white border10 mr40 padding-big">
			            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
			                <div class="line two_select padding-big-top">
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>私企</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>国企</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>集体所有制企业</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>联营企业</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>三资企业</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>私营企业</a>
			                    <a href="javascript:;" class='x12 padding fz20 margin-bottom'>其他企业</a>
			                </div>
			            </div>
			            <button class="button d_button b_blue c_white block">保存</button>
			        </div>
			    </div>
			
			    <div class="edit_honor_dialog box_dialog">
			        <div  class="text-center b_white border10 mr40 padding-big">
			            <div class="line two_select padding-big-top padding-large-bottom">
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>董事长</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>总经理</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>经理</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>储备干部</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>底层员工</a>
			            </div>
			            <button class="button d_button b_blue c_white block">保存</button>
			        </div>
			    </div>
			    <div class="edit_admin_dialog box_dialog">
			        <div  class="text-center b_white border10 mr40 padding-big">
			            <div class="line two_select padding-big-top padding-large-bottom">
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>高层管理</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>中层管理</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>底层管理</a>
			                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>一线职员</a>
			            </div>
			            <button class="button d_button b_blue c_white block">保存</button>
			        </div>
			    </div>
			    <div class="edit_worker_dialog box_dialog">
			        <div  class="text-center b_white border10 mr40 padding-big">
			            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
			                <div class="line two_select padding-big-top">
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>农林牧渔业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>采矿业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>制造业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>电力燃气及水的生产和供应业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>建筑业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>交通运输仓储和邮政业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>信息传输计算机服务和软件业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>批发和零售业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>住宿和餐饮业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>金融业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>房地产业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>租赁和商务服务业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>科学研究技术服务和地质勘查业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>水利环境和公共设施管理业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>居民服务和其他服务业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>教育</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>卫生社会保障和社会福利业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>文化体育和娱乐业</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>公共管理和社会组织</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>国际组织</a>
				              </div>
			            </div>
			            <button class="button d_button b_blue c_white block">保存</button>
			        </div>
			    </div>
			    <div class="edit_comin_dialog box_dialog">
			         <div  class="text-center b_white border10 mr40 padding-big">
			            <div  style='max-height:400px;overflow:auto;margin-bottom:20px;'>
			                <div class="line two_select padding-big-top">
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>1000元以下</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>1001-2000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>2001-3000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>3001-5000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>5001-8000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>8001-10000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>10001-20000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>20001-50000元</a>
				                <a href="javascript:;" class='x12 padding fz20 margin-bottom'>50000元以上</a>
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
			    $('dl dt.company').on('click',function(){
			        var _this= $(this);
			        $.do_dialog.open({'msg':$('.edit_company_dialog'),'initBefore':function(){
			            $('.edit_company_dialog .line a').removeClass('selected');
			        },initAfter:function(rs){
			            $('.edit_company_dialog .line a').on('click',function(){
			                $(this).addClass('selected').siblings().removeClass('selected');
			            })
			            $('.edit_company_dialog button').on('click',function(){
			                if($('.edit_company_dialog .line a').hasClass('selected')){
			                    _this.find('font').html($('.edit_company_dialog .line a.selected').html());
			                }
			                $.do_dialog.close(rs.index);
			            })
			        }});
			    })
			    $('dl dt.honor').on('click',function(){
			        var _this= $(this);
			        $.do_dialog.open({'msg':$('.edit_honor_dialog'),'initBefore':function(){
			            $('.edit_honor_dialog .line a').removeClass('selected');
			        },initAfter:function(rs){
			            $('.edit_honor_dialog .line a').on('click',function(){
			                $(this).addClass('selected').siblings().removeClass('selected');
			            })
			            $('.edit_honor_dialog button').on('click',function(){
			                if($('.edit_honor_dialog .line a').hasClass('selected')){
			                    _this.find('font').html($('.edit_honor_dialog .line a.selected').html());
			                }
			                $.do_dialog.close(rs.index);
			            })
			        }});
			    })
			    $('dl dt.admin').on('click',function(){
			        var _this= $(this);
			        $.do_dialog.open({'msg':$('.edit_admin_dialog'),'initBefore':function(){
			            $('.edit_admin_dialog .line a').removeClass('selected');
			        },initAfter:function(rs){
			            $('.edit_admin_dialog .line a').on('click',function(){
			                $(this).addClass('selected').siblings().removeClass('selected');
			            })
			            $('.edit_admin_dialog button').on('click',function(){
			                if($('.edit_admin_dialog .line a').hasClass('selected')){
			                    _this.find('font').html($('.edit_admin_dialog .line a.selected').html());
			                }
			                $.do_dialog.close(rs.index);
			            })
			        }});
			    })
			    $('dl dt.worker').on('click',function(){
			        var _this= $(this);
			        $.do_dialog.open({'msg':$('.edit_worker_dialog'),'initBefore':function(){
			            $('.edit_worker_dialog .line a').removeClass('selected');
			        },initAfter:function(rs){
			            $('.edit_worker_dialog .line a').on('click',function(){
			                $(this).addClass('selected').siblings().removeClass('selected');
			            })
			            $('.edit_worker_dialog button').on('click',function(){
			                if($('.edit_worker_dialog .line a').hasClass('selected')){
			                    _this.find('font').html($('.edit_worker_dialog .line a.selected').html());
			                }
			                $.do_dialog.close(rs.index);
			            })
			        }});
			    })
			    $('dl dt.comin').on('click',function(){
			        var _this= $(this);
			        $.do_dialog.open({'msg':$('.edit_comin_dialog'),'initBefore':function(){
			            $('.edit_comin_dialog .line a').removeClass('selected');
			        },initAfter:function(rs){
			            $('.edit_comin_dialog .line a').on('click',function(){
			                $(this).addClass('selected').siblings().removeClass('selected');
			            })
			            $('.edit_comin_dialog button').on('click',function(){
			                if($('.edit_comin_dialog .line a').hasClass('selected')){
			                    _this.find('font').html($('.edit_comin_dialog .line a.selected').html());
			                }
			                $.do_dialog.close(rs.index);
			            })
			        }});
			    })
			</script>
	</body>
	<%-- </ticket:cache> --%>
</html>
