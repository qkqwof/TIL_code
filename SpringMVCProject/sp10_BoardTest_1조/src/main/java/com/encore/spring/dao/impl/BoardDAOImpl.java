package com.encore.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.dao.BoardDAO;
import com.encore.spring.vo.Board;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	final String ns = "BoardMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public void insert(Board vo) throws Exception {
		
		sqlSession.insert(ns+"insert", vo);
	}


	public List<Board> selectAll() throws Exception {
		List<Board> list = sqlSession.selectList(ns+"selectAll");
		for(Board b : list) {
			System.out.println(b);
		}
		return sqlSession.selectList(ns+"selectAll");
	}



	public Board selectOne(String no) throws Exception {

		return sqlSession.selectOne(ns+"selectOne", no);
	}


	@Override
	public void delete(String no) throws Exception {
		sqlSession.delete(ns+"delete", no);
		
	}


	@Override
	public void update(Board board) throws Exception {
		sqlSession.update(ns+"update", board);
		
	}

}
