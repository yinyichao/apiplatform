var bLogin = -1;
function init(){
//	try {
//		var obj = new ActiveXObject("DPSDK_OCX.DPSDK_OCXCtrl.1");
		startPreview();
//	} catch (e) {
//		alert("请下载ocx");
//		location.href = "/download/ocx/DH/ocx_64.rar";
//	}
}
function startPreview(){
	
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
	if(nRet == 0)
	{
		bLogin = 1;
	}
	//视频通道ID
	/*var szCameraId = document.getElementById("textCameraID_g").value;
	nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 2, szCameraId, 1, 1, 1);*///第一个子窗口打开视频,视频通道号1000605$1$0$0，此字段的获取请参考开发包中附带的《组织树XML解析手册》和《常见问题解答文档》
	//alert("open video"  + "  " + nRet);
	/*nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 1, "1000605$1$0$1", 1, 1, 1);//第二个子窗口打开视频
	alert("open video"  + "  " + nRet);
	nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 2, "1000605$1$0$0", 1, 1, 1);//第三个子窗口打开视频
	alert("open video"  + "  " + nRet);*/
	//sleep 5分钟后，调用登出接口
	//DPSDK_Logout()
	startPreview_g();
}
function startPreview_g(){
	var obj = document.getElementById("DPSDK_OCX");
	obj.DPSDK_LoadDGroupInfo();
	var te = obj.DPSDK_GetDGroupStr();
	var szCameraId = document.getElementById("textCameraID_g").value;
	nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, 1, szCameraId, 1, 1, 1);//第一个子窗口打开视频,视频通道号1000605$1$0$0，此字段的获取请参考开发包中附带的《组织树XML解析手册》和《常见问题解答文档》

}
//抓图
function ButtonCapturePictureByWndNo_onclick(){
	var obj = document.getElementById("DPSDK_OCX");
	var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
	var mydate=new Date();
	var reg=new RegExp(":","g");
	var path="D:\\afjt\\"+mydate.toLocaleString().replace(" ","").replace("年","").replace("月","").replace("日","").replace(reg,"")+".bmp";
	//alert("gWndId"+gWndId+",nWndNo"+nWndNo);
	obj.DPSDK_CapturePictureByWndNo(gWndId, nWndNo,path);
	alert("存储路径："+path);
}
//登出
function ButtonLogout_onclick() 
{
    var obj = document.getElementById("DPSDK_OCX");
    if( bLogin == 1)
	{
        var nRet = obj.DPSDK_Logout();
	    bLogin = 0;
	}
}