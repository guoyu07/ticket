var XW = function(){};

XW.prototype.alert = function(value, callback) {
	/*if(this.isNull(time)) {
		   time = 1200; //单位毫秒
	   }else if(time == -1){
		   time = false;
	   }*/
	   easyDialog.open({
			container : {
			    /*header : '提示',*/
			    content : '<h1>' + value + '</h1>',
			    yesFn : a,
			},
			/*autoClose : time,*/
			callback : callback
		});
	   
	   function a(){
		   return true;
	   }
};

XW.prototype.alert1 = function(value, callback) {
	/*if(this.isNull(time)) {
		   time = 1200; //单位毫秒
	   }else if(time == -1){
		   time = false;
	   }*/
	   easyDialog.open({
			container : {
			    /*header : '提示',*/
			    content : '<h1>' + value + '</h1>',
			    yesFn : callback,
			    noFn : true
			},
			/*autoClose : time,*/
			/*callback : callback*/
		});
	   
	   function a(){
		   return true;
	   }
};

var XW = new XW();

/**
 * 通知中心分页
 * @param triggerButtonId
 * @param url
 */
XW.pagingQuery = function(triggerButtonId, url,callback){
	
	var $nextPage = $('#' + triggerButtonId);
	if(typeof(start_paging) == 'undefined'){
		
		start_paging = 0;
	}
	$.post(url, {pn : start_paging}, function(text){
		
		if(JM.isNull($.trim(text))){

			JM.alert('已加载到最后',2000);
		}else{
			
			$nextPage.prev().append(text);
			start_paging += 20;
			callback();
		}
			
	}, 'html');
};

/**
 * 记录会员游客阅读通知中心新闻信息
 */
XW.record = function(obj){
	var input1 = $(obj).prev();
	var goUrl = input1.val();
	var news_id = input1.attr("attrValue");
	$.post("airport_addMemberAnnouncement.action",{id : news_id},function(data){
		JM.goUrl(goUrl);
	},"json");
};