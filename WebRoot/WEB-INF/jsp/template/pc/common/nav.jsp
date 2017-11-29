<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="nav">
	<ul class="w980">
		<li class="bhh">
			<a href="/airportPc.action">
				<p>
					长水首页
				</p>
				<b>Home</b>
			</a>
		</li>
		<li class="bhh">
			<a href="/airportPc_flightQuery.action">
				<p>
					航班信息
				</p>
				<b>Flight</b>
			</a>
		</li>
		<ticket:systemCommon type="getNewsClassObj" value="changshuidongtai" />
		<li class="bhh">
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
				<p>
					长水动态
				</p>
				<b>News</b>
			</a>
		</li>
		<li class="bhh">
			<a href="/airportPc_trafficGuide.action">
				<p>
					交通指南
				</p>
				<b>Traffic guide</b>
			</a>
		</li>
		<li class="bhh">
			<a href="#">
				<p>
					票务指南
				</p>
				<b>Air tickets</b>
			</a>
		</li>
		<li class="bhh">
			<a href="/airportPc_trafficGuideMenu.action">
			<input type="hidden" value="/airportPc_passengerService.action?alias=xinglichaxun&&alias2=xinglituoyun"/>
				<p>
					旅客服务
				</p>
				<b>Service</b>
			</a>
		</li>
		<li class="bhh">
			<a href="/airportPc_businessDisplay.action">
				<p>
					机场商业
				</p>
				<b>Business</b>
			</a>
		</li>
	</ul>
</div>
