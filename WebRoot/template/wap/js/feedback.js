$(function(){
	uploadFile();
});

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
