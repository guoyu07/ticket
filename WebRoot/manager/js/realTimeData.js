$(function(){
//	var day = new Date();
//	var yester = new Date();
//	yester.setDate(yester.getDate()+1);
//	var tomo = new Date();
//	tomo.setDate(tomo.getDate()+2);
//	var yesterday = format(yester,"yyyy年MM月dd日");
//	var today = format(day,"yyyy年MM月dd日");
//	var tomorrow = format(tomo,"yyyy年MM月dd日");
//	$("#shishi").find("tr").eq(1).find("td").eq(0).text(today);
//	$("#shishi2").find("tr").eq(0).find("td").eq(0).text(yesterday);
//	$("#shishi3").find("tr").eq(0).find("td").eq(0).text(tomorrow);
	//alert(yesterday);
	//alert(today);
	//alert(tomorrow);
});

function format(date,format) {
	try {
		var year = date.getFullYear();
		var month = date.getMonth();
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();

		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minute < 10) {
			minute = "0" + minute;
		}
		if (second < 10) {
			second = "0" + second;
		}
		month = month + 1;	
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}

		if (JM.isNull(format)) {
			return year + "-" + month + "-" + day;
		} else if('yyyy年MM月dd日' == format) {
			var temp = year + "年" + month + "月" + day + "日";
			var d = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
			var dd = d[date.getDay()];
			return temp + " " + dd;
		} else {
			if ("yyyy-MM-dd" == format) {
				return year + "-" + month + "-" + day;
			} else if ("yyyy年MM月dd日" == format) {
				return year + "年" + month + "月" + day + "日";
			} else if ("yyyy-MM-dd hh:mm:ss" == format) {
				return year + "-" + month + "-" + day + " " + hour + ":"
						+ minute + ":" + second;
			} else if ("yyyy年MM月dd日 hh时mm分ss秒" == format) {
				return year + "年" + month + "月" + day + "日  " + hour
						+ "时" + minute + "分" + second + "秒";
			}
		}
	} catch (e) {
		return "";
	}
}