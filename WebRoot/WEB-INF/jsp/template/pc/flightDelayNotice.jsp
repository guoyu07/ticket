<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>延误公告 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>

		<div class="banner_17"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">延误公告</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="other_article other_L bhh">
				<h2>
					延误公告
				</h2>
				<!-- <h3>
					航班延误常识解读
					<br />
					（根据中国民航局消费者事务中心相关资料编辑整理）
				</h3> -->
				<h4>
					<ticket:common type="flightDelayNoticeObj"/>
							${flightDelayNoticeObj.description }
				</h4>
				
				<div class="FAQ_search_button">
					<!-- <a href="/airportPc_flightCompanyInfo.action"><dl>-->
					<a href="/airport/newsView/1465267859654.html"><dl>
							机票改签
						</dl>
					</a>
					<a href="/airport/newsView/1465267905626.html"><dl>
							行李服务
						</dl>
					</a>
					<a href="/airportPc_businessDisplay.action"><dl>
							酒店住宿
						</dl>
					</a>
				</div FAQ_search_button>
				<div class="FAQ_search_button">
					<a href="/airportPc_businessDisplay.action"><dl>
							餐饮零售
						</dl>
					</a>
					<a href="/airportPc_trafficGuide.action"><dl>
							交通查询
						</dl>
					</a>
					<a href="/airportPc_serviceHotLine.action"><dl>
							服务热线
						</dl>
					</a>
				</div FAQ_search_button>
			</div other_article other_L bhh>
			<%@ include file="common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
			<%@ include file="common/bottom.jsp" %>
	</body>
</html>
