<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
	$(function(){
		initValidForm();
	});
	function initValidForm() {
		var form = jQuery("#memberForm1");
		form.Validform({
			btnSubmit:"#submitBtn",
			tiptype:tipTypeFun,
			ajaxPost:true,
			datatype:{
				"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
			},
			beforeSubmit:function(curform){
				form.find("textarea").each(function(){
					try {
						var obj=$(this);
						if(!JM.isNull(obj)){
							try {
								var objKE = eval(obj.attr("name"));
								objKE.sync();//sync textarea editor content to commom's textarea
							} catch(ee){}
						}
					} catch(e) {}
				});
				var alertConfirm = form.attr("alertConfirm");
				if(JM.isNull(alertConfirm) || alertConfirm == 'false') {
					return true;
				} else {
					
					if(confirm('是否确认提交？')){
						return true;	
					} else {
						return false;
					}
				}
			},callback:function(data){
				var manageLink = $("#manageLink");
				//服务器端没有返回跳转连接，则试图获取ID为manageLink节点的href连接
				if(!JM.isNull(manageLink)){
					href = manageLink.attr("href");
					window.location.href = href + "?goodsCount="+data.info;
				}
			}
		});
	}
	</script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="c_content">
						<form action="/airport_lostGoodsQuery.action" method="post" id="memberForm1">
							<div>
								${newsClass.descript }
							</div>
							<br>
							<div class="lname_rinput fz22">
								<dl class="clearfix padding-bottom">
									<dt class='text-right'>
										遗失日期：
									</dt>
									<dd>
										<input type="date" name="pickUpTime" value="<ticket:common type="currentDate"/>" id="pickUpTime" style="width: 240px" datatype="*"/><span style="color:red;">*</span>
										<p class="Validform_checktip" ></p>
									</dd>
								</dl>
								<dl class="clearfix padding-bottom" style="margin-top: 10px">
									<dt class='text-right'>
										遗失位置：
									</dt>
									<dd>
										<select id="pickPosition_id" name="pickPosition_id" style="width: 240px">
											<option value="">
												请选择位置
											</option>
											<ticket:systemCommon type="lostPositionList" />
											<s:if test="#request.lostPositionList != null">
												<s:iterator id="lostPosition"
													value="#request.lostPositionList">
													<option value="${lostPosition.id }">
														${lostPosition.name }
													</option>
												</s:iterator>
											</s:if>
										</select>
									</dd>
								</dl>
								<dl class="clearfix padding-bottom" style="margin-top: 10px">
									<dt class='text-right'>
										物品类别：
									</dt>
									<dd>
										<select id="type_id" name="type_id" style="width: 240px" datatype="*">
											<option value="">
												请选择类别
											</option>
											<ticket:systemCommon type="goodsTypeList" />
											<s:if test="#request.goodsTypeList != null">
												<s:iterator id="goodsType" value="#request.goodsTypeList">
													<option value="${goodsType.id }">
														${goodsType.name }
													</option>
												</s:iterator>
											</s:if>
										</select><span style="color:red;">*</span>
										<p class="Validform_checktip" ></p>
									</dd>
								</dl>
								<dl class="clearfix padding-bottom" style="margin-top: 10px">
									<dt class='text-right'>
										物品颜色：
									</dt>
									<dd>
										<select id="color" name="color" style="width: 240px">
											<option value="">请选择颜色</option>
											<ticket:systemCommon type="goodsColorList"/>
											<s:if test="#request.goodsColorList != null">
												<s:iterator id="goodsColor" value="#request.goodsColorList">
													<option value="${goodsColor.id }">${goodsColor.name }</option>
												</s:iterator>
											</s:if>
										</select>
									</dd>
								</dl>
								<dl class="clearfix padding-bottom" style="margin-top: 10px">
									<dt class='text-right'>
										物品描述：
									</dt>
									<dd>
										<textarea class="input input-auto fz22" name="goodsDescript"
											id="goodsDescript" style="width:100%;height:100px" placeholder='不超过50字'></textarea>
									</dd>
								</dl>
								<dl class="clearfix padding-bottom" style="margin-top: 15px">
									<dt>
										&nbsp;
									</dt>
									<dd>
										<button class="button d_button bg-yello c_grey fz24">
											&nbsp;&nbsp;&nbsp;&nbsp;查询&nbsp;&nbsp;&nbsp;&nbsp;
										</button>
										<input id="manageLink" type="hidden" href="airport_goodsQueryResult.action"/>
									</dd>
								</dl>
								<br>
							</div>
						</form>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<div class="default_dialog">
				<div class="default_box">
					<img src="/template/wap/images/pic/60.jpg" height="653" width="507">
				</div>
			</div>
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript"><%--
			$(function() {
				$.do_dialog.open( {
					'msg' : $('.default_dialog')
				});
			});
		--%></script>
	</body>
</html>