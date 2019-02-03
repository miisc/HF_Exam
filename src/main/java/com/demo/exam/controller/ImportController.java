package com.demo.exam.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.exam.entity.Question;
import com.demo.exam.repo.QuestionRepo;
import com.demo.exam.util.ImportUtil;

@Controller
public class ImportController {
	private static final Logger log = LoggerFactory.getLogger(ImportController.class);

	QuestionRepo qRepo;

	public ImportController(QuestionRepo qRepo) {
		this.qRepo = qRepo;
	}

	@RequestMapping("/importExcel")
	public String Questions(Model model) {
		String filePath = "C:\\Users\\jine\\Downloads\\Book1.xlsx";
		List<Question> list = ImportUtil.importExcel(filePath, 0, 1, Question.class);
		qRepo.saveAll(list);
		int qCount = qRepo.count();
		model.addAttribute("qCount", qCount);

		log.info("Succeeded to import file " + filePath);
		return "index";
	}


}
