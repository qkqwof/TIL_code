package com.encore.spring.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.spring.domain.User;
import com.encore.spring.model.UserDAO;
import com.encore.spring.model.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Override
	public User login(User user) throws Exception {
		System.out.println(user);
		return userDAO.login(user);
	}

}
