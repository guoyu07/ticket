/**
 * PC端电瓶车的js
 */
$(function(){
//	isNan();
//	$(document).click(isNan);
	$("#num").blur(isNan);
	$("#addSum").click(addSum);
	$("#cutSum").click(cutSum);
	$("#buy").click(num);
});

function isNan(){
	var num = $("#num").val();
	if(isNaN(num)){
		JM.alert("不能输入特殊字符", 2000);
		$("#num").val(1);
		total();
		return false;
	}
	var numInt = parseInt(num);
	if(numInt > 50){
		JM.alert("数量过大！！", 2000);
		$("#num").val(10);
		total();
		return false;
	}
	if(numInt <= 0){
		JM.alert("数量不能小于0", 2000);
		$("#num").val(1);
		total();
		return false;
	}
	return true;
}

function addSum(){
	isNan();
	var num = $("#num").val();
	if(!isNaN(num)){
		var numInt = parseInt(num);
		numInt = numInt + 1;
		$("#num").val(numInt);
	}
}

function cutSum(){
	var num = $("#num").val();
	var numInt = parseInt(num);
	if(numInt == 1){
		numInt = 2;
	}
	numInt = numInt - 1;
	$("#num").val(numInt);
}

function num(){
	var num = $("#num").val();
	JM.goUrl("pcElectromobile_confirmPage.action?num=" + num);
}
