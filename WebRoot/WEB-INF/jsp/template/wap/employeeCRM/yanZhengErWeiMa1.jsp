<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/electromobileerweima.js"></script>
	<body class="mobile">
		<div class="mobile-view">
    <div class="mobile-page">
    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
        <div class="mobile-top">
            <div class="header">
                扫二维码验证
            </div>
        </div>
        </s:if>
        <s:else>
        <div class="mobile-top" style="display: none;">
            <div class="header">
                扫二维码验证
            </div>
        </div>
        </s:else>
        <div class="mobile-main">
            <div class="mr40">
                <div class="qr_box border" style="width:560px;height:400px;margin:0 auto;border:1px solid #344">
                    <div class='t_c fz40' style="padding-top:100px;"><span class='text-red' style='font-size:60px;'>${price }</span>元<p style='padding-top:50px'>${type }1次</p></div>
                </div>
                <input type="hidden" id="code" value="${serviceCode }"/>
            </div>
            <div class="tit">
                <a href="javascript:yanzheng();" class="b_yello c_grey check_btn">确认验证</a>
            </div>
            <div class="tit">
                <a href="javascript:;" class="b_yello c_grey check_btn1">验证不了?</a>
            </div>
            <div class="tit_tab">
					<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
			</div>
        </div>
    </div>
</div>
		<div class="dialog" style="display:none;">
			<div class="check_dialog box_dialog">
				<div class="c_content b_white">
					<h2 class="t_c fz30 c_black">
						验证遇到问题！
					</h2>
					<p class="c_l_grey fz22 t_c padding-big">
						二维码无效？二维码已使用？二维码已失效？
					</p>
					<h3 class="t_c fz26 c_blue padding-top-big">
						请确认二维码是否正确？
					</h3>
					<h3 class="t_c fz26 c_blue padding-top-big">
						可以尝试<a style="color:red;" id="other">其他方式</a>验证
					</h3>
				</div>
			</div>
			<%@ include file="../common/left.jsp" %>
		</div>
		<script type="text/javascript">
		    $(function(){
		        $('.check_btn1').on('tap',function(){
		            $.do_dialog.open({'msg':$('.check_dialog')});
		        })
		    });
		</script>
	</body>
</html>