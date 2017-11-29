/**
 * 点击了落地页
 */
function havaUrl(dom) {
	var id = $("#countId").val();
	var goUrl = $(dom).text();
	var goUrl1 = JM.trim(goUrl);
	var url1 = $(dom).attr("attrValue");
	var keyword = $("#keyword").val();
	$.ajax({
		url : "countSearch_addGoUrl.action",
		type : "post",
		dataType : "json",
		data : {
			"id" : id,
			"goUrl" : goUrl1,
			"goUrlHref" : url1,
			"clickKeyword" : keyword
		},
		success : function() {
			JM.goUrl(url1);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			JM.goUrl(url1);
		}
	});
}
/**
 * 点击了地图
 */
function goDitu(dom) {
	var id = $("#countId").val();
	var goUrl = $(dom).text();
	var goUrl1 = JM.trim(goUrl);
	var url1 = $(dom).attr("attrValue");
	$.ajax({
		url : "countSearch_addGoUrl.action",
		type : "post",
		dataType : "json",
		data : {
			"id" : id,
			"goUrl" : goUrl1,
			"goUrlHref" : url1
		},
		success : function() {
			JM.goUrl(url1);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			JM.goUrl(url1);
		}
	});
}
/**
 * 点击了百度
 */
function goBaidu(dom) {
	var id = $("#countId").val();
	var goUrl = $(dom).text();
	var goUrl1 = JM.trim(goUrl);
	var url1 = $(dom).attr("attrValue");
	$.ajax({
		url : "countSearch_addGoUrl.action",
		type : "post",
		dataType : "json",
		data : {
			"id" : id,
			"goUrl" : goUrl1,
			"goUrlHref" : url1
		},
		success : function() {
			JM.goUrl(url1);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			JM.goUrl(url1);
		}
	});
}