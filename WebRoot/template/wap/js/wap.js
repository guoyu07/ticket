/**
 * wap前台的JS方法
 */
jQuery(function(){
	initValidForm();
	sendSmsInit();
//	businessInit();
	queryFlightInit();
	focusFlightInit();
	transferNoLuggageInit();
	transfer();
	transferApp();
	queryLostGoods();
	selectCityByList();
	updatePersonalData();
	changLuggageState();
	isFormAppHasHeader();
	memberFocusFligtInit();
	initRevokeShare();
	showPackageDetail();
	careServiceInit();
//	getAllCityInit();
	$("#agreeRegister").bind("click",function(){
		var obj = $(this);
		if(obj.prop("checked")){
			$("#submitBtn").removeAttr("disabled");
		}else{
			$("#submitBtn").attr("disabled","disabled");
		}
	});
	
	$(".deleteMessage").bind("click",function(){
		var message_id = $(this).attr("message_id");
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'airportm_deleteMessage.action',
			data:'id='+message_id,
			success:function(data){
				if(data.status=='y'){
					window.location.reload();
					return;
				}else{
					return;
				}
			}
		});
	});
	
	/**
	 * 爱心服务初始化
	 */
	function careServiceInit(){
		$(".careServiceList").find("div").bind("click",function(){
			var href = $(this).attr("href");
			window.location.assign(href);
		});
	}
	$(".advertUrl").find("li").bind("click",function(){
		var url = $(this).attr("href");
		window.location.href = url;
	});
	
	$("#commonSearchBtn").bind("click", function(){
		var keyword = $("#keyword");
		var keyword2 = $("#keyword2");
		if(JM.isNullByJQuery(keyword)) {
			keyword.focus();
			return false;
		} else {
			var k1 = keyword.val();
			if(!JM.isNullByJQuery(keyword2)){
				var k2 = keyword2.val();
				if(k1 == k2){
					window.location.replace('/airport_commonSearch.action?commonKeyword='+JM.encode(keyword)+'&random='+JM.randomNumber);
				}else{
					window.location.href = '/airport_commonSearch.action?commonKeyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
				}
			}else{
				window.location.href = '/airport_commonSearch.action?commonKeyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
			}
		}
		return false;
	});
	
	JM.selectSelect("personCount");
	$(".regulationType").bind("click",function(){
		window.location.href="/airport_employeeHome.action";
	});
	//会员取消关注航班
	$(".removeFocus").bind("click",function(){
		var obj = $(this);
		var id = $(this).attr("id");
		var isYes = window.confirm("确定取消关注当前航班？");
		if(isYes){
			JM.alert("正在执行……", -1);
			$.ajax({
				type:'POST',
				dataType:'json',
				url:'/airportm_deleteFocus.action',
				data:'id='+id,
				success:function(data){
					if (data.status == JM.YES) {
						JM.alert("取消成功！", 500);
						obj.closest(".c_content").remove();
						$(".goBackToflight").attr("href","javascript:history.go(-2);");
						return;
					}else{
						JM.alert("取消失败！", 500);
						return;
					}
				}
			});
		}
	});
//	/**
//	 * 离港航班详情
//	 */
//	$(".departFlightDetail").unbind().bind("click",function(){
//		$.cookie("listUrl",window.location.href,{path: '/' });
//		var flightNumber = $(this).attr("flightNumber");
//		var flightDate = $(this).attr("flightDate");
//		var stopover = $(this).attr("stopover");
//		window.location.href = "/airport_departFlightDetail.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover;
//	});
//	/**
//	 * 到港航班详情
//	 */
//	$(".arrivalFlightDetail").unbind().bind("click",function(){
//		$.cookie("listUrl",window.location.href,{path: '/' });
//		var flightNumber = $(this).attr("flightNumber");
//		var flightDate = $(this).attr("flightDate");
//		var stopover = $(this).attr("stopover");
//		window.location.href = "/airport_arrivalFlightDetail.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover;
//	});
//	/**
//	 * 未关注离港航班详情
//	 */
//	$(".departFlightDetailNo").bind("click",function(){
//		$.cookie("listUrl",window.location.href,{path: '/' });
//		var flightNumber = $(this).attr("flightNumber");
//		var flightDate = $(this).attr("flightDate");
//		var flightState = $(this).attr("flightState");
//		var stopover = $(this).attr("stopover");
//		window.location.href = "/airport_queryDepartFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState;
//	});
//	/**
//	 * 未关注到港航班详情
//	 */
//	$(".arrivalFlightDetailNo").bind("click",function(){
//		$.cookie("listUrl",window.location.href,{path: '/' });
//		var flightNumber = $(this).attr("flightNumber");
//		var flightDate = $(this).attr("flightDate");
//		var stopover = $(this).attr("stopover");
//		var flightState = $(this).attr("flightState");
//		window.location.href = "/airport_queryArrivalFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState;
//	});
	$("#takeLuggage").bind("click",function(){
		if($(this).prop("checked",true)){
			$("#luggageState").val("1");
		}
	});
	$("#noLuggage").bind("click",function(){
		if($(this).prop("checked",true)){
			$("#luggageState").val("0");
		}
	});
	
	/**
	 * 进港乘机指南
	 */
	$(".arrivalFlightNav").bind("click",function(){
		var org = $(this).attr("org");
		var flightNumber = $(this).attr("flightNumber");
		var flightDate = $(this).attr("flightDate");
		window.location.href = "/airportm_reach.action?org="+org+"&flightNumber="+flightNumber+"&flightDate="+flightDate;
	});
	$(".transferFlightNav").bind("click",function(){
		var org = $(this).attr("org");//出发城市
		var des = $(this).attr("des");//到达城市
		var flightNumber = $(this).attr("flightNumber");
		var flightDate =$(this).attr("flightDate");
		window.location.href = "/airportm_transferNav.action?org="+org+"&des="+des+"&flightNumber="+flightNumber+"&flightDate="+flightDate;
	});
	$(".OneKeyFocus").bind("click",function(){
		var url=$(this).attr("url");
		window.location.href = url;
	});
	
	//根据城市查询航班信息
	$(".queryBycity").bind("click",function(){
		var setoutCity = $("#setoutCity").html();
		var arrivalCity = $("#arrivalCity").html();
		var departPortTime = $("#departPortTime").val();
		window.location.href = "/airport_queryByCity.action?setoutCity="+setoutCity+"&arrivalCity="+arrivalCity+"&departPortTime="+departPortTime;
	});
	
	
	//交通指南路线Ajax
	$(".trafficLineAjax").find("li").bind("tap",function(){
		var id = $(this).attr("trafficTypeId");
		if(JM.isNull(id)){
			return false;
		}
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_trafficLineAjax.action',
			data:'id='+id,
			success:function(data){
				$(".trafficLine").html(data);
			}
		});
	});
