package com.lakala.common;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {
	protected final static  Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	/**
	 * http get
	 * 
	 * @param url
	 * @param urlParameters
	 * @return
	 */
	public static String httpGet(String url, List<NameValuePair> urlParameters) {
		try {
			HttpGet getMethod = new HttpGet(url + "?" + urlParameters);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(getMethod);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String respContent = EntityUtils.toString(entity, "utf-8").trim();
				httpClient.getConnectionManager().shutdown();
				return respContent;
			}
			getMethod.releaseConnection();
			return "";

		} catch (Exception e) {
			logger.error("httpGet出错",e);
			e.printStackTrace();
			return null;
		}
	}

	public static String httpPost(String url, List<NameValuePair> urlParameters) {
		try {
			HttpPost postMethod = new HttpPost(url);
			postMethod.setEntity(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(postMethod);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String respContent = EntityUtils.toString(entity, "utf-8").trim();
				httpClient.getConnectionManager().shutdown();
				return respContent;
			}
			postMethod.releaseConnection();
			return "";
		} catch (Exception e) {
			logger.error("httpPost出错",e);
			e.printStackTrace();
			return null;
		}
	}

}
