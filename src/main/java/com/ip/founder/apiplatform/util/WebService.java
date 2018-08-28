package com.ip.founder.apiplatform.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WebService {
	public static String service(String url,String method,String... args){
		String resultData = null;
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory
				.newInstance();
		Client client = clientFactory.createClient(url);
		Object[] result = null;
		try {
			result = client.invoke(method, args);
			resultData = (String) result[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}
	public static String service2(String url,String method,String args,String hk,String vmcc){
		String resultData = null;
		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory
				.newInstance();
		Client client = clientFactory.createClient(url);
		Object[] result = null;
		try {
			result = client.invoke(method, 1,args,"","",1L,14,hk,vmcc);
			resultData = (String) result[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultData;
	}
}
