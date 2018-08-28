package com.ip.founder.apiplatform.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static String unicode(String data){
		String resultDate = "";
		try {
			resultDate=new String(data.getBytes("8859_1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultDate;
	}
	//get方式
	public static String getJsonByInternet(String path){
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if(200 == urlConnection.getResponseCode()){
                //得到输入流
                InputStream is =urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 public static void main(String[] args) {
	 String path ="http://10.26.35.66:8080/api/postTerminal";
	 String post = "data:{\"FID\": \"0101\",\"number\": \"0101\",\"name\": \"呼兰监狱\",\"sn\": \"ACB789OXCA\",\"position\": \"一监区\",\"type\": 1 ,\"level\": 0,\"ip\": \"192.168.0.111\",\"mac\": \"00:11:22:33:44:55:78\",\"org_FID\": \"0101\",\"super_FID\": \"010111\",\"model\": \"BL-E3009-KSA\",\"deleted\": 1,\"updateTime\": \"2017-5-5 12:33:12\"}";
	 postDownloadJson(path,post);
}
	//Post方式
	 public static String postDownloadJson(String path,String post){
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            if(post!=null){
            	printWriter.write(post);//post的参数 xx=xx&yy=yy
            }
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while((len=bis.read(arr))!= -1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            return bos.toString("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }  
	 //读取Http流
	 public static String ReadHttp(HttpServletRequest servletRequest){
			String data = "";
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
				String nextLine = bufferedReader.readLine();
				while (nextLine != null) {
					data += nextLine;
					nextLine = bufferedReader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return data;
		}
		
}
