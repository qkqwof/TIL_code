package com.encore.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.spring.domain.Board;

@Transactional
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional
	public void insert(Board vo) throws Exception {
		boardDAO.insert(vo);
		
	}

	public List<Board> selectAll() throws Exception {

		return boardDAO.selectAll();
	}

	public Board selectOne(String no) throws Exception {

		return boardDAO.selectOne(no);
	}

}
