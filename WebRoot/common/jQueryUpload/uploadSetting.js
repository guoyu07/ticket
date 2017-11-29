function initUploadifyPicture(fileId,type,fileQueue,auto,multi,showDIV,versionFlag){
	 $("#"+fileId).uploadify({
	   'uploader'       : '/common/jQueryUpload/uploadify.swf',
	   'script'         : '/jQueryUpload_fileUpload.action',//提交到的action	
	   'scriptData'		: {'type':type,'versionFlag':versionFlag}, //1大图 2小图 3其他文件
	   'method'         : 'GET',		   
	   'cancelImg'      : '/common/jQueryUpload/cancel.png',		//传图片取消X
	   'folder'         : 'temp',		//上传目录
	   'queueID'        : fileQueue,	//和存放队列的DIV的id一致   
	   'auto'           : auto,		//是否自动开始   	
	   'multi'          : multi,	//是否支持多文件上传  
	   'buttonText'     : '', 		//由于破解的原因，不能指定按钮上的文字
	   'fileDataName'   : 'picture',
	   'sizeLimit': 1024000, //设置单个文件大小限制，单位为byte 	
	   'simUploadLimit' : 1, //一次同步上传的文件数目  		
	   'queueSizeLimit' : 10, //队列中同时存在的文件个数限制 	
	   　onComplete: function (event, queueID, fileObj, response, data) {	
	       if(multi == false) {//不可以传多图
	    	   var _img = $("#" + fileQueue).parent().find("img");
		       if(_img.size() < 2) {
		    	   var value = response ;
			       $('#'+showDIV).append(value); 
		       } else { //删除上次的图片
		    	   _img.each(function(){
		    		   $(this).parent().click(); 
		    	   });
		    	   var value = response ;
			       $('#'+showDIV).append(value); 
		       }
	       } else {//可以传多图
	    	   var value = response ;
		       $('#'+showDIV).append(value); 
	       }
	   　},  
	   　onError: function(event, queueID, fileObj) {
	   　	 alert("文件:" + fileObj.name + "上传失败");  		
	   　},  		
	   　onCancel: function(event, queueID, fileObj){  	
	   　	 alert("取消了" + fileObj.name);  		
	   　}
	});
}
/**
*@param fileId 上传文件控件
*@param type 图片类型 1大图 2小图 3其他文件
*@param fileQueue 存放队列的DIV的id 显示上传进度条DIV
*@param auto 是否自动上传
*@param multi 是否是多文件上传
*@param showDIV 上传完后预览图片所在DIV中
*/
function initUploadify(fileId,type,fileQueue,auto,multi,showDIV,versionFlag){
	 $("#"+fileId).uploadify({
	   'uploader'       : '/common/jQueryUpload/uploadify.swf',
	   'script'         : '/jQueryUpload_fileUpload.action',//提交到的action	
	   'scriptData'		: {'type':type,'versionFlag':versionFlag}, //1大图 2小图 3其他文件
	   'method'         : 'GET',		   
	   'cancelImg'      : '/common/jQueryUpload/cancel.png',		//传图片取消X
	   'folder'         : 'temp',		//上传目录
	   'queueID'        : fileQueue,	//和存放队列的DIV的id一致   
	   'auto'           : auto,		//是否自动开始   	
	   'multi'          : multi,	//是否支持多文件上传  
	   'buttonText'     : '', //由于破解的原因，不能指定按钮上的文字
	   'fileDataName'   : 'picture',
	   'simUploadLimit' : 1, //一次同步上传的文件数目  		
	   'queueSizeLimit' : 10, //队列中同时存在的文件个数限制 	
	   　onComplete: function (event, queueID, fileObj, response, data) {
	       if(multi == false) {//不可以传多图
	    	   var _img = $("#" + fileQueue).parent().find("img");
		       if(_img.size() < 2) {
		    	   var value = response ;
			       $('#'+showDIV).append(value); 
		       } else { //删除上次的图片
		    	   _img.each(function(){
		    		   $(this).parent().click(); 
		    	   });
		    	   var value = response ;
			       $('#'+showDIV).append(value); 
		       }
	       } else {//可以传多图
	    	   var value = response ;
		       $('#'+showDIV).append(value); 
	       }
	   　},  
	   　onError: function(event, queueID, fileObj) {
	   　	 alert("文件:" + fileObj.name + "上传失败");  		
	   　},  		
	   　onCancel: function(event, queueID, fileObj){  	
	   　	 alert("取消了" + fileObj.name);  		
	   　}
	});
}

