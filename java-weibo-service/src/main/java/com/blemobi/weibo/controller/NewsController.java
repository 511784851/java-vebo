package com.blemobi.weibo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.blemobi.sep.probuf.nano.CommentProtos.PComment;
import com.blemobi.sep.probuf.nano.CommentProtos.PCommentLevel;
import com.blemobi.sep.probuf.nano.NewsProtos.PAudio;
import com.blemobi.sep.probuf.nano.NewsProtos.PImage;
import com.blemobi.sep.probuf.nano.NewsProtos.PPostInfo;
import com.blemobi.sep.probuf.nano.NewsProtos.PPostInfoList;
import com.blemobi.sep.probuf.nano.NewsProtos.PVideo;
import com.blemobi.sep.probuf.nano.OssProtos.PUpload;
import com.blemobi.sep.probuf.nano.ResultProtos.PMessage;
import com.blemobi.sep.probuf.nano.ResultProtos.PResult;
import com.blemobi.weibo.util.ClientUtil;
import com.blemobi.weibo.util.CommonUtil;

@Controller
public class NewsController {

	@Autowired
	private HttpServletRequest request;

	public static Map<String, PPostInfo> articleMap = new HashMap<String, PPostInfo>();
	
	@RequestMapping(value = "/publish.do", method = RequestMethod.POST)
	public String publish(@RequestParam String content, @RequestParam String srcType
			, @CookieValue String uuid, @CookieValue String token
			, @RequestParam("imageFile") CommonsMultipartFile[] imageFiles
			, @RequestParam("videoFile") CommonsMultipartFile videoFile
			, @RequestParam("audioFile") CommonsMultipartFile audioFile
			) {
		try{
			System.out.println("content: " + content);
	
			Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

			PPostInfo article = new PPostInfo();
			article.content = content;
			article.srcType = srcType;
	
			if ("image".equals(srcType)) {
				PImage[] imageArray = new PImage[9];
				int i=0;
				for (CommonsMultipartFile file : imageFiles) {
					if (!file.isEmpty()) {				
						PUpload upload = CommonUtil.ss(file, cookies);
						String url = upload.objectKey;	
						
						PImage image = new PImage();
						image.url = url;
						imageArray[i] = image;
						i++;
					}
				}
				article.images = imageArray;
			} else if ("video".equals(srcType)) {
				if (!videoFile.isEmpty()) {
					PUpload upload = CommonUtil.ss(videoFile, cookies);
					String url = upload.objectKey;
					
					PVideo video = new PVideo();
					video.url=url;
					video.img=url;
	
					article.video = video;
				}
			} else if ("audio".equals(srcType)) {
				if (!audioFile.isEmpty()) {
					PUpload upload = CommonUtil.ss(audioFile, cookies);
					String url = upload.objectKey;
					
					PAudio audio = new PAudio();
					audio.url=url;
	
					article.audio = audio;
				}
			}
			 
			String url = ClientUtil.createNewsUrl("/v1/news/post");

			byte[] body = article.toByteArray(article);
			PMessage message = ClientUtil.postBodyMethod(url, body, cookies);

			PResult result = PResult.parseFrom(message.data);
			if (result.errorCode == 0) {// ok
				return this.list("0", "0", uuid, token);
			} else {
				request.setAttribute("msgCode", "11000");
				System.out.println("发布微博失败:"+result.errorCode+":errorMsg"+result.errorMsg);
				return "publish";
			}
		}catch(Exception e){		
			request.setAttribute("msgCode", "11000");
			request.setAttribute("msg", "发布微博失败");
			
			//e.printStackTrace();
			return "publish";
		}
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(@RequestParam String id, @RequestParam String path, 
			@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		String url = ClientUtil.createNewsUrl("/v1/news/post?postid="+id);
		PMessage message = ClientUtil.deleteMethod(url, null, cookies);

		PResult result = PResult.parseFrom(message.data);
		System.out.println("type: " + result.errorMsg + "; errCode: " + result.errorCode);
		if (result.errorCode == 0) {// ok
		
		} else {
			request.setAttribute("msgCode", "11001");
			request.setAttribute("msg", "删除微博失败:"+result.errorMsg);
		}

		if("collect".equals(path)) {
			return this.listcollect("0", "0", uuid, token);
		} else {
			return this.list("0", "0", uuid, token);
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(@RequestParam String offset, @RequestParam String isNext
			, @CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", uuid));
		params.add(new BasicNameValuePair("type", "all"));
		params.add(new BasicNameValuePair("offset", offset));
		System.out.println("offset: "+offset);
		String url = ClientUtil.createNewsUrl("/v1/news/home");
		PMessage message = ClientUtil.getMethod(url, params, cookies);

		PPostInfoList postInfoList = PPostInfoList.parseFrom(message.data);
		PPostInfo[] postInfoArray = postInfoList.list;
		
		for(PPostInfo postInfo: postInfoArray){
			articleMap.put(postInfo.id+"", postInfo);
		}
		
		request.setAttribute("list", postInfoArray);
		request.setAttribute("isNext", isNext);
		
		return "list";
	}

	@RequestMapping(value = "/listcollect.do", method = RequestMethod.GET)
	public String listcollect(@RequestParam String offset, @RequestParam String isNext,
			@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("uuid", uuid));
		params.add(new BasicNameValuePair("count", "10"));
		params.add(new BasicNameValuePair("offset", offset));

		String url = ClientUtil.createNewsUrl("/v1/news/collect");
		PMessage message = ClientUtil.getMethod(url, params, cookies);
		PResult result = PResult.parseFrom(message.data);
		System.out.println("result"+result.errorMsg);
		
		PPostInfoList postInfoList = PPostInfoList.parseFrom(message.data);
		PPostInfo[] postInfoArray = postInfoList.list;
		
		for(PPostInfo postInfo: postInfoArray){
			postInfo.collected = true;
			articleMap.put(postInfo.id+"", postInfo);
		}

		request.setAttribute("list", postInfoArray);
		request.setAttribute("isNext", isNext);
		
		return "collect";
	}
	
	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public String detail(@RequestParam String id, @CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		PPostInfo postInfo = articleMap.get(id);

		this.commentlist(postInfo.id, postInfo.uuid, uuid, token);;
		
		request.setAttribute("article", postInfo);
		
		return "detail";
	}

	// 收藏微博
	@RequestMapping(value = "/collect.do", method = RequestMethod.GET)
	public String collect(@RequestParam String id, @RequestParam String path,
			@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		String url = ClientUtil.createNewsUrl("/v1/news/collect?oper=1&postid="+id);
		PMessage message = ClientUtil.postMethod(url, null, cookies);

		PResult result = PResult.parseFrom(message.data);
		if (result.errorCode == 0) {// ok
			PPostInfo postInfo = articleMap.get(id);
			postInfo.collected = true;
			articleMap.put(id, postInfo);
		} else {
			request.setAttribute("msgCode", "11002");
			request.setAttribute("msg", "收藏微博失败:"+result.errorMsg);
		}
		
		if("collect".equals(path)) {
			return this.listcollect("0", "0", uuid, token);
		} else if("detail".equals(path)) {
			return this.detail(id, uuid, token);
		} else {
			return this.list("0", "0", uuid, token);
		}
	}
	
	// 取消收藏微博
	@RequestMapping(value = "/uncollect.do", method = RequestMethod.GET)
	public String uncollect(@RequestParam String id, @RequestParam String path,
			@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		String url = ClientUtil.createNewsUrl("/v1/news/collect?oper=0&postid="+id);
		PMessage message = ClientUtil.postMethod(url, null, cookies);

		PResult result = PResult.parseFrom(message.data);
		if (result.errorCode == 0) {// ok
			PPostInfo postInfo = articleMap.get(id);
			postInfo.collected = false;
			articleMap.put(id, postInfo);
		} else {
			request.setAttribute("msgCode", "11003");
			request.setAttribute("msg", "取消收藏微博失败:"+result.errorMsg);
		}
		
		if("collect".equals(path)) {
			return this.listcollect("0", "0", uuid, token);
		} else if("detail".equals(path)) {
			return this.detail(id, uuid, token);
		} else {
			return this.list("0", "0", uuid, token);
		}
	}	
	
	private void commentlist(long postid, String postuuid, @CookieValue String uuid, @CookieValue String token) throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("postuuid", postuuid));
		params.add(new BasicNameValuePair("postid", postid+""));
		params.add(new BasicNameValuePair("level", "0"));
		params.add(new BasicNameValuePair("offset", "0"));
		params.add(new BasicNameValuePair("count", "2000"));
		
		String url = ClientUtil.createCommentUrl("/comment/commentmore");
		PMessage message = ClientUtil.getMethod(url, params, cookies);

		PCommentLevel commentLevel = PCommentLevel.parseFrom(message.data);

		PComment[] commentList = commentLevel.comments;

		request.setAttribute("commentList", commentList);
	}
}