/* 
* @Author: gavin
* @Date:   2015-03-24 15:00:24
* @Last Modified by:   anchen
* @Last Modified time: 2015-10-24 11:12:44
*/
var cfg={};
    cfg.scale = 1;
    cfg.zoom = 1;//默认缩放比
    cfg.app_default_width=640; //默认设计尺寸
    cfg.stop_touchmove=false; //默认允许触摸拖动
    cfg.stop_click=false; //默认允许点击
    
var tool={};

;(function($,window,document,undefined){

    viewport();

    //页面初始化
    $(function(){

        if(FastClick){
            //鼠标定级延迟
            FastClick.attach(document.body);
        }

        var resizer=function(){
            cfg.C_W = $(window).width();
            cfg.C_H = $(window).height();
            cfg.zoom =  $(window).width() / cfg.app_default_width;
            $("body.mobile").css("display" , "block");
            $('body.mobile').animate({'opacity':1},100);
        }
        
        //页面缩放
        $(window).resize(resizer).trigger('resize');


        //是否阻止默认事件
        $(document).on('touchmove',function(e){
            if(cfg.stop_touchmove){
                e.preventDefault();
            }
        });

        // 兼容jQuery不支持$.os的问题
        if(!$.os) {
            var os = {},
                ua = navigator.userAgent,
                platform = navigator.platform,
                android = ua.match(/(Android);?[\s\/]+([\d.]+)?/),
                ipad = ua.match(/(iPad).*OS\s([\d_]+)/),
                ipod = ua.match(/(iPod)(.*OS\s([\d_]+))?/),
                iphone = !ipad && ua.match(/(iPhone\sOS)\s([\d_]+)/)

            if(android) os.android = true, os.version = android[2]
            if(iphone && !ipod) os.ios = os.iphone = true, os.version = iphone[2].replace(/_/g, '.')
            if(ipad) os.ios = os.ipad = true, os.version = ipad[2].replace(/_/g, '.')
            if(ipod) os.ios = os.ipod = true, os.version = ipod[3] ? ipod[3].replace(/_/g, '.') : null

            $.os = os
        }

    });
    

    //弹窗插件 gavin 
    $.do_dialog={
        index:0,
        timer:[],
        opts:[],
        setting : function(config){
            var defaults = {
                'msg':'',
                'color': 'red',
                'fontSize': '12px',
                'background':'#000',
                'lock':true,
                'position':'center',
                'maskClick':true,
                'opacity':'0.7',
                'timer':0,
                'initAfter':null,
                'initBefore':null,
                'zIndex':1000,
                'closeAfter':null,
                'closeBefore':null
            };
            this.opts[this.index] = $.extend({},defaults, config);//将一个空对象做为第一个参数
            this.opts[this.index]['index'] = this.index;
        },

        //初始化dom
        init : function(config){
            var _this = this;
            var _index = _this.index;
            //初始化配置
            this.setting(config);

            //设置模板
            _this.opts[_index].html = $("<div class='do_dialog' id='do_dialog_"+_index+"' style='position:fixed;top:0px;right:0px;bottom:0px;left:0px;width:100%;height:100%;overflow:auto;z-index:"+(_this.opts[_index].zIndex+_index)+";-webkit-transform:all ease 0.2s;-moz-transform:all ease 0.2s;transform:all ease 0.2s'></div>");
            _this.opts[_index].holder = $("<div id='do_holder_"+_index+"' style='display:none;'></div>");
            _this.opts[_index].cont = $("<div class='do_dialog_content' style=''></div>");
            _this.opts[_index].mask = $("<div class='do_dialog_mask' style='position:fixed;z-index:0;top:0px;right:0px;bottom:0px;left:0px;width:100%;height:100%;opacity:"+_this.opts[_index].opacity+";background:"+_this.opts[_index].background+"'></div>");

            //填入内容
            if(_this.opts[_index].lock){
                $(_this.opts[_index].html).append(_this.opts[_index].mask);
            }
            $(_this.opts[_index].html).data('id',_index).append(_this.opts[_index].cont);
            $('body').append(_this.opts[_index].html);

            //展示
            _this.show();
        },

        //内容展示
        show : function(){
            var _this = this;
            var _index = _this.index;
            //加载回调
            typeof _this.opts[_index].initBefore == 'function' && _this.opts[_index].initBefore.call(this,_this.opts[_index]);

            _this.infoCache = undefined;
            //根据内容处理
            if(typeof _this.opts[_index].msg == 'object'){
                _this.infoCache = _this.opts[_index].msg;
                _this.opts[_index].holder.insertAfter(_this.infoCache);

                var _cssFloat = $(_this.infoCache).css('float');
                var _cssDisplay = $(_this.infoCache).css('display');
                var _cssVisibility = $(_this.infoCache).css('visibility');
                _this.infoCache.css({
                    'float':'none',
                    'display':'block',
                    'visibility':'visible',
                }).data({
                    'float':_cssFloat,
                    'display':_cssDisplay,
                    'visibility':_cssVisibility
                }).prependTo(_this.opts[_index].cont);
            }else{
                _this.infoCache = _this.opts[_index].msg;
                _this.opts[_index].cont.html(_this.infoCache);
            }

            //设置是否居中
            if(_this.opts[_index].position=='center'){
                _this.opts[_index].cont.find('div').eq(0).css({
                    'top':'50%',
                    'margin-top':-_this.opts[_index].cont.find('div').eq(0).outerHeight()/2,
                });
            }

            //点击遮罩关闭
            if(_this.opts[_index].maskClick){
                _this.opts[_index].mask.on('click',function(){
                    _this.close($(this).parent().data('id'));
                });
            }

            //自动关闭
            if(_this.opts[_index].timer>0){
                _this.timer[_index] = setTimeout(function(){
                    _this.close(_index);
                },_this.opts[_index].timer );
            }

            //加载回调
            typeof _this.opts[_index].initAfter == 'function' && _this.opts[_index].initAfter.call(this,_this.opts[_index]);
        },

        open : function(config){
            var _this = this;
            _this.index++;
            _this.init(config);
            return _this.index;
        },

        close : function(index){
            var _this = this;
            
            //判断全部关闭或者单个关闭
            if(index){
                var box = $('#do_dialog_'+index);
                var holder = $('#do_holder_'+index);
                var desc = box.find('.do_dialog_content').children();

                //关闭回调
                typeof _this.opts[index].closeBefore == 'function' && _this.opts[index].closeBefore.call(this,_this.opts[index]);

                if(_this.opts[index].timer>0){
                    clearTimeout(_this.timer[index]);
                    delete _this.timer[index]; 
                }

                //根据内容处理
                if(typeof _this.opts[index].msg == 'object'){
                    var _cssFloat = desc.data('float');
                    var _cssDisplay = desc.data('display');
                    var _cssVisibility = desc.data('visibility');
                    desc.css({
                        'float':_cssFloat,
                        'display':_cssDisplay,
                        'visibility':_cssVisibility
                    }).insertAfter(holder);
                    holder.remove();
                    desc.find('*').unbind('tap');
                    desc.find('*').bind('tap');
                    desc.find('*').unbind('click');
                    desc.find('*').bind('click');
                }
                box.css({'zIndex':'-1'}).hide().remove();

                //关闭回调
                typeof _this.opts[index].closeAfter == 'function' && _this.opts[index].closeAfter.call(this,_this.opts[index]);
                // delete _this.opts[index]; //清除配置文件释放内存
            }else{
                $('.do_dialog').each(function(){
                    var _index = $(this).data('id');
                    var box = $('#do_dialog_'+_index);
                    var holder = $('#do_holder_'+_index);
                    var desc = box.find('.do_dialog_content').children();

                    //关闭回调
                    typeof _this.opts[_index].closeBefore == 'function' && _this.opts[_index].closeBefore();

                    if(_this.opts[_index].timer>0){
                        clearTimeout(_this.timer[_index]);
                        delete _this.timer[_index]; 
                    }

                    //根据内容处理
                    if(typeof _this.opts[_index].msg == 'object'){
                        var _cssFloat = desc.data('float');
                        var _cssDisplay = desc.data('display');
                        var _cssVisibility = desc.data('visibility');
                        desc.css({
                            'float':_cssFloat,
                            'display':_cssDisplay,
                            'visibility':_cssVisibility
                        }).insertAfter(holder);
                        holder.remove();
                        desc.find('*').unbind('tap');
                        desc.find('*').bind('tap');
                        desc.find('*').unbind('click');
                        desc.find('*').bind('click');
                    }
                    box.css({'zIndex':'-1'}).hide().remove();
                    //关闭回调
                    typeof _this.opts[_index].closeAfter == 'function' && _this.opts[_index].closeAfter();
                    // delete _this.opts[_index]; //清除配置文件释放内存
                })
            }
        }
    }

})(window.jQuery || window.Zepto,window,document);


