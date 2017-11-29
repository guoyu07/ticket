<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/departFromPort.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right"  style="margin-left: 0;">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100" style="margin-left: 0;">
					<tr style="text-align: center">
				    	<td colspan="70">
							<form action="${actionName }_manage.action?versionFlag=site" method="get">
								航班日期：<input type="text" name="startTime" onclick="new WdatePicker();" value='${param.startTime }'/>&nbsp;&nbsp;
								航班号：<input type="text" name="flt" value="${param.flt }"/>
					  			<input type="submit" value="查询"/>
						  	</form>
						</td>
					</tr>
					  <tr class="my_table_top1" style="margin-left: 0;">
					    <td width="80">航班号</td>
					    <td width="80">航班日期</td>
					    <td width="80">航班重复次数</td>
					    <td width="80">计划出港时间</td>
					    <td width="80">计划到达时间</td>
					    <td width="80">实际出港时间</td>
					    <td width="80">预计出港时间</td>
					    <td width="80">起飞机场三字码</td>
					    <td width="80">落地机场三字码</td>
					    <td width="80">航班实时状态</td>
					    <td width="80">登机口</td>
					    <td width="80">行李转盘</td>
					    <td width="80">出港机位\到达机位</td>
					    <td width="80">登机状态</td>
					    <td width="80">飞机编号</td>
					    <td width="80">延误</td>
					    <td width="80">VIP</td>
					    <td width="80">航班性质（大类）</td>
					    <td width="80">航班性质（大类细分）</td>
					    <td width="80">航班分类</td>
					    <td width="80">航段操作类型</td>
					    <td width="80">起飞地三字码</td>
					    <td width="80">目的地三字码</td>
					    <td width="80">经停</td>
					    <td width="80">主航班号</td>
					    <td width="80">值机柜台</td>
					    <td width="80">值机柜台2</td>
					    <td width="80">登机门开启时间</td>
					    <td width="80">下站到达时间</td>
					    <td width="80">下站预计到港时间</td>
					    <td width="80">下站计划到港时间</td>
					    <td width="80">终到站计划时间</td>
					    <td width="80">航空公司两字码</td>
					    <td width="80">国内或国际</td>
					  </tr>
					  <s:if test="pageModule != null && pageModule.totalCount > 0">
					  	<s:iterator id="c" value="pageModule.pageList" status="st">
					  		<tr>
							    <td>${c.flt }</td>
							    <td>${c.flightDate }</td>
							    <td>${c.flightRepeat }</td>
							    <td><s:date name="#c.std" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.sta" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.abt" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.etd" format="yyyy-MM-dd HH:mm"/></td>
							    <td>${c.depCode }</td>
							    <td>${c.arrCode }</td>
							    <td>${c.frs }</td>
							    <td>${c.gat }</td>
							    <td>${c.baggage }</td>
							    <td>${c.stand }</td>
							    <td>${c.boardStaus }</td>
							    <td>${c.airnum }</td>
							    <td>${c.caacCode }</td>
							    <td>${c.vip }</td>
							    <td>${c.nat }</td>
							    <td>${c.fst }</td>
							    <td>${c.cla }</td>
							    <td>${c.otc }</td>
							    <td>${c.org }</td>
							    <td>${c.des }</td>
							    <td>${c.vi1 }</td>
							    <td>${c.mFlightNo }</td>
							    <td>${c.cid }</td>
							    <td>${c.CD2 }</td>
							    <td><s:date name="#c.got" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.aan" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.ean" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.stn" format="yyyy-MM-dd HH:mm"/></td>
							    <td><s:date name="#c.tdes" format="yyyy-MM-dd HH:mm"/></td>
							    <td>${c.acw }</td>
							    <td>${c.doi }</td>
							 </tr>
					  	</s:iterator>
					  </s:if>
					  <s:else>
					  	 <tr>
						    <td colspan="70">${noDataMessage }</td>
						  </tr>
					  </s:else>
					</table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>