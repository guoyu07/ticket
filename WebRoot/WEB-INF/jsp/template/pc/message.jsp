<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<title>消息通知 - 云南省昆明市长水机场</title>
<script type="text/javascript">
	function g(o){return document.getElementById(o);} 
				function HoverLi5(n){ 
					var isize = $("#isize").val();
					for(var i=1;i<=isize;i++){
						g('tb5_'+i).className='normaltab_5';
						g('tbc5_0'+i).className='undis';
					}
					g('tbc5_0'+n).className='dis';
					g('tb5_'+n).className='hovertab_5';
				}
</script>
</head>

<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<%@ include file="common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="/airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="#"><img
					src="/template/pc/images/ico5_2.gif" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" /> </a>
			</li>
		</ul>
	</div menu1>

	<div class="message w980 mt30">
		<ul>
			<ticket:common type="messageReminderList" />
			<s:if test="#request.messageReminderList != null">
				<s:iterator id="messageReminder" status="st"
					value="#request.messageReminderList">
					<s:set var="size" value="#request.messageReminderList.size()"></s:set>
					<input value="${size }" id="isize" type="hidden" />
					<li><small><s:date
								name="#messageReminder.reminderTime" format="yy-MM-dd HH:mm" />
					</small> <ticket:commonAnnex type="annex"
							entityUuid="${messageReminder.id}" annexType="1" size="1" /> <s:if
							test="#st.first">
							<h2 onclick="x:HoverLi5(${st.count});" id="tb5_${st.count }"
								class="hovertab_5">
								<a href="javascript:void(0)">${messageReminder.title }</a>
							</h2>
						</s:if> <s:else>
							<h2 onclick="x:HoverLi5(${st.count});" id="tb5_${st.count }"
								class="normaltab_5">
								<a href="javascript:void(0)">${messageReminder.title }</a>
							</h2>
						</s:else></li>
					<s:if test="#st.first">
						<div class="dis" id="tbc5_0${st.count }">
							<ticket:common type="messageReminderObj"
								value="${messageReminder.id }" />
							<ticket:commonAnnex type="annex"
								entityUuid="${messageReminderObj.id}" annexType="1" size="1" />
							<s:if test="#request.annex != null">
								<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="120" height="120">
							</s:if>
							${messageReminderObj.content }
						</div>
					</s:if>
					<s:else>
						<div class="undis" id="tbc5_0${st.count }">
							<ticket:common type="messageReminderObj"
								value="${messageReminder.id }" />
							<ticket:commonAnnex type="annex"
								entityUuid="${messageReminderObj.id}" annexType="1" size="1" />
							<s:if test="#request.annex != null">
								<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="120" height="120">
							</s:if>
							${messageReminderObj.content }
						</div>
					</s:else>
				</s:iterator>
			</s:if>
		</ul>
	</div message w980 mt30>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
