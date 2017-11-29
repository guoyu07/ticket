<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<link rel="stylesheet" href="/template/wap/css/StyleSheet.css">
	<script type="text/javascript">
	window.onload=function(){
			$.do_dialog.open({'msg':$('.dialog_openApp'),initBefore:function(rs){
	            $('.dialog_openApp .cls_btn').on('tap',function(){
	                $.do_dialog.close(rs.index);
	            });
	        }});
			//alert('此功能需要下载app，请点击屏幕下方下载.');
			//JM.scrollTo('.mobile-main', '.tit_tab', 0, 3000);
			//var href = "itms-services://?action=download-manifest&url=https://www.kcia.com.cn/app/ios/manifest.plist";
           
            document.getElementById('openApp').onclick = function(e){  
            	 JM.alert("请稍等……",-1);
            	 var u = navigator.userAgent;
                 var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
                 var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
                 if(isAndroid){
                	window.location.href="csjcapp://csjc.app/openwith";
	                var ifr = document.createElement('iframe');  
	                ifr.src = 'csjcapp://';  
	                ifr.style.display = 'none';  
	                document.body.appendChild(ifr);  
	                window.setTimeout(function(){  
	                	JM.alert("",100);
	                	var openReminder = window.confirm("温馨提示：尝试打开APP失败！请确保您已安装APP应用！是否下载新版本？");
	                	if(openReminder){
		                    window.location.href="http://m.kunmingia.com/app/android/csjc.apk"; 
	                	}else{
	                		return;
	                	}
	                	
	                },3000);  
            	}if(isiOS){
            		window.location.href="openCSJC://";
	                var ifr = document.createElement('iframe');  
	                ifr.src = 'csjcapp://';  
	                ifr.style.display = 'none';  
	                document.body.appendChild(ifr);  
	                window.setTimeout(function(){  
	                	JM.alert("",100);
		                window.location.href="itms-apps://itunes.apple.com/cn/app/qq-2011/id1134414490?mt=8"; 
	                	
	                },3000);  
            		
            	 }
            };
		}; 
	</script>

	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="网上值机" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="kv_pics mr40">
						<ul>
							<li>
								<ticket:common type="advertListByTypeName" value="值机须知图片"/>
								<s:if test="#request.advertListByTypeName != null">
									<a href="${advertListByTypeName.url }">
										<ticket:commonAnnex type="annex" entityUuid="${advertListByTypeName.id }" annexType="1" size="1"/>
										<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" style="width:87%;">
									</a>
								</s:if>
							</li>
						</ul>
					</div>
					<div class="c_content text-left fz20">
						<ticket:common type="systemDicObjByValue" value="checkOnlineNotice"/>
						${systemDicObjByValue.description }
					</div>
					<div class="tit_tab">
						<a href="${systemConfig.iosPath }" class="padding" style="padding-top: 20px;"><img
								src="/template/wap/images/ios_app_down.png">
						</a>
						<a href="${systemConfig.androidPath }" class='padding selected' style="padding-top: 20px;"><img
								src="/template/wap/images/android_app_down.png">
						</a>
					</div>
					<div class="tit_tab">
						<a href="/airport/bianjiedengji.ticket" class="padding selected border"
							style="background: none">便捷登机</a>
						<a href="/airport_trafficGuide.action" class='padding selected border'
							style="background: none">交通指南</a>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		<div style='display:none'>
            <div class="dialog_down box_dialog">
                <div class="c_content b_white">
                    <div class="media media-x">
                        <div class="media-body fz18 text-center">
                            <i class='icon-download c_blue' style='font-size:100px;'></i>
                            <br>
                           	 此功能需要使用app,未在当前系统上检测到APP应用，是否立刻下载？
                        </div>
                    </div>
                    <div class="tit_tab">
                        <a href="/app/android/csjc.apk" class=''>下载</a>
                        <a href="javascript:;" class='b_l_grey c_grey cls_btn'>取消</a>
                    </div>
                </div>
            </div>
    	</div>
		<div style='display:none'>
            <div class="dialog_openApp box_dialog">
                <div class="c_content b_white">
                    <div class="media media-x">
                        <div class="media-body fz18 text-center">
                            <i class='icon-download c_blue' style='font-size:100px;'></i>
                            <br>
                           	 该功能需要使用APP,是否马上打开？
                        </div>
                    </div>
                    <div class="tit_tab">
                        <a href="#" class='' id="openApp">打开</a>
                        <a href="javascript:;" class='b_l_grey c_grey cls_btn'>取消</a>
                    </div>
                </div>
            </div>
    	</div>
		
	</body>
</html>