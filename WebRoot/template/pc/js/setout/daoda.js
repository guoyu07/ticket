$(function(){
		$("#one").click(function(){
			$("#one").attr("class","selected");
			$("#two").attr("class","");
		});
		$("#two").click(function(){
			$("#two").attr("class","selected");
			$("#one").attr("class","");
		});
		$("#hava").change(hava);
		$("#one").click(hava);
		$("#two").click(hava);
		$("#chufa").click(selected);
		$("#daoda").click(selected1);
	});

function selected(){
	$("#chufa").attr("class","selected");
	$("#daoda").attr("class","");
	$("#isChufa").val("0");
}

function selected1(){
	$("#chufa").attr("class","");
	$("#daoda").attr("class","selected");
	$("#isChufa").val("1");
}

function hava(){
	var checkit = $("#hava").attr("checked");
	var one = $("#one").attr("class");
	var two = $("#two").attr("class");
	if(checkit != "checked" && one == "selected"){//国内到达无行李
		$("#01").show();
		$("#02").hide();
		$("#03").hide();
		$("#04").hide();
	}else if(checkit != "checked" && two == "selected"){//国际到达无行李
		$("#01").hide();
		$("#02").hide();
		$("#03").show();
		$("#04").hide();
	}else if(checkit == "checked" && two == "selected"){//国际到达有行李
		$("#01").hide();
		$("#02").hide();
		$("#03").hide();
		$("#04").show();
	}else if(checkit == "checked" && one == "selected"){//国内到达有行李
		$("#01").hide();
		$("#02").show();
		$("#03").hide();
		$("#04").hide();
	}
}
	/*function hava(){
		var checkit = $("#hava").attr("checked");
		var one = $("#one").attr("class");
		var two = $("#two").attr("class");
		if(checkit != "checked" && one == "selected"){//国内到达无行李
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic13.png" width="504" height="600" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="172,316,324,462" href="/airportPc_trafficGuide.action" alt="" />'+
                    '<area shape="rect" coords="172,120,324,268" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit != "checked" && two == "selected"){//国际到达无行李
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic15.png" width="493" height="624" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="297,511,403,624" href="/airportPc_trafficGuide.action" alt="" />'+
                    '<area shape="rect" coords="91,511,223,624" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,387,223,497" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,262,223,376" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,135,223,252" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,19,206,125" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit == "checked" && two == "selected"){//国际到达有行李
			$(".c_img").empty();
			var html = ' <img src="/template/pc/images/area/pic14.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="202,245,330,356" href="javascript:;" />'+
                    '<area shape="rect" coords="203,363,331,474" href="javascript:;" />'+
                    '<area shape="rect" coords="201,486,329,597" href="javascript:;" />'+
                    '<area shape="rect" coords="2,484,130,595" href="javascript:;" />'+
                    '<area shape="rect" coords="0,357,128,468" href="javascript:;"/>'+
                    '<area shape="rect" coords="198,126,326,237" href="/airportPc_trafficGuide.action" alt="" />'+
                    '<area shape="rect" coords="-3,117,125,228" href="javascript:;" />'+
                    '<area shape="rect" coords="-4,0,124,111" href="javascript:;" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit == "checked" && one == "selected"){//国内到达有行李
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic12.png" width="496" height="612" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="176,432,324,576" href="/airportPc_trafficGuide.action" alt="" />'+
                    '<area shape="rect" coords="176,231,347,385" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="176,45,320,193" href="javascript:;>" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}
	}*/