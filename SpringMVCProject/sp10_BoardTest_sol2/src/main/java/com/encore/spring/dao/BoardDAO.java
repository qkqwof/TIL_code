package com.encore.spring.dao;

import java.util.List;

import com.encore.spring.vo.Board;

public interface BoardDAO {

	void insert(Board vo) throws Exception;
	List<Board> selectAll() throws Exception;
	Board selectOne(String no) throws Exception;
	void delete(String no) throws Exception;
	void update(Board vo) throws Exception;
	
}
