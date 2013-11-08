/*!
 * Copyright &copy; 2012-2013 All rights reserved.
 	design by panxiaochao
 */

/*
函数原型：
$.jBox(content, options);
　　　 └ 或者 jBox(content, options);
参数说明：
- content　(string,json)
　　　└ 可以是string或json。当是string时，需要加上前缀标识（html:、id:、get:、post:、iframe:），如果没有加标识，系统会自动加上html:，具体请看应用例子。当是json时，表示一个或多个状态，每个状态的默认值为 $.jBox.stateDefaults。
- options　[可选] (json)
　　　└ 其它参数选项，默认值为 $.jBox.defaults。
*/


// 此例省略了前缀html:，前缀标识是不区分大小写的，也可以是HTML:
function info(content,option){
	$.jBox.info(info,option);
}
// 显示id为id-html的div内部html，同时设置了bottomText
// ajax get 页面ajax.html的内容并显示，例如要提交id=1，则地址应该为 ajax.html?id=1，post:前缀的使用与get:的一样
// 用iframe显示http://www.baidu.com的内容，并设置了标题、宽与高、按钮
function jinfocontent(content,option){
	$.jBox(content, option);
}

/*
函数原型：
$.jBox.open(content, title, width, height, options);
　　　 └ 或者 jBox.open(content, title, width, height, options);
参数说明：
- content　(string,json)
　　　└ 可以是string或json。当是string时，需要加上前缀标识（html:、id:、get:、post:、iframe:），如果没有加标识，系统会自动加上html:，具体请看应用例子。当是json时，表示一个或多个状态，每个状态的默认值为 $.jBox.stateDefaults。
- title　[可选] (string)
　　　└ 窗口标题，值为null时为不显示标题，默认值为 $.jBox.defaults.title。
- width　[可选] (string,number)
　　　└ 窗口宽度，值为'auto'或具体像素值（例如350），默认值为 $.jBox.defaults.width。
- height　[可选] (string,number)
　　　└ 窗口高度，值为'auto'或具体像素值（例如100），默认值为 $.jBox.defaults.height。
- options　[可选] (json)
　　　└ 其它参数选项，默认值为 $.jBox.defaults。ts。
*/

function openwindows(content, title, width, height, option){
	$.jBox.open(content, title, width, height, option);
}

/*
函数原型：
$.jBox.prompt(content, title, icon, options);
　　　 └ 或者 jBox.prompt(content, title, icon, options);
参数说明：
- content　(string)
　　　└ 只能是string，不支持前缀标识，默认值为''。
- title　[可选] (string)
　　　└ 窗口标题，值为null时为不显示标题，默认值为 $.jBox.defaults.title。
- icon　[可选] (string)
　　　└ 内容图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error'，默认值为'none'。
- options　[可选] (json)
　　　└ 其它参数选项，默认值为 $.jBox.defaults。

备注：以下几个方法由　$.jBox.prompt()　扩展而来，参数类似，请看下面的例子。
$.jBox.alert(content, title, options);
　　　 └ 或者 jBox.alert(content, title, options);
$.jBox.info(content, title, options);
　　　 └ 或者 jBox.info(content, title, options);
$.jBox.success(content, title, options);
　　　 └ 或者 jBox.success(content, title, options);
$.jBox.error(content, title, options);
　　　 └ 或者 jBox.error(content, title, options);
$.jBox.confirm(content, title, submit, options);
　　　 └ 或者 jBox.confirm(content, title, submit, options);
$.jBox.warning(content, title, submit, options);
　　　 └ 或者 jBox.warning(content, title, submit, options);
　　　 └ 上面方法中默认按钮的文字设置在 $.jBox.languageDefaults。
*/

function jprompt(content, title, icon, option){
	$.jBox.prompt(content, title, icon, option);
}

function jalert(content, title, option){
	$.jBox.alert(content, title, option);
}

function jinfo(content, title, option){
	$.jBox.info(content, title, option);
}

function jsuccess(content, title, option){
	$.jBox.success(content, title, option);
}

function jerror(content, title, option){
	$.jBox.error(content, title, option);
}

function jconfirm(content, title, option){
	$.jBox.confirm(content, title, option);
}

function jwarning(content, title, option){
	$.jBox.warning(content, title, option);
}

/*
函数原型：
$.jBox.tip(content, icon, options);
　　　 └ 或者 jBox.tip(content, icon, options);
参数说明：
- content　(string)
　　　└ 只能是string，不支持前缀标识，默认值为''。
- icon　[可选] (string)
　　　└ 内容图标，可选值有'info'、'success'、'warning'、'error'、'loading'，默认值为'info'，当为'loading'时，timeout值会被设置为0，表示不会自动关闭。
- options　[可选] (json)
　　　└ 其它参数选项，默认值为 $.jBox.tipDefaults。

*/
function jtip(content, icon, option){
	$.jBox.tip(content, icon, option);
}

function jloading(content, time, recontent, reicon, option){
	$.jBox.tip(content, 'loading');
	// 模拟time秒后完成操作
	window.setTimeout(function () { $.jBox.tip(recontent, reicon); }, time);
}
/*
函数原型：
$.jBox.messager(content, title, timeout, options);
　　　 └ 或者 jBox.messager(content, title, timeout, options);
参数说明：
- content　(string)
　　　└ 只能是string，不支持前缀标识，默认值为''。
- title　[可选] (string)
　　　└ 窗口标题，值为null时为不显示标题，默认值为 $.jBox.messagerDefaults.title。
- timeout　[可选] (number)
　　　└ 显示多少毫秒后自动关闭，如果为0则不自动关闭，默认值为 $.jBox.messagerDefaults.timeout。
- options　[可选] (json)
　　　└ 其它参数选项，默认值为 $.jBox.messagerDefaults。
*/

function jmessager(content, title, timeout, option){
	$.jBox.messager(content, title, timeout, option);
}

