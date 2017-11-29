/**
 * @description JM可以进行常用的JS验证. 参数封装. 事件绑定.等等操作.<b> 
 * @author HiSay
 * @datetime 2014-01-01
 */
//=========================================================================

var JM = function() {};

//defined some usually variables
JM.prototype.randomNumber = new Date().getTime(); // 随机数
JM.prototype.version = "JM2.0"; // 当前JM的版本
JM.prototype.todayDate = ''; //今天的日期字符串
JM.prototype.SUCCESS = 'success'; //成功字符串 标记用
JM.prototype.FAILTURE = 'failture'; //失败字符串 标记用
JM.prototype.YES = 'y'; //成功字符串 标记用
JM.prototype.NO = 'n'; //失败字符串 标记用


/** 自定义空指针异常 * */
var NullPointerException = function(message) {
	alert(message);
};

/** 自定义一个Map对象 模拟JAVA的实现 * */
function Map() {
	function struct(key, value) {
		this.key = key;
		this.value = value;
	}
	this.put = function(key, value) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	}
	this.get = function(key) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	}
	this.remove = function(key) {
		var v;
		for (var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	}
	this.size = function() {
		return this.arr.length;
	}
	this.isEmpty = function() {
		return this.arr.length <= 0;
	}
	this.containsKey = function(key) {
		for (var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return true;
			}
		}
		return false;
	}
	this.values = function() {
		var valueArr = new Array();
		for (var i = 0; i < this.arr.length; i++) {
			valueArr.push(this.arr[i].value);
		}
		return valueArr;
	}
	this.clear = function() {
		this.arr = [];
	}
	this.arr = new Array();
}

/** ***********************************基础类*************************************** */

/**
 * 根据指定的格式获取当前的日期字符串
 * 
 * @param value
 *            指定的日期格式
 * @result 返回指定的日期格式, 如果没有指定,则返回默认的日期格式
 */
