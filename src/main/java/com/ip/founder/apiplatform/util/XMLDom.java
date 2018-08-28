package com.ip.founder.apiplatform.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLDom {
	public static String fittingXML(String data) throws Exception{
		//33554446 延迟
		Map<String,String> map = parse(data);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element Preview = doc.createElement("Preview");
		doc.appendChild(Preview);
		Element StreamSvr = doc.createElement("StreamSvr");
		Preview.appendChild(StreamSvr);
		
		Element Svr = doc.createElement("Svr");
		Svr.setAttribute("type", map.get("vag_type"));
		Svr.setAttribute("ip", map.get("vag_ip"));
		Svr.setAttribute("port", map.get("vag_port"));
		StreamSvr.appendChild(Svr);
		
		/*if (map.get("stream_ip") != null&& !"".equals(map.get("stream_ip"))) {
			serviceNode = e.addElement("Svr");
			serviceNode.addAttribute("type", map.get("stream_type"));
			serviceNode.addAttribute("ip", map.get("stream_ip"));
			serviceNode.addAttribute("port", map.get("stream_port"));
		}*/
		Element CamIndexCode = doc.createElement("CamIndexCode");	
		CamIndexCode.setTextContent(map.get("indexcode"));
		Preview.appendChild(CamIndexCode);
		
		Element CamName = doc.createElement("CamName");	
		CamName.setTextContent(map.get("name"));
		Preview.appendChild(CamName);
		
		Element DevIndexCode = doc.createElement("DevIndexCode");	
		DevIndexCode.setTextContent(map.get("devindexcode"));
		Preview.appendChild(DevIndexCode);
		
		Element LinkType = doc.createElement("LinkType");	
		LinkType.setTextContent(map.get("linktype"));
		Preview.appendChild(LinkType);
		
		Element StreamType = doc.createElement("StreamType");	
		StreamType.setTextContent(map.get("streamtype"));
		Preview.appendChild(StreamType);
		
		Element ChannelNo = doc.createElement("ChannelNo");	
		ChannelNo.setTextContent(map.get("channelno"));
		Preview.appendChild(ChannelNo);
		
		Element NetZone = doc.createElement("NetZone");	
		NetZone.setAttribute("cnid", map.get("cnid"));
		NetZone.setAttribute("pnid", map.get("pnid"));
		Preview.appendChild(NetZone);
		
		Element UserLevel = doc.createElement("UserLevel");	
		UserLevel.setTextContent(map.get("userlevel"));
		Preview.appendChild(UserLevel);
		
		Element DevType = doc.createElement("DevType");	
		DevType.setTextContent(map.get("devtype"));
		Preview.appendChild(DevType);
		
		Element ProSeries = doc.createElement("ProSeries");	
		ProSeries.setTextContent(map.get("proseries"));
		Preview.appendChild(ProSeries);
		
		Element TreatyType = doc.createElement("TreatyType");	
		TreatyType.setTextContent(map.get("treatytype"));
		Preview.appendChild(TreatyType);
		
		Element UserName = doc.createElement("UserName");	
		UserName.setTextContent(map.get("username"));
		Preview.appendChild(UserName);

		Element PassWord = doc.createElement("PassWord");	
		PassWord.setTextContent(map.get("password"));
		Preview.appendChild(PassWord);
		
		Element FishEyeInfo = doc.createElement("FishEyeInfo");	
		Preview.appendChild(FishEyeInfo);
		
		Element InstallType = doc.createElement("InstallType");	
		InstallType.setTextContent(map.get("installtype"));
		FishEyeInfo.appendChild(InstallType);
		
		Element VideoDevType = doc.createElement("VideoDevType");	
		VideoDevType.setTextContent(map.get("videodevtype"));
		FishEyeInfo.appendChild(VideoDevType);

		/*String playType = StringUtils.trim(map.get("playtype"));
		String substream = StringUtils.trim(map.get("substream"));*/

		/*String videoType = map.get("videodevtype");
		if (StringUtils.isNotEmpty(intelCode) && !"18".equals(videoType)) {
			e = root.addElement("IntellInfo");
			Element indexcode = e.addElement("IndexCode");
			indexcode.addText(intelCode);
			Element channelNo = e.addElement("ChannelNo");
			channelNo.addText(StringUtils.defaultString(intelChannelNo));
			Element domainId = e.addElement("Domaind");
			domainId.addText(StringUtils.defaultString(map.get("intelldomaind")));
		Element userName = e.addElement("UserName");
		userName.addText(StringUtils.defaultString(map.get("intellusername")));
		Element password = e.addElement("PassWord");
			password.addText(StringUtils.defaultString(map.get("intellpassword")));
		}*/
		return DTS(doc);
	}
	public static Map<String,String> parse(String xml) throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document doc = builder.parse(is);
		NodeList list = doc.getElementsByTagName("row");
		Element e = (Element) list.item(0);
		resultMap.put("vag_type", e.getAttribute("vag_type"));
		resultMap.put("vag_ip", e.getAttribute("vag_ip"));
		resultMap.put("vag_port", e.getAttribute("vag_port"));
		resultMap.put("indexcode", e.getAttribute("indexcode"));
		resultMap.put("devindexcode", e.getAttribute("devindexcode"));
		resultMap.put("linktype", e.getAttribute("linktype"));
		resultMap.put("name", e.getAttribute("name"));
		resultMap.put("streamtype", e.getAttribute("streamtype"));
		resultMap.put("channelno", e.getAttribute("channelno"));
		resultMap.put("cnid", e.getAttribute("cnid"));
		resultMap.put("pnid", e.getAttribute("pnid"));
		resultMap.put("userlevel", e.getAttribute("userlevel"));
		resultMap.put("devtype", e.getAttribute("devtype"));
		resultMap.put("proseries", e.getAttribute("proseries"));
		resultMap.put("treatytype", e.getAttribute("treatytype"));
		resultMap.put("username", e.getAttribute("username"));
		resultMap.put("password", e.getAttribute("password"));
		resultMap.put("installtype", e.getAttribute("installtype"));
		resultMap.put("videodevtype", e.getAttribute("videodevtype"));
		return resultMap;
	}
	public static String DTS(Document doc) throws Exception{
		Source source = new DOMSource(doc);  
        StringWriter stringWriter = new StringWriter();  
        Result result = new StreamResult(stringWriter);  
        TransformerFactory factory = TransformerFactory.newInstance();  
        Transformer transformer = factory.newTransformer();  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
        transformer.transform(source, result);  
        return stringWriter.getBuffer().toString();
	}
}
