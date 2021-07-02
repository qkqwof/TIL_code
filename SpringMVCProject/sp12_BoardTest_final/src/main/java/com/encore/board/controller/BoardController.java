package com.encore.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encore.board.domain.BoardVO;
import com.encore.board.domain.MemberVO;
import com.encore.board.model.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("write.do")
	public String write(BoardVO bvo, HttpSession session, Model model) throws Exception{
		MemberVO mvo=(MemberVO)session.getAttribute("mvo");
			if(mvo==null) return "redierct:index.jsp";
			try {
			bvo.setMemberVO(mvo);
			model.addAttribute("bvo", bvo);
			boardService.write(bvo);
			
			
			System.out.println("BVO::"+bvo);
			return "board/show_content";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 작성 중 에러 발생했습니다.");
			return "Error";
		}
		
	}
	@RequestMapping("list.do")
	public String list(Model model) throws Exception{
		try {
			List<BoardVO> list=boardService.getBoardList();
			model.addAttribute("list", list);
			return "board/list";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 검색 중 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@RequestMapping("showContent.do")
	public String showDetail(HttpSession session, int no,Model model) throws Exception{
		if(session.getAttribute("mvo")==null) 
			return "redirect:index.jsp";
		try {
			boardService.updateCount(no);
			
			BoardVO bvo=boardService.showContent(no);
			model.addAttribute("bvo", bvo);
			return "board/show_content";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 상세 보기 중 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@RequestMapping("delete.do")
	public String doDelete(int no, Model model) throws Exception{
		try {
			boardService.deleteBoard(no);
			
			List<BoardVO> list=boardService.getBoardList();
			
			model.addAttribute("list", list);
			return "board/list";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 삭제 중 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@RequestMapping("updateView.do")
	public String updateForm(int no, Model model) throws Exception{
		try {
			BoardVO bvo=boardService.showContent(no);
			model.addAttribute("bvo", bvo);
			return "board/update";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 수정폼에서 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@RequestMapping("updateBoard.do")
	public String updateBoard(BoardVO vo, Model model) throws Exception{
		try {
			boardService.updateBoard(vo);
			
			BoardVO bvo=boardService.showContent(vo.getNo());
			model.addAttribute("bvo", bvo);
			
			return "board/show_content";
		}catch(Exception e) {
			model.addAttribute("message", "Spring Board - 게시글 수정중 에러 발생했습니다.");
			return "Error";
		}
	}
}









