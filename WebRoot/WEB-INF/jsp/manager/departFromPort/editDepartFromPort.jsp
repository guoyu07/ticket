<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/departFromPort.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑离港航班</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											航班号：
										</td>
										<td>
											<input id="flt" name="flt" class="my_input" datatype="*"
											       value="${departFromPort.flt}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航空公司两字码：
										</td>
										<td>
											<input id="acw" name="acw" class="my_input" datatype="*"
											       value="${departFromPort.acw}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航空公司两字码</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班实时状态：
										</td>
										<td>
											<input id="frs" name="frs" class="my_input" datatype="*"
											       value="${departFromPort.frs}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班实时状态</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											国内或国际：
										</td>
										<td>
											<input id="doi" name="doi" class="my_input" datatype="*"
											       value="${departFromPort.doi}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写国内或国际</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											计划出港时间：
										</td>
										<td>
											<input id="std" name="std" class="my_input" datatype="*"
											       value="${departFromPort.std}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写计划出港时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											目的地三字码：
										</td>
										<td>
											<input id="des" name="des" class="my_input" datatype="*"
											       value="${departFromPort.des}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写目的地三字码</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											计划到达时间：
										</td>
										<td>
											<input id="sta" name="sta" class="my_input" datatype="*"
											       value="${departFromPort.sta}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写计划到达时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											实际到达时间：
										</td>
										<td>
											<input id="tdt" name="tdt" class="my_input" datatype="*"
											       value="${departFromPort.tdt}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写实际到达时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											值机柜台：
										</td>
										<td>
											<input id="cid" name="cid" class="my_input" datatype="*"
											       value="${departFromPort.cid}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写值机柜台</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											登机口：
										</td>
										<td>
											<input id="gat" name="gat" class="my_input" datatype="*"
											       value="${departFromPort.gat}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写登机口</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											预计登机时间：
										</td>
										<td>
											<input id="got" name="got" class="my_input" datatype="*"
											       value="${departFromPort.got}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写预计登机时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											预计出港时间：
										</td>
										<td>
											<input id="etd" name="etd" class="my_input" datatype="*"
											       value="${departFromPort.etd}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写预计出港时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											实际出港时间：
										</td>
										<td>
											<input id="abt" name="abt" class="my_input" datatype="*"
											       value="${departFromPort.abt}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写实际出港时间</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>