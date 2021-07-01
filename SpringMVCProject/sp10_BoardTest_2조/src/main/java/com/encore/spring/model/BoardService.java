package com.encore.spring.model;

import java.util.List;

import com.encore.spring.domain.Board;

public interface BoardService {
	void insert(Board vo) throws Exception;
	List<Board> selectAll() throws Exception;
	Board selectOne(String no) throws Exception;
}
