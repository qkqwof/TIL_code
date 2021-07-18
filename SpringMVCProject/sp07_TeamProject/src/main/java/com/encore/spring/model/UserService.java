package com.encore.spring.model;

import com.encore.spring.domain.User;

public interface UserService {
	User login(User user) throws Exception;
}