/**
*@param fileId 上传文件控件
*@param type 图片类型 1大图 2小图 3其他文件
*@param fileQueue 存放队列的DIV的id 显示上传进度条DIV
*@param auto 是否自动上传
*@param multi 是否是多文件上传
*@param showDIV 上传完后预览图片所在DIV中
*/
function initUploadifyAnnex(fileId,type,fileQueue,auto,multi,showDIV,versionFlag){
	 $("#"+fileId).uploadify({
	   'uploader'       : '/common/jQueryUpload/uploadify.swf',
	   'script'         : '/jQueryUpload_fileUpload.action',//提交到的action	
	   'scriptData'		: {'type':type,'versionFlag':versionFlag}, //1大图 2小图 3其他文件
	   'method'         : 'GET',		   
	   'cancelImg'      : '/common/jQueryUpload/cancel.png',		//传图片取消X
	   'folder'         : 'temp',		//上传目录
	   'queueID'        : fileQueue,	//和存放队列的DIV的id一致   
	   'auto'           : auto,		//是否自动开始   	
	   'multi'          : multi,	//是否支持多文件上传  
	   'buttonText'     : '', //由于破解的原因，不能指定按钮上的文字
	   'fileDataName'   : 'picture',
	   'sizeLimit': 1024000000, //设置单个文件大小限制，单位为byte 
	   'simUploadLimit' : 1, //一次同步上传的文件数目
	   'queueSizeLimit' : 10, //队列中同时存在的文件个数限制 	
	   'fileDesc': '仅支持MP4流媒体格式.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
	　     'fileExt': '*.mp4;*.flv',//允许的格式		
	   　onComplete: function (event, queueID, fileObj, response, data) {		
	    var value = response ;
	    $('#'+showDIV).append(value); 
	   　},  
	   　onError: function(event, queueID, fileObj) {
	   　 alert("文件:" + fileObj.name + "上传失败");  		
	   　},  		
	   　onCancel: function(event, queueID, fileObj){  		
	   　 alert("取消了" + fileObj.name);  		
	   　} 		
	});
}


