package com.blemobi.weibo.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blemobi.sep.probuf.nano.LoginProtos.PLogin;
import com.blemobi.sep.probuf.nano.NewsProtos.PPersonStatistic;
import com.blemobi.sep.probuf.nano.ResultProtos.PMessage;
import com.blemobi.sep.probuf.nano.ResultProtos.PResult;
import com.blemobi.weibo.util.ClientUtil;
import com.blemobi.weibo.util.CommonUtil;
import com.blemobi.weibo.util.MD5;

@Controller
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password)
			throws ClientProtocolException, IOException {

		System.out.println("username: " + username + "; password: " + password);

		password = MD5.GetMD5Code(password + "3721");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("account", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("appid", "sep_web"));

		String url = ClientUtil.createLoginUrl("/v1/login/account");
		PMessage message = ClientUtil.getMethod(url, params, null);

		String type = message.type;
		if ("PLogin".equals(type)) {// ok
			PLogin login = PLogin.parseFrom(message.data);

			Cookie cookie1 = new Cookie("uuid", login.uuid);
			Cookie cookie2 = new Cookie("token", login.token);
			Cookie cookie3 = new Cookie("nickname", URLEncoder.encode(login.nickname, "utf-8"));
			Cookie cookie4 = new Cookie("imgURL", URLEncoder.encode(login.imgURL, "utf-8"));

			response.addCookie(cookie1);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
			response.addCookie(cookie4);

			return this.home(login.uuid, login.token);
		} else {
			request.setAttribute("msgCode", "10000");
			request.setAttribute("msg", "登录失败");
			
			return "login";
		}
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {
		System.out.println("uuid: " + uuid + "; token: " + token);

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		String url = ClientUtil.createLoginUrl("/v1/login/session");
		PMessage message = ClientUtil.deleteMethod(url, null, cookies);

		PResult result = PResult.parseFrom(message.data);
		if (result.errorCode == 0) {// ok
			Cookie cookie1 = new Cookie("uuid", null);
			Cookie cookie2 = new Cookie("token", null);
			Cookie cookie3 = new Cookie("nickname", null);
			Cookie cookie4 = new Cookie("imgURL", null);

			response.addCookie(cookie1);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
			response.addCookie(cookie4);
		}

		request.setAttribute("msgCode", "10001");
		request.setAttribute("msg", "退出登录成功");
		
		return "login";
	}

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(@CookieValue String uuid, @CookieValue String token)
			throws ClientProtocolException, IOException {

		Cookie[] cookies = CommonUtil.createLoginCookieParams(uuid, token);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", uuid));

		String url = ClientUtil.createNewsUrl("/v1/news/user/statistic");
		PMessage message = ClientUtil.getMethod(url, params, cookies);

		PPersonStatistic personStatistic = PPersonStatistic.parseFrom(message.data);

		request.setAttribute("person", personStatistic);

		return "home";
	}
}