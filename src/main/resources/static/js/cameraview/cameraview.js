// 显示海康视频预览页
function showStartPreviewHK(prison,code) {
	var url = '/video/startPreview?code='+code+'&prison='+prison;
	var iWidth = 600;
	var iHeight = 500;
	window.open (
					url,'new','height:' + iHeight + 'px;width:' + iWidth 
					+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
	return false;
}
function previewDH() {
    var url = '/video/previewDH';
    var iWidth = 600;
    var iHeight = 500;
    window.open (
        url,'new','height:' + iHeight + 'px;width:' + iWidth
        + 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
    return false;
}

// 显示海康视频回放页
function showPlayBackHK(prison,code){
	var url = '/video/playBack?code='+code+'&prison='+prison;
	var iWidth = 620;
	var iHeight = 520;
	window.open (
			url,'new','dialogHeight:' + iHeight + 'px;dialogWidth:' + iWidth 
			+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:no;');
	return false;
}
//显示大华视频预览页
function showStartPreviewDH(prison,code) {
	var url = '/video/startPreviewDH?code='+code+'&prison='+prison;
	var iWidth = 620;
	var iHeight = 520;
	window
			.open(
					url,'new','dialogHeight:' + iHeight + 'px;dialogWidth:' + iWidth 
					+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
	return false;
}
//显示大华视频回放页
function showPlayBackDH(prison,code){
	var url = '/video/playBackDH?code='+code+'&prison='+prison;
	var iWidth = 620;
	var iHeight = 800;
	window
			.open(
					url,'new','height:' + iHeight + 'px;width:' + iWidth 
					+ 'px;center:yes;');
	return false;
}
//显示大华视频回放页
function showDemo(prison,code){
	var url = '/video/demo?code='+code+'&prison='+prison;
	var iWidth = 620;
	var iHeight = 520;
	window
			.open(
					url,'new','dialogHeight:' + iHeight + 'px;dialogWidth:' + iWidth 
					+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
	return false;
}
//显示大华视频回放页
function rhtx(){
	var url = '/video/rhtx';
	var iWidth = 620;
	var iHeight = 520;
	window
			.open(
					url,'new','dialogHeight:' + iHeight + 'px;dialogWidth:' + iWidth 
					+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
	return false;
}
function preview64(){
	var url = '/video/preview64';
	var iWidth = 620;
	var iHeight = 520;
	window
			.open(
					url,'new','dialogHeight:' + iHeight + 'px;dialogWidth:' + iWidth 
					+ 'px;center:yes;status:no;toolbar:no;menubar:no;location:no;resizable:yes;scroll:no;titlebar:yes;minimize:yes;maximize:yes;');
	return false;
}