//	$(".trafficLineAjax").find("li").eq(0).trigger('tap');
	
	//内部员工重置密码
	$("#resetPwd").bind("click",function(){
		window.location.href = "/airport_employeeResetPwd.action";
	});
	//机场文化详细
	$(".cultureDetail").bind('click',function(){
		var url=$(this).attr("url");
		window.location.href="/airport/"+url+".ticket";
	});
	//商家详细信息
	$(".businessDetail").bind("click",function(){
		var businessId = $(this).attr("businessId");
		window.location.href = "/airport_businessDetail.action?id="+businessId;
		
	});
	
	//删除已设置的快捷菜单
	$(".deleteSetMenu").bind("click",function(){
		var id = $(this).attr("memberQuickMenuId");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportm_deleteQuickMenu.action',
			data:'id='+id,
			success:function(data){
				$(".centerMenuList").html(data);
//				jQuery.getScript('/template/wap/js/wap.js');
				return;
			}
		});
	});
	//个人设置
	$(".personalSetting").bind('click',function(){
		window.location.href="/airportm_personalSetting.action";
	});
	//跳转到收藏页面
	$(".toFavoritePage").bind('click',function(){
		var newsClassAlias=$(this).attr("newsClassAlias");
		var visitUrl = $(this).attr("visitUrl");
		var url=$(this).attr("url");
		if(!JM.isNull(newsClassAlias)){
			window.location.href="/airport/"+newsClassAlias+".ticket";
			return;
		}
		if(!JM.isNull(visitUrl)){
			window.location.href="/airport/"+visitUrl+".ticket";
			return;
		}else{
			window.location.href=url;
		}
	});
	//会员收藏
	$(".memberFavorite").unbind('tap').bind('tap',function(){
		var thisUrl = window.location.href;
		if(path.indexOf("?") != -1){
			thisUrl = thisUrl+"&flag=backTwo";
		}else{
			thisUrl = thisUrl+"?flag=backTwo";
		}
//		var thisUrl = window.location.href+"&flag=backTwo";
		$.cookie("preUrl",thisUrl,{path: '/' });
		JM.alert('请求中...', 0);
		var objectId=$(this).attr("objectId");
		var objectType=$(this).attr("objectType");
		var title=$(this).attr("title");
		var url=$(this).attr("url");
		var mid = TuYou.Cookie.get("mid");
		if(!JM.isNull(objectId)||!JM.isNull(objectType)){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'/airportm_favorite.action',
				data:'objectId='+objectId+"&objectType="+objectType+"&mid="+mid,
				success:function(data){
					if(data.length > 200){
						JM.alert('', 1, function(){
							
							var isYes = window.confirm("您还未登录，是否登录后收藏该页面？");
							if(isYes){
								window.location.href="/airportm_login.action?flag=focus";
								return;
							};
						});
					}else{
						
						data=$.parseJSON(data);
						JM.alert(data.info);
						$('.memberFavorite').toggleClass('icon-star');
						$('.memberFavorite').toggleClass('icon-star-o');
						return;
					}
				}
			});
			return;
		}else if(!JM.isNull(title)&&!JM.isNull(url)){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'/airportm_favorite.action',
				data:'title='+title+"&url="+url+"&mid="+mid,
				success:function(data){
					if(data.length > 200){
						JM.alert('', 1, function(){
							
							var isYes = window.confirm("您还未登录，是否登录后收藏该页面？");
							if(isYes){
								window.location.href="/airportm_login.action?flag=focus";
								return;
							};
						});
					}else{
						
						data=$.parseJSON(data);
						JM.alert(data.info);
							
						$('.memberFavorite').toggleClass('icon-star');
						$('.memberFavorite').toggleClass('icon-star-o');
						return;
					}
				}
			});
		}
		
	});
	$("#bjdj").bind('click',function(){
		window.location.href = '/airport/bianjiedengji.ticket';
	});
	$("#memberLogin").bind('click',function(){
		window.location.href = "/airportm_login.action";
	});
	//我的收藏
	$("#myFavorite").bind('click',function(){
		window.location.href = "/airportm_myFavorite.action";
	});
	//加载更多
	$(".notice_ft").bind("click",loadMore);
	//爱心服务选择模板
	$(".selectCaring").bind("click",function(){
		$(".selectCaring").removeClass('selected');
		$(this).addClass('selected');
		var news_id = $(this).attr("news_id");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_caringAjax.action',
			data:'id='+news_id,
			success:function(data){
			$("#caringContent").html(data);
		}
		});
	});
	//切换模板Ajax
	$(".changeFacilities").bind("click",function(){
		$(".changeFacilities").removeClass('selected');
		$(this).addClass('selected');
		var news_id = $(this).attr("news_id");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_changeFacilitiesAjax.action',
			data:'id='+news_id,
			success:function(data){
			$(".c_content").html(data);
		}
		});
	});

	//出发 
	$(".setoutFrom").bind('click',setoutFrom);
	//中转
	$(".transfer").bind('click',transfer);
	//中转左右切换
	$(".changeLR").unbind().bind('click',function(){
		var lValue = $("#leftValue").html();
		var rValue = $("#rightValue").html();
		$.cookie("rightValue",$.trim(lValue));
		$.cookie("leftValue",$.trim(rValue));
		$("#leftValue").html($.cookie("leftValue"));
		$("#rightValue").html($.cookie("rightValue"));
		transfer();
	});
	$(".appChangeLR").bind("click",function(){
		window.location.href="/airport_tongcheng.action";
	});
	//中转无行李左右切换
	$(".changeLRNoLuggage").bind('click',function(){
		var lValue = $("#leftValue").html();
		var rValue = $("#rightValue").html();
		$.cookie("rightValue",$.trim(lValue));
		$.cookie("leftValue",$.trim(rValue));
		transferNoLuggage();
	});
	$(".changeLuggageState").find("li").bind("tap",function(){
		var href = $(this).attr("href");
		if(JM.isNull(href) || href=='#'){
			return false;
		}else{
			
			var leftValue = $('#leftValue').text();
			leftValue = $.trim(leftValue)=='国内' ? 'inner' : 'outer';
			var rightValue = $('#rightValue').text();
			rightValue = $.trim(rightValue)=='国内' ? 'inner' : 'outer';
			href = href + "&left="+leftValue+"&right="+rightValue;
			window.location.href = href; 
			return false;
		}
	});
	//到达
	$(".reach").bind('click',reach);
	//下拉选择航空公司
	$(".luggageQuery").find("li").bind('tap',function(){
		var news_id = $(this).attr("news_id");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_luggageQueryAjax.action',
			data:'id='+news_id,
			success:function(data){
			$(".c_content").html(data);
		}
		});
	});
	//下拉查看行李寄存
	$(".luggageConsign").find("li").bind("tap",function(){
		var news_id = $(this).attr("news_id");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_luggageConsignAjax.action',
			data:'id='+news_id,
			success:function(data){
				$(".c_content").html(data);
			}
		});
	});
	//选择航空公司查看信息
	$(".flightAjax").find("li").bind('tap',function(){
		var flightid = $(this).attr("flightCompanyid");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_flightAjax.action',
			data:'id='+flightid,
			success: function (data) {
			    
			    $(".flightContent").html(data);
			    $(".flightContent").show();
              
		}
		});
	});
	//选择航空公司
	$(".selectFlightCompany").find("li").unbind().bind('tap',function(){
		var flightCompanyid = $(this).attr("flightid");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_selectFlightCompanyAjax.action',
			data:'id='+flightCompanyid,
			success:function(data){
			$(".flightCompanyContent").html(data);
		}
		});
	});
	setQuickMenuInit();
	customerMenuInit();
	transferInit();
	businessTopTypeInit();
	$("#money").bind("input propertychange", exchangeRate);
	$("#fromType,#toType").bind("change", exchangeRate);
	$("#exchangeTransferBtn").unbind().bind("tap", exchangeTransfer);
	$("#cityKeyword").bind("keyup", function(){
		selectCityAjax();
	}).bind("keydown", function(){
		selectCityAjax();
	}).bind("input", function(){
		selectCityAjax();
	}).bind("keypress",function(){
		selectCityAjax();
	});
	$("#stationKeyword").bind("focus", function(){
		$("#selectStationDiv").hide();
	});
	$(".searchTrafficType").bind("click",function(){
		var keyword=$("#stationKeyword").val();
		if(JM.isNull(keyword)){
			JM.alert("请输入关键词！");
			$("#stationKeyword").focus();
			return false;
		}
		searchTrafficLineByStation();
		$("#selectStationDiv").show();
	});
	
	//是否收藏的
	isFavorite();
	changeLuggageState();
	buyTicketAjax();
});
//-->
function changeLuggageState() {
	$(".changeLuggageState2").find("li").unbind().bind("tap",function(){
		var redirectUrl = $(this).attr("href");
		if(JM.isNull(redirectUrl) || redirectUrl=='#'){
			return false;
		}else{
			setTimeout(function(){window.location.href = redirectUrl;}, 500);
		}
	});
}
function businessTopTypeInit() {
	//机场商业类别切换
	$(".businessTopType").each(function(){
		var obj = $(this);
		obj.bind("click", function(){
			
			if($.trim($(this).text()) == '酒店'){
				//window.location = 'http://m.ctrip.com/webapp/hotel/?&allianceid=303758&sid=776936';
				$(".businessTopType").unbind();
				businessTopType(obj);
			}else{
				$(".businessTopType").unbind();
				businessTopType(obj);
			}
		});
	});
}
//function businessTopType(obj) {
//	var typeId = obj.attr("typeId");
//	$(".businessTopType").removeClass("bg-yello selected").addClass("bg-sub");
//	obj.removeClass("bg-sub").addClass("bg-yello selected");
//	$.ajax({
//		type:'POST',
//		dataType:'text',
//		url:'/airport_businessSecondTypeAjax.action',
//		data:'id='+typeId,
//		success:function(data){
//			$(".secondType").html(data);
//			businessTopTypeInit();
//			businessInit();
//			return;
//		}
//	});
//}
function setQuickMenuInit() {
	//取消设置快捷菜单
	$(".cancelSetQuickMenu").unbind().bind("click",function(){
		var id = $(this).attr("memberMenuId");
		var obj = $(this);
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportm_deleteQuickMenu.action',
			data:'id='+id,
			success:function(data){
				queryMenuStatus(id, 0, obj);
			}
		});
	});
	//会员设置快捷菜单
	$(".setQuickMenu").unbind().bind('click',function(){
		var obj = $(this);
		var quickMenu_id = $(this).attr("quickMenu_id");
		var defaultShowPosition = $("#defaultShowPosition").val();
		var flightQuickMenu = $("#flightQuickMenu").val();
		var menuCount = $(this).attr("menuCount");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/quickMenuMemberSetting_add.action',
			data:'quickMenu_id='+quickMenu_id+"&defaultShowPosition="+defaultShowPosition+"&flightQuickMenu="+flightQuickMenu+"&menuCount="+menuCount,
			success:function(data){
				data=$.parseJSON(data);
				if(data.info=='已达上限'){
					JM.alert('已达上限');
				}else{
					//window.location.reload();
					queryMenuStatus(quickMenu_id, defaultShowPosition, obj);
				}
				
			}
		});
	});
}
function customerMenuInit() {
	/**
	 * 会员设置快捷菜单
	 */
	$(".toSetQuickMenu").unbind().bind('click',function(){
		var defaultPosition = $(this).attr("defaultPosition");
		var flightQuickMenu = $(this).attr("flightQuickMenu");
		var preUrl = window.location.href;
		$.cookie("leftPreUrl",preUrl,{path: '/' });
		window.location.href = '/airportm_setQuickMenu.action?defaultPosition='+defaultPosition+"&flightQuickMenu="+flightQuickMenu;
	});
	/**
	 * 会员设置服务快捷菜单
	 */
	$(".serviceQuickMenuBtn").unbind().bind('click',function(){
		var defaultPosition = $(this).attr("defaultPosition");
		var flightQuickMenu = $(this).attr("flightQuickMenu");
		var menuCount = $(this).attr("menuCount");
		var preUrl = window.location.href;
		$.cookie("preUrl",preUrl);
		window.location.href = '/airportm_setServiceQuickMenu.action?defaultPosition='+defaultPosition+"&flightQuickMenu="+flightQuickMenu+"&menuCount="+menuCount+"&preUrl="+preUrl;
	});
}
//加载更多信息
function loadMore(){
	var newsClass_id = $("#newsClass_id").val();
	var startSize = parseInt($(this).attr("startSize"));
	var getCount = parseInt($(this).attr("getCount"));
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_infoTypeAjax.action',
		data:'id='+newsClass_id+'&ajaxType=newsAjax'+'&startSize='+startSize+'&pageCount='+getCount,
		success:function(data){
			if(JM.isNull($.trim(data))){
				
				JM.alert('已加载到最后', 2000);
			}else{
				
				$(".notice_ft").attr("startSize",startSize+getCount);
				$("#loadNews").append(data);
			}
		}
	});
}

