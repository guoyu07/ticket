/**
 * PC端的JS方法
 */
jQuery(function(){
	
	initValidForm();
	//切换机场商业
	changeAirportBusiness();
	//查询商户
	queryBusinessInit();
	//查询航空公司
	queryFlightCompanyInit();
	//查询航班
	queryFlight();
	//获取验证码
	sendSmsInit();
});

function changeAirportBusiness(){
	$(".airportBusiness").unbind().bind('click',function(){
		var obj = $(this);
		var flag = obj.attr("businessFlag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_changeAirportBusiness.action',
			data:'flag='+flag+"&random="+JM.randomNumber,
			success:function(data){
				$(".businessContent").html(data);
				queryBusinessInit();
			}
		});
	});
}

function queryBusinessInit(){
	$(".searchBusinessByKeyword").unbind().bind('click',function(){
		var type_id = $("#businessType").val();
		var keyword = $("#keyword").val();
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_queryBusiness.action',
			data:'businessType='+type+'&keyword='+keywpord,
			success:function(data){
				
			}
		});
	});
}

/**
 * 查询航空公司信息
 * @return
 */
function queryFlightCompanyInit(){
	$(".searchFlightCompanyByKeyword").unbind().bind('click',function(){
		var keyword = $("#keyword");
		var orderFlag=$(this).attr("orderFlag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_queryFlightCompany.action',
			data:'keyword='+JM.encodeByValue(keyword.val())+'&orderFlag='+orderFlag,
			success:function(data){
				$("#flightCompanySearchResult").html(data);
			}
		});
	});
}

function queryFlight(){
	$("#queryByFlight").bind("keyup", function(){
		queryFlightAjax();
	}).bind("keydown", function(){
		queryFlightAjax();
	}).bind("input", function(){
		queryFlightAjax();
	}).bind("keypress",function(){
		queryFlightAjax();
	});
	$("#queryByDate").bind("change", function(){
		queryFlightAjax();
	});
	$("#queryByTime").bind("change",function(){
		queryFlightAjax();
	});
	$(".queryFlightByKeyword").unbind().bind('click',function(){
		var flightFlag = $("#flightFlag").val();
		var keyword = $("#keyword").val();
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_queryFlightByKeyword.action',
			data:'keyword='+JM.encodeByValue(keyword)+"&flightFlag="+flightFlag,
			success:function(data){
				$("#flightSearchResult").html(data);
			}
		});
	});
	$(".changeQueryWay").unbind().bind('click',function(){
		var flag = $(this).attr('flag');
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_changeFlightQueryWay.action',
			data:'flag='+flag,
			success:function(data){
				$("#queryFlightWay").html(data);
			}
		});
	});
	$(".queryFlightByNoAndDate").unbind().bind('click',function(){
		var flightDate = $("#flightDate").val();
		var flightNumber = $("#flightNumber").val();
		if(JM.isNull(flightNumber)){
			JM.alert("请填写航班号~");
			$("#flightNumber").focus();
			return false;
		}
		if(JM.isNull(flightDate)){
			JM.alert("请选择日期~");
			$("#flightDate").focus();
			return false;
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_queryFlightByNoAndDate.action',
			data:'flightNumber='+flightNumber+"&flightDate"+flightDate,
			success:function(data){
				$("#queryFlightWay").html(data);
			}
		});
	});
	$(".queryFlightByCity").unbind().bind('click',function(){
		var setoutCity = $("#setoutCity").val();
		var reachCity = $("#reachCity").val();
		if(JM.isNull(flightDate)){
			JM.alert("请选择日期~");
			$("#flightDate").focus();
			return false;
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportPc_queryFlightByCity.action',
			data:'setoutCity='+setoutCity+"&reachCity"+reachCity+"&flightDate="+flightDate,
			success:function(data){
				$("#queryFlightWay").html(data);
			}
		});
	});
	
	$("#searchFlight").unbind().bind("click",function(){
		queryFlightAjax();
	});
}

function queryFlightAjax(){
	$("#flightQueryResult").html("");
	var flightFlag = $("#flightFlag").val();
	var keyword = $("#queryByFlight").val();
	var flightDate = $("#queryByDate").val();
	var flightTime = $("#queryByTime").val();
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airportPc_queryFlightByConditions.action',
		data:'flightFlag='+flightFlag+'&keyword='+keyword+'&flightDate='+flightDate+'&flightTime='+flightTime,
		success:function(data){
			$("#flightQueryResult").html(data);
		}
	});
}
/**
 * 验证表单
 */
function initValidForm() {
	
	var form = $("#pcForm");
	if(!form || form.size() <= 0){
		
		return;
	}
	var isAjax = JM.isNull(form.attr('ajaxPost')) ? true : false;
	form.Validform({
		tiptype:function (msg, o, cssctl) {
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip = o.obj.siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		ajaxPost:isAjax,
		beforeSubmit:function(curform){
			var alertConfirm = form.attr("alertConfirm");
			if(JM.isNull(alertConfirm)) {
				return true;
			} else {
				if(confirm('是否确认提交？')){
				   return true;	
				} else {
					return false;
				}
			}
		},
		callback:function(data){
			
			if(isAjax == true){
				
				if (data.status == JM.YES) {
					
					//服务器端返回跳转连接
					var href = data.info.href;
					function forward() {
						
						var params = form.serialize();
						var manageLink = $("#manageLink");
						
						//服务器端没有返回跳转连接，则试图获取ID为manageLink节点的href连接
						if(JM.isNull(href)){
							
							href = manageLink.attr("href");
						}
						
						//把form参数拼接到连接地址后面
						if(href.indexOf("?") < 0){
							
							href += '?';
						}else{
							
							href += '&';
						}
						href += params;
						
						// 是否不可返回，如果不可返回，这点击返回按钮不可返回
						if (form.attr('return') && form.attr('return') == 'false') {
							
							window.location.replace(href);
						} else {
							
							window.location.href = href;
						}
					}
					//data的info为空，或者info是一个也是一个json对象
					if(JM.isNull(href)){
						
						JM.alert(data.info, 2000, forward);
					}else{
						
						forward();
					}
					
				} else {
					JM.alert(data.info);
					JM.flush();
				}
			}
		}
	});
}


/**
 * 发送短信验证码
 * @return
 */
function sendSmsInit() {
	$(".sendSmsClass").unbind().bind("click", function(){
		var sendSmsBtn = $(this);
		sendSmsBtn.val("发验证码");
		var sendType = sendSmsBtn.attr("sendType");
		var elementId = sendSmsBtn.attr("elementId");
		var mobile = $("#"+elementId);
		if(JM.isNullByJQuery(mobile)) {
			mobile.focus();
			return false;
		}
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/sms.action',
			data:'sendType='+sendType+'&mobile='+JM.encode(mobile)+'&random=' + JM.randomNumber,
			success:function(data){
				if(data.status == JM.YES){
  					sendSmsBtn.unbind();
  					var surplus = 60;
					var handler = setInterval(function(){
						
						if(surplus > 0){
							$(".sendSmsClass").html(--surplus + '秒');
						}else{
							$(".sendSmsClass").html('发送验证码');
							clearInterval(handler);
						}
					}, 1000);
  					window.setTimeout(sendSmsInit, 60000);
  				} else if(data.status == JM.NO) {
  					alert(data.info);
  				}
			}
		});
	});
}