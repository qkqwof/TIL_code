package com.encore.spring.model;

import com.encore.spring.domain.User;

public interface UserDAO {
	User login(User user) throws Exception;
}
