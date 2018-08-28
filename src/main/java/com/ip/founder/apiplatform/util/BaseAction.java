package com.ip.founder.apiplatform.util;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action Base
 *
 * @author yins
 */
public class BaseAction {
	/*************************** 新版Spring支持不写get与set方法，为了规范请不要写，由Spring组件去负责生成get与set ******************************/

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;

	/**
	 * 设置Cookie
	 *
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	protected void setCookie(String name, String value, int maxAge) {
		try {
			// 注意此处的 URLEncoder
			if (null != value)
				value = URLEncoder.encode(value, "UTF-8");
			Cookie cookienull = new Cookie(name, null);
			cookienull.setPath("/");
			cookienull.setMaxAge(0);
			this.response.addCookie(cookienull);
			Cookie cookie = new Cookie(name, value);
			cookie.setPath("/");
			if (maxAge > 0)
				cookie.setMaxAge(maxAge);
			this.response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置Cookie
	 *
	 * @param name
	 * @param value
	 */
	protected void setCookie(String name, String value) {
		this.setCookie(name, value, 0);
	}

	/**
	 * 获取Cookie
	 *
	 * @param name
	 * @return
	 */
	protected String getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 清空Cookie
	 *
	 * @param name
	 */
	protected void clearCookie(String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		this.response.addCookie(cookie);
	}

	/******************************* jxl Service声明部分 ******************************************/
	/*@Autowired
	protected PlatformService platformService;
	@Autowired
	protected UserServiceImpl userService;*/
}
