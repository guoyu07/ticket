<%@page import="com.ticket.constants.ContextConstants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript">
		var orderId = '${orderId }';
		var mid = '${mid }';
		var idcard = '${param.idcard }';
	</script>
	<script type="text/javascript" src="/template/wap/js/convientBording/activate.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page" id="scrollTop">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="服务码激活" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <div class="panel mr40 border10">
		                <div class="panel-head fz22 padding-big-top padding-big-bottom" id="optiondiv">
		                	1、选择证件类型
		                	<select name="" class="input input-auto fz22" style="height: 40px;" id="select">
								<s:iterator value="comboList" var="dict">
									<option value="${dict.name }" style="height: 30px; width: 100%;">${dict.value }</option>
								</s:iterator>
							</select>
							<s:fielderror>
								<s:param>infoNotExist</s:param>
							</s:fielderror>
		                </div>
		                <div class="panel-body  padding-big-top padding-big-bottom c_red line">
		                	<form action="" method="post" id="memberForm01">
                                <div class="x1"><a class="button fz22 padding" href="javascript:newAdd('commonTraveller_simpleList.action')"><i class="icon-plus c_blue fz22"></i></a></div>
                    			<div class="x1"></div>
			                    <div class="x7">
		                    		<input id="idCard" type="text" class="input fz22 c_red" placeholder="请输入“添加出行人员”的证件号" style='height:50px;line-height:40px;'/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</div>
			                    <div class="x1"></div>
			                    <div class="x2">
			                    	<input type="button" id="tijiao" class="button b_blue c_white float-right fz22 block text-center padding" style='height:50px;line-height:30px;' value="提交"/>
			                    </div>
		                    </form>
		                </div>
		            </div>
                  	<form id="memberForm02" method="post" action="bjdjServiceCodeActivate_activate.action">
                  		<input type="hidden" name="checkQueueWait" value="true">
			            <div class="panel mr40 border10">
			                <div class="panel-head fz22 padding-big-top padding-big-bottom">2、选择或输入此身份证对应的航班信息</div>
			                <div class="panel-body fz16 padding-big-top padding-big-bottom">
			                    <dl id="items" class="fz20">
			                    	<!-- ajax加载 -->
			                    	<%=session.getAttribute(ContextConstants.ACTIVATE_ITEM_HTML) == null ? 
			                    			"" : (String)session.getAttribute(ContextConstants.ACTIVATE_ITEM_HTML)%>
			                    	<%session.removeAttribute(ContextConstants.ACTIVATE_ITEM_HTML); %>
			                    </dl>
			                    <!-- <div class="text-center">
			                    	<a class="button fz18 d_button">
			                    		<i class="icon-plus c_blue fz22 margin-big-right"></i>添加服务
			                    	</a>
			                    </div> -->
			                </div>
			            </div>
			            <div class="text-center">
			            	<a class="button fz18 d_button" href="javascript:JM.scrollTo('.mobile-main','#scrollTop');$('#idCard').val('')">
			            		<i class="icon-plus c_blue fz22 margin-big-right"></i>添加出行人员
			            	</a>
			            </div>
			            <div class="mr40 text-center">
			            	<input type="submit" class="button d_button bg-yello block" style="display: block;" value="立即激活"/>
			            	<a href="bjdjServiceCodeActivate_successPage.action" id="manageLink" style="display: none;"></a>
			            </div>
					</form>
		        </div>
		        <div class="mobile-foot">
		            <p class="text-center fz12 padding-big-top">COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
		<div class="saturation" style="display:none;">
			<div class="box_dialog">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class="txt txt-small radius-circle bg-red">
							<i class="icon-exclamation c_white"></i>
						</span>
						&nbsp;&nbsp;服务码激活休息厅此预约时段已饱和
					</h2>
					<hr>
					<div class="media media-x">
						<div class="media-body fz18">
							您好！您预约的此时段，机场休息厅已饱和，是否同意放弃进厅服务，只享受此套餐内其他服务项目
						</div>
					</div>
				</div>
				<div class="tit_tab">
					<a id="queueWait" href="javascript:smsTip()" class="">排队等候</a>
					<a id="agree" href="javascript:waitActivate()" class="b_l_grey c_grey">同意</a>
				</div>
			</div>
		</div>
		<div class="wait" style="display:none;">
			<div class="box_dialog">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class="txt txt-small radius-circle bg-red">
						<i class="icon-exclamation c_white"></i></span>&nbsp;&nbsp;服务码激活-选择排队等候
					</h2>
					<hr>
					<div class="media media-x">
						<div class="media-body fz18">
							您好！感谢您对我们的支持，等大厅有空位之后会以短信的形式通知您！
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
		    <div class="warn_msg2 box_dialog">
		        <div class="c_content b_white" >
		            <div class="media media-x">
		                <div class="media-body fz22">
		                    你好，您输入的证件号码未发现航班信息，请进行相关操作或手动输入信息
		                </div>
		            </div>
		            <div class="tit_tab">
		                <a href="airport_checkinServiceNotice.action" class="border b_white c_grey">网上值机</a>
		                <a href="javascript:addBlankActivateItem();">选择航班</a>
		            </div>
		        </div>
		    </div>
		    <div class="zj_ok box_dialog">
		        <div class="tit" style="border-bottom-left-radius:0px;border-bottom-right-radius:0px;">已值机航班</div>
		        <div class="c_content b_white" style="margin-top:0px;border-top-left-radius:0px;border-top-right-radius:0px;border-top:0px;">
		            <div class="line">
		                <!-- <div class="x5 text-center fz24"><label><input name='radio1' type='radio' class='d_checkbox'/>MU5317</label></div> -->
		            </div>
		            <div class="tit_tab">
		                <a href="javascript:addActivateItem();">确认</a>
		            </div>
		        </div>
		    </div>
		    <div class="warn_msg box_dialog">
		        <div class="c_content b_white" >
		            <div class="media media-x">
		                <div class="media-body fz22">
		                    请确定激活的航班信息，如姓名、航班号、航班时间。如遇信息不符请点击信息对应的框进行手动修改。
		                </div>
		            </div>
		            <div class="tit_tab">
		                <a href="javascript:closeUpdateCheckIn();">确认</a>
		            </div>
		        </div>
		    </div>
		    <div class="fucus_ok box_dialog">
		        <div class="tit" style="border-bottom-left-radius:0px;border-bottom-right-radius:0px;">已关注航班</div>
		        <div class="c_content b_white" style="margin-top:0px;border-top-left-radius:0px;border-top-right-radius:0px;border-top:0px;">
		            <div class="line">
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>MU5317</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>MU5317</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>SC6534</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>SC6534</label></div>
		            </div>
		            <div class="tit_tab">
		                <a href="javascript:addFocusFlight();" class="border b_white c_grey">新增关注航班</a>
		                <a href="javascript:closeFocusFlight();">确认</a>
		            </div>
		        </div>
		    </div>
		    <div class="commonTraveller box_dialog">
		        <div class="tit" style="border-bottom-left-radius:0px;border-bottom-right-radius:0px;">常用旅客卡</div>
		        <div class="c_content b_white" style="margin-top:0px;border-top-left-radius:0px;border-top-right-radius:0px;border-top:0px;">
		            <div class="line">
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>MU5317</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>MU5317</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>SC6534</label></div>
		                <div class="x5 text-center fz24"><label><input name="radio2" type='radio' class='d_checkbox'/>SC6534</label></div>
		            </div>
		            <div class="tit_tab">
		                <a href="javascript:closeCommonTraveller();">确认</a>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>