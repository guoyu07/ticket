<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		function showPhoneNumber(jsondata){
		
			//将传递过来的Json转换为对象  
			var jsonobjs = eval(jsondata);
			document.getElementById("myInput").value = jsonobjs[0].phoneNumber;
		}
	</script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="定制服务" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<form action="airportm_saveMemberFocus.action" id="memberForm" return="false">
						<div class='search_fly'>
							<s:if test="#request.airportPlan != null">
								<div class="c_content">
									<p class="t_l">
										<ticket:common type="queryCompanyByFlightNo" value="${airportPlan.flt }" />
										<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNo.id }" annexType="1" size="1" />
										<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position: relative; top: 5px;'>
										&nbsp;&nbsp; ${queryCompanyByFlightNo.name } &nbsp;&nbsp;
										<s:set var="transferFlt" value="flightCompanyService.transferFlightNumber(airportPlan.flt)"></s:set>
										<font class='c_black'>${transferFlt }</font>
									</p>
									<div class="fly_line clearfix line">
										<span class='float-left xl4'><em class='c_black'>${airportPlan.std }
										</em> <font>昆明</font> </span>
										<em class='t_c c_blue xl4'> <font class="block">计划</font>
											<i></i> </em>
										<span class='float-right xl4'><em class='c_black'>${airportPlan.eta }
										</em> <ticket:common type="airportInfoByFourCode" value="${airportPlan.arrive }"/><font>${airportInfoByFourCode.name }</font> 
										</span>
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="c_content">
									<p class="t_l">
										<ticket:common type="flightCompanyByTwoCode" value="${departFromPort.acw }" />
										<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1" />
										<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position: relative; top: 5px;'>
										&nbsp;&nbsp; ${flightCompanyByTwoCode.name } &nbsp;&nbsp;
										<font class='c_black'>${departFromPort.flt }</font>
									</p>
									<div class="fly_line clearfix line">
										<span class='float-left xl4'><em class='c_black'><s:date
													name="departFromPort.std" format="HH:mm" />
										</em> <font>昆明</font> </span>
										<em class='t_c c_blue xl4'> <font class="block">计划</font>
											<i></i> </em>
										<span class='float-right xl4'><em class='c_black'><s:date name="departFromPort.tdes" format="HH:mm" />
										</em> <font><ticket:common type="systemDicObjByName" value="${departFromPort.des }" />
											${systemDicObjByName.description }
										</font> </span>
									</div>
								</div>
							</s:else>
						</div>
						<div class="panel mr40 border10">
							<input type="hidden" id="luggageState" name="luggageState" value="1" />
							<div class="panel-body fz24 custom_radio">
								是否有行李：&nbsp;&nbsp;
								<label>
									<i class='icon-check-square'></i>
									<input type="radio" name="takeLuggage" value="1" <s:if test="memberFocusFlight.ifTakeLuggage==1">checked="checked"</s:if>>
									<span>&nbsp;&nbsp;是</span>
								</label> 
								&nbsp;&nbsp;∣&nbsp;&nbsp;
								<label class='checkbox_label'>
									<i class='icon-square-o'></i>
									<input type="radio" name="takeLuggage" value="0" <s:if test="memberFocusFlight.ifTakeLuggage!=1">checked="checked"</s:if>>
									<span>&nbsp;&nbsp;否</span>
								</label>
							</div>
							<div
								class="panel-foot b_blue c_white padding-big-top padding-big-bottom fz24">
								注：行李超规或逾重航空公司将收逾重行李费
								<br>
								<ticket:common type="filghtType" value="${des}"/>
								<a href="${departFromPort.doi=='D'? '/airport/1446030951793.ticket' : '/airport/1446176147309.ticket'}" class='margin-big-top button bg-yello radius-rounded c_l_grey' style='position: relative;' id='xlxz_btn_bak'>查看详情</a>
								&nbsp;&nbsp;
								<a href="${departFromPort.doi=='D'? '/airport/1446176635777.ticket' : '/airport/1446277772496.ticket'}" class='margin-big-top button bg-yello radius-rounded c_l_grey' style='position: relative;' id='xlxz_btn_bak'>查看安检须知</a>
							</div>
						</div>
						<div class="c_content fz24">
							乘机人数：
							<input type="number" value="1" datatype="n1-2" name="personCount" id="personCount" class="input input-auto d_input" style='width: 100px; font-size: 24px'/>
							<p class="Validform_checktip" style="display:inline;"> 请填写乘机人数</p>
						</div>
						<s:if test="#request.IDCardList != null">
							<s:iterator id="IDCard" value="#request.IDCardList">
								<div class="c_content fz24 form-inline">
									<div class="form-group line" >
			                           	<div class="label x3" style="padding-top:10px;">
			                                <label for="f_username_txt" style="padding-top:10px;font-weight:normal;display:block;">
			                                   	 身份证
			                                </label>
			                            </div>
			                            <div class="field x9">
			                                <input type="text" class="input d_input fz24" name="IDCardArray" value="${IDCard }" readonly="readonly">
			                            </div>
			                        </div>
			                        <%-- <div class="form-group" >
			                           	<div class="label x3" style="padding-top:10px;">
			                                <label for="f_username_txt" style="padding-top:10px;font-weight:normal;display:block;">
			                                    	电话
			                                </label>
			                            </div>
			                            <div class="field x9">
			                                <input id="myInput" type="text" class="input d_input fz24" name="phoneArray" dataType="m" ignore="ignore" maxlength="50" placeholder="电话">
			                                <p class="Validform_checktip" style="display:inline;"> 请填写电话号码</p>
			                            </div>
			                            <div>
			                            	<s:if test="#parameters.fromApp != null">
				                            	<a href="javascript:;"
													class='button bg-yello radius-rounded c_l_grey'
													style='position: relative;'
													onclick="${param.addressFlag == 'android' ? 'addressList.getAddressList()' : 'getAddressList()' }">通讯录</a>
											</s:if>
			                            </div>
			                        </div> --%>
								</div>
							</s:iterator>
						</s:if>
						<%-- <s:else>
							<div class="c_content fz24 form-inline">
								<div class="form-group" >
		                           	<div class="label x3" style="padding-top:10px;">
		                                <label for="f_username_txt" style="padding-top:10px;font-weight:normal;display:block;">
		                                    	电话
		                                </label>
		                            </div>
		                            <div class="field x9">
		                                <input id="myInput" type="text" class="input d_input fz24" name="phone" maxlength="50" dataType="m" ignore="ignore" placeholder="电话">
		                                <p class="Validform_checktip" style="display:inline;"> 请填写电话号码</p>
		                            </div>
		                            <div>
		                            	<s:if test="#parameters.fromApp != null">
			                            	<a href="javascript:;"
												class='button bg-yello radius-rounded c_l_grey'
												style='position: relative;'
												onclick="${param.addressFlag == 'android' ? 'addressList.getAddressList()' : 'getAddressList()' }">通讯录</a>
										</s:if>
		                            </div>
		                        </div>
							</div>
						</s:else> --%>
						<div class="panel mr40 border10">
							<div class="panel-body fz24 clearfix">
								旅程中有：
								<p class='float-right custom_checkbox' style='width: 385px;'>
									<span class="label button margin-bottom margin-right fz20">
										老人（>70）<input type='checkbox' name='specialPerson' value='1'>
									</span>
									<span class="label button margin-bottom margin-right fz20">
										儿童（2-12）<input type='checkbox' name='specialPerson' value='2'>
									</span>
									<span class="label button margin-bottom margin-right fz20">
										婴儿（<2）<input type='checkbox' name='specialPerson' value='3'>
									</span>
									<span class="label button margin-bottom margin-right fz20">
										担架旅客<input type='checkbox' name='specialPerson' value='4'>
									</span>
									<span class="label button margin-bottom margin-right fz20">
										轮椅旅客<input type='checkbox' name='specialPerson' value='5'>
									</span>
									<span class="label button margin-bottom margin-right fz20">
										孕妇<input type='checkbox' name='specialPerson' value='6'>
									</span>
								</p>
							</div>
							<div
								class="panel-foot b_blue c_white padding-big-top padding-big-bottom fz24">
								注：旅程中有上述旅客请选择后查看详情
								<br>
								<a href="#"class='margin-big-top button bg-yello radius-rounded c_l_grey' style='position: relative;' id='lkts_btn'>查看详情</a>
							</div>
						</div>
						<div class="tit_tab">
							<s:if test="#checkinInfos.size() == 0">
								<a href="/airport_checkinServiceNotice.action" class="b_blue">网上值机</a>
							</s:if>
							<a href="javascript:;" onclick="$('#memberForm').submit();" class="b_blue">确定</a>
							<s:if test="#request.airportPlan != null">
								<ticket:common type="airportInfoByFourCode" value="${airportPlan.dept }"/>
								<%--<input type="hidden" name="des" value="${airportInfoByFourCode.threeCode }" />
								--%><input type="hidden" name="org" value="${airportInfoByFourCode.threeCode }" />
							</s:if>
							<s:else>
								<input type="hidden" name="des" value="${departFromPort.des }" />
								<input type="hidden" name="org" value="${departFromPort.org }" />
							</s:else>
							<input type="hidden" name="flightDate" value="${flightDate }" />
							<input type="hidden" name="id" value="${id }" />
							<input type="hidden" name="flightNumber" value="${flightNumber }" />
							<input type="hidden" name="acw" value="${departFromPort.acw }" />
							<input type="hidden" name="gat" value="${departFromPort.gat }" />
							<input type="hidden" name="cid" value="${departFromPort.cid }" />
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
					<!-- <select name="" class="input">
						<option value="">
							东方航空公司
						</option>
					</select> -->
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
						</span>&nbsp;&nbsp;温馨提示
					</h2>
					<hr>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								1
							</div> </a>
						<div class="media-body fz18">
							<b>爱心通道：</b>年满70岁(含)以上老年人、婴幼儿、无人陪伴儿童、轮椅旅客以及残障人士、孕妇等可通过使用爱心通道进行安检。位置：位于国内员工通道西侧2号安检口；
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								2
							</div> </a>
						<div class="media-body fz18">
							<b>免费电瓶车：</b>为老（70岁以上）、弱、病、残、孕等特殊旅客提供免费电瓶车服务；
						</div>
					</div>
					<div class="media media-x">
						<a class="float-left" href="#">
							<div class="txt txt-small radius-circle bg-yello">
								3
							</div> </a>
						<div class="media-body fz18">
							<b>老年人及残障人士候机厅：</b>老年旅客可享受老年人及残障人士专用候机区服务；<br/><br/><br/>
							无人陪伴儿童：为5周岁以上12周岁以下无成人陪伴乘机的儿童办理登机手续，旅客全程享受优先安检、优先登机、手对手与机组交接等服务；<br/>
							无人陪伴老人：为无家人陪伴乘机的70周岁以上老人办理登机手续，旅客全程享受优先安检、优先登机、手对手与机组交接等服务；<br/>
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