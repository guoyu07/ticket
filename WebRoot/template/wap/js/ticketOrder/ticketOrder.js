$(function(){
	initialSelectCityPre();
	initTicketOrderClass();
	ticketOrderClassInit();
	changeCityOpiInit();
});
/**
 * 初始化城市选择的事件
 * @return
 */
function initTicketOrderClass() {
	$(".ticketOrderSelectCityClass").unbind().bind("click", function(){
		var cityCode = $(this).attr("cityCode");
		var cityName = $(this).attr("cityName");
		var ticketType = $("#ticketType");
		var buyType = $("#buyType");
		var cityType = $("#cityType");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/ticketOrder_setCitySelect.action',
			data:'cityType='+JM.encode(cityType)+'&ticketType='+JM.encode(ticketType)+'&buyType='+JM.encode(buyType)+'&cityCode='+JM.encodeByValue(cityCode)+'&cityName='+JM.encodeByValue(cityName)+'&random='+JM.randomNumber,
			success:function(data){
				if("domestic" == ticketType.val()) {
					if("single" == buyType.val()) {
						window.location.href = '/ticketOrder.action?direct=true';
					} else if("goAndBack" == buyType.val()) {
						window.location.href = '/ticketOrder_goAndBack.action?direct=true';
					}
				} else if("international" == ticketType.val()) {
					if("single" == buyType.val()) {
						window.location.href = '/ticketOrder_international.action?direct=true';
					} else if("goAndBack" == buyType.val()) {
						window.location.href = '/ticketOrder_internationalGoAndBack.action?direct=true';
					}
				}
			}
		});
		$(".ticketOrderSelectCityClass").css("cursor", "pointer");
		return false;
	});
	$("#queryCityKeyword").bind("keyup", function(){
		selectCityByKeyword();
	}).bind("keydown", function(){
		selectCityByKeyword();
	}).bind("input", function(){
		selectCityByKeyword();
	}).bind("keypress",function(){
		selectCityByKeyword();
	});
}
function initialSelectCityPre() {
	$(".selectCityClass").unbind().bind("click", function(){
		//alert("toSelect");
		window.location.href = '/ticketOrder_selectCity.action?ticketType='+$(this).attr("ticketType")+'&buyType='+$(this).attr("buyType")+'&cityType='+$(this).attr("cityType")+'&random='+JM.randomNumber;
	});
	$(".selectCityClass").css("cursor", "pointer");
}
function ticketOrderClassInit() {
	$(".ticketOrderBtnClass").unbind().bind("click", function(){
		var date1 = $("#date1");
		var date2 = $("#date2");
		var city1 = $("#city1");
		var city2 = $("#city2");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/ticketOrder_parseInfoToGetUrl.action',
			data:'ticketType='+city1.attr("ticketType")+'&buyType='+city1.attr("buyType")+'&date1='+date1.val()+'&date2='+date2.val()+'&city1='+city1.attr("cityCode")+'&city2='+city2.attr("cityCode")+'&random='+JM.randomNumber,
			success:function(data){
				if(!JM.isNull(data) && data != '#'){
					window.location.href = data;
				}
			}
		});
		return false;
	});
}
function changeCityOpiInit() {
	$(".changeCityPoiOrder").bind("click",function(){
		$(".changeCityPoiOrder").removeClass("selected");
		$(this).addClass("selected");
		var cityPoi = $(this).attr("cityPoi");
		$("#cityPoi").val(cityPoi);
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airport_changeCityPositionOrder.action',
			data:'flag='+cityPoi+'&random='+JM.randomNumber+'',
			success:function(data){
				$("#cityList").html(data);
				initTicketOrderClass();
			}
		});
	});
}
function selectCityByKeyword(){
	var keyword = $("#queryCityKeyword");
	var domOrInt = $("#domOrInt").val();
	var ticketType = $("#ticketType");
	var buyType = $("#buyType");
	var cityType = $("#cityType");
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
			data:'flag='+domOrInt+'&keyword='+JM.encode(keyword)+'&random='+JM.randomNumber+'',
			success:function(data){
				if(!JM.isNull(data)) {
					$("#selectCityDiv").html(data);
					$("#selectCityDiv").find("dd").bind("tap", function(){
						$.ajax({
							type:'POST',
							dataType:'text',
							url:'/ticketOrder_setCitySelect.action',
							data:'cityType='+JM.encode(cityType)+'&ticketType='+JM.encode(ticketType)+'&buyType='+JM.encode(buyType)+'&cityCode='+JM.encodeByValue($(this).attr("cityCode"))+'&cityName='+JM.encodeByValue($(this).attr("cityName"))+'&random='+JM.randomNumber,
							success:function(data){
								if("domestic" == ticketType.val()) {
									if("single" == buyType.val()) {
										window.location.href = '/ticketOrder.action?direct=true';
									} else if("goAndBack" == buyType.val()) {
										window.location.href = '/ticketOrder_goAndBack.action?direct=true';
									}
								} else if("international" == ticketType.val()) {
									if("single" == buyType.val()) {
										window.location.href = '/ticketOrder_international.action?direct=true';
									} else if("goAndBack" == buyType.val()) {
										window.location.href = '/ticketOrder_internationalGoAndBack.action?direct=true';
									}
								}
							}
						});
					});
				}
			}
		});
	}
	
}