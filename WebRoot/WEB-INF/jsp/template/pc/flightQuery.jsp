<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<script type="text/javascript" src="/template/pc/js/flightNotice.js"></script>
		<script src="/template/wap/layer/layer.js"></script>
		<title>航班查询 - 云南省昆明市长水机场</title>
		<ticket:common type="vpnBroken" var="sms"/>
		<s:if test="#request.sms != null">
			<script type="text/javascript">
				$(function(){
					layer.alert('${sms.value}', {
						  skin: 'layui-layer-lan' //样式类名
						  ,closeBtn: 0
						});
				});
			</script>
		</s:if>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_11"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="javascript:;">航班查询</a>
		</div place w980 mt30>
		<div class="w980 mt30">
			<div class="news_list bhh">
	        <div class="search_fly">
	    		<div class="button-group line tab_title">
	    		    <s:if test='flightFlag==null||flightFlag=="depart"'>
		    			<input type="hidden" id="flightFlag" value="depart" />
		    			<button class="button padding f18 selected selectFlightFlag" flightFlag="depart" style="width:50%">离港</button>
	                	<button class="button padding f18 selectFlightFlag" flightFlag="arrival" style="width:50%">进港</button>
		    		</s:if>
	    		    <s:else>
		    			<input type="hidden" id="flightFlag" value="arrival" />
		    			<button class="button padding f18 selectFlightFlag" flightFlag="depart" style="width:50%">离港</button>
	               		<button class="button padding f18 selected selectFlightFlag" flightFlag="arrival" style="width:50%">进港</button>
	    		    </s:else>
	                
	            </div>
	            <div class="main_box_no_padding">
	                <div class="clearfix"></div>
	                <div class="margin-large-top">
	                    <form action="" class="search_form">
	                        <div class="label clearfix"><input type="" name="" id="queryByFlight" value="${keyword }" class="input" placeholder="航班号/航空公司/机场代码"><button id="searchFlight" onclick="return false;"></button></div>
	                        <div class="label clearfix">
	                        <select name="queryByDate" class="input" style="height: 100%" id="queryByDate">
	                        	<option value="<ticket:common type="currentDate"/>" selected="selected"><ticket:common type="currentDate"/></option>
	                       		
	                        </select><i></i></div>
	                        <div class="label clearfix">
                        		<select name="" id="queryByTime" class="input" style="height: 100%">
                        			<option value="1">全天时段</option>
                        			<option value="00:00-00:59">00:00-00:59</option>
                        			<option value="01:00-01:59">01:00-01:59</option>
                        			<option value="02:00-02:59">02:00-02:59</option>
                        			<option value="03:00-03:59">03:00-03:59</option>
                        			<option value="04:00-04:59">04:00-04:59</option>
                        			<option value="05:00-05:59">05:00-05:59</option>
                        			<option value="06:00-06:59">06:00-06:59</option>
                        			<option value="07:00-07:59">07:00-07:59</option>
                        			<option value="08:00-08:59">08:00-08:59</option>
                        			<option value="09:00-09:59">09:00-09:59</option>
                        			<option value="10:00-10:59">10:00-10:59</option>
                        			<option value="11:00-11:59">11:00-11:59</option>
                        			<option value="12:00-12:59">12:00-12:59</option>
                        			<option value="13:00-13:59">13:00-13:59</option>
                        			<option value="14:00-14:59">14:00-14:59</option>
                        			<option value="15:00-15:59">15:00-15:59</option>
                        			<option value="16:00-16:59">16:00-16:59</option>
                        			<option value="17:00-17:59">17:00-17:59</option>
                        			<option value="18:00-18:59">18:00-18:59</option>
                        			<option value="19:00-19:59">19:00-19:59</option>
                        			<option value="20:00-20:59">20:00-20:59</option>
                        			<option value="21:00-21:59">21:00-21:59</option>
                        			<option value="22:00-22:59">22:00-22:59</option>
                        			<option value="23:00-23:59">23:00-23:59</option>
                        		</select>
	                        <i></i></div>
	                        <p>温馨提示：查询可单选查询</p>
	                    </form>
	                </div>
	            </div>
	
	            <div class="clearfix" id="flightQueryResult">
	                
	            </div>
	        </div>
            <div class="page_list">
                <ul>
                    <a href="?pn=0" ><li class="bhh">首页</li></a>
                    <a class="hover"><li class="bhh">1</li></a>
                    <a href="?pn=0"><li class="bhh">末页</li></a>
                </ul>
            </div page_list>
		</div JTZN_L bhh>
			<%@ include file="common/rightMenu.jsp" %>
		</div w980 mt30>
		<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
		<script type="text/javascript" src="/template/pc/js/flightQuery.js"></script>
	</body>
</html>