JM.prototype.getDateString = function(format) {
	try {
		var date = new Date();
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

		if (this.isNull(format)) {
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

/**
 * 去除指定字符串前后的空格
 * 
 * @param value
 *            要去除空格的字符串
 * @result 返回去除空格后的字符串
 */
JM.prototype.trim = function(value) {
	try {
		var re = /\s/g;
		value = value.replace(re, "");
		return value;
	} catch (e) {
		return value;
	}
}

/**
 * 返回当前浏览器的类型
 */
JM.prototype.browserVersion = function() {
	try {
		if (jQuery.browser.msie) {
			return "IE";
		} else if (jQuery.browser.mozilla) {
			return "Mozilla FireFox";
		} else if (jQuery.browser.safari) {
			return "Safari";
		} else if (jQuery.browser.opera) {
			return "Opera";
		} else {
			return "unknow";
		}
	} catch (e) {
		return "unknow";
	}
}

/**
 * 获取指定字符串的长度
 * 
 * @param value
 *            指定的字符串
 * @param ignoreWhiteSpace
 *            是否忽略字符串前后的空格 ture为忽略 false不忽略
 * @result 返回指定的字符串的长度
 */
JM.prototype.getLength = function(value, ignoreWhiteSpace) {
	try {
		var sum = 0;
/** 返回的字符串长度 */

		if (ignoreWhiteSpace) {
			value = this.trim(value);
		}
		for (var i = 0; i < value.length; i++) {
			if ((value.charCodeAt(i) >= 0) && (value.charCodeAt(i) <= 255)) {
				sum = sum + 1;
			} else {
				sum = sum + 2;
			}
		}
		return sum;
	} catch (e) {
		return 0;
	}
}

/**
 * 根据指定的url打开一个页面
 * 
 * @param url
 *            要打开的页面地址
 */
JM.prototype.goPage = function(url) {
	try {
		if (!this.isNull(url)) {
			window.location.href = url;
		}
	} catch (e) {
	}
}

/** ***********************************验证类*************************************** */

/**
 * 验证指定的字符串是否为email格式的
 * 
 * @param email
 *            要验证的字符串
 * @result 如果验证合法则返回true, 否则返回false
 */
JM.prototype.isEmail = function(email) {
	try {
		var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}jQuery/;
		if (myReg.test(email)) {
			return true;
		} else {
			return false;
		}
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为空或者全部都是空格,null,undefined
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是空格则返回true, 否则返回false
 */
JM.prototype.isNull = function(value) {
	try {
		if (value == "" || value == null || value == undefined) {
			return true;
		}
		return new RegExp("^[ ]+jQuery").test(value);
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为为合法的IP地址
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是IP则返回true, 否则返回false
 */
JM.prototype.isIP = function(value) {
	try {
		if (this.isNull(value)) {
			return false;
		}
		var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)jQuery/g
		if (re.test(value)) {
			if (RegExp.jQuery1 < 256 && RegExp.jQuery2 < 256 && RegExp.jQuery3 < 256
					&& RegExp.jQuery4 < 256) {
				return true;
			}
		}
		return false;
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为int类型
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是整数类型则返回true, 否则返回false
 */
JM.prototype.isInteger = function(value) {
	try {
		var regu = /^[-]{0,1}[0-9]{1,}jQuery/;
		return regu.test(value);
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为手机号码
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isMobile = function(value) {
	try {
		var regu = /^([1][3]|[1][5])[0-9]{9}jQuery/;
		var re = new RegExp(regu);
		if (re.test(value)) {
			return true;
		} else {
			return false;
		}
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为电话号码,小灵通,手机号码
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isPhone = function(value) {
	try {
		if (this.isNull(value)) {
			return false;
		}
		var isSuc = false;
		var reg = /^(\({0,1}\d{3,4})\){0,1}(-){0,1}(\d{7,8})jQuery/;
		if(reg.test(value)) {
			isSuc = true;
		} else {
			reg = /^1[3,5]\d{9}jQuery/;
			if(reg.test(value)) {
				isSuc = true;
			}
		}
		return isSuc;
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为金额格式
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isMoney = function(value) {
	try {
		var regu = "^[0-9]+[\.][0-9]{0,6}jQuery";
		var re = new RegExp(regu);
		if (re.test(value)) {
			return true;
		}
		return false;
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为浮点数类型
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isFloat = function(value) {
	try {
		var regu = "^[0-9]+[\.][0-9]{0,2}jQuery";
		var re = new RegExp(regu);
		if (re.test(value)) {
			return true;
		}
		return false;
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为数字
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isNumber = function(value) {
	try {
		var regu = /[0-9]jQuery/;
		var re = new RegExp(regu);
		if (re.test(value)) {
			return true;
		}
		return false;
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为指定的长度
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.checkLength = function(value, minLength, maxLength) {
	try {
		if (this.isNull(value) || this.isNull(minLength)
				|| this.isNull(maxLength)) {
			return false;
		} else if (value.length < minLength || value.length > maxLength) {
			return false;
		} else {
			return true;
		}
	} catch (e) {
		return false;
	}
}

/**
 * 判断指定字符串是否为身份证号码
 * 
 * @param value
 *            要判断的字符串
 * @result 如果是则返回true, 否则返回false
 */
JM.prototype.isIdCard = function(value) {
	try {
		if (this.isNull(value)) {
			return false;
		}
		value = this.trim(value);
		if (this.getLength(value) == 15 || this.getLength(value) == 18) {
			return true;
		} else {
			return false;
		}
	} catch (e) {
		return false;
	}
}

/** ***********************************表单类*************************************** */
/**
 * {1} 文本框只能输入数字代码(小数点也不能输入): onkeyup="this.value=this.value.replace(/\D/g,'')" *
 * {1} 文本框只能输入数字代码(小数点也不能输入):
 * onafterpaste="this.value=this.value.replace(/\D/g,'')"
 */

/**
 * 限制指定的objectElement只能输入数字
 * 
 * @param objectElement
 *            要绑定的元素ID
 * @result
 */
JM.prototype.OnlyInputNumber = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
						element.value = element.value.replace(/\D/g, '');
					});
			element.attachEvent("onafterpaste", function() {
						element.value = element.value.replace(/\D/g, '');
					});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
						element.value = element.value.replace(/\D/g, '');
					}, false);
			element.addEventListener("afterpaste", function() {
						element.value = element.value.replace(/\D/g, '');
					}, false);
		} else {
			element.addEventListener("keyup", function() {
						element.value = element.value.replace(/\D/g, '');
					}, false);
			element.addEventListener("afterpaste", function() {
						element.value = element.value.replace(/\D/g, '');
					}, false);
		}
	} catch (e) {
		return false;
	}
}

/**
 * 限制指定的objectElement只能输入数字和小数点
 * 
 * @param objectElement
 *            要绑定的元素
 * @result
 */
JM.prototype.OnlyInputNumberAndPoint = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
				// 先把非数字的都替换掉，除了数字和.
				element.value = element.value.replace(/[^\d.]/g, "");
					// 必须保证第一个为数字而不是.
					element.value = element.value.replace(/^\./g,"");
					// 保证只有出现一个.而没有多个.
					element.value = element.value.replace(/\.{2,}/g,".");
					// 保证.只出现一次，而不能出现两次以上
					element.value =
					element.value.replace(".","jQuery#jQuery").replace(/\./g,"").replace("jQuery#jQuery",".");
				});
			element.attachEvent("onafterpaste", function() {
				// 先把非数字的都替换掉，除了数字和.
				element.value = element.value.replace(/[^\d.]/g, "");
					// 必须保证第一个为数字而不是.
					 element.value = element.value.replace(/^\./g,"");
					// 保证只有出现一个.而没有多个.
					 element.value = element.value.replace(/\.{2,}/g,".");
					// 保证.只出现一次，而不能出现两次以上
					 element.value =
					 element.value.replace(".","jQuery#jQuery").replace(/\./g,"").replace("jQuery#jQuery",".");
				});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
				// 先把非数字的都替换掉，除了数字和.
				element.value = element.value.replace(/[^\d.]/g, "");
					// 必须保证第一个为数字而不是.
					 element.value = element.value.replace(/^\./g,"");
					// 保证只有出现一个.而没有多个.
					 element.value = element.value.replace(/\.{2,}/g,".");
					// 保证.只出现一次，而不能出现两次以上
					 element.value =
					 element.value.replace(".","jQuery#jQuery").replace(/\./g,"").replace("jQuery#jQuery",".");
				}, false);
			element.addEventListener("afterpaste", function() {
				// 先把非数字的都替换掉，除了数字和.
				element.value = element.value.replace(/[^\d.]/g, "");
					// 必须保证第一个为数字而不是.
					 element.value = element.value.replace(/^\./g,"");
					// 保证只有出现一个.而没有多个.
					 element.value = element.value.replace(/\.{2,}/g,".");
					// 保证.只出现一次，而不能出现两次以上
					 element.value =
					 element.value.replace(".","jQuery#jQuery").replace(/\./g,"").replace("jQuery#jQuery",".");
				}, false);
		} else {
			// do something here if you'd like
		}
	} catch (e) {
		return false;
	}
}

/**
 * 限制指定的objectElement只能输入字母和汉字
 * 
 * @param objectElement
 *            要绑定的元素ID
 * @result
 */
JM.prototype.OnlyInputLetterAndCharacter = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
						element.value = element.value.replace(/[\d]/g, '');
					});
			element.attachEvent("onbeforepaste", function() {
						clipboardData.setData('text', clipboardData
										.getData('text').replace(/[\d]/g, ''));
					});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
						element.value = element.value.replace(/[\d]/g, '');
					}, false);
			element.addEventListener("onbeforepaste", function() {
						clipboardData.setData('text', clipboardData
										.getData('text').replace(/[\d]/g, ''));
					}, false);
		} else {
			// do something here if you'd like
		}
	} catch (e) {
		return false;
	}
}

