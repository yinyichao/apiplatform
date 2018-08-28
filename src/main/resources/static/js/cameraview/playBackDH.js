var bLogin = -1;
function init() {
	//	try {
	//		var obj = new ActiveXObject("DPSDK_OCX.DPSDK_OCXCtrl.1");
	startPreview();
	//	} catch (e) {
	//		alert("请下载ocx");
	//		location.href = "/download/ocx/DH/ocx_64.rar";
	//	}
}
function startPreview() {

	var obj = document.getElementById("DPSDK_OCX");
	gWndId = obj.DPSDK_CreateSmartWnd(0, 0, 100, 100); //初始化控件，创建视频窗

	obj.DPSDK_SetWndCount(gWndId, 4);//将视频窗4分割

	var obj = document.getElementById("DPSDK_OCX");

	var szIp = document.getElementById("textIP").value;
	var nPort = document.getElementById("textPort").value;
	var szUsername = document.getElementById("textUser").value;
	var szPassword = document.getElementById("textPassword").value;
	var nRet = obj.DPSDK_Login(szIp, nPort, szUsername, szPassword);//登陆，参数根据环境自己设置
	//alert("Login" + "  " + nRet);
	if (nRet == 0) {
		bLogin = 1;
	}
	//nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 0, "1000001$1$0$0", 1, 1, 1);//第一个子窗口打开视频,视频通道号1000605$1$0$0，此字段的获取请参考开发包中附带的《组织树XML解析手册》和《常见问题解答文档》
	//alert("open video"  + "  " + nRet);
	/*nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 1, "1000605$1$0$1", 1, 1, 1);//第二个子窗口打开视频
	alert("open video"  + "  " + nRet);
	nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 2, "1000605$1$0$0", 1, 1, 1);//第三个子窗口打开视频
	alert("open video"  + "  " + nRet);*/
	//sleep 5分钟后，调用登出接口
	//DPSDK_Logout()
}
function startPreview_g(){
	var obj = document.getElementById("DPSDK_OCX");
	obj.DPSDK_LoadDGroupInfo();
	var te = obj.DPSDK_GetDGroupStr();
	var szCameraId = document.getElementById("textCameraID_g").value;
	alert(szCameraId);
	var obj = document.getElementById("DPSDK_OCX");
	var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
	//录像来源类型，2设备，3平台
	var nSourceType = document.getElementById("selectRecordSource").value;
	//录像类型,0全部
	var nRecordType = document.getElementById("selectRecordType").value;
	//开始时间，例如2014/9/4 0:00:00,需要转换成长整型：1409760000
	var strStartTime = document.getElementById("textStartTime").value;
	//结束时间，例如2014/9/4 12:12:12,需要转换成长整型：1409803932
	var strEndTime = document.getElementById("textEndTime").value;
	var nStartTime = getDate(strStartTime).getTime() / 1000;
	//	alert(nStartTime);
	var nEndTime = getDate(strEndTime).getTime() / 1000;
	var result = obj.DPSDK_StartTimePlaybackByWndNo(gWndId, nWndNo, szCameraId,
			nSourceType, nStartTime, nEndTime);
	if (result == 0) {
		alert("成功");
	}
	else
	{
		alert("错误码："+result);
	}
}
//登出
function ButtonLogout_onclick() {
	var obj = document.getElementById("DPSDK_OCX");
	if (bLogin == 1) {
		var nRet = obj.DPSDK_Logout();
		bLogin = 0;
	}
}
function getDate(strDate) {
	var date = eval('new Date('
			+ strDate.replace(/\d+(?=-[^-]+$)/, function(a) {
				return parseInt(a, 10) - 1;
			}).match(/\d+/g) + ')');
	return date;
}
function ButtonStartTimePlaybackByWndNo() {
	var obj = document.getElementById("DPSDK_OCX");
	var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
	//视频通道ID
	var szCameraId = document.getElementById("textCameraID").value;
	//录像来源类型，2设备，3平台
	var nSourceType = document.getElementById("selectRecordSource").value;
	//录像类型,0全部
	var nRecordType = document.getElementById("selectRecordType").value;
	//开始时间，例如2014/9/4 0:00:00,需要转换成长整型：1409760000
	var strStartTime = document.getElementById("textStartTime").value;
	//结束时间，例如2014/9/4 12:12:12,需要转换成长整型：1409803932
	var strEndTime = document.getElementById("textEndTime").value;
	var nStartTime = getDate(strStartTime).getTime() / 1000;
	//	alert(nStartTime);
	var nEndTime = getDate(strEndTime).getTime() / 1000;
	var result = obj.DPSDK_StartTimePlaybackByWndNo(gWndId, nWndNo, szCameraId,
			nSourceType, nStartTime, nEndTime);
	if (result == 0) {
		alert("成功");
	}
	else
	{
		alert("错误码："+result);
	}
}
//加载组织结构
function ButtonLoadDGroupInfo_onclick() {
	var obj = document.getElementById("DPSDK_OCX");

	ShowCallRetInfo(obj.DPSDK_LoadDGroupInfo(), "加载组织结构");
	
	document.getElementById("GlobalInfo").innerText = obj.DPSDK_GetDGroupStr();
}
function ShowCallRetInfo(nRet, strInfo) 
{
    //if (nRet != 0)
    //{
    //    var obj = document.getElementById("DPSDK_OCX");
    //    alert(strInfo + ": ErrorCode = "+obj.DPSDK_GetLastError());
    //}
	
	var str = "";
	if(nRet == 0)
	{
	     str = strInfo + " 成功！";
	}
	else
	{
	     str = strInfo + "失败！错误码：" + nRet;
	}
	document.getElementById("RetInfo").innerText = str;
}