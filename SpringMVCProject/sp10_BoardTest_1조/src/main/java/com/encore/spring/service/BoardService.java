package com.encore.spring.service;

import java.util.List;

import com.encore.spring.vo.Board;

public interface BoardService {
	void insert(Board vo) throws Exception;
	List<Board> selectAll() throws Exception;
	Board selectOne(String no) throws Exception;
	void delete(String no) throws Exception;
	void update(Board vo) throws Exception;
}
