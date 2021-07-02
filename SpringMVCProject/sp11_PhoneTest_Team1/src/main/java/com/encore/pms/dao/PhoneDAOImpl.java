package com.encore.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.pms.dto.Phone;
import com.encore.pms.dto.UserInfo;

@Repository
public class PhoneDAOImpl implements IPhoneDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	final String NS = "sql.pms.mapper.";

	@Override
	public int insert(Phone phone) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"insert",phone);
	}

	@Override
	public int delete(String num) {
		// TODO Auto-generated method stub
		return sqlSession.insert(NS+"delete",num);
	}

	@Override
	public Phone select(Phone phone) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"select",phone);
	}

	@Override
	public List<Phone> select() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NS+"select");
	}

	@Override
	public UserInfo selectUser(UserInfo user) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NS+"selectUser",user);
	}
	
	
}
