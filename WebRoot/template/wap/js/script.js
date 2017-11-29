/* 
* @Author: gavin
* @Date:   2015-06-26 10:47:02
* @Last Modified by:   gavin
* @Last Modified time: 2015-12-29 10:02:11
*/
var ops={};
ops.click=true;
ops.swipe = true;

/*常规*/
$(function(){

    //首页新闻轮播
    new Swiper('.ind_kv .swiper-container',{
        pagination : '.ind_kv .swiper-pagination',
        loop:true,
        autoplay : 8000,
        prevButton:'.ind_kv .kv_prev',
        nextButton:'.ind_kv .kv_next',
        paginationClickable :true,
        onSlideChangeEnd: function(swiper){
            var activeIndex = $(swiper.slides[swiper.activeIndex]).attr('data-swiper-slide-index');
            $('.ind_kv .ind_news ul li').eq(activeIndex).fadeIn().siblings().fadeOut();
        }
    });

    //新闻公告
    new Swiper('.notice_kv .swiper-container',{
        pagination : '.notice_kv .swiper-pagination',
        loop:true,
        autoplay : 8000,
        prevButton:'.notice_kv .kv_prev',
        nextButton:'.notice_kv .kv_next',
        paginationClickable :true,
        onSlideChangeEnd: function(swiper){
            var activeIndex = $(swiper.slides[swiper.activeIndex]).attr('data-swiper-slide-index');
            $('.notice_kv .ind_news ul li').eq(activeIndex).fadeIn().siblings().fadeOut();
        }
    });

    //
    new Swiper('.kv_pics .swiper-container',{
        pagination : '.kv_pics .swiper-pagination',
        loop:true,
        autoplay : 8000,
        prevButton:'.kv_pics .kv_prev',
        nextButton:'.kv_pics .kv_next'
    });

    //视频列表
    new Swiper('.video_kv .swiper-container',{
        pagination : '.video_kv .swiper-pagination',
        loop:true,
        autoplay : 8000,
        prevButton:'.video_kv .kv_prev',
        nextButton:'.video_kv .kv_next',
        onSlideChangeEnd: function(swiper){
            var activeIndex = $(swiper.slides[swiper.activeIndex]).attr('data-swiper-slide-index');
            $('.video_kv .text p').eq(activeIndex).fadeIn().siblings().hide();
        }
    });

    //便捷等级
    new Swiper('.quick_home_kv .swiper-container',{
        loop:true,
        autoplay : 8000,
        prevButton:'.quick_home_kv .kv_prev',
        nextButton:'.quick_home_kv .kv_next'
    });


    new Swiper('.news_ind .swiper-container',{
        pagination : '.news_ind .swiper-pagination',
        autoplay : 5000,
        paginationClickable :true
    });

    //点击返回
    $('.mobile-top .header a.FL .icon-angle-left').on('tap',function(e){
    	
    	if(!JM.isNull($(this).parent().attr('href'))
    			&& $(this).parent().attr('href').trim() != 'javascript:;'){
    		
    		return true;
    	}
        e.preventDefault();
        window.history.back();
    });

    //首页搜索框
    $('.search_form label input').on('tap',function(){
        var _this=$(this);
        _this.parent().find('.drop-menu').show('fast',function(){
            $('body').one('tap',function(){
                if($('.drop-menu').css('display')=='block'){
                    _this.parent().find('.drop-menu').hide();
                }
            })
        });
        $('.drop-menu li').on('tap',function(){
            _this.val($(this).html());
            $(this).parent().hide();
        })
        _this.keydown(function() {
            _this.parent().find('.drop-menu').hide();
        });
    })

     //wifi点击
    $('.wifi_btn').on('tap',function(){
        $.do_dialog.open({'msg':$('.wifi_dialog'),'initBefore':function(){
        }});
    });

    //点击搜索框
    /*
    $('.mobile-top .header a.FR').on('tap',function(e){
            if($(this).hasClass('selected')){
                $('.header_search').slideUp();
            }else{
                $('.header_search').slideDown();
            }
            $(this).toggleClass('selected');
    });
    */

    //底部菜单
    $('.ft_more,.ft_more_mask').on('tap',function(){
        $('.ft_more_mask').on('scrollstart',function(e){
            e.preventDefault();
        });
        if($('.ft_more').hasClass('selected')){
            $('.ft_more_mask').hide();
            $('.ft_more_con').hide();
            $('.ft_more_con a').removeClass('animated');
        }else{
            $('.ft_more_mask').show();
            $('.ft_more_con').show();
            $('.ft_more_con a').addClass('animated');
        }
        $('.ft_more').toggleClass('selected');
    });

    //选择
    $('body').on('tap','.tit_tab .button-group,.select_tit .button-group',function(eve){
        eve.preventDefault();
        var _this = $(this);
        clearTimeout(ops.tit_tab_timer);
        _this.parent().parent().find('.button-group').removeClass('open');
        $(".flightContent").hide();
        
        _this.addClass('open');

        ops.tit_tab_timer = setTimeout(function(){
            // $(":not('.tit_tab'),:not('.select_tit')").one('tap',function(e){
            //     e.stopPropagation(); 
            //     e.preventDefault();
            //     if(_this.parent().parent().find('.button-group').hasClass('open')){
            //         _this.parent().parent().find('.button-group').removeClass('open');
            //     }
            // })

            $('.drop-menu li').on('tap',function(){
                var text = $(this).html();
                $(this).parent().parent().find('button font').html(text);
                $(this).parent().parent().removeClass('open').attr('', '');
                $(".flightContent").show();
            })
        }, 100);
    })
    $(document).bind("tap", function(e) {
        if ($(e.target).closest(".button-group.open, .drop.open").length == 0) {
            $(".button-group, .drop").removeClass("open");
            
            $(".flightContent").show();
        }
    });

 
        
    
    $('.nav_btn,.icon-navicon').on('tap',function(e){
        if(ops.click){
            ops.click = false;

            ops.left_menu_dialog=$.do_dialog.open({'msg':$('.side_menu'),'position':'top',initBefore:function(){
                $('.side_menu').animateObj('slideInLeft');
            },initAfter:function(){
                var menu_scrool = new IScroll('.side_menu',{scrollX: false, scrollY: true, bounce:false,mouseWheel: true,click:iScrollClick()});
                $('.side_menu ul li.more h6').on('tap',function(e){
                    var _this = $(this);
                    $(this).parent().siblings().find('h6').removeClass('selected').find('i').attr('class','icon-sort-desc');
                    $('.side_menu ul li dl').slideUp(function(){
                        menu_scrool.refresh();
                    });
                    if(_this.hasClass('selected')){
                        _this.removeClass('selected');
                        _this.parent().parent().find('i').attr('class','icon-sort-desc');
                    }else{
                        _this.addClass('selected');
                        _this.parent().find('i').attr('class','icon-sort-asc');
                        _this.parent().find('dl').slideDown(function(){
                            menu_scrool.refresh();
                            menu_scrool.scrollToElement(_this.get(0));
                        });
                    }
                });

                //滚动一级菜单
                var yello = $('.side_menu h6:has(".c_yello")');
                if(yello.size() > 0){
                	
                	menu_scrool.scrollToElement(yello.get(0));
                }
                //滚动二次菜单
                $('.side_menu dl:has(".c_yello")').prev().trigger('tap');
                
                $('body').on('swipeleft',function(){
                    $.do_dialog.close(ops.left_menu_dialog);
                });
            },closeBefore:function(){
                $('.side_menu').animateObj('slideOutLeft');
                ops.click = true;
            }});
        }
    });
    $('.personalCenterBtn').on('tap',function(){
    	var mid = $(this).attr("memberId");
    	if(mid=="1"){
    		window.location.href ="/airportm_login.action";
    	}else{
    		if(ops.click){
                ops.click = false;
        		ops.right_menu_dialog=$.do_dialog.open({'msg':$('.side_right_menu'),'position':'top',initBefore:function(){
        			$('.side_right_menu').animateObj('slideInRight');
        		},initAfter:function(){
        			$('body').on('swiperight',function(){
        				$.do_dialog.close(ops.right_menu_dialog);
        			});
        		},closeBefore:function(){
        			$('.side_right_menu').animateObj('slideOutRight');
                    ops.click = true;
        		}});
            }
    	}
    });


    //处理低分选择原因的
    $('.checkbox').on('tap',function(){
        if(!$(this).parent().hasClass('checkbox_label')){ //解决冲突
            if($(this).hasClass('icon-check-square')){
                $(this).addClass('icon-square-o');
                $(this).removeClass('icon-check-square');
                $(this).next().attr("checked", "checked");
            }else{
                $(this).addClass('icon-check-square');
                $(this).removeClass('icon-square-o');
                $(this).next().removeAttr("checked");
            } 
        }
    });
    //点击标签也触发checkbox的选择事件
    $('.checkbox_label').on('tap',function(e){
        var checkbox = $(this).children('.checkbox');
        if(checkbox.hasClass('icon-check-square')){
            checkbox.addClass('icon-square-o');
            checkbox.removeClass('icon-check-square');
            checkbox.next().attr("checked", "checked");
        }else{
            checkbox.addClass('icon-check-square');
            checkbox.removeClass('icon-square-o');
            checkbox.next().removeAttr("checked");
        }
    });

    //多选
    $('.custom_checkbox .label').bind('tap',function(e){
        var checkbox = $(this);
        if(checkbox.find('input').prop('checked')){
            checkbox.removeClass('selected');
            checkbox.find('input').prop("checked",false);
        }else{
            checkbox.addClass('selected');
            checkbox.find('input').prop("checked",true);
        }
        
        var personCount = $("#personCount");
        if(personCount.size() > 0){
    		
        	var count = 0;
        	$('.custom_checkbox .label').each(function(e){
                var checkbox = $(this);
                if(checkbox.find('input').prop('checked')
                		&& (checkbox.find('input').val() == '1'
                			|| checkbox.find('input').val() == '2'
                			|| checkbox.find('input').val() == '3'
                			|| checkbox.find('input').val() == '6')){
                	
                	count++;
            	}
            });
        	if(personCount.val() < count){
        		
        		personCount.val(count);
        	}
    	};
    });
    //单选按钮
    $('.custom_radio label').on('tap',function(e){
        var checkbox = $(this);
        checkbox.find('i').addClass('icon-check-square').removeClass('icon-square-o');
        checkbox.find('input').attr("checked", "checked");
        checkbox.siblings().find('i').removeClass('icon-check-square').addClass('icon-square-o');
        checkbox.siblings().find('input').removeAttr("checked");;
    });


    //ajax提交表单
    $('.ajax-form').submit(function(){
        var _this = $(this);
        if(ops.click){
            ops.click=false;
            var form=$(this);
            form.ajaxSubmit(function(data){
                if(data.err==1){
                    $.do_dialog.open({'msg':$('.default_dialog'),'timer':3000,'initBefore':function(){
                        $('.default_dialog .dialog_text').html(data.msg);
                    }});
                    form.find('[name="info['+data.key+']"]').trigger('focus');
                }else if(data.err==2){
                    $.do_dialog.open({'msg':$('.default_dialog'),'timer':3000,'initBefore':function(){
                        $('.default_dialog .dialog_text').html(data.msg);
                    }});
                }else if(data.err==0){
                    $.do_dialog.open({'msg':$('.default_dialog'),'timer':3000,'initBefore':function(){
                        $('.default_dialog .dialog_text').html(data.msg);
                    },'closeAfter':function(){
                        if(data.action=='reload'){
                            window.location.reload();
                        }else if(data.action=='redirect'){
                            window.location.href=data.url;
                        }else if(data.action=='fun'){
                            var fun = _this.attr('callback');
                            var jsonobj=eval(fun);
                            jsonobj.call(this,data);
                        }
                    }});
                }else{
                    $.do_dialog.open({'msg':$('.default_dialog'),'timer':3000,'initBefore':function(){
                        $('.default_dialog .dialog_text').html(data.msg);
                    }})
                }
                ops.click=true;
            });
            return false;
        }
    });

});


function rand(min,max){
     return ( min+Math.floor ( Math.random () * (max - min) ) );
}

function geren(){
	 $.do_dialog.close(ops.left_menu_dialog);
	$.do_dialog.open({'msg':$('.side_right_menu'),'position':'top'});
}