//出发Ajax
function setoutFrom(){
	var flag = $(this).attr('flag');
	$(".setoutFrom").removeClass("selected");
	$(this).addClass("selected");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_setoutAjax.action',
		data:'flag='+flag,
		success:function(data){
		$("#positionNavigatesetout").html(data);
		setOutInit();
		$('.pic1 area').eq(6).click();
	}
	});
}
//中转Ajax
/*function transfer(){
	var luggageState = $("#luggageState");
	var leftValue = $("#leftValue");
	var rightValue = $("#rightValue");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_transferAjax.action',
		data:'transferLeft='+JM.encodeByValue(leftValue.html())+'&transferRight='+JM.encodeByValue(rightValue.html())+'&luggageState='+luggageState.val()+'&random='+JM.randomNumber,
		success:function(data){
			$("#positionNavigate").html(data);
			customerMenuInit();
		}
	});
}*/

function transfer(){
	if($.cookie("leftValue")==null){
		$("#leftValue").html("国内");
		$.cookie("leftValue","国内");
	}else{
		$("#leftValue").html($.cookie("leftValue"));
	}
	if($.cookie("rightValue")==null){
		$("#rightValue").html("国内");
		$.cookie("rightValue","国内");
	}else{
		$("#rightValue").html($.cookie("rightValue"));
	}
	var luggageState = $("#luggageState");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_transferAjax.action',
		data:'transferLeft='+JM.encodeByValue($.cookie("leftValue"))+'&transferRight='+JM.encodeByValue($.cookie("rightValue"))+'&luggageState='+luggageState.val()+'&random='+JM.randomNumber,
		success:function(data){
			$("#positionNavigate").html(data);
			customerMenuInit();
		}
	});
}
function transferApp(){
	var leftValue = $("#appLeftValue").text().trim();
	var rightValue = $("#appRightValue").text().trim();
	var luggageState = $("#luggageState");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_transferAjax.action',
		data:'transferLeft='+JM.encodeByValue(leftValue)+'&transferRight='+JM.encodeByValue(rightValue)+'&luggageState='+luggageState.val()+'&random='+JM.randomNumber,
		success:function(data){
		$("#positionNavigateApp").html(data);
			customerMenuInit();
		}
	});
}
//中转无行李Ajax
function transferNoLuggage(){
	if($.cookie("leftValue")==null){
		$("#leftValue").html("国内");
		$.cookie("leftValue","国内");
	}else{
		$("#leftValue").html($.cookie("leftValue"));
	}
	if($.cookie("rightValue")==null){
		$("#rightValue").html("国内");
		$.cookie("rightValue","国内");
	}else{
		$("#rightValue").html($.cookie("rightValue"));
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_transferNoLuggageAjax.action',
		data:'transferLeft='+JM.encodeByValue($.cookie("leftValue"))+'&transferRight='+JM.encodeByValue($.cookie("rightValue"))+'&random='+JM.randomNumber,
		success:function(data){
			$("#positionNavigate").html(data);
		}
	});
}

