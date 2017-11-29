<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/youZanGoodsInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理有赞商品</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      <a href="javascript:;" id="refrech">刷新</a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="290">编号</td>
								    <td width="80">商品ID</td>
								    <td width="80">商品名称</td>
								    <td width="80">商品别名</td>
								    <td width="80">商品详情url</td>
								    <td width="80">商品价格</td>
								    <td width="80">商品销量</td>
								    <!-- <td>上架状态</td> -->
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td>${st.count }</td>
										    <td>${c.num_iid }</td>
										    <td>${c.name }</td>
										    <td>${c.alias }</td>
										    <td>${c.detail_url }</td>
										    <td>${c.price }</td>
										    <td>${c.sold_num }</td>
										    <%-- <td>${c.listing }</td> --%>
										    <td>
										    	<span>
										    		<!-- <a href="#">绑定商家</a> -->
										    	</span>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="100">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					        </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>