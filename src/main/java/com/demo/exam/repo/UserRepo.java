package com.demo.exam.repo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo.exam.entity.User;

@Mapper
public interface UserRepo {

	@Select("select username,password,enabled from users where username = #{username}")
	public User getUserByUsername(@Param("username") String username);
}
