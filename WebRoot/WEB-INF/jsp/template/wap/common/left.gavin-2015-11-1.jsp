<%@ page language="java" pageEncoding="UTF-8"%>
<div class="side_menu">
    <div class="side_menu_con">
        <a href="javascript:;" class="close_btn"></a>
        <ul>
            <s:if test="tipMessage != 'indexPage'"><li><h6><a href="/airport.action">首页</a></h6></li></s:if>
            <li><h6><a href="/airport/chufa.ticket">出发</a></h6></li>
            <li><h6><a href="/airport/daoda.ticket">到达</a></h6></li>
            <li><h6><a href="/airport/zhongzhuan.ticket">中转</a></h6></li>
            <li>
				<ticket:common type="newsClassChilds" value="chengjixuzhi"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<s:if test="#request.newsClassChilds != null">
						<s:iterator id="nc" value="#request.newsClassChilds">
							<dd>
								<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
							</dd>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li><h6><a href="#">航班查询</a></h6></li>
            <li><h6><a href="#">网上值机</a></h6></li>
            <li><h6><a href="#">航班动态</a></h6></li>
            <li><h6><a href="#">票务指南</a><i class='icon-sort-asc'></i></h6>
                <dl>
                    <dd><a href="#">在线购票</a></dd>
                    <dd><a href="#">机票验真</a></dd>
                </dl>
            </li>
            <li><h6><a href="#">交通指南</a></h6></li>
            <li>
				<ticket:common type="newsClassChilds" value="xinwengonggao"/>
				<h6>
					<a href="/airport/xinwengonggao.ticket" >${newsClassObj.name }</a>
				</h6>
			</li>
			<li>
				<ticket:common type="newsClassChilds" value="bianjiedengji"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<s:if test="#request.newsClassChilds != null">
						<s:iterator id="nc" value="#request.newsClassChilds">
							<dd>
								<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
							</dd>
						</s:iterator>
					</s:if>
					<dd><a href="#">电瓶车服务</a></dd>
                    <dd><a href="#">便捷登机服务</a></dd>
				</dl>
			</li>
            <li>
				<ticket:common type="newsClassChilds" value="xinglifuwu"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="newsObj" value="#request.newsList">
							<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
								<dd>
									<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
								</dd>
							</s:if>
							<s:else>
								<dd>
									<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
								</dd>
							</s:else>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li><h6><a href="#">火车票代售</a></h6></li>
            <li><h6><a href="#">旅游咨询服务</a></h6></li>
            <li>
				<ticket:common type="newsClassChilds" value="bianminfuwu"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="newsObj" value="#request.newsList">
							<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
								<dd>
									<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
								</dd>
							</s:if>
							<s:else>
								<dd>
									<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
								</dd>
							</s:else>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li>
				<ticket:common type="newsClassChilds" value="gonggongsheshi"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="newsObj" value="#request.newsList">
							<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
								<dd>
									<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
								</dd>
							</s:if>
							<s:else>
								<dd>
									<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
								</dd>
							</s:else>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li>
				<ticket:common type="newsClassChilds" value="aixinfuwu"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="newsObj" value="#request.newsList">
							<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
								<dd>
									<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
								</dd>
							</s:if>
							<s:else>
								<dd>
									<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
								</dd>
							</s:else>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li>
				<ticket:common type="newsClassChilds" value="shanglvfuwu"/>
				<h6>
					<a href="#">${newsClassObj.name }</a><i class='icon-sort-asc'></i>
				</h6>
				<dl>
					<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="newsObj" value="#request.newsList">
							<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
								<dd>
									<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
								</dd>
							</s:if>
							<s:else>
								<dd>
									<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
								</dd>
							</s:else>
						</s:iterator>
					</s:if>
				</dl>
			</li>
            <li><h6><a href="#">航班延误</a><i class='icon-sort-asc'></i></h6>
                <dl>
                    <dd><a href="#">法律法规</a></dd>
                    <dd><a href="#">延误公告</a></dd>
                    <dd><a href="#">机票改签</a></dd>
                    <dd><a href="#">行李服务</a></dd>
                    <dd><a href="#">餐饮零售</a></dd>
                    <dd><a href="#">交通查询</a></dd>
                    <dd><a href="#">酒店住宿</a></dd>
                </dl>
            </li>
            <li><h6><a href="#">餐饮零售</a></h6></li>
            <li><h6><a href="#">酒店住宿</a></h6></li>
            <li>
				<ticket:common type="newsClassChilds" value="jichangwenhua"/>
				<h6>
					<a href="/airport/jichangwenhua.ticket" >${newsClassObj.name }</a>
				</h6>
			</li>
			<li>
				<ticket:common type="newsClassChilds" value="mianzeshengming"/>
				<h6>
					<a href="/airport/mianzeshengming.ticket" >${newsClassObj.name }</a>
				</h6>
			</li>
			<li>
				<ticket:common type="newsClassChilds" value="guanzhuwomen"/>
				<h6>
					<a href="/airport/guanzhuwomen.ticket" >${newsClassObj.name }</a>
				</h6>
			</li>
        </ul>
    </div>
</div>