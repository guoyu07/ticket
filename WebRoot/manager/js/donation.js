$(function() {
	var id = $("#idsValue").val();
	var code = $("#codes").val();
	var $tr = "";
	if (code.indexOf(",") != -1) {
		var codes = code.split(",");
		var ids = id.split(",");
		for ( var i = 0; i < codes.length; i++) {
			$tr += '<tr>' + '<td class="text_align_right" width="150">'
					+ '服务码：' + '</td>' + '<td>'
					+ '<input value="'+ codes[i] +'" disabled="disabled"/>'
					+ '<input name="code" value="'+ ids[i] +'" type="hidden"/>'
					+ '</td>' + '</tr>';
		}
		$("table tr").eq(0).before($tr);
	}else{
		$tr += '<tr>' + '<td class="text_align_right" width="150">'
		+ '服务码：' + '</td>' + '<td>'
		+ '<input value="'+ code +'" disabled="disabled" name="code"/>'
		+ '<input name="code" value="'+ id +'" type="hidden"/>'
		+ '</td>' + '</tr>';
		$("table tr").eq(0).before($tr);
	}
});