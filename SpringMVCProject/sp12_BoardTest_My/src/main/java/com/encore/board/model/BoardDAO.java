package com.encore.board.model;

import java.util.List;

import com.encore.board.domain.BoardVO;

public interface BoardDAO {
	int write(BoardVO vo);
	String selectByNoForDate(int no);
	List<BoardVO> getBoardList();
	BoardVO showContent(int no);
	
	void updateCount(int no);
	
	void updateBoard(BoardVO vo);
	void deleteBoard(int no);
}
