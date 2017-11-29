<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/news.js"></script>
	<!-- 百度地图 -->
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zfTIKtNx9zBgxLIwpAOt28dE"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理新闻信息</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td>
					      <input type="text" value="请输入新闻标题关键词" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword"  style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" class="my_input" style="width:55px;height: 33px;text-align: left" />
					      	
					      	&nbsp;&nbsp;根据新闻分类检索：
					      	<select name="newsClassManage_id" id="newsClassManage_id" class="my_select">
								<option value="">请选择位置</option>
								${newsClassHtml }
							</select>
					      <span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a href="#" class="batchCommendBtn" value="${actionName}CheckBox" entityName="${entityName}" style="display:none;">批量推荐</a>
					      	<a href="#" class="batchHotBtn" value="${actionName}CheckBox" entityName="${entityName}" style="display:none;">批量热门</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">新闻排序值</td>
							    <td width="80">新闻类别</td>
							    <td width="80">最后修改时间</td>
							    <td width="80">新闻标题</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td>${c.status.orderValue }</td>
									    <td>
									    	<ticket:systemCommon type="newsClassObj" value="${c.newsClass_id }"/>
									    	${newsClassObj.name }
									    </td>
									    <td><s:date name="#c.lastUpdateTime" format="yyyy-MM-dd"/> </td>
									    <td>${c.title }</td>
									    <td><span>
									    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}&newsClass_id=${newsClass_id}">编辑</a>
									    	<a href="/${actionName}_detail.action?id=${c.id }&versionFlag=${versionFlag}">详细信息</a>
									    	<s:if test="#c.status.audit == 1">
									    		<a href="#" class="auditEntity"  entityName="${entityName}" statusValue="0" value="${c.id }" style="color:#1D9010;">已审核</a>
									    	</s:if>
									    	<s:else>
									    		<a href="#" class="auditEntity"  entityName="${entityName}" statusValue="1" value="${c.id }" style="color:#FF0000;">未审核</a>
									    	</s:else>
									    	</span></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="8">${noDataMessage }</td>
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