/**
 * 限制指定的objectElement只能输入字母和汉字
 * 
 * @param objectElement
 *            要绑定的元素ID
 * @result
 */
JM.prototype.OnlyInputLetterAndNumber = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
						element.value = element.value
								.replace(/[^\w\.\@\/]/ig, '');
					});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
						element.value = element.value
								.replace(/[^\w\.\@\/]/ig, '');
					}, false);
		} else {
			// do something here if you'd like
		}
	} catch (e) {
		return false;
	}
}

/**
 * 限制指定的objectElement只能输入数字和英文
 * 
 * @param objectElement
 *            要绑定的元素ID
 * @result
 */
JM.prototype.OnlyInputNumberAndEnglish = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
						element.value = element.value
								.replace(/[^\d|chun]/g, '');
					});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
						element.value = element.value
								.replace(/[^\d|chun]/g, '');
					}, false);
		} else {
			// do something here if you'd like
		}
	} catch (e) {
		return false;
	}
}

/**
 * 限制指定的objectElement只能输入中文
 * 
 * @param objectElement
 *            要绑定的元素ID
 * @result
 */
JM.prototype.OnlyInputCharacter = function(objectElement) {
	try {
		var version = this.browserVersion();
		var element = document.getElementById(objectElement);

		if ("IE" == version) {
			element.attachEvent("onkeyup", function() {
						element.value = element.value.replace(
								/[^\u4E00-\u9FA5]/g, '');
					});
			element.attachEvent("onbeforepaste", function() {
						clipboardData.setData('text', clipboardData
										.getData('text').replace(
												/[^\u4E00-\u9FA5]/g, ''));
					});
		} else if ("Mozilla FireFox" == version) {
			element.addEventListener("keyup", function() {
						element.value = element.value.replace(
								/[^\u4E00-\u9FA5]/g, '');
					}, false);
			element.addEventListener("onbeforepaste", function() {
						clipboardData.setData('text', clipboardData
										.getData('text').replace(
												/[^\u4E00-\u9FA5]/g, ''));
					}, false);
		} else {
			// do something here if you'd like
		}
	} catch (e) {
		return false;
	}
}

/** ********************************************操作Cookie************************* */

/**
 * 根据传入的name,value,expire,path,domain,secure保存进客户端
 * 
 * @param name
 *            cookie的名称
 * @param value
 *            cookie的值
 * @param expire
 *            cookie的过期时间 以天为单位
 * @param path
 *            cookie的保存路径
 * @param domain
 *            cookie保存的域
 * @param secure
 *            cookie安全设置
 * @result 保存成功返回true 否则返回false
 */
JM.prototype.setCookie = function(name, value, expire, path, domain, secure) {
	try {
		var saveCookieByNameAndValue = false; // 根据name和value保存cookie
		var saveCookieByNameValueAndExpire = false; // 根据name,value和expire保存cookie
		var saveCookieByNameValueExpireAndPathDomain = false; // 根据name,value,expire,path和domain保存cookie
		var saveCookieByAll = false; // 根据所有属性保存cookie

		if (this.isNull(name) || this.isNull(value)) {
			return new NullPointerException("the value is null");
		} else {
			saveCookieByNameAndValue = true;
		}

		if (!this.isNull(expire)) {
			saveCookieByNameValueAndExpire = true;
		}

		if (!this.isNull(path) && !this.isNull(domain) && !this.isNull(expire)) {
			saveCookieByNameValueExpireAndPathDomain = true;
		}

		if (!this.isNull(path) && !this.isNull(domain) && !this.isNull(expire)
				&& !this.isNull(secure)) {
			saveCookieByAll = true;
		}

		if (saveCookieByAll) {
			/** 设置保存时间 * */
			var expireDate = new Date();
			expireDate.setTime(expireDate.getTime()
					+ (expire * 24 * 60 * 60 * 1000));
			document.cookie = name + "=" + escape(value) + ";"
			"expires=" + expireDate.toGMTString() + ";" + "path=" + path + ";"
					+ "domain=" + domain + ";" + "secure+" + "secure";
		}

		if (saveCookieByNameValueExpireAndPathDomain) {
			/** 设置保存时间 * */
			var expireDate = new Date();
			expireDate.setTime(expireDate.getTime()
					+ (expire * 24 * 60 * 60 * 1000));
			document.cookie = name + "=" + escape(value) + ";"
			"expires=" + expireDate.toGMTString() + ";" + "path=" + path + ";"
					+ "domain=" + domain;
		}

		if (saveCookieByNameValueAndExpire) {
			/** 设置保存时间 * */
			var expireDate = new Date();
			expireDate.setTime(expireDate.getTime()
					+ (expire * 24 * 60 * 60 * 1000));
			document.cookie = name + "=" + escape(value) + ";"
			"expires=" + expireDate.toGMTString();
		}

		if (saveCookieByNameAndValue) {
			document.cookie = name + "=" + escape(value);
		}

	} catch (e) {
	}
}

/**
 * 获取所有的cookies列表
 * 
 * @result 返回客户端所有的cookie列表
 */
JM.prototype.getCookies = function() {
	try {
		var allcookies = document.cookie;
		return allcookies
	} catch (e) {
	}
}

/**
 * 根据Cookie名称获得对应的值
 * 
 * @param value
 * @result 返回名称所对应的cookie值
 */
