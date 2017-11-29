FileUpload = {};

FileUpload.upload = function(callback, tip, tipTime){
	
	var $form = $('<form enctype="multipart/form-data" accept="image/gif, image/jpeg, image/x-png">');
	var $input = $('<input type="file" name="file.files">');
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
	
};