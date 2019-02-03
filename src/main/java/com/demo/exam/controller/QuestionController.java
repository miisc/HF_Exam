package com.demo.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.exam.entity.Question;
import com.demo.exam.entity.User;
import com.demo.exam.repo.PaperRepo;
import com.demo.exam.repo.QuestionRepo;
import com.demo.exam.util.Page;

@Controller
@RequestMapping("/question")
//@SessionAttributes(types = { User.class }, value = { "user" })
public class QuestionController {

	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	QuestionRepo qRepo;

	@Autowired
	PaperRepo pRepo;

//	@ModelAttribute
//	public User getUserFromSession(ModelMap model, HttpServletRequest request) {
//		
//		User user = new User();
//		if (model.get("user") != null)
//			user = (User) model.get("user");
//		return user;
//	}

	/*
	 * list question with default page parameters
	 * 
	 */
	@GetMapping("/list")
	public String list(@RequestParam(required = false, defaultValue = "1") int pageNumber,
			@RequestParam(required = false, defaultValue = "5") int pageSize, @RequestParam String questionType,
			//@ModelAttribute User user, 
			ModelMap model) {

		switch (questionType) {
		case "singleChoice":
			questionType = "单选";
			break;
		case "multipleChoice":
			questionType = "多选";
			break;
		case "trueFalse":
			questionType = "判断";
			break;
		default:
			questionType = "单选";
			break;
		}

		List<Question> questionList = qRepo.findAllByType(questionType);

		Page page = new Page(questionList.size(), pageNumber);
		page.setPageSize(pageSize);
		page.setPageNow(pageNumber);

		List<Question> questionListPerPage = questionList.subList((pageNumber - 1) * page.getPageSize(),
				pageNumber * page.getPageSize() >= questionList.size() ? questionList.size()
						: pageNumber * page.getPageSize());

		model.addAttribute("questionListPerPage", questionListPerPage);
		model.addAttribute("page", page);
		model.addAttribute("questionType", questionType);
		return "learning";
	}

	/**
	 * list question by page
	 * 
	 * @param pageNumber
	 *            current page number
	 * @param pageSize
	 *            question number per page
	 * @param questionType
	 *            question type: single, multiple, true or false
	 * @return question list as JSON file
	 */
	@RequestMapping("/listByPage")
	// @RequestParam int pageNumber, @RequestParam int pageSize,@RequestParam String
	// questionType
	public @ResponseBody Map<String, Object> listQuestionByPage(@RequestParam Map<String, String> params) {
		Integer pageNumber = Integer.valueOf(params.get("pageNumber"));
		Integer pageSize = Integer.valueOf(params.get("pageSize"));
		String questionType = params.get("questionType");

		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questionList = qRepo.findAllByType(questionType);
		Page page = new Page(questionList.size(), pageNumber);
		page.setPageSize(pageSize);
		page.setPageNow(pageNumber);

		List<Question> questionListPerPage = questionList.subList((pageNumber - 1) * page.getPageSize(),
				pageNumber * page.getPageSize() >= questionList.size() ? questionList.size()
						: pageNumber * page.getPageSize());
		map.put("questionListPerPage", questionListPerPage);
		map.put("page", page);
		// log.info("list by page");
		return map;
	}

	/*
	 * next question
	 */
	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String nextQuestion(Model model) {
		int total = (int) qRepo.count();
		Random r = new Random();
		int id = r.nextInt((int) total) + 1;
		Question question = qRepo.findById(id);
		model.addAttribute(question);
		return "practice";
	}

}
