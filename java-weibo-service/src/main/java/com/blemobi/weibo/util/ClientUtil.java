package com.blemobi.weibo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.blemobi.sep.probuf.nano.ResultProtos;
import com.blemobi.sep.probuf.nano.ResultProtos.PMessage;
import com.blemobi.weibo.global.Constant;


/**
 * @author 闁荤姍鍥ㄦ锭濠殿垽鎷�<andy.zhao@blemobi.com> 闁哄鏅滅划搴ㄥ煝閼测晜瀚柛鎰典簼閺嗗繒绱掗顒佸
 */
public class ClientUtil {
//
	private static String loginAddress = "192.168.1.245";
	private static int loginPort = 9001;

	private static String newsAddress = "192.168.1.245";
	private static int newsPort = 9004;

	private static String ossAddress = "192.168.1.245";
	private static int ossPort = 9003;

	private static String commentAddress = "192.168.1.245";
	private static int commentPort = 9005;
//
//	
//	private static String loginAddress = "10.117.227.37";
//	private static int loginPort = 9001;
//
//	private static String newsAddress = "10.117.233.79";
//	private static int newsPort = 9004;
//
//	private static String ossAddress = "10.117.234.41";
//	private static int ossPort = 9003;
//
//	private static String commentAddress = "10.117.230.157";
//	private static int commentPort = 9005;
	/**
	 * post闂佸搫鍊婚幊鎾舵閿涘嫭瀚柛鎰典簼閺嗭拷
	 * 
	 * @param url
	 *            闁荤姳璀﹂崹鎵閿燂拷
	 * @param params
	 *            闂佸憡鐟ラ崐褰掑汲閻斿摜鈹嶉柍鈺佸暕缁憋拷
	 * @return PMessage PMessage闁诲海鏁搁、濠囨寘閿燂拷
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static PMessage postMethod(String url, List<NameValuePair> params, Cookie[] cookies)
			throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);

		if (params != null) {
			// 闁荤姳绀佹晶浠嬫偪閸℃稑鐭楅柛灞剧♁濞堬拷
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(entity);
		}

		return clientExecute(httpPost, cookies);
	}

	public static PMessage post2Method(String url, String body, Cookie[] cookies)
			throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);

		if (body != null) {
			 StringBody stringBody = new StringBody(body);  
	        MultipartEntity entity = new MultipartEntity();    
	        entity.addPart("body", stringBody);  
	        httpPost.setEntity(entity);  
		}

		return clientExecute(httpPost, cookies);
	}
	
	/**
	 * get闂佸搫鍊婚幊鎾舵閿涘嫭瀚柛鎰典簼閺嗭拷
	 * 
	 * @param url
	 *            闁荤姳璀﹂崹鎵閿燂拷
	 * @param params
	 *            闂佸憡鐟ラ崐褰掑汲閻斿摜鈹嶉柍鈺佸暕缁憋拷
	 * @return PMessage PMessage闁诲海鏁搁、濠囨寘閿燂拷
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static PMessage getMethod(String url, List<NameValuePair> params, Cookie[] cookies)
			throws ClientProtocolException, IOException {
		url = resetGetUrl(url, params);
		HttpGet httpGet = new HttpGet(url);

		return clientExecute(httpGet, cookies);
	}

	public static PMessage deleteMethod(String url, List<NameValuePair> params, Cookie[] cookies)
			throws ClientProtocolException, IOException {
		HttpDelete httpDelete = new HttpDelete(url);
	
		return clientExecute(httpDelete, cookies);
	}
	/**
	 * 闁荤姴顑呴崯浼村极閿燂拷
	 * 
	 * @param httpRequestBase
	 *            http闁荤姴娲弨閬嶆儑閻楀牏鈹嶉柍鈺佸暕缁憋拷
	 * @return PMessage PMessage闁诲海鏁搁、濠囨寘閿燂拷
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private static PMessage clientExecute(HttpRequestBase httpRequestBase, Cookie[] cookies)
			throws ClientProtocolException, IOException {
		if (cookies != null) {
			StringBuilder sb = new StringBuilder();
			for (Cookie ck : cookies) {
				sb.append(ck.getName()).append('=').append(ck.getValue()).append(";");
			}
			httpRequestBase.setHeader("Cookie", sb.toString());
		}

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse response = client.execute(httpRequestBase);

		HttpEntity entity = response.getEntity();
		byte[] data = EntityUtils.toByteArray(entity);

		ResultProtos.PMessage message = ResultProtos.PMessage.parseFrom(data);

		return message;
	}

	/**
	 * 闂佹眹鍨婚崰鎰板垂濞嗙珢t闁荤姴娲弨閬嶆儑娴兼潙妫橀柣鐔稿绾拷闁诲海鎳撻張顒勫汲椤ょ备l
	 * 
	 * @param path
	 *            闁荤姳璀﹂崹鎵閿燂拷
	 * @param params
	 *            闂佸憡鐟ラ崐褰掑汲閻斿摜鈹嶉柍鈺佸暕缁憋拷
	 * @return String 闂佸搫鐗為幏椋庣磽娴ｅ摜澧㈡繛鍫熷敼rl
	 */
	private static String resetGetUrl(String path, List<NameValuePair> params) {
		StringBuffer url = new StringBuffer(path);
		if (params != null) {
			url.append("?");
			boolean bool = false;
			for (NameValuePair nvp : params) {
				if(bool){
					url.append("&");
				} else {
					bool = true;
				}
				
				url.append(nvp.getName());
				url.append("=");
				url.append(nvp.getValue());
			}
		}
		return url.toString();
	}

//	public static  String createLoginUrl(String basePath) {
//
//		return createUrl(loginAddress, loginPort, basePath);
//	}
//	
//	public static  String createNewsUrl(String basePath) {
//
//		return createUrl(newsAddress, newsPort, basePath);
//	}
//	
//	public static  String createOssUrl(String basePath) {
//
//		return createUrl(ossAddress, ossPort, basePath);
//	}
//	
//	public static  String createCommentUrl(String basePath) {
//
//		return createUrl(commentAddress, commentPort, basePath);
//	}
	
	
	public static  String createLoginUrl(String basePath) {
		String[] loginInfo = Constant.getLoginServer();
		String address = loginInfo[0];
		int port = Integer.parseInt(loginInfo[1]);

		return createUrl(address, port, basePath);
	}
	