//到达Ajax
function reach(){
	var flag = $(this).attr('flag');
	var luggageState = $("#luggageState").val();
	$(".reach").removeClass("selected");
	$(this).addClass("selected");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'airport_reachAjax.action',
		data:'flag='+flag+"&luggageState="+luggageState,
		success:function(data){
		$("#positionNavigate2").html(data);
	}
	});
}
function setOutInit() {
	$('.pic1 area').eq(6).bind('click',function(){
		$('.pic2').show();
		$('.pic1').hide();
	});
	$('.pic2 area').eq(8).bind('click',function(){
		$('.pic1').show();
		$('.pic2').hide();
	})
}
function transferInit() {
	/*if($.trim($("#leftValue").html())=='国际' && $.trim($("#rightValue").html())=='国际'){
		$("#middleVal").html('通程');
	}
	if($.cookie("leftValue")=="国际"&&$.cookie("leftValue")=="国际"){
		$("#middleVal").html('通程');
	}else{
		$("#middleVal").html('中转');
	}*/
	$(".leftDropMenuClass").find("li").bind("tap", function(){
		var value = $(this).html().trim();
		$.cookie("leftValue",$(this).html().trim());
		$("#leftValue").html(value);
		/*if($("#rightValue").html().trim()=='国际'&&value=='国际'){
			$("#middleVal").html('通程');
		}else{
			$("#middleVal").html('中转');
		}*/
		transfer();
	});
	$(".rightDropMenuClass").find("li").bind("tap", function(){
		var value = $(this).html().trim();
		$.cookie("rightValue",$(this).html().trim());
		$("#rightValue").html(value);
		/*if($("#leftValue").html().trim()=='国际'&&value=='国际'){
			$("#middleVal").html('通程');
		}else{
			$("#middleVal").html('中转');
		}*/
		transfer();
	});
	$(".appLeftDropMenuClass").find("li").unbind().bind("tap", function(){
		var value = $(this).html().trim();
		$("#appLeftValue").html(value);
		var rightValue = $("#appRightValue").html().trim();
		transferApp();
		window.location.href = "/airport_left.action?leftValue="+value+"&rightValue="+rightValue;
	});
	$(".appRightDropMenuClass").find("li").unbind().bind("tap", function(){
		var value = $(this).html().trim();
		$("#appRightValue").html(value);
		var leftValue = $("#appLeftValue").html().trim();
		transferApp();
		window.location.href = "/airport_left.action?leftValue="+leftValue+"&rightValue="+value;
	});
	
}
function transferNoLuggageInit() {
	$(".leftDropMenuClassNoLuggage").find("li").bind("tap", function(){
		var value = $(this).text();
		$.cookie("leftValue",$.trim(value));
	//	$("#leftValue").html(value);
		transferNoLuggage();
	});
	$(".rightDropMenuClassNoLuggage").find("li").bind("tap", function(){
		var value = $(this).text();
		$.cookie("rightValue",$.trim(value));
	//	$("#rightValue").html(value);
		transferNoLuggage();
	});
}
/**
 * 游客注册
 * @return
 */
function register(){
	window.location.href = "/airportm_register.action?versionFlag=site";
}
/*返回上一页*/  
function return_prepage()  
{  
	var isApp = GetQueryString("isApp");
	if(JM.isNull(isApp)){
		if(window.document.referrer==""||window.document.referrer==window.location.href)  
		{  
			window.location.href="{dede:type}[field:typelink /]{/dede:type}";  
		}else  
		{  
			window.location.href=window.document.referrer;  
		}  
	}else{
		
	}
}  
//获取地址栏参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); 
     return null;
}


/**
 * ValidForm的tipType自定义函数
 * @param msg
 * @param o
 * @param cssctl
 * @return
 */
function tipTypeFun (msg, o, cssctl) {
	//msg：提示信息;
	//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
	//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
	if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
		var objtip = o.obj.siblings(".Validform_checktip");
		cssctl(objtip, o.type);
		objtip.text(msg);
	} else {
		var objtip = o.obj.find("#msgdemo");
		cssctl(objtip, o.type);
		objtip.text(msg);
	}
}
/**
 * 身份证的验证方法
 * @param gets
 * @param obj
 * @param curform
 * @param regxp
 * @returns {Boolean}
 */
function yzsfz(gets,obj,curform,regxp){
    //参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
    var rgp1 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    var rgp2 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    if(rgp1.test(gets) || rgp2.test(gets)){
        if(gets.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=gets.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=gets.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    return true;
                }else{
                    return false;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                   return true;
                }else{
                    return false;
                }
            }
        }
        if(gets.length==15){
            var year =  gets.substring(6,8);
            var month = gets.substring(8,10);
            var day = gets.substring(10,12);
            var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
            if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){
                return false;
            }else{
                return true;
            }
        }
    }else{
        return false;
    }

}

/**
 * @description initial the memberForm to valid and submit.
 * @author HiSay
 * @datetime 2015-11-07 11:53:27
 */
