package com.demo.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.exam.entity.Paper;
import com.demo.exam.entity.Question;
import com.demo.exam.entity.User;
import com.demo.exam.repo.PaperRepo;
import com.demo.exam.repo.QuestionRepo;

@Controller
@SessionAttributes(types = { User.class }, value = { "user" })
public class PaperController {

	private static final Logger log = LoggerFactory.getLogger(PaperController.class);

	@Autowired
	QuestionRepo qRepo;

	@Autowired
	PaperRepo pRepo;

	// @ModelAttribute
	// public User getUserFromSession(ModelMap model, HttpServletRequest request) {
	// User user = new User();
	// if (model.get("user") != null)
	// user = (User) model.get("user");
	// return user;
	// }

	@RequestMapping("/generatePaper")
	public String generatePaper(@RequestParam(required = false, defaultValue = "5", value = "count") int count,
			ModelMap model) {
		User user;
		if (model.get("user") != null)
			user = (User) model.get("user");
		else
			return "index";
		if (user.getUsername() == null)
			return "index";		
		int total = qRepo.count();
		List<Question> qList = new ArrayList<Question>();
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int id = r.nextInt((int) total) + 1;
			qList.add(qRepo.findById(id));
		}
		Paper paper = new Paper();
		paper.setQuestions(qList);
		paper.setAnswers(new ArrayList<String>());
		model.addAttribute("paper", paper);

		// log.info("paper generated");
		return "paper";
	}

	@RequestMapping("/checkPaper")
	public String checkPaper(Paper paper, Model model) {

		return "forward:/";
	}
}
