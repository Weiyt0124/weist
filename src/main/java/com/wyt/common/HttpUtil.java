package com.wyt.common;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
/**
 * @author weiyt
 */
public class HttpUtil {
	private static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; customie8)";

	public static String sendGet(String url, String charset) throws Exception {
		URL realurl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) realurl.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);


		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
		String inputLine;
		StringBuffer result = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			result.append(inputLine);
			result.append("\r\n");
		}
		in.close();
		con.disconnect();
		return result.toString();
	}

	@SuppressWarnings("deprecation")
	public static String sendPost(String url, Map<String, String> param, String charset) throws Exception {
		URL realurl = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) realurl.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		StringBuffer buffer = new StringBuffer();
		if (param != null && !param.isEmpty()) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), charset))
						.append("&");
			}
		}
		buffer.deleteCharAt(buffer.length() - 1);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(buffer.toString());
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
		String inputLine;
		StringBuffer result = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			result.append(inputLine);
			result.append("\r\n");
		}
		in.close();
		con.disconnect();
		return result.toString();
	}
}