JM.prototype.getCookie = function(name) {
	try {
		var allcookies = document.cookie;
		var cookie_pos = allcookies.indexOf(name);
		if (cookie_pos != -1) {
			cookie_pos += name.length + 1;
			var cookie_end = allcookies.indexOf(";", cookie_pos);
			if (cookie_end == -1) {
				cookie_end = allcookies.length;
			}
			var value = unescape(allcookies.substring(cookie_pos, cookie_end));
			return value;
		}
	} catch (e) {
	}
}

/**
 * 根据Cookie名称获得对应的值
 * 
 * @param value
 * @result 返回名称所对应的cookie值
 */
JM.prototype.removeCookie = function(name) {
	try {
		var expireDate = new Date();
		expireDate.setTime(expireDate.getTime() - 10000); // 设置为一个过去的时间就自动过期
		var value = this.getCookie(name);
		if (value != null) {
			document.cookie = name + "=" + escape(value) + ";expires="
					+ expireDate.toGMTString();
		}
	} catch (e) {
	}
}

/** ******************************************操作JSON*********************************** */

/**
 * 根据指定的字符串(符合json规范的)创建JSON字符串
 * 
 * @param value
 *            指定的符合json规范的字符串
 * @result 返回JSON对象的字符串
 */
JM.prototype.createJson = function(value) {
	try {
		if (this.isNull(value)) {
			return new NullPointerException("the value is null");
		}
		var jsonObject = JSON.parse(value);
		if (!this.isNull(jsonObject)) {
			return jsonObject;
		}
	} catch (e) {
	}
}

/**
 * 根据指定的JSON对象反转为JS字符串
 * 
 * @param value
 *            JSON对象
 * @result 返回JS语法的字符串
 */
JM.prototype.reverceJson = function(value) {
	try {
		if (this.isNull(value)) {
			return new NullPointerException("the value is null");
		}
		var tempValue = JSON.stringify(value);
		if (!this.isNull(tempValue)) {
			return tempValue;
		}
	} catch (e) {
	}
}

/** ***************************************自定义登陆**************** */

/**
 * 根据传递进来的用户ID/密码ID/验证码ID,进行登陆的封装
 * 
 * @param nameElementId
 *            登陆名称ID
 * @param passwordElementId
 *            登陆密码ID
 * @param codeElementId
 *            验证码ID
 * @return 如果登陆成功则返回true, 否则返回fales
 */
JM.prototype.login = function(nameElementId, passwordElementId,
		codeElementId, submitURL, toURL) {
	try {
		var username = jQuery("#" + nameElementId);
/** 获取用户名 */
		var password = jQuery("#" + passwordElementId);
/** 获取密码 */
		var code = jQuery("#" + codeElementId);
/** 获取验证码 */

		if (this.isNull(username.val())) {
			alert("请输入您的用户名(登陆ID)!");
			username.focus();
			return false;
		} else if (this.isNull(password.val())) {
			alert("请输入您的登陆密码!");
			password.focus();
			return false;
		} else if (this.isNull(code.val())) {
			alert("请输入您的验证码!");
			code.focus();
			return false;
		} else {
			jQuery.ajax({
						type : "POST",
						url : submitURL + "&username="
								+ encodeURI(encodeURI(username.val()))
								+ "&password="
								+ encodeURI(encodeURI(password.val()))
								+ "&code=" + encodeURI(encodeURI(code.val()))
								+ "&time=" + new Date().getTime() + "",
						dataType : "text",
						success : function(data) {
							if (data != null) {
								if ("success" == data) {
									alert("欢迎您登陆: " + username.val());
									window.location.href = toURL;
								} else {
									alert(data);
									username.attr("value", "");
									password.attr("value", "");
									code.attr("value", "");
									username.focus();
								}
							}
						}
					});
		}
		return false;
	} catch (e) {
		return false;
	}
}

/**
 * 对JQuery的encode方法进行二次封装
 */
JM.prototype.encodeByJQuery = function(value) {
	try {
		var value = jQuery(value);
		return encodeURI(encodeURI(value.val()));
	}catch(e){}
}

JM.prototype.encode = function(value) {
	try {
		var value = jQuery(value);
		return encodeURI(encodeURI(value.val()));
	}catch(e){}
}

/**
 * 对JQuery的encode方法进行二次封装
 */
JM.prototype.encodeByValue = function(value) {
	try {
		return encodeURI(encodeURI(value));
	}catch(e){}
}

/**
 * 验证指定的jQuery对象里面的value是否为空
 * @param value 要验证的jQuery对象
 * @result 如果结果为空,则返回true , 如果不为空,则返回false
 */
JM.prototype.isNullByJQuery = function(value) {
	try {
		var value = jQuery(value);
		if(jQuery.trim(value.val()) == null || jQuery.trim(value.val()) == "") {
			return true;
		} else {
			return false;
		}
	}catch(e){}
}

/**
 * RGB颜色对象转为16进制
 */
JM.prototype.RgbToHex = function(rgb) {
	try {
		if (rgb.charAt(0) == '#') {
			return rgb;
		}
		var n = Number(rgb);
		var ds = rgb.split(/\D+/);
		var decimal = Number(ds[1]) * 65536 + Number(ds[2]) * 256 + Number(ds[3]);
		
		//转为16进制
		var s = decimal.toString(16);
		while (s.length < 6) {
			s = "0" + s;
		}
		
		return "#" + s;
	}catch(e){}
}

/**
 * 拷贝源内容到目标元素的内容
 */
JM.prototype.copyContent = function(source, target) {
	try {
		if(JM.isNull(source) || JM.isNull(target)) {
			return false;
		}
		
		var s1 = jQuery("#"+source);
		var s2 = jQuery("#"+target);
		
		s2.val(s1.val());
		return false;
	}catch(e) {
	}
}

/**
 * 截取sourceObject的length长度内容到targetObject
 */
