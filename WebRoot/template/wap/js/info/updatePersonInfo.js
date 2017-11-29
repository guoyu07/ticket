$(function(){
	
	
	$('#uploadImage').click(function(){
		
//		TuYou.FileUpload.clipUpload();
		TuYou.FileUpload.upload(function(data){
			
			$('input[name="images"]').val(data);
			$('.icon-plus').attr('src', data);
			JM.alert('修改成功', 1500);
		});
	});
	
});

function selected(dom){
	var _this = $(dom);
	var logoUrl = _this.attr("src");
	$('input[name="images"]').val(logoUrl);
	$('.icon-plus').attr('src', logoUrl);
}