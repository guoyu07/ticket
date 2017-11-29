<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<!-- <script src="/template/wap/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/template/wap/js/jquery.Gu.js"></script> -->
	<script type="text/javascript">
		$(function(){
			query();
		});
	
		function query(){
			
			XW.pagingQuery('nextPage', 'newss_list.action',a);
			
		}
		
		function a(){
			$(".showdiv_reg").unbind("click");
			$(".showdiv_reg").bind("click",function(){
				$("#popupLayer_reg").slideDown();
				$("#popupbgLayer").fadeIn(200);
				$("#popupIframeLayer").fadeIn(100);
			});
		}
	</script>
	<!-- <link rel="stylesheet" href="/template/wap/css/jquery.hiSlider.min.css"> -->
	<div class="mobile-view">
      	<div class="mobile-page">
    	<jsp:include page="../common/title.jsp">
        	<jsp:param value="通知中心" name="title"/>
        </jsp:include>
        <%-- <%@ include file="../common/favoriteAndShare.jsp" %> --%>
        <div class="tab-notice">
					
       <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <td class="tab-sel">
          	<ticket:common type="myAnnounecementCount"/>
          	<a href="javascript:location.replace('/airport/newss_announcement.action')">公告板<s:if test="#request.myAnnounecementCount == 1"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td>
          	<ticket:common type="myMessageCount"/>
          	<a href="javascript:location.replace('/airportm_myMessage.action')">私信<s:if test="#request.myMessageCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myEvaluationCount"/>
          	<a href="javascript:location.replace('/evaluation_myEvaluatePage.action')">我的评论<s:if test="#request.myEvaluationCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
          <td >
          	<ticket:common type="myFeedBackCount"/>
          	<a href="javascript:location.replace('/feedBack_myFeedBack.action')">我的反馈<s:if test="#request.myFeedBackCount > 0"><span class="tab-badge bg-red"></span></s:if></a>
          </td>
        </tr>
      </table>
    </div>  <hr color="#d8d8d8">
    	<div class="mobile-main">
    	
    		<div class="list-ad"> 
    			<ticket:common type="advertListNew" value="通知中心图片" size="10"/>
                  	<s:if test="#request.advertListNew != null">
						<s:iterator id="advert" value="#request.advertListNew">
							<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
			    			<a href="${advert.url }"><img src="${annex.annexPath}" alt=" " /></a>
			    			<span>${advert.name }</span>
                  		</s:iterator>
                  	</s:if>
        		<p style="display:none;">1<font class="fbt1" >/3</font></p>
      		</div>
          	<div class="list-jclby">
        		<ul>
              		
            	</ul>
            	<div  class="notice_ft" onclick="javascript:query();" id="nextPage"><i class="icon-arrow-circle-up" ></i>点击加载更多 </div>
      		</div>
     		<%-- <ul class="hiSlider hiSlider3">
     		<ticket:common type="advertListNew" value="通知中心图片" size="10"/>
     			<s:if test="#request.advertListNew != null">
					<s:iterator id="advert" value="#request.advertListNew">
						<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
		        		<li class="hiSlider-item"><img src="${annex.annexPath}" alt="${advert.name }"></li>
		        	</s:iterator>
		   		</s:if>
		    </ul> --%>
			<!-- <script src="/template/wap/js/jquery.hiSlider.min.js"></script>
            <script type="text/javascript">
				var jq=$.noConflict();
 					jq('.hiSlider3').hiSlider({
	        		isFlexible: true,
	        		isSupportTouch: true,
	        		titleAttr: function(curIdx){
	            		return jq('img', this).attr('alt');
	        		}
	    		});

			</script> -->
          	<!-- <input type="hidden" id="newsClass_id" value="cbd4f6c0-f20f-4848-855f-2df8978da5be"/>
          	<div class="notice_ls">
        		<div id="loadNews">
        			异步加载
            	</div>
        		<h3 class="notice_ft" startSize="4" onclick="javascript:query();" id="nextPage"><i class="icon-arrow-up"></i>点击加载更多 </h3>
      		</div> -->
		</div>
		<%@ include file="../common/quickNav.jsp"%>
		</div>
    </div>
	<div class="dialog" style="display:none;">
		<%@ include file="../common/left.jsp"%>
	</div>
	<!--自定义登陆弹出框-->
<div id="popupbgLayer" class="popupbg"></div>
<iframe id='popupIframeLayer' class='popupIframe' frameborder='0' ></iframe>

<!-- JiaThis Button BEGIN -->
<div class="jiathis_style_32x32 popupdiv" id="popupLayer_reg">
	<div class="popup_title"><span>分享到</span>
		<div id="closeDiv_reg" class="popupclose_btn"><a href="#">&times;</a></div>
	</div>	
	<div class="popup_show">
		<a class="jiathis_button_tsina">新浪微博</a>
		<a class="jiathis_button_weixin">微信</a>
		<a class="jiathis_button_qzone">QQ空间</a>
		<a class="jiathis_button_cqq">QQ好友</a>
		<a class="jiathis_button_email">邮件</a>
		<a class="jiathis_button_tieba">百度贴吧</a>
		<a class="jiathis_button_baidu">百度空间</a>
		<a class="jiathis_button_meilishuo">美丽说</a>
		<a class="jiathis_button_copy">复制网址</a>
		<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
		<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
	</div>
</div>

	
</body>
</html>
