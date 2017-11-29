<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<s:iterator var="item" value="evaluations">
<div class="panel mr40 border10">
	<div class="panel-head fz22 padding-big-top padding-big-bottom">
		评价时间：<s:date name="#item.status.createTime" format="yyyy-MM-dd"/>
		<a href="javascript:;" var="${item.feedback }" value="${item.id }" class='button bg-sub float-right feedbacks'>查看管理员回复</a>
	</div>
	<div class="panel-body fz22 padding-big-top padding-big-bottom">
		<div class="media media-x">
        <div class="media-body">
				${item.content }
			</div>
        <div class="clearfix"></div>
        
        <div class="lk content">
    <div class="main">
			<c:forEach var="image" items="${fn:split(item.images, ',') }">
				<c:if test="${image != null && image != '' ? true : false }">
					<a class="float-left Slide pl_${item.id }" href="javascript:;" i="${image }">
						<img src="${image }" height="100" width="100">
					</a>
				</c:if>
			</c:forEach>
			</div></div>
            <script type="text/javascript">
    $('.pl_${item.id }').simpleSlide();
</script>
            
		</div>
        
	</div>
</div>


</s:iterator>
