package com.encore.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.board.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public int write(BoardVO vo) {
		int row = boardDAO.write(vo); // DAOImpl과 똑같이 하는 것보다 이렇게 넣어주는게 더 깔끔 (날짜의 경우)
		System.out.println("Before vo :: "+vo);
		
		String date = boardDAO.selectByNoForDate(vo.getNo()); 
		
		vo.setWriteDate(date);
		System.out.println("After vo :: "+vo); // Before와 다르게 날짜 출력 확인
		return row;
	}

	@Override
	public List<BoardVO> getBoardList() {
		return boardDAO.getBoardList();
	}

	@Override
	public BoardVO showContent(int no) {
		return boardDAO.showContent(no);
	}

	
	////////////////////////////////////////////////////
	@Override
	public void updateCount(int no) {
		boardDAO.updateCount(no);
	}
	///////////////////////////////////////////////////
	
	
	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(int no) {
		boardDAO.deleteBoard(no);
	}
}
