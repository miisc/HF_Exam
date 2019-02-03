package com.demo.exam.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.exam.entity.User;

public class CookieUtil {

	private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

	public static boolean checkUserInCookie(Cookie[] cookies, User user) {
		if (cookies == null) {
			user.setUsername("");
			log.debug("no cookie now and user must login again!");
			return false;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username") && cookie.getValue() != null) {
				user.setUsername(cookie.getValue());
				log.debug("found username in cookie; username: " + user.getUsername());
				return true;
			}
		}
		return false;
	}

	public static User recoverUserFromCookie(Cookie[] cookies) {
		User user = new User();
		if (cookies == null) {
			user.setUsername("");
			return user;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username") && cookie.getValue() != null) {
				user.setUsername(cookie.getValue());
			}
			if (cookie.getName().equals("password") && cookie.getValue() != null) {
				user.setPassword(cookie.getValue());
			}
//			if (cookie.getName().equals("role") && cookie.getValue() != null) {
//				user.setRole(cookie.getValue());
//			}
		}
		return user;
	}

	public static boolean setUserInCookie(HttpServletResponse response, User user) {
		Cookie userNameCookie = new Cookie("username", user.getUsername());
		Cookie pwdCookie = new Cookie("password", user.getPassword());
//		Cookie roleCookie = new Cookie("role", user.getRole());
		userNameCookie.setMaxAge(3600);
		pwdCookie.setMaxAge(3600);
//		roleCookie.setMaxAge(3600);
		userNameCookie.setPath("/");
		pwdCookie.setPath("/");
//		roleCookie.setPath("/");
		response.addCookie(userNameCookie);
		response.addCookie(pwdCookie);
//		response.addCookie(roleCookie);
		return true;
	}

	public static boolean removeUserFromCookie(HttpServletResponse response, HttpServletRequest request,
			User loginUser) {
		Cookie userNameCookie = new Cookie("username", loginUser.getUsername());
		Cookie passwordCookie = new Cookie("password", loginUser.getPassword());
//		Cookie roleCookie = new Cookie("role", loginUser.getRole());
		userNameCookie.setMaxAge(0);
		userNameCookie.setPath("/");
		passwordCookie.setMaxAge(0);
		passwordCookie.setPath("/");
//		roleCookie.setMaxAge(0);
//		roleCookie.setPath("/");
		response.addCookie(userNameCookie);
		response.addCookie(passwordCookie);
//		response.addCookie(roleCookie);

		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("password");
		request.getSession().removeAttribute("role");
		return true;
	}
}
