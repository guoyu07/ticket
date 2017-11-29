/**
 * pc乘机须知
 */
$(function(){
	initQueryFlight();
//	queryFlightAjax(); 
    $("#queryByDate").append("<option value='"+GetDateStr(1)+"'>"+GetDateStr(1)+"</option>"); 
    $("#queryByDate").append("<option value='"+GetDateStr(2)+"'>"+GetDateStr(2)+"</option>"); 
    $("#queryByDate").append("<option value='"+GetDateStr(3)+"'>"+GetDateStr(3)+"</option>"); 
});

function go(){
	var url = $(this).find("input").val();
	
}

function initQueryFlight(){
	$(".selectFlightFlag").unbind().bind('click',function(){
		var obj = $(this);
		var flightFlag = obj.attr("flightFlag");
		$(".selectFlightFlag").removeClass("selected");
		obj.addClass("selected");
		$("#flightFlag").val(flightFlag);
	});
	
	$("#indexSearchFlight").unbind().bind("click",function(){
		var flightFlag = $("#flightFlag").val();
		var keyword = $("#keyword").val();
		window.location.href = "/airportPc_flightQuery.action?keyword="+keyword+"&flightFlag="+flightFlag;
	});
}

function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    if(m<9){
    	m="0"+m;
    }
    if(d<9){
    	d="0"+d;
    }
    return y+"-"+m+"-"+d;
}