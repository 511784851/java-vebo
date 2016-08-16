package com.blemobi.weibo.global;

/**
 * @author 鏉庡瓙鎵�<davis.lee@blemobi.com>
 * 杩欐槸璺熷墠绔疉pp鐩稿叧鐨勫叏灞�鍙橀噺鍊肩殑宸ュ叿绫伙紝鍓嶇App闇�瑕佺殑鍙傛暟锛岄兘鏄噸杩欓噷鑾峰彇銆�
 * 绯荤粺鍚姩鏃讹紝浠� consul鏈嶅姟鍣ㄨ幏鍙栧埌鐨勮繍琛屽弬鏁帮紝缁熶竴淇濆瓨鍦ㄨ繖閲�.
 * 绯荤粺涓叾浠栫殑瀛愭ā鍧楅渶瑕佺敤鍙傛暟淇℃伅锛岀粺涓�浠庤繖閲岃幏鍙�.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blemobi.weibo.consul.ConsulChangeListener;
import com.google.common.base.Strings;

import lombok.extern.log4j.Log4j;

@Log4j
public class Constant  {
	
	//閫氱敤閿�肩殑key鐨勫０鏄�
	private static final String KEY_WK_SERVER_URL = "wk_server_url";
	private static final String KEY_REDIS_USER_ADDR = "redis_user_addr";
	private static final String KEY_REDIS_USER_AUTH = "redis_user_auth";
	private static final String KEY_HEALTH_CHECK_PORT = "health_check_port";
	private static final String KEY_REDIS_MAX_CONNECT_NUM = "redis_max_connect_num";
	private static final String KEY_JETTY_PORT = "jetty_port";
	
	// 鎴戜滑瀵瑰鏈嶅姟鐨勪笟鍔℃墍鍦ㄧ殑鍖呯洰褰曘�傜洰褰曚箣澶栵紝鏄姝㈣闂�
	private static final String OutServicePermitPackagePath = "com.blemobi.chat.rest"; 
	// Consul瀹氭椂浠诲姟闂撮殧鏃堕棿锛屽崟浣嶏細姣
	private static final long ConsulIntervalTime = 1000 * 30;

	
	private static String[][] accountInfo = null;
	private static String[][] loginInfo = null;
	private static String[][] newsInfo = null;
	private static String[][] ossInfo = null;
	private static String[][] commentInfo = null;
	private static List<String> errorAccount = new ArrayList();
	private static List<String> errorLogin = new ArrayList();
	private static List<String> errorNews = new ArrayList();
	private static List<String> errorOss = new ArrayList();
	private static List<String> errorComment = new ArrayList();
	
	private static String[][] socialInfo = null;
	private static List<String> errorSocial = new ArrayList();
	
	// 瀹氫箟闃块噷鎮熺┖鐨勭郴缁熷鎺ョ殑url鍦板潃
	private static String aliWukongServiceURL = null; 
	// 瀹氫箟鎴戜滑鑷繁鐨凧etty鏈嶅姟鐨勭鍙�
	private static int jettyServerPort = 9006; 

	
	// 瀹氫箟Redis鏈嶅姟鐨処P鍦板潃
	private static String redisServerIP = null; 
	// 瀹氫箟Redis鏈嶅姟鐨勭鍙�
	private static String redisServerPort = null; 
	// 瀹氫箟Redis鏈嶅姟鐨勮璇佷俊鎭�
	private static String redisServerAuth = null; 
	// 鑱婂ぉ鏈嶅姟鍣ㄧ殑鍋ュ悍鍙戠幇鐨勭鍙�
	private static int chatServiceHealthPort = 19006;
	
	// redis杩炴帴姹犵殑鏈�澶ц繛鎺ユ暟
	private static int redisMaxConnectNum = 50;
		
	//鍒涘缓Consul鏈嶅姟鍣ㄧ殑閫傞厤鍣ㄥ璞★紝璇ュ璞¤兘鎺ュ彈浠巆onsul鏈嶅姟鍣ㄤ紶閫掕繃鏉ョ殑閰嶇疆淇℃伅鍙樻洿閫氱煡銆�
	private static ConsulChangeListener adapter = new ConsulChangeListener(){
		
		public void onEnvChange(Map<String, String> prop) {
			
			try{
				aliWukongServiceURL = prop.get(KEY_WK_SERVER_URL);
				redisServerAuth = prop.get(KEY_REDIS_USER_AUTH);
				jettyServerPort  = Integer.parseInt(prop.get(KEY_JETTY_PORT));
				chatServiceHealthPort  = Integer.parseInt(prop.get(KEY_HEALTH_CHECK_PORT));
				
				String redisUserAddr = prop.get(KEY_REDIS_USER_ADDR);; // 瀹氫箟Redis鏈嶅姟鐨処P鍦板潃鍜岀鍙�
				
				redisMaxConnectNum = Integer.parseInt(prop.get(KEY_REDIS_MAX_CONNECT_NUM)); // 瀹氫箟redis杩炴帴姹犵殑鏈�澶ц繛鎺ユ暟
				
				int point = redisUserAddr.indexOf(':');
				if(point < 1 || point >= (redisUserAddr.length()-1)){
					log.info("redis_user_addr format error!");
				}else{
					redisServerIP = redisUserAddr.substring(0, point);
					redisServerPort = redisUserAddr.substring(point+1);
				}
			}catch(RuntimeException e){
				log.info("Get Constant Info error! Info=(Consul Server error or Local Info not find.)");
				log.info("Please check config information.)");
				throw e;
			}
			
			
		}

		public void onServiceChange(String serviceName, String[][] serverInfo) {
			if(serviceName.equals("account")){
				accountInfo = serverInfo;
				errorAccount.clear();
			}
			if(serviceName.equals("login")){
				loginInfo = serverInfo;
				errorLogin.clear();
			}
			if(serviceName.equals("news")){
				newsInfo = serverInfo;
				errorNews.clear();
			}
			if(serviceName.equals("oss")){
				ossInfo = serverInfo;
				errorOss.clear();
			}
			if(serviceName.equals("comment")){
				commentInfo = serverInfo;
				errorComment.clear();
			}
			
			if(serviceName.equals("social")){
				socialInfo = serverInfo;
				errorSocial.clear();
			}
		}
	};
	
	/**
	 * 鑾峰彇璐︽埛鏈嶅姟鐨勬湇鍔″櫒鍒楄〃銆�
	 * 鏁翠釜杩囩▼鏄細鍏堜粠consul涓幏鍙栨墍鏈夌殑璐︽埛鏈嶅姟鍣紝鐒跺悗鍐嶇瓫閫夊仴搴风殑鏈嶅姟鍣紝鍐嶄粠鍋ュ悍涓殢鏈哄彇涓�鍙板仛涓烘湇鍔¤繑鍥炵粰鐢ㄦ埛銆�
	 * @return 杩斿洖鈥滃仴搴锋甯歌处鎴锋湇鍔♀�濈殑鏈嶅姟鍣ㄤ俊鎭�係tring[0]涓篒P鍦板潃锛孲tring[1]涓虹鍙ｃ��
	 */
	public static String[] getAccountServer() {
		String[][] healthAccount = getOnlineServer(accountInfo,errorAccount);
		int ramdom = (int)(Math.random() * healthAccount.length);
		return healthAccount[ramdom];
	}

	public static String[] getLoginServer() {
		String[][] healthLogin = getOnlineServer(loginInfo,errorLogin);
		int ramdom = (int)(Math.random() * healthLogin.length);
		return healthLogin[ramdom];
	}
	public static String[] getNewsServer() {
		String[][] healthNews = getOnlineServer(newsInfo,errorNews);
		int ramdom = (int)(Math.random() * healthNews.length);
		return healthNews[ramdom];
	}
	public static String[] getOssServer() {
		String[][] healthOss = getOnlineServer(ossInfo,errorOss);
		int ramdom = (int)(Math.random() * healthOss.length);
		return healthOss[ramdom];
	}
	public static String[] getCommentServer() {
		String[][] healthComment = getOnlineServer(commentInfo,errorComment);
		int ramdom = (int)(Math.random() * healthComment.length);
		return healthComment[ramdom];
	}
	
	/**
	 * 鑾峰彇濂藉弸鏈嶅姟鐨勬湇鍔″櫒鍒楄〃銆�
	 * 鏁翠釜杩囩▼鏄細鍏堜粠consul涓幏鍙栨墍鏈夌殑濂藉弸鏈嶅姟鍣紝鐒跺悗鍐嶇瓫閫夊仴搴风殑鏈嶅姟鍣紝鍐嶄粠鍋ュ悍涓殢鏈哄彇涓�鍙板仛涓烘湇鍔¤繑鍥炵粰鐢ㄦ埛銆�
	 * @return 杩斿洖鈥滃仴搴锋甯稿ソ鍙嬫湇鍔♀�濈殑鏈嶅姟鍣ㄤ俊鎭�係tring[0]涓篒P鍦板潃锛孲tring[1]涓虹鍙ｃ��
	 */
	public static String[] getSocialServer() {
		String[][] healthSocial = getOnlineServer(socialInfo,errorSocial);
		int ramdom = (int)(Math.random() * healthSocial.length);
		return healthSocial[ramdom];
//		return new String[]{"192.168.1.241","9008"};// 濂藉弸绯荤粺IP鍜宲ort淇℃伅
	}
	
	/**
	 * 浠巆onsul鏈嶅姟鍣ㄨ幏鍙朢edis鏈嶅姟鍣ㄧ殑淇℃伅銆�
	 * @param host 鏄痗onsul鏈嶅姟鍣ㄧ殑鍩熷悕鎴朓P鍦板潃銆�
	 * @return 杩斿洖Redis鏈嶅姟鍣ㄧ殑淇℃伅銆係tring[0]涓篒P鍦板潃锛孲tring[1]涓虹鍙ｃ��
	 */
	public static String[] getRedisServer() {
		return new String[]{redisServerIP,redisServerPort};
	}
	
	/**
	 * 浠巆onsul鏈嶅姟鍣ㄨ幏鍙朢edis鏈嶅姟鍣ㄧ殑淇℃伅銆�
	 * @return 杩斿洖Redis鏈嶅姟鍣ㄧ殑鐨凾oken璁よ瘉淇℃伅銆�
	 */
	public static String getRedisUserAuth() {
		return redisServerAuth;// Redis鏈嶅姟鍣ㄧ殑璁よ瘉
	}
	
	/**
	 * 鎶ュ憡鏈夊紓甯哥殑璐︽埛鏈嶅姟鍣紝璇ユ湇鍔″櫒灏嗚褰曞湪寮傚父鏈嶅姟鍣ㄥ垪琛ㄤ腑锛屽綋鐢ㄦ埛鑾峰彇璐︽埛鏈嶅姟鍣ㄦ椂锛屽垯杩囨护鎺夎鏈嶅姟鍣ㄣ��
	 * 璁板綍鏈嶅姟鍣ㄧ殑淇℃伅涓�(ip+port)鐨勬柟寮忔潵浠ｈ〃涓�鍙版湇鍔″櫒銆�
	 * @param addr 鏈夊紓甯哥殑璐︽埛鏈嶅姟鍣ㄧ殑IP鍦板潃銆�
	 * @param port 鏈夊紓甯哥殑璐︽埛鏈嶅姟鍣ㄧ殑Port绔彛銆�
	 */
	public static void reportErrorAccountServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorAccount.add(addr+"-"+port);
		}
	}

	public static void reportErrorLoginServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorLogin.add(addr+"-"+port);
		}
	}

	public static void reportErrorNewsServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorNews.add(addr+"-"+port);
		}
	}

	public static void reportErrorOssServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorOss.add(addr+"-"+port);
		}
	}

	public static void reportErrorCommentServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorComment.add(addr+"-"+port);
		}
	}
	
	/**
	 * 鎶ュ憡鏈夊紓甯哥殑濂藉弸鏈嶅姟鍣紝璇ユ湇鍔″櫒灏嗚褰曞湪寮傚父鏈嶅姟鍣ㄥ垪琛ㄤ腑锛屽綋鐢ㄦ埛鑾峰彇濂藉弸鏈嶅姟鍣ㄦ椂锛屽垯杩囨护鎺夎鏈嶅姟鍣ㄣ��
	 * 璁板綍鏈嶅姟鍣ㄧ殑淇℃伅涓�(ip+port)鐨勬柟寮忔潵浠ｈ〃涓�鍙版湇鍔″櫒銆�
	 * @param addr 鏈夊紓甯哥殑濂藉弸鏈嶅姟鍣ㄧ殑IP鍦板潃銆�
	 * @param port 鏈夊紓甯哥殑濂藉弸鏈嶅姟鍣ㄧ殑Port绔彛銆�
	 */
	public static void reportErrorFriendServer(String addr,int port) {
		//濡傛灉鏄郴缁熶笉鏄痩ocal妯″紡锛屾墠瀵瑰紓甯告湇鍔″櫒鍒楄〃杩囨护銆�
		//鍥犳鏈湴妯″紡锛岃鏈嶅姟鍣ㄥ彧鏈変竴鍙帮紝濡傛灉鎻愮ず鏈夋湇鍔″櫒寮傚父锛岃鏈嶅姟鍣ㄥ氨娌℃湁鏈嶅姟鍣ㄤ簡锛�
		if(!Strings.isNullOrEmpty(System.getProperty("EnvMode", ""))){
			errorSocial.add(addr+"-"+port);
		}
	}

	/**
	 * 浠庢煇涓湇鍔＄殑consul鏈嶅姟鍣ㄥ垪琛ㄤ腑锛岃繃鏉ュ嚭鏉ュ仴搴风殑鏈嶅姟鍣ㄥ垪琛ㄣ��
	 * @param all 鏌愪釜鏈嶅姟鐨勬墍鏈夋湇鍔″櫒锛屼篃灏辨槸浠巆onsul鏈嶅姟鍣ㄤ腑鑾峰彇鍒拌鏈嶅姟鐨勬墍鏈夋湇鍔″櫒銆�
	 * @param error 鏌愪釜鏈嶅姟鐨勬墍鏈夋湇鍔″櫒涓紝鏈夊紓甯哥殑鏈嶅姟鍣ㄧ被鍒��
	 * @return 杩斿洖鏌愪釜鏈嶅姟鐨勫仴搴风殑鏈嶅姟鍣ㄧ殑鍒楄〃锛屼篃灏辨槸鈥渁ll闆嗗悎鈥濅腑鎺掗櫎鎺夆�渆rror闆嗗悎鈥濆悗 锛屽墿浣欑殑缁撴灉銆係tring[i][0]涓篒P鍦板潃锛孲tring[i][1]涓虹鍙ｃ��
	 */
	private static String[][] getOnlineServer(String[][] all, List<String> error) {
		int count = all.length;
		String[][] buff = new String[count][2];
		int index = 0;
		
		String[] errorList = new String[error.size()];
		error.toArray(errorList);
		
		for(int i=0;i<count;i++){
			String v = buff[i][0] + "-" + buff[i][1];
			if(!isExist(errorList,v)){
				buff[index][0] = all[i][0];
				buff[index][1] = all[i][1];
				index++;
			}
		}

		String[][] rtn = new String[index][2];
		System.arraycopy(buff, 0, rtn, 0, rtn.length);
		
		return rtn;
	}

	/**
	 * 鍒ゆ柇闆嗗悎閲屾槸鍚﹀瓨鍦ㄦ煇涓厓绱犮��
	 * @param errorList 澶ч泦鍚堛��
	 * @param v 寰呮煡鎵惧瓨鍦ㄧ殑瀛楃涓插璞°��
	 * @return 濡傛灉瀛樺湪锛屽垯杩斿洖true銆�
	 */
	private static boolean isExist(String[] errorList, String v) {
		for(int i=0;i<errorList.length;i++){
			if(errorList[i].equals(v)){
				return true;
			}
		}
		return false;
	}


	/**
	 * 鑾峰彇jesry瀹瑰櫒涓厑璁歌闂殑鍖呭悕鐩綍銆傞粯璁ゅ�兼槸com.blemobi.chat.rest銆�
	 * @return 鎺堟潈璁块棶鐨勫寘鍚嶃��
	 */
	public static String getOutservicepermitpackagepath() {
		return OutServicePermitPackagePath;
	}


	/**
	 * 鑾峰彇璁块棶闃块噷鎮熺┖鐨刄RL鍦板潃銆�
	 * @return 鎮熺┖鐨刄RL鍦板潃銆�
	 */
	public static String getAliWukongServiceURL() {
		return aliWukongServiceURL;
	}

	/**
	 * 鑾峰彇璁块棶鑱婂ぉ绯荤粺鐨勬湇鍔＄鍙ｏ紝榛樿鍊兼槸9006銆�
	 * @return 鏈嶅姟绔彛銆�
	 */
	public static int getJettyServerPort() {
		return jettyServerPort;
	}

	/**
	 * 鑾峰彇璁块棶consul鏈嶅姟鍣ㄧ殑闂撮殧鏃堕棿銆�
	 * @return 闂撮殧鏃堕棿锛屽崟浣嶆槸姣鐨勫�笺��
	 */
	public static long getConsulIntervaltime() {
		return ConsulIntervalTime;
	}
	
	/**
	 * 鑾峰彇璁块棶redis鏈�澶ц繛鎺ユ暟銆�
	 * @return redis鏈�澶ц繛鎺ユ暟銆�
	 */
	public static int getRedisMaxConnectNum() {
		return redisMaxConnectNum;
	}


	/**
	 * 鑾峰彇娉ㄥ唽鐨勯�傞厤鍣紝褰撻�傞厤鍣ㄦ敞鍐岀櫥璁板埌ConsulManager绠＄悊绫诲悗锛屽垯鍙互鏀跺埌Consul鐨勯厤缃俊鎭彉鏇寸殑閫氱煡銆�
	 * @return 閫傞厤鍣ㄥ璞°��
	 */
	public static ConsulChangeListener getAdapter() {
		return adapter;
	}
	
	/**
	 * 鑾峰彇鑱婂ぉ鏈嶅姟鍣ㄧ殑鍋ュ悍鍙戠幇鐨勭鍙ｃ��
	 * @return 缃戠粶Socket绔彛鍊笺��
	 */
	public static int getChatServiceHealthPort() {
		return chatServiceHealthPort ;
	}
}
