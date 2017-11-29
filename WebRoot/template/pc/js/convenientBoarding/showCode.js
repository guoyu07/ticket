/**
 * 购买成功后，点击没收到短信触发的js
 */
$(function(){
	$("#showCode").click(showCode);
});

function showCode(){
	var codes = $("h3 input");
	var codeArr = new Array();
	for(var i=0;i<codes.length;i++){
		var code = $(codes[i]).val();
		codeArr[i] = code;
	}
	easyDialog.open({
		container : {
		    header : '服务码',
		    content : "服务码为：" + codeArr.join(",") + "，请务必记住此服务码！"
		  },
		  drag : false
	});
}