JM.prototype.cutTheContentToObject = function(sourceObject, targetObject, length) {
	try {
		if(JM.isNull(sourceObject) || JM.isNull(targetObject) || JM.isNull(length)) {
			return false;
		}
		if(length <= 0) {
			return false;
		}
		
		var source = jQuery("#"+sourceObject);
		var target = jQuery("#"+targetObject);
		
		var content = source.val().substring(0, length);
		target.val(content);
		return false;
	}catch(e) {
		return false;
	}
}

/**
 * JM刷新验证码
 */
 JM.prototype.flush = function(elementId, url) {
 	try {
 		if(this.isNull(elementId)) {
 			elementId = 'verificateCodeSrc';
 		}
 		if(this.isNull(url)) {
 			url = "/verificateCode.action?random="+new Date().getTime();
 		} else {
 			url = url + "&random="+new Date().getTime();
 		}
 		jQuery("#"+elementId).attr("src", url);
 	}catch(e) {
 		return false;
 	}
 }
 
 /**
  * 根据传入的checked表单元素名称，获取选中该checked的所有记录的字符串
  */
 JM.prototype.getValueByCheckedName = function(checkedName) {
 	try {
 		var checked = jQuery("input[name="+checkedName+"]:checked");
		if(checked.size() <= 0) {
			return null;
		} else {
			var str = "";
			jQuery("input[name="+checkedName+"]:checked").each(function(){
				str+= jQuery(this).val() + ",";
			});
			return str.substring(0, str.length-1);
		}
 	}catch(e){
 		return null;
 	}
 }
 
 /**
  * 根据传入的checked表单元素名称，获取选中该checked的所有记录的字符串
  */
 JM.prototype.getValueByCheckedNameAndSeparator = function(checkedName, separator) {
 	try {
 		var checked = jQuery("input[name="+checkedName+"]:checked");
		if(checked.size() <= 0) {
			return '';
		} else {
			var str = "";
			jQuery("input[name="+checkedName+"]:checked").each(function(){
				str+= jQuery(this).val() + separator;
			});
			return str;
		}
 	}catch(e){
 		return '';
 	}
 }
 
  /**
  * 根据传入的checked表单元素名称，获取选中该checked的所有记录的字符串
  */
 JM.prototype.getValueByCheckedClassAndSeparator = function(className, separator) {
 	try {
 		var checked = jQuery("input[class="+className+"]:checked");
		if(checked.size() <= 0) {
			return '';
		} else {
			var str = "";
			jQuery("input[class="+className+"]:checked").each(function(){
				str+= jQuery(this).val() + separator;
			});
			return str;
		}
 	}catch(e){
 		return '';
 	}
 }
 
 /**
  * 添加收藏
  */
 JM.prototype.addFavorite = function(sURL, sTitle) {
 	try {
      window.external.addFavorite(sURL, sTitle);
    } catch (e) {
      try {
          window.sidebar.addPanel(sTitle, sURL, "");
      } catch (e) {
          alert("加入收藏失败，请使用Ctrl+D进行添加");
      }
    }
 }
 
 /**
  * 设为首页
  */
 JM.prototype.setHome = function(obj,vrl) {
 	try {
      obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
    } catch(e){
      if(window.netscape) {
        try {
          netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
          alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
        }
        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
        prefs.setCharPref('browser.startup.homepage',vrl);
       }
    }
    //<a onclick="AddFavorite(window.location,document.title)">加入收藏</a>
	//<a onclick="SetHome(this,window.location)">设为首页</a>
 }
 
 /**
  * 设置字体
  */
 JM.prototype.setSize = function(id, size) {
 	try {
 		var obj = jQuery("#" + id);
 		obj.css("font-size", size + "px");
 	} catch(e) {
 		return false;
 	}
 }
 
 /**
  * 按Enter键确认(提交)
  * enterFuc 按Enter键想提交的方法
  */
  JM.prototype.setEnterKey = function(enterFuc) {
  	try {
	  	jQuery(document).bind("keypress", function(e){
			if(e.which == 13){
				eval(enterFuc);
				return false;
			}
		});
  	}catch(e){
  		return false;
  	}
  }
  
  JM.prototype.bindKey = function(funName) {
  	try {
	  	jQuery(document).unbind("keypress");
		JM.setEnterKey(funName + '()');
  	}catch(e){
  		return false;
  	}
  }
  
  /**
    * 选中select中的option
    * @param selectId 目标的select
    * @param value 目标value
    */
   JM.prototype.selectValue = function(selectId, value) {
   		try {
			var temp = jQuery("#"+selectId)[0];
			for(var i=0; i<temp.length; i++) {
				if (temp.options[i].value == value){
					temp.options[i].selected = true;
					break;
				}
			}
		}catch(e) {
		}
   }
   
   /**
    * 选中checkbox中的checked
    * @param selectId 目标的select
    * @param value 目标value
    */
   JM.prototype.selectCheckBoxValue = function(selectId, value) {
   		try {
			var chkboxs = jQuery("input[name="+selectId+"]");
			var array = new Array(); 
			array = value.split(',');
			for(var i=0; i<array.length; i++) {
				var t1 = JM.trim(array[i]);
				for(var j=0; j<chkboxs.size(); j++) {
					var t2 = JM.trim(jQuery(chkboxs.get(j)).val());
					if (t1 == t2){
						jQuery(chkboxs.get(j)).attr("checked", "true");
						break;
					}
				}
			}
		}catch(e) {
		}
   }
   
   /**
	 * 选中单选按钮
	 * @param radioName 单选按钮组的名称
	 * @param value 目标value
	 */
   JM.prototype.selectRadioValue = function(radioName, value) {
   		try {
			if(radioName != null && value != null && value != "" && radioName != "") {
				var temp = document.getElementsByName(radioName);
				for(var i=0; i<temp.length; i++) {
					if(value == temp[i].value) {
						temp[i].checked = true;
						break;
					}
				}
			}
		}catch(e) {
		}
   }
   
   /**
	 * 选中单选按钮
	 * @param radioName 单选按钮组的名称
	 * @param value 目标value
	 */
   JM.prototype.selectRadio = function(radioName) {
   		try {
			if(radioName != null && radioName != "" && radioName != undefined) {
				var currentValue = jQuery(jQuery("input[name="+radioName+"]").get(0)).attr("currentValue");
				this.selectRadioValue(radioName, currentValue);
			}
		}catch(e) {
		}
   }
   
    /**
    * 选中select中的option
    * @param selectId 目标的select
    * @param value 目标value
    */
   JM.prototype.selectSelect = function(selectId) {
   		try {
			var temp = jQuery("#"+selectId);
			var currentValue = temp.attr("currentValue");
			this.selectValue(selectId, currentValue);
		}catch(e) {
		}
   }
   
   /**
	 * 选中select中的option
	 * @param selectId 目标的select
	 * @param value 目标value
	 */
   JM.prototype.selectMultiValue = function(selectId, value) {
   		try {
			var array = new Array(); 
			array = value.split(',');
			
			var temp = jQuery("#"+selectId)[0];
			for(var i=0; i<array.length; i++) {
				var t = array[i];
				for(var j=0; j<temp.length; j++) {
					var tt = parseInt(temp.options[j].value);
					if (tt == t){
						temp.options[j].selected = true;
						break;
					}
				}
				t = null;
			}
		}catch(e) {
		}
   }
   
   /**
	 * 选中所有的复选框
	 * @param ids 复选框的name属性
	 * @param type 按钮类型
	 */
   JM.prototype.checkAll = function(ids, id) {
   		try {
			var t = jQuery("#" +id);
			if(t.is(":checked")) {
				jQuery("[name="+ids+"]:checkbox").attr("checked", "true"); 
			} else {
				jQuery("[name="+ids+"]:checkbox").removeAttr("checked");
			}
			return false;
		}catch(e) {
		}
   }
   
   
   /**
    * JS获取当前页面的版本标识
    */
   JM.prototype.flag = function() {
   		try {
   			return jQuery("#flag").val();
   		}catch(e){
   			return this.CHINESE;
   		}
   }
   /**
    * 获取当前页面的URL
    */
   JM.prototype.currentUrl = function(){
   		window.location.href = document.URL;
   }
   /**
    * 获取上一页的URL
    */
   JM.prototype.prevUrl = function(){
   		window.location.href = document.referrer;
   }
   
   /**
    * 比较两个密码是否一致
    * 一致返回true, 不一致返回false
    * @param element1 元素ID
    * @param element2 元素ID
    */
   JM.prototype.equalPassword = function(element1, element2) {
   		try {
   			var e1 = jQuery("#"+element1);
   			var e2 = jQuery("#"+element2);
   			if(e1.val() == e2.val()) {
   				return true;
   			}
   			return false;
   		}catch(e){
   			return false;
   		}
   }
   
   /**
    * 验证值是否可以用, 可以用返回true, 不可以用返回false
    * @param validateUrl 验证的URL
    * @param elementId   验证的元素ID
    * @param requestName 请求的名称
    */
   JM.prototype.validateValue = function(validateUrl, elementId, requestName) {
   		try {
   			var isSuc = false;
   			var objV = jQuery("#"+elementId);
   			jQuery.ajax({
   			    async:false,
   				type:'POST',
   				dataType:'text',
   				url:''+validateUrl+'',
   				data:''+requestName+'='+JM.trim(objV.val())+'&t='+JM.randomNumber,
   				success:function(data){
   					if(JM.SUCCESS == data) {
   						isSuc = true;
   					} else {
   						isSuc = false;
   					}
   				}
   			});
   			return isSuc;
   		}catch(e){
   			return false;
   		}
   }
   
   /**
    * 兼容IE和火狐和谷歌的复制链接方法 
    */
   JM.prototype.copy = function(meintext) {
   		try {
   			if (window.clipboardData) {
				window.clipboardData.setData("Text", meintext)
			 } else {
			 	var flashcopier = 'flashcopier';
			 	if(!document.getElementById(flashcopier)) {
			        var divholder = document.createElement('div');
			  		divholder.id = flashcopier;
			  		document.body.appendChild(divholder);
			 	}
			 	document.getElementById(flashcopier).innerHTML = '';
				var divinfo = '<embed src="/jslib/clipboard.swf" FlashVars="clipboard='+encodeURIComponent(meintext)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';
				document.getElementById(flashcopier).innerHTML = divinfo;
			}
			alert('地址已经复制到您的剪贴板，您可以发送给您的朋友啦！');
			return false;
   		}catch(e){
   			alert(e);
   			return false;
   		}
   } 
   
    /**
    * @param inputObj 具体的input对象
    * @param resultValue 要返回的结果字符串
    */
   function getParameterValue(inputObj, resultValue) {
		if(!JM.isNullByJQuery(inputObj)) {
			return resultValue + JM.encode(inputObj) + "&";
		} else{
			return "";
		}
	}
	
   /**
    * 处理没有的图片，或者丢失的图片
    * @param defaultSrc 默认图片
    * @return
    */
   JM.prototype.processNothingImg = function processNothingImg(defaultSrc) {
	   try{
			jQuery("img").bind("error", function() {
		   		jQuery(this).attr("src", defaultSrc);
		   	});
		   	var imgs = jQuery("img");
		   	imgs.each(function(){
		   		var src = jQuery(this).attr("src");
		   		if(src == "") {
		   			jQuery(this).attr("src", defaultSrc);
		   		} else {
		   			if(document.all) {
		   				jQuery(this).attr("src", src);
		   			}
		   		}
		   	});
	   }catch(e){
   			alert(e);
   			return false;
   	   }
   }
   
   /**
    * 封装请求参数
    * @param value 要请求封装的参数, 多个value用"|"分割
    */
   JM.prototype.buildParameter = function(value) {
   	  try {
   	  	var test = value.split('|');
		var resultValue = "";
		for(var i=0; i<test.length; i++){
			var inputObj = jQuery("#"+test[i]);
			if(this.isNull(inputObj)) {
	   	   	   inputObj = jQuery("input[name="+test[i]+"]:checked");
	   	    }
			var paramName = inputObj.attr("name");
			resultValue += getParameterValue(inputObj, paramName + "=");
		}
		
		//增加传输到服务器端附件的参数
		resultValue = addCommonAnnexParameter(resultValue);
		
		return resultValue + 'random=' + JM.randomNumber + '&operationFlag=1';
   	  } catch(e){
   	  	return false;
   	  }
   }
   
   /**
    * 增加传输到服务器端附件的参数
    */
   function addCommonAnnexParameter(resultValue) {
	    //添加附件
	   	if (jQuery("input[name='files']").val() != undefined) {
	   		resultValue = resultValue.substring(0, resultValue.length - 1);
			jQuery("input[name='files']").each(function(){
				resultValue = resultValue + "&files=" +  jQuery(this).val();
			});
			jQuery("input[name='annexTitle']").each(function(){
				if (!jQuery(this).hasClass("noSubmit")) {
					resultValue = resultValue + "&annexTitle=" +  jQuery(this).val();
				}
			});
			jQuery("input[name='annexContent']").each(function(){
				if (!jQuery(this).hasClass("noSubmit")) {
					resultValue = resultValue + "&annexContent=" +  jQuery(this).val();
				}
			});
			jQuery("input[name='annexOrderValue']").each(function(){
				if (!jQuery(this).hasClass("noSubmit")) {
					resultValue = resultValue + "&annexOrderValue=" +  jQuery(this).val();
				}
			});
			resultValue += "&";
	   	}
	   	return resultValue;
   }
   
   /**
    * 清空表单元素里面的值
    */
   JM.prototype.clearParameter = function(value) {
   	  try {
   	  	var test = value.split('|');
		for(var i=0; i<test.length; i++){
			var inputObj = jQuery("#"+test[i]);
			inputObj.val("");
		}
   	  } catch(e){
   	  	return false;
   	  }
   }
   
   /**
    * 清空表单元素里面的值
    */
   JM.prototype.executeMethod = function(methodName) {
   	  try {
   	  	eval(methodName+"()");
   	  } catch(e){
   	  	return false;
   	  }
   }
   
   /**
    * 清空表单元素里面的值
    */
   JM.prototype.executeMethodParameter = function(methodName) {
   	  try {
   	  	eval(methodName);
   	  } catch(e){
   	  	return false;
   	  }
   }
   
   /**
    * 返回上一页
    */
   JM.prototype.back = function(stepNum){
   	  if(stepNum == null || stepNum == ''){
   	  	window.history.go(-1);
   	  } else {
   	  	window.history.go(stepNum);
   	  }
   }
   
   /**
    *  转向到某个url
    */
   JM.prototype.goUrl = function(url){
   	  window.location.href = url;
   }
   
    /**
    *  转向到某个url
    */
   JM.prototype.reload = function(url){
   	  window.location.reload();
   }
   
   /**
    * 根据表单的ID或者NAME获取元素
    */
   JM.prototype.getFormObject = function(elementId) {
   	   var obj = jQuery("#" + elementId);
   	   if(obj.attr("tagName") == undefined) {
   	   	   obj = jQuery("input[name="+elementId+"]:checked");
   	   }
   	   return obj;
   }
   
   /**
    * 弹出EasyDialog的弹出层
    */
   JM.prototype.alert = function(value, time, callback) {
	   if(this.isNull(time)) {
		   time = 1200; //单位毫秒
	   }else if(time == -1){
		   time = false;
	   }
	   easyDialog.open({
		   container : {
		     content : '<h1>' + value + '</h1>'
		   },
		   autoClose : time,
		   callback : callback
	   });
   }
   
   /**
    * 加载提示框
    */
   JM.prototype.loading = function(msg){
	   
	   this.alert('<img src="/manager/kindeditor/themes/common/loading.gif" style="margin-right: 10px;"/>' + msg, -1);
   }
   
   /**
    * 关闭EasyDialog的弹出层
    */
   JM.prototype.closeAlert = function(){
	   
	   easyDialog.close();
   }
   
   /**
    * 初始化滚动
    */
   JM.prototype.initScroll = function(methodName) {
		jQuery(document).scroll(function(){
			var totalheight = parseFloat(jQuery(window).height()) + parseFloat(jQuery(window).scrollTop());
			if (jQuery(document).height() <= totalheight){
				JM.executeMethod(methodName);
		    } 
		});
	}
   
   JM.prototype.alertMsg = function(msgtitle, msg, mode, successMethod, failMethod) {//mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
	   msg = msg || '';
	   msgtitle = msgtitle || '';
       mode = mode || 0;
       if(JM.isNull(msgtitle)) {
    	   msgtitle = '系统提示';
       }
       if(JM.isNull(msg)) {
    	   msg = '您的操作有误~请重新试试吧 o(∩_∩)o';
       }
       var top = document.body.scrollTop || document.documentElement.scrollTop;
       var isIe = (document.all) ? true : false;
       var isIE6 = isIe && !window.XMLHttpRequest;
       var sTop = document.documentElement.scrollTop || document.body.scrollTop;
       var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
       var winSize = function(){
           var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
           // innerHeight获取的是可视窗口的高度，IE不支持此属性
           if (window.innerHeight && window.scrollMaxY) {
               xScroll = document.body.scrollWidth;
               yScroll = window.innerHeight + window.scrollMaxY;
           } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
               xScroll = document.body.scrollWidth;
               yScroll = document.body.scrollHeight;
           } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
               xScroll = document.body.offsetWidth;
               yScroll = document.body.offsetHeight;
           }

           if (self.innerHeight) {    // all except Explorer
               windowWidth = self.innerWidth;
               windowHeight = self.innerHeight;
           } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
               windowWidth = document.documentElement.clientWidth;
               windowHeight = document.documentElement.clientHeight;
           } else if (document.body) { // other Explorers
               windowWidth = document.body.clientWidth;
               windowHeight = document.body.clientHeight;
           }

           // for small pages with total height less then height of the viewport
           if (yScroll < windowHeight) {
               pageHeight = windowHeight;
           } else {
               pageHeight = yScroll;
           }

           // for small pages with total width less then width of the viewport
           if (xScroll < windowWidth) {
               pageWidth = windowWidth;
           } else {
               pageWidth = xScroll;
           }

           return{
               'pageWidth':pageWidth,
               'pageHeight':pageHeight,
               'windowWidth':windowWidth,
               'windowHeight':windowHeight
           }
       }();
       //alert(winSize.pageWidth);
       //遮罩层
       var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#000;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
       styleStr += (isIe) ? "filter:alpha(opacity=70);" : "opacity:0.7;"; //遮罩层DIV
       var shadowDiv = document.createElement('div'); //添加阴影DIV
       shadowDiv.style.cssText = styleStr; //添加样式
       shadowDiv.id = "shadowDiv";
       //如果是IE6则创建IFRAME遮罩SELECT
       if (isIE6) {
           var maskIframe = document.createElement('iframe');
           maskIframe.style.cssText = 'width:' + winSize.pageWidth + 'px;height:' + (winSize.pageHeight + 30) + 'px;position:absolute;visibility:inherit;z-index:-1;filter:alpha(opacity=0);';
           maskIframe.frameborder = 0;
           maskIframe.src = "about:blank";
           shadowDiv.appendChild(maskIframe);
       }
       document.body.insertBefore(shadowDiv, document.body.firstChild); //遮罩层加入文档
       //弹出框
       var styleStr1 = 'display:block;position:fixed;'; 
       var alertBox = document.createElement('div');
       alertBox.id = 'alertMsg';
       alertBox.style.cssText = styleStr1;
       //创建弹出框里面的内容span标签 标题
		var alertMsg_title = document.createElement('span');
       alertMsg_title.id = 'alertMsg_title';
       alertMsg_title.innerHTML = msgtitle;
       alertBox.appendChild(alertMsg_title);
		
		//创建弹出框里面的内容p标签 标题
       var alertMsg_info = document.createElement('P');
       alertMsg_info.id = 'alertMsg_info';
       alertMsg_info.innerHTML = msg;
       alertBox.appendChild(alertMsg_info);
       //创建按钮
       var btn_confirm = document.createElement('a');
       btn_confirm.id = 'alertMsg_btn_confirm';
       btn_confirm.href = 'javas' + 'cript:void(0)';
       btn_confirm.innerHTML = '确定';
       btn_confirm.onclick = function () {
           document.body.removeChild(alertBox);
           document.body.removeChild(shadowDiv);
           JM.executeMethodParameter(successMethod);
       };
       alertBox.appendChild(btn_confirm);
       if (mode === 1) {
           var btn_remove = document.createElement('a');
           btn_remove.id = 'alertMsg_btn_remove';
           btn_remove.href = 'javas' + 'cript:void(0)';
           btn_remove.innerHTML = '取消';
           btn_remove.onclick = function () {
               document.body.removeChild(alertBox);
               document.body.removeChild(shadowDiv);
               JM.executeMethodParameter(failMethod);
           };
           alertBox.appendChild(btn_remove);
       }
       document.body.appendChild(alertBox);
       if(mode == 0) {
    	   jQuery("#alertMsg_btn_confirm").width("99%");
       }
   }
   
   /**
    * 弹出EasyDialog的弹出层
    */
   JM.prototype.firstCharacterToLowerCase = function(value) {
	   var v1 = value.substring(0, 1);
	   var v2 = value.substr(1);
	   v1 = v1.toLowerCase();
	   return v1 + v2;
   }
   
   /**
    * 初始化点击事件
    * @return
    */
   JM.prototype.initialClickUrl = function(){
	   jQuery(".clickUrlClass").bind("click", function(){
		    window.location.href = jQuery(this).attr("url");
	   });
   }
   
   /**
    * dom1滚动到dom2的高度，动画效果持续一秒
    * @param dom1 
    * @param dom2
    */
   JM.prototype.scrollTo = function(dom1, dom2, height, time) { 
	   
	   var offset = $(dom2).offset(); //得到pos这个div层的offset，包含两个值，top和left 
	   var top = offset.top - (height ? height : 0);
	   $(dom1).animate({ 
		   scrollTop : top //让body的scrollTop等于pos的top，就实现了滚动 
	   	},(time ? time : 1000)); 
   } 
   
   /**
    * 确认弹出框
    * @param msg
    * @returns
    */
   JM.prototype.confirm = function(msg, okCallcack, cancelCallback){
	   
	   //显示上传提示
	   var confirm = bootbox.confirm({
		   title : '<span class="label label-xlg label-warning arrowed-right"">提示</span>',
		   size: 'small',
		   message : '<h3>' + msg + '</h3>',
		   callback : function(result){
			   
			   if(result == true && okCallcack){ //点击yes才执行回调函数
				   
				   okCallcack();
			   }else if(result == false && cancelCallback){
				   
				   cancelCallback();
			   }
		   }
	   });
	   return confirm;
   }
   
   /** 实例化JS对象,用于调用的页面用 */
   var JM = new JM();
   JM.todayDate = JM.getDateString('yyyy-MM-dd');