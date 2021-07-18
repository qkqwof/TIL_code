package com.encore.spring.model.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.domain.MyBook;
import com.encore.spring.model.MyBookDAO;

@Repository
public class BookDAOImpl implements MyBookDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void addBook(MyBook book) throws Exception {
		System.out.println(book+"DAO");
		sqlSession.insert("bookMapper.addBook",book);
	}

	@Override
	public List<MyBook> getBookList() throws Exception {
		return sqlSession.selectList("bookMapper.getBookList");
	}
	
		
}
