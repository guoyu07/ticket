$(function(){
		$("#one").click(function(){
			$("#one").attr("class","selected");
			$("#two").attr("class","");
		});
		$("#two").click(function(){
			$("#one").attr("class","");
			$("#two").attr("class","selected");
		});
		
		$("#one").click(have);
		$("#two").click(have);
		$("#have").change(have);
		$("#zhiji").change(have);
		
		$('.pic01 area').eq(8).on('click',function(){
            $('.pic02').show();
            $('.pic01').hide();
        });
        $('.pic02 area').eq(0).on('click',function(){
            $('.pic01').show();
            $('.pic02').hide();
        });
        
        $('.pic001 area').eq(8).on('click',function(){
            $('.pic002').show();
            $('.pic001').hide();
        });
        $('.pic002 area').eq(0).on('click',function(){
            $('.pic001').show();
            $('.pic002').hide();
        });
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
	
function have(){
	var checkit = $("#have").attr("checked");
	var one = $("#one").attr("class");
	var two = $("#two").attr("class");
	var zhiji = $("#zhiji").attr("checked");
	if(checkit != "checked" && one == "selected" && zhiji != "checked"){//国内出发无航班信息
		$("#a01").show();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").hide();
	}else if(one == "selected" && zhiji != "checked"){//国内出发无值机
		$("#a01").hide();
		$("#a02").show();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").hide();
	}else if(checkit != "checked" && one == "selected" && zhiji == "checked"){//国内出发无行李已值机
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").show();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").hide();
	}else if(checkit == "checked" && one == "selected" && zhiji == "checked"){//国内出发有行李已值机
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").show();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").hide();
	}else if(checkit == "checked" && two == "selected" && zhiji != "checked"){//国际出发无航班信息
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").show();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").hide();
	}else if(two == "selected" && zhiji != "checked"){//国际出发无值机
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").show();
		$("#a07").hide();
		$("#a08").hide();
	}else if(checkit != "checked" && two == "selected" && zhiji == "checked"){//国际出发无行李已值机
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").show();
		$("#a08").hide();
	}else if(checkit == "checked" && two == "selected" && zhiji == "checked"){//国际出发有行李已值机
		$("#a01").hide();
		$("#a02").hide();
		$("#a03").hide();
		$("#a04").hide();
		$("#a05").hide();
		$("#a06").hide();
		$("#a07").hide();
		$("#a08").show();
	}
}
	/*function have(){
		var checkit = $("#have").attr("checked");
		var one = $("#one").attr("class");
		var two = $("#two").attr("class");
		var zhiji = $("#zhiji").attr("checked");
		if(checkit != "checked" && one == "selected" && zhiji != "checked"){//国内出发无航班信息
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic1.png"  width="545" height="639" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="193,492,375,616" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="193,324,375,474" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="309,195,432,288" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="144,195,273,288" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="193,0,375,158" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit == "checked" && one == "selected" && zhiji == "checked"){//国内出发有行李已值机
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic7.png" width="496" height="604"usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="180,465,341,604" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="180,317,341,450" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="180,165,341,302" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="180,9,341,152" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit != "checked" && one == "selected" && zhiji == "checked"){//国内出发无行李已值机
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic6.png" width="497" height="604" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="171,410,348,569" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="171,216,348,378" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="171,36,348,186" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(one == "selected" && zhiji != "checked"){//国内出发无值机
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic1.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="193,492,375,616" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="193,324,375,474" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="309,195,432,288" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="144,195,273,288" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="193,0,375,158" href="javascript:;" alt="" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(checkit == "checked" && two == "selected" && zhiji == "checked"){//国际出发有行李已值机
			$(".c_img").empty();
			var html = '<div class="pic1">'+
                    '<img src="/template/pc/images/area/pic16_1.png" width="498" height="612" usemap="#m_pic" alt="" />'+
                    '<map name="m_pic" id="m_pic">'+
                        '<area shape="rect" coords="107,188,143,222" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="353,126,469,234" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="353,260,469,368" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="353,390,469,498" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="76,410,192,518" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="76,300,192,408" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="76,0,192,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>'+
                '<div class="pic2" style="display:none">'+
                    '<img src="/template/pc/images/area/pic16.png" width="519" height="627" usemap="#m_pic2" alt="" />'+
                    '<map name="m_pic2" id="m_pic2">'+
                        '<area shape="rect" coords="244,180,288,223" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="367,517,483,627" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="367,387,483,495" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="367,258,483,360" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="367,128,483,230" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,525,216,627" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,413,216,517" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,304,216,402" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="103,198,197,289" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="103,108,197,199" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,0,208,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>';
			$(".c_img").html(html);
		}else if(checkit != "checked" && two == "selected" && zhiji == "checked"){//国际出发无行李已值机
			$(".c_img").empty();
			var html = '<div class="pic1">'+
                    '<img src="/template/pc/images/area/pic4_1.png" width="510" height="623" usemap="#m_pic" alt="" />'+
                    '<map name="m_pic" id="m_pic">'+
                        '<area shape="rect" coords="237,186,275,223" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,520,474,623" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,391,474,499" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,252,474,360" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,527,198,623" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,412,198,514" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,301,198,406" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="94,207,188,288" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="94,115,188,199" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="74,0,198,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>'+
                '<div class="pic2" style="display:none">'+
                    '<img src="/template/pc/images/area/pic4.png" width="510" height="623" usemap="#m_pic2" alt="" />'+
                    '<map name="m_pic2" id="m_pic2">'+
                        '<area shape="rect" coords="237,186,275,223" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,520,474,623" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,391,474,499" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="360,252,474,360" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,527,198,623" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,412,198,514" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="80,301,198,406" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="94,207,188,288" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="94,115,188,199" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="74,0,198,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>';
			$(".c_img").html(html);
		}else if(checkit != "checked" && two == "selected" && zhiji != "checked"){//国际出发无航班信息
			$(".c_img").empty();
			var html = '<div class="pic1 padding-big">'+
                    '<img src="/template/pc/images/area/pic2_1.png" usemap="#m_pic" alt="" />'+
                    '<map name="m_pic" id="m_pic">'+
                        '<area shape="rect" coords="272,389,388,489" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="272,260,388,360" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="271,130,387,233" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="2,410,122,510" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="2,290,131,398" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="29,177,70,213" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="0,0,130,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>'+
                '<div class="pic2 padding-big" style="display:none">'+
                    '<img  src="/template/pc/images/area/pic2.png"  usemap="#m_pic2" alt="" />'+
                    '<map name="m_pic2" id="m_pic2">'+
                        '<area shape="rect" coords="329,129,460,232" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,260,460,368" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="328,389,459,490" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,522,460,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="63,522,194,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="62,412,193,513" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="67,306,196,401" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="224,184,260,220" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,205,175,287" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,114,175,192" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="61,1,186,103" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>';
			$(".c_img").html(html);
		}else if(two == "selected" && zhiji != "checked"){//国际出发无值机
			$(".c_img").empty();
			var html = '<img  src="/template/pc/images/area/pic2.png" usemap="#m_pic2" alt="" />'+
                    '<map name="m_pic2" id="m_pic2">'+
                        '<area shape="rect" coords="329,129,460,232" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,260,460,368" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="328,389,459,490" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,522,460,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="63,522,194,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="62,412,193,513" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="67,306,196,401" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="224,184,260,220" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,205,175,287" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,114,175,192" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="61,1,186,103" href="javascript:;" alt="" />'+
                    '</map>';
			$(".c_img").html(html);
		}else if(checkit == "checked" && two == "selected" && zhiji != "checked"){//国际出发无航班信息
			$(".c_img").empty();
			var html = '<div class="pic1 padding-big">'+
                    '<img src="/template/pc/images/area/pic2_1.png" usemap="#m_pic" alt="" />'+
                    '<map name="m_pic" id="m_pic">'+
                        '<area shape="rect" coords="272,389,388,489" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="272,260,388,360" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="271,130,387,233" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="2,410,122,510" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="2,290,131,398" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="29,177,70,213" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="0,0,130,108" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>'+
                '<div class="pic2 padding-big" style="display:none">'+
                    '<img  src="/template/pc/images/area/pic2.png"  usemap="#m_pic2" alt="" />'+
                    '<map name="m_pic2" id="m_pic2">'+
                        '<area shape="rect" coords="329,129,460,232" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,260,460,368" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="328,389,459,490" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="329,522,460,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="63,522,194,620" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="62,412,193,513" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="67,306,196,401" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="224,184,260,220" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,205,175,287" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="86,114,175,192" href="javascript:;" alt="" />'+
                        '<area shape="rect" coords="61,1,186,103" href="javascript:;" alt="" />'+
                    '</map>'+
                '</div>';
			$(".c_img").html(html);
		}
	}*/