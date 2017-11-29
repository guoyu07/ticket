<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
<body class="mobile">
<div class="mobile-view">
    <div class="mobile-page">
        <jsp:include page="../common/title.jsp">
			<jsp:param value="关联会员" name="title"/>
		</jsp:include>
        <div class="mobile-main">
            <!-- <div class='search_fly'>
                <div class="c_content">
                    <p class="t_l"><img src="../images/pic/62.png" height="24" width="24">&nbsp;&nbsp;昆明航空&nbsp;&nbsp;<font class='c_black'>CA4171</font> <span class='float-right'>座位号 <font class="c_black">B21</font></span></p>
                    <div class="fly_line clearfix">
                        <span class='float-left'><em class='c_black'>07:30</em><font>出发城市</font></span>
                        <i></i>
                        <span class='float-right'><em class='c_black'>07:30</em><font>到达城市</font></span>
                    </div>
                </div>
            </div> -->
            <div class="mr30">
                <form action="" method="get" class="addrbook clearfix">
                    <div class="addrbook_icon"></div>
                    <input type="text" class="input" name="" value="" placeholder='输入电话号码'>
                    <div class="x5"><button class="button d_button bg-yello block">获取验证码</button></div>
                    <div class="x6"><input name='' class="input d_input b_l_grey " placeholder='输入验证码'></div>
                </form>

            </div>
            <div class="tit_tab" style="padding-top:0px;">
               <!--  <a href="#">添加值机人</a> -->
                <a href="javascript:aaa();">发送</a>
            </div>
        </div>
    </div>
</div>
<div class="dialog" style="display:none;">
    <div class="box_dialog check_phone">
        <div class="c_content b_white">
            <div class="media media-x">
                <div class="media-body fz18">
                    <div class="order_form">
                        <div class="line clearfix mr40">
                            <div class="x12 mrtb20">
                                <input name='' class="input d_input b_l_grey block" placeholder='请输入手机号码'>
                            </div>
                            <div class="x5"><button class="button d_button bg-yello block">获取验证码</button></div>
                            <div class="x1"></div>
                            <div class="x6"><input name='' class="input d_input b_l_grey " placeholder='输入验证码'></div>
                            <div class="x12 mrtb20"><button class="button d_button bg-yello block">确认关联</button></div>
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
                    <div class="text-center padding fz22">
                        恭喜你，验证成功
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        
    });
    
    function aaa(){
    	alert("1");
    	$.do_dialog.open({'msg':$('.check_phone')});
    }
</script>
</body>
</html>