<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/airportInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td colspan="2">管理机场信息</td>
					    </tr>
					    <tr colspan="2">
							<td class="text_align_center" width="50%">
								请导入指定格式的excel表
								<input type="button" value="模板下载" id="downloadTemplate">
							</td>
							<td>
								<div id="importExcel">
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="thumbnail" name="thumbnail"
									style="width: 100px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    	<input id="ajaxSubmit" type="button" value="开始导入" onclick="upload();" class="btn_submit">
						    	</div>
						    </td>
					    </tr>
					    <tr class="text_align_left">
					    	<td colspan="2">
					    		<a href="javascript:void(0);" class="downLoadExcel" style="color:#1D9010;">下载机场信息</a>
					    	</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td colspan="2"><span>
					      	 <input type="text" value="请输入机场名称关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	<input type="hidden" name="domOrInt" id="domOrInt" value="${domOrInt }" />
					      	&nbsp;&nbsp;
					      	<select name="selectDistrict" id="selectDistrict" currentValue="${domOrInt }" class="my_select">
				      			<option value="">所有机场</option>
								<option value="domestic">国内机场</option>
								<option value="international">国际机场</option>
							</select>
							<a href="/airportInfo_recycle.action?versionFlag=site">机场信息回收站</a>
							<a href="/airportInfo_add.action?versionFlag=site">新增机场信息</a>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}">批量热门</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td colspan="2">
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">机场名称</td>
							    <td width="80">三字码</td>
							    <td width="80">四字码</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.name }</td>
									    <td>${c.threeCode }</td>
									    <td>${c.fourCode }</td>
									    <td><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span>
									    	<s:if test="#c.status.hot == 1">
									    		<a href="#" class="hotAirport"  entityName="${entityName}" statusValue="0" value="${c.id }" style="color:#1D9010;">取消热门</a>
									    	</s:if>
									    	<s:else>
									    		<a href="#" class="hotAirport"  entityName="${entityName}" statusValue="1" value="${c.id }" style="color:#1D9010;">设为热门</a>
									    	</s:else>
									    </td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="7">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
					       </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>