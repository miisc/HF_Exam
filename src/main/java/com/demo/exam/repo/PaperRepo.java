package com.demo.exam.repo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.demo.exam.entity.Paper;
import com.demo.exam.entity.Question;

@Mapper
public interface PaperRepo {

	/*
	 * @return: generated primary key
	 */
	@Insert({ "insert into paper(name, score) values(#{name},  #{score})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertPaper(Paper paper);
	
	
	public void sallFailedQuestion(List<Question> list, int paperID);
}
