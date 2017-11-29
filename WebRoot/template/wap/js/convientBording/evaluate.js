$(function(){
	
	$('#upload').click(addImage);
	
    $('.icon-star').on('tap', addScore);
    
    //处理低分选择原因的
    //点击标签也触发checkbox的选择事件
    $('.checkbox_label').on('tap', function(e){
    	
        var checkbox = $(this).children('.checkbox');
        selectReason(checkbox);
    });
});

/**
 * 处理打星的
 */
function addScore(){
    
    var item = $(this);
    var $stars = item.parent().children(); 
    var index = $stars.index(item);

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
    
    //低于等于三分打显示低分原因
    if(index<=2 && item.parent().next('.dialog').size() > 0){
        confirmReasion = $.do_dialog.open({
        	'msg':item.parent().next('.dialog').find('.search_dialog')
        });
    }
}

/**
 * 低分原因保存
 */
function selectReason(checkbox){
	
	var reason = [];
	
	//先查看自己是否选中
	if(!checkbox.hasClass('.icon-check-square')){
		
		reason.push(checkbox.next().val());
	}
	
	//再查看兄弟节点是否选中
	var siblings = checkbox.parent().siblings();
    for(var i = 0; i < siblings.length; i++){
    	
    	input = $(siblings[i]).children('.icon-check-square');
    	if(input.size() > 0){
    		
    		reason.push(input.next().val());
    	}
    }
    
    var parentId = checkbox.attr('parentId');
    var reasonDom = $('#'+parentId);
    reasonDom.val(reason.join("、"));
}

/**
 * 增加一张图片
 */
function addImage(){
    
    FileUpload.upload(function(data){
        
        var $image = $('<input type="hidden" name="images"></input>');
        $image.val(data);
        $('#memberForm').append($image);
        
        var $a = $('<a href="'+data+'" target="_blank" class="float-left padding-little"><img src="'+data+'" height="135" width="135"/></img>');
        var $delete = $('<p class="text-center c_red fz18" onclick="deleteImage(this);return false;">删除</p>');
        $a.append($delete);
        $('#upload').before($a);
        JM.alert('上传成功...', 2000);
        
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


