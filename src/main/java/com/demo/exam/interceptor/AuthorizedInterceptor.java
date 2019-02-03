package com.demo.exam.interceptor;

/**
 * use Spring security, not interceptor
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthorizedInterceptor implements HandlerInterceptor {

	public static final Logger log = Logger.getLogger(AuthorizedInterceptor.class);

	// before controller
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
		//log.debug("pre handle");
		HttpSession session = request.getSession();
		//log.debug("session id = "+session.getId());
		return true;
	}

	// after controller
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mv) {
		// log.debug("post handle");
	}

	// after render view to clean resource
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
		// log.debug("after completion");
	}
}
