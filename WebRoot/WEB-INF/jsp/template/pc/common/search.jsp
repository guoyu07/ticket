<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="kv w980">
	<div class="search">
		<div class="title">
			在线搜索
			<span>SEARCH</span>
		</div>
		<form action="/airportPc_commonSearch.action" method="post">
			<input type="text" name="keyword" value="${keyword}" placeholder='输入您需要的查找的信息'>
			<button type="submit"></button>
		</form>
		<div class="quick_menu">
			<div class="tit">
				<a href="#" style="display: none;"><img src="/template/pc/images/more.gif" class="hh" />
				</a>快捷菜单
			</div>
			<ul>
				<li class="bhh">
					<a href="/airportPc_payAttentionToUs.action" class="q1"></a>
				</li>
				<li class="bhh">
					<a href="/pcOrder.action" class="q2"></a>
				</li>
				<ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
				<li class="bhh">
					<a href="/airport/newsList/${getNewsClassObj.id}.html" class="q3"></a>
				</li>
				<li class="bhh">
					<a href="/airportPc_flightDelayNotice.action" class="q4"></a>
				</li>
			</ul>
		</div quick_menu>
	</div search>
</div kv w980>