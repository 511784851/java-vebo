package com.blemobi.weibo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.blemobi.sep.probuf.nano.OssProtos.PUpload;
import com.blemobi.sep.probuf.nano.ResultProtos.PMessage;

/**
 * @author 璧靛媷<andy.zhao@blemobi.com> 甯哥敤鍑芥暟瀹氫箟
 */
public class CommonUtil {
	/**
	 * 鐢熸垚璇锋眰uuid鍜宼oken鍙傛暟
	 * 
	 * @param uuid
	 *            鐢ㄦ埛uuid
	 * @param token
	 *            鐢╰oken
	 * @return List<NameValuePair> uuid鍜宼oken鍙傛暟
	 */
	public static List<NameValuePair> createLoginParams(String username, String password) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		return params;
	}

	/**
	 * 鐢熸垚璇锋眰uuid鍜宼oken鍙傛暟
	 * 
	 * @param uuid
	 *            鐢ㄦ埛uuid
	 * @param token
	 *            鐢╰oken
	 * @return List<NameValuePair> uuid鍜宼oken鍙傛暟
	 */
	public static Cookie[] createLoginCookieParams(String uuid, String token) {
		Cookie[] cookies = new Cookie[2];
		cookies[0] = new Cookie("uuid", uuid);
		cookies[1] = new Cookie("token", token);
		return cookies;
	}

	public static PUpload ss(CommonsMultipartFile multipartFile, Cookie[] cookies)
			throws Exception {

		DiskFileItem fileItem = (DiskFileItem) multipartFile.getFileItem();
		File file = fileItem.getStoreLocation();
		
		String name = fileItem.getName();
		String extension = name.substring(name.lastIndexOf(".")+1);
	
		MessageDigest md = MessageDigest.getInstance("MD5"); 
	    String digest = getDigest(new FileInputStream(file), md, 2048); 

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("digest", digest));// 瑕佷笂浼犵殑鏂囦欢鐨刴d5  hexstring
		params.add(new BasicNameValuePair("length", file.length() + ""));// 鏂囦欢澶у皬锛屽瓧鑺�
		params.add(new BasicNameValuePair("extension", extension));// 鎵╁睍鍚�
		System.out.println("digest---------->" + digest.length());
		String url = ClientUtil.createNewsUrl("/v1/news/uploadfileurl");
		PMessage message = ClientUtil.getMethod(url, params, cookies);
		PUpload upload = PUpload.parseFrom(message.data);
		
		System.out.println("upload---------->" + upload);
		if(upload.exists==false){
			String upurl = upload.url;		
			String[] list = upload.headers;
			byte[] b = getBytesFromFile(file);
			doPut(upurl, b, list);
		}
		
		return upload;
	}

	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)  
            throws NoSuchAlgorithmException,  
            IOException { 
		md.reset();  
		byte[] bytes = new byte[byteArraySize];  
		int numBytes;  
		while ((numBytes = is.read(bytes)) != -1) {  
		md.update(bytes, 0, numBytes);  
		}  
		byte[] digest = md.digest();  
		String result = new String(Hex.encodeHex(digest));  
		return result;  
	}   

	/** 
     * HttpClient PUT璇锋眰 
     * @author huang 
     * @date 2013-4-10 
     * @return 
     */  
	public static void doPut(String urlStr, byte[] paramStr, String[] list) throws Exception{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

		for(String header: list){
			String[] headerArray = header.split(":");
			conn.setRequestProperty(headerArray[0], headerArray[1]);
		}
		
        conn.setRequestMethod("PUT");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();     
        os.write(paramStr);     
        os.close();         
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line ;
//        String result ="";
//        while( (line =br.readLine()) != null ){
//            result += "/n"+line;
//        }
        int code = conn.getResponseCode();
        
        System.out.println("upload code:"+code);
        br.close();
                
    }

	 /**
     * 鏂囦欢杞寲涓哄瓧鑺傛暟缁�
     * @Author Sean.guo
     * @EditTime 2007-8-13 涓婂崍11:45:28
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            for (int n;(n = stream.read(b)) != -1;) {
				out.write(b, 0, n);
			}
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e){
        }
        return null;
    }
    
    
    public static String toUnixDate(long time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd  HH:mm");
		String str = sdf.format(time/1000000L);
        return str;
    }
    
    public static String toSysDate(long time) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    	String str = sdf.format(time/1000000L);
        return str;
    }
    
    public static String toSysDate(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String str = sdf.format(date);
        return str;
    }
}
