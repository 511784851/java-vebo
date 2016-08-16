package com.blemobi.weibo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.blemobi.sep.probuf.nano.NewsProtos.PFollowOrFansList;
import com.blemobi.sep.probuf.nano.NewsProtos.PRecommendUser;
import com.blemobi.sep.probuf.nano.ResultProtos.PMessage;
import com.blemobi.weibo.util.ClientUtil;
import com.blemobi.weibo.util.CommonUtil;

@Controller
public class CommentController {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/followers.do", method = RequestMethod.GET)
	public String followers(@RequestParam String count, @CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", uuid));
		params.add(new BasicNameValuePair("count", count));
		params.add(new BasicNameValuePair("cursor", "0"));

		String url = ClientUtil.createNewsUrl("/v1/news/follow");
		PMessage message = ClientUtil.getMethod(url, params, cookies);

		PFollowOrFansList followOrFansList = PFollowOrFansList.parseFrom(message.data);

		PRecommendUser[] list = followOrFansList.list;

		int _count = Integer.parseInt(count) + 20;

		request.setAttribute("list", list);
		request.setAttribute("count", _count);

		return "followers";
	}

	@RequestMapping(value = "/fans.do", method = RequestMethod.GET)
	public String fans(@RequestParam String count, @CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", uuid));
		params.add(new BasicNameValuePair("count", count));
		params.add(new BasicNameValuePair("cursor", "0"));

		String url = ClientUtil.createNewsUrl("/v1/news/fans");
		PMessage message = ClientUtil.getMethod(url, params, cookies);

		PFollowOrFansList followOrFansList = PFollowOrFansList.parseFrom(message.data);

		PRecommendUser[] list = followOrFansList.list;

		int _count = Integer.parseInt(count) + 20;

		request.setAttribute("list", list);
		request.setAttribute("count", _count);

		return "fans";
	}
}