function initValidForm() {
	var form = jQuery("#memberForm");
	var isAjax = JM.isNull(form.attr('ajaxPost')) ? true : false;
	form.Validform({
		btnSubmit:"#submitBtn",
		tiptype:tipTypeFun,
		ajaxPost:isAjax,
		datatype:{
			"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
			"sf" : yzsfz,
			"zw" : /^[\u4E00-\u9FA5]+$/,
			"yw" : /^[A-Za-z]+$/
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
		
			if(isAjax == true){
				
				if (data.status == JM.YES) {
					
					//服务器端返回跳转连接
					var href = '';
					function forward() {
						
						var manageLink = $("#manageLink");
						//服务器端没有返回跳转连接，则试图获取ID为manageLink节点的href连接
						if(JM.isNull(href)){
							
							href = manageLink.attr("href");
						}
						
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
					JM.alert(data.info, 3000);
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
function queryMenuStatus(menuId, showDefaultPosition, obj) {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/quickMenuMemberSetting_queryCurrentStatus.action',
		data:'quickMenu_id='+menuId+'&defaultShowPosition='+showDefaultPosition,
		success:function(data){
			data = $.parseJSON(data);
			if(data.flag == '1') {//已设置过，变成有颜色
				$(obj).removeClass().addClass("cancelSetQuickMenu");
				$(obj).attr("memberMenuId",data.memberMenuId);
 			} else { //没设置过，变灰
 				$(obj).removeClass().addClass("gray").addClass("setQuickMenu");
 			}
			setQuickMenuInit();
		}
	});
	return false;
}
$(function(){
	$('.tab_ls dt').on('tap', function() {
        var _this = $(this);
        if (_this.find('span').hasClass('icon-plus')) {
            _this.find('span').attr('class', 'icon-minus');
            _this.parent().find('dd').slideDown();
        } else {
            _this.find('span').attr('class', 'icon-plus');
            _this.parent().find('dd').slideUp();
        }
    })
});

//function businessInit(){
//	//根据商家类别查询商家列表
//	$(".businessInfoListByType").bind('click',function(){
//		var typeId = $(this).attr("typeId");
////		$(".businessInfoListByType").attr("style","");
////		$(this).attr("style","color:red");
//		$.ajax({
//			type:'POST',
//			dataType:'text',
//			url:'/airport_businessListAjax.action',
//			data:'id='+typeId,
//			success:function(data){
//				$("#sucai").html(data);
//				businessInit();
//			}
//		});
//	});
//	
//	$(".wz_kaifa").bind('click',function(){
//		var wz = $(this).attr("wz");
//		$.post("/airport_businessListAjaxByWz.action",{id:wz},function(data){
//			$("#sucai").html(data);
//			//businessInit();
//		},"text");
//	});
//	
//	$(".businessEvent").bind("click",function(){
//		var id = $(this).attr("attrId");
//		$.post("/airport_businessListAjaxByEvent.action",{id:id},function(data){
//			$("#sucai").html(data);
//			//businessInit();
//		},"text");
//	});
//	
//	$(".order").bind("click",function(){
//		var order = $(this).attr("order");
//		$.post("/airport_businessListAjaxByOrder.action",{id:order},function(data){
//			$("#sucai").html(data);
//			//businessInit();
//		},"text");
//	});
//}
function focusFlightInit(){
	/**
	 * 会员关注航班
	 */
	$(".focusFlight").unbind().bind("click",function(){
		var flightNumber = $("#flightNumber").val();
		var flightDate = $("#flightDate").val();
		var flightState = $("#flightState").val();
		var personRole = $(this).attr("personRole");
		var sessionMember = $("#sessionMember").val();
		var mid = $("#mid").val();
		var stopover=$("#stopover").val();
		if(!JM.isNull($.cookie("flag"))){
			$.ajax({
				type:'POST',
				datatype:'text',
				url:'airportm_checkFlightDateByActive.action',
				data:'flightNumber='+flightNumber+'&flightDate='+flightDate,
				success:function(data){
					var value = $.parseJSON(data);
					if(value.status == JM.YES){
						window.location.href='/airportm_focusFlight.action?flightNumber='+flightNumber+'&flightDate='+flightDate+"&memberRole="+personRole+"&flightState="+flightState+"&stopover="+stopover;
					}else{
						JM.alert("该航班时间不符合要求，不能激活！");
						return;
					}
				}
			});
		}else{
			var preUrl = window.location.href+"&stopover="+stopover;
			$.cookie("preUrl",preUrl,{path: '/' });
			if(JM.isNull(sessionMember)&&JM.isNull(mid)){
				var isYes = window.confirm("您还未登录，是否登录？");
				if(isYes){
				window.location.href='/airportm_focusFlight.action?flightNumber='+flightNumber+'&flightDate='+flightDate+"&memberRole="+personRole+"&flightState="+flightState+"&stopover="+stopover;
				}else{
					return false;
				}
			}else{
				window.location.href='/airportm_focusFlight.action?flightNumber='+flightNumber+'&flightDate='+flightDate+"&memberRole="+personRole+"&flightState="+flightState+"&stopover="+stopover;
			}
		}
	});
	
}

//航班查询方式Ajax
function queryFlightInit(){
	$(".queryFlight").bind("click",function(){
		var flag=$(this).attr("flag");
		window.location.replace(flag);
		/*$(".queryFlight").removeClass("selected");
		$(this).addClass("selected");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airport_flightQueryTemplateAjax.action',
			data:'flag='+flag,
			success:function(data){
				$(".queryFlightContent").html(data);
				queryFlightInit();
			}
		});*/
	});
	$(".exchangeCity").unbind().bind("click",function(){
		var leftCity = $("#orgCity").text();
		var rightCity = $("#desCity").text();
		var org = $("#org").val();
		var des = $("#des").val();
		$("#desCity").html(leftCity);
		$("#orgCity").html(rightCity);
		$("#org").val(des);
		$("#des").val(org);
	});
	$(".selectOrgCity").unbind().bind("tap",function(){
		window.location.href="/airport_selectCity.action?searchType=chufa";
	});
	$(".selectDesCity").unbind().bind("tap",function(){
		window.location.href="/airport_selectCity.action?searchType=daoda";
	});
	$(".flightOrg").find("li").bind("tap",function(){
		var org=$(this).attr("cityThreeCode");
		$("#org").val(org);
	});
	
	$(".flightDes").find("li").bind("tap",function(){
		var org=$(this).attr("cityThreeCode");
		$("#des").val(org);
	});
	$(".changeCityPoi").bind("click",function(){
		$(".changeCityPoi").removeClass("selected");
		$(this).addClass("selected");
		var cityPoi = $(this).attr("cityPoi");
		$("#cityPoi").val(cityPoi);
		JM.alert('加载中...', -1);
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_changeCityPosition.action',
			data:'flag='+cityPoi+'&random='+JM.randomNumber+'',
			success:function(data){
				
				JM.alert('加载完成',500);
				$("#cityList").html(data);
				selectCityByList();
			}
		});
	});
	/**
	 * 根据航班编号和日期查询航班信息
	 */
	$(".queryFlightByNoAndDate").bind("click",function(){
		var flightNumber = $("#flightNumber").val();
		var flightDate = $("#flightDate").val();
		var html=$(".queryFlightContent").html();
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
		window.location.href = "/airport_queryByFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate;
	});
	/**
	 * 根据城市查询航班信息
	 */
	$(".queryFlightByCity").bind("click",function(){
		var org = $("#org").val();
		var des = $("#des").val();
		var flightDate = $("#flightDate").val();
		if(JM.isNull(flightDate)){
			JM.alert("请选择日期~");
			$("#flightDate").focus();
			return false;
		}
		window.location.href = "/airport_queryFlightByCity.action?orgCode="+org+"&desCode="+des+"&flightDate="+flightDate;
	});
	$(".preDayFlight").unbind().bind("click",function(){
		var obj = $(this);
		var org = obj.attr("org");
		var des = obj.attr("des");
		var flightDate=obj.attr("flightDate");
		window.location.href = "/airport_queryFlightByCity.action?orgCode="+org+"&desCode="+des+"&flightDate="+flightDate;
	});
	$(".nextDayFlight").unbind().bind("click",function(){
		var obj = $(this);
		var org = obj.attr("org");
		var des = obj.attr("des");
		var flightDate=obj.attr("flightDate");
		window.location.href = "/airport_queryFlightByCity.action?orgCode="+org+"&desCode="+des+"&flightDate="+flightDate;
	});
	$(".preDayPlanFlight").unbind().bind("click",function(){
		var obj = $(this);
		var org = obj.attr("org");
		var des = obj.attr("des");
		var flightDate=obj.attr("flightDate");
		var href = "/airport_queryFlightByCity.action?orgCode="+org+"&desCode="+des+"&flightDate="+flightDate;
		window.location.href = href;
	});
	$(".nextDayPlanFlight").unbind().bind("click",function(){
		var obj = $(this);
		var org = obj.attr("org");
		var des = obj.attr("des");
		var flightDate=obj.attr("flightDate");
		window.location.href = "/airport_queryFlightByCity.action?orgCode="+org+"&desCode="+des+"&flightDate="+flightDate;
	});
	$(".preDayFlightByNo").unbind().bind("click",function(){
		var obj = $(this);
		var flightNumber = obj.attr("flightNo"); 
		var flightDate = obj.attr("flightDate");
		window.location.href = "/airport_queryByFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate;
	});
	$(".nextDayFlightByNo").unbind().bind("click",function(){
		var obj = $(this);
		var flightNumber = obj.attr("flightNo"); 
		var flightDate = obj.attr("flightDate");
		window.location.href = "/airport_queryByFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate;
	});
	$(".planFlightDetail").unbind().bind("click",function(){
		$.cookie("listUrl",window.location.href,{path: '/' });
		var obj = $(this);
		var flightNumber = obj.attr("flightNumber");
		var flightDate = obj.attr("flightDate");
		var flag= obj.attr("flag");
		window.location.href = "/airport_queryPlanFlightByNoAndDateAndCycle.action?flightDate="+flightDate+"&flightNumber="+flightNumber+"&flightState="+flag;
	});
	$(".backToQuery").unbind().bind("click",function(){
		var obj = $(this);
		var flightNumber = obj.attr("flightNumber");
		var flightDate = obj.attr("flightDate");
		window.location.href="/airport_backToQuery.action?direct=true&flightNumber="+flightNumber+"&flightDate="+flightDate+"&flag=queryByNo";
	});
}
function selectCity() {
	var keyword = $("#cityKeyword");
	var searchType = $("#searchType");
	if(JM.isNullByJQuery(keyword)){
		keyword.focus();
		return false;
	} else {
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_queryCityInfo.action',
			data:'searchType='+searchType.val()+'&keyword='+JM.encode(keyword)+'&random='+JM.randomNumber+'',
			success:function(data){
				if(JM.SUCCESS == data) {
					window.location.href = '/airport/hangbanchaxun.ticket?direct=true';
				} else {
					keyword.focus();
				}
			}
		});
	}
}
function getAllCityInit(){
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_getAllCity.action',
		data:'flag=domesticCityThreeCode'+'&random='+JM.randomNumber+'',
		success:function(data){
			$("#selectCityDiv").html(data);
			$("#selectCityDiv").find("li").bind("tap", function(){
				$.ajax({
					type:'POST',
					dataType:'text',
					url:'/airport_queryCityInfo.action',
					data:'searchType='+searchType.val()+'&keyword='+JM.encodeByValue($(this).attr("value"))+'&random='+JM.randomNumber+'',
					success:function(data){
						if(JM.SUCCESS == data) {
							window.location.href = '/airport/hangbanchaxun.ticket?direct=true';
						} else {
							keyword.focus();
						}
					}
				});
			});
		}
	});
}
function selectCityAjax() {
	var keyword = $("#cityKeyword");
	var searchType = $("#searchType");
	var domOrInt = $("#domOrInt").val();
	if(JM.isNullByJQuery(keyword)){
		keyword.focus();
		$("#selectCityDiv").hide();
		return false;
	} else {
		$("#selectCityDiv").show();
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_selectCityAjax.action',
			data:'searchType='+searchType.val()+'&flag='+domOrInt+'&keyword='+JM.encode(keyword)+'&random='+JM.randomNumber+'',
			success:function(data){
				if(!JM.isNull(data)) {
					$("#selectCityDiv").html(data);
					$("#selectCityDiv").find("dd").bind("tap", function(){
						$.ajax({
							type:'POST',
							dataType:'text',
							url:'/airport_queryCityInfo.action',
							data:'searchType='+searchType.val()+'&keyword='+JM.encodeByValue($(this).attr("value"))+'&random='+JM.randomNumber+'',
							success:function(data){
								if(JM.SUCCESS == data) {
									window.location.href = '/airport/hangbanchaxun.ticket?direct=true';
								} else {
									keyword.focus();
								}
							}
						});
					});
				}
			}
		});
	}
}

