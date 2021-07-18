package com.encore.spring.model.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.domain.User;
import com.encore.spring.model.UserDAO;
@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SqlSession sqlSession;
	@Override
	public User login(User user) throws Exception {
		System.out.println(user);
		return sqlSession.selectOne("userMapper.Login",user);
		//sqlSession.commit();
		
	}

}
