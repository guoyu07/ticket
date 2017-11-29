
 /* jQuery.gu --  Copyright ©2013 羿鸣传播-设计师：知道分子-蛊 常用前端效果封装*/
 
 
 
 //智能定位
$.fn.smartFloat = function() {
	var position = function(element) {
		var top = element.position().top, pos = element.css("position");
		$(window).scroll(function() {
			var scrolls = $(this).scrollTop();
			if (scrolls > top) {
				if (window.XMLHttpRequest) {
					element.css({
						position: "fixed",
						top: 0
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












//全屏banner
$(document).ready(function(){
	
	funcfoucs();
	
	$(".pict").hover(function(){
		$(this).addClass("jhover");
	},function(){
		$(this).removeClass("jhover");
	});
	
});




jQuery.extend( jQuery.easing,{
	def: 'easeOutQuint',
	swing: function (x, t, b, c, d) {
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	}
});

function funcfoucs(){
	
	var _imgArray = new Array();
	
	$(".foucs").find(".main").css({
		"position":"absolute"
	});
	for(var i = 0 ; i < $(".foucs").find(".element").length ;i++){
		if($(".foucs").find(".element").eq(i).find("img").attr("src")){
			_imgArray.push($(".foucs").find(".element").eq(i).find("img").attr("src"));
		}
	}
	if(/*@cc_on!@*/false){
		//IE
		setTimeout(startslide,400);
	}else{
		//Non IE
		if(_imgArray.length){
			loopImageLoader(0);
		}else{
			setTimeout(startslide,400);
		}
	}
	
	function loopImageLoader(i){
	  var image1 = new Image();
	  image1.src = _imgArray[i];
	  image1.onload = function(){
		i++;
		if(_imgArray.length != i){
		  loopImageLoader(i);
		}else{
			startslide();
		}
	  }
	}
	
	
	var _maxpage = 0;
	var _currentpage = 0;
	
	function startslide(){
		$(".foucs").find(".element").css("display","inline-block");
		
		$(".foucs").find(".right").hide();
		$(".foucs").find(".left").hide();
		
		$(".foucs").find(".right").fadeIn(600);
		$(".foucs").find(".left").fadeIn(600);
		

		_maxpage = $(".foucs").find(".pict").length;
		
		for(var i = 0 ; i < _maxpage ; i++){
			var _pos = Math.round(1440*(i-_currentpage)+$(window).width()/2-720);
			var _opa = 0.5;
			if(i == _currentpage)_opa = 1;
			if(_pos > $(window).width()){
				_pos -= _maxpage*1440
			}else if(_pos < -1440){
				_pos += _maxpage*1440
			}
			$(".foucs").find(".pict").eq(i).css({
				left:_pos,
				opacity:0
			})
			.animate({
				opacity:_opa
			},{
				duration:400 ,
				easing:'linear'
			})
		}
		$(".foucs").stop().find(".main").removeClass("main");
		$(".foucs").stop().find(".pict").eq(_currentpage).addClass("main").css({"position":"absolute"});
		
		
		window.onresize = function(){
			for(var i = 0 ; i < _maxpage ; i++){
				var _pos = Math.round(1440*(i-_currentpage)+$(window).width()/2-720);
				var _opa = 0.5;
				if(i == _currentpage)_opa = 1;
				if(_pos > $(window).width()){
					_pos -= _maxpage*1440
				}
				$(".foucs").stop().find(".pict").eq(i).css({
					left:_pos,
					opacity:_opa
				})
			}
		}
		$(".foucs").find(".right").click(nextPage);
		$(".foucs").find(".left").click(prevPage);
	}

	
	function nextPage(){
		_currentpage++;
		if(_currentpage >  _maxpage-1)_currentpage = 0;
		$(".foucs").stop().find(".main").removeClass("main");
		$(".foucs").stop().find(".pict").eq(_currentpage).addClass("main").css({"position":"absolute"});;
		_pict = $(".foucs").find(".pict");
		for(var i = 0 ; i < _maxpage ; i++){
			var _pos = Math.round(1440*(i-_currentpage)+$(window).width()/2-720);
			var _opa = 0.5;
			if(i == _currentpage)_opa = 1;
			if(_pos > $(window).width()){
				_pos -= _maxpage*1440
			}else if(_pos < -1440*2){
				_pos += _maxpage*1440
			}
			_pict.eq(i)
			.stop()
			.css({
				left:_pos+1440
			})
			.animate({
				left:_pos,
				opacity:_opa
			},{
				duration:700 ,
				easing:'easeOutQuint'
			})
		}
	}
	
	function prevPage(){
		_currentpage--;
		if(_currentpage< 0)_currentpage = _maxpage -1;
		$(".foucs").stop().find(".main").removeClass("main");
		$(".foucs").stop().find(".pict").eq(_currentpage).addClass("main").css({"position":"absolute"});;
		for(var i = 0 ; i < _maxpage ; i++){
			var _pos = Math.round(1440*(i-_currentpage)+$(window).width()/2-720);
			var _opa = 0.5;
			if(i == _currentpage)_opa = 1;
			if(_pos < -1440){
				_pos += _maxpage*1440
			}else if(_pos > $(window).width()+1440){
				_pos -= _maxpage*1440
			}
			$(".foucs").find(".pict").eq(i)
			.stop()
			.css({
				left:_pos-1440
			})
			.animate({
				left:_pos,
				opacity:_opa
			},{
				duration:700 ,
				easing:'easeOutQuint'
			})
		}
	}
	
	
}












/* 

滚动图片封装库

 */
$(document).ready(function(){                          
    var index=0;
    var length=$("#img li").length;
    var i=1;
    
    //关键函数：通过控制i ，来显示图片
    function showImg(i){
        $("#img li")
            .eq(i).stop(true,true).fadeIn(800)
            .siblings("li").hide();
         $("#cbtn li")
            .eq(i).addClass("hov")
            .siblings().removeClass("hov");
    }
    
    function slideNext(){
        if(index >= 0 && index < length-1) {
             ++index;
             showImg(index);
			 
        }else{
			if(confirm("已经是最后一张,点击确定重新浏览！")){
				showImg(0);
				index=0;
				aniPx=(length-5)*129+'px'; //所有图片数 - 可见图片数 * 每张的距离 = 最后一张滚动到第一张的距离
				$("#cSlideUl ul").animate({ "left": "+="+aniPx },200);
				i=1;
			}
            return false;
        }
        if(i<0 || i>length-5) {return false;}						  
               $("#cSlideUl ul").animate({ "left": "-=129px" },200)
               i++;
    }
     
    function slidePrev(){
       if(index >= 1 ) {
             --index;
             showImg(index);
        }
        if(i<2 || i>length+5) {return false;}
               $("#cSlideUl ul").animate({ "left": "+=129px" },200)
               i--;
    }	
        $("#img li").eq(0).show();
        $("#cbtn li").eq(0).addClass("hov");
        $("#cbtn tt").each(function(e){
            var str=(e+1)+"/"+length;
            $(this).html(str)
        })
    
        $(".picSildeRight,#next").click(function(){
               slideNext();
           })
        $(".picSildeLeft,#prev").click(function(){
               slidePrev();
           })
        $("#cbtn li").click(function(){
            index  =  $("#cbtn li").index(this);
            showImg(index);
        });	
		$("#next,#prev").hover(function(){
			$(this).children("a").fadeIn();
		},function(){
			$(this).children("a").fadeOut();
		})
    })	



//回到顶部
$(document).ready(function(){

//首先将#back-to-top隐藏

 $("#back-to-top").hide();

//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失

$(function () {
$(window).scroll(function(){
if ($(window).scrollTop()>100){
$("#back-to-top").fadeIn(1500);
}
else
{
$("#back-to-top").fadeOut(1500);
}
});

//当点击跳转链接后，回到页面顶部位置

$("#back-to-top").click(function(){
$('body,html').animate({scrollTop:0},1000);
return false;
});
});
});

//会员中心奠基展开

$(document).ready(function()
{
	$("#firstpane p.menu_head").click(function()
    {
		$(this).css({"backgroundImage":"url(/manager/images/up.gif)","background-color":"#333333"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
       	$(this).siblings().css({"backgroundImage":"url(/manager/images/down.gif)","background-color":"#484848"});
	});

});

function leftClick(obj) {
	$(obj).css({"backgroundImage":"url(/manager/images/up.gif)","background-color":"#333333"}).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
   	$(obj).siblings().css({"backgroundImage":"url(/manager/images/down.gif)","background-color":"#484848"});
}


	
//上下展开
	
	$(document).ready(function(){

	$(".btn-slide").click(function(){
		$("#panel").slideToggle("slow");
		$(this).toggleClass("active"); return false;
	});
	
	 
});
	



//*****************************jQuery弹出层************************************************************//

$(document).ready(function (){
	 $("#showdiv").click(function(){
	 $("#popupLayer").slideDown();
	 	$("#popupbgLayer").fadeIn(200);
	$("#popupIframeLayer").fadeIn(100);
//弹出隐藏层
});

	 
});

$(document).ready(function (){
    $("#closeDiv").click(function(){
	$("#popupLayer").slideUp();
	$("#popupbgLayer").fadeOut(200);
	$("#popupIframeLayer").fadeOut(100);

	});
	
	
	
});//关闭弹出层


