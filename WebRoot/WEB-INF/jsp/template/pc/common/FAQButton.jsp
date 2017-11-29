<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="FAQ_search_button">
	<a href="/pcOrder.action"><dl>
			便捷登机
		</dl>
	</a>
	<ticket:systemCommon type="getNewsClassObj" value="jichangfuwu" />
	<a href="/airport/newsList/${getNewsClassObj.id}.html"><dl>
			机场服务
		</dl>
	</a>
	<a href="/airportPc_trafficGuide.action"><dl>
			交通查询
		</dl>
	</a>
</div FAQ_search_button>
