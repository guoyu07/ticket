 //智能定位
$.fn.smartFloat = function() {
	var position = function(element) {
		var top = element.position().top, pos = element.css("position");
		$(window).scroll(function() {
			var scrolls = $(this).scrollTop();
			if (scrolls > 0) {
				if (window.XMLHttpRequest) {
					element.css({
						position: "fixed",
						bottom: 0
					});	
				} else {
					element.css({
						top: scrolls
					});	
				}
			}else {
				element.css({
					position: pos,
					top: top
				});	
			}
		});
};
	return $(this).each(function() {
		position($(this));						 
	});
};

$(function(){
	$("#float_nav").smartFloat();
	
});


//回到顶部
$(document).ready(function(){

	 $(".bill_top").hide();
	
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	
	$(function () {
	$(window).scroll(function(){
		if ($(window).scrollTop()>100){
		$(".bill_top").fadeIn(400);
		}
		else
		{
		$(".bill_top").fadeOut(400);
		}
	});

//当点击跳转链接后，回到页面顶部位置

	$(".bill_top").click(function(){
	$('body,html').animate({scrollTop:0},500);
	   return false;});
    });
});



//上下展开
	
$(document).ready(function(){
	$("#open1,#open2").bind("click", function(){
		var isOpen = $(this).attr("isOpen");
		var index = $(this).attr("index");
		if(isOpen == 1){
			$(this).attr("isOpen", "0");
			$(".my_list_open" + index).hide();
		} else {
			$(this).attr("isOpen", "1");
			$(".my_list_open" + index).show();
		}	
	});
});

	

//*****************************jQuery弹出层************************************************************//

$(document).ready(function (){
	$(".showdiv").click(function(){
		$("#popupLayer").slideDown();
		$("#popupbgLayer").fadeIn(200);
		$("#popupIframeLayer").fadeIn(100);
		return false;
	//弹出隐藏层
	});
});

$(document).ready(function (){
    $("#closeDiv").click(function(){
		$("#popupLayer").slideUp();
		$("#popupbgLayer").fadeOut(200);
		$("#popupIframeLayer").fadeOut(100);
		return false;
	});
});//关闭弹出层


//*****************************jQuery弹出层************************************************************//

$(document).ready(function (){
	 $(".showdiv_reg").click(function(){
	 $("#popupLayer_reg").slideDown();
	 $("#popupbgLayer").fadeIn(200);
	 $("#popupIframeLayer").fadeIn(100);
//弹出隐藏层
});

	 
});

$(document).ready(function (){
    $("#closeDiv_reg").click(function(){
	$("#popupLayer_reg").slideUp();
	$("#popupbgLayer").fadeOut(200);
	$("#popupIframeLayer").fadeOut(100);

	});
});//关闭弹出层

//******************* 选项卡*****************************//
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
	var menu=document.getElementById(name+i);
	var con=document.getElementById("con_"+name+"_"+i);
	menu.className=i==cursel?"hover":"";
	con.style.display=i==cursel?"block":"none";
	}
}
//**************点击展开关闭效果*********//
function open_zzjs_net(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
	var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
	var openTip = oOpenTip || "";
	var shutTip = oShutTip || "";
	if(targetObj.style.display!="none"){
	   if(shutAble) return;
	   targetObj.style.display="none";
	   if(openTip  &&  shutTip){
		sourceObj.innerHTML = shutTip;
	   }
	}
	else {
	   targetObj.style.display="block";
	   if(openTip  &&  shutTip){
		sourceObj.innerHTML = openTip;
	   }
	}
}
