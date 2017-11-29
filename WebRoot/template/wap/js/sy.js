function hd(objThis)
{
	hide_mask();
		document.getElementById('div_mask').style.display='block';
		document.getElementById('nav_hd').style.display='block';
		$('.ul_nav').find(objThis).addClass('li_on').siblings().removeClass('li_on');
		$('.ul_nav').find(objThis).html($('.ul_nav').find(objThis).html()+"<img class='navarrowdown' src='/template/wap/images/arrowdown.png' alt=' ' />");
		$('.ul_nav li').removeClass('top_sel');
		
	}
	function hide_mask()
	{
		$('.ul_nav li').removeClass('top_sel');
		var len=$('.ul_nav li').length;
		for(var i=0;i<len;i++)
		{
				$('.ul_nav li').eq(i).html($('.ul_nav li').eq(i).attr("title"));
			}
		
		
		
		document.getElementById('div_mask').style.display='none';
		document.getElementById('nav_hd').style.display='none';
		document.getElementById('div_type').style.display='none';
		document.getElementById('div_wz').style.display='none';
		document.getElementById('div_px').style.display='none';
		$('.ul_nav').find('.li_on').html($('.ul_nav').find('.li_on').html()+"<img class='navarrowdown' src='/template/wap/images/arrowdown.png' alt=' ' />");
		$('.ul_nav').find('.li_on').addClass('top_sel').removeClass('li_on');
		
	}
		
		function px(objThis)
{
	hide_mask();
		document.getElementById('div_mask').style.display='block';
		document.getElementById('div_px').style.display='block';
		$('.ul_nav').find(objThis).addClass('li_on').siblings().removeClass('li_on');
		
		$('.ul_nav').find(objThis).html($('.ul_nav').find(objThis).html()+"<img class='navarrowdown' src='/template/wap/images/arrowdown.png' alt=' ' />");
		$('.ul_nav li').removeClass('top_sel');
	}
		
		function wz(objThis)
		{
			hide_mask();
		document.getElementById('div_mask').style.display='block';
		document.getElementById('div_wz').style.display='block';
		$('.ul_nav').find(objThis).addClass('li_on').siblings().removeClass('li_on');
		$('.ul_nav').find(objThis).html($('.ul_nav').find(objThis).html()+"<img class='navarrowdown' src='/template/wap/images/arrowdown.png' alt=' ' />");
		$('.ul_nav li').removeClass('top_sel');
			}
	function fl(objThis){
		
		
		hide_mask();
		document.getElementById('div_mask').style.display='block';
		document.getElementById('div_type').style.display='block';
		
		$('.ul_nav').find(objThis).addClass('li_on').siblings().removeClass('li_on');
		$('.ul_nav').find(objThis).html($('.ul_nav').find(objThis).html()+"<img class='navarrowdown' src='/template/wap/images/arrowdown.png' alt=' ' />");
		$('.ul_nav li').removeClass('top_sel');
		
		
		}
		function sel_fl(objValue,objThis)
		{
				  var ulist=$('.type_l ul');
	  ulist.find(objThis).addClass('type_sel').siblings().removeClass('type_sel');
	  
	  $('.ul_nav_c').removeClass('show')
	  $('#ul_nav_c'+objValue).addClass('show');
	  //$('#ul_nav_c'+objValue).children().children().first().addClass('li_sel');
	}
	
	function sel_wz(objValue,objThis)
		{
				  var ulist=$('.wz_l ul');
	  ulist.find(objThis).addClass('wz_sel').siblings().removeClass('wz_sel');
	  
	  $('.ul_wz_c').removeClass('show')
	  $('#ul_wz_c'+objValue).addClass('show');
	  //$('#ul_nav_c'+objValue).children().children().first().addClass('li_sel');
	}
	 jQuery(document).ready(function () {
		 
		 jQuery(".ul_nav_c ul li").on("click", function () {
			 /*
                var str1 = jQuery(this).html();
                alert(str1);
				*/
				$('.type_r div ul li').removeClass('li_sel');
				
				jQuery(this).parent().find(jQuery(this)).addClass('li_sel');
				hide_mask();
				location.href=jQuery(this).attr('ref');
            });
			
			jQuery(".nav_hd ul li").on("click", function () {
			 
				hide_mask();
				location.href=jQuery(this).attr('ref');
            });
			
			jQuery(".ul_wz_c ul li").on("click", function () {
			 /*
                var str1 = jQuery(this).html();
                alert(str1);
				*/
				
				$('.wz_r div ul li').removeClass('li_sel');
				jQuery(this).parent().find(jQuery(this)).addClass('li_sel');
				hide_mask();
				location.href=jQuery(this).attr('ref');
            });
			
			jQuery(".div_px ul li").on("click", function () {
			 /*
                var str1 = jQuery(this).html();
                alert(str1);
				*/
				jQuery(this).parent().find(jQuery(this)).addClass('li_sel1').siblings().removeClass('li_sel1');
				hide_mask();
				location.href=jQuery(this).attr('ref');
            });
		 
	 });