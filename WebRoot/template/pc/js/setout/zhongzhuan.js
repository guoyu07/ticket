$(function(){
		$("#hava").change(hava);
		$("#one").change(hava);
		$("#two").change(hava);
		
		$(".notLogin").click(function(){
			JM.alert("请先登录,正在跳转。。。", 2000, function(){
				JM.goUrl("pcBjdjMember_toLoginPage.action");
			});
		});
	});

	function hava(){
		var checkit = $("#hava").attr("checked");
		var one = $("#one").val();
		var two = $("#two").val();
		if(checkit != "checked" && one == "国内" && two == "国内"){//无行李,国内到国内
			$("#b01").show();
			$("#b02").hide();
			$("#b03").hide();
			$("#b04").hide();
			$("#b05").hide();
			$("#b06").hide();
			$("#b07").hide();
		}else if(checkit == "checked" && one == "国内" && two == "国内"){//有行李，国内到国内
			$("#b01").hide();
			$("#b02").show();
			$("#b03").hide();
			$("#b04").hide();
			$("#b05").hide();
			$("#b06").hide();
			$("#b07").hide();
		}else if(checkit == "checked" && one == "国内" && two == "国际"){//有行李，国内到国际
			$("#b01").hide();
			$("#b02").hide();
			$("#b03").show();
			$("#b04").hide();
			$("#b05").hide();
			$("#b06").hide();
			$("#b07").hide();
		}else if(checkit != "checked" && one == "国内" && two == "国际"){//无行李，国内到国际
			$("#b01").hide();
			$("#b02").hide();
			$("#b03").hide();
			$("#b04").show();
			$("#b05").hide();
			$("#b06").hide();
			$("#b07").hide();
		}else if(checkit == "checked" && one == "国际" && two == "国内"){//有行李，国际到国内
			$("#b01").hide();
			$("#b02").hide();
			$("#b03").hide();
			$("#b04").hide();
			$("#b05").show();
			$("#b06").hide();
			$("#b07").hide();
		}else if(checkit != "checked" && one == "国际" && two == "国内"){//无行李，国际到国内
			$("#b01").hide();
			$("#b02").hide();
			$("#b03").hide();
			$("#b04").hide();
			$("#b05").hide();
			$("#b06").show();
			$("#b07").hide();
		}else if(one == "国际" && two == "国际"){//国际到国际
			$("#b01").hide();
			$("#b02").hide();
			$("#b03").hide();
			$("#b04").hide();
			$("#b05").hide();
			$("#b06").hide();
			$("#b07").show();
		}
	}
	/*function hava(){
		var checkit = $("#hava").attr("checked");
		var one = $("#one").val();
		var two = $("#two").val();
		//有行李，国内到国内
		if(checkit == "checked" && one == "国内" && two == "国内"){
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic3.png" usemap="#m_pic" alt="" />' +
                '<map name="m_pic" id="m_pic">' +
                   '<area shape="rect" coords="-8,484,136,606" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-6,363,138,476" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-13,243,131,353" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="0,122,144,239" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-4,0,140,119" href="javascript:;" alt="" />'+
                '</map>';
            $(".c_img").html(html);
		}else if(checkit != "checked" && one == "国内" && two == "国内"){//无行李,国内到国内
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic17.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="-6,432,150,565" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-13,289,143,424" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,144,155,278" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="2,2,158,134" href="javascript:;" alt="" />'+
                '</map>';
            $(".c_img").html(html);
		}else if(checkit != "checked" && one == "国内" && two == "国际"){//无行李，国内到国际
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic8.png" width="498" height="618" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="294,254,422,365" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="294,378,422,489" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="294,500,422,611" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,500,219,611" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,376,219,487" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,253,219,364" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,130,219,241" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="91,0,220,125" href="javascript:;" alt="" />'+
                '</map>';
           	$(".c_img").html(html);
		}else if(checkit != "checked" && one == "国际" && two == "国内"){//无行李，国际到国内
			$(".c_img").empty();
			var html = ' <img src="/template/pc/images/area/pic10.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="205,494,333,605" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="1,494,129,605" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="1,370,129,481" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="0,249,128,360" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,122,127,233" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="1,1,129,112" href="javascript:;" alt="" />'+
                '</map>';
            $(".c_img").html(html);
		}else if(checkit == "checked" && one == "国内" && two == "国际"){//有行李，国内到国际
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic18.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="197,492,326,607" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="197,366,326,484" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="197,247,326,355" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="1,486,126,604" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="0,368,125,480" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="3,244,128,362" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-2,120,131,242" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,0,132,120" href="javascript:;" alt="" />'+
                '</map>';
			 $(".c_img").html(html);
		}else if(checkit == "checked" && one == "国际" && two == "国内"){//有行李，国际到国内
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic9.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="1,1,129,112" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,121,127,232" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="0,246,128,357" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,367,127,478" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="-1,493,127,604" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="201,492,329,603" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="201,250,329,361" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="203,373,331,484" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="198,120,330,240" href="javascript:;" />'+
                '</map>';
			$(".c_img").html(html);
		}else if(one == "国际" && two == "国际"){//国际到国际
			$(".c_img").empty();
			var html = '<img src="/template/pc/images/area/pic11.png" usemap="#m_pic" alt="" />'+
                '<map name="m_pic" id="m_pic">'+
                    '<area shape="rect" coords="2,1,130,112" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="2,128,130,239" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="2,243,130,354" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="3,371,131,482" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="2,494,130,605" href="javascript:;" alt="" />'+
                    '<area shape="rect" coords="174,1,306,111" href="javascript:;" />'+
                    '<area shape="rect" coords="173,122,306,236" href="javascript:;" />'+
                    '<area shape="rect" coords="170,246,304,357" href="javascript:;" />'+
                    '<area shape="rect" coords="172,368,306,479" href="javascript:;" />'+
                    '<area shape="rect" coords="169,489,302,603" href="javascript:;" />'+
                    '<area shape="rect" coords="350,1,477,110" href="javascript:;" />'+
                    '<area shape="rect" coords="350,122,476,239" href="javascript:;" />'+
                '</map>';
			$(".c_img").html(html);
		}
		
	}*/