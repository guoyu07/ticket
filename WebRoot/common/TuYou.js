var TuYou = {};

//cookie操作（获取，设置，删除）
TuYou.Cookie = {
	get : function(name){
		
		var start = document.cookie.indexOf(name + "=");
		if (start == -1) {
			
			return null;
		}
		start = start + name.length + 1;
		
		var end = document.cookie.indexOf(";", start);
		if (end == -1) {
			
			end = document.cookie.length;
		}
		return document.cookie.substring(start, end);
	},
	set : function(name, value, expires, domain, secure){
		
		//不要忘了在对应getCookie函数里面加上decodeURI方法
		var str = name + "=" + encodeURI(value);
		if(expires){
			str += "; expires=" + expires.toGMTString();
		}
		if (domain) {
			str += "; domain=" + domain;
		}
		if (secure) {
			str += "; secure";
		}
		document.cookie = str;
	},
	remove : function(name){
		
		var expires = new Date();
		expires.setTime(expires.getTime() - 1);//将expires设为一个过去的日期，浏览器会自动删除它
		var cval = TuYou.Cookie.get(name);
		document.cookie = name + "=" + cval + "; expires=" + expires.toGMTString();
	}
};

TuYou.tree = {
		
	/**
	 * 渲染字典的展示的表格树
	 */
	renderTreeGrid : function(){
		
		$('#tg').treegrid(
			{
				fitColumns : true,
				nowrap : false,
				rownumbers : true,
				animate : true,
				width: $("#tg").parent().width(),
				height: "auto",
				collapsible : true,
				loadMsg : '数据加载中请稍后……',
				url : actionName+'_loadTree.action',
				idField : 'id',
				treeField : 'name',
				frozenColumns : [[{
					title : '字典名称',
					field : 'name',
					width : '50%'
				}]],
				columns : [[{
					field : 'value',
					title : '字典值',
					width : '45%'
				}]],
				onBeforeLoad : function(row, param) {

					// 此处就是异步加载地所在
					if(row){
						
						$(this).treegrid('options').url = actionName+'_loadTree.action?id='+ row.id;
					}
				}
			});
	},
	toManageDictionary : function (){
		
		window.location = actionName+'_manage.action?versionFlag=site';
	},
	toAddDictionary : function (){
		var row = $('#tg').treegrid('getSelected');
		if(row){
			
			window.location = actionName+'_add.action?versionFlag=site&id=' + row.id;
		}else{
			
			window.location = actionName+'_add.action?versionFlag=site';
		}
	},
	toEditDictionary : function (){
		var row = $('#tg').treegrid('getSelected');
		if(row){
			
			window.location = actionName+'_edit.action?versionFlag=site&id=' + row.id;
		}else{
			
			JM.alert("请先选择一条数据", 1500);
		}
	},
	toDeleteDictionary : function (){
		
		var row = $('#tg').treegrid('getSelected');
		
		if(!row){
			
			JM.alert("请先选择一条数据", 1500);
			return;
		}
		
		if(confirm('你确定要删除吗？')){
			
			var url = actionName+'_realDelete.action?versionFlag=site&id=' + row.id;
			$.getJSON(url, function(json){
				
				JM.alert(json.info, 2000);
				if(json.status == 'y'){
					
					$("#tg").treegrid("reload", row._parentId);
				}
			});
		}
	}
};


/* 
 * 加密解密 
 * */  
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";  
var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1,  
        63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1,  
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,  
        20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31,  
        32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,  
        50, 51, -1, -1, -1, -1, -1);  
TuYou.base64encode = function (str) {  
    var out, i, len;  
    var c1, c2, c3;  
    len = str.length;  
    i = 0;  
    out = "";  
    while (i < len) {  
        c1 = str.charCodeAt(i++) & 0xff;  
        if (i == len) {  
            out += base64EncodeChars.charAt(c1 >> 2);  
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);  
            out += "==";  
            break;  
        }  
        c2 = str.charCodeAt(i++);  
        if (i == len) {  
            out += base64EncodeChars.charAt(c1 >> 2);  
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4)  
                    | ((c2 & 0xF0) >> 4));  
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);  
            out += "=";  
            break;  
        }  
        c3 = str.charCodeAt(i++);  
        out += base64EncodeChars.charAt(c1 >> 2);  
        out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));  
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));  
        out += base64EncodeChars.charAt(c3 & 0x3F);  
    }  
    return out;  
};

