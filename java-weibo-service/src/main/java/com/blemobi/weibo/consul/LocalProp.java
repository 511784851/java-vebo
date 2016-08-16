package com.blemobi.weibo.consul;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import lombok.extern.log4j.Log4j;
@Log4j
public class LocalProp {
	private static final String[] Propkey = new String[]{
			"wk_server_domain",
			"wk_server_appToken",
			"wk_ios_appKey",
			"wk_ios_appSecret",
			"wk_android_appKey",
			"wk_android_appSecret",
			"wk_server_url",
			"health_check_port",
			"redis_user_addr",
			"redis_user_auth",
			"redis_max_connect_num",
			"jetty_port"
	};
	private static String[] account	= null;
	private static String[] login	= null;
	private static String[] news	= null;
	private static String[] oss	= null;
	private static String[] comment	= null;
	private static String[] social 	= null;
	
	private static HashMap<String,String> propInfo = new HashMap<String,String>();
	
	public static void invokeEnv(ConsulChangeListener adapter) {
		
		adapter.onServiceChange("account", new String[][]{account});
		adapter.onServiceChange("login", new String[][]{login});
		adapter.onServiceChange("news", new String[][]{news});
		adapter.onServiceChange("oss", new String[][]{oss});
		adapter.onServiceChange("comment", new String[][]{comment});
		adapter.onServiceChange("social", new String[][]{social});
		
		adapter.onEnvChange(propInfo);
	}

	public static void setLocalEnv(String filePath) throws IOException {

		String path = System.getProperty("user.dir")+File.separator+filePath;
		InputStream in =new FileInputStream(path);

		Properties fileProp = new Properties();
		fileProp.load(in);
		
		log.info("--- Start listing properties ---");
		for(String key:Propkey){
			propInfo.put(key, ""+fileProp.getProperty(key));
			log.info(key+" = ["+fileProp.getProperty(key)+"]");
		}
		
		account = new String[]{(String) fileProp.get("account_addr"),(String) fileProp.get("account_port")};
		login = new String[]{(String) fileProp.get("login_addr"),(String) fileProp.get("login_port")};
		news = new String[]{(String) fileProp.get("news_addr"),(String) fileProp.get("news_port")};
		oss = new String[]{(String) fileProp.get("oss_addr"),(String) fileProp.get("oss_port")};
		comment = new String[]{(String) fileProp.get("comment_addr"),(String) fileProp.get("comment_port")};
		social = new String[]{(String) fileProp.get("social_addr"),(String) fileProp.get("social_port")};
		
	}

}
