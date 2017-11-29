/**
 * 查看常用旅客的js
 */
$(function() {
	$("#addcomm").click(add);
	$(".guestlist").find("i").click(xiugai);
	$("#delCommon").click(del);
});

function add() {
	JM.goUrl($("#manageLink").attr("href"));
}
/**
 * 跳到修改页面
 */
function xiugai() {
	var urlPost = $("#editCommon").attr("href");
	var id = $(this).next().val();
	JM.alert("请求中。。。", 1000, JM.goUrl(urlPost + "?id=" + id));
}
/**
 * 删除常用旅客
 */
function del() {
	var id = $("#travellerId").val();
	var url = $("#manageLink").attr("href");
	$.ajax({
		url : "commonTraveller_del.action",
		type : "post",
		dataType : "json",
		data : {
			"id" : id
		},
		success : function(data) {
			if (data.status == 'y') {
				if (data.status == 'y') {
					JM.alert(data.info, 2000, function() {
						JM.goUrl(url);
					});
				} else {
					JM.alert(data.info, 2000);
				}
			} else {
				JM.alert(data.info, 2000);
			}
		}
	});
}
