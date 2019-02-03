package com.demo.exam.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.exam.entity.User;
import com.demo.exam.repo.QuestionRepo;
import com.demo.exam.repo.UserRepo;
import com.demo.exam.util.CookieUtil;

@Controller
@SessionAttributes(types = { User.class }, value = { "user" })
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	QuestionRepo qRepo;

	@Autowired
	UserRepo userService;

	public IndexController(QuestionRepo qRepo) {
		this.qRepo = qRepo;
	}

	/*
	 * No cookie or no UserName in cookie: first login, show login form Found
	 * cookie: show user name, no login form and auto login
	 */
	@RequestMapping("/index")
	public String index(User user, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Cookie[] cookies = request.getCookies();
		if (CookieUtil.checkUserInCookie(cookies, user)) {
			model.addAttribute("user", user);
		}
		model.addAttribute("singleQuestionCount", qRepo.countByType("单选"));
		model.addAttribute("multipleQuestionCount", qRepo.countByType("多选"));
		model.addAttribute("trueFalseQuestionCount", qRepo.countByType("判断"));
		return "index";
	}

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}
}
