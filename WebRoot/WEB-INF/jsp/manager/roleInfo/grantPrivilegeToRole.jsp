<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/roleInfo.js"></script>
	<script type="text/javascript">
		$(function(){
			d = new dTree('d');
			${moduleHtml}
			$("#showTree").html(d + "");
			$(".dTreeNode").hover(function(){
				$(this).css("background","#f5f5f5");
			},function(){
				$(this).css("background","fff");
			});
			$(".grantPrivilege").each(function(){
				$(this).prop("checked", "checked");
				$("#selectAllCheckBox").prop("checked", "checked");
			});
			$("input[type=checkbox]").bind("click", function(){
                  var systemModuleId = $(this).attr("systemModuleId");
                  var roleId = $(this).attr("roleId");
                  var privilegeId = $(this).attr("privilegeId");
                  var temp=$("#selectAllCheckBox").attr("selectAll");
                  if(roleId == "undefined" || roleId == null){
						return null;
                      }
                  if($(this).prop("checked")){
                	  $.ajax({
                			type:'POST',
                			dataType:'text',
                			url:'rolePrivilege_add.action',
                			data:'roleId='+roleId+'&systemModuleId='+systemModuleId+'&privilegeId='+privilegeId+'&random='+JM.randomNumber,
                			success:function(data){
                				//alert('ok');
                			}
                		});
                      }
                  else{
                	  $.ajax({
                			type:'POST',
                			dataType:'text',
                			url:'rolePrivilege_revoke.action',
                			data:'roleId='+roleId+'&systemModuleId='+systemModuleId+'&privilegeId='+privilegeId+'&random='+JM.randomNumber,
                			success:function(data){
                				//alert('no');
                			}
                		});
                      }
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
					<input type="hidden" id="roleId" name="roleId" value="${id }"/>
					<table class="my_table100_tree margin_top10">
						<tr>
							<td>
								<ticket:systemCommon type="roleObj" roleId="${id }"/>
							给${roleObj.name }授权</td></td>
						</tr>
						<tr class="text_align_left my_table_top">
							<td>
								<span>
									 <a href="javascript: d.openAll();">展开所有</a> 
									 <a href="javascript: d.closeAll();">收起所有</a>
								</span>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<div id="showTree" style="padding-top:10px; padding-left: 0px; padding-right: 0px;"></div>
							</td>
							<td><input type="checkbox" id="selectAllCheckBox" name="selectAllCheckBox"/>全选/取消选中<br />
							</td>
						</tr>
					</table>
					<!-- <table id="showTree" style="width:100%;height:100%;" ></table> -->
				</div>
			</div>
		</div>
		<!--自定义弹出框-->
		<div id="popLayer" class="popdiv">
			<div class="popup_show">
				正在执行<img src="/manager/images/processing.gif" style="height: 50px;width: 50px"/>
			</div>
		</div>
		<div id="popbgLayer" class="popupbg"></div>
		<iframe id='popIframeLayer' class='popIframe' frameborder='0'></iframe>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>