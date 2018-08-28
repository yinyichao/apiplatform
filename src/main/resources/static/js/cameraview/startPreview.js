/*开始预览 */
function showStartPreview(name) {
	/*try {
		var obj = new ActiveXObject("SinglePreview.IPreviewCtrl.1");*/
		LoginPlat();
		StartPreview();
		/*shoWenzi(name);
	} catch (e) {
		alert("请下载ocx");
		location.href = "/download/ocx/HK/preview/综合安防控件.exe";
	}*/
}
/*显示文字*/
function shoWenzi(name) {
	var _param = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<OSD>"
			+ "<X>0</X>" + "<Y>500</Y>" + "<Strings>" + "<String>" + name
			+ "</String>" + "</Strings>" + "</OSD>";
	preview.SetOSDParam(_param);
}

/*登陆平台 */
function LoginPlat() {
	var IP = document.frmApp.IP.value;
	var port = document.frmApp.port.value;
	var UserName = document.frmApp.UserName.value;
	var Password = document.frmApp.Password.value;
	var v1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><LoginInfo><LoginType>2</LoginType><SynLogin>1</SynLogin><IP>"
			+ IP
			+ "</IP><Port>"
			+ port
			+ "</Port><UserName>"
			+ UserName
			+ "</UserName><Password>" + Password + "</Password></LoginInfo>";
	var v = preview.LoginPlat(v1);
	//alert("登陆成功");
}
/*平台视频预览 */
function StartPreview() {
	//alert("开始预览");
	var Code = document.frmApp.CameraIndexCode.value;
	var _param = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Preview><CamIndexCode>"
			+ Code + "</CamIndexCode></Preview>";
	preview.StartPreview(_param);
}
//关闭视频
function StopPlay()
{
	preview.StopPreview();
}