$(function() {
	var id = $("#idsValue").val();
	var code = $("#codes").val();
	if (id.indexOf(",") != -1) {
		var ids = id.split(',');
	}
	var $table = "";
	if (code.indexOf(",") != -1) {
		var codes = code.split(",");
		for ( var i = 0; i < codes.length; i++) {
			$table += '<table  class="my_table100 margin_top10">'
					+ '<tr>'
					+ '<td class="text_align_right" width="150">'
					+ '身份证号码：'
					+ '</td>'
					+ '<td>'
					+ '<input  name="idCard"  datatype="sfz"/>'
					+ '<p class="Validform_checktip" style="display:inline;"> 请输入身份证号码</p>'
					+ '</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td class="text_align_right" width="150">'
					+ '姓名：'
					+ '</td>'
					+ '<td>'
					+ '<input name="name"  datatype="*"/>'
					+ '<p class="Validform_checktip" style="display:inline;"> 请输入姓名</p>'
					+ '</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td class="text_align_right" width="150">'
					+ '航班号：'
					+ '</td>'
					+ '<td>'
					+ '<input name="flightNo"  datatype="*"/>'
					+ '<p class="Validform_checktip" style="display:inline;"> 请输入航班号</p>'
					+ '</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td class="text_align_right" width="150">'
					+ '航班时间：'
					+ '</td>'
					+ '<td>'
					+ '<input  name="date"  datatype="*" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'});"/>'
					+ '<p class="Validform_checktip" style="display:inline;"> 请输入航班时间</p>'
					+ '</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td class="text_align_right" width="150">'
					+ '便捷服务码：'
					+ '</td>'
					+ '<td>'
					+ '<input name="codes" value="'
					+ codes[i]
					+ '" name="type_id"  datatype="*"/>'
					+ '<p class="Validform_checktip" style="display:inline;"> 请输入便捷服务码</p>'
					+ '</td>' + '</tr>' + '</table>';
		}
		;
		$("#versionFlag").after($table);
	} else {
		$table += '<table  class="my_table100 margin_top10">'
				+ '<tr>'
				+ '<td class="text_align_right" width="150">'
				+ '身份证号码：'
				+ '</td>'
				+ '<td>'
				+ '<input  name="idCard"  datatype="sfz"/>'
				+ '<p class="Validform_checktip" style="display:inline;"> 请输入身份证号码</p>'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td class="text_align_right" width="150">'
				+ '姓名：'
				+ '</td>'
				+ '<td>'
				+ '<input name="name"  datatype="*"/>'
				+ '<p class="Validform_checktip" style="display:inline;"> 请输入姓名</p>'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td class="text_align_right" width="150">'
				+ '航班号：'
				+ '</td>'
				+ '<td>'
				+ '<input name="flightNo"  datatype="*"/>'
				+ '<p class="Validform_checktip" style="display:inline;"> 请输入航班号</p>'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td class="text_align_right" width="150">'
				+ '航班时间：'
				+ '</td>'
				+ '<td>'
				+ '<input  name="date"  datatype="*" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'});"/>'
				+ '<p class="Validform_checktip" style="display:inline;"> 请输入航班时间</p>'
				+ '</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td class="text_align_right" width="150">'
				+ '便捷服务码：'
				+ '</td>'
				+ '<td>'
				+ '<input name="codes" value="'
				+ code
				+ '" name="type_id"  datatype="*"/>'
				+ '<p class="Validform_checktip" style="display:inline;"> 请输入便捷服务码</p>'
				+ '</td>' + '</tr>' + '</table>';
		$("#versionFlag").after($table);
	}
	
	$("#commonForm1").Validform({
		btnSubmit : "#submitBtn", 
		datatype:{
			"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
			"sfz" : yzsfz,
		},
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
		ajaxPost:true,
		beforeSubmit : function(curform) {
			activate();
			return false;
		},
	});
	
});
//激活
function activate() {
	var codes = $(".code");
	var names = $(".name");
	var dates = $(".date");
	var flightNos = $(".flightNo");
	var idCards = $(".idCard");

	var codeArr = new Array();
	var nameArr = new Array();
	var dateArr = new Array();
	var flightNoArr = new Array();
	var idCardArr = new Array();

	for ( var i = 0; i < codes.length; i++) {
		codeArr[i] = $(codes[i]).val();
		nameArr[i] = $(names[i]).val();
		dateArr[i] = $(dates[i]).val();
		flightNoArr[i] = $(flightNos[i]).val();
		idCardArr[i] = $(idCards[i]).val();
	}
	$.ajax({
		url : "bjdjServiceCode_serviceCodeActivate.action",
		type : "post",
		dataType : "json",
		data : {
			"codes" : codeArr.join(","),
			"name" : nameArr.join(","),
			"date" : dateArr.join(","),
			"flighNo" : flightNoArr.join(","),
			"idCard" : idCardArr.join(",")
		},
		success : function(data) {
			if (data.status == JM.YES) {
				JM.alert(data.info,2000);
			} else {
				JM.alert(data.info, 2000);
			}
		}
	});

}

/**
 * 身份证的验证方法
 * @param gets
 * @param obj
 * @param curform
 * @param regxp
 * @returns {Boolean}
 */
function yzsfz(gets,obj,curform,regxp){
    //参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
    var rgp1 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    var rgp2 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    if(rgp1.test(gets) || rgp2.test(gets)){
        if(gets.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=gets.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=gets.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    return true;
                }else{
                    return false;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                   return true;
                }else{
                    return false;
                }
            }
        }
        if(gets.length==15){
            var year =  gets.substring(6,8);
            var month = gets.substring(8,10);
            var day = gets.substring(10,12);
            var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
            if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){
                return false;
            }else{
                return true;
            }
        }
    }else{
        return false;
    }

}