function isFavorite(){
	
	var objectId = $('.memberFavorite').attr('objectId');
	var objectType = $('.memberFavorite').attr('objectType');
	var title=$('.memberFavorite').attr("title");
	var url=$('.memberFavorite').attr("url");
	
	if(JM.isNull(objectId)){
		
		$.post('memberFavorite_isFavorite.action', {title : title, url : url}, function(json){
			
			if(!JM.isNull(json)){
				
				if(json.status == 'y'){
					$('.memberFavorite').removeClass('icon-star-o');
					$('.memberFavorite').addClass('icon-star');
				}else{
					
					$('.memberFavorite').removeClass('icon-star');
					$('.memberFavorite').addClass('icon-star-o');
				}
			}
		});
		
	}else{
		
		$.post('memberFavorite_isFavorite.action', {objectId : objectId, objectType : objectType}, function(json){
			
			if(!JM.isNull(json)){
				
				if(json.status == 'y'){
					
					$('.memberFavorite').addClass('icon-star');
					$('.memberFavorite').removeClass('icon-star-o');
				}else{
					
					$('.memberFavorite').addClass('icon-star-o');
					$('.memberFavorite').removeClass('icon-star');
				}
			}
		});
	}
}

function flightQueryChange() {
	var orgCity = $("#orgCity");
	var org = $("#org");
	
	var orgCityValue = orgCity.html();
	var orgValue = org.val();
	var orgCityCode = orgCity.attr("cityThreeCode");
	
	orgCity.html(desCity.html());
	orgCity.attr("cityThreeCode", desCity.attr("cityThreeCode"));
	org.html(des.val());
	
	var desCity = $("#desCity");
	var des = $("#des");
	
	desCity.html(orgCityValue);
	desCity.attr("cityThreeCode", orgCityCode);
	des.html(orgValue);
}
function exchangeRate() {
	var fromType = $("#fromType");
	var toType = $("#toType");
	var money = $("#money");
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/baiduExchangeRate_exchange.action',
		data:'from='+fromType.val()+'&to='+toType.val()+'&amount='+money.val()+'&random='+JM.randomNumber+'',
		success:function(data){
			//$("#resultInfoDiv").html(money.val() + $('#fromType option:selected').text() + " = " + data.retData.convertedamount + $('#toType option:selected').text());
			$("#exchange_money").val(data.retData.convertedamount.toFixed(4));
		}
	});
	return false;
}
function exchangeTransfer() {
	/*var fromType = $("#fromType");
	var fromTypeValue = fromType.val();
	var fromTypeText = fromType.find("option:selected").text();
	
	var toType = $("#toType");
	fromType.find("option:selected").html(toType.find("option:selected").text());
	fromType.val(toType.val());
	
	toType.find("option:selected").html(fromTypeText);
	toType.val(fromTypeValue);*/
	var from = $('#fromType option:selected').val();
	var to  = $('#toType option:selected').val();
	$('#fromType').val(to);
	$('#toType').val(from);
	exchangeRate();
	return false;
}
function queryLostGoods(){
	$("#goodsDescript").keyup(function(e){
		 if(this.value.length > 20){
			 this.value = this.value.substring(0, 20);
			 JM.alert('字数已达上限');
			 return false; 
		 }else{
			 $("#descript").val($("#goodsDescript").val());
		 }
		
	});
	$(".queryLostGoods").bind("click",function(){
		var type_id=$("#type_id").val();
		var pickUpTime = $("#pickUpTime").val();
		var pickPosition_id =$("#pickPosition_id").val();
		var color = $("#color").val();
		var goodsDescript = $("#descript").val();
		if(JM.isNull(pickPosition_id)){
			JM.alert("请选择物品遗失位置~");
			return false;
		}
		if(JM.isNull(type_id)){
			JM.alert("请选择物品类别~");
			return false;
		}
		window.location.href="/airport_lostGoodsQuery.action?type_id="
			+type_id+"&pickUpTime="+pickUpTime+"&pickPosition_id="
			+pickPosition_id+"&color="+JM.encodeByValue(color)+"&goodsDescript="+JM.encodeByValue(goodsDescript);
	});
}

function selectCityByList(){
	$("#cityList").find("dd").unbind().bind("tap",function(){
		var searchType = $("#searchType");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_queryCityInfo.action',
			data:'searchType='+searchType.val()+'&keyword='+JM.encodeByValue($(this).attr("value"))+'&random='+JM.randomNumber+'',
			success:function(data){
				if(JM.SUCCESS == data) {
					window.location.href = '/airport/hangbanchaxun.ticket?direct=true';
				} else {
					searchType.focus();
				}
			}
		});
	});
}
/**
 * 更新各人资料
 * @return
 */
