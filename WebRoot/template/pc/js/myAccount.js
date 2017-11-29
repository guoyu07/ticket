$(function() {
	initValidForm();
	/**
	 * 姓名
	 */
	$('.input_dialog_btn').on('click', function() {
		var _this = $(this);
		$('.input_dialog input').attr("placeholder", "请输入您的真实姓名，填写之后不能修改！");
		$.slide({
			'id' : 'input_dialog',
			'content' : $('.input_dialog').get(0),
			lock : true
		});
		$('.input_dialog button').on('click', function() {
			var $value = $('.input_dialog input').val();
			if ($value) {
				_this.parent().find('font').html(JM.trim($value));
				$.dialog.list["input_dialog"].close();
			}
		});
	});
	/**
	 * 身份证
	 */
	$('.input1_dialog_btn').on(
			'click',
			function() {
				var _this = $(this);
				$('.input1_dialog input').attr("placeholder",
						"请输入您的" + JM.trim(_this.parent().find("em").text()));
				$.slide({
					'id' : 'input1_dialog',
					'content' : $('.input1_dialog').get(0),
					lock : true
				});
				$('.input1_dialog button').on('click', function() {
					var $value = $('.input1_dialog input').val();
					if ($value) {
						_this.parent().find('font').html(JM.trim($value));
						$.dialog.list["input1_dialog"].close();
					}
				});
			});
	$('.input2_dialog_btn').on(
			'click',
			function() {
				var _this = $(this);
				$('.input2_dialog input').attr("placeholder",
						"请输入您的" + JM.trim(_this.parent().find("em").text()));
				$.slide({
					'id' : 'input2_dialog',
					'content' : $('.input2_dialog').get(0),
					lock : true
				});
				$('.input2_dialog button').on(
						'click',
						function() {
							var $value = $('.input2_dialog input').val();
							if ($value) {
								_this.parent().find('font').html(
										JM.trim($value));
								$.dialog.list["input2_dialog"].close();
							}
						});
			});
	$('.input3_dialog_btn').on(
			'click',
			function() {
				var _this = $(this);
				$('.input3_dialog input').val("");
				$('.input3_dialog input').attr("placeholder",
						"请输入您的" + JM.trim(_this.parent().find("em").text()));
				$.slide({
					'id' : 'input3_dialog',
					'content' : $('.input3_dialog').get(0),
					lock : true
				});
				$('.input3_dialog button').on('click', function() {
					var $value = $('.input3_dialog input').val();
					if ($value) {
						_this.parent().find('font').html(JM.trim($value));
						$.dialog.list["input3_dialog"].close();
					}
				});
			});
	$('.input4_dialog_btn').on('click', function() {
		var _this = $(this);
		$('.input4_dialog input').attr("placeholder",
				"请输入您的" + JM.trim(_this.parent().find("em").text()));
		$.slide({
			'id' : 'input4_dialog',
			'content' : $('.input4_dialog').get(0),
			lock : true
		});
		$('.input4_dialog button').on('click', function() {
			var $value = $('.input4_dialog input').val();
			if ($value) {
				_this.parent().find('font').html(JM.trim($value));
				$.dialog.list["input4_dialog"].close();
			}
		});
	});
	/**
	 * 性别
	 */
	$('.select_dialog_btn').on('click', function() {
		var _this = $(this);
		$.slide({
			'id' : 'select_dialog',
			'content' : $('.select_dialog').get(0),
			lock : true
		});
		$('.select_dialog button').on('click', function() {
			var $m = $("#m");
			var $f = $("#f");
			var $value;
			if ($m.attr("checked") == "checked") {
				$value = $m.parent().text();
			}
			if ($f.attr("checked") == "checked") {
				$value = $f.parent().text();
			}
			if ($value) {
				_this.parent().find('font').html(JM.trim($value));
				$.dialog.list["select_dialog"].close();
			}
		});
	});
	/**
	 * 生日
	 */
	$(".date_dialog_btn").on('click', function() {
		var _this = $(this);
		$.slide({
			'id' : 'date_dialog',
			'content' : $('.date_dialog').get(0),
			lock : true
		});
		$('.date_dialog button').on('click', function() {
			var $value = $('.date_dialog input').val();
			if ($value) {
				_this.parent().find('font').html(JM.trim($value));
				$.dialog.list["date_dialog"].close();
			}
		});
	});

	$("#updateinfo").click(updateInfo);

	$('#uploadImage').click(function() {

		TuYou.FileUpload.upload(function(data) {

			$('input[name="images"]').val(data);
			$('.icon-plus').attr('src', data);
		});
	});
	
});

function initValidForm() {
	var form = jQuery("#memberForm");
	var isAjax = JM.isNull(form.attr('ajaxPost')) ? true : false;
	if(!isAjax){
		
		isAjax = JM.isNull(form.attr('ajaxpost')) ? true : false;
	}
	form.Validform({
		btnSubmit:"#submitBtn",
		tiptype:function(msg, o, cssctl) {
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip = o.obj.siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			} else {
				var objtip = o.obj.find("#msgdemo");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		ajaxPost:isAjax,
		datatype:{
			"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
		},
		beforeSubmit:function(curform){
			form.find("textarea").each(function(){
				try {
					var obj=$(this);
					if(!JM.isNull(obj)){
						try {
							var objKE = eval(obj.attr("name"));
							objKE.sync();//sync textarea editor content to commom's textarea
						} catch(ee){}
					}
				} catch(e) {}
			});
			var alertConfirm = form.attr("alertConfirm");
			if(!JM.isNull(alertConfirm)) {
				return true;
			} else {
				if(confirm('是否确认提交？')){
				   return true;	
				} else {
					return false;
				}
			}
		},callback:function(data){
		
			if(isAjax == true){
				
				if (data.status == JM.YES) {
					
					//服务器端返回跳转连接
					var href = data.info.href;
					function forward() {
						
						var manageLink = $("#manageLink");
						//服务器端没有返回跳转连接，则试图获取ID为manageLink节点的href连接
						if(JM.isNull(href)){
							
							href = manageLink.attr("href");
						}
						
						// 是否不可返回，如果不可返回，这点击返回按钮不可返回
						if (form.attr('return') && form.attr('return') == 'false') {
							
							window.location.replace(href);
						} else {
							
							window.location.href = href;
						}
					}
					//data的info为空，或者info是一个也是一个json对象
					if(JM.isNull(href)){
						
						JM.alert(data.info, 2000, forward);
					}else{
						
						forward();
					}
					
				} else {
					JM.alert(data.info);
					JM.flush();
				}
			}
		}
	});
}


function updateInfo() {
	var formStr = "";
	$("font").each(function() {
		var name = $(this).attr("name");
		var value = $(this).text();
		formStr += name + '=' + $.trim(value) + '&';
	});
	$.ajax({
		type : 'POST',
		datatype : 'text',
		url : '/pcMembers_updateInfo.action',
		data : formStr,
		success : function(data) {
			data = $.parseJSON(data);
			if (data.status == "y") {
				JM.alert(data.info,2000,function(){
					location.reload();
				});
			} else {
				JM.alert(data.info);
			}
		}
	});
}

function change() {
	$("#update").hide();
	$("#updateimg").show();
}

function change1() {
	$("#updateimg").hide();
	$("#update").show();
}