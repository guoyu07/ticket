<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<s:if test="uploadFile != null">
	<s:set name="ext" value="uploadFile.name.substring(uploadFile.name.indexOf('.')+1)"/>
	<div id="span${uploadFile.id}" style=" width:100px; float:left; margin:0 2px 0 0;"> 
          <div style=" clear:both;"> 
            <a style="width:16px; height:16px; float:right;" href="#" onclick="delImg('${uploadFile.id}','${uploadFile.name }');return false;">
				<img src="/common/jQueryUpload/cancel.png" border="0" style="width:16px;height: 16px;"/> </a> 
				<input type="hidden" id="fileNames" name="fileNames"
				value="/${uploadFile.name}${UPLOAD_SEPARATOR_VALUE}${uploadFile.type}${UPLOAD_SEPARATOR_VALUE}${uploadFile.oldName}" /> 
          </div>
          <div style="clear:both;">
			<s:if test="#ext == 'flv'">
				<img id="${uploadFile.type}" src="/common/jQueryUpload/videoAnnex.jpg" width="100">&nbsp;
			</s:if>
			<s:elseif test="#ext == 'swf'">
				 <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100" height="100">
	                <param name="movie" value="/${uploadFile.name}">
	                <param name="quality" value="high">
	                <param name="wmode" value="transparent">
	                <embed src="/${uploadFile.name}" width="100"  height="100" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>
                </object>
			</s:elseif>
			<s:else>
				<img id="${uploadFile.type}" src="<ticket:jsupload src="/${uploadFile.name}"/>" width="100">&nbsp;
			</s:else>
          </div>		 
	</div>
</s:if>