function updatePersonalData(){
	//修改个人其它信息
	$("#updateOtherInfo").bind("click",function(){
		var formStr = "";
		$("font").each(function(){
		   var name = $(this).attr("name");
		   var value = $(this).text();
		   formStr += name + '=' + $.trim(value) + '&';
		});
		$.ajax({
			type:'POST',
			datatype:'text',
			url:'/airportm_editMemberCompanyInfo.action',
		   	data:formStr,
		   	success:function(data){
				data = $.parseJSON(data);
				if(data.status=="y"){
					JM.alert(data.info);
				}else{
					JM.alert(data.info);
				}
			}
		});
	});
	
	//修改个人公司信息
	$("#updateCompanyInfo").bind("click",function(){
		var formStr = "";
		$("font").each(function(){
		   var name = $(this).attr("name");
		   var value = $(this).text();
		   formStr += name + '=' + $.trim(value) + '&';
		});
		$.ajax({
			type:'POST',
			datatype:'text',
			url:'/airportm_editMemberCompanyInfo.action',
		   	data:formStr,
		   	success:function(data){
				data = $.parseJSON(data);
				if(data.status=="y"){
					JM.alert(data.info);
				}else{
					JM.alert(data.info);
				}
			}
		});
	});
	
	//修改个人详细信息
	$("#updateDetailInfo").bind("click",function(){
		var json = {};
		$("font").each(function(){
		   var name = $(this).attr("name");
		   var value = $.trim($(this).html());
		   json[name] = value;
		});
		var formStr = $.param(json);
		$.ajax({
			type:'POST',
			datatype:'text',
			url:'/airportm_editMemberDetailInfo.action',
		   	data:formStr,
		   	success:function(data){
				data = $.parseJSON(data);
				if(data.status=="y"){
					JM.alert(data.info);
				}else{
					JM.alert(data.info);
				}
			}
		});
	});
	
	
	//修改个人基本信息
	$("#updateBaseInfo").bind("click",function(){
		var formStr = "";
		$("font").each(function(){
		   var name = $(this).attr("name");
		   var value = $(this).text();
		   formStr += name + '=' + $.trim(value) + '&';
		});
		$.ajax({
			type:'POST',
			datatype:'text',
			url:'/airportm_editMemberBaseInfo.action',
		   	data:formStr,
		   	success:function(data){
			data = $.parseJSON(data);
				if(data.status=="y"){
					JM.alert(data.info);
				}else{
					JM.alert(data.info);
				}
			}
		});
	});
	
	//修改个人教育信息
	$("#updateEducationInfo").bind("click",function(){
		var formStr = "";
		$("font").each(function(){
		   var name = $(this).attr("name");
		   var value = $(this).text();
		   formStr += name + '=' + $.trim(value) + '&';
		});
		$.ajax({
			type:'POST',
			datatype:'text',
			url:'/airportm_editMemberEducationInfo.action',
		   	data:formStr,
		   	success:function(data){
				data = $.parseJSON(data);
				if(data.status=="y"){
					JM.alert(data.info);
				}else{
					JM.alert(data.info);
				}
			}
		});
	});
}

function unFavorite(id){
	var cancelFavorite = window.confirm("确定取消收藏么？");
	if(cancelFavorite){
		JM.alert('请求中...', 0);
		$.post('airportm_unFavorite.action', 
			{
				id : id
			},
			function(json){
				
				if(json.status == 'y'){
					
					JM.alert(json.info, 2000, function(){
						
						JM.reload();
					});
				}else{
					
					JM.alert(json.info, 2000);
				}
				
			}, 'json');
		return;
	}else{
		return;
	}
}

/**
 * 根据交通车站搜索路线
 * @return
 */
function searchTrafficLineByStation(){
	var keyword = $("#stationKeyword");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_selectStationAjax.action',
		data:'keyword='+JM.encode(keyword)+'&random='+JM.randomNumber+'',
		success:function(data){
			if(!JM.isNull(data)) {
				$("#selectStationDiv").html(data);
				$("#selectStationDiv").find("li").bind("tap", function(){
					var id = $(this).attr("value");
					$.ajax({
						type:'POST',
						dataType:'text',
						url:'/airport_queryTrafficLineByStationId.action',
						data:'id='+id+'&random='+JM.randomNumber+'',
						success:function(data){
							if(JM.SUCCESS == data) {
								window.location.href = "/airport_resultBySearchTraffic.action";
							} else {
								keyword.focus();
							}
						}
					});
				});
			}
		}
	});
	
}

function changLuggageState(){
	/**
	 * 国内出发有无行李切换
	 */
	$(".SDChangeLuggageState").find("li").unbind().bind("tap",function(){
		var obj = $(this);
		var flag = obj.attr("flag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airportm_SDAjax.action',
			data:'flag='+flag,
			success:function(data){
				$("#SDContent").html(data);
			}
		});
	});
	/**
	 * 国际出发有无行李切换
	 */
	$(".SIChangeLuggageState").find("li").unbind().bind("tap",function(){
		var obj = $(this);
		var flag = obj.attr("flag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airportm_SIAjax.action',
			data:'flag='+flag,
			success:function(data){
			$("#SIContent").html(data);
		}
		});
	});
	/**
	 * 中转有无行李切换
	 */
	$(".TSchangeLuggageState").find("li").unbind().bind("tap",function(){
		var obj = $(this);
		var flag = obj.attr("flag");
		var transferFlag = $("#transferFlag").val();
		//国内转国内
		if(transferFlag=='DTD'){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'airportm_DTDAjax.action',
				data:'flag='+flag,
				success:function(data){
					$("#TSContent").html(data);
				}
			});
		}
		//国内转国际
		else if(transferFlag=='DTI'){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'airportm_DTIjax.action',
				data:'flag='+flag,
				success:function(data){
					$("#TSContent").html(data);
				}
			});
		}
		//国际转国内
		else if(transferFlag=='ITD'){
			$.ajax({
				type:'POST',
				dataType:'text',
				url:'airportm_ITDAjax.action',
				data:'flag='+flag,
				success:function(data){
					$("#TSContent").html(data);
				}
			});
		}
		else{
			return false;
		}
	});
	
	$(".RDChangeLuggageState").find("li").unbind().bind("tap",function(){
		var obj = $(this);
		var flag = obj.attr("flag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airportm_RDAjax.action',
			data:'flag='+flag,
			success:function(data){
				$("#RDContent").html(data);
			}
		});
	});
	
	$(".RIChangeLuggageState").find("li").unbind().bind("tap",function(){
		var obj = $(this);
		var flag = obj.attr("flag");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'airportm_RIAjax.action',
			data:'flag='+flag,
			success:function(data){
			$("#RIContent").html(data);
		}
		});
	});
}
/**
 * 提示下载app
 * @return
 */
function tipDownloadApp(){
	var isYes = window.confirm("仅APP支持导航，是否下载？");
	if(isYes){
		JM.alert("接口申请中……");
	}else{
		return;
	}
}

/**
 * 检测手机是否安装了app
 * @return
 */
function ifInstallApp(){
 if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {
    var loadDateTime = new Date();
	    window.setTimeout(function() {
	      var timeOutDateTime = new Date();
	      if (timeOutDateTime - loadDateTime < 5000) {
	    	  window.location = "/airport/wangshangzhiji/app.ticket";
	      } else {
	        window.close();
	      }
	    },25);
	    window.location = " apps custom url schemes ";
  } else if (navigator.userAgent.match(/android/i)) {
    var state = null;
    try {
      state = window.open("apps custom url schemes ", '_blank');
    } catch(e) {}
    if (state) {
      window.close();
    } else {
      window.location = "/airport/wangshangzhiji/app.ticket";
    }
  } else{
	  window.location.href = '/airport/wangshangzhiji.ticket';
  }
}
function buyTicketAjax() {
	$(".buyTicketAjax").find("li").bind("tap",function(){
		//var news_id = $(this).attr("sysObjId");
		var news_id = $(this).attr("ticketNoticeId");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_buyTicketNoticeAjax.action',
			data:'id='+news_id,
			success:function(data){
//				$(".sysObjContent").html(data);
				$(".ticketNoticeContent").html(data);
			}
		});
	});
}