//页面设置自适应
function viewport(){
    var screen_w = parseInt(window.screen.width);
        cfg.scale = screen_w / cfg.app_default_width;
    if ( /Android (\d+\.\d+)/.test( navigator.userAgent ) ) {
        var version = parseFloat( RegExp.$1 );
        document.write( version > 2.3
            ? '<meta name="viewport" content="width='+cfg.app_default_width+', minimum-scale = ' + cfg.scale + ', maximum-scale = ' + cfg.scale + ', user-scalable=no , target-densitydpi=device-dpi">'
            : '<meta name="viewport" content="width='+cfg.app_default_width+',minimum-scale = 1, maximum-scale = 1, user-scalable=no, target-densitydpi=device-dpi">' );
    } else {
        document.write( '<meta name="viewport" content="width='+cfg.app_default_width+', minimum-scale = ' + cfg.scale + ', maximum-scale = ' + cfg.scale + ', user-scalable=no , target-densitydpi=device-dpi">' );
    }
}

//缩略图
function DrawImage(ImgD,iwidth,iheight){   
    //参数(图片,允许的宽度,允许的高度)   
    var image=new Image();   
    image.src=ImgD.src;   
    if(image.width>0 && image.height>0){   
      if(image.width/image.height>= iwidth/iheight){   
          if(image.width>iwidth){     
              ImgD.width=iwidth;   
              ImgD.height=(image.height*iwidth)/image.width;   
          }else{   
              ImgD.width=image.width;     
              ImgD.height=image.height;   
          }   
      }else{   
          if(image.height>iheight){     
              ImgD.height=iheight;   
              ImgD.width=(image.width*iheight)/image.height;           
          }else{   
              ImgD.width=image.width;     
              ImgD.height=image.height;   
          }   
      }   
    }   
}

