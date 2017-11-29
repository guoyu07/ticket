$(function(){
	
	reInitAddStar();
		
	$("#classes").change(function(){
		
		var value = $(this).val();
		$.post(
				'evaluationSetting_querySubByParentId.action',
				{id : value},
				function(data){
					
					var json = data.info;
					if(json.length > 0){
								
						//执行默认动作
						var $project = $("#project");
						$project.children().remove();
						for(var j = 0; json && j < json.length; j++){
							
							$project.append('<option value="' + json[j].id + '">' + json[j].name + '</option>');
						}
						$project.trigger('change');
					}
					
				}, 'json');
	});
		
	$("#project").change(function(){
		
		var value = $(this).val();
		$.post(
				'evaluationSetting_querySubByParentId.action',
				{id : value},
				function(data){
					
					var json = data.info;
					if(json.length > 0){
						
						$target = $("#target");
						$target.children().remove();
						for(var i = 0; json && i < json.length; i++){
							
							$target.append(
									'<div class="line">' + 
									'<div class="x7 fz22 height-large">' + json[i].name + '</div>' + 
									'<input name="targetId" type="hidden" value="' + json[i].id + '"></input>' + 
									'<input name="star" type="hidden" value="5"></input>' + 
									'<div class="x5 star_lavel">' +
									'<i class="icon-star fz30 text-dot"></i>' +
									'<i class="icon-star fz30 text-dot"></i>' +
									'<i class="icon-star fz30 text-dot"></i>' +
									'<i class="icon-star fz30 text-dot"></i>' +
									'<i class="icon-star fz30 text-dot"></i>' +
									'</div>' +
							'</div>');
						}
						reInitAddStar();
					}
					
				}, 'json');
	});
	
	uploadFile();
	$('#classes').trigger('change');
});

/**
 * 重新初始化点击事件
 */
function reInitAddStar(){
	
	//处理打星的
    var $star = $('.icon-star');
    $star.unbind('tap').bind('tap', function(){
        
        var item = $(this);
        var $stars = item.parent().children(); 
        var index = $stars.index(item);
        
        //保存打分的隐藏域
        var input = item.parent().prev();
        input.val(index+1);
        
        for(var i = 0; i < $stars.size(); i++){
            
            var star = $($stars.get(i));
            if(i <= index){
                star.addClass('text-dot');
            }else{
                star.removeClass('text-dot');
            }
        }
    });
}

function uploadFile(){
	
	var $upload = $('.icon-plus');
	$upload.click(addImage);
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
        
//        var $delete = $('<p class="text-center c_red fz18" onclick="deleteImage(this)">删除</p>');
//        $item.after($delete);
        
        JM.alert('上传成功...', 2000);
        
        var $upload = $('.icon-plus');
    	$upload.unbind('click');
        $item.removeClass('icon-plus');
        $upload = $('.icon-plus');
    	$upload.bind('click', addImage);
        
    }, '上传中...', -1);
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