$(function(){
	
	$('.icon-plus').click(addImage);
	
	$('#classes').change(function(){
		
		var id = $(this).val();
		$.post('evaluationSetting_querySubByParentId.action', {id : id}, function(data){
			
			var project = $('#project');
			project.children().remove();
			for(var i = 0; i < data.length; i++){
				
				var item = data[i];
				if(i == 0){
					
					project.append('<td><input name="project" type="radio" value="' + item.id + '" checked="checked"/></td><td>' + item.name + '</td>');
				}else{
					
					project.append('<td><input name="project" type="radio" value="' + item.id + '" /></td><td>' + item.name + '</td>');
				}
			}
			$('input[name="project"').change(changeTarget);
			$('input[name="project"').trigger('change');
		}, 'json');
	});
	$('#classes').trigger('change');
});

function changeTarget(){
	
	if($(this).attr('checked') != true){
		
		return;
	}
	
	if(typeof(starCount) == 'undefined'){
		
		starCount = 0;
	}
	var id = $(this).val();
	$.post('evaluationSetting_querySubByParentId.action', {id : id}, function(data){
		
		var project = $('#target');
		project.children().remove();
		for(var i = 0; i < data.length; i++){
			
			var item = data[i];
			project.append('<div class="bhh" style="width:160px; text-align:center; margin-left:30px;"><input type="hidden" name="targetId" value="' + item.id + '"/><div>' + item.name + '</div><div id="start' + starCount + '"></div><div id="result' + starCount + '"></div></div>');
			rat('start'+starCount, 'result'+starCount, 1);
			starCount++;
		}
	}, 'json');
}

/**
 * 增加一张图片
 */
function addImage(){
    
	var $item = $(this);
    TuYou.FileUpload.upload(function(data){

        var $image = $('<input type="hidden" name="images"></input>');
        $image.val(data);
        $item.before($image);
        
        $item.attr('src', data);
        $item.parent().attr('href', data);
        $item.parent().attr('target', '_blank');
        
        JM.alert('上传成功...', 2000);
        
        var $upload = $('.icon-plus');
    	$upload.unbind('click');
        $item.removeClass('icon-plus');
        $upload = $('.icon-plus');
    	$upload.bind('click', addImage);
        
    }, '上传中...', 0);
}

/**
 * 删除图片
 * @param dom
 */
function deleteImage(dom){
    
    var $this = $(dom);
    var path = $this.parent().children('img').attr('src');
    $this.parent().remove();
    $('input[value="'+path+'"]').remove();
}