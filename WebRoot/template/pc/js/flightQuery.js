

$(function (){
	$("#flightQueryByCity").on("click",flightQueryByCity);
	$("#flightQueryByFlightNumber").on("click",flightQueryByFlightNumber);
})


/**
 * 根据航班号查询航班
 * @return
 */
function flightQueryByFlightNumber(){
	var flightDate = $("#flightDate");
	var flightNumber = $("#flightNumber");
	if(JM.isNullByJQuery(flightDate)){
		alert("请选择查询日期");
		return false;
	}
	if(JM.isNullByJQuery(flightNumber)){
		alert("请输入航班号");
		flightNumber.focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airportPc_queryFlightByNoAndDate.action',
		data:'flightDate='+flightDate.val()+"&flightNumber="+flightNumber.val(),
		success:function(data){
			$("#flightQueryResultList").html(data);
		}
	});
}

/**
 * 根据城市查询航班
 * @return
 */
function flightQueryByCity(){
	var flightDate = $("#flightDate");
	var setoutCity = $("#setoutCity");
	var reachCity = $("#reachCity");
	if(JM.isNullByJQuery(flightDate)){
		alert("请选择查询日期");
		return false;
	}
	if(JM.isNullByJQuery(setoutCity)){
		alert("请输入出发城市");
		setoutCity.focus();
		return false;
	}
	if(JM.isNullByJQuery(reachCity)){
		alert("请输入到达城市");
		reachCity.focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airportPc_queryFlightByCity.action',
		data:'flightDate='+flightDate.val()+"&setoutCity="+setoutCity.val()+"&reachCity="+reachCity.val(),
		success:function(data){
			$("#flightQueryResultList").html(data);
		}
	});
}