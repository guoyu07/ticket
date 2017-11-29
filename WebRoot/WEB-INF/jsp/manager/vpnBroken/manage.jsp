<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		$(function(){
			
			$('.pageNotify').click(function(){
				
				$.post(actionName + '_set.action', {}, function(json){
					
					if(json.status == JM.YES){
						
						JM.alert(json.info, 2000);
						location.reload();
					}else{
						
						JM.alert(json.info, 3000);
					}
				}, 'json');
			});
		});
	</script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td >管理vpn断线记录</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							    <td width="80">断线时间</td>
							    <td width="80">页面提醒</td>
							    <td width="80">短信通知次数</td>
							    <td width="80">状态</td>
							    <td width="80">备注</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
									    <td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
									    <td>${c.pageNotify == true ? '已打开' : '未打开' }</td>
									    <td>${c.smsNotifyCount }</td>
									    <td>${c.state.text }</td>
									    <td>${c.remark }</td>
									    <td><span>
									    	<s:if test="#c.state.value == 'untreated'">
									    		<a href="${actionName }_edit.action?versionFlag=site">回馈处理结果</a>
										    	<s:if test="#c.pageNotify == true">
										    		<a href="#" class="pageNotify" style="color:#FF0000;">关闭页面提示</a>
										    	</s:if>
										    	<s:else>
										    		<a href="#" class="pageNotify" style="color:#1D9010;">打开页面提示</a>
										    	</s:else>
									    	</s:if>
									    	</span>
									    </td>
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