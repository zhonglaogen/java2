/*
 * $Header: WebUtils.java
 * $Revision: 1.0.0.0 
 * $CreateDate: 2013-3-19
 * $ModifyDate: 2013-3-19 下午02:31:06
 * $Owner: 
 *
 * Copyright (c) 2011-2012 Shanghai Reign Co. Ltd.
 * All Right Reserved.
 */
package hario.pracitce;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

;


/**
 * WebUtils
 * @author   wangys
 * @version  1.0.0.0  2013-3-19 下午02:31:06
 */
public class WebUtils {
	/** opReport */


	/**
	 * 获取参数
	 * @param paramMap 参数Map
	 * @return
	 * 2010-6-11 下午05:37:09
	 * @throws UnsupportedEncodingException
	 */
	public static String getURL(String url, Map<String, Object> paramMap) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		builder.append(url);
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		int index = 0;
		for(Entry<String, Object> entry : entrySet) {
			if (index != 0) {
				builder.append("&");
			} else {
				builder.append("?");
			}
			builder.append(entry.getKey())
				   .append("=")
				   .append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
			index++;
		}
		return builder.toString();
	}

	/**
	 * 获取URI编码方式
	 * @param
	 * @return
	 * 2010-6-11 下午05:37:09
	 * @throws UnsupportedEncodingException
	 */
	public static String getParam(Map<String, Object> paramMap) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		Set<Entry<String, Object>> entrySet = paramMap.entrySet();
		int index = 0;
		for(Entry<String, Object> entry : entrySet) {
			if (index != 0) {
				builder.append("&");
			}
			builder.append(entry.getKey())
				   .append("=")
				   .append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
			index++;
		}
		return builder.toString();
	}

	/**
	 * 发送请求到指定URL，GET方式
	 * @param requestURL
	 * @param paramMap
	 * @return
	 * 2010-6-11 下午05:36:54
	 */
	public static String sendSSLGetRequest(String requestURL, Map<String, Object> paramMap, int timeout) {
		URL url = null;
		HttpsURLConnection connection = null;
		BufferedInputStream bis = null;		
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
			requestURL = getURL(requestURL, paramMap);
			url = new URL(requestURL);
			
			connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(false);
			// 设置连接超时时间: 60s
			connection.setConnectTimeout(timeout); 
			// 设置读取超时时间: 60s
		    connection.setReadTimeout(timeout); 
			connection.connect();

			int code = connection.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(connection.getInputStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				return builder.toString();
			} else if (code == HttpsURLConnection.HTTP_BAD_REQUEST) {
				bis = new BufferedInputStream(connection.getErrorStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				return builder.toString();
			} else {
				return "";
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public static String sendSSLGetRequestReader(String requestURL, Map<String, Object> paramMap, int timeout) {
		URL url = null;
		HttpsURLConnection connection = null;
		BufferedInputStream bis = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
			requestURL = getURL(requestURL, paramMap);
			url = new URL(requestURL);

			connection = (HttpsURLConnection) url.openConnection();
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(false);
			// 设置连接超时时间: 60s
			connection.setConnectTimeout(timeout);
			// 设置读取超时时间: 60s
			connection.setReadTimeout(timeout);
			connection.connect();

			int code = connection.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				StringBuilder sb = new StringBuilder();
				BufferedReader bb = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line = null;
				while ((line = bb.readLine()) != null) {
					String[] split = line.split("\\||\\||");
					sb.append(split[0]).append(",");
				}

				return sb.toString();
			} else if (code == HttpsURLConnection.HTTP_BAD_REQUEST) {
				bis = new BufferedInputStream(connection.getErrorStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				return builder.toString();
			} else {
				return "";
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}



	/**
	 * SSL验证器
	 * @author wangys
	 */
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * SSL验证器
	 * @author wangys
	 */
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	/**
	 * MyX509TrustManager
	 * @author   wangys
	 * @version  1.0.0.0  2016年1月11日 下午2:38:59
	 */
	static class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
