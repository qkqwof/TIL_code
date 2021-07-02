package com.encore.board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	final String NS = "sql.board.mapper.";
	
	@Override
	public int write(BoardVO vo) {
		return sqlSession.insert(NS+"write", vo);
	}

	@Override
	public String selectByNoForDate(int no) {
		return sqlSession.selectOne(NS+"selectByNoForDate", no);
	}

	@Override
	public List<BoardVO> getBoardList() {
		return sqlSession.selectList(NS+"getBoardList");
	}

	@Override
	public BoardVO showContent(int no) {
		return sqlSession.selectOne(NS+"showContent", no);
	}

	@Override
	public void deleteBoard(int no) {
		sqlSession.delete(NS+"deleteBoard", no);
		
	}

	@Override
	public void updateCount(int no) {
		sqlSession.update(NS+"updateCount", no);
		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		sqlSession.update(NS+"updateBoard", vo);		
	}
}
