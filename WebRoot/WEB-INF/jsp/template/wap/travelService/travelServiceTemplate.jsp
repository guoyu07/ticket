<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <!-- <script src="/template/wap/layer/layer.js"></script> -->
    <link rel="stylesheet" href="/template/wap/css/jd.css">
    <link href="/template/wap/css/iSlider.css" rel="stylesheet">
    
    <script type="text/javascript">
		$(function(){
			query();
		});
	
		function query(){
			
			pagingQuery('nextPage', 'newss_scenicSpotsNewsList.action');
			
		}
		function pagingQuery(triggerButtonId, url){
			
			var $nextPage = $('#' + triggerButtonId);
			if(typeof(start_paging) == 'undefined'){
				
				start_paging = 0;
			}
			$.post(url, {pn : start_paging}, function(text){
				
				if(JM.isNull($.trim(text))){

					JM.alert('已加载到最后',2000);
				}else{
					
					$nextPage.prev().append(text);
					start_paging += 20;
				}
					
			}, 'html');
		};
	</script>
    <style type="text/css">
    

.arc_list ul li h1{text-overflow: ellipsis; width: 614px;}
    
    </style>
	<body class="mobile">
    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
		<div class="mobile-view">
        </s:if>
          			<s:else>
                    <div class="mobile-view" style=" top:-94px;">
                    </s:else>
      		<div class="mobile-page">
    			<div class="mobile-top">
          			<div class="header"> <a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>自助旅游 <a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn"> <%-- <i class="icon-bell"></i><ticket:common type="newMessages"/><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if> --%><c:choose>
									<c:when test="${!empty sessionMember }">
										<ticket:common type="newMessages"/>
										<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
									</c:when>
									<c:otherwise>
										<ticket:common type="myAnnounecementCount"/>
										<i class="icon-bell"></i><c:if test="${myAnnounecementCount == 1 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
									</c:otherwise>
								</c:choose> </a> </div>
                    <div id="iSlider-wrapper"></div>
                    
 <script type="text/javascript" src="/template/wap/js/iSlider.js"></script>
<script type="text/javascript" src="/template/wap/js/iSlider.animate.js"></script>
<script id="show-code">
	$(document).ready(function(){
		var imgs = $("input[name='imgs']");
		
		var list = new Array();
		for(var i=0;i<imgs.length;i++){
			var imgList = $(imgs[i]).val();
			var datalink=$(imgs[i]).attr("datalink");
			var img = {content: imgList,url:datalink};
			list.push(img);
		}
	    /* var list = [
	        {content: "http://image.kmcsia.com/upload/site/image/201609/20160930034837.jpg"},
	        {content: "http://image.kmcsia.com/upload/site/image/201609/20160930034758.jpg"},
	        {content: "http://image.kmcsia.com/upload/site/image/201609/20160930034727.jpg"},
			{content: "http://image.kmcsia.com/upload/site/image/201609/20160930034837.jpg"},
	        {content: "http://image.kmcsia.com/upload/site/image/201609/20160930034758.jpg"},
	        {content: "http://image.kmcsia.com/upload/site/image/201609/20160930034727.jpg"},
			{content: "http://image.kmcsia.com/upload/site/image/201609/20160930034837.jpg"},
	        {content: "/template/wap/images/jd_ad1.jpg"}
	    ];
	 */
	    var S = new iSlider(document.getElementById('iSlider-wrapper'), list, {
	        isLooping: 1,
	        isOverspread: 1,
	        isAutoplay: 1,
	        animateTime: 800,
	        animateType: 'flow'
	    });
	});
</script>

          <img width="100%" alt=" " src="/template/wap/images/top_shw.jpg" style="margin-top: 53px; position:absolute; 
       z-index:999;">
        </div>
    	 <ticket:common type="advertListNew" value="旅游咨询首页幻灯片图片" size="10"/>
		<s:if test="#request.advertListNew != null">
			<s:iterator id="advert" value="#request.advertListNew" status="st">
				<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
					<input type="hidden" value="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" datalink="${advert.url}" name="imgs"/>
			</s:iterator>
		</s:if>
    <!--------go 景点---------->
    <div class="mobile-jd" style="margin-top:0px; top:400px;"><a href="/airport_scenicSpotsList.action"> <img src="/template/wap/images/jd_01.jpg" alt=" " class="jd_more"></a>
          <div class="jd-list">
        <ul>
        	<ticket:common type="scenicSpotsHotList"/>
        	<s:if test="#request.scenicSpotsHotList != null">
        		<s:iterator value="#request.scenicSpotsHotList" var="scenicSpots">
        			<%-- <s:if test="#st.count <= 3"> --%>
	            	<li> 
		              	<a href="/airport_scenicSpotsDetail.action?id=${scenicSpots.id }">
		                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                		<tr>
		                    		<td width="220">
		                    			<ticket:commonAnnex type="annex" entityUuid="${scenicSpots.id}" annexType="1" size="1"/>
										<s:if test="#request.annex != null">
											<s:iterator id="annex" value="#request.annex" status="st">
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class='img_jd'>
											</s:iterator>
										</s:if>
		                    		</td>
		                    		<td class="jd-li-r">
			                    		<h1>${scenicSpots.name }</h1>
			                    		<h2><s:date name="#scenicSpots.status.createTime" format="yyyy-MM-dd"/></h2>
			                    		<p>${scenicSpots.introduce }</p>
			                    		<h3>热门景点<img src="/template/wap/images/icon_3.jpg" alt=" "></h3>
		                    		</td>
		                  		</tr>
		              		</table>
		                </a> 
	                </li>
	               <%--  </s:if> --%>
                </s:iterator>
        	</s:if>
            </ul>
      </div>
          <a href="#"><img src="/template/wap/images/jd_05.jpg" alt=" " class="img_arc_t"></a>
          <div class="arc_list">
        	<!-- ajax加载数据 -->
      	</div>
          <div  class="notice_ft" id="nextPage" onclick="javascript:query();"><i class="icon-arrow-circle-up"></i>点击加载更多 </div>
        </div>
    
    <!--------end 景点----------> 
    
  </div>
        <%@ include file="../common/quickNav.jsp"%>
    </div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>