	public static  String createNewsUrl(String basePath) {
		String[] newsInfo = Constant.getNewsServer();
		String address = newsInfo[0];
		int port = Integer.parseInt(newsInfo[1]);

		return createUrl(address, port, basePath);
	}
	
	public static  String createOssUrl(String basePath) {
		String[] ossInfo = Constant.getOssServer();
		String address = ossInfo[0];
		int port = Integer.parseInt(ossInfo[1]);

		return createUrl(address, port, basePath);
	}
	
	public static  String createCommentUrl(String basePath) {
		String[] commentInfo = Constant.getCommentServer();
		String address = commentInfo[0];
		int port = Integer.parseInt(commentInfo[1]);

		return createUrl(address, port, basePath);
	}
	
	/**
	 * 闂佹眹鍨婚崰鎰板垂濮樿泛瀚夌�广儱鎳庨～銈夋煟閵娿儱鐎禦L
	 * 
	 * @param address
	 *            IP闂侀潻闄勫妯侯焽閿燂拷
	 * @param port
	 *            IP缂備焦妫忛崹鎷屻亹閿燂拷
	 * @param basePath
	 *            闁荤姍鍐仾缂侇煈鍠氶幑鍕敍濮樿京鐛�
	 * @return String url闁荤姳璀﹂崹鎵閿燂拷
	 */
	public static String createUrl(String address, int port, String basePath) {
		StringBuffer url = new StringBuffer("http://");
		url.append(address);
		url.append(":");
		url.append(port);
		url.append(basePath);

		return url.toString();
	}

	// 闁告梻鍠曢崗锟�: postBody鐟滆埇鍨圭槐锟犲矗閹达讣鎷锋担瑙勬闁圭櫢鎷�
	// @param urlPath 閻庝絻顫夐弻鐔煎捶閺夋寧绲�
	// @param json 閻熸洑妞掔槐鍫曟焻娴ｇ儤鐣遍柡浣哄瀹擄拷
	// @return
	// @throws Exception
	public static PMessage postBodyMethod(String urlPath, byte[] body, Cookie[] cookies) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		// 閻犱礁澧介悿鍝緊Output閻忕偟鍋為敓绐栧倽绀媡rue閻炴稏鍔庨妵姘变焊閸℃洖鈻忛柣鈧妽椤掓几rlConnection闁告劖鐟ラ崣鍡涘极閻楀牆绁�

		urlConnection.setDoInput(true);  
		urlConnection.setDoOutput(true);  
		urlConnection.setUseCaches(false);  

		// 閻庤鐭粻鐔奉嚗閸涱厼鏅搁柛蹇嬪劜閺嗙喖骞戦鐐暠闁告劕鎳庨鎰尵鐠囪尙锟界兘鏁嶇仦鎯х亯濞寸媭鍓濋鏇犵磾椤旀槒绀媋pplication/x-www-form-urlencoded缂侇偉顕ч悗锟�

		urlConnection.setRequestProperty("Content-type", "form-data");
		urlConnection.setRequestProperty("accept", "application/x-protobuf");
		urlConnection.setRequestMethod("POST"); 
		
		if (cookies != null) {
			StringBuilder sb = new StringBuilder();
			for (Cookie ck : cookies) {
				sb.append(ck.getName()).append('=').append(ck.getValue()).append(";");
			}
			urlConnection.setRequestProperty("Cookie", sb.toString());
		}
		//urlConnection.connect();
		// 鐎电増顨呴崺宀�鎷犻柨瀣勾闁汇劌瀚欢顓㈠礄閻戞銈﹂悗鐢殿攰閽栵拷
		OutputStream out = urlConnection.getOutputStream();
		// 闁硅泛锕ラ弳鐔煎箲椤旂厧鏅搁柛蹇嬪劥椤曨剙效閸屾粍鐣盉ody
		out.write(body);
		out.flush();
		out.close();
		
		// 濞寸姴瀛╁﹢鍥礉閳ヨ櫕鐝ら悹鍥嚙瑜板洭宕鍛畨
		InputStream inputStream = urlConnection.getInputStream();
		String encoding = urlConnection.getContentEncoding();
		String bodyString = IOUtils.toString(inputStream, encoding);

		ResultProtos.PMessage message = ResultProtos.PMessage.parseFrom(bodyString.getBytes());
		return message;
	}
}
