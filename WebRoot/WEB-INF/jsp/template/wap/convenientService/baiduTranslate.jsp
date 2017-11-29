<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/md5.js"></script>
	<script type="text/javascript">
	function Translation(){
		var from = $('#from option:selected').val();
		var to  = $('#to option:selected').val();
		var query = $("#content").val();
	    if(query == "" || query.length<1) {
			alert("请输入需要翻译的内容");
			return;
		}
		var appid = '20160203000010906';
		var key = '1dUU3Ps6j_8vkWmmL_94';
		var salt = (new Date).getTime();
		var str1 = appid + query + salt +key;
		var sign = MD5(str1);
		$.ajax({
		    url: '<%=request.getProtocol().equalsIgnoreCase("http") ? "http://api.fanyi.baidu.com/api/trans/vip/translate" : "https://fanyi-api.baidu.com/api/trans/vip/translate"%>',
		    type: 'get',
		    dataType: 'jsonp',
		    data: {
		        q: query,
		        appid: appid,
		        salt: salt,
		        from: from,
		        to: to,
		        sign: sign
		    },
		    success: function (data) {
		        $("#result").html(data.trans_result[0].dst);
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
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>