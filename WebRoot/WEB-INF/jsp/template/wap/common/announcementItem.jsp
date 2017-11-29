<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<s:iterator var="news" value="newsList">
	<li>
    	<div class="list-line"></div>
            <div class="list-main">
            <input type="hidden" value="/airport/${news.status.visitUrl }.ticket?" attrValue="${news.id }"/>
            <a href="javascript:;" onclick="XW.record(this);"> 
	            <div class="list-main-item1"> 
	            	<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id }" annexType="1" size="10"/>
	            	<s:if test="#request.queryAnnexList != null">
	            		<img src="${queryAnnexList[0].annexPath }" alt=" " />
	            	</s:if>
	                	<h1>${news.title } </h1>
	                	<p> ${fn:substring(news.introduce,0,10) }...</p>
	       		</div>
       		</a>
        	<h3 class="h_date"><s:date name="#news.status.createTime" format="yyyy-MM-dd"/></h3>
        	<div class="zanorshare fz12" style="display: none;"> 
	        	<a href="#" style="display:;" class="showdiv_reg"><i class="icon-share-alt"></i>分享</a>
        	</div>
        </div>
    </li>

	<%-- <dl class="clearfix">
		<dt> 
			<input type="hidden" value="/airport/${news.status.visitUrl }.ticket" attrValue="${news.id }"/>
			<a href="javascript:;" onclick="XW.record(this);"> 
				<img id="2" src="http://image.kmcsia.com/upload/site/attachment/201511/20151101063424.JPG" height="121" width="121"> 
			</a> 
		</dt>
		<input type="hidden" value="/airport/${news.status.visitUrl }.ticket" attrValue="${news.id }"/>
		<a href="javascript:;" onclick="XW.record(this);">
			<dd>
				<h6 class='fz18'>${news.title } </h6>
				<div class='desc fz16'> 摘要：${news.introduce }... <br /><span ><s:date name="#news.status.createTime" format="yyyy-MM-dd"/></span></div>
			</dd>
		</a>
		<div class="zanorshare fz12" >
			<!-- <img src="template/wap/images/zan.jpg" height="20" alt="" />5 -->
			<a href="#" style="display:;" class="showdiv_reg"><i class="icon-share-alt"></i>分享</a>
		</div>
	</dl> --%>
</s:iterator>