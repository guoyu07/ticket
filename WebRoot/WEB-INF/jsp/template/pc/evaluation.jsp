<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>评价中心 - 云南省昆明市长水机场</title>
		<script src="/common/validForm/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="/common/jquery-form/jquery-form.js"></script>
		<script type="text/javascript" src="/common/FileUpload.js"></script>
		<script type="text/javascript" src="/template/pc/js/evaluation/evaluate.js"></script>
	</head>
	
	<body style="background-color:#FFF;">
		<%@ include file="common/top.jsp" %>
		<%@ include file="common/nav.jsp" %>
		
		<div class="place w980 mt30">当前位置: <a href="/airportPc.action">首页</a> > <a href="javascript:;">评论中心</a></div place w980 mt30>
		
		<div class="IWantToComment w980 mt30">
			<form action="pCEvaluation_evaluation.action" method="post" ajaxPost="true" id="pcForm">
				<a href="/pCEvaluation_manage.action" id="manageLink" style="display: none;"></a>
				<img src="/template/pc/images/IWantToComment.gif" />
				<select id="classes" name="classes" style="width:980px; height:40px; padding-left:20px; margin:50px 0 50px 0px; font-size:16px; border:1px solid #ebebeb; color:#52565c;">
					<s:iterator value="classess" var="item">
	                   <option value="${item.id }">${item.name }</option>
	              	</s:iterator>
				</select>
				<table cellspacing="0" cellpadding="0" style="margin-left:220px;">
					<tr valign="top" id="project">
					</tr>
				</table>
				<div style="width:1000px; height:50px; margin:40px 0px 40px 0px; font-size:12px;" id="target">
					<!-- 评分 -->
				</div>
				
				<textarea name="content" datatype="*"></textarea>
				<div class="Validform_checktip"></div>
			
				<ul style="width:900px;">
					<li class="bhh"><a href="javascript:;"><img class="icon-plus" src="/template/pc/images/jia2.gif" height="87" width="87"/></a></li>
					<li class="bhh" style="margin:0 30px 0 30px;"><a href="javascript:;"><img class="icon-plus" src="/template/pc/images/jia2.gif" height="87" width="87" /></a></li>
					<li><a href="javascript:;"><img class="icon-plus" src="/template/pc/images/jia2.gif" height="87" width="87" /></a></li>
				</ul>
				
				<p><input type="image" src="/template/pc/images/ENTER.gif" style="height: 61px; width: 242px;"/></p>
				<!-- <p><img src="/template/pc/images/MessageContent.gif" /></p>
				
				<dl id="tb13_" class="bhh">
					<dd id="tb13_1" class="hovertab_13" onclick="x:HoverLi13(1);"><small>[ 来自：昆明市五华区 ]</small>1爱帮手的工作真的很棒</dd>
					<dt class="dis" id="tbc13_01">1干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。</dt>
				</dl tb13_>
				<dl id="tb13_" class="bhh">
					<dd id="tb13_2" class="normaltab_13" onclick="x:HoverLi13(2);"><small>[ 来自：昆明市五华区 ]</small>2爱帮手的工作真的很棒</dd>
					<dt class="undis" id="tbc13_02">2干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。</dt>
				</dl tb13_>
				<dl id="tb13_" class="bhh">
					<dd id="tb13_3" class="normaltab_13" onclick="x:HoverLi13(3);"><small>[ 来自：昆明市五华区 ]</small>3爱帮手的工作真的很棒</dd>
					<dt class="undis" id="tbc13_03">3干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。干的很认真，足足做了四个小时。厨房的角落和屋里有没擦到的地方。再注意卫生习惯就好了。浴室玻璃擦的很干净。</dt>
				</dl tb13_> -->
			</form>
		</div IWantToComment w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
		
		<script type="text/javascript">
			function rat(star,result,m){
			
				star= '#' + star;
				result= '#' + result;
				$(result).hide();//将结果DIV隐藏
			
				$(star).raty({
					hints: ['1','2', '3', '4', '5'],
					path: "/template/pc/images",
					starOff: 'star-off-big.gif',
					starOn: 'star-on-big.gif',
					size: 28,
					score: 5,
					start: 100,
					showHalf: true,
					target: result,
					targetKeep : true,//targetKeep 属性设置为true，用户的选择值才会被保持在目标DIV中，否则只是鼠标悬停时有值，而鼠标离开后这个值就会消失
					click: function (score, evt) {
						//第一种方式：直接取值
						/* alert('你的评分是'+score*m+'分'); */
					}
				});
			}
		</script>
	</body>
</html>
