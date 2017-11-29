/* 
* @Author: gavin
* @Date:   2015-06-26 10:47:02
* @Last Modified by:   anchen
* @Last Modified time: 2015-10-24 15:54:00
*/
var ops={};
ops.click=true;


/*常规*/
$(function(){

    new Swiper('.swiper-container',{
        pagination : '.swiper-pagination',
        onlyExternal : true,
        autoplay : 5000,
        paginationClickable :true
    });
    if($('.side_menu').get(0)){
        var menu_scrool = new IScroll('.side_menu',{scrollX: false, scrollY: true, mouseWheel: true, bounce:false });
    }
    // touch.on('body','swiperight',function(e){
    //     $('.side_menu').transition({ x:'100%' }, 500, 'linear',function(){});
    // });
    // touch.on('body','swipeleft',function(e){
    //     $('.side_menu').transition({ x:'-100%' }, 500, 'linear',function(){});
    // });
    
    $('body').on('swiperight',function(e){
        $('.side_menu').transition({ x:'100%' }, 500, 'linear',function(){});
    });
    $('body').on('swipeleft',function(){
        $('.side_menu').transition({ x:'-100%' }, 500, 'linear',function(){});
    });

    $('.side_menu ul li h6').on('tap',function(e){
        var _this = $(this);
        $('.side_menu ul li dl').slideUp();
        if(_this.hasClass('selected')){
            _this.removeClass('selected');
            _this.parent().parent().find('i').attr('class','icon-sort-asc');
            ops.click = true;
        }else{
            _this.addClass('selected');
            _this.parent().find('i').attr('class','icon-sort-desc');
            _this.parent().find('dl').slideDown(function(){
                menu_scrool.refresh();
                ops.click = true;
            });
        }
    });
    // $('.side_menu ul li h6').hammer().on('tap',function(){
    //     console.log(1);
    //     if(ops.click){
    //         ops.click = false;
    //         var _this = $(this);
    //         $('.side_menu ul li dl').slideUp();
    //         if(_this.hasClass('selected')){
    //             _this.removeClass('selected');
    //             _this.parent().parent().find('i').attr('class','icon-sort-asc');
    //             ops.click = true;
    //         }else{
    //             _this.addClass('selected');
    //             _this.parent().parent().find('i').attr('class','icon-sort-desc');
    //             _this.parent().find('dl').slideDown(function(){
    //                 menu_scrool.refresh();
    //                 ops.click = true;
    //             });
    //         }
    //     }
    // })

    
    // if($('.music_btn').get(0)){
    //     music();
    //     $('.music_btn').click(function(){
    //         if($(this).hasClass('selected')){
    //             music();
    //             $('#bgmusic').get(0).play();
    //         }else{
    //             clearInterval(ops.musicTimer);
    //             $('#bgmusic').get(0).pause();
    //         }
    //         $(this).toggleClass('selected');
    //     });
    // }

    // $.do_dialog.open({'msg':$('.game_dialog'),'background':'#000','opacity':1,'position':'top'});


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

