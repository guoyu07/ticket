<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	
	<script type="text/javascript">

	function Transformation(){
		var from = $('#from option:selected').val();
		var to  = $('#to option:selected').val();
		var amount = $("#amount").val();


		if(!isNaN(amount)){
			if(amount<1){
					alert("请输入大于0的数字!");
					return;
			}
		}else{
			alert("请输入数字!");
			return;
		}
		
	    amount= encodeURIComponent(amount);
		var url = "../baiduExchangeRate_write.action?from="+from+"&to="+to+"&amount="+amount;

		$.ajax({
		     type: 'get',
		     url: url ,
		     dataType: "JSON",
		     success: function(data){
			   var rs =  data.retData.convertedamount;
			   $("#result").html(rs);
		     } ,
			 error:function(){
				alert("错误");
		     }
		});
	}

	function Exchange(){
		var from = $('#from option:selected').val();
		var to  = $('#to option:selected').val();

		$('#from').val(to);
		$('#to').val(from);
	}
	
	</script>
	
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div style='margin: 40px 40px; font-size: 22px;'>
						<select id="from" class="input input-auto" style='width: 41%; height: 40px;'>
							<option selected value="CNY">
								人民币
							</option>
							<option value="USD">
								美元
							</option>
							<option value="EUR">
								欧元
							</option>
							<option value="TWD">
								台币
							</option>
							<option value="HKD">
								港币
							</option>
							<option value="KRW">
								韩元
							</option>
							<option value="GBP">
								英镑
							</option>
							<option value="JPY">
								日元
							</option>
							<option value="THB">
								泰铢
							</option>
							<option value="RUB">
								卢布
							</option>
						</select>
						<i onclick="Exchange()" class='icon-exchange margin-large-left margin-large-right'></i>
						<select id="to" class="input input-auto" style='width: 41%; height: 40px;'>
							<option  value="CNY">
								人民币
							</option>
							<option selected value="USD">
								美元
							</option>
							<option value="EUR">
								欧元
							</option>
							<option value="TWD">
								台币
							</option>
							<option value="HKD">
								港币
							</option>
							<option value="KRW">
								韩元
							</option>
							<option value="GBP">
								英镑
							</option>
							<option value="JPY">
								日元
							</option>
							<option value="THB">
								泰铢
							</option>
							<option value="RUB">
								卢布
							</option>
						</select>
						请输入金额12:<input id="amount"  name="amount" class="input" />
						<button  onclick="Transformation()" type="button" class="button bg-sub input margin-big-top"
							style="height: 60px; font-size: 24px;">
							兑换
						</button>
						<div class="fy_ls">
						兑换结果:
						<h4 id="result">
							仅供参考，兑换汇率以实际情况为准。
						</h4>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		
	</body>
</html>