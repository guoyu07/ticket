

$(function (){
	newsViewVideoPlayer();
});

/**
 * 文章详细页面创建视频播放器
 * @param videoPath
 * @param pictureUrl
 * @return
 */
function newsViewVideoPlayer(){
	var url = $("#newsViewVideoPlayer").attr("videoUrl");
	var pictureUrl = $("#newsViewVideoPlayer").attr("pictureUrl");
	$("#newsViewVideoPlayer").html(createVideoHtml(url,pictureUrl));
}

/**
 * 文章详细页面创建视频播放器
 * @param videoPath
 * @param pictureUrl
 * @return
 */
function createVideoHtml(videoPath,pictureUrl) {
	var videoHtml = '<div id="CuPlayer" align="center">\r\n<script type="text/javascript">\r\n';
		videoHtml+= 'var so = new SWFObject("/template/pc/flash/CuPlayerMiniV20_Black_S.swf", "CuPlayer", "690", "458", "9", "#000000");\r\n';
		videoHtml+= 'so.addParam("allowfullscreen", "true");\r\n';
		videoHtml+= 'so.addParam("allowscriptaccess", "always");\r\n';
		videoHtml+= 'so.addParam("wmode", "opaque");\r\n';
		videoHtml+= 'so.addParam("quality", "high");\r\n';
		videoHtml+= 'so.addParam("salign", "lt");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerFile", "'+videoPath+'");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerImage","'+pictureUrl+'");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerShowImage", "true");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerWidth", "690");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerHeight", "458");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerAutoPlay", "false");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerAutoRepeat", "false");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerShowControl", "true");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerAutoHideControl", "false");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerAutoHideTime", "6");\r\n';
		videoHtml+= 'so.addVariable("CuPlayerVolume", "80");\r\n';
		videoHtml+= 'so.write("CuPlayer");\r\n';
		videoHtml+= '</script></div>\r\n';
	return videoHtml;
}