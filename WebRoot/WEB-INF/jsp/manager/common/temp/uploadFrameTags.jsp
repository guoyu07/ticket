<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 颜色选择器 -->
<script type="text/javascript" charset="UTF-8" src="/common/iColorPicker/iColorPicker.js"></script>

<!-- 剪切组件 -->
<link href="/common/jcrop/jquery-ui-1.8.12.custom.css" rel="Stylesheet" type="text/css" />
<script type="text/javascript" src="/common/jcrop/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript" src="/common/jcrop/jquery.cropzoom.js"></script>
<script type="text/javascript" src="/common/jcrop/custom.js"></script>

<!-- 上传组件 -->
<link href="/common/swfUpload/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/swfUpload/swfupload.js"></script>
<script type="text/javascript" src="/common/swfUpload/swfupload.queue.js"></script>
<script type="text/javascript" src="/common/swfUpload/fileprogress.js"></script>
<script type="text/javascript" src="/common/swfUpload/handlers.js"></script>
<script type="text/javascript" src="/common/swfUpload/swfuploadCustom.js"></script>

<!-- 弹出框 -->
<link href="/common/swfUpload/css/frame.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/swfUpload/frame.js"></script>


<!-- 上传框 -->
<div id="commonUploadFrame" style="display:none">
 		<div class="title"><span class="commomUploadBtn_cancel">关闭</span></div>
		<div class="nav nav1 hovered">单个上传</div><div class="nav nav2">批量上传</div><div class="nav nav3">服务器浏览</div>
		
		<div class="container container1">
			<div>
		    	<div class="fieldset flash" id="swfuploadProgress1">
					<span class="legend">单个上传</span>
				</div>
				<span id="swfuploadBrowser1"></span>
				<input type="button" id="swfuploadBtn1" value="上传"/>
		     </div>
		</div>
		<div class="container container2" style="display:none">
			<div>
		    	<div class="fieldset flash" id="swfuploadProgress">
					<span class="legend">多个上传</span>
				</div>
				<span id="swfuploadBrowser"></span>
				<input type="button" id="swfuploadBtn" value="上传"/>
		     </div>
		</div>
		<div class="container container3" style="display:none">
			<!-- 通过ajax获取服务器端的文件 -->
		</div>
		
		<div class="bottom"><input type="button" class="commomUploadBtn_ok" value="保存" /><input type="button" class="commomUploadBtn_cancel" value="取消" /></div>
		<input type="hidden" id="commonUploadId" />
</div>

<!-- 剪切图片的弹出框 -->
<div id="cropFrame" style="display:none">
	<div class="title"><span class="cropBtn_cancel">关闭</span></div>
	<div id="cropContainer" class="cropContainer">
	
	</div>
</div>

<!-- 打水印的弹出框 -->
<div id="waterMarkFrame" style="display:none">
	<div class="title"><span class="waterMarkBtn_cancel" title="返回上传编辑器">关闭</span></div>
	<div class="waterMarkContainer">
		<input type="radio" name="waterMarkType" value="0" checked="checked" class="pressTextListener" />文字
		<input type="radio" name="waterMarkType" value="1" class="pressImageListener" />图片
		<div class="pressTextContainer">
			填写要打印的文字：<input id="fontText" type="text" name="pressText" />
			<br/>
			选择文字的颜色：
				<input readonly="readonly" id="fontColor" style="width:50px;"  name="titleColor" type="text" value="" class="iColorPicker" />
			<br/>
			选择文字的大小：
			           <select name="fontSize" id="fontSize">
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="14">14</option>
							<option value="16">16</option>
							<option value="18">18</option>
							<option value="20" selected="selected">20</option>
							<option value="22">22</option>
							<option value="26">26</option>
							<option value="28">28</option>
							<option value="36">36</option>
							<option value="48">48</option>
							<option value="56">56</option>
							<option value="72">72</option>
			           </select>
			   <br/>
			   透明度：
			     <select class="alphaValue" name="alpha">
			     	<option value="0.1">0.1</option>
			     	<option value="0.15">0.15</option>
			     	<option value="0.2">0.2</option>
			     	<option value="0.25">0.25</option>
			     	<option value="0.3">0.3</option>
			     	<option value="0.35">0.35</option>
			     	<option value="0.4">0.4</option>
			     	<option value="0.45">0.45</option>
			     	<option value="0.5">0.5</option>
			     	<option value="0.55">0.55</option>
			     	<option value="0.6">0.6</option>
			     	<option value="0.65">0.65</option>
			     	<option value="0.7">0.7</option>
			     	<option value="0.75">0.75</option>
			     	<option value="0.8" selected="selected">0.8</option>
			     	<option value="0.85">0.85</option>
			     	<option value="0.9">0.9</option>
			     	<option value="0.95">0.95</option>
			     	<option value="1">1</option>
			     </select>
		</div>
		<div class="pressImageContainer" style="display:none">
			<div>
		    	<div class="fieldset flash" id="swfuploadProgress2">
					<span class="legend">上传要打印的图片</span>
				</div>
				<span id="swfuploadBrowser2"></span>
				<input type="button" id="swfuploadBtn2" value="上传"/>
				<br/>
				透明度：
					<select class="alphaValue" name="alpha">
				     	<option value="0.1">0.1</option>
				     	<option value="0.15">0.15</option>
				     	<option value="0.2">0.2</option>
				     	<option value="0.25">0.25</option>
				     	<option value="0.3">0.3</option>
				     	<option value="0.35">0.35</option>
				     	<option value="0.4">0.4</option>
				     	<option value="0.45">0.45</option>
				     	<option value="0.5">0.5</option>
				     	<option value="0.55">0.55</option>
				     	<option value="0.6">0.6</option>
				     	<option value="0.65">0.65</option>
				     	<option value="0.7">0.7</option>
				     	<option value="0.75">0.75</option>
				     	<option value="0.8" selected="selected">0.8</option>
				     	<option value="0.85">0.85</option>
				     	<option value="0.9">0.9</option>
				     	<option value="0.95">0.95</option>
				     	<option value="1">1</option>
				     </select>
		     </div>
		</div>
		<div class="targetImageContainer">
			<div class="scrollTargetImageContainer">
				<img width="300"  />
			</div>
			
			请在图片上点击要加水印的地方<br/>
			当前获取的坐标为<input class="currentPosition" />
		</div>
	</div>
	<div class="bottom"><input type="button" class="pressWaterMarkBtn_ok" value="打印" /><input type="button" class="waterMarkBtn_cancel" value="返回" /></div>
</div>
<!-- 图片编辑的弹出框 -->
<div id="editAnnexFrame" style="display:none">
	<div class="title"><span class="hideAnnexListener">关闭</span></div>
	<div id="editAnnexContainer" class="editAnnexContainer">
		标题：<input name="annexTitle" />
		<br/>
		描述： <textarea rows="4" cols="40" name="annexContent"></textarea>
	</div>
	<div class="bottom"><input type="button" class="editAnnexOkListener" value="确定" /><input type="button" class="hideAnnexListener" value="返回" /></div>
</div>