function saveMenuId(dom){
	
	var id = $(dom).attr('id');
	var href = $(dom).attr('href_');
	$.post('base_saveMenuId.action', {id : id}, function(){
		
		window.location = href;
	});
}

/**
 * 判断是否是App请求，如果是则将头部信息加到title中
 */
function isFormAppHasHeader(){
	var isFormApp = $(".mobile-top").css("display");
	var header = $(".header").text();
	if(isFormApp == "none"){
		$("title").text(header);
	}
}

function memberFocusFligtInit(){
	//会员关注航班
	$(".memberFocusFlight").unbind().bind("click",function(){
		var obj = $(this);
		var flightNumber = obj.attr("flightNumber");
		var flightDate = obj.attr("flightDate");
		var flightState = obj.attr("flightState");
		var stopover = obj.attr("stopover");
		var fromList = obj.attr("fromList");
		var plan = obj.attr("plan");//计划航班标识
		if(!JM.isNull(fromList)){
			$.cookie("listUrl",window.location.href,{path: '/' });
		}
		
		var sessionMember = $("#sessionMember").val();
		var mid = $("#mid").val();
		if(!JM.isNull($.cookie("flag"))){
			$.ajax({
				type:'POST',
				datatype:'text',
				url:'airportm_checkFlightDateByActive.action',
				data:'flightNumber='+flightNumber+'&flightDate='+flightDate,
				success:function(data){
					if(data.status == JM.YES){
						window.location.href='/airportm_focusFlight.action?flightNumber='+flightNumber+'&flightDate='+flightDate+"&flightState="+flightState+"&stopover="+stopover;
					}else{
						JM.alert("该航班时间不符合要求，不能激活！");
						return;
					}
				}
			});
		}else{
			var preUrl;
			if(plan=="plan"){
				preUrl = "/airport_queryPlanFlightByNoAndDateAndCycle.action?flightDate="+flightDate+"&flightNumber="+flightNumber+"&flightState="+flightState+"&forFocus=true";
				
			}else{
				if(flightState=='depart'){
					
					preUrl = "/airport_queryDepartFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&flightState="+flightState+"&stopover="+stopover+"&forFocus=true";
				}else{
					
					preUrl = "/airport_queryArrivalFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState+"&forFocus=true";
				}
			}
			//var preUrl = window.location.href+"&forFocus=true";
			$.cookie("preUrl",preUrl,{path: '/' });
			
			if(JM.isNull(sessionMember)&&JM.isNull(mid)){
				var isYes = window.confirm("您还未登录，是否登录？");
				if(isYes){
					
					window.location.href = "/airportm_login.action?flag=focus";
				}else{
					return false;
				}
			}else{
				$.ajax({
					type:'POST',
					dataType:'json',
					url:'airportm_memberFocusFlight.action',
					data:'flightNumber='+flightNumber+'&flightDate='+flightDate+"&flightState="+flightState+"&stopover="+stopover,
					success:function(val){
						if(val.status == JM.YES){
							JM.alert('关注成功!');
							if(JM.isNull(fromList)){
								
								window.location.reload();
							}else{
								if(plan=="plan"){
									window.location.href = "/airport_queryPlanFlightByNoAndDateAndCycle.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&flightState="+flightState;
									
								} else{
									if(flightState=='depart'){
										
										window.location.href = "/airport_queryDepartFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover;
									}else{
										
										window.location.href = "/airport_queryArrivalFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState;
									}
								}
							}
							return;
						}else{
							JM.alert(val.info);
							if(JM.isNull(fromList)){
								return;
								//window.location.reload();
							}else{
								if(plan=="plan"){
									
									window.location.href = "/airport_queryPlanFlightByNoAndDateAndCycle.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&flightState="+flightState;
								} else{
									if(flightState=='depart'){
										
										window.location.href = "/airport_queryDepartFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState;
									}else{
										
										window.location.href = "/airport_queryArrivalFlightNoAndDate.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&stopover="+stopover+"&flightState="+flightState;
									}
								}
							}
							return;
						}
					}
				});
			}
		}
		
	});
	
	//旅程助手
	$(".journeyHelper").unbind().bind("click",function(){
		$.cookie("whereUrl",window.location.href,{path: '/' });
		var sessionMember = $("#sessionMember").val();
		var mid = $("#mid").val();
		var obj = $(this);
		var flightNumber = obj.attr("flightNumber");
		var flightDate = obj.attr("flightDate");
		var flightState = obj.attr("flightState");
		var stopover = obj.attr("stopover");
		if(JM.isNull(sessionMember)&&JM.isNull(mid)){
			var isYes = window.confirm("您还未登录，是否登录？");
			if(isYes){
				window.location.href = "airportm_journeyHelper.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&flightState="+flightState+"&stopover="+stopover;
			}else{
				return false;
			}
		}else{
			window.location.href = "airportm_journeyHelper.action?flightNumber="+flightNumber+"&flightDate="+flightDate+"&flightState="+flightState+"&stopover="+stopover;
		}
	});
	
	//定制服务
	$(".customizedService").unbind().bind('click',function(){
		var obj = $(this);
		var id = obj.attr("id");
		window.location.href = "airportm_customizedService.action?id="+id;
	});
	
	$(".flightNavigation").unbind().bind('click',function(){
		var id = $(this).attr("id");
		$.cookie("whereUrl",window.location.href,{path: '/' });
		window.location.href="/airportm_navigation.action?id="+id;
	});
	
//	$(".journeyGuide").unbind().bind('click',function(){
//		var id = $(this).attr("id");
//		$.cookie("whereUrl",window.location.href,{path: '/' });
//		window.location.href="/airportm_journeyGuide.action?id="+id;
//	});
}

function initFocusFlight(){
	 var forFocus = $("#forFocus").val();
	 var flightNumber = $("#flightNumber").val();
	 var flightDate = $("#flightDate").val();
	 var flightState = $("#flightState").val();
	 var stopover = $("#stopover").val();
	 if(forFocus!=""){
		 $("#forFocus").val("");
		 $.ajax({
			type:'POST',
			dataType:'json',
			url:'airportm_memberFocusFlight.action',
			data:'flightNumber='+flightNumber+'&flightDate='+flightDate+'&flightState='+flightState+'&stopover='+stopover,
			success:function(val){
				if(val.status == JM.YES){
					window.location.reload();
					JM.alert('关注成功!');
					return;
				}else{
					window.location.reload();
					JM.alert(val.info);
					return;
				}
			}
		});
	 }
}

/**
 * 撤销值机分享
 */
function initRevokeShare(){
	$("#revokeShare").bind("click",function(){
		var ensure = window.confirm("确定取消分享么？");
		if(ensure){
			var ticketNo = $(this).attr("ticketNo");
			var id=$(this).attr("value");
			$.ajax({
				type:'POST',
				datatype:'json',
				url:'/airportm_revokeShare.action',
				data:'ticketNo='+ticketNo+"&id="+id,
				success:function(val){
					if(val.status=JM.YES){
						JM.alert("撤销分享成功！");
						window.location.reload();
					}else{
						JM.alert("撤销分享失败！");
						return false;
					}
				}
			});
		}else{
			return false;
		}
	});
}

/**
 * 选择套餐提示
 */
function showPackageDetail(){
	
	$('#package').change(function(){
		
		var value = $(this).val();
		var description = $('[value="' + value + '"]').attr('description');
		JM.alert(description);
	});
}