TuYou.base64decode = function (str) {  
    var c1, c2, c3, c4;  
    var i, len, out;  
    len = str.length;  
    i = 0;  
    out = "";  
    while (i < len) {  
        /* c1 */  
        do {  
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];  
        } while (i < len && c1 == -1);  
        if (c1 == -1)  
            break;  
        /* c2 */  
        do {  
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];  
        } while (i < len && c2 == -1);  
        if (c2 == -1)  
            break;  
        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));  
        /* c3 */  
        do {  
            c3 = str.charCodeAt(i++) & 0xff;  
            if (c3 == 61)  
                return out;  
            c3 = base64DecodeChars[c3];  
        } while (i < len && c3 == -1);  
        if (c3 == -1)  
            break;  
        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));  
        /* c4 */  
        do {  
            c4 = str.charCodeAt(i++) & 0xff;  
            if (c4 == 61)  
                return out;  
            c4 = base64DecodeChars[c4];  
        } while (i < len && c4 == -1);  
        if (c4 == -1)  
            break;  
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);  
    }  
    return out;  
};

TuYou.utf16to8 = function (str) {  
    var out, i, len, c;  
    out = "";  
    len = str.length;  
    for (i = 0; i < len; i++) {  
        c = str.charCodeAt(i);  
        if ((c >= 0x0001) && (c <= 0x007F)) {  
            out += str.charAt(i);  
        } else if (c > 0x07FF) {  
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));  
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
        } else {  
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));  
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));  
        }  
    }  
    return out;  
};

TuYou.utf8to16 = function (str) {  
    var out, i, len, c;  
    var char2, char3;  
    out = "";  
    len = str.length;  
    i = 0;  
    while (i < len) {  
        c = str.charCodeAt(i++);  
        switch (c >> 4) {  
        case 0:  
        case 1:  
        case 2:  
        case 3:  
        case 4:  
        case 5:  
        case 6:  
        case 7:  
            // 0xxxxxxx  
            out += str.charAt(i - 1);  
            break;  
        case 12:  
        case 13:  
            // 110x xxxx 10xx xxxx  
            char2 = str.charCodeAt(i++);  
            out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));  
            break;  
        case 14:  
            // 1110 xxxx 10xx xxxx 10xx xxxx  
            char2 = str.charCodeAt(i++);  
            char3 = str.charCodeAt(i++);  
            out += String.fromCharCode(((c & 0x0F) << 12)  
                    | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));  
            break;  
        }  
    }  
    return out;  
};

/**
 * 多级联动
 * @param ids 需要联动的select, 按顺序组成一个数组
 * @param url 请求的地址,不带参数的
 * @param valueField 
 * @param nameField
 * @param callbacks 回调函数
 */
TuYou.multiGradeCascade = function (ids, url, valueField, nameField, callbacks){
	
	for(var i = 0; ids && i < ids.length; i++){
		
		var id = ids[i];
		var nextId = ids[i + 1];
		
		$("#" + id).change(function(){
			
			var value = $(this).val();
			var id = $(this).attr('id');
			var params = {};
			params[valueField] = value;
			$.post(
					url,
					params,
					function(json){
						
						if(json.length > 0){
							
							//先检测是否提供了回调函数，提供了就执行回调函数
							for(var k = 0; callbacks &&  k < ids.length; k++){
								
								if(id == ids[k]){
									
									var callback = callbacks[k];
									if(callback){
										
										callback(json);
										return;
									}
								}
							}
							
							//先检测是否提供了回调函数，提供了就执行回调函数
							for(var k = 0; callbacks &&  k < ids.length; k++){
								
								if(id == ids[k]){
									
									//执行默认动作
									var $next = $("#" + ids[k+1]);
									$next.children().remove();
									for(var j = 0; json && j < json.length; j++){
										
										$next.append('<option value="' + json[j][valueField] + '">' + json[j][nameField] + '</option>');
									}
									$next.trigger('change');
								}
							}
						}
						
					}, 'json');
		});
	}
};

/**
 * 分页查询
 */
