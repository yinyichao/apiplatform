//初始化
function init() {
	try {
		var obj = new ActiveXObject("AlmSinglePlaybackOcx.AlmSPBOcxImpl.1");
		LoginPlatBack();
	} catch (e) {
		alert("请下载ocx");
		location.href = "/download/ocx/HK/back/OCX_V6.4.0.4229.build20170323.exe";
	}
}
//登录
function LoginPlatBack() {
	var _param = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<PlaybackOcxConfig>"
			+ "<SnapParam><FileFormat>0</FileFormat><FilePath>C:\\capture\\</FilePath>"
			+ "<FileCategorization>0</FileCategorization><FileNameFormat></FileNameFormat><SnapMode>2</SnapMode>"
			+ "<ContinousNum>3</ContinousNum><ContinousMode>2</ContinousMode><ContinousInterval>1000</ContinousInterval></SnapParam>"
			+ "<ClipParam><FilePath>C:\clip\\</FilePath><FileNameFormat></FileNameFormat><PackSize>256</PackSize></ClipParam>"
			+ "</PlaybackOcxConfig>";

	playback.SetLocalParam(_param);
	//Demo默认使用密码登录方式
	var IP = document.getElementById("IP").value;
	var port = document.getElementById("port").value;
	var UserName = document.getElementById("UserName").value;
	var Password = document.getElementById("Password").value;
	var v1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LoginInfo><LoginType>2</LoginType><IP>"
			+ IP
			+ "</IP><Port>"
			+ port
			+ "</Port><UserName>"
			+ UserName
			+ "</UserName><Password>" + Password + "</Password></LoginInfo>";
	//var v1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LoginInfo><LoginType>2</LoginType><IP>10.33.29.133</IP><Port>80</Port><UserName>admin</UserName><Password>Abc12345</Password></LoginInfo>";
	//alert(v1);
	var v = playback.LoginPlat(v1);
	if (v != 0) {
		alert("登录失败，请查看日志playback.log");
	} else {
		alert("登录成功");
	}
}
/*视频回放 */
function playBackHK() {
	var Code = document.getElementById("CameraIndexCode").value;
	var CameraName = document.getElementById("CameraName").value;
	var StoreDeviceType = document.getElementById("StoreDeviceType").value;
	var BeginTime = document.getElementById("BeginTime").value;
	var EndTime = document.getElementById("EndTime").value;

	var _param = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
			+ "<PlaybackBasicInfo><CameraIndexCode>"
			+ Code
			+ "</CameraIndexCode>"
			+ "<CameraName>"
			+ CameraName
			+ "</CameraName>"
			+ "<StoreDeviceType>"
			+ StoreDeviceType
			+ "</StoreDeviceType>"
			+ "<BeginTime>"
			+ BeginTime
			+ "</BeginTime>"
			+ "<EndTime>"
			+ EndTime
			+ "</EndTime>"
			+ "<RightCode>10001,10002,10003,10004,10005,10006,10011,10012,10013,10014,10015,10017,10020,10021,10022,10024,10027,10028,10029,10032,10033,10051,10052</RightCode>"
			+ "</PlaybackBasicInfo>";
	//alert(_param);
	playback.SearchAndPlay(_param);
}
//关闭视频
function StopPlay()
{
    playback.StopPlayback();
}
//视频下载
function OnDownload()
{
	var Code = document.getElementById("CameraIndexCode").value;
	var CameraName = document.getElementById("CameraName").value;
	var StoreDeviceType = document.getElementById("StoreDeviceType").value;
	var BeginTime = document.getElementById("BeginTime").value;
	var EndTime = document.getElementById("EndTime").value;

    var _param = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
    + "<PlaybackBasicInfo>"
	+ "<CameraIndexCode>"+Code+"</CameraIndexCode>"
	+ "<CameraName>"+CameraName+"</CameraName>"
    + "<StoreDeviceType>"+StoreDeviceType+"</StoreDeviceType>"
    + "<BeginTime>"+BeginTime+"</BeginTime>"
    + "<EndTime>"+EndTime+"</EndTime>"
    + "<RightCode>10001,10002,10003,10004,10005,10006,10011,10012,10013,10014,10015,10017,10020,10021,10022,10024,10027,10028,10029,10032,10033,10051,10052</RightCode>"
    + "</PlaybackBasicInfo>";
    //alert(_param);
    playback.SearchAndDownload(_param);
}