/**
*@param fileId 上传文件控件
*@param type 图片类型 1大图 2小图 3其他文件
*@param fileQueue 存放队列的DIV的id 显示上传进度条DIV
*@param auto 是否自动上传
*@param multi 是否是多文件上传
*@param showDIV 上传完后预览图片所在DIV中
*/
function initUploadifyAllType(fileId,type,fileQueue,auto,multi,showDIV,versionFlag){
	 $("#"+fileId).uploadify({
	   'uploader'       : '/common/jQueryUpload/uploadify.swf',
	   'script'         : '/jQueryUpload_fileUpload.action',//提交到的action	
	   'scriptData'		: {'type':type,'versionFlag':versionFlag}, //1大图 2小图 3其他文件
	   'method'         : 'GET',		   
	   'cancelImg'      : '/common/jQueryUpload/cancel.png',		//传图片取消X
	   'folder'         : 'temp',		//上传目录
	   'queueID'        : fileQueue,	//和存放队列的DIV的id一致   
	   'auto'           : auto,		//是否自动开始   	
	   'multi'          : multi,	//是否支持多文件上传  
	   'buttonText'     : '', //由于破解的原因，不能指定按钮上的文字
	   'fileDataName'   : 'picture',
	   'simUploadLimit' : 1, //一次同步上传的文件数目
	   'queueSizeLimit' : 10, //队列中同时存在的文件个数限制 		
	   　onComplete: function (event, queueID, fileObj, response, data) {		
	    	var value = response ;
	        $('#'+showDIV).append(value); 
	   　},  
	   　onError: function(event, queueID, fileObj) {
	   　 alert("文件:" + fileObj.name + "上传失败");  		
	   　},  		
	   　onCancel: function(event, queueID, fileObj){  		
	   　 alert("取消了" + fileObj.name);  		
	   　} 		
	});
}
function delImg(id,name){
	try {
		jQuery.ajax({
			type: "POST",
			url: "jQueryUpload_deleteImage.action",
			data:"name="+name,
			success: function(msg){
				if(msg=="suc"){
				  var ss = $("#span" + id);
				  $("#span"+id).remove();
				}
			} 
		});
	} catch(e) {
	}
}
/**
*@param fileId 上传文件控件
*@param type 图片类型 1大图 2小图 3其他文件
*@param fileQueue 存放队列的DIV的id 显示上传进度条DIV
*@param auto 是否自动上传
*@param multi 是否是多文件上传
*@param showDIV 上传完后预览图片所在DIV中
*/
function initUploadifyStore(fileId,type,fileQueue,auto,multi,showDIV,versionFlag){
	 $("#"+fileId).uploadify({
	   'uploader'       : '/common/jQueryUpload/uploadify.swf',
	   'script'         : '/jQueryUpload_fileUpload.action',//提交到的action	
	   'scriptData'		: {'type':type,'versionFlag':versionFlag}, //1大图 2小图 3其他文件
	   'method'         : 'GET',		   
	   'cancelImg'      : '/common/jQueryUpload/cancel.png',		//传图片取消X
	   'folder'         : 'temp',		//上传目录
	   'queueID'        : fileQueue,	//和存放队列的DIV的id一致   
	   'auto'           : auto,		//是否自动开始   	
	   'multi'          : multi,	//是否支持多文件上传  
	   'buttonText'     : '', //由于破解的原因，不能指定按钮上的文字
	   'fileDataName'   : 'picture',
	   'simUploadLimit' : 1, //一次同步上传的文件数目  		
	   'queueSizeLimit' : 10, //队列中同时存在的文件个数限制 	
	   　onComplete: function (event, queueID, fileObj, response, data) {
	       if(multi == false) {//不可以传多图
	    	   var _img = $("#" + fileQueue).parent().find("img");
		       if(_img.size() < 2) {
		    	   var value = response ;
			       $('#'+showDIV).html(value); 
		       } else { //删除上次的图片
		    	   _img.each(function(){
		    		   $(this).parent().click(); 
		    	   });
		    	   var value = response ;
			       $('#'+showDIV).append(value); 
		       }
	       } else {//可以传多图
	    	   var value = response ;
		       $('#'+showDIV).append(value); 
	       }
	   　},  
	   　onError: function(event, queueID, fileObj) {
	   　	 alert("文件:" + fileObj.name + "上传失败");  		
	   　},  		
	   　onCancel: function(event, queueID, fileObj){  	
	   　	 alert("取消了" + fileObj.name);  		
	   　}
	});
}
/**
 * 
 * 删除图片
 * @param {Object} id
 * @param {Object} name
 */
function delImgUpdate(id,name){
	try {
		jQuery.ajax({
		type: "POST",
		url: "jQueryUpload_deleteImageUpdate.action",
		data:"name="+name,
		success: function(msg){
			if(msg == "suc"){
			  var ss = $("#span" + id);
			   $("#span"+id).remove();
			}
		} 
		});
	} catch(e) {
	}
}
$(document).ready(function() {
	$('a.poplight[href^=#]').click(function() {
		var popID = $(this).attr('rel');
		var popURL = $(this).attr('href');
		var query = popURL.split('?');
		var dim = query[1].split('&');
		var popWidth = dim[0].split('=')[1];

		$('#' + popID).fadeIn().css({
			'width': Number(popWidth)
		})
		var popMargTop = ($('#' + popID).height() + 80) / 2;
		var popMargLeft = ($('#' + popID).width() + 80) / 2;
		$('#' + popID).css({
			'margin-top': -popMargTop,
			'margin-left': -popMargLeft
		});
		$('body').append('<div id="fade"></div>');
		$('#fade').css({
			'filter': 'alpha(opacity=50)'
		}).fadeIn();
		return false;
	});
	$(".popup_block_title span").unbind().bind('click',
	function() {
		$('#fade , .popup_block').fadeOut(function() {
			$('#fade, a.close').remove();
		});
		return false;
	});
});