//动画
function swiperAnimateCache(el) {
    el = el.typeof=='Object' ? el : $(el);
    for (allBoxes = el, i = 0; i < allBoxes.length; i++) allBoxes[i].attributes["style"] ? allBoxes[i].setAttribute("animate-style-cache", allBoxes[i].attributes["style"].value) : allBoxes[i].setAttribute("animate-style-cache", " "), allBoxes[i].style.visibility = "hidden"
}
function swiperAnimate(id,el) {
    el = el.typeof=='Object' ? el : $(el);
    id = id.typeof=='Object' ? id : $(id);
    clearSwiperAnimate(el);
    // var b = id.slides[id.activeIndex].querySelectorAll(".ani");
    var b = id.find(el);

    for (i = 0; i < b.length; i++) b[i].style.visibility = "visible", effect = b[i].attributes["animate-effect"] ? b[i].attributes["animate-effect"].value : "", b[i].className = b[i].className + "  " + effect + " " + "animated", style = b[i].attributes["style"].value, duration = b[i].attributes["animate-duration"] ? b[i].attributes["animate-duration"].value : "", duration && (style = style + "animation-duration:" + duration + ";-webkit-animation-duration:" + duration + ";"), delay = b[i].attributes["animate-delay"] ? b[i].attributes["animate-delay"].value : "", delay && (style = style + "animation-delay:" + delay + ";-webkit-animation-delay:" + delay + ";"), b[i].setAttribute("style", style)
}
function clearSwiperAnimate(el) {
    el = el.typeof=='Object' ? el : $(el);
    for (allBoxes = el, i = 0; i < allBoxes.length; i++) allBoxes[i].setAttribute("style", allBoxes[i].attributes["animate-style-cache"].value), allBoxes[i].style.visibility = "hidden", allBoxes[i].className = allBoxes[i].className.replace("animated", " "), allBoxes[i].attributes["animate-effect"] && (effect = allBoxes[i].attributes["animate-effect"].value, allBoxes[i].className = allBoxes[i].className.replace(effect, " "))
}
