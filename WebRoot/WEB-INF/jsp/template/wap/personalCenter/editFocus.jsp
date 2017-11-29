<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="修改关注信息" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<form action="airportm_saveMemberFocus.action" method="post" id="memberForm" return="false">
						<input type="hidden" name="id" value="${id }" />
						<input type="hidden" name="flightNumber" value="${flightNumber }" />
						<input type="hidden" name="flightDate" value="${flightDate }" />
						<input type="hidden" name="luggageState" value="${memberFocusFlight.ifTakeLuggage }" />
						<a id="manageLink" href="/airportm_navigation.action?id=${id }&memberSelfMenuId=${memberSelfMenuId }" style="display: none;"></a>
						<div class="panel mr40 border10">
							<div class="panel-body fz24 custom_radio">
								是否有行李：&nbsp;&nbsp;
								<s:if test="memberFocusFlight.ifTakeLuggage==1">
								<label>
										<i class='icon-check-square'></i>
										<input type="radio" name="takeLuggage" value="1" checked="checked">
										<span>&nbsp;&nbsp;是</span>
									</label> 
									&nbsp;&nbsp;∣&nbsp;&nbsp;
									<label class='checkbox_label'>
										<i class='icon-square-o'></i>
										<input type="radio" name="takeLuggage" value="0">
										<span>&nbsp;&nbsp;否</span>
									</label>
								</s:if>
								<s:else>
									<label>
										<i class='icon-square-o'></i>
										<input type="radio" name="takeLuggage" value="1">
										<span>&nbsp;&nbsp;是</span>
									</label> 
									&nbsp;&nbsp;∣&nbsp;&nbsp;
									<label class='checkbox_label'>
										<i class='icon-check-square'></i>
										<input type="radio" checked="checked" name="takeLuggage" value="0">
										<span>&nbsp;&nbsp;否</span>
									</label>
								</s:else>
							</div>
							<div
								class="panel-foot b_blue c_white padding-big-top padding-big-bottom fz24">
								注：行李超规逾重航空公司将收逾重行李费
								<br>
								<ticket:common type="filghtType" value="${des}"/>
								<s:if test="#request.filghtType == 'domestic'">
									<a href="/airport/1446030951793.ticket"
									class='margin-big-top button bg-yello radius-rounded c_l_grey'
									style='position: relative;' id='xlxz_btn_bak'>查看详情</a>
									&nbsp;&nbsp;
									<a href="/airport/1446277772496.ticket"
									class='margin-big-top button bg-yello radius-rounded c_l_grey'
									style='position: relative;' id='xlxz_btn_bak'>查看安检须知</a>
								</s:if>
								<s:else>
									<a href="/airport/1446176147309.ticket"
									class='margin-big-top button bg-yello radius-rounded c_l_grey'
									style='position: relative;' id='xlxz_btn_bak'>查看详情</a>
									&nbsp;&nbsp;
									<a href="/airport/1446176635777.ticket"
									class='margin-big-top button bg-yello radius-rounded c_l_grey'
									style='position: relative;' id='xlxz_btn_bak'>查看安检须知</a>
								</s:else>
							</div>
						</div>
						<div class="c_content fz18">
							乘机人数：
							<input type="number" datatype="n1-2" name="personCount" id="personCount"
								value="${memberFocusFlight.personCount == null || memberFocusFlight.personCount <= 0 ? 1 : memberFocusFlight.personCount }" 
								class="input input-auto d_input" style='width: 100px; font-size: 24px'/>
							<p class="Validform_checktip" style="display:inline;"> 请填写乘机人数</p>
						</div>
						<div class="panel mr40 border10">
							<div class="panel-body fz24 clearfix">
								旅程中有：
								<p class='float-right custom_checkbox' style='width: 385px;'>
									<s:iterator value="{'1','2','3','4','5','6'}" id='number'>
										<span ${fn:contains(memberFocusFlight.takePerson,number) ? 'class="label button margin-bottom margin-right fz20 selected"' : 'class="label button margin-bottom margin-right fz20"'}>
											<input type="checkbox" name='takeSpecialPerson' value="${number }" ${fn:contains(memberFocusFlight.takePerson,number) ? 'checked="checked"' : ''}> 
										<s:if test="#number == '1'">
											老人（>70）
										</s:if> <s:elseif test="#number == '2'">
											儿童（2-12）
										</s:elseif> <s:elseif test="#number == '3'">
											婴儿（< 2）
										</s:elseif> <s:elseif test="#number == '4'">
											担架旅客
										</s:elseif> <s:elseif test="#number == '5'">
											轮椅旅客
										</s:elseif> <s:elseif test="#number == '6'">
											孕妇
										</s:elseif> 
										</span>
									</s:iterator>
								</p>
							</div>
							<div
								class="panel-foot b_blue c_white padding-big-top padding-big-bottom fz24">
								注：旅程中有上述旅客请选择后查看详情
								<br>
								<a href="#" class='button bg-yello radius-rounded c_l_grey'
									style='position: relative;' id='lkts_btn'>查看详情</a>
							</div>
						</div>
						<div class="tit_tab">
							<a href="javascript:;" onclick="$('#memberForm').submit();" class="b_blue">提交</a>
						</div>
					</form>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<div class="dialog" style="display: none;">
			<div class="box_dialog dialog_xlxz">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class='txt txt-small radius-circle bg-red'><i
							class='icon-exclamation c_white'></i>
						</span>&nbsp;&nbsp;行李须知
					</h2>
					<hr>
					<select name="" class="input">
						<option value="">
							东方航空公司
						</option>
					</select>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								1
							</div> </a>
						<div class="media-body fz18">
							托运行李重量每件不能超过50千克，体积不能超过40×60×100厘米，超过上述规定的行李，须事先征得东航同意后方能托运。符合国际运输条件的航班，每件托运行李重量不能超过32千克的特别规定。
							若判定为超规行李，请到机场国内超规行李托运处办理。
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								2
							</div> </a>
						<div class="media-body fz18">
							适用计重制免费行李额：持成人或儿童客票的头等舱旅客为40千克，公务舱旅客为30千克，经济舱旅客为20千克。符合国内运输条件持婴儿客票的旅客，无免费行李额；符合国际运输条件持婴儿客票的旅客，可享受10千克免费行李额；每名婴儿可免费托运婴儿手推车一辆
							。
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								3
							</div> </a>
						<div class="media-body fz18">
							适用计件制免费行李额：通常持成人或儿童客票的头等舱或公务舱旅客每人为2件行李；每件行李三边之和不超过158厘米、重量不超过32千克，经济舱旅客每人为2件行李；每件行李三边之和不超过158厘米、重量不超过23千克；持婴儿客票的旅客每人为1件行李；三边之和不超过115厘米、重量不超过23千克。每名婴儿可额外免费托运婴儿手推车一辆
						</div>
					</div>
				</div>
			</div>

			<div class="box_dialog dialog_lkts">
				<div class="c_content b_white">
					<h2 class="c_black">
						<span class='txt txt-small radius-circle bg-red'><i
							class='icon-exclamation c_white'></i>
						</span>&nbsp;&nbsp;老年人旅客提示
					</h2>
					<hr>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								1
							</div> </a>
						<div class="media-body fz18">
							年满70岁（含）以上老年人，婴幼儿，无人陪伴儿童，轮椅旅客以及残障人士，孕妇等可通过使用爱心通道进行按钮。
							位置：位于国内安检区域08号安检口；
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								2
							</div> </a>
						<div class="media-body fz18">
							老、弱、病、残、孕可免费享受电瓶车服务
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								3
							</div> </a>
						<div class="media-body fz18">
							老年旅客可享受老年人专用候机区服务；
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$('#xlxz_btn').on('tap', function() {
					$.do_dialog.open( {
						'msg' : $('.dialog_xlxz')
					});
				});
				$('#lkts_btn').on('tap', function() {
					$.do_dialog.open( {
						'msg' : $('.dialog_lkts')
					});
				});
				$('input[name="specialPerson"]').removeAttr("checked");
			})
		</script>
	</body>
</html>