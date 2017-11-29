<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/employeeCRM.js"></script>
	<body class="mobile">
	<div class="mobile-view">
		<div class="mobile-page">
		<s:if test="#session.fromApp == null && #parameters.fromApp == null">
			<div class="mobile-top">
				<div class="header"> 
					登录页面 
				</div>
			</div>
			</s:if>
			<s:else>
			<div class="mobile-top" style="display: none;">
				<div class="header"> 
					登录页面 
				</div>
			</div>
			</s:else>
			<div class="mobile-main">

				<dl class="">
					<s:if test="#session.supperAdministrators != null">
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yy">
						预约情况
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yanzheng">
						验证操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="paidan">
						派单操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="hexiao">
						核销操作
					</dd>
					</s:if>
					<s:if test="#session.manager != null">
						<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yy">
						预约情况
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yanzheng">
						验证操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="paidan">
						派单操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="hexiao">
						核销操作
					</dd>
					</s:if>
					<s:if test="#session.administrators != null">
						<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yy">
						预约情况
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yanzheng">
						验证操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="paidan">
						派单操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="hexiao">
						核销操作
					</dd>
					</s:if>
					<s:if test="#session.verification != null">
						<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yy">
						预约情况
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yanzheng">
						验证操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="paidan">
						派单操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="hexiao">
						核销操作
					</dd>
					</s:if>
					<s:if test="#session.dispatch != null">
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yy">
						预约情况
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="yanzheng">
						验证操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="paidan">
						派单操作
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="hexiao">
						核销操作
					</dd>
					</s:if>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="personInfo">
						个人中心
					</dd>
					<dd class='mr40 fz30 text-center c_text clearfix'
						style='line-height: 50px' id="loginout">
						注销
					</dd>
				</dl>
			</div>
		</div>
	</div>
	<div class="dialog" style="display:none;" id="dialog">
			<div class="check_dialog box_dialog">
				<div class="c_content b_white">
					<div class="tit_tab">
						<a href="/wapValidation_addElectromobile.action">电瓶车验证</a>
						<a href="/wapValidation_add.action">便捷登机验证</a>
					</div>
				</div>
			</div>
	</div>
	<div class="dialog" style="display:none;">
			<div class="check_dialog1 box_dialog">
				<div class="c_content b_white">
					<div class="tit_tab">
						<a href="/wapBjdjDispatch_manageForElectromobile.action">电瓶车派单</a>
						<a href="/wapBjdjDispatch_manage.action">便捷登机派单</a>
					</div>
				</div>
			</div>
	</div>
	<div class="dialog" style="display:none;">
			<div class="check_dialog2 box_dialog">
				<div class="c_content b_white">
					<div class="tit_tab">
						<a href="/wapBjdjDispatchList_manageElectromobile.action">电瓶车核销</a>
						<a href="/wapBjdjDispatchList_manage.action">便捷登机核销</a>
					</div>
				</div>
			</div>
	</div>
	<script type="text/javascript">
		    $(function(){
		        $('#yanzheng').on('tap',function(){
		            $.do_dialog.open({'msg':$('.check_dialog')});
		        });
		        $('#paidan').on('tap',function(){
		            $.do_dialog.open({'msg':$('.check_dialog1')});
		        });
		        $('#hexiao').on('tap',function(){
		            $.do_dialog.open({'msg':$('.check_dialog2')});
		        });
		    });
		</script>
	</body>
</html>