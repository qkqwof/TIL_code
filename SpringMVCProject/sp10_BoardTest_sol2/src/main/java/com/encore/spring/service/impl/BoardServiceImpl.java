package com.encore.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.spring.dao.BoardDAO;
import com.encore.spring.service.BoardService;
import com.encore.spring.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	public void insert(Board vo) throws Exception {
		boardDAO.insert(vo);
		
	}

	public List<Board> selectAll() throws Exception {

		return boardDAO.selectAll();
	}

	public Board selectOne(String no) throws Exception {

		return boardDAO.selectOne(no);
	}

	@Override
	public void delete(String no) throws Exception {
		boardDAO.delete(no);
		
	}

	@Override
	public void update(Board vo) throws Exception {
		boardDAO.update(vo);
		
	}

}
