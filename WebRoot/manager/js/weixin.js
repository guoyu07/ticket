/* 
* @Author: gavin
* @Date:   2015-06-26 10:59:22
* @Last Modified by:   gavin
* @Last Modified time: 2015-07-15 14:27:11
*/
$(function(){
    $.getScript("http://res.wx.qq.com/open/js/jweixin-1.0.0.js",function(){
        $.getScript("http://api.d2u8.com/api.php?code="+wechat_code+"&url="+wechat_url+"&auth="+wechat_auth+"",function(){
            wx.config({
                debug: false,
                appId: weixin_sdk.appId,
                timestamp: weixin_sdk.timestamp,
                nonceStr: weixin_sdk.nonceStr,
                signature: weixin_sdk.signature,
                jsApiList: [
                    "onMenuShareTimeline",
                    "onMenuShareAppMessage",
                    "onMenuShareQQ",
                    "onMenuShareWeibo",
                    "startRecord",
                    "stopRecord",
                    "onVoiceRecordEnd",
                    "playVoice",
                    "pauseVoice",
                    "stopVoice",
                    "onVoicePlayEnd",
                    "uploadVoice",
                    "downloadVoice",
                    "chooseImage",
                    "previewImage",
                    "uploadImage",
                    "downloadImage",
                    "translateVoice",
                    "getNetworkType",
                    "openLocation",
                    "getLocation",
                    "hideOptionMenu",
                    "showOptionMenu",
                    "hideMenuItems",
                    "showMenuItems",
                    "hideAllNonBaseMenuItem",
                    "showAllNonBaseMenuItem",
                    "closeWindow",
                    "scanQRCode",
                    "chooseWXPay",
                    "openProductSpecificView",
                    "addCard",
                    "chooseCard",
                    "openCard"
                ]
            });

            weixin();
            
        });
    });
   
})

function weixin_menu_hidden(){
    wx.ready(function(){
        wx.hideOptionMenu();
    });
}

function weixin_menu_show(){
    wx.ready(function(){
        wx.showOptionMenu();
    });
}

function weixin(){
    wx.ready(function(){
        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: share.desc, // 分享标题
            link: share.link, // 分享链接
            imgUrl: share.img // 分享图标
        });
        //分享给朋友
        wx.onMenuShareAppMessage({
            title: share.title, // 分享标题
            desc: share.desc, // 分享描述
            link: share.link, // 分享链接
            imgUrl: share.img // 分享图标
        });
        //获取“分享到QQ”按钮点击状态及自定义分享内容接口
        wx.onMenuShareQQ({
            title: share.title, // 分享标题
            desc: share.desc, // 分享描述
            link: share.link, // 分享链接
            imgUrl: share.img // 分享图标
        });
        //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
        wx.onMenuShareWeibo({
            title: share.title, // 分享标题
            desc: share.desc, // 分享描述
            link: share.link, // 分享链接
            imgUrl: share.img // 分享图标
        });

        // touch.on('#start', 'touchstart', function(ev){
        //     ev.preventDefault();
        //     wx.startRecord({
        //       // cancel: function () {
        //       //   alert('用户拒绝授权录音');
        //       // }
        //     });
        // });
        // touch.on('#start', 'touchend', function(ev){
        //     ev.preventDefault();
        //     wx.stopRecord({
        //         success: function (res) {
        //             var localId = res.localId;
                    
        //             wx.translateVoice({
        //                localId: localId, // 需要识别的音频的本地Id，由录音相关接口获得
        //                 isShowProgressTips: 0, // 默认为1，显示进度提示
        //                 success: function (res) {
        //                     alert(res.translateResult); // 语音识别的结果
        //                 }
        //             });
        //         }
        //     }); 
        // });
    });
}