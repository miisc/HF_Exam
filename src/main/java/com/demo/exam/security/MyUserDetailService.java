package com.demo.exam.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.demo.exam.repo.UserRepo;

public class MyUserDetailService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(MyUserDetailService.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepo userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
		jdbcDao.setDataSource(dataSource);
		jdbcDao.setRolePrefix("");
		UserDetails userDetail = jdbcDao.loadUserByUsername(username);

		if (userDetail == null)
			throw new UsernameNotFoundException(username + " not found");

		log.info("user [" + userDetail.getUsername() + "] role with custom prefix: " + userDetail.getAuthorities());

		return userDetail;
	}

}