TuYou.pagingQuery = function(triggerButtonId, url, back){
	
	var $nextPage = $('#' + triggerButtonId);
	if(typeof(start_paging) == 'undefined'){
		
		start_paging = 0;
	}
	$.post(url, {pn : start_paging}, function(text){
		
		if(JM.isNull($.trim(text)) && start_paging > 0){

			JM.alert('已加载到最后',2000);
		}else{
			
			$nextPage.parent().before(text);
			start_paging += 30;
		}
		if(!JM.isNull(back)){
			back();
		}
	}, 'html');
};

/**
 * 得到一个光标位置，在一个input中
 */
TuYou.getCursortPosition = function(ctrl) {
    var CaretPos = 0;   
    if (document.selection) { // IE Support
    	
    	ctrl.focus ();
        var Sel = document.selection.createRange ();
        Sel.moveStart ('character', -ctrl.value.length);
        CaretPos = Sel.text.length;
    } else if (ctrl.selectionStart || ctrl.selectionStart == '0'){ // Firefox support
    	
    	CaretPos = ctrl.selectionStart;
    }
    return (CaretPos);
};

/**
 * 设置光标的位置
 */
TuYou.setCaretPosition = function(ctrl, pos){
    if(ctrl.setSelectionRange)
    {
        ctrl.focus();
        ctrl.setSelectionRange(pos,pos);
    }
    else if (ctrl.createTextRange) {
        var range = ctrl.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
};

TuYou.isWeiXin = function(){ 
	var ua = window.navigator.userAgent.toLowerCase();
	if(typeof(ua.match(/MicroMessenger/i)) != 'undefined' && ua.match(/MicroMessenger/i) != null){ 
		
		return true; 
	}else{
		
		return false; 
	} 
};

/**
 * 设置iframe的高宽自适应
 * @param id
 */
TuYou.iFrameAutoHeight = function (id) {
	
	var ifm= document.getElementById(id);   
	var subWeb = document.frames ? document.frames[id].document : ifm.contentDocument;   
	if(ifm != null && subWeb != null) {
			
	   ifm.height = subWeb.body.scrollHeight;
	   ifm.width = '100%';
	}   
}

/**
 * 文件上传的一些方法(调用后台的jqueryFileUpload)
 */
TuYou.FileUpload = {
		
	upload : function(callback, tip, tipTime){
		
		var $form = $('<form method="post" enctype="multipart/form-data">');
		var $input = $('<input type="file" name="file.files" accept="image/*">');
		$form.append($input);
		
		$input.trigger('click');
		$input.change(function(){
			
			if(tip){
				
				if(tipTime){
					
					JM.alert(tip, tipTime);
				}else{
					JM.alert(tip, 2000);
				}
			}
			$form.ajaxSubmit({
				
				url : "fileUpload.action",
				method : "post",
				timeout : 1000 * 1000,
				success : function(data){
					
					callback(data);
				},
				error : function(data){
					
					callback(data);
				}
			});
		});
	},
		
	/**
	 * 直接上传图片 不裁剪
	 */
	directUpload : function(type, callback){
		
		var $file = $('<input type="file" name="picture" accept="image/*">');
		$file.trigger('click');
		$file.change(function(){
			
			TuYou.img.readFromFile($file, function(dataUrl){
				
				var formData = new FormData();
				formData.append('requestType', "page");
				formData.append('type', type);
				formData.append('clip', 'false');
				formData.append('picture', $file[0].files[0], dataUrl.split(',')[0].match(/:(.*?);/)[1]);
				
				//显示上传提示
				loading("正在上传...");
				
				//上传图片
				$.ajax({
					url : "jQueryUpload_fileUpload.action",
					type : 'POST',
					data : formData,
					processData : false,
					contentType : false,
					success : function(html){
						
						JM.closeAlert(); //关闭上传提示
						if(!JM.isNull(callback)){
							
							callback(dataUrl, html);
						}
					},
					error : function(data){
						
						JM.alert('上传失败', 2000);
					}
				});
			});
		});
	},
	
	/**
	 * 图片上传
	 * @param width 图片的长
	 * @param height 图片的宽
	 * @param callback(json) 上传成功的回调函数
	 * 			json.dataUrl 剪切后的图片
	 * 			json.status 上传是否成功
	 * 			json.info.fileName 上传图片的路径、类型、原名、新名
	 * 			json.info.path 图片的路径
	 * 			json.info.id 图片的id
	 */
	clipUpload : function(width, height, type, callback){
		
		$file = $('<input type="file" name="picture" accept="image/*" style="display:none;">');
		
		$file.trigger('click');
		$file.change(function(){
			
			TuYou.img.readFromFile($file, function(dataUrl){
				
				TuYou.img.showClipWindow(dataUrl, width, height, function(scale){
					
					var blob = TuYou.convert.dataURLtoBlob(dataUrl);
					
					var formData = new FormData();
					formData.append('requestType', "page");
					formData.append('x', scale.x);
					formData.append('y', scale.y);
					formData.append('width', scale.width);
					formData.append('height', scale.height);
					formData.append('width_', width);
					formData.append('height_', height);
					formData.append('type', JM.isNull(type) ? 1 : type);
					formData.append('clip', 'true');
					formData.append('picture', blob, dataUrl.split(',')[0].match(/:(.*?);/)[1]);
					
					//显示上传提示
					JM.loading("正在上传...");
					
					//上传图片
					$.ajax({
						url : "jQueryUpload_fileUpload.action",
						type : 'POST',
						data : formData,
						processData : false,
						contentType : false,
						success : function(html){
							
							JM.closeAlert(); //关闭上传提示
							if(!JM.isNull(callback)){
								
								callback(dataUrl, html);
							}
						},
						error : function(data){
							
							JM.alert('上传失败', 2000);
						}
					});
				});
			});
		});
	}
};

/**
 * 图像类操作
 */
TuYou.img = {
		
	/**
	 * 读取img
	 * @param $img 要去读的img标签
	 * @param callback(dataUrl) 通过回调函数返回url
	 */
	readFromImg : function($img, callback){
		
		var loading = JM.loading("加载中...");
		var canvas = document.createElement('canvas');
        var ctx = canvas.getContext('2d');
        canvas.height = $img[0].attr('height');
        canvas.width = $img[0].attr('width');
        ctx.drawImage($img[0], 0, 0);
        var dataURL = canvas.toDataURL(outputFormat);
        JM.closeAlert();
        callback(dataURL);
	},
	
	/**
	 * 读取img从一个网络url连接地址
	 * @param url 连接地址
	 * @param callback(dataUrl) 通过回调函数返回url
	 */
	readFromUrl : function(url, callback){
		
		var loading = JM.loading("加载中...");
		var image = new Image();
		image.src = url;
		image.onload = function(){
			
			var canvas = document.createElement('canvas');
			var ctx = canvas.getContext('2d');
			canvas.height = image.height;
			canvas.width = image.width;
			ctx.drawImage(image, 0, 0);
			var dataURL = canvas.toDataURL(outputFormat);
			JM.closeAlert();
			callback(dataURL);
		};
	},
	
	/**
	 * 读取img从一个网络url连接地址
	 * @param $file 连接地址
	 * @param callback(dataUrl) 通过回调函数返回url
	 */
	readFromFile : function($file, callback){
		
		var loading = JM.loading("加载中...");
		//检验是否为图像文件  
	    var file = $file[0].files[0];  
	    if(!/image\/\w+/.test(file.type)){  
	        alert("看清楚，这个需要图片！");  
	        return false;  
	    }  
	    var reader = new FileReader();  
	    //将文件以Data URL形式读入页面  
	    reader.readAsDataURL(file);  
	    reader.onload=function(e){  
	    	
	    	JM.closeAlert();
	        callback(this.result);
	    };
	},
	
	/**
	 * 图片缩放
	 * @param dataUrl 图片的dataUrl
	 * @param width 图片后的宽度
	 * @param height 图片后的高度
	 * @returns 返回缩放后的dataUrl
	 */
	scale : function(dataUrl, width, height){
		
		var loading = JM.loading("处理中...");
		var mime_type = dataUrl.split(';')[0].substring(5);
        var cvs = document.createElement('canvas');
        //naturalWidth真实图片的宽度
        cvs.width = width;
        cvs.height = height;
        var image = new Image();
		image.src = dataUrl;
		cvs.getContext("2d").drawImage(image, 0, 0, width, height);
        var newImageData = cvs.toDataURL(mime_type);
        JM.closeAlert();
        return newImageData;
	},
	
	/**
	 * 图片裁剪
	 * @param dataUrl 图片的dataUrl
	 * @param x 图片裁剪的起始横坐标
	 * @param y 图片裁剪的起始纵坐标
	 * @param width 裁剪区域的宽度
	 * @param height 裁剪区域的高度
	 * @returns 返回处理后的dataUrl
	 */
	clip : function(dataUrl, x, y, width, height){
		
		var loading = JM.loading("裁剪中...");
		var mime_type = dataUrl.split(';')[0].substring(5);
		var cvs = document.createElement('canvas');
		if(JM.isNull(cvs)){
			
			JM.alert('不支持绘图');
		}
		//naturalWidth真实图片的宽度
		cvs.width = width;
		cvs.height = height;
		var image = new Image();
		image.src = dataUrl;
		cvs.getContext("2d").drawImage(image, -x, -y);
		var newImageData = cvs.toDataURL(mime_type);
		JM.closeAlert();
		return newImageData;
	},
	
	/**
	 * 显示图片裁剪窗体
	 * @param dataUrl 原始图片
	 * @param wdith 要裁剪的长
	 * @param height 要裁剪的宽
	 * @param callback(dataUrl) 回调函数，参数为处理后的dataUrl
	 */
	showClipWindow : function(dataUrl, width, height, callback){
		
		var $img = $('<div style="width:570px; height:500px;">'
				+ '<img src="'+dataUrl+'" id="cutFileId"/>'
				+ '</div>');
		var scale = null;
		$img.find('img').cropper({  //把div变成一个剪切框
			aspectRatio: width / height, //裁剪的长宽比
			crop: function (data) {
				
				scale = data;
			}
		});
		$img.on({'build.cropper' : function(data){ //记录剪切的大小，scale={x, y, width, height}
			
			scale = data;
			console.info(scale);
		}});
		
		easyDialog.open({
			   container : {
			     content : $img
			   },
			   autoClose : time,
			   callback : callback
		   });
		
		var dialog = bootbox.dialog({//把剪切框显示弹出框中
            title: '图片剪切',
            message: function(dialog) {
            	
                return $img;  //把剪切框显示弹出框中
            },
            buttons: [{
                label: '确定',
                callback: function() {
                	
                	JM.closeAlert(); //关闭剪切窗口
//                	var sourceDataUrl = $img.find('img')[0].src;
//                	var clipDataUrl = TuYou.img.clip(sourceDataUrl, scale.x, scale.y, scale.width, scale.height); //剪切
//                	console.log(clipDataUrl);
//                	var scaleDataUrl = TuYou.img.scale(clipDataUrl, width, height);
//                	console.log(scaleDataUrl);
                	callback(scale);
                }
            }]
        });
	},
	
	/**
	 * 显示图片裁剪窗体，并且把剪切后的图片显示在指定的img标签上
	 * @param dataUrl 原始图片
	 * @param s 要裁剪的长宽比
	 * @param $img 剪切好后要显示在的img标签
	 */
	clipToImgShow : function(dataUrl, s, $img){
		
		TuYou.img.showClipWindow(dataUrl, s, function(newDataUrl){
			
			$img.attr('src', '');//先制空，否则不刷新
			$img.attr('src', newDataUrl); //把剪切后的DataUrl显示在指定的img标签上
		});
	},
	
	/**
	 * 图片清晰度调整
	 * @param dataUrl
	 * @param quality 图片调整度（1为不调整）
	 * @returns 返回处理后的dataUrl
	 */
	quality : function(dataUrl, quality){
		
		var loading = JM.loading("处理中...");
		var mime_type = data.split(';')[0].substring(5);
		var cvs = document.createElement('canvas');
		//naturalWidth真实图片的宽度
		cvs.width = dataUrl.naturalWidth;
		cvs.height = dataUrl.naturalHeight;
		var ctx = cvs.getContext("2d");
		var image = new Image();
		image.src = dataUrl;
		ctx.drawImage(image, 0, 0);
		var newImageData = cvs.toDataURL(mime_type, quality); //指定清晰度
		JM.closeAlert();
		return newImageData;
	}
};