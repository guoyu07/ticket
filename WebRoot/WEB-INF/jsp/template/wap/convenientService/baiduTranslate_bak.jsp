<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	
	<script type="text/javascript">

	function Translation(){
		var from = $('#from option:selected').val();
		var to  = $('#to option:selected').val();
		var content = $("#content").val();
	    if(content==""||content.length<1){
			alert("请输入需要翻译的内容");
			return;
		}
		
		//var url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=yE99vPf4GlFYEih6xAowGwDX&q="+content+"&from="+from+"&to="+to;
		var url = "http://openapi.baidu.com/public/2.0/bmt/translate";
		$.ajax({
		     type: 'post',
		     url: url ,
		     dataType: "JSONP",
		     data:{
			   client_id:"yE99vPf4GlFYEih6xAowGwDX",
			   q:content,
			   from:from,
			   to:to
		     },
		     success: function(data){
			   //alert(JSON.stringify(data));

			   $('#from').val(data.from);
			   $('#to').val(data.to);
			   var rs = data.trans_result[0].dst;
			   $("#result").html(rs);
		     } ,
			 error:function(){
				alert("错误");
		     }
		});
	}

	function Exchange(){
		var from = $('#from option:selected').val();
		var to  = $('#to option:selected').val();
		$('#from').val(to);
		$('#to').val(from);
	}

	function setExChange(){
		var from = $('#from option:selected').val();
		if(from!="auto"){
			$("#exChange").unbind("click").bind("click",function (){
				Exchange();
			});
			$("#exChange").removeAttr("style");
		}else{
			$("#exChange").unbind("click");
			$("#exChange").css('color','#CCCCCC');
			
		}
	}
	
	</script>
	
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div style='margin: 40px 40px; font-size: 22px;'>
						<select id="from" class="input input-auto fz22" style='width: 41%; height: 50px;line-height:normal' onchange="setExChange()">
							<option selected value="auto">
								自动检测
							</option>
							<option  value="zh">
								中文
							</option>
							<option value="en">
								英语
							</option>
							<option value="jp">
								日语
							</option>
							<option value="kor">
								韩语
							</option>
							<option value="fra">
								法语
							</option>
							<option value="th">
								泰语
							</option>
							<option value="de">
								德语
							</option>
							<option value="ru">
								俄罗斯语
							</option>
							<option value="spa">
								西班牙语
							</option>
							<option value="ara">
								葡萄牙语
							</option>
						</select>
						<i id="exChange"   style="color:#CCCCCC" class='icon-exchange margin-large-left margin-large-right'></i>
						<select id="to" class="input input-auto fz22" style='width: 41%; height: 50px;line-height:normal'>
							<option selected  value="zh">
								中文
							</option>
							<option  value="en">
								英语
							</option>
							<option value="jp">
								日语
							</option>
							<option value="kor">
								韩语
							</option>
							<option value="fra">
								法语
							</option>
							<option value="th">
								泰语
							</option>
							<option value="de">
								德语
							</option>
							<option value="ru">
								俄罗斯语
							</option>
							<option value="spa">
								西班牙语
							</option>
							<option value="ara">
								葡萄牙语
							</option>
						</select>
						<textarea id="content" name="" class="input margin-big-top fz22" rows="5" cols="10"></textarea>
						<button  onclick="Translation()" type="button" class="button bg-sub input margin-big-top"
							style="height: 60px; font-size: 24px;">
							翻译
						</button>
						
						<div class="fy_ls" style="margin:40px 0px 0px;">
							<h2 class="padding fz22">翻译结果</h2>
							<h4 id="result">
								
								
							</h4>
						
						</div>
						
					</div>
					<%--<div class="fy_ls">
						<h4>
							今日推荐
							<a href="" class="float-right">查看以往  </a>
						</h4>
						<ul>
							<li class="clearfix">
								<em><img src="/template/wap/images/pic/61.jpg" height="67"
										width="67">
								</em><span>麻烦请给我你的护照
									<p>
										May I see you passport ,placese?
									</p>
								</span>
							</li>
							<li class="clearfix">
								<em><img src="/template/wap/images/pic/61.jpg" height="67"
										width="67">
								</em><span>麻烦请给我你的护照
									<p>
										May I see you passport ,placese?
									</p>
								</span>
							</li>
						</ul>
					</div>
				--%></div>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>