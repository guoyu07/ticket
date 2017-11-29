<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
					<jsp:param value="旅游线路" name="title"/>
				</jsp:include>
		        <div class="mobile-main">
		            <div class="order_ls c_content">
		                <ul>
		                    <li class="line">
		                        <div class="clearfix">
		                            <div class="x5"><img src="/template/wap/images/pic/81.jpg" ></div>
		                            <div class="x7">
		                                <h4 class='fz20 padding-bottom c_grey'>昆明出发  度假特价、好礼相送</h4>
		                                <p class='fz18 padding-bottom'>丽江丽人居客栈位于七一街兴文巷，
		    紧邻主街，步行进入古城约4分钟
		    即可到达客栈门口。</p>
		                                <p class='fz18 padding-bottom'>价格：<span class='c_red'>￥480</span></p>
		                                <p class='fz18'>服务提供商：云南光大</p>
		                            </div>
		                        </div>
		                    </li>
		                </ul>
		                <hr>
		                <div class="panel-body fz16 padding-big-top padding-big-bottom">
		                    <dl class="fz20 c_black">
		                        <dd class="clearfix padding-big-bottom margin-big-bottom">
		                            <div class="line padding-large-bottom">
		                                <div class="x4 padding-top text-left"><i class='t_icons1'></i>出行时间：</div>
		                                <div class="x8">
		                                    <input name="date" type="datetime-local" class="input d_input">
		                                </div>
		                            </div>
		                            <div class="line padding-large-bottom">
		                                <div class="x4 padding-top text-left"><i class='t_icons2'></i>出行人数：</div>
		                                <div class="x8">
		                                    <i class='icon-minus-square-o float-left fz40 c_blue'></i><input name="idCard" type="text" class="input d_input" style="width:230px;float:left;margin:0 10px;"><i class='icon-plus-square-o float-right fz40 c_blue'></i>
		                                </div>
		                            </div>
		                            <div class="line padding-large-bottom">
		                                <div class="x4 padding-top text-left"><i class='t_icons3'></i>旅游意外险：</div>
		                                <div class="x8">
		                                    <input name="idCard" type="text" class="input d_input">
		                                </div>
		                            </div>
		                            <div class="line padding-large-bottom">
		                                <div class="x4 padding-top text-left s"><i class='t_icons4'></i>房间数：</div>
		                                <div class="x8">
		                                    <i class='icon-minus-square-o float-left fz40 c_blue'></i><input name="idCard" type="text" class="input d_input" style="width:230px;float:left;margin:0 10px;"><i class='icon-plus-square-o float-right fz40 c_blue'></i>
		                                </div>
		                            </div>
		                            <div class="line">
		                                <div class="x4"></div>
		                                <div class="x8">
		                                    <p><input class='d_checkbox ' type="checkbox">同意拼房</p><br>
		                                    <p><input class='d_checkbox' type="checkbox">不同意愿补差价500元</p>
		                                </div>
		                            </div>
		
		                        </dd>
		                        <!-- ajax加载 -->
		                    </dl>
		                    <div class="fz30 c_black">订单总价：<font style='color:red'>600</font>元</div>
		                </div>
		            </div>
		            <div class="mr40 fz22">
		                <p><input class='d_checkbox' type="checkbox">已阅读并同意服务协议</p>
		                <div class="tit_tab">
		                    <a href="" class="float-left">去付款</a>
		                </div>
		            </div>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>