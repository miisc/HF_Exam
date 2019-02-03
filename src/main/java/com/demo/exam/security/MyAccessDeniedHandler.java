package com.demo.exam.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse arg1, AccessDeniedException arg2)
			throws IOException, ServletException {
		log.info("access denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			log.info("User: " + auth.getName() + " role: " + auth.getAuthorities().toArray()[0] + " attempted to "
					+ request.getRequestURL());
		}
	}

}
